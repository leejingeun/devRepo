
/*#######################  GNB 메뉴 슬라이딩 관련 항목  ######################*/

$(function() {
	$("#header .gnb-btn-1").click(function () {
		$("#gnb, .page-cover").addClass("open"); 
		window.location.hash = "#open"; 
	});

	$("#header .gnb-btn-2").click(function () {
		$("#gnb, .page-cover").addClass("open"); 
		window.location.hash = "#open"; 
	});

	$('#header .gnb-close').click(function(){
		$("#gnb, .page-cover").removeClass("open"); 
	});
});





/*#######################  로그인 관련 항목  ######################*/

jQuery(function($){
	var login = $('#login-area');
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
	$('#login-area>.gLogin input[type=submit]').click(function(){
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





/*#######################  학습창 관련 항목  ######################*/

$(function() {
	$('.index-list dd div').click(function(){
		$(this).parent().siblings().find('ul').slideUp();
		$(this).siblings('ul').slideToggle();
	});

	$('.index-list dd').click(function(){
		$(this).addClass('highlight');
		$(this).siblings().removeClass('highlight');
	});

	$('.index-list dd li').click(function(){
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
	});
});











