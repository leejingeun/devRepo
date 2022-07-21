
/*******************************************************************************
 * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of sitglobal LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2016. 11. 21.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.commbiz.cmmcode.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

/**
* VO 클래스에 대한 내용을 작성한다.
* @author 이진근
* @since 2016. 10. 27.
*/
public class CommonCodeVO  extends BaseVO implements Serializable{

	 /**
	 *
	 */
	private static final long serialVersionUID = 1947919093570677418L;

	private String codeId = "";
    private String codeName = "";
    private String codeGroup = "";
    private String lecId = "";
    private String companyId;					//기업아이디
    private String traningProcessId;			//훈련과정아이디
    private String yyyy	= ""; 					//학년도
    private String term	= ""; 					//학기
    private String subjectCode	= ""; 			//교과목코드
    private String subjectName	= ""; 			//교과목명
    private String subClass	= ""; 				//분반
    private String weekCnt	= ""; 				//학습주차

	/**
     * @return the codeId
     */
    public String getCodeId() {
        return codeId;
    }

    /**
     * @param codeId the codeId to set
     */
    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    /**
     * @return the codeName
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * @param codeName the codeName to set
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * @return the codeGroup
     */
    public String getCodeGroup() {
		return codeGroup;
	}

    /**
     * @param codeGroup the codeGroup to set
     */
	public void setCodeGroup(String codeGroup) {
		this.codeGroup = codeGroup;
	}

	/**
     * @return the lecId
     */
	public String getLecId() {
		return lecId;
	}

	 /**
     * @param lecId the lecId to set
     */
	public void setLecId(String lecId) {
		this.lecId = lecId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getTraningProcessId() {
		return traningProcessId;
	}

	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

}
