<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<c:set var="targetUrl" value="/la/popmng/" />
<script type="text/javascript" src="${contextRootJS }/js/oklms/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="${contextRootJS }/js/oklms/popupApi.js"></script>
<script type="text/javaScript" language="javascript">
var jsonObj = eval(${lms:objectToJson(resultList)});
var popupList = jsonObj;
PopupOpenerAPI.dataList = popupList;
PopupOpenerAPI.contextPath = "${pageContext.request.contextPath}";

	/* ********************************************************
	 * 페이징 처리 함수
	 ******************************************************* */
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

		
		var dt = new Date();
		var month_bef = dt.getMonth();
		var month = dt.getMonth()+1;
		var day = dt.getDate();
		var year = dt.getFullYear();
		if("${param.startDate}"==""){
		$("#startDate").val(year+'-' + month_bef + '-' + day);
		$("#finishDate").val(year+'-' + month + '-' + day);
		}
		loadPage();
		
		// 미리보기 버튼 이벤트 기능
		$("a.btn-popup-open").bind("click", function() {

			var popupId = $(this).attr("data-popup-id");
			
			for (var i=0; i < PopupOpenerAPI.dataList.length; i++) {
				var popup = PopupOpenerAPI.dataList[i];

				if (popup.popupId == popupId) {
					PopupOpenerAPI.open(popup, false);
					break;
				}
			}

		});
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

		//     com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);

		com.datepicker('startDate', 'button');
		com.datepicker('finishDate', 'button');
	}

	/*====================================================================
	 사용자 정의 함수 
	 ====================================================================*/

	function press(event) {
		if (event.keyCode == 13) {
			fn_search('1');
		}
	}
	
	function fn_popup_detail(popupId){
		$("#popupId").val(popupId);
		$("#pageIndex").val(pageIndex);
		var reqUrl = CONTEXT_ROOT + targetUrl + "detailPopup.do";
		$("#frmPopmng").attr("action", reqUrl);
		$("#frmPopmng").submit();
		
		
	}

	/* 리스트 조회 */
	function fn_search(param1) {

		$("#pageIndex").val(param1);
		var reqUrl = CONTEXT_ROOT + targetUrl + "listPopup.do";
		$("#frmPopmng").attr("action", reqUrl);
		$("#frmPopmng").submit();
	}

	/* 상세조회 페이지로 이동 */
	function fn_read(param1) {

		$("#memSeq").val(param1);
		var reqUrl = CONTEXT_ROOT + targetUrl + "getMember.do";
		$("#frmPopmng").attr("action", reqUrl);
		$("#frmPopmng").submit();
	}

	/* 삭제 */
	function fn_delt() {
		if (confirm("삭제 하시겠습니까?")) {
			var checkedVals = com.getCheckedVal('check0', 'check1');
			$("#memSeq").val(checkedVals);
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteMemberList.do";
			$("#frmPopmng").attr("action", reqUrl);
			$("#frmPopmng").submit();
		}
	}

	/* 신규 페이지로 이동 */
	function fn_cret() {
		var reqUrl = CONTEXT_ROOT + targetUrl + "insertPopupView.do";
		$("#frmPopmng").attr("action", reqUrl);
		$("#frmPopmng").submit();
	}

	function fn_egov_search_PopupManage() {
		fn_search(pageIndex);
	}
	
	function fn_calendarClear(Obj) {
		document.getElementById(Obj).value=""; 
	}
</script>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부
	기능을 사용하실 수 없습니다.</noscript>
