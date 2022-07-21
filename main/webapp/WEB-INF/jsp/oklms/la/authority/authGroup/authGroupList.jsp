<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 김덕진    2016. 11. 30 오후 1:20         First Draft.
  ~  * 이진근    2016. 12. 01 오후 1:20         Modify Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<script type="text/javascript">
	var lastSel = 0;

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
		

// 	    com.datepicker('searchStartDate');
// 	    com.datepicker('searchEndDate');
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_adbkInfs('1');
		}
	}
	
	
// 	function fn_getAuthGroup(targetUrl , param1){
// 		var reqUrl = CONTEXT_ROOT + targetUrl;
// 		$("#authGroupId").val( param1 );
// 		$("#frmAuthGroup").attr("method", "post" );
// 		$("#frmAuthGroup").attr("action", reqUrl);
// 		$("#frmAuthGroup").submit();
// 	}




	/* 첵크된 Row 처리 */
	function fn_checkRowVals( thisObj ){

		var checkedCnt = $("#myGridTable input[name='" + thisObj.name + "']:checked").length;
// 		if( 1 < checkedCnt ){
// 			com.alert("한건만 선택해주세요.");
// 		}else{

			var valuesStr = "";
			$.each($("#myGridTable input[name='" + thisObj.name + "']:checked").parents("td").siblings(), function() {
				
				var inputObj = $(this).find("input");
				var inputName = inputObj.attr("name");
				var inputVal = inputObj.val();
				
				if( undefined != inputName ){

					inputName = inputName.replace("result_", "frm_");
					$("#" + inputName ).val(inputVal);
				}
			});
			
// 			$('#myGridTable th').each(function() {
// 				var thObj = this;
// 				com.log( thObj.cellIndex + " , " + thObj.textContent);
// 			});
// 		}
	}
	
	
	/* 리스트 조회 */
	function fn_search( pageNo ){
		$("#pageIndex").val( pageNo );
			
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/listAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}
	
	/* 추가 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/goInsertAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}

	/* 상세조회 */
	function fn_read( param1 ){
		
		$("#authGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/getAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}

	/* 수정 */
	function fn_updt( param1 ){
		
		$("#authGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/goUpdateAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}
			
	/* save(추가/수정) */
	function fn_save( param1 ){
		
		$("#authGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/goSaveAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}
			
	/* 삭제 */
	function fn_delt(){
		
// 		$("#authGroupId").val( param1 );

		var checkedVals = com.getCheckedVal('check0','check1');
		com.log(checkedVals);

		$("#delAuthGroupId").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/deleteAuthGroup.do";
		$("#frmAuthGroup").attr("action", reqUrl);
		$("#frmAuthGroup").submit();
	}

	/*
	* jsp 엑셀
	*/
	function fn_toExcel2(){
		var excelDownUrl = "/la/authority/authGroup/protoBoardExcelDownload2.do";
		
		//동적으로 iframe 생성
		var $iframe = $("#downIFrame");
		if($iframe.length < 1){
			var $iframe = $("<iframe id='downIFrame' name='downIFrame' frameBorder='0' scrolling='no' width='0' height='0'></iframe>");
			$(document.body).append($iframe);
		}
		
		//동적으로 다운로드용 form 생성
		var $form = $("#downForm");
		if($form.length < 1) {
			$form = $("<form id='downForm', method='post', action='"+excelDownUrl+"' target='downIFrame'></form>");
		  	$(document.body).append($form);
		}
		//이전에 처리한 다운로드파일정보 삭제
		$form.empty();

		//다운로드파일정보 세팅
//			$("<input></input>").attr({type:"hidden", name:"arg0", value:$.trim(arg0)}).appendTo($form);
//			$("<input></input>").attr({type:"hidden", name:"arg1", value:$.trim(arg1)}).appendTo($form);

		$form.submit();
		
	}
	
	function fn_reset(){

		$("#frmAuthGroupAJAX").find("input:text,input:hidden,select,textarea").val("");
		$("#frmAuthGroupAJAX").find("input:radio").prop("checked", false).end().buttonset("refresh");
	}
		
	/* AJAX 로 form 저장 */
	function fn_ajaxSave(){
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/saveAuthGroup.json";
// 			var param 	= $("#frmAuthGroup").serialize();
		var param = $("#frmAuthGroupAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 30000			// 기본 30초
    	});
	}
	
</script>


<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display: none">

<form id="frmAuthGroup" name="frmAuthGroup" action="<c:url value='/la/authority/authGroup/listAuthGroup.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="authGroupId" name="authGroupId" /> 
	<input type="hidden" id="delAuthGroupId" name="delAuthGroupId" />
	
	<ul class="search-list-1">
		<li>
			<span>권한 그룹 명</span>
			<input type="text" id="searchAuthGroupName" name="searchAuthGroupName" style="width:415px;" value="${searchAuthGroupName }"/>
		</li>
	</ul><!-- E : search-list-1 -->
	<div class="search-btn-area"><a href="#" onclick="fn_search(1);">조회</a></div>
	
	
	<ul class="board-info">
		<li class="info-area"><span>검색 결과</span> : <span id="totalRow">0</span> 건</li>
		<li class="btn-area">
			<a role="button" href="#" onclick="fn_save();">신규</a>
			<a role="button" href="#" onclick="fn_delt();">삭제</a>
		</li>
	</ul>
</form>

<!-- 						<div class="title-name-1" style="margin-top:40px;">권한 그룹</div> -->

<!-- <ul class="code-box"> -->

	<%-- <li class="code-area">
		<form:form commandName="frmAuthGroupAJAX">
			<table id="myInputTable" border="0" cellpadding="0" cellspacing="0" class="view-1">
				<tr><th>권한그룹아이디</th><td><input type="text" id="frm_authGroupId"       name="authGroupId"		value="${infoMap.authGroupId}" /></td></tr> 					
				<tr><th>권한그룹명</th><td><input type="text" id="frm_authGroupName"       name="authGroupName"		value="${infoMap.authGroupName}" /></td></tr> 					
				<tr><th>권한그룹설명</th><td><input type="text" id="frm_authGroupDesc"       name="authGroupDesc"		value="${infoMap.authGroupDesc}" /></td></tr> 					
				<tr><th>삭제여부</th><td><input type="text" id="frm_deleteYn"       name="deleteYn"		value="${infoMap.deleteYn}" /></td></tr> 					
			</table>
		</form:form>
		<!-- E : view-1 -->


		<div class="page-btn">
			<a href="javascript://" onclick="javascript:fn_reset();">초기화</a>
			<a href="javascript://" onclick="javascript:fn_ajaxSave();">저장</a>
		</div><!-- E : page-btn -->
	</li> --%>

<!-- 	<li class="list-area">
		<ul class="board-info">
			<li class="info-area"><span>검색 결과</span> : <span id="totalRow">0</span> 건</li>
			<li class="btn-area">
				<a role="button" href="#" onclick="fn_save();">신규</a>
				<a role="button" href="#" onclick="fn_delt();">삭제</a>
			</li>
		</ul> -->
		
		<table id="myGridTable" border="0" cellpadding="0" cellspacing="0" class="list-1">
			<thead>
				<tr>
					<th width="3%"><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');"/></th>
					<th width="7%">순번</th>
					<th width="25%">권한그룹아이디</th>
					<th width="15%">권한그룹명</th>
					<th width="30%">권한그룹설명</th>
					<th width="10%">삭제여부</th>
					<th width="10%">수정</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><input type="checkbox" name="check1" class="choice" value='<c:out value="${result.authGroupId}"/>'  onclick="javascript:fn_checkRowVals(this);"></td>
					<td><c:out value="${status.count}"/>
						<input type="hidden" name="result_authGroupId" value='<c:out value="${result.authGroupId}"/>' /> </td>
					<td><c:out value="${result.authGroupId}"/><input type="hidden" name="result_authGroupId"		value='<c:out value="${result.authGroupId}"/>'  /></td>
					<td><c:out value="${result.authGroupName}"/><input type="hidden" name="result_authGroupName"		value='<c:out value="${result.authGroupName}"/>'  /></td>
					<td><c:out value="${result.authGroupDesc}"/><input type="hidden" name="result_authGroupDesc"		value='<c:out value="${result.authGroupDesc}"/>'  /></td>
					<td><c:out value="${result.deleteYn}"/><input type="hidden" name="result_deleteYn"		value='<c:out value="${result.deleteYn}"/>'  /></td>
					<%-- 				<td><a class="btn-1" href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">보기</a></td> --%>
	<%-- 				<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_updt( '<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">수정</a></td> --%>
					<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_save( '<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">수정</a></td>
				</tr>
				</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				<tr>
					<td class="lt_text3" nowrap colspan="7"><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			</tbody>
		</table><!-- E : list -->

		<div class="page-num">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</div><!-- E : page-num -->
	<!-- </li> -->
<!-- </ul> -->

