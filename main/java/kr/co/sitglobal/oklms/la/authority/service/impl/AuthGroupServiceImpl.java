
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
import kr.co.sitglobal.oklms.la.authority.service.AuthGroupService;
import kr.co.sitglobal.oklms.la.authority.vo.AuthGroupVO;

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
@Service("authGroupService")
public class AuthGroupServiceImpl extends EgovAbstractServiceImpl implements AuthGroupService {

	private static final Logger LOG = LoggerFactory.getLogger(AuthGroupServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="authGroupIdGnrService")
    private EgovIdGnrService authGroupIdGnrService;
    
    @Resource(name = "authGroupMapper")
    private AuthGroupMapper authGroupMapper;

    
    
    
    @Override
	public List<AuthGroupVO> listDistAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("listDistAuthGroup");
		List<AuthGroupVO> data = authGroupMapper.listDistAuthGroup(authGroupVO);
		return data;
	}
    
	@Override
	public List<AuthGroupVO> listAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("listAuthGroup");
		List<AuthGroupVO> data = authGroupMapper.listAuthGroup(authGroupVO);
		return data;
	}

	@Override
	public Integer getAuthGroupCnt(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("getAuthGroupCnt");
		return authGroupMapper.getAuthGroupCnt(authGroupVO);
	}

	@Override
	public AuthGroupVO getAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("getAuthGroup");
		AuthGroupVO data = authGroupMapper.getAuthGroup(authGroupVO);
		return data;
	}


	@Override
	public int saveAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("saveAuthGroup");

		// PK Key 값 여부에따라 분기처리.( or MERGE INTO )
		if ( StringUtils.isBlank((String)authGroupVO.getAuthGroupId()) ){
			String pkStr = authGroupIdGnrService.getNextStringId();
			authGroupVO.setAuthGroupId(pkStr);
		}

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authGroupVO); // session의 정보를 VO에 추가.
 
		return authGroupMapper.saveAuthGroup(authGroupVO);
	}
	

	@Override
	public int insertAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("insertAuthGroup");
		int returnStr = 0;
		String pkStr = authGroupIdGnrService.getNextStringId();
		authGroupVO.setAuthGroupId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authGroupVO); // session의 정보를 VO에 추가.
 
		int sqlResultInt = authGroupMapper.insertAuthGroup(authGroupVO);
		if( 0 < sqlResultInt ){
			returnStr = Integer.parseInt(pkStr.equals("")?"0":pkStr);
		}
		return returnStr;
	}


	@Override
	public int updateAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("updateAuthGroup");

        LoginInfo loginInfo = new LoginInfo();
		loginInfo.putSessionToVo(authGroupVO); // session의 정보를 VO에 추가.
 
		int sqlResultInt = authGroupMapper.updateAuthGroup(authGroupVO); 
		return sqlResultInt;
	}


	@Override
	public int deleteAuthGroup(AuthGroupVO authGroupVO) throws Exception {
		LOG.debug("deleteAuthGroup");
		return authGroupMapper.deleteAuthGroup(authGroupVO);
	}


}
