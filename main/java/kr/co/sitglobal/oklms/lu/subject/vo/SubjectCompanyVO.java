
/*******************************************************************************
 * COPYRIGHT(C) 2016 WIZI LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of WIZI LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 12. 28.        First Draft.
 *
 *******************************************************************************/ 
package kr.co.sitglobal.oklms.lu.subject.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2016. 12. 28.
 */
public class SubjectCompanyVO extends BaseVO implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1623195581087203659L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	
	private String yyyy                         ;	// 학년도                                                                   
	private String companyId;
	private String companyName;
	private String hrdTraningNo; 				//HRD-NET 훈련과정번호
	private String hrdTraningName; 				//HRD-NET 훈련과정명
	private String hrdStartDate; 				
	private String hrdEndDate; 				
	private String weekId;
	private String weekCnt;
	private String noteSumCnt;
	private String evalPlanCnt;
	private String searchStatusType;
	private String memName;
	private String address;
	private String deptName;
	private String stuCnt;
	private String outCnt;
	private String tutNames;
	private String hrdNames;
	private String actNoteCnt;
	private String memInfos;
	private String infoNum;
	private String infoNums;
	private String subjectGrade;
	private String searchCompanyName;
	private String [] infoNumArr;
	private int memInfosLength;
	
	public String getSearchCompanyName() {
		return searchCompanyName;
	}
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	public String getTutNames() {
		return tutNames;
	}
	public void setTutNames(String tutNames) {
		this.tutNames = tutNames;
	}
	public String getHrdNames() {
		return hrdNames;
	}
	public void setHrdNames(String hrdNames) {
		this.hrdNames = hrdNames;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(String stuCnt) {
		this.stuCnt = stuCnt;
	}
	public String getOutCnt() {
		return outCnt;
	}
	public void setOutCnt(String outCnt) {
		this.outCnt = outCnt;
	}
	public String getInfoNum() {
		return infoNum;
	}
	public void setInfoNum(String infoNum) {
		this.infoNum = infoNum;
	}
	public String getInfoNums() {
		return infoNums;
	}
	public void setInfoNums(String infoNums) {
		this.infoNums = infoNums;
	}
	public String[] getInfoNumArr() {
		return infoNumArr;
	}
	public void setInfoNumArr(String[] infoNumArr) {
		this.infoNumArr = infoNumArr;
	}
	public int getMemInfosLength() {
		return memInfosLength;
	}
	public void setMemInfosLength(int memInfosLength) {
		this.memInfosLength = memInfosLength;
	}
	public String getMemInfos() {
		return memInfos;
	}
	public void setMemInfos(String memInfos) {
		this.memInfos = memInfos;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getActNoteCnt() {
		return actNoteCnt;
	}
	public void setActNoteCnt(String actNoteCnt) {
		this.actNoteCnt = actNoteCnt;
	}
	
	public String getSearchStatusType() {
		return searchStatusType;
	}
	public void setSearchStatusType(String searchStatusType) {
		this.searchStatusType = searchStatusType;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
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
	public String getHrdStartDate() {
		return hrdStartDate;
	}
	public void setHrdStartDate(String hrdStartDate) {
		this.hrdStartDate = hrdStartDate;
	}
	public String getHrdEndDate() {
		return hrdEndDate;
	}
	public void setHrdEndDate(String hrdEndDate) {
		this.hrdEndDate = hrdEndDate;
	}
	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getNoteSumCnt() {
		return noteSumCnt;
	}
	public void setNoteSumCnt(String noteSumCnt) {
		this.noteSumCnt = noteSumCnt;
	}
	public String getEvalPlanCnt() {
		return evalPlanCnt;
	}
	public void setEvalPlanCnt(String evalPlanCnt) {
		this.evalPlanCnt = evalPlanCnt;
	}
	
}
