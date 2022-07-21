<%--
  Class Name : EgovQustnrRespondInfoDetail.jsp
  Description : 설문조사 상세보기
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
$(document).ready(function() {
	
});
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qri/listEgovQustnrRespondInfo.do' />";
}
/* ********************************************************
 * 수정처리화면
 ******************************************************** */
function fn_egov_modify(qestnrQesrspnsId){
	var vFrom = document.frm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qri/goUpdateEgovQustnrRespondInfo.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete(qestnrQesrspnsId){
	var vFrom = document.frm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.qestnrTmplatId.value = document.getElementById("qestnrTmplatIdTemp").value;
		vFrom.action = "<c:url value='/la/uss/olp/qri/getEgovQustnrRespondInfo.do' />";
		vFrom.submit();
	}
}
</script>

<!-- 상단타이틀 -->
<div class="title-name-1">설문조사 상세보기</div>
<form name="frm" id="frm" method="post">
<input name="qestnrQesrspnsId" type="hidden" value="<c:out value="${resultList[0].qestnrQesrspnsId}" />">
<input id="qestnrTmplatIdTemp" name="qestnrTmplatIdTemp" type="hidden" value="<c:out value="${resultList[0].qestnrTmplatId}" />">
<input name="cmd" type="hidden" value="">

<!--  상세 폼 영역 Start  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
  <tr>
    <th width="20%" height="23">설문관리정보</th>
    <td width="80%">
	<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
	<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
	<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >설문문항정보</th>
    <td width="80%">
    <c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
	<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >설문유형</th>
    <td width="80%">
    <c:if test="${resultList[0].qestnTyCode == '1'}">객관식</c:if>
    <c:if test="${resultList[0].qestnTyCode == '2'}">주관식</c:if>
      <!-- <input name="title" type="text" size="73" value="" maxlength="70" style="width:100%;">   -->
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >설문항목정보</th>
    <td width="80%">
   	 	<c:out value="${fn:replace(resultList[0].iemCn , crlf , '<br/>')}" escapeXml="false" />
		<input name="qustnrIemId" type="hidden" value="${resultList[0].qustnrIemId}">
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >응답자답변내용<br>(주관식)</th>
    <td width="80%">
      <br>
      <c:out value="${fn:replace(resultList[0].respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >기타답변내용</th>
    <td width="80%">
      <br>
      <c:out value="${fn:replace(resultList[0].etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"   >응답자명</th>
    <td width="80%">
		 <c:out value="${fn:replace(resultList[0].respondNm , crlf , '<br/>')}" escapeXml="false" />
    </td>
  </tr>
</table>
</form>
<!--  상세 폼 영역 End  -->

<!-- 버튼 Start  -->
<div class="page-btn">
  <a href="javascript:fn_egov_modify()">수정</a>
  <a href="javascript:fn_egov_delete()">삭제</a>
  <a href="javascript:fn_egov_list()">목록</a>
</div>
<!-- 버튼 End  -->




