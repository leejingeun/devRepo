/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.member.web;

import java.util.List;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.member.service.MemberService;
import kr.co.sitglobal.oklms.la.member.vo.ExcelMemberVO;
import kr.co.sitglobal.oklms.la.member.vo.MemberVO;
import kr.co.sitglobal.oklms.comm.util.Config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import egovframework.com.cmm.util.EgovDoubleSubmitHelper;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
/**
* Controller 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 10. 27.
*/
@Controller
public class MemberController extends BaseController{

	@Resource(name = "memberService")
	private MemberService memberService;
	
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
	
	@RequestMapping(value = "/la/member/member/listMember.do")
	public String listMember(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model) throws Exception {
		
		CommonCodeVO codeVO = new CommonCodeVO();
		
		//수정화면에서 업데이트시 memId항목이 필수값이므로 목록으로 포워딩시 Null로 초기화하는 로직추가함. - jglee
		memberVO.setMemId("");
		List<MemberVO> resultList = memberService.listMember( memberVO );
		
		//회원유형 공통코드 조회
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> searchAuthGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
		
		//회원검색 공통코드 조회
		codeVO.setCodeGroup("SEARCH_MEMBER");
		List<CommonCodeVO> searchMemberCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//탈퇴여부 공통코드 조회
		codeVO.setCodeGroup("SCSN_YN");
		List<CommonCodeVO> searchScsnYnCodeList = commonCodeService.selectCmmCodeList(codeVO);

		Integer pageSize = memberVO.getPageSize();
		Integer page = memberVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
        paginationInfo.setPageSize(memberVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("memberVO", memberVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("searchAuthGroupCode", searchAuthGroupCodeList);
        model.addAttribute("searchMemberCode", searchMemberCodeList);
        model.addAttribute("searchScsnYnCode", searchScsnYnCodeList);
        
		// View호출
		return "oklms/la/member/memberList";
	}
	
	@RequestMapping(value = "/la/member/member/listMemberExcelDownload.do")
	public String listMemberExcelDownload(@ModelAttribute("frmMember") ExcelMemberVO excelMemberVO, ModelMap model, HttpServletRequest request) throws Exception {
		
		excelMemberVO.setPageSize(10000); // 1만건 조회
		List<ExcelMemberVO> resultList = memberService.listMemberAllExcelList( excelMemberVO );
		
        model.addAttribute("resultList", resultList);
        request.setAttribute("ExcelName", URLEncoder.encode( "회원관리정보".replaceAll(" ", "_") ,"UTF-8") );
        
		// View호출
		return "oklms_excel/la/member/memberExcelList";
	}
	
	@RequestMapping(value = "/la/member/member/listTutorMember.do")
	public String listTutorMember(@ModelAttribute("frmTutorMember") MemberVO memberVO, ModelMap model) throws Exception {
		
		if( StringUtils.isBlank( memberVO.getSearchAuthGroupId() ) ){
			//memberVO.setSearchAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);
		}
		
		List<MemberVO> resultList = memberService.listMember( memberVO );
		
		//회원검색 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("SEARCH_MEMBER");
		List<CommonCodeVO> searchMemberCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//탈퇴여부 공통코드 조회
		codeVO.setCodeGroup("SCSN_YN");
		List<CommonCodeVO> searchScsnYnCodeList = commonCodeService.selectCmmCodeList(codeVO);

		Integer pageSize = memberVO.getPageSize();
		Integer page = memberVO.getPageIndex();
		
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
        paginationInfo.setPageSize(memberVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);

        model.addAttribute("memberVO", memberVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("searchMemberCode", searchMemberCodeList);
        model.addAttribute("searchScsnYnCode", searchScsnYnCodeList);
        
		// View호출
		return "oklms/la/member/tutorMemberList";
	}
	
	@RequestMapping(value = "/la/member/member/listExcelMember.do")
	public String listExcelMember(@ModelAttribute("frmMemberExcel") ExcelMemberVO excelMemberVO, ModelMap model) throws Exception {
		
		List<ExcelMemberVO> resultList = memberService.listMemberAllExcelList( excelMemberVO );
		
        model.addAttribute("memberVO", excelMemberVO);
        model.addAttribute("resultList", resultList);
        
		// View호출
		return "oklms/la/member/memberAllCretExcelList";
	}

	@RequestMapping(value = "/la/member/member/getMember.do")
	public String getMember(@ModelAttribute("frmMember") MemberVO memberVO,  ModelMap model) throws Exception {
		memberVO = memberService.getMember( memberVO );

        model.addAttribute("memberVO", memberVO);

		// View호출
		return "oklms/la/member/memberRead";
	}
	
	@RequestMapping(value = "/la/member/member/getTutorMember.do")
	public String getTutorMember(@ModelAttribute("frmTutorMember") MemberVO memberVO,  ModelMap model) throws Exception {
		memberVO = memberService.getMember( memberVO );

        model.addAttribute("memberVO", memberVO);

		// View호출
		return "oklms/la/member/tutorMemberRead";
	}

	@RequestMapping(value = "/la/member/member/goInsertMember.do")
	public String goInsertMember(@ModelAttribute("frmMember") MemberVO memberVO,  ModelMap model)  throws Exception {
		
		//회원유형 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
		
		//지역번호 공통코드 조회
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("authGroupCode", authGroupCodeList);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);
		
		return "oklms/la/member/memberCret";	
	}
	
	@RequestMapping(value = "/la/member/member/goInsertTutorMember.do")
	public String goInsertTutorMember(@ModelAttribute("frmTutorMember") MemberVO memberVO,  ModelMap model)  throws Exception {
		
		//지역번호 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		model.addAttribute("memberVO", memberVO);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);
		
		return "oklms/la/member/tutorMemberCret";	
	}
	
	@RequestMapping(value = "/la/member/member/goUpdateMember.do")
	public String goUpdateMember(@ModelAttribute("frmMember") MemberVO memberVO,  ModelMap model) throws Exception {
		
		memberVO = memberService.getMember( memberVO );
		
		//회원유형 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("member");
		List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
		
		//지역번호 공통코드 조회
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

        model.addAttribute("memberVO", memberVO);
        model.addAttribute("authGroupCode", authGroupCodeList);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);
        
		return "oklms/la/member/memberUpdt";	
	}
	
	@RequestMapping(value = "/la/member/member/goUpdateTutorMember.do")
	public String goUpdateTutorMember(@ModelAttribute("frmTutorMember") MemberVO memberVO,  ModelMap model) throws Exception {
		
		memberVO = memberService.getMember( memberVO );
		
		//지역번호 공통코드 조회
		CommonCodeVO codeVO = new CommonCodeVO();
		codeVO.setCodeGroup("LOCAL_NUM");
		List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//핸드폰번호 공통코드 조회
		codeVO.setCodeGroup("MOBILE_NUM");
		List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력년도 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_YEAR");
		List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//달력월 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_MONTH");
		List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
				
		//달력일 공통코드 조회
		codeVO.setCodeGroup("CALENDAR_DAY");
		List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
		
		//이메일 도메인 공통코드 조회
		codeVO.setCodeGroup("EMAIL_DOMAIN");
		List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);

        model.addAttribute("memberVO", memberVO);
		model.addAttribute("localTelNoCode", localTelNoCodeList);
		model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
		model.addAttribute("yearCode", yearCodeList);
		model.addAttribute("monthCode", monthCodeList);
		model.addAttribute("dayCode", dayCodeList);
		model.addAttribute("emailDomainCode", emailDomainCodeList);
        
		return "oklms/la/member/tutorMemberUpdt";	
	}
	
