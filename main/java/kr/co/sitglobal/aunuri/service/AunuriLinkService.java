package kr.co.sitglobal.aunuri.service;

import java.util.List;

import kr.co.sitglobal.aunuri.vo.AunuriLinkEvalPlanNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectGradeVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekVO;

import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor=Exception.class)
public interface AunuriLinkService {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * List<AunuriLinkMemberVO>
	 */
	List<AunuriLinkMemberVO> listAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;


	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkSubjectVO
	 * @return
	 * List<AunuriLinkSubjectVO>
	 */
	List<AunuriLinkSubjectVO> listAunuriSubject(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkLessonVO
	 * @return
	 * List<AunuriLinkLessonVO>
	 */
	List<AunuriLinkLessonVO> listAunuriLesson(AunuriLinkLessonVO aunuriLinkLessonVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberMappingVO
	 * @return
	 * List<AunuriLinkMemberMappingVO>
	 */
	List<AunuriLinkMemberMappingVO> listAunuriIns(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO)throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberMappingVO
	 * @return
	 * List<AunuriLinkScheduleVO>
	 */
	List<AunuriLinkScheduleVO> listAunuriHaksaSchedule(AunuriLinkScheduleVO aunuriLinkScheduleVO) throws Exception;

	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param aunuriLinkSubjectWeekVO
	 * @return
	 * List<AunuriLinkSubjectWeekVO>
	 */
	AunuriLinkSubjectWeekVO getAunuriWeekLessonInfo(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception;


	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param aunuriLinkSubjectWeekVO
	 * @return
	 * List<AunuriLinkSubjectWeekVO>
	 */
	AunuriLinkSubjectWeekVO getAunuriWeekTime(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception;

	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * List<AunuriLinkSubjectWeekNcsVO>
	 */
	AunuriLinkSubjectWeekNcsVO getAunuriWeekNcsUnit(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;

	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * List<aunuriLinkSubjectWeekNcsVO>
	 */
	List<AunuriLinkSubjectWeekNcsVO> listAunuriWeekNcsElem(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;

	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * AunuriLinkMemberVO
	 */
	AunuriLinkMemberVO getAunuriUserImage(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;

	/**
	 * DB에서 성적평가기준 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkEvalPlanNcsVO
	 * @return
	 * List<AunuriLinkEvalPlanNcsVO>
	 */
	List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanCode(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;

	/**
	 * DB에서 NCS 평가계획 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkEvalPlanNcsVO
	 * @return
	 * List<AunuriLinkEvalPlanNcsVO>
	 */
	List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalPlanNote(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;

	/**
	 * DB에서 NCS 평가방법 Data를 여러건 조회하는 로직을 수행한다.
	 * @param aunuriLinkEvalPlanNcsVO
	 * @return
	 * List<AunuriLinkEvalPlanNcsVO>
	 */
	List<AunuriLinkEvalPlanNcsVO> listAunuriWeekNcsEvalWay(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;

	/**
	 * DB에서 성적평가기준 Data를 단건을 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * AunuriLinkEvalPlanNcsVO
	 */
	AunuriLinkEvalPlanNcsVO getAunuriWeekNcsEvalPlan(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;

	/**
	 * DB에서 NCS개설과목능력요소 Data를 단건을 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * AunuriLinkEvalPlanNcsVO
	 */
	AunuriLinkEvalPlanNcsVO getAunuriWeekNcsEvalPlanElem(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	
	/**
	 * DB에서 근로자별 성적 Data를 단건을 조회하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * AunuriLinkEvalPlanNcsVO
	 */
	List<AunuriLinkSubjectGradeVO> listSubectGrade(AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO) throws Exception;


	int getAunuriMemberCnt(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int getAunuriMemberTutCnt(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int getAunuriMemberTutSize(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int insertAunuriSubjectInfoTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int updateAunuriSubjectInfoTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int deleteAunuriTutMappingNull(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;
	int deleteAunuriTutMapping(AunuriLinkEvalPlanNcsVO evalPlanNcsVO) throws Exception;

	int updateAunuriOutMemberInfoEtc(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;


	AunuriLinkSubjectGradeVO getSubectGradeCnt(AunuriLinkSubjectGradeVO aunuriLinkSubjectGradeVO) throws Exception;


	String getAunuriWeekStartDate(AunuriLinkSubjectVO aunuriLinkSubjectVO)throws Exception;
	
	
	
}
