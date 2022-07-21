<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>

<c:set var="targetUrl" value="/lu/traningChange/"/>
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
		com.datepickerDateFormat('searchStartUpdateDate');
 	    com.datepickerDateFormat('searchEndUpdateDate');
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 훈련시간표 승인된 변경이력 조회 */
	function fn_search( ){

		var reqUrl = CONTEXT_ROOT + targetUrl + "listTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	/* 훈련시간표 변경내역 상세조회 페이지로 이동 */
	function fn_read( ){

		var checkedVal = "";
		checkedVal = $(":input:radio[name=traningProcessIdSelect]:checked").val();

		if(undefined == checkedVal){
			alert("상세조회할 훈련시간표 변경신청건을 목록에서 선택하여주십시오.");
			return false
		}

		$("#traningProcessIdArr").val( checkedVal );

		var reqUrl = CONTEXT_ROOT + targetUrl + "getTraningChangeScheduleApplicationCot.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	/* 훈련시간표 변경신청 수정 페이지로 이동 */
	function fn_updt( ){

		var checkedVal = "";
		checkedVal = $(":input:radio[name=traningProcessIdSelect]:checked").val();

		if(undefined == checkedVal){
			alert("수정할 훈련시간표 변경신청 목록에서 선택하여주십시오.");
			return false
		}

		$("#traningProcessIdArr").val( checkedVal );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateTraningChangeScheduleApplicationCot.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	/* 훈련시간표 변경신청 신규 페이지로 이동 */
	function fn_cret( ){

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertTraningChangeScheduleApplicationCot.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	/* 훈련시간표 변경신청 훈련일자 null로 초기화 및 변경상태값 변경 */
	function fn_del( ){

		var checkedVal = "";
		checkedVal = $(":input:radio[name=traningProcessIdSelect]:checked").val();

		if(undefined == checkedVal){
			alert("삭제할 훈련시간표 변경신청건을 미승인 변경신청 목록에서 선택하여주십시오.");
			return false
		}

		$("#traningProcessIdArr").val( checkedVal );

		if (confirm("승인대기중인 훈련시간표 변경신청건을 삭제 하시겠습니까?")) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteTraningChangeScheduleApplication.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").submit();
		}
	}

</script>

<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="companyId" name="companyId"/>
<input type="hidden" id="traningProcessId" name="traningProcessId" />
<input type="hidden" id="traningProcessIdArr" name="traningProcessIdArr" />
<input type="hidden" id="yyyy" name="yyyy" />
<input type="hidden" id="term" name="term" />
<input type="hidden" id="subjectCode" name="subjectCode" />
<input type="hidden" id="subClass" name="subClass" />

<input type="hidden" id="searchYyyy" name="searchYyyy" />
<input type="hidden" id="searchTerm" name="searchTerm" />
<input type="hidden" id="searchSubjectCode" name="searchSubjectCode" />
<input type="hidden" id="searchSubClass" name="searchSubClass" />

<div id="">
	<h2>훈련시간표 변경신청 내역</h2>
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
</div><!-- E : 교과목 정보 -->

	<div class="group-area mb-010">
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<col style="width:40px" />
				<col style="width:120px" />
				<col style="width:100px" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:100px" />
			</colgroup>
			<thead>
				<tr>
					<th>선택</th>
					<th>번호</th>
					<th>변경 신청일</th>
					<th>신청자</th>
					<th>변경사유</th>
					<th>IP</th>
					<th>상태</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList1}" varStatus="status">
				<tr>
					<td><input type="radio" id="traningProcessIdSelect" name="traningProcessIdSelect" value="${result.yyyy},${result.term},${result.subjectCode},${result.subClass}" class="choice" /></td>
					<td>${status.count}</td>
					<td>${result.updateDate}</td>
					<td>${result.memName}</td>
					<td class="left">${result.changeReason}</td>
					<td>${result.ipAddress}</td>
					<td class="bg-highlight">${result.changeStatusName}</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultList1) == 0}">
				<tr>
					<td colspan="7"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_cret" onclick="javascript:fn_cret(); return false" class="yellow float-left" onkeypress="this.onclick();">신규 변경신청</a>
			<a href="#fn_del" onclick="javascript:fn_del(); return false" class="gray-1">삭제</a>
			<a href="#fn_read" onclick="javascript:fn_read(); return false" class="yellow">상세조회</a>
			<a href="#fn_updt" onclick="javascript:fn_updt(); return false" class="orange">수정</a>
		</div>
	</div>



</div><!-- E : content-area -->


</form>





