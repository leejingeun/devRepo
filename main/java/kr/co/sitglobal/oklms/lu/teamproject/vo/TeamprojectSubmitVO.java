package kr.co.sitglobal.oklms.lu.teamproject.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;


/**
 * The persistent class for the lms_team_project_submit database table.
 * 
 */
public class TeamprojectSubmitVO extends TeamprojectVO implements Serializable{
	private static final long serialVersionUID = 1L;


	private int groupId;
	private String teamprojectSubmitId;
	
	private String leaderYn;
	private String memId;
	private String title;
	private String content;
	private int evalScore;			// 평가점수

	private String memName;
	private String companyId;
	private String submitDate;		// 과제 제출일

	private int teamMemberCnt;	// 팀원수
	private int boardCnt;			// 게시글수
	private String teamMembers;	// 팀원목록
	private String leaderName;		// 팀장이름 
	
	private String[] arrTeamprojectSubmitId; 

	
	public TeamprojectSubmitVO() {
	}
	public TeamprojectSubmitVO(TeamprojectVO teamprojectVO) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(this, teamprojectVO); 
	}


	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getTeamprojectSubmitId() {
		return teamprojectSubmitId;
	}

	public void setTeamprojectSubmitId(String teamprojectSubmitId) {
		this.teamprojectSubmitId = teamprojectSubmitId;
	}


	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getEvalScore() {
		return this.evalScore;
	}

	public void setEvalScore(int evalScore) {
		this.evalScore = evalScore;
	}

	public String getLeaderYn() {
		return this.leaderYn;
	}

	public void setLeaderYn(String leaderYn) {
		this.leaderYn = leaderYn;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public int getTeamMemberCnt() {
		return teamMemberCnt;
	}

	public void setTeamMemberCnt(int teamMemberCnt) {
		this.teamMemberCnt = teamMemberCnt;
	}

	public int getBoardCnt() {
		return boardCnt;
	}

	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}
	public String getLeaderName() {
		if(this.leaderName != null && !this.leaderName.isEmpty()){
			return this.leaderName;
		}else{
			if(teamMembers != null && !this.teamMembers.isEmpty()){
				this.leaderName = this.teamMembers.split(",")[0];
			}
		}
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String[] getArrTeamprojectSubmitId() {
		return arrTeamprojectSubmitId;
	}
	public void setArrTeamprojectSubmitId(String[] arrTeamprojectSubmitId) {
		this.arrTeamprojectSubmitId = arrTeamprojectSubmitId;
	}
	
}