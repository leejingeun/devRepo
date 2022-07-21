package kr.co.sitglobal.oklms.lu.workcert.vo;

import java.io.Serializable;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;
import kr.co.sitglobal.oklms.commbiz.util.BizUtil;

/**
* 재직증빙서류제출 VO 클래스에 대한 내용을 작성한다.
* @author 이창현
* @since 2016. 12. 27.
*/
public class WorkCertVO extends BaseVO implements Serializable{

	private static final long serialVersionUID = 107778091072449567L;

	private String workProofId;						//팝업id
	private String yyyy;									//학년도
	private String term;									//학기
	private String memId;								//학생아이디
	private String memName;						//학생명
	private String sendYn;								//제출여부(미제출/제출완료)
	private String atchFileId;							//재직증빙서류(원천징수영수증,4대보험, 재직증명서) 파일 아이디

	private String atchFileIdInc;
	private String atchFileIdRec;
	private String atchFileIdDoc;
	
	
	private String withholdingFileId;				//원천징수영수증 파일 아이디 (사용안함.)
	private String insureFileId;						//4대보험확인서 파일 아이디 (사용안함.)
	private String workproofFildId;					//재직증명서 파일 아이디 (사용안함.)
	private String downId;								//재직증빙서류 다운 아이디
	private String downDate;							//재직증빙서류 다운일
	private String deptNo;								//학과코드
	private String removeId;							//재직증빙서류 삭제 아이디
	private String removeDate;							//재직증빙서류 삭제일

	private String creatorId;							//생성자 아이디
	private String createDate;						//생성일
	private String modifierId;							//수정자 아이디
	private String modifyDate;						//수정일

	private String bizCompanyId;					//Session 기업아이디

	private String periodId;			//제직증빙서류 제출

	private String startTime;
	private String endTime;
	private String periodType;
	private String relativeRegulation;
	private String insuranceYn;
	private String receiptYn;

	private String state;//제출 상태 01 승인 02 반려
	private String returnReason;// 반려 이유
	private String year;
	private String grade;

	private String file1Yn;
	private String file2Yn;
	private String file3Yn;

	private String offInsYn; //4대보험 오프라인 여부
	private String offRecYn; //원천징수영수증 오프라인 여부
	private String offDocYn;	//보안서류 오프라인 여부

	private String memberTot; //학습 근로자 수
	private String insTot;	//4대보험 수
	private String recTot;	//원칭징수 수
	private String docTot; //보안서류 수
	private String stateTot; //승인 여부 수
	private String seach;
	
	private String search;		//검색조건
	
	private String seachState;
	private String seachGrade;

	private String startState;
	private String endState;
	
	private String sn;
	
	private String searchValue;
	private String searchCompanyName;
	private String searchState;
	
