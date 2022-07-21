package kr.co.sitglobal.oklms.lu.today.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class TodayVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4232557622609275212L;

	/**
	 * 
	 */
	private String subjectTraningType;
	private String subjectName;
	private String weekCnt;
	private String schoolYear;									   //학년
	private String yyyy; 										   //학년도
	private String term; 										   //학기
	private String subjectCode; 								   //과목
	private String subClass; 									   //반
		
	private String maxWeekCnt;  //마지막주차
	private String traningStDate; //기간 시작일
	private String traningEndDate;// 기간 종료일
	private String ncsUnitName; // 능력단위
	private String traningHour; // 주간훈련시간
	private String addYn; // 보강여부
	private String hrdTraningName; // 훈련명
	private String hrdTraningNo; // 훈련명
	private String updtApplicationStatus;
	private String statusName;
	private String updtApplicationName;
	
	private String noteCnt;
	private String memberCnt;

	private String reportId;
	private String reportName;
	private String score;
	private String submitStartDate;
	private String submitEndDate;
	private String totCnt;
	private String scoreCnt;
	private String lecCnt;
	private String submitDate;
	private String submitType;
	private String projectEndDate;
	private String projectName;
	
	private String teamTotCnt;
	private String teamScoreCnt;

	private String endDate;
	private String title;
	
	private String startTime;
	private String endTime;
	private String workProofId;
	private String state;
	private String atchFileIdInc;
	private String atchFileIdRec;
	private String atchFileIdDoc;
	private String insuranceYn;
	private String receiptYn;
	
	private String companyCnt;
	private String incCnt;
	private String recCnt;
	private String appCnt;
	private String companyName;
	private String companyId;
	private String memtype;
	private String memName;
	
	private String mm;
	private String traningProcessManageId;
	private String traningProcessId;
	private String traningProcessName;
	private String activityId;
	private String bigoCot;
	private String bigoHrd;
	private String discussId;
	private String interviewCnt;
	private String weekStDate;
	private String weekEdDate;
	private String onAttend;
	private String attendProgress;
	private String rate;
	
	private String attCnt;
	private String ojtState;
	private String stateOff;
	private String stateOjt;
	
	public String getStateOff() {
		return stateOff;
	}
	public void setStateOff(String stateOff) {
		this.stateOff = stateOff;
	}
	public String getStateOjt() {
		return stateOjt;
	}
	public void setStateOjt(String stateOjt) {
		this.stateOjt = stateOjt;
	}
	public String getOjtState() {
		return ojtState;
	}
	public void setOjtState(String ojtState) {
		this.ojtState = ojtState;
	}
	public String getTraningProcessName() {
		return traningProcessName;
	}
	public void setTraningProcessName(String traningProcessName) {
		this.traningProcessName = traningProcessName;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getBigoCot() {
		return bigoCot;
	}
	public void setBigoCot(String bigoCot) {
		this.bigoCot = bigoCot;
	}
	public String getBigoHrd() {
		return bigoHrd;
	}
	public void setBigoHrd(String bigoHrd) {
		this.bigoHrd = bigoHrd;
	}
	public String getAttCnt() {
		return attCnt;
	}
	public void setAttCnt(String attCnt) {
		this.attCnt = attCnt;
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
	public String getOnAttend() {
		return onAttend;
	}
	public void setOnAttend(String onAttend) {
		this.onAttend = onAttend;
	}
	public String getAttendProgress() {
		return attendProgress;
	}
	public void setAttendProgress(String attendProgress) {
		this.attendProgress = attendProgress;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getLecCnt() {
		return lecCnt;
	}
	public void setLecCnt(String lecCnt) {
		this.lecCnt = lecCnt;
	}
	public String getDiscussId() {
		return discussId;
	}
	public void setDiscussId(String discussId) {
		this.discussId = discussId;
	}
	public String getInterviewCnt() {
		return interviewCnt;
	}
	public void setInterviewCnt(String interviewCnt) {
		this.interviewCnt = interviewCnt;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getTraningProcessManageId() {
		return traningProcessManageId;
	}
	public void setTraningProcessManageId(String traningProcessManageId) {
		this.traningProcessManageId = traningProcessManageId;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getMemtype() {
		return memtype;
	}
	public void setMemtype(String memtype) {
		this.memtype = memtype;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getHrdTraningNo() {
		return hrdTraningNo;
	}
	public void setHrdTraningNo(String hrdTraningNo) {
		this.hrdTraningNo = hrdTraningNo;
	}
	public String getUpdtApplicationStatus() {
		return updtApplicationStatus;
	}
	public void setUpdtApplicationStatus(String updtApplicationStatus) {
		this.updtApplicationStatus = updtApplicationStatus;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getUpdtApplicationName() {
		return updtApplicationName;
	}
	public void setUpdtApplicationName(String updtApplicationName) {
		this.updtApplicationName = updtApplicationName;
	}
	public String getCompanyCnt() {
		return companyCnt;
	}
	public void setCompanyCnt(String companyCnt) {
		this.companyCnt = companyCnt;
	}
	public String getIncCnt() {
		return incCnt;
	}
	public void setIncCnt(String incCnt) {
		this.incCnt = incCnt;
	}
	public String getRecCnt() {
		return recCnt;
	}
	public void setRecCnt(String recCnt) {
		this.recCnt = recCnt;
	}
	public String getAppCnt() {
		return appCnt;
	}
	public void setAppCnt(String appCnt) {
		this.appCnt = appCnt;
	}
	public String getInsuranceYn() {
		return insuranceYn;
	}
	public void setInsuranceYn(String insuranceYn) {
		this.insuranceYn = insuranceYn;
	}
	public String getReceiptYn() {
		return receiptYn;
	}
	public void setReceiptYn(String receiptYn) {
		this.receiptYn = receiptYn;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWorkProofId() {
		return workProofId;
	}
	public void setWorkProofId(String workProofId) {
		this.workProofId = workProofId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAtchFileIdInc() {
		return atchFileIdInc;
	}
	public void setAtchFileIdInc(String atchFileIdInc) {
		this.atchFileIdInc = atchFileIdInc;
	}
	public String getAtchFileIdRec() {
		return atchFileIdRec;
	}
	public void setAtchFileIdRec(String atchFileIdRec) {
		this.atchFileIdRec = atchFileIdRec;
	}
	public String getAtchFileIdDoc() {
		return atchFileIdDoc;
	}
	public void setAtchFileIdDoc(String atchFileIdDoc) {
		this.atchFileIdDoc = atchFileIdDoc;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTeamTotCnt() {
		return teamTotCnt;
	}
	public void setTeamTotCnt(String teamTotCnt) {
		this.teamTotCnt = teamTotCnt;
	}
	public String getTeamScoreCnt() {
		return teamScoreCnt;
	}
	public void setTeamScoreCnt(String teamScoreCnt) {
		this.teamScoreCnt = teamScoreCnt;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getSubmitType() {
		return submitType;
	}
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSubmitStartDate() {
		return submitStartDate;
	}
	public void setSubmitStartDate(String submitStartDate) {
		this.submitStartDate = submitStartDate;
	}
	public String getSubmitEndDate() {
		return submitEndDate;
	}
	public void setSubmitEndDate(String submitEndDate) {
		this.submitEndDate = submitEndDate;
	}
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getScoreCnt() {
		return scoreCnt;
	}
	public void setScoreCnt(String scoreCnt) {
		this.scoreCnt = scoreCnt;
	}
	public String getNoteCnt() {
		return noteCnt;
	}
	public void setNoteCnt(String noteCnt) {
		this.noteCnt = noteCnt;
	}
	public String getMemberCnt() {
		return memberCnt;
	}
	public void setMemberCnt(String memberCnt) {
		this.memberCnt = memberCnt;
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
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
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
	public String getMaxWeekCnt() {
		return maxWeekCnt;
	}
	public void setMaxWeekCnt(String maxWeekCnt) {
		this.maxWeekCnt = maxWeekCnt;
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
	public String getNcsUnitName() {
		return ncsUnitName;
	}
	public void setNcsUnitName(String ncsUnitName) {
		this.ncsUnitName = ncsUnitName;
	}
	public String getTraningHour() {
		return traningHour;
	}
	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}
	public String getAddYn() {
		return addYn;
	}
	public void setAddYn(String addYn) {
		this.addYn = addYn;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	
	
	
	
}
