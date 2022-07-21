package kr.co.sitglobal.oklms.lu.interview.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class InterviewCompanyVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5333672457448888810L;
		
	private String companyName;
	private String hrdTraningName;	
	private String companyId;
	private String traningProcessId;
	private String searchCompanyName;
	private String searchHrdTraningName;	
	private String address;	
	private String memSeq;
	private String yyyy;
	private String term;
	
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSearchCompanyName() {
		return searchCompanyName;
	}
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	public String getSearchHrdTraningName() {
		return searchHrdTraningName;
	}
	public void setSearchHrdTraningName(String searchHrdTraningName) {
		this.searchHrdTraningName = searchHrdTraningName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	
}
