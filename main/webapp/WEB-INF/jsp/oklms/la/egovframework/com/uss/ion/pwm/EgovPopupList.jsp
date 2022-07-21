<%--
  Class Name : EgovPopupList.jsp
  Description : 팝업창관리 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2009.09.16    장동한          최초 생성

    author   : 공통서비스 개발팀 장동한
    since    : 2009.09.16

    Copyright (C) 2009 by MOPAS  All right reserved.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/" />
<c:set var="JsUrl"  value="${pageContext.request.contextPath}/js/egovframework/com/uss/ion/pwm/"/>
<script type="text/javascript" src="${JsUrl}prototype-1.6.0.3.js"></script>
<script type="text/javaScript" language="javascript">
/* ********************************************************
 * 페이징 처리 함수
 ******************************************************* */
function linkPage(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "<c:url value='/la/uss/ion/pwm/listPopup.do'/>";
   	document.listForm.submit();
}
/* ********************************************************
 * 등록 처리 함수
 ******************************************************** */
function fn_egov_regist_PopupManage(){
	location.href = "<c:url value='/la/uss/ion/pwm/registPopup.do' />";
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_PopupManage(popupId){
	var vFrom = document.listForm;
	vFrom.popupId.value = popupId;
	vFrom.action = "<c:url value='/la/uss/ion/pwm/detailPopup.do' />";
	vFrom.submit();
}

/* ********************************************************
 * 검색 함수
 ******************************************************** */
function fn_egov_search_PopupManage(){
	var vFrom = document.listForm;

	vFrom.action = "<c:url value='/la/uss/ion/pwm/listPopup.do' />";
	vFrom.submit();

}
/* ********************************************************
* 체크 박스 선택 함수
******************************************************** */
function fn_egov_checkAll_PopupManage(){

	var FLength = document.getElementsByName("checkList").length;
	var checkAllValue = document.getElementById('checkAll').checked;

	//undefined
	if( FLength == 1){
		document.getElementById("checkList").checked = checkAllValue;
	}{
			for(var i=0; i < FLength; i++)
			{
				document.getElementsByName("checkList")[i].checked = checkAllValue;
			}
		}

}
/* ********************************************************
* 팝업창 미리보기
******************************************************** */
function fn_egov_view_PopupManage(){


	var FLength = document.getElementsByName("checkList").length;

	if( FLength == 1){
		if(document.getElementById("checkList").checked == true){
			fn_egov_ajaxPopupInfo_PopupManage( document.getElementById("checkList").value );
		}
	}{
		for(var i=0; i < FLength; i++)
		{
			if(document.getElementsByName("checkList")[i].checked == true){
				fn_egov_ajaxPopupInfo_PopupManage( document.getElementsByName("checkList")[i].value );
			}
		}
	}
	return;

}
/* ********************************************************
* 팝업창 정보 Ajax통신으로 정보 획득
******************************************************** */
function fn_egov_ajaxPopupInfo_PopupManage(popupIds){
	var url = "<c:url value='/la/uss/ion/pwm/ajaxPopupManageInfo.do' />";

	var param = {
			popupId: popupIds
	};

	new Ajax.Request(url,
   {
     asynchronous:true,
     method:"post",
     parameters: param ,
     evalJSON:     false,
     evalJS:       false,
    onLoading  : function() {/*로딩중*/ },
    onSuccess  : function(returnValue)
    {
    	var returnValueArr = returnValue.responseText.split("||");

    	//if(fnGetCookie(popupIds) == null ){
    	   	fn_egov_popupOpen_PopupManage(popupIds,
        	    	returnValueArr[0],
        	    	returnValueArr[1],
        	    	returnValueArr[2],
        	    	returnValueArr[3],
        	    	returnValueArr[4],
        	    	returnValueArr[5]);
    	//}

    },
    onFailure: function() {/*불러오기 실패*/},
    onComplete : function() {/*모든 것을 완료*/}
   });
}

/* ********************************************************
* 팝업창  오픈
******************************************************** */
function fn_egov_popupOpen_PopupManage(popupId,fileUrl,width,height,top,left,stopVewAt){

	var url = "<c:url value='/commbiz/uss/ion/pwm/openPopupManage.do' />?";
	url = url + "fileUrl=" + fileUrl;
	url = url + "&stopVewAt=" + stopVewAt;
	url = url + "&popupId=" + popupId;
	var name = popupId;
	var openWindows = window.open(url,name,"width="+width+",height="+height+",top="+top+",left="+left+",toolbar=no,status=no,location=no,scrollbars=yes,menubar=no,resizable=yes");

	if (window.focus) {openWindows.focus()}
}

/* ********************************************************
* 팝업창  오픈 쿠키 정보 OPEN
******************************************************** */
function fnGetCookie(name) {
	  var prefix = name + "=";

	  var cookieStartIndex = document.cookie.indexOf(prefix);
	  if (cookieStartIndex == -1) return null;
	  var cookieEndIndex = document.cookie.indexOf(";", cookieStartIndex + prefix.length);
	  if (cookieEndIndex == -1) cookieEndIndex = document.cookie.length;


	  return unescape(document.cookie.substring(cookieStartIndex + prefix.length, cookieEndIndex));
}

</script>
<%-- noscript 테그 --%>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form name="listForm" action="<c:url value='/la/uss/ion/pwm/listPopup.do'/>" method="post">
						<!-- E : search-list-1 (검색조건 영역) -->
						<ul class="search-list-1">
							<li>
								<span>검색 조건</span>
								<select name="searchCondition" class="select" style="width:180px; margin-right:10px;" title="검색조건선택">
									<option value=''>--선택하세요--</option>
									<option value='POPUP_SJ_NM' <c:if test="${searchCondition == 'POPUP_SJ_NM'}">selected</c:if>>팝업창명</option>
									<option value='FILE_URL' <c:if test="${searchCondition == 'FILE_URL'}">selected</c:if>>팝업창URL</option>
								</select><input name="searchKeyword" type="text" size="10" value="" maxlength="35" style="width:230px;" title="검색단어입력">
							</li>
						</ul>
<input name="popupId" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${popupManageVO.pageIndex}'/>"/>
</form>
						<!-- E : search-list-1 (검색조건 영역) -->
						<div class="search-btn-area"><a href="#@" onclick="fn_egov_search_PopupManage(); return false;">조회하기</a></div>


						<!-- S : board-info (게시판 버튼 및 구분selectBox ) -->
						<ul class="board-info">
							<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }"/> 건</li>
							<li class="btn-area">
								<a href="#@" onclick="javascript:fn_egov_view_PopupManage();">미리보기</a>
								<a href="#@" onclick="javascript:fn_egov_search_PopupManage(); return false;">추가</a>
							</li>
						</ul>
						<!-- E : board-info (게시판 버튼 및 구분selectBox ) -->


						<!-- S : list (게시판 목록 영역) -->
						<table border="0" cellpadding="0" cellspacing="0" class="list-1">
							<thead>
								<tr>
									<th width="20px"><input type="checkbox" /></th>
									<th width="40px">순번</th>
									<th width="240px">제목</th>
									<th width="240px">게시 기간</th>
									<th>파일URL</th>
									<th width="70px">게시 상태</th>
								</tr>
							</thead>
							<tbody>
