<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("br", "<br/>");  //br 태그
      pageContext.setAttribute("cn", "\n"); //Space, Enter
%> 
<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2017. 04. 02 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>
 
 <c:set var="targetUrl" value="/lu/currproc/"/>
<script type="text/javascript" src="${contextRootJS }/js/oklms/onlinePreview.js"></script>
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
	
	function fn_online_traing(){
		var reqUrl = "/lu/online/listOnlineTraning.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}

	
</script>

<form id="frmLesson" name="frmLesson" method="post">
		<input type="hidden" id="estblYy" name="estblYy" value="${subjectVO.estblYy}" />
	<input type="hidden" id="estblSemstrCd" name="estblSemstrCd" value="${subjectVO.estblSemstrCd}" />
	<input type="hidden" id="courseNo" name="courseNo" value="${subjectVO.courseNo}" />
	<input type="hidden" id="partitnClasSeCd" name="partitnClasSeCd" value="${subjectVO.partitnClasSeCd}" />
</form>
<br/><br/><br/><br/>
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
			<th>학점</th>
			<td>${subjectVO.point}</td>
			<th>기업명</th>
			<td>${subjectVO.companyName}</td>
			<th>기업현장교사</th>
			<td>${sessionMemName}</td>
		</tr>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#none" onclick="fn_subject_schedule_form();" class="yellow">강의계획서</a><a href="#none" onclick="fn_subject_eval_form();" class="orange">체크리스트</a> 
	</div><!-- E : btn-area -->
</div><!-- E : 교과목 정보 -->

<h2>진행율</h2>
<div class="progress-area mb-040">
	<p>전체 진행율 [ 0 페이지 / 0 페이지 ]</p>
	<ul>
		<li style="width:0%;"><span><b>0</b> %</span></li>
	</ul>
</div>

<div class="btn-area align-right ">
	<a href="#!" onclick="javascript:fn_list(); return false" class="yellow">변경신청내역</a>
	<a href="#!" onclick="javascript:fn_cret(); return false" class="orange">신규 변경신청</a>
</div><!-- E : btn-area -->

<h2>훈련정보</h2>
<table class="type-2 mb-040">
	<colgroup>
		<col style="width:5%" />
		<col style="width:15%" />
		<col style="width:24%" />
		<col style="width:24%" />
		<col style="width:8%" />
		<col style="width:8%" />
		<col style="width:8%" />
		<col style="width:8%" />
	</colgroup>
	<thead>
		<tr>
			<th>주차</th>
			<th>훈련일자</th>
			<th>능력단위명</th>
			<th>능력단위요소명</th>
			<th>교육시간</th>
			<th>학습자료</th>
			<th>훈련일지</th>
			<th>학습활동서</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
			<c:choose>
				<c:when test="${result.rowspanCnt eq '1'}">
					<tr <c:if test="${result.nowWeekCnt eq result.weekCnt}">class="highlight"</c:if>>
						<td>${result.weekCnt}</td>
						<td class="border-left">${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
						<td class="left">${result.ncsUnitName}</td>
						<td class="left">${fn:replace(result.ncsElemName, cn , br )}</td>
						<td>${result.timeHour}</td>
						<td><!-- <a href="/lu/cop/bbs/BBSMSTR_000000000009/selectBoardList.do" class="btn-search-gray"></a> --></td>
						<td>
							<c:choose>
								<c:when test="${result.nowWeekYn eq 'Y'}">
									<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${result.traNoteCnt eq '0'}">
											<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">미작성</a>
										</c:when>
										<c:otherwise>
											<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${result.nowWeekYn eq 'Y'}">
									<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${result.actNoteCnt eq result.lessonCnt}">
											<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
										</c:when>
										<c:otherwise>
											<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">${result.actNoteCnt} / ${result.lessonCnt}</a>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${result.rowNum eq '1'}">
							<tr <c:if test="${result.nowWeekCnt eq result.weekCnt}">class="highlight"</c:if>>
								<td rowspan="${result.rowspanCnt}">${result.weekCnt}</td>
								<td class="border-left">${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
								<td class="left">${result.ncsUnitName}</td>
								<td class="left">${fn:replace(result.ncsElemName, cn , br )}</td>
								<td rowspan="${result.rowspanCnt}">${result.timeHour}</td>
								<td rowspan="${result.rowspanCnt}"><!-- <a href="/lu/cop/bbs/BBSMSTR_000000000009/selectBoardList.do" class="btn-search-gray"></a> --></td>
								<td rowspan="${result.rowspanCnt}">
									<c:choose>
										<c:when test="${result.nowWeekCnt eq result.weekCnt}">
											<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${result.traNoteCnt eq '0'}">
													<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">미작성</a>
												</c:when>
												<c:otherwise>
													<a href="/lu/traning/listTraningNoteOt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
								<td rowspan="${result.rowspanCnt}">
									<c:choose>
										<c:when test="${result.nowWeekCnt eq result.weekCnt}">
											<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-blue">진행</a>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${result.actNoteCnt eq result.lessonCnt}">
													<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-gray">완료</a>
												</c:when>
												<c:otherwise>
													<a href="/lu/activity/listActivityOt.do?weekCnt=${result.weekCnt}" class="btn-line-orange">${result.actNoteCnt} / ${result.lessonCnt}</a>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr <c:if test="${result.nowWeekCnt eq result.weekCnt}">class="highlight"</c:if>>
								<td class="border-left">${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
								<td class="left">${result.ncsUnitName}</td>
								<td class="left">${fn:replace(result.ncsElemName, cn , br )}</td>
							</tr>
						</c:otherwise>
					</c:choose>	 
					
				</c:otherwise>
			</c:choose>	
	</c:forEach>
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="10"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	</tbody>
</table><!-- E : 훈련정보 -->

<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="companyId" name="companyId" value="${subjectVO.companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${subjectVO.traningProcessId}" />

<input type="hidden" id="yyyy" name="yyyy" value="${subjectVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${subjectVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${subjectVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${subjectVO.subjectClass}" />

<input type="hidden" id="searchYyyy" name="searchYyyy" value="${subjectVO.yyyy}" />
<input type="hidden" id="searchTerm" name="searchTerm" value="${subjectVO.term}" />
<input type="hidden" id="searchSubjectCode" name="searchSubjectCode" value="${subjectVO.subjectCode}" />
<input type="hidden" id="searchSubClass" name="searchSubClass" value="${subjectVO.subjectClass}" />
</form>

<script type="text/javascript">

function fn_read1( ){
	alert('준비중');
}

function fn_read2( ){
	alert('준비중');
}

/* 훈련시간표 변경신청 신규 페이지로 이동 */
function fn_cret( ){

	var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplicationCot.do";

	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").submit();
}

/* 훈련시간표 변경신청내역 목록 이동 */
function fn_list( ){

	var reqUrl = CONTEXT_ROOT + "/lu/traningChange/listTraningChangeScheduleApplication.do";

	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").submit();
}

</script>




