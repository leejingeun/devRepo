/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.comm.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.sitglobal.oklms.comm.util.SpringBeanSupport;
import kr.co.sitglobal.oklms.commbiz.menu.service.CommbizMenuService;
import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.Globals;

 /**
 * 사용자 관련 정보를 조회하는 역활을 한다.
* 이진근
 * @since 2016. 07. 01.
 */
public class LoginInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7396985127032712205L;

	private static final Logger LOG = LoggerFactory.getLogger(LoginInfo.class);

	private final HttpSession session;
	private final String loginPageUsr;
	private final Map paramMap;

	public final HttpServletRequest request;
	public final HttpServletResponse response;
	public final String reqUri;

	private FilterConfig config;

	public LoginInfo() {

		ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest httpRequest = servletRequestAttribute.getRequest();
		HttpSession httpSession = httpRequest.getSession(true);

		this.request = httpRequest;
		this.response = null;
		this.session = httpSession;
		this.reqUri = httpRequest.getRequestURI();
		this.paramMap = httpRequest.getParameterMap();
		this.loginPageUsr = Globals.LOGIN_PAGE;

//		LOG.debug(this.loginPageUsr);

		this.session.setAttribute("LANGUAGE", "KOR");
	}
	public LoginInfo(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession(false);
		this.reqUri = request.getRequestURI();
		this.paramMap = request.getParameterMap();
		this.loginPageUsr = Globals.LOGIN_PAGE;

//		LOG.debug(this.loginPageUsr);

		this.session.setAttribute("LANGUAGE", "KOR");
	}

    public void putSessionToParameterMap( Map pMap ){
    	pMap.put("sessionMemSeq", getUserSeq());
    	pMap.put("sessionMemId", getUserId());
    	pMap.put("sessionMemName", getUserName());

       	pMap.put("sessionMemName" , getMemName() );
    	pMap.put("sessionMemEngName" , getMemEngName() );
    	pMap.put("sessionMemType" , getMemType() );
    	pMap.put("sessionAuthGroupId" , getAuthGroupId() );
    	pMap.put("sessionEmail" , getEmail() );
    	pMap.put("sessionLecId" , getLecId() );
    	pMap.put("sessionPeriodId" , getPeriodId() );
    	pMap.put("sessionClassId" , getClassId() );
		pMap.put("sessionPageDiv" , getPageDiv() );
		pMap.put("sessionLectureDiv" , getLectureDiv() );
    	pMap.put("sessionLessonId" , getLessonId() );
    	pMap.put("sessionLecStep" , getLecStep() );
    	pMap.put("sessionMenuArea" , getMenuArea() );
    	pMap.put("sessionSessionId" , getSessionId() );
    	pMap.put("sessionOverlaploginYn" , getOverlaploginYn() );
    	pMap.put("sessionLecTarget" , getLecTarget() );
    	pMap.put("sessionLecTargetYear" , getLecTargetYear() );
    	pMap.put("sessionFacilityNo" , getFacilityNo() );
    	pMap.put("sessionFacilityName" , getFacilityName() );

    	pMap.put("sessionDeptNm", getDeptNm());
    	pMap.put("sessionDeptNo", getDeptNo());


    	LOG.debug("======== SESSION To MAP ================" );
    	LOG.debug(pMap.toString() );
    	LOG.debug("=================================" );
    }

    public void putSessionToVo( BaseVO baseVo ){
    	baseVo.setSessionMemSeq(getUserSeq());
    	baseVo.setSessionMemId(getUserId());
    	baseVo.setSessionMemName(getUserName());

       	baseVo.setSessionMemName(		getMemName() );
    	baseVo.setSessionMemEngName(	getMemEngName() );
    	baseVo.setSessionMemType(		getMemType() );
    	baseVo.setSessionAuthGroupId(	getAuthGroupId() );
    	baseVo.setSessionEmail(		    getEmail() );
    	baseVo.setSessionLecId(		    getLecId() );
    	baseVo.setSessionPeriodId(		getPeriodId() );
    	baseVo.setSessionClassId(		getClassId() );
    	baseVo.setSessionPageDiv(		getPageDiv() );
		baseVo.setSessionLectureDiv(	getLectureDiv());
    	baseVo.setSessionLessonId(		getLessonId() );
    	baseVo.setSessionLecStep(		getLecStep() );
    	baseVo.setSessionMenuArea(		getMenuArea() );
    	baseVo.setSessionSessionId(		getSessionId() );
    	baseVo.setSessionOverlaploginYn(getOverlaploginYn() );
    	baseVo.setSessionLecTarget(		getLecTarget() );
    	baseVo.setSessionLecTargetYear(	getLecTargetYear() );
    	baseVo.setSessionFacilityNo(	getFacilityNo() );
    	baseVo.setSessionFacilityName(	getFacilityName() );
    	baseVo.setSessionDeptNm(getDeptNm());
    	baseVo.setSessionDeptNo(getDeptNo());
    	baseVo.setSessionIp( getUserIp() );
    	baseVo.setSessionCompanyId(getCompanyId());
    	
    	LOG.debug("======== SESSION To VO ================" );
    	LOG.debug(baseVo.toString() );
    	LOG.debug("=================================" );

    }

    /**
     * authGroupId 정보를 기준으로 현재 serviceArea( la , lu , lc ) 값을 반환한다.
     * @return
     * String
     */
    public String getServiceArea( String serviceArea ){
		String menuArea = "";
		String authGroupId = "";
		HttpSession session = request.getSession();
		if( null != this.getLoginInfo()){

			 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , this.getLoginInfo().getAuthGroupId() );
			 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , this.getLoginInfo().getMenuArea() ) ;
		}

