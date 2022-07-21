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
		}
	}
	
	 function fn_searchPageView(val) {
		$("#pageSize").val(val);
		 fn_search(pageIndex);
	}

	/* 리스트 조회 */
	function fn_search( pageIndex ){
		$("#pageIndex").val( pageIndex );
		var reqUrl = CONTEXT_ROOT + targetUrl + "listSubjectStd.do";
		$("#frmSubject").attr("action", reqUrl);
		$("#frmSubject").submit();
	}
	
	/* 탭이동 */
	function fn_searchTraningType( pageIndex ){
		$("#pageIndex").val( pageIndex );
		var reqUrl = CONTEXT_ROOT + targetUrl + "listSubjectStd.do";
		$("#frmMove").attr("action", reqUrl);
		$("#frmMove").submit();
	}
	
	
	/* 교과로 이동 */
	function fn_move_subject(subjectTraningType, year, term, subjectCode, subClass, subjectName, subjectType, onlineType,preSubjectCode){
		subjectName = encodeURIComponent(subjectName);
		if(preSubjectCode != ""){
			location.href = "/lu/currproc/listCurrProc.do?subjectTraningType="+subjectTraningType+"&year="+year+"&term="+term+"&subjectCode="+subjectCode+"&subjectName="+subjectName+"&subClass="+subClass+"&lecMenuMarkYn=Y&subjectType="+subjectType+"&onlineType="+onlineType+"&preSubjectCode="+preSubjectCode;
		} else {
			location.href = "/lu/currproc/listCurrProc.do?subjectTraningType="+subjectTraningType+"&year="+year+"&term="+term+"&subjectCode="+subjectCode+"&subjectName="+subjectName+"&subClass="+subClass+"&lecMenuMarkYn=Y&subjectType="+subjectType+"&onlineType="+onlineType;
		}
	}
	
</script>

			
<div id="content-area">
			<h2>훈련 개설교과 조회</h2>
			<!-- E : search-list-1 (검색조건 영역) -->
<form id="frmMove" name="frmMove" action="<c:url value='/lu/subject/listSubjectStd.do'/>" method="post">
	<input type="hidden" id="subjectTraningType" name="subjectTraningType" value="OFF" /> 
</form>				
			
