<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
	<head>
		<title>OK-LMS</title>
		<link rel="stylesheet" type="text/css" href="/css/oklms/font.css" />
		<!-- <link rel="stylesheet" type="text/css" href="/css/oklms/default.css" />
		<link rel="stylesheet" type="text/css" href="/css/oklms/main.css" /> -->
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
		
		<link href= "<%= request.getContextPath() %>/css/oklms/default.css" rel="stylesheet" type="text/css" />
		<link href= "<%= request.getContextPath() %>/css/oklms/main.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery/jquery-1.11.0.js"></script>
		<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery/jquery-common.js"></script> --%>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery/mvisual.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/common.js"></script>
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
<!-- ############### LU loginLayout ############### -->
<div id="main-wrapper">
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />
</div><!-- E : wrapper -->
<!-- ############### // LU loginLayout ############### -->
 </body>
</html>