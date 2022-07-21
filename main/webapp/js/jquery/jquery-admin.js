
/*************************************************************
  서브 LNB 관련 스크립트
 **************************************************************/

$(function() {
	$('#lnb dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();

		$(this).addClass('on');
		$(this).parent().siblings().find('dt').removeClass('on');

	});

	$('#lnb .depth-2 dd a').click(function(){
		$(this).addClass('on');
		$(this).siblings('a').removeClass('on');

	});
});