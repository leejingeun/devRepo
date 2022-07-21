/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 04. 17.         First Draft
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.planeval.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.planeval.vo.PlanEvalVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("planEvalMapper")
public interface PlanEvalMapper {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanInfo(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanElemInfo(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listFistEvalPlanLessonMember(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstRowNumber(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstStdScoreRowNumber(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstRowspan(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstInfo(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstElemId(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstCtrlSet(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listFistEvalPlanLessonMemberPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstRowNumberPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstStdScoreRowNumberPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstRowspanPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstInfoPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstElemIdPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanFirstCtrlSetPrt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanSubjectRebort(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * List<PlanEvalVO>
	 */
	List<PlanEvalVO> listNcsEvalPlanMemberRebort(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 조회한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	PlanEvalVO getEvDivCd(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 Data를 조회한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	PlanEvalVO getLessionMember(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 평가계획 성적 기준 Data를 추가하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int insertNcsEvalPlanStd(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 강의계획서학습내용 Data를 추가하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int insertNcsEvalPlanLrn(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 개설과목능력요소 Data를 추가하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int insertNcsEvalPlanLctreElem(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 개설과목관리항목설정 Data를 추가하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int insertNcsEvalPlanLctreCtrlSet(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 1차평가학습자별점수 Data를 추가하는 로직을 수행한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int insertFirstEvalStdScore(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 평가계획등록 성적기준별 Data를 조회한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int getNcsEvalPlanCnt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 평가계획등록 성적기준별 Data를 조회한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int getNcsEvalPlanStdScoreCnt(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 1차평가 학습근로자별 취득점수 Data를 업데이트한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int updateFirstEvalStdScore(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 1차평가 학습근로자별 취득점수 Data를 업데이트한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int updateNcsEvalPlanFirstAppYn(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 1차평가 학습근로자별 취득점수 Data를 업데이트한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int updateNcsEvalPlanApproval(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 1차평가 학습근로자별 취득점수 Data를 업데이트한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int updateNcsEvalPlanFirstAtchFile(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 평가계획등록 성적기준별 Data를 삭제한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int deleteNcsEvalPlanStd(PlanEvalVO planEvalVO) throws Exception;

	/**
	 * DB에서 평가계획등록 성적기준별 Data를 삭제한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int deleteNcsEvalPlanLrn(PlanEvalVO planEvalVO) throws Exception;


	/**
	 * DB에서 평가계획등록 성적기준별 Data를 삭제한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int deleteNcsEvalPlanLctreElem(PlanEvalVO planEvalVO) throws Exception;


	/**
	 * DB에서 평가계획등록 성적기준별 Data를 삭제한다.
	 * @param planEvalVO
	 * @return
	 * int
	 */
	int deleteNcsEvalPlanLctreCtrlSet(PlanEvalVO planEvalVO) throws Exception;





}
