package egovframework.com.cmm.service;

import org.apache.commons.lang3.StringUtils;

/**
 *  Class Name : Globals.java
 *  Description : 시스템 구동 시 프로퍼티를 통해 사용될 전역변수를 정의한다.
 *  Modification Information
 *
 *     수정일         수정자                   수정내용
 *   -------    --------    ---------------------------
 *   2009.01.19    박지욱          최초 생성
 *
 *  @author 공통 서비스 개발팀 박지욱
 *  @since 2009. 01. 19
 *  @version 1.0
 *  @see
 *
 */

public class Globals {
	//OS 유형
    public static final String OS_TYPE = EgovProperties.getProperty("Globals.OsType");
    //DB 유형
    public static final String DB_TYPE = EgovProperties.getProperty("Globals.DbType");
    //메인 페이지
    public static final String MAIN_PAGE = EgovProperties.getProperty("Globals.MainPage");
    //ShellFile 경로
    public static final String SHELL_FILE_PATH = EgovProperties.getPathProperty("Globals.ShellFilePath");
    //퍼로퍼티 파일 위치
    public static final String CONF_PATH = EgovProperties.getPathProperty("Globals.ConfPath");
    //Server정보 프로퍼티 위치
    public static final String SERVER_CONF_PATH = EgovProperties.getPathProperty("Globals.ServerConfPath");
    //Client정보 프로퍼티 위치
    public static final String CLIENT_CONF_PATH = EgovProperties.getPathProperty("Globals.ClientConfPath");
    //파일포맷 정보 프로퍼티 위치
    public static final String FILE_FORMAT_PATH = EgovProperties.getPathProperty("Globals.FileFormatPath");

    //파일 업로드 원 파일명
	public static final String ORIGIN_FILE_NM = "originalFileName";
	//파일 확장자
	public static final String FILE_EXT = "fileExtension";
	//파일크기
	public static final String FILE_SIZE = "fileSize";
	//업로드된 파일명
	public static final String UPLOAD_FILE_NM = "uploadFileName";
	//파일경로
	public static final String FILE_PATH = "filePath";

	//메일발송요청 XML파일경로
	public static final String MAIL_REQUEST_PATH = EgovProperties.getPathProperty("Globals.MailRequestPath");
	//메일발송응답 XML파일경로
	public static final String MAIL_RESPONSE_PATH = EgovProperties.getPathProperty("Globals.MailRResponsePath");

	// G4C 연결용 IP (localhost)
	public static final String LOCAL_IP = EgovProperties.getProperty("Globals.LocalIp");




    public static final String LOGIN_PAGE = EgovProperties.getProperty("Globals.LoginPage");

	/** 쿠키 아이디 이름( 암호화된 쿠키세션정보가 저장될 Key 이름 ) */
	public static final String SESSION_SYNC_ID = "SYNC_ID";
	/** 로그인 사용자 PK */
	public static final String SESSION_USER_SEQ = "SESSION_USER_SEQ";
    /** 로그인시 사용한 사용자 아이디 */
    public static final String CONNECTION_USER_ID = "CONNECTION_USER_ID";
	/** 로그인 사용자 아이디 */
	public static final String SESSION_USER_ID = "SESSION_USER_ID";
	/** 로그인 사용자 이름 */
	public static final String SESSION_USER_NAME = "SESSION_USER_NAME";
	/** 로그인 사용자 객체 */
	public static final String SESSION_USER_VO = "SESSION_USER_VO";
	/** 최종 로그인 일시 명칭 */
	public static final String SESSION_LAST_LOGIN_DT = "SESSION_LAST_LOGIN_DT";
	/** 메뉴 리스트 정보 */
	public static final String SESSION_MENU_LIST = "SESSION_MENU_LIST";
	/** 모바일 메뉴 리스트 정보 */
	public static final String SESSION_MM_MENU_LIST = "SESSION_MM_MENU_LIST";	
	public static final String LOCALE = "LOCALE";

    public static final String SESSION_SNYC_COOKIE_DOMAIN = EgovProperties.getProperty("Globals.sessionSnycCookieDomain");
	/** Session 유지 시간 지정 명칭 */
	public static final String SESSION_MAX_INACTIVE_INTERVAL = "SESSION_MAX_INACTIVE_INTERVAL";
	/** Session 유지 시간(초 , 1시간==3600초 ) */
	public static final String SESSION_MAX_INACTIVE_INTERVAL_TIME = StringUtils.defaultIfBlank((String) EgovProperties.getProperty("Globals.sessionMaxInactiveIntervalTime"),"3600");

	//로그인한 사용자에 진행중인 교과목 세션 KEY Start - jglee
	public static final String YYYY = "SESSION_YYYY";        				// 학년도
	public static final String TERM = "SESSION_TERM";        				// 학기
	public static final String SUBJECT_NAME= "SESSION_SUBJECT_NAME";        // 교과목명
	public static final String SUBJECT_CODE = "SESSION_SUBJECT_CODE";       // 교과목코드
	public static final String CLASS = "SESSION_CLASS";        				// 분반
	public static final String WEEK_CNT = "SESSION_WEEK_CNT";        		// 학습주차
	public static final String SUBJECT_ID = "SESSION_SUBJECT_ID";           // 교과별-교과목
	
