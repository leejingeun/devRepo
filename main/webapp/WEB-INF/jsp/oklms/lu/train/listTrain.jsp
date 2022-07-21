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
  ~  * 이진근    2017. 01. 09 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>
<c:set var="targetUrl" value="/lu/train/"/>
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

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		$("#pageIndex").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listTrain.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 상세조회 페이지로 이동 */
	function fn_read( param1 ){
		
		//컨테츠 미리보기 테스트때문에 주석처리함.
		$("#id").val( param1 );
		//$("#id").val( '2' );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "getTrain.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_showLearningpop(){
		$(".learning-area,.popup-bg").addClass("open"); 
		window.location.hash = "#open";
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_hideLearningpop(){
		$(".learning-area,.popup-bg").removeClass("open");
	}
	
</script>

<!-- 회원정보 팝업용 시작 -->
<form id="frmPop" name="frmPop" method="post">
	<input type="hidden" name="memSeqPop" id="memSeqPop"/>
	<input type="hidden" name="lectureStat" id="lectureStat" value="01"/>
</form>
<!-- 회원정보 팝업용 끝 -->
			
<div class="content">
		<h1>온라인훈련</h1>
			<!-- E : search-list-1 (검색조건 영역) -->
			<form id="frmTrain" name="frmTrain" action="<c:url value='/lu/train/listTrain.do'/>" method="post">
			<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
			<input type="hidden" id="id" name="id" />
			
			<div class="search">
<%-- 			<select name="category_code" id="category_code" style="width:120px">
	  			<option selected value=''>--전체--</option>
	  			<c:forEach var="categoryCd" items="${categoryCode}" varStatus="status">
					<option value="${categoryCd.category_code}" ${categoryCd.category_code == trainVO.category_code ? 'selected="selected"' : '' }>${categoryCd.category_name}</option>
				</c:forEach>
		 	</select> --%>
			<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
			<input type="button" class="grey_search" value="검색" onclick="fn_search('1'); return false;" />
			</div>
		
		<!-- E : search-list-1 (검색조건 영역) -->
		<div class="float_left">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b>건</div>
		</form>
		
		<table class="table" width="100%">
			<tr>
				<th width="5%">No</th>
				<th width="50%">온라인 교과목명</th>
				<th width="10%">교과목 코드</th>			
				<th width="25%">분야</th>
				<th width="20%">미리보기</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status" begin="${page}" end="${pageSize}">
			<tr>
				<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				<td class="left">
					<a href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.id}"/>'); return false" onkeypress="this.onclick;"><c:out value="${result.title}" /></a>
				</td>
				<td><c:out value="${result.id}" /></td>
				<td><c:out value="${result.category_name}" /></td>
				<td>
					<a href="javascript:fn_showLearningpop();"><img src="/images/oklms/std/inc/searchBtn.png" alt="미리보기" /></a>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
			 	<td class="none" colspan="6" ><spring:message code="common.nodata.msg" /></td>
			</tr>
		   </c:if>
		</table>
 
		<!-- S : page-num (페이징 영역) -->
		<div class="page-num">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</div>
		<!-- E : page-num (페이징 영역) -->
	</div>
	
	
	
	
					

	