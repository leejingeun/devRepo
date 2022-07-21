package kr.co.sitglobal.ifx.vo;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AunuriSubjectVO extends AunuriBaseVO implements Serializable {
	private static final long serialVersionUID = -3834881654965610469L;
	
	private String subjectName     =""; //교과목명
	private String subjectDivCd    =""; //과목구분
	private String subjectType     =""; //교과구분
	private String passType        =""; //이수구분
	private String authType        =""; //인증구분
	private String hgss            =""; //학-강-실-설
	private String ncsType         =""; //NCS 구분
	private String lectureTime     =""; //강의시간
	private String jobName         =""; //직무명
	private String gubun           =""; //구분
	private String skillCode       =""; //능력단위코드
	private String skillName       =""; //능력단위명
	private String jobBaseSkill    =""; //직업기초능력
	private String equipAndTool    =""; //장비및도구
	private String leaningModule   =""; //학습모듈
	private String subjectSummary  =""; //교과개요
	private String eduObject       =""; //교육목표
	private String classWay_title  =""; //수업방법_제목
	private String classWay_check  =""; //수업방법_체크값
	private String evalWay_title   =""; //평가방법_제목
	private String evalWay_check   =""; //평가방법_체크값
	private String onlineLectureCode   =""; //온라인강의코드(01:플립러닝, 02:순수온라인, 03:블랜디드, 04:없음)
	private String onlineLectureName   =""; //온라인강의명
	private String subjectTraningType =""; //과목구분
	private String onlineType =""; //온라인과정구분(없음:NONE,일반:NORMAL,플립러닝:FLIPPED,블렌디드:BLENDED)
	
	private String year;
	private String term;
	private String subjectCode;
	private String subClass;
	private String stuCnt;
	
	public String getStuCnt() {
		return stuCnt;
	}
	public void setStuCnt(String stuCnt) {
		this.stuCnt = stuCnt;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getPassType() {
		return passType;
	}
	public void setPassType(String passType) {
		this.passType = passType;
	}

	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getHgss() {
		return hgss;
	}
	public void setHgss(String hgss) {
		this.hgss = hgss;
	}
	public String getNcsType() {
		return ncsType;
	}
	public void setNcsType(String ncsType) {
		this.ncsType = ncsType;
	}
	public String getLectureTime() {
		return lectureTime;
	}
	public void setLectureTime(String lectureTime) {
		this.lectureTime = lectureTime;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getSkillCode() {
		return skillCode;
	}
	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getJobBaseSkill() {
		return jobBaseSkill;
	}
	public void setJobBaseSkill(String jobBaseSkill) {
		this.jobBaseSkill = jobBaseSkill;
	}
	public String getEquipAndTool() {
		return equipAndTool;
	}
	public void setEquipAndTool(String equipAndTool) {
		this.equipAndTool = equipAndTool;
	}
	public String getLeaningModule() {
		return leaningModule;
	}
	public void setLeaningModule(String leaningModule) {
		this.leaningModule = leaningModule;
	}
	public String getSubjectSummary() {
		return subjectSummary;
	}
	public void setSubjectSummary(String subjectSummary) {
		this.subjectSummary = subjectSummary;
	}
	public String getEduObject() {
		return eduObject;
	}
	public void setEduObject(String eduObject) {
		this.eduObject = eduObject;
	}
	public String getClassWay_title() {
		return classWay_title;
	}
	public void setClassWay_title(String classWay_title) {
		this.classWay_title = classWay_title;
	}
	public String getClassWay_check() {
		return classWay_check;
	}
	public void setClassWay_check(String classWay_check) {
		this.classWay_check = classWay_check;
	}
	public String getEvalWay_title() {
		return evalWay_title;
	}
	public void setEvalWay_title(String evalWay_title) {
		this.evalWay_title = evalWay_title;
	}
	public String getEvalWay_check() {
		return evalWay_check;
	}
	public void setEvalWay_check(String evalWay_check) {
		this.evalWay_check = evalWay_check;
	}
	public String getOnlineLectureCode() {
		return onlineLectureCode;
	}
	public void setOnlineLectureCode(String onlineLectureCode) {
		this.onlineLectureCode = onlineLectureCode;
	}
	public String getOnlineLectureName() {
		return onlineLectureName;
	}
	public void setOnlineLectureName(String onlineLectureName) {
		this.onlineLectureName = onlineLectureName;
	}
	public String getSubjectDivCd() {
		return subjectDivCd;
	}
	public void setSubjectDivCd(String subjectDivCd) {
		this.subjectDivCd = subjectDivCd;
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
	
	
	@Override
	public String toString() {
		return "AunuriSubjectVO [subjectName=" + subjectName + ", subjectType="
				+ subjectType + ", passType=" + passType + ", authType="
				+ authType + ", hgss=" + hgss + ", ncsType=" + ncsType
				+ ", lectureTime=" + lectureTime + ", jobName=" + jobName
				+ ", gubun=" + gubun + ", skillCode=" + skillCode
				+ ", skillName=" + skillName + ", jobBaseSkill=" + jobBaseSkill
				+ ", equipAndTool=" + equipAndTool + ", leaningModule="
				+ leaningModule + ", subjectSummary=" + subjectSummary
				+ ", eduObject=" + eduObject + ", classWay_title="
				+ classWay_title + ", classWay_check=" + classWay_check
				+ ", evalWay_title=" + evalWay_title + ", evalWay_check="
				+ evalWay_check + ", onlineLectureCode=" + onlineLectureCode + 
				", onlineLectureName=" + onlineLectureName + ", subjectTraningType=" 
				+ subjectTraningType + ", onlineType=" + onlineType +"]";
	}
	
	
	
}
