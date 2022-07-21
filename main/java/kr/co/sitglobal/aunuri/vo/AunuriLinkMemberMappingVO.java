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
public class AunuriLinkMemberMappingVO extends BaseVO implements Serializable {

	private static final long serialVersionUID = 6988247455840545482L;
	
	private String yyyy                    ;
	private String term                    ;
	private String subjectCode            ;
	private String subjectClass           ;
	private String memId ;
	private String memSeq ;
	private String deptNo ;
	private String subjInsMappingId ;
	private String subjCdpMappingId ; 
	
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
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getSubjInsMappingId() {
		return subjInsMappingId;
	}
	public void setSubjInsMappingId(String subjInsMappingId) {
		this.subjInsMappingId = subjInsMappingId;
	}
	public String getSubjCdpMappingId() {
		return subjCdpMappingId;
	}
	public void setSubjCdpMappingId(String subjCdpMappingId) {
		this.subjCdpMappingId = subjCdpMappingId;
	}
	
	
}
