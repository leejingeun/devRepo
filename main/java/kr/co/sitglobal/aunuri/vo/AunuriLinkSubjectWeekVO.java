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
public class AunuriLinkSubjectWeekVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = -3764405838564972550L;
	
	private String  weekId = "";
	private String  yyyy = "";
	private String  term = "";
	private String  subjectCode = "";
	private String  subjectClass = "";
	private String  weekCnt = "";
	
	private String  timeId = "";
	private String  traningDate = "";
	private String  traningStHour = "";
	private String  traningStMin = "";
	private String  traningEdHour = "";
	private String  traningEdMin = "";
	private String  lessonCn = "";
	private String  lessonMthd = "";
	
	public String getLessonMthd() {
		return lessonMthd;
	}
	public void setLessonMthd(String lessonMthd) {
		this.lessonMthd = lessonMthd;
	}
	public String getLessonCn() {
		return lessonCn;
	}
	public void setLessonCn(String lessonCn) {
		this.lessonCn = lessonCn;
	}
	
	
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
	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
	}
	public String getTraningDate() {
		return traningDate;
	}
	public void setTraningDate(String traningDate) {
		this.traningDate = traningDate;
	}
	public String getTraningStHour() {
		return traningStHour;
	}
	public void setTraningStHour(String traningStHour) {
		this.traningStHour = traningStHour;
	}
	public String getTraningStMin() {
		return traningStMin;
	}
	public void setTraningStMin(String traningStMin) {
		this.traningStMin = traningStMin;
	}
	public String getTraningEdHour() {
		return traningEdHour;
	}
	public void setTraningEdHour(String traningEdHour) {
		this.traningEdHour = traningEdHour;
	}
	public String getTraningEdMin() {
		return traningEdMin;
	}
	public void setTraningEdMin(String traningEdMin) {
		this.traningEdMin = traningEdMin;
	}
	
}
