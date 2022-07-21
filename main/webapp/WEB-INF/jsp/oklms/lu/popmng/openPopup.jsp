<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="data" value="${result}" />
<c:if test="${data == null}">
<script type="text/javascript">
 self.close(); 
</script>
</c:if>
<c:if test="${data != null}">
<c:set var="width" value="${data.popupWidth}"/>
<c:set var="height" value="${data.popupHeight}"/>
<!DOCTYPE html>
<html>
<head>	
<title>${data.title}</title>
<style type="text/css">
body { margin: 0px 0px; padding: 0px 0px; }
a, span, div, p { color: #000; }
ul, li, ol { list-style: none; margin: 0px 0px; padding: 0px 0px; }
label { vertical-align: middle; display: inline-block; padding-top: 2px; }
input[type=checkbox] { vertical-align: middle; display: inline-block; }
label, div.content { font-size: 12px; }
img { border: 0px; }
div.popup-wrapper { width: 100%; }
/* div.popup-wrapper > div.content { position: relative;  width: ${width}px; height: ${height}px; ${data.contentType == 'H' ? 'overflow-y: auto;' : ''} } */
div.popup-wrapper > div.content { position: relative;  width: 100%; height: ${height}px;  ${data.contentType == 'H' ? 'overflow-y: auto;' : ''};}
/* div.popup-wrapper > div.content > div.text { padding: 5px 5px; ${data.contentType == 'H' ? 'overflow-y: auto;' : ''}; height: ${height}px;} */
iv.popup-wrapper > div.content > div.text { padding: 10px 10px;}
div.popup-wrapper > div.close1 { border-top: 2px solid #b3b3b3; text-align: right; padding: 5px 5px; height: 20px; }
div.popup-wrapper > div.close1 > div.left { float: left; }
div.popup-wrapper > div.close1 > div.right { float: right; }
</style>
<script type="text/javascript" src="${contextRootJS }/js/oklms/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="${contextRootJS }/js/oklms/popupApi.js"></script>
<script type="text/javascript">
var popupId = "${data.popupId}";
var isLayer = "${param.isLayer}";
var contentType = "${data.contentType}";
var isUserPage = "${param.isUserPage}";
var pageUrl = "${data.pageUrl}";
$(function() {
	/* var cookieVal = getCookieValue("_POPUP_WEEK_"+popupId);
	if( cookieVal != '' ){
		viewClose(false);
	} */
	// 체크박스 이벤트
	$("input[name=isCloseViewSettings]").bind("click", function() {
		// 사용자화면에서 팝업을 호출했을경우에만 쿠키에 값을 기억시킨다.
		if (isUserPage == "true") {
			var closeType = $(this).val();
			if (closeType == "W") {
				var currentDate = new Date();
				currentDate = new Date(Date.parse(currentDate) + 7 * 1000 * 60 * 60 * 24);
				var currentDateStr = "";
				{
					var year = currentDate.getFullYear();
					var month = currentDate.getMonth() + 1;
					var day = currentDate.getDate();
				
					if (month < 10)
						month = "0" + month;
					if (day < 10)
						day = "0" + day;
					
					currentDateStr = year + "-" + month + "-" + day;
				}			  
				
				$.cookie("_POPUP_WEEK_" + popupId, currentDateStr, { path: "/", expires: 30 });
				
			} else if (closeType == "D") {
				
				var currentDate = new Date();
				
				var currentDateStr = "";
				
				{
					var year = currentDate.getFullYear();
					var month = currentDate.getMonth() + 1;
					var day = currentDate.getDate();
				
					if (month < 10)
						month = "0" + month;
					if (day < 10)
						day = "0" + day;
					
					currentDateStr = year + "-" + month + "-" + day;
				}
				
				$.cookie("_POPUP_TODAY_" + popupId, currentDateStr, { path: "/", expires: 30 });
			}	
		}
		
		viewClose(false);
	});
	
// 	var b = $.browser.webkit;
// 	if (b == undefined) {
// 		b = false;
// 	}
	
// 	// 크롬 또는 파이어폭스에서는 팝업창 resize 옵션이 활성화되므로 강제로 resize 이벤트를 등록하여 팝업띄운 크기로 강제로 resize한다.
// 	if (b || $.browser.mozilla) {
// 		var browserUIWidth = b ? 16 : 18;
// 		var closeHeight = 32;
// 		var browserUIHeight = b ? 68 : 80;
// 		var popupWindowWidth = parseInt("${width}") + browserUIWidth;
// 		var popupWindowHeight = parseInt("${height}") + closeHeight + browserUIHeight;

// 		$(window).resize(function() {
// 			window.resizeTo(popupWindowWidth, popupWindowHeight);
// 		});
// 	}
	
});

/***
 * 현재 레이어 또는 팝업을 닫는다.
 */
var viewClose = function(isPageMove) {
	// 레이어인경우 parent의 스크립트를 호출한다.
	if (isLayer == "true") {
// 		if (isPageMove) {
// 			var b = $.browser.webkit;
// 			if (b == undefined) {
// 				b = false;
// 			}
			
// 			// Trident 는 MSIE 11 버전부터 ? MSIE가 아닌 Trident로 변경됨
// 			if (navigator.userAgent.indexOf("Trident") > -1 || $.browser.msie) {
// 				$.browser.mozilla = false;
// 			}
// 			// 크롬 또는 파이어폭스에서는  onclick이벤트가 먼저실행되므로 강제로 window.open 시킨다.
// 			if (b || $.browser.mozilla) {
// 				window.open(pageUrl, 'newPage_' + popupId , '');
// 			}
// 		}
		var $popup = $('#popup-layer-' + popupId, parent.document.body);
		var $popup1 = $('#layerAll', parent.document.body);
		if ($popup.length > 0) {
			$popup.remove();
			$popup1.remove();
		}
	} else {
		self.close();
	}
};
</script>
</head>
<body>
	<div class="popup-wrapper">
		<div class="content">
		<c:if test="${data.contentType == 'I' && fn:length(data.imageFileId) > 0}">
			<a href="${data.pageUrl}" onclick="viewClose(true);" target="_blank"><img src="/commbiz/atchfle/atchFileDown.do?importAtchFileId=${data.imageFileId}&importFleSn=1"/></a>
		</c:if>
		<c:if test="${data.contentType == 'H'}">
			<div class="text">
			${data.content}
			</div>
		</c:if>
		</div>
		<div class="close1">
			<div class="left">
				<c:if test="${data.isCloseViewSettings == 'D'}">
		       		<input type="checkbox" id="isCloseViewSettings1" name="isCloseViewSettings" vSMART alue="D" />
		       		<label for="isCloseViewSettings1">하루동안 그만보기</label>
	       		</c:if>		
       			<c:if test="${data.isCloseViewSettings == 'W'}">
		       		<input type="checkbox" id="isCloseViewSettings2" name="isCloseViewSettings" value="W" />
		       		<label for="isCloseViewSettings2">일주일간 그만보기</label>
	       		</c:if>								    	
			</div>
			<div class="right">
				<a href="javascript:;" onclick="viewClose(false);"><img src="${pageContext.request.contextPath}/images/oklms/btn/btn_close.png"/></a>
			</div>	
		</div>
	</div>
</body>
</html>
</c:if>