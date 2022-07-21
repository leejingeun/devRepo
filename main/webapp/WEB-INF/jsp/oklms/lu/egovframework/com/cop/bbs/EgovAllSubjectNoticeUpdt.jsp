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
<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="${contextRootJS }/common/smartEditor/js/HuskyEZCreator.js"></script>
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
		initEditor();
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
		var categoryCd = $("input:radio[name='categoryCd']:checked").val();
		if(categoryCd == '02'){
			if( !com.checkRequiredField( $("#subjectTraningtype") ) ){
				return;
			}
			if( !com.checkRequiredField( $("#subjectName") ) ){
				return;
			}
			if( !com.checkRequiredField( $("#subClass") ) ){
				return;
			}
		}

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
	function fn_egov_regist_notice() {

	/* if (!validateBoard(document.board)){
		return;
	} */

		if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
			document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/updateBoardArticle.do'/>";
			document.board.submit();
		}
	}

	/* 목록화면으로 이동 */
	function fn_egov_select_noticeList() {
		document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
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

	function fn_egov_change_category() {
		document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
		document.board.submit();
	}

	function fn_egov_change() {
		var subjectTraningtype = $("#subjectTraningtype").val();
		if(subjectTraningtype == ''){
			alert("과정구분을 선택하세요.");
			return false;
		}
		document.board.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
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
<%-- <title><c:out value='${bdMstr.bbsNm}'/> - 게시글쓰기</title> --%>

<!-- body onload="javascript:editor_generate('nttCn');"-->
<!-- <body onLoad="HTMLArea.init(); HTMLArea.onload = initEditor; document.board.nttSj.focus(); makeFileAttachment();"> -->


<!-- S : 입력 폼 영역 -->
<form:form commandName="board" name="board" method="post" enctype="multipart/form-data" >
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="bbsId" value="<c:out value='${bdMstr.bbsId}'/>" />
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${bdMstr.bbsAttrbCode}'/>" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${bdMstr.bbsTyCode}'/>" />
<input type="hidden" name="replyPosblAt" value="<c:out value='${bdMstr.replyPosblAt}'/>" />
<input type="hidden" name="fileAtchPosblAt" value="<c:out value='${bdMstr.fileAtchPosblAt}'/>" />
<input type="hidden" name="posblAtchFileNumber" value="<c:out value='${bdMstr.posblAtchFileNumber}'/>" />
<input type="hidden" name="posblAtchFileSize" value="<c:out value='${bdMstr.posblAtchFileSize}'/>" />
<input type="hidden" name="tmplatId" value="<c:out value='${bdMstr.tmplatId}'/>" />
<input type="hidden" name="lectureMenuMarkYn" value="<c:out value='${lectureMenuMarkYn}'/>" />

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

<c:if test="${bdMstr.replyPosblAt == 'Y'}">
<input name="topNoticeYn" id="topNoticeYn" type="hidden" value="N">
</c:if>

<div id="">
	<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000005'}">
	<h2>전체 공지사항-게시글수정</h2>
	</c:if>
	<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000007'}">
	<h2>전체 Q&A-게시글수정</h2>
	</c:if>
	<c:if test="${bdMstr.bbsId == 'BBSMSTR_000000000006'}">
	<h2>전체 학습자료실-게시글수정</h2>
	</c:if>
	<table class="type-write">
		<colgroup>
			<col style="width:150px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<c:if test="${bdMstr.bbsId != 'BBSMSTR_000000000007'}">
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">카테고리</th>
				<td>
					<input type="radio" id="categoryCd" name="categoryCd" value="01" onchange="javascript:fn_egov_change_category();" class="choice" <c:if test="${ '01' eq result.categoryCd || '' eq result.categoryCd }"> checked="checked"</c:if> disabled="disabled" /> 전체공지&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="categoryCd" name="categoryCd" value="02" onchange="javascript:fn_egov_change_category();" class="choice" <c:if test="${ '02' eq result.categoryCd }"> checked="checked"</c:if> disabled="disabled" /> 과정공지&nbsp;&nbsp;
					<c:if test="${memType eq 'CDP'}">
						<input type="hidden" id="subjectTraningtype" name="subjectTraningtype" value="OFF">
					</c:if>
					<c:if test="${memType eq 'COT'}">
						<input type="hidden" id="subjectTraningtype" name="subjectTraningtype" value="OJT">
					</c:if>
					<c:if test="${memType eq 'PRT'}">
					과정구분:<select id="subjectTraningtype" name ="subjectTraningtype" onchange="javascript:fn_egov_change();" class="mr-025" disabled="disabled">
						<option value="" selected="selected">::선택::</option>
						<c:forEach var="prtSubjectTypeCd" items="${prtSubjectTypeCode}" varStatus="status">
							<option value="${prtSubjectTypeCd.codeId}" ${prtSubjectTypeCd.codeId == result.subjectTraningType ? 'selected' : '' }>${prtSubjectTypeCd.codeName}</option>
						</c:forEach>
					</select>
					</c:if>
					개설강좌:<select id="subjectName" name ="subjectName" onchange="javascript:fn_egov_change();" class="mr-025" disabled="disabled">
						<option value="" selected="selected">::선택::</option>
						<c:forEach var="prtSubjectCd" items="${prtSubjectCode}" varStatus="status">
							<option value="${prtSubjectCd.codeId}" ${prtSubjectCd.codeName == result.subjectName ? 'selected' : '' }>${prtSubjectCd.codeName}</option>
						</c:forEach>
					</select>
					분반:<select id="subClass" name ="subClass" onchange="" class="mr-025" disabled="disabled">
						<option value="" selected="selected">::선택::</option>
						<c:forEach var="prtClassCd" items="${prtClassCode}" varStatus="status">
							<option value="${prtClassCd.codeId}" ${prtClassCd.codeId == result.subClass ? 'selected' : '' }>${prtClassCd.codeName}분반</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			</c:if>
			<c:if test="${bdMstr.bbsId eq 'BBSMSTR_000000000007'}">
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">카테고리</th>
				<td>
					<input type="hidden" id="categoryCd" name="categoryCd" value="02">
					과정구분:${subjectTraningtype}&nbsp;&nbsp;
					개설강좌:${result.subjectName}&nbsp;&nbsp;
					분반:${result.subClass}
				</td>
			</tr>
			</c:if>
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">제목</th>
				<td><input type="text" id="nttSj" name="nttSj" maxlength="60" placeholder="(ex)제목 입력" value='<c:out value="${result.nttSj}" />' style="width:99%;" /></td>
			</tr>
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">내용</th>
				<td><textarea id="nttCn" name="nttCn" style="width: 100%;height: 325px;" rows="20" cols="80" placeholder="(ex)내용 입력"><c:out value="${result.nttCn}" /></textarea></td>
			</tr>
			<c:if test="${bdMstr.bbsAttrbCode == 'BBSA01'}">
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">게시기간</th>
				<td>
					<input type="text" id="ntceBgndeView" name="ntceBgndeView" value='<c:out value="${result.ntceBgnde}" />' placeholder="(ex)2017.04.01" style="width:100px;" readonly /> ~
					<input type="text" id="ntceEnddeView" name="ntceEnddeView" value='<c:out value="${result.ntceEndde}" />' placeholder="(ex)2017.04.30" style="width:100px;" readonly />
					<input id="ntceBgnde" name="ntceBgnde" type="hidden">
					<input id="ntceEndde" name="ntceEndde" type="hidden">
				</td>
			</tr>
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">상단노출</th>
				<td>
					<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  ${result.topNoticeYn == 'N' ? 'checked' : '' } />
			        미노출
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y" ${result.topNoticeYn == 'Y' ? 'checked' : '' } />
			        노출
				</td>
			</tr>
			</c:if>
			<c:if test="${bdMstr.bbsAttrbCode != 'BBSA01'}">
			<c:if test="${bdMstr.replyPosblAt == 'N'}"><!-- 답장 가능 여부가 불가능한 게시판만 TOP공지 옵션을 표시함. -->
			<tr>
				<th><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">상단노출</th>
				<td>
					<input name="topNoticeYn" id="topNoticeYn" type="radio" value="N"  ${result.topNoticeYn == 'N' ? 'checked' : '' } />
			        미노출
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        <input name="topNoticeYn" id="topNoticeYn" type="radio" value="Y" ${result.topNoticeYn == 'Y' ? 'checked' : '' } />
			        노출
				</td>
			</tr>
			</c:if>
			</c:if>
			<tr>
				<th>첨부파일</th>
				<td class="left">
				<c:if test="${!empty resultFile}">
					<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:com.deleteFile('${resultFile.atchFileId}|${resultFile.fileSn}', '/lu/cop/bbs${prefix}/${pathBbsIdStr}/forUpdateBoardArticle.do?bbsId=${bdMstr.bbsId}&nttId=${result.nttId}' )">[삭제]</a><br />
				</c:if>
				<c:if test="${empty resultFile}">
					<input type="text" id="file1" name="file1" style="width:50%;" readonly>
					<span>
						<a href="#@" class="checkfile">파일선택</a>
						<input type="file" class="file_input_hidden" name="file-input" id="file-input" onchange="javascript: document.getElementById('file1').value = this.value" />
					</span>
					<span id="fileSizeName">0KB / 10M</span>
				</c:if>

				<input type="hidden" id="atchFileId" name="atchFileId" value="${resultFile.atchFileId}">
				</td>
			</tr>
		</tbody>
	</table><!-- E : write -->

	<div class="btn-area align-right mt-010">
		<a href="#!" onclick="javascript:fn_egov_select_noticeList(); return false" class="gray-1 float-left">목록</a>
		<a href="#!" onclick="javascript:fn_egov_regist_notice(); return false" class="orange">등록</a>
	</div><!-- E : btn-area -->

</div><!-- E : content-area -->


</form:form>



