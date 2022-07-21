package kr.co.sitglobal.oklms.la.link.service;

import java.util.List;

import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;
import kr.co.sitglobal.oklms.la.company.vo.CompanyVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface LinkService {
	

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * int
	 */
	int insertAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param aunuriLinkSubjectVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriSubject(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception;
	
	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param aunuriLinkMemberMappingVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriIns(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param aunuriLinkLessonVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriLesson(AunuriLinkLessonVO aunuriLinkLessonVO) throws Exception;

	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param aunuriLinkLessonVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriCdp(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;

	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param aunuriLinkScheduleVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriSchedule(AunuriLinkScheduleVO aunuriLinkScheduleVO) throws Exception;

	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param AunuriLinkSubjectWeekNcsVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertAunuriWeekNcs(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param AunuriLinkSubjectWeekNcsVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int updateAunuriWeek(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는  SQL 을 호출한다.
	 * @param AunuriLinkSubjectWeekNcsVO
	 * @return
	 * @throws Exception
	 * int
	 */
	int executeBatch() throws Exception;


	int insertAunuriLinkTerm(AunuriLinkSubjectVO aunuriLinkSubjectVO)throws Exception;

}
