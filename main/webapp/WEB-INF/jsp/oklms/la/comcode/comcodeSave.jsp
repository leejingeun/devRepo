<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript" src="${contextRoot }js/FileSaver/FileSaver.js"></script>

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
		$("#frmComcode").find("input,select").val("");
	}

	/* HTML Form 신규, 수정 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			
			var reqUrl = CONTEXT_ROOT + "/la/comcode/comcode/saveComcode.do";
			$("#frmComcode").attr("action", reqUrl);
			$("#frmComcode").submit();
		}
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
</script>






<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">


			<form:form commandName="frmComcode">
				<double-submit:preventer tokenKey="frmComcodeToken"/>
				<input type="hidden" id="frm_codeId"   name="codeId"  value="${comcodeVO.codeId}" />
				<input type="hidden" id="frm_searchCodeName"   name="searchCodeName"  value="${comcodeVO.searchCodeName}" />
						<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
							<tbody>
								<tr>	<th>코드구분</th>		<td colspan="3"><input type="text" id="frm_codeGroup"       name="codeGroup"		value="${comcodeVO.codeGroup}"  style="width:50%;" />	
									<select id="searchCodeGroup" name="searchCodeGroup" style="width:280px; margin-right:10px;">
											<option value="">선택하세요</option>
										<c:forEach items="${comcodeGroupList}" var="item" varStatus="status" >
											<option value="${item.codeGroup}" <c:if test="${ item.codeGroup eq comcodeVO.codeGroup }" > selected="selected"</c:if> >${item.groupDesc}</option>
										</c:forEach>
									</select>
									<form:errors path="codeGroup" />	</td>	</tr>
								<tr>	<th>코드설명</th>		<td colspan="3"><input type="text" id="frm_groupDesc"       name="groupDesc"		value="${comcodeVO.groupDesc}"  style="width:100%;" />	<form:errors path="groupDesc" />	</td>	</tr>
								<tr>	<th>코드</th>		<td colspan="3"><input type="text" id="frm_codeCode"       name="codeCode"		value="${comcodeVO.codeCode}"  style="width:100%;" />	<form:errors path="codeCode" />	</td>	</tr>
								<tr>	<th>코드명</th>		<td colspan="3"><input type="text" id="frm_codeName"       name="codeName"		value="${comcodeVO.codeName}"  style="width:100%;" />	<form:errors path="codeName" />	</td>	</tr>
								<tr>	<th>코드영문명</th>		<td colspan="3"><input type="text" id="frm_codeNameEng"	name="codeNameEng"		value="${comcodeVO.codeNameEng}"  style="width:100%;" />	<form:errors path="codeNameEng" />	</td>	</tr>
								<tr>	<th>순서</th>		<td colspan="3"><input type="text" id="frm_codeOrder"       name="codeOrder"		value="${comcodeVO.codeOrder}"  style="width:100%;" />	<form:errors path="codeOrder" />	</td>	</tr>
								<tr>	<th>삭제여부</th>		<td colspan="3">
								
								<input type="radio" id="deleteYnY" name="deleteYn" class="choice" value="Y" <c:if test="${ 'Y' eq comcodeVO.deleteYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="deleteYnN" name="deleteYn" class="choice" value="N"  <c:if test="${ 'N' eq comcodeVO.deleteYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>
								
<%-- 								<input type="text" id="frm_deleteYn"       name="deleteYn"		value="${comcodeVO.deleteYn}"  style="width:100%;" />	 --%>
								<form:errors path="deleteYn" />	</td>	</tr>
								<tr>	<th>사용여부</th>		<td colspan="3">
								
									<input type="radio" id="useYnY" name="useYn" class="choice" value="Y" <c:if test="${ 'Y' eq comcodeVO.useYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="useYnN" name="useYn" class="choice" value="N"  <c:if test="${ 'N' eq comcodeVO.useYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>
								
<%-- 								<input type="text" id="frm_useYn"       name="useYn"		value="${comcodeVO.useYn}"  style="width:100%;" />	 --%>
								<form:errors path="useYn" />	</td>	</tr>
								<tr>	<th>비고1</th>		<td colspan="3"><input type="text" id="frm_bigo1"       name="bigo1"		value="${comcodeVO.bigo1}"  style="width:100%;" />	<form:errors path="bigo1" />	</td>	</tr>
								<tr>	<th>비고2</th>		<td colspan="3"><input type="text" id="frm_bigo2"       name="bigo2"		value="${comcodeVO.bigo2}"  style="width:100%;" />	<form:errors path="bigo2" />	</td>	</tr>
								<tr>	<th>비고3</th>		<td colspan="3"><input type="text" id="frm_bigo3"       name="bigo3"		value="${comcodeVO.bigo3}"  style="width:100%;" />	<form:errors path="bigo3" />	</td>	</tr>
							</tbody>
						</table><!-- E : view-1 -->
			</form:form>



						<div class="page-btn">
							<a role="button" href="#fn_formReset" onclick="javascript:fn_formReset(1);return false;" onkeypress="this.onclick;">초기화</a><a role="button" href="#fn_formSave" onclick="javascript:fn_formSave();return false;" onkeypress="this.onclick;">등록</a><a href="/la/comcode/comcode/listComcode.do">취소</a>
						</div>	
						
						
						
						<%--
<div align="center">
		
	<div style="float:left; border: 5px double #48BAE4; height: auto; padding: 10px;">
			<form:form commandName="frmComcode">
				<double-submit:preventer tokenKey="frmComcodeToken"/>
				<input type="hidden" id="prototypeId"   name="prototypeId"  value="${protoBoardVO.prototypeId}" />
			<div align="left">
				<h2>Validation Test Form</h2>
					<table>
						<tr><td>프로토타입id</td><td><c:out value="${protoBoardVO.prototypeId}" /></td>     </tr>
						<tr><td>조회수</td><td><input type="text" id="prototypeViewCnt"   name="prototypeViewCnt"  value="${protoBoardVO.prototypeViewCnt}" />		<form:errors path="prototypeViewCnt" /></td></tr>
						<tr><td>제목</td><td><input type="text" id="prototypeTitle"       name="prototypeTitle"   value="${protoBoardVO.prototypeTitle}" />			<form:errors path="prototypeTitle" /></td>      </tr>
						<tr><td>내용1</td><td><input type="text" id="prototypeContents1"  name="prototypeContents1"   value="${protoBoardVO.prototypeContents1}" />	<form:errors path="prototypeContents1" /></td>  </tr>
						<tr><td>내용2</td><td><input type="text" id="prototypeContents2"  name="prototypeContents2"  value="${protoBoardVO.prototypeContents2}" />	<form:errors path="prototypeContents2" /></td>   </tr>
						<tr><td>첨부파일ID</td><td><input type="text" id="atchFileId"      name="atchFileId"   value="${protoBoardVO.atchFileId}" />					<form:errors path="atchFileId" /></td>             </tr>
						<tr><td>사용여부</td><td><input type="text" id="useYsno"          name="useYsno"   value="${protoBoardVO.useYsno}" />						<form:errors path="useYsno" /></td>             </tr>
						<tr><td>생성자ID</td><td><input type="text" id="crtrId"           name="crtrId"   value="${protoBoardVO.crtrId}" />							<form:errors path="crtrId" /></td>              </tr>
						<tr><td>생성일시</td><td><input type="text" id="cretYmdtm"        name="cretYmdtm"   value="${protoBoardVO.cretYmdtm}" />					<form:errors path="cretYmdtm" /></td>           </tr>
						<tr><td>수정자ID</td><td><input type="text" id="updtrId"          name="updtrId"   value="${protoBoardVO.updtrId}" />						<form:errors path="updtrId" /></td>             </tr>
						<tr><td>수정일시</td><td><input type="text" id="updtYmdtm"        name="updtYmdtm"  value="${protoBoardVO.updtYmdtm}" />						<form:errors path="updtYmdtm" /></td>          </tr>
					</table>
			<div><a class="btn01" role="button" href="#fn_formSave" onclick="javascript:fn_formSave();return false;" onkeypress="this.onclick;"><span>mergeInto 저장/수정(VO)</span></a></div>

			<div style="height:10px"></div>
			
				
			</form:form>
		</div>
	</div>
</div>
						 --%>
