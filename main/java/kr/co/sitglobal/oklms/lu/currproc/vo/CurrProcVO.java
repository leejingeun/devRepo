
/*******************************************************************************
 * COPYRIGHT(C) 2016 WIZI LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of WIZI LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 28.        First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.currproc.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2016. 12. 28.
 */
public class CurrProcVO extends BaseVO implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 6602079789359019675L;
	
	private String yyyy; 						//학년도
	private String term; 						//학기
	private String subjectCode; 				//교과목코드
	private String subClass; 					//분반
	private String subjectClass; 					//분반
	private String subjectTraningType; 			//OJT, OFF
	private String subjectName; 				//개설교과명
	private String grade; 						//운영학년(1/2/3/4)
	private String pointUseYn; 					//학점사용 여부(학점형 : Y, 비학점 : N)
	private String point; 						//이수학점
	private String subjectType; 				//과정구분(일반 : NORMAL,고숙련 : HSKILL)
	private String onlineType; 					//온라인과정구분(없음:NONE,온라인:ONLINE,플립러닝:FLIPPED,블렌디드:BLENDED)
	private String deleteYn; 					//삭제여부
	private String weekCnt;	  		            //학습주차
	private String departmentName;             //학과명
	private String etc1;                        //기타1   
	private String companyId;					//기업아이디
	private String companyName;                 //기업명
	private String traningProcessId;			//훈련과정아이디
	private String traningProcessMappingId;  	//훈련과정매핑아이디
	private String hrdTraningNo; 				//HRD-NET 훈련과정번호
	private String hrdTraningName; 				//HRD-NET 훈련과정명
	private String pointUseYnName; 				//학점사용 여부명
	private String onlineTypeName; 				//온라인과정구분명
	private String firstLecLessionAt; 			//선수여부
	private String count;
	private String compCount;					// 수강생 기업 그룹 카운트
	private String memSeq;
	private String memId;
	private String memName;
	private String traningHour;
	private String firstStudyYn;
	private String insName;					//담당교수명
	private String insSeq;					//담당교수
	private String nowDate;
	private String gradeRatio;
	
	
	//검색필드
	private String searchSubjectName; 			//개설교과명 검색
	private String searchSubjectCode; 			//교과목코드 검색
	private String searchYyyy; 					//학년도 검색
	private String searchYyyyCd; 			    //학년도코드 검색
	private String searchTerm; 					//학기 검색
	private String searchTermCd; 				//학기코드 검색
	private String searchSubClass; 				//분반 검색
	private String searchSubClassCd; 			//분반코드 검색
	private String searchDepartmentName;        //학과명
	
	private String subjectTraningTypeName       ;   // 교과타입명    
	private String subjectTypeName              ;   // 과정구분  
	private String estblYy ;
	private String estblSemstrCd ;
	private String courseNo ;
	private String partitnClasSeCd ;
	private String tutNames;
	private String insNames;
	private String deptCode;
	private String deptName;
	private String subjectGrade;
	
	public String getSubjectTraningTypeName() {
		return subjectTraningTypeName;
	}
	public void setSubjectTraningTypeName(String subjectTraningTypeName) {
		this.subjectTraningTypeName = subjectTraningTypeName;
	}
	public String getSubjectTypeName() {
		return subjectTypeName;
	}
	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}
	public String getEstblYy() {
		return estblYy;
	}
	public void setEstblYy(String estblYy) {
		this.estblYy = estblYy;
	}
	public String getEstblSemstrCd() {
		return estblSemstrCd;
	}
	public void setEstblSemstrCd(String estblSemstrCd) {
		this.estblSemstrCd = estblSemstrCd;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getPartitnClasSeCd() {
		return partitnClasSeCd;
	}
	public void setPartitnClasSeCd(String partitnClasSeCd) {
		this.partitnClasSeCd = partitnClasSeCd;
	}
	public String getTutNames() {
		return tutNames;
	}
	public void setTutNames(String tutNames) {
		this.tutNames = tutNames;
	}
	public String getInsNames() {
		return insNames;
	}
	public void setInsNames(String insNames) {
		this.insNames = insNames;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	
	public String getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}
	
	public String getGradeRatio() {
		return gradeRatio;
	}
	public void setGradeRatio(String gradeRatio) {
		this.gradeRatio = gradeRatio;
	}
	public String getTraningHour() {
		return traningHour;
	}
	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}
	public String getFirstStudyYn() {
		return firstStudyYn;
	}
	public void setFirstStudyYn(String firstStudyYn) {
		this.firstStudyYn = firstStudyYn;
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
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	public String getSubjectTraningType() {
		return subjectTraningType;
	}
	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPointUseYn() {
		return pointUseYn;
	}
	public void setPointUseYn(String pointUseYn) {
		this.pointUseYn = pointUseYn;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getOnlineType() {
		return onlineType;
	}
	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEtc1() {
		return etc1;
	}
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getPointUseYnName() {
		return pointUseYnName;
	}
	public void setPointUseYnName(String pointUseYnName) {
		this.pointUseYnName = pointUseYnName;
	}
	public String getOnlineTypeName() {
		return onlineTypeName;
	}
	public void setOnlineTypeName(String onlineTypeName) {
		this.onlineTypeName = onlineTypeName;
	}
	public String getFirstLecLessionAt() {
		return firstLecLessionAt;
	}
	public void setFirstLecLessionAt(String firstLecLessionAt) {
		this.firstLecLessionAt = firstLecLessionAt;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getInsSeq() {
		return insSeq;
	}
	public void setInsSeq(String insSeq) {
		this.insSeq = insSeq;
	}
	
	
	public String getSearchSubjectName() {
		return searchSubjectName;
	}
	public void setSearchSubjectName(String searchSubjectName) {
		this.searchSubjectName = searchSubjectName;
	}
	public String getSearchSubjectCode() {
		return searchSubjectCode;
	}
	public void setSearchSubjectCode(String searchSubjectCode) {
		this.searchSubjectCode = searchSubjectCode;
	}
	public String getSearchYyyy() {
		return searchYyyy;
	}
	public void setSearchYyyy(String searchYyyy) {
		this.searchYyyy = searchYyyy;
	}
	public String getSearchYyyyCd() {
		return searchYyyyCd;
	}
	public void setSearchYyyyCd(String searchYyyyCd) {
		this.searchYyyyCd = searchYyyyCd;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getSearchTermCd() {
		return searchTermCd;
	}
	public void setSearchTermCd(String searchTermCd) {
		this.searchTermCd = searchTermCd;
	}
	public String getSearchSubClass() {
		return searchSubClass;
	}
	public void setSearchSubClass(String searchSubClass) {
		this.searchSubClass = searchSubClass;
	}
	public String getSearchSubClassCd() {
		return searchSubClassCd;
	}
	public void setSearchSubClassCd(String searchSubClassCd) {
		this.searchSubClassCd = searchSubClassCd;
	}
	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}
	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
	
	public String getCompCount() {
		return compCount;
	}
	public void setCompCount(String compCount) {
		this.compCount = compCount;
	}
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	
}
