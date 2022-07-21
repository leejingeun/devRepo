<%--
  Class Name : /la/egovframework/com/uss/olp/qrm/EgovQustnrRespondManageDetail.jsp
  Description : 응답자정보 상세보기
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
	location.href = "<c:url value='/la/uss/olp/qrm/listEgovQustnrRespondManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify(){
	var vFrom = document.QustnrRespondManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qrm/goUpdateEgovQustnrRespondManage.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete(){
	var vFrom = document.QustnrRespondManageForm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qrm/getEgovQustnrRespondManage.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>

<div class="title-name-1">응답자정보 상세보기</div>
<form name="QustnrRespondManageForm" id="QustnrRespondManageForm" method="post">
<input name="qestnrRespondId" type="hidden" value="<c:out value="${resultList[0].qestnrRespondId}" />">
<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
<input name="cmd" type="hidden" value="">

<!--  상세 폼 영역 Start  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<caption>상세조회 목록을 제공한다</caption>
<tbody>
  <tr>
    <th width="20%" height="23">설문관리정보</th>
    <td width="80%">
		<c:out value="${fn:replace(resultList[0].qestnrSj , crlf , '<br/>')}" escapeXml="false" />
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"  >성별</th>
    <td width="80%">
	<c:forEach items="${comCode014}" var="comCodeList" varStatus="status">
		<c:if test="${comCodeList.code eq resultList[0].sexdstnCode}">
			<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
		</c:if>
	</c:forEach>
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23"  >직업</th>
    <td width="80%">
	<c:forEach items="${comCode034}" var="comCodeList" varStatus="status">
		<c:if test="${comCodeList.code eq resultList[0].occpTyCode}">
			<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
		</c:if>
	</c:forEach>
    </td>
  </tr>
  <tr>
    <th height="23">생년월일</th>
    <td>
     ${fn:substring(resultList[0].brthdy, 0, 4)}-
     ${fn:substring(resultList[0].brthdy, 4, 6)}-
     ${fn:substring(resultList[0].brthdy, 6, 8)}
    </td>
  </tr>

  <tr>
    <th  width="20%" height="23">응답자명</th>
    <td width="80%">
		<c:out value="${fn:replace(resultList[0].respondNm , crlf , '<br/>')}" escapeXml="false" />
    </td>
  </tr>

  <tr>
    <th width="20%" height="23">전화번호</th>
    <td width="80%">
		${resultList[0].areaNo}-${resultList[0].middleTelno}-${resultList[0].endTelno}
    </td>
  </tr>
  </tbody>
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


