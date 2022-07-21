package kr.co.sitglobal.oklms.lu.interview.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class InterviewMemberVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8909715356880349495L;
	
	private String memSeq;
	private String memId;
	private String memName;
	private String memType;
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	
}
