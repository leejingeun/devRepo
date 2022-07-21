<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.commons.lang3.time.DateUtils" %>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils" %>
<%@ page import="kr.co.sitglobal.oklms.commbiz.util.BizUtil" %>
<%@ page import="kr.co.sitglobal.oklms.comm.vo.LoginInfo" %>
<%@ page import="egovframework.com.cmm.service.Globals" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%// page import="kr.co.sitglobal.oklms.sys.menu.vo.MenuMngmVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- oklms Header -->
<%
LoginInfo loginInfo = new LoginInfo();
//LoginInfo loginInfo = new LoginInfo(request, response);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- ############### subLayout ############### -->
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><tiles:insertAttribute name="title" /></title>
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="author" content="">
	<script type="text/javascript">
	//var CONTEXT_ROOT = "<c:url value='/' />"; // Client 에서 ContextRoot 활용
	//var CONTEXT_ROOT = "${pageContext.request.contextPath}";
	var CONTEXT_ROOT = "<%= request.getContextPath() %>";
	</script>
<%@include file="/includeCss.jsp"%>
<%@include file="/includeJs.jsp"%>

	<script type="text/javascript">

		//Controller에서  전달된 메시지를 출력한다. ( gridUtil.setRetMsg 값 )
		$(document).ready(function(){
			
			if(""!="${retMsgEncode}"){
				alert(decodeURI('${retMsgEncode}') );
			}else if ("" != "${retMsg}") {
				alert("${retMsg}");
			}
		});
			
		function GetURLParameter(sParam)
		{
		    var sPageURL = window.location.search.substring(1);
		    var sURLVariables = sPageURL.split('&');
		    for (var i = 0; i < sURLVariables.length; i++) 
		    {
		        var sParameterName = sURLVariables[i].split('=');
		        if (sParameterName[0] == sParam) 
		        {
		            return sParameterName[1];
		        }
		    }
		}
	
		document.createElement("nav");
		document.createElement("header");
		document.createElement("footer");
		document.createElement("section");
		document.createElement("aside");
		document.createElement("article");
		document.createElement("figure");
		
	</script>
</head>
<body>

<!-- ############### bodyNonLoginLayout ############### -->

<header>	<!-- header -->
	
		<div class="header_top">
				<%
		if(loginInfo.isLogin()){
			//String sessionLastLoginDt = loginInfo.getAttribute("SESSION_LAST_LOGIN_DT") + "";
			String sessionLastLoginDt = session.getAttribute(Globals.SESSION_LAST_LOGIN_DT) + "";
			//String sessionLastLoginDt = loginInfo.getLoginInfo().getLasLgnYmd() + "";
			
			
			
			String sessionLastLoginDtStr = null;
			try {
				sessionLastLoginDtStr = BizUtil.toLocaleDateString( sessionLastLoginDt , "EEE MMM dd HH:mm:ss Z yyyy" , Locale.ENGLISH , "yyyy-MM-dd HH:mm:ss" );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		%>
			  <ul class="utilmenu">
				<li class="time">최종로그인시간 : <%=sessionLastLoginDtStr %></li>
				<li class="user"><a href="#"><%=loginInfo.getUserName() %>님</a></li>
				<li><a href="/oklms/commbiz/my/myInfoUpdtPwChk.do">My Page</a></li>
				<li><a href="/oklms/commbiz/usr/logout.do">로그아웃</a></li>
				<li><a href="/oklms/commbiz/my/sitemap.do">사이트맵</a></li>
			  </ul>
		<%}else{ %>
			  <ul class="utilmenu">
				<li><a href="/oklms/commbiz/usr/loginForm.do">로그인</a></li>
				<li><a href="#">사이트맵</a></li>
			  </ul>
		<%} %>
			<h1><a href="/oklms/commbiz/main/mainPage.do"><img src="/images/ci.gif" alt=""></a></h1>
		</div>
		<nav class="gnb_wrap">
		
		</nav>
 </header> <!-- //header -->

 <section id="contents">  <!-- #contents -->
	<nav id="lnb">
	</nav>
	<section id="sub_contents"> <!-- #sub_contents -->
		<tiles:insertAttribute name="body" />
	</section><!-- #sub_contents // -->
 </section> <!-- #contents // -->

 <footer> <!-- #footer -->
 	<tiles:insertAttribute name="footer" />
 </footer> <!-- //footer -->
<!-- ############### // bodyNonLoginLayout ############### -->
 </body>
</html>