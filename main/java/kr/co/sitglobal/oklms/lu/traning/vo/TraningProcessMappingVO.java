package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TraningProcessMappingVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 425058810731234004L;
	
	private String companyId;					//기업아이디
	private String companyName;                 //기업명
	private String traningProcessManageId;       //훈련과정관리아이디
	private String traningProcessId;			//훈련과정아이디
	private String traningProcessMappingId;  	//훈련과정매핑아이디
	private String yyyy; 						//학년도
	private String term;						//학기
	private String subjectCode; 				//교과목코드
	private String subjectName; 				//교과목명
	private String subClass; 					//분반
	private String newYyyy; 					//수정-학년도
	private String newTerm;						//수정-학기
	private String newSubjectCode; 				//수정-교과목코드
	private String newSubjectName; 				//수정-교과목명
	private String newSubClass; 				//수정-분반
	private String hrdTraningNo; 				//HRD-NET 훈련과정번호
	private String hrdTraningName; 				//HRD-NET 훈련과정명
	private String deleteYn; 					//삭제여부
	private String count;
	private String memSeq;
	private int insertCount = 0;
	
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
	public String getTraningProcessMappingId() {
		return traningProcessMappingId;
	}
	public void setTraningProcessMappingId(String traningProcessMappingId) {
		this.traningProcessMappingId = traningProcessMappingId;
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
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public int getInsertCount() {
		return insertCount;
	}
	public void setInsertCount(int insertCount) {
		this.insertCount = insertCount;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getNewYyyy() {
		return newYyyy;
	}
	public void setNewYyyy(String newYyyy) {
		this.newYyyy = newYyyy;
	}
	public String getNewTerm() {
		return newTerm;
	}
	public void setNewTerm(String newTerm) {
		this.newTerm = newTerm;
	}
	public String getNewSubjectCode() {
		return newSubjectCode;
	}
	public void setNewSubjectCode(String newSubjectCode) {
		this.newSubjectCode = newSubjectCode;
	}
	public String getNewSubjectName() {
		return newSubjectName;
	}
	public void setNewSubjectName(String newSubjectName) {
		this.newSubjectName = newSubjectName;
	}
	public String getNewSubClass() {
		return newSubClass;
	}
	public void setNewSubClass(String newSubClass) {
		this.newSubClass = newSubClass;
	}
	public String getTraningProcessManageId() {
		return traningProcessManageId;
	}
	public void setTraningProcessManageId(String traningProcessManageId) {
		this.traningProcessManageId = traningProcessManageId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	
	
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
	

}
