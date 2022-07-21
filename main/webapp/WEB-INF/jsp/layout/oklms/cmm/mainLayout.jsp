<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- ############### mainLayout ############### -->
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<title><tiles:insertAttribute name="title" /></title>
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
<!-- ############### mainLayout ############### -->
<table style="width: 600px;height: 700px;" border="1">
<tr style="height: 100px;">
	<td><tiles:insertAttribute name="header" /></td>
</tr>
<tr style="vertical-align: top;">
	<td><tiles:insertAttribute name="body" /></td>
</tr>
<tr style="height: 100px;">
	<td><tiles:insertAttribute name="footer" /></td>
</tr>
</table>
<!-- ############### // mainLayout ############### -->
 </body>
</html>