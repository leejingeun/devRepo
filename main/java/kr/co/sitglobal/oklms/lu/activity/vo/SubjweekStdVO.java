package kr.co.sitglobal.oklms.lu.activity.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class SubjweekStdVO  extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7370555679841948577L;
	
	
	private String yyyy;
	private String term;
	private String subjectCode;
	private String classId;
	private String weekCnt;
	
	private String ncsUnitId;
	private String ncsUnitName;
	private String ncsElemId;
	private String ncsElemName;
	private String contentName;
	private String weekStDate;
	private String weekEdDate;
	private String weekMin;

	private String allTimeHour;
	private String traningStDate;
	private String traningEndDate;
	private String traningHour;
	private String weekId;
	private String subjectName;
	private String subjectTraningType;
	
	private String weekTimeHour;
	private String weekTimeMin;
	
	private String ojtCount;
	private String ojtCountSubmit;
	private String ojtWorkCount;
	private String offCount;
	private String offCountSubmit;
	private String offWorkCount;
	private String companyId;
	private String traningProcessId;
	private String traningType;
	private String weekWorkTimeHour;
	private String weekWorkAchievement;
	private String weekAddTimeHour;
	private String weekAddAchievement;
	private String activityNoteId;
	private String memName;
	private String memSeq;
	private String lessonId;
	
	private String content;
	private String reqContent;
	private String atchFileId;
	private String tableCnt;
	private String state;
	private String traningDate;
	
	public String getTraningDate() {
		return traningDate;
	}
	public void setTraningDate(String traningDate) {
		this.traningDate = traningDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOjtCountSubmit() {
		return ojtCountSubmit;
	}
	public void setOjtCountSubmit(String ojtCountSubmit) {
		this.ojtCountSubmit = ojtCountSubmit;
	}
	public String getOffCountSubmit() {
		return offCountSubmit;
	}
	public void setOffCountSubmit(String offCountSubmit) {
		this.offCountSubmit = offCountSubmit;
	}
	public String getTableCnt() {
		return tableCnt;
	}
	public void setTableCnt(String tableCnt) {
		this.tableCnt = tableCnt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReqContent() {
		return reqContent;
	}
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getAllTimeHour() {
		return allTimeHour;
	}
	public void setAllTimeHour(String allTimeHour) {
		this.allTimeHour = allTimeHour;
	}
	public String getWeekWorkTimeHour() {
		return weekWorkTimeHour;
	}
	public void setWeekWorkTimeHour(String weekWorkTimeHour) {
		this.weekWorkTimeHour = weekWorkTimeHour;
	}
	public String getWeekWorkAchievement() {
		return weekWorkAchievement;
	}
	public void setWeekWorkAchievement(String weekWorkAchievement) {
		this.weekWorkAchievement = weekWorkAchievement;
	}
	public String getWeekAddTimeHour() {
		return weekAddTimeHour;
	}
	public void setWeekAddTimeHour(String weekAddTimeHour) {
		this.weekAddTimeHour = weekAddTimeHour;
	}
	public String getWeekAddAchievement() {
		return weekAddAchievement;
	}
	public void setWeekAddAchievement(String weekAddAchievement) {
		this.weekAddAchievement = weekAddAchievement;
	}
	public String getActivityNoteId() {
		return activityNoteId;
	}
	public void setActivityNoteId(String activityNoteId) {
		this.activityNoteId = activityNoteId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getTraningType() {
		return traningType;
	}
	public void setTraningType(String traningType) {
		this.traningType = traningType;
	}
	public String getOjtCount() {
		return ojtCount;
	}
	public void setOjtCount(String ojtCount) {
		this.ojtCount = ojtCount;
	}
	public String getOjtWorkCount() {
		return ojtWorkCount;
	}
	public void setOjtWorkCount(String ojtWorkCount) {
		this.ojtWorkCount = ojtWorkCount;
	}
	public String getOffCount() {
		return offCount;
	}
	public void setOffCount(String offCount) {
		this.offCount = offCount;
	}
	public String getOffWorkCount() {
		return offWorkCount;
	}
	public void setOffWorkCount(String offWorkCount) {
		this.offWorkCount = offWorkCount;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getWeekTimeHour() {
		return weekTimeHour;
	}
	public void setWeekTimeHour(String weekTimeHour) {
		this.weekTimeHour = weekTimeHour;
	}
	public String getWeekTimeMin() {
		return weekTimeMin;
	}
	public void setWeekTimeMin(String weekTimeMin) {
		this.weekTimeMin = weekTimeMin;
	}
	public String getTraningStDate() {
		return traningStDate;
	}
	public void setTraningStDate(String traningStDate) {
		this.traningStDate = traningStDate;
	}
	public String getTraningEndDate() {
		return traningEndDate;
	}
	public void setTraningEndDate(String traningEndDate) {
		this.traningEndDate = traningEndDate;
	}
	public String getTraningHour() {
		return traningHour;
	}
	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}
	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectTraningType() {
		return subjectTraningType;
	}
	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
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
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getNcsUnitId() {
		return ncsUnitId;
	}
	public void setNcsUnitId(String ncsUnitId) {
		this.ncsUnitId = ncsUnitId;
	}
	public String getNcsUnitName() {
		return ncsUnitName;
	}
	public void setNcsUnitName(String ncsUnitName) {
		this.ncsUnitName = ncsUnitName;
	}
	public String getNcsElemId() {
		return ncsElemId;
	}
	public void setNcsElemId(String ncsElemId) {
		this.ncsElemId = ncsElemId;
	}
	public String getNcsElemName() {
		return ncsElemName;
	}
	public void setNcsElemName(String ncsElemName) {
		this.ncsElemName = ncsElemName;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getWeekStDate() {
		return weekStDate;
	}
	public void setWeekStDate(String weekStDate) {
		this.weekStDate = weekStDate;
	}
	public String getWeekEdDate() {
		return weekEdDate;
	}
	public void setWeekEdDate(String weekEdDate) {
		this.weekEdDate = weekEdDate;
	}
	public String getWeekMin() {
		return weekMin;
	}
	public void setWeekMin(String weekMin) {
		this.weekMin = weekMin;
	}	
	
	
	
}
