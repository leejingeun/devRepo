package kr.co.sitglobal.oklms.comm.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.util.Config;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;

public class BaseVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2559620674663202106L;

	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;

	/** select total count */
    private String totalCount = "";

    /** login usr_id */
    private String sessionMemId = "";

    /** login usr_seq */
    private String sessionMemSeq = "";

    /** login usr_name */
    private String sessionMemName = "";
    
    /** login sso_yn */
	private String sessionSsoLoginYn = "";

    
	private String sessionMemEngName;
    private String sessionMemType;
    private String sessionAuthGroupId;
    private String sessionEmail;
    private String sessionLecId;
    private String sessionPeriodId;
    private String sessionClassId;
	private String sessionPageDiv;
	private String sessionLectureDiv;
    private String sessionLessonId;
    private String sessionLecStep;
    private String sessionMenuArea;
    private String sessionSessionId;
    private String sessionOverlaploginYn;
    private String sessionLecTarget;
    private String sessionLecTargetYear;
    private String sessionFacilityNo;
    private String sessionFacilityName;
    private String sessionDeptNm;
    private String sessionDeptNo;
    private String sessionYyyy;
    private String sessionTerm;
    private String sessionSubjectCode;
    private String sessionSubClass;
	private String sessionIp;
	private String sessionCompanyId;

	public static final int WRITE_MODE = 100;
	public static final int UPDATE_MODE = 200;
	public static final int REPLY_MODE = 300;
	public static final int VIEW_MODE = 400;

	protected int mode;

	protected String deleteYn = "";
	protected String insertDate = "";
	protected String insertDt = "";
	protected String insertUser = "";
	protected String updateDate = "";
	protected String updateDt = "";
	protected String updateUser = "";
	protected String insertUserName = "";
	protected String updateUserName = "";

	protected String pageDiv = "KOR";

	/**
	 * 메뉴 영역
	 */
	protected String configMenuLms = Config.MENU_LMS; //"LMS"
	protected String configMenuLcms = Config.MENU_LCMS; //"LCMS"

	/**
	 * 메뉴 구분
	 * STD : 일반사용자, ADM : 관리자, ENG : 영문학습자, AST : 조교자, INS : 강사
	 */
	protected String configMenuStd =  Config.MENU_STD; // "STD";
	protected String configMenuAst =  Config.MENU_AST; // "AST";
	protected String configMenuIns =  Config.MENU_INS; // "INS";
	protected String configMenuAdm =  Config.MENU_ADM; // "ADM";
	protected String configMenuEng =  Config.MENU_ENG; // "ENG";
	protected String configMenuLecture =  Config.MENU_LECTURE; // "STD04";                      // 강의 안내 메뉴 ID
	protected String configMenuEngLecture =  Config.MENU_ENG_LECTURE; // "ENG00";                  // 강의 안내 메뉴 ID

	/**
	 * 카테고리 구분
	 * 01 : 강의 카테고리
	 * 02 : LCMS 카테고리
	 */
	protected String configCateLecture = 	Config.CATE_LECTURE; // "01"	// 카테고리 구분 01 : 강의 카테고리 02 : LCMS 카테고리
	// LCMS 카테고리를 별도로 관리하지 않고 강의 카테고리를 같이 사용하도록 함
	protected String configCateLcms = 		Config.CATE_LCMS; //01
	protected String configCateCommu = 		Config.CATE_COMMU; //03

	/**
	 * 강의 구분
	 * 01 : 진행중인 강의
	 * 02 : 지난강의
	 * 03 : 예정강의
	 */
	protected String configLecDiv = 		Config.LEC_DIV; //lectureDiv
	protected String configLecIng = 		Config.LEC_ING; //01
	protected String configLecPass = 		Config.LEC_PASS; //02
	protected String configLecExp = 		Config.LEC_EXP; //03
	protected String configLecNotComp =		Config.LEC_NOT_COMP; //04

	protected String configDIV_ONLINE =		Config.DIV_ONLINE; //N : 온라인
	protected String configDivOffline =		Config.DIV_OFFLINE; //F : 오프라인
	protected String configDivBlended =		Config.DIV_BLENDED; //B : 블렌디드(혼합)


	protected String configDateType = 		Config.DATE_TYPE; // "YYYY.MM.DD"	// 일자 타입
	protected String configEndTime = 		Config.END_TIME; // "23:59"
	protected String configDateTimeType = 	Config.DATE_TIME_TYPE; // "YYYY.MM.DD HH24:MI"
	//protected String configDefaultAspId = 	Config.DEFAULT_ASP_ID; // "2014AASP0000001"	// 기본 ASP_ID
	//protected String configsitgloballearnTemp = 	Config.sitglobalLEARN_TEMP; // "sitglobalLEARNTEMP"	// 수강신청관련 변수

	public String getConfigMenuIns() {
		return configMenuIns;
	}

	public String getSessionDeptNm() {
		return sessionDeptNm;
	}

	public void setSessionDeptNm(String sessionDeptNm) {
		this.sessionDeptNm = sessionDeptNm;
	}

	public String getSessionDeptNo() {
		return sessionDeptNo;
	}

	public void setSessionDeptNo(String sessionDeptNo) {
		this.sessionDeptNo = sessionDeptNo;
	}

	public void setConfigMenuIns(String configMenuIns) {
		this.configMenuIns = configMenuIns;
	}

	public String getConfigDateType() {
		return configDateType;
	}

	public void setConfigDateType(String configDateType) {
		this.configDateType = configDateType;
	}

	public String getConfigEndTime() {
		return configEndTime;
	}

	public void setConfigEndTime(String configEndTime) {
		this.configEndTime = configEndTime;
	}

	public String getConfigDateTimeType() {
		return configDateTimeType;
	}

	public void setConfigDateTimeType(String configDateTimeType) {
		this.configDateTimeType = configDateTimeType;
	}
	public String getSessionLevel() {		
		return BizUtil.getLevel(sessionMemId);
	}
