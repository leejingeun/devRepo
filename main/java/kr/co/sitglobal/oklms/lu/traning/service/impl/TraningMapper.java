package kr.co.sitglobal.oklms.lu.traning.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.lu.traning.vo.TraningMemberVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningNoteVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessMappingVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningProcessSearchVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningScheduleVO;
import kr.co.sitglobal.oklms.lu.traning.vo.TraningVO; 
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("traningMapper")
public interface TraningMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param traningProcessSearchVO
	 * @return
	 * List<TraningProcessSearchVO>
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
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningSubjWeek(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningSubjWeekTime(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningSubjNcsUnit(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param TraningScheduleVO
	 * @return
	 * List<TraningScheduleVO>
	 */
	List<TraningScheduleVO> listTraningSubjNcsElem(TraningScheduleVO traningScheduleVO) throws Exception;

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
	 * OffJt td rowspan 갯수를 구한다.
	 * @param traningProcessMappingVO
	 * @return
	 * int
	 */
	int getTraningProcessCnt(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	/**
	 * Ojt td rowspan 갯수를 구한다.
	 * @param traningProcessMappingVO
	 * @return
	 * int
	 */
	int getTraningProcessMappingCnt(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningProcessInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;

	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningProcessMappingInfoList(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningProcessMappingInfoListUpdate(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회화는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	String getSubjectTraningType(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int updateMemberCompanyId(TraningProcessMappingVO traningProcessMappingVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningSubjWeekSchExcel(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningSubjWeekTimeSchExcel(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningSubjWeekNcsUnitSchExcel(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertTraningSubjWeekNcsElemSchExcel(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertSubjWeek(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertSubjWeekTime(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertSubjWeekNcsUnit(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int insertSubjWeekNcsElem(TraningScheduleVO traningScheduleVO) throws Exception;

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
	int updateTraningSubjWeekSchStatus(TraningScheduleVO traningScheduleVO) throws Exception;

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
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param traningScheduleVO
	 * @return
	 * String
	 */
	String getTraningScheduleStatus(TraningScheduleVO traningScheduleVO) throws Exception;
	
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int deletetTraningSchedule(TraningScheduleVO traningScheduleVO) throws Exception;
	
	/**
	 * DB에서 Data를 추가하는 로직을 수행한다.
	 * @param traningProcessMappingVO
	 * @return
	 * String
	 */
	int deletetTraningWeekTimeSchedule(TraningScheduleVO traningScheduleVO) throws Exception;
	
	
}
