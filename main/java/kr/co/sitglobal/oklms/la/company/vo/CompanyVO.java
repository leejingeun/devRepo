package kr.co.sitglobal.oklms.la.company.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CompanyVO extends BaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1329823451159588771L;

	private String companyId; //업체코드
	private String companyCode; //회사구분코드
	private String companyName; //회사명
	private String companyNo; //사업자번호
	private String employInsManageNo; //기업고용보험관리번호
	
	private String bigBusinessType; //대분류 업종
	private String middleBusinessType; //중분류 업종
	private String smailBusinessType; //소분류 업종
	private String companyDivCd; //기업구분코드
	
	private String corpNo; //법인번호
	private String homePage; //홈페이지
	private String businessType; //업종
	private String businessCondition; //업태
	private String ceo; //업체대표
	private String name; //성명
	private String position; //직위
	private String zoneCode; //지역코드
	private String zipCode; //우편번호
	private String address; //주소
	private String addressDtl; //상세주소
	private String telNo1; //전화번호1
	private String telNo2; //전화번호2
	private String telNo3; //전화번호3
	private String faxNo1; //팩스번호1
	private String faxNo2; //팩스번호2
	private String faxNo3; //팩스번호3
	private String email; //이메일
	private String choiceDay; //선정일
	private String regularEmploymentCnt; //상시근로자수
	private String deleteYn; //삭제여부
	private String insertDate; //등록일
	private String insertUser; //등록자
	private String updateDate; //수정일
	private String updateUser; //수정자
	private String traningProcessId; //훈련과정아이디
	private String hrdTraningName; //훈련과정명
	private String hrdTraningNo; //훈련과정번호

	private String searchCompanyName;				  //기업명 검색어
	private String searchEmployInsManageNo;		     //기업고용보험관리번호 검색어

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCorpNo() {
		return corpNo;
	}
	public void setCorpNo(String corpNo) {
		this.corpNo = corpNo;
	}
	public String getHomePage() {
		return homePage;
	}
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}
	
	public String getCeo() {
		return ceo;
	}
	public void setCeo(String ceo) {
		this.ceo = ceo;
	}
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDtl() {
		return addressDtl;
	}
	public void setAddressDtl(String addressDtl) {
		this.addressDtl = addressDtl;
	}
	public String getTelNo1() {
		return telNo1;
	}
	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
	}
	public String getTelNo2() {
		return telNo2;
	}
	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
	}
	public String getTelNo3() {
		return telNo3;
	}
	public void setTelNo3(String telNo3) {
		this.telNo3 = telNo3;
	}
	public String getFaxNo1() {
		return faxNo1;
	}
	public void setFaxNo1(String faxNo1) {
		this.faxNo1 = faxNo1;
	}
	public String getFaxNo2() {
		return faxNo2;
	}
	public void setFaxNo2(String faxNo2) {
		this.faxNo2 = faxNo2;
	}
	public String getFaxNo3() {
		return faxNo3;
	}
	public void setFaxNo3(String faxNo3) {
		this.faxNo3 = faxNo3;
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
	public String getEmployInsManageNo() {
		return employInsManageNo;
	}
	public void setEmployInsManageNo(String employInsManageNo) {
		this.employInsManageNo = employInsManageNo;
	}
	public String getBigBusinessType() {
		return bigBusinessType;
	}
	public void setBigBusinessType(String bigBusinessType) {
		this.bigBusinessType = bigBusinessType;
	}
	public String getMiddleBusinessType() {
		return middleBusinessType;
	}
	public void setMiddleBusinessType(String middleBusinessType) {
		this.middleBusinessType = middleBusinessType;
	}
	public String getSmailBusinessType() {
		return smailBusinessType;
	}
	public void setSmailBusinessType(String smailBusinessType) {
		this.smailBusinessType = smailBusinessType;
	}
	public String getCompanyDivCd() {
		return companyDivCd;
	}
	public void setCompanyDivCd(String companyDivCd) {
		this.companyDivCd = companyDivCd;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessCondition() {
		return businessCondition;
	}
	public void setBusinessCondition(String businessCondition) {
		this.businessCondition = businessCondition;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChoiceDay() {
		return choiceDay;
	}
	public void setChoiceDay(String choiceDay) {
		this.choiceDay = choiceDay;
	}
	public String getRegularEmploymentCnt() {
		return regularEmploymentCnt;
	}
	public void setRegularEmploymentCnt(String regularEmploymentCnt) {
		this.regularEmploymentCnt = regularEmploymentCnt;
	}
	public String getSearchCompanyName() {
		return searchCompanyName;
	}
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	public String getSearchEmployInsManageNo() {
		return searchEmployInsManageNo;
	}
	public void setSearchEmployInsManageNo(String searchEmployInsManageNo) {
		this.searchEmployInsManageNo = searchEmployInsManageNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String getHrdTraningNo() {
		return hrdTraningNo;
	}
	public void setHrdTraningNo(String hrdTraningNo) {
		this.hrdTraningNo = hrdTraningNo;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	
	
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
	
	
	/* @Override
	public String toString() {
		return "CompanyVO [companyId=" + companyId + ", companyCode="
				+ companyCode + ", companyName=" + companyName + ", companyNo="
				+ companyNo + ", corpNo=" + corpNo + ", homePage=" + homePage +
				
				, ceo=" + ceo + ", zoneCode=" + zoneCode
				+ ", zipCode=" + zipCode + ", address=" + address
				+ ", addressDtl=" + addressDtl + ", telNo1=" + telNo1
				+ ", telNo2=" + telNo2 + ", telNo3=" + telNo3 + ", faxNo1="
				+ faxNo1 + ", faxNo2=" + faxNo2 + ", faxNo3=" + faxNo3
				+ ", deleteYn=" + deleteYn + ", insertDate=" + insertDate
				+ ", insertUser=" + insertUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}*/
}
