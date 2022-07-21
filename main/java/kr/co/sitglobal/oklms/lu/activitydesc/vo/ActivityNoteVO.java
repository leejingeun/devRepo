package kr.co.sitglobal.oklms.lu.activitydesc.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * 활동내역
 * @author user
 *
 */
public class ActivityNoteVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -236833227359951694L;

	private String activityId;
	private String subject_code;
	private String traningProcessId; 
	private String yyyy;
	private String term;
	private String companyId;
	private String classs;
	private String deleteYn;
	private String insertDate;
	private String insertUser;
	private String updateDate;
	private String updateUser;
	private String bigo;

	private String activityDetailId;
	private String activityType;

	private String mm;
	private String submitYn;
	private String activityDate;
	private String memName;
	private String activityContent;
	private String leadTime;

	private String companyName;
	private String address;
	private String traningStDate;
	private String hrdTraningName;

	private String[] activityDateArr;
	private String[] memNameArr;
	private String[] activityContentArr;
	private String[] leadTimeArr;
	private String[] activityDetailIdArr;
	private String[] activityIdArr;


	private String subjectCode;
	private String weekCnt;
	private String ccmCnt;
	private String ccmCot;

	private String bigoCot;
	private String bigoHrd;


	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String[] getActivityIdArr() {
		return activityIdArr;
	}
	public void setActivityIdArr(String[] activityIdArr) {
		this.activityIdArr = activityIdArr;
	}
	public String getBigoCot() {
		return bigoCot;
	}
	public void setBigoCot(String bigoCot) {
		this.bigoCot = bigoCot;
	}
	public String getBigoHrd() {
		return bigoHrd;
	}
	public void setBigoHrd(String bigoHrd) {
		this.bigoHrd = bigoHrd;
	}
	private String printDate;

	public String getPrintDate() {
		return printDate;
	}
	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}
	public String getCcmCnt() {
		return ccmCnt;
	}
	public void setCcmCnt(String ccmCnt) {
		this.ccmCnt = ccmCnt;
	}
	public String getCcmCot() {
		return ccmCot;
	}
	public void setCcmCot(String ccmCot) {
		this.ccmCot = ccmCot;
	}
	public String[] getActivityDetailIdArr() {
		return activityDetailIdArr;
	}
	public void setActivityDetailIdArr(String[] activityDetailIdArr) {
		this.activityDetailIdArr = activityDetailIdArr;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String[] getActivityDateArr() {
		return activityDateArr;
	}
	public void setActivityDateArr(String[] activityDateArr) {
		this.activityDateArr = activityDateArr;
	}
	public String[] getMemNameArr() {
		return memNameArr;
	}
	public void setMemNameArr(String[] memNameArr) {
		this.memNameArr = memNameArr;
	}
	public String[] getActivityContentArr() {
		return activityContentArr;
	}
	public void setActivityContentArr(String[] activityContentArr) {
		this.activityContentArr = activityContentArr;
	}
	public String[] getLeadTimeArr() {
		return leadTimeArr;
	}
	public void setLeadTimeArr(String[] leadTimeArr) {
		this.leadTimeArr = leadTimeArr;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getInsertUser() {
		return insertUser;
	}
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getBigo() {
		return bigo;
	}
	public void setBigo(String bigo) {
		this.bigo = bigo;
	}
	public String getActivityDetailId() {
		return activityDetailId;
	}
	public void setActivityDetailId(String activityDetailId) {
		this.activityDetailId = activityDetailId;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getSubmitYn() {
		return submitYn;
	}
	public void setSubmitYn(String submitYn) {
		this.submitYn = submitYn;
	}
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
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
	public String getTraningStDate() {
		return traningStDate;
	}
	public void setTraningStDate(String traningStDate) {
		this.traningStDate = traningStDate;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}



}
