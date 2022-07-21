
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
import kr.co.sitglobal.oklms.la.authority.vo.AuthMapVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_AUTH_MAP에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("authMapMapper")
public interface AuthMapMapper {

	/**
	 * COM_AUTH_MAP 에서 distinct AuthGroup를 조회하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * List<AuthMapVO>
	 */
	List<AuthMapVO> listDistAuthGroup(AuthMapVO authMapVO) throws Exception;
	
	
	/**
	 * COM_AUTH_MAP의 목록을 조회하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * List<AuthMapVO>
	 */
	List<AuthMapVO> listAuthMap(AuthMapVO authMapVO) throws Exception;
	/**
	 * COM_AUTH_MAP의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAuthMapCnt(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP에서 단건 조회하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * AuthMapVO
	 */
	AuthMapVO getAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 *COM_AUTH_MAP에  mergeInto 처리하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * AuthMapVO
	 */
	int saveAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * String
	 */
	int insertAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP에 정보를 수정하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * String
	 */
	int updateAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * int
	 */
	int deleteAuthMap(AuthMapVO authMapVO) throws Exception;
	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param authMapVO
	 * @return
	 * List<AuthMapVO>
	 */
	List<AuthMapVO> listAuthMapTree(AuthMapVO authMapVO);

}
