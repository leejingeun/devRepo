
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.subject.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import kr.co.sitglobal.oklms.lu.grade.vo.GradeVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectCompanyVO;
import kr.co.sitglobal.oklms.lu.subject.vo.SubjectVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * 프로토타입 게시판 CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author 이진근
 * @since 2017. 01. 06.
 */
@Mapper("SubjectMapper")
public interface SubjectMapper {

	/**
	 * 단걸을 조회하는 SQL 을 호출한다.
	 * @param memberVO
	 * @return
	 * SubjectVO
	 */
	SubjectVO getSubjectInfo(SubjectVO subjectVO) throws Exception;



	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectPrtOff(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectPrtOffWeek(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectPrtOjt(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectPrtOjtWeek(SubjectVO subjectVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectCdpOff(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectStdOff(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectStdOffWeek(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectStdOjt(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectStdOjtWeek(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectCotOjt(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectVO
	 * @return
	 * List<SubjectVO>
	 */
	List<SubjectVO> listInChargeSubjectCotOjtWeek(SubjectVO subjectVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectCompanyVO
	 * @return
	 * List<SubjectCompanyVO>
	 */
	List<SubjectCompanyVO> listInChargeCompanyStudyStateCcn(SubjectCompanyVO subjectCompanyVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectCompanyVO
	 * @return
	 * String
	 */
	SubjectCompanyVO getActivityNoteMemInfos(SubjectCompanyVO subjectCompanyVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectCompanyVO
	 * @return
	 * List<SubjectCompanyVO>
	 */
	List<SubjectCompanyVO> listInChargeCompanyStateCcn(SubjectCompanyVO subjectCompanyVO) throws Exception;

	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectCompanyVO
	 * @return
	 * List<SubjectCompanyVO>
	 */
	List<SubjectCompanyVO> listInChargeCompanyStudyStateCcm(SubjectCompanyVO subjectCompanyVO) throws Exception;


	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param subjectCompanyVO
	 * @return
	 * List<SubjectCompanyVO>
	 */
	List<SubjectCompanyVO> listInChargeCompanyStateCcm(SubjectCompanyVO subjectCompanyVO) throws Exception;


	/**
	 * 개설강좌정보 조회 데이타 체크 Count하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return
	 * int
	 */
	String getMinSubjectClass(SubjectVO subjectVO) throws Exception;



	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<SubjectVO> listOjtClassName(SubjectVO subjectVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<SubjectVO> listOjtClassStdName(SubjectVO subjectVO) throws Exception;

}
