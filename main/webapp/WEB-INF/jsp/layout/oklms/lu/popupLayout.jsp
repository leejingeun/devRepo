<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
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

		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" /> -->
		<meta content="IE=edge" http-equiv="X-UA-Compatible">
		
		<script type="text/javascript">
			var CONTEXT_ROOT = "<%= request.getContextPath() %>";
		</script>
		
		<%@include file="/includeCss.jsp"%>
		<%-- <link href= "<%= request.getContextPath() %>/css/oklms/usr_default.css" rel="stylesheet" type="text/css" /> --%>
		<link href= "<%= request.getContextPath() %>/css/oklms/default.css" rel="stylesheet" type="text/css" />
		
		<%@include file="/includeStdJs.jsp"%>
		<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/bootstrap.min.js"></script> --%>
		<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-common.js"></script> --%>
		
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
<!-- ############### LU popupLayout ############### -->
<tiles:insertAttribute name="popupTitle" />	
<tiles:insertAttribute name="body" />
 <!-- ############### // LU popupLayout ############### -->
 </body>
</html>
</html>
