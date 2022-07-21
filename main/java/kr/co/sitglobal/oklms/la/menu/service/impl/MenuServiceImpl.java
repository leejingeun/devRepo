
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
package kr.co.sitglobal.oklms.la.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.la.menu.service.MenuService;
import kr.co.sitglobal.oklms.la.menu.vo.MenuVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
//import org.apache.commons.beanutils.BeanUtils;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {

	private static final Logger LOG = LoggerFactory.getLogger(MenuServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="menuIdGnrService")
    private EgovIdGnrService menuIdGnrService;
    
    @Resource(name = "menuMapper")
    private MenuMapper menuMapper;

	@Override
	public List<MenuVO> listMenu(MenuVO menuVO) throws Exception {
		LOG.debug("listMenu");
		List<MenuVO> data = menuMapper.listMenu(menuVO);
		return data;
	}
	
	@Override
	public List<MenuVO> listManuTempMenuOrderBefore(MenuVO menuVO) throws Exception {
		LOG.debug("listManuTempMenuOrderBefore");
		List<MenuVO> data = menuMapper.listManuTempMenuOrderBefore(menuVO);
		return data;
	}
	
	@Override
	public List<MenuVO> listManuTempMenuOrderAfter(MenuVO menuVO) throws Exception {
		LOG.debug("listManuTempMenuOrderAfter");
		List<MenuVO> data = menuMapper.listManuTempMenuOrderAfter(menuVO);
		return data;
	}

	@Override
	public Integer getMenuCnt(MenuVO menuVO) throws Exception {
		LOG.debug("getMenuCnt");
		return menuMapper.getMenuCnt(menuVO);
	}
	
	@Override
	public Integer getMaxManuOrder(MenuVO menuVO) throws Exception {
		LOG.debug("getMaxManuOrder");
		return menuMapper.getMaxManuOrder(menuVO);
	}
	
	@Override
	public Integer getManuOrderMax(MenuVO menuVO) throws Exception {
		LOG.debug("getManuOrderMax");
		return menuMapper.getManuOrderMax(menuVO);
	}
	
	@Override
	public Integer getManuOrderTopMax(MenuVO menuVO) throws Exception {
		LOG.debug("getManuOrderTopMax");
		return menuMapper.getManuOrderTopMax(menuVO);
	}

	@Override
	public MenuVO getMenu(MenuVO menuVO) throws Exception {
		LOG.debug("getMenu");
		MenuVO data = menuMapper.getMenu(menuVO);
		return data;
	}
	
	@Override
	public String getUpMenuId(String menuId) throws Exception {
		LOG.debug("getUpMenuId");
		return menuMapper.getUpMenuId(menuId);
	}

	@Override
	public int saveMenu(MenuVO menuVO) throws Exception {
		LOG.debug("saveMenu");

		// PK Key 값 여부에따라 분기처리.( or MERGE INTO )
		if ( StringUtils.isBlank((String)menuVO.getMenuId()) ){
			String pkStr = menuIdGnrService.getNextStringId();
			menuVO.setMenuId(pkStr);
		}

        LoginInfo loginInfo = new LoginInfo();
//        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
//	      //BeanUtils.copyProperties(loginVO,menuVO); // 객체 전체를 복사 하는 경우. menuVO에서 loginVO와 일치하는 모든 필드의 값을 복사한다. 
		  loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가.
//        menuVO.setSessionMemSeq(loginVO.getSessionMemSeq()); 
		return menuMapper.saveMenu(menuVO);
	}
	
	@Override
	public String insertTopMenu(MenuVO menuVO) throws Exception {
		LOG.debug("insertTopMenu");
		String returnStr = "";
		String pkStr = menuIdGnrService.getNextStringId();
		menuVO.setMenuId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
	    loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 
 
		int sqlResultInt = menuMapper.insertTopMenu(menuVO);
		if( 0 < sqlResultInt ){
			returnStr = pkStr;
		}
		
		return returnStr;
	}
	
	@Override
	public String insertMenuBefore(MenuVO menuVO) throws Exception {
		LOG.debug("insertMenuBefore");
		String returnStr = "SUCESS";

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.insertMenu(menuVO);
		if( 0 < sqlResultInt ){
			returnStr = returnStr;
		}
		return returnStr;
	}

	@Override
	public String insertMenu(MenuVO menuVO) throws Exception {
		LOG.debug("insertMenu");
		String returnStr = "";
		String pkStr = menuIdGnrService.getNextStringId();
		menuVO.setMenuId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 
		  
//        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
//	      //BeanUtils.copyProperties(loginVO,menuVO); // 객체 전체를 복사 하는 경우. menuVO에서 loginVO와 일치하는 모든 필드의 값을 복사한다. 
//        menuVO.setSessionMemSeq(loginVO.getSessionMemSeq()); 
		int sqlResultInt = menuMapper.insertMenu(menuVO);
		if( 0 < sqlResultInt ){
			returnStr = pkStr;
		}
		return returnStr;
	}
	
	@Override
	public String insertMenuAdd(MenuVO menuVO) throws Exception {
		LOG.debug("insertMenuAdd");
		String returnStr = "";
		String menuIdTemp = "";
		menuIdTemp = menuVO.getMenuId();
		menuVO.setMenuIdTemp(menuIdTemp);
		
		String pkStr = menuIdGnrService.getNextStringId();
		menuVO.setMenuId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.insertMenuAdd(menuVO);
		if( 0 < sqlResultInt ){
			returnStr = pkStr;
		}
		return returnStr;
	}
	
	@Override
	public String insertMenuTopAdd(MenuVO menuVO) throws Exception {
		LOG.debug("insertMenuTopAdd");
		String returnStr = "";
		String menuIdTemp = "";
		menuIdTemp = menuVO.getMenuId();
		menuVO.setMenuIdTemp(menuIdTemp);
		
		String pkStr = menuIdGnrService.getNextStringId();
		menuVO.setMenuId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.insertMenuTopAdd(menuVO);
		if( 0 < sqlResultInt ){
			returnStr = pkStr;
		}
		return returnStr;
	}
	
	@Override
	public void insertMenuTempCopy(MenuVO menuVO) throws Exception {
		LOG.debug("insertMenuTempCopy");
		menuMapper.insertMenuTempCopy(menuVO);
	}

	@Override
	public int updateMenu(MenuVO menuVO) throws Exception {
		LOG.debug("updateMenu");
        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

//        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
//	      //BeanUtils.copyProperties(loginVO,menuVO); // 객체 전체를 복사 하는 경우. menuVO에서 loginVO와 일치하는 모든 필드의 값을 복사한다. 
//        menuVO.setSessionMemSeq(loginVO.getSessionMemSeq()); 
		int sqlResultInt = menuMapper.updateMenu(menuVO); 
		return sqlResultInt;
	}
	
	@Override
	public int updateMenuMenuOrder(MenuVO menuVO) throws Exception {
		LOG.debug("updateMenuMenuOrder");
        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.updateMenuMenuOrder(menuVO); 
		return sqlResultInt;
	}
	
	@Override
	public int updateMenuMenuOrder1(MenuVO menuVO) throws Exception {
		LOG.debug("updateMenuMenuOrder1");
        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.updateMenuMenuOrder1(menuVO); 
		return sqlResultInt;
	}
	
	@Override
	public int updateMenuMenuOrder2(MenuVO menuVO) throws Exception {
		LOG.debug("updateMenuMenuOrder2");
        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(menuVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = menuMapper.updateMenuMenuOrder2(menuVO); 
		return sqlResultInt;
	}
	
	@Override
	public int deleteMenu(MenuVO menuVO) throws Exception {
		LOG.debug("deleteMenu");
		return menuMapper.deleteMenu(menuVO);
	}
	
	@Override
	public void deleteMenuAll(MenuVO menuVO) throws Exception {
		LOG.debug("deleteMenuAll");
		menuMapper.deleteMenuAll(menuVO);
	}
	
	@Override
	public void deleteMenuTemp(MenuVO menuVO) throws Exception {
		LOG.debug("deleteMenuTemp");
		menuMapper.deleteMenuTemp(menuVO);
	}

	@Override
	public List<MenuVO> listMenuTree(MenuVO menuVO) throws Exception {

		List<MenuVO> data = menuMapper.listMenuTree(menuVO);
		return data;
	}

	@Override
	public List<HashMap<String, Object>> listMenuTree(Map<String, Object> commandMap) throws Exception {

		List<HashMap<String, Object>> data = menuMapper.listMenuTree(commandMap);
		return data;
	}

}
