package kr.co.sitglobal.oklms.comm.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

/**
 * @author windy
 *
 */
public final class Config {
    // context 실제 경로
    public static String realPath = "";
    public static String sysRealPath = "";
    /**
     * content root 경로
     */
    // 스콤 콘텐츠 실제 root 경로
    private static String scormRealPath = "";
    // 비스콤 콘텐츠 실제 root 경로
    private static String nonScormRealPath = "";
    
    // 로컬 테스트용
    static {
//        realPath = "D:/oklms/edu/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/oklms";
    }

    public static WebApplicationContext applicationContext;

    /**
     * 일자 타입
     */
    public static final String DATE_TYPE = "YYYY.MM.DD";
    public static final String DATE_TIME_TYPE = "YYYY.MM.DD HH24:MI";
    public static final String END_TIME = "23:59";
    /**
     * 사용자 사진파일 경로
     */
    public static final String USR_PHOTO_PATH = "/upload/user/";
    
    /**
     * 기본 권한그룹 추가 - 서버용
     */
    public static final String DEFAULT_AUTH_ADM 				= "2016AUTH0000001";    	//관리자
    public static final String DEFAULT_AUTH_STD     			= "2016AUTH0000002";    	//학습근로자
    public static final String DEFAULT_AUTH_PRO_TUTOR 			= "2016AUTH0000003";		//담당교수
    public static final String DEFAULT_AUTH_CRI_COMPANY 		= "2016AUTH0000004";		//기업전담자
    public static final String DEFAULT_AUTH_CRI_CENTER 			= "2016AUTH0000005";		//센터전담자
    public static final String DEFAULT_AUTH_CRI_DEPARTMENT 		= "2016AUTH0000006";		//학과전담자
    public static final String DEFAULT_AUTH_ADV_TUTOR 			= "2016AUTH0000007";		//지도교수
    public static final String DEFAULT_AUTH_SUPERVISOR_TEACHAR 	= "2016AUTH0000008";		//기업현장교사  
    
    /**
     * 기본 권한그룹 추가 - 화면용
     */
    public static final String ADM 		= "ADM";    	//관리자
    public static final String STD     	= "STD";    	//학습근로자
    public static final String PRT 		= "PRT";		//담당교수
    public static final String CCM 		= "CCM";		//기업전담자
    public static final String CCN 		= "CCN";		//센터전담자
    public static final String CDP 		= "CDP";		//학과전담자
    public static final String ATT 		= "ATT";		//지도교수
    public static final String COT 		= "COT";		//기업현장교사
    
    
    /*public static final String DEFAULT_AUTH_CONTENTS = "2016AUTH0000002";    //컨텐츠권한 2016AUTH0000002
    public static final String DEFAULT_AUTH_STD      = "2016AUTH0000003";    //학습자 2016AUTH0000003
    public static final String DEFAULT_GUEST_AUTH = "2016AUTH0000004";	//게스트권한 : 2016AUTH0000004
    public static final String DEFAULT_AUTH_INS_NEW = "2016AUTH0000005";    //교수자(튜터) 2016AUTH0000005
    
    public static final String DEFAULT_AUTH     = "2009AUTH0000004";    //일반
    public static final String DEFAULT_AUTH_INS = "2009AUTH0000002";    //교수자(튜터)
    public static final String DEFAULT_AUTH_ASS = "2009AUTH0000003";    //조교
    public static final String DEFAULT_AUTH_EDU = "2009AUTH0000005";    //교육담당자
    public static final String DEFAULT_AUTH_SME = "2009AUTH0000008";    //SME( 저작권위원회신규)
    public static final String DEFAULT_AUTH_LEC_ADM = "2010AUTH0000001";    //과정운영자( 저작권위원회신규)
    public static final String DEFAULT_AUTH_DEF_ADM = "2010AUTH0000002";    //운영자( 저작권위원회신규)
    public static final String DEFAULT_AUTH_CUS = "2010AUTH0000003";    //고객센터( 저작권위원회신규)
    public static final String DEFAULT_AUTH_ASP_ADM = "2011AUTH0000003"; //ASP운영자 추후 변경
    public static final String DEFAULT_MOBILE_AUTH = "2011AUTH0000001"; //모바일 학습자 추후 변경
    public static final String DEFAULT_MOBILE_AUTH_ADM = "2011AUTH0000002"; //모바일 운영자 추후 변경
    
    //신규권한
    public static final String DEFAULT_AUTH_EXAM = "2014AUTH0000001";	//자격시험 사용자
    public static final String DEFAULT_AUTH_COM = "2014AUTH0000002";	//공통 사용자
    public static final String DEFAULT_AUTH_EDU_ADM = "2014AUTH0000003";	//교육 운영자
    public static final String DEFAULT_AUTH_EXAM_ADM = "2014AUTH0000004";	//자격시험 운영자
*/
    /**
     * 메뉴 영역
     */
    public static final String MENU_LMS     = "LMS";
    public static final String MENU_LCMS    = "LCMS";

