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
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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

/* 목록 으로 가기 */
function fn_egov_list(){
	var vFrom = document.frm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/la/uss/olp/qqm/listEgovQustnrQestnManage.do' />";
	vFrom.submit();
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
	if(confirm("삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qqm/getEgovQustnrQestnManage.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문문항 통계</div>
<!--  상단타이틀 End -->

<form id="frm" name="frm" method="post">
<input name="qestnrQesitmId" type="hidden" value="${resultList[0].qestnrQesitmId}">
<input name="cmd" type="hidden" value="<c:out value=''/>"/>
<%-- 설문지정보 상태유지 --%>
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
<input name="searchMode" id="searchMode" type="hidden" value="${qustnrQestnManageVO.searchMode}">

<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
  <tr>
    <th width="20%" height="23"  nowrap>설문지정보(제목)<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      ${resultList[0].qestnrSj}
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  nowrap>질문순번<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
      ${resultList[0].qestnSn}
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  nowrap >질문유형<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
	    <c:if test="${resultList[0].qestnTyCode == '1'}">객관식</c:if>
	    <c:if test="${resultList[0].qestnTyCode == '2'}">주관식</c:if>
    </td>
  </tr>
  <tr>
    <th height="23"  >질문 내용<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td>
      <br>
      <c:out value="${fn:replace(resultList[0].qestnCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  nowrap >최대선택건수<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    ${resultList[0].mxmmChoiseCo}
    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  nowrap >설문항목 결과<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    <br>
    <table border="0" cellpadding="0" cellspacing="0">
    <c:forEach items="${statisticsList}" var="statisticsList" varStatus="status">
	    <tr>
	    <td height="25px">
	    	${statisticsList.iemCn}
	    </td>
	    <td>
	    <img src="/images/egovframework/com/uss/olp/qqm/chart/chart${status.count}.JPG" width="${statisticsList.qustnrPercent}px" height="8" alt="차트이미지"> ${statisticsList.qustnrPercent}%
	    </td>
	    </tr>
    </c:forEach>
    </table>
    <br>
    </td>
  </tr>

  <tr>
    <th width="20%" height="23"  nowrap >응답자답변내용 결과<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>

    <table border="0" cellpadding="0" cellspacing="0">
    <c:forEach items="${statisticsList2}" var="statisticsList2" varStatus="status">
     <c:if test="${statisticsList2.respondAnswerCn ne ''}">
	    <tr>
		    <td>
		    	 <c:out value="${fn:replace(statisticsList2.respondAnswerCn , crlf , '<br/>')}" escapeXml="false" />
		    </td>
	    </tr>
	  </c:if>
    </c:forEach>
    </table>

    </td>
  </tr>
  <tr>
    <th width="20%" height="23"  nowrap >기타답변내용 결과<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td width="80%" nowrap>
    <table border="0" cellpadding="0" cellspacing="0">
    <c:forEach items="${statisticsList2}" var="statisticsList2" varStatus="status">
     <c:if test="!${statisticsList2.etcAnswerCn ne ''}">
	    <tr>
		    <td>
		    	 <c:out value="${fn:replace(statisticsList2.etcAnswerCn , crlf , '<br/>')}" escapeXml="false" />
		    </td>
	    </tr>
	  </c:if>
    </c:forEach>
    <tr><td></td></tr>
    </table>
    </td>
  </tr>
</table>
</form>

