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
package kr.co.sitglobal.oklms.commbiz.menu.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.ifx.service.IfxService;
import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;
import kr.co.sitglobal.oklms.commbiz.menu.service.CommbizMenuService;
import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;
import kr.co.sitglobal.oklms.lu.main.service.LmsUserMainPageService;
import kr.co.sitglobal.oklms.lu.main.vo.LmsUserMainPageVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;
//import org.codehaus.jackson.map.ObjectMapper; // spring 3.x , jackson 1.x
 /**
 * 사용자,관리자의 화면에서 상단 , 좌측의 메뉴 영역에 노출될 메뉴를 조회하기 위한 용도.
 * @author 
 * @since 2016. 07. 01.
 */
@Controller
public class CommbizMenuController extends BaseController{

	private static final Logger LOG = LoggerFactory.getLogger(CommbizMenuController.class);
	
	@Resource(name = "commbizMenuService")
	private CommbizMenuService commbizMenuService;
	
	@Resource(name = "ifxService")
	private IfxService ifxService;
	
	@Resource(name = "LmsUserMainPageService")
	private LmsUserMainPageService lmsUserMainPageService;
	
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
	 

	@RequestMapping(value = "/{serviceArea}/commbiz/menu/headMenu.do")
	public String headMenu(HttpServletRequest request, HttpServletResponse response, @PathVariable("serviceArea") String serviceArea , @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출
		LOG.debug("#### /{serviceArea}/commbiz/menu/headMenu.do , serviceArea : " + serviceArea );

//    	LoginInfo loginInfo = new LoginInfo();
    	LoginInfo loginInfo = new LoginInfo(request, response);
    	HttpSession session = request.getSession(); // 강의관리 관련 세션 Key get 처리

		LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
    	
		ArrayList<CommbizMenuVO> menuStructureList = null;
		//관리자는 메뉴정보를 필요할 때마다 매번 가져오도록 한다.(LCMS 전환)
		
		
		if(loginVO!=null &&  "2016AUTH0000001".equals( loginVO.getAuthGroupId() ) ){ 
			/*2016AUTH0000001	관리자
			2016AUTH0000002	학습근로자	
			2016AUTH0000003	담당교수	
			2016AUTH0000004	기업전담자
			2016AUTH0000005	센터전담자
			2016AUTH0000006	학과전담자
			2016AUTH0000007	지도교수
			2016AUTH0000008	기업현장교사*/
			
			String menuArea = null;
			String authGroupId = null;
			String memType = null;
			
			if( null != loginVO){
				 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , loginVO.getAuthGroupId() );
				 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , loginVO.getMenuArea() ) ;
				 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , loginVO.getMemType() ) ;
			}
			 
			Map commandMap2 = new HashMap();
			commandMap2.put("menuArea", menuArea);
			commandMap2.put("memType", memType);
			commandMap2.put("authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
			//commandMap2.put("authGroupId", "2016AUTH0000003"); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
			menuStructureList = commbizMenuService.listMenu(commandMap2);
			
		}else if(serviceArea!=null && serviceArea.equals("mm")){
			
			String menuArea = null;
			String authGroupId = null;
			String memType = null;
			
			if( null != loginVO){
				 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , loginVO.getAuthGroupId() );
				 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , loginVO.getMenuArea() ) ;
				 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , loginVO.getMemType() ) ;
			}
			Map commandMap2 = new HashMap();
			commandMap2.put("menuArea", "MOBILE");
			commandMap2.put("memType", memType);
			commandMap2.put("authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
			//commandMap2.put("authGroupId", "2016AUTH0000003"); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
			menuStructureList = commbizMenuService.mobilelistMenu(commandMap2);
			
		}else{
			 menuStructureList = (ArrayList<CommbizMenuVO>) loginInfo.getSimpleMenuInfo();
		}
		// 상단 알림
		LmsUserMainPageVO mainPageVO = new LmsUserMainPageVO();
		loginInfo.putSessionToVo(mainPageVO);		
    	if(loginVO!=null){    		
    		List<LmsUserMainPageVO>  listLmsUserMainPageTodayCnt = lmsUserMainPageService.listLmsUserMainPageTodayCnt(mainPageVO);
    		model.addAttribute( "listLmsUserMainPageTodayCnt", listLmsUserMainPageTodayCnt);
    	}
