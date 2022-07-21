/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.member.vo;

import java.io.Serializable;
import java.sql.Date;

import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2017. 01. 06.
 */
public class ExcelMemberStdVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3861411154717477452L;

    private String year;
    private String month;
    private String day;
	private String hpNo1;                     //핸드폰번호1
	private String authGroupId;               //권한그룹 ID

	private String memSeq;                    //회원 고유번호
	private String memId;					  //회원 ID
	private String memType;					  //공통코드의 회원유형 코드
	private String memName;                   //회원성명
	private String pwdQuest;                  //패스워드 확인 시 선택한 질문의 번호
	private String sex;                       //공통코드 성별
	private String memNameEng;                //영문명
	private String memBirth;                  //생년월일
	private String pwdQuestAns;               //패스워드 찾기 질문에 대한 답
	private String deleteName;
	private String deleteDt;
	private String subjectMappingCnt;
	private String subjectGrade;

	private String memPassword;               //비밀번호(DB에 저장된 해시암호화된 비밀번호)
	private String memPasswordEncript;			//비밀번호( 사용자가 화면에서 입력한 암호화 전 비밀번호 )
	private String memPasswordEncripted;		//비밀번호( 사용자가 화면에서 입력한 암호화 된 비밀번호 )

	private String curMemPassword;            //현재비밀번호
	private String newMemPassword;            //새비밀번호
	private String initPassword;              //초기화 비밀번호
	private String email;                     //e-mail 주소
	private String email1;                     //e-mail 주소
	private String email2;                     //e-mail 주소
	private String email3;                     //e-mail 주소
	private String memRegiNo;                 //주민번호
	private String memRegiNo1;                 //주민번호 앞자리(6자리)
	private String memRegiNo2;                 //주민번호 뒷자리(7자리)
	private String zipCode;                   //우편번호
	private String address;                   //주소
	private String addressDtl;                //상세주소
	private String telNo;					  //전화번호 (2013.0412)
	private String telNo1;                    //전화번호1
	private String telNo2;                    //전화번호2
	private String telNo3;                    //전화번호3
	private String hpNo;                      //핸드폰번호(2011.03.24)
	private String hpNo2;                     //핸드폰번호2
	private String hpNo3;                     //핸드폰번호3
	private String newsletterYn;              //뉴스레터 수신여부
	private String photoFilePath;             //사진파일 경로
	private String workingPlace;              //근무지
	private String scholarship;               //최종학력
	private String scholarshipName;          //최종학력
	private String secedeReason;              //탈퇴사유
	private String relationField;             //관련분야
	private String career;                    //주요경력
	private String title;                     //직위
	private String titleName;                //직위명
	private String titlePath;                 //직위
	private String mAuthGroupId;              //권한그룹 ID
	private String bndlRegYn;                 //일괄등록여부
	private String loginCnt;                  //접속횟수
	private String deleteYn;				  // 삭제 여부
	private String scsnYn;                    //탈퇴여부
	private String majr;                      //전공분야
	private String receiveMailYn;             //메일수신여부
	private String receiveSmsYn;             //SMS수신여부
	private String foreignYn;                 //외국인여부
	private String namecheckYn;              //실명인증여부
	private String nationCode;                //국가코드
	private String companyId;                 //회사고유번호
	/*g-pin 관련 컬럼*/
	private String gPinYn;                    //g-pin가입여부
	private String gPinKey1;                    //g-pin개인식별번호
	private String gPinKey2;                    //g-pin중복가입확인정보

	private String lastLoginYmd; // 최종 로그인 일자
	private Date lastPwUpdtYmd; // 최종 비밀번호 변경 일자
	private Integer pwErrNumberTimes; // 비밀번호 오류 횟수
	private Date useExpireYmd; // 사용 만료 일자

	/*교원정보*/
	private String memDtlSeq;			  //회원 상세고유번호
	private String schoolSeq;				  //학교번호
	private String schoolName;				  //학교이름
	private String schoolDiv;				  //학교구분
	private String schoolClass;				  //학교분류(I:유치원,E:초등학교,M:중학교,H:고등학교,C:전문대학,U:대학교,L:대학원)';
	private String establishmentDiv;		  //설립구분
	private String education;				  //소속교육청
	private String dtlTelNo1;			  //학교전화번호1
	private String dtlTelNo2;			  //학교전화번호2
	private String dtlTelNo3;			  //학교전화번호3
	private String dtlZipCode;			  //학교우편번호
	private String dtlAddress;			  //학교주소
	private String dtlAddressDtl;		  //학교상세주소
	private String chargeSubject;			  //담당교과
	private String attachDepartment;		  //소속부서
	private String educationCareer;			  //교육경력
	private String finalQualify;			  //최종자격
	private String joinCourse;				  //가입경로
	private String joinObject;				  //가입목적
	private String instituteName;			  //소속기관
	private String certificationDiv;		  //수료증구분
	private String position;				  //직위

	private String chargeSubjectEtc;		  //기타 담당교과
	private String finalQualifyEtc;			  //기타 최종자격
	private String joinCourseEtc;			  //기타 가입경로
	private String joinObjectEtc;			  //기타 가입목적
	private String positionEtc;				  //기타 직위
	private String schoolSub;				  //학교교급
	/*교원정보*/
	private String authGroupName;             //권한명
	private String companyName;               //회사명
	private String faxNo1;                    //회사 팩스번호1
	private String faxNo2;                    //회사 팩스번호2
	private String faxNo3;                    //회사 팩스번호3

	private MultipartFile uploadFile;		  //업로드 회원 파일
	private long uploadSize = 1024 * 200 ;    //파일 업로드 사이즈

	private int memIdCount;                         //아이디 카운트(중복확인시)
	private int eduCnt;                             //회사 정보를 등록되어진 교육담당자 여부
	/**/
	private String insMemEducation;				//교원회원 소속교육청
	private String insMemDtlTelNo1;				//교원회원 소속전화번호1
	private String insMemDtlTelNo2;				//교원회원 소속전화번호2
	private String insMemDtlTelNo3;				//교원회원 소속전화번호3
	private String insMemDtlZipCode;			//교원회원 우편번호
	private String insMemDtlAddress;			//교원회원 주소
	private String insMemDtlAddressDtl;			//교원회원 상세주소
	private String insMemAttachDepartment;		//교원회원 소속부서
	private String insMemEducationCareer;		//교원회원 교육경력
	private String insMemJoinCourse;			//교원회원 가입경로
	private String insMemJoinObject;			//교원회원 가입목적
	private String insMemChargeSubjectEtc;		//교원회원 기타 담당교과
	private String insMemJoinCourseEtc;			//교원회원 기타 가입경로
	private String insMemJoinObjectEtc;			//교원회원 기타 가입목적
	private String insSchoolSub;				//교원회원 학교교급
	private String insSchoolName;				//교원회원 학교명

	private String proMemEducation;				//교육전문직 소속교육청
	private String proMemDtlTelNo1;				//교육전문직 소속전화번호1
	private String proMemDtlTelNo2;				//교육전문직 소속전화번호2
	private String proMemDtlTelNo3;				//교육전문직 소속전화번호3
	private String proMemDtlZipCode;			//교육전문직 우편번호
	private String proMemDtlAddress;			//교육전문직 주소
	private String proMemDtlAddressDtl;			//교육전문직 상세주소
	private String proMemAttachDepartment;		//교육전문직 소속부서
	private String proMemEducationCareer;		//교육전문직 교육경력
	private String proMemJoinCourse;			//교육전문직 가입경로
	private String proMemJoinObject;			//교육전문직 가입목적
	private String proMemChargeSubjectEtc;		//교육전문직 기타 담당교과
	private String proMemJoinCourseEtc;			//교육전문직 기타 가입경로
	private String proMemJoinObjectEtc;			//교육전문직 기타 가입목적
	private String proSchoolSub;				//교육전문직 학교교급

	private String stdMemAttachDepartment;		//일반회원 소속부서
	private String stdMemJoinCourse;			//일반회원 가입경로
	private String stdMemJoinCourseEtc;			//일반회원 가입목적
	private String stdMemJoinObject;			//일반회원 기타 가입경로
	private String stdMemJoinObjectEtc;			//일반회원 기타 가입목적
	private String jobGroup;					//일반회원 직업군
	private String jobGroupEtc;					//일반회원 직업군기타

	private String stdJobGroup;					//일반회원 직업군
	private String indJobGroup;					//산업종사자 직업군
	private String schJobGroup;					//학생 구분
	private String stdJobGroupEtc;				//일반회원 직업군기타
	private String indJobGroupEtc;				//산업종사자 직업군기타
	private String schJobGroupEtc;				//학생 구분기타
	private String stdCompanyName;              //일반회원 소속/회사명
	private String stdCompanyId;              //일반회원 소속/회사명

	private String indMemAttachDepartment;		//산업종사자 소속부서
	private String indMemJoinCourse;			//산업종사자 가입경로
	private String indMemJoinCourseEtc;			//산업종사자 가입목적
	private String indMemJoinObject;			//산업종사자 기타 가입경로
	private String indMemJoinObjectEtc;			//산업종사자 기타 가입목적
	private String indCompanyName;              //산업종사자 소속/회사명
	private String indCompanyId;              //산업종사자 소속/회사명

	private String schMemJoinCourse;			//학생 가입경로
	private String schMemJoinCourseEtc;			//학생 가입목적
	private String schMemJoinObject;			//학생 기타 가입경로
	private String schMemJoinObjectEtc;			//학생 기타 가입목적
	private String schMajr;                     //학생 전공분야
	private String schSchoolName;				//학생 학교교급

	private String indPosition;				 	//직위
	private String indPositionEtc;				//기타 직위
	private String proPosition;				 	//직위
	private String proPositionEtc;				//기타 직위

	/**
	 * 부모님 동의 테이블 정보 (2011.06.14 : 김상범)
	 */
	private String memParentSeq		="";
	private String parentName		="";
	private String parentBirth		="";
	private String parentRegiNo		="";
	private String parentGPinKey1	="";
	private String parentGpinKey2	="";
	/*
	 * 부모님 동의 페이지에서의  전화번호는 별도의 아래의 이름으로 설정
	 * DB 데이터 저장시 tel_no1, tel_no2, tel_no3 로 쿼리를 변경
	 */
	private String parentTelNo1		="";
	private String parentTelNo2		="";
	private String parentTelNo3		="";

	/* 담당자변경 관련 항목 */
	private String relatedResearchOrganizationName; //관련연구 기관명
	private String relatedResearchYear;             //관련연구 기간
	private String sameSiteWorkYear;                //동일현장 경력년도
	private String sameSiteWorkCd;                  //동일현장 경력코드
	private String participateStartDate;            //참여일자 시작일
	private String participateEndDate;              //참여일자 종료일


	/**
	 * 회원 검색
	 */
	private String searchMemId;
	private String searchMemType;
	private String searchMemName;
	private String searchSubjectTraningType;
