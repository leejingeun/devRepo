
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 최선호    2016. 9. 05.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.mail.vo;

import java.io.Serializable;
import java.util.ArrayList;

import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.service.EgovProperties;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 최선호
 * @since 2016. 9. 05.
 */
public class SmsVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6287536631734724947L;
	
	
	private String trNum;
	private String trSenddate;          //메시지를 전송할 시간, 미래시간을 넣으면 예약 발송
	private String trId;           		//관리번호
	private String trSendstat;          //발송상태 :::::::::::::      '0' = 발송대기, '1' = 전송완료, '2' = 결과수신완료
	private String trRsltstat;          //발송 결과 수신 값
	private String trMsgtype;           //문자 전송형태 '0' = 일반메시지, '1' = 콜백 URL메시지
	private String trPhone;           	//수신할 핸드폰 번호
	private String trCallBack;          //송신할 핸드폰 번호
	private String trMsg;           	//전송할 메시지
	private String tFlag;           	//예약문자 전송 여부 0=미사용, 1=사용
	private String trRsltdate;
	private String trModified;
	private String lecNo;          		//공지사항 분반원 핸드폰번호 가져올 변수
	private String classNo;          	//공지사항 분반원 핸드폰번호 가져올 변수
	private String smsFlag;           	//공지사항 분반원 핸드폰번호 가져올 변수
	private String smsType;           	//문자 서비스 타입
	private String trNet;
	private String trEtc1;				//보내는사람 SEQ
	private String trEtc2;				//받는사람 SEQ
	private String trEtc3;				//받는사람 수강아이디(LESSON_ID)
	private String trEtc4;
	private String trEtc5;
	private String trEtc6;
	private String reservation;
	private String smsContent;
	
	private String scLogTable ;			//2011년 6월 이후 로그테이블이 월단위로 생김 (ex: "sc_log_201104"형식)
	
	private String memType;
	private String lecId;
	//검색 필드 
	private String searchCallback;
	private String searchTrPhone;
	private String searchStartDate;
	private String searchEndDate;
    private String[] memSeqs;           //받는사람 고유번호  
    private String recvMemSeqSet = "";  //받는 사람 시퀀스 집합
    private String receiveName = "";    //받는 사람 이름    
    private String receiveMemSeq = "";    //받는 사람 관리번호    
    private String receiverLessonId;		//수신자수강아이디
    private String sendName = "";       //보내는 사람 이름
    private String senderMemSeq;			//발신자회원번호
    private String classId = "";		//반아이디
    private String periodId = "";		//기수아이디
    private String lessonId = "";		//수강아이디
    private String examId = "";			//시험아이디
    private String reportId = "";		//과제아이디
    private String smsGroup = "";		//sms그룹
    private String searchScLogTable;	//로그테이블검색
    
    private String searchCateDepth1 ="";//대분류
    private String searchCateDepth2 ="";//소분류
    
    private String searchLecId ="";			//강의아이디검색
    private String searchPeriodId ="";		//기수아이디검색
    private String searchClassId="";		//반아이디검색
    private String searchSendName="";		//보낸사람검색
    private String searchReceiveName="";	//받는사람검색
    
    private String examDate="";	//시험일자
    private String examNo=""; //수험번호
    
    private String boardDtlId=""; //게시판번호
    
    //SMS엑셀보내기
	private MultipartFile uploadFile; // 업로드 회원 파일
	private long uploadSize = 1024 * 200; // 파일 업로드 사이즈

