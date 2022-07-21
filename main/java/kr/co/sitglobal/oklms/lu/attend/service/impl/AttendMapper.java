package kr.co.sitglobal.oklms.lu.attend.service.impl;

import java.util.List;

import kr.co.sitglobal.ifx.vo.CmsCourseContentVO;
import kr.co.sitglobal.oklms.lu.attend.vo.AttendVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningSchVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningVO;
import kr.co.sitglobal.oklms.lu.online.vo.OnlineTraningWeekVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AttendMapper")
public interface AttendMapper {
	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */ 
	String getProgressId(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */ 
	String getCmsProgressId(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertProgress(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertCmsProgress(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getTimeCount(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertTime(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer updateTime(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getTimeMaxCount(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertTimeMax(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer updateTimeMax(AttendVO attendVO) throws Exception;
	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getPageProgressCount(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertPageProgress(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getPageProgressSumCount(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	AttendVO getPageProgressInfo(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 저장하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer insertPageProgressSum(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 수정하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer updatePageProgressSum(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getCmsTimeProgress(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer getTimeProgress(AttendVO attendVO) throws Exception;
	
	
	/**
	 * 단건을 수정하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	Integer updateTimeProgress(AttendVO attendVO) throws Exception;
	
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * String
	 */
	String getCmsLessonItemProgress(AttendVO attendVO) throws Exception;
	
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * String
	 */
	String getLessonProgress(AttendVO attendVO) throws Exception;
	
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	String getLessonScheduleProgress(AttendVO attendVO) throws Exception;
	
	/**
	 * 단건을 조회하는 SQL 을 호출한다.
	 * @param attendVO
	 * @return
	 * Interger
	 */
	String getWeekProgressRate(AttendVO attendVO) throws Exception;
	

}
