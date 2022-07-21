<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="ko">
	<head>
		<title>학습관리통합시스템 온라인교육시스템 : 접속오류</title>
		<link rel="stylesheet" type="text/css" href="/css/oklms/font.css" />
		<script type="text/javascript" src="/js/jquery/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="/js/oklms/webfont.js"></script>
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
			WebFont.load({
				custom: {
					families: ['nsM' , 'play' , 'nbgM',]
				}
			});
			function fncGoAfterErrorPage(){
			    history.back(-2);
			}
		</script>


		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" /> -->
		<meta content="IE=edge" http-equiv="X-UA-Compatible">

		<link rel="stylesheet" type="text/css" href="/css/oklms/default.css" />
	</head>


	<body>
		<div id="wrap-error">
			<ul>
				<li class="logo"></li>
				<li class="text h-type">
					<p class="type-21">잘못 된 접근경로 입니다</p>
					<p class="type-22">바로가기 버튼을 눌러<br />다시 접속 해 주시기 바랍니다</p>
					<p class="type-23"><a href="/">바로가기</a></p>
				</li>
				<li class="number"><span>CUSTOMER CENTER</span>041) 1111-2222, 8</li>
			</ul>
		</div>
		<!-- 
													<c:choose>
														<c:when test="${null != exception.message }">
															${exception.message}
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
													 -->
	</body>
</html>