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
function fn_egov_insert_commentList() {

/* 	if (!validateComment(document.frm)){
		return;
	} */
	if( !com.checkRequiredField( $("#commentCn") ) ){
		return;
	}
	if (confirm('<spring:message code="common.regist.msg" />')) {
		document.frm.action = "<c:url value='/lu/cop/bbs/${bbsId}/insertBoardComment.do'/>";
		document.frm.submit();
	}
}

function fn_egov_updt_commentList() {
	if (!validateComment(document.frm)){
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

/* function fn_egov_select_commentList(pageNo) {
		document.frm.subPageIndex.value = pageNo;
		document.frm.commentNo.value = '';
		document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/selectBoardArticle.do'/>";
		document.frm.submit(); 
	} 
*/
	
function fn_egov_select_commentList() {
	document.frm.commentCn.value = '';
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
<div class="comment">댓글 (<span><c:out value="${resultCnt}"/></span>)</div>
<div>
<table class="table_view">
	<colgroup>
		<col style="width:*" />
	</colgroup>
	<tr>
		<th class="bottom_none">
		<textarea id='commentCn' name='commentCn' rows='4' class="comment_textarea"><c:out value="${searchVO.commentCn}" /></textarea>
		<input id="wrterNm" name="wrterNm" type="hidden" size="20" value='<c:out value="${searchVO.wrterNm}" />' maxlength="20" title="작성자이름입력">
		<c:choose>
		<c:when test="${searchVO.commentNo == ''}">
			<input type="button" class="btn_comment" value="댓글등록" onclick="fn_egov_insert_commentList();" />
		</c:when>
		<c:otherwise>
			<input type="button" class="btn_comment" value="댓글수정" onclick="fn_egov_updt_commentList();" />
		</c:otherwise>
		</c:choose>
		<input type="button" class="btn_comment_reset" value="댓글초기화" onclick="fn_egov_select_commentList();" />
		</th>
	</tr>
</table>
</div>

<div class="comment_bg">
	<c:if test="${fn:length(resultList) == 0}">
	<div class="comment_list_top">
		댓글이 없습니다.			
	</div>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<div class="comment_list">
	<b>	
		<c:choose>
		<c:when test="${not empty result.wrterNm}">
			<c:out value="${result.wrterNm}" />
		</c:when>
		<c:otherwise>
			<c:out value="${result.frstRegisterNm}" />
		</c:otherwise>
		</c:choose>
	</b> 
	ㅣ <c:out value="${result.frstRegisterPnttm}" /> 
	<c:if test="${anonymous == 'true' || result.wrterId == sessionUniqId}">
	<span>
		<input type="button" class="table_view" value="" onclick="fn_egov_selectCommentForupdt('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />', '<c:out value="${bbsId}" />');" />
		<input type="button" class="table_delete" value="" onclick="fn_egov_deleteCommentList('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');" />
	</span>
	</c:if>
	<br>
	<c:out value="${result.commentCn}" /> 					
	</div>
	</c:forEach>
</div>
<!-- 댓글 끝 -->

<c:if test="${not empty subMsg}">
<script>
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>
<%-- </c:if> --%>
