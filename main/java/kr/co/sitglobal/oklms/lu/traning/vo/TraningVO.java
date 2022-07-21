package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author leejingeun
 *
 */
public class TraningVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -1329823451159588771L;

	private String traningProcessManageId;       //훈련과정관리아이디
	private String traningProcessId; 			//훈련과정아이디
	private String tempTraningProcessId;
	private String traningProcessIdArr;         //훈련과정아이디 배열
	private String companyId;					//기업아이디
	private String companyName;					//기업명
	private String hrdTraningNo; 				//HRD-NET 훈련과정번호
	private String hrdTraningName; 				//HRD-NET 훈련과정명
	private String traningProcessName; 			//훈련과정명
	private String traningProcessNo; 			//훈련과정번호
	private String deleteYn;					//삭제여부
	private String address;
	private String choiceDay;
	private String employInsManageNo;

	public String getTraningProcessManageId() {
		return traningProcessManageId;
	}
	public void setTraningProcessManageId(String traningProcessManageId) {
		this.traningProcessManageId = traningProcessManageId;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getHrdTraningNo() {
		return hrdTraningNo;
	}
	public void setHrdTraningNo(String hrdTraningNo) {
		this.hrdTraningNo = hrdTraningNo;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getTraningProcessIdArr() {
		return traningProcessIdArr;
	}
	public void setTraningProcessIdArr(String traningProcessIdArr) {
		this.traningProcessIdArr = traningProcessIdArr;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChoiceDay() {
		return choiceDay;
	}
	public void setChoiceDay(String choiceDay) {
		this.choiceDay = choiceDay;
	}
	public String getEmployInsManageNo() {
		return employInsManageNo;
	}
	public void setEmployInsManageNo(String employInsManageNo) {
		this.employInsManageNo = employInsManageNo;
	}
	public String getTempTraningProcessId() {
		return tempTraningProcessId;
	}
	public void setTempTraningProcessId(String tempTraningProcessId) {
		this.tempTraningProcessId = tempTraningProcessId;
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


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }


}
