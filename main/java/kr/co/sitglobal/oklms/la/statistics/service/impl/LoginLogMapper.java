
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 7. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.statistics.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.statistics.vo.LoginLogVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_MENU에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("loginLogMapper")
public interface LoginLogMapper {

	/**
	 * LoginLogVO의 전체 목록을 조회하는 SQL 을 호출한다.
	 * @param loginLogVO
	 * @return
	 * List<LoginLogVO>
	 */
	List<LoginLogVO> listLoginLog(LoginLogVO loginLogVO);
	
}
