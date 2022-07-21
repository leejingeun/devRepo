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
			
		var reqUrl = CONTEXT_ROOT + targetUrl + "listSmsHistory.do";
		$("#frmMail").attr("action", reqUrl);
		$("#frmMail").submit();
	}

	/* 상세조회 페이지로 이동 */
	function fn_read( trNum ){
		
		$("#trNum").val( trNum );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "getSmsHistory.do";
		$("#frmMail").attr("action", reqUrl);
		$("#frmMail").submit();
	}
	

</script>


<form id="frmMail" name="frmMail" action="<c:url value='/la/mail/mail/listSmsHistory.do'/>" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
<input type="hidden" id="trNum"  name="trNum" />
<ul class="search-list-1">
	<%-- <li>
		<span>발송기간</span>
		<select name=searchScLogTable id="searchScLogTable" style="width:120px">
	  			<c:forEach var="smsTableList" items="${smsTableList}" varStatus="status">
					<option value="${smsTableList.codeId}" ${smsTableList.codeId == mailVO.searchScLogTable ? 'selected="selected"' : '' }>${smsTableList.codeName}</option>
				</c:forEach>
		</select>
	</li> --%>
	<li>
		<span>수신번호</span>
		<input type="text" name="searchTrPhone" id="searchTrPhone" value="${mailVO.searchTrPhone}" style="width:415px;" onkeypress="press(event);"/>
	</li>
	<li>
		<span>발신번호</span>
		<input type="text" name="searchCallback" id="searchCallback" value="${mailVO.searchCallback}" style="width:415px;" onkeypress="press(event);"/>
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
			<th>번호</th>
	        <th>발신번호</th>
	        <th>수신번호</th>
	        <th>발신일</th>
	        <th>성공여부</th>
		</tr>
	</thead>
	<tbody>
	<c:choose>
        <c:when test="${!empty listSmsHistory}">
          <c:forEach var="listSmsHistory" items="${listSmsHistory}" varStatus="status">
            <tr>
              <td>${totalCount-((pageIndex-1) * pageSize + status.count)+1}</td>
              <td><a href="#fn_read" onclick="javascript:fn_read('${listSmsHistory.trNum }');">${listSmsHistory.trCallback }</a></td>
              <td>${listSmsHistory.trPhone }</td>
              <td>${listSmsHistory.trSenddate }</td>
              <td><c:choose>
                  <c:when test="${listSmsHistory.trSendstat eq '0' }"> 발송대기 </c:when>
                  <c:when test="${listSmsHistory.trSendstat eq '1' }"> 전송완료 </c:when>
                  <c:otherwise> 결과수신완료 </c:otherwise>
                </c:choose>
              </td>
            </tr>
          </c:forEach>
        </c:when>
        <c:otherwise>
          <tr>
            <td colspan="7"><spring:message code="common.nodata.msg" /></td>
          </tr>
        </c:otherwise>
    </c:choose>
</tbody>
</table><!-- E : list -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div><!-- E : page-num -->
</form>