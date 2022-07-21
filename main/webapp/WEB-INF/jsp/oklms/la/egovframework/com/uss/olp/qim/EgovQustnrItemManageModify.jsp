<%--
  Class Name : EgovQustnrItemManageRegist.jsp
  Description : 설문항목 등록 페이지
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
	$("#frm").find("input,textarea").val("");
}

/* 입력 필드 유효성 첵크 */
function fn_egov_validateForm() {		
	if( !com.checkRequiredField( $("#iemSn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#iemCn") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qim/listEgovQustnrItemManage.do' />";
}

/* 저장처리 */
function fn_egov_save(){
	var varFrom = document.getElementById("frm");

	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		varFrom.action = "<c:url value='/la/uss/olp/qim/goUpdateEgovQustnrItemManage.do' />";
		varFrom.submit();
	}
}
</script>
</head>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문항목 수정</div>
<!--  상단타이틀 End -->
<form:form commandName="frm" name="frm" method="post">
<input name="cmd" type="hidden" value="<c:out value='save'/>">
<input id="qustnrIemId" name="qustnrIemId" type="hidden" value="<c:out value="${resultList[0].qustnrIemId}" />">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<tr> 
<th scope="row" width="20%" height="23"  nowrap>설문정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
  <c:out value="${fn:replace(resultList[0].qestnrCn , crlf , '<br/>')}" escapeXml="false" />
  <input name="qestnrId" type="hidden" value="<c:out value="${resultList[0].qestnrId}" />">
  <input name="qestnrTmplatId" type="hidden" value="<c:out value="${resultList[0].qestnrTmplatId}" />">  
</td>
</tr>
<tr> 
<th scope="row" width="20%" height="23"  nowrap>설문문항정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
  <c:out value="${fn:replace(resultList[0].qestnrQesitmCn , crlf , '<br/>')}" escapeXml="false" />
  <input name="qestnrQesitmId" type="hidden" title="설문문항정보" value="<c:out value="${resultList[0].qestnrQesitmId}" />">
</td>
</tr>
<tr> 
<th scope="row" height="23"  >항목 순번<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td>
  <input name="iemSn" type="text" size="5" title="항목 순번" value="<c:out value="${resultList[0].iemSn}" />" maxlength="5" style="width:100px;">
  <div><form:errors path="iemSn" cssClass="error" /></div>
</td>
</tr> 
<tr> 
<th scope="row" height="23"  >항목 내용<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td>
  <textarea name="iemCn" class="textarea"  cols="75" rows="5" title="항목 내용" style="width:100%;"><c:out value="${resultList[0].iemCn}" /></textarea>
  <div><form:errors path="iemCn" cssClass="error" /></div>  
</td>
</tr> 
<tr> 
<th scope="row" width="20%" height="23"  nowrap >기타답변여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
   <select name="etcAnswerAt" title="기타답변여부">
   	<option value="N" <c:if test="${resultList[0].etcAnswerAt ==  'N'}">selected</c:if>>N</option>
   	<option value="Y" <c:if test="${resultList[0].etcAnswerAt ==  'Y'}">selected</c:if>>Y</option>
  	</select>
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