//		ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) loginInfo.getSimpleMenuInfo();
		
		HashMap<String, Object> menuList = new HashMap<String,Object>();
		
		for (CommbizMenuVO menuVO : menuStructureList) {
			String upperMenuNo = menuVO.getUpMenuId();
			String menuLevel = menuVO.getMenuLevel();
			//String checkTemp = (String)menuMap.get( "CHECK" );
			//int menuLevelTemp = Integer.parseInt( levelTemp );
			String key = upperMenuNo + "_" +  menuLevel;
			
			List<CommbizMenuVO> list =  (List<CommbizMenuVO>)menuList.get(key);
			
			if(list == null)
				list =  new ArrayList<CommbizMenuVO>();
			
			if( "2016MENU0000140".equals(menuVO.getRootMenuId() ) || "2016MENU0000160".equals(menuVO.getRootMenuId() ) ){
				// 회원서비스 메뉴는 Header 영역의 메뉴에서 제외.
			}else{
				
				list.add( menuVO);
				menuList.put( key, list);
			}
			
		}
		
		model.addAttribute( "menuList", menuList);
		

		ObjectMapper mapper = new ObjectMapper();

		//1. Convert Java object to JSON format
//		String listMenulistJson = mapper.writeValueAsString( menuStructureList );
//
//		model.addAttribute( "list_menulist_json", listMenulistJson );

		//serviceArea = loginInfo.getServiceArea( serviceArea );
		
		return "layout/oklms/" + serviceArea + "/header";
	}
	
	@RequestMapping(value = "/{serviceArea}/commbiz/menu/leftMenu.do")
	public String leftMenu(HttpServletRequest request, HttpServletResponse response , @PathVariable("serviceArea") String serviceArea , @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출
		LOG.debug("#### /commbiz/menu/leftMenu.do : serviceArea=" + serviceArea );

		String rootMenuId = StringUtils.defaultIfBlank( (String)commandMap.get("rootMenuId") , (String)model.get("rootMenuId") );
		String menuId = StringUtils.defaultIfBlank( (String)commandMap.get("menuId") , (String)model.get("menuId") );
		
		
		String isPopUp = (String)commandMap.get("isPopUp");
		

		HttpSession session = request.getSession();
		rootMenuId = StringUtils.defaultIfBlank( rootMenuId , (String)session.getAttribute("rootMenuId") );
		menuId = StringUtils.defaultIfBlank( menuId , (String)session.getAttribute("menuId") );

    	LoginInfo loginInfo = new LoginInfo(request, response);
		ArrayList<CommbizMenuVO> menuStructureList = null;
		
		if(serviceArea!=null && serviceArea.equals("mm")){			
			menuStructureList =(ArrayList<CommbizMenuVO>) loginInfo.getSimpleMobileMenuInfo();
		}else{
			menuStructureList =(ArrayList<CommbizMenuVO>) loginInfo.getSimpleMenuInfo();	
		}		
		
		
		HashMap<String, Object> menuList = new HashMap<String,Object>();
		
		
		for( CommbizMenuVO menuVO : menuStructureList ) {
			
			String upperMenuNo = menuVO.getUpMenuId();
			String menuLevel = menuVO.getMenuLevel();
			String key = upperMenuNo + "_" +  menuLevel;

			//if( "la".equals( serviceArea ) || "lc".equals( serviceArea ) ){ // Header에 1차 메뉴가 없는 경우.( left 메뉴에 1차 메뉴부터 보는 경우.)
				List<CommbizMenuVO> list =  (List<CommbizMenuVO>)menuList.get(key);
				
				if(list == null)
					list =  new ArrayList<CommbizMenuVO>();
				
				list.add( menuVO);
				
				menuList.put( key, list);
//			}else{
//				
//				 if( menuVO.getRootMenuId().equals( rootMenuId )  ){ // Header에 1차 메뉴가 존재하는 경우는 항상 1차메뉴를 클릭해야 left 메뉴가 보이도록 하는 경우 사용.
//					
//					List<CommbizMenuVO> list =  (List<CommbizMenuVO>)menuList.get(key);
//					
//					if(list == null)
//						list =  new ArrayList<CommbizMenuVO>();
//					
//					list.add( menuVO);
//					
//					menuList.put( key, list);
//				}
//			}

			if( menuVO.getMenuId().equals( menuId ) ){

			    model.addAttribute( "menuPathLeafNode", menuVO.getMenuPathLeafNode() );
			    model.addAttribute( "menuIdPathLeafNode", menuVO.getMenuIdPathLeafNode());
			}
		}
		
		model.addAttribute( "menuList", menuList);
		

		ObjectMapper mapper = new ObjectMapper();

		//1. Convert Java object to JSON format
		String listMenulistJson = mapper.writeValueAsString( menuStructureList );

		//API 통한 교과정보, 관리자 제외한 진행중인 교과정보.
		if(!loginInfo.getAuthGroupId().equals("2016AUTH0000001") && !loginInfo.getAuthGroupId().equals("2016AUTH0000005")){
			AunuriMemberVO aunuriMemberVO = new AunuriMemberVO();
			aunuriMemberVO.setSessionMemSeq(loginInfo.getMemSeq());
			
			if("STD".equals(loginInfo.getMemType())){ //학습근로자 개설교과
				List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectLesson(aunuriMemberVO);
				model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
				
				List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectLesson(aunuriMemberVO);
				model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			} else if("PRT".equals(loginInfo.getMemType())){ //담당교수 개설교과
				List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectInsMapping(aunuriMemberVO);
				model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
				List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectInsMapping(aunuriMemberVO);
				model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
				int hSkillCnt = ifxService.getOjtAunuriSubjectInsHSkillCnt(aunuriMemberVO);
				model.addAttribute( "hSkillCnt", hSkillCnt );
			} else if("COT".equals(loginInfo.getMemType())){ //기업현장교사 개설교과
				List<AunuriSubjectVO> listOjtAunuriSubject= ifxService.getOjtAunuriSubjectTutMapping(aunuriMemberVO);
				model.addAttribute( "listOjtAunuriSubject", listOjtAunuriSubject );
			} else { //학과전담자 개설교과
				List<AunuriSubjectVO> listOffJtAunuriSubject= ifxService.getOffJtAunuriSubjectCdpMapping(aunuriMemberVO);
				model.addAttribute( "listOffJtAunuriSubject", listOffJtAunuriSubject );
			}
			
		}

		model.addAttribute( "loginAuthGroupId", loginInfo.getAuthGroupId());
		model.addAttribute( "loginAuthGroupName", loginInfo.getAuthGroupName());
		model.addAttribute( "loginMemName", loginInfo.getMemName() );
		model.addAttribute( "loginMemId", loginInfo.getMemId() );
		model.addAttribute( "list_menulist_json", listMenulistJson );
		
		return "layout/oklms/" + serviceArea + "/left";
	}


	@RequestMapping(value = "/commbiz/menu/menuMove.do")
	public String menuMove(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model , RedirectAttributes redirectAttributes ) throws Exception {
		// View호출

		String returnURL = "/";
		String rootMenuId = (String)commandMap.get("rootMenuId");
		String menuId = (String)commandMap.get("menuId");
		String isPopUp = (String)commandMap.get("isPopUp");
		String lecMenuMarkYn = (String)commandMap.get("lecMenuMarkYn");
		LOG.debug("################################# ");
		LOG.debug("#### lecMenuMarkYn : " + lecMenuMarkYn );
		LOG.debug("################################# ");
		
		// 강의매뉴 표시 여부 Key 세션 설정
		HttpSession session = request.getSession(true);
		session.setAttribute("lecMenuMarkYn" , lecMenuMarkYn );         		
		
		
		// 교과목 메뉴가 아닐경우 교과목 관련 세션삭제 2017.02.20 csh
		if("N".equals(lecMenuMarkYn)){
			BizUtil.setEmptyLecInfo(request);
		}
		
		
//		HttpSession session = request.getSession();
//		ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) session.getAttribute( Globals.SESSION_MENU_LIST );

    	LoginInfo loginInfo = new LoginInfo();
//    	LoginInfo loginInfo = new LoginInfo(request, response);
		LOG.debug("#### loginInfo : " + loginInfo );
		ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) loginInfo.getAuthenticMenuInfo();
		
		LOG.debug("#### menuStructureList.size() : " + menuStructureList.size() );
		
		HashMap<String, Object> menuList = new HashMap<String,Object>();
		Map<String, Object> returnResultMap = new HashMap<String,Object>();
		
		for( CommbizMenuVO menuVO : menuStructureList ) {
			
			if( menuVO.getMenuId().equals( menuId ) ){
				
				String upperMenuNo = menuVO.getUpMenuId();
				returnURL = StringUtils.defaultIfBlank( menuVO.getMenuUrl() , "/");
		    	returnResultMap.put("rootMenuId",  menuVO.getRootMenuId() );
		    	returnResultMap.put("menuId", menuVO.getMenuId());
		    	

		    	returnResultMap.put("createAuthYn"		, menuVO.getCreateAuthYn());	// 생성 권한 여부
    			returnResultMap.put("readAuthYn"		, menuVO.getReadAuthYn());		// 상세 조회 권한 여부
    			returnResultMap.put("updateAuthYn"		, menuVO.getUpdateAuthYn());	// 수정 권한 여부
    			returnResultMap.put("deleteAuthYn"		, menuVO.getDeleteAuthYn());	// 삭제 권한 여부
    			returnResultMap.put("printAuthYn"		, menuVO.getPrintAuthYn());	// 출력 권한 여부
    			returnResultMap.put("downloadAuthYn"	, menuVO.getDownloadAuthYn());	// 다운로드 권한 여부
    			returnResultMap.put("otherAuthYn"		, menuVO.getOtherAuthYn());	// 기타 권한 여부
    			returnResultMap.put("listAuthYn"		, menuVO.getListAuthYn());		// 목록 조회 권한 여부
				
				
//			    redirectAttributes.addFlashAttribute("rootMenuId", menuVO.getRootMenuId());
//			    redirectAttributes.addFlashAttribute("menuId", menuVO.getMenuId());
//			    redirectAttributes.addAttribute("rootMenuId", menuVO.getRootMenuId());
//			    redirectAttributes.addAttribute("menuId", menuVO.getMenuId());
			    
			    LOG.debug("#### /commbiz/menu/menuMove.do ==>> menuId : " + menuId + " , returnURL : " + returnURL );
			    
			    // 게시판 메뉴ID 예외처리
			    if(returnURL.indexOf("?")>-1){
			    	returnURL+="&menuId="+menuId;
			    } 
			    
				break;
			}
		}

		if( -1 < returnURL.indexOf("alert(")){
	    	
			returnResultMap.put("retMsg", StringUtils.remove(StringUtils.remove(returnURL, "alert(\"") , "\");" ) );
	    	
			if( null != loginInfo.getLoginInfo() && "ADM".equals(loginInfo.getLoginInfo().getMemType() ) ){
				returnURL = "/la/main/lmsAdminMainPage.do";
			}else{
				returnURL = Globals.MAIN_PAGE;
			}
		}
		

		if( StringUtils.isBlank( returnURL ) || "#".equals( returnURL ) || 0 > returnURL.indexOf(".do")){
			returnURL = Globals.MAIN_PAGE;
//		    redirectAttributes.addFlashAttribute("retMsg", "등록되지 않은 메뉴를 호출 하였습니다.(Not Found Menu URL)");
	    	returnResultMap.put("retMsg", "등록되지 않은 메뉴를 호출 하였습니다.(Not Found Menu URL)");
		}

		redirectAttributes.addFlashAttribute("returnResultMap" , returnResultMap);
		
		return "redirect:" + returnURL;
	}

	@RequestMapping(value = "/{serviceArea}/commbiz/menu/bodyLocation.do")
	public String bodyLocation(HttpServletRequest request, HttpServletResponse response , @PathVariable("serviceArea") String serviceArea , @RequestParam Map<String, Object> commandMap, ModelMap model ) throws Exception {
		// View호출

		LOG.debug("#### /commbiz/menu/bodyLocation.do" );

		String rootMenuId = StringUtils.defaultIfBlank( (String)commandMap.get("rootMenuId") , (String)model.get("rootMenuId") );
		String menuId = StringUtils.defaultIfBlank( (String)commandMap.get("menuId") , (String)model.get("menuId") );		
		String isPopUp = (String)commandMap.get("isPopUp");
		
		HttpSession session = request.getSession();

		rootMenuId = StringUtils.defaultIfBlank( rootMenuId , (String)session.getAttribute("rootMenuId") );
		menuId = StringUtils.defaultIfBlank( menuId , (String)session.getAttribute("menuId") );
		
//		ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) session.getAttribute( Globals.SESSION_MENU_LIST );

//    	LoginInfo loginInfo = new LoginInfo();
    	LoginInfo loginInfo = new LoginInfo(request, response);
		ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) loginInfo.getAuthenticMenuInfo();
		
		HashMap<String, Object> menuList = new HashMap<String,Object>();
		
		for( CommbizMenuVO menuVO : menuStructureList ) {
			
			if( menuVO.getMenuId().equals( menuId ) ){

			    model.addAttribute( "menuAuthGroupId", menuVO.getAuthGroupId() );
			    model.addAttribute( "menuReadAuthYn", menuVO.getReadAuthYn() );
			    model.addAttribute( "menuListAuthYn", menuVO.getListAuthYn() );
			    model.addAttribute( "menuCreateAuthYn", menuVO.getCreateAuthYn() );
			    model.addAttribute( "menuUpdateAuthYn", menuVO.getUpdateAuthYn() );
			    model.addAttribute( "menuDeleteAuthYn", menuVO.getDeleteAuthYn() );
			    model.addAttribute( "menuPrintAuthYn", menuVO.getPrintAuthYn() );
			    model.addAttribute( "menuDownloadAuthYn", menuVO.getDownloadAuthYn() );
			    
			    model.addAttribute( "menuArea", menuVO.getMenuArea());
			    model.addAttribute( "menuRootId", menuVO.getRootMenuId());
			    model.addAttribute( "menuPathLeafNode", menuVO.getMenuPathLeafNode() );
			    model.addAttribute( "menuIdPathLeafNode", menuVO.getMenuIdPathLeafNode());
			    model.addAttribute( "menuTitle", menuVO.getMenuTitle());
			    
			    
				break;
			}
		}
		
		
		return "layout/oklms/" + serviceArea + "/bodyLocation";
	}
}
