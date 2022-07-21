//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];


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

$(document).on("click","#preview-index dt",function(){ 	
	$(this).parent().siblings().find('dd').slideUp();
	$(this).siblings('dd').slideToggle();
});
$(document).on("click","#preview-index dd a",function(){ 
	$(this).addClass('current');
	$(this).siblings().removeClass('current');
});



function goPreview(linkContentYn, weekCnt, contentName, subjStep, subjTitle, lessonId, contentType, contentsDir, contentsIdxFile, url){
	lessonList(linkContentYn, weekCnt, contentName, subjStep, subjTitle, lessonId, contentType, contentsDir, contentsIdxFile, url);
	showPreviewPop();
	setPage();
}

//컨텐츠 내용 조회
function lessonList(linkContentYn, weekCnt, contentName, subjStep, subjTitle, vlessonId, contentType, contentsDir, contentsIdxFile, url){
	
	if(linkContentYn == "Y"){
		$("#lessonId").val(vlessonId);
		$("#lessonItemId").val("");
		$("#lessonSubitemId").val("");
	 	var reqUrl = CONTEXT_ROOT + "/lu/online/lessonList.json";
	 	var param = $("#frmLesson").serializeArray();
		com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
		if (200 == jqXHR.status ) {
			var retData 	= jqXHR.responseJSON.retData;
			addTitleCmsTree(retData, linkContentYn, weekCnt, contentName, subjStep, subjTitle, vlessonId);
		}
		}, {
			async : true,
			type : "POST",
			errorCallback : null
		});
	} else {
	
		addTitleTree(weekCnt, contentName, subjStep, subjTitle, vlessonId, contentType, contentsDir, contentsIdxFile, url);
	}
}

//회차정보
function addTitleTree(weekCnt, contentName, subjStep, subjTitle, vlessonId, contentType, contentsDir, contentsIdxFile, url){
	var titleStr = "";
	var titleId = "title_"+weekCnt+subjStep;
	if(totPage == 0) $("#title-index").html("");
	//$("#title-index").html("");
	
	titleStr += "<dl>";
	titleStr += "<dt id='title-area'><span><b>"+weekCnt+"</b> 주차</span>"+subjStep+" 차시 "+subjTitle+"</dt>";
	titleStr += "<dd class='on' id='"+titleId+"'>";
	titleStr += "<a  href='#none'>"+subjTitle+"</a>";	
	titleStr += "</dd>";
	titleStr += "</dl>";
	$("#title-index").append(titleStr);
	
	totPage++;	
	setPage();
	
	$(".wait-img").attr("style", "display:none;");
	$("#contentFrame").attr("width","1000px");
	$("#contentFrame").attr("height","660px");
	$("#title-index").attr("height","645px");
	
	if(contentType == "MP4"){
		var movieUrl = contentsDir+"/"+contentsIdxFile;
		$("#contentFrame").attr("src","/lu/online/getOnlineTraningMovieViewer.do?movieUrl="+movieUrl+"&width=1000&height=660");
	} else { // 아닐경우
   		$("#contentFrame").attr("src",url);
	}
	
}


//회차정보
function addTitleCmsTree(data, linkContentYn, weekCnt, contentName, subjStep, subjTitle, vlessonId){
	var flessonId = "";
	var flessonItemId = "";
	var fsubitemListId = "";
	var titleStr = "";
	var titleId = "title_"+weekCnt+subjStep;
	if(totPage == 0) $("#title-index").html("");
	//$("#title-index").html("");
	
	titleStr += "<dl>";
	titleStr += "<dt id='title-area'><span><b>"+weekCnt+"</b> 주차</span>"+subjStep+" 차시 "+subjTitle+"</dt>";
	titleStr += "<dd class='on' id='"+titleId+"'>";
	for (var i = 0; i < data.length; i++) {
		var list = data[i];
		if( i==0 && totPage == 0){
			var flessonId = vlessonId;
			var flessonItemId = list.lesson_item_id;
			var fsubitemListId = list.lesson_sub_item;
		}
			
		//console.log("totPage : "+totPage);   
		console.log("vlessonId : "+vlessonId);
		console.log("list.lesson_item_id : "+list.lesson_item_id);
		console.log("list.lesson_sub_item : "+list.lesson_sub_item);
		console.log("list.title : " +list.title);
		console.log("list.display_order : " +list.display_order);
		
		if(list.display_order == "1") titleStr += "<a  id='"+list.lesson_item_id+"' href='javascript:goViewLesson(\""+vlessonId+"\",\""+list.lesson_item_id+"\",\""+list.lesson_sub_item+"\");'>"+list.title+"</a>";	
		contentArray.push({lessonId: vlessonId, lessonItemId: list.lesson_item_id, subitemListId : list.lesson_sub_item});
		totPage++;	
	}
	
	titleStr += "</dd>";
	titleStr += "</dl>";
	$("#title-index").append(titleStr);
	//$("#title-index").append("</dl>");
	
	goViewLesson(vlessonId,flessonItemId,fsubitemListId);
}


//컨텐츠 paging 처리
function setPage(){
	$("#contentPage").html("<b>"+curPage+"</b>"+"/"+totPage);
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
 	var param = $("#frmLesson").serializeArray();
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
	console.log("movieUrl : "+movieUrl);
	if(contentUrl != ""){
		$(".wait-img").attr("style", "display:none;");
		$("#contentFrame").attr("width",width+"px");
   		$("#contentFrame").attr("height",height+"px");
   		$("#title-index").attr("height",(height-15)+"px");
		// 동영상일 경우
		if(fileExt == ".mp4"){
			$("#contentFrame").attr("src","/lu/online/getOnlineTraningMovieViewer.do?movieUrl="+contentUrl+"&width="+width+"&height="+height);
		} else { // 아닐경우
	   		$("#contentFrame").attr("src",contentUrl);
		}
 	
	}else{
		alert("컨텐츠 URL를 찾지 못했습니다.");
	}
	
	$("#learning-area").focus();
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
