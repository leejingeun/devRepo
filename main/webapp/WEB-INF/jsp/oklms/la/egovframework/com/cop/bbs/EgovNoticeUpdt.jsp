<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : /la/egovframework/com/cop/bbs/EgovNoticeUpdt.jsp
  * @Description : 게시물 수정 화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.19   이삼섭          최초 생성
  *   2011.09.15   서준식          유효기간 시작일이 종료일보다 빠른지 체크하는 로직 추가
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.19
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- la : EgovNoticeUpdt.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<style>
<!--
table.htmlarea_30_table td {
/*  @import url(/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.css); */
 padding : 1px 1px 1px 1px;
 } 
-->
</style>
<script type="text/javascript">
//_editor_area = "nttCn";
//_editor_url = "<c:url value='/html/egovframework/com/cmm/utl/htmlarea3.0/'/>";
</script>

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<%-- <script type="text/javascript" src="<c:url value='/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js'/>" ></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="${contextRootJS }/common/smartEditor/js/HuskyEZCreator.js"></script>
<validator:javascript formName="board" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
	var oEditors = [];
	
	$(document).ready(function() {
		loadPage();	
	});
	
	/*====================================================================
	jqGrid 및 화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
		initEditor();
	}

	/* 각종 버튼에 대한 액션 초기화 */ 
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
		$("#nttSj").focus();
		makeFileAttachment();
		//공지사항 게시판만 달력 표시
		<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
			com.datepicker('ntceBgndeView');
			com.datepicker('ntceEnddeView');
		</c:if>
	}

	/* 화면이 로드된후 에디터 기본옵션 설정 초기화 */ 
	function initEditor() {
		//Smart Editor
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "nttCn",
			sSkinURI: "/common/smartEditor/SmartEditor2Skin.html",	
			htParams : {bUseToolbar : true,
				fOnBeforeUnload : function(){
					//alert("아싸!");	
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["textAreaContent"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});
	}

	/*====================================================================
	사용자 정의 함수 
	====================================================================*/
	
	/* 입력 필드 초기화 */ 
	function fn_formReset(){
		$("#board").find("select,textarea,radio").val("");
		$("#nttSj").val('');
		$("#ntceBgndeView").val('');
		$("#ntceEnddeView").val('');
		oEditors.getById["nttCn"].exec("SET_CONTENTS", [""]); 
		$("#nttSj").focus();
	}
	
	/* 입력 필드 유효성 첵크 */
	function fn_egov_validateForm(obj){
		if( !com.checkRequiredField( $("#nttSj") ) ){
			return;
		}
		
		var data =oEditors.getById["nttCn"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("필수항목을 입력해 주세요.");
			oEditors.getById["nttCn"].exec("FOCUS"); 
			return false;
		}
	
		if( !com.checkRequiredField( $("#ntceBgndeView") ) ){
			return;
		}
		if( !com.checkRequiredField( $("#ntceEnddeView") ) ){
			return;
		}
		
		if(com.diffDate($("#ntceBgndeView").val(),$("#ntceEnddeView").val()) < 0){
			alert("게시기간 종료일자가 시작일자보다 이전입니다.\n확인 해주세요.");
			$("#ntceEnddeView").focus();
			return false;
		}
		
		$("#nttCn").val(data);
		$("#ntceBgnde").val($("#ntceBgndeView").val());
		$("#ntceEndde").val($("#ntceEnddeView").val());
		
		return true;
	}
	
	/* 화면입력폼 정보 저장 */
	function fn_egov_regist_notice(){
		
		/* if (!validateBoard(document.board)){
			return;
		} */
		
		if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
			document.board.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/updateBoardArticle.do'/>";
			document.board.submit();
		}
	}

	/* 목록화면으로 이동 */
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
		document.board.submit();
	}

	/* 첨부파일 정보 셋팅 */
	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}
	
	/* 첨부파일 정보 셋팅 */
	function makeFileAttachment(){
	<c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">

		var existFileNum = document.board.fileListCnt.value;
		var maxFileNum = document.board.posblAtchFileNumber.value;

		if (existFileNum=="undefined" || existFileNum ==null) {
			existFileNum = 0;
		}
		if (maxFileNum=="undefined" || maxFileNum ==null) {
			maxFileNum = 0;
		}
		var uploadableFileNum = maxFileNum - existFileNum;
		if (uploadableFileNum<0) {
			uploadableFileNum = 0;
		}
		if (uploadableFileNum != 0) {
			fn_egov_check_file('Y');
			var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), uploadableFileNum );
			multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
		} else {
			fn_egov_check_file('N');
		}
	</c:if>
	}
	
	/* 달력 정보 초기화 */
	function fn_calendarClear(Obj) {
		document.getElementById(Obj).value=""; 
	}
