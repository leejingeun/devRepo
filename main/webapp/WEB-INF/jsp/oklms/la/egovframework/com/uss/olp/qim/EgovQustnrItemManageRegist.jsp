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
	if( !com.checkRequiredField( $("#qestnrId") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatId") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrQesitmCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrQesitmId") ) ){
		return;
	}
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
	var varFrom = document.frm;
	
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		
		var reqUrl = "/la/uss/olp/qim/goInsertEgovQustnrItemManage.do";
		
		$("#frm").attr("action", reqUrl);
		$("#frm").attr("target", "_self");
		$("#frm").submit();
	}

	/* if(confirm("<spring:message code="common.save.msg" />")){

		varFrom.action =  "<c:url value='/la/uss/olp/qim/EgovQustnrItemManageRegist.do' />";

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

		if(!validateQustnrItemManageVO(varFrom)){
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

	$("#qestnrId").val(qestnrId);
	$("#qestnrTmplatId").val(qestnrTmplatId);
	$("#qestnrCn").val(qestnrCn);
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
	}
}

/* 팝업창에서 설문지문항정보 제목을 클릭시 전달정보 셋팅 */
function setSelectInfo2(param1, param2, param3){
		var qestnrQesitmCn = param3;
		var qestnrQesitmId = param1;
		var qestnTyCode = param2;

		$("#qestnrQesitmCn").val(qestnrQesitmCn);
		$("#qestnrQesitmId").val(qestnrQesitmId);
}


  /* function fn_egov_QustnrQestnManageListPopup_QustnrItemManage(){
	var sParam = "";
	sParam = sParam + "searchCondition=QESTNR_ID";
	sParam = sParam + "&searchKeyword=" + document.getElementById("qestnrId").value;

  	window.showModalDialog("<c:url value='/la/uss/olp/qqm/popupEgovQustnrQestnManageList.do' />?" + sParam, self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
  } */
</script>

<!-- 상단타이틀 <!--  상단타이틀 Start -->
<div class="title-name-1">설문항목 등록</div>
<!--  상단타이틀 End -->
<form:form commandName="frm" name="frm" method="post">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<tr>
<th scope="row" width="20%" height="23"  nowrap>설문정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
  <input name="qestnrCn" id="qestnrCn" type="text" size="73" value="" title="설문정보 입력" maxlength="4000" style="width:300px;" readonly>
   <a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qestnrCnPop( ); return false" onkeypress="this.onclick;">검색</a>
  <input name="qestnrId" id="qestnrId" type="hidden" value="">
  <input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="">
</td>
</tr>
<tr>
<th scope="row" width="20%" height="23"  nowrap>설문문항정보<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
  <input name="qestnrQesitmCn" id="qestnrQesitmCn" type="text" title="설문문항정보 입력" size="73" value="" maxlength="4000" style="width:300px;" readonly>
   <a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qestnrQesitmCnPop( ); return false" onkeypress="this.onclick;">검색</a>
  <input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="">
</td>
</tr>
<tr>
<th scope="row" height="23">항목 순번<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td>
  <input name="iemSn" id="iemSn" type="text" size="5" value="1" title="항목 순번" value="" maxlength="5" style="width:100px;">
  <div><form:errors path="iemSn" cssClass="error" /></div>
</td>
</tr>
<tr>
<th scope="row" height="23">항목 내용<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td>
  <textarea name="iemCn" title="항목 내용 입력" cols="75" rows="5"  style="width:100%;"></textarea>
  <div><form:errors path="iemCn" cssClass="error" /></div>
</td>
</tr>
<tr>
<th scope="row" width="20%" height="23"  nowrap >기타답변여부<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
<td width="80%" nowrap>
   <select name="etcAnswerAt" title="기타답변여부 선택">
   	<option value="N">N</option>
   	<option value="Y">Y</option>
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
