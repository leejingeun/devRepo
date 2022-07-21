<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 sitglobal LEARN ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of sitglobal LEARN.
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근C    2016. 10. 27 오후 1:20         Modify Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<script type="text/javascript">

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
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	function press(event) {
		if (event.keyCode==13) {
			fn_idUseAtCheck();
		}
	}
	
	/* 적용버튼 클릭시 이벤트 */
	function fn_goIdUse() {		
		$("#memId", opener.document).val("${memberId}");
		$("#memId", opener.document).attr("readonly", true);
		$("#status", opener.document).val(true);	
		self.close();
	}
	
	/* 아이디 중복체크 */
	/* function fn_idUseAtCheck(){
		
		var reqUrl = CONTEXT_ROOT + "/la/popup/popup/popupMemberInfoSmsSend.do";
		
		$("#frmSmsPop").attr("action", reqUrl);
		$("#frmSmsPop").submit();
	} */

</script>

<form id="frmPop" name="frmPop" method="post">
<input type="hidden" name="memSeq" id ="memSeq" value ="${memSeq}"/>

<div id="popup-wrarpr">
<div id="popup-header">
	<h1><img src="/images/oklms/adm/inc/pop_border_02.png" alt="" />아이디 중복 체크</h1>
	<p><a href="#" onclick="parent.close();">닫기</a></p>
</div><!-- E : p-header -->

<div id="popup-content-area">
	<div id="popup-container">

<!-- 팝업 내용 영역 시작 : 가로 650px , 세로 560px -->

<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
		<tr>
		    <td align="center">
		    	<c:choose>
		        <c:when test="${memIdCount == 0 }">
		          <p>아이디 ${memberId} 는 사용하실수 있습니다. <a href="#fn_goIdUse" class="btn" onclick="javascript:fn_goIdUse(); return false" onkeypress="this.onclick;">적용</a>
		          <p/>
		        </c:when>
		        <c:otherwise>
		          <p>아이디 ${memberId} 는 사용중입니다.</p>
		        </c:otherwise>
		      </c:choose>
   			</td>
		</tr>
	</tbody>
</table><!-- E : view-1 -->
<!-- <table border="0" cellpadding="1" cellspacing="1" class="list-1" >
	<tr>
		<td colspan="2">다른 아이디를 사용 하시려면,<br/>계속해서 아이디 중복확인을 하셔야 합니다.</td>
	</tr>
	<tr>
		<th>아이디</th>
		<td>
			<input name="memId" id="memId" minlength="4" maxlength="10" enter="fn_idUseAtCheck();"/>
		</td>
	</tr>
</table> --><!-- E : list -->

<!-- 팝업 내용  영역 끝 -->

	</div><!-- E : p-contentiner -->
</div><!-- E : p-content-area -->
	<div id="popup-footer"></div><!-- E : p-footer -->
</div><!-- E : p-wrapper -->
</form>		
		
		