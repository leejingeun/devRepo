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
package kr.co.sitglobal.oklms.la.authority.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_AUTH_GROUP 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class AuthGroupVO extends BaseVO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1275760896004298396L;
	private String delAuthGroupId;
	private String[] delAuthGroupIdList;
	
	private String authGroupId; /* 권한그룹아이디 */
	private String authGroupName; /* 권한그룹명 */
	private String authGroupDesc; /* 권한그룹설명 */
	private String deleteYn; /* 삭제여부 */
	private String insertUser; /* 등록자 */
	private String insertDate; /* 등록일 */
	private String updateUser; /* 수정자 */
	private String updateDate; /* 수정일 */

	/* 검색 영역 parameters */
	private String searchAuthGroupId; /* 검색 필드 : 권한그룹아이디 */
	private String searchAuthGroupName; /* 검색 필드 : 권한그룹명 */
	private String searchAuthGroupDesc; /* 검색 필드 : 권한그룹설명 */
	private String searchDeleteYn; /* 검색 필드 : 삭제여부 */
	private String searchInsertUser; /* 검색 필드 : 등록자 */
	private String searchInsertDate; /* 검색 필드 : 등록일 */
	private String searchUpdateUser; /* 검색 필드 : 수정자 */
	private String searchUpdateDate; /* 검색 필드 : 수정일 */
	
	
	
	
	/**
	 * @return the delAuthGroupId
	 */
	public String getDelAuthGroupId() {
		return delAuthGroupId;
	}
	/**
	 * @param delAuthGroupId the delAuthGroupId to set
	 */
	public void setDelAuthGroupId(String delAuthGroupId) {
		this.delAuthGroupId = delAuthGroupId;
	}
	
	/**
	 * @return the delAuthGroupIdList
	 */
	public String[] getDelAuthGroupIdList() {
		return delAuthGroupIdList;
	}
	/**
	 * @param delAuthGroupIdList the delAuthGroupIdList to set
	 */
	public void setDelAuthGroupIdList(String[] delAuthGroupIdList) {
		this.delAuthGroupIdList = delAuthGroupIdList;
	}
	/**
	 * @return the authGroupId
	 */
	public String getAuthGroupId() {
		return authGroupId;
	}
	/**
	 * @param authGroupId the authGroupId to set
	 */
	public void setAuthGroupId(String authGroupId) {
		this.authGroupId = authGroupId;
	}
	/**
	 * @return the authGroupName
	 */
	public String getAuthGroupName() {
		return authGroupName;
	}
	/**
	 * @param authGroupName the authGroupName to set
	 */
	public void setAuthGroupName(String authGroupName) {
		this.authGroupName = authGroupName;
	}
	/**
	 * @return the authGroupDesc
	 */
	public String getAuthGroupDesc() {
		return authGroupDesc;
	}
	/**
	 * @param authGroupDesc the authGroupDesc to set
	 */
	public void setAuthGroupDesc(String authGroupDesc) {
		this.authGroupDesc = authGroupDesc;
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
	 * @return the searchAuthGroupId
	 */
	public String getSearchAuthGroupId() {
		return searchAuthGroupId;
	}
	/**
	 * @param searchAuthGroupId the searchAuthGroupId to set
	 */
	public void setSearchAuthGroupId(String searchAuthGroupId) {
		this.searchAuthGroupId = searchAuthGroupId;
	}
	/**
	 * @return the searchAuthGroupName
	 */
	public String getSearchAuthGroupName() {
		return searchAuthGroupName;
	}
	/**
	 * @param searchAuthGroupName the searchAuthGroupName to set
	 */
	public void setSearchAuthGroupName(String searchAuthGroupName) {
		this.searchAuthGroupName = searchAuthGroupName;
	}
	/**
	 * @return the searchAuthGroupDesc
	 */
	public String getSearchAuthGroupDesc() {
		return searchAuthGroupDesc;
	}
	/**
	 * @param searchAuthGroupDesc the searchAuthGroupDesc to set
	 */
	public void setSearchAuthGroupDesc(String searchAuthGroupDesc) {
		this.searchAuthGroupDesc = searchAuthGroupDesc;
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

	
	
}
