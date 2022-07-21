
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
package kr.co.sitglobal.oklms.commbiz.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.log.service.ComLoginLogService;
import kr.co.sitglobal.oklms.commbiz.log.vo.ComLoginLogVO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

 /**
 * Service Implements 클레스 : 비지니스 로직을 구현하는 클레스.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("comLoginLogService")
public class ComLoginLogServiceImpl extends EgovAbstractServiceImpl implements ComLoginLogService {

	/** ID Generation */
//    @Resource(name="ComLoginLogIdGnrService")
//    private EgovIdGnrService ComLoginLogIdGnrService;
    
    @Resource(name = "comLoginLogMapper")
    private ComLoginLogMapper comLoginLogMapper;

	@Override
	public List<ComLoginLogVO> listComLoginLog(ComLoginLogVO comLoginLogVO) throws Exception {
		// TODO Auto-generated method stub
		List<ComLoginLogVO> data = comLoginLogMapper.listComLoginLog(comLoginLogVO);
		return data;
	}

	@Override
	public Integer getComLoginLogCnt(ComLoginLogVO comLoginLogVO) throws Exception {
		// TODO Auto-generated method stub
		return comLoginLogMapper.getComLoginLogCnt(comLoginLogVO);
	}

	

	@Override
	public String insertComLoginLog(ComLoginLogVO comLoginLogVO) throws Exception {
		String returnStr = "";

		int sqlResultInt = comLoginLogMapper.insertComLoginLog(comLoginLogVO);
		return returnStr;
	}
}