    /**
     * 메뉴 구분
     * STD : 일반사용자, ADM : 관리자, ENG : 영문학습자,
     * AST : 조교자, INS : 강사
     */
    public static final String MENU_STD = "STD";
    public static final String MENU_AST = "AST";
    public static final String MENU_INS = "INS";
    public static final String MENU_ADM = "ADM";
    public static final String MENU_ENG = "ENG";
    public static final String MENU_LECTURE = "STD04";                      // 강의 안내 메뉴 ID
    public static final String MENU_ENG_LECTURE = "ENG00";                  // 강의 안내 메뉴 ID

    /**
     * 카테고리 구분
     * 01 : 강의 카테고리
     * 02 : LCMS 카테고리
     */
    public static final String CATE_LECTURE     = "01";
//    public static final String CATE_LCMS        = "02";
    // LCMS 카테고리를 별도로 관리하지 않고 강의 카테고리를 같이 사용하도록 함
    public static final String CATE_LCMS        = "01";
    public static final String CATE_COMMU       = "03";

    /**
     * 강의 구분
     * 01 : 진행중인 강의
     * 02 : 지난강의
     * 03 : 예정강의
     * 04 : 미개설강의
     */
    public static final String LEC_DIV          = "lectureDiv";
    public static final String LEC_ING          = "01";
    public static final String LEC_PASS         = "02";
    public static final String LEC_EXP          = "03";
    public static final String LEC_NOT_COMP     = "04";

    public static final String DIV_ONLINE       ="N";    //온라인
    public static final String DIV_OFFLINE      ="F";    //오프라인
    public static final String DIV_BLENDED      ="B";    //블렌디드(혼합)

    /**
     * 이전 페이지로 이동 파라메터
     */
    public static final String PARAM_HISTORY_BACK = "historyBack";

    /**
     * 강의 유형
     * S : 스콤형
     * N : 비스콤형 
     */
    public static final String LEC_TYPE_SCORM	= "S";
    public static final String LEC_TYPE_NONSCORM= "N";

    /**
     * 강의관련 파라미터 변수명
     */
    public static final String PARAM_LEC_ID     	= "lecId";
    public static final String PARAM_PERIOD_ID  	= "periodId";
    public static final String PARAM_CLASS_ID   	= "classId";
    public static final String PARAM_LESSON_ID  	= "lessonId";
    public static final String PARAM_LEC_STEP   	= "lecStep";
    public static final String PARAM_LEC_DIV 		= "lectureDiv";
    public static final String PARAM_PRE_EXAM_YN 	= "preExamYN";


    // 미응시
    public static final int EXAM_STATUS_NOT_APPLY = 70;
    // 출제완료
    public static final int EXAM_STATUS_APPLY = 60;
    // 종료
    public static final int EXAM_STATUS_FINISHED = 50;
    // 진행중
    public static final int EXAM_STATUS_PROGRESS = 40;
    // 출제완료
    public static final int EXAM_STATUS_SETTING = 30;
    // 준비중
    public static final int EXAM_STATUS_PREPARE = 20;
    // 시험 비상시 default시간
    public static final String EXAM_DEFAULT_TIME = "00";

