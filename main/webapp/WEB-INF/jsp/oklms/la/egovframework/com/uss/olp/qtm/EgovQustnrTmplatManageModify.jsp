<%--
  Class Name : /la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageModify.jsp
  Description : 설문템플릿 수정 페이지
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
	document.board.qestnrTmplatTy.focus();
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
	if( !com.checkRequiredField( $("#qestnrTmplatTy") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatCn") ) ){
		return;
	}
	if( !com.checkRequiredField( $("#qestnrTmplatCours") ) ){
		return;
	}
	
	return true;
}

/* 목록 으로 가기 */
function fn_egov_list_QustnrTmplatManage(){
	location.href = "<c:url value='/la/uss/olp/qtm/listEgovQustnrTmplatManage.do' />";
}

/* 저장처리 */
function fn_egov_save_QustnrTmplatManage(){
	if (fn_egov_validateForm() && confirm('<spring:message code="common.regist.msg" />')) {
		var vFrom = document.qustnrTmplatManage;
		vFrom.action = "<c:url value='/la/uss/olp/qtm/updateEgovQustnrTmplatManage.do'/>";
		vFrom.submit();
	}
}

</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문템플릿 수정</div>
<!--  상단타이틀 End -->

<form:form commandName="frm"  name="frm" method="post">
<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId} ">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<caption>수정 을 제공한다</caption>
<tbody>
  <tr>
    <th height="23">템플릿유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
      <input id="qestnrTmplatTy" name="qestnrTmplatTy" type="text" style="width:99%" value="${resultList[0].qestnrTmplatTy} ">
      <div><form:errors path="qestnrTmplatTy"/></div>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">템플릿설명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%">
    	<textarea id="qestnrTmplatCn" name="qestnrTmplatCn" style="width: 98%;height: 200px;" rows="20" cols="80" title="템플릿설명" style="width:99%;">${resultList[0].qestnrTmplatCn}</textarea>
    	<div><form:errors path="qestnrTmplatCn"/></div>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">템플릿파일(경로)<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%">
    <input id="qestnrTmplatCours" name="qestnrTmplatCours" type="text" style="width:99%" value="${resultList[0].qestnrTmplatCours} ">
    <div><form:errors path="qestnrTmplatCours"/></div>
    </td>
  </tr>
  </tbody>
</table>
</form:form>
<!--  등록  폼 영역 End -->

<!-- 버튼 Start  -->
<div class="page-btn">
	<a href="#@" onclick="javascript:fn_egov_save_QustnrTmplatManage();return false;">저장</a>
	<a href="#@" onclick="fn_formReset();return false;">초기화</a>
	<a href="#@" onclick="javascript:fn_egov_list_QustnrTmplatManage();return false;">목록</a>
</div>
<!-- 버튼 End  -->

