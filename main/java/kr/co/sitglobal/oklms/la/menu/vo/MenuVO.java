/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
* 이진근    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.la.menu.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_MENU 테이블에 대응되는 VO 클래스입니다.
 * 
* 이진근
 * @since 2016. 07. 01.
 */
public class MenuVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 4747515175985943759L;
	
	private String isLeaf;
	private String expanded;
	private String loaded;
	private String upMenuTitle;

	private String menuId; /* 메뉴ID */
	private String menuIdTemp; 
	private String delMenuId;
	private String[] delMenuIdList; /* 삭제할 PK(메뉴ID) 리스트 */
	private String menuType; /* 메뉴분류 */
	private String menuViewTypeCode; /* 메뉴 노출 타입 */
	private String menuArea; /* 메뉴영역 */
	private String upMenuId; /* 대메뉴ID */
	private Integer menuDepth; /* 깊이 */
	private Integer menuOrder; /* 메뉴순서 */
	private Integer mexMenuOrder; /* 메뉴순서Max */
	private String menuStatus; /* 상태 */
	private String menuUrl; /* 경로 */
	private String menuTitle; /* 화면 타이틀 제목 */
	private String menuImage; /* 메뉴이미지 */
	private String deleteYn; /* 삭제여부 */
	private String showYn; /* 활성여부 */
	private String insertUser; /* 등록자 */
	private String insertDate; /* 등록일 */
	private String updateUser; /* 수정자 */
	private String updateDate; /* 수정일 */
	private String etc1; /* 기타1 */
	private String etc2; /* 기타2 */
	private String createAuthUrlPattern; /* 생성 권한 URL 패턴 */
	private String readAuthUrlPattern; /* 상세 조회 권한 URL 패턴 */
	private String updateAuthUrlPattern; /* 수정 권한 URL 패턴 */
	private String deleteAuthUrlPattern; /* 삭제 권한 URL 패턴 */
	private String printAuthUrlPattern; /* 출력 권한 URL 패턴 */
	private String downloadAuthUrlPattern; /* 다운로드 권한 URL 패턴 */
	private String otherAuthUrlPattern; /* 기타 권한 URL 패턴 */
	private String listAuthUrlPattern; /* 목록 조회 권한 URL 패턴 */
	private String lectureMenuMarkYn; /* 강의메뉴표시여부 */

	/* 검색 영역 parameters */
	private String searchMenuId;
	private String searchMenuType;
	private String searchMenuViewType;
	private String searchMenuArea;
	private String searchUpMenuId;
	private Integer searchMenuDepth;
	private Integer searchMenuOrder;
	private String searchMenuStatus;
	private String searchMenuUrl;
	private String searchMenuTitle;
	private String searchMenuImage;
	private String searchDeleteYn;
	private String searchShowYn;
	private String searchInsertUser;
	private String searchInsertDate;
	private String searchUpdateUser;
	private String searchUpdateDate;
	private String searchEtc1;
	private String searchEtc2;

	private String searchCreateAuthUrlPattern; /* 검색 필드 : 생성 권한 URL 패턴 */
	private String searchReadAuthUrlPattern; /* 검색 필드 : 상세 조회 권한 URL 패턴 */
	private String searchUpdateAuthUrlPattern; /* 검색 필드 : 수정 권한 URL 패턴 */
	private String searchDeleteAuthUrlPattern; /* 검색 필드 : 삭제 권한 URL 패턴 */
	private String searchPrintAuthUrlPattern; /* 검색 필드 : 출력 권한 URL 패턴 */
	private String searchDownloadAuthUrlPattern; /* 검색 필드 : 다운로드 권한 URL 패턴 */
	private String searchOtherAuthUrlPattern; /* 검색 필드 : 기타 권한 URL 패턴 */
	private String searchListAuthUrlPattern; /* 검색 필드 : 목록 조회 권한 URL 패턴 */
	/**
	 * @return the isLeaf
	 */
	public String getIsLeaf() {
		return isLeaf;
	}
	/**
	 * @param isLeaf the isLeaf to set
	 */
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	/**
	 * @return the expanded
	 */
	public String getExpanded() {
		return expanded;
	}
	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	/**
	 * @return the loaded
	 */
	public String getLoaded() {
		return loaded;
	}
	/**
	 * @param loaded the loaded to set
	 */
	public void setLoaded(String loaded) {
		this.loaded = loaded;
	}
	/**
	 * @return the upMenuTitle
	 */
	public String getUpMenuTitle() {
		return upMenuTitle;
	}
	/**
	 * @param upMenuTitle the upMenuTitle to set
	 */
	public void setUpMenuTitle(String upMenuTitle) {
		this.upMenuTitle = upMenuTitle;
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
	 * @return the menuId
	 */
	public String getMenuIdTemp() {
		return menuIdTemp;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuIdTemp(String menuIdTemp) {
		this.menuIdTemp = menuIdTemp;
	}
	/**
	 * @return the delMenuId
	 */
	public String getDelMenuId() {
		return delMenuId;
	}
	/**
	 * @param delMenuId the delMenuId to set
	 */
	public void setDelMenuId(String delMenuId) {
		this.delMenuId = delMenuId;
	}
	/**
	 * @return the delMenuIdList
	 */
	public String[] getDelMenuIdList() {
		return delMenuIdList;
	}
	/**
	 * @param delMenuIdList the delMenuIdList to set
	 */
	public void setDelMenuIdList(String[] delMenuIdList) {
		this.delMenuIdList = delMenuIdList;
	}
	/**
	 * @return the menuType
	 */
	public String getMenuType() {
		return menuType;
	}
	/**
	 * @param menuType the menuType to set
	 */
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	/**
	 * @return the menuViewTypeCode
	 */
	public String getMenuViewTypeCode() {
		return menuViewTypeCode;
	}
	/**
	 * @param menuViewTypeCode the menuViewTypeCode to set
	 */
	public void setMenuViewTypeCode(String menuViewTypeCode) {
		this.menuViewTypeCode = menuViewTypeCode;
	}
	/**
	 * @return the menuArea
	 */
	public String getMenuArea() {
		return menuArea;
	}
	/**
	 * @param menuArea the menuArea to set
	 */
	public void setMenuArea(String menuArea) {
		this.menuArea = menuArea;
	}
	/**
	 * @return the upMenuId
	 */
	public String getUpMenuId() {
		return upMenuId;
	}
	/**
	 * @param upMenuId the upMenuId to set
	 */
	public void setUpMenuId(String upMenuId) {
		this.upMenuId = upMenuId;
	}
	/**
	 * @return the menuDepth
	 */
	public Integer getMenuDepth() {
		return menuDepth;
	}
	/**
	 * @param menuDepth the menuDepth to set
	 */
	public void setMenuDepth(Integer menuDepth) {
		this.menuDepth = menuDepth;
	}
	/**
	 * @return the menuOrder
	 */
	public Integer getMenuOrder() {
		return menuOrder;
	}
	/**
	 * @param menuOrder the menuOrder to set
	 */
	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}
	/**
	 * @return the menuStatus
	 */
	public String getMenuStatus() {
		return menuStatus;
	}
	/**
	 * @param menuStatus the menuStatus to set
	 */
	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	 * @return the menuImage
	 */
	public String getMenuImage() {
		return menuImage;
	}
	/**
	 * @param menuImage the menuImage to set
	 */
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
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
	 * @return the showYn
	 */
	public String getShowYn() {
		return showYn;
	}
	/**
	 * @param showYn the showYn to set
	 */
	public void setShowYn(String showYn) {
		this.showYn = showYn;
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
	 * @return the etc1
	 */
	public String getEtc1() {
		return etc1;
	}
	/**
	 * @param etc1 the etc1 to set
	 */
	public void setEtc1(String etc1) {
		this.etc1 = etc1;
	}
	/**
	 * @return the etc2
	 */
	public String getEtc2() {
		return etc2;
	}
	/**
	 * @param etc2 the etc2 to set
	 */
	public void setEtc2(String etc2) {
		this.etc2 = etc2;
	}
	/**
	 * @return the createAuthUrlPattern
	 */
	public String getCreateAuthUrlPattern() {
		return createAuthUrlPattern;
	}
	/**
	 * @param createAuthUrlPattern the createAuthUrlPattern to set
	 */
	public void setCreateAuthUrlPattern(String createAuthUrlPattern) {
		this.createAuthUrlPattern = createAuthUrlPattern;
	}
	/**
	 * @return the readAuthUrlPattern
	 */
	public String getReadAuthUrlPattern() {
		return readAuthUrlPattern;
	}
	/**
	 * @param readAuthUrlPattern the readAuthUrlPattern to set
	 */
	public void setReadAuthUrlPattern(String readAuthUrlPattern) {
		this.readAuthUrlPattern = readAuthUrlPattern;
	}
	/**
	 * @return the updateAuthUrlPattern
	 */
	public String getUpdateAuthUrlPattern() {
		return updateAuthUrlPattern;
	}
	/**
	 * @param updateAuthUrlPattern the updateAuthUrlPattern to set
	 */
	public void setUpdateAuthUrlPattern(String updateAuthUrlPattern) {
		this.updateAuthUrlPattern = updateAuthUrlPattern;
	}
	/**
	 * @return the deleteAuthUrlPattern
	 */
	public String getDeleteAuthUrlPattern() {
		return deleteAuthUrlPattern;
	}
	/**
	 * @param deleteAuthUrlPattern the deleteAuthUrlPattern to set
	 */
	public void setDeleteAuthUrlPattern(String deleteAuthUrlPattern) {
		this.deleteAuthUrlPattern = deleteAuthUrlPattern;
	}
	/**
	 * @return the printAuthUrlPattern
	 */
	public String getPrintAuthUrlPattern() {
		return printAuthUrlPattern;
	}
	/**
	 * @param printAuthUrlPattern the printAuthUrlPattern to set
	 */
	public void setPrintAuthUrlPattern(String printAuthUrlPattern) {
		this.printAuthUrlPattern = printAuthUrlPattern;
	}
	/**
	 * @return the downloadAuthUrlPattern
	 */
	public String getDownloadAuthUrlPattern() {
		return downloadAuthUrlPattern;
	}
	/**
	 * @param downloadAuthUrlPattern the downloadAuthUrlPattern to set
	 */
	public void setDownloadAuthUrlPattern(String downloadAuthUrlPattern) {
		this.downloadAuthUrlPattern = downloadAuthUrlPattern;
	}
	/**
	 * @return the otherAuthUrlPattern
	 */
	public String getOtherAuthUrlPattern() {
		return otherAuthUrlPattern;
	}
	/**
	 * @param otherAuthUrlPattern the otherAuthUrlPattern to set
	 */
	public void setOtherAuthUrlPattern(String otherAuthUrlPattern) {
		this.otherAuthUrlPattern = otherAuthUrlPattern;
	}
	/**
	 * @return the listAuthUrlPattern
	 */
	public String getListAuthUrlPattern() {
		return listAuthUrlPattern;
	}
	/**
	 * @param listAuthUrlPattern the listAuthUrlPattern to set
	 */
	public void setListAuthUrlPattern(String listAuthUrlPattern) {
		this.listAuthUrlPattern = listAuthUrlPattern;
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
	 * @return the searchMenuType
	 */
	public String getSearchMenuType() {
		return searchMenuType;
	}
	/**
	 * @param searchMenuType the searchMenuType to set
	 */
	public void setSearchMenuType(String searchMenuType) {
		this.searchMenuType = searchMenuType;
	}
	/**
	 * @return the searchMenuViewType
	 */
	public String getSearchMenuViewType() {
		return searchMenuViewType;
	}
	/**
	 * @param searchMenuViewType the searchMenuViewType to set
	 */
	public void setSearchMenuViewType(String searchMenuViewType) {
		this.searchMenuViewType = searchMenuViewType;
	}
	/**
	 * @return the searchMenuArea
	 */
	public String getSearchMenuArea() {
		return searchMenuArea;
	}
	/**
	 * @param searchMenuArea the searchMenuArea to set
	 */
	public void setSearchMenuArea(String searchMenuArea) {
		this.searchMenuArea = searchMenuArea;
	}
	/**
	 * @return the searchUpMenuId
	 */
	public String getSearchUpMenuId() {
		return searchUpMenuId;
	}
	/**
	 * @param searchUpMenuId the searchUpMenuId to set
	 */
	public void setSearchUpMenuId(String searchUpMenuId) {
		this.searchUpMenuId = searchUpMenuId;
	}
	/**
	 * @return the searchMenuDepth
	 */
	public Integer getSearchMenuDepth() {
		return searchMenuDepth;
	}
	/**
	 * @param searchMenuDepth the searchMenuDepth to set
	 */
	public void setSearchMenuDepth(Integer searchMenuDepth) {
		this.searchMenuDepth = searchMenuDepth;
	}
	/**
	 * @return the searchMenuOrder
	 */
	public Integer getSearchMenuOrder() {
		return searchMenuOrder;
	}
	/**
	 * @param searchMenuOrder the searchMenuOrder to set
	 */
	public void setSearchMenuOrder(Integer searchMenuOrder) {
		this.searchMenuOrder = searchMenuOrder;
	}
	/**
	 * @return the searchMenuStatus
	 */
	public String getSearchMenuStatus() {
		return searchMenuStatus;
	}
	/**
	 * @param searchMenuStatus the searchMenuStatus to set
	 */
	public void setSearchMenuStatus(String searchMenuStatus) {
		this.searchMenuStatus = searchMenuStatus;
	}
	/**
	 * @return the searchMenuUrl
	 */
	public String getSearchMenuUrl() {
		return searchMenuUrl;
	}
	/**
	 * @param searchMenuUrl the searchMenuUrl to set
	 */
	public void setSearchMenuUrl(String searchMenuUrl) {
		this.searchMenuUrl = searchMenuUrl;
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
	 * @return the searchMenuImage
	 */
	public String getSearchMenuImage() {
		return searchMenuImage;
	}
	/**
	 * @param searchMenuImage the searchMenuImage to set
	 */
	public void setSearchMenuImage(String searchMenuImage) {
		this.searchMenuImage = searchMenuImage;
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
	 * @return the searchShowYn
	 */
	public String getSearchShowYn() {
		return searchShowYn;
	}
	/**
	 * @param searchShowYn the searchShowYn to set
	 */
	public void setSearchShowYn(String searchShowYn) {
		this.searchShowYn = searchShowYn;
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
	 * @return the searchEtc1
	 */
	public String getSearchEtc1() {
		return searchEtc1;
	}
	/**
	 * @param searchEtc1 the searchEtc1 to set
	 */
	public void setSearchEtc1(String searchEtc1) {
		this.searchEtc1 = searchEtc1;
	}
	/**
	 * @return the searchEtc2
	 */
	public String getSearchEtc2() {
		return searchEtc2;
	}
	/**
	 * @param searchEtc2 the searchEtc2 to set
	 */
	public void setSearchEtc2(String searchEtc2) {
		this.searchEtc2 = searchEtc2;
	}
	/**
	 * @return the searchCreateAuthUrlPattern
	 */
	public String getSearchCreateAuthUrlPattern() {
		return searchCreateAuthUrlPattern;
	}
	/**
	 * @param searchCreateAuthUrlPattern the searchCreateAuthUrlPattern to set
	 */
	public void setSearchCreateAuthUrlPattern(String searchCreateAuthUrlPattern) {
		this.searchCreateAuthUrlPattern = searchCreateAuthUrlPattern;
	}
	/**
	 * @return the searchReadAuthUrlPattern
	 */
	public String getSearchReadAuthUrlPattern() {
		return searchReadAuthUrlPattern;
	}
	/**
	 * @param searchReadAuthUrlPattern the searchReadAuthUrlPattern to set
	 */
	public void setSearchReadAuthUrlPattern(String searchReadAuthUrlPattern) {
		this.searchReadAuthUrlPattern = searchReadAuthUrlPattern;
	}
	/**
	 * @return the searchUpdateAuthUrlPattern
	 */
	public String getSearchUpdateAuthUrlPattern() {
		return searchUpdateAuthUrlPattern;
	}
	/**
	 * @param searchUpdateAuthUrlPattern the searchUpdateAuthUrlPattern to set
	 */
	public void setSearchUpdateAuthUrlPattern(String searchUpdateAuthUrlPattern) {
		this.searchUpdateAuthUrlPattern = searchUpdateAuthUrlPattern;
	}
	/**
	 * @return the searchDeleteAuthUrlPattern
	 */
	public String getSearchDeleteAuthUrlPattern() {
		return searchDeleteAuthUrlPattern;
	}
	/**
	 * @param searchDeleteAuthUrlPattern the searchDeleteAuthUrlPattern to set
	 */
	public void setSearchDeleteAuthUrlPattern(String searchDeleteAuthUrlPattern) {
		this.searchDeleteAuthUrlPattern = searchDeleteAuthUrlPattern;
	}
	/**
	 * @return the searchPrintAuthUrlPattern
	 */
	public String getSearchPrintAuthUrlPattern() {
		return searchPrintAuthUrlPattern;
	}
	/**
	 * @param searchPrintAuthUrlPattern the searchPrintAuthUrlPattern to set
	 */
	public void setSearchPrintAuthUrlPattern(String searchPrintAuthUrlPattern) {
		this.searchPrintAuthUrlPattern = searchPrintAuthUrlPattern;
	}
	/**
	 * @return the searchDownloadAuthUrlPattern
	 */
	public String getSearchDownloadAuthUrlPattern() {
		return searchDownloadAuthUrlPattern;
	}
	/**
	 * @param searchDownloadAuthUrlPattern the searchDownloadAuthUrlPattern to set
	 */
	public void setSearchDownloadAuthUrlPattern(String searchDownloadAuthUrlPattern) {
		this.searchDownloadAuthUrlPattern = searchDownloadAuthUrlPattern;
	}
	/**
	 * @return the searchOtherAuthUrlPattern
	 */
	public String getSearchOtherAuthUrlPattern() {
		return searchOtherAuthUrlPattern;
	}
	/**
	 * @param searchOtherAuthUrlPattern the searchOtherAuthUrlPattern to set
	 */
	public void setSearchOtherAuthUrlPattern(String searchOtherAuthUrlPattern) {
		this.searchOtherAuthUrlPattern = searchOtherAuthUrlPattern;
	}
	/**
	 * @return the searchListAuthUrlPattern
	 */
	public String getSearchListAuthUrlPattern() {
		return searchListAuthUrlPattern;
	}
	/**
	 * @param searchListAuthUrlPattern the searchListAuthUrlPattern to set
	 */
	public void setSearchListAuthUrlPattern(String searchListAuthUrlPattern) {
		this.searchListAuthUrlPattern = searchListAuthUrlPattern;
	}
	public Integer getMexMenuOrder() {
		return mexMenuOrder;
	}
	public void setMexMenuOrder(Integer mexMenuOrder) {
		this.mexMenuOrder = mexMenuOrder;
	}
	public String getLectureMenuMarkYn() {
		return lectureMenuMarkYn;
	}
	public void setLectureMenuMarkYn(String lectureMenuMarkYn) {
		this.lectureMenuMarkYn = lectureMenuMarkYn;
	}
}
