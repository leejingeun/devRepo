var PopupOpenerAPI = {};

/**
 * 레이어로 팝업을 보여준다.
 */
PopupOpenerAPI.openLayer = function(popup, isUserPage) {
	
	var width = popup.popupWidth;
	var height = popup.popupHeight;
	
	// 이미지일경우
//	if (popup.contentType == "I") {
//		width = popup.imageWidth;
//		height = popup.imageHeight;
//	}
	// 팝업창애 하단영역 공간크기를 더해준다.
	height = parseInt(height,10) + 32;
	
	
	var $layer = $('<div id="popup-layer-' + popup.popupId + '"/>');
	var $layerAll = $('<div id=layerAll/>');
	
	$layerAll.css({
		position: "absolute",
		top: "0",
		left: "0",
		width: "100%",
		height: "250%",
		background: "#000",
		opacity: ".5",
		
	});
	
	$layer.css({
		border: "1px solid #000",
		background: "#fff",
		zIndex: "999",
		position: "absolute",
		left: popup.positionLeft + "px",
		top: popup.positionTop + "px"
	});
	
	var $iframe = $('<iframe width="' + width + '" height="' + height + '" src="' + "/la/popmng/openPopup.do?isLayer=true&popupId=" + popup.popupId + '&isUserPage=' + isUserPage + '&pageType=' + popup.pageType + '" scrolling="no" frameborder="0"></iframe>');
	
	$layer.append($iframe);
	
	var $appender = $("body");
	
	if (parent != undefined) {
		$appender = $(parent.document.body);
	}
	
	$appender.append($layer);
	$appender.append($layerAll);
	$layer.show();
	$layerAll.show();
	// 팝업 위치를 모두 입력을안하였을경우 가운데 정렬
	if (popup.positionLeft == 0 && popup.positionTop == 0) {
		$layer.center();
	}

};

/**
 * window.open 기능을 이용해 새로운 창에서 팝업을 보여준다.
 */
PopupOpenerAPI.openPopup = function(popup, isUserPage) {
	
	var top = 0;
	var left = 0;
	var popupUrl="";
	var width = popup.popupWidth;
	var height = popup.popupHeight;
	
	// 이미지일경우
//	if (popup.contentType == "I") {
//		width = popup.imageWidth;
//		height = popup.imageHeight;
//	}

	if (popup.positionLeft == 0 && popup.positionTop == 0) {
		var cw = screen.availWidth; // 화면 넓이
		var ch = screen.availHeight; // 화면 높이
		left = (cw - width) / 2; // 가운데 띄우기위한 창의 x위치
		top = (ch - height) / 2; // 가운데 띄우기위한 창의 y위치
	} else {
		left = popup.positionLeft;
		top = popup.positionTop;
	}
	
	// 팝업창애 하단영역 공간크기를 더해준다.
	height = parseInt(height,10) + 34;

//	var b = $.browser.webkit;
//	if (b == undefined) {
//		b = false;
//	}
//	if (navigator.userAgent.indexOf("Trident") > -1 || $.browser.msie) {
//		width = parseInt(width,10) - 4;
//		height = parseInt(height,10) - 3;
//	}
	//window.open("파일명(경로포함)","새창이름",'scrollbars=yes,toolbar=yes,location=yes,resizable=yes,status=yes,menubar=yes,resizable=yes,width=100,height=100,left=0,top=0,fullscreen');
	var option = "scrollbars=yes, resizable=no, status=no, location=no, menubar=no, width=" + width + ", height=" + height + ", left=" + left + ", top=" + top;
//	if(isUserPage){
//		popupUrl = "/lu/popmng/openPopup.do?popupId=" + popup.popupId + "&isUserPage=" + isUserPage+ '&pageType=' + popup.pageType;
//	}else{
		popupUrl = "/la/popmng/openPopup.do?popupId=" + popup.popupId + "&isUserPage=" + isUserPage+ '&pageType=' + popup.pageType;
//	}
	var opener = window.open( popupUrl, "popup_" + popup.popupId, option);
	
	try {
		opener.focus();
	} catch (e) {
		alert(getMessage("msg.popupWarning"));
	}
};

/**
 * 팝업을 보여준다.
 */
PopupOpenerAPI.open = function(popup, isUserPage) {
	

	//하루동안 그만보기 걸러냄 2depth
	var cookieValToday = getCookieValue("_POPUP_TODAY_"+popup.popupId);
	
	if( cookieValToday == ''){
	
		if (popup.popupType == "L") {
		
			PopupOpenerAPI.openLayer(popup, isUserPage);
		} else {
	
			PopupOpenerAPI.openPopup(popup, isUserPage);
		}
	}else{
		return false;
	}
}


/**
 * pageType 종류별로 검사하여 팝업을 모두 오픈한다.
 */
PopupOpenerAPI.allOpen = function(pageType) {
	
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
	
	for (var i=0; i < PopupOpenerAPI.dataList.length; i++) {
		var popup = PopupOpenerAPI.dataList[i];
		
		
		if (popup.pageType == pageType) {
			
			// 하루동안 그만보기
			var today = $.cookie("_POPUP_TODAY_" + popup.popupId);
			
			// 일주일간 그만보기
			var weekDay = $.cookie("_POPUP_WEEK_" + popup.popupId);
			
			if (today == undefined) {
				today = "";
			}
			
			if (weekDay == undefined) {
				weekDay = "";
			}
			
			var isOpen = true;
			
			if (today == currentDateStr) {
				isOpen = false;
			}
			
			if (weekDay.length > 0) {
				
				weekDay = weekDay.replace("-", "");
				weekDay = weekDay.replace("-", "");
				
				var cDate = currentDateStr;
				cDate = cDate.replace("-", "");
				cDate = cDate.replace("-", "");
				
				if (eval(weekDay) > eval(cDate)) {
					isOpen = false;
				}
			}
			
			if (isOpen) {
				PopupOpenerAPI.open(popup, true);
			}
		}
		
	}
};


jQuery.fn.center = function () {
    this.css("position","absolute");
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + $(window).scrollTop()) + "px");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + $(window).scrollLeft()) + "px");
    return this;
};

$(function() {
	// 학습자 사이트 메인 
	// PopupOpenerAPI.allOpen("main");
	// 학습자 사이트 서브 
	// PopupOpenerAPI.allOpen("sub");
	// 교강사 popup 
	// PopupOpenerAPI.allOpen("tutor");
});