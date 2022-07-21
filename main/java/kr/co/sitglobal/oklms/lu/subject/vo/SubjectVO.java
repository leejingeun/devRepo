
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
package kr.co.sitglobal.oklms.lu.subject.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2016. 12. 28.
 */
public class SubjectVO extends BaseVO implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 8037994394299812655L;
	/**
	 *
	 */

	private String yyyy                         ;	// 학년도
	private String term                         ; 	// 학기
	private String subjectCode                  ;   // 교과목코드
	private String subjectClass                 ;   // 분반
	private String subjectTraningType           ;   // OJT, OFF
	private String subjectTraningTypeName       ;   // 교과타입명
	private String subjectName                  ;   // 교과명
	private String departmentName               ;   // 학과명
	private String grade                        ;   // 운영학년(1/2/3/4)
	private String pointUseYn                   ;   // 학점사용 여부(학점형 : Y, 비학점 : N)
	private String point                        ;   // 이수학점
	private String subjectType                  ;   // 과정구분(일반 : NORMAL,고숙련 : HSKILL)
	private String subjectTypeName              ;   // 과정구분
	private String onlineType                   ;   // 온라인과정구분(없음:NONE,온라인:ONLINE,플립러닝:FLIPPED,블렌디드:BLENDED)
	private String onlineTypeName               ;   // 온라인과정구분명
	private String traningHour                  ;   // 훈련시간
	private String firstStudyYn                 ;   // 선수학습 여부(Y/N)
	private String insName                      ;   // 교수명
	private String insSeq                       ;   // 교수 고유번호
	private String cdpName                      ;   // 학과담당자명
	private String cdpSeq                       ;   // 학과담당자 고유번호
	private String stdName                      ;   // 학습근로자명
	private String stdSeq                       ;   // 학습근로자 고유번호
	private String gradeRatio		            ;   // 성적비율
	private String traningStDate			    ; // 훈련시작일

	private String estblYy ;
	private String estblSemstrCd ;
	private String courseNo ;
	private String partitnClasSeCd ;
	private String companyId;
	private String companyName;
	private String traningProcessId;

	private String deptCode;
	private String deptName;
	private String subjectGrade;

	private String tutNames;
	private String insNames;

	private String searchPeriodType;
	private String traningMonth;
	private String preSubjectCode;
	private String traningDate;
	private String weekCnt;
	private String planEvalOjtPrtYn;

	private String searchStatusType;

	public String getSearchStatusType() {
		return searchStatusType;
	}

	public void setSearchStatusType(String searchStatusType) {
		this.searchStatusType = searchStatusType;
	}

	public String getWeekCnt() {
		return weekCnt;
	}

	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}

	public String getTraningDate() {
		return traningDate;
	}

	public void setTraningDate(String traningDate) {
		this.traningDate = traningDate;
	}

	public String getPreSubjectCode() {
		return preSubjectCode;
	}

	public void setPreSubjectCode(String preSubjectCode) {
		this.preSubjectCode = preSubjectCode;
	}

	public String getTraningMonth() {
		return traningMonth;
	}

	public void setTraningMonth(String traningMonth) {
		this.traningMonth = traningMonth;
	}

	public String getTermName() {
		String result = "";
		if("1".equals(term)){
			result = "1학기";
		} else if("2".equals(term)){
			result = "2학기";
		} else if("3".equals(term)){
			result = "여름학기";
		} else {
			result = "겨울학기";
		}
		return result;
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

	public String getSearchPeriodType() {
		return searchPeriodType;
	}

	public void setSearchPeriodType(String searchPeriodType) {
		this.searchPeriodType = searchPeriodType;
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

	public String getSubjectClass() {
		return subjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}

	public String getSubjectTraningType() {
		return subjectTraningType;
	}

	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}

	public String getSubjectTraningTypeName() {
		return subjectTraningTypeName;
	}

	public void setSubjectTraningTypeName(String subjectTraningTypeName) {
		this.subjectTraningTypeName = subjectTraningTypeName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getSubjectTypeName() {
		return subjectTypeName;
	}

	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}

	public String getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}

	public String getOnlineTypeName() {
		return onlineTypeName;
	}

	public void setOnlineTypeName(String onlineTypeName) {
		this.onlineTypeName = onlineTypeName;
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

	public String getCdpName() {
		return cdpName;
	}

	public void setCdpName(String cdpName) {
		this.cdpName = cdpName;
	}

	public String getCdpSeq() {
		return cdpSeq;
	}

	public void setCdpSeq(String cdpSeq) {
		this.cdpSeq = cdpSeq;
	}

	public String getGradeRatio() {
		return gradeRatio;
	}

	public void setGradeRatio(String gradeRatio) {
		this.gradeRatio = gradeRatio;
	}

	public String getTraningStDate() {
		return traningStDate;
	}

	public void setTraningStDate(String traningStDate) {
		this.traningStDate = traningStDate;
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
	public String getPlanEvalOjtPrtYn() {
		return planEvalOjtPrtYn;
	}
	public void setPlanEvalOjtPrtYn(String planEvalOjtPrtYn) {
		this.planEvalOjtPrtYn = planEvalOjtPrtYn;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStdSeq() {
		return stdSeq;
	}
	public void setStdSeq(String stdSeq) {
		this.stdSeq = stdSeq;
	}

	public String toString() {
        final String NL  = "\n";
        final String TAB = "    ";

        String retValue = "";

        retValue = "Lu > Subject > Vo > SubjectVO ( "
            + super.toString() + NL
            + TAB + "yyyy = " + this.yyyy + NL
            + TAB + "term = " + this.term + NL
            + TAB + "subjectCode = " + this.subjectCode + NL
            + TAB + "subjectClass = " + this.subjectClass + NL
            + TAB + "subjectTraningType = " + this.subjectTraningType + NL
            + TAB + "subjectTraningTypeName = " + this.subjectTraningTypeName + NL
            + TAB + "subjectName = " + this.subjectName + NL
            + TAB + "departmentName = " + this.departmentName + NL
            + TAB + "grade = " + this.grade + NL
            + TAB + "pointUseYn = " + this.pointUseYn + NL
            + TAB + "point = " + this.point + NL
            + TAB + "subjectType = " + this.subjectType + NL
            + TAB + "subjectTypeName = " + this.subjectTypeName + NL
            + TAB + "onlineType = " + this.onlineType + NL
            + TAB + "onlineTypeName = " + this.onlineTypeName + NL
            + TAB + "traningHour = " + this.traningHour + NL
            + TAB + "firstStudyYn = " + this.firstStudyYn + NL
            + TAB + "insName = " + this.insName + NL
            + TAB + "insSeq = " + this.insSeq + NL
            + TAB + "cdpName = " + this.cdpName + NL
            + TAB + "cdpSeq = " + this.cdpSeq + NL
            + TAB + "gradeRatio = " + this.gradeRatio + NL
            + TAB + "traningStDate = " + this.traningStDate + NL
            + TAB + "traningStDate = " + this.companyId + NL
            + TAB + "traningStDate = " + this.companyName + NL
            + TAB + "traningStDate = " + this.traningProcessId + NL
            + " )";

        return retValue;
    }
}
