package kr.co.sitglobal.oklms.lu.traning.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;  


@Mapper("weekTraningMapper")
public interface WeekTraningMapper {

	List<TraningNoteVO> listWeekTraningNoteCot(TraningNoteVO traningNoteVO)throws Exception;
	List<TraningNoteVO> listWeekTraningNoteBottomCot(TraningNoteVO traningNoteVO)throws Exception;
	int updateWeekTraningNoteCot(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteCot(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteAddCot(TraningNoteVO traningNoteVO) throws Exception;
	
	List<TraningNoteVO> listWeekTraningNotePrd(TraningNoteVO traningNoteVO)throws Exception;
	List<TraningNoteVO> listWeekTraningNoteBottomPrd(TraningNoteVO traningNoteVO)throws Exception;
	int updateWeekTraningNotePrd(TraningNoteVO traningNoteVO) throws Exception;

	List<TraningNoteVO> listWeekTraningNoteBottomCcn(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteCcn(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteAddCcn(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteAddCcnAdd(TraningNoteVO traningNoteVO) throws Exception;
	
	int updateWeekTraningNoteCcn(TraningNoteVO traningNoteVO) throws Exception;

	
	List<TraningNoteVO> getWeekTraningNotePrd(TraningNoteVO traningNoteVO) throws Exception;
	List<TraningNoteVO> getWeekTraningNoteAddPrd(TraningNoteVO traningNoteVO) throws Exception;
	 
	// 교수용 출력물 정보
	List<TraningNoteVO> detailTraningNotePrd(TraningNoteVO traningNoteVO) throws Exception;
	// 현장교사용 출력물 정보
	List<TraningNoteVO> detailTraningNoteCot(TraningNoteVO traningNoteVO) throws Exception;	
	
	// 센터담당자 월간출석부용
	List<TraningNoteVO> selectDayTraningNoteAll(TraningNoteVO traningNoteVO) throws Exception;
	
	// 센터담당자 월간출석부용합계
	List<TraningNoteVO> selectDayTraningNoteTotal(TraningNoteVO traningNoteVO) throws Exception;
	// 센터담당자 월간출석부전체조회
	TraningNoteVO selectDayTraningNoteAllSum(TraningNoteVO traningNoteVO) throws Exception;
}
