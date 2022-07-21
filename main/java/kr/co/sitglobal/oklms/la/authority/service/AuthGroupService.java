
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
package kr.co.sitglobal.oklms.la.authority.service;

import java.util.List;

import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_AUTH_GROUP에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface AuthGroupService {

	
	
	
	/**
	 * COM_AUTH_GROUP 에서 distinct AuthGroup를 조회하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * List<AuthGroupVO>
	 */
	List<AuthGroupVO> listDistAuthGroup(AuthGroupVO authGroupVO) throws Exception;
	
	/**
	 * COM_AUTH_GROUP 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * List<AuthGroupVO>
	 */
	List<AuthGroupVO> listAuthGroup(AuthGroupVO authGroupVO) throws Exception;
	
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param authGroupVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAuthGroupCnt(AuthGroupVO authGroupVO) throws Exception;
	
	/**
	 * COM_AUTH_GROUP 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * AuthGroupVO
	 */
	AuthGroupVO getAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP 에서 Data를 추가하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * String
	 */
	int insertAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP 에서 Data를 수정하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * String
	 */
	int updateAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP 에서 Data를 삭제하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * int
	 */
	int deleteAuthGroup(AuthGroupVO authGroupVO) throws Exception;

	/**
	 * COM_AUTH_GROUP 에서 PK값 여부로 Data를 추가 or 수정하는 로직을 수행한다.
	 * @param authGroupVO
	 * @return
	 * @throws Exception
	 * AuthGroupVO
	 */
	int saveAuthGroup(AuthGroupVO authGroupVO) throws Exception;

}
