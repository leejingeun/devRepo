<!DOCTYPE HTML>
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
		
		<%@include file="/includeCss.jsp"%>
		<link href= "<%= request.getContextPath() %>/css/oklms/default.css" rel="stylesheet" type="text/css" />
		<link href= "<%= request.getContextPath() %>/css/oklms/main.css" rel="stylesheet" type="text/css" />
		
		<%@include file="/includeStdJs.jsp"%>
<%-- 		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/bootstrap.min.js"></script> --%>
<%-- 		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-common.js"></script> --%>
		
		
		<script type="text/javascript" src="/js/oklms/jquery-admin.js"></script>
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

			/**
			 * browser 버전 체크
			 * @returns
			 */
			function browserVer(){

				var bwVer = "";
				var browserInfo = navigator.userAgent.toLowerCase();
				if( "Explorer" == browserType() ){
					bwVer = getIEVersion();
				}else if( "Chrome" == browserType() ){
					bwVer = getChromeVersion();
				}else{
					var bw = $.browser;
					if( undefined != bw ){
						bwVer = $.browser.version;
					}
				}
				return bwVer;
			}

			function getIEVersion() {
				var match = navigator.userAgent.match(/(?:MSIE |Trident\/.*; rv:)(\d+)/);
				return match ? parseInt(match[1]) : undefined;
			}

			function getChromeVersion() {
				var vMatch = navigator.userAgent.match(/(?:Chrome\/.*)(\d+)/);
				vMatch = vMatch + "";
				vMatch = vMatch.split(" ");
				return vMatch ? vMatch[0] : undefined;
			}
			/**
			 * browser  타입 체크
			 * @returns
			 */
			function browserType(){
				var browserInfo = navigator.userAgent.toLowerCase();
				var browser     = browserInfo;

				if(browserInfo.indexOf('msie') != -1)    browser = "Explorer";
				if(browserInfo.indexOf('trident') != -1) browser = "Explorer";
				if(browserInfo.indexOf('safari') != -1)  browser = "Safari";
				if(browserInfo.indexOf('chrome') != -1)  browser = "Chrome";
				if(browserInfo.indexOf('opr') != -1)     browser = "Opera";
				if(browserInfo.indexOf('firefox') != -1) browser = "Firefox";
				if(browserInfo == browser)               browser = "등록되지 않은 브라우저 입니다";

				/*
				 alert("Explorer : "+$.browser.msie);
				 alert("safari : "  +$.browser.safari);
				 alert("opera : "   +$.browser.opera);
				 alert("mozilla : " +$.browser.mozilla);
				 alert("chrome : "  +$.browser.chrome);
				 */
				return  browser;
			}

			if( "Explorer" == browserType() && 10 > browserVer() ){
				alert("Internet Explorer 10 이상 버전에 최적화 되어있으므로 업그레이드후 이용을 권장합니다.\n F12 클릭후 아래 내용을 확인해 주세요.\n 브라우저 모드 : IE 10 , 문서 모드 : 표준");
			}


		</script>
	</head>
<body>
<!-- ############### LU subLayout ############### -->
		<div id="wrapper">
		
			<!-- S : top-area --><tiles:insertAttribute name="header" /><!-- E : top-area -->
			
			<div id="content-box">
			<div id="container">
			<!-- S : top-area -->
					<!-- S : menu-area --><tiles:insertAttribute name="left" /><!-- E : menu-area -->
						<div id="content-area">
							<!-- S : body --><tiles:insertAttribute name="body" /><!-- E : body -->
						</div><!-- E : content-area -->
			<!-- S : footer --><tiles:insertAttribute name="footer" /><!-- E : footer -->
		</div><!-- E : content-area -->
		</div><!-- E : container -->
		</div><!-- E : content-box -->
		</div><!-- wrapper -->
<!-- ############### // LU subLayout ############### -->
 </body>
</html>