    /**
     * 게시판 유형 상수
     * COM_BOARD_NOTICE : 공통 공지사항
     * COM_BOARD_FAQ    : 공통 FAQ
     * COM_BOARD_QNA    : 공통 Q&A
     * COM_BOARD_DATA   : 공통 자료실
     * COMU_BOARD_NOTI  : 커뮤니티 공지사항
     * COMU_BOARD_NEWS  : 커뮤니티 산기대뉴스
     * COM_BOARD_TECH   : 공통 최신정보기술 
     * COM_BOARD_FTA    : 공통 FTA 관련보도 
     * COM_BOARD_REF    : 공통 환불요청
     * @version : 1.0
     * @author  : 이경호

     */
    public static final String COM_BOARD_NOTICE  = "COM_BOARD_NOTICE";
    public static final String COM_BOARD_FAQ     = "COM_BOARD_FAQ";
    public static final String COM_BOARD_QNA     = "COM_BOARD_QNA";
    public static final String COM_BOARD_DATA    = "COM_BOARD_DATA";
    public static final String COMU_BOARD_NOTI   = "COMU_BOARD_NOTI";
    public static final String COMU_BOARD_NEWS   = "COMU_BOARD_NEWS";
    public static final String COM_BOARD_TECH   = "COM_BOARD_TECH";
    public static final String COM_BOARD_FTA   = "COM_BOARD_FTA";
    public static final String COM_BOARD_REF   = "COM_BOARD_REF";
    
    
    /**
     * 커뮤니티관련 파라미터 변수명
     */
    public static final String PARAM_COMM_ID            = "commId";
    public static final String PARAM_BG_COLOR_CODE      = "bgColorCode";

    /**
     * 메뉴관련 파라미터 변수명
     */
    public static final String PARAM_MENU_ID            = "menuId";

    /**
     * 커뮤니티관련 디폴트값
     */
    public static final String DEFAULT_BG_COLOR_CODE    = "02";
    public static final String DEFAULT_TITLE_IMAGE_ID   = "2007CIMG0000001";

    /**
     * 커뮤니티회원관련 파라미터 변수명
     */
    public static final String MEM_GRADE_01 = "01";   //준회원
    public static final String MEM_GRADE_02 = "02";   //정회원
    public static final String MEM_GRADE_03 = "03";   //운영진
    public static final String MEM_GRADE_04 = "04";   //운영자
    public static final String JOIN_STATUS_01 = "01"; //가입신청
    public static final String JOIN_STATUS_02 = "02"; //승인
    public static final String JOIN_STATUS_03 = "03"; //거절
    public static final String MEM_STATUS_01 = "01";  //정상
    public static final String MEM_STATUS_02 = "02";  //탈퇴
    public static final String MEM_STATUS_03 = "03";  //강제탈퇴
    public static final String COMMU_STATUS_01 = "01"; //정상
    public static final String COMMU_STATUS_02 = "02"; //폐쇄
    public static final String COMMU_STATUS_03 = "03"; //강제폐쇄
    

    /**
     * SESSION 변수
     */
    //로그인한 사용자에 진행중인 교과목 세션 KEY Start - jglee
  	public static final String YYYY = "SESSION_YYYY";        				// 학년도
  	public static final String TERM = "SESSION_TERM";        				// 학기
  	public static final String SUBJECT_NAME= "SESSION_SUBJECT_NAME";        // 교과목명
  	public static final String SUBJECT_CODE = "SESSION_SUBJECT_CODE";       // 교과목코드
  	public static final String CLASS = "SESSION_CLASS";        				// 분반
  	public static final String WEEK_CNT = "SESSION_WEEK_CNT";        		// 학습주차
  	public static final String SUBJECT_ID = "SESSION_SUBJECT_ID";           // 교과별-교과목
  	//로그인한 사용자에 진행중인 교과목 세션 KEY End - jglee
  	