	public static final String SUBJECT_TRANING_TYPE = "SESSION_SUBJECT_TRANING_TYPE";           // 훈련타입 ( OJT / OFF )
	public static final String SUBJECT_TYPE = "SESSION_SUBJECT_TYPE";           // 학부/고숙련 ( NORMAL / HSKILL )
	public static final String ONLINE_TYPE = "SESSION_ONLINE_TYPE";           // 온라인학습타입
	
	public static final String PRE_SUBJECT_CODE = "SESSION_PRE_SUBJECT_CODE";           // 이전교과 코드 (진행중인 교과X)
	
	//로그인한 사용자에 진행중인 교과목 세션 KEY End - jglee


	//센터담당자 기업선택 세션 Key Start
	public static final String BIZCOMPANY_ID     = "SESSION_BIZCOMPANY_ID";   // 선택된 회사아이디 세션
	public static final String BIZCOMPANY_NAME   = "SESSION_BIZCOMPANY_NAME"; // 선택된 회사명 세션
	//센터담당자 기업선택 세션 Key End

    public static final String SESSION_INFO     = "SessionParam";           // "LOGIN_SESSION_KEY";
    public static final String MEM_SEQ          = "SESSION_MEM_SEQ";        // 회원 고유번호
    public static final String MEM_ID           = "SESSION_MEM_ID";         // 사용자 아이디
    public static final String MEM_ASP_ID       = "SESSION_MEM_ASP_ID";     // 회원 ASP ID
    public static final String MEM_NAME         = "SESSION_MEM_NAME";       // 사용자 성명
    public static final String MEM_NAME_ENG     = "SESSION_MEM_NAME_ENG";   // 사용자 성명
    public static final String MEM_TYPE         = "SESSION_MEM_TYPE";       // 사융자유형
    public static final String AUTH_GROUP_ID    = "SESSION_AUTH_GROUP_ID";  // 권한그룹ID
    public static final String AUTH_GROUP_NAME    = "SESSION_AUTH_GROUP_NAME";  // 권한그룹명

    public static final String EMAIL            = "SESSION_EMAIL";          // E-MAIL ADDRESS
    public static final String LEC_ID           = "SESSION_LEC_ID";         // 강의 ID
    public static final String LEC_DIV           = "SESSION_LEC_DIV";         // 강의구분
    public static final String PERIOD_ID        = "SESSION_PERIOD_ID";      // 기수 ID
    public static final String CLASS_ID         = "SESSION_CLASS_ID";       // 반 ID
    public static final String PAGE_DIV         = "SESSION_PAGE_DIV";       // 페이지 모드(일반, 강사, 관리자)
    public static final String LESSON_ID  = "SESSION_LESSON_ID";     // 레슨 ID
    public static final String LEC_STEP         = "SESSION_LEC_STEP";       // 차시
    public static final String MENU_AREA        = "SESSION_MENU_AREA";      // 메뉴영역(LMS/LCMS구분)
    public static final String COMM_ID          = "SESSION_COMM_ID";        // 커뮤니티 ID
    public static final String BG_COLOR_CODE    = "SESSION_BG_COLOR_CODE";  // 커뮤니티 배경색 코드
    public static final String COMPANY_ID     = "SESSION_COMPANY_ID";   // 회사코드
    public static final String COMPANY_DOMAIN   = "SESSION_COMPANY_DOMAIN";        // 회원 고유번호
    public static final String COMPANY_LOGO    = "SESSION_COMPANY_LOGO";        // 회원 고유번호
    public static final String ASP_ID           = "SESSION_ASP_ID";         //ASP ID (현재 접속한URL의 ASP ID)
    public static final String SITE_TYPE        = "SESSION_SITE_TYPE";     //SITE_TYPE 교원(A) 일반(B)
    public static final String ASP_STYLE        = "SESSION_ASP_STYLE";         //ASP_STYLE(A,B,C)
    public static final String SESSION_ID        = "SESSION_ID";         //세션 아이디(로그인 중복을 막기 위함)
    public static final String OVERLAPLOGIN_YN        = "OVERLAPLOGIN_YN";         //중복로그인 사용여부
    public static final String LEC_TARGET           = "SESSION_LEC_TARGET";         //교육대상
    public static final String LEC_TARGET_YEAR        = "SESSION_LEC_TARGET_YEAR";         //교육대상연차
    public static final String FACILITY_NO        = "SESSION_FACILITY_NO";         //소속코드
    public static final String FACILITY_NAME        = "SESSION_FACILITY_NAME";         //소속명​
    public static final String DEPT_NM  		= "SESSION_DEPT_NM"; 			//학과명
    public static final String DEPT_NO		 	= "SESSION_DEPT_NO";			//학과번호
    public static final String IP		 	= "SESSION_IP";			//IP
    
    public static final String SSO_LOGIN_YN = "SESSION_SSO_LOGIN_YN";			// SSO 인증틍 통하여 로그인했는지 여부

}
