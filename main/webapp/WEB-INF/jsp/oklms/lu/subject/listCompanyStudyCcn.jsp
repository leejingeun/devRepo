<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

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
<c:set var="targetUrl" value="/lu/subject/"/>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보

	$(document).ready(function() {
		
		$("#checkbox").click(function(){
			if($("#checkbox").is(":checked")==true){
				$("input:checkbox[name='infoNumArr']").prop("checked",true);
			}else{
				$("input:checkbox[name='infoNumArr']").prop("checked",false);
			} 
		});	

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
			fn_search();
		}
	}
	
	 function fn_searchPageView(val) {
		$("#pageSize").val(val);
		 fn_search(pageIndex);
	}

	/* 리스트 조회 */
	function fn_search(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listCompanyCcn.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
	/* 엑셀 다운로드 */
	function fn_excel(){ 
		reqUrl = CONTEXT_ROOT + targetUrl + "excelCompanyCcn.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").attr("target","_self");
		$("#frmCompany").submit();	 
	}
	
	/* 엑셀 다운로드 */
	function fn_print(){ 
		var reqUrl = CONTEXT_ROOT + targetUrl + "printCompanyCcn.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").attr("target","_blank");
		$("#frmCompany").submit();
}
	
	
	/* 탭이동 */
	function fn_searchStatusType(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listCompanyCcn.do";
		$("#frmMove").attr("action", reqUrl);
		$("#frmMove").submit();
	}
	
	
	
	
	
</script>

			
<div id="content-area">
			<h2>담당기업체현황</h2>
			<!-- E : search-list-1 (검색조건 영역) -->
			
<form id="frmMove" name="frmMove" action="<c:url value='/lu/subject/listCompanyCcn.do'/>" method="post">
	<input type="hidden" id="searchStatusType" name="searchStatusType" value="COM" /> 
</form>					
			

<form id="frmCompany" name="frmCompany" action="<c:url value='/lu/subject/listCompanyCcn.do'/>" method="post">
		
			<dl id="tab-type">
				<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">학습관리 현황</a></dt>
				<dd id="tab-content-11" style="display:block;">

					<div class="search-box-1 mt-020 mb-010">
						<select  name="yyyy" id="yyyy">
							<c:forEach var="i" begin="0" end="2" step="1">
										<option value="${nowYear-i}" <c:if test="${compVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
									</c:forEach>	
						</select>
						<input type="text" name="searchKeyword" id="searchKeyword" value="${compVO.searchKeyword}" onkeypress="press(event);" placeholder="훈련과정명, 기업명 검색" style="width:250px;" />
						<a href="#!" onclick="fn_search();">검색</a>
					</div><!-- E : search-box-1 -->



					<table class="type-2">
						<colgroup>
							<col style="width:5px" />
							<col style="width:300px" />
							<col style="width:600px" />
							<%-- <col style="width:80px" />
							<col style="width:80px" /> --%>
							<col style="width:50px" />
							<col style="width:50px" />
							<c:forEach var="i" begin="0" end="${maxCnt}" step="1">
								<col style="width:80px" />
							</c:forEach>
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" id="checkbox" class="choice" /></th>
								<th>훈련과정명</th>
								<th>기업명</th>
								<!-- <th>시작일자</th>
								<th>종료일자</th> -->
								<th>훈련<br />일지</th>
								<th>내부<br />평가</th>
								<th colspan="${maxCnt}">학습활동서</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td rowspan="1"><input type="checkbox" name="infoNumArr" id="infoNumArr${status.index}" value="${result.infoNum}"  class="choice" /></td>
									<td rowspan="1">${result.hrdTraningName}</td>
									<td rowspan="1">${result.companyName}</td>
									<%-- <td rowspan="2">${result.hrdStartDate}</td>
									<td rowspan="1">${result.hrdEndDate}</td> --%>
									<td rowspan="1">${result.noteSumCnt}/${result.weekCnt}</td>
									<td rowspan="1">0/${result.evalPlanCnt}</td>
									<c:forEach var="i" begin="1" end="${maxCnt}" step="1">
										<c:set var="str" value="${fn:split(result.memInfos,',')[i-1]}"/>
										<td>${fn:split(str,'|')[0]}</td>
									</c:forEach>
								</tr>
								<%-- <tr>
									<c:forEach var="i" begin="1" end="${maxCnt}" step="1">
										<c:set var="str" value="${fn:split(result.memInfos,',')[i-1]}"/>
										<c:choose>
											<c:when test="${!empty fn:split(str,'|')[1]}"><td <c:if test="${ i == 1 }"> class="border-left" </c:if>>${fn:split(str,'|')[1]}/${result.weekCnt}</td></c:when>
											<c:otherwise><td></td></c:otherwise>
										</c:choose>
										</c:forEach>	
								</tr> --%>
							</c:forEach>
								<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="8"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>	
						</tbody>
					</table><!-- E : type-2 -->

					<div class="btn-area align-right mt-010">
						<a href="#fn_excel" onclick="fn_excel();" class="yellow">엑셀저장</a><a href="#!" onclick="javascript:fn_print();" class="orange">출력</a>
					</div><!-- E : btn-area -->
				</dd>
				<dt class="tab-name-12"><a href="javascript:fn_searchStatusType();">참여기업현황</a></dt>
				<dd id="tab-content-12"></dd>
			</dl>

</form>
					</div><!-- E : content-area -->
	
	
	
	
					

	