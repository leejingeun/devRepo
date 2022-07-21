package kr.co.sitglobal.oklms.lu.today.service;

import java.util.List;
 












import kr.co.sitglobal.oklms.lu.today.vo.TodayVO;

import org.springframework.transaction.annotation.Transactional; 

import egovframework.com.cop.bbs.service.BoardVO;

@Transactional(rollbackFor=Exception.class)
public interface TodayService {
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ActivityVO
	 */
	List<BoardVO> listQnaStd(BoardVO boardVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ActivityVO
	 */
	List<BoardVO> listQnaCot(BoardVO boardVO) throws Exception;
	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * ActivityVO
	 */
	List<BoardVO> listQnaCdp(BoardVO boardVO) throws Exception;	
	/**
	 * 
	 * 교수 today 훈련일지작성 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listTraningNotePrt(TodayVO todayVO) throws Exception;
	
	/**
	 * 
	 * 현장교사 today 훈련일지작성 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listTraningNoteCot(TodayVO todayVO) throws Exception;
	
	/**
	 * 
	 * 교수 today 학습활동서확인 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>listActivityNotePrt (TodayVO todayVO) throws Exception;
	/**
	 * 
	 * 현장교사 today 학습활동서확인 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>listActivityNoteCot (TodayVO todayVO) throws Exception;
	
 
	/**
	 * 
	 * 학습근로자 today 과제미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listReportSubmitStd (TodayVO todayVO) throws Exception;
	
	/**
	 * 
	 * 교수 today 과제미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listReportSubmitPrt (TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 학습근로자 today 팀프로젝트 미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listTeamProjectSubmitStd (TodayVO todayVO) throws Exception;
	/**
	 * 
	 * 교수 today 팀프로젝트 미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listTeamProjectSubmitPrt (TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 학습근로자 today 토론 미참여 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listDiscussStd (TodayVO todayVO) throws Exception;
	/**
	 * 
	 * 학과전담자 today 토론 미참여 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listDiscussCdp (TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 학습근로자 today 재직증빙서류 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listWorkCertStd	(TodayVO todayVO) throws Exception;
	/**
	 * 
	 * 학과전담자 today 재직증빙서류 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listWorkCertCdp	(TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 센터전담자 today 담당자 변경신청목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listComMemberCcm (TodayVO todayVO) throws Exception;	
	
	/**
	 * 
	 * 현장교사 today 면담일지 작성 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listInterviewCot (TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 센터전담자 today 면담일지 작성 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listInterviewCcn (TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 학습근로자 today 온라인미수강 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listOnlineStd	(TodayVO todayVO) throws Exception;
	/**
	 * 
	 * 학과전담자 today 온라인미수강 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listOnlineCdp(TodayVO todayVO) throws Exception;
	/**
	 * 
	 * HRD담당자 today 활동내역서 미작성 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listActivityHrd(TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * HRD담당자 today 주간훈련일지 미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO>	listWeekTraningNoteHrd(TodayVO todayVO) throws Exception;	
	/**
	 * 
	 * 센터담당자 today 주간훈련일지 미제출 목록
	 * 
	 * @return
	 * @throws Exception
	 */
	List<TodayVO> listWeekTraningNoteCompany(TodayVO todayVO) throws Exception;	
}
