<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!-- ############### bodyLayout ############### -->
<!DOCTYPE HTML>
<html lang="ko">
<head>
<title><tiles:insertAttribute name="title" /></title>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />
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
	//Controller에서  전달된 메시지를 출력한다. ( gridUtil.setretMsg 값 )
	$(document).ready(function() {
		
		if(""!="${retMsgEncode}"){
			alert(decodeURI('${retMsgEncode}') );
		}else if ("" != "${retMsg}") {
			alert("${retMsg}");
		}
	});

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
<!-- ############### bodyLayout ############### -->
	<tiles:insertAttribute name="body" />
<!-- ############### // bodyLayout ############### -->
</body>
</html>