/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 05. 10.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.qestnr.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.qestnr.vo.QestnrVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("qestnrMapper")
public interface QestnrMapper{

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param qestnrVO
	 * @return
	 * List<QestnrVO>
	 */
	List<QestnrVO> listQestnrInfo(QestnrVO qestnrVO) throws Exception;

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param qestnrVO
	 * @return
	 * List<QestnrVO>
	 */
	List<QestnrVO> listQestnrStdInfo(QestnrVO qestnrVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param qestnrVO
	 * @return
	 * List<QestnrVO>
	 */
	List<QestnrVO> listQestnrEtcAnswerCn(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param qestnrVO
	 * @return
	 * QestnrVO
	 */
	QestnrVO getQestnrInfo(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param qestnrVO
	 * @return
	 * QestnrVO
	 */
	QestnrVO getQestnrItem(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int insertQestnrInfo(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int insertQestnrItem(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int insertQestnrAnswerResult(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int updateQestnrInfo(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int updateQestnrItem(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int deleteQestnrInfo(QestnrVO qestnrVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param QestnrVO
	 * @return
	 * int
	 */
	int deleteQestnrItem(QestnrVO qestnrVO) throws Exception;


}