	@RequestMapping(value = "/la/member/member/insertMember.do")
	public String insertMember(@ModelAttribute("frmMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){
				
				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			//회원유형 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("member");
			List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
			
			//지역번호 공통코드 조회
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("authGroupCode", authGroupCodeList);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);
			
			return "oklms/la/member/memberCret";
		}
		
	    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMemberToken")) {  
	        String insertPK = memberService.insertMember(memberVO);
	        retMsg = "정상적으로 (저장)처리되었습니다.!";
	    //}
		
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/insertTutorMember.do")
	public String insertTutorMember(@ModelAttribute("frmTutorMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		String retMsg = "입력값을 확인해주세요";
		
		//memberVO.setAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){
				
				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			//지역번호 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);
			
			return "oklms/la/member/tutorMemberCret";
		}
		
	    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmTutorMemberToken")) {
	        String insertPK = memberService.insertMember(memberVO);
	        retMsg = "정상적으로 (저장)처리되었습니다.!";
	    //}
		
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listTutorMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/insertExcelMember.do")
	public String insertExcelMember(@ModelAttribute("frmMemberExcel") @Valid ExcelMemberVO excelMemberVO, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status, MultipartHttpServletRequest multiRequest) throws Exception {
		
		String retMsg = "";
		
		 String result = memberService.insertMemberAllExcel(excelMemberVO, multiRequest);
		 
		 if(result == "SUCCESS"){
			 retMsg = "정상적으로 회원을 일괄업로드 처리되었습니다.!";
		 }else if(result == "FAIL"){
			 retMsg = "회원 일괄등록시 에러가 발생하였습니다.!";
		 }else{
			 retMsg = "("+ result + ")" + "이미등록된 회원입니다.";
		 }
	     
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		
		return "redirect:/la/member/member/listMember.do";
	}

	@RequestMapping(value = "/la/member/member/updateMember.do")
	public String updateMember(@ModelAttribute("frmMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			//회원유형 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("member");
			List<CommonCodeVO> authGroupCodeList = commonCodeService.selectAuthGroupCodeList(codeVO);
			
			//지역번호 공통코드 조회
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("authGroupCode", authGroupCodeList);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);
			
			return "oklms/la/member/memberUpdt";
		}
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
		    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMemberToken")) {  
		    	
		    	int updateCnt = memberService.updateMember( memberVO );
		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		    //}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/updateTutorMember.do")
	public String updateTutorMember(@ModelAttribute("frmTutorMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		
		//memberVO.setAuthGroupId(Config.DEFAULT_AUTH_INS_NEW);

		if (bindingResult.hasErrors()) {

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			//지역번호 공통코드 조회
			CommonCodeVO codeVO = new CommonCodeVO();
			codeVO.setCodeGroup("LOCAL_NUM");
			List<CommonCodeVO> localTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//핸드폰번호 공통코드 조회
			codeVO.setCodeGroup("MOBILE_NUM");
			List<CommonCodeVO> mobileTelNoCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력년도 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_YEAR");
			List<CommonCodeVO> yearCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//달력월 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_MONTH");
			List<CommonCodeVO> monthCodeList = commonCodeService.selectCmmCodeList(codeVO);
					
			//달력일 공통코드 조회
			codeVO.setCodeGroup("CALENDAR_DAY");
			List<CommonCodeVO> dayCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			//이메일 도메인 공통코드 조회
			codeVO.setCodeGroup("EMAIL_DOMAIN");
			List<CommonCodeVO> emailDomainCodeList = commonCodeService.selectCmmCodeList(codeVO);
			
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("localTelNoCode", localTelNoCodeList);
			model.addAttribute("mobileTelNoCode", mobileTelNoCodeList);
			model.addAttribute("yearCode", yearCodeList);
			model.addAttribute("monthCode", monthCodeList);
			model.addAttribute("dayCode", dayCodeList);
			model.addAttribute("emailDomainCode", emailDomainCodeList);
			model.addAttribute("retMsg", retMsg);
			
			return "oklms/la/member/tutorMemberUpdt";
		}
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
		    //if (EgovDoubleSubmitHelper.checkAndSaveToken("frmTutorMemberToken")) {  
		    	int updateCnt = memberService.updateMember( memberVO );
		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
		    //}
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listTutorMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/updateMemberPassWordInit.do")
	public String updateMemberPassWordInit(@ModelAttribute("frmMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) && StringUtils.isBlank( memberVO.getMemId() )){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			int updateCnt = memberService.updateMemberPassWordInit( memberVO );
			if(updateCnt > 0){
				retMsg = "정상적으로 (초기화)처리되었습니다.!";
			}else{
				retMsg = "초기화된 비밀번호가 없습니다.!";
			}
	    	
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/updateTutorMemberPassWordInit.do")
	public String updateTutorMemberPassWordInit(@ModelAttribute("frmTutorMember") @Valid MemberVO memberVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) && StringUtils.isBlank( memberVO.getMemId() )){
			retMsg = "존재하지 않는 정보입니다.";
		}else{
			int updateCnt = memberService.updateMemberPassWordInit( memberVO );
			if(updateCnt > 0){
				retMsg = "정상적으로 (초기화)처리되었습니다.!";
			}else{
				retMsg = "초기화된 비밀번호가 없습니다.!";
			}
	    	
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listTutorMember.do";
	}

	@RequestMapping(value = "/la/member/member/deleteMemberList.do")
	public String deleteMemberList(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			memberVO.setMemSeqs(memberVO.getMemSeq().split(","));
			
			int deletetCnt = memberService.deleteMemberList( memberVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/deleteTutorMemberList.do")
	public String deleteTutorMemberList(@ModelAttribute("frmTutorMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			memberVO.setMemSeqs(memberVO.getMemSeq().split(","));
			
			int deletetCnt = memberService.deleteMemberList( memberVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listTutorMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/deleteMember.do")
	public String deletetMember(@ModelAttribute("frmMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			int deletetCnt = memberService.deleteMember( memberVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listMember.do";
	}
	
	@RequestMapping(value = "/la/member/member/deleteTutorMember.do")
	public String deletetTutorMember(@ModelAttribute("frmTutorMember") MemberVO memberVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";
		
		if( StringUtils.isBlank( memberVO.getMemSeq() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			int deletetCnt = memberService.deleteMember( memberVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/member/member/listTutorMember.do";
	}
	
}
