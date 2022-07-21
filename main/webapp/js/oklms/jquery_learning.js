/*************************************************************
  학습창 슬라이딩/목차 관련 스크립트
 **************************************************************/

var showLearningpop = function(){
	$(".learning-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop = function(){
	$(".learning-area,.popup-bg").removeClass("open"); 
};

$(function() {
	$('.list-zone dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();
	});

	$('.list-zone dd a').click(function(){
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
	});
});
