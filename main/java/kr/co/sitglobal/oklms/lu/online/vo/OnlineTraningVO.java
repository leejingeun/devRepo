package kr.co.sitglobal.oklms.lu.online.vo;

import java.io.Serializable;


import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OnlineTraningVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2268120446397826748L;
	/**
	 * 
	 */
	private String subjOnStandSeq;
	private String onlineSubjectId;
	private String yyyy;
	private String term;
	private String subjectCode;
	private String subjectClass;
	private String startDate;
	private String endDate;
	private String weekStudyType;
	private String weekStudy;
	private String cutProgress;
	private String attendProgress;
	private String attendReflectType;
	private String attendNextDay;
	private String scheduleTime;
	private String progressAttendTypeCode;
	private String attCnt;
	
	
	public String getAttCnt() {
		return attCnt;
	}



	public void setAttCnt(String attCnt) {
		this.attCnt = attCnt;
	}



	public String getSubjOnStandSeq() {
		return subjOnStandSeq;
	}



	public void setSubjOnStandSeq(String subjOnStandSeq) {
		this.subjOnStandSeq = subjOnStandSeq;
	}
	
	
	public String getOnlineSubjectId() {
		return onlineSubjectId;
	}



	public void setOnlineSubjectId(String onlineSubjectId) {
		this.onlineSubjectId = onlineSubjectId;
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



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getWeekStudyType() {
		return weekStudyType;
	}



	public void setWeekStudyType(String weekStudyType) {
		this.weekStudyType = weekStudyType;
	}



	public String getWeekStudy() {
		return weekStudy;
	}



	public void setWeekStudy(String weekStudy) {
		this.weekStudy = weekStudy;
	}



	public String getCutProgress() {
		return cutProgress;
	}



	public void setCutProgress(String cutProgress) {
		this.cutProgress = cutProgress;
	}



	public String getAttendProgress() {
		return attendProgress;
	}



	public void setAttendProgress(String attendProgress) {
		this.attendProgress = attendProgress;
	}



	public String getAttendReflectType() {
		return attendReflectType;
	}



	public void setAttendReflectType(String attendReflectType) {
		this.attendReflectType = attendReflectType;
	}



	public String getAttendNextDay() {
		return attendNextDay;
	}



	public void setAttendNextDay(String attendNextDay) {
		this.attendNextDay = attendNextDay;
	}



	public String getScheduleTime() {
		return scheduleTime;
	}



	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}



	public String getProgressAttendTypeCode() {
		return progressAttendTypeCode;
	}



	public void setProgressAttendTypeCode(String progressAttendTypeCode) {
		this.progressAttendTypeCode = progressAttendTypeCode;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	

}
