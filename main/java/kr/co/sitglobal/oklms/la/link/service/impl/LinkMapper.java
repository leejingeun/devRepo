package kr.co.sitglobal.oklms.la.link.service.impl;

import java.util.List;

import kr.co.sitglobal.aunuri.vo.AunuriLinkLessonVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberMappingVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkMemberVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkScheduleVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekNcsVO;
import kr.co.sitglobal.aunuri.vo.AunuriLinkSubjectWeekVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("linkMapper")
public interface LinkMapper {
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * StringLinkMapper
	 */
	int insertAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;

	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param ㅁunuriLinkSubjectVO
	 * @return
	 * Integer
	 */
	int insertAunuriSubject(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param ㅁunuriLinkSubjectVO
	 * @return
	 * Integer
	 */
	int insertAunuriLesson(AunuriLinkLessonVO aunuriLinkLessonVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * Integer
	 */
	int insertAunuriSubjectWeek(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekVO
	 * @return
	 * Integer
	 */
	int insertAunuriWeekTime(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekVO
	 * @return
	 * Integer
	 */
	int updateAunuriWeekTime(AunuriLinkSubjectWeekVO aunuriLinkSubjectWeekVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * Integer
	 */
	int insertAunuriIns(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;
	

	
	/**
	 * 목록을 조회하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * List<AunuriLinkMemberVO>
	 */
	List<AunuriLinkMemberVO> listAunuriMember(AunuriLinkMemberVO aunuriLinkMemberVO) throws Exception;
	
	
	/**
	 * 목록을 조회하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * List<AunuriLinkMemberVO>
	 */
	List<AunuriLinkMemberMappingVO> listSubjectCdp(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberMappingVO
	 * @return
	 * Integer
	 */
	int insertAunuriCdp(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;
	
	
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkScheduleVO
	 * @return
	 * Integer
	 */
	int insertAunuriSchedule(AunuriLinkScheduleVO aunuriLinkScheduleVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * Integer
	 */
	int insertAunuriWeekNcsUnit(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * Integer
	 */
	int updateAunuriWeekNcsUnit(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * Integer
	 */
	int insertAunuriWeekNcsElem(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * Integer
	 */
	int updateAunuriWeekNcsElem(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	/**
	 * 단건을 등록하는 SQL 을 호출한다.
	 * @param aunuriLinkSubjectWeekNcsVO
	 * @return
	 * Integer
	 */
	int comMemberTest(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	/**
	 * 목록을 조회하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * List<aunuriLinkSubjectWeekNcsVO>
	 */
	List<AunuriLinkSubjectWeekNcsVO> listAunuriWeek(AunuriLinkSubjectWeekNcsVO aunuriLinkSubjectWeekNcsVO) throws Exception;
	
	
	
	
	int insertAunuriSubjectTerm(AunuriLinkSubjectVO aunuriLinkSubjectVO) throws Exception;
	
	/**
	 * 목록을 조회하는 SQL 을 호출한다.
	 * @param aunuriLinkMemberVO
	 * @return
	 * List<AunuriLinkMemberVO>
	 */
	List<AunuriLinkMemberMappingVO> listSubjectCdpTerm(AunuriLinkMemberMappingVO aunuriLinkMemberMappingVO) throws Exception;
	
}
