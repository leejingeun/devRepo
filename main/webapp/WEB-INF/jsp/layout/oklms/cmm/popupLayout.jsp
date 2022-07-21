<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- ############### popupLayout ############### -->
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
<!-- ############### popupLayout ############### -->
	<section id="popup" style="width:100%"> <!-- #popup -->
<tiles:insertAttribute name="popupTitle" />	
		<div class="popup_content">
<tiles:insertAttribute name="body" />
		</div>
	</section><!-- #sub_contents // --> 
 <!-- ############### // popupLayout ############### -->
 </body>
</html>
