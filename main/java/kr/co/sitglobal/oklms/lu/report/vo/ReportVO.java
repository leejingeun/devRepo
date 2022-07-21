package kr.co.sitglobal.oklms.lu.report.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class ReportVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String reportId;
	private String yyyy;
	private String term;
	private String subjectCode;
	private String classId; 
	private String weekCnt;
	private String reportName;
	private String reportDesc;
	private String evalYn;
	private String score;
	private String submitStartDate;
	private String submitEndDate;
	private String atchFileId;
	private String deleteYn;
	private String reportSubmitId;
	private String title;
	private String content;
	private String evalScore;
	private String submitDateType; // 제출기간타입  학습활동서제출마감
	private String totCnt;
	private String scoreCnt;
	private String scoreOn;
	private String checkYn;	
	private String lrsAtchFileId;
	private String submitStatus;
	private String memName;
	private String memSeq;
	private String memId;
	
	private String [] arrEvalScore;
	private String [] arrReportSubmitId;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String[] getArrEvalScore() {
		return arrEvalScore;
	}
	public void setArrEvalScore(String[] arrEvalScore) {
		this.arrEvalScore = arrEvalScore;
	}
	public String[] getArrReportSubmitId() {
		return arrReportSubmitId;
	}
	public void setArrReportSubmitId(String[] arrReportSubmitId) {
		this.arrReportSubmitId = arrReportSubmitId;
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
	public String getLrsAtchFileId() {
		return lrsAtchFileId;
	}
	public void setLrsAtchFileId(String lrsAtchFileId) {
		this.lrsAtchFileId = lrsAtchFileId;
	}
	public String getSubmitStatus() {
		return submitStatus;
	}
	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}
	public String getCheckYn() {
		return checkYn;
	}
	public void setCheckYn(String checkYn) {
		this.checkYn = checkYn;
	}
	public String getScoreOn() {
		return scoreOn;
	}
	public void setScoreOn(String scoreOn) {
		this.scoreOn = scoreOn;
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
	public String getSubClass() {
		return classId;
	}
	public void setSubClass(String subClass) {
		this.classId = subClass;
	}
	public String getSubmitDateType() {
		return submitDateType;
	}
	public void setSubmitDateType(String submitDateType) {
		this.submitDateType = submitDateType;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
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
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getReportDesc() {
		return reportDesc;
	}
	public void setReportDesc(String reportDesc) {
		this.reportDesc = reportDesc;
	}
	public String getEvalYn() {
		return evalYn;
	}
	public void setEvalYn(String evalYn) {
		this.evalYn = evalYn;
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
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getReportSubmitId() {
		return reportSubmitId;
	}
	public void setReportSubmitId(String reportSubmitId) {
		this.reportSubmitId = reportSubmitId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEvalScore() {
		return evalScore;
	}
	public void setEvalScore(String evalScore) {
		this.evalScore = evalScore;
	}
	public String  toString(){
		String data="";
		data += "reportId"+reportId;
		data += "yyyy"+yyyy;
		data += "term"+term;
		data += "subjectCode"+subjectCode;
		data += "classId "+classId ;
		data += "weekCnt"+weekCnt;
		data += "reportName"+reportName;
		data += "reportDesc"+reportDesc;
		data += "evalYn"+evalYn;
		data += "score"+score;
		data += "submitStartDate"+submitStartDate;
		data += "submitEndDate"+submitEndDate;
		data += "atchFileId"+atchFileId;
		data += "deleteYn"+deleteYn;
		data += "reportSubmitId"+reportSubmitId;
		data += "title"+title;
		data += "content"+content;
		data += "evalScore"+evalScore;
		data += "submitDateType"+submitDateType;
		
		return data;
	}
}
