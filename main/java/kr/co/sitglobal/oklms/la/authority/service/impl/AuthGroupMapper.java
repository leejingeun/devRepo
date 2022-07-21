
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
package kr.co.sitglobal.oklms.la.authority.service.impl;

import java.util.List;

import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_AUTH_GROUP에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("authGroupMapper")
public interface AuthGroupMapper {

	/**
	 * COM_AUTH_GROUP의 distinct AuthGroup를 조회하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * List<AuthGroupVO>
	 */
	List<AuthGroupVO> listDistAuthGroup(AuthGroupVO authGroupVO) throws Exception;
	
	/**
	 * COM_AUTH_GROUP의 목록을 조회하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * List<AuthGroupVO>
	 */
	List<AuthGroupVO> listAuthGroup(AuthGroupVO authGroupVO) throws Exception;
	/**
	 * COM_AUTH_GROUP의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAuthGroupCnt(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP에서 단건 조회하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * AuthGroupVO
	 */
	AuthGroupVO getAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 *COM_AUTH_GROUP에  mergeInto 처리하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * AuthGroupVO
	 */
	int saveAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * String
	 */
	int insertAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP에 정보를 수정하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * String
	 */
	int updateAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * int
	 */
	int deleteAuthGroup(AuthGroupVO authGroupVO) throws Exception;

}
