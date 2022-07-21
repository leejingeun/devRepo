/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 02. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.currproc.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.currproc.vo.CurrProcVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("currProcMapper")
public interface CurrProcMapper {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<CurrProcVO> listSubjectSeach(CurrProcVO currProcVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<CurrProcVO> listTrainSubjectSeach(CurrProcVO currProcVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<CurrProcVO> listCotMappingTrainSubjectDetail(CurrProcVO currProcVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param currProcVO
	 * @return
	 * List<CurrProcVO>
	 */
	List<CurrProcVO> listSubjectClassInfo(CurrProcVO currProcVO) throws Exception;

	/**
	 * 개설강좌정보 조회 데이타 체크 Count하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return currProcVO
	 *
	 */
	CurrProcVO getMySubjectInfo(CurrProcVO currProcVO) throws Exception;

	/**
	 * 개설강좌정보 조회 데이타 체크 Count하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return currProcVO
	 *
	 */
	CurrProcVO getMyTrainSubjectInfo(CurrProcVO currProcVO) throws Exception;

	/**
	 * 개설강좌정보 조회 데이타 체크 Count하는 SQL 을 호출한다.
	 * @param currProcVO
	 * @return
	 * int
	 */
	int getSubjectCnt(CurrProcVO currProcVO) throws Exception;
	
	
	
	
}
