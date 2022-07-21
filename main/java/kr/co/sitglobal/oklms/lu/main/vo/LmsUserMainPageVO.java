
/*******************************************************************************
 * COPYRIGHT(C) 2016 WIZI LEARN ALL RIGHTS RESERVED.
 * This software is the proprietary information of WIZI LEARN.
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 01. 20.        First Draft.
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.main.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

 /**
 * VO 클래스에 대한 내용을 작성한다.
 * @author 이진근
 * @since 2017. 01. 20.
 */
public class LmsUserMainPageVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5283413661630016847L;

	private String activityNoteCnt;                                //학습활동서
	private String onlineLectureCnt;                               //온라인강의
	private String traningNoteWriteCnt;                            //훈련일지작성
	private String activityNoteAgreeCnt;                           //학습활동서승인
	private String surveyCnt;                                      //설문조사
	private String traningNoteAgreeCnt;                            //훈련일지승인
	private String interviewNoteCnt;                               //면담일지
	private String weekTraningNoteUnsubmissionCompanyCnt;          //주간훈련일지 미제출기업
	private String activityNoteUnsubmissionCnt;                    //학습활동서 미제출
	private String interviewNoteUnsubmissionCompanyCnt;            //면담일지 미제출기업
	private String traningTimeModifyApplicationCnt;                //훈련시간표 변경신청
	private String persionModifyApplicationCnt;                    //담당자 변경신청
	private String registerContentsCnt;                            //등록할컨텐츠
	private String taskCnt;                                        //과제
	private String teamProjectCnt;                                 //팀프로젝트
	private String workCertCnt;                                    //재직증빙서류
	private String questionAndAnswerCnt;                           //Q&A
	private String noticeCnt;                                      //공지사항
	private String startLectureTime; 							   //시작 시간
	private String endLectureTime; 								   //종료 시간
	private String subjectName; 								   //교과목명
	private String subClass; 									   //분반
	private String onlineLecture; 								   //온라인강의
	private String lecturePlace; 								   //장소
	private String memberPrtName; 								   //담당교수
	private String memberCotName; 								   //기업현장교사
	private String weekCnt; 									   //주차
	private String cutClassCnt; 								   //결석
	private String onlineNoRegister; 							   //온라인미수강
	private String noteNoWrite; 								   //활동서미작성
	private String yyyy; 										   //학년도
	private String term; 										   //학기
	private String professor; 									   //담당교수
	private String schoolYear; 									   //학년
	private String companyCnt; 									   //기업체수
	private String allStudentCnt; 								   //전체학생수
	private String dropoutCnt; 									   //중도탈락자
	private String companyName; 								   //기업명
	private String traningTime; 								   //훈련시간
	private String traningDivision; 							   //훈련구분
	
	private int myPageTodayCnt1 = 0;
	private int myPageTodayCnt2 = 0;
	private int myPageTodayCnt3 = 0;
	private int myPageTodayCnt4 = 0;
	private int myPageTodayCnt5 = 0;
	private int myPageTodayCnt6 = 0;
	private int myPageTotalCnt1 = 0;
	private int myPageTotalCnt2 = 0;
	private int myPageTotalCnt3 = 0;
	private int myPageTotalCnt4 = 0;
	private int myPageTotalCnt5 = 0;
	
	private String subjectCode = "";
	private String subjectClass = "";
	private String weekId = "";
	private String traningDate = "";
	private String traningStHour = "";
	private String traningStMin = "";
	private String traningEdHour = "";
	private String traningEdMin = "";
	private String ncsElemName = "";
	private String totWeekCnt = "";
	private String tutName  = "";

	private String maxWeekCnt;  //마지막주차
	private String traningStDate; //기간 시작일
	private String traningEndDate;// 기간 종료일
	private String ncsUnitName; // 능력단위
	private String traningHour; // 주간훈련시간
	private String addYn; // 보강여부
	private String hrdTraningName; // 훈련명
	private String subjectTraningType;
	private String departmentName;
	private String grade;
	private String pointUseYn;
	private String point;
	private String subjectType;
	private String onlineType;
	private String firstStudyYn;
	private String gradeRatio;
	private String departmentNo;
	
	private String lctrumNm;
	private String lctrumCd;
	private String subjectTraningTypeName;
	private String subjectTypeName;
	private String onlineTypeName;
	private String companyId;
	private String traningProcessId;
	private String weekProgressRate;
	private String contentName;
	private String weekMappingCnt;
	private String lessonId;
	private String attendProgress; 
	private String cutProgress;
	private String progressAttendTypeCode;
	private String tutNames; 						
	
	private String totalTime;
	private String attend;
	private String lateness;
	private String onTotalTime;
	private String onAttend;
	private String onLateness;
	private String realProgressRate;
	private String totalCnt;
	private String onTotalCnt;
	private String stuCnt;
	private String outCnt;
	
	public String getStuCnt() {
		return stuCnt;
	}

	public void setStuCnt(String stuCnt) {
		this.stuCnt = stuCnt;
	}

	public String getOutCnt() {
		return outCnt;
	}

	public void setOutCnt(String outCnt) {
		this.outCnt = outCnt;
	}

	public String getOnTotalCnt() {
		return onTotalCnt;
	}

	public void setOnTotalCnt(String onTotalCnt) {
		this.onTotalCnt = onTotalCnt;
	}

	public String getLateness() {
		return lateness;
	}

	public void setLateness(String lateness) {
		this.lateness = lateness;
	}

	public String getOnLateness() {
		return onLateness;
	}

	public void setOnLateness(String onLateness) {
		this.onLateness = onLateness;
	}

	public String getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(String totalCnt) {
		this.totalCnt = totalCnt;
	}

	public String getHrdTraningName() {
		return hrdTraningName;
	}

	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}

	public String getRealProgressRate() {
		return realProgressRate;
	}

	public void setRealProgressRate(String realProgressRate) {
		this.realProgressRate = realProgressRate;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getAttend() {
		return attend;
	}

	public void setAttend(String attend) {
		this.attend = attend;
	}

	public String getOnTotalTime() {
		return onTotalTime;
	}

	public void setOnTotalTime(String onTotalTime) {
		this.onTotalTime = onTotalTime;
	}

	public String getOnAttend() {
		return onAttend;
	}

	public void setOnAttend(String onAttend) {
		this.onAttend = onAttend;
	}

	public String getTutNames() {
		return tutNames;
	}

	public void setTutNames(String tutNames) {
		this.tutNames = tutNames;
	}

	public String getAttendProgress() {
		return attendProgress;
	}

	public void setAttendProgress(String attendProgress) {
		this.attendProgress = attendProgress;
	}

	public String getCutProgress() {
		return cutProgress;
	}

	public void setCutProgress(String cutProgress) {
		this.cutProgress = cutProgress;
	}

	public String getProgressAttendTypeCode() {
		return progressAttendTypeCode;
	}

	public void setProgressAttendTypeCode(String progressAttendTypeCode) {
		this.progressAttendTypeCode = progressAttendTypeCode;
	}

	public String getLessonId() {
		return lessonId;
	}

	public void setLessonId(String lessonId) {
		this.lessonId = lessonId;
	}

	public String getSubjectTraningTypeName() {
		return subjectTraningTypeName;
	}

	public void setSubjectTraningTypeName(String subjectTraningTypeName) {
		this.subjectTraningTypeName = subjectTraningTypeName;
	}

	public String getSubjectTypeName() {
		return subjectTypeName;
	}

	public void setSubjectTypeName(String subjectTypeName) {
		this.subjectTypeName = subjectTypeName;
	}

	public String getOnlineTypeName() {
		return onlineTypeName;
	}

	public void setOnlineTypeName(String onlineTypeName) {
		this.onlineTypeName = onlineTypeName;
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

	public String getWeekProgressRate() {
		return weekProgressRate;
	}

	public void setWeekProgressRate(String weekProgressRate) {
		this.weekProgressRate = weekProgressRate;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getWeekMappingCnt() {
		return weekMappingCnt;
	}

	public void setWeekMappingCnt(String weekMappingCnt) {
		this.weekMappingCnt = weekMappingCnt;
	}

	public String getLctrumNm() {
		return lctrumNm;
	}

	public void setLctrumNm(String lctrumNm) {
		this.lctrumNm = lctrumNm;
	}

	public String getLctrumCd() {
		return lctrumCd;
	}

	public void setLctrumCd(String lctrumCd) {
		this.lctrumCd = lctrumCd;
	}

	public String getMaxWeekCnt() {
		return maxWeekCnt;
	}

	public void setMaxWeekCnt(String maxWeekCnt) {
		this.maxWeekCnt = maxWeekCnt;
	}

	public String getTraningStDate() {
		return traningStDate;
	}

	public void setTraningStDate(String traningStDate) {
		this.traningStDate = traningStDate;
	}

	public String getTraningEndDate() {
		return traningEndDate;
	}

	public void setTraningEndDate(String traningEndDate) {
		this.traningEndDate = traningEndDate;
	}

	public String getNcsUnitName() {
		return ncsUnitName;
	}

	public void setNcsUnitName(String ncsUnitName) {
		this.ncsUnitName = ncsUnitName;
	}

	public String getTraningHour() {
		return traningHour;
	}

	public void setTraningHour(String traningHour) {
		this.traningHour = traningHour;
	}

	public String getAddYn() {
		return addYn;
	}

	public void setAddYn(String addYn) {
		this.addYn = addYn;
	}

	public String getSubjectTraningType() {
		return subjectTraningType;
	}

	public void setSubjectTraningType(String subjectTraningType) {
		this.subjectTraningType = subjectTraningType;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPointUseYn() {
		return pointUseYn;
	}

	public void setPointUseYn(String pointUseYn) {
		this.pointUseYn = pointUseYn;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}

	public String getOnlineType() {
		return onlineType;
	}

	public void setOnlineType(String onlineType) {
		this.onlineType = onlineType;
	}

	public String getFirstStudyYn() {
		return firstStudyYn;
	}

	public void setFirstStudyYn(String firstStudyYn) {
		this.firstStudyYn = firstStudyYn;
	}

	public String getGradeRatio() {
		return gradeRatio;
	}

	public void setGradeRatio(String gradeRatio) {
		this.gradeRatio = gradeRatio;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getActivityNoteCnt() {
		return activityNoteCnt;
	}

	public void setActivityNoteCnt(String activityNoteCnt) {
		this.activityNoteCnt = activityNoteCnt;
	}

	public String getOnlineLectureCnt() {
		return onlineLectureCnt;
	}

	public void setOnlineLectureCnt(String onlineLectureCnt) {
		this.onlineLectureCnt = onlineLectureCnt;
	}

	public String getTraningNoteWriteCnt() {
		return traningNoteWriteCnt;
	}

	public void setTraningNoteWriteCnt(String traningNoteWriteCnt) {
		this.traningNoteWriteCnt = traningNoteWriteCnt;
	}

	public String getActivityNoteAgreeCnt() {
		return activityNoteAgreeCnt;
	}

	public void setActivityNoteAgreeCnt(String activityNoteAgreeCnt) {
		this.activityNoteAgreeCnt = activityNoteAgreeCnt;
	}

	public String getSurveyCnt() {
		return surveyCnt;
	}

	public void setSurveyCnt(String surveyCnt) {
		this.surveyCnt = surveyCnt;
	}

	public String getTraningNoteAgreeCnt() {
		return traningNoteAgreeCnt;
	}

	public void setTraningNoteAgreeCnt(String traningNoteAgreeCnt) {
		this.traningNoteAgreeCnt = traningNoteAgreeCnt;
	}

	public String getInterviewNoteCnt() {
		return interviewNoteCnt;
	}

	public void setInterviewNoteCnt(String interviewNoteCnt) {
		this.interviewNoteCnt = interviewNoteCnt;
	}

	public String getWeekTraningNoteUnsubmissionCompanyCnt() {
		return weekTraningNoteUnsubmissionCompanyCnt;
	}

	public void setWeekTraningNoteUnsubmissionCompanyCnt(
			String weekTraningNoteUnsubmissionCompanyCnt) {
		this.weekTraningNoteUnsubmissionCompanyCnt = weekTraningNoteUnsubmissionCompanyCnt;
	}

	public String getActivityNoteUnsubmissionCnt() {
		return activityNoteUnsubmissionCnt;
	}


	public void setActivityNoteUnsubmissionCnt(String activityNoteUnsubmissionCnt) {
		this.activityNoteUnsubmissionCnt = activityNoteUnsubmissionCnt;
	}

	public String getInterviewNoteUnsubmissionCompanyCnt() {
		return interviewNoteUnsubmissionCompanyCnt;
	}

	public void setInterviewNoteUnsubmissionCompanyCnt(
			String interviewNoteUnsubmissionCompanyCnt) {
		this.interviewNoteUnsubmissionCompanyCnt = interviewNoteUnsubmissionCompanyCnt;
	}

	public String getTraningTimeModifyApplicationCnt() {
		return traningTimeModifyApplicationCnt;
	}

	public void setTraningTimeModifyApplicationCnt(
			String traningTimeModifyApplicationCnt) {
		this.traningTimeModifyApplicationCnt = traningTimeModifyApplicationCnt;
	}

	public String getPersionModifyApplicationCnt() {
		return persionModifyApplicationCnt;
	}

	public void setPersionModifyApplicationCnt(String persionModifyApplicationCnt) {
		this.persionModifyApplicationCnt = persionModifyApplicationCnt;
	}

	public String getRegisterContentsCnt() {
		return registerContentsCnt;
	}

	public void setRegisterContentsCnt(String registerContentsCnt) {
		this.registerContentsCnt = registerContentsCnt;
	}

	public String getTaskCnt() {
		return taskCnt;
	}

	public void setTaskCnt(String taskCnt) {
		this.taskCnt = taskCnt;
	}

	public String getTeamProjectCnt() {
		return teamProjectCnt;
	}

	public void setTeamProjectCnt(String teamProjectCnt) {
		this.teamProjectCnt = teamProjectCnt;
	}

	public String getWorkCertCnt() {
		return workCertCnt;
	}

	public void setWorkCertCnt(String workCertCnt) {
		this.workCertCnt = workCertCnt;
	}

	public String getQuestionAndAnswerCnt() {
		return questionAndAnswerCnt;
	}

	public void setQuestionAndAnswerCnt(String questionAndAnswerCnt) {
		this.questionAndAnswerCnt = questionAndAnswerCnt;
	}

	public String getNoticeCnt() {
		return noticeCnt;
	}

	public void setNoticeCnt(String noticeCnt) {
		this.noticeCnt = noticeCnt;
	}

	public String getStartLectureTime() {
		return startLectureTime;
	}

	public void setStartLectureTime(String startLectureTime) {
		this.startLectureTime = startLectureTime;
	}

	public String getEndLectureTime() {
		return endLectureTime;
	}

	public void setEndLectureTime(String endLectureTime) {
		this.endLectureTime = endLectureTime;
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

	public String getOnlineLecture() {
		return onlineLecture;
	}

	public void setOnlineLecture(String onlineLecture) {
		this.onlineLecture = onlineLecture;
	}

	public String getLecturePlace() {
		return lecturePlace;
	}

	public void setLecturePlace(String lecturePlace) {
		this.lecturePlace = lecturePlace;
	}

	public String getMemberPrtName() {
		return memberPrtName;
	}

	public void setMemberPrtName(String memberPrtName) {
		this.memberPrtName = memberPrtName;
	}

	public String getMemberCotName() {
		return memberCotName;
	}

	public void setMemberCotName(String memberCotName) {
		this.memberCotName = memberCotName;
	}

	public String getWeekCnt() {
		return weekCnt;
	}

	public void setWeekCnt(String weekCnt) {
		this.weekCnt = weekCnt;
	}

	public String getCutClassCnt() {
		return cutClassCnt;
	}

	public void setCutClassCnt(String cutClassCnt) {
		this.cutClassCnt = cutClassCnt;
	}

	public String getOnlineNoRegister() {
		return onlineNoRegister;
	}

	public void setOnlineNoRegister(String onlineNoRegister) {
		this.onlineNoRegister = onlineNoRegister;
	}

	public String getNoteNoWrite() {
		return noteNoWrite;
	}

	public void setNoteNoWrite(String noteNoWrite) {
		this.noteNoWrite = noteNoWrite;
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getCompanyCnt() {
		return companyCnt;
	}

	public void setCompanyCnt(String companyCnt) {
		this.companyCnt = companyCnt;
	}

	public String getAllStudentCnt() {
		return allStudentCnt;
	}

	public void setAllStudentCnt(String allStudentCnt) {
		this.allStudentCnt = allStudentCnt;
	}

	public String getDropoutCnt() {
		return dropoutCnt;
	}

	public void setDropoutCnt(String dropoutCnt) {
		this.dropoutCnt = dropoutCnt;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTraningTime() {
		return traningTime;
	}

	public void setTraningTime(String traningTime) {
		this.traningTime = traningTime;
	}

	public String getTraningDivision() {
		return traningDivision;
	}

	public void setTraningDivision(String traningDivision) {
		this.traningDivision = traningDivision;
	}

	public int getMyPageTodayCnt1() {
		return myPageTodayCnt1;
	}

	public void setMyPageTodayCnt1(int myPageTodayCnt1) {
		this.myPageTodayCnt1 = myPageTodayCnt1;
	}

	public int getMyPageTodayCnt2() {
		return myPageTodayCnt2;
	}

	public void setMyPageTodayCnt2(int myPageTodayCnt2) {
		this.myPageTodayCnt2 = myPageTodayCnt2;
	}

	public int getMyPageTodayCnt3() {
		return myPageTodayCnt3;
	}

	public void setMyPageTodayCnt3(int myPageTodayCnt3) {
		this.myPageTodayCnt3 = myPageTodayCnt3;
	}

	public int getMyPageTodayCnt4() {
		return myPageTodayCnt4;
	}

	public void setMyPageTodayCnt4(int myPageTodayCnt4) {
		this.myPageTodayCnt4 = myPageTodayCnt4;
	}

	public int getMyPageTodayCnt5() {
		return myPageTodayCnt5;
	}

	public void setMyPageTodayCnt5(int myPageTodayCnt5) {
		this.myPageTodayCnt5 = myPageTodayCnt5;
	}
	
	public int getMyPageTodayCnt6() {
		return myPageTodayCnt6;
	}

	public void setMyPageTodayCnt6(int myPageTodayCnt6) {
		this.myPageTodayCnt6 = myPageTodayCnt6;
	}

	public int getMyPageTotalCnt1() {
		return myPageTotalCnt1;
	}

	public void setMyPageTotalCnt1(int myPageTotalCnt1) {
		this.myPageTotalCnt1 = myPageTotalCnt1;
	}

	public int getMyPageTotalCnt2() {
		return myPageTotalCnt2;
	}

	public void setMyPageTotalCnt2(int myPageTotalCnt2) {
		this.myPageTotalCnt2 = myPageTotalCnt2;
	}

	public int getMyPageTotalCnt3() {
		return myPageTotalCnt3;
	}

	public void setMyPageTotalCnt3(int myPageTotalCnt3) {
		this.myPageTotalCnt3 = myPageTotalCnt3;
	}

	public int getMyPageTotalCnt4() {
		return myPageTotalCnt4;
	}

	public void setMyPageTotalCnt4(int myPageTotalCnt4) {
		this.myPageTotalCnt4 = myPageTotalCnt4;
	}

	public int getMyPageTotalCnt5() {
		return myPageTotalCnt5;
	}

	public void setMyPageTotalCnt5(int myPageTotalCnt5) {
		this.myPageTotalCnt5 = myPageTotalCnt5;
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

	public String getNcsElemName() {
		return ncsElemName;
	}

	public void setNcsElemName(String ncsElemName) {
		this.ncsElemName = ncsElemName;
	}

	public String getTotWeekCnt() {
		return totWeekCnt;
	}

	public void setTotWeekCnt(String totWeekCnt) {
		this.totWeekCnt = totWeekCnt;
	}

	public String getTutName() {
		return tutName;
	}

	public void setTutName(String tutName) {
		this.tutName = tutName;
	}

	public String toString() {
        final String NL  = "\n";
        final String TAB = "    ";

        String retValue = "";

        retValue = "Lu > Main > Vo > LmsUserMainPageVO ( "
            + super.toString() + NL
            + TAB + "activityNoteCnt = " + this.activityNoteCnt + NL
            + " )";

        return retValue;
    }
}
