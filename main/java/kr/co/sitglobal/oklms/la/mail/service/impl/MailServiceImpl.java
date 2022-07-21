
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 14.         First Draft.( Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.mail.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.util.CommonUtil;
import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.util.FileUploadUtil;
import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.commbiz.util.SendMail;
import kr.co.sitglobal.oklms.la.mail.service.MailService;
import kr.co.sitglobal.oklms.la.mail.vo.MailVO;
import kr.co.sitglobal.oklms.la.mail.vo.SmsVO;
import kr.co.sitglobal.oklms.la.member.vo.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
 * @author 최선호
 * @since 2016. 9. 05.
 */
@Transactional(rollbackFor=Exception.class)
@Service("mailService")
public class MailServiceImpl extends EgovAbstractServiceImpl implements MailService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
	
	//메일첨부파일 업로드 경로
    private String uploadPath = "/upload/mail";
    private MultipartFile mFile;
    
    @Resource(name = "mailMapper")
    private MailMapper mailMapper;
    
	/** ID Generation */
    @Resource(name="mailIdGnrService")
    private EgovIdGnrService mailIdGnrService;
    
    @Override
	public List<MailVO> listMailHistory(MailVO mailVO) throws Exception {
		// TODO Auto-generated method stub
		List<MailVO> data = mailMapper.listMailHistory(mailVO);
		return data;
	}
    
    @Override
	public List<MailVO> listSmsHistory(MailVO mailVO) throws Exception {
		// TODO Auto-generated method stub
		List<MailVO> data = mailMapper.listSmsHistory(mailVO);
		return data;
	}
	
    @Override
    public MailVO getMailHistory(MailVO mailVO) throws Exception{
		// TODO Auto-generated method stub
        return mailMapper.getMailHistory(mailVO);
    }
    
    @Override
    public MailVO getSmsHistory(MailVO mailVO) throws Exception{
		// TODO Auto-generated method stub
        return mailMapper.getSmsHistory(mailVO);
    }
    
    @Override
    public String getMemSeqEmailSet(MailVO mailVO) throws Exception{
    	String recieveMemSeq = "";
    	List memberList = mailMapper.listMemberMail(mailVO);
        if (memberList!=null && memberList.size()>0){
            for(int idx=0 ; idx<memberList.size() ; idx++){
            	MemberVO member = (MemberVO) memberList.get(idx);
                recieveMemSeq += member.getMemSeq();
                if (idx<memberList.size()-1){
                    recieveMemSeq += ",";
                }
            }
        }
        return recieveMemSeq;
    }
    
    @Override
    public String getMemTypeEmailSet(MailVO mailVO) throws Exception{
    	String emailSet = "";
    	List memberList = mailMapper.listMemberMail(mailVO);
    	if (memberList!=null && memberList.size()>0){
            for(int idx=0 ; idx<memberList.size() ; idx++){
                MemberVO member = (MemberVO) memberList.get(idx);
                emailSet += member.getEmail();
                if (idx<memberList.size()-1){
                    emailSet += ","; 
                }
            }
        }
        return emailSet;
    }
    
    @Override
    public String sendMail(MailVO mailVO) throws Exception{
    	String strClassId = "";
    	String smtpHost = EgovProperties.getProperty("mail.smtp.host");    	
        String sendName = mailVO.getSendName();
        String sendEmail = mailVO.getSendEmail();
        String senderMemSeq = mailVO.getSenderMemSeq();
        String mailClass = mailVO.getMailClass();
        String sendResult = "Y";
        int msgType = mailVO.getMsgType();
        String recvEmailSet = "";
        String recvMemSeqSet = "";
        String recvLessonIdSet = "";
        String[] receiveEmail = null;
        //수신자 회원번호
        String[] receiverMemSeq = null;
        String[] receiverLessonId = null;
        strClassId = mailVO.getClassId();
        if (!"".equals(strClassId) && strClassId != null){            
            List mailList = mailMapper.listMail(mailVO);
            if( mailList != null && mailList.size() > 0 ) {
                receiveEmail = new String[mailList.size()];
                receiverMemSeq = new String[mailList.size()];
                receiverLessonId = new String[mailList.size()];
                for( int idx = 0 ; idx < mailList.size() ; idx++ ) {
                    MailVO mailVOStd = (MailVO) mailList.get( idx );
                    receiveEmail[idx] = mailVOStd.getReceiveEmail();
                    receiverMemSeq[idx] = mailVOStd.getReceiveUser();
                    receiverLessonId[idx] = mailVOStd.getReceiverLessonId();
                }
            }
        }else{
            recvEmailSet = mailVO.getRecvEmailSet();
            recvMemSeqSet = mailVO.getRecvMemSeqSet();
            recvLessonIdSet = mailVO.getReceiverLessonId();
            receiveEmail = recvEmailSet.split(",");
            receiverMemSeq = recvMemSeqSet.split(",");
            if (!StringUtils.isBlank(recvLessonIdSet) ) {
            	receiverLessonId = recvLessonIdSet.split(",");
            } 
        }

        String mailTitle = mailVO.getMailTitle();
        String mailContent = "";
        mailContent = mailVO.getMailContent();
        
      //첨부파일이 존재할 경우
        if (mailVO.hasFile()) {
            ArrayList aFileList = mailVO.getAttachFile();
            int fileCnt = aFileList.size();

            String tempFileName;
            String realUploadPath = CommonUtil.appendPath(Config.realPath,uploadPath );
            //String fileLocalPath=sitglobalProperties.get("system.mail.file.path")+uploadPath+File.separator;

            for (int idx = 0; idx < fileCnt; idx++) {
                mFile = (MultipartFile) aFileList.get(idx);;

                if (!mFile.isEmpty()) {
                    tempFileName = FileUploadUtil.uploadFormFileAsRealName(mFile, realUploadPath );
                    mailVO.setFilePath( realUploadPath +File.separator+ tempFileName);
                    mailVO.setFileName( tempFileName);
                }
            }
        }
        String filePath = mailVO.getFilePath();
        
        try{
            SendMail sendmail = new SendMail(smtpHost);

            //본문인코딩방식,제목 인코딩방식
            sendmail.setEncoding("euc-kr","euc-kr");

            //보내는 사람 주소
            sendmail.setFromAddr(new String(sendName.getBytes("euc-kr"),"8859_1")+"<"+sendEmail+">");

            //0-받는사람,1-참고,2-숨은참고
            sendmail.setRecType(0);

            //메일 방식, 0-텍스트, 1-html
            sendmail.setMsgType(msgType);

            //제목 셋팅
            sendmail.setSubject(mailTitle);

            //내용셋팅
            sendmail.setText(mailContent);

            //첨부파일경로셋팅
            sendmail.setFilePath(filePath);

            //보내는 사람 수 만큼 메일 발송
            for(int idx = 0 ; idx < receiveEmail.length ; idx++ ){
            	if( !StringUtils.isBlank( receiverMemSeq[idx]) ){
            		// 이메일 수신 여부에 상관없이 무조건 보내야 하는 메일이 있는 경우 때문에 수정 (회원인증 메일 등)
            		String receiveMailYn = "";
            		if("Y".equals(mailVO.getReceiveMailYnBypass())){
            			receiveMailYn = "Y";
            		}else{
            			receiveMailYn = mailMapper.getReceiveMailYn(receiverMemSeq[idx]);
            		}

            		if(receiveMailYn.equals("Y")){
                        if( !StringUtils.isBlank( receiveEmail[idx]) ){
                        	sendmail.setToAddr(receiveEmail[idx].trim());
                        	sendmail.send();
                        }
            		}
            	}else{
            		// 이메일 수신 여부에 상관없이 무조건 보내야 하는 메일이 있는 경우 때문에 수정 (회원인증 메일 등)
            		String receiveMailYn = "";
            		if("Y".equals(mailVO.getReceiveMailYnBypass())){
            			receiveMailYn = "Y";
            		}

            		if(receiveMailYn.equals("Y")){
                        if( !StringUtils.isBlank( receiveEmail[idx]) ){                        	
                        	sendmail.setToAddr(receiveEmail[idx].trim());
                        	sendmail.send();
                        }
            		}
            	}
            }

            return sendResult;
        }catch(Exception e){
        	LOGGER.error( e.getMessage(), e );
            sendResult = "N";
            return sendResult;
        }finally{
            if (mailVO.hasFile()) FileUploadUtil.deleteFormFile(mFile, filePath);
            //메일 전송후 메일 이력 테이블에 저장
            insertMailHistory(mailVO, mailTitle, sendEmail, receiveEmail, sendResult, senderMemSeq, receiverMemSeq, mailClass, receiverLessonId);
        }
    }
    
    public void insertMailHistory(MailVO mailVO, String mailTitle, String senderEmail,
    		String[] receiverEmail, String sendResult, String senderMemSeq,
    		String[] receiverMemSeq, String mailClass, String[] receiverLessonId) throws Exception{
    	
    	if(receiverEmail != null){
    		for(int i=0;i<receiverEmail.length;i++){
    			mailVO.setHistoryId(mailIdGnrService.getNextStringId());
    			mailVO.setSenderMemSeq(senderMemSeq);
    			mailVO.setSenderEmail(senderEmail);
        		mailVO.setReceiverMemSeq(receiverMemSeq[i]);
        		mailVO.setReceiverEmail(receiverEmail[i]);
        		mailVO.setSendSuccessYn(sendResult);
        		mailVO.setMailClass(mailClass);
        		mailVO.setMailTitle(mailTitle);
        		mailVO.setInsertUser(senderMemSeq);
        		if(receiverLessonId != null){
        			mailVO.setReceiverLessonId(receiverLessonId[i]);
        		}
        		mailMapper.insertMailHistory(mailVO);
        	}
    	}
    }
    
    @Override
    public int updateMailHistory(MailVO mailVO) throws Exception{
		// TODO Auto-generated method stub
        return mailMapper.updateMailHistory(mailVO);
    }
    
    
    @Override
    public int insertSendSms(MailVO mailVO) throws Exception{
		// TODO Auto-generated method stub
    	/*
    	String date = "";
    	if ("".equals(mailVO.getTrSenddate()) || mailVO.getTrSenddate() == null || "0".equals(mailVO.gettFlag())) {
			date = "SYSDATE";
		} else if ("1".equals(mailVO.gettFlag())) {
			date = " TO_DATE('" + mailVO.getTrSenddate() + "', 'YYYYMMDDHH24MISS') ";
		}
    	
    	mailVO.setTrSenddate(date);
    	*/
    	String strReceiver = mailVO.getTrPhone();
    	String [] receiverArr = null;
    	String receiver = "";
    	strReceiver = strReceiver.replaceAll("-", "");
        int result = 0;
    	receiverArr = TextStringUtil.split(strReceiver, ",");
    	
    	//공지사항 sms 보내기
	    for (int i = 0; i < receiverArr.length; i++) {
	    	
			String pkStr = mailIdGnrService.getNextIntegerId()+"";
			mailVO.setTrNum(pkStr);
			
	    	receiver = receiverArr[i];

	    	mailVO.setTrPhone(receiver);
	    	mailVO.setTrEtc1(mailVO.getSenderMemSeq());
	    	
            result++; 
            mailMapper.insertSendSms(mailVO);
	    	            
    	}
    	
        return result;
    }
    
    @Override
    public int insertSms(SmsVO sms) throws Exception{
    	String strReceiver = sms.getTrPhone();
    	String recvMemSeq = sms.getRecvMemSeqSet();
    	String lessonId = sms.getReceiverLessonId();
    	String [] receiverArr = null;
    	String [] recvMemSeqArr = null;
    	String [] receiverLessonIdArr = null;
    	String receiver = "";
    	String receiverMemSeq = "";
    	String receiverLessonId = "";
    	List hpList = null;
    	strReceiver = strReceiver.replaceAll("-", "");
        int result = 0;

    	receiverArr = TextStringUtil.split(strReceiver, ","); 
    	recvMemSeqArr = TextStringUtil.split(recvMemSeq, ",");
    	if(!"".equals(lessonId)){
    		receiverLessonIdArr = TextStringUtil.split(lessonId, ",");
    	}
    	//공지사항 sms 보내기
	    for (int i = 0; i < receiverArr.length; i++) {
	    	receiver = receiverArr[i];
	    	receiverMemSeq = recvMemSeqArr[i];
	    	if(receiverLessonIdArr != null){
	    		receiverLessonId = receiverLessonIdArr[i];
	    	}
	    	//SMS 수신동의 여부에 따라 처리
	    	String receiveSmsYn = mailMapper.getReceiveSmsYn(receiverMemSeq);
	    	if (!StringUtils.isBlank(receiveSmsYn) ) {
	    		if(receiveSmsYn.equals("Y")){
	    			String pkStr = mailIdGnrService.getNextIntegerId()+"";
	    			sms.setTrNum(pkStr);
	    	    	sms.setTrPhone(receiver);
	    			sms.setTrEtc1(sms.getSenderMemSeq());
	    			sms.setTrEtc2(receiverMemSeq);
	    			sms.setTrEtc3(receiverLessonId);
	    			sms.settFlag("0");
	                result += mailMapper.insertSms(sms);
	    		}
	    	}
    	}
        return result;
    }
    
}
