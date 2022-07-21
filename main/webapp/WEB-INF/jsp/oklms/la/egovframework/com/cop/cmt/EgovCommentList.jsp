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
  * @ 2009.06.29   한성곤          최초 생성
  *
  *  @author 공통컴포넌트개발팀 한성곤
  *  @since 2009.06.29
  *  @version 1.0
  *  @see
  *
  */
%>

<%-- <c:if test="${type == 'head'}"> --%>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="comment" staticJavascript="false" xhtml="true" cdata="false"/>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">
function fn_egov_insert_commentList() {

	if (!validateComment(document.frm)){
		return;
	}
	if (confirm('<spring:message code="common.regist.msg" />')) {
		document.frm.action = "<c:url value='/la/cop/cmt${prefix}/insertComment.do'/>";
		document.frm.submit();
	}
}

function fn_egov_updt_commentList() {
	if (!validateComment(document.frm)){
		return;
	}

	if (confirm('<spring:message code="common.update.msg" />')) {
		document.frm.modified.value = "true";
		document.frm.action = "<c:url value='/la/cop/cmt${prefix}/updateComment.do'/>";
		document.frm.submit();
	}
}

function fn_egov_selectCommentForupdt(commentNo, index) {
	var param_bbsId;
	param_bbsId = '${bbsId}'; 
	
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
	if(param_bbsId != null){
		document.frm.action = "<c:url value='/la/cop/bbs/${bbsId}/selectBoardArticle.do'/>";
	}else{
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/selectBoardArticle.do'/>";
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
		document.frm.action = "<c:url value='/la/cop/cmt${prefix}/deleteComment.do'/>";
		document.frm.submit();
	}
}

/* function fn_egov_select_commentList(pageNo) {
		document.frm.subPageIndex.value = pageNo;
		document.frm.commentNo.value = '';
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/selectBoardArticle.do'/>";
		document.frm.submit(); 
	} 
*/
	
function fn_egov_select_commentList(pageNo) {
	document.frm.commentNo.value = '';
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
						<div class="reply-area">
							<ul class="write">
								<li class="title">댓글 (<c:out value="${resultCnt}"/>)</li>
								<li class="body">
									<!-- <label for="reply-write" class="iLabel">콘텐츠와 무관한 댓글, 악플은 삭제될 수 있습니다.</label> -->
									<textarea name="commentCn" class="textarea"  cols="50" rows="4"   id="reply-write" class="iText" title="댓글내용입력"><c:out value="${searchVO.commentCn}" /></textarea><form:errors path="commentCn" />
									<input name="wrterNm" type="hidden" size="20" value='<c:out value="${searchVO.wrterNm}" />' maxlength="20" title="작성자이름입력"><form:errors path="wrterNm" />
									<c:if test="${anonymous == 'true'}">
									  패스워드 : <input name="commentPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
								  	</c:if>
								</li>
								<li class="btn">
	<c:choose>
		<c:when test="${searchVO.commentNo == ''}">
			<a href="javascript:fn_egov_insert_commentList()">댓글등록</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:fn_egov_updt_commentList()">댓글수정 </a>
		</c:otherwise>
	</c:choose>
			<a href="javascript:fn_egov_select_commentList('1')">댓글초기화</a>
								</li>
							</ul>


		<c:forEach var="result" items="${resultList}" varStatus="status">
							<dl class="list">
								<dt><c:choose><c:when test="${not empty result.wrterNm}"><c:out value="${result.wrterNm}" /></c:when>
						    			<c:otherwise><c:out value="${result.frstRegisterNm}" /></c:otherwise>
						    		</c:choose> <p> <span><c:out value="${result.frstRegisterPnttm}" /></span></p>
		    				    <c:if test="${anonymous == 'true' || result.wrterId == sessionUniqId}">
								     <a href="javascript:fn_egov_selectCommentForupdt('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');" class="modify"><spring:message code="button.update" /></a>
								     <a href="javascript:fn_egov_deleteCommentList('<c:out value="${result.commentNo}" />', '<c:out value="${status.index}" />');" class="del"><spring:message code="button.delete" /></a>
							    </c:if>
							    <c:if test="${anonymous == 'true'}">
								    <br>
								    	패스워드 : <input name="testPassword" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
							    </c:if>
		    					</dt>
								<dd><c:out value="${result.commentCn}" /></dd>
								<%-- <dd><a href="">답글 5</a></dd> --%>
							</dl>
 		</c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
							<dl class="list">
								<dt>댓글이 없습니다.</dt>
							</dl>
		 </c:if>
						</div><!-- E : reply-area -->

<c:if test="${not empty subMsg}">
<script>
	alert("<c:out value='${subMsg}'/>");
</script>
</c:if>
<%-- </c:if> --%>
