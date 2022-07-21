
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

import kr.co.sitglobal.oklms.la.authority.vo.AuthMapVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_AUTH_MAP에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface AuthMapService {

	/**
	 * COM_AUTH_MAP 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * List<AuthMapVO>
	 */
	List<AuthMapVO> listAuthMap(AuthMapVO authMapVO) throws Exception;
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param authMapVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getAuthMapCnt(AuthMapVO authMapVO) throws Exception;
	
	/**
	 * COM_AUTH_MAP 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * AuthMapVO
	 */
	AuthMapVO getAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP 에서 Data를 추가하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * String
	 */
	int insertAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP 에서 Data를 수정하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * String
	 */
	int updateAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP 에서 Data를 삭제하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * int
	 */
	int deleteAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * COM_AUTH_MAP 에서 PK값 여부로 Data를 추가 or 수정하는 로직을 수행한다.
	 * @param authMapVO
	 * @return
	 * @throws Exception
	 * AuthMapVO
	 */
	int saveAuthMap(AuthMapVO authMapVO) throws Exception;

	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param authMapVO
	 * @return
	 * List<AuthMapVO>
	 * @throws Exception 
	 */
	List<AuthMapVO> listAuthMapTree(AuthMapVO authMapVO) throws Exception;

}
