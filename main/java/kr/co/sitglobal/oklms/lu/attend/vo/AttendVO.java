package kr.co.sitglobal.oklms.lu.attend.vo;

import java.io.Serializable;


import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AttendVO extends BaseVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2931526913927759069L;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private String progressAttendId           = "";    //진도일련번호
	private String lecId                = "";    //강의ID
    private String periodId             = "";    //기수ID
    private String classId              = "";    //반ID
	private String stdLessonId             = "";    //수강ID
	private String cmsLessonId = ""; 
	private String cmsLessonItemId = ""; 
    private String cmsLessonSubItem = ""; 
	private String linkContentYn = "";
	private String lecStep              = "";    //해당차시
    private String memSeq               = "";    //수강생
	private String timeProgressRatio       = "";    // 시간진도율
	private String timeProgress       = "";    // 시간진도율
    private String pageProgressRatio         = "";    // 페이지진도율
    
    private String finalCmsLessonId = "";
	private String finalCmsLessonItemId = "";
    private String finalCmsLessonSubItem = "";
    
    private String vDateTime = "";

	// 강의 유형
    private String lecType;
    
    /**
     * 수업참여시간 
     */
    private String progressTimeId       = "";    //수업참여시간ID
    private String startDate            = "";    //수강 시작시간
    private String endDate              = "";    //수강 종료시간
    
    /**
     *  수강, 맛보기, 미리보기 템플릿을 구하기 위한 파리미터로 사용
     */
    private String lecScheduleId        = "";    //차시ID
    private String courseId             = "";    //Course ID
    private String courseTitle          = "";    //Course ID
    private String templateId           = "0";   //default '0'
    private String previewDiv           = "";    //미리보기 구분(COURSE, LMS, LCMS)
    private String packageIdx           = "";    //LCMS package idx
    
    /**
     * 
     */
    private String schDiv               = "";
    private String divName              = "";    
    private String memName              = "";
    private String attendYn             = "";
    private String lecTitle             = "";
    private String checkValue           = "";
    private String lecName              = "";
    private String periodName           = "";
    private String className            = "";
    
    private String activityId           = "";
    private String scoId                = "";
    private String button               = "";
    private String viewTOC              = "false";
    private String memType              = "";	//멤버 타입 강사로그인시 필요
    
    //비스콤 페이지별 진도체크
    private String pageProgressAttendId = "";
    private String pageInfo = "";
	private String isPreview = "";
    private String finalConnPage = "";
	private String weekId = "";
    private String subjSchId = "";
    
    private String progressAttendTypeCode = "";
    private int progressSum = 0;
	private int progressCount = 0;
	private String progressYn = "";
	
	private String weekProgressRate = "";
	private String cmsLessonItemProgress = "";
	private String weekProgressYn = "";
	
	public String getWeekProgressYn() {
		return weekProgressYn;
	}
	public void setWeekProgressYn(String weekProgressYn) {
		this.weekProgressYn = weekProgressYn;
	}
	public String getWeekProgressRate() {
		return weekProgressRate;
	}
	public void setWeekProgressRate(String weekProgressRate) {
		this.weekProgressRate = weekProgressRate;
	}
	
	public String getCmsLessonItemProgress() {
		return cmsLessonItemProgress;
	}
	public void setCmsLessonItemProgress(String cmsLessonItemProgress) {
		this.cmsLessonItemProgress = cmsLessonItemProgress;
	}
	public String getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}
    
	public String getTimeProgress() {
		return timeProgress;
	}
	public void setTimeProgress(String timeProgress) {
		this.timeProgress = timeProgress;
	}
	
	public String getProgressYn() {
		return progressYn;
	}
	public void setProgressYn(String progressYn) {
		this.progressYn = progressYn;
	}
	public int getProgressSum() {
		return progressSum;
	}
	public void setProgressSum(int progressSum) {
		this.progressSum = progressSum;
	}
	public int getProgressCount() {
		return progressCount;
	}
	public void setProgressCount(int progressCount) {
		this.progressCount = progressCount;
	}
    public String getProgressAttendTypeCode() {
		return progressAttendTypeCode;
	}
	public void setProgressAttendTypeCode(String progressAttendTypeCode) {
		this.progressAttendTypeCode = progressAttendTypeCode;
	}
	public String getLinkContentYn() {
		return linkContentYn;
	}
	public void setLinkContentYn(String linkContentYn) {
		this.linkContentYn = linkContentYn;
	}
    
    public String getStdLessonId() {
		return stdLessonId;
	}
	public void setStdLessonId(String stdLessonId) {
		this.stdLessonId = stdLessonId;
	}
	
	public String getCmsLessonId() {
		return cmsLessonId;
	}
	public void setCmsLessonId(String cmsLessonId) {
		this.cmsLessonId = cmsLessonId;
	}
    
	public String getCmsLessonItemId() {
		return cmsLessonItemId;
	}
	public void setCmsLessonItemId(String cmsLessonItemId) {
		this.cmsLessonItemId = cmsLessonItemId;
	}
	
	public String getCmsLessonSubItem() {
		return cmsLessonSubItem;
	}
	public void setCmsLessonSubItem(String cmsLessonSubItem) {
		this.cmsLessonSubItem = cmsLessonSubItem;
	}
    
    public String getvDateTime() {
		return vDateTime;
	}
	public void setvDateTime(String vDateTime) {
		this.vDateTime = vDateTime;
	}
    
    public String getProgressAttendId() {
		return progressAttendId;
	}
	public void setProgressAttendId(String progressAttendId) {
		this.progressAttendId = progressAttendId;
	}
	
    public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
	}
	public String getSubjSchId() {
		return subjSchId;
	}
	public void setSubjSchId(String subjSchId) {
		this.subjSchId = subjSchId;
	}
    
	public String getFinalCmsLessonId() {
		return finalCmsLessonId;
	}
	public void setFinalCmsLessonId(String finalCmsLessonId) {
		this.finalCmsLessonId = finalCmsLessonId;
	}
	public String getFinalCmsLessonItemId() {
		return finalCmsLessonItemId;
	}
	public void setFinalCmsLessonItemId(String finalCmsLessonItemId) {
		this.finalCmsLessonItemId = finalCmsLessonItemId;
	}
	public String getFinalCmsLessonSubItem() {
		return finalCmsLessonSubItem;
	}
	public void setFinalCmsLessonSubItem(String finalCmsLessonSubItem) {
		this.finalCmsLessonSubItem = finalCmsLessonSubItem;
	}
    
	public String getLecId() {
		return lecId;
	}
	public void setLecId(String lecId) {
		this.lecId = lecId;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getLecStep() {
		return lecStep;
	}
	public void setLecStep(String lecStep) {
		this.lecStep = lecStep;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	
	public String getTimeProgressRatio() {
		return timeProgressRatio;
	}
	public void setTimeProgressRatio(String timeProgressRatio) {
		this.timeProgressRatio = timeProgressRatio;
	}
	public String getPageProgressRatio() {
		return pageProgressRatio;
	}
	public void setPageProgressRatio(String pageProgressRatio) {
		this.pageProgressRatio = pageProgressRatio;
	}
	
	public String getLecType() {
		return lecType;
	}
	public void setLecType(String lecType) {
		this.lecType = lecType;
	}
	public String getProgressTimeId() {
		return progressTimeId;
	}
	public void setProgressTimeId(String progressTimeId) {
		this.progressTimeId = progressTimeId;
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
	public String getLecScheduleId() {
		return lecScheduleId;
	}
	public void setLecScheduleId(String lecScheduleId) {
		this.lecScheduleId = lecScheduleId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getPreviewDiv() {
		return previewDiv;
	}
	public void setPreviewDiv(String previewDiv) {
		this.previewDiv = previewDiv;
	}
	public String getPackageIdx() {
		return packageIdx;
	}
	public void setPackageIdx(String packageIdx) {
		this.packageIdx = packageIdx;
	}
	public String getSchDiv() {
		return schDiv;
	}
	public void setSchDiv(String schDiv) {
		this.schDiv = schDiv;
	}
	public String getDivName() {
		return divName;
	}
	public void setDivName(String divName) {
		this.divName = divName;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getAttendYn() {
		return attendYn;
	}
	public void setAttendYn(String attendYn) {
		this.attendYn = attendYn;
	}
	public String getLecTitle() {
		return lecTitle;
	}
	public void setLecTitle(String lecTitle) {
		this.lecTitle = lecTitle;
	}
	public String getCheckValue() {
		return checkValue;
	}
	public void setCheckValue(String checkValue) {
		this.checkValue = checkValue;
	}
	public String getLecName() {
		return lecName;
	}
	public void setLecName(String lecName) {
		this.lecName = lecName;
	}
	public String getPeriodName() {
		return periodName;
	}
	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getScoId() {
		return scoId;
	}
	public void setScoId(String scoId) {
		this.scoId = scoId;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public String getViewTOC() {
		return viewTOC;
	}
	public void setViewTOC(String viewTOC) {
		this.viewTOC = viewTOC;
	}
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	public String getPageProgressAttendId() {
		return pageProgressAttendId;
	}
	public void setPageProgressAttendId(String pageProgressAttendId) {
		this.pageProgressAttendId = pageProgressAttendId;
	}
	public String getIsPreview() {
		return isPreview;
	}
	public void setIsPreview(String isPreview) {
		this.isPreview = isPreview;
	}
	public String getFinalConnPage() {
		return finalConnPage;
	}
	public void setFinalConnPage(String finalConnPage) {
		this.finalConnPage = finalConnPage;
	}

}
