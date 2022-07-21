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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.commbiz.menu.vo.CommbizMenuVO;
/**
 * COM_AUTH_MAP 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class AuthMapVO extends CommbizMenuVO implements Serializable {
	
	private static final long serialVersionUID = -8059339090758541457L;

	private String menuIdAndAuthGroupId; // 메뉴ID^권한그룹아이디
	
	@NotNull
	@Size(min=1,max=15,message="메뉴를 선택해주세요.")
	private String menuId; /* 메뉴ID */
	@NotNull
	@Size(min=1,max=15,message="권한 그룹을 선택해주세요.")
	private String authGroupId; /* 권한그룹아이디 */
	
	private String menuTitle; /* 메뉴명 */
	private String authGroupName; /* 권한그룹명*/
	
	private String deleteYn; /* 삭제여부 */
	private String insertUser; /* 등록자 */
	private String insertDate; /* 등록일 */
	private String updateUser; /* 수정자 */
	private String updateDate; /* 수정일 */
	private String createAuthYn; /* 생성 권한 여부 */
	private String readAuthYn; /* 상세 조회 권한 여부 */
	private String updateAuthYn; /* 수정 권한 여부 */
	private String deleteAuthYn; /* 삭제 권한 여부 */
	private String printAuthYn; /* 출력 권한 여부 */
	private String downloadAuthYn; /* 다운로드 권한 여부 */
	private String otherAuthYn; /* 기타 권한 여부 */
	private String listAuthYn; /* 목록 조회 권한 여부 */

	/* 검색 영역 parameters */
	private String searchMenuId; /* 검색 필드 : 메뉴ID */
	private String searchAuthGroupId; /* 검색 필드 : 권한그룹아이디 */
	
	private String searchMenuArea; /* 검색 필드 : 메뉴 영역*/
	private String searchMenuType; /* 검색 필드 : 메뉴 타입*/
	
	private String searchMenuTitle; /* 메뉴명 */
	private String searchAuthGroupName; /* 권한그룹명*/
	
	private String searchDeleteYn; /* 검색 필드 : 삭제여부 */
	private String searchInsertUser; /* 검색 필드 : 등록자 */
	private String searchInsertDate; /* 검색 필드 : 등록일 */
	private String searchUpdateUser; /* 검색 필드 : 수정자 */
	private String searchUpdateDate; /* 검색 필드 : 수정일 */
	private String searchCreateAuthYn; /* 검색 필드 : 생성 권한 여부 */
	private String searchReadAuthYn; /* 검색 필드 : 상세 조회 권한 여부 */
	private String searchUpdateAuthYn; /* 검색 필드 : 수정 권한 여부 */
	private String searchDeleteAuthYn; /* 검색 필드 : 삭제 권한 여부 */
	private String searchPrintAuthYn; /* 검색 필드 : 출력 권한 여부 */
	private String searchDownloadAuthYn; /* 검색 필드 : 다운로드 권한 여부 */
	private String searchOtherAuthYn; /* 검색 필드 : 기타 권한 여부 */
	private String searchListAuthYn; /* 검색 필드 : 목록 조회 권한 여부 */
	
	
	
	
	public String getSearchMenuType() {
		return searchMenuType;
	}
	public void setSearchMenuType(String searchMenuType) {
		this.searchMenuType = searchMenuType;
	}
	public String getSearchMenuArea() {
		return searchMenuArea;
	}
	public void setSearchMenuArea(String searchMenuArea) {
		this.searchMenuArea = searchMenuArea;
	}
	/**
	 * @return the menuId
	 */
	public String getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
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
	 * @return the createAuthYn
	 */
	public String getCreateAuthYn() {
		return createAuthYn;
	}
	/**
	 * @param createAuthYn the createAuthYn to set
	 */
	public void setCreateAuthYn(String createAuthYn) {
		this.createAuthYn = createAuthYn;
	}
	/**
	 * @return the readAuthYn
	 */
	public String getReadAuthYn() {
		return readAuthYn;
	}
	/**
	 * @param readAuthYn the readAuthYn to set
	 */
	public void setReadAuthYn(String readAuthYn) {
		this.readAuthYn = readAuthYn;
	}
	/**
	 * @return the updateAuthYn
	 */
	public String getUpdateAuthYn() {
		return updateAuthYn;
	}
	/**
	 * @param updateAuthYn the updateAuthYn to set
	 */
	public void setUpdateAuthYn(String updateAuthYn) {
		this.updateAuthYn = updateAuthYn;
	}
	/**
	 * @return the deleteAuthYn
	 */
	public String getDeleteAuthYn() {
		return deleteAuthYn;
	}
	/**
	 * @param deleteAuthYn the deleteAuthYn to set
	 */
	public void setDeleteAuthYn(String deleteAuthYn) {
		this.deleteAuthYn = deleteAuthYn;
	}
	/**
	 * @return the printAuthYn
	 */
	public String getPrintAuthYn() {
		return printAuthYn;
	}
	/**
	 * @param printAuthYn the printAuthYn to set
	 */
	public void setPrintAuthYn(String printAuthYn) {
		this.printAuthYn = printAuthYn;
	}
	/**
	 * @return the downloadAuthYn
	 */
	public String getDownloadAuthYn() {
		return downloadAuthYn;
	}
	/**
	 * @param downloadAuthYn the downloadAuthYn to set
	 */
	public void setDownloadAuthYn(String downloadAuthYn) {
		this.downloadAuthYn = downloadAuthYn;
	}
	/**
	 * @return the otherAuthYn
	 */
	public String getOtherAuthYn() {
		return otherAuthYn;
	}
	/**
	 * @param otherAuthYn the otherAuthYn to set
	 */
	public void setOtherAuthYn(String otherAuthYn) {
		this.otherAuthYn = otherAuthYn;
	}
	/**
	 * @return the listAuthYn
	 */
	public String getListAuthYn() {
		return listAuthYn;
	}
	/**
	 * @param listAuthYn the listAuthYn to set
	 */
	public void setListAuthYn(String listAuthYn) {
		this.listAuthYn = listAuthYn;
	}
	/**
	 * @return the searchMenuId
	 */
	public String getSearchMenuId() {
		return searchMenuId;
	}
	/**
	 * @param searchMenuId the searchMenuId to set
	 */
	public void setSearchMenuId(String searchMenuId) {
		this.searchMenuId = searchMenuId;
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
	/**
	 * @return the searchCreateAuthYn
	 */
	public String getSearchCreateAuthYn() {
		return searchCreateAuthYn;
	}
	/**
	 * @param searchCreateAuthYn the searchCreateAuthYn to set
	 */
	public void setSearchCreateAuthYn(String searchCreateAuthYn) {
		this.searchCreateAuthYn = searchCreateAuthYn;
	}
	/**
	 * @return the searchReadAuthYn
	 */
	public String getSearchReadAuthYn() {
		return searchReadAuthYn;
	}
	/**
	 * @param searchReadAuthYn the searchReadAuthYn to set
	 */
	public void setSearchReadAuthYn(String searchReadAuthYn) {
		this.searchReadAuthYn = searchReadAuthYn;
	}
	/**
	 * @return the searchUpdateAuthYn
	 */
	public String getSearchUpdateAuthYn() {
		return searchUpdateAuthYn;
	}
	/**
	 * @param searchUpdateAuthYn the searchUpdateAuthYn to set
	 */
	public void setSearchUpdateAuthYn(String searchUpdateAuthYn) {
		this.searchUpdateAuthYn = searchUpdateAuthYn;
	}
	/**
	 * @return the searchDeleteAuthYn
	 */
	public String getSearchDeleteAuthYn() {
		return searchDeleteAuthYn;
	}
	/**
	 * @param searchDeleteAuthYn the searchDeleteAuthYn to set
	 */
	public void setSearchDeleteAuthYn(String searchDeleteAuthYn) {
		this.searchDeleteAuthYn = searchDeleteAuthYn;
	}
	/**
	 * @return the searchPrintAuthYn
	 */
	public String getSearchPrintAuthYn() {
		return searchPrintAuthYn;
	}
	/**
	 * @param searchPrintAuthYn the searchPrintAuthYn to set
	 */
	public void setSearchPrintAuthYn(String searchPrintAuthYn) {
		this.searchPrintAuthYn = searchPrintAuthYn;
	}
	/**
	 * @return the searchDownloadAuthYn
	 */
	public String getSearchDownloadAuthYn() {
		return searchDownloadAuthYn;
	}
	/**
	 * @param searchDownloadAuthYn the searchDownloadAuthYn to set
	 */
	public void setSearchDownloadAuthYn(String searchDownloadAuthYn) {
		this.searchDownloadAuthYn = searchDownloadAuthYn;
	}
	/**
	 * @return the searchOtherAuthYn
	 */
	public String getSearchOtherAuthYn() {
		return searchOtherAuthYn;
	}
	/**
	 * @param searchOtherAuthYn the searchOtherAuthYn to set
	 */
	public void setSearchOtherAuthYn(String searchOtherAuthYn) {
		this.searchOtherAuthYn = searchOtherAuthYn;
	}
	/**
	 * @return the searchListAuthYn
	 */
	public String getSearchListAuthYn() {
		return searchListAuthYn;
	}
	/**
	 * @param searchListAuthYn the searchListAuthYn to set
	 */
	public void setSearchListAuthYn(String searchListAuthYn) {
		this.searchListAuthYn = searchListAuthYn;
	}
	/**
	 * @return the menuTitle
	 */
	public String getMenuTitle() {
		return menuTitle;
	}
	/**
	 * @param menuTitle the menuTitle to set
	 */
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
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
	 * @return the searchMenuTitle
	 */
	public String getSearchMenuTitle() {
		return searchMenuTitle;
	}
	/**
	 * @param searchMenuTitle the searchMenuTitle to set
	 */
	public void setSearchMenuTitle(String searchMenuTitle) {
		this.searchMenuTitle = searchMenuTitle;
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
	 * @return the menuIdAndAuthGroupId
	 */
	public String getMenuIdAndAuthGroupId() {
		return menuIdAndAuthGroupId;
	}
	/**
	 * @param menuIdAndAuthGroupId the menuIdAndAuthGroupId to set
	 */
	public void setMenuIdAndAuthGroupId(String menuIdAndAuthGroupId) {
		this.menuIdAndAuthGroupId = menuIdAndAuthGroupId;
	}

}
