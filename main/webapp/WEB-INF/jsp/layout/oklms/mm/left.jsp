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
<%
	LoginInfo loginInfo = new LoginInfo();
 
	if(loginInfo.isLogin()){
		String sessionLastLoginDt = session.getAttribute(Globals.SESSION_LAST_LOGIN_DT) + ""; // 최종 로그인 시간	
	}
%>
	<c:if test="${not empty menuList}">
				<div class="header-area"> 

					<h2>
					<c:set var="upperMenuNo" value="TOP"/>
					<c:set var="menuLevel" value="1"/>
					<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>					
					<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
						<c:if test="${menuId eq menu1.menuId}">${menu1.menuTitle}</c:if>
					</c:forEach>					
					</h2>
					<a href="#!" onclick="javascript:history.back();" class="gnb-pre">이전 화면으로 이동</a>
					<a href="#!" class="gnb-btn-2">메뉴 보기</a>
					<div class="page-cover"></div>

					<div id="gnb">
						<h3><b onclick="javascript:location.href='/mm/main/lmsMainPage.do'">INDEX</b> <a href="#!" class="gnb-close">메뉴 닫기</a></h3>

						<div class="info-area">
							<span><b><%=loginInfo.getUserName() %></b>님<br />환영합니다.</span>
							<a href="/commbiz/login/mobilelogout.do" >로그아웃</a>
						</div>

						<div class="menu">

						<c:if test="${not empty menuList[key1]}">
							<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
							 	<c:if test="${menu1.isLeafMenu eq '1'}">
									<a href="${menu1.menuUrl }" >${menu1.menuTitle } </a>
						 		</c:if>
							</c:forEach>
						</c:if>
						</div>
					</div><!-- E : gnb -->
				</div><!-- E : header-area -->
	</c:if>
	<c:if test="${empty menuList}">
				<div class="header-area"> 

					<h2 class="hidden">메인메뉴</h2>
					<a href="#!" class="gnb-btn-2">메뉴 보기</a>
					<div class="page-cover"></div>

					<div id="gnb">
						<h3><b onclick="javascript:location.href='/mm/main/lmsMainPage.do'">INDEX</b> <a href="#!" class="gnb-close">메뉴 닫기</a></h3>
						<div class="login-area">
							<a href="/mm/login/goLmsMobileLogin.do" class="btn-login">로그인 하기</a>
							<a href="#!" class="btn-find">아이디 / 비밀번호 찾기</a>
						</div>
						<div class="menu">
						</div>
					</div><!-- E : gnb -->
				</div><!-- E : header-area --> 	
	</c:if>
<script type="text/javascript">
<!--
 
		$(document).ready(function() {
			// 메뉴권한 체크
			<c:if test="${empty menuList}">
			<%
			// 로그인여부 체크
			if(loginInfo.isLogin()){
			%>
			alert("모바일 메뉴 권한이 없습니다.");
			location.href="/commbiz/login/mobilelogout.do";
			<%	
			}
			%>
			</c:if>
			
		});
//-->
</script>