
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.comcode.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_COMCODE에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface ComcodeService {

	/**
	 * COM_COMCODE 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * List<ComcodeVO>
	 */
	List<ComcodeVO> listComcode(ComcodeVO comcodeVO) throws Exception;
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getComcodeCnt(ComcodeVO comcodeVO) throws Exception;
	
	/**
	 * COM_COMCODE 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * ComcodeVO
	 */
	ComcodeVO getComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE 에서 Data를 추가하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * String
	 */
	String insertComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE 에서 Data를 수정하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * String
	 */
	int updateComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE 에서 Data를 삭제하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * int
	 */
	int deleteComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE 에서 PK값 여부로 Data를 추가 or 수정하는 로직을 수행한다.
	 * @param comcodeVO
	 * @return
	 * @throws Exception
	 * ComcodeVO
	 */
	int saveComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * 코드 그룹 목록을 조회한다.
	 * @param comcodeVO
	 * @return
	 * @throws Exception
	 * List<ComcodeVO>
	 */
	List<ComcodeVO> listComcodeGroup(ComcodeVO comcodeVO) throws Exception;
}