<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="lt_text3" colspan="7"><spring:message code="common.nodata.msg" /></td>
								</tr>
</c:if>
 <%-- 데이터를 화면에 출력해준다 --%>
<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
								<tr>
									<td><input type="checkbox" name="checkList" id="checkList" value="${resultInfo.popupId}"></td>
									<td><c:out value="${(popupManageVO.pageIndex-1) * popupManageVO.pageSize + status.count}"/></td>
									<td><a href="#@" onclick="javascript:fn_egov_detail_PopupManage('${resultInfo.popupId}'); return false;"><c:out value="${resultInfo.popupTitleNm}"/></a></td>
									<td>
										<c:out value="${fn:substring(resultInfo.ntceBgnde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceBgnde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceBgnde, 8, 10)}"/>H <c:out value="${fn:substring(resultInfo.ntceBgnde, 10, 12)}"/>M
										~
										<c:out value="${fn:substring(resultInfo.ntceEndde, 0, 4)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 4, 6)}"/>-<c:out value="${fn:substring(resultInfo.ntceEndde, 6, 8)}"/> <c:out value="${fn:substring(resultInfo.ntceEndde, 8, 10)}"/>H <c:out value="${fn:substring(resultInfo.ntceEndde, 10, 12)}"/>M
									</td>
									<td><c:out value="${resultInfo.fileUrl}"/></td>
									<td><c:out value="${resultInfo.ntceAt}"/></td>
								</tr>
</c:forEach>
							</tbody>
						</table>
						<!-- E : list (게시판 목록 영역) -->


						<!-- S : page-num (페이징 영역) -->
						<div class="page-num">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_search_PopupManage" />
						</div>
						<!-- E : page-num (페이징 영역) -->
