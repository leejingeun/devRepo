package kr.co.sitglobal.oklms.lu.teamproject.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectGroupVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectSubmitVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectVO;

@Mapper("TeamprojectMapper")
public interface TeamprojectMapper {
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return
	 * OnlineTraningSchVO
	 */
	List<OnlineTraningSchVO> listLmsSubjWeek(CurrProcVO currProcVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * TeamprojectVO
	 */ 
	CurrProcVO getCurrproc(CurrProcVO currProcVO) throws Exception; 
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * TeamprojectVO
	 */
	TeamprojectVO getTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * TeamprojectVO
	 */
	List<MemberVO> listStudents(CurrProcVO currProcVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * TeamprojectVO
	 */
	List<TeamprojectVO> listTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * TeamprojectVO
	 */
	List<TeamprojectVO> listTeamprojectStd(TeamprojectVO teamprojectVO) throws Exception;
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * String
	 */
	int insertTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param TeamprojectGroupVO
	 * @return
	 * String
	 */
	int insertTeamprojectGroup(TeamprojectGroupVO teamprojectGroupVO) throws Exception;
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param TeamprojectGroupVO
	 * @return
	 * String
	 */
	int deleteTeamprojectGroup(TeamprojectGroupVO teamprojectGroupVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param TeamprojectGroupVO
	 * @return
	 * String
	 */
	TeamprojectGroupVO getMaxTeamprojectGroupId(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param TeamprojectGroupVO
	 * @return
	 * String
	 */
	int restoreTeamprojectGroup(TeamprojectGroupVO teamprojectGroupVO) throws Exception;
	
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * String
	 */ 
	int updateTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param teamprojectVO
	 * @return
	 * int
	 */
	int deleteTeamproject(TeamprojectVO teamprojectVO) throws Exception;	

	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * TeamprojectSubmitVO
	 */
	TeamprojectSubmitVO getTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * String
	 */
	int insertTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * String
	 */ 
	int updateTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * int
	 */
	int deleteTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;	

	/**
	 * 팀프로젝트 전체학생정보 삭제.
	 * @param teamprojectVO
	 * @return
	 * int
	 */
	int deleteAllTeamprojectSubmit(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 팀프로젝트 전체학생정보 팅장초기화.
	 * @param teamprojectVO
	 * @return
	 * int
	 */
	int clearAllTeamprojectSubmitLeader(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * TeamprojectSubmitVO
	 */
	List<TeamprojectSubmitVO> listTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
	/**
	 * 팀프로젝트 그룹전원 제출여부 변경하기.
	 * @param teamprojectSubmitVO
	 * @return
	 * int
	 */
	int updateAllTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
	/**
	 * 팀프로젝트 제출파일 제거.
	 * @param teamprojectSubmitVO
	 * @return
	 * int
	 */
	int deleteTeamprojectSubmitAttechFile(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
}
