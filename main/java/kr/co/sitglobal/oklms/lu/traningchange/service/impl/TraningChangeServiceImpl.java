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

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.atchFile.service.AtchFileService;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traningchange.service.TraningChangeService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
 * @author 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("traningChangeService")
public class TraningChangeServiceImpl extends EgovAbstractServiceImpl implements TraningChangeService{

	/** ID Generation */
    @Resource(name = "atchFileService")
   	private AtchFileService atchFileService;

    @Resource(name = "traningChangeMapper")
    private TraningChangeMapper traningChangeMapper;
    
    //미승인 변경신청 목록
  	@Override
	public List<TraningScheduleVO> listTraningChangeScheduleDisapproved(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningChangeMapper.listTraningChangeScheduleDisapproved(traningScheduleVO);
		return data;
	}
  	
  	//승인된 변경이력 목록 
  	@Override
	public List<TraningScheduleVO> listTraningChangeScheduleApproved(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningChangeMapper.listTraningChangeScheduleApproved(traningScheduleVO);
		return data;
	}
  	
  	//승인된 변경신청 내역 목록 엑셀다운로드 
  	@Override
	public List<TraningScheduleVO> listTraningChangeScheduleApprovedExcelDownload(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningChangeMapper.listTraningChangeScheduleApprovedExcelDownload(traningScheduleVO);
		return data;
	}
  	
  	//훈련시간표 변경신청 상세 목록 
  	@Override
	public List<TraningScheduleVO> listTraningChangeScheduleDisapprovedDetail(TraningScheduleVO traningScheduleVO) throws Exception {
		List<TraningScheduleVO> data = traningChangeMapper.listTraningChangeScheduleDisapprovedDetail(traningScheduleVO);
		return data;
	}
  	
  	//훈련과정시간표 변경신청건 변경사유 수정 저장
  	@Override
	public int updateTraningChangeSchedule(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.updateTraningChangeSchedule(traningScheduleVO);
	}
  	
  	//훈련과정시간표 변경신청건 훈련일자 수정 저장
  	@Override
	public int updateTraningChangeScheduleTime(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.updateTraningChangeScheduleTime(traningScheduleVO);
	}
  	
  	//센터담당자가 승인처리
  	@Override
	public int updateTraningChangeScheduleStatus(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.updateTraningChangeScheduleStatus(traningScheduleVO);
	}
  	
  	//센터담당자가 승인처리
  	@Override
	public int updateTraningChangeScheduleStatusApprovalNot(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.updateTraningChangeScheduleStatusApprovalNot(traningScheduleVO);
	}
  	
    //센터담당자가 승인 처리시 실제사용하는 주차별시간 테이블에 훈련일자를 변경일자로 업데이트
  	@Override
	public int updateSubjWeekTime(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.updateSubjWeekTime(traningScheduleVO);
	}
  	
  	//HRD담당자, 기입현장교사가 삭제처리
  	@Override
	public int deleteTraningChangeScheduleDisapproved(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.deleteTraningChangeScheduleDisapproved(traningScheduleVO);
	}
  	
  	//HRD담당자, 기입현장교사가 삭제처리
  	@Override
	public int deleteTraningChangeScheduleTimeDisapproved(TraningScheduleVO traningScheduleVO) throws Exception {
		return traningChangeMapper.deleteTraningChangeScheduleDisapproved(traningScheduleVO);
	}
}
