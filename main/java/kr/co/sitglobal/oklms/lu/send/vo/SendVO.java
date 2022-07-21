package kr.co.sitglobal.oklms.lu.send.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class SendVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3863414035550169455L;

	/**
	 * 
	 */

	/**
	 * 
	 */
	private String yyyy                         ;	// 학년도                                                                   
	private String term                         ; 	// 학기                                                                     
	private String subjectCode                  ;   // 교과목코드                                                               
	private String subjectClass                 ;   // 분반  
	private String subjectName                  ;   // 교과명      
	private String deptCode;
	private String deptName;
	private String subjectGrade;
	private String companyId;
	private String companyName;
	private String memSeq;
	private String memId;
	private String memName;
	private String adYear;
	
	public String getAdYear() {
		return adYear;
	}
	public void setAdYear(String adYear) {
		this.adYear = adYear;
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
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	
	
	
	
}
