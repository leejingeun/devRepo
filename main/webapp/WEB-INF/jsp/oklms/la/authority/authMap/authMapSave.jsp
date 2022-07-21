<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


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
		$("#frmAuthMap").find("input,select").val("");
	}

	/* HTML Form 신규, 수정 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			
			var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/saveAuthMap.do";
			$("#frmAuthMap").attr("action", reqUrl);
			$("#frmAuthMap").submit();
		}
	}
	
				
		/* 상위메뉴 선택 팝업 */
	function fn_seachUperMenuPopup(){
		var reqUrl = "/la/menu/uperMenuList.do";
		var params = "";
		var searchMenuAreaVal = $("#searchMenuArea").val();
		var searchMenuTypeVal = $("#searchMenuType").val();
		if( !com.isBlank( searchMenuAreaVal ) ){
			params = "?searchMenuArea=" + searchMenuAreaVal;
		}
		if( !com.isBlank( searchMenuTypeVal ) ){
			if( com.isBlank( searchMenuAreaVal ) ){
				params = params + "?";
			}else{
				params = params + "&";
			}
			params = params + "searchMenuType=" + searchMenuTypeVal;
		}
		reqUrl = reqUrl + params;
		
		com.openPopup("searchMenuList", reqUrl, 425, 620);
	}

	
	function fnSelectMenuPopupAfter(map){
		var menuId = map['menuId'];
		var menuTitle = map['menuTitle'];
		var menuArea = map['searchMenuAreaVal'];
		var menuType = map['searchMenuTypeVal'];
		var menuObj = map['menuObj'];

// 		$("#menuId").val(menuId);
// 		$("#menuTitle").val(menuTitle);

		$("#frm_menuId").val(menuId);
		$("#frm_menuTitle").val(menuTitle);
    }
	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
</script>






<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">


			<form:form commandName="frmAuthMap">
				<double-submit:preventer tokenKey="frmAuthMapToken"/>
				<input type="hidden" id="frm_searchAuthGroupId"   name="searchAuthGroupId"  value="${authMapVO.searchAuthGroupId}" />
				<input type="hidden" id="frm_searchMenuTitle"   name="searchMenuTitle"  value="${authMapVO.searchMenuTitle}" />
						<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
							<tbody>
								<tr>	<th>권한그룹아이디</th>		<td colspan="3">
								<select id="authGroupId" name="authGroupId" style="width:280px; margin-right:10px;">
									<option value="">선택하세요</option>
								<c:forEach items="${authGroupList}" var="item" varStatus="status" >
									<option value="${item.authGroupId}" <c:if test="${ item.authGroupId eq authMapVO.searchAuthGroupId or item.authGroupId eq authMapVO.authGroupId or item.authGroupId eq returnResultMap.searchAuthGroupId}" > selected="selected"</c:if> >${item.authGroupName}</option>
								</c:forEach></select>
								
								</td>	</tr>
								<tr>	<th>메뉴ID</th>		<td colspan="3"><input type="hidden" id="frm_menuId"       name="menuId"		value="${authMapVO.menuId}" />
								<input type="text" id="frm_menuTitle" readonly title="메뉴 입력"   style="width:90%;" value="${authMapVO.menuTitle}">
								<a class="btn_g" href="javascript://" onclick="fn_seachUperMenuPopup();" title="메뉴 선택 팝업 열기">찾기</a>
									<form:errors path="menuId" />	</td>	</tr>
								<tr>	<th>생성 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_createAuthYnY" name="createAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.createAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_createAuthYnN" name="createAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.createAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="createAuthYn" />		</td>	</tr>
								<tr>	<th>상세 조회 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_readAuthYnY" name="readAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.readAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_readAuthYnN" name="readAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.readAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="readAuthYn" />	</td>	</tr>
								<tr>	<th>수정 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_updateAuthYnY" name="updateAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.updateAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_updateAuthYnN" name="updateAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.updateAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="updateAuthYn" />		</td>	</tr>
								<tr>	<th>삭제 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_deleteAuthYnY" name="deleteAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.deleteAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_deleteAuthYnN" name="deleteAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.deleteAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="deleteAuthYn" />		</td>	</tr>
								<tr>	<th>출력 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_printAuthYnY" name="printAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.printAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_printAuthYnN" name="printAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.printAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="printAuthYn" />		</td>	</tr>
								<tr>	<th>다운로드 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_downloadAuthYnY" name="downloadAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.downloadAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_downloadAuthYnN" name="downloadAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.downloadAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="downloadAuthYn" />		</td>	</tr>
								<tr>	<th>기타 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_otherAuthYnY" name="otherAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.otherAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_otherAuthYnN" name="otherAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.otherAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="otherAuthYn" />		</td>	</tr>
								<tr>	<th>목록 조회 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_listAuthYnY" name="listAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.listAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_listAuthYnN" name="listAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.listAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="listAuthYn" />		</td>	</tr>
								<tr>	<th>삭제여부</th>		<td colspan="3"><input type="radio" id="frm_deleteYnY" name="deleteYn" class="choice" value="Y" <c:if test="${ 'Y' eq authMapVO.deleteYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="deleteYnN" name="deleteYn" class="choice" value="N"  <c:if test="${ 'N' eq authMapVO.deleteYn }"> checked="checked"</c:if>/><label for="temp-2">N</label> 	<form:errors path="deleteYn" />	</td>	</tr>
							</tbody>
						</table><!-- E : view-1 -->
			</form:form>



						<div class="page-btn">
							<a role="button" href="#fn_formReset" onclick="javascript:fn_formReset(1);return false;" onkeypress="this.onclick;">초기화</a><a role="button" href="#fn_formSave" onclick="javascript:fn_formSave();return false;" onkeypress="this.onclick;">등록</a><a href="/la/authority/authMap/listAuthMap.do">취소</a>
						</div>	
						