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
package kr.co.sitglobal.oklms.la.authority.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.authority.service.AuthGroupService;
import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;

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
public class AuthGroupController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(AuthGroupController.class);

	@Resource(name = "authGroupService")
	private AuthGroupService authGroupService;

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
	
//	// /authority/authGroup/listBasic.do
//	// /authority/authGroup/AuthGroup/listCode.do
	@RequestMapping(value = "/la/authority/authGroup/listAuthGroup.do")
	public String listAuthGroup(@ModelAttribute("frmAuthGroup") AuthGroupVO authGroupVO, ModelMap model) throws Exception {
		
		LOG.debug("listAuthGroup : authGroupVO=" + authGroupVO.toString() );
		
//		List<AuthGroupVO> authGroupGroupList = authGroupService.listAuthGroupGroup( new AuthGroupVO() );
		List<AuthGroupVO> resultList = authGroupService.listAuthGroup( authGroupVO );


		// {pageSize=10, pageIndex=1, searchPrototypeTitle=}
		Integer pageSize = authGroupVO.getPageSize();
		Integer page = authGroupVO.getPageIndex();
//		int totalCnt = authGroupService.getAuthGroupCnt(authGroupVO);
		int totalCnt = 0;
		if( 0 < resultList.size() ){
			
			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
		}
		int totalPage = (int) Math.ceil(totalCnt / pageSize);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalCount", totalCnt);
        model.addAttribute("pageIndex", page);
		

        PaginationInfo paginationInfo = new PaginationInfo();

        paginationInfo.setCurrentPageNo(authGroupVO.getPageIndex());
        paginationInfo.setRecordCountPerPage(authGroupVO.getPageUnit());
        paginationInfo.setPageSize(authGroupVO.getPageSize());
        paginationInfo.setTotalRecordCount(totalCnt);

        model.addAttribute("resultCnt", totalCnt );
        model.addAttribute("paginationInfo", paginationInfo);
        
        

        model.addAttribute("authGroupVO", authGroupVO);
        model.addAttribute("resultList", resultList);
//        model.addAttribute("authGroupGroupList", authGroupGroupList);
        
		// View호출
		return "oklms/la/authority/authGroup/authGroupList";
	}

