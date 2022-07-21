<%--
  Class Name : EgovQustnrManageModify.jsp
  Description : 설문관리 수정 페이지
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
<script type="text/javaScript" language="javascript">
/*====================================================================
화면 초기화 
====================================================================*/
$(document).ready(function() {
	document.frm.qestnrSj.focus();
	com.datepicker('qestnrBeginDe');
	com.datepicker('qestnrEndDe');
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
	var varFrom = document.frm;
	
	var sStartDay = varFrom.qestnrBeginDe.value.replaceAll("-","");
	var sEndDay = varFrom.qestnrEndDe.value.replaceAll("-","");
	
	alert("sStartDay: "+sStartDay);
	alert("sEndDay: "+sEndDay);
	
	var iStartDay = parseInt(sStartDay);
	var iEndDay = parseInt(sEndDay);
	
	alert("iStartDay: "+iStartDay);
	alert("iEndDay: "+iEndDay);
	
	if( !com.checkRequiredField( $("#qestnrSj") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrPurps") ) ){
		return;
	} 	
	if( !com.checkRequiredField( $("#qestnrWritngGuidanceCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrBeginDe") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrEndDe") ) ){
		return;
	}
	if(iStartDay > iEndDay || iEndDay < iStartDay){
		alert("설문기간 시작일은 종료일 보다 클수 없고 \n\n설문기간 종료일은 시작일 보다 작을수 없습니다!");
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTrget") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatId") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qmc/listEgovQustnrManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_save(){
	var varFrom = document.frm;
	
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		varFrom.action = "<c:url value='/la/uss/olp/qmc/goUpdateEgovQustnrManage.do' />";
		varFrom.submit();
	}

/* 	var sStartDay = form.qestnrBeginDe.value.replaceAll("-","");
	var sEndDay = form.qestnrEndDe.value.replaceAll("-","");

	var iStartDay = parseInt(sStartDay);
	var iEndDay = parseInt(sEndDay);

	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateQustnrManageVO(form)){
			return;
		}else{
			if(iStartDay > iEndDay || iEndDay < iStartDay){
				alert("설문기간  시작일은 종료일  보다 클수 없고 \n\n설문기간 종료일은 시작일 보다 작을수 없습니다!");
				return;
			}
			form.submit();
		}
	} */
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

<c:set var="qustnrManageVO.qestnrSj" value="${resultList[0].qestnrPurps}"/>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문관리 수정</div>
<!--  상단타이틀 End -->
<form:form commandName="frm" name="frm" method="post">
<input name="qestnrTmplatIdTemp" id="qestnrTmplatIdTemp" type="hidden" value="${resultList[0].qestnrTmplatId}">
<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">
<input type="hidden" name="cal_url" value="<c:url value='/sym/cal/EgovNormalCalPopup.do'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<caption>수정 을 제공한다</caption>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnrSj">설문제목</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input type="text" name="qestnrSj" id="qestnrSj" size="73" maxlength="250" title="설문제목 입력" value="${resultList[0].qestnrSj} "/>
      <form:errors path="qestnrSj"/>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >설문목적<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    	<textarea id="qestnrPurps" name="qestnrPurps" style="width: 98%;height: 200px;" rows="20" cols="80" title="설문목적 입력" style="width:99%;">${resultList[0].qestnrPurps}</textarea>
    	<form:errors path="qestnrPurps"/>
    </td>
  </tr>
  <tr>
    <th scope="row" height="23" ><label for="qestnrWritngGuidanceCn">설문작성안내 내용</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
    	<textarea id="qestnrWritngGuidanceCn" name="qestnrWritngGuidanceCn" style="width: 98%;height: 200px;" rows="20" cols="80" title="설문작성안내내용 입력" style="width:99%;">${resultList[0].qestnrWritngGuidanceCn}</textarea>
    	<form:errors path="qestnrWritngGuidanceCn"/>
    </td>
  </tr>
  <tr,>
    <th scope="row" width="20%" height="23"  nowrap ><label for="qestnrBeginDe">설문기간</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input type="text" name="qestnrBeginDe" id="qestnrBeginDe" value='<c:out value="${resultList[0].qestnrBeginDe}" />' size="12" maxlength="10" readonly="true" title="설문대상 시작일 입력"/>
      <form:errors path="qestnrBeginDe"/>
      ~<input type="text" name="qestnrEndDe" id="qestnrEndDe" value='<c:out value="${resultList[0].qestnrEndDe}" />' size="12" maxlength="10" readonly="true" title="설문대상 종료일 입력"/>
      <form:errors path="qestnrEndDe"/>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnrTrget">설문대상</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<select name="qestnrTrget" id="qestnrTrget" style="width:110px;">
			<option value=''>--선택--</option>
			<c:if test="${resultList[0].qustnrTrget == '99999'}">
				<option selected value='99999'>전체</option>
			</c:if>
			<c:if test="${resultList[0].qustnrTrget != '99999'}">
				<option value='99999'>전체</option>
			</c:if>
			<c:forEach var="authGroupCd" items="${authGroupCode}" varStatus="status">
			<option value="${authGroupCd.codeId}" ${authGroupCd.codeId == resultList[0].qustnrTrget ? 'selected="selected"' : '' }>${authGroupCd.codeName}</option>
			</c:forEach>
		</select>
		<div><form:errors path="qestnrTrget"/></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap >템플릿 유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
		<select name="qestnrTmplatId" id="qestnrTmplatId" style="width:110px;">
			<option selected value=''>--선택--</option>
			<c:forEach var="surveyTmplatCd" items="${surveyTmplatCode}" varStatus="status">
				<option value="${surveyTmplatCd.codeId}" ${surveyTmplatCd.codeId == resultList[0].qestnrTmplatId ? 'selected="selected"' : '' }>${surveyTmplatCd.codeName}</option>
			</c:forEach>
		</select>
		<div><form:errors path="qestnrTrget"/></div>
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