	private String[] memIdArr;
	private String[] workProofIdArr;

	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getSearchCompanyName() {
		return searchCompanyName;
	}
	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}
	public String getSearchState() {
		return searchState;
	}
	public void setSearchState(String searchState) {
		this.searchState = searchState;
	}
	public String getAtchFileIdInc() {
		return atchFileIdInc;
	}
	public void setAtchFileIdInc(String atchFileIdInc) {
		this.atchFileIdInc = atchFileIdInc;
	}
	public String getAtchFileIdRec() {
		return atchFileIdRec;
	}
	public void setAtchFileIdRec(String atchFileIdRec) {
		this.atchFileIdRec = atchFileIdRec;
	}
	public String getAtchFileIdDoc() {
		return atchFileIdDoc;
	}
	public void setAtchFileIdDoc(String atchFileIdDoc) {
		this.atchFileIdDoc = atchFileIdDoc;
	}
	public String getStartState() {
		return startState;
	}
	public void setStartState(String startState) {
		this.startState = startState;
	}
	public String getEndState() {
		return endState;
	}
	public void setEndState(String endState) {
		this.endState = endState;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getSeachGrade() {
		return seachGrade;
	}
	public void setSeachGrade(String seachGrade) {
		this.seachGrade = seachGrade;
	}
	public String getSeach() {
		return seach;
	}
	public void setSeach(String seach) {
		this.seach = seach;
	}
	public String getSeachState() {
		return seachState;
	}
	public void setSeachState(String seachState) {
		this.seachState = seachState;
	}
	public String getStateTot() {
		return stateTot;
	}
	public void setStateTot(String stateTot) {
		this.stateTot = stateTot;
	}
	public String getOffInsYn() {
		return offInsYn;
	}
	public void setOffInsYn(String offInsYn) {
		this.offInsYn = offInsYn;
	}
	public String getOffRecYn() {
		return offRecYn;
	}
	public void setOffRecYn(String offRecYn) {
		this.offRecYn = offRecYn;
	}
	public String getOffDocYn() {
		return offDocYn;
	}
	public void setOffDocYn(String offDocYn) {
		this.offDocYn = offDocYn;
	}
	public String getMemberTot() {
		return memberTot;
	}
	public void setMemberTot(String memberTot) {
		this.memberTot = memberTot;
	}
	public String getInsTot() {
		return insTot;
	}
	public void setInsTot(String insTot) {
		this.insTot = insTot;
	}
	public String getRecTot() {
		return recTot;
	}
	public void setRecTot(String recTot) {
		this.recTot = recTot;
	}
	public String getDocTot() {
		return docTot;
	}
	public void setDocTot(String docTot) {
		this.docTot = docTot;
	}
	public String[] getWorkProofIdArr() {
		return workProofIdArr;
	}
	public void setWorkProofIdArr(String[] workProofIdArr) {
		this.workProofIdArr = workProofIdArr;
	}
	public String[] getMemIdArr() {
		return memIdArr;
	}
	public void setMemIdArr(String[] memIdArr) {
		this.memIdArr = memIdArr;
	}
	public String getFile1Yn() {
		return file1Yn;
	}
	public void setFile1Yn(String file1Yn) {
		this.file1Yn = file1Yn;
	}
	public String getFile2Yn() {
		return file2Yn;
	}
	public void setFile2Yn(String file2Yn) {
		this.file2Yn = file2Yn;
	}
	public String getFile3Yn() {
		return file3Yn;
	}
	public void setFile3Yn(String file3Yn) {
		this.file3Yn = file3Yn;
	}
	private String companyName;

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getInsuranceYn() {
		return insuranceYn;
	}
	public void setInsuranceYn(String insuranceYn) {
		this.insuranceYn = insuranceYn;
	}
	public String getReceiptYn() {
		return receiptYn;
	}
	public void setReceiptYn(String receiptYn) {
		this.receiptYn = receiptYn;
	}
	public String getPeriodId() {
		return periodId;
	}
	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPeriodType() {
		return periodType;
	}
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	public String getRelativeRegulation() {
		return relativeRegulation;
	}
	public void setRelativeRegulation(String relativeRegulation) {
		this.relativeRegulation = relativeRegulation;
	}
	public String getBizCompanyId() {
		return bizCompanyId;
	}
	public void setBizCompanyId(String bizCompanyId) {
		this.bizCompanyId = bizCompanyId;
	}
	public String getRemoveId() {
		return removeId;
	}
	public void setRemoveId(String removeId) {
		this.removeId = removeId;
	}
	public String getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}

	public String getWorkProofId() {
		return workProofId;
	}
	public void setWorkProofId(String workProofId) {
		this.workProofId = workProofId;
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
	public String getSendYn() {
		return sendYn;
	}
	public void setSendYn(String sendYn) {
		this.sendYn = sendYn;
	}
	public String getWithholdingFileId() {
		return withholdingFileId;
	}
	public void setWithholdingFileId(String withholdingFileId) {
		this.withholdingFileId = withholdingFileId;
	}
	public String getInsureFileId() {
		return insureFileId;
	}
	public void setInsureFileId(String insureFileId) {
		this.insureFileId = insureFileId;
	}
	public String getWorkproofFildId() {
		return workproofFildId;
	}
	public void setWorkproofFildId(String workproofFildId) {
		this.workproofFildId = workproofFildId;
	}
	public String getDownId() {
		return downId;
	}
	public void setDownId(String downId) {
		this.downId = downId;
	}
	public String getDownDate() {
		return downDate;
	}
	public void setDownDate(String downDate) {
		this.downDate = downDate;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifierId() {
		return modifierId;
	}
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getLevel() {		
		return BizUtil.getLevel(memId);
	}
	public String getYearAdmission() {		
		return BizUtil.getYearAdmission(memId);
	}
	 
	@Override
	public String toString() {
		return "WorkCertVO [workProofId=" + workProofId + ", yyyy=" + yyyy
				+ ", term=" + term + ", memId=" + memId + ", memName="
				+ memName + ", sendYn=" + sendYn + ", atchFileId=" + atchFileId
				+ ", withholdingFileId=" + withholdingFileId
				+ ", insureFileId=" + insureFileId + ", workproofFildId="
				+ workproofFildId + ", downId=" + downId + ", downDate="
				+ downDate + ", removeId=" + removeId + ", removeDate="
				+ removeDate + ", creatorId=" + creatorId + ", createDate="
				+ createDate + ", modifierId=" + modifierId + ", modifyDate="
				+ modifyDate + ", bizCompanyId=" + bizCompanyId + "]";
	}




}
