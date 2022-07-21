<%--
  Class Name : EgovQustnrRespondInfoRegist.jsp
  Description : 설문조사 등록 페이지
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="frm" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/*====================================================================
화면 초기화 
====================================================================*/
$(document).ready(function() {
	document.board.qestnrTmplatTy.focus();
});

/*====================================================================
사용자 정의 함수  
====================================================================*/

/* 입력 필드 초기화 */ 
function fn_formReset(){
	/* $("#qustnrTmplatManage").find("input,textarea").val("");
	$('#preview').empty(); */
}

/* 입력 필드 유효성 첵크 */
function fn_egov_validateForm() {		
	/* if( !com.checkRequiredField( $("#qestnrTmplatTy") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatCours") ) ){
		return;
	} */
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "/la/uss/olp/qnn/listEgovQustnrRespondInfoManage.do";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save_QustnrRespondInfo(){
	var varFrom = document.frm;

	//설문응답자  Validtation

	if(varFrom.sexdstnCode.selectedIndex == 0){
		alert('설문응답자정보 성별을  택해주세요!');
		varFrom.sexdstnCode.focus();
		return;
	}else if(varFrom.occpTyCode.selectedIndex == 0){
		alert('설문응답자정보 직업을 선택해주세요!');
		varFrom.occpTyCode.focus();
		return;
	}else if(varFrom.brthYYYY.selectedIndex == 0){
		alert('설문응답자정보 생년월일(년)을 선택해주세요!');
		varFrom.brthYYYY.focus();
		return;
	}else if(varFrom.brthMM.selectedIndex == 0){
		alert('설문응답자정보 생년월일(월)을 선택해주세요!');
		varFrom.brthMM.focus();
		return;
	}else if(varFrom.brthDD.selectedIndex == 0){
		alert('설문응답자정보 생년월일(일)을 선택해주세요!');
		varFrom.brthDD.focus();
		return;
	}else if(varFrom.respondNm.value == ""){
		alert('설문응답자정보 응답자명을 입력해주세요!');
		varFrom.respondNm.focus();
		return;
	}



	//설문정보 Validtation
	<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
	<c:if test="${QestmInfo.qestnTyCode ==  '1'}">
	if((!fn_egov_selectBoxChecking("${QestmInfo.qestnrQesitmId}"))){
		alert('${status1.count}번 설문문항을 작성해 주세요!');
		document.getElementsByName("${QestmInfo.qestnrQesitmId}")[0].focus();
		return;
	}

		<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status01">
		<c:if test="${QestmInfo.qestnrTmplatId eq QestmItem.qestnrTmplatId && QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">

			<c:if test="${QestmItem.etcAnswerAt eq  'Y'}">
			//기타답변을 선택했는체크
			if(fn_egov_RadioBoxValue("${QestmInfo.qestnrQesitmId}") == "${QestmItem.qustnrIemId}"){
				if(document.getElementById("ETC_${QestmItem.qustnrIemId}").value == ""){
					alert('${status1.count}번 설문문항 기타답변을 작성해주세요!');
					document.getElementById("ETC_${QestmItem.qustnrIemId}").focus();
					return;
				}
			}

			</c:if>
		</c:if>
		</c:forEach>
	</c:if>


	<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
	if( document.getElementById("${QestmInfo.qestnrQesitmId}").value == "" ){
		//alert('${status1.count}. ${QestmInfo.qestnCn}       \n\n설문문항을 작성해 주세요!');
		alert('${status1.count}번 설문문항을 작성해 주세요!');
		document.getElementById("${QestmInfo.qestnrQesitmId}").focus();
		return;
	}
	</c:if>
	</c:forEach>


	if(confirm("<spring:message code="common.save.msg" />")){

		varFrom.brth.value = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');
		varFrom.action =  "/la/uss/olp/qnn/goInsertEgovQustnrRespondInfoManage.do";
		varFrom.submit();
	}
}
/************************************************************************
//라디오박스 : 몇개선택했는데 체크해주는함수
************************************************************************/
function fn_egov_checkbox_amout_max( sbName){
	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	return reusltCount;

}
/************************************************************************
//라디오박스 : 최대선택건수 체크
************************************************************************/
function fn_egov_checkbox_amout( sbName, sbCount, sbObj){

	var FLength= document.getElementsByName(sbName).length;

	var reuslt = false;
	var reusltCount = 0;
	for(var i=0; i < FLength; i++)
	{
		if(document.getElementsByName(sbName)[i].checked == true){
			reusltCount++;
		}
	}

	if(reusltCount > sbCount){
	 	alert("최대선택건수 [" + sbCount + "]건을  초과하였습니다!" );
	 	sbObj.checked=false;
	 	return;
	}
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

/************************************************************************
//라디오박스 체크 박스
************************************************************************/
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

</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문응답자 정보</div>
<!--  상단타이틀 End -->
<form name="frm" method="post" action="">
<!-- 상단 타이틀  영역 -->

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >성별<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>

<select name="sexdstnCode" title="성별 선택">
	<option value="">선택</option>
	<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
		<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq Emplyrinfo.sexdstnCode}">selected</c:if>>${comCodeList.codeNm}</option>
	</c:forEach>
</select>

    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >직업<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>

<select name="occpTyCode" title="직업 선택">
	<option value="">선택</option>
	<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
		<option value="${comCodeList.code}">${comCodeList.codeNm}</option>
	</c:forEach>
</select>

    </td>
  </tr>
  <tr>
    <th scope="row" height="23"  >생년월일<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
       <select name="brthYYYY" id="brthYYYY" title="연도 선택">
       	<option value="">선택</option>
	     <c:forEach var="h" begin="1960" end="2009" step="1">
	      	<option value="${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 0, 4) ==  h}">selected</c:if>>${h}</option>
	      </c:forEach>
       </select>년

       <select name="brthMM" id="brthMM" title="월 선택">
       <option value="">선택</option>
	     <c:forEach var="h" begin="1" end="12" step="1">
			<c:choose>
			    <c:when test="${h < 10}">
			 	<c:set var="brthMM" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    <c:set var="brthMM" value="${h}"/>
			    </c:otherwise>
			</c:choose>
	      	<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 4, 6) ==  brthMM}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      </c:forEach>
	   </select>월
       <select name="brthDD" id="brthDD" title="일 선택">
       <option value="">선택</option>
	     <c:forEach var="h" begin="1" end="31" step="1">
			<c:choose>
			    <c:when test="${h < 10}">
			 	<c:set var="brthDD" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    <c:set var="brthDD" value="${h}"/>
			    </c:otherwise>
			</c:choose>
	      	<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(Emplyrinfo.brth, 6, 8) ==  brthDD}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      </c:forEach>
       </select>일

       <input name="brth" id="brth" type="hidden" value="">
    </td>
  </tr>

  <tr>
    <th scope="row" width="20%" height="23"  nowrap >응답자명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<input name="respondNm" type="text" title="응답자명 입력" size="73" value="${Emplyrinfo.emplyrNm}" maxlength="50" style="width:120px;">
    </td>
  </tr>
