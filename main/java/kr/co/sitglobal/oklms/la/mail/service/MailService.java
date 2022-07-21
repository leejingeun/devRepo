
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
package kr.co.sitglobal.oklms.la.mail.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.mail.vo.MailVO;
import kr.co.sitglobal.oklms.la.mail.vo.SmsVO;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

 /**
 * Service 클래스에 대한 내용을 작성한다.
 * @author 최선호
 * @since 2016. 9. 05.
 */
@Transactional(rollbackFor=Exception.class)
public interface MailService {
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param lessonVO
	 * @return
	 * List<MailVO>
	 */
	List<MailVO> listMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * List<MailVO>
	 */
	List<MailVO> listSmsHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param lessonVO
	 * @return
	 * MailVO
	 */
	MailVO getMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	MailVO getSmsHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건 조회하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * MailVO
	 */
	String getMemSeqEmailSet(MailVO mailVO) throws Exception;
	
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
	String sendMail(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건을 수정하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * int
	 */
	int updateMailHistory(MailVO mailVO) throws Exception;
	
	/**
	 * DB에서 Data를 단건을 등록하는 로직을 수행한다.
	 * @param mailVO
	 * @return
	 * int
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
