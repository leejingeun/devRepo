/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 04. 17.         First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.planeval.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanEvalVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5679600031795380679L;

	private String manageItemSetId = "";
	private String planEvId = "";
	private String stdScoreId = "";
	private String lectureNcsElemId = "";
	private String yyyy = "";
	private String term = "";
	private String tmpTerm = "";
	private String subjectCode = "";
	private String subClass = "";
	private String subjectName = "";
	private String weekCnt = "";
	private String subjectTraningType = "";
	private String subjectType = "";

	private String ncsUnitId = "";
	private String ncsUnitName = "";
	private String ncsElemId = "";
	private String ncsElemName = "";
	private String evDivCd = "";
	private String evDivName = "";
	private String evDivDate = "";
	private String etcName = "";
	private String evRt = "";
	private String elemNumCd = "";
	private String elemVw = "";
	private String cdDiv = "";
	private String dtlCd = "";
	private String dtlCdName = "";
	private String dtlCdCheckYn = "";
	private String entDataName = "";
    private String lessonCn = "";
    private String evalScore = "";
    private String oldEvalScore = "";
    private String newEvalScore = "";
    private String lessonCnPassAt = "";
    private String oldLessonCnPassAt = "";
    private String newLessonCnPassAt = "";
    private String lessonCnPass = "";
    private String dayOfWeek = "";

    private String atchFileId = "";
	private String deleteYn = "";

	private String rn = "";
	private String rowspan = "";

	private String memSeq = "";
	private String memId = "";
	private String memName = "";

	private String traningProcessId; // 훈련과정아이디
	private String companyId; // 기업아이디

	private String applyApprovalYn; // 승인신청여부
	private String retunReason; 	// 반려사유
	private String status; 			// 상태(1:승인대기, 2:승인, 3:반려)
	private String changeStatus;

	int failCnt = 0;
	int passCnt = 0;
	int updateCnt = 0;
	int stdScoreCnt = 0;
	int comDetailFileCnt = 0;

	private String searchYyyy = "";
	private String searchTerm = "";
	private String searchSubjectCode = "";
	private String searchSubClass = "";
	private String searchSubjectTraningType = "";

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
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public String getDtlCdCheckYn() {
		return dtlCdCheckYn;
	}
	public void setDtlCdCheckYn(String dtlCdCheckYn) {
		this.dtlCdCheckYn = dtlCdCheckYn;
	}
	public String getEntDataName() {
		return entDataName;
	}
	public void setEntDataName(String entDataName) {
		this.entDataName = entDataName;
	}
	public String getLessonCn() {
		return lessonCn;
	}
	public void setLessonCn(String lessonCn) {
		this.lessonCn = lessonCn;
	}
	public String getEvalScore() {
		return evalScore;
	}
	public void setEvalScore(String evalScore) {
		this.evalScore = evalScore;
	}
	public String getLessonCnPassAt() {
		return lessonCnPassAt;
	}
	public void setLessonCnPassAt(String lessonCnPassAt) {
		this.lessonCnPassAt = lessonCnPassAt;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
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
	public String getEvDivDate() {
		return evDivDate;
	}
	public void setEvDivDate(String evDivDate) {
		this.evDivDate = evDivDate;
	}
	public String getManageItemSetId() {
		return manageItemSetId;
	}
	public void setManageItemSetId(String manageItemSetId) {
		this.manageItemSetId = manageItemSetId;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getLectureNcsElemId() {
		return lectureNcsElemId;
	}
	public void setLectureNcsElemId(String lectureNcsElemId) {
		this.lectureNcsElemId = lectureNcsElemId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getTmpTerm() {
		return tmpTerm;
	}
	public void setTmpTerm(String tmpTerm) {
		this.tmpTerm = tmpTerm;
	}
	public String getLessonCnPass() {
		return lessonCnPass;
	}
	public void setLessonCnPass(String lessonCnPass) {
		this.lessonCnPass = lessonCnPass;
	}
	public String getStdScoreId() {
		return stdScoreId;
	}
	public void setStdScoreId(String stdScoreId) {
		this.stdScoreId = stdScoreId;
	}
	public String getOldLessonCnPassAt() {
		return oldLessonCnPassAt;
	}
	public void setOldLessonCnPassAt(String oldLessonCnPassAt) {
		this.oldLessonCnPassAt = oldLessonCnPassAt;
	}
	public String getNewLessonCnPassAt() {
		return newLessonCnPassAt;
	}
	public void setNewLessonCnPassAt(String newLessonCnPassAt) {
		this.newLessonCnPassAt = newLessonCnPassAt;
	}
	public String getOldEvalScore() {
		return oldEvalScore;
	}
	public void setOldEvalScore(String oldEvalScore) {
		this.oldEvalScore = oldEvalScore;
	}
	public String getNewEvalScore() {
		return newEvalScore;
	}
	public void setNewEvalScore(String newEvalScore) {
		this.newEvalScore = newEvalScore;
	}
	public int getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}
	public int getPassCnt() {
		return passCnt;
	}
	public void setPassCnt(int passCnt) {
		this.passCnt = passCnt;
	}
	public int getUpdateCnt() {
		return updateCnt;
	}
	public void setUpdateCnt(int updateCnt) {
		this.updateCnt = updateCnt;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSearchYyyy() {
		return searchYyyy;
	}
	public void setSearchYyyy(String searchYyyy) {
		this.searchYyyy = searchYyyy;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public String getSearchSubjectTraningType() {
		return searchSubjectTraningType;
	}
	public void setSearchSubjectTraningType(String searchSubjectTraningType) {
		this.searchSubjectTraningType = searchSubjectTraningType;
	}
	public String getSearchSubjectCode() {
		return searchSubjectCode;
	}
	public void setSearchSubjectCode(String searchSubjectCode) {
		this.searchSubjectCode = searchSubjectCode;
	}
	public String getSearchSubClass() {
		return searchSubClass;
	}
	public void setSearchSubClass(String searchSubClass) {
		this.searchSubClass = searchSubClass;
	}
	public String getSubjectTraningType() {
		return subjectTraningType;
	}
	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}
	public String getApplyApprovalYn() {
		return applyApprovalYn;
	}
	public void setApplyApprovalYn(String applyApprovalYn) {
		this.applyApprovalYn = applyApprovalYn;
	}
	public String getRetunReason() {
		return retunReason;
	}
	public void setRetunReason(String retunReason) {
		this.retunReason = retunReason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStdScoreCnt() {
		return stdScoreCnt;
	}
	public void setStdScoreCnt(int stdScoreCnt) {
		this.stdScoreCnt = stdScoreCnt;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}
	public int getComDetailFileCnt() {
		return comDetailFileCnt;
	}
	public void setComDetailFileCnt(int comDetailFileCnt) {
		this.comDetailFileCnt = comDetailFileCnt;
	}



	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
