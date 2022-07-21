/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * AA    2016. 07. 01.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.atchFile.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
 * ATCH_FILE 테이블에 대응되는 VO 클래스입니다.
 * 
 * @author AA
 * @since 2016. 07. 01.
 */
public class AtchFileVO extends BaseVO implements Serializable {
	private static final long serialVersionUID = 2894937640998115979L;

	private String atchFileId;
	private String[] atchFileIdArr;
	private Integer fileSn;
	private String fileSavePath;
	private String saveFileName;
	private String orgFileName;
	private String fileExtn;
	private long fileSize;
	private Integer downCnt;
	private String insertUser;
	private String insertDate;
	private String updateUser;
	private String updateDate;
	private String deleteYn;

	/* 검색 영역 parameters */
	private String searchAtchFileId;
	private Integer searchFileSn;
	private String searchFileSavePath;
	private String searchSaveFileName;
	private String searchOrgFileName;
	private String searchFileExtn;
	private Integer searchFileSize;
	private Integer searchDownCnt;
	private String searchInsertUser;
	private String searchInsertDate;
	private String searchUpdateUser;
	private String searchUpdateDate;
	private String searchDeleteYn;

	public String[] getAtchFileIdArr() {
		return atchFileIdArr;
	}

	public void setAtchFileIdArr(String[] atchFileIdArr) {
		this.atchFileIdArr = atchFileIdArr;
	}

	/**
	 * @return the atchFileId
	 */
	public String getAtchFileId() {
		return atchFileId;
	}

	/**
	 * @param atchFileId the atchFileId to set
	 */
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	/**
	 * @return the fileSn
	 */
	public Integer getFileSn() {
		return fileSn;
	}

	/**
	 * @param fileSn the fileSn to set
	 */
	public void setFileSn(Integer fileSn) {
		this.fileSn = fileSn;
	}

	/**
	 * @return the fileSavePath
	 */
	public String getFileSavePath() {
		return fileSavePath;
	}

	/**
	 * @param fileSavePath the fileSavePath to set
	 */
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	/**
	 * @return the saveFileName
	 */
	public String getSaveFileName() {
		return saveFileName;
	}

	/**
	 * @param saveFileName the saveFileName to set
	 */
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	/**
	 * @return the orgFileName
	 */
	public String getOrgFileName() {
		return orgFileName;
	}

	/**
	 * @param orgFileName the orgFileName to set
	 */
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	/**
	 * @return the fileExtn
	 */
	public String getFileExtn() {
		return fileExtn;
	}

	/**
	 * @param fileExtn the fileExtn to set
	 */
	public void setFileExtn(String fileExtn) {
		this.fileExtn = fileExtn;
	}

	/**
	 * @return the fileSize
	 */
	public long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the downCnt
	 */
	public Integer getDownCnt() {
		return downCnt;
	}

	/**
	 * @param downCnt the downCnt to set
	 */
	public void setDownCnt(Integer downCnt) {
		this.downCnt = downCnt;
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
	 * @return the searchAtchFileId
	 */
	public String getSearchAtchFileId() {
		return searchAtchFileId;
	}

	/**
	 * @param searchAtchFileId the searchAtchFileId to set
	 */
	public void setSearchAtchFileId(String searchAtchFileId) {
		this.searchAtchFileId = searchAtchFileId;
	}

	/**
	 * @return the searchFileSn
	 */
	public Integer getSearchFileSn() {
		return searchFileSn;
	}

	/**
	 * @param searchFileSn the searchFileSn to set
	 */
	public void setSearchFileSn(Integer searchFileSn) {
		this.searchFileSn = searchFileSn;
	}

	/**
	 * @return the searchFileSavePath
	 */
	public String getSearchFileSavePath() {
		return searchFileSavePath;
	}

	/**
	 * @param searchFileSavePath the searchFileSavePath to set
	 */
	public void setSearchFileSavePath(String searchFileSavePath) {
		this.searchFileSavePath = searchFileSavePath;
	}

	/**
	 * @return the searchSaveFileName
	 */
	public String getSearchSaveFileName() {
		return searchSaveFileName;
	}

	/**
	 * @param searchSaveFileName the searchSaveFileName to set
	 */
	public void setSearchSaveFileName(String searchSaveFileName) {
		this.searchSaveFileName = searchSaveFileName;
	}

	/**
	 * @return the searchOrgFileName
	 */
	public String getSearchOrgFileName() {
		return searchOrgFileName;
	}

	/**
	 * @param searchOrgFileName the searchOrgFileName to set
	 */
	public void setSearchOrgFileName(String searchOrgFileName) {
		this.searchOrgFileName = searchOrgFileName;
	}

	/**
	 * @return the searchFileExtn
	 */
	public String getSearchFileExtn() {
		return searchFileExtn;
	}

	/**
	 * @param searchFileExtn the searchFileExtn to set
	 */
	public void setSearchFileExtn(String searchFileExtn) {
		this.searchFileExtn = searchFileExtn;
	}

	/**
	 * @return the searchFileSize
	 */
	public Integer getSearchFileSize() {
		return searchFileSize;
	}

	/**
	 * @param searchFileSize the searchFileSize to set
	 */
	public void setSearchFileSize(Integer searchFileSize) {
		this.searchFileSize = searchFileSize;
	}

	/**
	 * @return the searchDownCnt
	 */
	public Integer getSearchDownCnt() {
		return searchDownCnt;
	}

	/**
	 * @param searchDownCnt the searchDownCnt to set
	 */
	public void setSearchDownCnt(Integer searchDownCnt) {
		this.searchDownCnt = searchDownCnt;
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

}
