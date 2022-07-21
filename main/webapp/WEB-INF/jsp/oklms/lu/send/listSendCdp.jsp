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
<c:set var="targetUrl" value="/lu/send/"/>
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
		
		$("#checkbox").click(function(){
			if($("#checkbox").is(":checked")==true){
				$("input:checkbox[name='memSeqs']").prop("checked",true);
			}else{
				$("input:checkbox[name='memSeqs']").prop("checked",false);
			} 
		});	

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
//      com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_search(1);
		}
	}
	
	function fn_searchPageView(val) {
		$("#pageSize").val(val);
		 fn_search(pageIndex);
	}
	

	/* 리스트 조회 */
	function fn_search( pageIndex ){
		$("#pageIndex").val( pageIndex );
		var reqUrl = CONTEXT_ROOT + targetUrl + "listSendCdp.do";
		$("#frmSend").attr("action", reqUrl);
		$("#frmSend").submit();
	}
	
	
	
	
</script>

			
<div id="content-area">
			<h2>SMS / E-MAIL 발송</h2>
			<!-- E : search-list-1 (검색조건 영역) -->
			

<form id="frmSend" name="frmSend" action="<c:url value='/lu/send/listSendCdp.do'/>" method="post">
    <input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
		
			<div class="search-box-1 mb-020">
					<select name="deptCode" id="deptCode">
						<option value="">학과명</option>
						<c:forEach var="result" items="${deptCodeList}" varStatus="status">
							<option value="${result.codeId}" <c:if test="${sendVO.deptCode eq result.codeId }" > selected="selected"  </c:if>>${result.codeName}</option>
						</c:forEach>		
					</select>
					<select name="subjectGrade" id="subjectGrade">
						<option value="">대상학년</option>
						<option value="1" <c:if test="${sendVO.subjectGrade eq '1' }" > selected="selected"  </c:if>>1학년</option>
						<option value="2" <c:if test="${sendVO.subjectGrade eq '2' }" > selected="selected"  </c:if>>2학년</option>
						<option value="3" <c:if test="${sendVO.subjectGrade eq '3' }" > selected="selected"  </c:if>>3학년</option>
						<option value="4" <c:if test="${sendVO.subjectGrade eq '4' }" > selected="selected"  </c:if>>4학년</option>
					</select>
					<input type="text" name="subjectName" id="subjectName" value="${sendVO.subjectName}" placeholder="교과명 검색" onkeypress="press(event);" style="width:150px;" />
					<input type="text" name="companyName" id="companyName" value="${sendVO.companyName}" placeholder="기업명" onkeypress="press(event);"  style="width:150px;" />
					<input type="text" name="searchKeyword" id="searchKeyword" value="${sendVO.searchKeyword}" placeholder="학생명, 학번 검색" onkeypress="press(event);"  style="width:150px;" />
					<select name="adYear" id="adYear" >
						<option value="">입학년도</option>
							<c:forEach var="i" begin="0" end="2" step="1">
								<option value="${nowYear-i}" <c:if test="${sendVO.adYear eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}년도</option>
							</c:forEach>
					</select>
					<a href="#!" onclick="javascript:fn_search(1);">검색</a>
				</div><!-- E : search-box-1 -->

				<ul class="page-sum bg-none mb-007">
					<li class="float-right">
						PAGE VIEW : <input type="radio" name="pageSizeCnt" id="pageSizeCnt1" value="10" <c:if test="${pageSize eq '10' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('10');"> 10
						<input type="radio" name="pageSizeCnt"  id="pageSizeCnt2" value="20" <c:if test="${pageSize eq '20' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('20');"> 20
						<input type="radio" name="pageSizeCnt"  id="pageSizeCnt3" value="50" <c:if test="${pageSize eq '50' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('50');"> 50
						Lines
					</li>
				</ul>

				<div class="group-area mb-040">
					<table class="type-2">
					<colgroup>
						<col style="width:40px" />
						<col style="width:140px" />
						<col style="width:50px" />
						<col style="width:*" />
						<col style="width:170px" />
						<col style="width:110px" />
						<col style="width:90px" />
						<col style="width:110px" />
					</colgroup>
					<thead>
						<tr>
							<th><input type="checkbox" name="checkbox" id="checkbox" class="choice" /></th>
							<th>학과</th>
							<th>학년</th>
							<th>교과목명</th>
							<th>기업명</th>
							<th>학번</th>
							<th>입학년도</th>
							<th>이름</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="result" items="${resultList}" varStatus="status">
						<tr>
							<td><input type="checkbox" name="memSeqs" value="${result.memSeq}"  class="choice" /></td>
							<td>${result.deptName}</td>
							<td>${result.subjectGrade}</td>
							<td class="left">${result.subjectName}</td>
							<td>${result.companyName}</td>
							<td>${result.memId}</td>
							<td>${result.adYear}</td>
							<td>${result.memName}</td>
						</tr>
					</c:forEach>	
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="8"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>		
					</tbody>
				</table>

				<ul class="page-num-btn mt-015">
					<li class="page-num-area">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
					</li>
					<li class="page-btn-area">
					<!-- 임시주석 -->
							<!--
						<a href="" class="yellow">SMS 발송</a>
						<a href="" class="orange">E-MAIL 발송</a>-->
					</li>
				</ul><!-- E : page-num-btn -->
</form>

					</div><!-- E : content-area -->
	
	
	
	
					

	