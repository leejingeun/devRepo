<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 14 오후 1:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<c:set var="targetUrl" value="/la/mail/mail/"/>

<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보

	$(document).ready(function() {

		if ('' == pageSize) {
			pageSize = 10;
		}
		if ('' == totalCount) {
			totalCount = 0;
		}
		if ('' == pageIndex) {
			pageIndex = 1;
		}

		loadPage();
	});

	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}


	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

//         com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( pageNo ){
		$("#pageIndex").val( pageNo );
			
		var reqUrl = CONTEXT_ROOT + targetUrl + "listMailHistory.do";
		$("#frmMail").attr("action", reqUrl);
		$("#frmMail").submit();
	}

	/* 상세조회 페이지로 이동 */
	function fn_read( historyId ){
		
		$("#historyId").val( historyId );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "getMailHistory.do";
		$("#frmMail").attr("action", reqUrl);
		$("#frmMail").submit();
	}
	

</script>


<form id="frmMail" name="frmMail" action="<c:url value='/la/mail/mail/listMailHistory.do'/>" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
<input type="hidden" id="historyId"  name="historyId" />
<ul class="search-list-1">
	<li>
		<span>메일유형</span>
		<select name="searchMailClass" id="searchMailClass" style="width:120px">
	  			<option selected value=''>전체</option>
	  			<c:forEach var="mailClassCode" items="${mailClassCodeList}" varStatus="status">
					<option value="${mailClassCode.codeId}" ${mailClassCode.codeId == mailVO.searchMailClass ? 'selected="selected"' : '' }>${mailClassCode.codeName}</option>
				</c:forEach>
		</select>
	</li>
	<li>
		<span>제목</span>
		<input type="text" name="searchMailTitle" id="searchMailTitle" value="${mailVO.searchMailTitle}" style="width:415px;" onkeypress="press(event);"/>
	</li>
	<li>
		<span>발신자</span>
		<input type="text" name="searchSenderMemName" id="searchSenderMemName" value="${mailVO.searchSenderMemName}" style="width:415px;" onkeypress="press(event);"/>
	</li>
	<li>
		<span>수신자</span>
		<input type="text" name="searchReceiverMemName" id="searchReceiverMemName" value="${mailVO.searchReceiverMemName}" style="width:415px;" onkeypress="press(event);"/>
	</li>
</ul><!-- E : search-list-1 -->
<div class="search-btn-area">
	<a href="#fn_search" onclick="javascript:fn_search(1); return false" onkeypress="this.onclick();">조회</a>
</div>

<ul class="board-info">
	<span>검색결과 : 총 </span><span id="totalRow">0</span><span> 건</span>
</ul><!-- E : board-info -->

<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="15%" >메일유형</th>
			<th>제목</th>
			<th width="10%" >발신자</th>
			<th width="10%" >수신자</th>
			<th width="10%" >발신일</th>
			<th width="5%" >성공여부</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
        <c:when test="${!empty listMailHistory}">
          <c:forEach var="listMailHistory" items="${listMailHistory}">
            <tr>
              <td>${listMailHistory.mailClassName}</td>
              <td class="title"><a href="#fn_read" onclick="javascript:fn_read('${listMailHistory.historyId}');">${listMailHistory.mailTitle}</a></td>
              <td>${listMailHistory.senderMemName }</td>
              <td>${listMailHistory.receiverMemName} [${listMailHistory.receiverMemId}]</td>
              <td>${listMailHistory.insertDate}</td>
              <td>${listMailHistory.sendSuccessYn == 'Y' ? '성공' : '실패'}</td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="6"><spring:message code="common.nodata.msg" /></td>
          </tr>
        </c:otherwise>
    </c:choose>
</tbody>
</table><!-- E : list -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div><!-- E : page-num -->
</form>