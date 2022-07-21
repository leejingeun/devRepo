package kr.co.sitglobal.oklms.comm.service.impl;

import java.util.ArrayList;
import java.util.List;

import kr.co.sitglobal.oklms.comm.vo.LoginInfo;

import org.springframework.web.context.request.RequestContextHolder;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovUserDetailsService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

public class SitGlobalUserDetailsSessionServiceImpl extends EgovAbstractServiceImpl implements EgovUserDetailsService {

	public Object getAuthenticatedUser() {

		LoginInfo loginInfo = new LoginInfo();
        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
        
//		if (RequestContextHolder.getRequestAttributes() == null) {
//			return null;
//		}

		return loginVO;

	}

	public List<String> getAuthorities() {

		// 권한 설정을 리턴한다.
		List<String> listAuth = new ArrayList<String>();

		return listAuth;
	}

	public Boolean isAuthenticated() {
		// 인증된 유저인지 확인한다.

		if (RequestContextHolder.getRequestAttributes() == null) {
			return false;
		} else {

			LoginInfo loginInfo = new LoginInfo();
	        LoginVO loginVO = (LoginVO) loginInfo.getLoginInfo();
	        
			if ( loginVO == null) {
				return false;
			} else {
				return true;
			}
		}

	}

}
