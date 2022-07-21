//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];


var showLearningPop = function(){
	$(".learning-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningPop = function(){
	$(".learning-area,.popup-bg").removeClass("open"); 
	curPage=1;
	totPage=0;
	contentArray = [];
	$("#index-schedule").html("");
	$("#contentFrame").attr("src","#");
	$(".wait-img").attr("style", "display:block;");
};


$(document).on("click",".index-list dd div",function(){ 	
	$(this).parent().siblings().find('ul').slideUp();
	$(this).siblings('ul').slideToggle();
});
$(document).on("click",".index-list dd",function(){ 
	$(this).addClass('highlight');
	$(this).siblings().removeClass('highlight');
});
$(document).on("click",".index-list dd li",function(){ 
	$(this).addClass('current');
	$(this).siblings().removeClass('current');
});

function goWeekLesson(weekId, weekCnt, contentName){
	$("#weekId").val(weekId);
	$("#weekCnt").val(weekCnt);
	$("#weekContentName").text(contentName);
	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcStdWeekSchedule.json";
	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		//com.alert( jqXHR.responseJSON.retMsg );
		var retData 	= jqXHR.responseJSON.retData;
		addTree(retData);
	} else {
		alert("차시정보를 읽어오는대 실패했습니다.")
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
	
	showLearningPop();
}

function addTree(data){
	var weekId = $("#weekId").val();
	var weekCnt = $("#weekCnt").val();
	var weekAttendId = "weekAttend"+weekCnt;
	var row = 0;
	
	$("#index-schedule").append("<dt><span><b>"+weekCnt+"</b> 주차</span> <b id='"+weekAttendId+"'>출석 : ○</b></dt>");
	
	for (var i = 0; i < data.length; i++) {
		var list = data[i];
		var firstClass = "";
		
		if( i == 0) {
			firstClass = "highlight";
			row = data.length;
		}
	
	/*     if(i == 0) {
			treeStr+="	<td rowspan='"+row+"'>X</td>";
			treeStr+="	<td rowspan='"+row+"'>"+weekCnt+"</td>";
		} */  
		$("#index-schedule").append("<dd class='"+firstClass+"'><div><a href='#!'><b>["+list.subjStep+"차시]</b> "+list.subjTitle+"</a></div><ul class='on' id='view-list"+i+"'></dd>");
	   // treeStr+="		<a href='javascript:showList(\""+i+"\");' class='view-btn1' id='view-btn"+i+"' style='display:none;' >열기</a><a href='javascript:closeList('\""+i+"\"');' class='view-btn2'  id='view-btn"+i+"'>닫기</a>";
		
	   
	   if(list.linkContentYn == "Y") {// CMS 와 연계
			cmsLessonList(weekId, list.subjSchId, list.cmsLessonId, "view-list"+i);
		} else {
				//$("#view-list"+i).append("<li><a id='"+list.id+"' href='javascript:goViewMovieLesson(\""+vlessonId+"\",\""+list.lesson_item_id+"\",\""+list.lesson_sub_item+"\");'><span>○</span>"+list.title+"</a></li>");
			$("#view-list"+i).append("<li><a id='' href='javascript:goMovieLesson(\""+weekId+"\",\""+weekCnt+"\",\""+list.subjSchId+"\",\""+list.contentType+"\",\""+list.contentsDir+"\",\""+list.contentsIdxFile+"\",\""+list.url+"\");'><span>○</span>"+list.subjTitle+"</a></li>");
			//$("#"+addId).append("<li><a id='"+list.id+"' href='javascript:goViewLesson(\""+vlessonId+"\",\""+list.lesson_item_id+"\",\""+list.lesson_sub_item+"\");'><span>○</span>"+list.title+"</a></li>");	
			contentArray.push({lessonId: "", lessonItemId: "", subitemListId : "", weekId : weekId, subjSchId : list.subjSchId });
			totPage++;	
		}
	   
		setPage();
	} 
	
	$(".wait-img").attr("style", "display:none;");
}


//컨텐츠 내용 조회
function cmsLessonList(weekId, subjSchId, vlessonId, addId){
	//$("#lessonItemId").val(vlessonItemId);
	//$("#lessonSubItemId").val(vlessonSubItemId);
	$("#lessonId").val(vlessonId);
	
	$("#lessonItemId").val("");
	$("#lessonSubitemId").val("");
	
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcStdLesson.json";
 	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		var retData 	= jqXHR.responseJSON.retData;
		//alert("lit vlessonId : "+vlessonId);
		addCmsTree(retData, weekId, subjSchId ,addId, vlessonId);
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
}

//회차정보
function addCmsTree(data, weekId, subjSchId, addId, vlessonId){
	var flessonId = "";
	var flessonItemId = "";
	var fsubitemListId = "";
	
	console.log("==================== data.length : "+data.length);
	
	for (var i = 0; i < data.length; i++) {
		var list = data[i];
		/*if( i==0 && totPage == 0){
			var flessonId = vlessonId;
			var flessonItemId = list.lesson_item_id;
			var fsubitemListId = list.lesson_sub_item;
			
			//alert(flessonId);
			//alert(flessonItemId);
			//alert(fsubitemListId);
		}*/
			
		//console.log("totPage : "+totPage);   
		console.log("vlessonId : "+vlessonId+ " list.lesson_item_id : "+list.lesson_item_id+ " list.lesson_sub_item : "+list.lesson_sub_item);   
		if(list.display_order == "1") $("#"+addId).append("<li><a id='"+list.id+"' href='javascript:goViewLesson(\""+vlessonId+"\",\""+list.lesson_item_id+"\",\""+list.lesson_sub_item+"\");'><span>○</span>"+list.title+"</a></li>");	
		contentArray.push({lessonId: vlessonId, lessonItemId: list.lesson_item_id, subitemListId : list.lesson_sub_item, weekId : weekId, subjSchId : subjSchId});
		totPage++;	
	}
	
	goViewLesson(vlessonId,flessonItemId,fsubitemListId);
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

//컨텐츠 목록에서 위치 서칭 처리
function movieContentArrayPositionSearch(vWeekId,vSubjSchIdId){
	var pos=0;
	for (i = 0; i < contentArray.length; i++) {
       if (contentArray[i].weekId == vWeekId && contentArray[i].subjSchIdId == vSubjSchIdId) {
    	   //alert('position: ' + i);
    	   pos=i;
           break;
       }
	}	
	return pos;
}


//컨텐츠 내용 조회
function viewLesson(vlessonId,vlessonItemId,vlessonSubItemId){
	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val(vlessonItemId);
	$("#lessonSubItemId").val(vlessonSubItemId);
	
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcStdNote.json";
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

function goMovieLesson(weekId, weekCnt, subjSchId, contentType, contentsDir, contentsIdxFile, url){
	
	var contentArrayIndex = movieContentArrayPositionSearch(weekId, subjSchId);
	
	//paging 변경
	curPage = contentArrayIndex+1;
	setPage();
	
	$(".wait-img").attr("style", "display:none;");
	$("#contentFrame").attr("width","1000px");
	$("#contentFrame").attr("height","660px");
	$("#index").attr("height",(660+100)+"px");
	$("#index-list").attr("height",(660-100)+"px");
	if(contentType == "MP4"){
		var movieUrl = contentsDir+"/"+contentsIdxFile;
		$("#contentFrame").attr("src","/lu/currproc/getCurrProcMovieViewer.do?movieUrl="+movieUrl+"&width=1000&height=660");
	} else { // 아닐경우
   		$("#contentFrame").attr("src",url);
	}
}





//컨텐츠 paging 처리
function setPage(){
	$("#contentPage").html("<b>"+curPage+"</b>"+"/"+totPage);
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
   		$("#index").attr("height",(height+100)+"px");
   		$("#index-list").attr("height",(height-100)+"px");
		// 동영상일 경우
		if(fileExt == ".mp4"){
			$("#contentFrame").attr("src","/lu/currproc/getCurrProcMovieViewer.do?movieUrl="+movieUrl+"&width="+width+"&height="+height);
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