/*	public String getConfigDefaultAspId() {
		return configDefaultAspId;
	}

	public void setConfigDefaultAspId(String configDefaultAspId) {
		this.configDefaultAspId = configDefaultAspId;
	}*/

	public String getConfigCateLecture() {
		return configCateLecture;
	}

	public void setConfigCateLecture(String configCateLecture) {
		this.configCateLecture = configCateLecture;
	}

	/*public String getConfigsitgloballearnTemp() {
		return configsitgloballearnTemp;
	}

	public void setConfigsitgloballearnTemp(String configsitgloballearnTemp) {
		this.configsitgloballearnTemp = configsitgloballearnTemp;
	}*/
	public String getPageDiv() {
		return pageDiv;
	}
	public void setPageDiv(String pageDiv) {
		this.pageDiv = pageDiv;
	}
	public static int getWriteMode() {
		return WRITE_MODE;
	}
	public static int getUpdateMode() {
		return UPDATE_MODE;
	}
	public static int getReplyMode() {
		return REPLY_MODE;
	}
	public static int getViewMode() {
		return VIEW_MODE;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getInsertUser() {
		return insertUser;
	}
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getInsertUserName() {
		return insertUserName;
	}
	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	/**
	 * @return the searchCondition
	 */
	public String getSearchCondition() {
		return searchCondition;
	}
	/**
	 * @param searchCondition the searchCondition to set
	 */
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	/**
	 * @return the searchKeyword
	 */
	public String getSearchKeyword() {
		return searchKeyword;
	}
	/**
	 * @param searchKeyword the searchKeyword to set
	 */
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	/**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the pageUnit
	 */
	public int getPageUnit() {
		return pageUnit;
	}
	/**
	 * @param pageUnit the pageUnit to set
	 */
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the firstIndex
	 */
	public int getFirstIndex() {
		return firstIndex;
	}
	/**
	 * @param firstIndex the firstIndex to set
	 */
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	/**
	 * @return the lastIndex
	 */
	public int getLastIndex() {
		return lastIndex;
	}
	/**
	 * @param lastIndex the lastIndex to set
	 */
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	/**
	 * @return the recordCountPerPage
	 */
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	/**
	 * @param recordCountPerPage the recordCountPerPage to set
	 */
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	/**
	 * @return the sessionMemId
	 */
	public String getSessionMemId() {
		return sessionMemId;
	}
	/**
	 * @param sessionMemId the sessionMemId to set
	 */
	public void setSessionMemId(String sessionMemId) {
		this.sessionMemId = sessionMemId;
	}
	/**
	 * @return the sessionMemSeq
	 */
	public String getSessionMemSeq() {
		return sessionMemSeq;
	}
	/**
	 * @param sessionMemSeq the sessionMemSeq to set
	 */
	public void setSessionMemSeq(String sessionMemSeq) {
		this.sessionMemSeq = sessionMemSeq;
	}
	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @return the sessionMemName
	 */
	public String getSessionMemName() {
		return sessionMemName;
	}
	/**
	 * @param sessionMemName the sessionMemName to set
	 */
	public void setSessionMemName(String sessionMemName) {
		this.sessionMemName = sessionMemName;
	}
	/**
	 * @return the sessionMemEngName
	 */
	public String getSessionMemEngName() {
		return sessionMemEngName;
	}
	/**
	 * @param sessionMemEngName the sessionMemEngName to set
	 */
	public void setSessionMemEngName(String sessionMemEngName) {
		this.sessionMemEngName = sessionMemEngName;
	}
	/**
	 * @return the sessionMemType
	 */
	public String getSessionMemType() {
		return sessionMemType;
	}
	/**
	 * @param sessionMemType the sessionMemType to set
	 */
	public void setSessionMemType(String sessionMemType) {
		this.sessionMemType = sessionMemType;
	}
	/**
	 * @return the sessionAuthGroupId
	 */
	public String getSessionAuthGroupId() {
		return sessionAuthGroupId;
	}
	/**
	 * @param sessionAuthGroupId the sessionAuthGroupId to set
	 */
	public void setSessionAuthGroupId(String sessionAuthGroupId) {
		this.sessionAuthGroupId = sessionAuthGroupId;
	}
	/**
	 * @return the sessionEmail
	 */
	public String getSessionEmail() {
		return sessionEmail;
	}
	/**
	 * @param sessionEmail the sessionEmail to set
	 */
	public void setSessionEmail(String sessionEmail) {
		this.sessionEmail = sessionEmail;
	}
	/**
	 * @return the sessionLecId
	 */
	public String getSessionLecId() {
		return sessionLecId;
	}
	/**
	 * @param sessionLecId the sessionLecId to set
	 */
	public void setSessionLecId(String sessionLecId) {
		this.sessionLecId = sessionLecId;
	}
	/**
	 * @return the sessionPeriodId
	 */
	public String getSessionPeriodId() {
		return sessionPeriodId;
	}
	/**
	 * @param sessionPeriodId the sessionPeriodId to set
	 */
	public void setSessionPeriodId(String sessionPeriodId) {
		this.sessionPeriodId = sessionPeriodId;
	}
	/**
	 * @return the sessionClassId
	 */
	public String getSessionClassId() {
		return sessionClassId;
	}
	/**
	 * @param sessionClassId the sessionClassId to set
	 */
	public void setSessionClassId(String sessionClassId) {
		this.sessionClassId = sessionClassId;
	}
	/**
	 * @return the sessionPageDiv
	 */
	public String getSessionPageDiv() {
		return sessionPageDiv;
	}
	/**
	 * @param sessionPageDiv the sessionPageDiv to set
	 */
	public void setSessionPageDiv(String sessionPageDiv) {
		this.sessionPageDiv = sessionPageDiv;
	}
	public String getSessionLectureDiv() {
		return sessionLectureDiv;
	}
	public void setSessionLectureDiv(String sessionLectureDiv) {
		this.sessionLectureDiv = sessionLectureDiv;
	}
	/**
	 * @return the sessionLessonId
	 */
	public String getSessionLessonId() {
		return sessionLessonId;
	}
	/**
	 * @param sessionLessonId the sessionLessonId to set
	 */
	public void setSessionLessonId(String sessionLessonId) {
		this.sessionLessonId = sessionLessonId;
	}
	/**
	 * @return the sessionLecStep
	 */
	public String getSessionLecStep() {
		return sessionLecStep;
	}
	/**
	 * @param sessionLecStep the sessionLecStep to set
	 */
	public void setSessionLecStep(String sessionLecStep) {
		this.sessionLecStep = sessionLecStep;
	}
	/**
	 * @return the sessionMenuArea
	 */
	public String getSessionMenuArea() {
		return sessionMenuArea;
	}
	/**
	 * @param sessionMenuArea the sessionMenuArea to set
	 */
	public void setSessionMenuArea(String sessionMenuArea) {
		this.sessionMenuArea = sessionMenuArea;
	}
	/**
	 * @return the sessionSessionId
	 */
	public String getSessionSessionId() {
		return sessionSessionId;
	}
	/**
	 * @param sessionSessionId the sessionSessionId to set
	 */
	public void setSessionSessionId(String sessionSessionId) {
		this.sessionSessionId = sessionSessionId;
	}
	/**
	 * @return the sessionOverlaploginYn
	 */
	public String getSessionOverlaploginYn() {
		return sessionOverlaploginYn;
	}
	/**
	 * @param sessionOverlaploginYn the sessionOverlaploginYn to set
	 */
	public void setSessionOverlaploginYn(String sessionOverlaploginYn) {
		this.sessionOverlaploginYn = sessionOverlaploginYn;
	}
	/**
	 * @return the sessionLecTarget
	 */
	public String getSessionLecTarget() {
		return sessionLecTarget;
	}
	/**
	 * @param sessionLecTarget the sessionLecTarget to set
	 */
	public void setSessionLecTarget(String sessionLecTarget) {
		this.sessionLecTarget = sessionLecTarget;
	}
	/**
	 * @return the sessionLecTargetYear
	 */
	public String getSessionLecTargetYear() {
		return sessionLecTargetYear;
	}
	/**
	 * @param sessionLecTargetYear the sessionLecTargetYear to set
	 */
	public void setSessionLecTargetYear(String sessionLecTargetYear) {
		this.sessionLecTargetYear = sessionLecTargetYear;
	}
	/**
	 * @return the sessionFacilityNo
	 */
	public String getSessionFacilityNo() {
		return sessionFacilityNo;
	}
	/**
	 * @param sessionFacilityNo the sessionFacilityNo to set
	 */
	public void setSessionFacilityNo(String sessionFacilityNo) {
		this.sessionFacilityNo = sessionFacilityNo;
	}
	/**
	 * @return the sessionFacilityName
	 */
	public String getSessionFacilityName() {
		return sessionFacilityName;
	}
	/**
	 * @param sessionFacilityName the sessionFacilityName to set
	 */
	public void setSessionFacilityName(String sessionFacilityName) {
		this.sessionFacilityName = sessionFacilityName;
	}
	public String getConfigMenuLms() {
		return configMenuLms;
	}
	public void setConfigMenuLms(String configMenuLms) {
		this.configMenuLms = configMenuLms;
	}
	public String getConfigMenuLcms() {
		return configMenuLcms;
	}
	public void setConfigMenuLcms(String configMenuLcms) {
		this.configMenuLcms = configMenuLcms;
	}
	public String getConfigMenuStd() {
		return configMenuStd;
	}
	public void setConfigMenuStd(String configMenuStd) {
		this.configMenuStd = configMenuStd;
	}
	public String getConfigMenuAst() {
		return configMenuAst;
	}
	public void setConfigMenuAst(String configMenuAst) {
		this.configMenuAst = configMenuAst;
	}
	public String getConfigMenuAdm() {
		return configMenuAdm;
	}
	public void setConfigMenuAdm(String configMenuAdm) {
		this.configMenuAdm = configMenuAdm;
	}
	public String getConfigMenuEng() {
		return configMenuEng;
	}
	public void setConfigMenuEng(String configMenuEng) {
		this.configMenuEng = configMenuEng;
	}
	public String getConfigMenuLecture() {
		return configMenuLecture;
	}
	public void setConfigMenuLecture(String configMenuLecture) {
		this.configMenuLecture = configMenuLecture;
	}
	public String getConfigMenuEngLecture() {
		return configMenuEngLecture;
	}
	public void setConfigMenuEngLecture(String configMenuEngLecture) {
		this.configMenuEngLecture = configMenuEngLecture;
	}
	public String getConfigLecDiv() {
		return configLecDiv;
	}
	public void setConfigLecDiv(String configLecDiv) {
		this.configLecDiv = configLecDiv;
	}
	public String getConfigLecIng() {
		return configLecIng;
	}
	public void setConfigLecIng(String configLecIng) {
		this.configLecIng = configLecIng;
	}
	public String getConfigLecPass() {
		return configLecPass;
	}
	public void setConfigLecPass(String configLecPass) {
		this.configLecPass = configLecPass;
	}
	public String getConfigLecExp() {
		return configLecExp;
	}
	public void setConfigLecExp(String configLecExp) {
		this.configLecExp = configLecExp;
	}
	public String getConfigLecNotComp() {
		return configLecNotComp;
	}
	public void setConfigLecNotComp(String configLecNotComp) {
		this.configLecNotComp = configLecNotComp;
	}
	public String getConfigDIV_ONLINE() {
		return configDIV_ONLINE;
	}
	public void setConfigDIV_ONLINE(String configDIV_ONLINE) {
		this.configDIV_ONLINE = configDIV_ONLINE;
	}
	public String getConfigDivOffline() {
		return configDivOffline;
	}
	public void setConfigDivOffline(String configDivOffline) {
		this.configDivOffline = configDivOffline;
	}
	public String getConfigDivBlended() {
		return configDivBlended;
	}
	public void setConfigDivBlended(String configDivBlended) {
		this.configDivBlended = configDivBlended;
	}
	public String getConfigCateLcms() {
		return configCateLcms;
	}
	public void setConfigCateLcms(String configCateLcms) {
		this.configCateLcms = configCateLcms;
	}
	public String getConfigCateCommu() {
		return configCateCommu;
	}
	public void setConfigCateCommu(String configCateCommu) {
		this.configCateCommu = configCateCommu;
	}
	public String getInsertDt() {
		return insertDt;
	}
	public void setInsertDt(String insertDt) {
		this.insertDt = insertDt;
	}
	public String getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(String updateDt) {
		this.updateDt = updateDt;
	}
	public String getSessionYyyy() {
		return sessionYyyy;
	}
	public void setSessionYyyy(String sessionYyyy) {
		this.sessionYyyy = sessionYyyy;
	}
	public String getSessionTerm() {
		return sessionTerm;
	}
	public void setSessionTerm(String sessionTerm) {
		this.sessionTerm = sessionTerm;
	}
	public String getSessionSubjectCode() {
		return sessionSubjectCode;
	}
	public void setSessionSubjectCode(String sessionSubjectCode) {
		this.sessionSubjectCode = sessionSubjectCode;
	}
	public String getSessionSubClass() {
		return sessionSubClass;
	}
	public void setSessionSubClass(String sessionSubClass) {
		this.sessionSubClass = sessionSubClass;
	}
	public String getSessionSsoLoginYn() {
		return sessionSsoLoginYn;
	}
	public void setSessionSsoLoginYn(String sessionSsoLoginYn) {
		this.sessionSsoLoginYn = sessionSsoLoginYn;
	}
	public String getSessionIp() {
		return sessionIp;
	}
	public void setSessionIp(String sessionIp) {
		this.sessionIp = sessionIp;
	}
	public String getSessionCompanyId() {
		return sessionCompanyId;
	}
	public void setSessionCompanyId(String sessionCompanyId) {
		this.sessionCompanyId = sessionCompanyId;
	}
}
