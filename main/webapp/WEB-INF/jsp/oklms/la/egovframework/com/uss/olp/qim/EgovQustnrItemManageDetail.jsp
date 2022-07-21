<%--
Class Name : EgovQustnrItemManageDetail.jsp
Description : 설문항목 상세보기
Modification Information
 
수정일 수정자 수정내용
------------------------------------------
 2008.03.09장동한최초 생성
 2016.12.20이진근모듈 수정
 
author : 공통서비스 개발팀 장동한
since: 2009.03.09
 
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
	location.href = "<c:url value='/la/uss/olp/qim/listEgovQustnrItemManage.do' />";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify(){
	var vFrom = document.frm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qim/goUpdateEgovQustnrItemManage.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete(){
	var vFrom = document.frm;
	if(confirm("삭제시 설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.qestnrTmplatId.value = document.getElementById("qestnrTmplatIdTemp").value;
		vFrom.action = "<c:url value='/la/uss/olp/qim/getEgovQustnrItemManage.do' />";
		vFrom.submit();
	}
}
</script>

<div class="title-name-1">설문항목 상세보기</div>
<form name="frm" id="frm" method="post">
<input name="qustnrIemId" type="hidden" value="<c:out value="${resultList[0].qustnrIemId}" />">
<input id="qestnrTmplatIdTemp" name="qestnrTmplatIdTemp" type="hidden" value="<c:out value="${resultList[0].qestnrTmplatId}" />">
<input name="cmd" type="hidden" value="">

<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<tr> 
<th width="20%" height="23">설문정보</th>
<td width="80%">
<c:out value="${fn:replace(resultList[0].qestnrCn , crlf , '<br/>')}" escapeXml="false" />
<input name="qestnrId" type="hidden" value="<c:out value="${resultList[0].qestnrId}" />">
<input name="qestnrTmplatId" type="hidden" value="<c:out value="${resultList[0].qestnrTmplatId}" />">
</td>
</tr>
<tr> 
<th width="20%" height="23">설문문항정보</th>
<td width="80%">
<c:out value="${fn:replace(resultList[0].qestnrQesitmCn , crlf , '<br/>')}" escapeXml="false" />
<input name="qestnrQesitmId" type="hidden" value="<c:out value="${resultList[0].qestnrQesitmId}" />">
</td>
</tr>
<tr> 
<th height="23">질문 순번</th>
<td>
 <c:out value="${resultList[0].iemSn}" />
</td>
</tr> 
<tr> 
<th height="23">질문 내용</th>
<td>
 <br>
<c:out value="${fn:replace(resultList[0].iemCn , crlf , '<br/>')}" escapeXml="false" />
<br><br>
</td>
</tr> 
<tr> 
<th width="20%" height="23">기타답변여부</th>
<td width="80%">
 <c:out value=" ${resultList[0].etcAnswerAt}" />
</td>
</tr>
</table>
</form>
<!--상세 폼 영역 End-->

<!-- 버튼 Start-->
<div class="page-btn">
<a href="javascript:fn_egov_modify()">수정</a>
<a href="javascript:fn_egov_delete()">삭제</a>
<a href="javascript:fn_egov_list()">목록</a>
</div>
<!-- 버튼 End-->
