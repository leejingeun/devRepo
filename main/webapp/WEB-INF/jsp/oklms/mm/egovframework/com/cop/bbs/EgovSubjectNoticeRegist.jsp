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
  * @Class Name : /mm/egovframework/com/cop/bbs/EgovNoticeRegist.jsp
  * @Description : 게시물 등록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2016.12.30   이진근          최초 생성
  *
  */
%>
<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextRootJS }/js/oklms/common.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/plugins/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery.maskedinput.js"></script>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="board" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	var oEditors = [];

	$(document).ready(function() {
		loadPage();
		$('#file-input').on("change", previewImages);
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
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
		$("#nttSj").focus();
		//makeFileAttachment();
		//공지사항 게시판만 달력 표시
		<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
			com.datepicker('ntceBgndeView');
			com.datepicker('ntceEnddeView');
		</c:if>
	}
 
/*====================================================================
사용자 정의 함수
====================================================================*/

	/* 입력 필드 초기화 */
	function fn_formReset(){
		$("#board").find("select,textarea,radio").val("");
		$("#nttSj").val('');
		<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
		$("#ntceBgndeView").val('');
		$("#ntceEnddeView").val('');
		</c:if>
		oEditors.getById["nttCn"].exec("SET_CONTENTS", [""]);
		$("#nttSj").focus();
	}

	/* 입력 필드 유효성 첵크 */
	function fn_egov_validateForm() {
 

		if( !com.checkRequiredField( $("#nttSj") ) ){
			return;
		}
		if( !com.checkRequiredField( $("#nttCn") ) ){
			return;
		}		
		<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">

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
		$("#ntceBgnde").val($("#ntceBgndeView").val());
		$("#ntceEndde").val($("#ntceEnddeView").val());
		</c:if>
		return true;
	}

	/* 화면입력폼 정보 저장 */
	function fn_egov_regist_notice() {
 
		if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
			document.board.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/insertBoardArticle.do'/>";
			document.board.submit();
		}
	}

	/* 목록화면으로 이동 */
	function fn_egov_select_noticeList(pageNo) {
		document.board.pageIndex.value = pageNo;
		document.board.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
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

	function fn_egov_change_category() {
		document.board.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
		document.board.submit();
	}

	function fn_egov_change() {
		var subjectTraningtype = $("#subjectTraningtype").val();
		if(subjectTraningtype == ''){
			alert("과정구분을 선택하세요.");
			return false;
		}
		document.board.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
		document.board.submit();
	}

	function previewImages() {
		  var $preview = $('#preview').empty();
		  $("#fileName").val($('#file-input').val());
		  var filesize = 0;
		  if (this.files) {
			  $.each(this.files, readAndPreview);
		  }

		  function readAndPreview(i, file) {
			if (window.FileReader) { // FireFox, Chrome, Opera 확인.
				//if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
				//      return alert(file.name +" is not an image");
			    //} // else...
			    filesize = filesize + file.size;
				if(filesize > 10000000){ //Checking Sum 10M Size
					alert("전체 사이즈 10M이상 업로드 할수 없습니다.");

					$("#file1").val("");

					return false;
				}else{

					var filesizeNumber = "";
					if(filesize>1000000){
						filesizeNumber = ((filesize/1000000).toFixed(2))+" M";
					}else if(filesize>1000){
						filesizeNumber = ((filesize/1000).toFixed(2))+" KB";
					}else{
						filesizeNumber = filesize+" B";
					}

					$("#fileSizeName").html( filesizeNumber +" / 10M");
				}
			}else{ // safari is not supported FileReader
		        alert('not supported Webbrowser!!');
		    }
		  }
	}

</script>
 

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
<input type="hidden" name="authFlag" value="<c:out value='${bdMstr.authFlag}'/>" />
<input type="hidden" name="lectureMenuMarkYn" value="<c:out value='${lectureMenuMarkYn}'/>" />

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

<input type="hidden" id="yyyy" name="yyyy"  value="<c:out value='${searchVO.yyyy}'/>" />
<input type="hidden" id="term" name="term"  value="<c:out value='${searchVO.term}'/>" />
<input type="hidden" id="subjectCode" name="subjectCode"  value="<c:out value='${searchVO.subjectCode}'/>" />
<input type="hidden" id="subClass" name="subClass"  value="<c:out value='${searchVO.subClass}'/>" />

<input type="file" style="display:none;" id="file-input" name="file-input"  value="" />


<div id="container">
	 

	<div id="contents-area">
	
		<dl class="bbs-view mt-020"> 
			<dt>
				<span>				 						
					<input type="text" id="nttSj" name="nttSj" maxlength="60" placeholder="(ex)제목 입력" style="width:99%;" />				
				</span>
			</dt>
			<dd>
				<textarea id="nttCn" name="nttCn" style="width: 100%;height: 125px;"  placeholder="(ex)내용 입력"></textarea>
			</dd>
			<c:if test="${result.ntceBgnde != '10000101'}">
				<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
			<dd>
				<span>게시기간</span> : 
					<input type="text" id="ntceBgndeView" name="ntceBgndeView"  style="width:100px;" readonly /> ~
					<input type="text" id="ntceEnddeView" name="ntceEnddeView"  style="width:100px;" readonly />
					<input id="ntceBgnde" name="ntceBgnde" type="hidden">
					<input id="ntceEndde" name="ntceEndde" type="hidden">
			</dd>
				</c:if>
			</c:if>
			<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
			<dd>
				<span>상단노출</span> : 		
								<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  checked  />
						        미노출
						        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y"   />
						        노출
						        
			</dd> 
			</c:if>
		</dl>
		 

	<div class="btn-area align-right mt-010">
		<a href="#fn_egov_select_noticeList" onclick="javascript:fn_egov_select_noticeList('1'); return false" class="gray-1 float-left">목록</a>
		<a href="#fn_egov_regist_notice" onclick="javascript:fn_egov_regist_notice(); return false" class="orange">등록</a>
	</div><!-- E : btn-area -->
	
	</div><!-- E : content-area -->
</div><!-- E : content-area -->


</form:form>



