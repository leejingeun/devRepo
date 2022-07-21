package kr.co.sitglobal.oklms.lu.traning.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TraningScheduleVO extends BaseVO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5560833365667268428L;

	/* 훈련시간표-주차정보 */
	private String oldWeekId;   			//주 아이디
	private String newWeekId;   			//주 아이디
	private String weekId;   				//주 아이디
	private String yyyy; 					//학년도
	private String term; 					//학기
	private String subjectCode; 			//교과목코드
	private String sujbjectName;            //교과목명
	private String subClass; 				//분반
	private String weekCnt;	  		        //학습주차
	private String trainPlace;       		//훈련장소
	private String subjctTitle;      		//주요교과목제목 및 내용
	private String ipAddress; 				//IP주소
	private String changeReason;            //변경사유
	private String retunReason;     		//반려사유
	private String status;  				//상태(1:승인대기 2:승인 3:반려)
	private String changeStatus;  			//변경상태(1:승인대기 2:승인 3:반려)
	private String changeStatusName;
	private String resultStatus;            //상태(Y:저장성공, N:저장실패)
	private String atchFileId;  			//훈련시간표 일괄등록 파일아이디
	private String deleteYn; 				//삭제여부

	/* 훈련시간표-주차별시간 */
	private String timeId;  				//시간 아이디
	private String traningDate;  			//변경전 훈련일
	private String traningStHour; 			//변경전 훈련시작-시간
	private String traningStMin;  			//변경전 훈련시작-분
	private String traningEdHour; 			//변경전 훈련종료-시간
	private String traningEdMin; 			//변경전 훈련종료-분

	private String changeTraningDate;  		//변경후 훈련일
	private String changeTraningStHour; 	//변경후 훈련시작-시간
	private String changeTraningStMin; 		//변경후 훈련시작-분
	private String changeTraningEdHour; 	//변경후 훈련종료-시간
	private String changeTraningEdMin; 		//변경후 훈련종료-분

	private String leadTime; 				//변경전 소요시간
	private String leadTimeSum; 			//변경전 소요시간SUM
	private String changeLeadTime; 			//변경전 소요시간
	private String changeLeadTimeSum; 		//변경전 소요시간SUM

	/* 훈련시간표-주차별 NCS단위 */
	private String oldNcsUnitId;			//NCS 단위아이디
	private String newNcsUnitId;			//NCS 단위아이디
	private String ncsUnitId; 				//NCS 단위아이디
	private String ncsUnitName;             //NCS 단위명

	/* 훈련시간표-주차별 NCS요소 */
	private String ncsElemId;			 	//NCS 요소아이디
	private String ncsElemName;				//NCS 요소명

	/* 기타항목 */
	private String companyId;					//기업아이디
	private String companyName;                 //기업명
	private String traningProcessId;			//훈련과정아이디
	private String subjectName; 				//교과목명
	private String hrdTraningNo; 				//HRD-NET 훈련과정번호
	private String hrdTraningName; 				//HRD-NET 훈련과정명
	private String memSeq;
	private String memId;
	private String memName;
	private String memType;
	private String scheduleViewYn;
	private String orgFileName;
	private String dayOfWeek;
	private String dayOfWeekChange;
	private String traningProcessIdArr;         //훈련과정아이디 배열

	/* 검색항목 */
	private String searchYyyy; 					//학년도
	private String searchTerm; 					//학기
	private String searchSubjectCode; 			//교과목코드
	private String searchSubClass; 				//분반
	private String searchSubjectName; 			//교과목명
	private String searchCompanyName; 			//회사명
	private String searchTraningProcessName; 	//훈련과정명
	private String searchStartUpdateDate;       //변경신청시작일
	private String searchEndUpdateDate;         //변경신청종료일


	public String getWeekId() {
		return weekId;
	}
	public void setWeekId(String weekId) {
		this.weekId = weekId;
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
	public String getTrainPlace() {
		return trainPlace;
	}
	public void setTrainPlace(String trainPlace) {
		this.trainPlace = trainPlace;
	}
	public String getSubjctTitle() {
		return subjctTitle;
	}
	public void setSubjctTitle(String subjctTitle) {
		this.subjctTitle = subjctTitle;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
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
	public String getTimeId() {
		return timeId;
	}
	public void setTimeId(String timeId) {
		this.timeId = timeId;
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
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
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
	public String getTraningProcessId() {
		return traningProcessId;
	}
	public void setTraningProcessId(String traningProcessId) {
		this.traningProcessId = traningProcessId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getHrdTraningNo() {
		return hrdTraningNo;
	}
	public void setHrdTraningNo(String hrdTraningNo) {
		this.hrdTraningNo = hrdTraningNo;
	}
	public String getHrdTraningName() {
		return hrdTraningName;
	}
	public void setHrdTraningName(String hrdTraningName) {
		this.hrdTraningName = hrdTraningName;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
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
	public String getSearchSubjectName() {
		return searchSubjectName;
	}
	public void setSearchSubjectName(String searchSubjectName) {
		this.searchSubjectName = searchSubjectName;
	}
	public String getSujbjectName() {
		return sujbjectName;
	}
	public void setSujbjectName(String sujbjectName) {
		this.sujbjectName = sujbjectName;
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
	public String getMemType() {
		return memType;
	}
	public void setMemType(String memType) {
		this.memType = memType;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getLeadTimeSum() {
		return leadTimeSum;
	}
	public void setLeadTimeSum(String leadTimeSum) {
		this.leadTimeSum = leadTimeSum;
	}
	public String getScheduleViewYn() {
		return scheduleViewYn;
	}
	public void setScheduleViewYn(String scheduleViewYn) {
		this.scheduleViewYn = scheduleViewYn;
	}
	public String getOrgFileName() {
		return orgFileName;
	}
	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}
	public String getOldWeekId() {
		return oldWeekId;
	}
	public void setOldWeekId(String oldWeekId) {
		this.oldWeekId = oldWeekId;
	}
	public String getNewWeekId() {
		return newWeekId;
	}
	public void setNewWeekId(String newWeekId) {
		this.newWeekId = newWeekId;
	}
	public String getOldNcsUnitId() {
		return oldNcsUnitId;
	}
	public void setOldNcsUnitId(String oldNcsUnitId) {
		this.oldNcsUnitId = oldNcsUnitId;
	}
	public String getNewNcsUnitId() {
		return newNcsUnitId;
	}
	public void setNewNcsUnitId(String newNcsUnitId) {
		this.newNcsUnitId = newNcsUnitId;
	}
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public String getChangeTraningDate() {
		return changeTraningDate;
	}
	public void setChangeTraningDate(String changeTraningDate) {
		this.changeTraningDate = changeTraningDate;
	}
	public String getChangeTraningStHour() {
		return changeTraningStHour;
	}
	public void setChangeTraningStHour(String changeTraningStHour) {
		this.changeTraningStHour = changeTraningStHour;
	}
	public String getChangeTraningStMin() {
		return changeTraningStMin;
	}
	public void setChangeTraningStMin(String changeTraningStMin) {
		this.changeTraningStMin = changeTraningStMin;
	}
	public String getChangeTraningEdHour() {
		return changeTraningEdHour;
	}
	public void setChangeTraningEdHour(String changeTraningEdHour) {
		this.changeTraningEdHour = changeTraningEdHour;
	}
	public String getChangeTraningEdMin() {
		return changeTraningEdMin;
	}
	public void setChangeTraningEdMin(String changeTraningEdMin) {
		this.changeTraningEdMin = changeTraningEdMin;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public String getTraningProcessIdArr() {
		return traningProcessIdArr;
	}
	public void setTraningProcessIdArr(String traningProcessIdArr) {
		this.traningProcessIdArr = traningProcessIdArr;
	}
	public String getChangeStatus() {
		return changeStatus;
	}
	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}
	public String getSearchCompanyName() {
		return searchCompanyName;
	}
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	public String getSearchTraningProcessName() {
		return searchTraningProcessName;
	}
	public void setSearchTraningProcessName(String searchTraningProcessName) {
		this.searchTraningProcessName = searchTraningProcessName;
	}
	public String getSearchStartUpdateDate() {
		return searchStartUpdateDate;
	}
	public void setSearchStartUpdateDate(String searchStartUpdateDate) {
		this.searchStartUpdateDate = searchStartUpdateDate;
	}
	public String getSearchEndUpdateDate() {
		return searchEndUpdateDate;
	}
	public void setSearchEndUpdateDate(String searchEndUpdateDate) {
		this.searchEndUpdateDate = searchEndUpdateDate;
	}
	public String getChangeLeadTime() {
		return changeLeadTime;
	}
	public void setChangeLeadTime(String changeLeadTime) {
		this.changeLeadTime = changeLeadTime;
	}
	public String getChangeLeadTimeSum() {
		return changeLeadTimeSum;
	}
	public void setChangeLeadTimeSum(String changeLeadTimeSum) {
		this.changeLeadTimeSum = changeLeadTimeSum;
	}
	public String getDayOfWeekChange() {
		return dayOfWeekChange;
	}
	public void setDayOfWeekChange(String dayOfWeekChange) {
		this.dayOfWeekChange = dayOfWeekChange;
	}
	public String getChangeStatusName() {
		return changeStatusName;
	}
	public void setChangeStatusName(String changeStatusName) {
		this.changeStatusName = changeStatusName;
	}



	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
