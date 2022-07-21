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
package kr.co.sitglobal.oklms.la.mail.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

//import kr.co.sitglobal.oklms.comm.util.DateTimeUtil;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.mail.service.MailService;
import kr.co.sitglobal.oklms.la.mail.vo.MailVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 /**
 * 
 * @author 
 * @since 2016. 7. 15.
 */
@Controller
public class MailController extends BaseController{

	@Resource(name = "mailService")
	private MailService mailService;
	
	@Resource(name = "commonCodeService")
	private CommonCodeService commonCodeService;
	
	@Resource(name = "beanValidatorJSR303")
	Validator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		try {
			dataBinder.setValidator(this.validator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/la/mail/mail/listMailHistory.do")
	public String listMailHistory(@ModelAttribute("frmMail") MailVO mailVO, ModelMap model) throws Exception {
		
		Integer pageSize = 10;
		
		List<MailVO> listMailHistory = mailService.listMailHistory( mailVO );
		
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup( "MAIL_CLASS" );
		List<CommonCodeVO> mailClassCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		Integer page = mailVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < listMailHistory.size() ){
			
			totalCnt = Integer.parseInt( listMailHistory.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
		

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(mailVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mailVO.getPageUnit());
        paginationInfo.setPageSize(mailVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("mailVO", mailVO);
        model.addAttribute("listMailHistory", listMailHistory);
        model.addAttribute("mailClassCodeList", mailClassCodeList);
        
		// View호출
		return "oklms/la/mail/mailHistoryList";
	}
	

	@RequestMapping(value = "/la/mail/mail/getMailHistory.do")
	public String getMailHistory(@ModelAttribute("frmMail") MailVO mailVO,  ModelMap model ) throws Exception {
		
		mailVO = mailService.getMailHistory( mailVO );

        model.addAttribute("mailVO", mailVO);

		// View호출
		return "oklms/la/mail/mailHistoryRead";
	}
	
	
	@RequestMapping(value = "/la/mail/mail/updateMailHistory.do")
	public String updateMailHistory(@ModelAttribute("frmMail") MailVO mailVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		String retMsg = "입력값을 확인해주세요.";
		
		if( StringUtils.isBlank( mailVO.getHistoryId()) ){
			retMsg = "수정할 정보가 없습니다.";
		}else{
			
			int updateCnt = mailService.updateMailHistory( mailVO );
			
			if(updateCnt > 0){
				retMsg = "정상적으로 (삭제)처리되었습니다.!";
			} 
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("mailVO", mailVO);
        
		// View호출
        return "redirect:/la/mail/mail/listMailHistory.do";
		
	}
	
	
	@RequestMapping(value = "/la/mail/mail/listSmsHistory.do")
	public String listSmsHistory(@ModelAttribute("frmMail") MailVO mailVO, ModelMap model) throws Exception {
		
		Integer pageSize = 10;
		
		if( StringUtils.isBlank( mailVO.getSearchScLogTable() ) ){
			//mailVO.setScLogTable("SC_LOG_"+DateTimeUtil.getDateTimeByPattern("yyyyMM"));
			//mailVO.setSearchScLogTable("SC_LOG_"+DateTimeUtil.getDateTimeByPattern("yyyyMM"));
			
			mailVO.setScLogTable("SC_LOG");
			mailVO.setSearchScLogTable("SC_LOG");
		}else{
			mailVO.setScLogTable(mailVO.getSearchScLogTable());
		}
		
		
		List<MailVO> listSmsHistory = mailService.listSmsHistory( mailVO );
		
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup( "SMS_TABLE" );
		List<CommonCodeVO> smsTableList = commonCodeService.selectSmsTableList(codeVO);
		
		Integer page = mailVO.getPageIndex();
		int totalCnt = 0;
		if( 0 < listSmsHistory.size() ){
			
			totalCnt = Integer.parseInt( listSmsHistory.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
		

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(mailVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(mailVO.getPageUnit());
        paginationInfo.setPageSize(mailVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("mailVO", mailVO);
        model.addAttribute("listSmsHistory", listSmsHistory);
        model.addAttribute("smsTableList", smsTableList);
        
		// View호출
		return "oklms/la/mail/smsHistoryList";
	}
	

	@RequestMapping(value = "/la/mail/mail/getSmsHistory.do")
	public String getSmsHistory(@ModelAttribute("frmMail") MailVO mailVO,  ModelMap model , HttpServletRequest request) throws Exception {
		
		
		mailVO.setScLogTable(mailVO.getSearchScLogTable());
		mailVO = mailService.getSmsHistory( mailVO );

        model.addAttribute("mailVO", mailVO);

		// View호출
		return "oklms/la/mail/smsHistoryRead";
	}
	
	
	@RequestMapping(value = "/la/mail/mail/goSaveSendSms.do")
	public String goSendSms(@ModelAttribute("frmMail") MailVO mailVO,  ModelMap model , HttpServletRequest request) throws Exception {
		
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup( "SMS_GROUP" );
		List<CommonCodeVO> smsGroupCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		mailVO.setTrCallback(EgovProperties.getProperty("Globals.sms.sender.default.phoneno"));
		model.addAttribute("smsGroupCodeList", smsGroupCodeList);
        model.addAttribute("mailVO", mailVO);

		// View호출
		return "oklms/la/mail/smsSendSave";
	}
	
	@RequestMapping(value = "/la/mail/mail/saveSendSms.do")
	public String saveSendSms(@ModelAttribute("frmMail") MailVO mailVO,  ModelMap model , HttpServletRequest request) throws Exception {
		
		
		String sender = this.getStringParameter(request, "trCallBack", EgovProperties.getProperty("Globals.sms.sender.default.phoneno"));
		String reservation = this.getStringParameter(request, "reservation", "");
		String useFlag = this.getStringParameter(request, "tFlag", "0");
		String smsContent = this.getStringParameter(request, "smsContent", "");
		String retMsg = "";		

		mailVO.setTrCallback(sender);
		mailVO.setTrPhone(mailVO.getTrPhone());
		mailVO.setTrSenddate(reservation);
		mailVO.settFlag(useFlag);
		mailVO.setTrMsgtype("0");
		mailVO.setTrMsg(smsContent);
		mailVO.setSenderMemSeq(mailVO.getSenderMemSeq());		//보내는 사람 SEQ
		
		int insertCnt = mailService.insertSendSms( mailVO );

		if( 0 < insertCnt ){
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		model.addAttribute("retMsg", retMsg);
        model.addAttribute("sendSuccess", "send");
        model.addAttribute("mailVO", mailVO);

		// View호출
        return "oklms/la/mail/smsSendSave";
	}
	
	// 회원목록에서 SMS보내기 버튼 클릭시 실행하는 메소드
	@RequestMapping(value = "/la/popup/popup/popupMemberInfoSaveSendSms.do")
	public String popupMemberInfoSaveSendSms(@ModelAttribute("frmMail") MailVO mailVO,  ModelMap model , HttpServletRequest request) throws Exception {
		
		String sender = this.getStringParameter(request, "trCallBack", EgovProperties.getProperty("Globals.sms.sender.default.phoneno"));
		String reservation = this.getStringParameter(request, "reservation", "");
		String useFlag = this.getStringParameter(request, "tFlag", "0");
		String smsContent = this.getStringParameter(request, "smsContent", "");
		String retMsg = "";		

		mailVO.setTrCallback(sender);
		mailVO.setTrPhone(mailVO.getTrPhone());
		mailVO.setTrSenddate(reservation);
		mailVO.settFlag(useFlag);
		mailVO.setTrMsgtype("0");
		mailVO.setTrMsg(smsContent);
		mailVO.setSenderMemSeq(mailVO.getSenderMemSeq());		//보내는 사람 SEQ
		
		int insertCnt = mailService.insertSendSms( mailVO );

		if( 0 < insertCnt ){
			retMsg = "Y";
		}else{
			retMsg = "N";
		}
		
		//model.addAttribute("retMsg", retMsg);
        model.addAttribute("sendSuccess", retMsg);
        model.addAttribute("mailVO", mailVO);

		// View호출
        return "oklms_popup/la/popup/smsSendPopup";
	}
	
	
	/**
     * request의 parameter값을 가져옴
     * 
     * @param request HttpServletRequest
     * @param key parateter key값
     * @param defaultValue 값이 없을경우의 기본값
     * @return String parameter value
     */
    protected String getStringParameter(HttpServletRequest request, String key,
            String defaultValue) {
        return ServletRequestUtils.getStringParameter(request, key,
                defaultValue);

    }
}