//	@RequestMapping(value = "/la/authority/authGroup/getAuthGroup.do")
//	public String getAuthGroup(@ModelAttribute("frmAuthGroup") AuthGroupVO authGroupVO,  ModelMap model , HttpServletRequest request) throws Exception {
//		authGroupVO = authGroupService.getAuthGroup( authGroupVO );
//
//        model.addAttribute("authGroupVO", authGroupVO);
//
//		// View호출
//		return "oklms/la/authority/authGroup/authGroupRead";
//	}
//
//	@RequestMapping(value = "/la/authority/authGroup/goInsertAuthGroup.do")
//	public String goInsertAuthGroup(ModelMap model) throws Exception {
//		return "oklms/la/authority/authGroup/authGroupCret";	
//	}
//	
//	@RequestMapping(value = "/la/authority/authGroup/insertAuthGroup.do")
//	public String insertAuthGroup(@ModelAttribute("frmAuthGroup") @Valid AuthGroupVO authGroupVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//		
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("authGroupVO", authGroupVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//				
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/authority/authGroup/authGroupCret";
//		}
//		
//	    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmAuthGroupToken")) {  
//	        String insertPK = authGroupService.insertAuthGroup(authGroupVO);
//	        retMsg = "정상적으로 (저장)처리되었습니다.!";
//	    }
//		
//		
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/authority/authGroup/listAuthGroup.do";
//	}
//
//	@RequestMapping(value = "/la/authority/authGroup/goUpdateAuthGroup.do")
//	public String goUpdateAuthGroup(@ModelAttribute("frmAuthGroup") @Valid AuthGroupVO authGroupVO, BindingResult bindingResult, ModelMap model) throws Exception {
//		authGroupVO = authGroupService.getAuthGroup( authGroupVO );
//
//        model.addAttribute("authGroupVO", authGroupVO);
//		return "oklms/la/authority/authGroup/authGroupUpdt";	
//	}
//
//	@RequestMapping(value = "/la/authority/authGroup/updateAuthGroup.do")
//	public String updateAuthGroup(@ModelAttribute("frmAuthGroup") @Valid AuthGroupVO authGroupVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("authGroupVO", authGroupVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/authority/authGroup/authGroupCret";
//		}
//		
//		if( StringUtils.isBlank( authGroupVO.getAuthGroupId() ) ){
//			retMsg = "존재하지 않는 정보입니다.";
//		}else{
//		    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmAuthGroupToken")) {  
//		    	
//		    	int updateCnt = authGroupService.updateAuthGroup( authGroupVO );
//		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
//		    }
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/authority/authGroup/listAuthGroup.do";
//	}
//
	@RequestMapping(value = "/la/authority/authGroup/deleteAuthGroup.do")
	public String deletetAuthGroup(@ModelAttribute("frmAuthGroup") AuthGroupVO authGroupVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		
		if( StringUtils.isBlank( authGroupVO.getDelAuthGroupId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			authGroupVO.setDelAuthGroupIdList( authGroupVO.getDelAuthGroupId().split(","));
			
			int deletetCnt = authGroupService.deleteAuthGroup( authGroupVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/authority/authGroup/listAuthGroup.do";
	}


	@RequestMapping(value = "/la/authority/authGroup/goSaveAuthGroup.do")
	public String goSaveAuthGroup(@ModelAttribute("frmAuthGroup") AuthGroupVO authGroupVO , ModelMap model) throws Exception {

        String searchAuthGroupName = authGroupVO.getSearchAuthGroupName();
		
		if( StringUtils.isNoneBlank( authGroupVO.getAuthGroupId() ) ){
			authGroupVO = authGroupService.getAuthGroup( authGroupVO );
			authGroupVO.setSearchAuthGroupName(searchAuthGroupName);
		}

        model.addAttribute("authGroupVO", authGroupVO);
//        model.addAttribute("authGroupGroupList", authGroupGroupList);

        
		return "oklms/la/authority/authGroup/authGroupSave";	
	}
	
	@RequestMapping(value = "/la/authority/authGroup/saveAuthGroup.do")
	public String saveAuthGroup(@ModelAttribute("frmAuthGroup") @Valid AuthGroupVO authGroupVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";
		int resultCnt = 0;

//		validator.validate(authGroupVO, bindingResult); //validation 수행
		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...
		    model.addAttribute("authGroupVO", authGroupVO);

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			model.addAttribute("retMsg", retMsg);
			return "oklms/la/authority/authGroup/authGroupSave";
//			throw new BindException(bindingResult); // WEB-INF/config/framework/springmvc/com-servlet.xml : <prop key="org.springframework.validation.BindException">validationJsonView</prop>
//			return "validationJsonView";
		}
		
		// 화면에서 저장시 authGroupId 항목으로 신규저장 혹은 수정저장인지 판단함. - jglee  
		if( StringUtils.isBlank( authGroupVO.getAuthGroupId() ) ){
			resultCnt = authGroupService.insertAuthGroup( authGroupVO ); //신규저장
		} else {
			resultCnt = authGroupService.updateAuthGroup( authGroupVO ); //수정저장
		}
		
		//int resultCnt = authGroupService.saveAuthGroup( authGroupVO ); 
				
		if( 0 < resultCnt ){
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

		Map<String, Object> returnResultMap = new HashMap<String,Object>();
		returnResultMap.put("retMsg", retMsg);
		returnResultMap.put("searchAuthGroupName", authGroupVO.getSearchAuthGroupName() );
		redirectAttributes.addFlashAttribute("returnResultMap" , returnResultMap);
	      
		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		redirectAttributes.addFlashAttribute("searchAuthGroupName", authGroupVO.getSearchAuthGroupName());
		return "redirect:/la/authority/authGroup/listAuthGroup.do";
	}
	
	
	
	@RequestMapping(value = "/la/authority/authGroup/saveAuthGroup.json")
	public @ResponseBody Map<String, Object> saveAuthGroupJson(@ModelAttribute("frmAuthGroupAJAX") @Valid AuthGroupVO authGroupVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			int resultCnt = authGroupService.saveAuthGroup( authGroupVO ); 
			
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
	
}
