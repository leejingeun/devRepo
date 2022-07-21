
/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 7. 20.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.la.comcode.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_COMCODE에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("comcodeMapper")
public interface ComcodeMapper {

	/**
	 * COM_COMCODE의 목록을 조회하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * List<ComcodeVO>
	 */
	List<ComcodeVO> listComcode(ComcodeVO comcodeVO) throws Exception;
	/**
	 * COM_COMCODE의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getComcodeCnt(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE에서 단건 조회하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * ComcodeVO
	 */
	ComcodeVO getComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 *COM_COMCODE에  mergeInto 처리하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * ComcodeVO
	 */
	int saveComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * String
	 */
	int insertComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE에 정보를 수정하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * String
	 */
	int updateComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * COM_COMCODE에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param comcodeVO
	 * @return
	 * int
	 */
	int deleteComcode(ComcodeVO comcodeVO) throws Exception;

	/**
	 * 코드 그룹 목록을 조회한다.
	 * @param comcodeVO
	 * @return
	 * @throws Exception
	 * List<ComcodeVO>
	 */
	List<ComcodeVO> listComcodeGroup(ComcodeVO comcodeVO) throws Exception;
	
}