<form name="frmPopmng" id="frmPopmng" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="orderKey" 	name="orderKey" value="${param.orderKey}" /> 
	<input type="hidden" id="orderDir" name="orderDir" value="${param.orderDir}" />
	<input type="hidden" id="popupId" name="popupId"  />
	
	<!-- E : search-list-1 (검색조건 영역) -->
	<ul class="search-list-1">
		<li><span>게시일</span> <input type="text" id="startDate" name="startDate" value="${param.startDate}" style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('startDate');"/>~ 
		<input type="text"  id="finishDate" name="finishDate" value="${param.finishDate}" style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('finishDate');"/>
		</li>
		<li><span>노출위치</span> <select name="pageType" class="selectpicker btn-xs reset" data-width="130px">
				<option value="">-선택-</option>
				<c:forEach items="${ccFocusList}" var="ccInfo" varStatus="status">
					<option value="${ccInfo.codeId}" ${param.pageType == ccInfo.codeId ? 'selected="selected"' : ''}>${ccInfo.codeName}</option>
				</c:forEach>
		</select></li>
		<li><span>검색대상</span> <select name="searchKeyword" class="selectpicker btn-xs reset" data-width="130px">
				<option value="">-선택-</option>
				<c:forEach items="${ccSearchList}" var="ccInfo" varStatus="status">
					<option value="${ccInfo.codeId}"
						${param.searchKeyword == ccInfo.codeId ? 'selected="selected"' : ''}>${ccInfo.codeName}</option>
				</c:forEach>
		</select> <input type="text" id="searchCondition" name="searchCondition" style="width: 300px;" value="${popupManageVO.searchCondition}" /></li>
	</ul>
</form>
<!-- E : search-list-1 (검색조건 영역) -->
<div class="search-btn-area">
	<a href="#@" onclick="fn_egov_search_PopupManage(); return false;">조회</a>
</div>
<!-- S : board-info (게시판 버튼 및 구분selectBox ) -->
<ul class="board-info">
	<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }" /> 건</li>
</ul>
<!-- E : board-info (게시판 버튼 및 구분selectBox ) -->
<!-- S : list (게시판 목록 영역) -->
<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="20px"><input type="checkbox" /></th>
			<th width="40px">순번</th>
			<th width="40px">노출위치</th>
			<th width="240px">제목</th>
			<th width="240px">게시 기간</th>
			<th width="40px">미리보기</th>
			<th width="70px">등록자</th>
			<th width="70px">등록일</th>
			<th width="70px">사용여부</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td class="lt_text3" colspan="9"><spring:message
						code="common.nodata.msg" /></td>
			</tr>
		</c:if>
		<%-- 데이터를 화면에 출력해준다 --%>
		<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
			<tr>
				<td><input type="checkbox" name="checkList" id="checkList"
					value="${resultInfo.popupId}"></td>
				<td><c:out value="${(pageIndex-1) * pageSize + status.count}" /></td>
				<td>
				<c:forEach items="${ccFocusList}" var="ccInfoName" begin="0" varStatus="status" >
					<c:if test="${resultInfo.pageType eq ccInfoName.codeCode}">${ccInfoName.codeName}</c:if>
				</c:forEach>
				</td>
				<td><a href="javascript:fn_popup_detail('${resultInfo.popupId}');"><span color="blue"><c:out value="${resultInfo.title}" /></span></a></td>
				<td><c:out value="${fn:substring(resultInfo.startDate, 0, 4)}" />-<c:out value="${fn:substring(resultInfo.startDate, 4, 6)}" />-<c:out value="${fn:substring(resultInfo.startDate, 6, 8)}" />
					 ~ <c:out value="${fn:substring(resultInfo.finishDate, 0, 4)}" />-<c:out value="${fn:substring(resultInfo.finishDate, 4, 6)}" />-<c:out value="${fn:substring(resultInfo.finishDate, 6, 8)}" />
				</td>
				<td><span><a class="btn-popup-open" href="javascript:;" data-popup-id="${resultInfo.popupId}">보기</span></a></span></td>
				<td><c:out value="${resultInfo.creatorId}" /></td>
				<td><c:out value="${fn:substring(resultInfo.createDate, 0, 4)}" />-<c:out value="${fn:substring(resultInfo.createDate, 4, 6)}" />-<c:out value="${fn:substring(resultInfo.createDate, 6, 8)}" /></td>
				<td><c:out value="${resultInfo.isUse}" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- E : list (게시판 목록 영역) -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_search" />
</div>
<!-- E : page-num -->

<div class="page-btn">
	<a href="#" onclick="fn_cret();">등록</a>
</div>
<!-- E : page-btn -->


