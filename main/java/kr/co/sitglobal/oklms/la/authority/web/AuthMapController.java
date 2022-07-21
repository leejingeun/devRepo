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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.la.authority.service.AuthGroupService;
import kr.co.sitglobal.oklms.la.authority.service.AuthMapService;
import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;
import kr.co.sitglobal.oklms.la.authority.vo.AuthMapVO;
import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;

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
import org.springframework.web.bind.annotation.RequestParam;
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
public class AuthMapController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(AuthMapController.class);

	@Resource(name = "authGroupService")
	private AuthGroupService authGroupService;
	@Resource(name = "authMapService")
	private AuthMapService authMapService;

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
	
//	// /authMap/listBasic.do
//	// /authMap/AuthMap/listCode.do
	@RequestMapping(value = "/la/authority/authMap/listAuthMap.do")
	public String listAuthMap(ModelMap model) throws Exception {
		
		LOG.debug("listAuthMap" );
		
		List<AuthGroupVO> authGroupList = authGroupService.listAuthGroup( new AuthGroupVO() );
		List<AuthGroupVO> distAuthGroupList = authGroupService.listDistAuthGroup( new AuthGroupVO() );
//		List<AuthMapVO> resultList = new ArrayList<AuthMapVO>();
//		if( StringUtils.isNoneBlank( authMapVO.getSearchAuthGroupId() ) ){
//			
//			resultList = authMapService.listAuthMap( authMapVO );
//		}else{
//	        model.addAttribute("retMsg", "권한 그룹을 지정해 주세요.");			
//		}
//
//
//		// {pageSize=10, pageIndex=1, searchPrototypeTitle=}
//		Integer pageSize = authMapVO.getPageSize();
//		Integer page = authMapVO.getPageIndex();
////		int totalCnt = authMapService.getAuthMapCnt(authMapVO);
//		int totalCnt = 0;
//		if( 0 < resultList.size() ){
//			
//			totalCnt = Integer.parseInt( resultList.get(0).getTotalCount() );
//		}
//		int totalPage = (int) Math.ceil(totalCnt / pageSize);
//
//        model.addAttribute("pageSize", pageSize);
//        model.addAttribute("totalCount", totalCnt);
//        model.addAttribute("pageIndex", page);
//		
//
//        PaginationInfo paginationInfo = new PaginationInfo();
//
//        paginationInfo.setCurrentPageNo(authMapVO.getPageIndex());
//        paginationInfo.setRecordCountPerPage(authMapVO.getPageUnit());
//        paginationInfo.setPageSize(authMapVO.getPageSize());
//        paginationInfo.setTotalRecordCount(totalCnt);
//
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
        
        

//        model.addAttribute("authMapVO", authMapVO);
//        model.addAttribute("resultList", resultList);
        model.addAttribute("authGroupList", authGroupList);
        model.addAttribute("distAuthGroupList", distAuthGroupList);
        
//        model.addAttribute("searchAuthGroupId", authMapVO.getSearchAuthGroupId());
//        model.addAttribute("searchMenuTitle", authMapVO.getSearchMenuTitle());
        
		// View호출
		return "oklms/la/authority/authMap/authMapList";
	}

