<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

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
	
	$(document).ready(function() {

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

// 		doAction('formReset');
	}
	

	/*====================================================================
		조회버튼이나 페이지 클릭시 실행되는 함수는 꼭 doAction(sAction)으로 만들어 사용해 주시기 바랍니다.
	====================================================================*/

	
	/* 목록 페이지로 이동 */
	function fn_list(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listSmsHistory.do";
		$("#frmMail").attr("method", "post" );
		$("#frmMail").attr("action", reqUrl);
		$("#frmMail").submit();	
	}
	

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
</script>
<div class="title-name-1">기본정보</div>

<form id="frmMail" name="frmMail"  method="post">
	<input type="hidden" 		id="scLogTable"  						name="scLogTable"	 					value="${mailVO.scLogTable }" />
	<input type="hidden" 		id="searchScLogTable"  			name="searchScLogTable"	 					value="${mailVO.searchScLogTable }" />
	<input type="hidden" 		id="searchTrPhone" 					name="searchTrPhone"	 				value="${mailVO.searchTrPhone }" />
	<input type="hidden" 		id="searchCallback"  				name="searchCallback"	 				value="${mailVO.searchCallback }" />
	<input type="hidden" 		id="searchReceiveName"  			name="searchReceiveName"	 		value="${mailVO.searchReceiveName }" />
	<input type="hidden" 		id="searchSendName"  				name="searchSendName"	 			value="${mailVO.searchReceiverMemName }" />
</form>


<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
		<tr>
			<th width="10%">발신번호</th>
			<td colspan="5">${mailVO.trCallback}</td>
		</tr>
		<tr>
			<th>수신번호</th>
			<td colspan="5">${mailVO.trPhone}</td>
		</tr>
		<tr>
			<th>성공여부</th>
			<td colspan="5">
			<c:choose>
		        <c:when test="${mailVO.trSendstat eq '0' }"> 발송대기 </c:when>
		        <c:when test="${mailVO.trSendstat eq '1' }"> 전송완료 </c:when>
		        <c:otherwise> 결과수신완료 </c:otherwise>
		      </c:choose>
			</td>
		</tr>
		<tr>
			<th>메세지</th>
			<td colspan="5">${mailVO.trMsg}</td>
		</tr>
	</tbody>
</table><!-- E : view-1 -->

<div class="page-btn">
	<a href="#fn_list" onclick="javascript:fn_list();" onkeypress="this.onclick;">목록</a>
</div><!-- E : page-btn -->	

