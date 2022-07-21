package kr.co.sitglobal.oklms.lu.traningstatus.service;


import java.util.List;

import kr.co.sitglobal.oklms.lu.traningstatus.vo.TraningStatusVO;

import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor=Exception.class)
public interface TraningStatusService {
	/**
	 * 권장진도율 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * TraningStatusVO
	 */
	TraningStatusVO getProgress(TraningStatusVO traningStatusVO) throws Exception;
	
 	/**
	 * 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * TraningStatusVO
	 */
	List<TraningStatusVO> listTraningStatus(TraningStatusVO traningStatusVO) throws Exception;
	
	List<TraningStatusVO> listTraningStatusDetail(TraningStatusVO traningStatusVO) throws Exception;
	
	List<TraningStatusVO> listTraningStatusPrt(TraningStatusVO traningStatusVO) throws Exception;
	
	List<TraningStatusVO> listTraningStatusDetailPrt(TraningStatusVO traningStatusVO) throws Exception;
	
	List<TraningStatusVO> popupAttendListOff(TraningStatusVO traningStatusVO) throws Exception;
	
	List<TraningStatusVO> popupAttendListOnlineOff(TraningStatusVO traningStatusVO) throws Exception;
	
	/**
	 * OFF 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * TraningStatusVO
	 */
	List<TraningStatusVO> listOffTraningStatus(TraningStatusVO traningStatusVO) throws Exception;
	
	/**
	 * OJT 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * TraningStatusVO
	 */
	List<TraningStatusVO> listOjtTraningStatus(TraningStatusVO traningStatusVO) throws Exception;
	
	/**
	 * 주차별 리스트 조회하는 SQL 을 호출한다.
	 * @param reportVO
	 * @return
	 * TraningStatusVO
	 */
	List<TraningStatusVO> listWeekTraningStatus(TraningStatusVO traningStatusVO) throws Exception;

}
