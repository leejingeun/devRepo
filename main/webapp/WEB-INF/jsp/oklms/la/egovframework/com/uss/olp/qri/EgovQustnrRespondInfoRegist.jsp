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
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>
<script type="text/javaScript" language="javascript">
/*====================================================================
화면 초기화 
====================================================================*/
$(document).ready(function() {
	document.frm.qestnrCn.focus();
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
	if( !com.checkRequiredField( $("#qestnrCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatId") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrId") ) ){
		return;
	} 	
	if( !com.checkRequiredField( $("#qestnrQesitmCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrQesitmId") ) ){
		return;
	}
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

/* 저장처리 */
function fn_egov_save(){
	var varFrom = document.frm;
	
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		
		var reqUrl = "/la/uss/olp/qri/goInsertEgovQustnrRespondInfo.do";
		
		$("#frm").attr("action", reqUrl);
		$("#frm").attr("target", "_self");
		$("#frm").submit();
	}

	/* if(confirm("<spring:message code="common.save.msg" />")){

		varFrom.action =  "<c:url value='/la/uss/olp/qri/EgovQustnrRespondInfoRegist.do' />";

		if(varFrom.qestnrCn.value == "" ||
				varFrom.qestnrTmplatId.value == "" ||
				varFrom.qestnrId.value == ""
				){
			alert("설문지정보를  입력해주세요!");
			varFrom.qestnrCn.focus();
			return;

		}

		if(varFrom.qestnrQesitmCn.value == "" ||
				varFrom.qestnrQesitmId.value == ""
				){
			alert("설문문항정보를  입력해주세요!");
			varFrom.qestnrQesitmCn.focus();
			return;

		}

		if(!validatefrm(varFrom)){
			return;
		}else{
			varFrom.submit();
		}
	} */
}

/* 설문지정보 팝업창열기 */
function fn_egov_search_qestnrCnPop( ){
		popOpenWindow("", "popSearch", 800, 500);
		
		var reqUrl = "/la/uss/olp/qmc/popupEgovQustnrManageList.do";
		
		$("#frm").attr("action", reqUrl);
		$("#frm").attr("target", "popSearch");
		$("#frm").submit();
}

/* 팝업창에서 설문지정보 선택버튼을 클릭시 전달정보 셋팅 */
function setQestnrCn1(strQestnrCn){
	if( strQestnrCn ){
		var arrQestnrCn = strQestnrCn.split("||");
		var qestnrCn = arrQestnrCn[0];
		var qestnrTmplatId = arrQestnrCn[1];
		var qestnrId = arrQestnrCn[2];

		$("#qestnrCn").val(qestnrCn);
		$("#qestnrTmplatId").val(qestnrTmplatId);
		$("#qestnrId").val(qestnrId);
	}
}

/* 팝업창에서 설문지정보 제목을 클릭시 전달정보 셋팅 */
function setQestnrCn2(param1, param2, param3){
 	var qestnrId = param1;
 	var qestnrTmplatId = param2;
 	var qestnrCn = param3;

	$("#qestnrCn").val(qestnrCn);
	$("#qestnrTmplatId").val(qestnrTmplatId);
	$("#qestnrId").val(qestnrId);
}

/* 설문지문항정보 팝업창열기 */
function fn_egov_search_qestnrQesitmCnPop(){
	popOpenWindow("", "popSearch", 800, 500);
	
	var reqUrl = "/la/uss/olp/qqm/popupEgovQustnrQestnManageList.do";
	
	$("#frm").attr("action", reqUrl);
	$("#frm").attr("target", "popSearch");
	$("#frm").submit();
}

/* 팝업창에서 설문지문항정보 선택버튼을 클릭시 전달정보 셋팅 */
function setSelectInfo1(strSelectInfo){
	if( strSelectInfo ){
		var arrSelectInfo = strSelectInfo.split("||");
		var qestnrQesitmCn = arrSelectInfo[0];
		var qestnrQesitmId = arrSelectInfo[1];
		var qestnTyCode = arrSelectInfo[2];

		$("#qestnrQesitmCn").val(qestnrQesitmCn);
		$("#qestnrQesitmId").val(qestnrQesitmId);
		
		if(qestnTyCode == '1'){
			$("#divQestnTyCode").text('객관식');	
		}
		if(qestnTyCode == '2'){
			$("#divQestnTyCode").text('주관식');
		}
	}
}

/* 팝업창에서 설문지문항정보 제목을 클릭시 전달정보 셋팅 */
function setSelectInfo2(param1, param2, param3){
	var qestnrQesitmCn = param3;
	var qestnrQesitmId = param1;
	var qestnTyCode = param2;
	
	$("#qestnrQesitmCn").val(qestnrQesitmCn);
	$("#qestnrQesitmId").val(qestnrQesitmId);
	
	if(qestnTyCode == '1'){
		$("#divQestnTyCode").text('객관식');	
	}
	if(qestnTyCode == '2'){
		$("#divQestnTyCode").text('주관식');
	}
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

 /* 설문지정보 팝업창열기 / 설문관리정보 */
 /* function fn_egov_QustnrManageListPopup_QustnrItemManage(){
 	window.showModalDialog("<c:url value='/la/uss/olp/qmc/popupEgovQustnrManageList.do' />", self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
 } */
 
 /* 설문지문항정보 팝업창열기 / 설문문항정보 */
/*   function fn_egov_QustnrQestnManageListPopup_QustnrItemManage(){
	var sParam = "";
	sParam = sParam + "searchCondition=QESTNR_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrId").value;

  	window.showModalDialog("<c:url value='/la/uss/olp/qqm/popupEgovQustnrQestnManageList.do' />?" + sParam, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
  } */
 
 /* 설문항목정보 팝업창열기 / 설문항목정보 */
/* function fn_egov_QustnrItemManageListPopup_QustnrItemManage(){

	var sParam = "";
	sParam = sParam + "searchCondition=QUSTNR_QESITM_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrQesitmId").value;

  	window.showModalDialog("<c:url value='/la/uss/olp/qim/popupEgovQustnrItemManageList.do' />?" + sParam, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");

} */
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문조사 등록</div>
<!--  상단타이틀 End -->
<form:form commandName="frm" name="frm" method="post">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!-- 등록  폼 영역  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
  <tr>
    <th width="20%" height="23">설문관리정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
	<input name="qestnrCn" id="qestnrCn" type="text" title="설문관리정보 입력" size="73" value="" maxlength="4000" style="width:300px;" readonly>
     	<a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qestnrCnPop( ); return false" onkeypress="this.onclick;">검색</a>
	<input name="qestnrId" id="qestnrId" type="hidden" value="">
	<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="">
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">설문문항정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
	<input name="qestnrQesitmCn" id="qestnrQesitmCn" type="text" title="설문문항정보 입력" size="73" value="" maxlength="4000" style="width:300px;" readonly>
    	<a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qestnrQesitmCnPop( ); return false" onkeypress="this.onclick;">검색</a>
	<input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="">
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">설문유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
    	<div id="divQestnTyCode"></div>
      <!-- <input name="title" type="text" size="73" value="" maxlength="70" style="width:100%;">   -->
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">설문항목정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
		<input name="qustnrIemCn" id="qustnrIemCn" type="text" title="설문항목정보 입력" size="73" value="" maxlength="4000" style="width:300px;" readonly>
         <a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qustnrIemCnPop( ); return false" onkeypress="this.onclick;">검색</a>
		<input name="qustnrIemId" id="qustnrIemId" type="hidden" value="">
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23">
    <DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">응답자답변내용</DIV><br><DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">(주관식)</DIV></th>
    <td width="80%" >
      <textarea name="respondAnswerCn" class="textarea" title="응답자답변내용 입력"  cols="75" rows="4"  style="width:100%;"></textarea>
      <div><form:errors path="respondAnswerCn" cssClass="error" /></div>
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23">
    <DIV style="padding:0px 0px 0px 0px;margin:0px 15px 0px 0px;border:solid 0px;">기타답변내용</DIV>
    <td width="80%" >
      <textarea name="etcAnswerCn" class="textarea" title="기타답변내용 입력"  cols="75" rows="4"  style="width:100%;"></textarea>
      <div><form:errors path="etcAnswerCn" cssClass="error" /></div>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">응답자명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" >
		<input name="respondNm" type="text" title="응답자명 입력" size="50" value="" maxlength="50" style="width:120px;">
		<div><form:errors path="respondNm" cssClass="error" /></div>
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
