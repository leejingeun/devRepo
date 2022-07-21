<%--
  Class Name : EgovQustnrQestnManageDetail.jsp
  Description : 설문문항 상세보기
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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
$(document).ready(function() {
	
});

/* 목록 으로 가기 */
function fn_egov_list(){
	var varFrom = document.getElementById("frm");
	varFrom.action =  "<c:url value='/la/uss/olp/qqm/listEgovQustnrQestnManage.do' />";
	varFrom.submit();
}

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify(){
	var vFrom = document.frm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qqm/goUpdateEgovQustnrQestnManage.do' />";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete(){
	var vFrom = document.frm;
	if(confirm("삭제시  설문문항, 설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qqm/getEgovQustnrQestnManage.do' />";
		vFrom.submit();
	}
}
</script>


<!-- 상단타이틀 -->
<div class="title-name-1">설문문항 상세보기</div>
<form name="frm" id="frm" method="post">

<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
<input name="cmd" type="hidden" value="">
<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">

<!--  상세 폼 영역 Start  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<caption>상세조회 목록을 제공한다</caption>
  <tr>
    <th width="20%" height="23" >설문지정보(제목)</th>
    <td width="80%">
      ${resultList[0].qestnrSj}
    </td>
  </tr>
  <tr>
    <th  width="20%" height="23" >질문순번</th>
    <td width="80%">
      ${resultList[0].qestnSn}
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  >질문유형</th>
    <td width="80%">

<c:forEach items="${cmmCode018}" var="comCodeList" varStatus="status">
<c:if test="${comCodeList.code eq resultList[0].qestnTyCode}">
<c:out value="${fn:replace(comCodeList.codeNm , crlf , '<br/>')}" escapeXml="false" />
</c:if>
</c:forEach>

    </td>
  </tr>
  <tr>
    <th height="23"  >질문 내용</th>
    <td>
      <br>
      <c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  >최대선택건수</th>
    <td width="80%">
    ${resultList[0].mxmmChoiseCo}
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
