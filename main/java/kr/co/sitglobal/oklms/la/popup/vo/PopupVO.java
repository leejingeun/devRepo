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
package kr.co.sitglobal.oklms.la.popup.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * COM_POPUP 테이블에 대응되는 VO 클래스입니다.
 * 
 */
public class PopupVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 7421979671774966465L;
	private String popupId;//팝업 아이디
	private String pageType;//노출위치(공통그룹코드)
	private String title;//제목
	private String startDate;//게시기간 시작일자
	private String startDateTime;//게시기간 시작시간
	private String finishDate;//게시기간 종료일자
	private String finishDateTime;//게시기간 종료시간
	
	private String popupType;//팝업형식 (w: windows, l: layer)
	private String isCloseViewSettings;//그만 보기 설정 값들 (d: 오늘하루그만보기, w: 일주일그만보기)
	private String popupWidth;//팝업창 가로크기
	private String popupHeight;//팝업창 세로크기
	private String positionTop;//팝업 상단위치

	private String positionLeft;//팝업 왼쪽위치
	private String contentType;//컨텐츠 종류 (i: 이미지 업로드, h: html)
	private String imageFileId;//이미지 파일아이디
	private String content;//내용
	private String isUse;//사용여부
	
	private String creatorId;//생성자아이디
	private String createDate;//생성일
	private String modifierId;//수정자아이디
	private String modifyDate;//수정일
	private String pageUrl;//페이지url
	
	private String currentPage;//현재페이지
	private String countPerPage;//페이지당리스트수
	
	private String checkItem;//선택된 데이터
	
	private String orderKey;//정렬
	private String orderDir;//정렬방향
	
	private String fileName;//이미지파일
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}
	public String getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	public String getCheckItem() {
		return checkItem;
	}
	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getFinishDateTime() {
		return finishDateTime;
	}
	public void setFinishDateTime(String finishDateTime) {
		this.finishDateTime = finishDateTime;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(String countPerPage) {
		this.countPerPage = countPerPage;
	}
	public String getPopupId() {
		return popupId;
	}
	public void setPopupId(String popupId) {
		this.popupId = popupId;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getPopupType() {
		return popupType;
	}
	public void setPopupType(String popupType) {
		this.popupType = popupType;
	}
	public String getIsCloseViewSettings() {
		return isCloseViewSettings;
	}
	public void setIsCloseViewSettings(String isCloseViewSettings) {
		this.isCloseViewSettings = isCloseViewSettings;
	}
	public String getPopupWidth() {
		return popupWidth;
	}
	public void setPopupWidth(String popupWidth) {
		this.popupWidth = popupWidth;
	}
	public String getPopupHeight() {
		return popupHeight;
	}
	public void setPopupHeight(String popupHeight) {
		this.popupHeight = popupHeight;
	}
	public String getPositionTop() {
		return positionTop;
	}
	public void setPositionTop(String positionTop) {
		this.positionTop = positionTop;
	}
	public String getPositionLeft() {
		return positionLeft;
	}
	public void setPositionLeft(String positionLeft) {
		this.positionLeft = positionLeft;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getImageFileId() {
		return imageFileId;
	}
	public void setImageFileId(String imageFileId) {
		this.imageFileId = imageFileId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsUse() {
		return isUse;
	}
	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	@Override
	public String toString() {
		return "PopupVO [popupId=" + popupId + ", pageType=" + pageType
				+ ", title=" + title + ", startDate=" + startDate
				+ ", startDateTime=" + startDateTime + ", finishDate="
				+ finishDate + ", finishDateTime=" + finishDateTime
				+ ", popupType=" + popupType + ", isCloseViewSettings="
				+ isCloseViewSettings + ", popupWidth=" + popupWidth
				+ ", popupHeight=" + popupHeight + ", positionTop="
				+ positionTop + ", positionLeft=" + positionLeft
				+ ", contentType=" + contentType + ", imageFileId="
				+ imageFileId + ", content=" + content + ", isUse=" + isUse
				+ ", creatorId=" + creatorId + ", createDate=" + createDate
				+ ", modifierId=" + modifierId + ", modifyDate=" + modifyDate
				+ ", pageUrl=" + pageUrl + ", currentPage=" + currentPage
				+ ", countPerPage=" + countPerPage + ", checkItem=" + checkItem
				+ ", orderKey=" + orderKey + ", orderDir=" + orderDir + "]";
	}
}
