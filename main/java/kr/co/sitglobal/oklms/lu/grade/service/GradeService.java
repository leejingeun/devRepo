package kr.co.sitglobal.oklms.lu.grade.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.activity.vo.ActivityVO;
import kr.co.sitglobal.oklms.lu.activity.vo.MemberVO;
import kr.co.sitglobal.oklms.lu.activity.vo.SubjweekStdVO;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Transactional(rollbackFor=Exception.class)
public interface GradeService {

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param gradeVO
	 * @return
	 * GradeVO
	 */
	List<GradeVO> listGradeSendStatusList(GradeVO gradeVO) throws Exception;
	
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param gradeVO
	 * @return
	 * GradeVO
	 */
	List<GradeVO> listGradeSendList(GradeVO gradeVO) throws Exception;

	/**
	 * 단건을 등록하는 SQL을 호출한다
	 * @param gradeVO
	 * @return
	 * int
	 */
	int insertGradeCdpSend(GradeVO gradeVO) throws Exception;

	/**
	 * 단건을 조회하는 SQL을 호출한다
	 * @param gradeVO
	 * @return
	 * String
	 */
	String getGradeGroupMemIds(GradeVO gradeVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL을 호출한다
	 * @param gradeVO
	 * @return
	 * String
	 */
	GradeVO getGradeCcmConfirmInfo(GradeVO gradeVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL을 호출한다
	 * @param gradeVO
	 * @return
	 * String
	 */
	GradeVO getGradeCcmSubmitInfo(GradeVO gradeVO) throws Exception;
	
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param gradeVO
	 * @return
	 * GradeVO
	 */
	List<GradeVO> listGradeConfirmList(GradeVO gradeVO) throws Exception;

	/**
	 * 단건을 수정하는 SQL을 호출한다
	 * @param gradeVO
	 * @return
	 * int
	 */
	int updateGradeCcmConfirmY(GradeVO gradeVO) throws Exception;




	
}
