package kr.co.sitglobal.oklms.lu.teamproject.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectSubmitVO;
import kr.co.sitglobal.oklms.lu.teamproject.vo.TeamprojectVO;

@Transactional(rollbackFor=Exception.class)
public interface TeamprojectService {
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return
	 * OnlineTraningSchVO
	 */
	List<OnlineTraningSchVO> listLmsSubjWeek(CurrProcVO currProcVO) throws Exception;
	
	CurrProcVO getCurrproc(CurrProcVO currProcVO) throws Exception;
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * TeamprojectVO
	 */
	TeamprojectVO getTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * TeamprojectVO
	 */
	List<MemberVO> listStudents(CurrProcVO currProcVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * TeamprojectVO
	 */
	List<TeamprojectVO> listTeamproject(TeamprojectVO teamprojectVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * TeamprojectVO
	 */
	List<TeamprojectVO> listTeamprojectStd(TeamprojectVO teamprojectVO) throws Exception;		
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * String
	 */
	int insertTeamproject(TeamprojectVO teamprojectVO,final MultipartHttpServletRequest multiRequest) throws Exception;
 	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * String
	 */
	int insertTeamprojectStd(TeamprojectSubmitVO teamprojectSubmitVO,final MultipartHttpServletRequest multiRequest) throws Exception;
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param teamprojectVo
	 * @return
	 * String
	 */ 
	int updateTeamproject(TeamprojectVO teamprojectVO,final MultipartHttpServletRequest multiRequest) throws Exception;
	
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param teamprojectVo
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
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * String
	 */ 
	int updateTeamprojectSubmitArr(TeamprojectSubmitVO teamprojectSubmitVO, HttpServletRequest request) throws Exception;
	/**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * int
	 */
	int deleteTeamprojectSubmitAttechFile(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param teamprojectSubmitVO
	 * @return
	 * TeamprojectSubmitVO
	 */
	List<TeamprojectSubmitVO> listTeamprojectSubmit(TeamprojectSubmitVO teamprojectSubmitVO) throws Exception;	
}
