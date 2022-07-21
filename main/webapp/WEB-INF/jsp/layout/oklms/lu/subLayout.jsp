<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<link rel="stylesheet" type="text/css" href="/css/oklms/font.css" />
		<script type="text/javascript" src="/js/oklms/webfont.js"></script>
		<script type="text/javascript">
			WebFont.load({
				custom: {
					families: ['nsM' , 'play' , 'nbgM',]
				}
			});
		</script>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		
		<script type="text/javascript">
			var CONTEXT_ROOT = "<%= request.getContextPath() %>";
		</script>
		
		<%-- <%@include file="/includeCss.jsp"%> --%>
		<link href= "<%= request.getContextPath() %>/css/oklms/default.css" rel="stylesheet" type="text/css" />
		
		<%@include file="/includeStdJs.jsp"%>
		<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/bootstrap.min.js"></script> --%>
		<link href="<%= request.getContextPath() %>/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		
			//Controller에서  전달된 메시지를 출력한다.
			$(document).ready(function(){
		
				if(""!="${retMsgEncode}"){
					alert(decodeURI('${retMsgEncode}') );
				}else if ("" != "${retMsg}") {
					alert("${retMsg}");
				}else if ("" != "${returnResultMap.retMsg}") {
					alert("${returnResultMap.retMsg}");
				}
			});
		</script>
	</head>
<body>
<!-- ############### LU subLayout ############### -->
		<div id="wrapper">
		
			<!-- S : top-area --><tiles:insertAttribute name="header" /><!-- E : top-area -->
			
			<div id="content-box">
			<div id="container">
			<!-- S : top-area -->
<%-- 			<tiles:insertAttribute name="searchAndLogin" /> --%>
			<!-- E : searchAndLogin -->
				<!-- <li id="body-wrap"> -->
					<!-- S : menu-area --><tiles:insertAttribute name="left" /><!-- E : menu-area -->
					
						<!-- S : body location --><tiles:insertAttribute name="bodyLocation" /><!-- E : body location -->
						<!-- <div id="content-area" style="margin-top:0px"> -->
						<div id="content-area">
							<!-- S : body --><tiles:insertAttribute name="body" /><!-- E : body -->
						</div><!-- E : content-area -->
					
				<!-- </li> --><!-- E : body-wrap -->
			<!-- </ul> --><!-- E : container -->
			<!-- <hr /> -->
			<!-- S : footer --><tiles:insertAttribute name="footer" /><!-- E : footer -->
			
		</div><!-- E : content-area -->
		</div><!-- E : container -->
		</div><!-- E : content-box -->
		</div><!-- wrapper -->
<!-- ############### // LU subLayout ############### -->
 </body>
</html>