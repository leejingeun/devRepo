package kr.co.sitglobal.oklms.lu.interview.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;
import kr.co.sitglobal.oklms.commbiz.atchFile.vo.AtchFileVO;

public class InterviewVO extends BaseVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String interviewNoteId;
	private String interviewNoteDate;
	
	private String interviewNoteDateMmParam;

	private String companyId;
	private String companyName;
	
	private String subjectCode;
	private String subjectName;
	private String traningProcessId;
	private String traningProcessName;	
	
	private String interviewMemSeq;
	private String interviewMemName;
	
	private String trainingTalk;
	private String workTalk;
	private String tempTalk;
	private String totalTalk;
	private String sendYn;
	private String sendDate;
	
	private String atchFileImgId;
	private String atchFileId;
	private String tempSj1;
	private String tempCn1;
	private String tempSj2;
	private String tempCn2;
	private String tempSj3;
	private String tempCn3;
	
	private String interviewMemberId;
	private String interviewMemberSeq;
	private String interviewMemberName;
	
	private String interviewMemberSeqs;
	private String interviewMemberNames;

	private String[] arrInterviewMemberId;
	private String[] arrInterviewMemberSeq;
	private String[] arrInterviewMemberName;

	
	private AtchFileVO resultFile;
	private AtchFileVO resultImgFile;
	private String yyyy;
	private String mm;
	
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public AtchFileVO getResultFile() {
		return resultFile;
	}
	public void setResultFile(AtchFileVO resultFile) {
		this.resultFile = resultFile;
	}
	public AtchFileVO getResultImgFile() {
		return resultImgFile;
	}
	public void setResultImgFile(AtchFileVO resultImgFile) {
		this.resultImgFile = resultImgFile;
	}
	public String getInterviewNoteDateMmParam() {
		return interviewNoteDateMmParam;
	}
	public void setInterviewNoteDateMmParam(String interviewNoteDateMmParam) {
		this.interviewNoteDateMmParam = interviewNoteDateMmParam;
	}
	public String getInterviewMemName() {
		return interviewMemName;
	}
	public void setInterviewMemName(String interviewMemName) {
		this.interviewMemName = interviewMemName;
	}
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getTraningProcessName() {
		return traningProcessName;
	}
	public void setTraningProcessName(String traningProcessName) {
		this.traningProcessName = traningProcessName;
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
	public String getInterviewMemberSeqs() {
		return interviewMemberSeqs;
	}
	public void setInterviewMemberSeqs(String interviewMemberSeqs) {
		this.interviewMemberSeqs = interviewMemberSeqs;
	}
	public String getInterviewMemberNames() {
		return interviewMemberNames;
	}
	public void setInterviewMemberNames(String interviewMemberNames) {
		this.interviewMemberNames = interviewMemberNames;
	}
	public String getInterviewNoteId() {
		return interviewNoteId;
	}
	public void setInterviewNoteId(String interviewNoteId) {
		this.interviewNoteId = interviewNoteId;
	}
	public String getInterviewNoteDate() {
		return interviewNoteDate;
	}
	public void setInterviewNoteDate(String interviewNoteDate) {
		this.interviewNoteDate = interviewNoteDate;
	}	
	public String getInterviewNoteDateMm() {
		String interviewNotemm;
		if(interviewNoteDate!=null&&interviewNoteDate.length()>8){
			interviewNotemm = interviewNoteDate.substring(0, 7);	
		}else{
			interviewNotemm = "";
		}
		return interviewNotemm;
	}
	public String getInterviewNoteMm() {
		String interviewNotemm;
		if(interviewNoteDate!=null&&interviewNoteDate.length()>8){
			interviewNotemm = interviewNoteDate.substring(5, 7);	
		}else{
			interviewNotemm = "";
		}
		return interviewNotemm;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getInterviewMemSeq() {
		return interviewMemSeq;
	}
	public void setInterviewMemSeq(String interviewMemSeq) {
		this.interviewMemSeq = interviewMemSeq;
	}
	public String getTrainingTalk() {
		return trainingTalk;
	}
	public void setTrainingTalk(String trainingTalk) {
		this.trainingTalk = trainingTalk;
	}
	public String getWorkTalk() {
		return workTalk;
	}
	public void setWorkTalk(String workTalk) {
		this.workTalk = workTalk;
	}
	public String getTempTalk() {
		return tempTalk;
	}
	public void setTempTalk(String tempTalk) {
		this.tempTalk = tempTalk;
	}
	public String getTotalTalk() {
		return totalTalk;
	}
	public void setTotalTalk(String totalTalk) {
		this.totalTalk = totalTalk;
	}
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getAtchFileImgId() {
		return atchFileImgId;
	}
	public void setAtchFileImgId(String atchFileImgId) {
		this.atchFileImgId = atchFileImgId;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getTempSj1() {
		return tempSj1;
	}
	public void setTempSj1(String tempSj1) {
		this.tempSj1 = tempSj1;
	}
	public String getTempCn1() {
		return tempCn1;
	}
	public void setTempCn1(String tempCn1) {
		this.tempCn1 = tempCn1;
	}
	public String getTempSj2() {
		return tempSj2;
	}
	public void setTempSj2(String tempSj2) {
		this.tempSj2 = tempSj2;
	}
	public String getTempCn2() {
		return tempCn2;
	}
	public void setTempCn2(String tempCn2) {
		this.tempCn2 = tempCn2;
	}
	public String getTempSj3() {
		return tempSj3;
	}
	public void setTempSj3(String tempSj3) {
		this.tempSj3 = tempSj3;
	}
	public String getTempCn3() {
		return tempCn3;
	}
	public void setTempCn3(String tempCn3) {
		this.tempCn3 = tempCn3;
	}
	public String getInterviewMemberId() {
		return interviewMemberId;
	}
	public void setInterviewMemberId(String interviewMemberId) {
		this.interviewMemberId = interviewMemberId;
	}
	public String getInterviewMemberSeq() {
		return interviewMemberSeq;
	}
	public void setInterviewMemberSeq(String interviewMemberSeq) {
		this.interviewMemberSeq = interviewMemberSeq;
	}
	public String getInterviewMemberName() {
		return interviewMemberName;
	}
	public void setInterviewMemberName(String interviewMemberName) {
		this.interviewMemberName = interviewMemberName;
	}
	public String[] getArrInterviewMemberId() {
		return arrInterviewMemberId;
	}
	public void setArrInterviewMemberId(String[] arrInterviewMemberId) {
		this.arrInterviewMemberId = arrInterviewMemberId;
	}
	public String[] getArrInterviewMemberSeq() {
		return arrInterviewMemberSeq;
	}
	public void setArrInterviewMemberSeq(String[] arrInterviewMemberSeq) {
		this.arrInterviewMemberSeq = arrInterviewMemberSeq;
	}
	public String[] getArrInterviewMemberName() {
		return arrInterviewMemberName;
	}
	public void setArrInterviewMemberName(String[] arrInterviewMemberName) {
		this.arrInterviewMemberName = arrInterviewMemberName;
	}
	
	
	
	
}
