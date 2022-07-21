<%--
  Class Name : /la/egovframework/com/uss/olp/qtm/EgovQustnrTmplatManageDetail.jsp
  Description : 설문템플릿 상세보기
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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javaScript" language="javascript">

/*====================================================================
화면 초기화 
====================================================================*/
$(document).ready(function() {
	
});

/*====================================================================
사용자 정의 함수  
====================================================================*/

/* 목록 으로 가기 */
function fn_egov_list(){
	location.href = "<c:url value='/la/uss/olp/qtm/listEgovQustnrTmplatManage.do' />";
}

/* 수정 화면 이동 */ 
function fn_egov_modify(){
	var vFrom = document.qustnrTmplatManage;
	vFrom.action = "<c:url value='/la/uss/olp/qtm/goUpdateEgovQustnrTmplatManage.do' />";
	vFrom.submit();
}

/* 삭제처리 */ 
function fn_egov_delete(){
	var vFrom = document.qustnrTmplatManage;
	if(confirm("삭제시 설문템플릿, 설문항목, 설문문항, 설문응답자관리, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qtm/getEgovQustnrTmplatManage.do' />";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>

<!--  상단타이틀 Start -->
<div class="title-name-1">설문템플릿 상세보기</div>
<!--  상단타이틀 End -->

<form id="qustnrTmplatManage"  name="qustnrTmplatManage" method="post">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${resultList[0].qestnrTmplatId}">
<input name="cmd" id="cmd" type="hidden" value="">

<!--  상세 폼 영역 Start  -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
<caption>상세조회 목록을 제공한다</caption>
<tbody>
  <tr>
    <th height="23">템플릿유형</th>
    <td>${resultList[0].qestnrTmplatTy}</td>
  </tr>
  <%-- <tr>
    <th width="20%" height="23">템플릿유형&nbsp;&nbsp;&nbsp;<br>이미지정보</th>
    <td width="80%" >
     <c:if test="${resultList[0].qestnrTmplatImagepathnm ne null}">
      	<c:if test="${resultList[0].qestnrTmplatImagepathnm ne ''}">
    	<img src="<c:url value='/la/uss/olp/qtm/EgovQustnrTmplatManageImg.do' />?qestnrTmplatId=${resultList[0].qestnrTmplatId}" alt="${resultList[0].qestnrTmplatTy}템플릿이미지" title="${resultList[0].qestnrTmplatTy}템플릿이미지">
    	</c:if>
	</c:if>
    </td>
  </tr> --%>
  <tr>
    <th width="20%" height="23">템플릿설명</th>
    <td width="80%" >
      <br>
      <c:out value="${fn:replace(resultList[0].qestnrTmplatCn , crlf , '<br/>')}" escapeXml="false" />
	  <br><br>
    </td>
  </tr>
  <tr>
    <th width="20%" height="23">템플릿파일(경로)</th>
    <td width="80%" >${resultList[0].qestnrTmplatCours}</td>
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

