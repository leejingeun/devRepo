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
					families: ['nbgM', 'play']
				}
			});
		</script>

		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="keywords" content="학습관리통합시스템">
		<meta name="description" content="학습관리통합시스템">
		<script type="text/javascript">
			var CONTEXT_ROOT = "<%= request.getContextPath() %>";
		</script>
		
		<%@include file="/includeCss.jsp"%>
		<link href= "<%= request.getContextPath() %>/css/oklms/adm_default.css" rel="stylesheet" type="text/css" />
		
		<%@include file="/includeJs.jsp"%>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-admin.js"></script>
		
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
<!-- ############### LA bodyLayout ############### -->
		<ul id="wrapper">
			<!-- S : body --><tiles:insertAttribute name="body" /><!-- E : body -->
		</ul><!-- wrapper -->
<!-- ############### // LA bodyLayout ############### -->
 </body>
</html>