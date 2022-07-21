/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 11. 21.         First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.comm.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import egovframework.com.cmm.service.EgovProperties;

 /**
 * 로그인 여부를 첵크한다.
 * @author 이성원
 * @since 2016. 8. 25.
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginCheckInterceptor.class);
	/**
	 * 세션에 계정정보(LoginVO)가 있는지 여부로 인증 여부를 체크한다.
	 * 계정정보(LoginVO)가 없다면, 로그인 페이지로 이동한다.
	 */
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//		UrlPathHelper urlPathHelper = new UrlPathHelper(); 
//		String originalURL = urlPathHelper.getOriginatingRequestUri(request);
//		
//		LoginInfo loginInfo = new LoginInfo();
//
//        LOGGER.debug("");
//        LOGGER.debug("#####################################################################################################");
//		LOGGER.debug("#### LoginCheckInterceptor #### CHECK URI : " + originalURL + " ####");
//        
//        if( loginInfo.isLogin() ){
//    		LOGGER.debug("#### LoginCheckInterceptor #### loginInfo.isLogin() : true ####");
//            LOGGER.debug("#####################################################################################################");
//            LOGGER.debug("");
//			return true;
//        }else{
//			String loginPageUrl = "/lu/login/goLmsUserLogin.do"; // "/{serviceArea}/commbiz/menu/leftMenu.do"
//			if( 1 == StringUtils.countMatches( originalURL , "/la/") ){
//				loginPageUrl = "/la/login/goLmsAdminLogin.do";
//			}else if( 1 == StringUtils.countMatches( originalURL , "/lc/") ){
//				loginPageUrl = "/lc/login/goLcmsLogin.do";
//			}
//
//    		LOGGER.debug("#### LoginCheckInterceptor #### redirect: : " + loginPageUrl + " ####");
//            LOGGER.debug("#####################################################################################################");
//            LOGGER.debug("");
//			ModelAndView modelAndView = new ModelAndView("redirect:" + loginPageUrl);
//			throw new ModelAndViewDefiningException(modelAndView);
//
////			return true;
//        }
//	}

	
	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response, 
							 Object handler) throws IOException 
	{
		UrlPathHelper urlPathHelper = new UrlPathHelper(); 
		String originalURL = urlPathHelper.getOriginatingRequestUri(request);
		String uri = request.getRequestURI().toLowerCase();
		String header = request.getHeader("User-Agent").toLowerCase().replaceAll(" ", "");
		String strCurrentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		//관리자 및 사용자 첫로그인페이지 호출시 포워딩할 주소 비교를 위해 추가함 - jglee
		String returnURL = request.getRequestURI(); 
		
		
		LOGGER.debug("");
		LOGGER.debug("#####################################################################################################");
		LOGGER.debug("#### LoginInterceptor #### strCurrentUrl : " + strCurrentUrl + " ####");
		LOGGER.debug("#### LoginInterceptor #### CHECK URI : " + originalURL + " ####");
		LOGGER.debug("#### LoginInterceptor #### returnURL : " + returnURL + " ####");
		LOGGER.debug("#####################################################################################################");
		LOGGER.debug("");
		
		String retMsg = "";
		

		String requestUrlType = "";
		/*
		if( 1 == StringUtils.countMatches( originalURL , "/la/") ){
			requestUrlType = "la";
		}else if( 1 == StringUtils.countMatches( originalURL , "/lu/") ){
				requestUrlType = "lu";
		}else if( 1 == StringUtils.countMatches( originalURL , "/mm/") ){
			requestUrlType = "mm";
		}
		*/
		if( 1 == StringUtils.countMatches( returnURL , "/la/") ){
			requestUrlType = "la";
		}else if( 1 == StringUtils.countMatches( returnURL , "/lu/") ){
				requestUrlType = "lu";
		}else if( 1 == StringUtils.countMatches( returnURL , "/mm/") ){
			requestUrlType = "mm";
		}
		

		String addParams = "";
		if( StringUtils.isNoneBlank( request.getQueryString() ) ){
			addParams = "?" + request.getQueryString();
		}
//		String bbsId = (String)request.getParameter("bbsId");
//		
//		String addParams = "";
//		// 패턴 첵크 대상 Parameter
//		if( StringUtils.isNoneBlank( bbsId ) ){
//			if( 0 == addParams.length() ){
//				addParams = addParams + "?";
//			}else{
//				addParams = addParams + "&";
//			}
//			addParams = addParams + "bbsId=" + bbsId; 
//		}
		
    	
    	//============================================ SSO 인증관련 추가 ==========================================//
    	// SSO 사용여부 
		
		if(!requestUrlType.equals("mm")){
			String ssoYn =  EgovProperties.getProperty("Globals.ssoYn");
			String memId = "";
			LOGGER.info("#### ssoYn : "+ssoYn);
			// SSO 사용일 경우에만 체크
			if(ssoYn.equals("Y")){
				HttpSession ssoSession = request.getSession(true);
				String eXSignOnUserId = (String) ssoSession.getAttribute("eXSignOn.session.userid");
				
				LOGGER.info("#### eXSignOnUserId : "+eXSignOnUserId);
				
				// SSO 사용하여 로그인 했는지 여부 
				String sesssionSsoLoginYn = (String) ssoSession.getAttribute("SESSION_SSO_LOGIN_YN");
				
				LOGGER.info("#### sesssionSsoLoginYn : "+sesssionSsoLoginYn);
				
				try {
					JSONParser parser = new JSONParser();
					JSONObject jsonObj = (JSONObject)parser.parse(eXSignOnUserId);
					memId = jsonObj.get("empno").toString();
				} catch (Exception e) {
					memId = "";
				}
				
				if(!"".equals(memId)){ // 인증정보가 있으며 로그아웃 상태
					if(null == sesssionSsoLoginYn){
						// 로그인 처리
						String targetUrl = "/commbiz/login/ssoLoginProc.do";
						
						LOGGER.info("#### sso login targetUrl : " + targetUrl );
						
						response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );
					}
				} else { // 인증정보가 없으며 SSO 인증을 통하여 로그인 된 상태
					if("Y".equals(sesssionSsoLoginYn)){
						// 로그아웃 처리
						String targetUrl = "/commbiz/login/logout.do";
						
						LOGGER.info("#### sso logout targetUrl : " + targetUrl );
						
						response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );
					}
				}
			}
		}
		
		//================================================= SSO 인증관련 end ===========================================//
		
		LoginInfo loginInfo = new LoginInfo();

    	/* Login 되어있는지 check */
    	if(!loginInfo.isLogin()){
        	//----------------------------------------------------------------
        	// Login 되어있지 않으면 로그인화면으로 redirect 또는 로그인결과 json 으로 redirect
        	//----------------------------------------------------------------
			
			if( -1 == uri.indexOf(".json") || -1 == originalURL.indexOf(".json") ){
				LOGGER.debug("#### LoginInterceptor -> IsSessionCheck ####");
//				String returnURL = "<script>alert('로그인이 필요합니다.');</script>";
				
				// Iframe으로 호출하지 않은 경우는 정상동작하지 않음!!
//				String returnURL = "<script>alert('로그인이 필요합니다.');window.parent.location.href='" + request.getRequestURI().replace ( request.getServletPath(), "" ) + Globals.LOGIN_PAGE + "?reqUri=" + request.getServletPath() + "&retMsg=로그인이 필요합니다.';</script>";
//				String returnURL = "<script>alert('로그인이 필요합니다.');window.location.href='" + request.getRequestURI().replace ( request.getServletPath(), "" ) + Globals.LOGIN_PAGE + "?reqUri=" + request.getServletPath() + "&retMsg=로그인이 필요합니다.';</script>";
//				String returnURL = "<script>parent.com.alert('로그인이 필요합니다.');parent.com.moveUrl('" + request.getRequestURI().replace ( request.getServletPath(), "" ) + Globals.LOGIN_PAGE + "' , 'reqUri=" + request.getServletPath() + "&retMsg=로그인이 필요합니다.');</script>";
//				LOGGER.debug("#### returnURL : " + returnURL );
//				response.setContentType("text/html; charset=UTF-8");
//				response.getWriter().print(returnURL);
//				response.getWriter().flush();
				
				if( Pattern.matches( "/.*/login/go.*Login.*[.]((do)|(json))" , originalURL) ){
					return true;
				}else{

					String loginPageUrl = "/lu/login/goLmsUserLogin.do"; // "/{serviceArea}/commbiz/menu/leftMenu.do"
					if( "la".equals( requestUrlType ) ){
						loginPageUrl = "/lu/login/goLmsUserLogin.do";
					}else if( "lu".equals( requestUrlType ) ){
						
						// 모바일 분기추가
						if (header.indexOf("mobile") != -1) {
							loginPageUrl = "/mm/login/goLmsMobileLogin.do";
							response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + loginPageUrl );
						} else {
							loginPageUrl = "/lu/login/goLmsUserLogin.do";
						}

					}else if( "mm".equals( requestUrlType ) ){
						loginPageUrl = "/mm/login/goLmsMobileLogin.do";
					}


//					String targetUrl = loginPageUrl + "?reqUri=" + request.getServletPath() + addParams;
					String targetUrl = loginPageUrl + "?reqUri=" + request.getServletPath() + URLEncoder.encode( addParams ,"UTF-8");

					LOGGER.info("#### !targetUrl : " + targetUrl );
					
					response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );
//				response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + Globals.LOGIN_PAGE + "?retMsg=" + retMsg + "&reqUri=" + request.getServletPath());
					return false;
				}
				
			}else{
				LOGGER.debug("#### LoginInterceptor -> IsSessionCheckAjax #### " + request.getRequestURI() + "=="  + request.getRequestURI() + "==>" + request.getRequestURI().replace ( request.getServletPath(), "" ) );
				response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + "/cais/commbiz/usr/sessionNullForAjaxReturn.json");
				return false;
			}
		}else{
			if( 1 == StringUtils.countMatches( originalURL , "/la/login/goLmsAdminLogin.do") ){

				String targetUrl = "/la/main/lmsAdminMainPage.do";
				
				LOGGER.info("#### 1targetUrl : " + targetUrl );
				
				response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );
				return false;
				
			}else if( 1 == StringUtils.countMatches( originalURL , "/mm/login/goLmsMobileLogin.do") ){

				String targetUrl = "/mm/login/goLmsMobileLogin.do";
				
				LOGGER.info("#### 2targetUrl : " + targetUrl );
				
				response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );
				return false;
			}else if( 1 == StringUtils.countMatches( originalURL , "/lu/login/goLmsUserLogin.do") ){

				String targetUrl = "/lu/main/lmsUserMainPage.do";
				
				LOGGER.info("#### 3targetUrl : " + targetUrl );
				
				response.sendRedirect(  request.getRequestURI().replace ( request.getServletPath(), "" ) + targetUrl );		
				return false;		
			}
		}
    
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
	{
		try {
			super.postHandle(request, response, handler, modelAndView);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
	{
		try {
			super.afterCompletion(request, response, handler, ex);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
