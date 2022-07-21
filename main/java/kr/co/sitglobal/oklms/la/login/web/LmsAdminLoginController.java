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
package kr.co.sitglobal.oklms.la.login.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.co.sitglobal.oklms.comm.web.BaseController;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import kr.co.sitglobal.oklms.commbiz.member.service.MemberService;
//import kr.co.sitglobal.oklms.commbiz.member.vo.MemberVO;
 /**
 * 
 * @author 
 * @since 2016. 07. 01.
 */
@Controller
public class LmsAdminLoginController extends BaseController{

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
	 * 로그인 페이지 이동.
	 * @param commandMap
	 * @param model
	 * @return
	 * @throws Exception
	 * String
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = { "/la/login/goLmsAdminLogin.do" })
	public String goLmsAdminLogin(HttpServletRequest request, @RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		// View호출
		String returnPage = "";

		String reqUri = StringUtils.defaultIfBlank( (String)commandMap.get("reqUri") , "/la/main/lmsAdminMainPage.do" );
		String retMsg = StringUtils.defaultIfBlank( (String)commandMap.get("retMsg") , "" );
		String memId = StringUtils.defaultIfBlank( (String)commandMap.get("memId") , "" );
		

		request.setAttribute("reqUri" , reqUri );
		request.setAttribute("retMsg" , retMsg );
		request.setAttribute("memId" , memId );

		
		returnPage = "oklms_body/la/login/lmsAdminLogin";
		return returnPage;
	}
	
}
