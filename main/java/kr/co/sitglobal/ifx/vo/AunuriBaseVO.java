package kr.co.sitglobal.ifx.vo;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AunuriBaseVO implements Serializable {

	private static final long serialVersionUID = 3242398069103733759L;
	/*
	 * 공통 컬럼
	 */
	private String year     	=""; //학년도
	private String term     	=""; //학기
	private String subjectCode  =""; //교과목코드
	private String subClass     =""; //분반
	private String weekCnt      =""; //학습주차
	
	/** login user session info */
    private String sessionMemId = "";
    private String sessionMemSeq = "";
    private String sessionMemName = "";
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
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getSessionMemId() {
		return sessionMemId;
	}
	public void setSessionMemId(String sessionMemId) {
		this.sessionMemId = sessionMemId;
	}
	public String getSessionMemSeq() {
		return sessionMemSeq;
	}
	public void setSessionMemSeq(String sessionMemSeq) {
		this.sessionMemSeq = sessionMemSeq;
	}
	public String getSessionMemName() {
		return sessionMemName;
	}
	public void setSessionMemName(String sessionMemName) {
		this.sessionMemName = sessionMemName;
	}
	public String getSessionMemEngName() {
		return sessionMemEngName;
	}
	public void setSessionMemEngName(String sessionMemEngName) {
		this.sessionMemEngName = sessionMemEngName;
	}
	public String getSessionMemType() {
		return sessionMemType;
	}
	public void setSessionMemType(String sessionMemType) {
		this.sessionMemType = sessionMemType;
	}
	public String getSessionAuthGroupId() {
		return sessionAuthGroupId;
	}
	public void setSessionAuthGroupId(String sessionAuthGroupId) {
		this.sessionAuthGroupId = sessionAuthGroupId;
	}
	public String getSessionEmail() {
		return sessionEmail;
	}
	public void setSessionEmail(String sessionEmail) {
		this.sessionEmail = sessionEmail;
	}
	public String getSessionLecId() {
		return sessionLecId;
	}
	public void setSessionLecId(String sessionLecId) {
		this.sessionLecId = sessionLecId;
	}
	public String getSessionPeriodId() {
		return sessionPeriodId;
	}
	public void setSessionPeriodId(String sessionPeriodId) {
		this.sessionPeriodId = sessionPeriodId;
	}
	public String getSessionClassId() {
		return sessionClassId;
	}
	public void setSessionClassId(String sessionClassId) {
		this.sessionClassId = sessionClassId;
	}
	public String getSessionPageDiv() {
		return sessionPageDiv;
	}
	public void setSessionPageDiv(String sessionPageDiv) {
		this.sessionPageDiv = sessionPageDiv;
	}
	public String getSessionLectureDiv() {
		return sessionLectureDiv;
	}
	public void setSessionLectureDiv(String sessionLectureDiv) {
		this.sessionLectureDiv = sessionLectureDiv;
	}
	public String getSessionLessonId() {
		return sessionLessonId;
	}
	public void setSessionLessonId(String sessionLessonId) {
		this.sessionLessonId = sessionLessonId;
	}
	public String getSessionLecStep() {
		return sessionLecStep;
	}
	public void setSessionLecStep(String sessionLecStep) {
		this.sessionLecStep = sessionLecStep;
	}
	public String getSessionMenuArea() {
		return sessionMenuArea;
	}
	public void setSessionMenuArea(String sessionMenuArea) {
		this.sessionMenuArea = sessionMenuArea;
	}
	public String getSessionSessionId() {
		return sessionSessionId;
	}
	public void setSessionSessionId(String sessionSessionId) {
		this.sessionSessionId = sessionSessionId;
	}
	public String getSessionOverlaploginYn() {
		return sessionOverlaploginYn;
	}
	public void setSessionOverlaploginYn(String sessionOverlaploginYn) {
		this.sessionOverlaploginYn = sessionOverlaploginYn;
	}
	public String getSessionLecTarget() {
		return sessionLecTarget;
	}
	public void setSessionLecTarget(String sessionLecTarget) {
		this.sessionLecTarget = sessionLecTarget;
	}
	public String getSessionLecTargetYear() {
		return sessionLecTargetYear;
	}
	public void setSessionLecTargetYear(String sessionLecTargetYear) {
		this.sessionLecTargetYear = sessionLecTargetYear;
	}
	public String getSessionFacilityNo() {
		return sessionFacilityNo;
	}
	public void setSessionFacilityNo(String sessionFacilityNo) {
		this.sessionFacilityNo = sessionFacilityNo;
	}
	public String getSessionFacilityName() {
		return sessionFacilityName;
	}
	public void setSessionFacilityName(String sessionFacilityName) {
		this.sessionFacilityName = sessionFacilityName;
	}
}