//		2016AUTH0000001	슈퍼운영자
//		2016AUTH0000004	게스트
//		2016AUTH0000003	학습자
//		2016AUTH0000002	컨텐츠권한
//		2016AUTH0000005	튜터
		if( "2016AUTH0000001".equals(authGroupId)){
			if( "LMS".equals(menuArea)){
				serviceArea = "la";
			}else if( "LCMS".equals(menuArea)){
				serviceArea = "lc";
			}
		}

		serviceArea = StringUtils.defaultIfBlank( serviceArea , "lu" );
		return serviceArea;
    }

    /**
     * session에서 사용자 정보를 조회한다.
     * @return
     * Object
     */
    public LoginVO getLoginInfo(){

    	LoginVO vo = null;
		if( this.isLogin() && null != RequestContextHolder.getRequestAttributes() ){

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if( httpSession.getAttribute(Globals.SESSION_USER_VO) instanceof LoginVO ){

			    	LOG.debug("#### getLoginInfo() : instanceof LoginVO");
					vo = (LoginVO)httpSession.getAttribute(Globals.SESSION_USER_VO);
				}else{

			    	LOG.debug("#### getLoginInfo() : String");
//					UserActService userActService = null;
//					try {
//						userActService = (UserActService) SpringBeanSupport.getBean( "userActService" );
////						userActService = (UserActService) SpringBeanSupport.getBean( this.request , "userActService" );
//						if (userActService == null) {
//							LOG.error( "Fail to create 'UserActService' object" );
//							return vo;
//						}else{
//
//							String usrId = (String) httpSession.getAttribute(Globals.SESSION_USER_ID);
//
//							Map<String,Object> commandMap = new HashMap<String,Object>();
//							commandMap.put( "usrId", usrId);
//
//							List<LoginVO> userList = null;
//							try {
//								userList = userActService.getUserList(commandMap);
//							} catch (EgovBizException e) {
//								e.printStackTrace();
//							}
//							if( 0 < userList.size() ){
//
//								vo = (LoginVO) userList.get(0);
//						    	vo.setSessionMemSeq(vo.getMemSeq());
//						    	vo.setSessionMemId(vo.getMemId());
//						    	vo.setSessionMemName(vo.getMemName());
//								session.setAttribute(Globals.SESSION_USER_VO, vo);
//							}
//						}
//					} catch (NoSuchBeanDefinitionException ex) {
//						LOG.error( "No SSO ServiceImpl Class!" );
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
				}
			}
		}
		return vo;
    }

    /**
     * 단순 session 체크 : login check하여 login되어있지않으면 login page로 redirect
     * @return
     * boolean
     */
    public boolean isLogin()
    {
        String usrId = "";
        boolean isLogin = false;
        try {
        	usrId = (String) session.getAttribute(Globals.SESSION_USER_ID);

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if(StringUtils.isNotBlank(usrId)
						&& httpSession.getAttribute(Globals.SESSION_USER_VO) instanceof LoginVO
//						&& httpSession.getAttribute( Globals.SESSION_MENU_LIST ) instanceof List 슈퍼관리자는 메뉴정보를 매번 가져오는 형식이므로 로그인 여부 첵크에서 메뉴 리스트를 이용한 첵크는 빠져야함.
						){
					isLogin = true;
				}
			}
        }catch ( Exception e) {
        }

        return isLogin;
    }
	/**
	 * session 정보 return
	 *
	 * @param void
	 * @return String
	 * @see #
	 */
	public String getAttribute(String pAttributeName) {
		String rtnValue;
		if (session == null)
			return "";
		try {
			rtnValue = (String) session.getAttribute(pAttributeName);
			if (rtnValue == null)
				return "";
		} catch (Exception e) {
			return "";
		}

		return rtnValue;
	}

    /**
    * 사용자 ID return
    * @param void
    * @return String
    * @see   #
    */
    public String getUserSeq() {
        return getAttribute(Globals.SESSION_USER_SEQ);
    }

    /**
    * 사용자 ID return
    * @param void
    * @return String
    * @see   #
    */
    public String getUserId() {
        return getAttribute(Globals.SESSION_USER_ID);
    }

    /**
    * 사용자 NAME return
    * @param void
    * @return String
    * @see   #
    */
    public String getUserName() {
        return getAttribute(Globals.SESSION_USER_NAME);
    }

    /**
     * 회원 고유번호
     * @return
     * String
     */
    public String getMemSeq() {

        return getAttribute(Globals.MEM_SEQ          );
    }
    /**
     * 사용자 아이디
     * @return
     * String
     */
    public String getMemId() {

        return getAttribute(Globals.MEM_ID           );
    }
    /**
     * 사용자 성명
     * @return
     * String
     */
    public String getMemName() {

        return getAttribute(Globals.MEM_NAME         );
    }
    /**
     * 사용자 영문 성명
     * @return
     * String
     */
    public String getMemEngName() {

        return getAttribute(Globals.MEM_NAME_ENG     );
    }
    /**
     * 사융자유형
     * @return
     * String
     */
    public String getMemType() {

        return getAttribute(Globals.MEM_TYPE         );
    }
    /**
     * 권한그룹ID
     * @return
     * String
     */
    public String getAuthGroupId() {

        return getAttribute(Globals.AUTH_GROUP_ID    );
    }


    /**
     * 권한그룹NAME
     * @return
     * String
     */
    public String getAuthGroupName() {

        return getAttribute(Globals.AUTH_GROUP_NAME   );
    }

    /**
     * E-MAIL ADDRESS
     * @return
     * String
     */
    public String getEmail() {

        return getAttribute(Globals.EMAIL            );
    }
    /**
     * 강의 ID
     * @return
     * String
     */
    public String getLecId() {

        return getAttribute(Globals.LEC_ID           );
    }
    /**
     * 기수 ID
     * @return
     * String
     */
    public String getPeriodId() {

        return getAttribute(Globals.PERIOD_ID        );
    }
    /**
     * 반 ID
     * @return
     * String
     */
    public String getClassId() {

        return getAttribute(Globals.CLASS_ID         );
    }
    /**
     * 페이지 모드(일반, 강사, 관리자)
     * @return
     * String
     */
    public String getPageDiv() {

        return getAttribute(Globals.PAGE_DIV         );
    }
	 public String getLectureDiv() {

		 return getAttribute(Globals.LEC_DIV         );
	 }
    /**
     * 레슨 ID
     * @return
     * String
     */
    public String getLessonId() {

        return getAttribute(Globals.LESSON_ID  	     );
    }
    /**
     * 차시
     * @return
     * String
     */
    public String getLecStep() {

        return getAttribute(Globals.LEC_STEP         );
    }
    /**
     * 메뉴영역(LMS/LCMS구분)
     * @return
     * String
     */
    public String getMenuArea() {

        return getAttribute(Globals.MENU_AREA        );
    }
    /**
     * 세션 아이디(로그인 중복을 막기 위함)
     * @return
     * String
     */
    public String getSessionId(){

        return getAttribute(Globals.SESSION_ID       );
    }
    /**
     * 중복로그인 사용여부
     * @return
     * String
     */
    public String getOverlaploginYn() {

        return getAttribute(Globals.OVERLAPLOGIN_YN  );
    }
    /**
     * 교육대상
     * @return
     * String
     */
    public String getLecTarget() {

        return getAttribute(Globals.LEC_TARGET       );
    }
    /**
     * 교육대상연차
     * @return
     * String
     */
    public String getLecTargetYear() {

        return getAttribute(Globals.LEC_TARGET_YEAR  );
    }
    /**
     * 소속코드
     * @return
     * String
     */
    public String getFacilityNo() {

        return getAttribute(Globals.FACILITY_NO      );
    }
    /**
     * 소속명
     * @return
     * String
     */
    public String getFacilityName() {

    	return getAttribute(Globals.FACILITY_NAME    );
    }

    /**
     * 학과명
     * @return
     */
    public String getDeptNm(){

    	return getAttribute(Globals.DEPT_NM    );
    }
    /**
     * 학과번호
     * @return
     */
    public String getDeptNo(){
    	return getAttribute(Globals.DEPT_NO    );
    }

    /**
     * 학과번호
     * @return
     */
    public String getUserIp(){
    	return getAttribute(Globals.IP    );
    }
    
    
    /**
     * 회사아이디
     * @return
     */
    public String getCompanyId(){
    	return getAttribute(Globals.COMPANY_ID    );
    }






    /**
     * session에서 (메뉴 타입(팝업 등등...)과 상관없이)매뉴 정보를 조회한다. ( menuViewTypeCode 를 조건값으로 걸지 않음)
     * authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , ((LoginVO)this.getLoginInfo()).getAuthGroupId() );
     * menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , ((LoginVO)this.getLoginInfo()).getMenuArea() ) ;
     * @return
     * Object
     */
    public ArrayList<CommbizMenuVO> getAuthenticMenuInfo(){

    	LOG.debug("#### getAuthenticMenuInfo()");

//    	LOG.debug("#### getAuthenticMenuInfo(String serviceArea)");
//    	 ( serviceArea : RequestMapping(value = "/{serviceArea}/commbiz/menu/leftMenu.do" )

//    	String menuArea = StringUtils.defaultIfEmpty(serviceArea , "LMS");
//
//    	if( "la".equals(serviceArea) || "lu".equals(serviceArea) ){
//    		menuArea = "LMS";
//    	}else if( "lc".equals(serviceArea) ){
//    		menuArea = "LCMS";
//    	}


    	ArrayList<CommbizMenuVO> voList = null;
//		if( this.isLogin() && null != RequestContextHolder.getRequestAttributes() ){
		if( null != RequestContextHolder.getRequestAttributes() ){

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if( httpSession.getAttribute(Globals.SESSION_MENU_LIST) instanceof java.util.List && 0 < ( (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MENU_LIST) ).size() ){

			    	LOG.debug("#### getAuthenticMenuInfo() : instanceof java.util.List");

					voList = (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MENU_LIST);
				}else{

			    	LOG.debug("#### getAuthenticMenuInfo() : String");

					CommbizMenuService commbizMenuService = null;
					try {
						commbizMenuService = (CommbizMenuService) SpringBeanSupport.getBean( "commbizMenuService" );
//						userActService = (UserActService) SpringBeanSupport.getBean( this.request , "userActService" );
						if (commbizMenuService == null) {
							LOG.error( "Fail to create 'CommbizMenuService' object" );
							return voList;
						}else{

							LOG.debug("#### Menu info DataBase Selected!!" );
//							String usrId = (String) httpSession.getAttribute(Globals.SESSION_USER_ID);
							String authGroupId = null;
							String menuArea = null;
							String memType = null;
							if( null != this.getLoginInfo()){

								 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , this.getLoginInfo().getAuthGroupId() );
								 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , this.getLoginInfo().getMenuArea() ) ;
								 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , this.getLoginInfo().getMemType() ) ;
							} else {
								//사용자 메인페이지에 로그인 없이 접근시 필요함.
								authGroupId = "2016AUTH0000002";
								menuArea = "lu";
								memType = "STD";
							}

							Map<String,Object> commandMap = new HashMap<String,Object>();
//							commandMap.put( "usrId", usrId);
							commandMap.put( "memType", memType);
							commandMap.put( "menuArea", menuArea);
							commandMap.put( "authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )

					    	ArrayList<CommbizMenuVO> menuList = commbizMenuService.listMenu(commandMap);

					    	if( httpSession.getAttribute(Globals.SESSION_MENU_LIST) instanceof String){

					    		session.setAttribute(Globals.SESSION_MENU_LIST, menuList);
					    	}
					    	voList = menuList;
						}
					} catch (NoSuchBeanDefinitionException ex) {
						LOG.error( "No SSO ServiceImpl Class!" );
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return voList;
    }

    /**
     * session에서 (사용자 화면에 노출될 메뉴만 선별된)매뉴 정보를 조회한다.
     * authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , ((LoginVO)this.getLoginInfo()).getAuthGroupId() );
     * menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , ((LoginVO)this.getLoginInfo()).getMenuArea() ) ;
     * @return
     * Object
     */
    public ArrayList<CommbizMenuVO> getSimpleMenuInfo(){

    	LOG.debug("#### getSimpleMenuInfo()");

//    	LOG.debug("#### getSimpleMenuInfo(String serviceArea)");
//    	 ( serviceArea : RequestMapping(value = "/{serviceArea}/commbiz/menu/leftMenu.do" )
//    	String menuArea = StringUtils.defaultIfEmpty(serviceArea , "LMS");
//
//    	if( "la".equals(serviceArea) || "lu".equals(serviceArea) ){
//    		menuArea = "LMS";
//    	}else if( "lc".equals(serviceArea) ){
//    		menuArea = "LCMS";
//    	}

    	ArrayList<CommbizMenuVO> voList = null;
		if( this.isLogin() && null != RequestContextHolder.getRequestAttributes() ){

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if( httpSession.getAttribute(Globals.SESSION_MENU_LIST) instanceof java.util.List && 0 < ((ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MENU_LIST)).size() ){

			    	LOG.debug("#### getSimpleMenuInfo() : instanceof java.util.List");

			    	voList = new ArrayList<CommbizMenuVO>();
			    	ArrayList<CommbizMenuVO> tempList = (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MENU_LIST);
			    	if( null != tempList ){
			    	}else{
			    		// 슈퍼 운영자는 메뉴정보를 필요할 때마다 매번 가져오도록 한다.(LCMS 전환)
			    		tempList = this.getAuthenticMenuInfo();
			    	}

		    		LOG.debug("#### tempList.size() : " + tempList.size() );

		    		// 2016CCOD0000011 : 메뉴 노출 , 2016CCOD0000012 : 메뉴 비노출(페이지:메인,사용자관련등등...) , 2016CCOD0000013 : 메뉴 비노출(팝업) , 2016CCOD0000014 : 메뉴 비노출(import)
		    		for( CommbizMenuVO cbizMenuVO : tempList ){
		    			if( "2016CCOD0000011".equals(cbizMenuVO.getMenuViewTypeCode() ) ){
		    				voList.add(cbizMenuVO);
		    			}
		    		}

				}else{

			    	LOG.debug("#### getSimpleMenuInfo() : String");
				}
			}else{
				LOG.debug("#### httpSession is null");

			}
		}

		if( null == voList ){

			LOG.debug("#### null == voList ");

			CommbizMenuService commbizMenuService = null;
			try {
				commbizMenuService = (CommbizMenuService) SpringBeanSupport.getBean( "commbizMenuService" );
//				userActService = (UserActService) SpringBeanSupport.getBean( this.request , "userActService" );
				if (commbizMenuService == null) {
					LOG.error( "Fail to create 'CommbizMenuService' object" );
					return voList;
				}else{

//					String usrId = (String) httpSession.getAttribute(Globals.SESSION_USER_ID);
					String menuArea = null;
					String authGroupId = null;
					String memType = null;
					if( null != this.getLoginInfo()){
						 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , this.getLoginInfo().getAuthGroupId() );
						 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , this.getLoginInfo().getMenuArea() ) ;
						 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , this.getLoginInfo().getMemType() ) ;
					}

					Map<String,Object> commandMap = new HashMap<String,Object>();
//					commandMap.put( "usrId", usrId);
					commandMap.put( "memType", memType);
					commandMap.put( "menuArea", menuArea);
					commandMap.put( "authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
					commandMap.put( "menuViewTypeCode", "2016CCOD0000011"); // 2016CCOD0000011 : 메뉴 노출 , 2016CCOD0000012 : 메뉴 비노출(페이지:메인,사용자관련등등...) , 2016CCOD0000013 : 메뉴 비노출(팝업) , 2016CCOD0000014 : 메뉴 비노출(import)

					voList = commbizMenuService.listMenu(commandMap);
				}
			} catch (NoSuchBeanDefinitionException ex) {
				LOG.error( "No ServiceImpl Class!" );
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return voList;
    }

    /**
     * session에서 (모바일사용자 화면에 노출될 메뉴만 선별된)매뉴 정보를 조회한다.
     * authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , ((LoginVO)this.getLoginInfo()).getAuthGroupId() );
     * menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , ((LoginVO)this.getLoginInfo()).getMenuArea() ) ;
     * @return
     * Object
     */
    public ArrayList<CommbizMenuVO> getSimpleMobileMenuInfo(){

    	LOG.debug("#### getSimpleMobileMenuInfo()");

    	ArrayList<CommbizMenuVO> voList = null;
		if( this.isLogin() && null != RequestContextHolder.getRequestAttributes() ){

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if( httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST) instanceof java.util.List && 0 < ((ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST)).size() ){

			    	LOG.debug("#### getSimpleMobileMenuInfo() : instanceof java.util.List");

			    	voList = new ArrayList<CommbizMenuVO>();
			    	ArrayList<CommbizMenuVO> tempList = (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST);
			    	if( null != tempList ){
			    	}else{
			    		// 슈퍼 운영자는 메뉴정보를 필요할 때마다 매번 가져오도록 한다.(LCMS 전환)
			    		tempList = this.getAuthenticMenuInfo();
			    	}

		    		LOG.debug("#### tempList.size() : " + tempList.size() );

		    		// 2016CCOD0000011 : 메뉴 노출 , 2016CCOD0000012 : 메뉴 비노출(페이지:메인,사용자관련등등...) , 2016CCOD0000013 : 메뉴 비노출(팝업) , 2016CCOD0000014 : 메뉴 비노출(import)
		    		for( CommbizMenuVO cbizMenuVO : tempList ){
		    			if( "2016CCOD0000011".equals(cbizMenuVO.getMenuViewTypeCode() ) ){
		    				voList.add(cbizMenuVO);
		    			}
		    		}

				}else{

			    	LOG.debug("#### getSimpleMobileMenuInfo() : String");
				}
			}else{
				LOG.debug("#### httpSession is null");

			}
		}

		if( null == voList ){

			LOG.debug("#### null == voList ");

			CommbizMenuService commbizMenuService = null;
			try {
				commbizMenuService = (CommbizMenuService) SpringBeanSupport.getBean( "commbizMenuService" );
//				userActService = (UserActService) SpringBeanSupport.getBean( this.request , "userActService" );
				if (commbizMenuService == null) {
					LOG.error( "Fail to create 'CommbizMenuService' object" );
					return voList;
				}else{

//					String usrId = (String) httpSession.getAttribute(Globals.SESSION_USER_ID);
					String menuArea = null;
					String authGroupId = null;
					String memType = null;
					if( null != this.getLoginInfo()){
						 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , this.getLoginInfo().getAuthGroupId() );
						 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , this.getLoginInfo().getMenuArea() ) ;
						 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , this.getLoginInfo().getMemType() ) ;
					}

					Map<String,Object> commandMap = new HashMap<String,Object>();
