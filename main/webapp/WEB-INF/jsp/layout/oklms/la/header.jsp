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
<!-- ############### LA Header ############### -->
	<%
	if(loginInfo.isLogin()){
		//String sessionLastLoginDt = loginInfo.getAttribute("SESSION_LAST_LOGIN_DT") + "";
		String sessionLastLoginDt = session.getAttribute(Globals.SESSION_LAST_LOGIN_DT) + ""; // 최종 로그인 시간
		//String sessionLastLoginDt = loginInfo.getLoginInfo().getLasLgnYmd() + "";
		
	%>
	<c:if test="${ empty menuList}">
 					<dl id="top-area"> 
 						<dt>&nbsp;</dt> 
						<dd class="info"><span><%=loginInfo.getUserName() %></span>님 안녕하세요. <a href="/commbiz/login/logout.do" class="logout">로그아웃</a> </dd>
 						
<!--  						<dd class="bg-1"></dd>  -->
<!--  						<dd class="bg-2"></dd>  -->
 					</dl>	 
	</c:if> 					
	<c:if test="${not empty menuList}"> 					
 			<div id="header">
				<h1><a href="/lu/main/lmsUserMainPage.do" title="학습관리통합시스템 온라인교육시스템">학습관리통합시스템</a></h1>
			</div>
 					
 					
			<div id="gnb-pc" class="newgnb-menu-area newgnb-two-depth-area">
				<h2 class="hidden">전체메뉴</h2>
				<div class="newgnb-menu">
					<ul class="gnb-one-depth">
					
						<c:set var="upperMenuNo" value="TOP"/>
						<c:set var="menuLevel" value="1"/>
						<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>
						<c:if test="${not empty menuList[key1]}">
							<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
								<!-- S : depth-1 -->
								<li class="sel0${status.count }">
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${0 eq menu1.isLeafMenu}"><a id="hm_a_${menu1.menuId }" href="#" >${menu1.menuTitle }<img src="/images/oklms/std/inc/blank.png" alt="" /></a></c:when> --%>
<%-- 											<c:otherwise><a id="hm_a_${menu1.menuId }" href="#" onclick="javascript:com.subPageMove('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }' , false);">${menu1.menuTitle }<img src="/images/oklms/std/inc/blank.png" alt="" /></a></c:otherwise> --%>
<%-- 										</c:choose> --%>
										<a id="hm_a_${menu1.menuId }" href="#" class="btn-slide" onclick="javascript:com.subPageMove('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }' , false);">${menu1.menuTitle }<img src="/images/oklms/std/inc/blank.png" alt="" /></a>
										<c:set var="key2" value="${ menu1.menuId}_${menu1.menuLevel+1}"/>
										<c:choose>
											<c:when test="${not empty menuList[key2]}">
											<div class="list0${status.count } sell">
												<ul>
												<c:forEach var="menu2" items="${menuList[key2]}" varStatus="status2">
													<!-- S : depth-2 -->
<%-- 													<c:choose> --%>
<%-- 														<c:when test="${0 eq menu2.isLeafMenu}"><li><a id="hm_a_${menu2.menuId }" href="#" >${menu2.menuTitle }</a></li></c:when> --%>
<%-- 														<c:otherwise><li><a id="hm_a_${menu2.menuId }" href="#" onclick="javascript:com.subPageMove('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuTitle }</a></li></c:otherwise> --%>
<%-- 													</c:choose> --%>
														<li><a id="hm_a_${menu2.menuId }" href="#" onclick="javascript:com.subPageMove('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuTitle }</a></li>
												</c:forEach>
												</ul>
											</div>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
								</li>
								
							</c:forEach>
						</c:if>
					
					</ul>
				</div>
			</div><!-- gnb -->
	</c:if>
	<%}else{ %>
					<dl id="top-area">
						<dt><span>ADMIN</span> mode</dt>
						<dd class="info"><a href="/lu/login/goLmsUserLogin.do"></a></dd>
<!-- 						<dd class="bg-1"></dd> -->
<!-- 						<dd class="bg-2"></dd> -->
					</dl>			  
	<%} %>
	
<!-- ############### // LA Header ############### -->