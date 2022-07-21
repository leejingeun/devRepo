/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 11. 21.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.comm.interceptor;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.util.UrlConnectUtil;
import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;


 /**
 * 메뉴ID 를 사용하지않고 URL 주소를 직접 호출하는 경우 메뉴 아이디를 할당해주는 역활을 함.
* 이진근
 * @since
 */
public class MenuInfoInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuInfoInterceptor.class);

	/**
	 * request의 parameter 에 menuNo 정보가 없이 URL 을 호출하는경우 Menu No 정보를 조회하여 있으면 메뉴 정보 셋팅 처리.
	 */
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {

		String requestURI = request.getRequestURI(); // 요청 URI

		String strCurrentUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		String originalURL = urlPathHelper.getOriginatingRequestUri(request);
//		String subStrOriginalURL = StringUtils.substringBefore( originalURL , "." );

		String nttId = (String)request.getParameter("nttId");
		String menuArea = (String)request.getParameter("menuArea");

		LOGGER.debug("#### nttId : " + nttId + " ####");
		LOGGER.debug("#### menuArea : " + menuArea + " ####");
		LOGGER.debug("#### queryString : " + request.getQueryString() + " ####");

		String addParams = "";
		if( StringUtils.isNoneBlank( request.getQueryString() ) ){
			addParams = "?" + request.getQueryString();
		}
//		// 패턴 첵크 대상 Parameter
//		String bbsId = (String)request.getParameter("bbsId");	// 게시판...
//
//		if( StringUtils.isNoneBlank( bbsId ) ){
//			if( 0 == addParams.length() ){
//				addParams = addParams + "?";
//			}else{
//				addParams = addParams + "&";
//			}
//			addParams = addParams + "bbsId=" + bbsId;
//		}

		originalURL = originalURL + addParams;

		LOGGER.debug("");
		LOGGER.debug("#####################################################################################################");
		LOGGER.debug("#### MenuInfoInterceptor #### strCurrentUrl : " + strCurrentUrl + " ####");
		LOGGER.debug("#### MenuInfoInterceptor #### CHECK URI : " + originalURL + " ####");
//		LOGGER.debug("#### MenuInfoInterceptor #### requestURI : " + requestURI + " ####");
		LOGGER.debug("#####################################################################################################");
		LOGGER.debug("");

		if( StringUtils.isNoneBlank(originalURL) && ( 0 < originalURL.indexOf(".json") ||  0 < originalURL.indexOf(".jsp") ) ){
			// json 방식은 첵크하지 않는 경우인데 들어오는 경우 호출 메서드에 @ResponseBody 반환 하는지 첵크. ( com-servlet.xml )
			LOGGER.warn("#### *.json is exclude-mapping URL : return Value setting Check!! ( @ResponseBody )");
		}

		StackTraceElement[] stacks = new Throwable().getStackTrace();

		String contextPathStr = request.getContextPath();
		requestURI = requestURI.replace( contextPathStr , "");

		boolean isDirectURLCall = true;

		if( isDirectURLCall ){

			HttpSession session = request.getSession(true);

			Map<String, String[]> parameters = request.getParameterMap();

//			HttpSession session = request.getSession();
//			ArrayList<CommbizMenuVO> menuStructureList = (ArrayList<CommbizMenuVO>) session.getAttribute( Globals.SESSION_MENU_LIST );

//			String serviceArea = "lu"; // "/{serviceArea}/commbiz/menu/leftMenu.do"
//			if( 1 == StringUtils.countMatches( originalURL , "/la/") ){
//				serviceArea = "la";
//			}else if( 1 == StringUtils.countMatches( originalURL , "/lc/") ){
//				serviceArea = "lc";


//			}

	    	LoginInfo loginInfo = new LoginInfo();
			ArrayList<CommbizMenuVO> menuStructureList = loginInfo.getAuthenticMenuInfo();

			if( 1 == StringUtils.countMatches( originalURL , "/mm/") ){
				menuStructureList = loginInfo.getAuthenticMobileMenuInfo();
			}


			boolean isFindUrl = false;

			LOGGER.debug("#### menuStructureList Size : " + menuStructureList.size() );
			LOGGER.debug("#### Call!! : UrlConnectUtil.isRequestUrlAble()" );

			CommbizMenuVO menuObj = UrlConnectUtil.isRequestUrlAble( menuStructureList , originalURL );

			LOGGER.debug("#### menuStructureList Find MenuVO : " + menuObj );

			if( null != menuObj ){
				isFindUrl = true;

				LOGGER.debug("#### menuStructureList [ session.setAttribute('rootMenuId', ] Find MenuVO - menuObj.getRootMenuId() : " + menuObj.getRootMenuId() );
				LOGGER.info("#### menuStructureList [ session.setAttribute('menuId', ] Find MenuVO - menuObj.getMenuId() : " + menuObj.getMenuId() );

				session.setAttribute("rootMenuId" , menuObj.getRootMenuId() );
				session.setAttribute("menuId" , menuObj.getMenuId() );
				request.setAttribute("rootMenuId" , menuObj.getRootMenuId());
				request.setAttribute("menuId" , menuObj.getMenuId());

				request.setAttribute("curMenu" , menuObj);

		    	request.setAttribute("createAuthYn"		, menuObj.getCreateAuthYn());	// 생성 권한 여부
    			request.setAttribute("readAuthYn"		, menuObj.getReadAuthYn());		// 상세 조회 권한 여부
    			request.setAttribute("updateAuthYn"		, menuObj.getUpdateAuthYn());	// 수정 권한 여부
    			request.setAttribute("deleteAuthYn"		, menuObj.getDeleteAuthYn());	// 삭제 권한 여부
    			request.setAttribute("printAuthYn"		, menuObj.getPrintAuthYn());	// 출력 권한 여부
    			request.setAttribute("downloadAuthYn"	, menuObj.getDownloadAuthYn());	// 다운로드 권한 여부
    			request.setAttribute("otherAuthYn"		, menuObj.getOtherAuthYn());	// 기타 권한 여부
    			request.setAttribute("listAuthYn"		, menuObj.getListAuthYn());		// 목록 조회 권한 여부
			}
		}

		LOGGER.debug( "#### isDirectURICall ( " + originalURL + " ): " + isDirectURLCall );

		return true;
	}
}