</script>

<!-- body onload="javascript:editor_generate('nttCn');"-->
<!-- <body onLoad="HTMLArea.init(); HTMLArea.onload = initEditor; document.board.nttSj.focus(); makeFileAttachment();"> -->

<div class="title-name-1"><c:out value='${bdMstr.bbsNm}'/> - 게시글수정</div>
<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="returnUrl" value="<c:url value='/la/cop/bbs/${pathBbsIdStr}/forUpdateBoardArticle.do'/>"/>

<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" />
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" />

<input type="hidden" name="bbsAttrbCode" value="<c:out value='${bdMstr.bbsAttrbCode}'/>" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${bdMstr.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${bdMstr.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${bdMstr.tmplatId}'/>" />

<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="ntcrNm" value="dummy">	<!-- validator 처리를 위해 지정 -->
<input type="hidden" name="password" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<c:if test="${bdMstr.bbsAttrbCode != 'BBSA01'}">
   <input id="ntceBgnde" name="ntceBgnde" type="hidden" value="10000101">
   <input id="ntceEndde" name="ntceEndde" type="hidden" value="99991231">
   <input id="ntceBgndeView" name="ntceBgndeView" type="hidden" value="10000101">
   <input id="ntceEnddeView" name="ntceEnddeView" type="hidden" value="99991231">
</c:if>

<c:if test="${bdMstr.replyPosblAt == 'Y'}">
<input name="topNoticeYn" id="topNoticeYn" type="hidden" value="N">
</c:if>

<%-- <input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}'/>" > --%>
<input type="hidden" name="replyPosblAt" value="<c:out value='${result.replyPosblAt}'/>" >

<%-- <table width="100%" cellpadding="8" class="table-search" border="0">
<tr>
	<td width="100%"class="title_left">
	<img src="<c:url value='/images/egovframework/com/cmm/icon/tit_icon.gif' />" width="16" height="16" hspace="3" align="middle" alt="제목버튼이미지">&nbsp;<c:out value='${bdMstr.bbsNm}'/> - 게시글 수정</td>
</tr>
</table> --%>

<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th><spring:message code="cop.nttSj" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td colspan="3"><input name="nttSj" id="nttSj" type="text" size="60" value='<c:out value="${result.nttSj}" />'  maxlength="60" title="제목수정"><br/><form:errors path="nttSj" /></td>
		</tr>
		<tr>
			<th><spring:message code="cop.nttCn" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td colspan="3"><table class="htmlarea_30_table" width="100%" border="0" cellpadding="0" cellspacing="0" class="noStyle">
				<tr><td>
					<%-- <textarea id="nttCn" name="nttCn" class="textarea" cols="75" rows="28" style="width:550px; height:300px;" title="내용입력"><c:out value="${result.nttCn}" escapeXml="false" /></textarea><form:errors path="nttCn" /> --%>
					<textarea name="nttCn" id="nttCn" style="width: 100%;height: 500px;" rows="20" cols="80"><c:out value="${result.nttCn}" /></textarea><form:errors path="nttCn" />
				</td></tr>
				</table></td>
		</tr>
		<%--
		<tr>
			<th>팝업 파일</th>
			<td colspan="3">
				<input type="text" id="fileName-1" style="width:40%;" readonly="readonly">
				<span>
					<a href="#@" class="checkfile">찾아보기</a>
					<input type="file" class="file_input_hidden" onchange="javascript: document.getElementById('fileName-1').value = this.value" />
				</span>
			</td>
		</tr>
		 --%>
  <c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
	<tr>
		<th><spring:message code="cop.noticeTerm" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
		<td colspan="3">					
			<%-- <input id="ntceBgnde" name="ntceBgnde" type="hidden" value='<c:out value="${result.ntceBgnde}" />'>
			<input name="ntceBgndeView" type="text" size="10" value="${fn:substring(result.ntceBgnde, 0, 4)}-${fn:substring(result.ntceBgnde, 4, 6)}-${fn:substring(result.ntceBgnde, 6, 8)}"  readOnly title="게시시작일자수정" onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceBgnde, document.board.ntceBgndeView);" >
			~
			<input id="ntceEndde" name="ntceEndde" type="hidden"  value='<c:out value="${result.ntceEndde}" />'>
			<input name="ntceEnddeView" type="text" size="10" value="${fn:substring(result.ntceEndde, 0, 4)}-${fn:substring(result.ntceEndde, 4, 6)}-${fn:substring(result.ntceEndde, 6, 8)}"  readOnly title="게시종료일자수정" onClick="javascript:fn_egov_NormalCalendar(document.board, document.board.ntceEndde, document.board.ntceEnddeView);"  >
			<form:errors path="ntceBgndeView" /><form:errors path="ntceEnddeView" /> --%>
			
			<input type="text" id="ntceBgndeView" name="ntceBgndeView" value='<c:out value="${result.ntceBgnde}" />' style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('ntceBgndeView');"/>
		    <input id="ntceBgnde" name="ntceBgnde" type="hidden">
		    <form:errors path="ntceBgndeView" />
		  	~ 
			<input type="text"  id="ntceEnddeView" name="ntceEnddeView" value='<c:out value="${result.ntceEndde}" />' style="width: 70px;" readonly="readonly" /><img src="/images/oklms/adm/inc/calendar_btn_02_x.gif" onclick="fn_calendarClear('ntceEnddeView');"/> 
			<input id="ntceEndde" name="ntceEndde" type="hidden">
			<form:errors path="ntceEnddeView" />
		</td>
	</tr>
  </c:if>
  <!-- 답장 가능 여부가 불가능한 게시판만 TOP공지 옵션을 표시함. -->
