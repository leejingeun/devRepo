/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.la.comcode.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_COMCODE 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class ComcodeVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 2237155679077037565L;
	private String codeId;
	@NotNull
	@Size(min = 1, max = 30, message="코드구분을 입력하세요(messg)(최소:{min} , 최대:{max})")
	private String codeGroup;
	private String groupDesc;
	@NotNull
	@Size(min = 1, max = 10, message="코드을 입력하세요(messg)(최소:{min} , 최대:{max})")
	private String codeCode;
	@NotNull
	@Size(min = 1, max = 90, message="코드명을 입력하세요(messg)(최소:{min} , 최대:{max})")
	private String codeName;
	private String codeNameEng;
	private Integer codeOrder;
	@NotNull
	@Size(min = 1, max = 1, message="삭제여부을 입력하세요(messg)(최소:{min} , 최대:{max})")
	private String deleteYn;
	@NotNull
	@Size(min = 1, max = 1, message="사용여부을 입력하세요(messg)(최소:{min} , 최대:{max})")
	private String useYn;
	private String bigo1;
	private String bigo2;
	private String bigo3;
	private String insertDate;
	private String insertUser;
	private String updateDate;
	private String updateUser;

	private String delCodeId;
	private String[] delCodeIdList;

	/* 검색 영역 parameters */
	private String searchCodeId;
	private String searchCodeGroup;
	private String searchGroupDesc;
	private String searchCodeCode;
	private String searchCodeName;
	private String searchCodeNameEng;
	private Integer searchCodeOrder;
	private String searchBigo1;
	private String searchBigo2;
	private String searchDeleteYn;
	private String searchInsertDate;
	private String searchInsertUser;
	private String searchUpdateDate;
	private String searchUpdateUser;
	private String searchUseYn;
	private String searchBigo3;
	/**
	 * @return the codeId
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId the codeId to set
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return the codeGroup
	 */
	public String getCodeGroup() {
		return codeGroup;
	}
	/**
	 * @param codeGroup the codeGroup to set
	 */
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}
	/**
	 * @return the groupDesc
	 */
	public String getGroupDesc() {
		return groupDesc;
	}
	/**
	 * @param groupDesc the groupDesc to set
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	/**
	 * @return the codeCode
	 */
	public String getCodeCode() {
		return codeCode;
	}
	/**
	 * @param codeCode the codeCode to set
	 */
	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}
	/**
	 * @return the codeName
	 */
	public String getCodeName() {
		return codeName;
	}
	/**
	 * @param codeName the codeName to set
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	/**
	 * @return the codeNameEng
	 */
	public String getCodeNameEng() {
		return codeNameEng;
	}
	/**
	 * @param codeNameEng the codeNameEng to set
	 */
	public void setCodeNameEng(String codeNameEng) {
		this.codeNameEng = codeNameEng;
	}
	/**
	 * @return the codeOrder
	 */
	public Integer getCodeOrder() {
		return codeOrder;
	}
	/**
	 * @param codeOrder the codeOrder to set
	 */
	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}
	/**
	 * @return the bigo1
	 */
	public String getBigo1() {
		return bigo1;
	}
	/**
	 * @param bigo1 the bigo1 to set
	 */
	public void setBigo1(String bigo1) {
		this.bigo1 = bigo1;
	}
	/**
	 * @return the bigo2
	 */
	public String getBigo2() {
		return bigo2;
	}
	/**
	 * @param bigo2 the bigo2 to set
	 */
	public void setBigo2(String bigo2) {
		this.bigo2 = bigo2;
	}
	/**
	 * @return the deleteYn
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	/**
	 * @param deleteYn the deleteYn to set
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	/**
	 * @return the insertDate
	 */
	public String getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate the insertDate to set
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return the insertUser
	 */
	public String getInsertUser() {
		return insertUser;
	}
	/**
	 * @param insertUser the insertUser to set
	 */
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}
	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}
	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	/**
	 * @return the bigo3
	 */
	public String getBigo3() {
		return bigo3;
	}
	/**
	 * @param bigo3 the bigo3 to set
	 */
	public void setBigo3(String bigo3) {
		this.bigo3 = bigo3;
	}
	
	/**
	 * @return the delCodeId
	 */
	public String getDelCodeId() {
		return delCodeId;
	}
	/**
	 * @param delCodeId the delCodeId to set
	 */
	public void setDelCodeId(String delCodeId) {
		this.delCodeId = delCodeId;
	}
	/**
	 * @return the delCodeIdList
	 */
	public String[] getDelCodeIdList() {
		return delCodeIdList;
	}
	/**
	 * @param delCodeIdList the delCodeIdList to set
	 */
	public void setDelCodeIdList(String[] delCodeIdList) {
		this.delCodeIdList = delCodeIdList;
	}
	/**
	 * @return the searchCodeId
	 */
	public String getSearchCodeId() {
		return searchCodeId;
	}
	/**
	 * @param searchCodeId the searchCodeId to set
	 */
	public void setSearchCodeId(String searchCodeId) {
		this.searchCodeId = searchCodeId;
	}
	/**
	 * @return the searchCodeGroup
	 */
	public String getSearchCodeGroup() {
		return searchCodeGroup;
	}
	/**
	 * @param searchCodeGroup the searchCodeGroup to set
	 */
	public void setSearchCodeGroup(String searchCodeGroup) {
		this.searchCodeGroup = searchCodeGroup;
	}
	/**
	 * @return the searchGroupDesc
	 */
	public String getSearchGroupDesc() {
		return searchGroupDesc;
	}
	/**
	 * @param searchGroupDesc the searchGroupDesc to set
	 */
	public void setSearchGroupDesc(String searchGroupDesc) {
		this.searchGroupDesc = searchGroupDesc;
	}
	/**
	 * @return the searchCodeCode
	 */
	public String getSearchCodeCode() {
		return searchCodeCode;
	}
	/**
	 * @param searchCodeCode the searchCodeCode to set
	 */
	public void setSearchCodeCode(String searchCodeCode) {
		this.searchCodeCode = searchCodeCode;
	}
	/**
	 * @return the searchCodeName
	 */
	public String getSearchCodeName() {
		return searchCodeName;
	}
	/**
	 * @param searchCodeName the searchCodeName to set
	 */
	public void setSearchCodeName(String searchCodeName) {
		this.searchCodeName = searchCodeName;
	}
	/**
	 * @return the searchCodeNameEng
	 */
	public String getSearchCodeNameEng() {
		return searchCodeNameEng;
	}
	/**
	 * @param searchCodeNameEng the searchCodeNameEng to set
	 */
	public void setSearchCodeNameEng(String searchCodeNameEng) {
		this.searchCodeNameEng = searchCodeNameEng;
	}
	/**
	 * @return the searchCodeOrder
	 */
	public Integer getSearchCodeOrder() {
		return searchCodeOrder;
	}
	/**
	 * @param searchCodeOrder the searchCodeOrder to set
	 */
	public void setSearchCodeOrder(Integer searchCodeOrder) {
		this.searchCodeOrder = searchCodeOrder;
	}
	/**
	 * @return the searchBigo1
	 */
	public String getSearchBigo1() {
		return searchBigo1;
	}
	/**
	 * @param searchBigo1 the searchBigo1 to set
	 */
	public void setSearchBigo1(String searchBigo1) {
		this.searchBigo1 = searchBigo1;
	}
	/**
	 * @return the searchBigo2
	 */
	public String getSearchBigo2() {
		return searchBigo2;
	}
	/**
	 * @param searchBigo2 the searchBigo2 to set
	 */
	public void setSearchBigo2(String searchBigo2) {
		this.searchBigo2 = searchBigo2;
	}
	/**
	 * @return the searchDeleteYn
	 */
	public String getSearchDeleteYn() {
		return searchDeleteYn;
	}
	/**
	 * @param searchDeleteYn the searchDeleteYn to set
	 */
	public void setSearchDeleteYn(String searchDeleteYn) {
		this.searchDeleteYn = searchDeleteYn;
	}
	/**
	 * @return the searchInsertDate
	 */
	public String getSearchInsertDate() {
		return searchInsertDate;
	}
	/**
	 * @param searchInsertDate the searchInsertDate to set
	 */
	public void setSearchInsertDate(String searchInsertDate) {
		this.searchInsertDate = searchInsertDate;
	}
	/**
	 * @return the searchInsertUser
	 */
	public String getSearchInsertUser() {
		return searchInsertUser;
	}
	/**
	 * @param searchInsertUser the searchInsertUser to set
	 */
	public void setSearchInsertUser(String searchInsertUser) {
		this.searchInsertUser = searchInsertUser;
	}
	/**
	 * @return the searchUpdateDate
	 */
	public String getSearchUpdateDate() {
		return searchUpdateDate;
	}
	/**
	 * @param searchUpdateDate the searchUpdateDate to set
	 */
	public void setSearchUpdateDate(String searchUpdateDate) {
		this.searchUpdateDate = searchUpdateDate;
	}
	/**
	 * @return the searchUpdateUser
	 */
	public String getSearchUpdateUser() {
		return searchUpdateUser;
	}
	/**
	 * @param searchUpdateUser the searchUpdateUser to set
	 */
	public void setSearchUpdateUser(String searchUpdateUser) {
		this.searchUpdateUser = searchUpdateUser;
	}
	/**
	 * @return the searchUseYn
	 */
	public String getSearchUseYn() {
		return searchUseYn;
	}
	/**
	 * @param searchUseYn the searchUseYn to set
	 */
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	/**
	 * @return the searchBigo3
	 */
	public String getSearchBigo3() {
		return searchBigo3;
	}
	/**
	 * @param searchBigo3 the searchBigo3 to set
	 */
	public void setSearchBigo3(String searchBigo3) {
		this.searchBigo3 = searchBigo3;
	}

	
	
	
}
