<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

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
		var count = 0;
		count = '${resultTraningChangeReadListCount}';

		$("#count").val(count);
	}

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* 입력 필드 초기화 */
	function fn_formReset( ){
		$("#retunReason").val(null);
	}

	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#companyId").val('');
		$("#traningProcessId").val('');

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/listTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("method", "post" );
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	/* 승인 */
	function fn_approvalUpdt(){
		if (confirm("훈련시간표 변경신청건에 대해 승인 하시겠습니까?")) {

			$("#changeStatus").val('2');

			var reqUrl = CONTEXT_ROOT + "/lu/traningChange/updateTraningChangeScheduleApplication.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").submit();
		}
	}

	/* 반려 */
	function fn_returnUpdt(){

		if (confirm("훈련시간표 변경신청건에 대해 반려 하시겠습니까?")) {

			if($("#retunReason").val() == ""){
				alert("반려시 사유를 입력 주세요.");
				$("#retunReason").focus();
				return false;
			}

			$("#changeStatus").val('3');

			var reqUrl = CONTEXT_ROOT + "/lu/traningChange/updateTraningChangeScheduleApplicationNot.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").submit();
		}
	}

</script>

<form id="frmTraning" name="frmTraning" method="post" >
<input type="hidden" id="companyId" name="companyId" value="${traningSchVO.companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningSchVO.traningProcessId}" />
<input type="hidden" id="updateStatusYn" name="updateStatusYn" value="N" />
<input type="hidden" id="changeStatus" name="changeStatus" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="yyyy" name="yyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${currProcReadVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${currProcReadVO.subClass}" />

<input type="hidden" id="searchYyyy" name="searchYyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="searchTerm" name="searchTerm" value="${currProcReadVO.term}" />
<input type="hidden" id="searchSubjectCode" name="searchSubjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="searchSubClass" name="searchSubClass" value="${currProcReadVO.subClass}" />

<div id="">
	<h2>훈련시간표 변경신청</h2>

	<table class="type-2">
		<colgroup>
			<col style="width:150px" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th class="bg-highlight">상태</th>
			<th>기업명</th>
			<th>소재지</th>
			<th>선정일</th>
			<th>기업고용관리번호</th>
			<th>훈련과정명</th>
		</tr>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td class="bg-highlight">승인대기</td>
			<td>${result.companyName}</td>
			<td>${result.address}</td>
			<td>${result.choiceDay}</td>
			<td>${result.employInsManageNo}</td>
			<td>${result.hrdTraningName}</td>
		</tr>
		</c:forEach>
	</table>

	<h4 class="mt-030 mb-010"><span>${currProcReadVO.subjectName}</span> (${currProcReadVO.subClass}반) ㅣ ${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</h4>

	<div class="group-area mb-040">
		<table class="type-write">
			<colgroup>
				<col style="width:150px" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th>주요교과목 및 내용</th>
				<td class="left">
					${traningChangeReadVO.subjctTitle}
					<input type="hidden" id="weekId" name="weekId" value="${traningChangeReadVO.weekId}" />
				</td>
			</tr>
			<tr>
				<th>변경사유</th>
				<td class="left">${traningChangeReadVO.changeReason}</td>
			</tr>
			<tr>
				<th>변경자</th>
				<td class="left">${traningChangeReadVO.memName}</td>
			</tr>
		</table>

		<div class="btn-area align-right mt-010">
			<c:if test="${ 'CCN' eq traningSchVO.sessionMemType && '1' == traningChangeReadVO.changeStatus}">
				<a href="#fn_formReset" onclick="javascript:fn_formReset(); return false" class="gray-1 float-left">입력취소</a>
				<input type="text" id="retunReason" name="retunReason" placeholder="ex)반려시 사유 필수 입력" />
				<a href="#fn_returnUpdt" onclick="javascript:fn_returnUpdt(); return false" class="yellow float-right">반려</a>
				<a href="#fn_approvalUpdt" onclick="javascript:fn_approvalUpdt(); return false" class="orange float-right">승인</a>
			</c:if>
		</div>
	</div>

	<div class="group-area">
		<h2>훈련시간표 변경 승인처리할 내역</h2>
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<%-- <col style="width:*" /> --%>
				<col style="width:110px" />
				<col style="width:105px" />
				<col style="width:40px" />
				<col style="width:145px" />
				<col style="width:110px" />
				<col style="width:40px" />
				<%-- <col style="width:100px" /> --%>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">주차</th>
					<!-- <th rowspan="2">능력단위요소</th> -->
					<th colspan="3">변경 전</th>
					<th colspan="3">변경 후 (신청내용)</th>
					<!-- <th rowspan="2">추가 / 삭제</th> -->
				</tr>
				<tr>
					<th class="border-left">일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
					<th>일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="status">
				<tr>
					<td>${result.weekCnt}</td>
					<%-- <td class="left">${result.ncsElemName}</td> --%>
					<td>${result.traningDate} (${result.dayOfWeek})</td>
					<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
					<td>${result.leadTime}</td>
					<td>${result.changeTraningDate} (${result.dayOfWeekChange})</td>
					<td>${result.changeTraningStHour}:00 ~ ${result.changeTraningEdHour}:00</td>
					<td>
						${result.changeLeadTime}
						<input type="hidden" id="traningDate-${status.count}" name="traningDate-${status.count}" value="${result.traningDate}" />
						<input type="hidden" id="traningStHour-${status.count}" name="traningStHour-${status.count}" value="${result.traningStHour}" />
						<input type="hidden" id="traningStMin-${status.count}" name="traningStMin-${status.count}" value="${result.traningStMin}" />
						<input type="hidden" id="traningEdHour-${status.count}" name="traningEdHour-${status.count}" value="${result.traningEdHour}" />
						<input type="hidden" id="traningEdMin-${status.count}" name="traningEdMin-${status.count}" value="${result.traningEdMin}" />
						<input type="hidden" id="leadTime-${status.count}" name="leadTime-${status.count}" value="${result.leadTime}" />

						<input type="hidden" id="changeTraningDate-${status.count}" name="changeTraningDate-${status.count}" value="${result.changeTraningDate}" />
						<input type="hidden" id="changeTraningStHour-${status.count}" name="changeTraningStHour-${status.count}" value="${result.changeTraningStHour}" />
						<input type="hidden" id="changeTraningEdHour-${status.count}" name="changeTraningEdHour-${status.count}" value="${result.changeTraningEdHour}" />
						<input type="hidden" id="changeLeadTime-${status.count}" name="changeLeadTime-${status.count}" value="${result.changeLeadTime}" />
					</td>
					<!-- <td><a href="#!" class="btn-full-blue">추가</a> <a href="#!" class="btn-full-gray">삭제</a></td> -->
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_list" onclick="javascript:fn_list();" class="gray-1" >목록</a>
		</div>
	</div><!-- E : 훈련일정변경 -->

</div><!-- E : content-area -->


</form>




