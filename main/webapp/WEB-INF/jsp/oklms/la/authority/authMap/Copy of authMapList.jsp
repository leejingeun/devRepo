<%--
  Class Name : list.jsp 
  Description : 기본형
  Modification Information
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>


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
	
	
// 	function fn_getAuthMap(targetUrl , param1){
// 		var reqUrl = CONTEXT_ROOT + targetUrl;
// 		$("#menuIdAndAuthGroupId").val( param1 );
// 		$("#frmAuthMap").attr("method", "post" );
// 		$("#frmAuthMap").attr("action", reqUrl);
// 		$("#frmAuthMap").submit();
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
			
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/listAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
	
	/* 추가 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goInsertAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/* 상세조회 */
	function fn_read( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/getAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/* 수정 */
	function fn_updt( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goUpdateAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
			
	/* save(추가/수정) */
	function fn_save( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goSaveAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
			
	/* 삭제 */
	function fn_delt(){
		
// 		$("#menuIdAndAuthGroupId").val( param1 );

		var checkedVals = com.getCheckedVal('check0','check1');
		com.log(checkedVals);

		$("#delCodeId").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/deleteAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/*
	* jsp 엑셀
	*/
	function fn_toExcel2(){
		var excelDownUrl = "/la/authority/authMap/protoBoardExcelDownload2.do";
		
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
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/saveAuthMap.json";
// 			var param 	= $("#frmAuthMap").serialize();
		var param = $("#frmAuthMapAJAX").serializeArray();

		
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

<form id="frmAuthMap" name="frmAuthMap" action="<c:url value='/la/authority/authMap/listAuthMap.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" id="menuIdAndAuthGroupId" name="menuIdAndAuthGroupId" /> 
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
			<span>권한 그룹</span>
			<select id="searchAuthGroupId" name="searchAuthGroupId" style="width:280px; margin-right:10px;">
									<option value="">선택하세요</option>
								<c:forEach items="${authGroupList}" var="item" varStatus="status" >
									<option value="${item.authGroupId}" <c:if test="${ item.authGroupId eq searchAuthGroupId or item.authGroupId eq returnResultMap.searchAuthGroupId}" > selected="selected"</c:if> >${item.authGroupName}</option>
								</c:forEach></select>
		</li>
		<li>
			<span>메뉴명</span>
			<input type="text" id="searchMenuTitle" name="searchMenuTitle" style="width:415px;" value="${searchMenuTitle }"/>
		</li>
	</ul><!-- E : search-list-1 -->
	<div class="search-btn-area"><a href="#" onclick="fn_search(1);">조회하기</a></div>
	
	
	<ul class="board-info">
		<li class="info-area"><span>검색 결과</span> : <span id="totalRow">0</span> 건</li>
		<li class="btn-area">
			<a role="button" href="#" onclick="fn_save();">신규</a>
			<a role="button" href="#" onclick="fn_delt();">삭제</a>
			<select style="width:80px; margin-left:20px;"></select>
		</li>
	</ul>
	
	<table id="myGridTable" border="0" cellpadding="0" cellspacing="0" class="list-1">
		<thead>
			<tr>
				<th width="30px"><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');"/></th>
				<th width="30px">순번</th>
									<th width="100px">메뉴ID</th>
									<th width="100px">권한그룹아이디</th>
									<th width="100px">메뉴명</th>
									<th width="100px">권한그룹명</th>
									<th width="100px">삭제여부</th>
									<th width="100px">등록자</th>
									<th width="100px">등록일</th>
									<th width="100px">수정자</th>
									<th width="100px">수정일</th>
									<th width="100px">생성 권한 여부</th>
									<th width="100px">상세 조회 권한 여부</th>
									<th width="100px">수정 권한 여부</th>
									<th width="100px">삭제 권한 여부</th>
									<th width="100px">출력 권한 여부</th>
									<th width="100px">다운로드 권한 여부</th>
									<th width="100px">기타 권한 여부</th>
									<th width="100px">목록 조회 권한 여부</th>
				<!-- 				<th width="100px">보기</th> -->
<!-- 				<th width="100px">수정</th> -->
				<th width="100px">추가/수정</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td><input type="checkbox" name="check1" class="check2" value='<c:out value="${result.menuId}"/>^<c:out value="${result.authGroupId}"/>'  onclick="javascript:fn_checkRowVals(this);"></td>
				<td><c:out value="${totalCount-((pageIndex-1) * pageSize + status.count)+1}"/>
					<input type="hidden" name="result_menuIdAndAuthGroupId" value='<c:out value="${result.menuId}"/>^<c:out value="${result.authGroupId}"/>' /> </td>
									<td><c:out value="${result.menuId}"/><input type="hidden" name="result_menuId"		value='<c:out value="${result.menuId}"/>'  /></td>
									<td><c:out value="${result.authGroupId}"/><input type="hidden" name="result_authGroupId"		value='<c:out value="${result.authGroupId}"/>'  /></td>
									<td><c:out value="${result.menuTitle}"/><input type="hidden" name="result_menuTitle"		value='<c:out value="${result.menuTitle}"/>'  /></td>
									<td><c:out value="${result.authGroupName}"/><input type="hidden" name="result_authGroupName"		value='<c:out value="${result.authGroupName}"/>'  /></td>
									<td><c:out value="${result.deleteYn}"/><input type="hidden" name="result_deleteYn"		value='<c:out value="${result.deleteYn}"/>'  /></td>
									<td><c:out value="${result.insertUser}"/><input type="hidden" name="result_insertUser"		value='<c:out value="${result.insertUser}"/>'  /></td>
									<td><c:out value="${result.insertDate}"/><input type="hidden" name="result_insertDate"		value='<c:out value="${result.insertDate}"/>'  /></td>
									<td><c:out value="${result.updateUser}"/><input type="hidden" name="result_updateUser"		value='<c:out value="${result.updateUser}"/>'  /></td>
									<td><c:out value="${result.updateDate}"/><input type="hidden" name="result_updateDate"		value='<c:out value="${result.updateDate}"/>'  /></td>
									<td><c:out value="${result.createAuthYn}"/><input type="hidden" name="result_createAuthYn"		value='<c:out value="${result.createAuthYn}"/>'  /></td>
									<td><c:out value="${result.readAuthYn}"/><input type="hidden" name="result_readAuthYn"		value='<c:out value="${result.readAuthYn}"/>'  /></td>
									<td><c:out value="${result.updateAuthYn}"/><input type="hidden" name="result_updateAuthYn"		value='<c:out value="${result.updateAuthYn}"/>'  /></td>
									<td><c:out value="${result.deleteAuthYn}"/><input type="hidden" name="result_deleteAuthYn"		value='<c:out value="${result.deleteAuthYn}"/>'  /></td>
									<td><c:out value="${result.printAuthYn}"/><input type="hidden" name="result_printAuthYn"		value='<c:out value="${result.printAuthYn}"/>'  /></td>
									<td><c:out value="${result.downloadAuthYn}"/><input type="hidden" name="result_downloadAuthYn"		value='<c:out value="${result.downloadAuthYn}"/>'  /></td>
									<td><c:out value="${result.otherAuthYn}"/><input type="hidden" name="result_otherAuthYn"		value='<c:out value="${result.otherAuthYn}"/>'  /></td>
									<td><c:out value="${result.listAuthYn}"/><input type="hidden" name="result_listAuthYn"		value='<c:out value="${result.listAuthYn}"/>'  /></td>
				<%-- 				<td><a class="btn-1" href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.menuId}"/>^<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">보기</a></td> --%>
<%-- 				<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_updt( '<c:out value="${result.menuId}"/>^<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">수정</a></td> --%>
				<td><a class="btn-2" href="#fn_read" onclick="javascript:fn_save( '<c:out value="${result.menuId}"/>^<c:out value="${result.authGroupId}"/>'); return false" onkeypress="this.onclick;">추가/수정</a></td>
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




	<div style="height:20px;width: 30px"></div>
	<div style="float:left;"></div>
	<div style="float:left; border: 5px double #48BAE4; height: auto; padding: 10px;">
		<div align="left">
			<form:form commandName="frmAuthMapAJAX">
				<table id="myInputTable">
									<tr><th>메뉴ID</th><td><input type="text" id="frm_menuId"       name="menuId"		value="${infoMap.menuId}" /></td></tr> 					
									<tr><th>권한그룹아이디</th><td><input type="text" id="frm_authGroupId"       name="authGroupId"		value="${infoMap.authGroupId}" /></td></tr>
									<tr><th>메뉴명</th><td><input type="text" id="frm_menuTitle"       name="menuTitle"		value="${infoMap.menuTitle}" /></td></tr> 					
									<tr><th>권한그룹명</th><td><input type="text" id="frm_authGroupName"       name="authGroupName"		value="${infoMap.authGroupName}" /></td></tr> 					
									<tr><th>삭제여부</th><td><input type="text" id="frm_deleteYn"       name="deleteYn"		value="${infoMap.deleteYn}" /></td></tr> 					
									<tr><th>등록자</th><td><input type="text" id="frm_insertUser"       name="insertUser"		value="${infoMap.insertUser}" /></td></tr> 					
									<tr><th>등록일</th><td><input type="text" id="frm_insertDate"       name="insertDate"		value="${infoMap.insertDate}" /></td></tr> 					
									<tr><th>수정자</th><td><input type="text" id="frm_updateUser"       name="updateUser"		value="${infoMap.updateUser}" /></td></tr> 					
									<tr><th>수정일</th><td><input type="text" id="frm_updateDate"       name="updateDate"		value="${infoMap.updateDate}" /></td></tr> 					
									<tr><th>생성 권한 여부</th><td><input type="text" id="frm_createAuthYn"       name="createAuthYn"		value="${infoMap.createAuthYn}" /></td></tr> 					
									<tr><th>상세 조회 권한 여부</th><td><input type="text" id="frm_readAuthYn"       name="readAuthYn"		value="${infoMap.readAuthYn}" /></td></tr> 					
									<tr><th>수정 권한 여부</th><td><input type="text" id="frm_updateAuthYn"       name="updateAuthYn"		value="${infoMap.updateAuthYn}" /></td></tr> 					
									<tr><th>삭제 권한 여부</th><td><input type="text" id="frm_deleteAuthYn"       name="deleteAuthYn"		value="${infoMap.deleteAuthYn}" /></td></tr> 					
									<tr><th>출력 권한 여부</th><td><input type="text" id="frm_printAuthYn"       name="printAuthYn"		value="${infoMap.printAuthYn}" /></td></tr> 					
									<tr><th>다운로드 권한 여부</th><td><input type="text" id="frm_downloadAuthYn"       name="downloadAuthYn"		value="${infoMap.downloadAuthYn}" /></td></tr> 					
									<tr><th>기타 권한 여부</th><td><input type="text" id="frm_otherAuthYn"       name="otherAuthYn"		value="${infoMap.otherAuthYn}" /></td></tr> 					
									<tr><th>목록 조회 권한 여부</th><td><input type="text" id="frm_listAuthYn"       name="listAuthYn"		value="${infoMap.listAuthYn}" /></td></tr> 					
								</table>
			</form:form>
		</div>
		<div style="clear:both;" align="left">
			<div><a class="btn01" href="javascript://" onclick="javascript:fn_ajaxSave();"><span>AJAX 추가 & 수정 저장</span></a></div>
		</div>	
		
	</div>
	
	
	
	
</div>
