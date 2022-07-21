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

<script type="text/javascript" src="${contextRoot }js/FileSaver/FileSaver.js"></script>

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
	
	
// 	function fn_getComcode(targetUrl , param1){
// 		var reqUrl = CONTEXT_ROOT + targetUrl;
// 		$("#codeId").val( param1 );
// 		$("#frmComcode").attr("method", "post" );
// 		$("#frmComcode").attr("action", reqUrl);
// 		$("#frmComcode").submit();
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
			
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/listComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}
	
	/* 추가 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/goInsertComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}

	/* 상세조회 */
	function fn_read( param1 ){
		
		$("#codeId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/getComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}

	/* 수정 */
	function fn_updt( param1 ){
		
		$("#codeId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/goUpdateComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}
			
	/* save(추가/수정) */
	function fn_save( param1 ){
		
		$("#codeId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/goSaveComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}
			
	/* 삭제 */
	function fn_delt(){
		
// 		$("#codeId").val( param1 );

		var checkedVals = com.getCheckedVal('check0','check1');
		com.log(checkedVals);

		$("#delCodeId").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/deleteComcode.do";
		$("#frmComcode").attr("action", reqUrl);
		$("#frmComcode").submit();
	}

	/*
	* jsp 엑셀
	*/
	function fn_toExcel2(){
		var excelDownUrl = "/la/comcode/comcode/protoBoardExcelDownload2.do";
		
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
	
	/* AJAX 로 form 저장 */
	function fn_ajaxSave(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcode.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
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
	
	
	/* AJAX 로 form 저장 */
	function fn_member(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriMember.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_subject(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriSubject.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_lesson(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriLessonJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_ins(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriInsJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_cdp(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriCdpJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	
	/* AJAX 로 form 저장 */
	function fn_sch(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriSchJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_ncs(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriNcsJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_week(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcodeAunuriWeekJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	/* AJAX 로 form 저장 */
	function fn_ncs_update(){
		
		var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/updateComcodeAunuriWeekJson.json";
// 			var param 	= $("#frmComcode").serialize();
		var param = $("#frmComcodeAJAX").serializeArray();

		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 300000000			// 기본 30초
  
    	});
	}
	
	
	
</script>


<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display: none">

<form id="frmComcode" name="frmComcode" action="<c:url value='/la/comcode/comcode/listComcode.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="codeId" name="codeId" /> 
	<input type="hidden" id="delCodeId" name="delCodeId" />

	<ul class="search-list-1">
<%--
		<li>
			<span>기간</span>
			<input type="text" id="searchStartDate" name="searchStartDate" style="width:80px;" /> ~ 
			<input type="text" id="searchEndDate" name="searchEndDate" style="width:80px;" />
			<dl class="date">
				<dt><a role="button" href="">전체</a></dt>
				<dd><a role="button" href="#" onclick="javascript:com.datepicker('searchEndDate', 30);">1 개월</a></dd>
				<dd><a role="button" href="#" onclick="javascript:com.datepicker('searchEndDate', 90);">3 개월</a></dd>
				<dd><a role="button" href="#" onclick="javascript:com.datepicker('searchEndDate', 180);">6 개월</a></dd>
			</dl>
		</li>
 --%>	
		<li>
			<span>코드 구분</span>
			<select id="searchCodeGroup" name="searchCodeGroup" style="width:280px; margin-right:10px;">
									<option value="">선택하세요</option>
								<c:forEach items="${comcodeGroupList}" var="item" varStatus="status" >
									<option value="${item.codeGroup}" <c:if test="${ item.codeGroup eq searchCodeGroup or item.codeGroup eq returnResultMap.searchCodeGroup}" > selected="selected"</c:if> >${item.groupDesc}</option>
								</c:forEach></select>
		</li>
		<li>
			<span>코드 명</span>
			<input type="text" id="searchCodeName" name="searchCodeName" style="width:415px;" value="${searchCodeName }"/>
		</li>
	</ul><!-- E : search-list-1 -->
	<div class="search-btn-area"><a href="#" onclick="fn_search(1);">조회</a></div>
	
	
	<ul class="board-info">
		<li class="info-area"><span>검색 결과</span> : <span id="totalRow">0</span> 건</li>
		<li class="btn-area">
			<a role="button" href="#" onclick="fn_save();">신규</a>
			<a role="button" href="#" onclick="fn_delt();">삭제</a>
			
			
			<a role="button" href="#" onclick="fn_member();">아우누리회원연계</a>
			<a role="button" href="#" onclick="fn_subject();">개설교과목|주차|주차시간</a>
			<a role="button" href="#" onclick="fn_ncs();">주차별NCS연계</a>
			<a role="button" href="#" onclick="fn_lesson();">수강정보연계</a>
			<a role="button" href="#" onclick="fn_ins();">교수정보연계</a>
			<a role="button" href="#" onclick="fn_cdp();">학과전담자매핑</a>
			<a role="button" href="#" onclick="fn_sch();">학사일정연계</a>
			
			<a role="button" href="#" onclick="fn_week();">주차별 시간 및 NCS연계</a>
			
			<a role="button" href="#" onclick="fn_ncs_update();">주차별 NCS업데이트</a>
			
<!-- 			<select style="width:80px; margin-left:20px;"></select> -->
		</li>
	</ul>
	
	<table id="myGridTable" border="0" cellpadding="0" cellspacing="0" class="list-1">
		<thead>
			<tr>
				<th width="30px"><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');"/></th>
				<th width="30px">순번</th>
				<th width="100px">코드구분</th>
				<th width="100px">코드</th>
				<th>코드명</th>
				<th width="100px">코드영문명</th>
				<th width="100px">코드설명</th>
				<th width="100px">순서</th>
				<th width="100px">삭제여부(탈퇴)</th>
				<th width="100px">사용여부</th>
				<th width="100px">비고1</th>
				<th width="100px">비고2</th>
				<th width="100px">비고3</th>
<!-- 				<th width="100px">등록일자</th> -->
<!-- 				<th width="100px">등록자</th> -->
<!-- 				<th width="100px">수정일자</th> -->
<!-- 				<th width="100px">수정자</th> -->
<!-- 				<th width="100px">보기</th> -->
<!-- 				<th width="100px">수정</th> -->
				<th width="100px">수정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><input type="checkbox" name="check1" class="choice" value='<c:out value="${result.codeId}"/>'  onclick="javascript:fn_checkRowVals(this);"></td>
				<td><c:out value="${totalCount-((pageIndex-1) * pageSize + status.count)+1}"/>
					<input type="hidden" name="result_codeId" value='<c:out value="${result.codeId}"/>' /> </td>
				<td><c:out value="${result.codeGroup}"/><input type="hidden" name="result_codeGroup"		value='<c:out value="${result.codeGroup}"/>'  /></td>
				<td><c:out value="${result.codeCode}"/><input type="hidden" name="result_codeCode"		value='<c:out value="${result.codeCode}"/>'  /></td>
				<td class="subject"><c:out value="${result.codeName}"/><input type="hidden" name="result_codeName"		value='<c:out value="${result.codeName}"/>'  /></td>
				<td><c:out value="${result.codeNameEng}"/><input type="hidden" name="result_codeNameEng"		value='<c:out value="${result.codeNameEng}"/>'  /></td>
				<td><c:out value="${result.groupDesc}"/><input type="hidden" name="result_groupDesc"		value='<c:out value="${result.groupDesc}"/>'  /></td>
				<td><c:out value="${result.codeOrder}"/><input type="hidden" name="result_codeOrder"		value='<c:out value="${result.codeOrder}"/>'  /></td>
				<td><c:out value="${result.deleteYn}"/><input type="hidden" name="result_deleteYn"		value='<c:out value="${result.deleteYn}"/>'  /></td>
				<td><c:out value="${result.useYn}"/><input type="hidden" name="result_useYn"		value='<c:out value="${result.useYn}"/>'  /></td>
				<td><c:out value="${result.bigo1}"/><input type="hidden" name="result_bigo1"		value='<c:out value="${result.bigo1}"/>'  /></td>
				<td><c:out value="${result.bigo2}"/><input type="hidden" name="result_bigo2"		value='<c:out value="${result.bigo2}"/>'  /></td>
				<td><c:out value="${result.bigo3}"/><input type="hidden" name="result_bigo3"		value='<c:out value="${result.bigo3}"/>'  /></td>
<%-- 				<td><c:out value="${result.insertDate}"/><input type="hidden" name="result_insertDate"		value='<c:out value="${result.insertDate}"/>'  /></td> --%>
<%-- 				<td><c:out value="${result.insertUser}"/><input type="hidden" name="result_insertUser"		value='<c:out value="${result.insertUser}"/>'  /></td> --%>
<%-- 				<td><c:out value="${result.updateDate}"/><input type="hidden" name="result_updateDate"		value='<c:out value="${result.updateDate}"/>'  /></td> --%>
<%-- 				<td><c:out value="${result.updateUser}"/><input type="hidden" name="result_updateUser"		value='<c:out value="${result.updateUser}"/>'  /></td> --%>
<%-- 				<td><a class="btn-1" href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">보기</a></td> --%>
<%-- 				<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_updt( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">수정</a></td> --%>
				<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_save( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">수정</a></td>
			</tr>
			</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td class="lt_text3" nowrap colspan="20"><spring:message code="common.nodata.msg" /></td>
			</tr>
		</c:if>
		</tbody>
	</table>
</form>

<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div>



		
		
<%--

<div>

	<div style="height: 20px"></div>

	<div style="width:70%; float: left; border: 5px double #48BAE4; height: auto; padding: 10px;">
		<div align="right">
			<span>총 </span><span id="totalRow">0</span><span>건</span>
		</div>
		<div style="float: none;">


			<div id="border">
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>" />
		
					<table id="myGridTable" border="1" class="table-line"  summary="번호,제목 , 내용1, 내용2, 첨부파일 , 조회수 목록입니다">
						<thead>
							<tr>
								<th scope="col" width="2%" nowrap><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');"/></th>
								<th scope="col" width="10%" nowrap>번호</th>
								<th scope="col" width="30%" nowrap>제목</th>
								<th scope="col" width="10%" nowrap>내용1</th>
								<th scope="col" width="10%" nowrap>내용2</th>
								<th scope="col" width="4%" nowrap>첨부파일</th>
								<th scope="col" width="4%" nowrap>조회수</th>
								<th scope="col" width="8%" nowrap>사용여부</th>
								<th scope="col" width="15%" nowrap>생성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td class="lt_text3" nowrap>
										<input type="checkbox" name="check1" class="choice" value='<c:out value="${result.codeId}"/>'  onclick="javascript:fn_checkRowVals(this);">
									</td>
									<td class="lt_text3" nowrap>
										<c:out value="${totalCount-((pageIndex-1) * pageSize + status.count)+1}"/>
										<input type="hidden" name="result_codeId" value='<c:out value="${result.codeId}"/>' /> 
									</td>
									<td class="lt_text3" nowrap>
										<c:out value="${result.prototypeTitle}" />
										<a href="/la/comcode/comcode/getComcode.do" onclick="javascript:fn_getComcode(this.href, '<c:out value="${result.codeId}"/>'); return false">[Read]</a>
										<a href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">[Read]</a>
										<a href="#fn_updt" onclick="javascript:fn_updt( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">[Updt]</a>
										<a href="#fn_save" onclick="javascript:fn_save( '<c:out value="${result.codeId}"/>'); return false" onkeypress="this.onclick;">[Save(수정)]</a>
										<a href="#fn_save" onclick="javascript:fn_save( ''); return false">[Save(추가)]</a>
										<input type="hidden" name="result_prototypeTitle" value='<c:out value="${result.prototypeTitle}"/>' />
									</td>
									<td class="lt_text3" nowrap><c:out value="${result.prototypeContents1}" /><input type="hidden" name="result_prototypeContents1" value='<c:out value="${result.prototypeContents1}"/>' /></td>
									<td class="lt_text3" nowrap><c:out value="${result.prototypeContents2}" /><input type="hidden" name="result_prototypeContents2" value='<c:out value="${result.prototypeContents2}"/>' /></td>
									<td class="lt_text3" nowrap><c:out value="${result.atchFileId}" /><input type="hidden" name="result_atchFileId" value='<c:out value="${result.atchFileId}"/>' /></td>
									<td class="lt_text3" nowrap><c:out value="${result.prototypeViewCnt}" /><input type="hidden" name="result_prototypeViewCnt" value='<c:out value="${result.prototypeViewCnt}"/>' /></td>
									<td class="lt_text3" nowrap><c:if test="${result.useYsno == 'N'}">사용 안함<input type="hidden" name="result_useYsno" value='N' /></c:if> <c:if test="${result.useYsno == 'Y'}">사용<input type="hidden" name="result_useYsno" value='Y' /></c:if></td>
									<td class="lt_text3" nowrap><c:out value="${result.cretYmdtm}" /><input type="hidden" name="result_cretYmdtm" value='<c:out value="${result.cretYmdtm}"/>' /></td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="lt_text3" nowrap colspan="6"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td height="10"></td>
						</tr>
					</table>
			</div>


		</div>
		<div style="height: 40px"></div>
<!-- 		<div id="pageNavi" name="pageNavi" style="height: 30px;"></div> -->
		


	
		<div align="right" style="height: 100px;">

<!-- 			<a class="btn01" role="button" href="#" onclick="fn_toExcel();">simple 엑셀</a> -->
<!-- 			<a class="btn01" role="button" href="#" onclick="fn_toExcel1();">poi 엑셀</a> -->
<!-- 			<a class="btn01" role="button" href="#" onclick="fn_toExcel2();">jsp 엑셀</a> -->
		</div>
	</div>
 --%>

<%-- 

	<div style="height:20px;width: 30px"></div>
	<div style="float:left;"></div>
	<div style="float:left; border: 5px double #48BAE4; height: auto; padding: 10px;">
		<div align="left">
			<form:form commandName="frmComcodeAJAX">
				<table id="myInputTable">
					<tr><th>코드아이디</th><td><input type="text" id="frm_codeId"       name="codeId"		value="${infoMap.codeId}" /></td></tr> 
					<tr><th>코드구분</th><td><input type="text" id="frm_codeGroup"       name="codeGroup"		value="${infoMap.codeGroup}" /></td></tr>
					<tr><th>코드설명</th><td><input type="text" id="frm_groupDesc"       name="groupDesc"		value="${infoMap.groupDesc}" /></td></tr>
					<tr><th>코드</th><td><input type="text" id="frm_codeCode"       name="codeCode"		value="${infoMap.codeCode}" /></td></tr>
					<tr><th>코드명</th><td><input type="text" id="frm_codeName"       name="codeName"		value="${infoMap.codeName}" /></td></tr>
					<tr><th>코드영문명</th><td><input type="text" id="frm_codeNameEng"	name="codeNameEng"		value="${infoMap.codeNameEng}" /></td></tr>
					<tr><th>순서</th><td><input type="text" id="frm_codeOrder"       name="codeOrder"		value="${infoMap.codeOrder}" /></td></tr>
					<tr><th>삭제여부(탈퇴)</th><td><input type="text" id="frm_deleteYn"       name="deleteYn"		value="${infoMap.deleteYn}" /></td></tr>
					<tr><th>사용여부</th><td><input type="text" id="frm_useYn"       name="useYn"		value="${infoMap.useYn}" /></td></tr>
					<tr><th>비고1</th><td><input type="text" id="frm_bigo1"       name="bigo1"		value="${infoMap.bigo1}" /></td></tr>
					<tr><th>비고2</th><td><input type="text" id="frm_bigo2"       name="bigo2"		value="${infoMap.bigo2}" /></td></tr>
					<tr><th>비고3</th><td><input type="text" id="frm_bigo3"       name="bigo3"		value="${infoMap.bigo3}" /></td></tr>
				</table>
			</form:form>
		</div>
		<div style="clear:both;" align="left">
			<div><a class="btn01" href="javascript://" onclick="javascript:fn_ajaxSave();"><span>AJAX 추가 & 수정 저장</span></a></div>
		</div>	
		
	</div>
	 --%>
	
	
	
</div>
