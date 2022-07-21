/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.menu.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.cmmcode.service.CommonCodeService;
import kr.co.sitglobal.oklms.commbiz.cmmcode.vo.CommonCodeVO;
import kr.co.sitglobal.oklms.la.authority.service.AuthMapService;
import kr.co.sitglobal.oklms.la.authority.vo.AuthMapVO;
import kr.co.sitglobal.oklms.la.menu.service.MenuService;
import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

 /**
 * 관리자가 메뉴 data에 대한 CRUD 작업을 하기위한 용도.
 * @author 
 * @since 2016. 07. 01.
 */
@Controller
public class MenuController extends BaseController{
	
	@Resource(name = "menuService")
	private MenuService menuService;
	
	@Resource(name = "authMapService")
	private AuthMapService authMapService;

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
	

	
	
	/**
	 * 메뉴를 트리 구조로 조회한다. : 팝업에서 사용됨.
	 * @param menuVO
	 * @param model
	 * @return
	 * @throws Exception
	 * String
	 */
	@RequestMapping(value = "/la/menu/uperMenuList.do")
	public String menuUperMenuSercList(@ModelAttribute MenuVO menuVO, ModelMap model) throws Exception {
		//상위메뉴 검색 (팝업)
		model.put("searchMenuArea", menuVO.getSearchMenuArea() );
		model.put("searchMenuType", menuVO.getSearchMenuType() );
		return "oklms_body/la/menu/uperMenuList";
	}

