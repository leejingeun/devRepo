<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%-- <script type="text/javascript" src="${contextRootJS }/js/jquery/plugins/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery.maskedinput.js"></script> --%>
<script type="text/javascript">

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
		var cnt = '${resultTraningProcessListCnt}';
		var tdOffJtTotalCnt = '${tdOffJtTotalCnt}';

		$("#companyId").val('${tempCompanyId}');
	//	$("#companyName").html('${tempCompanyName}');
		$("#address").html('${tempAddress}');
		$("#choiceDay").html('${tempChoiceDay}');
		$("#employInsManageNo").html('${tempEmployInsManageNo}');

		$("#tempCompanyName").val('${tempCompanyName}');
		$("#tempAddress").val('${tempAddress}');
		$("#tempChoiceDay").val('${tempChoiceDay}');
		$("#tempEmployInsManageNo").val('${tempEmployInsManageNo}');

		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			fn_showCompanypop();
		}
		
		if(cnt == '' || cnt == '0'){
			if(cnt == '0'){
				$(".list-show").hide();
				$(".list-hide").show();
			} else {
				$(".list-show").show();
				$(".list-hide").hide();
			}

			$("#styleDisplay1").hide();
			$("#styleDisplay2").hide();
		} else {
			$(".list-show").hide();
			$(".list-hide").show();
			$("#styleDisplay1").show();

			if(tdOffJtTotalCnt != '' && tdOffJtTotalCnt != '0'){
				$("#styleDisplay2").show();
			}
		}
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/
	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		//var reqUrl = "/lu/popup/goPopupSearch.do";
		var reqUrl = "/lu/traning/listTraningProcessManage.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		//var reqUrl = "/lu/popup/goPopupSearch.do";
		var reqUrl = "/lu/traning/listTraningProcessManage.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = "/lu/traning/listTraningProcessManage.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//훈련과정 추가 신규입력화면 이동
	function fn_goInsert(){

		if($("#companyId").val() == ''){
			alert("기업체 정보가 없습니다. 기업체 검색 버튼을 클릭하여 기업체를 선택하여주십시오.");
			return false;
		}

		var reqUrl = "/lu/traning/goInsertTraningProcessManageList.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//훈련과정관리에 메핑된 개설강좌 목록 조회
	function fn_detail(param1, param2){
		$("#companyId").val(param1);
		$("#traningProcessId").val(param2);
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = "/lu/traning/listTraningProcessManage.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//기업체검색후 기업체 훈련과정 메핑 목록 조회
	function fn_listTraningProcessManage( param1 ){

		$("#companyId").val(param1);
		$("#tempTraningProcessId").val('noDivPopup');
		$("#pageIndex").val("1");
		

		var reqUrl = "/lu/traning/listTraningProcessManage.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
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
			alert("훈련과정관리를 처리할 하나의 기업체를 선택하여주십시오. ")
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
			var companyName = arrInfo[1];
			var address = arrInfo[2];
			var choiceDay = arrInfo[3];
			var employInsManageNo = arrInfo[4];

			$("#companyId").val(companyId);
			$("#companyName").html(companyName);
			$("#address").html(address);
			$("#choiceDay").html(choiceDay);
			$("#employInsManageNo").html(employInsManageNo);

			//서버단에서 사용하기위해 Temp성 항목정의
			$("#tempCompanyName").val(companyName);
			$("#tempAddress").val(address);
			$("#tempChoiceDay").val(choiceDay);
			$("#tempEmployInsManageNo").val(employInsManageNo);
			//companyId 값이 있으면 훈련과정명 및 훈련과정번호 정보를 가지고올 Json 서버로직을 수행한다.
			if(companyId != ''){
				fn_listTraningProcessManage( companyId );
			} //companyId 비교 if문 End
		} //obj 훈련과정마스터에 메핑된 개설강좌 정보 if문 End
	}

	//훈련과정 마스터 및 개설강좌 메핑 데이타 삭제
	function fn_delete(){
		if (confirm("삭제 하시겠습니까?")) {

			var obj = "";
			obj = $("input:radio[name='traningProcessIdArr']:checked").val();
			if(undefined == obj){
				alert("삭제 처리할 훈련과정을 선택하여주십시오. ")
				return false;
			}

			var reqUrl = "/lu/traning/deleteTraningProcessManage.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").attr("target", "_self");
			$("#frmTraning").submit();
		}
	}

	//훈련과정 수정화면으로 이동
	function fn_goUpdate( ){

		var obj = "";
		obj = $("input:radio[name='traningProcessIdArr']:checked").val();
		if(undefined == obj){
			alert("수정 처리할 훈련과정을 선택하여주십시오. ")
			return false;
		}

		var reqUrl = "/lu/traning/goUpdateTraningProcessManageList.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//훈련과정 첫화면으로 이동
	function fn_goList( ){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = "/lu/traning/listTraningProcessManage.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}


</script>

<!-- 훈련과정관리용 끝 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="companyId" name="companyId"/>
<input type="hidden" id="traningProcessId" name="traningProcessId" />
<input type="hidden" id="uiGubun" name="uiGubun" value="traningProcessCompanyPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="/lu/traning/traningProcessManageList" />

<!-- Temp용 항목 -->
<input type="hidden" id="tempCompanyName" name="tempCompanyName"/>
<input type="hidden" id="tempAddress" name="tempAddress"/>
<input type="hidden" id="tempChoiceDay" name="tempChoiceDay"/>
<input type="hidden" id="tempEmployInsManageNo" name="tempEmployInsManageNo"/>
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>

<ul id="student-popup" style="display:none;">
	<li class="company-area" style="margin-left:-350px; margin-top:-119px;">
		<h1>기업체 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="기업명을 입력" value="${searchKeyword }" />
			<a href="#!" onclick="javascript:fn_search(1); return false">조회</a>
			<a href="#!" onclick="javascript:fn_searchKeywordNo(1); return false">전체조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="7%" />
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
						<td><input type="radio" name="tempCompanyId" id="tempCompanyId" value="${result.companyId}||${result.companyName}||${result.address}||${result.choiceDay}||${result.employInsManageNo}"></td>
						<td>${result.companyName}</td>
						<td class="left">${result.address}</td>
						<td>${result.choiceDay}</td>
						<td>${result.employInsManageNo}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultCompanyList) == 0}">
					<tr>
						<td colspan="5"><spring:message code="common.nodata.msg" /></td>
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
</ul> <!-- E : student-popup -->

	<div id="">
			<h2>훈련과정 관리</h2>
			<div class="group-area">
				<table class="type-2">
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
							<th>기업고용보험관리번호</th>
						</tr>
					</thead>
					<tbody>
						<tr class="list-hide" style="display:none">
							<td id="companyName">${tempCompanyName}</td>
							<td id="address"></td>
							<td id="choiceDay"></td>
							<td id="employInsManageNo"></td>
						</tr>
						<tr class="list-show">
							<td colspan="4">자료가 없습니다. 기업체 검색버튼을 클릭하여 하나의 기업체를 선택해주세요</td>
						</tr>
					</tbody>
				</table>

				<div class="btn-area align-right mt-010">
					<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">기업체 검색</a>
					<a href="#fn_goInsert" class="orange" onclick="javascript:fn_goInsert(); return false" onkeypress="this.onclick();">훈련과정 추가</a>
				</div><!-- E : btn-area -->
			</div>
	</div><!-- E : container -->

	<table id="styleDisplay1" class="type-2 mt-020" style="display:none">
		<colgroup>
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<thead>
		<tr>
			<th>훈련과정명</th>
			<th>훈련과정번호</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="result" items="${resultTraningProcessList}" varStatus="status">
		<tr>
			<td><a href="#fn_detail" onclick="javascript:fn_detail('${result.companyId}','${result.traningProcessId}')" class="text">${result.hrdTraningName}</a></td>
			<td>${result.hrdTraningNo}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>

	<div id="styleDisplay2" class="group-area mt-020" style="display:none">
	<table class="type-2">
		<colgroup>
			<col style="width:40px" />
			<col style="width:180px" />
			<col style="width:180px" />
			<col style="width:80px" />
			<col style="width:*" />
			<col style="width:60px" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
				<th>구분</th>
				<th>개설강좌명</th>
				<th>분반</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td rowspan="${(tdOffJtTotalCnt+tdOjtTotalCnt)}"><input type="radio" name="traningProcessIdArr" id="traningProcessIdArr" value="${offJtTraingOneVO.companyId},${offJtTraingOneVO.companyName},${offJtTraingOneVO.traningProcessId}"></td>
				<td rowspan="${(tdOffJtTotalCnt+tdOjtTotalCnt)}">${offJtTraingOneVO.hrdTraningName}</td>
				<td rowspan="${(tdOffJtTotalCnt+tdOjtTotalCnt)}">${offJtTraingOneVO.hrdTraningNo}</td>
				<c:forEach var="result" items="${resultOffjtSubjectInfoList}" varStatus="status">
				<c:if test="${status.count == 1}">
					<td rowspan="${tdOffJtTotalCnt}">Off-JT</td>
					<td class="left">${result.subjectName}</td>
					<td>${result.subClass}</td> 
				</tr>
				</c:if>
				<c:if test="${status.count != 1}">
				<tr>
					<td class="left">${result.subjectName}</td>
					<td>${result.subClass}</td>
				</tr>
				</c:if>
				</c:forEach>
			<c:forEach var="result" items="${resultOjtSubjectInfoList}" varStatus="status">
			<c:if test="${status.count == 1}">
			<tr>
				<td rowspan="${tdOjtTotalCnt}">OJT</td>
				<td class="left">${result.subjectName}</td>
				<td>${result.subClass}</td>
			</tr>
			</c:if>
			<c:if test="${status.count != 1}">
			<tr>
				<td class="left">${result.subjectName}</td>
				<td>${result.subClass}</td>
			</tr>
			</c:if>
			</c:forEach>
		</tbody>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#fn_goList" class="gray-1 float-left" onclick="javascript:fn_goList();">목록</a>
		<a href="#fn_delete" class="gray-1" onclick="javascript:fn_delete();">삭제</a>
		<a href="#fn_goUpdate" class="yellow" onclick="javascript:fn_goUpdate();">수정</a>
	</div><!-- E : btn-area -->
</div><!-- E : 훈련과정명 -->

<script type="text/javascript">
 $( function() {
	 $(".company-area").draggable();
  });
 
</script>

</form>