<form id="frmSubject" name="frmSubject" action="<c:url value='/lu/subject/listSubjectStd.do'/>" method="post">
    <input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="subjectTraningType" name="subjectTraningType" value="OJT" /> 
		
			<div class="group-area mt-020">
							
				<div class="search-box-0"><input type="radio" name="searchPeriodType"  id="searchPeriodType1" value="TERM" onclick="fn_search(1);"  <c:if test="${subjectVO.searchPeriodType eq 'TERM' }" > checked="checked"  </c:if> class="choice" /> 학기단위로 보기&nbsp;&nbsp;&nbsp;<input type="radio" name="searchPeriodType"   id="searchPeriodType2" value="WEEK" onclick="fn_search(1);" <c:if test="${subjectVO.searchPeriodType eq 'WEEK' }" > checked="checked"  </c:if> class="choice" /> 주단위로 보기</div>
					<dl id="tab-type">
						
						<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">OJT</a></dt>
						<dd id="tab-content-11" style="display:block;">

							<div class="search-box-1 mt-020 mb-020">
								<input type="radio" name="pointUseYn" id="pointUseYn1" class="choice" value="Y" <c:if test="${subjectVO.pointUseYn eq 'Y' }" > checked="checked"  </c:if>   /> 학점형&nbsp;&nbsp;&nbsp;
								<input type="radio" name="pointUseYn" id="pointUseYn2" class="choice" value="N" <c:if test="${subjectVO.pointUseYn eq 'N' }" > checked="checked"  </c:if>  /> 비학점형&nbsp;&nbsp;&nbsp;
								<select name="yyyy" id="yyyy" > 
									<option value="" >학년도</option>
									<c:forEach var="i" begin="0" end="2" step="1">
										<option value="${nowYear-i}" <c:if test="${subjectVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
									</c:forEach>								
								</select> 
								<select name="term" id="term">
									<option value="" >학기</option>
									<option value="1" <c:if test="${subjectVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
									<option value="2" <c:if test="${subjectVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
									<option value="3" <c:if test="${subjectVO.term eq '3' }" > selected="selected"  </c:if>>여름학기</option>
									<option value="4" <c:if test="${subjectVO.term eq '4' }" > selected="selected"  </c:if>>겨울학기</option>
								</select>
								<select name="subjectGrade" id="subjectGrade">
									<option value="">대상학년</option>
									<option value="1" <c:if test="${subjectVO.subjectGrade eq '1' }" > selected="selected"  </c:if>>1학년</option>
									<option value="2" <c:if test="${subjectVO.subjectGrade eq '2' }" > selected="selected"  </c:if>>2학년</option>
									<option value="3" <c:if test="${subjectVO.subjectGrade eq '3' }" > selected="selected"  </c:if>>3학년</option>
									<option value="4" <c:if test="${subjectVO.subjectGrade eq '4' }" > selected="selected"  </c:if>>4학년</option>
								</select>
								<select name="subjectType" id="subjectType">
									<option value="">과정구분</option>
									<option value="NORMAL" <c:if test="${subjectVO.subjectType eq 'NORMAL' }" > selected="selected"  </c:if>>학부</option>
									<option value="HSKILL" <c:if test="${subjectVO.subjectType eq 'HSKILL' }" > selected="selected"  </c:if>>고숙련</option>
								</select>
								<input type="text" name="companyName" id="companyName" value="${subjectVO.companyName}" placeholder="기업명 검색" style="width:70px;" /><input type="text" name="subjectName" id="subjectName" value="${subjectVO.subjectName}" placeholder="교과명 검색" style="width:100px;" /><a href="#!" onclick="javascript:fn_search(1);">검색</a>
							</div><!-- E : search-box-1 -->
					
							<ul class="page-sum bg-none mb-007">
								<li class="float-right">
									PAGE VIEW : <input type="radio" name="pageSizeCnt" id="pageSizeCnt1" value="10" <c:if test="${pageSize eq '10' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('10');"> 10
									<input type="radio" name="pageSizeCnt"  id="pageSizeCnt2" value="20" <c:if test="${pageSize eq '20' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('20');"> 20
									<input type="radio" name="pageSizeCnt"  id="pageSizeCnt3" value="50" <c:if test="${pageSize eq '50' }" > checked="checked"  </c:if> onclick="javascript:fn_searchPageView('50');"> 50
									Lines
								</li>
							</ul>
							
							
							<table class="type-2">
								<colgroup>
									<col style="width:40px" />
									<col style="width:50px" />
									<col style="width:50px" />
									<col style="width:60px" />
									<col style="width:*" />
									<col style="width:50px" />
									<col style="width:120px" />
									<col style="width:90px" />
									<col style="width:60px" />
								</colgroup>
								<thead>
									<tr>
										<th>번호</th>
										<th>학년도</th>
										<th>학기</th>
										<th>대상학년</th>
										<th>개설교과명</th>
										<th>분반</th>
										<th>기업명</th>
										<th>기업현장교사</th>
										<th>과정구분</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="result" items="${resultList}" varStatus="status">
										<tr>
											<td><c:out value="${(subjectVO.pageIndex-1) * subjectVO.pageSize + status.count}"/></td>
											<td>${result.yyyy}</td>
											<td>${result.termName}</td>
											<td>${result.subjectGrade}학년</td>
											<td class="left"><a href="#fn_move_subject" onclick="fn_move_subject('${result.subjectTraningType}','${result.yyyy}','${result.term}','${result.subjectCode}','${result.subjectClass}','${result.subjectName}','${result.subjectType}','${result.onlineType}','${result.preSubjectCode}');" class="text">${result.subjectName }</a></td>
											<td>${result.subjectClass}</td>
											<td>${result.companyName}</td>
											<td>${result.tutNames}</td>
											<td>${result.subjectTypeName}</td>
											<!-- javascript:fn_lec_menu_display('OFF','2017','1','MAE700','01','기계제도및CAD','NORMAL','ONLINE'); -->
											
										</tr>
									</c:forEach>
									<c:if test="${fn:length(resultList) == 0}">
										<tr>
											<td colspan="9"><spring:message code="common.nodata.msg" /></td>
										</tr>
									</c:if>	
								</tbody>
							</table><!-- E : type-2 -->
							
							
							<div class="page-num mt-015">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
							</div><!-- E : page-num -->
							<dt class="tab-name-12"><a href="javascript:fn_searchTraningType();">Off-JT</a></dt>
							<dd id="tab-content-12"></dd>
							
							</dl>
						</div>


</form>
					</div><!-- E : content-area -->
	
	
	
	
					

	