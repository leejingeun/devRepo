<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.commons.lang3.time.DateUtils" %>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils" %>
<%@ page import="kr.co.sitglobal.oklms.commbiz.util.BizUtil" %>
<%@ page import="kr.co.sitglobal.oklms.comm.vo.LoginInfo" %>
<%@ page import="egovframework.com.cmm.service.Globals" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%-- <%@ page import="kr.co.sitglobal.oklms.sys.menu.vo.MenuMngmVO" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- oklms Header -->
<%
LoginInfo loginInfo = new LoginInfo();
//LoginInfo loginInfo = new LoginInfo(request, response);
%>
		
<!-- ############### header ############### -->
<div class="header_top">
		<h1>CI oklms 학습관리통합시스템</h1>
	<%
	if(loginInfo.isLogin()){
		//String sessionLastLoginDt = loginInfo.getAttribute("SESSION_LAST_LOGIN_DT") + "";
		String sessionLastLoginDt = session.getAttribute(Globals.SESSION_LAST_LOGIN_DT) + "";
		//String sessionLastLoginDt = loginInfo.getLoginInfo().getLasLgnYmd() + "";
		
	%>
		  <ul class="utilmenu">
			<li class="time">최종로그인시간 : <%=sessionLastLoginDt %></li>
			<li class="user"><a href="#"><%=loginInfo.getUserName() %>님</a></li>
<!-- 			<li><a href="/oklms/commbiz/my/myInfoUpdtPwChk.do">My Page</a></li> -->
			<li><a href="/commbiz/login/logout.do">로그아웃</a></li>
<!-- 			<li><a href="/oklms/commbiz/my/sitemap.do">사이트맵</a></li> -->
		  </ul>
	<%}else{ %>
		  <ul class="utilmenu">
			<li><a href="/lu/login/goLmsUserLogin.do">로그인</a></li>
			<li><a href="#">사이트맵</a></li>
		  </ul>
	<%} %>
		  
	</div>
 <%--
	<nav class="gnb_wrap">

		<c:set var="upperMenuNo" value="ROOT"/>
		<c:set var="menuLevel" value="1"/>
		<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>
		<c:if test="${not empty menuList[key1]}">
			<ul id="gnb">
				<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
					<li><a href="#" onclick="javascript:com.subPageMove('${menu1.rootMenuId }', '${menu1.menuId }' , false);">${menu1.menuName }</a>
						<c:set var="key2" value="${ menu1.menuId}_${menu1.menuLevel+1}"/>
						<c:if test="${not empty menuList[key2]}">
							<ul class="depth2">
								<c:forEach var="menu2" items="${menuList[key2]}" varStatus="status2">
									<li><a href="#" onclick="javascript:com.subPageMove('${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuName }</a>
										<c:set var="key3" value="${ menu2.menuId}_${menu2.menuLevel+1}"/>
										<c:if test="${not empty menuList[key3]}">
											<ul class="depth3">
												<c:forEach var="menu3" items="${menuList[key3]}" varStatus="status3">
													<li><a href="#" onclick="javascript:com.subPageMove('${menu3.rootMenuId }', '${menu3.menuId }' , false);">${menu3.menuName }</a>
														<c:set var="key4" value="${ menu3.menuId}_${menu3.menuLevel+1}"/>
														<c:if test="${not empty menuList[key4]}">
															<ul class="depth4">
																<c:forEach var="menu4" items="${menuList[key4]}" varStatus="status3">
																	<li><a href="#" onclick="javascript:com.subPageMove('${menu4.rootMenuId }', '${menu4.menuId }' , false);">${menu4.menuName }</a></li>
																</c:forEach>														
															</ul>				
														</c:if>
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</nav>
<script type="text/javascript">
	$(document).ready(function() {
		
		$("#gnb .depth2").stop().slideDown(0);
		var menuHeight = $("#gnb").height();
		 $("#gnb .depth2").height(menuHeight-70 ); 		//높이 재조정
		 
		 
		 $("#gnb > li").mouseover(function(){
			 $("#gnb .depth2").stop().slideDown(300);
			 $("#gnb").css("border-bottom","solid 1px #f0f0f0");
		 });
		 $("#gnb > li").mouseout(function(){
			 $("#gnb .depth2").stop().slideUp(300);
			 $("#gnb").css("border-bottom","solid 1px #176abe");
		 });
		 $("#gnb .depth2").hide();
	}); 
 </script>
 
  --%>
<!-- ############### // header ############### -->