<%--
  Class Name : EgovQustnrManageDetail.jsp
  Description : 설문관리 상세보기
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
	location.href = "<c:url value='/la/uss/olp/qmc/listEgovQustnrManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify(){
	var vFrom = document.frm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qmc/goUpdateEgovQustnrManage.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete(){
	var vFrom = document.frm;

	if(confirm("삭제시 설문관리, 설문항목, 설문문항, 설문응답자관리, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qmc/getEgovQustnrManage.do' />";
		vFrom.submit();
	}
}
</script>

<!-- 상단타이틀 -->
<div class="title-name-1">설문관리 상세보기</div>
<form id="frm" name="frm" method="post">
<input name="qestnrId" type="hidden" value="${resultList[0].qestnrId}">
<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
<input name="cmd" type="hidden" value="<c:out value=''/>">

<!--  상세 폼 영역 Start  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<caption>상세조회 목록을 제공한다</caption>
  <tr>
    <th width="20%" height="23">설문제목</th>
    <td width="80%">
       ${resultList[0].qestnrSj}
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">설문목적</th>
    <td width="80%">
      <br>
      <c:out value="${fn:replace(resultList[0].qestnrPurps , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th height="23">설문작성안내 내용</th>
    <td>
      <br>
      <c:out value="${fn:replace(resultList[0].qestnrWritngGuidanceCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>

  <tr>
    <th width="20%" height="23">설문대상</th>
    <td width="80%" >

<%-- <c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
<c:if test="${comCodeList.code eq resultList[0].qestnrTrget}">
<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
</c:if>
</c:forEach> --%>

<c:out value="${resultList[0].qestnrTrgetNm}" />

    </td>
  </tr>

  <tr>
    <th width="20%" height="23">설문기간</th>
    <td width="80%" >
       ${resultList[0].qestnrBeginDe} ~ ${resultList[0].qestnrEndDe}
    </td>
  </tr>

    <tr>
    <th width="20%" height="23">템플릿 유형</th>
    <td width="80%">
      <c:out value="${resultList[0].qestnrTmplatTy}" />
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

