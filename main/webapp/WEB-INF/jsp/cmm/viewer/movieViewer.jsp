<% 
	String contextRootJS = request.getContextPath();
%>
<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="ko">
	<head>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<link rel="stylesheet" href="/css/oklms/movie.css" type="text/css" />
		<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-1.11.1.js"></script>
		<script type="text/javascript" src="/jwplayer5/jwplayer.js"></script>
		<script type="text/javascript">
			var width = ${param.width};
			var height = ${param.height};
			$(document).ready( function(){
				jwplayer('video').setup({
					flashplayer: "/jwplayer5/player.swf",
					file: "${param.movieUrl}",
					title: "제목",
					width: (width-15),	// -15
					height: (height-17), // -17
					controlbar: "bottom",       // 컨트롤바 bottom, top, over, none
					playlist: "none",           // 플레이리스트 위치 none, top, bottom, right, left, over
					playlistsize: "100",        // 플레이리스트 높이
					stretching: "exactfit",      // 플레이어 화면 크기에 영상을 맞춤
					repeat :"true",           // 재생리스트의반복설정 false, true, always
					mute: "false",             // 음소거 false(음소거없음,default), true(음소거)
					autostart: "true",         // 자동재생(true/false) 
					menu: "false",                 // 마우스 오른쪽 클릭시 메뉴(jwplayer버전표시 등) 안보임
					wmode: "transparent"        // opaque
				});
			});	
			
			
			// width: screen.width
			// height: screen.height
			// width: $(window).width()
			// height: $(window).height()
			// stretching: uniform(Default)  // 영상 비율 변하지않음
			// stretching: "fill"            // 가로크기에 맞춰 영상을 꽉채움.
			// stretching: "exactfit"        // 영상의 화면비율 무시하고  플레이어 화면 크기에 영상을 맞춤.
			// stretching: "none"            // 플레이어 크기와 상관없이 원본영상크기대로 나옴.
			// volume: "100"                 // 기본볼륨(0~100)
			// playlistfile: "./test.rss"    // xml 파일주소 
			// menu: "false"                 // 마우스 오른쪽 클릭시 메뉴(jwplayer버전표시 등) 안보임
			// frontcolor: 'red'             // 컨트롤바 기본색
			// lightcolor: 'blue'            // 컨트롤바 강조색
		</script>
		
	</head>

   	<body>
   		<div id="movie-area" style="width:'${param.width}';height: ${param.height}">
			<div id="video">플레이어 불러오는 중...</div>
		</div>
	</body>
</body>
</html>