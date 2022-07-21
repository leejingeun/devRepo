package kr.co.sitglobal.oklms.lu.online.service.impl;

import java.util.List;

import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchElemVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("OnlineTraningMapper")
public interface OnlineTraningMapper {

	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<onlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<onlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningCdpSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<onlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOfflineTraningCdpSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<onlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningAllWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	String getOnlineTraningWeekProgressYn(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * CompanyVO
	 */
	OnlineTraningVO getOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception;
	
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception;
	
	/**
	 * 정보를 단건 수정하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int updateOnlineTraningStand(OnlineTraningVO onlineTraningVO) throws Exception;
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertOnlineTraningWeekContent(OnlineTraningWeekVO onlineTraningWeekVO) throws Exception;
	
	/**
	 * 정보를 여러건 삭제하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int deleteOnlineTraningWeekContent(String weekId) throws Exception;
	
	
	/**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertOnlineTraningWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * 정보를 여러건 삭제하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int deleteOnlineTraningWeekSchedule(String weekId) throws Exception;
	
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningWeekSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	OnlineTraningSchVO getAllProgressRateLesson(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	OnlineTraningSchVO getTraningStatus(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	List<OnlineTraningSchVO> listOjtTraningStdSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOnlineTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOjtTraningInsSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchVO> listOjtTraningCotSchedule(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	/**
	 * 정보를 여러건 삭제하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int deleteOnlineTraningWeekScheduleElem(String weekId) throws Exception;
	
	/**
	 * 정보를 단건 등록하는 SQL 을 호출한다.
	 * @param onlineTraningSchElemVO
	 * @return
	 * String
	 */
	int insertOnlineTraningWeekScheduleElem(OnlineTraningSchElemVO onlineTraningSchElemVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param onlineTraningSchElemVO
	 * @return
	 * List<OnlineTraningSchVO>
	 */
	List<OnlineTraningSchElemVO> listOnlineTraningWeekScheduleElem(OnlineTraningSchVO onlineTraningSchVO) throws Exception;
	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * CompanyVO
	 *//*
	CompanyVO getCompany(CompanyVO companyVO) throws Exception;

	*//**
	 * 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 *//*
	int insertCompany(CompanyVO companyVO) throws Exception;
	
	*//**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * String
	 *//* 
	int updateCompany(CompanyVO companyVO) throws Exception;
	


	
	*//**
	 * 정보를 삭제하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * int
	 *//*
	int deleteCompany(CompanyVO companyVO) throws Exception;	

	
	*//**
	 * 정보를 사업자 번호 중복 체크 Count하는 SQL 을 호출한다.
	 * @param companyVO
	 * @return
	 * int
	 *//*
	int getCompanyNoCnt(CompanyVO companyVO) throws Exception;	*/
	
}
