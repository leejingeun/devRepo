<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style type="text/css"></style>

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
	}

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
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
	
		var reqUrl = "/lu/company/listCompany.do";
		//alert($("#pageIndex").val());
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}

	/* 상세조회 페이지로 이동 */
	function fn_read( param1 ){
		
		$("#companyId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateCompany.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
	/* 수정 페이지로 이동 */
	function fn_updt(  ){
		
		var checkedVal = $(":input:radio[name=companyIdSelect]:checked").val();
		
		if(undefined == checkedVal){
			alert("수정할 기업명에 라디오버튼을 선택하여주십시오.");
			return false
		}
		
		$("#companyId").val( checkedVal );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateCompany.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
	/* 삭제 */
	function fn_delt(){
		if (confirm("삭제 하시겠습니까?")) {
			var checkedVal = $(":input:radio[name=companyIdSelect]:checked").val();
			
			if(undefined == checkedVal){
				alert("삭제할 기업명에 라디오버튼을 선택하여주십시오.");
				return false
			}
			
			$("#companyId").val( checkedVal );
			
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteCompany.do";
			
			$("#frmCompany").attr("action", reqUrl);
			$("#frmCompany").submit();
		}
	}
	
	/* 신규 페이지로 이동 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertCompany.do";
		
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
</script>
	
	
<div id="">
	<h2>기본정보 관리</h2>
	
<form id="frmCompany" name="frmCompany" action="<c:url value='/lu/Company/Company/listCompany.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="companyId" name="companyId" />
	
	<div class="search-box-1">
		<input type="text" id="searchCompanyName" name="searchCompanyName" placeholder="(ex)기업명" value=""/>
		<input type="text" id="searchEmployInsManageNo" name="searchEmployInsManageNo" placeholder="(ex)기업고용보험관리번호" value=""/>
		<a href="#fn_search" onclick="javascript:fn_search(1); return false" >검색</a>
		
	
	</div><!-- E : search-box-1 -->
	
	
	<div class="btn-area align-right mt-010">
		<a href="#fn_cret" class="yellow" onclick="javascript:fn_cret(); return false" onkeypress="this.onclick();">신규등록</a>
	</div><!-- E : btn-area -->
	
	<table class="type-2 mt-020">
		<colgroup>
			<col style="width:50px" />
			<col style="width:100" />
			<col style="width:100" />
			<col style="width:*" />
			<col style="width:100px" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>기업명</th>
				<th>기업고용보험관리번호 (사업자등록번호)</th>
				<th>소재지</th>
				<th>선정일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<c:if test="${status.count eq '1'}">
					<td><input type="radio" name="companyIdSelect" value="${result.companyId}" class="choice" checked="checked"></td>
				</c:if>
				<c:if test="${status.count != '1'}">
					<td><input type="radio" name="companyIdSelect" value="${result.companyId}" class="choice"></td>
				</c:if>
				<%-- <td><a href="#fn_read" onclick="javascript:fn_read('${result.companyId}')" class="text">${result.companyName}</a></td> --%>
				<td>${result.companyName}</td>
				<td>${result.employInsManageNo} (${result.companyNo})</td>
				<td class="left">${result.address}</td>
				<td>${result.choiceDay}</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="5"><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table>
	
 	<ul class="page-num-btn mt-015">
		<li class="page-num-area">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</li>
		<li class="page-btn-area">
			<a href="#fn_delt" class="gray-1" onclick="javascript:fn_delt(); return false" onkeypress="this.onclick();">삭제</a>
			<a href="#fn_updt" class="yellow" onclick="javascript:fn_updt(); return false" onkeypress="this.onclick();">수정</a>
		</li>
	</ul><!-- E : page-num-btn -->
	
</form>

</div><!-- E : content-area -->
	
