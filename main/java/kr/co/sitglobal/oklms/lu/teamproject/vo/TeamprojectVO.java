package kr.co.sitglobal.oklms.lu.teamproject.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;


/**
 * The persistent class for the lms_team_project database table.
 * 
 */
public class TeamprojectVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String teamprojectId;
	private String atchFileId;
	private String subClass;
	private String compositionType;	// 팀 구성방식(학번/N,기업/C,수동/M) 
	private String evalYn;
	private String projectDesc;
	private String projectEndDate;
	private String projectName;
	private String projectStartDate;
	private int score;
	private String subjectCode;
	private String submitLateYn;
	private String submitType;		// T = 팀장만제출 / I = 개별제출
	private int teamCnt;
	private String teamLeaderAutoYn;
	private String term;
	private int weekCnt;
	private String yyyy;
	
	private String submitStartDate;	// 제출 시작일
	private String submitEndDate;		// 제출 종료일
	private int totCnt;					// 팀별 총학생수 or 그룹수 //  submitType == T 이면 그룹 / I 이면 총학생수
	private int submitCnt;				// 제출자수 T/I 구분에따라 달라짐.
	
	private int scoreCnt;				// 채점 완료 대상수
	private String scoreOn;				// 채점 완료여부 Y/N
	private String orderByLeader;		// 팀장우선 오더링 여부
	private String submitStatus;		// 제출기한 상태값
	
	

	public TeamprojectVO() {
	}

	public String getTeamprojectId() {
		return this.teamprojectId;
	}

	public void setTeamprojectId(String teamprojectId) {
		this.teamprojectId = teamprojectId;
	}

	public String getAtchFileId() {
		return this.atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getSubClass() {
		return this.subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getCompositionType() {
		return this.compositionType;
	}

	public void setCompositionType(String compositionType) {
		this.compositionType = compositionType;
	}

	public String getDeleteYn() {
		return this.deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

	public String getEvalYn() {
		return this.evalYn;
	}

	public void setEvalYn(String evalYn) {
		this.evalYn = evalYn;
	}

	public String getProjectDesc() {
		return this.projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getProjectEndDate() {
		return this.projectEndDate;
	}

	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectStartDate() {
		return this.projectStartDate;
	}

	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubmitLateYn() {
		return this.submitLateYn;
	}

	public void setSubmitLateYn(String submitLateYn) {
		this.submitLateYn = submitLateYn;
	}

	public String getSubmitType() {
		return this.submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public int getTeamCnt() {
		return this.teamCnt;
	}

	public void setTeamCnt(int teamCnt) {
		this.teamCnt = teamCnt;
	}

	public String getTeamLeaderAutoYn() {
		return this.teamLeaderAutoYn;
	}

	public void setTeamLeaderAutoYn(String teamLeaderAutoYn) {
		this.teamLeaderAutoYn = teamLeaderAutoYn;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public int getWeekCnt() {
		return this.weekCnt;
	}

	public void setWeekCnt(int weekCnt) {
		this.weekCnt = weekCnt;
	}

	public String getYyyy() {
		return this.yyyy;
	}

	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
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

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public int getScoreCnt() {
		return scoreCnt;
	}

	public void setScoreCnt(int scoreCnt) {
		this.scoreCnt = scoreCnt;
	}

	public String getScoreOn() {
		return scoreOn;
	}

	public void setScoreOn(String scoreOn) {
		this.scoreOn = scoreOn;
	}

	public int getSubmitCnt() {
		return submitCnt;
	}

	public void setSubmitCnt(int submitCnt) {
		this.submitCnt = submitCnt;
	}

	public String getOrderByLeader() {
		return orderByLeader;
	}

	public void setOrderByLeader(String orderByLeader) {
		this.orderByLeader = orderByLeader;
	}

	public String getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(String submitStatus) {
		this.submitStatus = submitStatus;
	}

}