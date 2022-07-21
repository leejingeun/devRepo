// JavaScript Document

var tabListName = '.name-tab-btn li';
var tabContentName = '.name-tab-content';
var tabListNames = '.name-tab-btns li';
var tabContentNames = '.name-tab-contents';
/* Header 영역 */
$(function() {
	UiTab.init();
	UiTabs.init();
	/*
	// get 파라메터 사용하여, 기본선택 탭 지정코드 추가.
	var selectTab = getQuerystring("selectTab");
	if( !!selectTab ){
		
		var tabList = $(tabListName);
		var tabContent = $(tabContentName);
		
		$this = $($("."+selectTab)[0]);
		tabList.removeClass('current');
		tabContent.css('display', 'none');
		
		var selectIndex = tabList.index($this);
		tabContent.eq(selectIndex).css('display', 'block');
		tabList.eq(selectIndex).addClass('current');
	}
	*/
});

var UiTab = {
				
	init : function() {
		var tabList = $(tabListName);
		var tabContent = $(tabContentName);
		if ( tabList.length == 0 ) { return false; }
		
		tabContent.css('display', 'none');
		tabContent.first().css('display', 'block');
	//학습활동서 담당교수 OJT조회처리 임시 수정	
		//tabList.first().addClass('current');
		
		tabList.click(UiTab.tabAction);
			
	},
	
	tabAction : function() {
		
		var tabList = $(tabListName);
		var tabContent = $(tabContentName);
		
		$this = $(this);
		
		tabList.removeClass('current');
		tabContent.css('display', 'none');
		
		var selectIndex = tabList.index($this);
		tabContent.eq(selectIndex).css('display', 'block');
		
		$this.addClass('current');
		
		return false;
	}
};



var UiTabs = {
				
	init : function() {
		var tabList = $(tabListNames);
		var tabContent = $(tabContentNames);
		if ( tabList.length == 0 ) { return false; }
		
		tabContent.css('display', 'none');
		tabContent.first().css('display', 'block');
	//학습활동서 담당교수 OJT조회처리 임시 수정	
	//tabList.first().addClass('current');
		
		tabList.click(UiTab.tabAction);
			
	},
	
	tabAction : function() {
		
		var tabList = $(tabListNames);
		var tabContent = $(tabContentNames);
		
		$this = $(this);
		
		tabList.removeClass('current');
		tabContent.css('display', 'none');
		
		var selectIndex = tabList.index($this);
		tabContent.eq(selectIndex).css('display', 'block');
		
		$this.addClass('current');
		
		return false;
	}
};

