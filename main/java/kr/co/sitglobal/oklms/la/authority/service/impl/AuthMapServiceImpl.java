
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
package kr.co.sitglobal.oklms.la.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.la.authority.service.AuthMapService;
import kr.co.sitglobal.oklms.la.authority.vo.AuthMapVO;

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
@Service("authMapService")
public class AuthMapServiceImpl extends EgovAbstractServiceImpl implements AuthMapService {

	private static final Logger LOG = LoggerFactory.getLogger(AuthMapServiceImpl.class);

        
    
    @Resource(name = "authMapMapper")
    private AuthMapMapper authMapMapper;

	@Override
	public List<AuthMapVO> listAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("listAuthMap");
		List<AuthMapVO> data = authMapMapper.listAuthMap(authMapVO);
		return data;
	}
	@Override
	public List<AuthMapVO> listAuthMapTree(AuthMapVO authMapVO) throws Exception {
		LOG.debug("listAuthMapTree");
		List<AuthMapVO> data = authMapMapper.listAuthMapTree(authMapVO);
		return data;
	}

	@Override
	public Integer getAuthMapCnt(AuthMapVO authMapVO) throws Exception {
		LOG.debug("getAuthMapCnt");
		return authMapMapper.getAuthMapCnt(authMapVO);
	}

	@Override
	public AuthMapVO getAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("getAuthMap");
		AuthMapVO data = authMapMapper.getAuthMap(authMapVO);
		return data;
	}


	@Override
	public int saveAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("saveAuthMap");

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authMapVO); // session의 정보를 VO에 추가.
 
		return authMapMapper.saveAuthMap(authMapVO);
	}
	

	@Override
	public int insertAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("insertAuthMap");
		String returnStr = "";

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authMapVO); // session의 정보를 VO에 추가.

		int sqlResultInt = 0;
		String pkStr = "";
		//if( StringUtils.isNoneBlank( authMapVO.getMenuId() ) && StringUtils.isNoneBlank( authMapVO.getAuthGroupId() ) ){
			
			sqlResultInt = authMapMapper.insertAuthMap(authMapVO);
			
			//pkStr = authMapVO.getMenuId() + "^" + authMapVO.getAuthGroupId();
		//}
//		if( 0 < sqlResultInt ){
//			returnStr = pkStr;
//		}
		return sqlResultInt;
	}


	@Override
	public int updateAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("updateAuthMap");
        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authMapVO); // session의 정보를 VO에 추가. 

		int sqlResultInt = 0;
		//if( StringUtils.isNoneBlank( authMapVO.getMenuId() ) && StringUtils.isNoneBlank( authMapVO.getAuthGroupId() ) ){
			
			sqlResultInt = authMapMapper.updateAuthMap(authMapVO);
		//}
		  
		return sqlResultInt;
	}


	@Override
	public int deleteAuthMap(AuthMapVO authMapVO) throws Exception {
		LOG.debug("deleteAuthMap");
		int sqlResultInt = 0;
		
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authMapVO); // session의 정보를 VO에 추가. 
		
		if( StringUtils.isNoneBlank( authMapVO.getMenuId() ) && StringUtils.isNoneBlank( authMapVO.getAuthGroupId() ) ){
			
			sqlResultInt = authMapMapper.deleteAuthMap(authMapVO);
		}
		return sqlResultInt;
	}


}
