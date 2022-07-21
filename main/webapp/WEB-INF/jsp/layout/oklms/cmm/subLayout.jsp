<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<!-- ############### subLayout ############### -->

<header>	<!-- header -->
	<tiles:insertAttribute name="header" />
 </header> <!-- //header -->

 <section id="contents">  <!-- #contents -->
	<nav id="lnb">
		<tiles:insertAttribute name="left" />
	</nav>
	<section id="sub_contents"> <!-- #sub_contents -->
		<tiles:insertAttribute name="bodyLocation" />
		<tiles:insertAttribute name="body" />
	</section><!-- #sub_contents // -->
 </section> <!-- #contents // -->

 <footer> <!-- #footer -->
 	<tiles:insertAttribute name="footer" />
 </footer> <!-- //footer -->
<!-- ############### // subLayout ############### -->
 </body>
</html>