<%--
  Class Name : EgovQustnrRespondManageModify.jsp
  Description : 응답자정보 수정 페이지
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
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/*====================================================================
화면 초기화 
====================================================================*/
$(document).ready(function() {
});

/*====================================================================
사용자 정의 함수  
====================================================================*/

/* 입력 필드 초기화 */ 
function fn_formReset(){
	$("#frm").find("input,textarea,select").val("");
}

/* 입력 필드 유효성 첵크 */
function fn_egov_validateForm() {		
	if( !com.checkRequiredField( $("#sexdstnCode") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#occpTyCode") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#respondNm") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#areaNo") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#middleTelno") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#endTelno") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qrm/listEgovQustnrRespondManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
	var vFrom = document.frm;

	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		var brth = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');
		vFrom.brth.value = brth;
		
		vFrom.action = "<c:url value='/la/uss/olp/qrm/goUpdateEgovQustnrRespondManage.do'/>";
		vFrom.submit();
	}
	
	/* if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/la/uss/olp/qrm/EgovQustnrRespondManageModify.do' />";

		varFrom.brth.value = fn_egov_SelectBoxValue('brthYYYY') + "" + fn_egov_SelectBoxValue('brthMM') + "" + fn_egov_SelectBoxValue('brthDD');

		if(!validateQustnrRespondManageVO(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	} */
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
<div class="title-name-1">응답자정보 수정</div>
<!--  상단타이틀 End -->

<form:form commandName="frm" name="frm" method="post">
<input name="qestnrRespondId" type="hidden" value="${resultList[0].qestnrRespondId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">d

<!-- 등록  폼 영역  --><!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문관리정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
	<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    </td>
  </tr>
  <tr> 
    <th  width="20%" height="23"   >성별<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
	<select name="sexdstnCode" title="성별 선택">
		<option value="">선택</option>
		<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
			<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].sexdstnCode}">selected</c:if>>${comCodeList.codeNm}</option>
		</c:forEach>
	</select>
	<div><form:errors path="sexdstnCode"/></div> 
    </td>
  </tr>
  <tr> 
    <th scope="row" width="20%" height="23" nowrap >직업<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
	<select name="occpTyCode" title="직업 선택">
		<option value="">선택</option>
		<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
			<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].occpTyCode}">selected</c:if>>${comCodeList.codeNm}</option>
		</c:forEach>
	</select>
	<div><form:errors path="occpTyCode"/></div> 
    </td>
  </tr>
  <tr>
    <th scope="row" height="23"  >생년월일<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
       <select name="brthYYYY" id="brthYYYY" title="년도 선택">
	     <c:forEach var="h" begin="1930" end="2016" step="1">
	      	<option value="${h}" <c:if test="${fn:substring(resultList[0].brthdy, 0, 4) ==  h}">selected</c:if>>${h}</option>
	      </c:forEach>
       </select>년
       <select name="brthMM" id="brthMM" title="월 선택">
	     <c:forEach var="h" begin="1" end="12" step="1">
			<c:choose>
			    <c:when test="${h < 10}">
			 	<c:set var="brthMM" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    <c:set var="brthMM" value="${h}"/>
			    </c:otherwise>
			</c:choose>
	      	<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(resultList[0].brthdy, 4, 6) ==  brthMM}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      </c:forEach>
       </select>월
       <select name="brthDD" id="brthDD" title="일 선택">
	     <c:forEach var="h" begin="1" end="31" step="1">
			<c:choose>
			    <c:when test="${h < 10}">
			 	<c:set var="brthDD" value="0${h}"/>
			    </c:when>
			    <c:otherwise>
			    <c:set var="brthDD" value="${h}"/>
			    </c:otherwise>
			</c:choose>
	      	<option value="<c:if test="${h < 10}">0</c:if>${h}" <c:if test="${fn:substring(resultList[0].brthdy, 6, 8) ==  brthDD}">selected</c:if>><c:if test="${h < 10}">0</c:if>${h}</option>
	      </c:forEach>
       </select>일
       <input name="brth" id="brth" type="hidden" value="">

    </td>
  </tr>

  <tr>
    <th scope="row" width="20%" height="23"  nowrap >응답자명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<input name="respondNm" type="text" title="응답자명 입력" size="73" value="${resultList[0].respondNm}" maxlength="50" style="width:120px;">
		<div><form:errors path="respondNm" cssClass="error" /></div>
    </td>
  </tr>

  <tr>
    <th scope="row" width="20%" height="23"  nowrap >전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input name="areaNo" type="text" title="지역번호 입력" size="4" value="${resultList[0].areaNo}" maxlength="4">-
      <input name="middleTelno" type="text" title="국번 입력" size="4" value="${resultList[0].middleTelno}" maxlength="4">-
      <input name="endTelno" type="text" size="4" title="번호 입력" value="${resultList[0].endTelno}" maxlength="4">
      <div><form:errors path="areaNo" cssClass="error" /></div>
      <div><form:errors path="middleTelno" cssClass="error" /></div>
      <div><form:errors path="endTelno" cssClass="error" /></div>
    </td>
  </tr>
</table>
</form:form>
<!--  등록  폼 영역 End -->

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_save();return false;">저장</a>
	<a href="#@" onclick="fn_formReset();return false;">초기화</a>
	<a href="#@" onclick="javascript:fn_egov_list();return false;">목록</a>
</div>
<!-- 버튼 End  -->
