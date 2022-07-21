<%--
  Class Name : EgovQustnrQestnManageRegist.jsp
  Description : 설문문항 등록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2016.12.20    이진근          모듈 수정

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.19

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
	$("#frm").find("input,textarea,select").val("");
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
	
	if( !com.checkRequiredField( $("#qestnrCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnSn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnTyCode") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnCn") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list(){
	var varFrom = document.getElementById("frm");
	varFrom.action =  "<c:url value='/la/uss/olp/qqm/listEgovQustnrQestnManage.do' />";
	varFrom.submit();
}

 /* 저장처리 */
function fn_egov_save(){
	var varFrom = document.frm;
	
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		
		var reqUrl = "/la/uss/olp/qqm/goInsertEgovQustnrQestnManage.do";
		
		$("#frm").attr("action", reqUrl);
		$("#frm").attr("target", "_self");
		$("#frm").submit();
	}
	
	/* if(confirm("<spring:message code="common.save.msg" />")){

		if(form.qestnrCn.value == "" ||
				form.qestnrTmplatId.value == "" ||
				form.qestnrId.value == ""
				){
			alert("설문지정보를  입력해주세요!");
			form.qestnrCn.focus();
			return;

		}

		if(!validatefrm(form)){
			return;
		}else{
			form.submit();
		}
	} */
}

 
 /* function fn_egov_search_qestnrCnPop(){
 	window.showModalDialog("<c:url value='/la/uss/olp/qmc/popupEgovQustnrManageList.do' />", self,"dialogWidth:800px;dialogHeight:500px;resizable:yes;center:yes");
 } */
 
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
 
</script>
</head>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문문항 등록</div>
<!--  상단타이틀 End -->
<form name="frm" id="frm" method="post">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<caption>등록 을 제공한다</caption>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnrCn">설문지정보(제목)</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input name="qestnrCn" id="qestnrCn" title="설문지정보(제목) 입력" type="text" size="73" maxlength="2000" style="width:300px;" readonly>
      	<a href="#LINK" class="btn" onclick="javascript:fn_egov_search_qestnrCnPop( ); return false" onkeypress="this.onclick;">검색</a>
      <input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden">
      <input name="qestnrId" id="qestnrId" type="hidden">
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnSn">질문순번</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input name="qestnSn" id="qestnSn" type="text" size="50" value="1" maxlength="10" style="width:60px;" title="질문순번 입력">
      <div><form:errors path="qestnSn"/></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap ><label for="qestnTyCode">질문유형</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
	<select name="qestnTyCode" title="질문유형 선택">
		<option value="">선택</option>
		<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
			<option value="${comCodeList.code}">${comCodeList.codeNm}</option>
		</c:forEach>
	</select>
    </td>
  </tr>
  <tr>
    <th scope="row" height="23"  ><label for="qestnCn">질문 내용</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
      <textarea name="qestnCn" id="qestnCn" class="textarea"  cols="75" rows="10"  style="width:99%;" title="질문내용 입력"></textarea>
      <div><form:errors path="qestnCn"/></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="mxmmChoiseCo">최대선택건수</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
       <select name="mxmmChoiseCo" title="최대선택건수 선택">
       	<option value="1">1</option>
       	<option value="2">2</option>
       	<option value="3">3</option>
       	<option value="4">4</option>
       	<option value="5">5</option>
       	<option value="6">6</option>
       	<option value="7">7</option>
       	<option value="8">8</option>
       	<option value="9">9</option>
       	<option value="10">10</option>
      	</select>
    </td>
  </tr>
</table>
</form>
<!--  등록  폼 영역 End -->

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_save();return false;">저장</a>
	<a href="#@" onclick="fn_formReset();return false;">초기화</a>
	<a href="#@" onclick="javascript:fn_egov_list();return false;">목록</a>
</div>
<!-- 버튼 End  -->
