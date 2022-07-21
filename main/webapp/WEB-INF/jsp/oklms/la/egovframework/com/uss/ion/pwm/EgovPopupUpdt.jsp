<%--
  Class Name : EgovPopupUpdt.jsp
  Description : 팝업창관리 수정 페이지
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/" />
<%pageContext.setAttribute("crlf", "\r\n"); %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/cal/EgovCalPopup.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<validator:javascript formName="popupManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_PopupManage(){

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_PopupManage(){

	var varFrom = document.popupManageVO;

	if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/la/uss/ion/pwm/updtPopup.do' />";
		if(!validatePopupManageVO(varFrom)){
			return;
		}else{

			var ntceBgndeYYYMMDD = document.getElementById('ntceBgndeYYYMMDD').value;
			var ntceEnddeYYYMMDD = document.getElementById('ntceEnddeYYYMMDD').value;

			var iChkBeginDe = Number( ntceBgndeYYYMMDD.replaceAll("-","") );
			var iChkEndDe = Number( ntceEnddeYYYMMDD.replaceAll("-","") );

			if(iChkBeginDe > iChkEndDe || iChkEndDe < iChkBeginDe ){
				alert("게시시작일자는 게시종료일자 보다 클수 없고,\n게시종료일자는 게시시작일자 보다 작을수 없습니다. ");
				return;
			}

			varFrom.ntceBgnde.value = ntceBgndeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceBgndeHH') +  fn_egov_SelectBoxValue('ntceBgndeMM');
			varFrom.ntceEndde.value = ntceEnddeYYYMMDD.replaceAll('-','') + fn_egov_SelectBoxValue('ntceEnddeHH') +  fn_egov_SelectBoxValue('ntceEnddeMM');

			varFrom.submit();
		}
	}
}
/* ********************************************************
* RADIO BOX VALUE FUNCTION
******************************************************** */
function fn_egov_RadioBoxValue(sbName)
{
	var FLength = document.getElementsByName(sbName).length;
	var FValue = "";
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			FValue = document.getElementsByName(sbName)[i].value;
		}
	}
	return FValue;
}
/* ********************************************************
* SELECT BOX VALUE FUNCTION
******************************************************** */
function fn_egov_SelectBoxValue(sbName)
{
	var FValue = "";
	for(var i=0; i < document.getElementById(sbName).length; i++)
	{
		if(document.getElementById(sbName).options[i].selected == true){

			FValue=document.getElementById(sbName).options[i].value;
		}
	}

	return  FValue;
}
/* ********************************************************
* PROTOTYPE JS FUNCTION
******************************************************** */
String.prototype.trim = function(){
	return this.replace(/^\s+|\s+$/g, "");
}

