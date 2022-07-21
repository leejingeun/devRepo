<%--
  Class Name : EgovPopupDetail.jsp
  Description : 팝업창관리 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.09.16    장동한          최초 생성
 
    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.16
   
    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/" />
<%pageContext.setAttribute("crlf", "\r\n"); %>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_PopupManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_PopupManage(){
	location.href = "<c:url value='/la/uss/ion/pwm/listPopup.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_PopupManage(){
	var vFrom = document.PopupManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/ion/pwm/updtPopup.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_PopupManage(){
	var vFrom = document.PopupManageForm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/ion/pwm/detailPopup.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!--  상단타이틀 -->

						<!-- S : 상세 보기 영역 (3 단 상세보기) -->

						<div class="title-name-1">기본정보</div>
<form name="PopupManageForm" action="<c:url value=''/>" method="post">
<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
<input name="cmd" type="hidden" value="<c:out value=''/>"/>
						<!-- S : view-1 -->
						<table border="0" cellpadding="0" cellspacing="0" class="view-1">
							<tbody>
								<tr>
									<th>팝업창 명</th>
									<td colspan="5"><c:out value="${popupManageVO.popupTitleNm}" /></td>
								</tr>
								<tr>
									<th>팝업창URL</th>
									<td colspan="5"><c:out value="${popupManageVO.fileUrl}" /></td>
								</tr>
								<tr>
									<th>팝업창 위치</th>
									<td colspan="5">가로<c:out value="${popupManageVO.popupWlc}" />  세로<c:out value="${popupManageVO.popupHlc}" escapeXml="false" /></td>
								</tr>
								<tr>
									<th>팝업창 크기</th>
									<td colspan="5">WIDTH<c:out value="${popupManageVO.popupWSize}" />  HEIGHT<c:out value="${popupManageVO.popupHSize}" escapeXml="false" /></td>
								</tr>
								<tr>
									<th>팝업창 게시 기간</th>
									<td colspan="5">
								 	<c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(popupManageVO.ntceBgnde, 8, 10)}"/>H <c:out value="${fn:substring(popupManageVO.ntceBgnde, 10, 12)}"/>M 
								 	~
								 	<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(popupManageVO.ntceEndde, 8, 10)}"/>H <c:out value="${fn:substring(popupManageVO.ntceEndde, 10, 12)}"/>M
								    </td>
								</tr>
								<tr>
									<th>그만보기 설정 여부</th>
									<td colspan="5"><c:out value="${popupManageVO.stopVewAt}"/></td>
								</tr>
								<tr>
									<th>게시상태</th>
									<td colspan="5"><c:out value="${popupManageVO.ntceAt}"/></td>
								</tr>
							</tbody>
						</table>
						<!-- E : view-1 -->
</form>
						<!-- S : page-btn -->
						<div class="page-btn">
							<a href="#@" onclick="fn_egov_modify_PopupManage(); return false;">수정</a>
							<a href="#@" onclick="fn_egov_delete_PopupManage(); return false;">삭제</a>
							<a href="#@" onclick="fn_egov_list_PopupManage(); return false;">목록</a>
						</div>
						<!-- E : page-btn -->
						<!-- E : 상세 보기 영역 (3 단 상세보기) -->
