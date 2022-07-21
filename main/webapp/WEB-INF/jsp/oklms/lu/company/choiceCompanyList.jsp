<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style type="text/css">
</style>

<c:set var="targetUrl" value="/lu/company/"/>
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
		
		$("#curMenuTitle").html(com.getCurMenuTitle());
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

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		$("#pageIndex").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listCompany.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}

	
	function fn_searchPageView(val) {
		$("#pageSize").val(val);
		fn_search(pageIndex);
	}
	

	function fn_choiceCompany(companyId,companyName) {
		$("#companyId").val(companyId);
		$("#companyName").val(companyName);
		var reqUrl = CONTEXT_ROOT + targetUrl +"ChoiceCompany.json";
		var param = $("#frmSessionCompany").serializeArray();

		com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
		if (200 == jqXHR.status ) {
			com.alert( jqXHR.responseJSON.retMsg );
		}
		}, {
			async : true,
			type : "POST",
			spinner : true,
			errorCallback : null,
			timeout : 1000			// 기본 30초
		});
	}
	
</script>
<div class="content">
<form id="frmSessionCompany" name="frmSessionCompany" method="post">
	<input type="hidden" id="companyId" name="companyId" />
	<input type="hidden" id="companyName" name="companyName" />
</form>
<!-- 회원정보 팝업용 끝 -->
<form id="frmCompany" name="frmCompany" action="<c:url value='/lu/Company/Company/listCompany.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	
	<h1 id="curMenuTitle"></h1>
	<div class="search">
		<span>기업</span>&nbsp;
		<select name="searchName" id="searchName" style="width:70px">
				<option value=''>전체</option>
				<option ${param.searchName == 'NAME' ? 'selected="selected"' : ''} value='NAME'>이름</option>
				<option ${param.searchName == 'ID' ? 'selected="selected"' : ''} value='ID'>아이디</option>
		</select>
		&nbsp;
		<input type="text" name="searchWord" id="searchWord" value="${param.searchWord}" style="width:410px;" />
		<input type="button" class="grey_search" value="검색"  onClick="javascript:fn_search(1);"/>
	</div>
<div class="float_left">총  ${paginationInfo.totalRecordCount}건 (${pageIndex} / ${paginationInfo.totalPageCount} Page)</div>
<div class="float_right">Page View :  
10&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="10" ${pageSize == '10' ? 'checked' : ''} onclick="javascript:fn_searchPageView('10');">
20&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="20" ${pageSize == '20' ? 'checked' : ''} onclick="javascript:fn_searchPageView('20');"> 
50&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="50" ${pageSize == '50' ? 'checked' : ''} onclick="javascript:fn_searchPageView('50');"> 
Lines
</div>
<!-- E : search-list-1 -->
</form>
<table class="table" id="rptbl">
	<thead>
		<tr>
			<th width="15%">회사아이디</th>
			<th width="30%">회사명</th>
			<th width="20%">업종</th>
			<th width="20%">업태</th>
			<th width="15%">업체대표</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td style="text-align: left;"><c:out value="${result.companyId}" /></td>
			<td style="text-align: left;">
				<a href="javascript:fn_choiceCompany('${result.companyId}','${result.companyName}')"><span color="blue" style="float:none;"><c:out value="${result.companyName}" /></span></a>
			</td>
			<td><c:out value="${result.businessType}" /></td>
			<td><c:out value="${result.businessCondition}" /></td>
			<td><c:out value="${result.ceo}" /></td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E : list -->
</div>
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div><!-- E : page-num -->