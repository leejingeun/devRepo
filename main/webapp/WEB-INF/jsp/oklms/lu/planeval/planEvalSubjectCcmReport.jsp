<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

<c:set var="targetUrl" value="/lu/evalPlan/"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
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

		//기업체 검색 팝업 조회 성공여부
		var returnMsg = "";
		var subjectName = "";
		var searchYyyyCodeId = "";
		var cnt = "";

		returnMsg = '${returnMsg}';
		searchYyyyCodeId = '${searchYyyyCodeId}';
		cnt = '${cnt}';

		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			fn_showCompanypop();
		}

		if(searchYyyyCodeId == ''){
			$("#displaly2").show();
			$("#displaly3").hide();
		} else {
			$("#displaly2").hide();
			$("#displaly3").show();
		}

	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").submit();
	}

	//개설강좌 조회
	function fn_goSearch(){

		if($("#companyId").val() == ''){
			alert("기업체 정보가 없습니다. 기업체 검색 버튼을 클릭하여 기업체를 선택하여주십시오.");
			return false;
		}

		if($("#searchYyyy").val() == ''){
			alert("학년도를 선택하여주십시오.");
			$("#searchYyyy").focus();
			return false;
		}

		if($("#searchTerm").val() == ''){
			alert("학기를 선택하여주십시오.");
			$("#searchTerm").focus();
			return false;
		}

		if($("#searchSubClass").val() == ''){
			alert("분반을 선택하여주십시오.");
			$("#searchSubClass").focus();
			return false;
		}

		if($("#searchSubjectTraningType").val() == ''){
			alert("과정구분을 선택하여주십시오.");
			$("#searchSubjectTraningType").focus();
			return false;
		}

		if($("#searchSubjectCode").val() == ''){
			alert("개설강좌명을 선택하여주십시오.");
			$("#searchSubjectCode").focus();
			return false;
		}

		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	function fn_showCompanypop( ){
		$("#student-popup").show();
		$(".company-area,.popup-bg").addClass("open");
		window.location.hash = "#open";
	}

	function fn_closeCompanypop( ){
		$("#student-popup").hide();
		$(".company-area,.popup-bg").removeClass("open");
	}

	function fn_hideCompanypop( ){
		var obj = "";
		obj = $("input:radio[name='tempCompanyId']:checked").val();
		if(undefined == obj){
			alert("하나의 기업체를 선택하여주십시오. ")
			return false;
		} else {
			$(".company-area,.popup-bg").removeClass("open");
			fn_setCompanypopInfo(obj);
		}
	}

	// 기업체 정보 셋팅
	function fn_setCompanypopInfo(obj){
		if( obj ){
			var arrInfo = obj.split("||");
			var companyId = arrInfo[0];
			/* var companyName = arrInfo[1];
			var address = arrInfo[2];
			var choiceDay = arrInfo[3];
			var employInsManageNo = arrInfo[4]; */
			var traningProcessId = arrInfo[5];
			//var hrdTraningName = arrInfo[6];

			$("#companyId").val(companyId);
			$("#traningProcessId").val(traningProcessId);
			/* $("#companyName").html(companyName);
			$("#address").html(address);
			$("#choiceDay").html(choiceDay);
			$("#employInsManageNo").html(employInsManageNo);
			$("#hrdTraningName").html(hrdTraningName); */

			/* $("#displaly2").hide();
			$("#displaly3").show(); */

			fn_goSearchList( companyId, traningProcessId );

		} //obj 훈련시간표등록 목록 if문 End
	}

	//기업체 검색후 기업체명 한건을 선택시
	function fn_goSearchList( param1, param2 ){
		$("#companyId").val(param1);
		$("#traningProcessId").val(param2);
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	//탭버튼 클릭시
	function fn_showTabbtn( param1 ){

/* 		var companyId = "";
		companyId = $("#companyId").val();
		if(companyId == ''){
			alert("기업체 정보가 없습니다. 기업체검색버튼을 클릭하여 기업체 선택후 탭버튼을 클릭해주세요.");
			return false;
		} */

		doActionShowTabbtn( param1 );

		$("#tabPageGubun").val(param1);
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	//탭버튼 활성화
	function doActionShowTabbtn( param1 ){
		if("01" == param1){
			$(".tab-name-11").addClass('on');
			$(".tab-name-12").removeClass('on');

			$("#tab-content-11").css('display','block');
			$("#tab-content-12").css('display','none');
		} else {
			$(".tab-name-12").addClass('on');
			$(".tab-name-11").removeClass('on');

			$("#tab-content-12").css('display','block');
			$("#tab-content-11").css('display','none');
		}
	}

	function fn_report( ){
		var yyyy = "";
		var term = "";
		var subjectCode = "";
		var subClass = "";

		var selectInfo = $("input:radio[name='tmpSubjectCode']:checked").val();

		if(undefined == selectInfo){
			alert("출력할 개설교과정보를 선택하여주십시오.");
			return false;
		}

		var arrInfo = selectInfo.split("||");
		yyyy = arrInfo[0];
		term = arrInfo[1];
		term = "10"+term;
		subjectCode = arrInfo[2];
		subClass = arrInfo[3];

		$("#yyyy").val(yyyy);
		$("#term").val(term);
		$("#subjectCode").val(subjectCode);
		$("#subClass").val(subClass);

		var reqUrl = CONTEXT_ROOT + targetUrl + "getNcsEvalPlanReportView.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

</script>

<form id="frmEvalPlan" name="frmEvalPlan" method="post">
<input type="hidden" id="tabPageGubun" name="tabPageGubun" value="01" />
<input type="hidden" id="reportGubun" name="reportGubun" value="SUBJECT" />
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="yyyy" name="yyyy" value="${planEvalVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${planEvalVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${planEvalVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${planEvalVO.subClass}" />
<input type="hidden" name="companyId" id="companyId" value="${planEvalVO.companyId}" />
<input type="hidden" name="traningProcessId" id="traningProcessId" value="${planEvalVO.traningProcessId}" />
<input type="hidden" id="memSeq" name="memSeq" />
<input type="hidden" id="memId" name="memId" />
<input type="hidden" id="memName" name="memName" />
<input type="hidden" id="memSeqArr" name="memSeqArr" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />


<ul id="student-popup" style="display:none;">
	<li class="company-area" style="margin-left:-350px; margin-top:-139px;">
		<h1>기업체 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="기업명을 입력" value="${searchKeyword }" />
			<a href="#!" onclick="javascript:fn_search(1); return false" onkeypress="this.onclick();">조회</a>
			<a href="#!" onclick="javascript:fn_searchKeywordNo(1); return false" onkeypress="this.onclick();">전체조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="7%" />
					<col width="*" />
					<%-- <col width="*" /> --%>
					<col width="*" />
					<col width="*" />
					<col width="*" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>기업명</th>
						<!-- <th>소재지</th> -->
						<th>선정일</th>
						<th>기업고용관리번호</th>
						<th>훈련과정명</th>
						<th>훈련과정번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultSearchList}" varStatus="status">
					<tr>
						<td><input type="radio" name="tempCompanyId" id="tempCompanyId" value="${result.companyId}||${result.companyName}||${result.address}||${result.choiceDay}||${result.employInsManageNo}||${result.traningProcessId}||${result.hrdTraningName}"></td>
						<td>${result.companyName}</td>
						<%-- <td class="left">${result.address}</td> --%>
						<td>${result.choiceDay}</td>
						<td>${result.employInsManageNo}</td>
						<td>${result.hrdTraningName}</td>
						<td>${result.hrdTraningNo}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultSearchList) == 0}">
					<tr>
						<td colspan="7"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 기업체검색 -->

			<c:if test="${fn:length(resultSearchList) != 0}">
			<div class="page-num mt-015">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_searchPaging" />
			</div>
			</c:if>

		<div class="btn-area align-center mt-010">
			<a href="#fn_closeCompanypop" class="yellow close" onclick="javascript:fn_closeCompanypop(); return false" onkeypress="this.onclick();">닫기</a>
			<a href="#fn_hideCompanypop" class="orange close" onclick="javascript:fn_hideCompanypop(); return false" onkeypress="this.onclick();">확인</a>
		</div><!-- E : btn-area -->
	</li>

	<li class="popup-bg"></li>
</ul><!-- E : student-popup -->

<div id="">
	<h2>직무수행능력평가</h2>

	<div class="gropu-area">
		<table class="type-2" id="displaly2">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th>기업명</th>
				<th>소재지</th>
				<th>선정일</th>
				<th>기업고용보험관리번호</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
			</tr>
			<tr>
				<td colspan="6">자료가 없습니다. 기업체 검색버튼을 클릭하여 하나의 기업체를 선택해주세요</td>
			</tr>
		</table>

		<table class="type-2" id="displaly3">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th>기업명</th>
				<th>소재지</th>
				<th>선정일</th>
				<th>기업고용보험관리번호</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td>${result.companyName}</td>
				<td>${result.address}</td>
				<td>${result.choiceDay}</td>
				<td>${result.employInsManageNo}</td>
				<td>${result.hrdTraningName}</td>
				<td>${result.hrdTraningNo}</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			</c:if>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">훈련과정 검색</a>
		</div><!-- E : btn-area -->
	</div>

	<dl id="tab-type">
		<dt class="tab-name-11 on"><a href="#!" onclick="javascript:fn_showTabbtn('01'); return false">개설교과별</a></dt>
		<dd id="tab-content-11" style="display:block;">

			<div class="search-box-1 mt-020">
				학년도:
				<select id="searchYyyy" name="searchYyyy" onchange="">
					<option value="" selected="selected">::선택::</option>
					<c:forEach var="yearSubjCd" items="${yearSubjCode}" varStatus="status">
						<option value="${yearSubjCd.codeId}" ${yearSubjCd.codeId == planEvalVO.searchYyyy ? 'selected="selected"' : '' }>${yearSubjCd.codeName} 학년도</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				학기:
				<select id="searchTerm" name="searchTerm" onchange="">
				<option value="" selected="selected">::선택::</option>
					<c:forEach var="termSubjCd" items="${termSubjCode}" varStatus="status">
						<option value="${termSubjCd.codeId}" ${termSubjCd.codeId == planEvalVO.searchTerm ? 'selected="selected"' : '' }>${termSubjCd.codeName} 학기</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				분반:
				<select id="searchSubClass" name="searchSubClass" onchange="">
				<option value="" selected="selected">::선택::</option>
					<c:forEach var="classSubjCd" items="${classSubjCode}" varStatus="status">
						<option value="${classSubjCd.codeId}" ${classSubjCd.codeId == planEvalVO.searchSubClass ? 'selected="selected"' : '' }>${classSubjCd.codeName} 분반</option>
					</c:forEach>
				</select>&nbsp;&nbsp;
				과정구분:
				<select id="searchSubjectTraningType" name="searchSubjectTraningType" onchange="">
					<option value="" selected="selected">::선택::</option>
					<option value="OFF" ${'OFF' == planEvalVO.searchSubjectTraningType ? 'selected="selected"' : '' }>OFF-JT</option>
					<option value="OJT" ${'OJT' == planEvalVO.searchSubjectTraningType ? 'selected="selected"' : '' }>OJT</option>
				</select>&nbsp;&nbsp;
				개설강좌명:
				<select id="searchSubjectCode" name="searchSubjectCode" onchange="">
					<option value="" selected="selected">::선택::</option>
					<c:forEach var="subjNameSubjCd" items="${subjNameSubjCode}" varStatus="status">
						<option value="${subjNameSubjCd.codeId}" ${subjNameSubjCd.codeId == planEvalVO.searchSubjectCode ? 'selected="selected"' : '' }>${subjNameSubjCd.codeName}</option>
					</c:forEach>
				</select>
				<a href="#fn_goSearch" onclick="javascript:fn_goSearch(); return false" onkeypress="this.onclick();">조회</a>
			</div><!-- E : search-box-1 -->

			<table class="type-2 mt-010">
				<colgroup>
					<col style="width:50px" />
					<col style="width:80px" />
					<col style="width:100px" />
					<col style="width:100px" />
					<col style="width:*" />
					<col style="width:80px" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>구분</th>
						<th>학년도</th>
						<th>학기</th>
						<th>개설교과명</th>
						<th>분반</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result1" items="${resultSubjectRebortList}" varStatus="status">
					<tr>
						<td><input type="radio" id="tmpSubjectCode" name="tmpSubjectCode" value="${result1.yyyy}||${result1.term}||${result1.subjectCode}||${result1.subClass}"></td>
						<td>${result1.subjectTraningType}</td>
						<td>${result1.yyyy}</td>
						<td>${result1.term}</td>
						<td class="left">${result1.subjectName}</td>
						<td>${result1.subClass}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultSubjectRebortList) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>

			<div class="btn-area align-right mt-010">
				<!-- 최선호과장요청으로 주석처리함. -->
				<!-- <a href="#!" onclick="javascript:fn_report(); return false" class="gray-3">직무수행능력평가결과 (교과목별) 출력</a> -->
			</div><!-- E : btn-area -->
		</dd><!-- E : 평가결과조회 -->

		<dt class="tab-name-12"><a href="#!" onclick="javascript:fn_showTabbtn('02'); return false">학습근로자별</a></dt>
		<dd id="tab-content-12"></dd>
	</dl>

</div><!-- E : content-area -->


<script type="text/javascript">
 $( function() {
	 $(".company-area").draggable();
  });

</script>
</form>

