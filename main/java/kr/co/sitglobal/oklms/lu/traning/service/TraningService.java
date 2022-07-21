package kr.co.sitglobal.oklms.lu.traning.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
 



import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO; 

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Transactional(rollbackFor=Exception.class)
public interface TraningService {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<TraningVO>
	 */
	List<TraningVO> listTraningProcess(TraningVO traningVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * List<TraningVO>
	 */
	List<TraningVO> listTraningProcessManage(TraningVO traningVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningProcessMappingVO
	 * @return
	 * List<TraningProcessMappingVO>
	 */
	List<TraningProcessMappingVO> listOjtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningProcessMappingVO
	 * @return
	 * List<TraningProcessMappingVO>
	 */
	List<TraningProcessMappingVO> listOffJtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningProcessMappingVO
	 * @return
	 * List<TraningProcessMappingVO>
	 */
	List<TraningProcessMappingVO> listTraningProcessInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningScheduleExcelDownload(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningScheduleView(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningScheduleModifyView(TraningScheduleVO traningScheduleVO) throws Exception;

	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param TraningProcessMappingVO
	 * @return
	 * TraningProcessMappingVO
	 */
	TraningProcessMappingVO getOffJtSubjectInfo(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * TraningScheduleVO
	 */
	TraningScheduleVO getTraningSubjWeekInfo(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * TraningScheduleVO
	 */
	TraningScheduleVO getTraningSubjWeekTimeSum(TraningScheduleVO traningScheduleVO) throws Exception;

	/**
	 * OffJt td rowspan 갯수를 구한다.
	 * @param traningVO
	 * @return
	 * int
	 */
	int getTdOffJtTotalCnt(TraningVO traningVO) throws Exception;

	/**
	 * Ojt td rowspan 갯수를 구한다.
	 * @param traningVO
	 * @return
	 * int
	 */
	int getTdOjtTotalCnt(TraningVO traningVO) throws Exception;

	/**
	 * Ojt td rowspan 갯수를 구한다.
	 * @param traningProcessMappingVO
	 * @return
	 * int
	 */
	int getTraningProcessMappingCnt(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

     /**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertTraningProcessInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	 /**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertTraningProcessMappingInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	 /**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param companyVO
	 * @return
	 * String
	 */
	int insertTraningProcessMappingInfoListUpdate(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	String insertTraningScheduleExcel(TraningScheduleVO traningScheduleVO, MultipartHttpServletRequest multiRequest,HttpServletRequest request) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	String insertTraningSchedule(TraningScheduleVO traningScheduleVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int updateTraningProcessMappingInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int updateTraningSubjWeekSchFileId(TraningScheduleVO traningScheduleVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int deleteTraningProcessInfoList(TraningVO traningVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int deleteTraningProcessMappingInfoList(TraningVO traningVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int deleteTraningProcessMappingInfoSubjList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 조회하는 로직을 수행한다.
	 * @param traningScheduleVO
	 * @return
	 * String
	 */
	String getTraningScheduleStatus(TraningScheduleVO traningScheduleVO)throws Exception; 
	
	
	/**
	 * DB에서 Data를 조회하는 로직을 수행한다.
	 * @param traningScheduleVO
	 * @return
	 * String
	 */
	int deleteTraningSchedule(TraningScheduleVO traningScheduleVO) throws Exception;
	

}