String.prototype.replaceAll = function(src, repl){
	 var str = this;
	 if(src == repl){return str;}
	 while(str.indexOf(src) != -1) {
	 	str = str.replace(src, repl);
	 }
	 return str;
}
</script>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!--  상단타이틀 -->
<form:form commandName="popupManageVO" name="popupManageVO" action="${pageContext.request.contextPath}/uss/ion/pwm/updtPopup.do" method="post" >
						<!-- S : 입력 폼 영역 -->
						<!-- S : view-1 -->
						<table border="0" cellpadding="0" cellspacing="0" class="view-1">
							<tbody>
								<tr>
									<th width="150px">팝업창 명</th>
									<td colspan="3"><form:input path="popupTitleNm" size="73" cssClass="txaIpt" maxlength="255"/><form:errors path="popupTitleNm" cssClass="error"/></td>
								</tr>
								<tr>
									<th>팝업창 URL</th>
									<td colspan="3"><form:input path="fileUrl" size="73" cssClass="txaIpt" maxlength="255"/><form:errors path="fileUrl" cssClass="error"/></td>
								</tr>
								<tr>
									<th>팝업창 위치</th>
									<td colspan="3">가로 <form:input path="popupWlc" size="5" maxlength="10"/>  세로<form:input path="popupHlc" size="5" maxlength="10"/>
  <form:errors path="popupWlc" cssClass="error"/>
  <form:errors path="popupHlc" cssClass="error"/></td>
								</tr>
								<tr>
									<th>팝업창 크기</th>
									<td colspan="3">WIDTH <form:input path="popupWSize" size="5" maxlength="10"/>  HEIGHT<form:input path="popupHSize" size="5" maxlength="10"/>
  <form:errors path="popupWSize" cssClass="error"/>
  <form:errors path="popupHSize" cssClass="error"/></td>
								</tr>
								<tr>
									<th>게시 기간</th>
									<td colspan="3">
										<input type="hidden" name="cal_url" id="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>" >
									    <input type="text" name="ntceBgndeYYYMMDD" id="ntceBgndeYYYMMDD" size="10" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceBgnde, 6, 8)}"/>" readonly>
									    <a href="#" onClick="javascript:fn_egov_NormalCalendar(document.forms[0], document.forms[0].ntceBgndeYYYMMDD);">
									    <img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />"  align="middle" style="border:0px" alt="달력창팝업버튼이미지">
									    </a>
									    <form:select path="ntceBgndeHH">
									        <form:options items="${ntceBgndeHH}" itemValue="code" itemLabel="codeNm"/>
									    </form:select>H
									    <form:select path="ntceBgndeMM">
									        <form:options items="${ntceBgndeMM}" itemValue="code" itemLabel="codeNm"/>
									    </form:select>M
									    &nbsp;&nbsp;~&nbsp;&nbsp;
									    <input type="text" name="ntceEnddeYYYMMDD" id="ntceEnddeYYYMMDD" size="10" maxlength="10" class="readOnlyClass" value="<c:out value="${fn:substring(popupManageVO.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(popupManageVO.ntceEndde, 6, 8)}"/>" readonly>
									    <a href="#" onClick="javascript:fn_egov_NormalCalendar(document.forms[0], document.forms[0].ntceEnddeYYYMMDD);">
									    <img src="<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif' />" align="middle" style="border:0px" alt="달력창팝업버튼이미지">
									    </a>
									    <form:select path="ntceEnddeHH">
									        <form:options items="${ntceEnddeHH}" itemValue="code" itemLabel="codeNm"/>
									    </form:select>H
									    <form:select path="ntceEnddeMM">
									        <form:options items="${ntceEnddeMM}" itemValue="code" itemLabel="codeNm"/>
									    </form:select>M
								    </td>
								</tr>
								<tr>
									<th>그만보기 설정 여부</th>
									<td colspan="3"><input type="radio" name="stopVewAt" value="Y" <c:if test="${popupManageVO.stopVewAt eq 'Y'}">checked</c:if>>Y
		<input type="radio" name="stopVewAt" value="N" <c:if test="${popupManageVO.stopVewAt eq 'N'}">checked</c:if>>N</td>
								</tr>
								<tr>
									<th>게시 상태</th>
									<td colspan="3">
										<input type="radio" name="ntceAt" value="Y" <c:if test="${popupManageVO.ntceAt eq 'Y'}">checked</c:if>>Y
										<input type="radio" name="ntceAt" value="N" <c:if test="${popupManageVO.ntceAt eq 'N'}">checked</c:if>>N
									</td>
								</tr>
							</tbody>
						</table>
						<!-- E : view-1 -->

<form:hidden path="ntceBgnde" />
<form:hidden path="ntceEndde" />
<input name="popupId" type="hidden" value="${popupManageVO.popupId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>"/>
</form:form>
						<!-- S : page-btn -->
						<div class="page-btn">
							<a href="<c:url value='/la/uss/ion/pwm/listPopup.do' />">목록</a><a href="#@" onclick="fn_egov_save_PopupManage(); return false;">저장</a>
						</div>
						<!-- E : page-btn -->						
						<!-- E : 입력 폼 영역 -->
