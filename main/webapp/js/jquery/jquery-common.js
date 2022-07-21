
/*************************************************************
  로그인 관련 스크립트
 **************************************************************/

jQuery(function($){
	var login = $('#login');
	var uid = $('.iText.uid');
	var upw = $('.iText.upw');
	
	// Input Clear
	var iText = $('.item>.iLabel').next('.iText');
	$('.item>.iLabel').css('position','absolute');
	iText
		.focus(function(){
			$(this).prev('.iLabel').css('visibility','hidden');
		})
		.blur(function(){
			if($(this).val() == ''){
				$(this).prev('.iLabel').css('visibility','visible');
			} else {
				$(this).prev('.iLabel').css('visibility','hidden');
			}
		})
		.change(function(){
			if($(this).val() == ''){
				$(this).prev('.iLabel').css('visibility','visible');
			} else {
				$(this).prev('.iLabel').css('visibility','hidden');
			}
		})
		.blur();

	// Validation
	$('#login>.gLogin input[type=submit]').click(function(){
		if(uid.val() == '' && upw.val() == ''){
			alert('아이디와 비밀번호를 입력하세요!');
			return false;
		}
		else if(uid.val() == ''){
			alert('아이디를 입력하세요!');
			return false;
		}
		else if(upw.val() == ''){
			alert('비밀번호를 입력하세요!');
			return false;
		}
	});
});










/*************************************************************
  메인 팝업존 관련 스크립트
 **************************************************************/

var onBannerGroupIdx = 1;
var onPopupIdx = 1;
var onPopupStopPoint = true;

var Interval = '';

// 이벤트 선언부 
$(function() {
	Main.init();
});

// 바디 함수 명시 
var Main = {
	init : function() {
		try {
			$('.event_popup_prev').click(function() {
				Main.popupMove('prev');
			});
			$('.event_popup_next').click(function() {
				Main.popupMove('next');
			});
			
			Interval = setInterval("Main.popupMove('next')", '5000');
			$('.event_popup_stop').click(Main.popupStop);
		} catch(e) {
			alert("[/js/main/main.js's Main.init] " + e.description);
		}
	},

	popupStop : function() {
		try {
			if(Interval) {
				clearInterval(Interval);
				Interval = '';
			} else {
				Interval = setInterval("Main.popupMove('next')", '5000');
			}
		} catch(e) {
			alert("[/js/main/main.js's Main.popupStop] " + e.description);
		}
	},

	popupMove : function(pram) {
		try {
			var target = $('.pop-img .popupitem');
			if(pram == 'prev') {
				if(onPopupIdx == 1) {
					onPopupIdx = target.length;
				} else {
					onPopupIdx = onPopupIdx-1;
				}
				target.css('display', 'none');
				target.each(function(idx) {
					if(idx+1 == onPopupIdx) {
						$(this).css('display', 'block');
					}
				});
			} else {
				if(onPopupIdx == target.length ) {
					onPopupIdx = 1;
				} else {
					onPopupIdx = onPopupIdx+1;
				}
				target.css('display', 'none');
				target.each(function(idx) {
					if(idx+1 == onPopupIdx) {
						$(this).css('display', 'block');
					}
				});
			}
		} catch(e) {
			alert("[/js/main/main.js's Main.popupMove] " + e.description);
		}
	}
};










/*************************************************************
  서브 LNB 관련 스크립트
 **************************************************************/

$(function() {
	$('#lnb dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();

	});
});


var lnbTab1 = function(){
	$(".course-tab-1").addClass('on');
	$(".course-tab-2").removeClass('on');

	$(".course-menu-1").css('display','block');
	$(".course-menu-2").css('display','none');
};
var lnbTab2 = function(){
	$(".course-tab-2").addClass('on');
	$(".course-tab-1").removeClass('on');

	$(".course-menu-2").css('display','block');
	$(".course-menu-1").css('display','none');
};










/*************************************************************
  서브 주요기관 사이트 관련 스크립트
 **************************************************************/

$(function() {
	$('#site-info .dropdown-toggle').click(function(){
		$(this).parent().siblings().find('div').slideUp();
		$(this).siblings('div').slideToggle();
	});
});










/*************************************************************
  Tab 관련 스크립트
 **************************************************************/