	/**
	 * 관리할 메뉴를 트리 구조로 조회한다.
	 * @param commandMap
	 * @param menuVO
	 * @param model
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	@RequestMapping(value = "/la/menu/listMenuTree.json")
	public @ResponseBody Map<String, Object> listMenuTree(@RequestParam Map<String, Object> commandMap, @ModelAttribute MenuVO menuVO, ModelMap model) throws Exception {

		// session의 정보를 VO에 추가. - jglee
		LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(menuVO); 
        
        // 메뉴화면에서 searchMenuType 파라메터로 넘길떼 sessionMemType 항목에 셋팅한다. - jglee
        String searchMenuType = (String)commandMap.get("searchMenuType");
        if(!StringUtils.isBlank( searchMenuType ) ){
        	menuVO.setSessionMemType(searchMenuType);
        } 
        
		List<MenuVO> data = menuService.listMenuTree(menuVO);
		
		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		returnMap.put("retCd", 200);
		returnMap.put("retMsg", "[" + data.size() + "]건 조회되었습니다." );
		returnMap.put("page", "");
		returnMap.put("total", "");
		returnMap.put("records", data);
		returnMap.put("totalRecords", data.size());
		returnMap.put("rows", data );

		return returnMap;
	}
	
	/**
	 * 메뉴관리 저장 : JSON
	 * @param menuVO
	 * @param bindingResult
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "/la/menu/saveMenu.json")
	public @ResponseBody Map<String, Object> saveMenu(@ModelAttribute @Valid MenuVO menuVO, BindingResult bindingResult) throws Exception {

		HashMap<String,Object> returnMap = new HashMap<String,Object>();
		String retCd = "FAILE";
		String retMsg = "입력값을 확인해주세요";
		String pkMenuId = "";
		int count = 0;
		int manuOrderMax = 0;
		int manuOrderTopMax = 0;
		
		//추가된매뉴ID 1건에 대한 권한설정 테이블에 필요항목 셋팅
		AuthMapVO authMapVO = new AuthMapVO();
		authMapVO.setDeleteYn("N");
		authMapVO.setCreateAuthYn("Y");
		authMapVO.setReadAuthYn("Y");
		authMapVO.setUpdateAuthYn("Y");
		authMapVO.setDeleteAuthYn("Y");
		authMapVO.setPrintAuthYn("Y");
		authMapVO.setDownloadAuthYn("Y");
		authMapVO.setOtherAuthYn("Y");
		authMapVO.setListAuthYn("Y");
		
		//menuType(회원유형)에 따른 권한그룹코드 셋팅
		if("ADM".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_ADM);
		}
		if("STD".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_STD);
		}
		if("PRT".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_PRO_TUTOR);
		}
		if("CCM".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_CRI_COMPANY);
		}
		if("CCN".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_CRI_CENTER);
		}
		if("CDP".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_CRI_DEPARTMENT);
		}
		if("ATT".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_ADV_TUTOR);
		}
		if("COT".equals(menuVO.getMenuType())){
			authMapVO.setAuthGroupId(Config.DEFAULT_AUTH_SUPERVISOR_TEACHAR);
		}
		
		//저장시 validation 에러가 있으면 에러화면 포워딩
		if (bindingResult.hasErrors()) { 
			retMsg = "입력값을 확인해주세요. 저장에 실패하였습니다.";
		} else {
			// 신규 혹은 수정 메뉴인지 분별
			if( "최상위".equals(menuVO.getUpMenuTitle()) || !StringUtils.isBlank(menuVO.getUpMenuTitle()) ){
				// 메뉴 신규저장
				if("최상위".equals(menuVO.getUpMenuTitle())){
					//최상위 신규메뉴 추가 저장
					pkMenuId = menuService.insertTopMenu(menuVO); 
					
					//menuType(회원유형)에 따른 권한설정 Default 저장
					authMapVO.setMenuId(pkMenuId);
					authMapService.insertAuthMap(authMapVO);
				} else {
					//최상위 하위신규메뉴 추가 저장
					manuOrderMax = menuService.getManuOrderMax(menuVO);
					if("TOP".equals(menuVO.getUpMenuId())){
						//최상위 하위신규메뉴 upMenuId -> TOP 인경우
						count = menuService.getMaxManuOrder(menuVO);
						if(count > 0){
							menuVO.setUpMenuId(menuVO.getMenuId());
							menuService.updateMenuMenuOrder(menuVO);
							pkMenuId = menuService.insertMenuAdd(menuVO);
							
							//menuType(회원유형)에 따른 권한설정 Default 저장
							authMapVO.setMenuId(pkMenuId);
							authMapService.insertAuthMap(authMapVO);
						}
						if(count == 0){
							manuOrderTopMax = menuService.getManuOrderTopMax(menuVO);
							if(manuOrderMax == manuOrderTopMax){
								pkMenuId = menuService.insertMenuTopAdd(menuVO);
							}else{
								menuVO.setUpMenuId(menuVO.getMenuId());
								menuService.updateMenuMenuOrder(menuVO);
								pkMenuId = menuService.insertMenuAdd(menuVO);
							}
							
							//menuType(회원유형)에 따른 권한설정 Default 저장
							authMapVO.setMenuId(pkMenuId);
							authMapService.insertAuthMap(authMapVO);
						}
					}else{
						//최상위 하위신규메뉴 upMenuId -> TOP 아닌경우
						menuService.updateMenuMenuOrder(menuVO);
						pkMenuId = menuService.insertMenuAdd(menuVO);
						
						//menuType(회원유형)에 따른 권한설정 Default 저장
						authMapVO.setMenuId(pkMenuId);
						authMapService.insertAuthMap(authMapVO);
					}
				}
			}else{
				// 메뉴 수정저장	
				menuService.updateMenu(menuVO); 
			}

			retCd = "SUCCESS";
			retMsg = "정상적으로 (저장)처리되었습니다.";
		}
		
		returnMap.put("retCd", retCd );
		returnMap.put("retMsg", retMsg );

		return returnMap;
	}
	
	
	// /la/menu/listBasic.do
	// /la/menu/Menu/menuMain.do
	@RequestMapping(value = "/la/menu/listMenu.do")
	public String listMenu(@ModelAttribute("frmMenu") MenuVO menuVO, ModelMap model) throws Exception {
		
		CommonCodeVO commonCodeVO =  new CommonCodeVO();
		commonCodeVO.setCodeGroup("");
		List<CommonCodeVO> menuViewTypeCodeList = commonCodeService.selectCmmCodeList(commonCodeVO);
//		List<MenuVO> resultList = menuService.listMenu( menuVO );
//        model.addAttribute("resultCnt", totalCnt );
//        model.addAttribute("paginationInfo", paginationInfo);
//        
//        
//
//        model.addAttribute("menuVO", menuVO);
//        model.addAttribute("resultList", resultList);
		// View호출
		return "oklms/la/menu/menuList";
	}

//	@RequestMapping(value = "/la/menu/getMenu.do")
//	public String getMenu(@ModelAttribute("frmMenu") MenuVO menuVO,  ModelMap model , HttpServletRequest request) throws Exception {
//		menuVO = menuService.getMenu( menuVO );
//
//        model.addAttribute("menuVO", menuVO);
//
//		// View호출
//		return "oklms/la/menu/menuRead";
//	}
//
//	@RequestMapping(value = "/la/menu/goInsertMenu.do")
//	public String goInsertMenu(ModelMap model) throws Exception {
//		return "oklms/la/menu/menuCret";	
//	}
//	
//	@RequestMapping(value = "/la/menu/insertMenu.do")
//	public String insertMenu(@ModelAttribute("frmMenu") @Valid MenuVO menuVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//		
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("menuVO", menuVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//				
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/menu/menuCret";
//		}
//		
//	    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMenuToken")) {  
//	        String insertPK = menuService.insertMenu(menuVO);
//	        retMsg = "정상적으로 (저장)처리되었습니다.!";
//	    }
//		
//		
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/menu/listMenu.do";
//	}
//
//	@RequestMapping(value = "/la/menu/goUpdateMenu.do")
//	public String goUpdateMenu(@ModelAttribute("frmMenu") @Valid MenuVO menuVO, BindingResult bindingResult, ModelMap model) throws Exception {
//		menuVO = menuService.getMenu( menuVO );
//
//        model.addAttribute("menuVO", menuVO);
//		return "oklms/la/menu/menuUpdt";	
//	}
//
//	@RequestMapping(value = "/la/menu/updateMenu.do")
//	public String updateMenu(@ModelAttribute("frmMenu") @Valid MenuVO menuVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) {
//		    model.addAttribute("menuVO", menuVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			
//			return "oklms/la/menu/menuCret";
//		}
//		
//		if( StringUtils.isBlank( menuVO.getMenuId() ) ){
//			retMsg = "존재하지 않는 정보입니다.";
//		}else{
//		    if (EgovDoubleSubmitHelper.checkAndSaveToken("frmMenuToken")) {  
//		    	
//		    	int updateCnt = menuService.updateMenu( menuVO );
//		    	retMsg = "정상적으로 (수정)처리되었습니다.!";
//		    }
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/menu/listMenu.do";
//	}
//
//	@RequestMapping(value = "/la/menu/deleteMenu.do")
//	public String deletetMenu(@ModelAttribute("frmMenu") MenuVO menuVO, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
//
//		String retMsg = "";
//
//		
//		if( StringUtils.isBlank( menuVO.getDelMenuId() ) ){
//			retMsg = "삭제할 정보가 없습니다.";
//		}else{
//			
//			menuVO.setDelMenuIdList( menuVO.getDelMenuId().split(","));
//			
//			int deletetCnt = menuService.deleteMenu( menuVO );
//			retMsg = "정상적으로 (삭제)처리되었습니다.!";
//		}
//
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/menu/listMenu.do";
//	}
//
//
//	@RequestMapping(value = "/la/menu/goSaveMenu.do")
//	public String goSaveMenu(@ModelAttribute("frmMenu") MenuVO menuVO , ModelMap model) throws Exception {
//		menuVO = menuService.getMenu( menuVO );
//
//        model.addAttribute("menuVO", menuVO);
//		return "oklms/la/menu/menuSave";	
//	}
//	
//	@RequestMapping(value = "/la/menu/saveMenu.do")
//	public String saveMenu(@ModelAttribute("frmMenu") @Valid MenuVO menuVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//
//		String retMsg = "입력값을 확인해주세요";
//
////		validator.validate(menuVO, bindingResult); //validation 수행
//		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...
//		    model.addAttribute("menuVO", menuVO);
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\\n" + fieldError.getDefaultMessage();
//			}
//			
//			model.addAttribute("retMsg", retMsg);
//			return "oklms/la/menu/menuSave";
////			throw new BindException(bindingResult); // WEB-INF/config/framework/springmvc/com-servlet.xml : <prop key="org.springframework.validation.BindException">validationJsonView</prop>
////			return "validationJsonView";
//		}
//		
//		
//		int resultCnt = menuService.saveMenu( menuVO ); 
//				
//		if( 0 < resultCnt ){
//			retMsg = "정상적으로 처리되었습니다.";
//		}else{
//			retMsg = "처리된 정보가 없습니다.";
//		}
//		
//		redirectAttributes.addFlashAttribute("retMsg", retMsg);
//		return "redirect:/la/menu/listMenu.do";
//	}
//	
//	@RequestMapping(value = "/la/menu/goSaveMenu.json")
//	public @ResponseBody Map<String, Object> goSaveMenu(@ModelAttribute("frmMenuAJAX") @Valid MenuVO menuVO, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {
//		
//		Map<String , Object> returnMap = new HashMap<String , Object>();
//		String retCd = "FAILE";
//		String retMsg = "입력값을 확인해주세요";
//
//		if (bindingResult.hasErrors()) { //만일 validation 에러가 있으면...
//
//			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
//			for( FieldError fieldError : fieldErrorList ){
//
//				retMsg = retMsg + "\n" + fieldError.getDefaultMessage();
//			}
//		}else{
//			
//			int resultCnt = menuService.saveMenu( menuVO ); 
//			
//			if( 0 < resultCnt ){
//				retCd = "SUCCESS";
//				retMsg = "정상적으로 처리되었습니다.";
//			}else{
//				retMsg = "처리된 정보가 없습니다.";
//			}
//			
//		}
//
//		returnMap.put("retCd", retCd);
//		returnMap.put("retMsg", retMsg);
//
//		return returnMap;
//	}
}
