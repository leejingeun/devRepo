
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
package kr.co.sitglobal.oklms.la.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

 /**
 * COM_MENU에 대한 CRUD 쿼리를 마이바티스로 연결하는 클레스.
* 이진근
 * @since 2016. 7. 20.
 */
@Mapper("menuMapper")
public interface MenuMapper {

	/**
	 * COM_MENU의 목록을 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listManuTempMenuOrderBefore(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listManuTempMenuOrderAfter(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getMenuCnt(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getMaxManuOrder(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getManuOrderMax(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU의 목록을 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getManuOrderTopMax(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU에서 단건 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * MenuVO
	 */
	MenuVO getMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에서 단건 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * MenuVO
	 */
	String getUpMenuId(String menuId) throws Exception;

	/**
	 *COM_MENU에  mergeInto 처리하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * MenuVO
	 */
	int saveMenu(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int insertTopMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int insertMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int insertMenuAdd(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 단건 저장하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int insertMenuTopAdd(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	void insertMenuTempCopy(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU에 정보를 수정하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 수정하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 수정하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder1(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에 정보를 수정하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder2(MenuVO menuVO) throws Exception;
	

	/**
	 * COM_MENU에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	int deleteMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	void deleteMenuAll(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU에서 정보를 삭제하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	void deleteMenuTemp(MenuVO menuVO) throws Exception;
	
	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param commandMap
	 * @return
	 * List<MenuVO>
	 */
	List<HashMap<String, Object>> listMenuTree(Map<String, Object> commandMap) throws Exception;
	
	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param commandMap
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listMenuTree(MenuVO menuVO) throws Exception;

}
