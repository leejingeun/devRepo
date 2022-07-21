<%--
  Class Name : EgovQustnrRespondInfoModify.jsp
  Description : 설문조사 수정 페이지
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
<validator:javascript formName="qustnrRespondInfoVO" staticJavascript="false" xhtml="true" cdata="false"/>
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
	if( !com.checkRequiredField( $("#qustnrIemCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qustnrIemId") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#respondAnswerCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#etcAnswerCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#respondNm") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qri/listEgovQustnrRespondInfo.do' />";
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
var varFrom = document.frm;
	
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		
		var reqUrl = "/la/uss/olp/qri/goUpdateEgovQustnrRespondInfo.do";
		
		$("#frm").attr("action", reqUrl);
		$("#frm").attr("target", "_self");
		$("#frm").submit();
	}

	/* if(confirm("<spring:message code="common.save.msg" />")){
		varFrom.action =  "<c:url value='/la/uss/olp/qri/EgovQustnrRespondInfoModify.do' />";

		if(!validateQustnrRespondInfoVO(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	} */
}
/* 설문항목정보 팝업창열기 */
function fn_egov_search_qustnrIemCnPop(){
	popOpenWindow("", "popSearch", 800, 500);
	
	var reqUrl = "/la/uss/olp/qim/popupEgovQustnrItemManageList.do";
	
	$("#frm").attr("action", reqUrl);
	$("#frm").attr("target", "popSearch");
	$("#frm").submit();
}

/* 팝업창에서 설문항목정보 선택버튼을 클릭시 전달정보 셋팅 */
function setSelectQustnrIemCn1(strSelectInfo){
	if( strSelectInfo ){
		var arrSelectInfo = strSelectInfo.split("||");
		var qustnrIemCn = arrSelectInfo[0];
		var qustnrIemId = arrSelectInfo[3];

		$("#qustnrIemCn").val(qustnrIemCn);
		$("#qustnrIemId").val(qustnrIemId);		
	}
}

/* 팝업창에서 설문항목정보 제목을 클릭시 전달정보 셋팅 */
function setSelectQustnrIemCn2(param1, param2, param3, param4){
	var qestnrId = param1;
	var qestnrTmplatId = param2;
	var qustnrIemId = param3;
	var qustnrIemCn = param4;
	

	$("#qustnrIemCn").val(qustnrIemCn);
	$("#qustnrIemId").val(qustnrIemId);
}
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문조사 수정</div>
<!--  상단타이틀 End -->

<form:form commandName="frm" name="frm" action="" method="post">
<input name="qestnrQesrspnsId" type="hidden" value="${resultList[0].qestnrQesrspnsId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문관리정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
	<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
	<input name="qestnrId" id="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
	<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문문항정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    <c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
	<input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    	<div id="divQestnTyCode">
    <c:if test="${resultList[0].qestnTyCode == '1'}">객관식</c:if>
    <c:if test="${resultList[0].qestnTyCode == '2'}">주관식</c:if>
    	</div>
      <!-- <input name="title" type="text" size="73" value="" maxlength="70" style="width:100%;">   -->
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문항목정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<input name="qustnrIemCn" id="qustnrIemCn" type="text" title="설문문항정보" size="73" value="${resultList[0].iemCn}" maxlength="4000" style="width:300px;" readonly>
   		 <a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qustnrIemCnPop( ); return false" onkeypress="this.onclick;">검색</a>
		<input name="qustnrIemId" id="qustnrIemId" type="hidden" value="${resultList[0].qustnrIemId}">
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >
    <DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">응답자답변내용</DIV><br><DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">(주관식)</DIV></th>
    <td width="80%" nowrap>
      <textarea name="respondAnswerCn" class="textarea" title="응답자답변내용"  cols="75" rows="4"  style="width:100%;">${resultList[0].respondAnswerCn}</textarea>
      <div><form:errors path="respondAnswerCn" cssClass="error" /></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >
    	<DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">기타답변내용</DIV>
    </th>
    <td width="80%" nowrap>
      <textarea name="etcAnswerCn" class="textarea" title="기타답변내용"  cols="75" rows="4"  style="width:100%;">${resultList[0].etcAnswerCn}</textarea>
      <div><form:errors path="etcAnswerCn" cssClass="error" /></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >응답자명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<input name="respondNm" type="text" title="응답자명" size="50" value="<c:out value="${fn:replace(resultList[0].respondNm , crlf , '<br/>')}" escapeXml="false" />" maxlength="50" style="width:120px;">
		<div><form:errors path="respondNm" cssClass="error" /></div>
    </td>
  </tr>
</table>
</form:form>
<!--  등록  폼 영역 End -->

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_save();return false;">저장</a>
	<!-- <a href="#@" onclick="fn_formReset();return false;">초기화</a> -->
	<a href="#@" onclick="javascript:fn_egov_list();return false;">목록</a>
</div>
<!-- 버튼 End  -->