//					commandMap.put( "usrId", usrId);
					commandMap.put( "memType", memType);
					commandMap.put( "menuArea", menuArea);
					commandMap.put( "authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )
					commandMap.put( "menuViewTypeCode", "2016CCOD0000011"); // 2016CCOD0000011 : 메뉴 노출 , 2016CCOD0000012 : 메뉴 비노출(페이지:메인,사용자관련등등...) , 2016CCOD0000013 : 메뉴 비노출(팝업) , 2016CCOD0000014 : 메뉴 비노출(import)

					voList = commbizMenuService.mobilelistMenu(commandMap);
				}
			} catch (NoSuchBeanDefinitionException ex) {
				LOG.error( "No ServiceImpl Class!" );
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return voList;
    }

    /**
     * session에서 (메뉴 타입(팝업 등등...)과 상관없이)매뉴 정보를 조회한다. ( menuViewTypeCode 를 조건값으로 걸지 않음)
     * authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , ((LoginVO)this.getLoginInfo()).getAuthGroupId() );
     * menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , ((LoginVO)this.getLoginInfo()).getMenuArea() ) ;
     * @return
     * Object
     */
    public ArrayList<CommbizMenuVO> getAuthenticMobileMenuInfo(){

    	LOG.debug("#### getAuthenticMobileMenuInfo()");

    	ArrayList<CommbizMenuVO> voList = null;
//		if( this.isLogin() && null != RequestContextHolder.getRequestAttributes() ){
		if( null != RequestContextHolder.getRequestAttributes() ){

			ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession httpSession = servletRequestAttribute.getRequest().getSession(true);

			if( null != httpSession){
				if( httpSession.getAttribute(Globals.SESSION_MENU_LIST) instanceof java.util.List && 0 < ( (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST) ).size() ){

			    	LOG.debug("#### getAuthenticMenuInfo() : instanceof java.util.List");

					voList = (ArrayList<CommbizMenuVO>)httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST);
				}else{

			    	LOG.debug("#### getAuthenticMenuInfo() : String");

					CommbizMenuService commbizMenuService = null;
					try {
						commbizMenuService = (CommbizMenuService) SpringBeanSupport.getBean( "commbizMenuService" );
//						userActService = (UserActService) SpringBeanSupport.getBean( this.request , "userActService" );
						if (commbizMenuService == null) {
							LOG.error( "Fail to create 'CommbizMenuService' object" );
							return voList;
						}else{

							LOG.debug("#### Menu info DataBase Selected!!" );
							String authGroupId = null;
							String menuArea = null;
							String memType = null;
							if( null != this.getLoginInfo()){

								 authGroupId = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.AUTH_GROUP_ID) , this.getLoginInfo().getAuthGroupId() );
								 menuArea = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MENU_AREA) , this.getLoginInfo().getMenuArea() ) ;
								 memType = StringUtils.defaultIfBlank( (String)session.getAttribute(Globals.MEM_TYPE) , this.getLoginInfo().getMemType() ) ;
							} else {
								//사용자 메인페이지에 로그인 없이 접근시 필요함.
								authGroupId = "2016AUTH0000002";
								menuArea = "mm";
								memType = "STD";
							}

							Map<String,Object> commandMap = new HashMap<String,Object>();
//							commandMap.put( "usrId", usrId);
							commandMap.put( "memType", memType);
							commandMap.put( "menuArea", menuArea);
							commandMap.put( "authGroupId", authGroupId); // 사용자 그룹 셋팅( 메뉴조회에 사용됨. )

					    	ArrayList<CommbizMenuVO> menuList = commbizMenuService.mobilelistMenu(commandMap);

					    	if( httpSession.getAttribute(Globals.SESSION_MM_MENU_LIST) instanceof String){

					    		session.setAttribute(Globals.SESSION_MM_MENU_LIST, menuList);
					    	}
					    	voList = menuList;
						}
					} catch (NoSuchBeanDefinitionException ex) {
						LOG.error( "No SSO ServiceImpl Class!" );
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		return voList;
    }
}
