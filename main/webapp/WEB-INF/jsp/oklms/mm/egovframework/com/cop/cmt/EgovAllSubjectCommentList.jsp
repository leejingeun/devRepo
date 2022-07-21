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

<link href= "/css/oklms/mobile_default.css" rel="stylesheet" type="text/css" />

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
		document.frm.action = "<c:url value='/mm/cop/bbs/${bbsId}/insertBoardComment.do'/>";
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
		document.frm.action = "<c:url value='/mm/cop/bbs/${bbsId}/updateBoardComment.do'/>";
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
		document.frm.action = "<c:url value='/mm/cop/bbs/${bbsId}/selectBoardArticle.do'/>";
	}else{
		document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/selectBoardArticle.do'/>";
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
		document.frm.action = "<c:url value='/mm/cop/bbs/${bbsId}/deleteBoardComment.do'/>";
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
		document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/selectBoardArticle.do'/>";
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

<input type="hidden" name="param_bbsId" value="<c:out value='${searchVO.bbsId}'/>" />
<input type="hidden" name="param_nttId"  value="<c:out value='${searchVO.nttId}'/>" />

<c:if test="${anonymous != 'true'}">
<input type="hidden" name="commentPassword" value="dummy">	<!-- validator 처리를 위해 지정 -->
</c:if>

<!-- 댓글 시작 -->
<dl class="bbs-reply mt-010">
	




	
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<dt>		
		<span>
			<c:choose>
			<c:when test="${not empty result.wrterNm}">
				[<c:out value="${result.wrterNm}" />]
			</c:when>
			<c:otherwise>
				[<c:out value="${result.frstRegisterNm}" />]
			</c:otherwise>
			</c:choose>
		</span>  <c:out value="${result.frstRegisterPnttm}" /> 
	</dt>
	<dd>
		<c:out value="${result.commentCn}" />
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
