
/*#######################  로그인 관련항목  ######################*/

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







/*#######################  하위메뉴 슬라이딩 관련항목  ######################*/

$(function() {
	$('#menu-area dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();

		$(this).addClass('on');
		$(this).parent().siblings().find('dt').removeClass('on');

	});
});





/*#######################  검색조건 슬라이딩 관련항목  ######################*/

var showCategory = function(){
	$("#hidden-line").slideToggle();
	$(".search-btn-1").fadeOut(750);
};
var closeCategory = function(){
	$("#hidden-line").slideToggle();
	$(".search-btn-1").fadeIn(750);
};







 /*#######################  메인 게시판 탭버튼 관련항목  ######################*/

var showNotice = function(){
	$(".tab-name-11").addClass('on');
	$(".tab-name-12").removeClass('on');

	$("#tab-content-12").css('display','none');
	$("#tab-content-11").css('display','block');
};
var showEvent = function(){
	$(".tab-name-12").addClass('on');
	$(".tab-name-11").removeClass('on');

	$("#tab-content-11").css('display','none');
	$("#tab-content-12").css('display','block');
};






var showDaily = function(){
	$(".tab-name-21").addClass('on');
	$(".tab-name-22").removeClass('on');

	$("#tab-content-22").css('display','none');
	$("#tab-content-21").css('display','block');
};
var showMonthly = function(){
	$(".tab-name-22").addClass('on');
	$(".tab-name-21").removeClass('on');

	$("#tab-content-21").css('display','none');
	$("#tab-content-22").css('display','block');
};






var showQna = function(){
	$(".tab-name-31").addClass('on');
	$(".tab-name-32").removeClass('on');
	$(".tab-name-33").removeClass('on');
	$(".tab-name-34").removeClass('on');
	$(".tab-name-35").removeClass('on');
	$(".tab-name-36").removeClass('on');

	$("#tab-content-31").css('display','block');
	$("#tab-content-32").css('display','none');
	$("#tab-content-33").css('display','none');
	$("#tab-content-34").css('display','none');
	$("#tab-content-35").css('display','none');
	$("#tab-content-36").css('display','none');
};

var showPurchase = function(){
	$(".tab-name-31").removeClass('on');
	$(".tab-name-32").addClass('on');
	$(".tab-name-33").removeClass('on');
	$(".tab-name-34").removeClass('on');
	$(".tab-name-35").removeClass('on');
	$(".tab-name-36").removeClass('on');

	$("#tab-content-31").css('display','none');
	$("#tab-content-32").css('display','block');
	$("#tab-content-33").css('display','none');
	$("#tab-content-34").css('display','none');
	$("#tab-content-35").css('display','none');
	$("#tab-content-36").css('display','none');
};

var showPayment = function(){
	$(".tab-name-31").removeClass('on');
	$(".tab-name-32").removeClass('on');
	$(".tab-name-33").addClass('on');
	$(".tab-name-34").removeClass('on');
	$(".tab-name-35").removeClass('on');
	$(".tab-name-36").removeClass('on');

	$("#tab-content-31").css('display','none');
	$("#tab-content-32").css('display','none');
	$("#tab-content-33").css('display','block');
	$("#tab-content-34").css('display','none');
	$("#tab-content-35").css('display','none');
	$("#tab-content-36").css('display','none');
};

var showSystem = function(){
	$(".tab-name-31").removeClass('on');
	$(".tab-name-32").removeClass('on');
	$(".tab-name-33").removeClass('on');
	$(".tab-name-34").addClass('on');
	$(".tab-name-35").removeClass('on');
	$(".tab-name-36").removeClass('on');

	$("#tab-content-31").css('display','none');
	$("#tab-content-32").css('display','none');
	$("#tab-content-33").css('display','none');
	$("#tab-content-34").css('display','block');
	$("#tab-content-35").css('display','none');
	$("#tab-content-36").css('display','none');
};

var showEtc = function(){
	$(".tab-name-31").removeClass('on');
	$(".tab-name-32").removeClass('on');
	$(".tab-name-33").removeClass('on');
	$(".tab-name-34").removeClass('on');
	$(".tab-name-35").addClass('on');
	$(".tab-name-36").removeClass('on');

	$("#tab-content-31").css('display','none');
	$("#tab-content-32").css('display','none');
	$("#tab-content-33").css('display','none');
	$("#tab-content-34").css('display','none');
	$("#tab-content-35").css('display','block');
	$("#tab-content-36").css('display','none');
};

var showRequest = function(){
	$(".tab-name-31").removeClass('on');
	$(".tab-name-32").removeClass('on');
	$(".tab-name-33").removeClass('on');
	$(".tab-name-34").removeClass('on');
	$(".tab-name-35").removeClass('on');
	$(".tab-name-36").addClass('on');

	$("#tab-content-31").css('display','none');
	$("#tab-content-32").css('display','none');
	$("#tab-content-33").css('display','none');
	$("#tab-content-34").css('display','none');
	$("#tab-content-35").css('display','none');
	$("#tab-content-36").css('display','block');
};






