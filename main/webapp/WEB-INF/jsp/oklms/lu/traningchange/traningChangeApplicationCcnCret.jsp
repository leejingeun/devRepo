<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

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
		var subjctTitle = "";
		var searchYyyyCodeId = "";
		var retunReason = "";
		var count = 0;

		returnMsg = '${returnMsg}';
		searchYyyyCodeId = '${searchYyyyCodeId}';
		count = '${resultTraningChangeReadListCount}';
		subjctTitle = '${traningChangeReadVO.subjctTitle}';
		retunReason = '${traningChangeReadVO.retunReason}';

		$("#count").val(count);

		/* alert("count="+count);
		alert("subjctTitle="+subjctTitle); */

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

		if(subjctTitle == ''){
			$("#displaly1").hide();
		}else{
			$("#displaly1").show();
		}

		//신규 변경신청 목록에 달력 표시
		<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="scriptStatus1">
			com.datepickerDateFormat('changeTraningDate-'+'${scriptStatus1.count}');
		</c:forEach>
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplication.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
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

		if($("#searchSubjectCode").val() == ''){
			alert("개설강좌명을 선택하여주십시오.");
			$("#searchSubjectCode").focus();
			return false;
		}

		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
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
			alert("훈련시간표 변경신청등록을 처리할 하나의 기업체를 선택하여주십시오. ");
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

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/goInsertTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//첫화면으로 이동
	function fn_goList( ){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/listTraningChangeScheduleApplication.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//변경 신청건 저장
	function fn_formSave(){

		if (confirm("변경신청한 입력값대로 변경 하시겠습니까?")) {
			if($("#changeReason").val() == ""){
				$("#changeReason").focus();
				alert("훈련시간표 변경사유는 필수입력사항입니다.");
				return false;
			}

			<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="scriptStatus2">
				if($("#changeTraningDate-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningDate-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 일자가 비어있습니다.");
					return false;
				}
				if($("#changeTraningStHour-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningStHour-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 시작시간이 비어있습니다.");
					return false;
				}
				if($("#changeTraningEdHour-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningEdHour-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 종료시간이 비어있습니다.");
					return false;
				}
				if($("#changeLeadTime-"+${scriptStatus2.count}).val() == ""){
					$("#changeLeadTime-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 소요시간이 비어있습니다.");
					return false;
				}
			</c:forEach>

			var reqUrl = CONTEXT_ROOT + "/lu/traningChange/updateTraningChangeScheduleApplication.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").submit();
		}
	}

</script>

