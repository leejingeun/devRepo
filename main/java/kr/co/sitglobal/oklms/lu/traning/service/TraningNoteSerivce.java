package kr.co.sitglobal.oklms.lu.traning.service;

import java.util.List;
 
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO; 

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface TraningNoteSerivce {
 
	/**
	 * 훈련지원> 훈련일지 정보
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	TraningNoteVO getTraningNote(TraningNoteVO traningNoteVO)throws Exception;

	TraningNoteVO getTraningNowWeekCnt(TraningNoteVO traningNoteVO)throws Exception;
	
	/**
	 * 훈련지원> 훈련일지 정보 > 정규수업 학습근로자 리스트
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	List<TraningNoteVO> listTraningRegularClassMember(TraningNoteVO traningNoteVO)throws Exception;
	/**
	 * 훈련지원> 훈련일지 정보 > 정규수업 학습근로자 리스트목록만
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	List<TraningNoteVO> listTraningClassMember(TraningNoteVO traningNoteVO)throws Exception;
	
	/**
	 *	훈련지원> 훈련일지 정보 > 정규수업 시간 타임
	 * @param traningNoteVO
	 * @return
	 */
	TraningNoteVO getTraningRegularTime(TraningNoteVO traningNoteVO)throws Exception;
	/**
	 * 훈련지원> 훈련일지 정보 > 정규수업  상세 마스터 등록
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	int goInsertTraningNoteDetail(TraningNoteVO traningNoteVO)throws Exception;
	/**
	 * 훈련지원> 훈련일지 정보 > 보강수업 타임
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	 List<TraningNoteVO> getTraningEnrichmentTime(TraningNoteVO traningNoteVO)throws Exception;
	/**
	 * 훈련지원> 훈련일지 정보 > 보강수업 학습근로자 리스트
	 * @param traningNoteVO
	 * @return
	 */
	List<TraningNoteVO> listTraningEnrichmentClassMember(TraningNoteVO traningNoteVO)throws Exception;
	/**
	 * 훈련지원 >훈련일지 정보 > 보강 수업 시간 종료일 등록 및 수정
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	int goInsertEnrichmentTraningTime(TraningNoteVO traningNoteVO)throws Exception;

	int deleteTraningNoteEnrichment(TraningNoteVO traningNoteVO) throws Exception;

	/**
	 * 훈련지원 기업 리스트
	 * @param traningNoteVO
	 * @return
	 * @throws Exception
	 */
	List<TraningNoteVO> listSubjcetName(TraningNoteVO traningNoteVO)throws Exception;

	List<TraningNoteVO> listTraningRegularTime(TraningNoteVO traningNoteVO)throws Exception;

	int getTraningRegularClassMemberCnt(TraningNoteVO traningNoteVO)throws Exception;



}
