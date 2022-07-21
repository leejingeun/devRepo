package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;
import java.util.List;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;

/**
 *훈련지원> 훈련일지
 * @author user
 *
 */
public class TraningNoteVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3556717779113072094L;
	/**
	 * 기간 시작일traniningNoteDetailId
	 */
	private String traningStDate;
	/**
	 * 기간 종료일
	 */
	private String traningEndDate;
	/***
	 * 능력단위
	 */
	private String ncsUnitName;
	/**
	 * 능력 단위 요소
	 */
	private String ncsElemName;
	/**
	 * 주간훈련시간
	 */
	private String traningHour;
	/**
	 * 학습주차
	 */
	private String weekCnt;
	private String nowWeekCnt;	
	/**
	 * 학습 코드
	 */
	private String subjectCode;
	/**
	 * 학기
	 */
	private String term;
	/**
	 * 학기 녀도
	 */
	private String yyyy;
	/**
	 * 반
	 */ 
	private String classId;

	private String lessonId;

	private String memSeq;
	
	private String traningNoteMemberSeqs;
	private String traningNoteMemberIds;
	private String traningNoteMemberNames;
	private String companyId;

	private String studyTime;
	
	private String addStudyTime;
	
	private String studyTimeOff;
	
	private String addStudyTimeOff;

	private String studyTimeOjt;
	
	private String addStudyTimeOjt;
	
	private String studyPlanTime;

	private String studyTimePlan;
	
	private String achievement;

	private String bigo;

	private String[] studyTimeArr;

	private String[] bigoArr;

	//총평
	private String review;

	private String traningStHour;

	private String attendProgress;

	private String companyName;

	private String memId;

	private String[] memIdArr;

	private String memName;

	private String dayOfWeek;

	private String traningDate;

	private String traningMonth;
	
	private String traningStMin;

	private String traningEdHour;

	private String traningEdMin;

	private String timeHour;

	private String timeMin;

	private String traningSt;

	private String traningEd;

	private String traniningNoteMasterId;

	private String traniningNoteDetailId;

	private String[] traniningNoteMasterIdArr;

	private String[] traniningNoteDetailIdArr;

	private String achievementEnrichment;

	private String subjectTraningType;

	private String subjectName;

	private String current;

	private String maxClasss;

	private String traningType;

	private String state;
	
	private String addState;

	private String ojtState;
	
	private String ojtAddState;
	
	private String lessonCn;

	private String [] score;
	
	private String hrdTraningName;
	
	private String traningProcessId;
	
	private String llCount;
	
	private String[] achievementArr;
	
	private String statusType;	
	
	private String departmentName;
	
	private String prtName;
	
	private String cotName;
	
	private String lastDate;
	private String mm;
	
	private String sumTimeOff;
	private String sumTimeOjt;
	private String addSumTimeOff;
	private String addSumTimeOjt;
	private String totTimeOff;
	private String totTimeOjt;
	//반려코멘트
	private String returnComment;
	
	public String getReturnComment() {
		return returnComment;
	}
	public void setReturnComment(String returnComment) {
		this.returnComment = returnComment;
	}
	public String getSumTimeOff() {
		return sumTimeOff;
	}
	public void setSumTimeOff(String sumTimeOff) {
		this.sumTimeOff = sumTimeOff;
	}
	public String getSumTimeOjt() {
		return sumTimeOjt;
	}
	public void setSumTimeOjt(String sumTimeOjt) {
		this.sumTimeOjt = sumTimeOjt;
	}
	public String getAddSumTimeOff() {
		return addSumTimeOff;
	}
	public void setAddSumTimeOff(String addSumTimeOff) {
		this.addSumTimeOff = addSumTimeOff;
	}
	public String getAddSumTimeOjt() {
		return addSumTimeOjt;
	}
	public void setAddSumTimeOjt(String addSumTimeOjt) {
		this.addSumTimeOjt = addSumTimeOjt;
	}
	public String getTotTimeOff() {
		return totTimeOff;
	}
	public void setTotTimeOff(String totTimeOff) {
		this.totTimeOff = totTimeOff;
	}
	public String getTotTimeOjt() {
		return totTimeOjt;
	}
	public void setTotTimeOjt(String totTimeOjt) {
		this.totTimeOjt = totTimeOjt;
	}
	public String getTraningMonth() {
		return traningMonth;
	}
	public void setTraningMonth(String traningMonth) {
		this.traningMonth = traningMonth;
	}
	public String getStudyTimeOff() {
		return studyTimeOff;
	}
	public void setStudyTimeOff(String studyTimeOff) {
		this.studyTimeOff = studyTimeOff;
	}
	public String getAddStudyTimeOff() {
		return addStudyTimeOff;
	}
	public void setAddStudyTimeOff(String addStudyTimeOff) {
		this.addStudyTimeOff = addStudyTimeOff;
	}
	public String getStudyTimeOjt() {
		return studyTimeOjt;
	}
	public void setStudyTimeOjt(String studyTimeOjt) {
		this.studyTimeOjt = studyTimeOjt;
	}
	public String getAddStudyTimeOjt() {
		return addStudyTimeOjt;
	}
	public void setAddStudyTimeOjt(String addStudyTimeOjt) {
		this.addStudyTimeOjt = addStudyTimeOjt;
	}
	private List<List<TraningNoteVO>> traningNoteVOArr;	
 
	public List<List<TraningNoteVO>> getTraningNoteVOArr() {
		return traningNoteVOArr;
	}
	public void setTraningNoteVOArr(List<List<TraningNoteVO>> traningNoteVOArr) {
		this.traningNoteVOArr = traningNoteVOArr;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getAddStudyTime() {
		return addStudyTime;
	}
	public void setAddStudyTime(String addStudyTime) {
		this.addStudyTime = addStudyTime;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPrtName() {
		return prtName;
	}
	public void setPrtName(String prtName) {
		this.prtName = prtName;
	}
	public String getCotName() {
		return cotName;
	}
	public void setCotName(String cotName) {
		this.cotName = cotName;
	}
	public String getTraningNoteMemberIds() {
		return traningNoteMemberIds;
	}
	public void setTraningNoteMemberIds(String traningNoteMemberIds) {
		this.traningNoteMemberIds = traningNoteMemberIds;
	}
	public String getTraningNoteMemberNames() {
		return traningNoteMemberNames;
	}
	public void setTraningNoteMemberNames(String traningNoteMemberNames) {
		this.traningNoteMemberNames = traningNoteMemberNames;
	}
	public String getTraningNoteMemberSeqs() {
		return traningNoteMemberSeqs;
	}
	public void setTraningNoteMemberSeqs(String traningNoteMemberSeqs) {
		this.traningNoteMemberSeqs = traningNoteMemberSeqs;
	}
	public String getStudyPlanTime() {
		return studyPlanTime;
	}
	public void setStudyPlanTime(String studyPlanTime) {
		this.studyPlanTime = studyPlanTime;
	}
	public String getStatusType() {
		return statusType;
	}
	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}
	public String[] getAchievementArr() {
		return achievementArr;
	}
	public void setAchievementArr(String[] achievementArr) {
		this.achievementArr = achievementArr;
	}
	public String getStudyTimePlan() {
		return studyTimePlan;
	}
	public void setStudyTimePlan(String studyTimePlan) {
		this.studyTimePlan = studyTimePlan;
	}
	public String getLlCount() {
		return llCount;
	}
	public void setLlCount(String llCount) {
		this.llCount = llCount;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getOjtState() {
		return ojtState;
	}
	public void setOjtState(String ojtState) {
		this.ojtState = ojtState;
	}
	public String getOjtAddState() {
		return ojtAddState;
	}
	public void setOjtAddState(String ojtAddState) {
		this.ojtAddState = ojtAddState;
	}
	public String getNowWeekCnt() {
		return nowWeekCnt;
	}
	public void setNowWeekCnt(String nowWeekCnt) {
		this.nowWeekCnt = nowWeekCnt;
	}
	public String getAddState() {
		return addState;
	}
	public void setAddState(String addState) {
		this.addState = addState;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String[] getScore() {
		return score;
	}
	public void setScore(String[] score) {
		this.score = score;
	}
	public String getLessonCn() {
		return lessonCn;
	}
	public void setLessonCn(String lessonCn) {
		this.lessonCn = lessonCn;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getTraningType() {
		return traningType;
	}
	public void setTraningType(String traningType) {
		this.traningType = traningType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMaxClasss() {
		return maxClasss;
	}
	public void setMaxClasss(String maxClasss) {
		this.maxClasss = maxClasss;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
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
	public String getAchievementEnrichment() {
		return achievementEnrichment;
	}
	public void setAchievementEnrichment(String achievementEnrichment) {
		this.achievementEnrichment = achievementEnrichment;
	}
	public String[] getTraniningNoteMasterIdArr() {
		return traniningNoteMasterIdArr;
	}
	public void setTraniningNoteMasterIdArr(String[] traniningNoteMasterIdArr) {
		this.traniningNoteMasterIdArr = traniningNoteMasterIdArr;
	}
	public String[] getTraniningNoteDetailIdArr() {
		return traniningNoteDetailIdArr;
	}
	public void setTraniningNoteDetailIdArr(String[] traniningNoteDetailIdArr) {
		this.traniningNoteDetailIdArr = traniningNoteDetailIdArr;
	}
	private String addyn;

	private String feedback;

	private String startTime;

	private String finishTime;

	private String memNm;

	private String[] memNmArr;

	private String planTime;

	private String weekId;

	private String timeId;

	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}
	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}
	public String getStudyTime() {
		return studyTime;
	}
	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getLevel() {		
		return BizUtil.getLevel(memId);
	}
	public String[] getMemIdArr() {
		return memIdArr;
	}
	public void setMemIdArr(String[] memIdArr) {
		this.memIdArr = memIdArr;
	}
	public String getMemNm() {
		return memNm;
	}
	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}
	public String[] getMemNmArr() {
		return memNmArr;
	}
	public void setMemNmArr(String[] memNmArr) {
		this.memNmArr = memNmArr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	private String studyDate;

	public String getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String[] getStudyTimeArr() {
		return studyTimeArr;
	}
	public void setStudyTimeArr(String[] studyTimeArr) {
		this.studyTimeArr = studyTimeArr;
	}
	public String[] getBigoArr() {
		return bigoArr;
	}
	public void setBigoArr(String[] bigoArr) {
		this.bigoArr = bigoArr;
	}
	public String getAddyn() {
		return addyn;
	}
	public void setAddyn(String addyn) {
		this.addyn = addyn;
	}
	public String getTraniningNoteMasterId() {
		return traniningNoteMasterId;
	}
	public void setTraniningNoteMasterId(String traniningNoteMasterId) {
		this.traniningNoteMasterId = traniningNoteMasterId;
	}
	public String getTraniningNoteDetailId() {
		return traniningNoteDetailId;
	}
	public void setTraniningNoteDetailId(String traniningNoteDetailId) {
		this.traniningNoteDetailId = traniningNoteDetailId;
	}
	public String getTraningSt() {
		return traningSt;
	}
	public void setTraningSt(String traningSt) {
		this.traningSt = traningSt;
	}
	public String getTraningEd() {
		return traningEd;
	}
	public void setTraningEd(String traningEd) {
		this.traningEd = traningEd;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getTraningDate() {
		return traningDate;
	}
	public void setTraningDate(String traningDate) {
		this.traningDate = traningDate;
	}
	public String getTraningStMin() {
		return traningStMin;
	}
	public void setTraningStMin(String traningStMin) {
		this.traningStMin = traningStMin;
	}
	public String getTraningEdHour() {
		return traningEdHour;
	}
	public void setTraningEdHour(String traningEdHour) {
		this.traningEdHour = traningEdHour;
	}
	public String getTraningEdMin() {
		return traningEdMin;
	}
	public void setTraningEdMin(String traningEdMin) {
		this.traningEdMin = traningEdMin;
	}
	public String getTimeHour() {
		return timeHour;
	}
	public void setTimeHour(String timeHour) {
		this.timeHour = timeHour;
	}
	public String getTimeMin() {
		return timeMin;
	}
	public void setTimeMin(String timeMin) {
		this.timeMin = timeMin;
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
	public String getLessonId() {
		return lessonId;
	}
	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getTraningStHour() {
		return traningStHour;
	}
	public void setTraningStHour(String traningStHour) {
		this.traningStHour = traningStHour;
	}
	public String getAttendProgress() {
		return attendProgress;
	}
	public void setAttendProgress(String attendProgress) {
		this.attendProgress = attendProgress;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
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
	public String getNcsElemName() {
		return ncsElemName;
	}
	public void setNcsElemName(String ncsElemName) {
		this.ncsElemName = ncsElemName;
	}
	public String getTraningHour() {
		return traningHour;
	}
	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}


}
