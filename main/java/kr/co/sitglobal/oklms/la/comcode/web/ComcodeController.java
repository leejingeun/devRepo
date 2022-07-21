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
package kr.co.sitglobal.oklms.la.comcode.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import kr.co.sitglobal.aunuri.service.AunuriLinkService;
import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.comcode.service.ComcodeService;
import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;
import kr.co.sitglobal.oklms.la.link.service.LinkService;
import kr.co.sitglobal.oklms.la.member.service.MemberService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
 /**
 * 
 * @author 
 * @since 2016. 07. 01.
 */
@Controller
public class ComcodeController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(ComcodeController.class);

	@Resource(name = "comcodeService")
	private ComcodeService comcodeService;
	
	//=== 임시추가======================
	@Resource(name = "linkService")
	private LinkService linkService;
	// =============================
	
	
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
	
//	// /la/comcode/comcode/listBasic.do
//	// /la/comcode/Comcode/listCode.do
	@RequestMapping(value = "/la/comcode/comcode/listComcode.do")
	public String listComcode(@ModelAttribute("frmComcode") ComcodeVO comcodeVO, ModelMap model) throws Exception {
		
		LOG.debug("listComcode : comcodeVO=" + comcodeVO.toString() );
		
		List<ComcodeVO> comcodeGroupList = comcodeService.listComcodeGroup( new ComcodeVO() );
		List<ComcodeVO> resultList = comcodeService.listComcode( comcodeVO );


		// {pageSize=10, pageIndex=1, searchPrototypeTitle=}
		Integer pageSize = comcodeVO.getPageSize();
		Integer page = comcodeVO.getPageIndex();
//		int totalCnt = comcodeService.getComcodeCnt(comcodeVO);
		int totalCnt = 0;
		
		if( 0 < resultList.size() ){
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
		

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(comcodeVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(comcodeVO.getPageUnit());
        paginationInfo.setPageSize(comcodeVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        
        

        model.addAttribute("comcodeVO", comcodeVO);
        model.addAttribute("resultList", resultList);
        model.addAttribute("comcodeGroupList", comcodeGroupList);
        model.addAttribute("searchCodeGroup", comcodeVO.getSearchCodeGroup());
        model.addAttribute("searchCodeName", comcodeVO.getSearchCodeName());
        
		// View호출
		return "oklms/la/comcode/comcodeList";
	}

//	@RequestMapping(value = "/la/comcode/comcode/getComcode.do")
//	public String getComcode(@ModelAttribute("frmComcode") ComcodeVO comcodeVO,  ModelMap model , HttpServletRequest request) throws Exception {
//		comcodeVO = comcodeService.getComcode( comcodeVO );
//
//        model.addAttribute("comcodeVO", comcodeVO);
//
//		// View호출
//		return "oklms/la/comcode/comcodeRead";
//	}
//
//	@RequestMapping(value = "/la/comcode/comcode/goInsertComcode.do")
//	public String goInsertComcode(ModelMap model) throws Exception {
//		return "oklms/la/comcode/comcodeCret";	
//	}
//	
//	@RequestMapping(value = "/la/comcode/comcode/insertComcode.do")
//	public String insertComcode(@ModelAttribute("frmComcode") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//		
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("comcodeVO", comcodeVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//				
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/comcode/comcodeCret";
//		}
//		
//	    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmComcodeToken")) {  
//	        String insertPK = comcodeService.insertComcode(comcodeVO);
//	        retMsg = "정상적으로 (저장)처리되었습니다.!";
//	    }
//		
//		
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/comcode/comcode/listComcode.do";
//	}
//
//	@RequestMapping(value = "/la/comcode/comcode/goUpdateComcode.do")
//	public String goUpdateComcode(@ModelAttribute("frmComcode") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model) throws Exception {
//		comcodeVO = comcodeService.getComcode( comcodeVO );
//
//        model.addAttribute("comcodeVO", comcodeVO);
//		return "oklms/la/comcode/comcodeUpdt";	
//	}
//
//	@RequestMapping(value = "/la/comcode/comcode/updateComcode.do")
//	public String updateComcode(@ModelAttribute("frmComcode") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("comcodeVO", comcodeVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/comcode/comcodeCret";
//		}
//		
//		if( StringUtils.isBlank( comcodeVO.getCodeId() ) ){
//			retMsg = "존재하지 않는 정보입니다.";
//		}else{
//		    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmComcodeToken")) {  
//		    	
//		    	int updateCnt = comcodeService.updateComcode( comcodeVO );
//		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
//		    }
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/comcode/comcode/listComcode.do";
//	}
//
	@RequestMapping(value = "/la/comcode/comcode/deleteComcode.do")
	public String deletetComcode(@ModelAttribute("frmComcode") ComcodeVO comcodeVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		
		if( StringUtils.isBlank( comcodeVO.getDelCodeId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			comcodeVO.setDelCodeIdList( comcodeVO.getDelCodeId().split(","));
			
			int deletetCnt = comcodeService.deleteComcode( comcodeVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/comcode/comcode/listComcode.do";
	}


	@RequestMapping(value = "/la/comcode/comcode/goSaveComcode.do")
	public String goSaveComcode(@ModelAttribute("frmComcode") ComcodeVO comcodeVO , ModelMap model) throws Exception {

		List<ComcodeVO> comcodeGroupList = comcodeService.listComcodeGroup( new ComcodeVO() );
		
        String searchCodeGroup = comcodeVO.getSearchCodeGroup();
        String searchCodeName = comcodeVO.getSearchCodeName();

		
		if( StringUtils.isNoneBlank( comcodeVO.getCodeId() ) ){
			
			comcodeVO = comcodeService.getComcode( comcodeVO );
			comcodeVO.setSearchCodeGroup(searchCodeGroup);
			comcodeVO.setSearchCodeName(searchCodeName);
		}

        model.addAttribute("comcodeVO", comcodeVO);
        model.addAttribute("comcodeGroupList", comcodeGroupList);

        
		return "oklms/la/comcode/comcodeSave";	
	}
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcode.do")
	public String saveComcode(@ModelAttribute("frmComcode") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

//		validator.validate(comcodeVO, bindingResult); //validation 수행
		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...
		    model.addAttribute("comcodeVO", comcodeVO);

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			model.addAttribute("retMsg", retMsg);
			return "oklms/la/comcode/comcodeSave";
//			throw new BindException(bindingResult); // WEB-INF/config/framework/springmvc/com-servlet.xml : <prop key="org.springframework.validation.BindException">validationJsonView</prop>
//			return "validationJsonView";
		}
		
		
		int resultCnt = comcodeService.saveComcode( comcodeVO ); 
				
		if( 0 < resultCnt ){
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

		Map<String, Object> returnResultMap = new HashMap<String,Object>();
		returnResultMap.put("retMsg", retMsg);
		returnResultMap.put("searchCodeGroup", comcodeVO.getSearchCodeGroup());
		returnResultMap.put("searchCodeName", comcodeVO.getSearchCodeName() );
		redirectAttributes.addFlashAttribute("returnResultMap" , returnResultMap);
	      
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		redirectAttributes.addFlashAttribute("searchCodeGroup", comcodeVO.getSearchCodeGroup());
//		redirectAttributes.addFlashAttribute("searchCodeName", comcodeVO.getSearchCodeName());
		return "redirect:/la/comcode/comcode/listComcode.do";
	}
	
	
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcode.json")
	public @ResponseBody Map<String, Object> saveComcodeJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}

		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriMember.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriMemberJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkMemberVO aunuriLinkMemberVO = new AunuriLinkMemberVO();
		int iResult = linkService.insertAunuriMember(aunuriLinkMemberVO);
		
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriSubject.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriSubjectJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkSubjectVO aunuriLinkSubjectVO = new AunuriLinkSubjectVO();
		int iResult = linkService.insertAunuriSubject(aunuriLinkSubjectVO);
		
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriInsJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriInsJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO = new AunuriLinkMemberMappingVO();
		int iResult = linkService.insertAunuriIns(aunuriLinkMemberMappingVO);
		
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriLessonJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriLessonJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkLessonVO aunuriLinkLessonVO = new AunuriLinkLessonVO();
		int iResult = linkService.insertAunuriLesson(aunuriLinkLessonVO);
		
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriCdpJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriCdpJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO = new AunuriLinkMemberMappingVO();
		int iResult = linkService.insertAunuriCdp(aunuriLinkMemberMappingVO);
		
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriSchJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriSchJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkScheduleVO aunuriLinkScheduleVO = new AunuriLinkScheduleVO();
		int iResult = linkService.insertAunuriSchedule(aunuriLinkScheduleVO);
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/comcode/comcode/saveComcodeAunuriNcsJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriNcsJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO = new AunuriLinkSubjectWeekNcsVO();
		int iResult = linkService.insertAunuriWeekNcs(aunuriLinkSubjectWeekNcsVO);
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/comcode/comcode/updateComcodeAunuriWeekJson.json")
	public @ResponseBody Map<String, Object> saveComcodeAunuriWeekJson(@ModelAttribute("frmComcodeAJAX") @Valid ComcodeVO comcodeVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO = new AunuriLinkSubjectWeekNcsVO();
		int iResult = linkService.updateAunuriWeek(aunuriLinkSubjectWeekNcsVO);
		
		/*@Resource(name = "aunuriLinkService")
		private AunuriLinkService aunuriLinkService;
		
		@Resource(name = "memberService")
		private MemberService memberService;*/
		

		/*if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = comcodeService.saveComcode( comcodeVO ); 
			
			if( 0 < resultCnt ){
				retCd = "SUCCESS";
				retMsg = "정상적으로 처리되었습니다.";
			}else{
				retMsg = "처리된 정보가 없습니다.";
			}
			
		}
		
		
*/
		
		if( 0 < iResult ){
			retCd = "SUCCESS";
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}
		
		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
}
