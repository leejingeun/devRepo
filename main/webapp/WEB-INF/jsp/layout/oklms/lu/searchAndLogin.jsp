<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.commons.lang3.time.DateUtils" %>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils" %>
<%@ page import="kr.co.sitglobal.oklms.commbiz.util.BizUtil" %>
<%@ page import="kr.co.sitglobal.oklms.comm.vo.LoginInfo" %>
<%@ page import="egovframework.com.cmm.service.Globals" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- ############### LU searchAndLogin location ########## -->
<script type="text/javascript">
	/* 리스트 조회 */
	function fn_searchLec( pageNo ){
		$("#pageIndex").val( pageNo );
			
		var reqUrl = CONTEXT_ROOT + "/lu/course/course/All/listCourse.do";
		$("#frmLecSearch").attr("action", reqUrl);
		$("#frmLecSearch").submit();
	}
	
</script>
				<li id="search-log-wrap">
					<form id="frmLecSearch" name="frmLecSearch" method="post" action="/lu/course/course/All/listCourse.do">
						<input type="hidden" id="searchField" name="searchField" value="SEARCH_LEC_NAME"/>
						<div class="search-area">
							<label for="searchForm"><span>과정 </span>검색</label>
							<fieldset>
								<input type="text" id="searchValue" name="searchValue"><a href="#!" onclick="fn_searchLec('1');">검색</a>
							</fieldset>
						</div><!-- E : search-area -->
					</form>

	<%
	LoginInfo loginInfo = new LoginInfo();
	if(loginInfo.isLogin()){
		//String sessionLastLoginDt = loginInfo.getAttribute("SESSION_LAST_LOGIN_DT") + "";
		String sessionLastLoginDt = session.getAttribute(Globals.SESSION_LAST_LOGIN_DT) + ""; // 최종 로그인 시간
		//String sessionLastLoginDt = loginInfo.getLoginInfo().getLasLgnYmd() + "";
		
	%>
					<div class="info-area">
						<p class="info-text"><span><%=loginInfo.getUserName() %></span> 님 안녕하세요.</p>
						<p class="info-btn-1"><a href="/commbiz/login/logout.do" class="btn-logout">로그아웃</a><a href="/lu/user/user/goUpdateUser.do" class="btn-modify">정보수정</a></p>
						<p class="info-btn-2"><a href="#!" class="btn-mail">메일보내기</a><a href="#!" class="btn-memo">쪽지 <span>00</span>통</a></p>
					</div><!-- E : info-area -->
	<%}else{ %>
					<div id="login" class="login-area gLogin">
						<h3>회원 로그인</h3>
<!-- 						<form action="login-form-area" id="gLogin" > -->
						<form id="loginProcForm" method="post" action="/commbiz/login/loginProc.do" class="gLogin">
							<input id="reqUri" name="reqUri" type="hidden" value="${reqUri }">
							<input id="menuArea" name="menuArea" type="hidden" value="LMS">
							<fieldset>
								<legend>회원 로그인</legend>
								<ul>
									<li class="item"><label for="uid" class="iLabel">아이디</label><input name="memId" type="text" value="" id="memId" class="iText uid" /></li>
									<li class="item"><label for="upw" class="iLabel">비밀번호</label><input name="memPasswordEncript" type="password" value="" id="memPasswordEncript" class="iText upw" /></li>
								</ul>
								<span><input name="login-btn" type="submit" value="LOGIN" /></span><!--  /lu/login/goLmsUserLogin.do  -->
								<a href="#!" class="btn-login">회원 로그인</a><a href="/lu/join/join/goJoinMember.do" class="btn-join">회원가입</a><a href="/lu/join/join/goIdPasswordFind.do" class="btn-find">아이디/비밀번호 찾기</a>
							</fieldset>
						</form>
					</div><!-- E : login-area -->	  
	<%} %>
				</li><!-- E : search-log-wrap -->
<!-- ############### // LU searchAndLogin location ########## -->
