package kr.co.sitglobal.oklms.lu.online.vo;

import java.io.Serializable;





import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OnlineTraningSchVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2499143270225852917L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	
	private String yyyy;
	private String term;
	private String subjectCode;
	private String subjectClass;
	private String classId; 
	private String weekId            ;
	private String weekCnt           ;
	private String subjSchId        ;
	private String subjStep          ;
	private String subjTitle         ;
	private String startDate         ;
	private String endDate           ;
	private String studyTime         ;
	private String contentsDir       ;
	private String contentsIdxFile  ;
	private String contentsRealFile ;
	private String linkContentYn ;
	private String cmsCourseContentId ;
	private String cmsLessonId ;
	private String cmsId;
	private String contentType ;
	private String url;
	private String deviceTypeCode;
	private String deviceTypeCodeName;
	private String atchFileId;
	
	private String contentName;
	private String weekStDate;
	private String weekEdDate;
	private String weekMin;
	private String ncsName;
	private String dayWeek;
	
	private String traningDate;
	private String traningStHour;
	private String traningStMin;
	private String traningEdHour;
	private String traningEdMin ; 
	private String weekMappingCnt;
	private int pageCount = 0;
	
	private String lessonId = "";
	private String jsonLessonData;
	
	private String stdLessonId = "";
	private String lessonProgress = "";
	private String weekProgressRate = "";
	
	private String reportId = "";
	private String discussId = "";
	private String traningSt = "";
	private String traningEd = "";
	private String timeHour = "";
	private String timeMin = "";
	private String rowNum = "";
	private String rowspanCnt = "";
	private String traNoteCnt = "";
	private String actNoteCnt = "";
	private String onRealRrogressRate = "";
	private String onRealRrogressTime = "";
	private String realProgressRate = "";
	private String teamProjectId = "";		
	private String lctrumCd = "";
	private String lctrumNm = "";
	private String nowWeekYn = "";
	private String nowWeekCnt = "";
	private String lessonCnt = "";
	private String pageType = "";
	private String btnText = "";
	private String weekProgressYn = "";
	private String ncsUnitName = "";
	private String ncsElemName = "";
	
	public String getNcsUnitName() {
		return ncsUnitName;
	}


	public void setNcsUnitName(String ncsUnitName) {
		this.ncsUnitName = ncsUnitName;
	}


	public String getNcsElemName() {
		return ncsElemName;
	}


	public void setNcsElemName(String ncsElemName) {
		this.ncsElemName = ncsElemName;
	}

	public String getWeekProgressYn() {
		return weekProgressYn;
	}


	public void setWeekProgressYn(String weekProgressYn) {
		this.weekProgressYn = weekProgressYn;
	}


	public String getBtnText() {
		return btnText;
	}


	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}


	public String getPageType() {
		return pageType;
	}


	public void setPageType(String pageType) {
		this.pageType = pageType;
	}


	public String getLessonCnt() {
		return lessonCnt;
	}


	public void setLessonCnt(String lessonCnt) {
		this.lessonCnt = lessonCnt;
	}


	public String getNowWeekCnt() {
		return nowWeekCnt;
	}


	public void setNowWeekCnt(String nowWeekCnt) {
		this.nowWeekCnt = nowWeekCnt;
	}


	public String getNowWeekYn() {
		return nowWeekYn;
	}


	public void setNowWeekYn(String nowWeekYn) {
		this.nowWeekYn = nowWeekYn;
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
	
	public String getTeamProjectId() {
		return teamProjectId;
	}


	public void setTeamProjectId(String teamProjectId) {
		this.teamProjectId = teamProjectId;
	}

	
	
	public String getRealProgressRate() {
		return realProgressRate;
	}


	public void setRealProgressRate(String realProgressRate) {
		this.realProgressRate = realProgressRate;
	}


	public String getOnRealRrogressRate() {
		return onRealRrogressRate;
	}


	public void setOnRealRrogressRate(String onRealRrogressRate) {
		this.onRealRrogressRate = onRealRrogressRate;
	}


	public String getOnRealRrogressTime() {
		return onRealRrogressTime;
	}


	public void setOnRealRrogressTime(String onRealRrogressTime) {
		this.onRealRrogressTime = onRealRrogressTime;
	}
	
	public String getTraNoteCnt() {
		return traNoteCnt;
	}


	public void setTraNoteCnt(String traNoteCnt) {
		this.traNoteCnt = traNoteCnt;
	}


	public String getActNoteCnt() {
		return actNoteCnt;
	}


	public void setActNoteCnt(String actNoteCnt) {
		this.actNoteCnt = actNoteCnt;
	}
	
	public String getRowNum() {
		return rowNum;
	}


	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}


	public String getRowspanCnt() {
		return rowspanCnt;
	}


	public void setRowspanCnt(String rowspanCnt) {
		this.rowspanCnt = rowspanCnt;
	}
	
	public String getTraningSt() {
		return traningSt;
	}


	public void setTraningSt(String traningSt) {
		this.traningSt = traningSt;
	}


	public String getTraningEd() {
		return traningEd;
	}


	public void setTraningEd(String traningEd) {
		this.traningEd = traningEd;
	}


	public String getTimeHour() {
		return timeHour;
	}


	public void setTimeHour(String timeHour) {
		this.timeHour = timeHour;
	}


	public String getTimeMin() {
		return timeMin;
	}


	public void setTimeMin(String timeMin) {
		this.timeMin = timeMin;
	}



	public String getDiscussId() {
		return discussId;
	}


	public void setDiscussId(String discussId) {
		this.discussId = discussId;
	}


	public String getReportId() {
		return reportId;
	}


	public void setReportId(String reportId) {
		this.reportId = reportId;
	}


	public String getWeekProgressRate() {
		return weekProgressRate;
	}


	public void setWeekProgressRate(String weekProgressRate) {
		this.weekProgressRate = weekProgressRate;
	}


	public String getStdLessonId() {
		return stdLessonId;
	}


	public void setStdLessonId(String stdLessonId) {
		this.stdLessonId = stdLessonId;
	}


	public String getLessonProgress() {
		return lessonProgress;
	}


	public void setLessonProgress(String lessonProgress) {
		this.lessonProgress = lessonProgress;
	}


	public String getLessonId() {
		return lessonId;
	}


	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}
	
	public String getJsonLessonData() {
		return jsonLessonData;
	}


	public void setJsonLessonData(String jsonLessonData) {
		this.jsonLessonData = jsonLessonData;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public String getWeekMappingCnt() {
		return weekMappingCnt;
	}


	public void setWeekMappingCnt(String weekMappingCnt) {
		this.weekMappingCnt = weekMappingCnt;
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
	
	public String getDayWeek() {
		return dayWeek;
	}


	public void setDayWeek(String dayWeek) {
		this.dayWeek = dayWeek;
	}


	public String getNcsName() {
		return ncsName;
	}


	public void setNcsName(String ncsName) {
		this.ncsName = ncsName;
	}


	public String getDeviceTypeCodeName() {
		return deviceTypeCodeName;
	}


	public void setDeviceTypeCodeName(String deviceTypeCodeName) {
		this.deviceTypeCodeName = deviceTypeCodeName;
	}
	
	public String getSubjectClass() {
		return subjectClass;
	}


	public void setSubjectClass(String subjectClass) {
		this.subjectClass = subjectClass;
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
	
	public String getClassId() {
		return classId;
	}



	public void setClassId(String classId) {
		this.classId = classId;
	}



	public String getLinkContentYn() {
		return linkContentYn;
	}



	public void setLinkContentYn(String linkContentYn) {
		this.linkContentYn = linkContentYn;
	}



	public String getCmsCourseContentId() {
		return cmsCourseContentId;
	}



	public void setCmsCourseContentId(String cmsCourseContentId) {
		this.cmsCourseContentId = cmsCourseContentId;
	}



	public String getCmsLessonId() {
		return cmsLessonId;
	}



	public void setCmsLessonId(String cmsLessonId) {
		this.cmsLessonId = cmsLessonId;
	}



	public String getCmsId() {
		return cmsId;
	}



	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}



	public String getContentType() {
		return contentType;
	}



	public void setContentType(String contentType) {
		this.contentType = contentType;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getDeviceTypeCode() {
		return deviceTypeCode;
	}



	public void setDeviceTypeCode(String deviceTypeCode) {
		this.deviceTypeCode = deviceTypeCode;
	}



	public String getAtchFileId() {
		return atchFileId;
	}



	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
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
		return classId;
	}
	
	
	public void setSubClass(String subClass) {
		this.classId = subClass;
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



	public String getSubjSchId() {
		return subjSchId;
	}



	public void setSubjSchId(String subjSchId) {
		this.subjSchId = subjSchId;
	}



	public String getSubjStep() {
		return subjStep;
	}



	public void setSubjStep(String subjStep) {
		this.subjStep = subjStep;
	}



	public String getSubjTitle() {
		return subjTitle;
	}



	public void setSubjTitle(String subjTitle) {
		this.subjTitle = subjTitle;
	}



	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getStudyTime() {
		return studyTime;
	}



	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}



	public String getContentsDir() {
		return contentsDir;
	}



	public void setContentsDir(String contentsDir) {
		this.contentsDir = contentsDir;
	}



	public String getContentsIdxFile() {
		return contentsIdxFile;
	}



	public void setContentsIdxFile(String contentsIdxFile) {
		this.contentsIdxFile = contentsIdxFile;
	}



	public String getContentsRealFile() {
		return contentsRealFile;
	}



	public void setContentsRealFile(String contentsRealFile) {
		this.contentsRealFile = contentsRealFile;
	}

	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	

}
