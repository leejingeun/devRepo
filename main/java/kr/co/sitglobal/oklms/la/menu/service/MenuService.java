
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
package kr.co.sitglobal.oklms.la.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;

import org.springframework.transaction.annotation.Transactional;

 /**
 * COM_MENU에 대한 Service Interface 입니다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface MenuService {

	/**
	 * COM_MENU 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listManuTempMenuOrderBefore(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * List<MenuVO>
	 */
	List<MenuVO> listManuTempMenuOrderAfter(MenuVO menuVO) throws Exception;
	
	/**
	 * 목록은 조회와 동일한 검색 조건을 수행하였을 때의 총 data건수를 조회하는 SQL 을 호출한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * Integer
	 */
	Integer getMenuCnt(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * MenuVO
	 */
	String getUpMenuId(String menuId) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 한건 조회하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * MenuVO
	 */
	MenuVO getMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	Integer getMaxManuOrder(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	Integer getManuOrderMax(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	Integer getManuOrderTopMax(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	String insertTopMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	String insertMenuBefore(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	String insertMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	String insertMenuAdd(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	String insertMenuTopAdd(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 추가하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	void insertMenuTempCopy(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU 에서 Data를 수정하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 수정하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 수정하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder1(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 수정하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * String
	 */
	int updateMenuMenuOrder2(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU 에서 Data를 삭제하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	int deleteMenu(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 삭제하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	void deleteMenuAll(MenuVO menuVO) throws Exception;
	
	/**
	 * COM_MENU 에서 Data를 삭제하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * int
	 */
	void deleteMenuTemp(MenuVO menuVO) throws Exception;

	/**
	 * COM_MENU 에서 PK값 여부로 Data를 추가 or 수정하는 로직을 수행한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * MenuVO
	 */
	int saveMenu(MenuVO menuVO) throws Exception;

	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param commandMap
	 * @return
	 * List<HashMap<String,Object>>
	 * @throws Exception 
	 */
	List<HashMap<String, Object>> listMenuTree(Map<String, Object> commandMap) throws Exception;

	/**
	 * 메소드에 대한 설명을 작성한다.
	 * @param menuVO
	 * @return
	 * @throws Exception
	 * List<MenuVO>
	 */
	List<MenuVO> listMenuTree(MenuVO menuVO) throws Exception;

}
