<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_PopupManage(){
	var reqUrl = "/la/popmng/updatePopupView.do";
	$("#frmPopup").attr("action", reqUrl);
	$("#frmPopup").submit();
	
}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_PopupManage(){
	if(confirm("삭제 하시겠습니까?")){
		var reqUrl = "/la/popmng/deletePopup.do";
		$("#frmPopup").attr("action", reqUrl);
		$("#frmPopup").submit();
	}
}
</script>
<!--  상단타이틀 -->
<!--     model.addAttribute("result", result); -->
<!--         model.addAttribute("atchFile", atchFileVO); -->
<img id="displayBox" src="/js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">
<form:form commandName="frmPopup" method="post">
				<input type="hidden" id="popupId"   name="popupId"  value="${result.popupId}" />
				<input type="hidden" id="imageFileId"   name="imageFileId"  value="${result.imageFileId}" />
				<input type="hidden" id="contentType"   name="contentType"  value="${result.contentType}" />
				
				<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
					<tbody>
						<tr><th>노출위치</th>		<td colspan="3">	
						<c:forEach items="${ccFocusList}" var="ccInfo" varStatus="status">
						 <c:if test="${ccInfo.codeCode == result.pageType}">${ccInfo.codeName}</c:if>
						</c:forEach>
						</td>	</tr>
						<tr>	<th>제목</th>		<td colspan="3">${result.title}</td>	</tr>
						<tr>	<th>게시일</th>		<td colspan="3">
						${result.startDate} ${result.startDateTime} ~ ${result.finishDate} ${result.finishDateTime}  
						</td>	</tr>
						<tr>	<th>팝업형식</th>		<td colspan="3">
						<c:choose>
							<c:when test="${result.popupType=='L'}">layer</c:when>
							<c:when test="${result.popupType=='W'}">windows</c:when>
						</c:choose>
						</td>	</tr>
						<tr>	<th>그만보기설정</th>		<td colspan="3">
						<c:choose>
							<c:when test="${result.isCloseViewSettings=='D'}">하루동안 그만보기</c:when>
							<c:when test="${result.isCloseViewSettings=='W'}">일주일간 그만보기</c:when>
						</c:choose>
						</td>	</tr>
						<tr>	<th>컨텐츠종류</th>		<td colspan="3">
						<c:choose>
							<c:when test="${result.contentType=='H'}">HTML</c:when>
							<c:when test="${result.contentType=='I'}">이미지 업로드</c:when>
						</c:choose>
					    </td>	</tr>
					    <tr class="trPopupSize">	<th>팝업창 크기</th>	<td colspan="3">
						가로: ${result.popupWidth} px</br>
						세로: ${result.popupHeight} px 
						</td>	</tr>
					
						<c:if test="${result.contentType=='I'}">
								<tr class="trImageUpload">	<th>링크 URL</th>		<td colspan="3">
									${result.pageUrl}
								</td>	</tr>
								<tr class="trImageUpload">	<th>이미지 업로드</th>		<td colspan="3">
										<div id="preview"><img src="/commbiz/atchfle/atchFileDown.do?importAtchFileId=${result.imageFileId}&importFleSn=1" style="height:100px"/></div>
								</td>	</tr>
						</c:if>
						<tr>	<th>사용여부</th>		<td colspan="3">
						<c:choose>
							<c:when test="${result.isUse=='Y'}">예</c:when>
							<c:when test="${result.isUse=='N'}">아니오</c:when>
						</c:choose>
						</td>	</tr>
					</tbody>
				</table><!-- E : view-1 -->
</form:form>
<!-- S : page-btn -->
<div class="page-btn">
	<a href="#@" onclick="fn_egov_modify_PopupManage(); return false;">수정</a>
	<a href="#@" onclick="fn_egov_delete_PopupManage(); return false;">삭제</a>
	<a href="/la/popmng/listPopup.do">목록</a>
</div>
<!-- E : page-btn -->
<!-- E : 상세 보기 영역 (3 단 상세보기) -->