var showTabbtn1 = function(){
	$(".tab-name-11").addClass('on');
	$(".tab-name-12").removeClass('on');
	$(".tab-name-13").removeClass('on');
	$(".tab-name-14").removeClass('on');
	$(".tab-name-15").removeClass('on');

	$("#tab-content-12").css('display','none');
	$("#tab-content-13").css('display','none');
	$("#tab-content-14").css('display','none');
	$("#tab-content-15").css('display','none');
	$("#tab-content-11").css('display','block');
};
var showTabbtn2 = function(){
	$(".tab-name-12").addClass('on');
	$(".tab-name-11").removeClass('on');
	$(".tab-name-13").removeClass('on');
	$(".tab-name-14").removeClass('on');
	$(".tab-name-15").removeClass('on');

	$("#tab-content-11").css('display','none');
	$("#tab-content-13").css('display','none');
	$("#tab-content-14").css('display','none');
	$("#tab-content-15").css('display','none');
	$("#tab-content-12").css('display','block');
};
var showTabbtn3 = function(){
	$(".tab-name-13").addClass('on');
	$(".tab-name-11").removeClass('on');
	$(".tab-name-12").removeClass('on');
	$(".tab-name-14").removeClass('on');
	$(".tab-name-15").removeClass('on');

	$("#tab-content-11").css('display','none');
	$("#tab-content-12").css('display','none');
	$("#tab-content-14").css('display','none');
	$("#tab-content-15").css('display','none');
	$("#tab-content-13").css('display','block');
};
var showTabbtn4 = function(){
	$(".tab-name-14").addClass('on');
	$(".tab-name-11").removeClass('on');
	$(".tab-name-12").removeClass('on');
	$(".tab-name-13").removeClass('on');
	$(".tab-name-15").removeClass('on');

	$("#tab-content-11").css('display','none');
	$("#tab-content-12").css('display','none');
	$("#tab-content-13").css('display','none');
	$("#tab-content-15").css('display','none');
	$("#tab-content-14").css('display','block');
};
var showTabbtn5 = function(){
	$(".tab-name-15").addClass('on');
	$(".tab-name-11").removeClass('on');
	$(".tab-name-12").removeClass('on');
	$(".tab-name-13").removeClass('on');
	$(".tab-name-14").removeClass('on');

	$("#tab-content-11").css('display','none');
	$("#tab-content-12").css('display','none');
	$("#tab-content-13").css('display','none');
	$("#tab-content-14").css('display','none');
	$("#tab-content-15").css('display','block');
};










/*************************************************************
  Toggle 관련 스크립트
 **************************************************************/

$(function() {
	$('.learner-training dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();

		$(this).addClass('current');
		$(this).parent().siblings().find('dt').removeClass('current');

	});
});










/*************************************************************
  피드백 입력/풀다운 관련 스크립트
 **************************************************************/

jQuery(function($){
	var login = $('.btn-area');
	var uid = $('.fText.feedcontent');
	
	// Input Clear
	var iText = $('.feedback>.feedLabel').next('.fText');
	$('.feedback>.feedLabel').css('position','absolute');
	iText
		.focus(function(){
			$(this).prev('.feedLabel').css('visibility','hidden');
		})
		.blur(function(){
			if($(this).val() == ''){
				$(this).prev('.feedLabel').css('visibility','visible');
			} else {
				$(this).prev('.feedLabel').css('visibility','hidden');
			}
		})
		.change(function(){
			if($(this).val() == ''){
				$(this).prev('.feedLabel').css('visibility','visible');
			} else {
				$(this).prev('.feedLabel').css('visibility','hidden');
			}
		})
		.blur();
});

$(function() {
	$('.feedback .dropdown-toggle').click(function(){
		$(this).parent().siblings().find('p').slideUp();
		$(this).siblings('p').slideToggle();
	});
});










/*************************************************************
  학습창 관련 스크립트
 **************************************************************/

$(function() {
	$("#index-open").click(function () {
		$("#index").addClass("open"); 
		$("#index-open").css('display','none'); 
		window.location.hash = "#open"; 
	});

	$('#index-close').click(function(){
		$("#index").removeClass("open"); 
		$("#index-open").css('display','block'); 
	});
});



var showList1 = function(){
	$(".view-btn1").css('display','none');
	$(".view-btn2").css('display','block');
	$(".view-list").css('display','block');
};

var closeList1 = function(){
	$(".view-btn1").css('display','block');
	$(".view-btn2").css('display','none');
	$(".view-list").css('display','none');
};


var showList = function(index){
	$("#view-btn"+index).css('display','none');
	$("#view-btn"+index).css('display','block');
	$("#view-list"+index).css('display','block');
};

var closeList = function(index){
	$("#view-btn"+index).css('display','block');
	$("#view-btn"+index).css('display','none');
	$("#view-list"+index).css('display','none');
};

