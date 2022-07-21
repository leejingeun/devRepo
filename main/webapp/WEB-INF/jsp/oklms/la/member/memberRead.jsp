<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 01 오후 1:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<c:set var="targetUrl" value="/la/member/member/"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
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
	}

	/*====================================================================
	사용자 정의 함수 
	====================================================================*/

	/* 수정 페이지로 이동 */
	function fn_updt(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateMember.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	/* 목록 페이지로 이동 */
	function fn_list(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listMember.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();	
	}
	
	/* 회원삭제 */
	function fn_del(){
		if( confirm("회원을 삭제하겠습니까?") ) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "deletemember.do";

			$("#frmMember").attr("method", "post" );
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").submit();	
		}
	}
	
</script>

<form:form commandName="frmMember">
<input type="hidden" name="memSeq" id="memSeq" value="${memberVO.memSeq}"/>

<!-- 검색조건유지 필드 시작 -->
<input type="hidden" name="searchAuthGroupId" id="searchAuthGroupId" value="${memberVO.searchAuthGroupId}"/>
<input type="hidden" name="searchName" id="searchName" value="${memberVO.searchName}"/>
<input type="hidden" name="searchScsnYn" id="searchScsnYn" value="${memberVO.searchScsnYn}"/>
<!--  <input type="hidden" name="searchWord" id="searchWord" value="${memberVO.searchWord}"/> -->
</form:form>
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
		<tr>
		    <th>아아디</th>
		    <td>${memberVO.memId}</td>
		    <th>성명</th>
		    <td>${memberVO.memName}</td>
		</tr>
	  <tr>
	    <th>권한그룹</th>
	  	<td>${memberVO.authGroupName}</td>
	    <th>성별</th>
	    <td>${memberVO.sex eq 'M' ? '남자' : memberVO.sex eq 'F'? '여자': ''}</td>
	  </tr>
	  <tr>
	    <th>주민등록번호</th>
	    <td>${memberVO.memBirth}${memberVO.memBirth ne '' ? '-*******' : ''}</td>
	    <th>비밀번호</th>
	    <td>******</td>
	  </tr>
	  <tr>
	    <th>전화번호</th>
	    <td>${memberVO.telNo1}-${memberVO.telNo2}-${memberVO.telNo3}</td>
	    <th>핸드폰번호</th>
	    <td>${memberVO.hpNo1}-${memberVO.hpNo2}-${memberVO.hpNo3}</td>
	  </tr>
	  <tr>
	    <th>주소</th>
	    <td>[${memberVO.zipCode}] ${memberVO.address} ${memberVO.addressDtl}</td>
	    <th>이메일주소</th>
	    <td>${memberVO.email}</td>
	  </tr>
	  <tr>
	    <th>권한그룹</th>
	    <td>${memberVO.authGroupName}</td>
	    <th>실명인증 여부</th>
	    <td>${memberVO.namecheckYn eq 'Y' ? '이메일 인증' : memberVO.gPinYn eq 'Y'? '아이핀 인증' : '미인증'}</td>
	  </tr>
	  <tr>
	    <th>소속</th>
	    <td colspan="3">${memberVO.compNm}</td>
	  </tr>
	  <tr>
	    <th>이메일 수신</th>
	    <td>${memberVO.receiveMailYn eq 'Y' ? '수신' : '미수신'}</td>
	    <th>SMS 수신</th>
	    <td>${memberVO.receiveSmsYn eq 'Y' ? '수신' : '미수신'}</td>
	  </tr>
	</tbody>
</table><!-- E : view-1 -->


<div class="page-btn">
	<a href="#fn_updt" onclick="javascript:fn_updt();" onkeypress="this.onclick;">수정</a>
	<a href="#fn_list" onclick="javascript:fn_list();" onkeypress="this.onclick;">목록</a>
</div><!-- E : page-btn -->	
		
