<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/la/popup/popup/"/>

<style>
<!--
table.list-1 td {
/*  @import url(/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.css); */
 text-align: left;
 } 
 
 table.list-1 td.lt_text1	{text-align:center;}
 
}
-->
</style>

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
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goPopupCompanyList.do";
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
	//선택 버튼을 클릭시 부모창에 필요항목 셋팅
	function doSelectInfo(){
		if( opener ){
			var selectInfo = $("input:radio[name='strCheckItem']:checked").val();
			
			if(undefined == selectInfo){
				alert("추가할 회사명을 선택하여주십시오.");
				return false;
			}
			
			opener.setSelecItem1(selectInfo);
			window.close();
		}
	}

	// 설문제목을 선택시 부모창에 필요항목 셋팅
	function fn_select_subject(companyNameParm, companyIdParm){
		var companyName = "";
		var companyId = "";
		
		companyName = companyNameParm;
		companyId = companyIdParm;
		
		if(companyName == ''){
			alert("추가할 회사명을 선택하여주십시오.");
			return false;
		}
		if(companyId == ''){
			alert("추가할 회사명을 선택하여주십시오.");
			return false;
		}
			
		opener.setSelecItem2(companyName, companyId);
		window.close();
	}
	
</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmCompany" name="frmCompany" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	
<div id="popup-wrarpr">
<div id="popup-header">
	<h1><img src="/images/oklms/adm/inc/pop_border_02.png" />기업정보 검색</h1>
	<p><a href="#" onclick="parent.close();"></a></p>
</div><!-- E : p-header -->

<div id="popup-content-area">
<div id="popup-container">

<!-- 팝업 내용 영역 시작 : 가로 650px , 세로 560px -->

<div class="name-area" style="width:99%">
	<span>검색 조건</span> :
	<select name="searchName" id="searchName" title="검색조건선택" style="width: 150px;">
		<option value="">--전체--</option>
		<option ${param.searchName == 'NAME' ? 'selected="selected"' : ''} value='NAME'>이름</option>
			<option ${param.searchName == 'ID' ? 'selected="selected"' : ''} value='ID'>아이디</option>
	</select>
	</br> 
	<span>검색 단어</span> : <input name="searchWord" name="searchWord" type="text" size="35" value='<c:out value="${param.searchWord}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">   
	<a href="#fn_search()" onclick="javascript:fn_search(1); return false;" onkeypress="this.onclick;" class="btn">검색</a>
</div>

<table border="0" cellpadding="0" cellspacing="0" class="list-1">
				<thead>
					<tr>
						<th scope="col"  width="35px" nowrap>회사아이디</th>
						<th scope="col"  nowrap>회사명</th>
						<th scope="col"  width="50px" nowrap>업종</th>
						<th scope="col"  width="80px" nowrap>업태</th>
						<th scope="col"  width="80px" nowrap>업체대표</th>
						<th scope="col"  width="30px" nowrap>선택</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
					<td class="lt_text1" nowrap>
						<input type="hidden" id="companyId" name="companyId" value="${result.companyId}" />
						<c:out value="${result.companyId}" />
					</td>
					<td class="lt_text3L" nowrap>
					 	<a href="#LINK" onClick="fn_select_subject('${result.companyName}', '${result.companyId}')"><span color="blue">${result.companyName}</span></a>
					</td>
					<td class="lt_text1" nowrap>${result.businessType}</td>
					<td class="lt_text1" nowrap>${result.businessCondition}</td>
					<td class="lt_text1" nowrap>${result.ceo}</td>
					<td class="lt_text1"><input type="radio" name="strCheckItem" id="strCheckItem" value="${result.companyName}||${result.companyId}"></td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td class="lt_text1" colspan="6"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
				</tbody>
			</table><!-- E : list -->	

<%-- <table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="15%">회사아이디</th>
			<th width="30%">회사명</th>
			<th width="20%">업종</th>
			<th width="20%">업태</th>
			<th width="10%">업체대표</th>
			<th width="5%">선택</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<input type="hidden" id="companyId" name="companyId" value="${result.companyId}" />
			<td><c:out value="${result.companyId}" /></td>
			<td>
			<a href="#LINK" onClick="fn_select_subject('${result.companyName}', '${result.companyId}')"><span color="blue">${result.companyName}</span></a>
			</td>
			<td><c:out value="${result.businessType}" /></td>
			<td><c:out value="${result.businessCondition}" /></td>
			<td><c:out value="${result.ceo}" /></td>
			<td><input type="radio" name="strCheckItem" id="strCheckItem" value="${result.companyName}||${result.companyId}"></td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E : list --> --%>

<!-- 팝업 내용  영역 끝 -->
		<div class="page-btn">
			<a href="#doSelectInfo()" onclick="javascript:doSelectInfo();" onkeypress="this.onclick;">선택</a>
		</div><!-- E : page-btn -->

		<div class="page-num">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</div><!-- E : page-num -->

	</div><!-- E : p-contentiner -->
</div><!-- E : p-content-area -->

<div id="popup-footer"></div><!-- E : p-footer -->
</div><!-- E : p-wrapper -->
</form>
					

	