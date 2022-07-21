package kr.co.sitglobal.oklms.lu.grade.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

public class GradeVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3276185659082783277L;

	
	private String yyyy = "";
	private String term = "";
	private String companyId = "";
	private String companyName = "";
	private String companyNo = "";
	private String ip = "";
	private String ccmName = "";
	private String stuCnt = "";
	private String deptNo = "";
	private String deptNm = "";
	private String sendYn = "";
	private String search = "";
	private String groupMemIds = "";
	private String semstrCd = "";
	private String submitId = "";
	private String submitIds = "";
	private String confirmYn = "";
	private String confirmUser = "";
	private String confirmDate = "";
	private String gradeCnt  = "";
	
	public String getGradeCnt() {
		return gradeCnt;
	}

	public void setGradeCnt(String gradeCnt) {
		this.gradeCnt = gradeCnt;
	}

	public String getConfirmYn() {
		return confirmYn;
	}

	public void setConfirmYn(String confirmYn) {
		this.confirmYn = confirmYn;
	}

	public String getConfirmUser() {
		return confirmUser;
	}

	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	
	public String getSubmitId() {
		return submitId;
	}

	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}
	
	public String getSubmitIds() {
		return submitIds;
	}

	public void setSubmitIds(String submitIds) {
		this.submitIds = submitIds;
	}

	public String getSemstrCd() {
		return semstrCd;
	}
	
	public String getTermNm() {
		String result = "";
		if("1".equals(term)){
			result = "1학기";
		} else if("2".equals(term)){
			result = "2학기";
		} else if("3".equals(term)){
			result = "여름학기";
		} else {
			result = "겨울학기";
		}
		return result;
	}
	
	public void setSemstrCd(String semstrCd) {
		this.semstrCd = semstrCd;
	}
	public String getGroupMemIds() {
		return groupMemIds;
	}
	public void setGroupMemIds(String groupMemIds) {
		this.groupMemIds = groupMemIds;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCcmName() {
		return ccmName;
	}
	public void setCcmName(String ccmName) {
		this.ccmName = ccmName;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(String stuCnt) {
		this.stuCnt = stuCnt;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	
}
