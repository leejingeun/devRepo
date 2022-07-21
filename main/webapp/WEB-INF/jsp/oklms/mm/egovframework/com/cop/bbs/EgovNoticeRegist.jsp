<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<%
 /**
  * @Class Name : /lu/egovframework/com/cop/bbs/EgovNoticeRegist.jsp
  * @Description : 게시물 등록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2016.12.30   이진근          최초 생성
  *
  */
%>
<!-- la : EgovNoticeRegist.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
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
	function fn_egov_validateForm() {		
		if( !com.checkRequiredField( $("#nttSj") ) ){
			return;
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
		
		var data =oEditors.getById["nttCn"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("필수항목을 입력해 주세요.");
			oEditors.getById["nttCn"].exec("FOCUS"); 
			return false;
		}
		
		$("#nttCn").val(data);
		$("#ntceBgnde").val($("#ntceBgndeView").val());
		$("#ntceEndde").val($("#ntceEnddeView").val());
		
		return true;
	}

	/* 화면입력폼 정보 저장 */
	function fn_egov_regist_notice() {
	
	/* if (!validateBoard(document.board)){
		return;
	} */

		if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {	
			document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/insertBoardArticle.do'/>";
			document.board.submit();
		}
	}

	/* 목록화면으로 이동 */
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
		document.board.submit();
	}
	
	/* 첨부파일 정보 셋팅 */
	function makeFileAttachment(){
	<c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">
		 var maxFileNum = document.board.posblAtchFileNumber.value;
	     if(maxFileNum==null || maxFileNum==""){
	    	 maxFileNum = 3;
	     }
		 var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), maxFileNum );
		 multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	</c:if>
	}
	
	/* 달력 정보 초기화 */
	function fn_calendarClear(Obj) {
		document.getElementById(Obj).value=""; 
	}
</script>
<%-- <title><c:out value='${bdMstr.bbsNm}'/> - 게시글쓰기</title> --%>

<!-- body onload="javascript:editor_generate('nttCn');"-->
<!-- <body onLoad="HTMLArea.init(); HTMLArea.onload = initEditor; document.board.nttSj.focus(); makeFileAttachment();"> -->


<!-- S : 입력 폼 영역 -->
<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="bbsId" value="<c:out value='${bdMstr.bbsId}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${bdMstr.bbsAttrbCode}'/>" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${bdMstr.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${bdMstr.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${bdMstr.tmplatId}'/>" />

<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${bdMstr.authFlag}'/>" />

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

<input type="hidden" id="yyyy" name="yyyy"  value="<c:out value='${yyyy}'/>" />
<input type="hidden" id="term" name="term"  value="<c:out value='${term}'/>" />
<input type="hidden" id="subjectCode" name="subjectCode"  value="<c:out value='${subjectCode}'/>" />
<input type="hidden" id="subClass" name="subClass"  value="<c:out value='${subClass}'/>" />

<c:if test="${bdMstr.replyPosblAt == 'Y'}">
<input name="topNoticeYn" id="topNoticeYn" type="hidden" value="N">
</c:if>

<div class="content">
		<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000013'}">
		<h1>공지사항-게시글쓰기</h1>
		</c:if>
		<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000014'}">
		<h1>Q&A-게시글쓰기</h1>
		</c:if>
		<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000015'}">
		<h1>학습자료실-게시글쓰기</h1>
		</c:if>	
			<table class="table_write">
				<colgroup>
					<col style="width:150px" />
					<col style="width:*" />
					<col style="width:150px" />
					<col style="width:*" />
				</colgroup>
			         	<tr>
					<th>제목<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td colspan="3" class="none">
						<input type="text" name="nttSj" id="nttSj"  maxlength="60" value="" style="width:95%;"/>
					</td>
				</tr>
				<tr>
					<th>학년도<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td><c:out value='${yyyy}'/>학년도</td>
					<th>학기<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td class="none"><c:out value='${term}'/>학기</td>
				</tr>
				<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
				<tr>
					<th>상단노출<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td>
					<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  checked  />
			        미노출
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y"   />
			        노출 
			      	</td>
					<th>게시기간<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td class="none">
					<input type="text" id="ntceBgndeView" name="ntceBgndeView" style="width:100px;" readonly /><input type="button" class="table_delete_top"  onclick="fn_calendarClear('ntceBgndeView');" /> ~
					<input type="text" id="ntceEnddeView" name="ntceEnddeView" style="width:100px;" readonly /><input type="button" class="table_delete_top"  onclick="fn_calendarClear('ntceEnddeView');" />
					<input id="ntceBgnde" name="ntceBgnde" type="hidden">
					<input id="ntceEndde" name="ntceEndde" type="hidden"> 
					</td>
				</tr>
				</c:if>
				<c:if test="${bdMstr.bbsAttrbCode != 'BBSA01'}">
				<c:if test="${bdMstr.replyPosblAt == 'N'}"><!-- 답장 가능 여부가 불가능한 게시판만 TOP공지 옵션을 표시함. -->
				<tr>
					<th>상단노출</td>
					<td class="none" colspan="3">
					<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  checked  />
			        미노출
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y"   />
			        노출 
			      	</td>
				</tr>
				</c:if>
				</c:if>
				<tr>
					<th>내용<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></td>
					<td colspan="3" class="none"><textarea name="nttCn" id="nttCn" style="width: 100%;height: 325px;" rows="20" cols="80"></textarea></td>
				</tr>
				<c:if test="${bdMstr.fileAtchPosblAt == 'Y'}">
				<tr>
					<th>첨부파일</th>
					<td colspan="3" class="none">
						<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/>
					</td>
				</tr>
				<tr>
					<th>첨부파일 목록</th>
					<td colspan="3" class="none">
						<div id="egovComFileList"></div>
					</td>
				</tr>
				</c:if>
			</table>

			<!--
			회원유형(memType) 아래권한을 가진 사용자만 초기화 및 등록버튼 활성화
			담당교수     : PRT,
			지도교수     : ATT,
			기업현장교사 : COT  
			-->
			<div class="btn_left"><a href="#@" onclick="fn_egov_select_noticeList();return false;"><input type="button" class="black" value="목록" /></a></div>
			<c:if test="${memType == 'PRT'||memType == 'ATT'||memType == 'COT'}">
			<div class="btn_right">
				<a href="#@" onclick="fn_formReset();return false;"><input type="button" class="yellow" value="초기화" /></a>
				<a href="#@" onclick="fn_egov_regist_notice();return false;"><input type="button" class="orenge" value="저장" /></a>
			</div>
			</c:if>
	</div>
<!-- E : view-1 -->
</form:form>