<!-- 훈련시간표 변경신청등록 관련 항목 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="companyId" name="companyId" value="${traningSchVO.companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningSchVO.traningProcessId}" />
<input type="hidden" id="yyyy" name="yyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${currProcReadVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${currProcReadVO.subClass}" />
<input type="hidden" id="updateStatusYn" name="updateStatusYn" value="Y" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="uiGubun" name="uiGubun" value="traningProcessCompanyPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="traningChangeApplicationCcnCret" />
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>

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
	<h2>훈련시간표 변경신청 </h2>

	<div id="displaly2">
	<table class="type-1-1">
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
	</div>

	<div id="displaly3">
	<table class="type-1-1">
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
		<c:forEach var="result" items="${resultCompanyTraningProcessList}" varStatus="status">
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
	</div>

	<div class="btn-area align-right mt-010">
		<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">기업체 검색</a>
	</div><!-- E : btn-area -->

	<div class="search-box-1 mt-040 ">
		학년도:
		<select id="searchYyyy" name="searchYyyy" onchange="">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="yearSubjCd" items="${yearSubjCode}" varStatus="status">
				<option value="${yearSubjCd.codeId}" ${yearSubjCd.codeId == traningSchVO.searchYyyy ? 'selected="selected"' : '' }>${yearSubjCd.codeName} 학년도</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		학기:
		<select id="searchTerm" name="searchTerm" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="termSubjCd" items="${termSubjCode}" varStatus="status">
				<option value="${termSubjCd.codeId}" ${termSubjCd.codeId == traningSchVO.searchTerm ? 'selected="selected"' : '' }>${termSubjCd.codeName} 학기</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		분반:
		<select id="searchSubClass" name="searchSubClass" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="classSubjCd" items="${classSubjCode}" varStatus="status">
				<option value="${classSubjCd.codeId}" ${classSubjCd.codeId == traningSchVO.searchSubClass ? 'selected="selected"' : '' }>${classSubjCd.codeName} 분반</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		개설강좌명:
		<select id="searchSubjectCode" name="searchSubjectCode" onchange="">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="subjNameSubjCd" items="${subjNameSubjCode}" varStatus="status">
				<option value="${subjNameSubjCd.codeId}" ${subjNameSubjCd.codeId == traningSchVO.searchSubjectCode ? 'selected="selected"' : '' }>${subjNameSubjCd.codeName}</option>
			</c:forEach>
		</select>
		<a href="#fn_goSearch" onclick="javascript:fn_goSearch(); return false" onkeypress="this.onclick();">조회</a>
	</div><!-- E : search-box-1 -->


	<div id="displaly1" style="display:none;">

	<h4 class="mt-020"><span>${currProcReadVO.subjectName}</span> (${currProcReadVO.subClass}반) ㅣ ${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</h4>

	<div class="group-area mt-010">
		<table class="type-write">
			<colgroup>
				<col style="width:150px" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th>주요교과목 및 내용</th>
				<td class="left">
					${traningChangeReadVO.subjctTitle}
					<input type="hidden" id="weekId" name="weekId" value="${traningChangeReadVO.weekId}" />
				</td>
			</tr>
			<tr>
				<th>변경사유</th>
				<td class="left">
					<c:if test="${empty traningChangeReadVO.changeReason}">
						<input type="text" id="changeReason" name="changeReason" maxlength="298" placeholder="(ex)훈련시간표 변경사유 입력" style="width:97%;" />
					</c:if>
					<c:if test="${!empty traningChangeReadVO.changeReason}">
						${traningChangeReadVO.changeReason}
					</c:if>
				</td>
			</tr>
			<tr>
				<th>변경자</th>
				<td class="left">${traningSchVO.sessionMemName}</td>
			</tr>
			<tr>
				<th>변경승인상태</th>
				<td class="bg-highlight">
					<c:if test="${traningChangeReadVO.changeStatus eq '1'}"><font color="blue">승인대기</font></c:if>
					<c:if test="${traningChangeReadVO.changeStatus eq '2'}"><font color="red">승인</font></c:if>
					<c:if test="${traningChangeReadVO.changeStatus eq '3'}"><font color="red">반려</font></c:if>
				</td>
			</tr>
			<c:if test="${traningChangeReadVO.changeStatus eq '3'}">
			<tr>
				<th>반려사유</th>
				<td class="left">${traningChangeReadVO.retunReason}</td>
			</tr>
			</c:if>
		</table>

<c:if test="${resultTraningChangeReadList[0].dayOfWeekChange eq null}">
	<div class="group-area mt-010">
		<h2>신규 변경신청</h2>
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<%-- <col style="width:*" /> --%>
				<col style="width:110px" />
				<col style="width:105px" />
				<col style="width:40px" />
				<col style="width:145px" />
				<col style="width:60px" />
				<col style="width:40px" />
				<%-- <col style="width:100px" /> --%>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">주차</th>
					<!-- <th rowspan="2">능력단위요소</th> -->
					<th colspan="3">변경 전</th>
					<th colspan="3">변경 후 (신청내용)</th>
					<!-- <th rowspan="2">추가 / 삭제</th> -->
				</tr>
				<tr>
					<th class="border-left">일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
					<th>일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="status">
				<tr>
					<td>${result.weekCnt}</td>
					<%-- <td class="left">${result.ncsElemName}</td> --%>
					<td>${result.traningDate} (${result.dayOfWeek})</td>
					<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
					<td>${result.leadTime}</td>
					<td><input type="text" id="changeTraningDate-${status.count}" name="changeTraningDate-${status.count}" placeholder="(ex)2017.04.01" value="" style="width:85px;" readonly="readonly"/></td>
					<td>
						<select id="changeTraningStHour-${status.count}" name="changeTraningStHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">시작시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}">${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
						<select id="changeTraningEdHour-${status.count}" name="changeTraningEdHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">종료시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}" >${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="number" min="0" max="10" name="changeLeadTime-${status.count}" id="changeLeadTime-${status.count}" class="arrEvalScore" onchange="javascript:com.checkNumber(document.getElementById('changeLeadTime-${status.count}'));" style="width:40px; text-align:center;" />
						<input type="hidden" id="timeId-${status.count}" name="timeId-${status.count}" value="${result.timeId}" />
					</td>
					<!-- <td><a href="#!" class="btn-full-blue">추가</a> <a href="#!" class="btn-full-gray">삭제</a></td> -->
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_formSave" onclick="javascript:fn_formSave();" class="orange" >변경신청</a>
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="gray-1" >취소</a>
		</div>
	</div><!-- E : 훈련일정변경 -->
</c:if>

<c:if test="${resultTraningChangeReadList[0].dayOfWeekChange != null}">
<div class="group-area mt-010">
		<h2>신규 변경신청</h2>
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<%-- <col style="width:*" /> --%>
				<col style="width:110px" />
				<col style="width:105px" />
				<col style="width:40px" />
				<col style="width:145px" />
				<col style="width:60px" />
				<col style="width:40px" />
				<%-- <col style="width:100px" /> --%>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">주차</th>
					<!-- <th rowspan="2">능력단위요소</th> -->
					<th colspan="3">변경 전</th>
					<th colspan="3">변경 후 (신청내용)</th>
					<!-- <th rowspan="2">추가 / 삭제</th> -->
				</tr>
				<tr>
					<th class="border-left">일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
					<th>일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="status">
				<tr>
					<td>${result.weekCnt}</td>
					<%-- <td class="left">${result.ncsElemName}</td> --%>
					<td>${result.traningDate} (${result.dayOfWeek})</td>
					<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
					<td>${result.leadTime}</td>
					<td>${result.changeTraningDate} (${result.dayOfWeekChange})</td>
					<td>${result.changeTraningStHour}:00 ~ ${result.changeTraningEdHour}:00</td>
					<td>${result.changeLeadTime}</td>
					<!-- <td><a href="#!" class="btn-full-blue">추가</a> <a href="#!" class="btn-full-gray">삭제</a></td> -->
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="gray-1" >취소</a>
		</div>
	</div><!-- E : 훈련일정변경 -->
</c:if>

	</div>

	</div>

</div><!-- E : content-area -->

</form>


