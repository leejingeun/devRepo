
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 최선호    2016. 9. 05.         First Draft.( Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.mail.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.mail.vo.MailVO;
import kr.co.sitglobal.oklms.la.mail.vo.SmsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * CRUD 쿼리를 마이바티스로 연결하는 클레스.
 * @author 이성원
 * @since 2016. 7. 20.
 */
@Mapper("mailMapper")
public interface MailMapper {

	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * List<LessonVO>
	 */
	List<MailVO> listMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * List<LessonVO>
	 */
	List<MailVO> listSmsHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	List<MailVO> listMemberMail(MailVO mailVO) throws Exception;
	
	/**
	 * 목록은 조회하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * List<LessonVO>
	 */
	List<MailVO> listMail(MailVO mailVO) throws Exception;
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * LessonVO
	 */
	MailVO getMailHistory(MailVO mailVO) throws Exception;
	
	
	/**
	 * 단건 조회하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * LessonVO
	 */
	MailVO getSmsHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	String getMemTypeEmailSet(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	String getReceiveMailYn(String memSeq) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	String getReceiveSmsYn(String memSeq) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * String
	 */
	int insertMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * 정보를 수정하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * String
	 */
	int updateMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * 정보를 등록하는 SQL 을 호출한다.
	 * @param lessonVO
	 * @return
	 * String
	 */
	int insertSendSms(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건을 등록하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * int
	 */
	int insertSms(SmsVO smsVO) throws Exception;
	
}
