/*******************************************************************************
 * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
 * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
 *
 * Revision History
 * Author   Date            Description
 * ------   ----------      ------------------------------------
 * 이진근    2017. 03. 02.         First Draft.( Auto Code Generate )
 *
 *******************************************************************************/
package kr.co.sitglobal.oklms.lu.discuss.vo;

import java.io.Serializable;
import java.util.List;

import kr.co.sitglobal.oklms.comm.vo.BaseVO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DiscussVO extends BaseVO implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -2796779133473657996L;

	/* 토론 */
	private String discussId;        //토론 아이디
	private String yyyy;             //학년도
	private String term;             //학기
	private String subjectCode;      //교과목코드
	private String subjectName;      //교과목명
	private String subClass;         //분반
	private String title;            //토론 제목
	private String content;          //토론 내용
	private String startDate;        //시작-일
	private String startDt;          //시작-일(Full숫자로표현)
	private String startHour;        //시작-시간
	private String startMin;         //시작-분
	private String endDate;          //종료-일
	private String endDt;            //종료-일(Full숫자로표현)
	private String endHour;          //종료-시간
	private String endMin;           //종료-분
	private String evalYn;           //평가여부(Y/N)
	private String evalScore;        //평가점수
	private String scoreOpenYn;      //점수공개여부(Y/N)
	private String atchFileId;       //제출 파일 아이디
	private String deleteYn;         //삭제여부
	private String memSeq;           //회원고유번호
	private String evalYnName;       //평가명
	private String scoreOpenYnName;  //점수공개명
	private String memId;            	//회원ID
	private String memName;          	//회원명
	private String stdMarkResultState;  //채점결과 상태
	private String state;  //토론진행 상태
	private String resultEvalScore; //학습근로자 평가결과점수
	private String currentDt; //현재일
	private String evalScoreResutlYn;

	private List<DiscussVO> listDiscussOpinionComment; //토론의견 댓글 목록

	/* 토론 의견 */
	private String discussOpinionId;    //의견 아이디
	private String hitCnt;              //조회수
	private String goodCnt;             //추천수

	/* 토론 의견코멘트 */
	private String discussCommentId;    //코멘트 아이디

	/* 의견 추천 히스토리 */
	private String historyId;           //추천 히스토리  아이디

	/* 학습자별 토론 평가점수 */
	private String opinionCnt; 		//의견수
	private String commentCnt; 		//댓글수
	private String opinionGoodCnt; 	//추천수

	/* 학습자별 토론 평가점수 배열 */
	private String [] arrEvalScore;
	private String [] arrMemSeq;

	/* 검색필드 */
	private String searchTitle; 		//제목
	private String searchInsUserName; 		//작성자
	private String searchContent; 	//내용


	public List<DiscussVO> getListDiscussOpinionComment() {
		return listDiscussOpinionComment;
	}
	public void setListDiscussOpinionComment(
			List<DiscussVO> listDiscussOpinionComment) {
		this.listDiscussOpinionComment = listDiscussOpinionComment;
	}
	public String getDiscussId() {
		return discussId;
	}
	public void setDiscussId(String discussId) {
		this.discussId = discussId;
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
	public String getTitle() {
		return title;
	}
	public String getTitleAsHtmlEnterScript(){
      final String LT_CHAR = "&lt;";
       final String LT_REPLACE_CHAR = "<";
       final String GT_CHAR = "&gt;";
       final String GT_REPLACE_CHAR = ">";

      String scriptLecNotice = title;
       // '<', '>' 처리
        if( scriptLecNotice.indexOf( LT_CHAR ) >= 0 ) {
            scriptLecNotice = scriptLecNotice.replaceAll( LT_CHAR, LT_REPLACE_CHAR );
        }
        if( scriptLecNotice.indexOf( GT_CHAR ) >= 0 ) {
            scriptLecNotice = scriptLecNotice.replaceAll( GT_CHAR, GT_REPLACE_CHAR );
        }

      return scriptLecNotice;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public String getEvalYn() {
		return evalYn;
	}
	public void setEvalYn(String evalYn) {
		this.evalYn = evalYn;
	}
	public String getEvalScore() {
		return evalScore;
	}
	public void setEvalScore(String evalScore) {
		this.evalScore = evalScore;
	}
	public String getScoreOpenYn() {
		return scoreOpenYn;
	}
	public void setScoreOpenYn(String scoreOpenYn) {
		this.scoreOpenYn = scoreOpenYn;
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
	public String getDiscussOpinionId() {
		return discussOpinionId;
	}
	public void setDiscussOpinionId(String discussOpinionId) {
		this.discussOpinionId = discussOpinionId;
	}
	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}
	public String getGoodCnt() {
		return goodCnt;
	}
	public void setGoodCnt(String goodCnt) {
		this.goodCnt = goodCnt;
	}
	public String getDiscussCommentId() {
		return discussCommentId;
	}
	public void setDiscussCommentId(String discussCommentId) {
		this.discussCommentId = discussCommentId;
	}
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getMemSeq() {
		return memSeq;
	}
	public void setMemSeq(String memSeq) {
		this.memSeq = memSeq;
	}
	public String getEvalYnName() {
		return evalYnName;
	}
	public void setEvalYnName(String evalYnName) {
		this.evalYnName = evalYnName;
	}
	public String getScoreOpenYnName() {
		return scoreOpenYnName;
	}
	public void setScoreOpenYnName(String scoreOpenYnName) {
		this.scoreOpenYnName = scoreOpenYnName;
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
	public String getOpinionCnt() {
		return opinionCnt;
	}
	public void setOpinionCnt(String opinionCnt) {
		this.opinionCnt = opinionCnt;
	}
	public String getCommentCnt() {
		return commentCnt;
	}
	public void setCommentCnt(String commentCnt) {
		this.commentCnt = commentCnt;
	}
	public String getOpinionGoodCnt() {
		return opinionGoodCnt;
	}
	public void setOpinionGoodCnt(String opinionGoodCnt) {
		this.opinionGoodCnt = opinionGoodCnt;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getStdMarkResultState() {
		return stdMarkResultState;
	}
	public void setStdMarkResultState(String stdMarkResultState) {
		this.stdMarkResultState = stdMarkResultState;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getResultEvalScore() {
		return resultEvalScore;
	}
	public void setResultEvalScore(String resultEvalScore) {
		this.resultEvalScore = resultEvalScore;
	}
	public String getCurrentDt() {
		return currentDt;
	}
	public void setCurrentDt(String currentDt) {
		this.currentDt = currentDt;
	}
	public String[] getArrEvalScore() {
		return arrEvalScore;
	}
	public void setArrEvalScore(String[] arrEvalScore) {
		this.arrEvalScore = arrEvalScore;
	}
	public String[] getArrMemSeq() {
		return arrMemSeq;
	}
	public void setArrMemSeq(String[] arrMemSeq) {
		this.arrMemSeq = arrMemSeq;
	}
	public String getEvalScoreResutlYn() {
		return evalScoreResutlYn;
	}
	public void setEvalScoreResutlYn(String evalScoreResutlYn) {
		this.evalScoreResutlYn = evalScoreResutlYn;
	}


	public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	public String getSearchInsUserName() {
		return searchInsUserName;
	}
	public void setSearchInsUserName(String searchInsUserName) {
		this.searchInsUserName = searchInsUserName;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}


	/**
     * toString 메소드를 대치한다.
     */
    public String toString() {
	return ToStringBuilder.reflectionToString(this);
    }
}
