<%--
  Class Name : EgovQustnrManageList.jsp
  Description : 설문관리 목록 페이지
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style>
<!--
table.list-1 td {
/*  @import url(/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.css); */
 text-align: left;
 } 
 
 table.list-1 td.lt_text1	{text-align:center;}
 
}
-->
</style>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 초기화
 ******************************************************** */
$(document).ready(function() {
	
}); 
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function press(event) {
	if (event.keyCode==13) {
		fn_search('1');
	}
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist(){
	location.href = "<c:url value='/la/uss/olp/qmc/goInsertEgovQustnrManage.do' />";
}
/* ********************************************************
 * 수정 처리 함수
 ******************************************************** */
function fn_egov_modify(){
	location.href = "<c:url value='/la/uss/olp/qmc/goUpdateEgovQustnrManage.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail(qestnrId){
	var vFrom = document.listForm;
	
	vFrom.qestnrId.value = qestnrId;
	vFrom.action = "<c:url value='/la/uss/olp/qmc/getEgovQustnrManage.do' />";
	vFrom.submit();
}
/* ********************************************************
 * 삭제 처리 함수
 ******************************************************** */
function fn_egov_delete(qestnrId){
	var vFrom = document.listForm;
	if(confirm("삭제 하시겠습니까?")){
		vFrom.qestnrId.value = qestnrId;
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/la/uss/olp/qmc/listEgovQustnrManage.do' />";
		vFrom.submit();
	}
}
/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_search(pageNo){
	var vFrom = document.listForm;

	vFrom.pageIndex.value = pageNo;
	vFrom.action = "<c:url value='/la/uss/olp/qmc/listEgovQustnrManage.do' />";
	vFrom.submit();

}

/* ********************************************************
 * 선택한 설문지정보 -> 설문문항 바로가기
 ******************************************************** */
function fn_egov_list(qestnrId, qestnrTmplatId, Type){
	var vFrom = document.listForm;
	var sAction = "";
	vFrom.pageIndex.value = '1';
	vFrom.qestnrId.value = qestnrId;
	vFrom.qestnrTmplatId.value = qestnrTmplatId;
	vFrom.searchCondition.options[0].selected = true;
	vFrom.searchKeyword.value = '';
	vFrom.searchMode.value = 'Y';

	//QRM QQM QRI
	if(Type == 'QRM'){ //설문응답자정보 목록화면 이동
		sAction  = "<c:url value='/la/uss/olp/qrm/listEgovQustnrRespondManage.do' />";
	}else if(Type == 'QQM'){ //설문문항 목록화면 이동
		sAction  = "<c:url value='/la/uss/olp/qqm/listEgovQustnrQestnManage.do' />";
	}else if(Type == 'QRI'){ //응답자결과 목록화면 이동
		sAction  = "<c:url value='/la/uss/olp/qri/listEgovQustnrRespondInfo.do' />";
	}

	vFrom.action = sAction;
	vFrom.submit();

}

</script>

<form id="listForm" name="listForm" method="post">
<input name="qestnrId" type="hidden" value="">
<input name="qestnrTmplatId" type="hidden" value="">
<input name="searchMode" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

<!-- E : search-list-1 (검색조건 영역) -->
<ul class="search-list-1">
	<li><span>검색 조건 선택</span> 
	<select name="searchCondition" id="searchCondition" class="select" title="검색조건선택" style="width: 150px;">
		<option value="">--선택--</option>
		<option value='QUSTNR_SJ' <c:if test="${searchCondition == 'QUSTNR_SJ'}">selected</c:if>>설문제목</option>
		<option value='FRST_REGISTER_ID' <c:if test="${searchCondition == 'FRST_REGISTER_ID'}">selected</c:if>>등록자</option>
	</select> 
	</li>
	<li>
	<span>검색 단어</span>
		<input name="searchKeyword" name="searchKeyword" type="text" size="35" value='<c:out value="${searchKeyword}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
	</li>
</ul>

<div class="search-btn-area">
	<a href="#@" onclick="fn_search('1'); return false;">조회</a>
</div>
<!-- E : search-list-1 (검색조건 영역) -->

<!-- S : board-info (목록 전체건수영역 ) -->
<ul class="board-info">
	<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }" /> 건</li>
</ul>
<!-- E : board-info (목록 전체건수영역 ) -->

<!-- S : list (목록 영역) -->
<table border="0" cellpadding="0" cellspacing="0" class="list-1">
<caption>목록 을 제공한다</caption>
<thead>
<tr>
	<th width="35px" nowrap>순번</th>
	<th nowrap>설문제목</th>
	<th width="150px" nowrap>설문기간</th>
	<th width="60px" nowrap>설문응답<br>자정보</th>
	<th width="60px" nowrap>설문문항</th>
	<th width="60px" nowrap>설문조사</th>
	<th width="70px" nowrap>등록자</th>
	<th width="70px" nowrap>등록일자</th>
</tr>
</thead>
<tbody>
<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
<c:if test="${fn:length(resultList) == 0}">
<tr>
<td class="lt_text1" colspan="9">
	<spring:message code="common.nodata.msg" />
</td>
</tr>
</c:if>
 <%-- 데이터를 화면에 출력해준다 --%>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
<tr>
	<td class="lt_text1" nowrap>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
	<td class="lt_text3L" nowrap>
    	<a href="#"  onclick="fn_egov_detail('${resultInfo.qestnrId}');"><span color="blue">${resultInfo.qestnrSj}</span></a>
	</td>
	<td class="lt_text1" nowrap>${resultInfo.qestnrBeginDe}~${resultInfo.qestnrEndDe}</td>
	<td class="lt_text1" nowrap>
		<%-- <span class="link"><input type="submit" style="width:40px;border:solid 0px black;text-align:left;" value="보기" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRM'); return false;"></span> --%>
		<a href="#"  class="btn-1" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRM');">보기</a>
	</td>
	<td class="lt_text1" nowrap>
		<%-- <span class="link"><input type="submit" style="width:40px;border:solid 0px black;text-align:left;" value="보기" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QQM'); return false;"></span> --%>
		<a href="#"  class="btn-1" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QQM');">보기</a>
	</td>
	<td class="lt_text1" nowrap>
		<%-- <span class="link"><input type="submit" style="width:40px;border:solid 0px black;text-align:left;" value="보기" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRI'); return false;"></span> --%>
		<a href="#"  class="btn-1" onclick="fn_egov_list('${resultInfo.qestnrId}','${resultInfo.qestnrTmplatId}','QRI');">보기</a>
	</td>
	<td class="lt_text1" nowrap>${resultInfo.frstRegisterNm}</td>
	<td class="lt_text1" nowrap>${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
</tr>
</c:forEach>
</tbody>
</table>
</form>

<!-- S : page-num -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div>
<!-- E : page-num -->

<!-- S : page-btn -->
<div class="page-btn">
	<a href="#" onclick="fn_egov_regist();"><spring:message code="button.create" /></a>
</div>
<!-- E : page-btn -->