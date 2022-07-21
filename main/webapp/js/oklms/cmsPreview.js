//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];

$(document).on("click","#preview-index dt",function(){ 	
	$(this).parent().siblings().find('dd').slideUp();
	$(this).siblings('dd').slideToggle();
});
$(document).on("click","#preview-index dd a",function(){ 
	$(this).addClass('current');
	$(this).siblings().removeClass('current');
});



/* 컨텐츠 미리보기 레이어팝업 open */
function fn_showPreviewPop(param1,lesson_id){
	self.moveTo(0,0); //창위치
    self.resizeTo(screen.availWidth, screen.availHeight); //창크기
		$("#id").val(param1);
 	var reqUrl = CONTEXT_ROOT + "/lu/online/listOnlineTraning.json";
 	var param = $("#frmPop").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
			courseContentItemList(jqXHR.responseJSON.retMsg, lesson_id);
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
	showPreviewPop();
}

//컨텐츠 목록 가져오기
function courseContentItemList(data, lesson_id){
	var jsonData = JSON.parse(data);
	var firstLessonId = "";
	var firstListId = "";
	$("#title-index").html("");
	
	for (var i = 0; i < jsonData.body.list.length; i++) {
	    var list = jsonData.body.list[i];
	    var titleId = "title_"+list.id;
		$("#title-index").append("<dl id='"+list.id+"'>");
	    $("#"+list.id).append("<dt id="+titleId+"><span><b>"+(i+1)+"</b> 차시</span>"+list.title+"</dt>");
	    
	    for (var j = 0; j < list.lesson.item_list.length; j++) {
	    	var item_list = list.lesson.item_list[j];
	    
	    	if(j == 0){
	    		if(i==0){
	    			firstLessonId = item_list.lesson_id;
			    	firstListId = item_list.id;
	    			
	    			$("#"+list.id).append("<dd class='on' id='"+item_list.lesson_id+"'></dd>");
	    			$("#"+item_list.lesson_id).append("<a  id='"+item_list.id+"' href='javascript:goViewLesson(\""+item_list.lesson_id+"\",\""+item_list.id+"\");'>"+item_list.title+"</a>");
	    			//class='current'
	    		}else{
	    			$("#"+list.id).append("<dd id='"+item_list.lesson_id+"'></dd>");
	    			$("#"+item_list.lesson_id).append("<a id='"+item_list.id+"' href='javascript:goViewLesson(\""+item_list.lesson_id+"\",\""+item_list.id+"\");'>"+item_list.title+"</a>");
	    		}
	    		
	    		if(lesson_id  == item_list.lesson_id) {
	    			firstLessonId = item_list.lesson_id;
			    	firstListId = item_list.id;
			    	$("#"+titleId).parent().siblings().find('dd').slideUp();
					$("#"+titleId).siblings('dd').slideToggle();
					//$("#"+titleId).focus();
					//scrollLink(titleId);
	    		}
	    		
	    	}else{
	    		$("#"+item_list.lesson_id).append("<a id='"+item_list.id+"' href='javascript:goViewLesson(\""+item_list.lesson_id+"\",\""+item_list.id+"\");'>"+item_list.title+"</a>");	
	    	}
	    	
	    	for (var z = 0; z < item_list.subitem_list.length; z++) {
	    		var subitem_list = item_list.subitem_list[z];
	    		contentArray.push({lessonId: item_list.lesson_id, lessonItemId: subitem_list.lesson_item_id,subitemListId:subitem_list.id});
	    		totPage++;	
	    	}
	    }
	}
	
	
	goViewLesson(firstLessonId,firstListId);
//		console.log(contentArray);
	setPage();
}

//컨텐츠 목록에서 위치 서칭 처리
function contentArrayPositionSearch(vlessonId,vlessonItemId){
	var pos=0;
	for (i = 0; i < contentArray.length; i++) {
	       if (contentArray[i].lessonId == vlessonId && contentArray[i].lessonItemId == vlessonItemId) {
	    	   //alert('position: ' + i);
	    	   pos=i;
	           break;
	       }
	}	
	return pos;
}

//컨텐츠 이동
function goViewLesson(vlessonId,vlessonItemId){
	var contentArrayIndex = contentArrayPositionSearch(vlessonId,vlessonItemId);
	var tagVal = vlessonItemId;
	
	//paging 변경
	curPage = contentArrayIndex+1;
	setPage();
	
	$("#"+tagVal).addClass('current');
	$("#"+tagVal).siblings().removeClass('current');
	
	viewLesson(vlessonId,vlessonItemId,contentArray[contentArrayIndex].subitemListId);
}

