<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="targetUrl" value="/la/statistics/" />
<script type="text/javaScript" language="javascript">
	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************* */
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
		var dt = new Date();
		var month_bef = dt.getMonth();
		var month = dt.getMonth()+1;
		var day = dt.getDate();
		var year = dt.getFullYear();
		
		if("${param.startDate}"==""){
			$("#startDate").val(year+'-' + month + '-' + day);
			$("#finishDate").val(year+'-' + month + '-' + day);	
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

		//     com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);

		com.datepicker('startDate', 'button');
		com.datepicker('finishDate', 'button');
	}

	/*====================================================================
	 사용자 정의 함수 
	 ====================================================================*/
	function press(event) {
		if (event.keyCode == 13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search(param1) {

		$("#pageIndex").val(param1);
		var reqUrl = CONTEXT_ROOT + targetUrl + "listLoginLog.do";
		$("#frmLoginLog").attr("action", reqUrl);
		$("#frmLoginLog").submit();
	}

	function fn_LoginLogManage() {
		$("#startDate").val() =="" 
		fn_search(pageIndex);
	}
	
	function fn_calendarClear(Obj) {
		document.getElementById(Obj).value="";
	}
</script>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부
	기능을 사용하실 수 없습니다.</noscript>
<form name="frmLoginLog" id="frmLoginLog" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="orderKey" 	name="orderKey" value="${param.orderKey}" /> 
	<input type="hidden" id="orderDir" name="orderDir" value="${param.orderDir}" />
	<!-- E : search-list-1 (검색조건 영역) -->
	<ul class="search-list-1">
		<li><span>로그인아이피</span> 
			<input type="text" id="searchClientIp" name="searchClientIp" style="width: 300px;" value="${param.searchClientIp}" />
		</li>
		<li><span>로그인아이디</span> 
			<input type="text" id="searchLogMemId" name="searchLogMemId" style="width: 300px;" value="${param.searchLogMemId}" />
		</li>
		<li><span>게시일</span> 
		<input type="text" id="startDate" name="startDate" value="${param.startDate}" style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('startDate');"/> ~ 
		<input type="text"  id="finishDate" name="finishDate" value="${param.finishDate}" style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('finishDate');"/>
		</li>
	</ul>
</form>
<!-- E : search-list-1 (검색조건 영역) -->
<div class="search-btn-area">
	<a href="#@" onclick="fn_LoginLogManage(); return false;">조회</a>
</div>
<ul class="board-info">
	<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }" /> 건</li>
</ul>
<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="100px">아이피</th>
			<th width="100px">아이디</th>
			<th width="100px">일시</th>
			<th width="100px">회원구분</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td class="lt_text3" colspan="4"><spring:message code="common.nodata.msg" /></td>
			</tr>
		</c:if>
		<%-- 데이터를 화면에 출력해준다 --%>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><c:out value="${resultInfo.clientIp}" /></td>
				<td><c:out value="${resultInfo.logMemId}" /></td>
				<td><c:out value="${resultInfo.loginDate}" /></td>
				<td><c:out value="${resultInfo.logMemType}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- E : list (게시판 목록 영역) -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div>
<!-- E : page-num -->