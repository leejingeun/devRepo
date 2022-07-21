package kr.co.sitglobal.oklms.lu.online.service;

import java.util.List;

import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessSearchVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface OnlineTraningService {
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningAllWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 등록하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * String
	 */
	int insertOnlineTraningStand(OnlineTraningVO onlineTraningVO)throws Exception;
	
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * String
	 */
	OnlineTraningVO getOnlineTraningStand(OnlineTraningVO onlineTraningVO)throws Exception;
	
	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * String
	 */
	int updateOnlineTraningStand(OnlineTraningVO onlineTraningVO)throws Exception;

	
	/**
	 * DB에서 Data를 등록하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * String
	 */
	int insertOnlineWeekSchedule(OnlineTraningWeekVO onlineTraningWeekVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOjtTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOjtTraningCotSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	OnlineTraningSchVO getAllProgressRateLesson(OnlineTraningSchVO onlineTraningSchVO) throws Exception;

	List<OnlineTraningSchVO> listOjtTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;

	OnlineTraningSchVO getTraningStatus(OnlineTraningSchVO onlineTraningSchVO) throws Exception;

	List<OnlineTraningSchVO> listOnlineTraningCdpSchedule(
			OnlineTraningSchVO onlineTraningSchVO) throws Exception;

	List<OnlineTraningSchVO> listOfflineTraningCdpSchedule(
			OnlineTraningSchVO onlineTraningSchVO) throws Exception;

	String getOnlineTraningWeekProgressYn(OnlineTraningSchVO onlineTraningSchVO)
			throws Exception;



	

}
