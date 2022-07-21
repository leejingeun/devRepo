
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
package kr.co.sitglobal.oklms.commbiz.log.service;

import java.util.List;

import kr.co.sitglobal.oklms.commbiz.log.vo.ComLoginLogVO;

import org.springframework.transaction.annotation.Transactional;

/**
* COM_LOGIN_LOG에 대한 Service Interface 입니다.
* 이진근
* @since 2016. 07. 01.
*/
@Transactional(rollbackFor=Exception.class)
public interface ComLoginLogService {

	/**
	 * COM_LOGIN_LOG 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param comLoginLogVO
	 * @return
	 * List<ComLoginLogVO>
	 */
	List<ComLoginLogVO> listComLoginLog(ComLoginLogVO comLoginLogVO) throws Exception;
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param comLoginLogVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getComLoginLogCnt(ComLoginLogVO comLoginLogVO) throws Exception;
	

	/**
	 * COM_LOGIN_LOG 에서 Data를 추가하는 로직을 수행한다.
	 * @param comLoginLogVO
	 * @return
	 * String
	 */
	String insertComLoginLog(ComLoginLogVO comLoginLogVO) throws Exception;

}
