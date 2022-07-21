<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 01 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<style type="text/css">
</style>

<c:set var="targetUrl" value="/lu/currproc/"/>
<script type="text/javascript" src="${contextRootJS }/js/oklms/onlineStudyPreview.js"></script>
<script type="text/javascript">
	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보
	
	$(document).ready(function() {

		if ('' == pageSize) {
			pageSize = 10;
		}
		if ('' == totalCount) {
			totalCount = 0;
		}
		if ('' == pageIndex) {
			pageIndex = 1;
		}

		loadPage();
	});

	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}


	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

//         com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function fn_subject_schedule_form(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "getCurrProcScheduleForm.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	
	function fn_subject_eval_form(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "getCurrProcEvalForm.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	

	
</script>

<form id="frmLesson" name="frmLesson" method="post">
	<input type="hidden" id="id" name="id" value="" /> 
	<input type="hidden" id="courseContentId" name="courseContentId"/>
	<input type="hidden" id="lessonId" name="lessonId" />
	<input type="hidden" id="lessonItemId" name="lessonItemId" />
	<input type="hidden" id="lessonSubItemId" name="lessonSubItemId" />
	<input type="hidden" id="contentsDir" name="contentsDir" />
	<input type="hidden" id="contentsIdxFile" name="contentsIdxFile" />
	<input type="hidden" id="contentsRealFile" name="contentsRealFile" />
	<input type="hidden" id="weekCnt" name="weekCnt" value="" />
	<input type="hidden" id="weekId" name="weekId" value="" />
	<input type="hidden" id="subjSchId" name="subjSchId" value="" />
	<input type="hidden" id="cmsLessonId" name="cmsLessonId" value="" />
	<input type="hidden" id="cmsLessonItemId" name="cmsLessonItemId" value="" />
	<input type="hidden" id="cmsLessonSubItem" name="cmsLessonSubItem" value="" />
	<input type="hidden" id="linkContentYn" name="linkContentYn" value="" />
	<input type="hidden" id="progressAttendId" name="progressAttendId" value="" />
	<input type="hidden" id="progressTimeId" name="progressTimeId" value="" />
	<input type="hidden" id="pageInfo" name="pageInfo" value="" />
	<input type="hidden" id="viewLessonId" name="viewLessonId" value="" />
	<input type="hidden" id="spanId" name="spanId" value="" />
	<input type="hidden" id="attendProgress" name="attendProgress" value="${onlineTraningVO.attendProgress}" />
	<input type="hidden" id="cutProgress" name="cutProgress" value="${onlineTraningVO.cutProgress}" />
	<input type="hidden" id="progressAttendTypeCode" name="progressAttendTypeCode" value="${onlineTraningVO.progressAttendTypeCode}" />
	
	<input type="hidden" id="estblYy" name="estblYy" value="${subjectVO.estblYy}" />
	<input type="hidden" id="estblSemstrCd" name="estblSemstrCd" value="${subjectVO.estblSemstrCd}" />
	<input type="hidden" id="courseNo" name="courseNo" value="${subjectVO.courseNo}" />
	<input type="hidden" id="partitnClasSeCd" name="partitnClasSeCd" value="${subjectVO.partitnClasSeCd}" />
	
	<input type="hidden" id="classId" name="classId" value="" />
	<input type="hidden" id="companyId" name="companyId" value="" />
	
</form>

<ul id="learning-popup">
				<li class="learning-area"  id="learning-area" style="height:820px; margin-top:-410px;">
					<div class="title">
						<span>${subjectVO.subjectName}</span>
						<a href="javascript:hideLearningPop();" class="btn-4">종료</a>
					</div>
		
					<div id="learn-container">
						<div id="index" style="height:760px">
							<div class="index-title"><span>INDEX</span></div>
							<div class="index-list" id="index" style="height:560px">
								<dl id="index-schedule">
									<!-- <dt><span><b>01</b> 주차</span> <b>출석 : ○</b></dt>
									<dd class="highlight">
										<div><a href="#!"><b>[01차시]</b> 전기공압의 기초회로</a></div>
										<ul class="on">
											<li><a href="#!"><span>○</span>과정소개</a></li>
											<li><a href="#!"><span>○</span>학습에 앞서</a></li>
											<li class="current"><a href="#!"><span>○</span>사전지식체크</a></li>
											<li><a href="#!"><span>○</span>시퀀스 제어란?</a></li>
											<li><a href="#!"><span>○</span>전기접점의 종류와 특징</a></li>
										</ul>
									</dd>
									-->
								</dl>
							</div>
		
							<div class="index-guide">
								<span>○ : 출석인정 진도율 ${onlineTraningVO.attendProgress}이상, △ : 지각인정 진도율 ${onlineTraningVO.cutProgress}이상 ~ 출석인정 진도율 ${onlineTraningVO.attendProgress}미만, × : 지각인정 진도율 ${onlineTraningVO.cutProgress}미만</span>
							</div>
						</div>
		
		
						<div id="movie-zone">
							<div class="movie-name"><span id="week-schedule-title">[ 01주차 ] 01차시&nbsp;&nbsp;-&nbsp;&nbsp;전기공압의 기초회로</span></div>
							<div class="wait-img" style="height:660px">잠시만 기다려주세요.</div>
							<iframe src="" id="contentFrame" width="512px" height="660px" frameborder="0" allowfullscreen></iframe>
		
							<div class="movie-control">
								<a href="javascript:prevView();" class="pre">이전</a>
								<span id="contentPage"><b>01</b> / 24</span>
								<a href="javascript:nextView();" class="next">다음</a>
							</div>
						</div><!-- E : movie-zone -->
					</div>
		
				</li>
				<li class="popup-bg"></li>
			</ul><!-- E : learning-popup -->
		<div id="content-area">
						<h2>교과목 정보</h2>
						
						
						<h4 class="mb-010"><span>${subjectVO.subjectName}</span> (${subjectVO.subjectClass}반) ㅣ ${subjectVO.yyyy}학년도 ${subjectVO.term}학기</h4>

						<div class="group-area mb-010">
							<table class="type-1">
								<colgroup>
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
								</colgroup>
								<tr>
									<th>교과형태</th>
									<td>${subjectVO.subjectTraningTypeName}</td>
									<th>과정구분</th>
									<td>${subjectVO.subjectTypeName}</td>
									<th>훈련시간</th>
									<td>${subjectVO.traningHour}시간</td>
								</tr>
								<tr>
									<th>담당교수</th>
									<td>${subjectVO.insNames}</td>
									<th>온라인 교육형태</th>
									<td>${subjectVO.onlineTypeName} (성적비율 ${subjectVO.gradeRatio}%)</td>
									<th>선수여부</th>
									<td>${subjectVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
								</tr>
							</table>

							<div class="btn-area align-right mt-010">
								<a href="#none" onclick="fn_subject_schedule_form();" class="yellow">강의계획서</a><a href="#none" onclick="fn_subject_eval_form();" class="orange">체크리스트</a>
							</div><!-- E : btn-area -->
						</div><!-- E : 교과목 정보 -->

	<%-- <div class="progress-area mb-030">
									<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
									<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
									<p>실제 진도율<c:if test="${ getProgress.realProgressRate > 0}" > (<fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" />)</c:if></p>
									<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
								</div><!-- E : 진행율 --> --%>


						<h2>진행율</h2>
						<div class="progress-area mb-040">
							<ul>
								<!-- <li style="width:0%; text-align: center"><span><b>0</b> %</span></li> -->
								<li style="width:${ empty getProgress.realProgressRate  ? 0 : getProgress.realProgressRate}%; text-align: center"><span><b> ${ empty getProgress.realProgressRate  ? 0 : getProgress.realProgressRate }</b> %</span></li>
							</ul>
						</div><!-- E : 진행율 -->

						<h2>훈련정보</h2>
						<table class="type-2">
							<colgroup>
								<col style="width:5%" />
								<col style="width:120px" />
								<col style="width:*" />
								<col style="width:40px" />
								<col style="width:75px" />
								<col style="width:40px" />
								<col style="width:85px" />
								<col style="width:40px" />
								<col style="width:60px" />
								<col style="width:60px" />
							</colgroup>
							<thead>
								<tr>
									<th>주차</th>
									<th>훈련일자</th>
									<th>능력단위/<br />능력단위요소</th>
									<th>교육<br />시간</th>
									<th>온라인<br />학습</th>
									<th>보조<br />자료</th>
									<th>과제<br />정보</th>
									<th>학습<br />자료</th>
									<th>훈련<br />일지</th>
									<th>학습<br />활동서</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									
									
									<c:choose>
										<c:when test="${result.weekCnt eq '10'}">
											<tr>
												<td>${result.weekCnt}</td>
												<td>${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
												<td colspan="8" align="center">휴강 (보강일 2017.06.17)</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr <c:if test="${result.nowWeekYn eq 'Y'}">class="highlight"</c:if>>
												<td>${result.weekCnt}</td>
												<td>${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
												<td class="left">${result.ncsName}</td>
												<td>${result.timeHour}</td>
												<td>
												<c:choose>
													<c:when test="${result.weekMappingCnt ne '0'}">
														<a href="javascript:goWeekLessonPreview('${result.weekId}','${result.weekCnt}','${result.contentName}');" class="btn-full-gray">미리보기</a>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
												</td>
												<td><!-- <a href="javascript:alert('준비중입니다.');" class="btn-search-gray"></a> --></td>
												<td>
												<c:if test="${!empty  result.reportId}">
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-full-gray" style="width:49px;">과제</a>
												</c:if>
												<c:if test="${!empty  result.discussId}">
													<c:if test="${!empty  result.reportId}"><br /></c:if>
													<a href="/lu/discuss/getDiscuss.do?discussId=${result.discussId}&yyyy=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subjectClass}" class="btn-full-gray" style="width:49px;">토론</a>
												</c:if>
												</td>
												<td><!-- <a href="/lu/cop/bbs/BBSMSTR_000000000009/selectBoardArticle.do" class="btn-search-gray"></a> --></td>
												<td>
													<c:choose>
														<c:when test="${result.nowWeekYn eq 'Y'}">
															<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${result.traNoteCnt eq '0'}">
																	<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-orange">미작성</a>
																</c:when>
																<c:otherwise>
																	<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${result.nowWeekYn eq 'Y'}">
															<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${result.actNoteCnt eq result.lessonCnt}">
																	<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
																</c:when>
																<c:otherwise>
																	<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">${result.actNoteCnt} / ${result.lessonCnt}</a>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<c:if test="${result.weekCnt eq '10'}">
											<tr <c:if test="${result.nowWeekYn eq 'Y'}">class="highlight"</c:if>>
												<td>10주차<br/>보강</td>
												<td>2017.06.17(토)<br />${result.traningSt} ~ ${result.traningEd}</td>
												<td class="left">${result.ncsName}</td>
												<td>${result.timeHour}</td>
												<td>
												<c:choose>
													<c:when test="${result.weekMappingCnt ne '0'}">
														<a href="javascript:goWeekLessonPreview('${result.weekId}','${result.weekCnt}','${result.contentName}');" class="btn-full-gray">미리보기</a>
													</c:when>
													<c:otherwise>
													</c:otherwise>
												</c:choose>
												</td>
												<td><!-- <a href="javascript:alert('준비중입니다.');" class="btn-search-gray"></a> --></td>
												<td>
												<c:if test="${!empty  result.reportId}">
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-full-gray" style="width:49px;">과제</a>
												</c:if>
												<c:if test="${!empty  result.discussId}">
													<c:if test="${!empty  result.reportId}"><br /></c:if>
													<a href="/lu/discuss/getDiscuss.do?discussId=${result.discussId}&yyyy=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subjectClass}" class="btn-full-gray" style="width:49px;">토론</a>
												</c:if>
												</td>
												<td><!-- <a href="/lu/cop/bbs/BBSMSTR_000000000009/selectBoardArticle.do" class="btn-search-gray"></a> --></td>
												<td>
													<c:choose>
														<c:when test="${result.nowWeekYn eq 'Y'}">
															<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${result.traNoteCnt eq '0'}">
																	<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-orange">미작성</a>
																</c:when>
																<c:otherwise>
																	<a href="/lu/traning/listTraningNote.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${result.nowWeekYn eq 'Y'}">
															<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
														</c:when>
														<c:otherwise>
															<c:choose>
																<c:when test="${result.actNoteCnt eq result.lessonCnt}">
																	<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
																</c:when>
																<c:otherwise>
																	<a href="/lu/activity/listActivityPrt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">${result.actNoteCnt} / ${result.lessonCnt}</a>
																</c:otherwise>
															</c:choose>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</c:if>
								</c:forEach>
								<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="10"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>
							</tbody>
						</table><!-- E : 훈련정보 -->

						