//	private String searchPwdQuest;
//	private String searchSex;
//	private String searchMemNameEng;
//	private String searchMemBirth;
//	private String searchPwdQuestAns;
//	private String searchMemPassword;
//	private String searchEmail;
//	private String searchMemRegiNo;
//	private String searchZipCode;
//	private String searchAddress;
//	private String searchAddressDtl;
//	private String searchTelNo1;
//	private String searchTelNo2;
//	private String searchTelNo3;
//	private String searchHpNo1;
//	private String searchHpNo2;
//	private String searchHpNo3;
//	private String searchNewsletterYn;
//	private String searchPhotoFilePath;
//	private String searchWorkingPlace;
//	private String searchScholarship;
//	private String searchSecedeReason;
//	private String searchRelationField;
//	private String searchCareer;
//	private String searchTitle;
	private String searchAuthGroupId;
//	private String searchBndlRegYn;
//	private java.math.BigDecimal searchLoginCnt;
//	private String searchMajr;
//	private String searchReceiveMailYn;
//	private String searchReceiveSmsYn;
//	private String searchForeignYn;
//	private String searchNamecheckYn;
//	private String searchNationCode;
//	private String searchGPinYn;
//	private String searchGPinKey1;
//	private String searchGPinKey2;
//	private String searchSchoolSeq;
//	private String searchTutorClauseAgreeYn;
//	private String searchAgreeYn;
//	private String searchNonageYn;
//	private String searchDeleteYn;
//	private java.sql.Date searchInsertDate;
//	private String searchInsertUser;
//	private java.sql.Date searchUpdateDate;
//	private String searchUpdateUser;
//	private java.math.BigDecimal searchCertiNo;
//	private String searchCertiCharacter;
//	private String searchCertiYn;
//	private java.math.BigDecimal searchCertiAttemptCnt;
//	private String searchCompNm;
//	private String searchDeptNm;
//	private String searchDutyNm;
//	private String searchRemk;
//	private String searchWrongRegiNo;
//	private String searchOrgPwNull;
//	private String searchOrgMemId;
//	private java.sql.Date searchLastLoginYmd;
//	private java.sql.Date searchLastPwUpdtYmd;
//	private java.math.BigDecimal searchPwErrNumberTimes;
//	private java.sql.Date searchUseExpireYmd;

	private String searchCompanyId;			  //회사명 검색
	private String searchAuthGroup;			  //회원유형검색
	private String searchName;				  //회원 ID,NAME 검색값
	private String searchWord;				  //회원 검색어
	private String searchDivName;			  //학교명검색인지, 회사명검색인지 구분
	private String searchSchoolName;		  //학교명 검색
	private String searchPhone;			      //전화 번호검색
	private String searchScsnYn;			  //회원 탈퇴여부검색
	private String searchYyyy;
	private String searchTerm;
	private String searchSubjectCode;
	private String searchSubClass;

	private String[] memSeqs;				  //회원 고유번호
	private String memDiv;					  //회원구분(G:일반,T:교원,P:전문직,Y:청소년)
	private String searchMemDiv;			  //회원구분검색(G:일반,T:교원,P:전문직,Y:청소년)
	private int memDtlCount;				  //회원상세정보 카운트
	private String trPhone;					  //SMS 보내기 핸드폰 번호

   	// 비밀번호사용여부
	private String passwordUseYn;
	// 게시판비밀번호
	private String boardPassword;

	private String authGroupCount;
    //튜터 약관 동의 필드
    private String tutorClauseAgreeYn = "";
    //ASP_ID 추가 2011.09.02
    private String aspId;
    private String aspName;
    private String siteType;
    private String aspUrl;

    //검색조건 ASP_ID 추가 2011.10.7
    private String searchAspId;
    //비밀번호 발송 분류
    private String sendFlag;

    // 산기대 추가
    private String searchField = "";
    private String searchValue = "";

    // 인증관련 추가
    private long certiNo;
    private String certiCharacter = "";
    private String certiYn = "";
    private String insertResult = "";
    //주문배송지정보
    private String orderMemName = "";
    private String orderZipCode = "";
    private String orderAddress = "";
    private String orderAddressDtl = "";
    private String orderHpNo1 = "";
    private String orderHpNo2 = "";
    private String orderHpNo3 = "";
    private String orderComment = "";
    private String oldAuthGroupId = "";

	private boolean changePassword = false;
    // 소속항목 추가 2014.09.01
    private String compNm = "";

    // 회원구분 추가
    private String roleDiv = "";		// E:자격시험, L:교육, B:공통

    // 과정대상, 년차 추가
    private String lecTarget = "";
	private String lecTargetYear = "";
	// 소속코드, 소속명, 권한코드 추가
	private String facilityNo = "";
	private String facilityName = "";
	private String employeeNo = "";
	private String authorityNo = "";
	private String adminZoneCode = "";
	private String adminZoneName = "";
	private String insertDate = "";
	private String agreeYn = "";
	private String loginPWMaxUsedDays = "";

	//관리자, 학습자 팝업용 필드
	private String lectureStat = "";
	private String memSeqPop = "";

	// 사용자 기업별 훈련과정 및 개설강좌 메핑용 필드
	private String subjTutMappingId;
	private String subjCcmMappingId;
	private String subjInsMappingId;
	private String lessonId;
	private String traningProcessId; // 훈련과정아이디
	private String oldTraningProcessId; // 이전훈련과정아이디
	private String traningProcessMappingId; // 훈련과정매핑아이디
	private String[] traningProcessMappingIdArr; // 훈련과정매핑아이디
	private String yyyy; // 학년도
	private String term; // 학기
	private String subjectCode; // 교과목코드
	private String subjectName; // 교과목명
	private String subClass; // 분반
	private String oldYyyy; // 이전학년도
	private String oldTerm; // 이전학기
	private String oldSubjectCode; // 이전교과목코드
	private String oldSubClass; // 이전분반
	private String hrdTraningNo; // HRD-NET 훈련과정번호
	private String hrdTraningName; // HRD-NET 훈련과정명
	private String insertFlag; // 엑셀일괄등록 flag(01:개설교과메핑전 데이타, 02:개설교과메핑이후 데이타)
	private String subjectTraningType; //교과구분
	private String departmentName; //학과명
	private String updtApplicationStatus; //담당자변경 진행상태
	private String updtApplicationName; //담당자변경 진행상태명
	private String topCompanyName; //기업명
	private String topAddress; /* 소재지 */
	private String topHrdTraningName; /* 훈련과정명 */
	private String topHrdTraningNo; /* 훈련과정번호 */
	private String topStatusName; /* 진행상태명 */
	private String status; /* 진행상태 */
	private String retunReason; /* 반려사유 */
	private String memTypeName; /* 회원유형명 */
	private String cretGubun; //담장자변경신청건에 대해 신규, 변견인지 구분Flag (01:변경신청, 02:신규)

	public String getAdminZoneCode() {
		return adminZoneCode;
	}
	public void setAdminZoneCode(String adminZoneCode) {
		this.adminZoneCode = adminZoneCode;
	}
	public String getAdminZoneName() {
		return adminZoneName;
	}
	public void setAdminZoneName(String adminZoneName) {
		this.adminZoneName = adminZoneName;
	}
	public String getAuthorityNo() {
		return authorityNo;
	}
	public void setAuthorityNo(String authorityNo) {
		this.authorityNo = authorityNo;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getFacilityNo() {
		return facilityNo;
	}
	public void setFacilityNo(String facilityNo) {
		this.facilityNo = facilityNo;
	}
	public String getFacilityName() {
		return facilityName;
	}
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

    public String getOldAuthGroupId() {
  		return oldAuthGroupId;
  	}
  	public void setOldAuthGroupId(String oldAuthGroupId) {
  		this.oldAuthGroupId = oldAuthGroupId;
  	}
    /**
	 * @return the sendFlag
	 */
	public String getSendFlag() {
		return TextStringUtil.fixNull(sendFlag);
	}
	/**
	 * @param sendFlag the sendFlag to set
	 */
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}
	//
	/**
	 * @return the siteType
	 */
	public String getSiteType() {
		return TextStringUtil.fixNull(siteType);
	}
	/**
	 * @param siteType the siteType to set
	 */
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	/**
	 * @return the aspId
	 */
	public String getAspId() {
		return TextStringUtil.fixNull(aspId);
	}
	/**
	 * @param aspId the aspId to set
	 */
	public void setAspId(String aspId) {
		this.aspId = aspId;
	}
	/**
	 * @return the tutorClauseAgreeYn
	 */
	public String getTutorClauseAgreeYn() {
		return TextStringUtil.fixNull(tutorClauseAgreeYn);
	}
	/**
	 * @param tutorClauseAgreeYn the tutorClauseAgreeYn to set
	 */
	public void setTutorClauseAgreeYn(String tutorClauseAgreeYn) {
		this.tutorClauseAgreeYn = tutorClauseAgreeYn;
	}





	/**
	 * @return the searchAuthGroupId
	 */
	public String getSearchAuthGroupId() {
		return searchAuthGroupId;
	}
	/**
	 * @param searchAuthGroupId the searchAuthGroupId to set
	 */
	public void setSearchAuthGroupId(String searchAuthGroupId) {
		this.searchAuthGroupId = searchAuthGroupId;
	}
	/**
	 * @return the searchMemId
	 */
	public String getSearchMemId() {
		return searchMemId;
	}
	/**
	 * @param searchMemId the searchMemId to set
	 */
	public void setSearchMemId(String searchMemId) {
		this.searchMemId = searchMemId;
	}
	/**
	 * @return the searchMemType
	 */
	public String getSearchMemType() {
		return searchMemType;
	}
	/**
	 * @param searchMemType the searchMemType to set
	 */
	public void setSearchMemType(String searchMemType) {
		this.searchMemType = searchMemType;
	}
	/**
	 * @return the searchMemName
	 */
	public String getSearchMemName() {
		return searchMemName;
	}
	/**
	 * @param searchMemName the searchMemName to set
	 */
	public void setSearchMemName(String searchMemName) {
		this.searchMemName = searchMemName;
	}
	/**
	 * @return the searchCompanyId
	 */
	public String getSearchCompanyId() {
		return TextStringUtil.fixNull(searchCompanyId);
	}
	/**
	 * @param searchCompanyId the searchCompanyId to set
	 */
	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}
	/**
	 * @return the searchAuthGroup
	 */
	public String getSearchAuthGroup() {
		return TextStringUtil.fixNull(searchAuthGroup);
	}
	/**
	 * @param searchAuthGroup the searchAuthGroup to set
	 */
	public void setSearchAuthGroup(String searchAuthGroup) {
		this.searchAuthGroup = searchAuthGroup;
	}
	/**
	 * @return the searchName
	 */
	public String getSearchName() {
		return TextStringUtil.fixNull(searchName);
	}
	/**
	 * @param searchName the searchName to set
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	/**
	 * @return the searchWord
	 */
	public String getSearchWord() {
		return TextStringUtil.fixNull(searchWord);
	}
	/**
	 * @param searchWord the searchWord to set
	 */
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	/**
	 * @return the uploadSize
	 */
	public long getUploadSize() {
		return uploadSize;
	}
	/**
	 * @param uploadSize the uploadSize to set
	 */
	public void setUploadSize(long uploadSize) {
		this.uploadSize = uploadSize;
	}
	/**
	 * @return the uploadFile
	 */
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return TextStringUtil.fixNull(companyName);
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the authGroupName
	 */
	public String getAuthGroupName() {
		return TextStringUtil.fixNull(authGroupName);
	}
	/**
	 * @param authGroupName the authGroupName to set
	 */
	public void setAuthGroupName(String authGroupName) {
		this.authGroupName = authGroupName;
	}
	/**
	 * @return the memSeq
	 */
	public String getMemSeq() {
		return TextStringUtil.fixNull(memSeq);
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
		return TextStringUtil.fixNull(memId);
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
		return TextStringUtil.fixNull(memType);
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
		return TextStringUtil.fixNull(memName);
	}
	/**
	 * @param memName the memName to set
	 */
	public void setMemName(String memName) {
		this.memName = memName;
	}
	/**
	 * @return the pwdQuest
	 */
	public String getPwdQuest() {
		return TextStringUtil.fixNull(pwdQuest);
	}
	/**
	 * @param pwdQuest the pwdQuest to set
	 */
	public void setPwdQuest(String pwdQuest) {
		this.pwdQuest = pwdQuest;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return TextStringUtil.fixNull(sex);
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return the memNameEng
	 */
	public String getMemNameEng() {
		return TextStringUtil.fixNull(memNameEng);
	}
	/**
	 * @param memNameEng the memNameEng to set
	 */
	public void setMemNameEng(String memNameEng) {
		this.memNameEng = memNameEng;
	}
	/**
	 * @return the memBirth
	 */
	public String getMemBirth() {
		if("".equals(TextStringUtil.fixNull(memBirth)))
			memBirth = TextStringUtil.fixNull(year) + TextStringUtil.fixNull(month) + TextStringUtil.fixNull(day);
		return TextStringUtil.fixNull(memBirth);
	}
	/**
	 * @param memBirth the memBirth to set
	 */
	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}
	/**
	 * @return the pwdQuestAns
	 */
	public String getPwdQuestAns() {
		return TextStringUtil.fixNull(pwdQuestAns);
	}
	/**
	 * @param pwdQuestAns the pwdQuestAns to set
	 */
	public void setPwdQuestAns(String pwdQuestAns) {
		this.pwdQuestAns = pwdQuestAns;
	}
	/**
	 * @return the memPassword
	 */
	public String getMemPassword() {
		return TextStringUtil.fixNull(memPassword);
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
	 * @return the email
	 */
	public String getEmail() {
		return TextStringUtil.fixNull(email);
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	/**
	 * @return the memRegiNo
	 */
	public String getMemRegiNo() {
		return TextStringUtil.fixNull(memRegiNo);
	}
	/**
	 * @param memRegiNo the memRegiNo to set
	 */
	public void setMemRegiNo(String memRegiNo) {
		this.memRegiNo = memRegiNo;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return TextStringUtil.fixNull(zipCode);
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return TextStringUtil.fixNull(address);
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the addressDtl
	 */
	public String getAddressDtl() {
		return TextStringUtil.fixNull(addressDtl);
	}
	/**
	 * @param addressDtl the addressDtl to set
	 */
	public void setAddressDtl(String addressDtl) {
		this.addressDtl = addressDtl;
	}
	/**
	 * @return the telNo1
	 */
	public String getTelNo1() {
		return TextStringUtil.fixNull(telNo1);
	}
	/**
	 * @param telNo1 the telNo1 to set
	 */
	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}
	/**
	 * @return the telNo2
	 */
	public String getTelNo2() {
		return TextStringUtil.fixNull(telNo2);
	}
	/**
	 * @param telNo2 the telNo2 to set
	 */
	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}
	/**
	 * @return the telNo3
	 */
	public String getTelNo3() {
		return TextStringUtil.fixNull(telNo3);
	}
	/**
	 * @param telNo3 the telNo3 to set
	 */
	public void setTelNo3(String telNo3) {
		this.telNo3 = telNo3;
	}
	/**
	 * @return the hpNo1
	 */
	public String getHpNo1() {
		return TextStringUtil.fixNull(hpNo1);
	}
	/**
	 * @param hpNo1 the hpNo1 to set
	 */
	public void setHpNo1(String hpNo1) {
		this.hpNo1 = hpNo1;
	}
	/**
	 * @return the hpNo2
	 */
	public String getHpNo2() {
		return TextStringUtil.fixNull(hpNo2);
	}
	/**
	 * @param hpNo2 the hpNo2 to set
	 */
	public void setHpNo2(String hpNo2) {
		this.hpNo2 = hpNo2;
	}
	/**
	 * @return the hpNo3
	 */
	public String getHpNo3() {
		return TextStringUtil.fixNull(hpNo3);
	}
	/**
	 * @param hpNo3 the hpNo3 to set
	 */
	public void setHpNo3(String hpNo3) {
		this.hpNo3 = hpNo3;
	}
	/**
	 * @return the newsletterYn
	 */
	public String getNewsletterYn() {
		return TextStringUtil.fixNull(newsletterYn);
	}
	/**
	 * @param newsletterYn the newsletterYn to set
	 */
	public void setNewsletterYn(String newsletterYn) {
		this.newsletterYn = newsletterYn;
	}
	/**
	 * @return the photoFilePath
	 */
	public String getPhotoFilePath() {
		return TextStringUtil.fixNull(photoFilePath);
	}
	/**
	 * @param photoFilePath the photoFilePath to set
	 */
	public void setPhotoFilePath(String photoFilePath) {
		this.photoFilePath = photoFilePath;
	}
	/**
	 * @return the workingPlace
	 */
	public String getWorkingPlace() {
		return workingPlace;
	}
	/**
	 * @param workingPlace the workingPlace to set
	 */
	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}
	/**
	 * @return the scholarship
	 */
	public String getScholarship() {
		return TextStringUtil.fixNull(scholarship);
	}
	/**
	 * @param scholarship the scholarship to set
	 */
	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}
	/**
	 * @return the secedeReason
	 */
	public String getSecedeReason() {
		return TextStringUtil.fixNull(secedeReason);
	}
	/**
	 * @param secedeReason the secedeReason to set
	 */
	public void setSecedeReason(String secedeReason) {
		this.secedeReason = secedeReason;
	}
	/**
	 * @return the relationField
	 */
	public String getRelationField() {
		return TextStringUtil.fixNull(relationField);
	}
	/**
	 * @param relationField the relationField to set
	 */
	public void setRelationField(String relationField) {
		this.relationField = relationField;
	}
	/**
	 * @return the career
	 */
	public String getCareer() {
		return TextStringUtil.fixNull(career);
	}

    /**
     * 줄바꿈 처리
     * @return the content
     */
    public String getCareerAsHtmlWithScript() {
        final String LT_CHAR = "&lt;";
        final String LT_REPLACE_CHAR = "<";
        final String GT_CHAR = "&gt;";
        final String GT_REPLACE_CHAR = ">";

        String scriptCareer = TextStringUtil.getHtmlString(career);
        // '<', '>' 처리
        if( scriptCareer.indexOf( LT_CHAR ) >= 0 ) {
        	scriptCareer = scriptCareer.replaceAll( LT_CHAR, LT_REPLACE_CHAR );
        }
        if( scriptCareer.indexOf( GT_CHAR ) >= 0 ) {
        	scriptCareer = scriptCareer.replaceAll( GT_CHAR, GT_REPLACE_CHAR );
        }

        return scriptCareer;
    }
	/**
	 * @param career the career to set
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return TextStringUtil.fixNull(title);
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitlePath() {
		return titlePath;
	}
	public void setTitlePath(String titlePath) {
		this.titlePath = titlePath;
	}
	/**
	 * @return the authGroupId
	 */
	public String getAuthGroupId() {
		return TextStringUtil.fixNull(authGroupId);
	}
	/**
	 * @param authGroupId the authGroupId to set
	 */
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	/**
	 * @return the bndlRegYn
	 */
	public String getBndlRegYn() {
		return TextStringUtil.fixNull(bndlRegYn);
	}
	/**
	 * @param bndlRegYn the bndlRegYn to set
	 */
	public void setBndlRegYn(String bndlRegYn) {
		this.bndlRegYn = bndlRegYn;
	}
	/**
	 * @return the loginCnt
	 */
	public String getLoginCnt() {
		return TextStringUtil.fixNull(loginCnt);
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
		return TextStringUtil.fixNull(scsnYn);
	}
	/**
	 * 탈퇴 여부
	 * @param scsnYn the scsnYn to set
	 */
	public void setScsnYn(String scsnYn) {
		this.scsnYn = scsnYn;
	}
	/**
	 * @return the majr
	 */
	public String getMajr() {
		return TextStringUtil.fixNull(majr);
	}
	/**
	 * @param majr the majr to set
	 */
	public void setMajr(String majr) {
		this.majr = majr;
	}
	/**
	 * @return the receiveMailYn
	 */
	public String getReceiveMailYn() {
		return TextStringUtil.fixNull(receiveMailYn);
	}
	/**
	 * @param receiveMailYn the receiveMailYn to set
	 */
	public void setReceiveMailYn(String receiveMailYn) {
		this.receiveMailYn = receiveMailYn;
	}
	/**
	 * @return the receiveSmsYn
	 */
	public String getReceiveSmsYn() {
		return TextStringUtil.fixNull(receiveSmsYn);
	}
	/**
	 * @param receiveSmsYn the receiveSmsYn to set
	 */
	public void setReceiveSmsYn(String receiveSmsYn) {
		this.receiveSmsYn = receiveSmsYn;
	}
	/**
	 * @return the foreignYn
	 */
	public String getForeignYn() {
		return TextStringUtil.fixNull(foreignYn);
	}
	/**
	 * @param foreignYn the foreignYn to set
	 */
	public void setForeignYn(String foreignYn) {
		this.foreignYn = foreignYn;
	}
	/**
	 * @return the namecheckYn
	 */
	public String getNamecheckYn() {
		return TextStringUtil.fixNull(namecheckYn);
	}
	/**
	 * @param namecheckYn the namecheckYn to set
	 */
	public void setNamecheckYn(String namecheckYn) {
		this.namecheckYn = namecheckYn;
	}
	/**
	 * @return the nationCode
	 */
	public String getNationCode() {
		return TextStringUtil.fixNull(nationCode);
	}
	/**
	 * @param nationCode the nationCode to set
	 */
	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return TextStringUtil.fixNull(companyId);
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the memSeqs
	 */
	public String[] getMemSeqs() {
		return memSeqs;
	}
	/**
	 * @param memSeqs the memSeqs to set
	 */
	public void setMemSeqs(String[] memSeqs) {
		this.memSeqs = memSeqs;
	}

	/**
     * @return the memIdCount
     */
    public int getMemIdCount() {
            return memIdCount;
    }

    /**
     * @param memIdCount
     *             the memIdCount to set
     */
    public void setMemIdCount(int memIdCount) {
            this.memIdCount = memIdCount;
    }

    public String getFaxNo1() {
        return TextStringUtil.fixNull(faxNo1);
    }

    public void setFaxNo1(String faxNo1) {
        this.faxNo1 = faxNo1;
    }

    public String getFaxNo2() {
        return TextStringUtil.fixNull(faxNo2);
    }

    public void setFaxNo2(String faxNo2) {
        this.faxNo2 = faxNo2;
    }

    public String getFaxNo3() {
        return TextStringUtil.fixNull(faxNo3);
    }

    public void setFaxNo3(String faxNo3) {
        this.faxNo3 = faxNo3;
    }

    public String getgPinYn() {
        return TextStringUtil.fixNull(gPinYn);
    }
    public void setgPinYn(String gPinYn) {
        this.gPinYn = gPinYn;
    }
    public String getgPinKey1() {
        return TextStringUtil.fixNull(gPinKey1);
    }
    public void setgPinKey1(String gPinKey1) {
        this.gPinKey1 = gPinKey1;
    }
    public String getgPinKey2() {
        return TextStringUtil.fixNull(gPinKey2);
    }
    public void setgPinKey2(String gPinKey2) {
        this.gPinKey2 = gPinKey2;
    }



	/**
	 * @return the mAuthGroupId
	 */
	public String getmAuthGroupId() {
		return mAuthGroupId;
	}
	/**
	 * @param mAuthGroupId the mAuthGroupId to set
	 */
	public void setmAuthGroupId(String mAuthGroupId) {
		this.mAuthGroupId = mAuthGroupId;
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
	 * @return the eduCnt
	 */
	public int getEduCnt() {
		return eduCnt;
	}
	/**
	 * @param eduCnt the eduCnt to set
	 */
	public void setEduCnt(int eduCnt) {
		this.eduCnt = eduCnt;
	}
	/**
	 * @return the education
	 */
	public String getEducation() {
		return TextStringUtil.fixNull(education);
	}
	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}
	/**
	 * @return the establishmentDiv
	 */
	public String getEstablishmentDiv() {
		return TextStringUtil.fixNull(establishmentDiv);
	}
	/**
	 * @return the establishmentDiv
	 */
	public String getStrEstablishmentDiv() {
		String strEstablishmentDiv;
		if(establishmentDiv.equals("P"))
			strEstablishmentDiv = "사립";
		else if(establishmentDiv.equals("B"))
			strEstablishmentDiv = "공립";
		else if(establishmentDiv.equals("N"))
			strEstablishmentDiv = "국립";
		else
			strEstablishmentDiv = "";

		return strEstablishmentDiv;
	}
	/**
	 * @param establishmentDiv the establishmentDiv to set
	 */
	public void setEstablishmentDiv(String establishmentDiv) {
		this.establishmentDiv = establishmentDiv;
	}
	/**
	 * @return the dtlAddress
	 */
	public String getDtlAddress() {
		return TextStringUtil.fixNull(dtlAddress);
	}
	/**
	 * @param dtlAddress the dtlAddress to set
	 */
	public void setDtlAddress(String dtlAddress) {
		this.dtlAddress = dtlAddress;
	}
	/**
	 * @return the dtlAddressDtl
	 */
	public String getDtlAddressDtl() {
		return TextStringUtil.fixNull(dtlAddressDtl);
	}
	/**
	 * @param dtlAddressDtl the dtlAddressDtl to set
	 */
	public void setDtlAddressDtl(String dtlAddressDtl) {
		this.dtlAddressDtl = dtlAddressDtl;
	}
	/**
	 * @return the schoolDiv
	 */
	public String getSchoolDiv() {
		return TextStringUtil.fixNull(schoolDiv);
	}
	/**
	 * @param schoolDiv the schoolDiv to set
	 */
	public void setSchoolDiv(String schoolDiv) {
		this.schoolDiv = schoolDiv;
	}
	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return TextStringUtil.fixNull(schoolName);
	}
	/**
	 * @param schoolName the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	/**
	 * @return the dtlTelNo1
	 */
	public String getDtlTelNo1() {
		return TextStringUtil.fixNull(dtlTelNo1);
	}
	/**
	 * @param schoolTelNo1 the schoolTelNo1 to set
	 */
	public void setDtlTelNo1(String dtlTelNo1) {
		this.dtlTelNo1 = dtlTelNo1;
	}
	/**
	 * @return the dtlTelNo2
	 */
	public String getDtlTelNo2() {
		return TextStringUtil.fixNull(dtlTelNo2);
	}
	/**
	 * @param dtlTelNo2 the dtlTelNo2 to set
	 */
	public void setDtlTelNo2(String dtlTelNo2) {
		this.dtlTelNo2 = dtlTelNo2;
	}
	/**
	 * @return the dtlTelNo3
	 */
	public String getDtlTelNo3() {
		return TextStringUtil.fixNull(dtlTelNo3);
	}
	/**
	 * @param dtlTelNo3 the dtlTelNo3 to set
	 */
	public void setDtlTelNo3(String dtlTelNo3) {
		this.dtlTelNo3 = dtlTelNo3;
	}
	/**
	 * @return the dtlZipCode
	 */
	public String getDtlZipCode() {
		return TextStringUtil.fixNull(dtlZipCode);
	}
	/**
	 * @param dtlZipCode the dtlZipCode to set
	 */
	public void setDtlZipCode(String dtlZipCode) {
		this.dtlZipCode = dtlZipCode;
	}
	/**
	 * @return the attachDepartment
	 */
	public String getAttachDepartment() {
		return TextStringUtil.fixNull(attachDepartment);
	}
	/**
	 * @param attachDepartment the attachDepartment to set
	 */
	public void setAttachDepartment(String attachDepartment) {
		this.attachDepartment = attachDepartment;
	}
	/**
	 * @return the chargeSubject
	 */
	public String getChargeSubject() {
		return TextStringUtil.fixNull(chargeSubject);
	}
	/**
	 * @param chargeSubject the chargeSubject to set
	 */
	public void setChargeSubject(String chargeSubject) {
		this.chargeSubject = chargeSubject;
	}
	/**
	 * @return the educationCareer
	 */
	public String getEducationCareer() {
		return TextStringUtil.fixNull(educationCareer);
	}
	/**
	 * @param educationCareer the educationCareer to set
	 */
	public void setEducationCareer(String educationCareer) {
		this.educationCareer = educationCareer;
	}
	/**
	 * @return the finalQualify
	 */
	public String getFinalQualify() {
		return TextStringUtil.fixNull(finalQualify);
	}
	/**
	 * @param finalQualify the finalQualify to set
	 */
	public void setFinalQualify(String finalQualify) {
		this.finalQualify = finalQualify;
	}
	/**
	 * @return the joinCourse
	 */
	public String getJoinCourse() {
		return TextStringUtil.fixNull(joinCourse);
	}
	/**
	 * @param joinCourse the joinCourse to set
	 */
	public void setJoinCourse(String joinCourse) {
		this.joinCourse = joinCourse;
	}
	/**
	 * @return the joinObject
	 */
	public String getJoinObject() {
		return TextStringUtil.fixNull(joinObject);
	}
	/**
	 * @param joinObject the joinObject to set
	 */
	public void setJoinObject(String joinObject) {
		this.joinObject = joinObject;
	}
	/**
	 * @return the memDiv
	 */
	public String getMemDiv() {
		return TextStringUtil.fixNull(memDiv);
	}
	/**
	 * @param memDiv the memDiv to set
	 */
	public void setMemDiv(String memDiv) {
		this.memDiv = memDiv;
	}
	/**
	 * @return the memDtlCount
	 */
	public int getMemDtlCount() {
		return memDtlCount;
	}
	/**
	 * @param memDtlCount the memDtlCount to set
	 */
	public void setMemDtlCount(int memDtlCount) {
		this.memDtlCount = memDtlCount;
	}
	/**
	 * @return the memDtlSeq
	 */
	public String getMemDtlSeq() {
		return TextStringUtil.fixNull(memDtlSeq);
	}
	/**
	 * @param memDtlSeq the memDtlSeq to set
	 */
	public void setMemDtlSeq(String memDtlSeq) {
		this.memDtlSeq = memDtlSeq;
	}
	/**
	 * @return the schoolSeq
	 */
	public String getSchoolSeq() {
		return TextStringUtil.fixNull(schoolSeq);
	}
	/**
	 * @param schoolSeq the schoolSeq to set
	 */
	public void setSchoolSeq(String schoolSeq) {
		this.schoolSeq = schoolSeq;
	}
	/**
	 * @return the schoolClass
	 */
	public String getSchoolClass() {
		return TextStringUtil.fixNull(schoolClass);
	}
	/**
	 * @param schoolClass the schoolClass to set
	 */
	public void setSchoolClass(String schoolClass) {
		this.schoolClass = schoolClass;
	}
	/**
	 * @return the passwordUseYn
	 */
	public String getPasswordUseYn() {
		return TextStringUtil.fixNull(passwordUseYn);
	}
	/**
	 * @param passwordUseYn the passwordUseYn to set
	 */
	public void setPasswordUseYn(String passwordUseYn) {
		this.passwordUseYn = passwordUseYn;
	}
	/**
	 * @return the boardPassword
	 */
	public String getBoardPassword() {
		return TextStringUtil.fixNull(boardPassword);
	}
	/**
	 * @param boardPassword the boardPassword to set
	 */
	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}
	/**
	 * @return the authGroupCount
	 */
	public String getAuthGroupCount() {
		return authGroupCount;
	}
	/**
	 * @param authGroupCount the authGroupCount to set
	 */
	public void setAuthGroupCount(String authGroupCount) {
		this.authGroupCount = authGroupCount;
	}
	/**
	 * @return the searchDivName
	 */
	public String getSearchDivName() {
		return TextStringUtil.fixNull(searchDivName);
	}
	/**
	 * @param searchDivName the searchDivName to set
	 */
	public void setSearchDivName(String searchDivName) {
		this.searchDivName = searchDivName;
	}
	/**
	 * @return the searchSchoolName
	 */
	public String getSearchSchoolName() {
		return TextStringUtil.fixNull(searchSchoolName);
	}
	/**
	 * @param searchSchoolName the searchSchoolName to set
	 */
	public void setSearchSchoolName(String searchSchoolName) {
		this.searchSchoolName = searchSchoolName;
	}
	/**
	 * @return the certificationDiv
	 */
	public String getCertificationDiv() {
		return TextStringUtil.fixNull(certificationDiv);
	}
	/**
	 * @param certificationDiv the certificationDiv to set
	 */
	public void setCertificationDiv(String certificationDiv) {
		this.certificationDiv = certificationDiv;
	}
	/**
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return TextStringUtil.fixNull(instituteName);
	}
	/**
	 * @param instituteName the instituteName to set
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return TextStringUtil.fixNull(position);
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the insMemAttachDepartment
	 */
	public String getInsMemAttachDepartment() {
		return TextStringUtil.fixNull(insMemAttachDepartment);
	}
	/**
	 * @param insMemAttachDepartment the insMemAttachDepartment to set
	 */
	public void setInsMemAttachDepartment(String insMemAttachDepartment) {
		this.insMemAttachDepartment = insMemAttachDepartment;
	}
	/**
	 * @return the insMemEducation
	 */
	public String getInsMemEducation() {
		return TextStringUtil.fixNull(insMemEducation);
	}
	/**
	 * @param insMemEducation the insMemEducation to set
	 */
	public void setInsMemEducation(String insMemEducation) {
		this.insMemEducation = insMemEducation;
	}
	/**
	 * @return the insMemEducationCareer
	 */
	public String getInsMemEducationCareer() {
		return TextStringUtil.fixNull(insMemEducationCareer);
	}
	/**
	 * @param insMemEducationCareer the insMemEducationCareer to set
	 */
	public void setInsMemEducationCareer(String insMemEducationCareer) {
		this.insMemEducationCareer = insMemEducationCareer;
	}
	/**
	 * @return the insMemJoinCourse
	 */
	public String getInsMemJoinCourse() {
		return TextStringUtil.fixNull(insMemJoinCourse);
	}
	/**
	 * @param insMemJoinCourse the insMemJoinCourse to set
	 */
	public void setInsMemJoinCourse(String insMemJoinCourse) {
		this.insMemJoinCourse = insMemJoinCourse;
	}
	/**
	 * @return the insMemJoinObject
	 */
	public String getInsMemJoinObject() {
		return TextStringUtil.fixNull(insMemJoinObject);
	}
	/**
	 * @param insMemJoinObject the insMemJoinObject to set
	 */
	public void setInsMemJoinObject(String insMemJoinObject) {
		this.insMemJoinObject = insMemJoinObject;
	}
	/**
	 * @return the proMemAttachDepartment
	 */
	public String getProMemAttachDepartment() {
		return TextStringUtil.fixNull(proMemAttachDepartment);
	}
	/**
	 * @param proMemAttachDepartment the proMemAttachDepartment to set
	 */
	public void setProMemAttachDepartment(String proMemAttachDepartment) {
		this.proMemAttachDepartment = proMemAttachDepartment;
	}
	/**
	 * @return the proMemEducation
	 */
	public String getProMemEducation() {
		return TextStringUtil.fixNull(proMemEducation);
	}
	/**
	 * @param proMemEducation the proMemEducation to set
	 */
	public void setProMemEducation(String proMemEducation) {
		this.proMemEducation = proMemEducation;
	}
	/**
	 * @return the proMemEducationCareer
	 */
	public String getProMemEducationCareer() {
		return TextStringUtil.fixNull(proMemEducationCareer);
	}
	/**
	 * @param proMemEducationCareer the proMemEducationCareer to set
	 */
	public void setProMemEducationCareer(String proMemEducationCareer) {
		this.proMemEducationCareer = proMemEducationCareer;
	}
	/**
	 * @return the proMemJoinCourse
	 */
	public String getProMemJoinCourse() {
		return TextStringUtil.fixNull(proMemJoinCourse);
	}
	/**
	 * @param proMemJoinCourse the proMemJoinCourse to set
	 */
	public void setProMemJoinCourse(String proMemJoinCourse) {
		this.proMemJoinCourse = proMemJoinCourse;
	}
	/**
	 * @return the proMemJoinObject
	 */
	public String getProMemJoinObject() {
		return TextStringUtil.fixNull(proMemJoinObject);
	}
	/**
	 * @param proMemJoinObject the proMemJoinObject to set
	 */
	public void setProMemJoinObject(String proMemJoinObject) {
		this.proMemJoinObject = proMemJoinObject;
	}
	/**
	 * @return the trPhone
	 */
	public String getTrPhone() {
		return TextStringUtil.fixNull(trPhone);
	}
	/**
	 * @param trPhone the trPhone to set
	 */
	public void setTrPhone(String trPhone) {
		this.trPhone = trPhone;
	}
	/**
	 * @return the insMemDtlAddress
	 */
	public String getInsMemDtlAddress() {
		return TextStringUtil.fixNull(insMemDtlAddress);
	}
	/**
	 * @param insMemDtlAddress the insMemDtlAddress to set
	 */
	public void setInsMemDtlAddress(String insMemDtlAddress) {
		this.insMemDtlAddress = insMemDtlAddress;
	}
	/**
	 * @return the insMemDtlAddressDtl
	 */
	public String getInsMemDtlAddressDtl() {
		return TextStringUtil.fixNull(insMemDtlAddressDtl);
	}
	/**
	 * @param insMemDtlAddressDtl the insMemDtlAddressDtl to set
	 */
	public void setInsMemDtlAddressDtl(String insMemDtlAddressDtl) {
		this.insMemDtlAddressDtl = insMemDtlAddressDtl;
	}
	/**
	 * @return the insMemDtlTelNo1
	 */
	public String getInsMemDtlTelNo1() {
		return TextStringUtil.fixNull(insMemDtlTelNo1);
	}
	/**
	 * @param insMemDtlTelNo1 the insMemDtlTelNo1 to set
	 */
	public void setInsMemDtlTelNo1(String insMemDtlTelNo1) {
		this.insMemDtlTelNo1 = insMemDtlTelNo1;
	}
	/**
	 * @return the insMemDtlTelNo2
	 */
	public String getInsMemDtlTelNo2() {
		return TextStringUtil.fixNull(insMemDtlTelNo2);
	}
	/**
	 * @param insMemDtlTelNo2 the insMemDtlTelNo2 to set
	 */
	public void setInsMemDtlTelNo2(String insMemDtlTelNo2) {
		this.insMemDtlTelNo2 = insMemDtlTelNo2;
	}
	/**
	 * @return the insMemDtlTelNo3
	 */
	public String getInsMemDtlTelNo3() {
		return TextStringUtil.fixNull(insMemDtlTelNo3);
	}
	/**
	 * @param insMemDtlTelNo3 the insMemDtlTelNo3 to set
	 */
	public void setInsMemDtlTelNo3(String insMemDtlTelNo3) {
		this.insMemDtlTelNo3 = insMemDtlTelNo3;
	}
	/**
	 * @return the insMemDtlZipCode
	 */
	public String getInsMemDtlZipCode() {
		return TextStringUtil.fixNull(insMemDtlZipCode);
	}
	/**
	 * @param insMemDtlZipCode the insMemDtlZipCode to set
	 */
	public void setInsMemDtlZipCode(String insMemDtlZipCode) {
		this.insMemDtlZipCode = insMemDtlZipCode;
	}
	/**
	 * @return the proMemDtlAddress
	 */
	public String getProMemDtlAddress() {
		return TextStringUtil.fixNull(proMemDtlAddress);
	}
	/**
	 * @param proMemDtlAddress the proMemDtlAddress to set
	 */
	public void setProMemDtlAddress(String proMemDtlAddress) {
		this.proMemDtlAddress = proMemDtlAddress;
	}
	/**
	 * @return the proMemDtlAddressDtl
	 */
	public String getProMemDtlAddressDtl() {
		return TextStringUtil.fixNull(proMemDtlAddressDtl);
	}
	/**
	 * @param proMemDtlAddressDtl the proMemDtlAddressDtl to set
	 */
	public void setProMemDtlAddressDtl(String proMemDtlAddressDtl) {
		this.proMemDtlAddressDtl = proMemDtlAddressDtl;
	}
	/**
	 * @return the proMemDtlTelNo1
	 */
	public String getProMemDtlTelNo1() {
		return TextStringUtil.fixNull(proMemDtlTelNo1);
	}
	/**
	 * @param proMemDtlTelNo1 the proMemDtlTelNo1 to set
	 */
	public void setProMemDtlTelNo1(String proMemDtlTelNo1) {
		this.proMemDtlTelNo1 = proMemDtlTelNo1;
	}
	/**
	 * @return the proMemDtlTelNo2
	 */
	public String getProMemDtlTelNo2() {
		return TextStringUtil.fixNull(proMemDtlTelNo2);
	}
	/**
	 * @param proMemDtlTelNo2 the proMemDtlTelNo2 to set
	 */
	public void setProMemDtlTelNo2(String proMemDtlTelNo2) {
		this.proMemDtlTelNo2 = proMemDtlTelNo2;
	}
	/**
	 * @return the proMemDtlTelNo3
	 */
	public String getProMemDtlTelNo3() {
		return TextStringUtil.fixNull(proMemDtlTelNo3);
	}
	/**
	 * @param proMemDtlTelNo3 the proMemDtlTelNo3 to set
	 */
	public void setProMemDtlTelNo3(String proMemDtlTelNo3) {
		this.proMemDtlTelNo3 = proMemDtlTelNo3;
	}
	/**
	 * @return the proMemDtlZipCode
	 */
	public String getProMemDtlZipCode() {
		return TextStringUtil.fixNull(proMemDtlZipCode);
	}
	/**
	 * @param proMemDtlZipCode the proMemDtlZipCode to set
	 */
	public void setProMemDtlZipCode(String proMemDtlZipCode) {
		this.proMemDtlZipCode = proMemDtlZipCode;
	}
	/**
	 * @return the chargeSubjectEtc
	 */
	public String getChargeSubjectEtc() {
		return TextStringUtil.fixNull(chargeSubjectEtc);
	}
	/**
	 * @param chargeSubjectEtc the chargeSubjectEtc to set
	 */
	public void setChargeSubjectEtc(String chargeSubjectEtc) {
		this.chargeSubjectEtc = chargeSubjectEtc;
	}
	/**
	 * @return the insMemChargeSubjectEtc
	 */
	public String getInsMemChargeSubjectEtc() {
		return TextStringUtil.fixNull(insMemChargeSubjectEtc);
	}
	/**
	 * @param insMemChargeSubjectEtc the insMemChargeSubjectEtc to set
	 */
	public void setInsMemChargeSubjectEtc(String insMemChargeSubjectEtc) {
		this.insMemChargeSubjectEtc = insMemChargeSubjectEtc;
	}
	/**
	 * @return the insMemJoinCourseEtc
	 */
	public String getInsMemJoinCourseEtc() {
		return TextStringUtil.fixNull(insMemJoinCourseEtc);
	}
	/**
	 * @param insMemJoinCourseEtc the insMemJoinCourseEtc to set
	 */
	public void setInsMemJoinCourseEtc(String insMemJoinCourseEtc) {
		this.insMemJoinCourseEtc = insMemJoinCourseEtc;
	}
	/**
	 * @return the insMemJoinObjectEtc
	 */
	public String getInsMemJoinObjectEtc() {
		return TextStringUtil.fixNull(insMemJoinObjectEtc);
	}
	/**
	 * @param insMemJoinObjectEtc the insMemJoinObjectEtc to set
	 */
	public void setInsMemJoinObjectEtc(String insMemJoinObjectEtc) {
		this.insMemJoinObjectEtc = insMemJoinObjectEtc;
	}
	/**
	 * @return the joinCourseEtc
	 */
	public String getJoinCourseEtc() {
		return TextStringUtil.fixNull(joinCourseEtc);
	}
	/**
	 * @param joinCourseEtc the joinCourseEtc to set
	 */
	public void setJoinCourseEtc(String joinCourseEtc) {
		this.joinCourseEtc = joinCourseEtc;
	}
	/**
	 * @return the joinObjectEtc
	 */
	public String getJoinObjectEtc() {
		return TextStringUtil.fixNull(joinObjectEtc);
	}
	/**
	 * @param joinObjectEtc the joinObjectEtc to set
	 */
	public void setJoinObjectEtc(String joinObjectEtc) {
		this.joinObjectEtc = joinObjectEtc;
	}
	/**
	 * @return the positionEtc
	 */
	public String getPositionEtc() {
		return TextStringUtil.fixNull(positionEtc);
	}
	/**
	 * @param positionEtc the positionEtc to set
	 */
	public void setPositionEtc(String positionEtc) {
		this.positionEtc = positionEtc;
	}
	/**
	 * @return the proMemChargeSubjectEtc
	 */
	public String getProMemChargeSubjectEtc() {
		return TextStringUtil.fixNull(proMemChargeSubjectEtc);
	}
	/**
	 * @param proMemChargeSubjectEtc the proMemChargeSubjectEtc to set
	 */
	public void setProMemChargeSubjectEtc(String proMemChargeSubjectEtc) {
		this.proMemChargeSubjectEtc = proMemChargeSubjectEtc;
	}
	/**
	 * @return the proMemJoinCourseEtc
	 */
	public String getProMemJoinCourseEtc() {
		return TextStringUtil.fixNull(proMemJoinCourseEtc);
	}
	/**
	 * @param proMemJoinCourseEtc the proMemJoinCourseEtc to set
	 */
	public void setProMemJoinCourseEtc(String proMemJoinCourseEtc) {
		this.proMemJoinCourseEtc = proMemJoinCourseEtc;
	}
	/**
	 * @return the proMemJoinObjectEtc
	 */
	public String getProMemJoinObjectEtc() {
		return TextStringUtil.fixNull(proMemJoinObjectEtc);
	}
	/**
	 * @param proMemJoinObjectEtc the proMemJoinObjectEtc to set
	 */
	public void setProMemJoinObjectEtc(String proMemJoinObjectEtc) {
		this.proMemJoinObjectEtc = proMemJoinObjectEtc;
	}
	/**
	 * @return the stdMemAttachDepartment
	 */
	public String getStdMemAttachDepartment() {
		return TextStringUtil.fixNull(stdMemAttachDepartment);
	}
	/**
	 * @param stdMemAttachDepartment the stdMemAttachDepartment to set
	 */
	public void setStdMemAttachDepartment(String stdMemAttachDepartment) {
		this.stdMemAttachDepartment = stdMemAttachDepartment;
	}
	/**
	 * @return the stdMemJoinCourse
	 */
	public String getStdMemJoinCourse() {
		return TextStringUtil.fixNull(stdMemJoinCourse);
	}
	/**
	 * @param stdMemJoinCourse the stdMemJoinCourse to set
	 */
	public void setStdMemJoinCourse(String stdMemJoinCourse) {
		this.stdMemJoinCourse = stdMemJoinCourse;
	}
	/**
	 * @return the stdMemJoinObject
	 */
	public String getStdMemJoinObject() {
		return TextStringUtil.fixNull(stdMemJoinObject);
	}
	/**
	 * @param stdMemJoinObject the stdMemJoinObject to set
	 */
	public void setStdMemJoinObject(String stdMemJoinObject) {
		this.stdMemJoinObject = stdMemJoinObject;
	}
	/**
	 * @return the finalQualifyEtc
	 */
	public String getFinalQualifyEtc() {
		return TextStringUtil.fixNull(finalQualifyEtc);
	}
	/**
	 * @param finalQualifyEtc the finalQualifyEtc to set
	 */
	public void setFinalQualifyEtc(String finalQualifyEtc) {
		this.finalQualifyEtc = finalQualifyEtc;
	}
	/**
	 * @return the stdMemJoinCourseEtc
	 */
	public String getStdMemJoinCourseEtc() {
		return TextStringUtil.fixNull(stdMemJoinCourseEtc);
	}
	/**
	 * @param stdMemJoinCourseEtc the stdMemJoinCourseEtc to set
	 */
	public void setStdMemJoinCourseEtc(String stdMemJoinCourseEtc) {
		this.stdMemJoinCourseEtc = stdMemJoinCourseEtc;
	}
	/**
	 * @return the stdMemJoinObjectEtc
	 */
	public String getStdMemJoinObjectEtc() {
		return TextStringUtil.fixNull(stdMemJoinObjectEtc);
	}
	/**
	 * @param stdMemJoinObjectEtc the stdMemJoinObjectEtc to set
	 */
	public void setStdMemJoinObjectEtc(String stdMemJoinObjectEtc) {
		this.stdMemJoinObjectEtc = stdMemJoinObjectEtc;
	}
	/**
	 * @return the jobGroup
	 */
	public String getJobGroup() {
		return TextStringUtil.fixNull(jobGroup);
	}
	/**
	 * @param jobGroup the jobGroup to set
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	/**
	 * @return the jobGroupEtc
	 */
	public String getJobGroupEtc() {
		return TextStringUtil.fixNull(jobGroupEtc);
	}
	/**
	 * @param jobGroupEtc the jobGroupEtc to set
	 */
	public void setJobGroupEtc(String jobGroupEtc) {
		this.jobGroupEtc = jobGroupEtc;
	}
	/**
	 * @return the searchMemDiv
	 */
	public String getSearchMemDiv() {
		return TextStringUtil.fixNull(searchMemDiv);
	}
	/**
	 * @param searchMemDiv the searchMemDiv to set
	 */
	public void setSearchMemDiv(String searchMemDiv) {
		this.searchMemDiv = searchMemDiv;
	}
	/**
	 * @return the insSchoolSub
	 */
	public String getInsSchoolSub() {
		return TextStringUtil.fixNull(insSchoolSub);
	}
	/**
	 * @param insSchoolSub the insSchoolSub to set
	 */
	public void setInsSchoolSub(String insSchoolSub) {
		this.insSchoolSub = insSchoolSub;
	}
	/**
	 * @return the proSchoolSub
	 */
	public String getProSchoolSub() {
		return TextStringUtil.fixNull(proSchoolSub);
	}
	/**
	 * @param proSchoolSub the proSchoolSub to set
	 */
	public void setProSchoolSub(String proSchoolSub) {
		this.proSchoolSub = proSchoolSub;
	}
	/**
	 * @return the schoolSub
	 */
	public String getSchoolSub() {
		return TextStringUtil.fixNull(schoolSub);
	}
	/**
	 * @param schoolSub the schoolSub to set
	 */
	public void setSchoolSub(String schoolSub) {
		this.schoolSub = schoolSub;
	}
	public String getHpNo() {
		return TextStringUtil.fixNull(hpNo);
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	/**
	 * @return the memParentSeq
	 */
	public String getMemParentSeq() {
		return TextStringUtil.fixNull(memParentSeq);
	}
	/**
	 * @param memParentSeq the memParentSeq to set
	 */
	public void setMemParentSeq(String memParentSeq) {
		this.memParentSeq = memParentSeq;
	}
	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return TextStringUtil.fixNull(parentName);
	}
	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @return the parentBirth
	 */
	public String getParentBirth() {
		return TextStringUtil.fixNull(parentBirth);
	}
	/**
	 * @param parentBirth the parentBirth to set
	 */
	public void setParentBirth(String parentBirth) {
		this.parentBirth = parentBirth;
	}
	/**
	 * @return the parentRegiNo
	 */
	public String getParentRegiNo() {
		return TextStringUtil.fixNull(parentRegiNo);
	}
	/**
	 * @param parentRegiNo the parentRegiNo to set
	 */
	public void setParentRegiNo(String parentRegiNo) {
		this.parentRegiNo = parentRegiNo;
	}
	/**
	 * @return the parentGPinKey1
	 */
	public String getParentGPinKey1() {
		return TextStringUtil.fixNull(parentGPinKey1);
	}
	/**
	 * @param parentGPinKey1 the parentGPinKey1 to set
	 */
	public void setParentGPinKey1(String parentGPinKey1) {
		this.parentGPinKey1 = parentGPinKey1;
	}
	/**
	 * @return the parentGpinKey2
	 */
	public String getParentGpinKey2() {
		return TextStringUtil.fixNull(parentGpinKey2);
	}
	/**
	 * @param parentGpinKey2 the parentGpinKey2 to set
	 */
	public void setParentGpinKey2(String parentGpinKey2) {
		this.parentGpinKey2 = parentGpinKey2;
	}
	/**
	 * @return the parentTelNo1
	 */
	public String getParentTelNo1() {
		return TextStringUtil.fixNull(parentTelNo1);
	}
	/**
	 * @param parentTelNo1 the parentTelNo1 to set
	 */
	public void setParentTelNo1(String parentTelNo1) {
		this.parentTelNo1 = parentTelNo1;
	}
	/**
	 * @return the parentTelNo2
	 */
	public String getParentTelNo2() {
		return TextStringUtil.fixNull(parentTelNo2);
	}
	/**
	 * @param parentTelNo2 the parentTelNo2 to set
	 */
	public void setParentTelNo2(String parentTelNo2) {
		this.parentTelNo2 = parentTelNo2;
	}
	/**
	 * @return the parentTelNo3
	 */
	public String getParentTelNo3() {
		return TextStringUtil.fixNull(parentTelNo3);
	}
	/**
	 * @param parentTelNo3 the parentTelNo3 to set
	 */
	public void setParentTelNo3(String parentTelNo3) {
		this.parentTelNo3 = parentTelNo3;
	}
	/**
	 * @return the searchPhone
	 */
	public String getSearchPhone() {
		return TextStringUtil.fixNull(searchPhone);
	}
	/**
	 * @param searchPhone the searchPhone to set
	 */
	public void setSearchPhone(String searchPhone) {
		this.searchPhone = searchPhone;
	}
	/**
	 * @return the searchScsnYn
	 */
	public String getSearchScsnYn() {
		return TextStringUtil.fixNull(searchScsnYn);
	}
	/**
	 * @param searchScsnYn the searchScsnYn to set
	 */
	public void setSearchScsnYn(String searchScsnYn) {
		this.searchScsnYn = searchScsnYn;
	}

	/**
	 * @return the setSearchAspId
	 */
	public String getSearchAspId(){
		return TextStringUtil.fixNull(searchAspId);
	}
	/**
	 * @param searchScsnYn the searchScsnYn to set
	 */
	public void setSearchAspId(String searchAspId) {
		this.searchAspId = searchAspId;
	}

	/**
	 * @return the setSearchAspId
	 */
	public String getAspName(){
		return TextStringUtil.fixNull(aspName);
	}
	/**
	 * @param searchScsnYn the searchScsnYn to set
	 */
	public void setAspName(String aspName) {
		this.aspName = aspName;
	}
	/**
	 * @return the indJobGroup
	 */
	public String getIndJobGroup() {
		return TextStringUtil.fixNull(indJobGroup);
	}
	/**
	 * @param indJobGroup the indJobGroup to set
	 */
	public void setIndJobGroup(String indJobGroup) {
		this.indJobGroup = indJobGroup;
	}
	/**
	 * @return the schJobGroup
	 */
	public String getSchJobGroup() {
		return TextStringUtil.fixNull(schJobGroup);
	}
	/**
	 * @param schJobGroup the schJobGroup to set
	 */
	public void setSchJobGroup(String schJobGroup) {
		this.schJobGroup = schJobGroup;
	}
	/**
	 * @return the stdJobGroup
	 */
	public String getStdJobGroup() {
		return TextStringUtil.fixNull(stdJobGroup);
	}
	/**
	 * @param stdJobGroup the stdJobGroup to set
	 */
	public void setStdJobGroup(String stdJobGroup) {
		this.stdJobGroup = stdJobGroup;
	}
	/**
	 * @return the indJobGroupEtc
	 */
	public String getIndJobGroupEtc() {
		return TextStringUtil.fixNull(indJobGroupEtc);
	}
	/**
	 * @param indJobGroupEtc the indJobGroupEtc to set
	 */
	public void setIndJobGroupEtc(String indJobGroupEtc) {
		this.indJobGroupEtc = indJobGroupEtc;
	}
	/**
	 * @return the schJobGroupEtc
	 */
	public String getSchJobGroupEtc() {
		return TextStringUtil.fixNull(schJobGroupEtc);
	}
	/**
	 * @param schJobGroupEtc the schJobGroupEtc to set
	 */
	public void setSchJobGroupEtc(String schJobGroupEtc) {
		this.schJobGroupEtc = schJobGroupEtc;
	}
	/**
	 * @return the stdJobGroupEtc
	 */
	public String getStdJobGroupEtc() {
		return TextStringUtil.fixNull(stdJobGroupEtc);
	}
	/**
	 * @param stdJobGroupEtc the stdJobGroupEtc to set
	 */
	public void setStdJobGroupEtc(String stdJobGroupEtc) {
		this.stdJobGroupEtc = stdJobGroupEtc;
	}
	/**
	 * @return the indMemJoinCourse
	 */
	public String getIndMemJoinCourse() {
		return TextStringUtil.fixNull(indMemJoinCourse);
	}
	/**
	 * @param indMemJoinCourse the indMemJoinCourse to set
	 */
	public void setIndMemJoinCourse(String indMemJoinCourse) {
		this.indMemJoinCourse = indMemJoinCourse;
	}
	/**
	 * @return the indMemJoinCourseEtc
	 */
	public String getIndMemJoinCourseEtc() {
		return TextStringUtil.fixNull(indMemJoinCourseEtc);
	}
	/**
	 * @param indMemJoinCourseEtc the indMemJoinCourseEtc to set
	 */
	public void setIndMemJoinCourseEtc(String indMemJoinCourseEtc) {
		this.indMemJoinCourseEtc = indMemJoinCourseEtc;
	}
	/**
	 * @return the indMemJoinObject
	 */
	public String getIndMemJoinObject() {
		return TextStringUtil.fixNull(indMemJoinObject);
	}
	/**
	 * @param indMemJoinObject the indMemJoinObject to set
	 */
	public void setIndMemJoinObject(String indMemJoinObject) {
		this.indMemJoinObject = indMemJoinObject;
	}
	/**
	 * @return the indMemJoinObjectEtc
	 */
	public String getIndMemJoinObjectEtc() {
		return TextStringUtil.fixNull(indMemJoinObjectEtc);
	}
	/**
	 * @param indMemJoinObjectEtc the indMemJoinObjectEtc to set
	 */
	public void setIndMemJoinObjectEtc(String indMemJoinObjectEtc) {
		this.indMemJoinObjectEtc = indMemJoinObjectEtc;
	}
	/**
	 * @return the schMemJoinCourse
	 */
	public String getSchMemJoinCourse() {
		return TextStringUtil.fixNull(schMemJoinCourse);
	}
	/**
	 * @param schMemJoinCourse the schMemJoinCourse to set
	 */
	public void setSchMemJoinCourse(String schMemJoinCourse) {
		this.schMemJoinCourse = schMemJoinCourse;
	}
	/**
	 * @return the schMemJoinCourseEtc
	 */
	public String getSchMemJoinCourseEtc() {
		return TextStringUtil.fixNull(schMemJoinCourseEtc);
	}
	/**
	 * @param schMemJoinCourseEtc the schMemJoinCourseEtc to set
	 */
	public void setSchMemJoinCourseEtc(String schMemJoinCourseEtc) {
		this.schMemJoinCourseEtc = schMemJoinCourseEtc;
	}
	/**
	 * @return the schMemJoinObject
	 */
	public String getSchMemJoinObject() {
		return TextStringUtil.fixNull(schMemJoinObject);
	}
	/**
	 * @param schMemJoinObject the schMemJoinObject to set
	 */
	public void setSchMemJoinObject(String schMemJoinObject) {
		this.schMemJoinObject = schMemJoinObject;
	}
	/**
	 * @return the schMemJoinObjectEtc
	 */
	public String getSchMemJoinObjectEtc() {
		return TextStringUtil.fixNull(schMemJoinObjectEtc);
	}
	/**
	 * @param schMemJoinObjectEtc the schMemJoinObjectEtc to set
	 */
	public void setSchMemJoinObjectEtc(String schMemJoinObjectEtc) {
		this.schMemJoinObjectEtc = schMemJoinObjectEtc;
	}
	/**
	 * @return the indMemAttachDepartment
	 */
	public String getIndMemAttachDepartment() {
		return TextStringUtil.fixNull(indMemAttachDepartment);
	}
	/**
	 * @param indMemAttachDepartment the indMemAttachDepartment to set
	 */
	public void setIndMemAttachDepartment(String indMemAttachDepartment) {
		this.indMemAttachDepartment = indMemAttachDepartment;
	}
	/**
	 * @return the indPosition
	 */
	public String getIndPosition() {
		return TextStringUtil.fixNull(indPosition);
	}
	/**
	 * @param indPosition the indPosition to set
	 */
	public void setIndPosition(String indPosition) {
		this.indPosition = indPosition;
	}
	/**
	 * @return the indPositionEtc
	 */
	public String getIndPositionEtc() {
		return TextStringUtil.fixNull(indPositionEtc);
	}
	/**
	 * @param indPositionEtc the indPositionEtc to set
	 */
	public void setIndPositionEtc(String indPositionEtc) {
		this.indPositionEtc = indPositionEtc;
	}
	/**
	 * @return the proPosition
	 */
	public String getProPosition() {
		return TextStringUtil.fixNull(proPosition);
	}
	/**
	 * @param proPosition the proPosition to set
	 */
	public void setProPosition(String proPosition) {
		this.proPosition = proPosition;
	}
	/**
	 * @return the proPositionEtc
	 */
	public String getProPositionEtc() {
		return TextStringUtil.fixNull(proPositionEtc);
	}
	/**
	 * @param proPositionEtc the proPositionEtc to set
	 */
	public void setProPositionEtc(String proPositionEtc) {
		this.proPositionEtc = proPositionEtc;
	}
	/**
	 * @return the schMajr
	 */
	public String getSchMajr() {
		return TextStringUtil.fixNull(schMajr);
	}
	/**
	 * @param schMajr the schMajr to set
	 */
	public void setSchMajr(String schMajr) {
		this.schMajr = schMajr;
	}
	/**
	 * @return the mAuthGroupId
	 */
	public String getMAuthGroupId() {
		return TextStringUtil.fixNull(mAuthGroupId);
	}
	/**
	 * @param authGroupId the mAuthGroupId to set
	 */
	public void setMAuthGroupId(String authGroupId) {
		mAuthGroupId = authGroupId;
	}
	/**
	 * @return the indCompanyName
	 */
	public String getIndCompanyName() {
		return TextStringUtil.fixNull(indCompanyName);
	}
	/**
	 * @param indCompanyName the indCompanyName to set
	 */
	public void setIndCompanyName(String indCompanyName) {
		this.indCompanyName = indCompanyName;
	}
	/**
	 * @return the stdCompanyName
	 */
	public String getStdCompanyName() {
		return TextStringUtil.fixNull(stdCompanyName);
	}
	/**
	 * @param stdCompanyName the stdCompanyName to set
	 */
	public void setStdCompanyName(String stdCompanyName) {
		this.stdCompanyName = stdCompanyName;
	}
	/**
	 * @return the indCompanyId
	 */
	public String getIndCompanyId() {
		return TextStringUtil.fixNull(indCompanyId);
	}
	/**
	 * @param indCompanyId the indCompanyId to set
	 */
	public void setIndCompanyId(String indCompanyId) {
		this.indCompanyId = indCompanyId;
	}
	/**
	 * @return the stdCompanyId
	 */
	public String getStdCompanyId() {
		return TextStringUtil.fixNull(stdCompanyId);
	}
	/**
	 * @param stdCompanyId the stdCompanyId to set
	 */
	public void setStdCompanyId(String stdCompanyId) {
		this.stdCompanyId = stdCompanyId;
	}
	/**
	 * @return the insSchoolName
	 */
	public String getInsSchoolName() {
		return TextStringUtil.fixNull(insSchoolName);
	}
	/**
	 * @param insSchoolName the insSchoolName to set
	 */
	public void setInsSchoolName(String insSchoolName) {
		this.insSchoolName = insSchoolName;
	}
	/**
	 * @return the schSchoolName
	 */
	public String getSchSchoolName() {
		return TextStringUtil.fixNull(schSchoolName);
	}
	/**
	 * @param schSchoolName the schSchoolName to set
	 */
	public void setSchSchoolName(String schSchoolName) {
		this.schSchoolName = schSchoolName;
	}
	/**
	 * @return the curMemPassword
	 */
	public String getCurMemPassword() {
		return TextStringUtil.fixNull(curMemPassword);
	}
	/**
	 * @param curMemPassword the curMemPassword to set
	 */
	public void setCurMemPassword(String curMemPassword) {
		this.curMemPassword = curMemPassword;
	}
	/**
	 * @return the newMemPassword
	 */
	public String getNewMemPassword() {
		return TextStringUtil.fixNull(newMemPassword);
	}
	/**
	 * @param newMemPassword the newMemPassword to set
	 */
	public void setNewMemPassword(String newMemPassword) {
		this.newMemPassword = newMemPassword;
	}
	/**
	 * @return the initPassword
	 */
	public String getInitPassword() {
		return TextStringUtil.fixNull(initPassword);
	}
	/**
	 * @param initPassword the initPassword to set
	 */
	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}
	public String getAspUrl() {
		return TextStringUtil.fixNull(aspUrl);
	}
	public void setAspUrl(String aspUrl) {
		this.aspUrl = aspUrl;
	}
	/**
	 * @return the searchField
	 */
	public String getSearchField() {
		return TextStringUtil.fixNull(searchField);
	}
	/**
	 * @param searchField the searchField to set
	 */
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return TextStringUtil.fixNull(searchValue);
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

    public String getSearchQuery() {
        String searchQuery = "";

        return searchQuery;
    }
	/**
	 * @return the year
	 */
	public String getYear() {
		return TextStringUtil.fixNull(year);
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return TextStringUtil.fixNull(month);
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return TextStringUtil.fixNull(day);
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the telNo
	 */
	public String getTelNo() {
		return TextStringUtil.fixNull(telNo);
	}
	/**
	 * @param telNo the telNo to set
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	/**
	 * @return the certiNo
	 */
	public long getCertiNo() {
		return certiNo;
	}
	/**
	 * @param certiNo the certiNo to set
	 */
	public void setCertiNo(long certiNo) {
		this.certiNo = certiNo;
	}
	/**
	 * @return the certiCharacter
	 */
	public String getCertiCharacter() {
		return TextStringUtil.fixNull(certiCharacter);
	}
	/**
	 * @param certiCharacter the certiCharacter to set
	 */
	public void setCertiCharacter(String certiCharacter) {
		this.certiCharacter = certiCharacter;
	}
	/**
	 * @return the certiYn
	 */
	public String getCertiYn() {
		return TextStringUtil.fixNull(certiYn);
	}
	/**
	 * @param certiYn the certiYn to set
	 */
	public void setCertiYn(String certiYn) {
		this.certiYn = certiYn;
	}
	/**
	 * @return the insertResult
	 */
	public String getInsertResult() {
		return TextStringUtil.fixNull(insertResult);
	}
	/**
	 * @param insertResult the insertResult to set
	 */
	public void setInsertResult(String insertResult) {
		this.insertResult = insertResult;
	}
	public String getOrderMemName() {
		return orderMemName;
	}
	public void setOrderMemName(String orderMemName) {
		this.orderMemName = orderMemName;
	}
	public String getOrderZipCode() {
		return orderZipCode;
	}
	public void setOrderZipCode(String orderZipCode) {
		this.orderZipCode = orderZipCode;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderAddressDtl() {
		return orderAddressDtl;
	}
	public void setOrderAddressDtl(String orderAddressDtl) {
		this.orderAddressDtl = orderAddressDtl;
	}
	public String getOrderHpNo1() {
		return orderHpNo1;
	}
	public void setOrderHpNo1(String orderHpNo1) {
		this.orderHpNo1 = orderHpNo1;
	}
	public String getOrderHpNo2() {
		return orderHpNo2;
	}
	public void setOrderHpNo2(String orderHpNo2) {
		this.orderHpNo2 = orderHpNo2;
	}
	public String getOrderHpNo3() {
		return orderHpNo3;
	}
	public void setOrderHpNo3(String orderHpNo3) {
		this.orderHpNo3 = orderHpNo3;
	}
	public String getOrderComment() {
		return orderComment;
	}
	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}
	/**
	 * @return the roleDiv
	 */
	public String getRoleDiv() {
		return TextStringUtil.fixNull(roleDiv);
	}
	/**
	 * @param roleDiv the roleDiv to set
	 */
	public void setRoleDiv(String roleDiv) {
		this.roleDiv = roleDiv;
	}
	/**
	 * @return the changePassword
	 */
	public boolean isChangePassword() {
		return changePassword;
	}
	/**
	 * @param changePassword the changePassword to set
	 */
	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}
	/**
	 * @return the compNm
	 */
	public String getCompNm() {
		return TextStringUtil.fixNull(compNm);
	}
	/**
	 * @param compNm the compNm to set
	 */
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}

	public String getLecTarget() {
		return lecTarget;
	}
	public void setLecTarget(String lecTarget) {
		this.lecTarget = lecTarget;
	}
	public String getLecTargetYear() {
		return lecTargetYear;
	}
	public void setLecTargetYear(String lecTargetYear) {
		this.lecTargetYear = lecTargetYear;
	}

    public String getInsertDate() {
        return TextStringUtil.fixNull(insertDate);
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }
	public String getLectureStat() {
		return lectureStat;
	}
	public void setLectureStat(String lectureStat) {
		this.lectureStat = lectureStat;
	}
	public String getMemSeqPop() {
		return memSeqPop;
	}
	public void setMemSeqPop(String memSeqPop) {
		this.memSeqPop = memSeqPop;
	}
	public String getAgreeYn() {
		return agreeYn;
	}
	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}
	public String getLoginPWMaxUsedDays() {
		return loginPWMaxUsedDays;
	}
	public void setLoginPWMaxUsedDays(String loginPWMaxUsedDays) {
		this.loginPWMaxUsedDays = loginPWMaxUsedDays;
	}
	public String getSubjTutMappingId() {
		return subjTutMappingId;
	}
	public void setSubjTutMappingId(String subjTutMappingId) {
		this.subjTutMappingId = subjTutMappingId;
	}
	public String getSubjCcmMappingId() {
		return subjCcmMappingId;
	}
	public void setSubjCcmMappingId(String subjCcmMappingId) {
		this.subjCcmMappingId = subjCcmMappingId;
	}
	public String getSubjInsMappingId() {
		return subjInsMappingId;
	}
	public void setSubjInsMappingId(String subjInsMappingId) {
		this.subjInsMappingId = subjInsMappingId;
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getTraningProcessMappingId() {
		return traningProcessMappingId;
	}
	public void setTraningProcessMappingId(String traningProcessMappingId) {
		this.traningProcessMappingId = traningProcessMappingId;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	public String getHrdTraningNo() {
		return hrdTraningNo;
	}
	public void setHrdTraningNo(String hrdTraningNo) {
		this.hrdTraningNo = hrdTraningNo;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String getInsertFlag() {
		return insertFlag;
	}
	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}
	public String getSubjectTraningType() {
		return subjectTraningType;
	}
	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDeleteName() {
		return deleteName;
	}
	public void setDeleteName(String deleteName) {
		this.deleteName = deleteName;
	}
	public String getDeleteDt() {
		return deleteDt;
	}
	public void setDeleteDt(String deleteDt) {
		this.deleteDt = deleteDt;
	}
	public String getOldTraningProcessId() {
		return oldTraningProcessId;
	}
	public void setOldTraningProcessId(String oldTraningProcessId) {
		this.oldTraningProcessId = oldTraningProcessId;
	}



	public String getRelatedResearchOrganizationName() {
		return relatedResearchOrganizationName;
	}
	public void setRelatedResearchOrganizationName(
			String relatedResearchOrganizationName) {
		this.relatedResearchOrganizationName = relatedResearchOrganizationName;
	}
	public String getRelatedResearchYear() {
		return relatedResearchYear;
	}
	public void setRelatedResearchYear(String relatedResearchYear) {
		this.relatedResearchYear = relatedResearchYear;
	}
	public String getSameSiteWorkYear() {
		return sameSiteWorkYear;
	}
	public void setSameSiteWorkYear(String sameSiteWorkYear) {
		this.sameSiteWorkYear = sameSiteWorkYear;
	}
	public String getSameSiteWorkCd() {
		return sameSiteWorkCd;
	}
	public void setSameSiteWorkCd(String sameSiteWorkCd) {
		this.sameSiteWorkCd = sameSiteWorkCd;
	}
	public String getParticipateStartDate() {
		return participateStartDate;
	}
	public void setParticipateStartDate(String participateStartDate) {
		this.participateStartDate = participateStartDate;
	}
	public String getParticipateEndDate() {
		return participateEndDate;
	}
	public void setParticipateEndDate(String participateEndDate) {
		this.participateEndDate = participateEndDate;
	}
	public String getUpdtApplicationStatus() {
		return updtApplicationStatus;
	}
	public void setUpdtApplicationStatus(String updtApplicationStatus) {
		this.updtApplicationStatus = updtApplicationStatus;
	}
	public String getUpdtApplicationName() {
		return updtApplicationName;
	}
	public void setUpdtApplicationName(String updtApplicationName) {
		this.updtApplicationName = updtApplicationName;
	}
	public String getTopCompanyName() {
		return topCompanyName;
	}
	public void setTopCompanyName(String topCompanyName) {
		this.topCompanyName = topCompanyName;
	}
	public String getTopAddress() {
		return topAddress;
	}
	public void setTopAddress(String topAddress) {
		this.topAddress = topAddress;
	}
	public String getTopHrdTraningName() {
		return topHrdTraningName;
	}
	public void setTopHrdTraningName(String topHrdTraningName) {
		this.topHrdTraningName = topHrdTraningName;
	}
	public String getTopHrdTraningNo() {
		return topHrdTraningNo;
	}
	public void setTopHrdTraningNo(String topHrdTraningNo) {
		this.topHrdTraningNo = topHrdTraningNo;
	}
	public String getTopStatusName() {
		return topStatusName;
	}
	public void setTopStatusName(String topStatusName) {
		this.topStatusName = topStatusName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRetunReason() {
		return retunReason;
	}
	public void setRetunReason(String retunReason) {
		this.retunReason = retunReason;
	}
	public String getMemTypeName() {
		return memTypeName;
	}
	public void setMemTypeName(String memTypeName) {
		this.memTypeName = memTypeName;
	}
	public String getOldYyyy() {
		return oldYyyy;
	}
	public void setOldYyyy(String oldYyyy) {
		this.oldYyyy = oldYyyy;
	}
	public String getOldTerm() {
		return oldTerm;
	}
	public void setOldTerm(String oldTerm) {
		this.oldTerm = oldTerm;
	}
	public String getOldSubjectCode() {
		return oldSubjectCode;
	}
	public void setOldSubjectCode(String oldSubjectCode) {
		this.oldSubjectCode = oldSubjectCode;
	}
	public String getOldSubClass() {
		return oldSubClass;
	}
	public void setOldSubClass(String oldSubClass) {
		this.oldSubClass = oldSubClass;
	}
	public String getCretGubun() {
		return cretGubun;
	}
	public void setCretGubun(String cretGubun) {
		this.cretGubun = cretGubun;
	}
	public String getScholarshipName() {
		return scholarshipName;
	}
	public void setScholarshipName(String scholarshipName) {
		this.scholarshipName = scholarshipName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getMemRegiNo1() {
		return memRegiNo1;
	}
	public void setMemRegiNo1(String memRegiNo1) {
		this.memRegiNo1 = memRegiNo1;
	}
	public String getMemRegiNo2() {
		return memRegiNo2;
	}
	public void setMemRegiNo2(String memRegiNo2) {
		this.memRegiNo2 = memRegiNo2;
	}
	public String getSubjectMappingCnt() {
		return subjectMappingCnt;
	}
	public void setSubjectMappingCnt(String subjectMappingCnt) {
		this.subjectMappingCnt = subjectMappingCnt;
	}
	public String[] getTraningProcessMappingIdArr() {
		return traningProcessMappingIdArr;
	}
	public void setTraningProcessMappingIdArr(String[] traningProcessMappingIdArr) {
		this.traningProcessMappingIdArr = traningProcessMappingIdArr;
	}
	public String getSearchYyyy() {
		return searchYyyy;
	}
	public void setSearchYyyy(String searchYyyy) {
		this.searchYyyy = searchYyyy;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getSearchSubjectCode() {
		return searchSubjectCode;
	}
	public void setSearchSubjectCode(String searchSubjectCode) {
		this.searchSubjectCode = searchSubjectCode;
	}
	public String getSearchSubClass() {
		return searchSubClass;
	}
	public void setSearchSubClass(String searchSubClass) {
		this.searchSubClass = searchSubClass;
	}
	public String getSearchSubjectTraningType() {
		return searchSubjectTraningType;
	}
	public void setSearchSubjectTraningType(String searchSubjectTraningType) {
		this.searchSubjectTraningType = searchSubjectTraningType;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}



	/**
	 * toString 메소드를 대치한다.
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
