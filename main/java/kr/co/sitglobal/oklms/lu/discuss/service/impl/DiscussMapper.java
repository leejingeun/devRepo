/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 02.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.discuss.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.discuss.vo.DiscussVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("discussMapper")
public interface DiscussMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param discussVO
	 * @return
	 * List<DiscussVO>
	 */
	List<DiscussVO> listDiscussInfo(DiscussVO discussVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param discussVO
	 * @return
	 * List<DiscussVO>
	 */
	List<DiscussVO> listDiscussOpinion(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param discussVO
	 * @return
	 * List<DiscussVO>
	 */
	List<DiscussVO> listDiscussComment(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param discussVO
	 * @return
	 * List<DiscussVO>
	 */
	List<DiscussVO> listDiscussEvalScoreResultStd(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param discussVO
	 * @return
	 * DiscussVO
	 */
	DiscussVO getDiscussInfo(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param discussVO
	 * @return
	 * DiscussVO
	 */
	DiscussVO getDiscussOpinionList(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param discussVO
	 * @return
	 * DiscussVO
	 */
	DiscussVO getDiscussOpinionComment(DiscussVO discussVO) throws Exception;
	
	/**
	 * 토론의 의견수를 구한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int getDiscussOpinionCnt(DiscussVO discussVO) throws Exception;
	
	/**
	 * 토론의 의견수를 구한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int getDiscussOpinionCommentCnt(DiscussVO discussVO) throws Exception;
	
	/**
	 * 토론 의견의 추천수를 구한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int getDiscussOpinionHistCnt(DiscussVO discussVO) throws Exception;
	
	/**
	 * 토론 의견의 추천수를 구한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int getDiscussEvalScoreStdCnt(DiscussVO discussVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int insertDiscussInfo(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int insertDiscussOpinion(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int insertDiscussGoodHist(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int insertDiscussComment(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int insertDiscussStdEvalScore(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussInfo(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussOpinion(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussComment(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussOpinionHitCnt(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussOpinionGoodCnt(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussStdEvalScore(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int updateDiscussStdEvalScoreYn(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int deleteDiscussInfo(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int deleteDiscussOpinion(DiscussVO discussVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param DiscussVO
	 * @return
	 * int
	 */
	int deleteDiscussComment(DiscussVO discussVO) throws Exception;

	
	

	
	
}
