<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

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

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>

<script type="text/javascript">


	var lastSel=0;
	
	var vGridId = "myGrid01";
	var pageSize 	= '${pageSize}';  //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount  = '${totalCount}';  //전체 데이터 갯수
	var pageIndex   = '${pageIndex}';  //현재 페이지 정보

	$(document).ready(function() {

		if( '' == pageSize 		) {pageSize = 10;}
		if( '' == totalCount 	) {totalCount = 0;}
		if( '' == pageIndex 	) {pageIndex = 1;}
		
		loadPage();	
	});
	
	/*====================================================================
		jqGrid 및 화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
		$("#searchCodeGroup").change(function() {
			$("#frm_codeGroup").val( $("#searchCodeGroup").val() );
			$("#frm_groupDesc").val( $("#searchCodeGroup option:selected" ).text() );
			if( "" == $("#searchCodeGroup").val() ){
				$("#frm_groupDesc").val( "" );
			}
		});
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보

		
	}

	/*
	* 화면 유효성 첵크
	*/
	function fn_formValidate() {
		return true;
	}

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmAuthGroup").find("input,select").val("");
	}

	/* HTML Form 신규, 수정 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			
			var reqUrl = CONTEXT_ROOT + "/la/authority/authGroup/saveAuthGroup.do";
			$("#frmAuthGroup").attr("action", reqUrl);
			$("#frmAuthGroup").submit();
		}
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
</script>


<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">


<form:form commandName="frmAuthGroup">
<double-submit:preventer tokenKey="frmAuthGroupToken"/>
<input type="hidden" id="frm_authGroupId"   name="authGroupId"  value="${authGroupVO.authGroupId}" />
<input type="hidden" id="frm_searchAuthGroupName"   name="searchAuthGroupName"  value="${authGroupVO.searchAuthGroupName}" />
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>	<th width="130px">권한그룹명</th>		<td colspan="3"><input type="text" id="frm_AuthGroupName"       name="authGroupName"		value="${authGroupVO.authGroupName}"  style="width:100%;" />	<form:errors path="authGroupName" />	</td>	</tr>
		<tr>	<th>권한그룹설명</th>		<td colspan="3"><input type="text" id="frm_AuthGroupDesc"       name="authGroupDesc"		value="${authGroupVO.authGroupDesc}"  style="width:100%;" />	<form:errors path="authGroupDesc" />	</td>	</tr>
		<tr>	<th>삭제여부</th>		<td colspan="3"><input type="radio" id="frm_deleteYnY" name="deleteYn" class="choice" value="Y" <c:if test="${ 'Y' eq authGroupVO.deleteYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="deleteYnN" name="deleteYn" class="choice" value="N"  <c:if test="${ 'N' eq authGroupVO.deleteYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>		<form:errors path="deleteYn" />	</td>	</tr>
	</tbody>
</table><!-- E : view-1 -->
</form:form>


<div class="page-btn">
	<a role="button" href="#fn_formReset" onclick="javascript:fn_formReset(1);return false;" onkeypress="this.onclick;">초기화</a><a role="button" href="#fn_formSave" onclick="javascript:fn_formSave();return false;" onkeypress="this.onclick;">등록</a><a href="/la/authority/authGroup/listAuthGroup.do">취소</a>
</div>	
						