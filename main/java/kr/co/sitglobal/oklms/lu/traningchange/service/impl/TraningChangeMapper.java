/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 27.    First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.traningchange.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("traningChangeMapper")
public interface TraningChangeMapper {

	//미승인 변경신청 목록
	List<TraningScheduleVO> listTraningChangeScheduleDisapproved(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//승인된 변경이력 목록 
	List<TraningScheduleVO> listTraningChangeScheduleApproved(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//승인된 변경신청 내역 목록 엑셀다운로드
	List<TraningScheduleVO> listTraningChangeScheduleApprovedExcelDownload(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//훈련시간표 변경신청 상세 목록 
	List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//훈련과정시간표 변경신청건 변경사유 수정 저장
	int updateTraningChangeSchedule(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//훈련과정시간표 변경신청건 훈련일자 수정 저장
	int updateTraningChangeScheduleTime(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//센터담당자가 승인처리
	int updateTraningChangeScheduleStatus(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//센터담당자가 반려처리
	int updateTraningChangeScheduleStatusApprovalNot(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//센터담당자가 승인처리시 실제사용하는 주차별시간 테이블에 훈련일자를 변경일자로 업데이트
	int updateSubjWeekTime(TraningScheduleVO traningScheduleVO) throws Exception;
	
	//HRD담당자, 기입현장교사가 삭제처리
	int deleteTraningChangeScheduleDisapproved(TraningScheduleVO traningScheduleVO) throws Exception;
		
	//HRD담당자, 기입현장교사가 삭제처리
	int deleteTraningChangeScheduleTimeDisapproved(TraningScheduleVO traningScheduleVO) throws Exception;

}