//	private String examId = "";				// 자격시험 접수자, 자격증 접수자 메일 발송
    private String qfeType = "";				// 자격시험 : E, 자격증 : C
    private String receiverReferenceId = "";		// 수신자 시험접수 or 자격증접수 아이디
    private String receiverHp = "";			// 수신자 핸드폰 번호
    private String paymentStatus = "";
    
	/**
	 * @return the reportId
	 */
	public String getReportId() {
		return TextStringUtil.fixNull(reportId);
	}
	/**
	 * @param reportId the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return TextStringUtil.fixNull(classId);
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * @return the periodId
	 */
	public String getPeriodId() {
		return TextStringUtil.fixNull(periodId);
	}
	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	/**
	 * @return the memSeqs
	 */
	public String[] getMemSeqs() {
		return memSeqs;
	}
	/**
	 * @param memSeqs the memSeqs to set
	 */
	public void setMemSeqs(String[] memSeqs) {
		this.memSeqs = memSeqs;
	}
	/**
	 * @return the classNo
	 */
	public String getClassNo() {
		return TextStringUtil.fixNull(classNo);
	}
	/**
	 * @param classNo the classNo to set
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	/**
	 * @return the lec_no
	 */
	public String getLecNo() {
		return TextStringUtil.fixNull(lecNo);
	}
	/**
	 * @param lec_no the lec_no to set
	 */
	public void setLecNo(String lecNo) {
		this.lecNo = lecNo;
	}
	/**
	 * @return the searchEndDate
	 */
	public String getSearchEndDate() {
		return TextStringUtil.fixNull(searchEndDate);
	}
	/**
	 * @param searchEndDate the searchEndDate to set
	 */
	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	/**
	 * @return the searchStartDate
	 */
	public String getSearchStartDate() {
		return TextStringUtil.fixNull(searchStartDate);
	}
	/**
	 * @param searchStartDate the searchStartDate to set
	 */
	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}
	/**
	 * @return the searchTrPhone
	 */
	public String getSearchTrCallback() {
		return TextStringUtil.fixNull(searchCallback);
	}
	/**
	 * @param searchTrPhone the searchTrPhone to set
	 */
	public void setSearchTrCallback(String searchCallback) {
		this.searchCallback = searchCallback;
	}
	/**
	 * @return the smsFlag
	 */
	public String getSmsFlag() {
		return TextStringUtil.fixNull(smsFlag);
	}
	/**
	 * @param smsFlag the smsFlag to set
	 */
	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}
	/**
	 * @return the smsType
	 */
	public String getSmsType() {
		return TextStringUtil.fixNull(smsType);
	}
	/**
	 * @param smsType the smsType to set
	 */
	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	/**
	 * @return the trEtc1
	 */
	public String getTrEtc1() {
		return TextStringUtil.fixNull(trEtc1);
	}
	/**
	 * @param trEtc1 the trEtc1 to set
	 */
	public void setTrEtc1(String trEtc1) {
		this.trEtc1 = trEtc1;
	}
	/**
	 * @return the trEtc2
	 */
	public String getTrEtc2() {
		return TextStringUtil.fixNull(trEtc2);
	}
	/**
	 * @param trEtc2 the trEtc2 to set
	 */
	public void setTrEtc2(String trEtc2) {
		this.trEtc2 = trEtc2;
	}
	/**
	 * @return the trEtc3
	 */
	public String getTrEtc3() {
		return TextStringUtil.fixNull(trEtc3);
	}
	/**
	 * @param trEtc3 the trEtc3 to set
	 */
	public void setTrEtc3(String trEtc3) {
		this.trEtc3 = trEtc3;
	}
	/**
	 * @return the trEtc4
	 */
	public String getTrEtc4() {
		return TextStringUtil.fixNull(trEtc4);
	}
	/**
	 * @param trEtc4 the trEtc4 to set
	 */
	public void setTrEtc4(String trEtc4) {
		this.trEtc4 = trEtc4;
	}
	/**
	 * @return the trEtc5
	 */
	public String getTrEtc5() {
		return TextStringUtil.fixNull(trEtc5);
	}
	/**
	 * @param trEtc5 the trEtc5 to set
	 */
	public void setTrEtc5(String trEtc5) {
		this.trEtc5 = trEtc5;
	}
	/**
	 * @return the trEtc6
	 */
	public String getTrEtc6() {
		return TextStringUtil.fixNull(trEtc6);
	}
	/**
	 * @param trEtc6 the trEtc6 to set
	 */
	public void setTrEtc6(String trEtc6) {
		this.trEtc6 = trEtc6;
	}
	/**
	 * @return the trId
	 */
	public String getTrId() {
		return TextStringUtil.fixNull(trId);
	}
	/**
	 * @param trId the trId to set
	 */
	public void setTrId(String trId) {
		this.trId = trId;
	}
	/**
	 * @return the trModified
	 */
	public String getTrModified() {
		return TextStringUtil.fixNull(trModified);
	}
	/**
	 * @param trModified the trModified to set
	 */
	public void setTrModified(String trModified) {
		this.trModified = trModified;
	}
	/**
	 * @return the trMsg
	 */
	public String getTrMsg() {
		return TextStringUtil.fixNull(trMsg);
	}
    /**
     * 줄바꿈 처리
     * @return the content
     */
    public String getTrMsgAsHtml() {
        return TextStringUtil.getHtmlString(trMsg);
    }
	/**
	 * @param trMsg the trMsg to set
	 */
	public void setTrMsg(String trMsg) {
		this.trMsg = trMsg;
	}
	/**
	 * @return the trMsgType
	 */
	public String getTrMsgtype() {
		return TextStringUtil.fixNull(trMsgtype);
	}
	/**
	 * @param trMsgType the trMsgType to set
	 */
	public void setTrMsgtype(String trMsgtype) {
		this.trMsgtype = trMsgtype;
	}
	/**
	 * @return the trNet
	 */
	public String getTrNet() {
		return TextStringUtil.fixNull(trNet);
	}
	/**
	 * @param trNet the trNet to set
	 */
	public void setTrNet(String trNet) {
		this.trNet = trNet;
	}
	/**
	 * @return the trNum
	 */
	public String getTrNum() {
		return TextStringUtil.fixNull(trNum);
	}
	/**
	 * @param trNum the trNum to set
	 */
	public void setTrNum(String trNum) {
		this.trNum = trNum;
	}
	/**
	 * @return the trPhone
	 */
	public String getTrPhone() {
		return TextStringUtil.fixNull(trPhone);
	}
	/**
	 * @param trPhone the trPhone to set
	 */
	public void setTrPhone(String trPhone) {
		this.trPhone = trPhone;
	}
	/**
	 * @return the trRsltdate
	 */
	public String getTrRsltdate() {
		return TextStringUtil.fixNull(trRsltdate);
	}
	/**
	 * @param trRsltdate the trRsltdate to set
	 */
	public void setTrRsltdate(String trRsltdate) {
		this.trRsltdate = trRsltdate;
	}
	/**
	 * @return the trRsltStat
	 */
	public String getTrRsltstat() {
		return TextStringUtil.fixNull(trRsltstat);
	}
	/**
	 * @param trRsltStat the trRsltStat to set
	 */
	public void setTrRsltstat(String trRsltstat) {
		this.trRsltstat = trRsltstat;
	}
	/**
	 * @return the trSendDate
	 */
	public String getTrSenddate() {
		return TextStringUtil.fixNull(trSenddate);
	}
	/**
	 * @param trSendDate the trSendDate to set
	 */
	public void setTrSenddate(String trSenddate) {
		this.trSenddate = trSenddate;
	}
	/**
	 * @return the trSendStat
	 */
	public String getTrSendstat() {
		return TextStringUtil.fixNull(trSendstat);
	}
	/**
	 * @param trSendStat the trSendStat to set
	 */
	public void setTrSendstat(String trSendstat) {
		this.trSendstat = trSendstat;
	}
	/**
	 * @return the memType
	 */
	public String getMemType() {
		return TextStringUtil.fixNull(memType);
	}
	/**
	 * @param memType the memType to set
	 */
	public void setMemType(String memType) {
		this.memType = memType;
	}
	/**
	 * @return the lecId
	 */
	public String getLecId() {
		return TextStringUtil.fixNull(lecId);
	}
	/**
	 * @param lecId the lecId to set
	 */
	public void setLecId(String lecId) {
		this.lecId = lecId;
	}
	/**
	 * @return the receiveName
	 */
	public String getReceiveName() {
		return TextStringUtil.fixNull(receiveName);
	}
	/**
	 * @param receiveName the receiveName to set
	 */
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	/**
	 * @return the receiverLessonId
	 */
	public String getReceiverLessonId() {
		return TextStringUtil.fixNull(receiverLessonId);
	}
	/**
	 * @param receiverLessonId the receiverLessonId to set
	 */
	public void setReceiverLessonId(String receiverLessonId) {
		this.receiverLessonId = receiverLessonId;
	}
	/**
	 * @return the recvMemSeqSet
	 */
	public String getRecvMemSeqSet() {
		return TextStringUtil.fixNull(recvMemSeqSet);
	}
	/**
	 * @param recvMemSeqSet the recvMemSeqSet to set
	 */
	public void setRecvMemSeqSet(String recvMemSeqSet) {
		this.recvMemSeqSet = recvMemSeqSet;
	}
	/**
	 * @return the senderMemSeq
	 */
	public String getSenderMemSeq() {
		return TextStringUtil.fixNull(senderMemSeq);
	}
	/**
	 * @param senderMemSeq the senderMemSeq to set
	 */
	public void setSenderMemSeq(String senderMemSeq) {
		this.senderMemSeq = senderMemSeq;
	}
	/**
	 * @return the sendName
	 */
	public String getSendName() {
		return TextStringUtil.fixNull(sendName);
	}
	/**
	 * @param sendName the sendName to set
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	/**
	 * @return the lessonId
	 */
	public String getLessonId() {
		return TextStringUtil.fixNull(lessonId);
	}
	/**
	 * @param lessonId the lessonId to set
	 */
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	/**
	 * @return the receiveMemSeq
	 */
	public String getReceiveMemSeq() {
		return TextStringUtil.fixNull(receiveMemSeq);
	}
	/**
	 * @param receiveMemSeq the receiveMemSeq to set
	 */
	public void setReceiveMemSeq(String receiveMemSeq) {
		this.receiveMemSeq = receiveMemSeq;
	}
	/**
	 * @return the examId
	 */
	public String getExamId() {
		return TextStringUtil.fixNull(examId);
	}
	/**
	 * @param examId the examId to set
	 */
	public void setExamId(String examId) {
		this.examId = examId;
	}
	/**
	 * @return the searchTrPhone
	 */
	public String getSearchTrPhone() {
		return TextStringUtil.fixNull(searchTrPhone);
	}
	/**
	 * @param searchTrPhone the searchTrPhone to set
	 */
	public void setSearchTrPhone(String searchTrPhone) {
		this.searchTrPhone = searchTrPhone;
	}
	/**
	 * @return the smsGroup
	 */
	public String getSmsGroup() {
		return TextStringUtil.fixNull(smsGroup);
	}
	/**
	 * @param smsGroup the smsGroup to set
	 */
	public void setSmsGroup(String smsGroup) {
		this.smsGroup = smsGroup;
	}
	/**
	 * @return the scLogTable
	 */
	public String getScLogTable() {
		return TextStringUtil.fixNull(scLogTable);
	}
	/**
	 * @param scLogTable the scLogTable to set
	 */
	public void setScLogTable(String scLogTable) {
		this.scLogTable = scLogTable;
	}
	/**
	 * @return the searchCateDepth1
	 */
	public String getSearchCateDepth1() {
		return TextStringUtil.fixNull(searchCateDepth1);
	}
	/**
	 * @param searchCateDepth1 the searchCateDepth1 to set
	 */
	public void setSearchCateDepth1(String searchCateDepth1) {
		this.searchCateDepth1 = searchCateDepth1;
	}
	/**
	 * @return the searchCateDepth2
	 */
	public String getSearchCateDepth2() {
		return TextStringUtil.fixNull(searchCateDepth2);
	}
	/**
	 * @param searchCateDepth2 the searchCateDepth2 to set
	 */
	public void setSearchCateDepth2(String searchCateDepth2) {
		this.searchCateDepth2 = searchCateDepth2;
	}
	/**
	 * @return the searchCallback
	 */
	public String getSearchCallback() {
		return TextStringUtil.fixNull(searchCallback);
	}
	/**
	 * @param searchCallback the searchCallback to set
	 */
	public void setSearchCallback(String searchCallback) {
		this.searchCallback = searchCallback;
	}
	/**
	 * @return the searchLecId
	 */
	public String getSearchLecId() {
		return TextStringUtil.fixNull(searchLecId);
	}
	/**
	 * @param searchLecId the searchLecId to set
	 */
	public void setSearchLecId(String searchLecId) {
		this.searchLecId = searchLecId;
	}
	/**
	 * @return the searchPeriodId
	 */
	public String getSearchPeriodId() {
		return TextStringUtil.fixNull(searchPeriodId);
	}
	/**
	 * @param searchPeriodId the searchPeriodId to set
	 */
	public void setSearchPeriodId(String searchPeriodId) {
		this.searchPeriodId = searchPeriodId;
	}
	/**
	 * @return the searchClassId
	 */
	public String getSearchClassId() {
		return TextStringUtil.fixNull(searchClassId);
	}
	/**
	 * @param searchClassId the searchClassId to set
	 */
	public void setSearchClassId(String searchClassId) {
		this.searchClassId = searchClassId;
	}
	/**
	 * @return the searchSendName
	 */
	public String getSearchSendName() {
		return TextStringUtil.fixNull(searchSendName);
	}
	/**
	 * @param searchSendName the searchSendName to set
	 */
	public void setSearchSendName(String searchSendName) {
		this.searchSendName = searchSendName;
	}
	/**
	 * @return the searchReceiveName
	 */
	public String getSearchReceiveName() {
		return TextStringUtil.fixNull(searchReceiveName);
	}
	/**
	 * @param searchReceiveName the searchReceiveName to set
	 */
	public void setSearchReceiveName(String searchReceiveName) {
		this.searchReceiveName = searchReceiveName;
	}	
	
	/**
	 * @return the searchScLogTable
	 */
	public String getSearchScLogTable() {
		return TextStringUtil.fixNull(searchScLogTable);
	}
	/**
	 * @param searchScLogTable the searchScLogTable to set
	 */
	public void setSearchScLogTable(String searchScLogTable) {
		this.searchScLogTable = searchScLogTable;
	}
	
	/**
	 * @return the examDate
	 */
	public String getExamDate() {
		return TextStringUtil.fixNull(examDate);
	}
	/**
	 * @param examDate the examDate to set
	 */
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	/**
	 * @return the examNo
	 */
	public String getExamNo() {
		return TextStringUtil.fixNull(examNo);
	}
	/**
	 * @param examNo the examNo to set
	 */
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public long getUploadSize() {
		return uploadSize;
	}
	public void setUploadSize(long uploadSize) {
		this.uploadSize = uploadSize;
	}
	public String getBoardDtlId() {
		return TextStringUtil.fixNull(boardDtlId);
	}
	public void setBoardDtlId(String boardDtlId) {
		this.boardDtlId = boardDtlId;
	}
	/**
	 * @return the qfeType
	 */
	public String getQfeType() {
		return TextStringUtil.fixNull(qfeType);
	}
	/**
	 * @param qfeType the qfeType to set
	 */
	public void setQfeType(String qfeType) {
		this.qfeType = qfeType;
	}
	/**
	 * @return the receiverReferenceId
	 */
	public String getReceiverReferenceId() {
		return TextStringUtil.fixNull(receiverReferenceId);
	}
	/**
	 * @param receiverReferenceId the receiverReferenceId to set
	 */
	public void setReceiverReferenceId(String receiverReferenceId) {
		this.receiverReferenceId = receiverReferenceId;
	}
	/**
	 * @return the receiverHp
	 */
	public String getReceiverHp() {
		return TextStringUtil.fixNull(receiverHp);
	}
	/**
	 * @param receiverHp the receiverHp to set
	 */
	public void setReceiverHp(String receiverHp) {
		this.receiverHp = receiverHp;
	}
	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return TextStringUtil.fixNull(paymentStatus);
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public String getTrCallBack() {
		return trCallBack;
	}
	public void setTrCallBack(String trCallBack) {
		this.trCallBack = trCallBack;
	}
	public String gettFlag() {
		return tFlag;
	}
	public void settFlag(String tFlag) {
		this.tFlag = tFlag;
	}
	
}
