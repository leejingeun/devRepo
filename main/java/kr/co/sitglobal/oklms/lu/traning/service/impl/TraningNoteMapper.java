package kr.co.sitglobal.oklms.lu.traning.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.traning.vo.TraningMemberVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessSearchVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("traningNoteMapper")
public interface TraningNoteMapper {
 

	TraningNoteVO getTraningNote(TraningNoteVO traningNoteVO)throws Exception;
	
	TraningNoteVO getTraningNowWeekCnt(TraningNoteVO traningNoteVO)throws Exception;
	
	int getTraningRegularClassMemberCnt(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listTraningRegularClassMember2(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listTraningRegularClassMember(TraningNoteVO traningNoteVO)throws Exception;

	/**
	 * 훈련지원> 훈련일지 정보 > 정규수업 학습근로자 리스트목록만
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	List<TraningNoteVO> listTraningClassMember(TraningNoteVO traningNoteVO)throws Exception;
	
	TraningNoteVO getTraningRegularTime(TraningNoteVO traningNoteVO)throws Exception;

	int goUpdateTraningTime(TraningNoteVO traningNoteVO)throws Exception;

	int goUpdateTraningNoteMaster(TraningNoteVO traningNoteVO)throws Exception;

	int goUpdateTraningNoteDetail(TraningNoteVO traningNoteVO)throws Exception;

	int goInsertTraningNoteMaster(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> getTraningEnrichmentTime(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listTraningEnrichmentClassMember(TraningNoteVO traningNoteVO)throws Exception;

	int goInsertEnrichmentTraningTime(TraningNoteVO traningNoteVO)throws Exception;

	int goInsertTraningNoteDetail(TraningNoteVO traningNoteVO)throws Exception;

	int goUpdateEnrichmentTraningTime(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listSubjcetName(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listTraningRegularTime(TraningNoteVO traningNoteVO)throws Exception;

}