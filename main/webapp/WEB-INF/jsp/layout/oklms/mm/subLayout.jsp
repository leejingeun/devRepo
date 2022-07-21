<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<link rel="stylesheet" type="text/css" href="/css/oklms/font.css" />
		<script type="text/javascript" src="/js/oklms/webfont.js"></script>
		<script type="text/javascript">
			WebFont.load({
				custom: {
					families: ['nbgM', 'nsM', 'play']
				}
			});
		</script>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" />

		<script type="text/javascript">
		<!--
			var CONTEXT_ROOT = "<%= request.getContextPath() %>";
		//-->	
		</script>
		 
		<link href= "<%= request.getContextPath() %>/css/oklms/mobile_default.css" rel="stylesheet" type="text/css" />
		

		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-latest.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-mobile.js"></script>
				

		
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
<!-- ############### M mainLayout ############### -->
		<div id="wrapper">
			<div id="header" class="sub-type">
			<!-- S : menu-area --><tiles:insertAttribute name="left" /><!-- E : menu-area -->
			</div>
			<hr />
			
			<!-- S : body --><tiles:insertAttribute name="body" /><!-- E : body -->						
					
			<hr />
				
			<!-- S : footer --><tiles:insertAttribute name="footer" /><!-- E : footer -->
				
		</div><!-- wrapper -->
<!-- ############### // M mainLayout ############### -->
 </body>
</html>