<c:if test="${bdMstr.replyPosblAt == 'N'}">
<tr>
	<th>상단노출</th>
	<td>
		<input name="topNoticeYnTemp" id="topNoticeYnTemp" type="hidden" value="${result.topNoticeYn}" />
		<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  ${result.topNoticeYn == 'N' ? 'checked' : '' }  />
        미노출
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y"   ${result.topNoticeYn == 'Y' ? 'checked' : '' }  />
        노출 
      	</td>
	</td>
</tr>
</c:if>
  <c:if test="${anonymous == 'true'}">
	<tr>
		<th><spring:message code="cop.ntcrNm" /></th>
		<td colspan="3"><input name="ntcrNm" type="text" size="20" value='<c:out value="${result.ntcrNm}" />'  maxlength="10" title="작성자이름"></td>					
	</tr>
	<tr>
		<th><spring:message code="cop.password" /></th>
		<td colspan="3"><input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력"></td>
	</tr>
  </c:if>
  <c:if test="${not empty result.atchFileId}">
	<tr>
		<th><spring:message code="cop.atchFileList" /></th>
		<td colspan="6">
			<c:import url="/cmm/fms/selectFileInfsForUpdate.do" charEncoding="utf-8">
			<c:param name="param_atchFileId" value="${result.atchFileId}" />
			</c:import>
		</td>
	</tr>
  </c:if>
  <c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">
<%-- 	  	<c:if test="${result.atchFileId == ''}"> --%>
		<input type="hidden" name="fileListCnt" value="0" />
<%-- 	  	</c:if> --%>
	<tr>
		<th><spring:message code="cop.atchFile" /></th>
		<td colspan="3">
			<div id="file_upload_posbl"  style="display:none;" >
				<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<td>
						<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/>
<!-- 												<input type="text" id="fileName-1" style="width:40%;" readonly="readonly"> -->
<!-- 												<span> -->
<!-- 													<a href="#@" class="checkfile">찾아보기</a> -->
<!-- 													<input type="file" name="file_1" id="egovComFileUploader" class="file_input_hidden" onchange="javascript: document.getElementById('fileName-1').value = this.value" /> -->
<!-- 												</span> -->
					</td>
				</tr>
				<tr>
					<td><div id="egovComFileList"></div></td>
				</tr>
				</table>
			</div>
			<div id="file_upload_imposbl"  style="display:none;" >
				<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
				<tr>
					<td><spring:message code="common.imposbl.fileupload" /></td>
				</tr>
				</table>
			</div>
	 </tr>
  </c:if>
	</tbody>
</table><!-- E : view-1 -->
</form:form>

<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_regist_notice();return false;">저장</a>
	<a href="#@" onclick="fn_formReset();return false;">초기화</a>
<c:if test="${bdMstr.authFlag == 'Y'}">
<c:if test="${result.frstRegisterId == searchVO.frstRegisterId}">
	<!-- <a href="javascript:fn_egov_regist_notice();"><spring:message code="button.update" /></a> -->
</c:if>
</c:if>
	<!-- <a href="javascript:fn_egov_select_noticeList();"><spring:message code="button.list" /></a> -->
	<a href="#@" onclick="javascript:fn_egov_select_noticeList();return false;">목록</a>
</div><!-- E : page-btn -->
