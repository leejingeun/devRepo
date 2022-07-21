package kr.co.sitglobal.oklms.la.main.web;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.comm.web.BaseController;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;

@Controller
public class LmsAdminMainPageController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(LmsAdminMainPageController.class);

//	@Resource(name = "mainPageService")
//	private MainPageService mainPageService;
	
	
	@RequestMapping(value = "/la/main/lmsAdminMainPage.do")
	public String lmsAdminMainPage(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출

		LOG.debug("#### /la/main/lmsAdminMainPage.do" );
		
		String retMsg = StringUtils.defaultIfBlank( (String) commandMap.get("retMsg"), "");
		model.addAttribute("retMsg", retMsg );
		
		String retMsgEncode = StringUtils.defaultIfBlank( (String) commandMap.get("retMsgEncode"), "");
		retMsgEncode = URLDecoder.decode( retMsgEncode ,"UTF-8");
		model.addAttribute("retMsgEncode", retMsgEncode );
		
		//사용자 권한
		LoginInfo loginInfo = new LoginInfo();
        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
        if( loginInfo.isLogin() ){
        	
        	model.addAttribute("authGroupId", loginVO.getAuthGroupId());
        	model.addAttribute("usrId", loginVO.getMemId());
        	model.addAttribute("usrSeq", loginVO.getMemSeq());
        }
		
		return "oklms/la/main/lmsAdminMainPage";
        //return  "redirect:/la/lecture/lectureLms/listLectureForIns.do";
	}

	/**
	 * 관리자 화면에서 LCMS 와 LMS 관리 화면 전환을 위해 사용됨.
	 * @param request
	 * @param response
	 * @param commandMap
	 * @param model
	 * @param redirectAttributes
	 * @param status
	 * @return
	 * @throws Exception
	 * String
	 */
	@RequestMapping(value = "/la/main/changeMenuArea.do")
	public String changeMenuArea(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> commandMap, ModelMap model, RedirectAttributes redirectAttributes, SessionStatus status) throws Exception {

		String redirectUrl = "/lc/main/lcmsMainPage.do";
		//사용자 권한
		LoginInfo loginInfo = new LoginInfo();
        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
        if( loginInfo.isLogin() ){

        	HttpSession  session = request.getSession(false);
        	String menuArea = loginInfo.getAttribute(Globals.MENU_AREA);
        	
        	LOG.debug("#### menuArea : " + menuArea );
        	
        	if( "LMS".equals( menuArea ) ){
	    		session.setAttribute(Globals.MENU_AREA, "LCMS");
	    		redirectUrl = "/lc/main/lcmsMainPage.do";        		
        	}else if( "LCMS".equals( menuArea ) ){

	    		session.setAttribute(Globals.MENU_AREA, "LMS");
	    		redirectUrl = "/la/main/lmsAdminMainPage.do";
        	}else{
	    		session.setAttribute(Globals.MENU_AREA, "LCMS");
	    		redirectUrl = "/lc/main/lcmsMainPage.do";
        	}
        }
    	
        LOG.debug("#### redirectUrl : " + redirectUrl );
    	
		// View호출
		return "redirect:" + redirectUrl;
	}
	
}
