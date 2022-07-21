
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.statistics.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.statistics.vo.LoginLogVO;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public interface LoginLogService {
	/**
	 * COM_LOGIN_LOG 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param loginLogVO
	 * @return
	 * List<LoginLogVO>
	 */
	public List<LoginLogVO> listLoginLog(LoginLogVO loginLogVO) throws Exception;
	
}
