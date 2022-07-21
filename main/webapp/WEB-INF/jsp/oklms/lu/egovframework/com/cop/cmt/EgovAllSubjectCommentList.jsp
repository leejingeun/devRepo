<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovCommentList.jsp
  * @Description : 댓글
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2017.01.02   이진근          최초 생성
  *
  */
%>

<%-- <c:if test="${type == 'head'}"> --%>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="comment" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
$(document).ready(function() {
	$("#commentCn").focus();
	$('#file-input').on("change", commentPreviewImages);
});

function fn_egov_insert_commentList() {

	/* 	if (!validateComment(document.frm)){
		return;
	} */
//alert("댓글저장");

	if( !com.checkRequiredField( $("#commentCn") ) ){
		return;
	}

	if (confirm('<spring:message code="common.regist.msg" />')) {
		document.frm.action = "<c:url value='/lu/cop/bbs/${bbsId}/insertBoardComment.do'/>";
		document.frm.submit();
	}
}

function fn_egov_updt_commentList() {
	/* if (!validateComment(document.frm)){
		return;
	} */
	//alert("댓글수정");

	if( !com.checkRequiredField( $("#commentCn") ) ){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		document.frm.modified.value = "true";
		document.frm.action = "<c:url value='/lu/cop/bbs/${bbsId}/updateBoardComment.do'/>";
		document.frm.submit();
	}
}

function fn_egov_selectCommentForupdt(commentNo, index, bbsId) {
	var param_bbsId = bbsId;

	<c:if test="${anonymous == 'true'}">
	var passwordObject;

	if (typeof(document.frm.testPassword.length) == 'undefined') {
		password = document.frm.testPassword;
	} else {
		password = document.frm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	document.frm.confirmPassword.value = password.value;
	</c:if>

	document.frm.commentNo.value = commentNo;
	document.frm.bbsId.value = param_bbsId;

	if(param_bbsId != null){
		document.frm.action = "<c:url value='/lu/cop/bbs/${bbsId}/selectBoardArticle.do'/>";
	}else{
		document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/selectBoardArticle.do'/>";
	}

	document.frm.submit();
}

function fn_egov_deleteCommentList(commentNo, index) {
	<c:if test="${anonymous == 'true'}">
	var passwordObject;

	if (typeof(document.frm.testPassword.length) == 'undefined') {
		password = document.frm.testPassword;
	} else {
		password = document.frm.testPassword[index];
	}

	if ("<c:out value='${anonymous}'/>" == "true" && password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		password.focus();
		return;
	}

	document.frm.confirmPassword.value = password.value;
	</c:if>

	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.frm.modified.value = "true";
		document.frm.commentNo.value = commentNo;
		document.frm.action = "<c:url value='/lu/cop/bbs/${bbsId}/deleteBoardComment.do'/>";
		document.frm.submit();
	}
}

function commentPreviewImages() {
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

				$("#file2").val("");

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

/* function fn_egov_select_commentList(pageNo) {
		document.frm.subPageIndex.value = pageNo;
		document.frm.commentNo.value = '';
		document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/selectBoardArticle.do'/>";
		document.frm.submit();
	}
*/

function fn_egov_select_commentList() {
	document.frm.commentCn.value = '';
	document.frm.file2.value = '';
}
</script>

<%-- </c:if> --%>

<%-- <c:if test="${type == 'body'}"> --%>
<input name="subPageIndex" type="hidden" value="<c:out value='${searchVO.subPageIndex}'/>">
<input name="commentNo" type="hidden" value="<c:out value='${searchVO.commentNo}'/>">
<input name="modified" type="hidden" value="false">
<input name="confirmPassword" type="hidden">

<input type="hidden" name="param_bbsId" value="<c:out value='${bbsId}'/>" />
<input type="hidden" name="param_nttId"  value="<c:out value='${nttId}'/>" />

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="commentPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<!-- 댓글 시작 -->
<dl class="comment-area">
	<dt>댓글 (<span><c:out value="${resultCnt}"/></span>)</dt>
	<dd class="text-area">
		<textarea id='commentCn' name='commentCn' rows="4" placeholder="(ex)댓글내용 입력"><c:out value="${searchVO.commentCn}" /></textarea>
		<input type="hidden" id="wrterNm" name="wrterNm" value='<c:out value="${searchVO.wrterNm}" />'>
		<input type="hidden" id="atchFileId" name="atchFileId">
		<%--<input type="hidden" id="atchFileId" name="atchFileId" value="${resultCommentFile.atchFileId}">
		 <c:if test="${!empty resultCommentFile}">
			<a href="javascript:com.downFile('${resultCommentFile.atchFileId}','${resultCommentFile.fileSn}');" class="text-file">${resultCommentFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		<c:if test="${empty resultCommentFile}">
			<input type="text" id="file2" name="file2" style="width:400px;" readonly>
			<div>
				<a href="#@" class="checkfile">파일선택</a>
				<input type="file" class="file_input_hidden" name="file-input" id="file-input" onchange="javascript: document.getElementById('file2').value = this.value" />
			</div>
			<!-- <div id="fileSizeName" class="btn-area align-right mt-010">0KB / 10M</div> -->
		</c:if> --%>
	</dd>
	<dd class="right">
		<a href="#!" onclick="fn_egov_select_commentList();" class="gray-1">댓글초기화</a>
		<c:choose>
		<c:when test="${searchVO.commentNo == ''}">
			<a href="#!" onclick="fn_egov_insert_commentList();" class="yellow">댓글등록</a>
		</c:when>
		<c:otherwise>
			<a href="#!" onclick="fn_egov_updt_commentList();" class="orange">댓글수정</a>
		</c:otherwise>
		</c:choose>
	</dd>
	<c:if test="${fn:length(resultList) == 0}">
	<dd>
		<b>댓글이 없습니다.</b>
	</dd>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<dd>
		<b>
			<c:choose>
			<c:when test="${not empty result.wrterNm}">
				<c:out value="${result.wrterNm}" />
			</c:when>
			<c:otherwise>
				<c:out value="${result.frstRegisterNm}" />
			</c:otherwise>
			</c:choose>
		</b> [ <c:out value="${result.frstRegisterPnttm}" /> ] &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%-- <c:if test="${!empty resultCommentFile}">
		- 첨부파일 :
		<a href="javascript:com.downFile('${resultCommentFile.atchFileId}','${resultCommentFile.fileSn}');" class="text-file">${resultCommentFile.orgFileName}</a>
		</c:if> --%>
		<c:if test="${anonymous == 'true' || result.wrterId == sessionUniqId}">
		<span>
			<a href="#!" class="btn-view" onclick="fn_egov_selectCommentForupdt('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />', '<c:out value="${bbsId}" />');">상세보기</a>
			<a href="#!" class="btn-del" onclick="fn_egov_deleteCommentList('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');">삭제</a>
		</span>
		</c:if>
		<p><c:out value="${result.commentCn}" /></p>
	</dd>
	</c:forEach>
</dl><!-- E : 댓글 -->
<!-- 댓글 끝 -->

<c:if test="${not empty subMsg}">
<script>
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>
<%-- </c:if> --%>
