package kr.co.sitglobal.oklms.lu.attend.service;

import java.util.List;


import kr.co.sitglobal.oklms.lu.attend.vo.AttendVO;


import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface AttendService {
	
	/**
	 * DB에서 Data를 등록하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * AttendVO
	 */
	AttendVO insertAttendInfo(AttendVO attendVO)throws Exception;
	
	
	/**
	 * DB에서 Data를 수정하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * AttendVO
	 */
	AttendVO updateAttend(AttendVO attendVO)throws Exception;
	
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * AttendVO
	 */
	String getCmsLessonItemProgress(AttendVO attendVO)throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * AttendVO
	 */
	String getLessonProgress(AttendVO attendVO)throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param onlineTraningVO
	 * @return
	 * AttendVO
	 */
	String getLessonScheduleProgress(AttendVO attendVO)throws Exception;
	
	
}
