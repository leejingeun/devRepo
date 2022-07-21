/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.log.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_LOGIN_LOG 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class ComLoginLogVO extends BaseVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8106019810606558513L;
	private String memSeq;
	private java.sql.Date loginDate;
	private String clientIp;
	private String memId;
	private String memType;

	public String getMemSeq() {
		return this.memSeq;
	}

	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}

	public java.sql.Date getLoginDate() {
		return this.loginDate;
	}

	public void setLoginDate(java.sql.Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getClientIp() {
		return this.clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getMemId() {
		return this.memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemType() {
		return this.memType;
	}

	public void setMemType(String memType) {
		this.memType = memType;
	}
	
	public String toString() {
        final String NL  = "\n";
        final String TAB = "    ";
        
        String retValue = "";
        
        retValue = "ComLoginLogVO ( "
            + super.toString() + NL
            + TAB + "memSeq = " + this.memSeq + NL
            + TAB + "loginDate = " + this.loginDate + NL
            + TAB + "clientIp = " + this.clientIp + NL
            + TAB + "memId = " + this.memId + NL
            + TAB + "memType = " + this.memType + NL
            + " )";
    
        return retValue;
    }

}
