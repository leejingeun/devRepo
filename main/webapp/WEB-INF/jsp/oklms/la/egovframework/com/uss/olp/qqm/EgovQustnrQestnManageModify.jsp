<%--
  Class Name : EgovQustnrQestnManageModify.jsp
  Description : 설문문항 수정 페이지
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
		varFrom.action = "<c:url value='/la/uss/olp/qqm/goUpdateEgovQustnrQestnManage.do' />";
		varFrom.submit();
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
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문문항 수정</div>
<!--  상단타이틀 End -->
<form:form commandName="frm" name="frm" method="post">
<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
<input name="cmd" type="hidden" value="<c:out value='save'/>">

<!--  등록  폼 영역 Start -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
<caption>등록 을 제공한다</caption>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnrTmplatId">설문지정보(제목)</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      ${resultList[0].qestnrSj}
      <input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
      <input name="qestnrId" id="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap><label for="qestnSn">질문순번</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      <input name="qestnSn" type="text" size="50" value="${resultList[0].qestnSn}" maxlength="70" style="width:60px;" title="질문순번 입력">
      <div><form:errors path="qestnSn" cssClass="error" /></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap ><label for="qestnTyCode">질문유형</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>

<select name="qestnTyCode" title="질문유형 선택">
	<option value="">선택</option>
	<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
		<option value="${comCodeList.code}" <c:if test="${comCodeList.code eq resultList[0].qestnTyCode}">selected</c:if>>${comCodeList.codeNm}</option>
	</c:forEach>
</select>

     </td>
  </tr>
  <tr>
    <th scope="row" height="23"  ><label for="qestnCn">질문 내용</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
      <textarea name="qestnCn" id="qestnCn" class="textarea"  cols="75" rows="10" title="질문내용 입력" style="width:99%;">${resultList[0].qestnCn}</textarea>
     <div><form:errors path="qestnCn" cssClass="error"  /></div>
    </td>
  </tr>
  <tr>
    <th scope="row" width="20%" height="23"  nowrap ><label for="required">최대선택건수</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
       <select name="mxmmChoiseCo" title="최대선택건수 선택">
       	<option value="1" <c:if test="${resultList[0].mxmmChoiseCo == '1'}">selected</c:if>>1</option>
       	<option value="2" <c:if test="${resultList[0].mxmmChoiseCo == '2'}">selected</c:if>>2</option>
       	<option value="3" <c:if test="${resultList[0].mxmmChoiseCo == '3'}">selected</c:if>>3</option>
       	<option value="4" <c:if test="${resultList[0].mxmmChoiseCo == '4'}">selected</c:if>>4</option>
       	<option value="5" <c:if test="${resultList[0].mxmmChoiseCo == '5'}">selected</c:if>>5</option>
       	<option value="6" <c:if test="${resultList[0].mxmmChoiseCo == '6'}">selected</c:if>>6</option>
       	<option value="7" <c:if test="${resultList[0].mxmmChoiseCo == '7'}">selected</c:if>>7</option>
       	<option value="8" <c:if test="${resultList[0].mxmmChoiseCo == '8'}">selected</c:if>>8</option>
       	<option value="9" <c:if test="${resultList[0].mxmmChoiseCo == '9'}">selected</c:if>>9</option>
       	<option value="10" <c:if test="${resultList[0].mxmmChoiseCo == '10'}">selected</c:if>>10</option>
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

