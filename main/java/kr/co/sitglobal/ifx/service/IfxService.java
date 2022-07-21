package kr.co.sitglobal.ifx.service;

import java.util.List;

import kr.co.sitglobal.ifx.vo.AunuriMemberVO;
import kr.co.sitglobal.ifx.vo.AunuriSubjectVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface IfxService {
	/**
	 * Aunuri에서 학생정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriMemberVO>
	 */
	List<AunuriMemberVO> getAunuriMember(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOjtAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOffJtAunuriSubject(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 학습자에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOjtAunuriSubjectLesson(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 학습자에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOffJtAunuriSubjectLesson(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 담당교사에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOjtAunuriSubjectInsMapping(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 담당교사에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOffJtAunuriSubjectInsMapping(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 기업현장교사에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOjtAunuriSubjectTutMapping(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * Aunuri에서 학과전담자에 메핑된 교과정보를 가져온다.
	 * @param aunuriMemberVO
	 * @return
	 * List<AunuriSubjectVO>
	 */
	List<AunuriSubjectVO> getOffJtAunuriSubjectCdpMapping(AunuriMemberVO aunuriMemberVO) throws Exception;
	
	/**
	 * DB에서 교수의 담당 고숙련 과정 갯수를 조회하는 로직을 수행한다.
	 * @param aunuriMemberVO
	 * @return
	 * Integer
	 */
	int getOjtAunuriSubjectInsHSkillCnt(AunuriMemberVO aunuriMemberVO) throws Exception;
	
}
