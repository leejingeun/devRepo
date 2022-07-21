package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TraningProcessSearchVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1275222766691284271L;
	private String traningProcessId; 			//훈련과정아이디
	private String traningProcessName; 			//훈련과정명
	private String traningProcessNo; 			//훈련과정번호
	private String deleteYn; 					//삭제여부

	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getTraningProcessName() {
		return traningProcessName;
	}
	public void setTraningProcessName(String traningProcessName) {
		this.traningProcessName = traningProcessName;
	}
	public String getTraningProcessNo() {
		return traningProcessNo;
	}
	public void setTraningProcessNo(String traningProcessNo) {
		this.traningProcessNo = traningProcessNo;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
	

}
