
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
package kr.co.sitglobal.oklms.commbiz.login.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.co.sitglobal.oklms.commbiz.login.service.CommBizLoginService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

 /**
 * 프로토타입 게시판 CRUD 비지니스 로직을 구현하는 클레스.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
@Service("commBizLoginService")
public class CommBizLoginServiceImpl extends EgovAbstractServiceImpl implements CommBizLoginService {

    
    @Resource(name = "commBizLoginMapper")
    private CommBizLoginMapper commBizLoginMapper;

	@Override
	public List<LoginVO> listLoginVO(LoginVO loginVO) throws Exception {
		// TODO Auto-generated method stub
		// egovLogger.debug("        // 항목조회
		List<LoginVO> data = commBizLoginMapper.listLoginVO(loginVO);
		return data;
	}


	@Override
	public List<Map> getSessionValue(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map> getSessionSyncString(Map<String, Object> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void regSessionSync(Map<String, Object> pParamMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updSessionSync(Map<String, Object> pParamMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delSessionSync(Map<String, Object> pParamMap) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int updateLoginVOPwFailCnt(LoginVO loginVO) throws Exception {

		String pwFailMaxCnt = EgovProperties.getProperty("Globals.loginPWFailMaxCnt");
		commBizLoginMapper.updateLoginVOPwFailCnt(loginVO);
		int cnt = Integer.parseInt(pwFailMaxCnt) - loginVO.getPwErrNumberTimes();
		return cnt;
	}

	@Override
	public void updateLoginVOPwInit(LoginVO loginVO) throws Exception {
		// TODO Auto-generated method stub

		String initPw = RandomStringUtils.randomAlphanumeric(8);
		loginVO.setMemPasswordEncript(initPw);
		commBizLoginMapper.updateLoginVOPwInit(loginVO);
	}

	@Override
	public void updateLoginVOExpireYmd(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		commBizLoginMapper.updateLoginVOExpireYmd(paramMap);
	}

	@Override
	public void updateLoginVOLastLoginDt(LoginVO loginVO) throws Exception {
		// TODO Auto-generated method stub
		commBizLoginMapper.updateLoginVOLastLoginDt(loginVO);
		
	}

	@Override
	public void updateLoginVOPw(LoginVO loginVO) throws Exception {
		// TODO Auto-generated method stub
		commBizLoginMapper.updateLoginVOPw(loginVO);
		
	}

	@Override
	public LoginVO getLoginCompanyId(LoginVO loginVO) throws Exception {
		// TODO Auto-generated method stub
		return commBizLoginMapper.getLoginCompanyId(loginVO);
	}

}