</table>
<!-- 등록  폼 영역  -->

<!--  서브타이틀 Start -->
<div class="title-name-1">설문정보</div>
<!--  서브타이틀 End -->

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
    		<c:out value="${Comtnqestnrinfo[0].qestnrBeginDe}" /> ~ <c:out value="${Comtnqestnrinfo[0].qestnrEndDe}" />
    	</td>
    </tr>
    </table>

    </td>
</tr>

<%-- 설문템플릿설정 --%>
<%-- <c:import url="/la/uss/olp/qri/egovQustnrRespondInfoManageTemplate.do" charEncoding="utf-8">
	<c:param name="templateUrl" value="${QustnrTmplatManage[0].qestnrTmplatCours}" />
</c:import> --%>

<c:forEach items="${Comtnqustnrqesitm}" var="QestmInfo" varStatus="status1">
<tr>
    <td colspan="2">
    	<table width="100%" border="0" cellpadding="2" cellspacing="0">
    	<tr>
    		<td style="background-color:#E3E3E3;">
    		<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시">${status1.count}. <c:out value="${fn:replace(QestmInfo.qestnCn , crlf , '<br/>')}" escapeXml="false" />
    		<c:if test="${QestmInfo.mxmmChoiseCo >  1}"><font color="red"><b>(최대선택건수 ${QestmInfo.mxmmChoiseCo})</b></font></c:if>
    		</td>
    	</tr>
    	<tr>
    	<td style="padding:0px 0px 0px 0px;margin:0px 0px 0px 0px;background-color:#E3E3E3;">
		    <%-- 객관식 --%>
		    <c:if test="${QestmInfo.qestnTyCode ==  '1'}">
	    	<table border="0" cellpadding="0" cellspacing="0">
	    	<tr>
		    	<c:forEach items="${Comtnqustnriem}" var="QestmItem" varStatus="status01">
		    	<c:if test="${QestmInfo.qestnrTmplatId eq QestmItem.qestnrTmplatId && QestmInfo.qestnrId eq QestmItem.qestnrId && QestmInfo.qestnrQesitmId eq QestmItem.qestnrQesitmId}">
		    		<td style="background-color:#E3E3E3;">

		    		<%-- 다중체크구현 로직 --%>
		    		<c:if test="${QestmInfo.mxmmChoiseCo ==  '1'}">
		    		<input type="radio" name="${QestmItem.qestnrQesitmId}" value="${QestmItem.qustnrIemId}" style="border:0px;"> <c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false" />
		    		</c:if>

		    		<c:if test="${QestmInfo.mxmmChoiseCo >  1}">
		    		<input type="checkbox" name="${QestmItem.qestnrQesitmId}" value="${QestmItem.qustnrIemId}" id="${QestmItem.qestnrQesitmId}" onClick="fn_egov_checkbox_amout('${QestmItem.qestnrQesitmId}', ${QestmInfo.mxmmChoiseCo}, this)"  title="체크박스"><c:out value="${fn:replace(QestmItem.iemCn , crlf , '<br/>')}" escapeXml="false"/>
		    		</c:if>
		    		<%-- 기타답변여부 --%>
		    		<c:if test="${QestmItem.etcAnswerAt eq  'Y'}">
		    		<input name="ETC_${QestmItem.qustnrIemId}" id="ETC_${QestmItem.qustnrIemId}" type="text" size="73" value="" maxlength="1000" style="width:150px;" title="기타답변여부">
		    		</c:if>
		    		<c:if test="${QestmItem.etcAnswerAt eq  'N' || QestmItem.etcAnswerAt eq ''}">
		    		<input name="ETC_${QestmItem.qustnrIemId}" id="ETC_${QestmItem.qustnrIemId}" type="hidden" size="73" value="" maxlength="1000">
		    		</c:if>
		    		</td>
		    	</c:if>
		    	</c:forEach>
	    	</tr>
	    	</table>
	    	</c:if>

    		<%-- 주관식 --%>
    		<c:if test="${QestmInfo.qestnTyCode ==  '2'}">
    		<textarea name="${QestmInfo.qestnrQesitmId}" id="${QestmInfo.qestnrQesitmId}" class="textarea"  cols="75" rows="4"  style="width:99%;" value="" title="주관식내용입력"></textarea>
    		</c:if>
	    </td>
    	</tr>
    	</table>
    </td>
</tr>
<tr>
	<td colspan="2" height="1">
	<%-- 최대선택 건수 --%>
	<input type="hidden" name="MXMM_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.mxmmChoiseCo}">
	<%-- 객관식/주관식  타입 --%>
	<input type="hidden" name="TY_${QestmInfo.qestnrQesitmId}" value="${QestmInfo.qestnTyCode}">
	</td>
</tr>
</c:forEach>


</table>
</form>
<!--  등록  폼 영역 End -->

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_save();return false;">저장</a>
	<!-- <a href="#@" onclick="fn_formReset();return false;">초기화</a> -->
	<a href="#@" onclick="javascript:fn_egov_list();return false;">목록</a>
</div>
<!-- 버튼 End  -->