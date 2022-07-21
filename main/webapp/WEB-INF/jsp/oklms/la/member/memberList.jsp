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
  ~  * 이진근    2016. 12. 01 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<style type="text/css">
</style>

<c:set var="targetUrl" value="/la/member/member/"/>
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

//         com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		$("#pageIndex").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listMember.do";
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 상세조회 페이지로 이동 */
	function fn_read( param1 ){
		
		$("#memSeq").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "getMember.do";
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	/* 삭제 */
	function fn_delt(){
		if (confirm("삭제 하시겠습니까?")) {
			var checkedVals = com.getCheckedVal('check0','check1');
			
			$("#memSeq").val( checkedVals );
			
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteMemberList.do";
			
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").submit();
		}
	}
	
	/* 신규 페이지로 이동 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMember.do";
		
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	/* 사용자 패스워드 초기화 */
	function fn_updatePassWordInit(  param1, param2 ){
		if(confirm("비밀번호를 초기화 하시겠습니까?\n\n초기화시 비빌번호는 '1111' 로 변경됩니다.")){
			
			$("#memSeq").val( param1 );
			$("#memId").val( param2 );
			
			var reqUrl = CONTEXT_ROOT + targetUrl + "updateMemberPassWordInit.do";
			
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").submit();
		}
	}
	
	//전체 메일보내기
	function fn_allMailSend(){
		popOpenWindow( "", "sendMailStdAll", 600, 560, 100, 100, ',scrollbars=yes' );
		$("#memType").val( 'STD' );
		
		var reqUrl = CONTEXT_ROOT + "/la/popup/popup/goPopupMemberInfoAllMailSendList.do";
		
		$("#frmMember").attr("target","sendMailStdAll");
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	//선택 메일보내기
	function fn_mailSend(){
		var checkedVals = com.getCheckedVal('check0','check1');
		
		if(checkedVals == ''){
			alert(getMessage("EMAIL_CHECK_MEMBER"));
			return;
		}
		
		popOpenWindow( "", "sendMail", 540, 460, 100, 100, ',scrollbars=yes' );
		$("#memSeq").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/popup/popup/goPopupMemberInfoMailSendList.do";
		
		$("#frmMember").attr("target","sendMail");
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	//선택 SMS보내기
	function fn_smsSend(){
		var checkedVals = com.getCheckedVal('check0','check1');
		
		if(checkedVals == ''){
			alert(getMessage("SMS_CHECK_MEMBER"));
			return;
		}
		
		popOpenWindow( "", "sendMail", 540, 460, 100, 100, ',scrollbars=yes' );
		$("#memSeq").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/popup/popup/goPopupMemberInfoSmsSendList.do";
		
		$("#frmMember").attr("target","sendMail");
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	//엑셀 다운로드
	function fn_exclDownload(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listMemberExcelDownload.do";
		
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target","_self");
		$("#frmMember").submit();
	}
	
	/* 단체회원 등록 */
	function fn_excel_upload(){
		
		if($("input:file[name=uploadExcelFile]").val() == ""){
			alert("첨부할 파일이 존재하지 않습니다.");
			return;
		}
		
		var src = $("#uploadExcelFile").val();
		
		 if(!src.match(/\.(xls)$/i)) {
		      alert("엑셀(xls) 파일만 업로드 가능합니다.");
		      return;
		}
		
		if(confirm("작성한 엑셀파일로 회원일괄 등록 하시겠습니까?")){
			
		    var reqUrl = CONTEXT_ROOT + targetUrl + "insertExcelMember.do";
		    
		    $("#frmMemberExcel").attr("method", "post" );
			$("#frmMemberExcel").attr("enctype", "multipart/form-data" );
			$("#frmMemberExcel").attr("action", reqUrl);
			$("#frmMemberExcel").attr("target","_self");
			$("#frmMemberExcel").submit();
		}
	}
	
	/* 단체회원 양식 다운로드 */
	function fn_file_down(){
		var uploadFilePath = "/downloadfiles/";
	    $("#filename").val("allMemberSaveForm.xls");
	    $("#uploadFilePath").val(uploadFilePath);
	    
	    var reqUrl = CONTEXT_ROOT + "/simpleDown.sv";
	    $("#frmDownLoad").attr("action",reqUrl);
	    $("#frmDownLoad").submit();
	}
	
</script>

<!-- 회원정보 팝업용 시작 -->
<form id="frmPop" name="frmPop" method="post">
	<input type="hidden" name="memSeqPop" id="memSeqPop"/>
	<input type="hidden" name="lectureStat" id="lectureStat" value="01"/>
</form>
<form id="frmDownLoad" >
	<input type="hidden"  name="filename" id="filename"  />
	<input type="hidden"  name="uploadFilePath" id="uploadFilePath" />
</form>
<!-- 회원정보 팝업용 끝 -->
<form id="frmMember" name="frmMember" action="<c:url value='/la/member/member/listMember.do'/>" method="post">
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
	<input type="hidden" name="memSeq" id="memSeq"/>
	<input type="hidden" name="memId" id="memId"/>
	<input type="hidden" name="memType" id="memType"/>
	<input type="hidden" name="nowDtInsFlag" id="nowDtInsFlag"/>
	<input type="hidden" name="bndlRegYn" id="bndlRegYn"/>
<ul class="search-list-1">
	<li>
		<span>회원유형</span>
		<select name="searchAuthGroupId" id="searchAuthGroupId" style="width:120px">
	  			<option selected value=''>전체</option>
	  			<c:forEach var="searchAuthGroupCd" items="${searchAuthGroupCode}" varStatus="status">
					<option value="${searchAuthGroupCd.codeId}" ${searchAuthGroupCd.codeId == memberVO.searchAuthGroupId ? 'selected="selected"' : '' }>${searchAuthGroupCd.codeName}</option>
				</c:forEach>
		 </select>
		 &nbsp;
		 <select name="searchName" id="searchName" style="width:70px">
 			<option selected value=''>전체</option>
 			<c:forEach var="searchMemberCd" items="${searchMemberCode}" varStatus="status">
				<option value="${searchMemberCd.codeId}" ${searchMemberCd.codeId == memberVO.searchName ? 'selected="selected"' : '' }>${searchMemberCd.codeName}</option>
			</c:forEach>
		</select>
		&nbsp;
		<select name="searchScsnYn" id="searchScsnYn" style="width:70px">
 			<option selected value=''>전체</option>
 			<c:forEach var="searchScsnYnCd" items="${searchScsnYnCode}" varStatus="status">
				<option value="${searchScsnYnCd.codeId}" ${searchScsnYnCd.codeId == memberVO.searchScsnYn ? 'selected="selected"' : '' }>${searchScsnYnCd.codeName}</option>
			</c:forEach>
		</select>
		&nbsp;
		<input type="text" name="searchWord" id="searchWord" value="" style="width:410px;" />
	</li>
</ul><!-- E : search-list-1 -->
<div class="search-btn-area">
	<a href="#fn_search" onclick="javascript:fn_search(1); return false" onkeypress="this.onclick;">조회</a>
</div>
</form>
<table border="0" cellpadding="5" cellspacing="5" class="">
	<tr><td></td></tr>
</table>
<form id="frmMemberExcel" name="frmMemberExcel">
<ul class="search-list-1">
	<li>
		<span>파일 다운로드</span>
		<a href="javascript:fn_file_down();"><b>일괄 회원등록 양식.xls</b></a>&nbsp;서식파일을 다운받아 파일 양식에 맞게 작성해 주세요.
	</li>
	<li>
		<span>파일 업로드</span>
		<input name="uploadExcelFile" class="form_01 checkFileType" id="uploadExcelFile" type="file" style="width:50%; height:16px;" />
	</li>
</ul><!-- E : search-list-1 -->
</form>

<ul class="board-info">
	<span>검색결과 : 총 </span><span id="totalRow">0</span><span> 건</span>
	<li class="btn-area">
		<span>선택 항목</span>
		<a href="#fn_allMailSend" onclick="javascript:fn_allMailSend( ); return false" onkeypress="this.onclick;" class="btn">전체메일보내기</a>
		<a href="#fn_mailSend" onclick="javascript:fn_mailSend( ); return false" onkeypress="this.onclick;" class="btn">메일보내기</a>
		<a href="#fn_smsSend" onclick="javascript:fn_smsSend( ); return false" onkeypress="this.onclick;" class="btn">SMS보내기</a>
		<a href="#fn_exclDownload" onclick="javascript:fn_exclDownload( ); return false" onkeypress="this.onclick;" class="btn">엑셀다운로드</a>
	</li>
</ul><!-- E : board-info -->

<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="2%" ><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');"/></th>
			<th width="18%">아이디</th>
			<th width="13%">성명</th>
			<th width="15%">권한그룹</th>
			<th width="8%">가입일</th>
			<th width="10%">접속</th>
			<th width="10%">비밀번호<br>초기화</th>
			<th width="7%">상세진입</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td><input type="checkbox" name="check1" id="check1" value='<c:out value="${result.memSeq}"/>'></td>
			<td><c:out value="${result.memId}" /></td>
			<td><c:out value="${result.memName}" /></td>
			<td><c:out value="${result.authGroupName}" /></td>
			<td><c:out value="${result.insertDate}" /></td>
			<td><c:out value="${result.loginCnt}" /></td>
			<td>
				<a href="#fn_updatePassWordInit" onclick="javascript:fn_updatePassWordInit( '<c:out value="${result.memSeq}"/>','<c:out value="${result.memId}"/>'); return false" onkeypress="this.onclick;" class="btn-1">초기화</a>
			</td>
			<td>
				<a href="#fn_read" onclick="javascript:fn_read( '<c:out value="${result.memSeq}"/>'); return false" onkeypress="this.onclick;" class="btn-1">상세</a>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="7"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E : list -->

<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div><!-- E : page-num -->

<div class="page-btn">
	<a href="#" onclick="fn_delt();">탈퇴</a>
	<a href="#" onclick="fn_excel_upload();">엑셀업로드</a>
	<a href="#" onclick="fn_cret();">등록</a>
</div><!-- E : page-btn -->
					

	