//	@RequestMapping(value = "/la/authority/authMap/getAuthMap.do")
//	public String getAuthMap(@ModelAttribute("frmAuthMap") AuthMapVO authMapVO,  ModelMap model , HttpServletRequest request) throws Exception {
//		authMapVO = authMapService.getAuthMap( authMapVO );
//
//        model.addAttribute("authMapVO", authMapVO);
//
//		// View호출
//		return "oklms/la/authority/authMap/authMapRead";
//	}
//
//	@RequestMapping(value = "/la/authority/authMap/goInsertAuthMap.do")
//	public String goInsertAuthMap(ModelMap model) throws Exception {
//		return "oklms/la/authority/authMap/authMapCret";	
//	}
//	
//	@RequestMapping(value = "/la/authority/authMap/insertAuthMap.do")
//	public String insertAuthMap(@ModelAttribute("frmAuthMap") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//		
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("authMapVO", authMapVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//				
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/authority/authMap/authMapCret";
//		}
//		
//	    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmAuthMapToken")) {  
//	        String insertPK = authMapService.insertAuthMap(authMapVO);
//	        retMsg = "정상적으로 (저장)처리되었습니다.!";
//	    }
//		
//		
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/authority/authMap/listAuthMap.do";
//	}
//
//	@RequestMapping(value = "/la/authority/authMap/goUpdateAuthMap.do")
//	public String goUpdateAuthMap(@ModelAttribute("frmAuthMap") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model) throws Exception {
//		authMapVO = authMapService.getAuthMap( authMapVO );
//
//        model.addAttribute("authMapVO", authMapVO);
//		return "oklms/la/authority/authMap/authMapUpdt";	
//	}
//
//	@RequestMapping(value = "/la/authority/authMap/updateAuthMap.do")
//	public String updateAuthMap(@ModelAttribute("frmAuthMap") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("authMapVO", authMapVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/authority/authMap/authMapCret";
//		}
//		
//		if( StringUtils.isBlank( authMapVO.getCodeId() ) ){
//			retMsg = "존재하지 않는 정보입니다.";
//		}else{
//		    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmAuthMapToken")) {  
//		    	
//		    	int updateCnt = authMapService.updateAuthMap( authMapVO );
//		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
//		    }
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/authority/authMap/listAuthMap.do";
//	}
//
	@RequestMapping(value = "/la/authority/authMap/deleteAuthMap.do")
	public String deletetAuthMap(@ModelAttribute("frmAuthMap") AuthMapVO authMapVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		String retMsg = "";

		
		if( StringUtils.isBlank( authMapVO.getMenuId() ) || StringUtils.isBlank( authMapVO.getAuthGroupId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			int deletetCnt = authMapService.deleteAuthMap( authMapVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
		}

		redirectAttributes.addFlashAttribute("retMsg", retMsg);
		return "redirect:/la/authority/authMap/listAuthMap.do";
	}


	@RequestMapping(value = "/la/authority/authMap/goSaveAuthMap.do")
	public String goSaveAuthMap(@ModelAttribute("frmAuthMap") AuthMapVO authMapVO , ModelMap model) throws Exception {

		List<AuthGroupVO> authGroupList = authGroupService.listAuthGroup( new AuthGroupVO() );
		
        String searchAuthGroupId = authMapVO.getSearchAuthGroupId();
        String searchMenuTitle = authMapVO.getSearchMenuTitle();

		
		if( StringUtils.isNoneBlank( authMapVO.getMenuIdAndAuthGroupId() ) || (StringUtils.isNoneBlank( authMapVO.getMenuId() ) && StringUtils.isNoneBlank( authMapVO.getAuthGroupId() ) ) ){
			
			if( StringUtils.isNoneBlank( authMapVO.getMenuIdAndAuthGroupId() ) ){
				String[] ids = StringUtils.split( authMapVO.getMenuIdAndAuthGroupId() , "^");
				if( null != ids && 2 == ids.length ){
					authMapVO.setMenuId(ids[0]);
					authMapVO.setAuthGroupId(ids[1]);
				}
			}
			
			authMapVO = authMapService.getAuthMap( authMapVO );
			authMapVO.setSearchAuthGroupId(searchAuthGroupId);
			authMapVO.setSearchMenuTitle(searchMenuTitle);
		}

        model.addAttribute("authMapVO", authMapVO);
        model.addAttribute("authGroupList", authGroupList);

        
		return "oklms/la/authority/authMap/authMapSave";	
	}
	
	@RequestMapping(value = "/la/authority/authMap/saveAuthMap.do")
	public String saveAuthMap(@ModelAttribute("frmAuthMap") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String retMsg = "입력값을 확인해주세요";

//		validator.validate(authMapVO, bindingResult); //validation 수행
		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...
		    model.addAttribute("authMapVO", authMapVO);

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
			}
			
			model.addAttribute("retMsg", retMsg);
			return "oklms/la/authority/authMap/authMapSave";
//			throw new BindException(bindingResult); // WEB-INF/config/framework/springmvc/com-servlet.xml : <prop key="org.springframework.validation.BindException">validationJsonView</prop>
//			return "validationJsonView";
		}
		
		
		int resultCnt = authMapService.saveAuthMap( authMapVO ); 
				
		if( 0 < resultCnt ){
			retMsg = "정상적으로 처리되었습니다.";
		}else{
			retMsg = "처리된 정보가 없습니다.";
		}

		Map<String, Object> returnResultMap = new HashMap<String,Object>();
		returnResultMap.put("retMsg", retMsg);
		returnResultMap.put("searchAuthGroupId", authMapVO.getSearchAuthGroupId());
		returnResultMap.put("searchMenuTitle", authMapVO.getSearchMenuTitle() );
		redirectAttributes.addFlashAttribute("returnResultMap" , returnResultMap);
	      
		return "redirect:/la/authority/authMap/listAuthMap.do";
	}
	
	
	
	@RequestMapping(value = "/la/authority/authMap/saveAuthMap.json")
	public @ResponseBody Map<String, Object> saveAuthMapJson(@ModelAttribute("frmAuthMapAJAX") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";

		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...

			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			for( FieldError fieldError : fieldErrorList ){

				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
			}
		}else{
			
			// 신규 및 수정 mapper 호출 분기 처리함 - jglee
//			if( StringUtils.isBlank(authMapVO.getMenuId()) && StringUtils.isBlank(authMapVO.getAuthGroupId())){
//				resultCnt = authMapService.insertAuthMap( authMapVO ); //신규저장
//			} else {
//				resultCnt = authMapService.updateAuthMap( authMapVO ); //수정저장
//			}
			
			int resultCnt = authMapService.saveAuthMap( authMapVO ); 
			
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

	@RequestMapping(value = "/la/authority/authMap/deleteAuthMap.json")
	public @ResponseBody Map<String, Object> deletetAuthMap(@ModelAttribute("frmAuthMapAJAX") @Valid AuthMapVO authMapVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
		
		Map<String , Object> returnMap = new HashMap<String , Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		
		if( StringUtils.isBlank( authMapVO.getMenuId() ) || StringUtils.isBlank( authMapVO.getAuthGroupId() ) ){
			retMsg = "삭제할 정보가 없습니다.";
		}else{
			
			int deletetCnt = authMapService.deleteAuthMap( authMapVO );
			retMsg = "정상적으로 (삭제)처리되었습니다.!";
			retCd = "SUCCESS";
		}


		returnMap.put("retCd", retCd);
		returnMap.put("retMsg", retMsg);

		return returnMap;
	}
	
	
	@RequestMapping(value = "/la/authority/authMap/listAuthMapTree.json")
	public @ResponseBody Map<String, Object> listAuthMapTreeJson(@ModelAttribute("frmAuthMap") AuthMapVO authMapVO, ModelMap model) throws Exception {
		
		LOG.debug("listAuthMap : authMapVO=" + authMapVO.toString() );
		List<AuthGroupVO> authGroupList = authGroupService.listAuthGroup( new AuthGroupVO() );
		List<AuthMapVO> resultList = new ArrayList<AuthMapVO>();
		if( StringUtils.isNoneBlank( authMapVO.getSearchAuthGroupId() ) ){
			
			resultList = authMapService.listAuthMapTree( authMapVO );
		}else{
	        model.addAttribute("retMsg", "권한 그룹을 지정해 주세요.");			
		}
		
		
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("retCd", 200);
		returnMap.put("retMsg", "[" + resultList.size() + "]건 조회되었습니다." );
		returnMap.put("page", "");
		returnMap.put("total", "");
		returnMap.put("records", resultList);
		returnMap.put("totalRecords", resultList.size());
		returnMap.put("rows", resultList );

		return returnMap;
	}
}
