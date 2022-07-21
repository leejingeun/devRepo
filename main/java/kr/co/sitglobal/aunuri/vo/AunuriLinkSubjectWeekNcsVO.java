/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 06.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.aunuri.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.co.sitglobal.oklms.comm.util.TextStringUtil;
import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.web.multipart.MultipartFile;

/**
 * VO 클래스에 대한 내용을 작성한다.
 * 
 * @author 이진근
 * @since 2017. 01. 06.
 */
public class AunuriLinkSubjectWeekNcsVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 3732756549104255004L;
	
	private String  weekId = "";
	private String  yyyy = "";
	private String  term = "";
	private String  subjectCode = "";
	private String  subjectClass = "";
	private String  weekCnt = "";
	
	private String ncsUnitId = "";
	private String ncsUnitName = "";
	private String ncsElemId = "";
	private String ncsElemName = "";
	private String lessonCn = "";
	
	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
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
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectClass() {
		return subjectClass;
	}
	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
	}
	public String getWeekCnt() {
		return weekCnt;
	}
	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}
	public String getNcsUnitId() {
		return ncsUnitId;
	}
	public void setNcsUnitId(String ncsUnitId) {
		this.ncsUnitId = ncsUnitId;
	}
	public String getNcsUnitName() {
		return ncsUnitName;
	}
	public void setNcsUnitName(String ncsUnitName) {
		this.ncsUnitName = ncsUnitName;
	}
	public String getNcsElemId() {
		return ncsElemId;
	}
	public void setNcsElemId(String ncsElemId) {
		this.ncsElemId = ncsElemId;
	}
	public String getNcsElemName() {
		return ncsElemName;
	}
	public void setNcsElemName(String ncsElemName) {
		this.ncsElemName = ncsElemName;
	}
	public String getLessonCn() {
		return lessonCn;
	}
	public void setLessonCn(String lessonCn) {
		this.lessonCn = lessonCn;
	}
	
}
