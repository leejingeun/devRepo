
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
package kr.co.sitglobal.oklms.commbiz.login.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import egovframework.com.cmm.LoginVO;

 /**
 * 클래스에 대한 내용을 작성한다.
* 이진근
 * @since 2016. 07. 01.
 */
@Transactional(rollbackFor=Exception.class)
public interface CommBizLoginService {

	/**
	 * DB에서 Data를 여러건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * List<MemberVO>
	 */
	List<LoginVO> listLoginVO(LoginVO loginVO) throws Exception;


	List<Map> getSessionValue(Map<String, Object> param) throws Exception;
	List<Map> getSessionSyncString(Map<String, Object> param) throws Exception;
	void regSessionSync(Map<String, Object> pParamMap) throws Exception;
	void updSessionSync(Map<String, Object> pParamMap) throws Exception;
	void delSessionSync(Map<String, Object> pParamMap) throws Exception;


	/**
	 * 비밀번호 오류 횟수 증가 및 잔여 오류 허용 횟수 반환.
	 * @param memberVO
	 * @return
	 * int
	 */
	int updateLoginVOPwFailCnt(LoginVO loginVO) throws Exception;

	/**
	 * 사용자의 비밀번호를 임의의 문자열로 초기화 시킴.
	 * @param memberVO
	 * void
	 */
	void updateLoginVOPwInit(LoginVO loginVO) throws Exception;

	/**
	 * 로그인 시 사용만료일 업데이트
	 * @param paramMap
	 * void
	 */
	void updateLoginVOExpireYmd(Map<String, Object> paramMap) throws Exception;

	/**
	 * 최종 로그인 일시 업데이트.
	 * @param memberVO
	 * void
	 */
	void updateLoginVOLastLoginDt(LoginVO loginVO) throws Exception;

	/**
	 * 비밀번호를 입력 받은 PW로 초기화 한다. (비밀번호 찾기 팝업의 비밀번호 초기화)
	 * @param memberVO
	 * @throws Exception
	 * void
	 */
	void updateLoginVOPw(LoginVO loginVO) throws Exception;
	/**
	 * DB에서 회사정보를 한건 조회하는 로직을 수행한다.
	 * @param memberVO
	 * @return
	 * LoginVO
	 */
	LoginVO getLoginCompanyId(LoginVO loginVO) throws Exception;

}
