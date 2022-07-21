<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>

<c:set var="targetUrl" value="/lu/discuss/"/>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
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
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 토론 상세조회 페이지로 이동 */
	function fn_read( param1, param2, param3, param4, param5){
		var startDt = param2;
		var endDt = param3;
		var currentDt = param4;
		var title = param5;

		if(startDt > currentDt){
			alert("토론 제목 = ["+title+"] 공개시작전 입니다.");
			return false;
		} else if(currentDt > endDt){
			alert("토론 제목 = ["+title+"] 마감 완료되없습니다.");
			return false;
		}

		$("#discussId").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "getDiscuss.do";

		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

</script>

<form id="frmDiscuss" name="frmDiscuss" method="post">
<input type="hidden" id="discussId" name="discussId" />
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />

<div id="">
	<h2>토론</h2>
	<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

	<table class="type-1 mb-040">
		<colgroup>
			<col style="width:15%" />
			<col style="width:*px" />
			<col style="width:15%" />
			<col style="width:*px" />
			<col style="width:15%" />
			<col style="width:*px" />
		</colgroup>
		<tbody>
			<tr>
				<th>교과형태</th>
			<td>${currProcReadVO.subjectTraningTypeName}</td>
				<th>과정구분</th>
			<td>${currProcReadVO.subjectTypeName}</td>
				<th>학점</th>
				<td>${currProcReadVO.point }학점</td>
			</tr>
			<tr>
				<th>교수</th>
				<td>${currProcReadVO.insNames}</td>
				<th>온라인 교육형태</th>
				<td>${currProcReadVO.onlineTypeName} (성적비율 ${currProcReadVO.gradeRatio}%)</td>
				<th>선수여부</th>
				<td>${currProcReadVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
			</tr>
		</tbody>
	</table>

	<h2>토론 목록 조회</h2>
	<table class="type-2">
		<colgroup>
			<col style="width:50px" />
			<col style="width:*" />
			<col style="width:180px" />
			<col style="width:50px" />
			<col style="width:50px" />
			<col style="width:50px" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>기간</th>
				<th>의견</th>
				<th>점수</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultDiscussList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td class="left"><a href="#fn_read" onclick="javascript:fn_read('${result.discussId}','${result.startDt}','${result.endDt}','${result.currentDt}','${result.title}'); return false" class="text">${result.title}</a></td>
				<td>${result.startDate} ~ ${result.endDate}</td>
				<td>${result.opinionCnt}</td>
				<td><c:if test="${result.scoreOpenYn eq 'Y'}">${result.resultEvalScore}</c:if></td>
				<td>
					<c:choose>
					<c:when test="${result.startDt > result.currentDt}">
					<span class="btn-line-gray"> 예정 </span>
					</c:when>
					<c:when test="${result.endDt >= result.currentDt}">
					<span class="btn-line-blue"> 진행 </span>
					</c:when>
					<c:when test="${result.endDt > result.currentDt}">
					<span class="btn-line-orange"> 완료 </span>
					</c:when>
					<c:when test="${result.currentDt > result.endDt}">
					<span class="btn-line-orange"> 마감 </span>
					</c:when>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultDiscussList) == 0}">
			<tr>
				<td colspan="6"><spring:message code="common.nosarch.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table><!-- E : 토론 -->

</div><!-- E : content-area -->

</form>



