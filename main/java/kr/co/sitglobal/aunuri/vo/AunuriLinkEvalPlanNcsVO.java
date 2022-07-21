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

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * VO 클래스에 대한 내용을 작성한다.
 *
 * @author 이진근
 * @since 2017. 01. 06.
 */
public class AunuriLinkEvalPlanNcsVO extends BaseVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -914523998040501813L;

	private String planEvId = "";
	private String yyyy = "";
	private String term = "";
	private String subjectCode = "";
	private String subClass = "";
	private String weekCnt = "";

	private String ncsUnitId = "";
	private String ncsUnitName = "";
	private String ncsElemId = "";
	private String ncsElemName = "";
	private String evDivCd = "";
	private String evDivName = "";
	private String etcName = "";
	private String evRt = "";
	private String elemNumCd = "";
	private String elemVw = "";
	private String cdDiv = "";
	private String dtlCd = "";
	private String dtlCdName = "";
	private String entDataName = "";
    private String lessonCn = "";
    private String seq = "";

	private String atchFileId = "";
	private String deleteYn = "";

	private String rn = "";
	private String rowspan = "";

	private String codeId = "";
	private String codeName = "";
	private String companyName = "";
	private String tutorName = "";

	public String getPlanEvId() {
		return planEvId;
	}
	public void setPlanEvId(String planEvId) {
		this.planEvId = planEvId;
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
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
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
	public String getEvDivCd() {
		return evDivCd;
	}
	public void setEvDivCd(String evDivCd) {
		this.evDivCd = evDivCd;
	}
	public String getEvDivName() {
		return evDivName;
	}
	public void setEvDivName(String evDivName) {
		this.evDivName = evDivName;
	}
	public String getEtcName() {
		return etcName;
	}
	public void setEtcName(String etcName) {
		this.etcName = etcName;
	}
	public String getEvRt() {
		return evRt;
	}
	public void setEvRt(String evRt) {
		this.evRt = evRt;
	}
	public String getElemNumCd() {
		return elemNumCd;
	}
	public void setElemNumCd(String elemNumCd) {
		this.elemNumCd = elemNumCd;
	}
	public String getElemVw() {
		return elemVw;
	}
	public void setElemVw(String elemVw) {
		this.elemVw = elemVw;
	}
	public String getLessonCn() {
		return lessonCn;
	}
	public void setLessonCn(String lessonCn) {
		this.lessonCn = lessonCn;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getRn() {
		return rn;
	}
	public void setRn(String rn) {
		this.rn = rn;
	}
	public String getRowspan() {
		return rowspan;
	}
	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}
	public String getCdDiv() {
		return cdDiv;
	}
	public void setCdDiv(String cdDiv) {
		this.cdDiv = cdDiv;
	}
	public String getDtlCd() {
		return dtlCd;
	}
	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}
	public String getDtlCdName() {
		return dtlCdName;
	}
	public void setDtlCdName(String dtlCdName) {
		this.dtlCdName = dtlCdName;
	}
	public String getEntDataName() {
		return entDataName;
	}
	public void setEntDataName(String entDataName) {
		this.entDataName = entDataName;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTutorName() {
		return tutorName;
	}
	public void setTutorName(String tutorName) {
		this.tutorName = tutorName;
	}



	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}
