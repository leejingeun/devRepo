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
public class AunuriLinkSubjectVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = -6272038949002138626L;
	
	private String yyyy                    ;
	private String term                    ;
	private String subjectCode            ;
	private String subjectClass           ;
	private String subjectName            ;
	private String departmentNo           ;
	private String departmentName         ;
	private String subjectType            ;
	private String pointUseYn            ;
	private String point                   ;
	private String traningHour            ;
	private String subjectTraningType    ;
	private String onlineType             ;
	private String lctrumCd               ;
	private String lctrumNm               ;
	private String gradeRatio							;
	private String memId ;
	private String firstStudyYn ;
	private String historyId;
	
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getFirstStudyYn() {
		return firstStudyYn;
	}
	public void setFirstStudyYn(String firstStudyYn) {
		this.firstStudyYn = firstStudyYn;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getGradeRatio() {
		return gradeRatio;
	}
	public void setGradeRatio(String gradeRatio) {
		this.gradeRatio = gradeRatio;
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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getDepartmentNo() {
		return departmentNo;
	}
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getPointUseYn() {
		return pointUseYn;
	}
	public void setPointUseYn(String pointUseYn) {
		this.pointUseYn = pointUseYn;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getTraningHour() {
		return traningHour;
	}
	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}
	public String getSubjectTraningType() {
		return subjectTraningType;
	}
	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}
	public String getOnlineType() {
		return onlineType;
	}
	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}
	public String getLctrumCd() {
		return lctrumCd;
	}
	public void setLctrumCd(String lctrumCd) {
		this.lctrumCd = lctrumCd;
	}
	public String getLctrumNm() {
		return lctrumNm;
	}
	public void setLctrumNm(String lctrumNm) {
		this.lctrumNm = lctrumNm;
	}
	
	
}
