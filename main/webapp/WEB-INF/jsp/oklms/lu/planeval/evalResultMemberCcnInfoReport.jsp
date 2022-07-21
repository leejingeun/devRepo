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
		returnMsg = '${returnMsg}';
		var cnt = '${cnt}';
		var seachYn = '${seachYn}';

		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			if(seachYn == 'N'){
				fn_showCompanypop();
			}
		}

		if(cnt == '0'){
			$("#styleDisplay1").show();
			$("#styleDisplay2").hide();
			$(".list-show").show();
			$(".list-hide").hide();
		} else {
			$("#styleDisplay1").hide();
			$("#styleDisplay2").show();
			$(".list-show").hide();
			$(".list-hide").hide();
		}
	}

	function press(event) {
		if (event.keyCode==13) {
			fn_search();
		}
	}

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo(param1){
		$("#searchKeyword").val('');
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMember.do";
		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	function fn_showCompanypop( ){
		$("#student-popup").show();
		$(".company-area,.popup-bg").addClass("open");
		window.location.hash = "#open";
	}

	function fn_closeCompanypop( ){
		$("#searchKeyword").val('');
		$("#student-popup").hide();
		$(".company-area,.popup-bg").removeClass("open");
	}

	function fn_hideCompanypop( ){
		$("#searchKeyword").val('');
		var obj = "";
		obj = $("input:radio[name='tempCompanyId']:checked").val();
		if(undefined == obj){
			alert("기업체를 선택하여주십시오. ")
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

			$("#companyId").val(companyId);

			fn_listMember( companyId );
		}
	}

	function fn_listMember( param1 ){

		$("#companyId").val(param1);
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	//회원정보 검색 목록 조회
	function fn_searchMember( ){

		if($("#companyId").val() == ''){
			alert("기업체를 검색하여 선택하여주십시오.");
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

		if($("#searchSubjectTraningType").val() == ''){
			alert("과정구분을 선택하여주십시오.");
			$("#searchSubjectTraningType").focus();
			return false;
		}

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	function fn_reportView( param1, param2 ){
		$("#memId").val( param1 );
		$("#memName").val( param2 );

		popOpenWindow("", "popSearch", 1050, 560);

		var reqUrl = "/lu/evalPlan/popupEvalResultMemberReportView.do";

		$("#frmEvalResult").attr("target", "popSearch");
		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").submit();
	}

	function fn_excelDownload( ){
		if($("#companyId").val() == ''){
			alert("기업체를 검색하여 선택하여주십시오.");
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

		if($("#searchSubjectTraningType").val() == ''){
			alert("과정구분을 선택하여주십시오.");
			$("#searchSubjectTraningType").focus();
			return false;
		}

		var reqUrl = CONTEXT_ROOT + targetUrl + "listEvalResultMemberExcelDownload.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	function fn_report( ){
		var memId = "";
		var memName = "";

		var selectInfo = $("input:radio[name='tmpMemId']:checked").val();

		if(undefined == selectInfo){
			alert("출력할 학습근로자를 선택하여주십시오.");
			return false;
		}

		var arrInfo = selectInfo.split("||");
		memId = arrInfo[0];
		memName = arrInfo[1];

		$("#memId").val(memId);
		$("#memName").val(memName);

		var reqUrl = CONTEXT_ROOT + targetUrl + "getEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

</script>

<form id="frmEvalResult" name="frmEvalResult" method="post">
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
	<li class="company-area" style="margin-left:-350px; margin-top:-119px;">
		<h1>기업체 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="기업명을 입력" value="${searchKeyword}" />
			<a href="#!" onclick="javascript:fn_search(1);">조회</a>
			<a href="#!" onclick="javascript:fn_searchKeywordNo(1);">전체 조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="10%" />
					<col width="*" />
					<col width="*" />
					<col width="*" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>기업명</th>
						<th>소재지</th>
						<th>선정일</th>
						<th>기업고용관리번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultCompanyList}" varStatus="status">
					<tr>
						<td><input type="radio" name="tempCompanyId" id="tempCompanyId" value="${result.companyId}||${result.companyName}||${result.address}||${result.choiceDay}||${result.traningProcessId}||${result.hrdTraningName}"></td>
						<td>${result.companyName}</td>
						<td>${result.address}</td>
						<td>${result.choiceDay}</td>
						<td>${result.employInsManageNo}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultCompanyList) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 기업체검색 -->

			<c:if test="${fn:length(resultCompanyList) != 0}">
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
	<h2>평가결과서</h2>

	<div class="group-area">
		<table class="type-2" id="styleDisplay1">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
			</colgroup>
			<thead>
				<tr>
					<th>기업명</th>
					<th>소재지</th>
					<th>선정일</th>
					<th>기업고용관리번호</th>
				</tr>
			</thead>
			<tbody>
				<tr class="list-hide" style="display:none">
					<td id="companyName"></td>
					<td id="address"></td>
					<td id="choiceDay"></td>
					<td id="employInsManageNo"></td>
				</tr>
				<tr class="list-show">
					<td colspan="4">자료가 없습니다. 기업체 검색버튼을 클릭하여 하나의 기업체를 선택해주세요</td>
				</tr>
			</tbody>
		</table>

		<table class="type-2" id="styleDisplay2" style="display:none">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
			</colgroup>
			<thead>
				<tr>
					<th>기업명</th>
					<th>소재지</th>
					<th>선정일</th>
					<th>기업고용관리번호</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultCompanyTraningProcessList}" varStatus="status">
				<tr>
					<td>${result.companyName}</td>
					<td>${result.address}</td>
					<td>${result.choiceDay}</td>
					<td>${result.employInsManageNo}</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultCompanyTraningProcessList) == 0}">
				<tr>
					<td colspan="4">자료가 없습니다. 기업체 검색버튼을 클릭하여 하나의 기업체를 선택해주세요</td>
				</tr>
				</c:if>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">기업체 검색</a>
		</div><!-- E : btn-area -->
	</div>

	<div class="search-box-1 mt-020">
		<select id="searchYyyy" name="searchYyyy" onchange="">
			<option value="" selected="selected">::학년도::</option>
			<c:forEach var="i" begin="0" end="5" step="1">
		    	<option value="${nowYear-i}" <c:if test="${planEvalVO.searchYyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
		    </c:forEach>
		</select>
		<select id="searchTerm" name="searchTerm" onchange="">
			<option value="" selected="selected">::학기::</option>
			<option value="1" <c:if test="${planEvalVO.searchTerm eq '1' }" > selected="selected"  </c:if>>1학기</option>
			<option value="2" <c:if test="${planEvalVO.searchTerm eq '2' }" > selected="selected"  </c:if>>2학기</option>
		</select>
		<select id="searchSubjectTraningType" name="searchSubjectTraningType" onchange="">
			<option value="" selected="selected">::과정구분::</option>
			<option value="OJT" <c:if test="${planEvalVO.searchSubjectTraningType eq 'OJT' }" > selected="selected"  </c:if>>OJT</option>
			<option value="OFF" <c:if test="${planEvalVO.searchSubjectTraningType eq 'OFF' }" > selected="selected"  </c:if>>OFF-JT</option>
		</select>
		<a href="#!" onclick="javascript:fn_searchMember();">조회</a>
	</div><!-- E : search-box-1 -->

	<table class="type-2 mt-020">
	<colgroup>
		<col style="width:40px" />
		<col style="width:40px" />
		<col style="width:100px" />
		<col style="width:100px" />
		<col style="width:150px" />
		<col style="width:*" />
		<col style="width:80px" />
		<col style="width:70px" />
	</colgroup>
	<thead>
		<tr>
			<th>선택</th>
			<th>번호</th>
			<th>학번</th>
			<th>성명</th>
			<th>기업명</th>
			<th>훈련과정명</th>
			<th>학적상태</th>
			<th>평가결과</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultMemberStdList}" varStatus="status">
		<tr>
			<td><input type="radio" id="tmpMemId" name="tmpMemId" value="${result.memId}||${result.memName}"></td>
			<td>${status.count}</td>
			<td>${result.memId}</td>
			<td>${result.memName}</td>
			<td>${result.companyName}</td>
			<td class="left">${result.hrdTraningName}</td>
			<td>${result.deleteName}<c:if test="${result.deleteYn eq 'Y'}"><br />(${result.deleteDt})</c:if></td>
			<td><a href="#!" onclick="javascript:fn_reportView('${result.memId}','${result.memName}');" class="btn-line-blue">조회</a></td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultMemberStdList) == 0}">
		<tr>
			<td colspan="8">자료가 없습니다.</td>
		</tr>
		</c:if>
	</tbody>
</table>

<div class="btn-area align-right mt-010">
	<a href="#!" onclick="javascript:fn_excelDownload(); return false" class="orange">엑셀 저장</a>
	<a href="#!" onclick="javascript:fn_report(); return false" class="gray-3">출력</a>
</div><!-- E : btn-area -->

</div><!-- E : content-area -->

<script type="text/javascript">
 $( function() {
	 $(".company-area").draggable();
  });

</script>
</form>

