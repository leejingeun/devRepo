
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
package kr.co.sitglobal.oklms.commbiz.menu.service.impl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.menu.service.CommbizMenuService;
import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;
import kr.co.sitglobal.oklms.commbiz.menu.web.CommbizMenuController;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
//import org.apache.commons.beanutils.BeanUtils;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("commbizMenuService")
public class CommbizMenuServiceImpl extends EgovAbstractServiceImpl implements CommbizMenuService {

	private static final Logger LOG = LoggerFactory.getLogger(CommbizMenuServiceImpl.class);
	
    @Resource(name = "commbizMenuMapper")
    private CommbizMenuMapper commbizMenuMapper;

    
	@Override
	public ArrayList<CommbizMenuVO> listMenu(Map<String, Object> commandMap) throws Exception{
		
		if( !commandMap.containsKey("authGroupId") ){
			
			commandMap.put("authGroupId", "2016AUTH0000004");
			LOG.debug("#### default authGroupId Setting : 2016AUTH0000004");
		}else{
			if( StringUtils.isBlank( (String)commandMap.get("authGroupId") ) ) {

				commandMap.put("authGroupId", "2016AUTH0000004");
				LOG.debug("#### default authGroupId Setting : 2016AUTH0000004");
			}
		}
		
		return commbizMenuMapper.listMenu(commandMap);
	}
	@Override
	public ArrayList<CommbizMenuVO> mobilelistMenu(Map<String, Object> commandMap) throws Exception{
		
		if( !commandMap.containsKey("authGroupId") ){
			
			commandMap.put("authGroupId", "2016AUTH0000004");
			LOG.debug("#### default authGroupId Setting : 2016AUTH0000004");
		}else{
			if( StringUtils.isBlank( (String)commandMap.get("authGroupId") ) ) {

				commandMap.put("authGroupId", "2016AUTH0000004");
				LOG.debug("#### default authGroupId Setting : 2016AUTH0000004");
			}
		}
		
		return commbizMenuMapper.mobilelistMenu(commandMap);
	}
}
