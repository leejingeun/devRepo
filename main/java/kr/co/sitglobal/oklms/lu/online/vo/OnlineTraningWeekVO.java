package kr.co.sitglobal.oklms.lu.online.vo;

import java.io.Serializable;







import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OnlineTraningWeekVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3303477995547655087L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 * 
	 */
	
	private String yyyy                         ;	// 학년도                                                                   
	private String term                         ; 	// 학기                                                                     
	private String subjectCode                  ;   // 교과목코드                                                               
	private String subjectClass                 ;   // 분반                 
	
	private String weekId;
	private String weekCnt      ;
	private String atchFileId  ;
	private String contentName;
	private String weekStDate;
	private String weekEdDate;
	private String weekMin;
	private String weekSchDelim;
	private String weekStudyType;
	
	private String [] weekIds;
	private String [] weekCnts      ;
	private String [] atchFileIds  ;
	private String [] contentNames;
	private String [] weekStDates;
	private String [] weekEdDates;
	private String [] weekMins;
	
	private String [] weekSchVals;
	private String [] weekSchStDates;
	private String [] weekSchEdDates;
	private String [] weekSchMins;
	
	private String [] subjTitles;
	private String [] linkContentTypes;
	private String [] cmsCourseContentIds;
	private String [] cmsLessonIds;
	private String [] cmsIds;
	private String [] contentTypes;
	private String [] contentsDirs;
	private String [] contentsIdxFiles;
	private String [] contentsRealFiles;
	private String [] urls;
	private String [] deviceTypeCodes;
	private String [] jsonLessonDatas;
	
	
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


	public String getWeekId() {
		return weekId;
	}


	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}


	public String getWeekCnt() {
		return weekCnt;
	}


	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}


	public String getAtchFileId() {
		return atchFileId;
	}


	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}


	public String getContentName() {
		return contentName;
	}


	public void setContentName(String contentName) {
		this.contentName = contentName;
	}


	public String getWeekStDate() {
		return weekStDate;
	}


	public void setWeekStDate(String weekStDate) {
		this.weekStDate = weekStDate;
	}


	public String getWeekEdDate() {
		return weekEdDate;
	}


	public void setWeekEdDate(String weekEdDate) {
		this.weekEdDate = weekEdDate;
	}


	public String getWeekMin() {
		return weekMin;
	}


	public void setWeekMin(String weekMin) {
		this.weekMin = weekMin;
	}


	public String getWeekSchDelim() {
		return weekSchDelim;
	}


	public void setWeekSchDelim(String weekSchDelim) {
		this.weekSchDelim = weekSchDelim;
	}


	public String getWeekStudyType() {
		return weekStudyType;
	}


	public void setWeekStudyType(String weekStudyType) {
		this.weekStudyType = weekStudyType;
	}


	public String[] getWeekIds() {
		return weekIds;
	}


	public void setWeekIds(String[] weekIds) {
		this.weekIds = weekIds;
	}


	public String[] getWeekCnts() {
		return weekCnts;
	}


	public void setWeekCnts(String[] weekCnts) {
		this.weekCnts = weekCnts;
	}


	public String[] getAtchFileIds() {
		return atchFileIds;
	}


	public void setAtchFileIds(String[] atchFileIds) {
		this.atchFileIds = atchFileIds;
	}


	public String[] getContentNames() {
		return contentNames;
	}


	public void setContentNames(String[] contentNames) {
		this.contentNames = contentNames;
	}


	public String[] getWeekStDates() {
		return weekStDates;
	}


	public void setWeekStDates(String[] weekStDates) {
		this.weekStDates = weekStDates;
	}


	public String[] getWeekEdDates() {
		return weekEdDates;
	}


	public void setWeekEdDates(String[] weekEdDates) {
		this.weekEdDates = weekEdDates;
	}


	public String[] getWeekMins() {
		return weekMins;
	}


	public void setWeekMins(String[] weekMins) {
		this.weekMins = weekMins;
	}


	public String[] getWeekSchVals() {
		return weekSchVals;
	}


	public void setWeekSchVals(String[] weekSchVals) {
		this.weekSchVals = weekSchVals;
	}


	public String[] getWeekSchStDates() {
		return weekSchStDates;
	}


	public void setWeekSchStDates(String[] weekSchStDates) {
		this.weekSchStDates = weekSchStDates;
	}


	public String[] getWeekSchEdDates() {
		return weekSchEdDates;
	}


	public void setWeekSchEdDates(String[] weekSchEdDates) {
		this.weekSchEdDates = weekSchEdDates;
	}


	public String[] getWeekSchMins() {
		return weekSchMins;
	}


	public void setWeekSchMins(String[] weekSchMins) {
		this.weekSchMins = weekSchMins;
	}


	public String[] getSubjTitles() {
		return subjTitles;
	}


	public void setSubjTitles(String[] subjTitles) {
		this.subjTitles = subjTitles;
	}


	public String[] getLinkContentTypes() {
		return linkContentTypes;
	}


	public void setLinkContentTypes(String[] linkContentTypes) {
		this.linkContentTypes = linkContentTypes;
	}


	public String[] getCmsCourseContentIds() {
		return cmsCourseContentIds;
	}


	public void setCmsCourseContentIds(String[] cmsCourseContentIds) {
		this.cmsCourseContentIds = cmsCourseContentIds;
	}


	public String[] getCmsLessonIds() {
		return cmsLessonIds;
	}


	public void setCmsLessonIds(String[] cmsLessonIds) {
		this.cmsLessonIds = cmsLessonIds;
	}


	public String[] getCmsIds() {
		return cmsIds;
	}


	public void setCmsIds(String[] cmsIds) {
		this.cmsIds = cmsIds;
	}


	public String[] getContentTypes() {
		return contentTypes;
	}


	public void setContentTypes(String[] contentTypes) {
		this.contentTypes = contentTypes;
	}


	public String[] getContentsDirs() {
		return contentsDirs;
	}


	public void setContentsDirs(String[] contentsDirs) {
		this.contentsDirs = contentsDirs;
	}


	public String[] getContentsIdxFiles() {
		return contentsIdxFiles;
	}


	public void setContentsIdxFiles(String[] contentsIdxFiles) {
		this.contentsIdxFiles = contentsIdxFiles;
	}


	public String[] getContentsRealFiles() {
		return contentsRealFiles;
	}


	public void setContentsRealFiles(String[] contentsRealFiles) {
		this.contentsRealFiles = contentsRealFiles;
	}


	public String[] getUrls() {
		return urls;
	}


	public void setUrls(String[] urls) {
		this.urls = urls;
	}


	public String[] getDeviceTypeCodes() {
		return deviceTypeCodes;
	}


	public void setDeviceTypeCodes(String[] deviceTypeCodes) {
		this.deviceTypeCodes = deviceTypeCodes;
	}


	public String[] getJsonLessonDatas() {
		return jsonLessonDatas;
	}


	public void setJsonLessonDatas(String[] jsonLessonDatas) {
		this.jsonLessonDatas = jsonLessonDatas;
	}
	
	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	

}
