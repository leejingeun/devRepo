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
package kr.co.sitglobal.oklms.la.statistics.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_LOGIN_LOG 테이블에 대응되는 VO 클래스입니다.
 * 
 */
public class LoginLogVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = -8994999902169733862L;
	private String memSeq;		//회원 Seq
	private String loginDate;	//로그인 일자
	private String clientIp; 	//로그인 아이피
	private String logMemId;	//로그인 아이디
	private String logMemType;	//회원 구분
	private String startDate;	//조회시작일자
	private String finishDate;	//조회종료일자
	
	private String searchClientIp; 	//조회조건 로그인 아이피
	private String searchLogMemId;	//조회조건 로그인 아이디
	
	public String getSearchClientIp() {
		return searchClientIp;
	}
	public void setSearchClientIp(String searchClientIp) {
		this.searchClientIp = searchClientIp;
	}
	public String getSearchLogMemId() {
		return searchLogMemId;
	}
	public void setSearchLogMemId(String searchLogMemId) {
		this.searchLogMemId = searchLogMemId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getLogMemId() {
		return logMemId;
	}
	public void setLogMemId(String logMemId) {
		this.logMemId = logMemId;
	}
	public String getLogMemType() {
		return logMemType;
	}
	public void setLogMemType(String logMemType) {
		this.logMemType = logMemType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	
	@Override
	public String toString() {
		return "LoginLogVO [memSeq=" + memSeq + ", loginDate=" + loginDate
				+ ", clientIp=" + clientIp + ", logMemId=" + logMemId
				+ ", logMemType=" + logMemType + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + "]";
	}
}
