<%--
  Class Name : EgovQustnrRespondInfoRegist.jsp
  Description : 설문지 통계
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2016.12.20    이진근          모듈 수정

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
$(document).ready(function() {
	 
});
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
	location.href = "<c:url value='${returnUrl}'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){

}

/************************************************************************
//셀렉트 박스 선택했는 찾는 함수
************************************************************************/

function fn_egov_selectBoxChecking(sbName){

	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reuslt=true;
		}
	}

	return reuslt;
}
/************************************************************************
//셀렉트박스 값 컨트롤 함수
************************************************************************/
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
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문 통계</div>
<!--  상단타이틀 End -->

<!-- 상단타이틀 -->
<form name="qustnrRespondInfoManage" action="#LINK" method="post">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qestnrId}">

<!-- 서브타이틀  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<tr>
    <td scope="row" width="20%" height="35" >설문제목 :</td>
    <td width="80%">
	<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    </td>
</tr>
<tr>
    <td scope="row" width="20%" height="35" >설문목적 :</td>
    <td width="80%">
	<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
    </td>
</tr>
<tr>
    <td scope="row" width="20%" height="35" > 설문작성 안내내용 :</td>

    <td width="80%">
	<c:out value="${fn:replace(Comtnqestnrinfo[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
    </td>
</tr>
<tr>
    <td height="35" colspan="2">
    <table width="100%" border="0" cellpadding="0">
    <tr>
    	<td width="50%" align="center">
    	<b>설문대상  :</b>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '1'}">학생</c:if>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '2'}">대학생</c:if>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '3'}">직장인</c:if>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '4'}">군인</c:if>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '5'}">교사</c:if>
			<c:if test="${Comtnqestnrinfo[0].qestnrTrget ==  '6'}">기타</c:if>
    	</td>
    	<td width="50%" align="center">
    	<b>설문기간 :</b>
    	${Comtnqestnrinfo[0].qestnrBeginDe}~${Comtnqestnrinfo[0].qestnrEndDe}
    	</td>
    </tr>
    </table>

    </td>
</tr>
<%--
	객관식 : qestnrStatistic1
	주관식 : qestnrStatistic2
--%>
<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
<tr>
    <td colspan="2">
    	<table width="100%" border="0" cellpadding="2" cellspacing="0">
    	<tr>
    		<td style="background-color:#E3E3E3;">${status1.count}. <c:out value="${fn:replace(QestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false"/></td>
    	</tr>
    	<tr>
    	<td style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;background-color:#E3E3E3;">

    		<table width="100%" border="0" cellpadding="0" cellspacing="0">

    			<%-- 객관식 일때  --%>
    			<c:if test="${QestmInfo.qestnTyCode ==  '1'}">
    			<%-- 설문항목 LOOP --%>
    			<c:set var="chartCount" value="1" />
    			<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status2">
    			<c:set var="chartCheck" value="0" />
    			<%-- 해당 설문문항 AND 설문문항  체크 --%>
    			<c:if test="${QestmInfo.qestnrTmplatId eq QestmItem.qestnrTmplatId && QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">
    			<tr>
    				<td style="background-color:#E3E3E3;" width="10px" height="28px"></td>
    				<td style="background-color:#E3E3E3;" width="100px"><c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" /></td>
    				<td style="background-color:#E3E3E3;">
    				<%-- 설문통계(객관식) LOOP --%>
    				<c:forEach items="${qestnrStatistic1}" var="QestmStatistic1" varStatus="status3">
    					<c:if test="${QestmInfo.qestnrTmplatId eq QestmStatistic1.qestnrTmplatId && QestmInfo.qestnrId eq QestmStatistic1.qestnrId && QestmStatistic1.qestnrQesitmId eq QestmItem.qestnrQesitmId && QestmStatistic1.qustnrIemId eq QestmItem.qustnrIemId}">
    					<img src="${ImgUrl}chart/chart${chartCount}.JPG" width="${QestmStatistic1.qustnrPercent}px" height="8" "alt="차트이미지"> ${QestmStatistic1.qustnrPercent}%
    					<c:set var="chartCheck" value="${chartCheck+1}" />
    				</c:if>
    				</c:forEach>
    				<c:if test="${chartCheck eq 0}">
    				<img src="${ImgUrl}chart/chart${chartCount}.JPG" width="0 px" height="8" alt="차트이미지"> 0%
    				</c:if>
    				</td>
    			</tr>
    			<c:set var="chartCount" value="${chartCount+1}" />
    			</c:if>
    			</c:forEach>
    			</c:if>

    			<%-- 주관식 --%>
    			<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
    			<%-- 설문통계(객관식) LOOP --%>
    			<c:forEach items="${qestnrStatistic2}" var="QestmStatistic2" varStatus="status4">
    				<c:if test="${QestmInfo.qestnrTmplatId eq QestmStatistic2.qestnrTmplatId && QestmInfo.qestnrId eq QestmStatistic2.qestnrId && QestmInfo.qestnrQesitmId eq QestmStatistic2.qestnrQesitmId}">
	    			<tr>
	    				<td style="background-color:#E3E3E3;" width="10px" align="right" valign="top"></td>
	    				<td style="background-color:#E3E3E3;" >
	    				<br>
	    				<c:out value="${fn:replace(QestmStatistic2.respondNm , crlf , '<br/>')}" escapeXml="false" /> : <c:out value="${fn:replace(QestmStatistic2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
	    				<br>
	    				</td>
	    			</tr>
    				</c:if>
    			</c:forEach>
    			</c:if>
    		</table>

    	</tr>
    	</table>
    </td>
</tr>
<tr>
	<td colspan="2" height="1">
	<input type="hidden" name="TY_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.qestnTyCode}">
	</td>
</tr>
</c:forEach>
</table>
</form>

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_list();return false;">목록</a>
</div>
<!-- 버튼 End  -->
