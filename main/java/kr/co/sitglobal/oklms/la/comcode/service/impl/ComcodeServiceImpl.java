
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
package kr.co.sitglobal.oklms.la.comcode.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;
import kr.co.sitglobal.oklms.la.comcode.service.ComcodeService;
import kr.co.sitglobal.oklms.la.comcode.vo.ComcodeVO;

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
@Service("comcodeService")
public class ComcodeServiceImpl extends EgovAbstractServiceImpl implements ComcodeService {

	private static final Logger LOG = LoggerFactory.getLogger(ComcodeServiceImpl.class);
	
	/** ID Generation */
    @Resource(name="comcodeIdGnrService")
    private EgovIdGnrService comcodeIdGnrService;
    
    @Resource(name = "comcodeMapper")
    private ComcodeMapper comcodeMapper;

	@Override
	public List<ComcodeVO> listComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("listComcode");
		List<ComcodeVO> data = comcodeMapper.listComcode(comcodeVO);
		return data;
	}

	@Override
	public Integer getComcodeCnt(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("getComcodeCnt");
		return comcodeMapper.getComcodeCnt(comcodeVO);
	}

	@Override
	public ComcodeVO getComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("getComcode");
		ComcodeVO data = comcodeMapper.getComcode(comcodeVO);
		return data;
	}


	@Override
	public int saveComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("saveComcode");

		// PK Key 값 여부에따라 분기처리.( or MERGE INTO )
		if ( StringUtils.isBlank((String)comcodeVO.getCodeId()) ){
			String pkStr = comcodeIdGnrService.getNextStringId();
			comcodeVO.setCodeId(pkStr);
		}

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(comcodeVO); // session의 정보를 VO에 추가.
 
		return comcodeMapper.saveComcode(comcodeVO);
	}
	

	@Override
	public String insertComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("insertComcode");
		String returnStr = "";
		String pkStr = comcodeIdGnrService.getNextStringId();
		comcodeVO.setCodeId(pkStr);

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(comcodeVO); // session의 정보를 VO에 추가.

		int sqlResultInt = comcodeMapper.insertComcode(comcodeVO);
		if( 0 < sqlResultInt ){
			returnStr = pkStr;
		}
		return returnStr;
	}


	@Override
	public int updateComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("updateComcode");

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.putSessionToVo(comcodeVO); // session의 정보를 VO에 추가.
 
		int sqlResultInt = comcodeMapper.updateComcode(comcodeVO); 
		return sqlResultInt;
	}


	@Override
	public int deleteComcode(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("deleteComcode");
		return comcodeMapper.deleteComcode(comcodeVO);
	}

	@Override
	public List<ComcodeVO> listComcodeGroup(ComcodeVO comcodeVO) throws Exception {
		LOG.debug("listComcodeGroup");
		List<ComcodeVO> data = comcodeMapper.listComcodeGroup(comcodeVO);
		return data;
	}


}
