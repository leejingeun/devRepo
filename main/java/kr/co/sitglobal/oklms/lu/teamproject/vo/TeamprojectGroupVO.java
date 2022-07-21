package kr.co.sitglobal.oklms.lu.teamproject.vo;

import java.io.Serializable;
import java.util.List;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;


/**
 * The persistent class for the lms_team_project_group database table.
 * 
 */
public class TeamprojectGroupVO extends BaseVO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String teamprojectId;
	private int groupId;

	private List<TeamprojectSubmitVO> teamprojectSubmits;

	public TeamprojectGroupVO() {
	}

	public List<TeamprojectSubmitVO> getLmsTeamProjectSubmits() {
		return this.teamprojectSubmits;
	}

	public void setLmsTeamProjectSubmits(List<TeamprojectSubmitVO> teamprojectSubmits) {
		this.teamprojectSubmits = teamprojectSubmits;
	}

	public TeamprojectSubmitVO addLmsTeamProjectSubmit(TeamprojectSubmitVO lmsTeamProjectSubmit) {
		getLmsTeamProjectSubmits().add(lmsTeamProjectSubmit);
		return lmsTeamProjectSubmit;
	}

	public TeamprojectSubmitVO removeLmsTeamProjectSubmit(TeamprojectSubmitVO lmsTeamProjectSubmit) {
		getLmsTeamProjectSubmits().remove(lmsTeamProjectSubmit);
		return lmsTeamProjectSubmit;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getTeamprojectId() {
		return teamprojectId;
	}

	public void setTeamprojectId(String teamprojectId) {
		this.teamprojectId = teamprojectId;
	}
	
	

}