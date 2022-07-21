
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
package kr.co.sitglobal.oklms.la.statistics.service.impl;

import java.util.List;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.la.popup.vo.PopupVO;
import kr.co.sitglobal.oklms.la.statistics.service.LoginLogService;
import kr.co.sitglobal.oklms.la.statistics.vo.LoginLogVO;

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
@Service("loginLogService")
public class LoginLogServiceImpl extends EgovAbstractServiceImpl implements LoginLogService{

	private static final Logger LOG = LoggerFactory.getLogger(LoginLogServiceImpl.class);
	
    @Resource(name = "loginLogMapper")
    private LoginLogMapper loginLogMapper;
	
    /**
	 * LoginLogVO의 전체 목록을 조회하는 SQL 을 호출한다.
	 * @param loginLogVO
	 * @return
	 * List<LoginLogVO>
	 */
    public List<LoginLogVO> listLoginLog(LoginLogVO loginLogVO){
		LOG.debug("listLoginLog");
		List<LoginLogVO> data = loginLogMapper.listLoginLog(loginLogVO);
		return data;
	}
}
