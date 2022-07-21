package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * 학습
 * @author user
 *
 */
public class TraningMemberVO extends BaseVO implements Serializable{


	private static final long serialVersionUID = -5429986145304388969L;
	private String memId; //학번
	private String companyId; //업체코드
	private String memName; //학습글로자 일므
	private String companyName; //업체명
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



}
