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
public class AunuriLinkSubjectGradeVO extends BaseVO implements Serializable {

	
	private static final long serialVersionUID = 7581922545280718533L;
	
	private String yy                    ;
	private String semstrCd                    ;
	private String memId					;
	private String subjectGrade					;
	private String memName					;
	private String subject1					;
	private String subject2					;
	private String subject3					;
	private String subject4					;
	private String subject5					;
	private String totPnt					;
	private String totLctrePnt					;
	private String totReqstPnt					;
	private String totGetPnt						;
	private String totMarkAvrg					;
	private String memIds					;
	private String groupMemIds					;
	private String  [] groupMemIdArr					;
	private String gradeCnt ;
	
	public String getGradeCnt() {
		return gradeCnt;
	}
	public void setGradeCnt(String gradeCnt) {
		this.gradeCnt = gradeCnt;
	}
	public String[] getGroupMemIdArr() {
		return groupMemIdArr;
	}
	public void setGroupMemIdArr(String[] groupMemIdArr) {
		this.groupMemIdArr = groupMemIdArr;
	}
	public String getMemIds() {
		return memIds;
	}
	public void setMemIds(String memIds) {
		this.memIds = memIds;
	}
	public String getGroupMemIds() {
		return groupMemIds;
	}
	public void setGroupMemIds(String groupMemIds) {
		this.groupMemIds = groupMemIds;
	}
	public String getYy() {
		return yy;
	}
	public void setYy(String yy) {
		this.yy = yy;
	}
	public String getSemstrCd() {
		String result = "";
		if("1".equals(semstrCd)){
			result = "101";
		} else if ("2".equals(semstrCd)){
			result = "102";
		} else if ("3".equals(semstrCd)){
			result = "103";
		} else if ("4".equals(semstrCd)){
			result = "104";
		} else {
			result = semstrCd;
		}
		return result;
	}
	public void setSemstrCd(String semstrCd) {
		this.semstrCd = semstrCd;
	}
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getSubjectGrade() {
		return subjectGrade;
	}
	public void setSubjectGrade(String subjectGrade) {
		this.subjectGrade = subjectGrade;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public String getSubject2() {
		return subject2;
	}
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}
	public String getSubject3() {
		return subject3;
	}
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}
	public String getSubject4() {
		return subject4;
	}
	public void setSubject4(String subject4) {
		this.subject4 = subject4;
	}
	public String getSubject5() {
		return subject5;
	}
	public void setSubject5(String subject5) {
		this.subject5 = subject5;
	}
	public String getTotPnt() {
		return totPnt;
	}
	public void setTotPnt(String totPnt) {
		this.totPnt = totPnt;
	}
	public String getTotLctrePnt() {
		return totLctrePnt;
	}
	public void setTotLctrePnt(String totLctrePnt) {
		this.totLctrePnt = totLctrePnt;
	}
	public String getTotReqstPnt() {
		return totReqstPnt;
	}
	public void setTotReqstPnt(String totReqstPnt) {
		this.totReqstPnt = totReqstPnt;
	}
	public String getTotGetPnt() {
		return totGetPnt;
	}
	public void setTotGetPnt(String totGetPnt) {
		this.totGetPnt = totGetPnt;
	}
	public String getTotMarkAvrg() {
		return totMarkAvrg;
	}
	public void setTotMarkAvrg(String totMarkAvrg) {
		this.totMarkAvrg = totMarkAvrg;
	}
	
}