    public static final String SESSION_INFO     = "SessionParam";           // "LOGIN_SESSION_KEY";
    public static final String MEM_SEQ          = "SESSION_MEM_SEQ";        // 회원 고유번호
    public static final String MEM_ID           = "SESSION_MEM_ID";         // 사용자 아이디
    public static final String MEM_ASP_ID       = "SESSION_MEM_ASP_ID";     // 회원 ASP ID
    public static final String MEM_NAME         = "SESSION_MEM_NAME";       // 사용자 성명
    public static final String MEM_NAME_ENG     = "SESSION_MEM_NAME_ENG";   // 사용자 성명
    public static final String MEM_TYPE         = "SESSION_MEM_TYPE";       // 사융자유형
    public static final String AUTH_GROUP_ID    = "SESSION_AUTH_GROUP_ID";  // 권한그룹ID
    public static final String EMAIL            = "SESSION_EMAIL";          // E-MAIL ADDRESS
    public static final String LEC_ID           = "SESSION_LEC_ID";         // 강의 ID
    public static final String PERIOD_ID        = "SESSION_PERIOD_ID";      // 기수 ID
    public static final String CLASS_ID         = "SESSION_CLASS_ID";       // 반 ID
    public static final String PAGE_DIV         = "SESSION_PAGE_DIV";       // 페이지 모드(일반, 강사, 관리자)
    public static final String LESSON_ID		= "SESSION_LESSON_ID";	    // 레슨 ID
    public static final String LEC_STEP         = "SESSION_LEC_STEP";       // 차시
    public static final String MENU_AREA        = "SESSION_MENU_AREA";      // 메뉴영역(LMS/LCMS구분)
    public static final String COMM_ID          = "SESSION_COMM_ID";        // 커뮤니티 ID
    public static final String BG_COLOR_CODE    = "SESSION_BG_COLOR_CODE";  // 커뮤니티 배경색 코드
    public static final String COMPANY_ID    	= "SESSION_COMPANY_ID";  	// 회사코드
    public static final String COMPANY_DOMAIN   = "SESSION_COMPANY_DOMAIN";        // 회원 고유번호
    public static final String COMPANY_LOGO   	= "SESSION_COMPANY_LOGO";        // 회원 고유번호
    public static final String ASP_ID           = "SESSION_ASP_ID";	        //ASP ID (현재 접속한URL의 ASP ID)
    public static final String SITE_TYPE        = "SESSION_SITE_TYPE";	    //SITE_TYPE 교원(A) 일반(B)
    public static final String ASP_STYLE        = "SESSION_ASP_STYLE";	        //ASP_STYLE(A,B,C)
    public static final String SESSION_ID        = "SESSION_ID";	        //세션 아이디(로그인 중복을 막기 위함)
    public static final String OVERLAPLOGIN_YN        = "OVERLAPLOGIN_YN";	        //중복로그인 사용여부
    public static final String LEC_TARGET        			= "SESSION_LEC_TARGET";	        //교육대상
    public static final String LEC_TARGET_YEAR        = "SESSION_LEC_TARGET_YEAR";	        //교육대상연차
    public static final String FACILITY_NO        = "SESSION_FACILITY_NO";	        //소속코드
    public static final String FACILITY_NAME        = "SESSION_FACILITY_NAME";	        //소속명
   
    /**
     * RTE 관려 SESSION 변수
     */
    public static final String COURSE_ID        = "COURSEID";               //
    public static final String COURSE_TITLE     = "COURSETITLE";            //
    public static final String SCO_ID           = "SCOID";                  //
    public static final String ACTIVITY_ID      = "ACTIVITYID";             //
    public static final String NUM_ATTEMPTS     = "NUMATTEMPTS";            //
    public static final String EXIT             = "EXIT";                   //
    public static final String TOC              = "TOC";                    //

    /**
     * RTE Object file ROOT path
     */
    public static final String RTE_OBJ_ROOT     = "RTEOBJROOT";

    /**
     * 강의실 유형 구분
     * 01 : 나의강의실 강의실
     * 02 : 과정안내 강의실
     */
    public static final String MY_LECTURE       = "01";
    public static final String COURSE_LECTURE   = "02";

    public static final String MENU_NAVI_KEY    = "menuNavi"; // navigation menu attribute key


    public static final String DEFAULT_PACKAGE_IDX = "2000PKGE0000000";
    public static final String DEFAULT_ORG_IDX = "2000ORGA0000000";

    private static final String CONTENT_PATH = "/content";
    
    /**
     * 메일 유형 
     */
    public static final String MAIL_CLASS_DIRECT = "DIRT";
    public static final String MAIL_CLASS_PROGRESS = "PROG";
    public static final String MAIL_CLASS_ENTER = "ENTR";
    public static final String MAIL_CLASS_PASSWORD = "PASS";
    public static final String MAIL_CLASS_ACCEPT = "ACET";
    public static final String MAIL_CLASS_CANCEL = "CANC";
    public static final String MAIL_CLASS_CERTI = "CRTI";
    

}