//컨텐츠 내용 조회
function viewLesson(vlessonId,vlessonItemId,vlessonSubItemId){
	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val(vlessonItemId);
	$("#lessonSubItemId").val(vlessonSubItemId);
	
 	var reqUrl = CONTEXT_ROOT + "/lu/online/onlineNote.json";
 	var param = $("#frmPop").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
 		//com.alert( jqXHR.responseJSON.retMsg );
		setViewLesson(jqXHR.responseJSON.retMsg);
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
}

//컨텐츠 동영상/플래쉬 변경처리
function setViewLesson(data){
	
	var jsonData = JSON.parse(data);
	var contentUrl = jsonData.result.contentUrl;
	var movieUrl = jsonData.result.contentUrl;
	var width = parseInt(jsonData.result.width,10);
	var height = parseInt(jsonData.result.height,10);
	
	if (contentUrl.indexOf('Contents') != -1 || contentUrl.indexOf('Sources/Contents') != -1) {
	      contentUrl = 'http://cdn.e-koreatech.ac.kr/Portal/'+contentUrl;
	} else {
	      contentUrl = 'http://cdn.e-koreatech.ac.kr/Portal/Sources/Contents/'+contentUrl;
	}
	
	// 확장자 체크
	var fileExt =  getExtensionOfFilename(contentUrl);
	console.log("fileExt : "+fileExt);
	console.log("contentUrl : "+contentUrl);
	//console.log("movieUrl : "+movieUrl);
	if(contentUrl != ""){
		$(".wait-img").attr("style", "display:none;");
		$("#contentFrame").attr("width",width+"px");
   		$("#contentFrame").attr("height",height+"px");
   		$("#title-index").attr("height",(height-15)+"px");
		// 동영상일 경우
		if(fileExt == ".mp4"){
			// cdn 경로가 있을경우 추가
	/*		if(contentUrl.indexOf('cdn') != -1) {
				movieUrl = "http://cdn.e-koreatech.ac.kr/Portal"+movieUrl;
			} else {
				movieUrl = contentUrl;
			}
			
			console.log("------------ movieUrl : "+movieUrl);*/
			$("#contentFrame").attr("src","/lu/online/getOnlineTraningMovieViewer.do?movieUrl="+movieUrl+"&width="+width+"&height="+height);
		} else { // 아닐경우
	   		$("#contentFrame").attr("src",contentUrl);
		}
 	
	}else{
		alert("컨텐츠 URL를 찾지 못했습니다.");
	}
}

function openMenu(tagVal){
	$("#"+tagVal).parent().parent().siblings().find('a').removeClass('current');
	$("#"+tagVal).parent().parent().siblings().find('dd').removeClass('on');
	$("#"+tagVal).parent().addClass('on');
}

//Next/Prev 으로 컨텐츠 화면 컨트롤
function setNPMenu(vCurPage){
	var pos = vCurPage-1;
	var tagVal = contentArray[pos].lessonItemId;
	$("#"+tagVal).addClass('current');
	$("#"+tagVal).siblings().removeClass('current');
	//메뉴접기
	$("#"+tagVal).parent().parent().siblings().find('a').removeClass('current');
	$("#"+tagVal).parent().parent().siblings().find('dd').removeClass('on');
	$("#"+tagVal).parent().addClass('on');

	viewLesson(contentArray[pos].lessonId,contentArray[pos].lessonItemId,contentArray[pos].subitemListId);
	
}


//Next 처리
function nextView(){
	if(curPage != totPage){
		curPage++;
		setNPMenu(curPage,"next");
		setPage();
	}else{
		alert("마지막 강의입니다.");
	}
}

//Prev 처리
function prevView(){
	if(curPage != 1){
		curPage--;
		setNPMenu(curPage,"prev");
		setPage();
	}else{
		alert("첫 강의입니다.");
	}
}

//컨텐츠 paging 처리
function setPage(){
	$("#contentPage").html("<b>"+curPage+"</b>"+"/"+totPage);
}

/*************************************************************
  학습창 슬라이딩/목차 관련 스크립트
 **************************************************************/
var showPreviewPop = function(){
	$(".preview-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hidePreviewPop = function(){
	$(".preview-area,.popup-bg").removeClass("open"); 
	curPage=1;
	totPage=0;
	contentArray = [];
	$("#contentFrame").attr("src","#");
	$(".wait-img").attr("style", "display:block;");
};
