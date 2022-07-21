package egovframework.com.cmm;

import java.io.Serializable;
import java.sql.Date;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 * @
 * @  수정일         수정자                   수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.03.03    박지욱          최초 생성
* 이진근     sitglobal 서비스에 맞게 수정.
 *
 *  @author 공통서비스 개발팀 박지욱
 *  @since 2009.03.03
 *  @version 1.0
 *  @see
 *
 */
public class LoginVO extends BaseVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4020564459921584722L;

	/** 아이디 */
	private String id; // memId 와 동기화.
	/** 이름 */
	private String name; // memName 과 동기화
	// /** 주민등록번호 */
	// private String ihidNum;
	// /** 비밀번호 */
	// private String password;
	// /** 비밀번호 힌트 */
	// private String passwordHint;
	// /** 비밀번호 정답 */
	// private String passwordCnsr;
	// /** 사용자구분 */
	// private String userSe;
	// /** 조직(부서)ID */
	// private String orgnztId;
	// /** 조직(부서)명 */
	// private String orgnztNm;
	/** 고유아이디 */
	private String uniqId; // memSeq 와 동기화
	// /** 로그인 후 이동할 페이지 */
	// private String url;
	// /** 사용자 IP정보 */
	// private String ip;
	// /** GPKI인증 DN */
	// private String dn;


	private String memSeq;                    //회원 고유번호
	private String memId;					  //회원 ID
	private String memType;					  //공통코드의 회원유형 코드
	private String memName;                   //회원성명
	private String memNameEng;                   //회원성명

	private String memPassword;               //비밀번호(DB에 저장된 해시암호화된 비밀번호)
	private String memPasswordEncript;			//비밀번호( 사용자가 화면에서 입력한 암호화 전 비밀번호 )
	private String memPasswordEncripted;		//비밀번호( 사용자가 화면에서 입력한 암호화 된 비밀번호 )

	private String authGroupId;               //권한그룹 ID
	private String authGroupName;             //권한그룹 NAME
	private String loginCnt;                  //접속횟수
	private String deleteYn;				  // 삭제 여부
	private String scsnYn;                    //탈퇴여부

	private String lastLoginYmd; // 최종 로그인 일자
	private Date lastPwUpdtYmd; // 최종 비밀번호 변경 일자
	private Integer pwErrNumberTimes; // 비밀번호 오류 횟수
	private Date useExpireYmd; // 사용 만료 일자



	private String email;                     //e-mail 주소
	private String lecId;					// 강의 ID
	private String periodId;				// 기수 ID
	private String classId;					// 반 ID
	private String pageDiv;					// 페이지 모드 (일반, 강사 , 관리자 )
	private String lessonId;				// 레슨 ID
	private String lecStep;					// 차시
	private String menuArea;				// 메뉴영역( LMS , LCMS 구분)
	private String overlaploginYn; 			// 중복로그인 사용 여부
	private String lecTarget;				// 교육 대상
	private String lecTargetYear;			// 교육대상연차
	private String facilityNo;				// 소속코드
	private String facilityName;			// 소속 명
	private String receivePaperYn;    // 쪽지 수신 여부
	private String oldAuthGroupId;    // 이전권한그룹아이디
	private String sessionId;         // 세션아이디(중복로그인 방지용)
	private String orgCombineId;      // 이전통합아이디
	private String employeeNo;        // 가통시스템 직원번호
	private String authorityNo;       // 권한번호
	private String companyId; 		  // 회사코드
	private String compNm; 		  	  // 회사명

	private String deptNm;			  //학과명
	private String deptNo;			//학과번호

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getAuthGroupName() {
		return authGroupName;
	}

	public void setAuthGroupName(String authGroupName) {
		this.authGroupName = authGroupName;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return getMemId();
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		setMemId(id);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return getMemName();
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		setMemName(name);
	}
	/**
	 * @return the uniqId
	 */
	public String getUniqId() {
		return getMemSeq();
	}
	/**
	 * @param uniqId the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		setMemSeq(uniqId);
	}
	/**
	 * @return the memSeq
	 */
	public String getMemSeq() {
		return memSeq;
	}
	/**
	 * @param memSeq the memSeq to set
	 */
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	/**
	 * @return the memId
	 */
	public String getMemId() {
		return memId;
	}
	/**
	 * @param memId the memId to set
	 */
	public void setMemId(String memId) {
		this.memId = memId;
	}
	/**
	 * @return the memType
	 */
	public String getMemType() {
		return memType;
	}
	/**
	 * @param memType the memType to set
	 */
	public void setMemType(String memType) {
		this.memType = memType;
	}
	/**
	 * @return the memName
	 */
	public String getMemName() {
		return memName;
	}
	/**
	 * @param memName the memName to set
	 */
	public void setMemName(String memName) {
		this.memName = memName;
	}

	/**
	 * @return the memNameEng
	 */
	public String getMemNameEng() {
		return memNameEng;
	}
	/**
	 * @param memNameEng the memNameEng to set
	 */
	public void setMemNameEng(String memNameEng) {
		this.memNameEng = memNameEng;
	}
	/**
	 * @return the memPassword
	 */
	public String getMemPassword() {
		return memPassword;
	}
	/**
	 * @param memPassword the memPassword to set
	 */
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	/**
	 * @return the memPasswordEncript
	 */
	public String getMemPasswordEncript() {
		return memPasswordEncript;
	}
	/**
	 * @param memPasswordEncript the memPasswordEncript to set
	 */
	public void setMemPasswordEncript(String memPasswordEncript) {
		this.memPasswordEncript = memPasswordEncript;
	}
	/**
	 * @return the memPasswordEncripted
	 */
	public String getMemPasswordEncripted() {
		return memPasswordEncripted;
	}
	/**
	 * @param memPasswordEncripted the memPasswordEncripted to set
	 */
	public void setMemPasswordEncripted(String memPasswordEncripted) {
		this.memPasswordEncripted = memPasswordEncripted;
	}
	/**
	 * @return the authGroupId
	 */
	public String getAuthGroupId() {
		return authGroupId;
	}
	/**
	 * @param authGroupId the authGroupId to set
	 */
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	/**
	 * @return the loginCnt
	 */
	public String getLoginCnt() {
		return loginCnt;
	}
	/**
	 * @param loginCnt the loginCnt to set
	 */
	public void setLoginCnt(String loginCnt) {
		this.loginCnt = loginCnt;
	}
	/**
	 * @return the deleteYn
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	/**
	 * @param deleteYn the deleteYn to set
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * @return the scsnYn
	 */
	public String getScsnYn() {
		return scsnYn;
	}
	/**
	 * @param scsnYn the scsnYn to set
	 */
	public void setScsnYn(String scsnYn) {
		this.scsnYn = scsnYn;
	}
	/**
	 * @return the lastLoginYmd
	 */
	public String getLastLoginYmd() {
		return lastLoginYmd;
	}
	/**
	 * @param lastLoginYmd the lastLoginYmd to set
	 */
	public void setLastLoginYmd(String lastLoginYmd) {
		this.lastLoginYmd = lastLoginYmd;
	}
	/**
	 * @return the lastPwUpdtYmd
	 */
	public Date getLastPwUpdtYmd() {
		return lastPwUpdtYmd;
	}
	/**
	 * @param lastPwUpdtYmd the lastPwUpdtYmd to set
	 */
	public void setLastPwUpdtYmd(Date lastPwUpdtYmd) {
		this.lastPwUpdtYmd = lastPwUpdtYmd;
	}
	/**
	 * @return the pwErrNumberTimes
	 */
	public Integer getPwErrNumberTimes() {
		return pwErrNumberTimes;
	}
	/**
	 * @param pwErrNumberTimes the pwErrNumberTimes to set
	 */
	public void setPwErrNumberTimes(Integer pwErrNumberTimes) {
		this.pwErrNumberTimes = pwErrNumberTimes;
	}
	/**
	 * @return the useExpireYmd
	 */
	public Date getUseExpireYmd() {
		return useExpireYmd;
	}
	/**
	 * @param useExpireYmd the useExpireYmd to set
	 */
	public void setUseExpireYmd(Date useExpireYmd) {
		this.useExpireYmd = useExpireYmd;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the lecId
	 */
	public String getLecId() {
		return lecId;
	}
	/**
	 * @param lecId the lecId to set
	 */
	public void setLecId(String lecId) {
		this.lecId = lecId;
	}
	/**
	 * @return the periodId
	 */
	public String getPeriodId() {
		return periodId;
	}
	/**
	 * @param periodId the periodId to set
	 */
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	/**
	 * @return the classId
	 */
	public String getClassId() {
		return classId;
	}
	/**
	 * @param classId the classId to set
	 */
	public void setClassId(String classId) {
		this.classId = classId;
	}
	/**
	 * @return the pageDiv
	 */
	public String getPageDiv() {
		return pageDiv;
	}
	/**
	 * @param pageDiv the pageDiv to set
	 */
	public void setPageDiv(String pageDiv) {
		this.pageDiv = pageDiv;
	}
	/**
	 * @return the lessonId
	 */
	public String getLessonId() {
		return lessonId;
	}
	/**
	 * @param lessonId the lessonId to set
	 */
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	/**
	 * @return the lecStep
	 */
	public String getLecStep() {
		return lecStep;
	}
	/**
	 * @param lecStep the lecStep to set
	 */
	public void setLecStep(String lecStep) {
		this.lecStep = lecStep;
	}
	/**
	 * @return the menuArea
	 */
	public String getMenuArea() {
		return menuArea;
	}
	/**
	 * @param menuArea the menuArea to set
	 */
	public void setMenuArea(String menuArea) {
		this.menuArea = menuArea;
	}
	/**
	 * @return the overlaploginYn
	 */
	public String getOverlaploginYn() {
		return overlaploginYn;
	}
	/**
	 * @param overlaploginYn the overlaploginYn to set
	 */
	public void setOverlaploginYn(String overlaploginYn) {
		this.overlaploginYn = overlaploginYn;
	}
	/**
	 * @return the lecTarget
	 */
	public String getLecTarget() {
		return lecTarget;
	}
	/**
	 * @param lecTarget the lecTarget to set
	 */
	public void setLecTarget(String lecTarget) {
		this.lecTarget = lecTarget;
	}
	/**
	 * @return the lecTargetYear
	 */
	public String getLecTargetYear() {
		return lecTargetYear;
	}
	/**
	 * @param lecTargetYear the lecTargetYear to set
	 */
	public void setLecTargetYear(String lecTargetYear) {
		this.lecTargetYear = lecTargetYear;
	}
	/**
	 * @return the facilityNo
	 */
	public String getFacilityNo() {
		return facilityNo;
	}
	/**
	 * @param facilityNo the facilityNo to set
	 */
	public void setFacilityNo(String facilityNo) {
		this.facilityNo = facilityNo;
	}
	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}
	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getReceivePaperYn() {
		return receivePaperYn;
	}

	public void setReceivePaperYn(String receivePaperYn) {
		this.receivePaperYn = receivePaperYn;
	}

	public String getOldAuthGroupId() {
		return oldAuthGroupId;
	}

	public void setOldAuthGroupId(String oldAuthGroupId) {
		this.oldAuthGroupId = oldAuthGroupId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOrgCombineId() {
		return orgCombineId;
	}

	public void setOrgCombineId(String orgCombineId) {
		this.orgCombineId = orgCombineId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getAuthorityNo() {
		return authorityNo;
	}

	public void setAuthorityNo(String authorityNo) {
		this.authorityNo = authorityNo;
	}
}
