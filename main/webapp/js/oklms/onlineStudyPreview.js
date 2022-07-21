//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];


// 30초 마다 학습시간 업데이트
var interValTime = 30;
var upateInterval;
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
	$(this).parent().parent().siblings().find('li').removeClass('current');
});



function goWeekLessonPreview(weekId, weekCnt, contentName){
	$("#weekId").val(weekId);
	$("#weekCnt").val(weekCnt);
	$("#weekContentName").text(contentName);
	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcPreviewWeekSchedule.json";
	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		//com.alert( jqXHR.responseJSON.retMsg );
		var retData 	= jqXHR.responseJSON.retData;
		addTree(  retData );
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
	var attendProgress =  $("#attendProgress").val();
	var cutProgress 		=  $("#cutProgress").val();
	var row = 0;
	var weekMark = "";
	
	$("#index-schedule").append("<dt><span><b>"+weekCnt+"</b> 주차</span> <b id='b_"+weekId+"'>미리보기</b> </dt>");
	
	for (var i = 0; i < data.length; i++) {
		var list = data[i];
		var firstClass = "";
		var weekSchId = weekId+"_"+list.subjSchId;
			
		if( i == 0) {
			firstClass = "highlight";
			row = data.length;
		}
	
	/*     if(i == 0) {
			treeStr+="	<td rowspan='"+row+"'>X</td>";
			treeStr+="	<td rowspan='"+row+"'>"+weekCnt+"</td>";
		} */  
		$("#index-schedule").append("<dd id='high_"+list.subjStep+"' class='"+firstClass+"'><div><a href='#!'><b>["+list.subjStep+"차시]</b> "+list.subjTitle+"</a></div><ul class='' id='"+weekSchId+"'></dd>");
	  	
	   
	   if(list.linkContentYn == "Y") {// CMS 와 연계
			var cmsData = cmsLessonList(weekId, list.subjSchId, list.cmsLessonId, weekSchId);
			for (var x = 0; x < cmsData.length; x++) {
				var cmsList = cmsData[x];
				var cmsPageNo = (x+1);
				var spanId = list.subjSchId+"_"+cmsList.lesson_item_id;
				
				//var cmsData = cmsLessonList(stdLessonId, weekId, list.subjSchId, list.cmsLessonId, weekSchId);
				
				if(cmsList.display_order == "1") {
					
					$("#"+weekSchId).append("<li id='"+weekSchId+"_"+totPage+"'><a href='javascript:goViewLesson(\""+weekId+"\",\""+weekCnt+"\",\""+list.subjSchId+"\",\""+list.linkContentYn+"\",\""+list.cmsLessonId+"\",\""+cmsList.lesson_item_id+"\",\""+cmsList.lesson_sub_item+"\",\""+list.contentType+"\",\""+list.contentsDir+"\",\""+list.contentsIdxFile+"\",\""+list.url+"\",\""+cmsPageNo+"\");'><span>○</span>"+cmsList.title+"</a></li>");
				}	
				contentArray.push({weekId : weekId, weekCnt : weekCnt, subjSchId : list.subjSchId, subjStep : list.subjStep , subTitle : list.subjTitle, linkContentYn : list.linkContentYn, lessonId: list.cmsLessonId, lessonItemId: cmsList.lesson_item_id, subitemListId : cmsList.lesson_sub_item, contentType : list.contentType, contentsDir : list.contentsDir, contentsIdxFile : list.contentsIdxFile, url : list.url, viewLessonId : weekSchId+"_"+totPage, pageInfo : cmsPageNo , spanId : spanId});
				totPage++;	
			}
		} else {
			var pageNo = (i+1);
			var spanId = list.subjSchId+"_"+i;
			
			$("#"+weekSchId).append("<li id='"+weekSchId+"_"+totPage+"'><a href='javascript:goViewLesson(\""+weekId+"\",\""+weekCnt+"\",\""+list.subjSchId+"\",\""+list.linkContentYn+"\",\"\",\"\",\"\",\""+list.contentType+"\",\""+list.contentsDir+"\",\""+list.contentsIdxFile+"\",\""+list.url+"\",\""+pageNo+"\");'><span>○</span>"+list.subjTitle+"</a></li>");
			contentArray.push({weekId : weekId, weekCnt : weekCnt, subjSchId : list.subjSchId, subjStep : list.subjStep , subTitle : list.subjTitle, linkContentYn : list.linkContentYn, lessonId: "", lessonItemId: "", subitemListId : "", contentType : list.contentType, contentsDir : list.contentsDir, contentsIdxFile : list.contentsIdxFile, url : list.url, viewLessonId : weekSchId+"_"+totPage, pageInfo : pageNo, spanId : spanId });
			totPage++;	
		}
	   
	   setPage();
	} 
	startView();
	$(".wait-img").attr("style", "display:none;");
	
}

//컨텐츠 내용 조회
function cmsLessonList(weekId, subjSchId, vlessonId, addId){
	$("#subjSchId").val(subjSchId);
	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val("");
	$("#lessonSubitemId").val("");
	var retData = "";
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcPreviewLesson.json";
 	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		retData 	= jqXHR.responseJSON.retData;
	}
	}, {
		async : false,
		type : "POST",
		errorCallback : null
	});
	return retData;
}


//컨텐츠 이동
function goViewLesson(vWeekId,vWeekCnt,vSubjSchId,vLinkContentYn,vlessonId,vlessonItemId,vlessonSubItem,vContentType,vContentsDir,vContentsIdxFile,vUrl ,vPageInfo, spanId){
	
	var contentArrayIndex = 0;
	// CMS 연계 콘텐츠면 CMS에서 포지션을 찾음
	if(vLinkContentYn == "Y"){
		contentArrayIndex = contentArrayPositionSearch(vlessonId,vlessonItemId);
	} else {
		contentArrayIndex = movieContentArrayPositionSearch(vWeekId,vSubjSchId);
		
	}
	
	var tagVal = vlessonItemId;
	
	//paging 변경
	curPage = contentArrayIndex+1;
	setPage();
	
	$("#"+tagVal).addClass('current');
	$("#"+tagVal).siblings().removeClass('current');
	
	if(vLinkContentYn == "Y"){
		viewLesson(vWeekId,vWeekCnt,vSubjSchId,vlessonId,vlessonItemId,contentArray[contentArrayIndex].subitemListId,vPageInfo,contentArray[contentArrayIndex].viewLessonId, contentArray[contentArrayIndex].spanId);
	} else {
		movieLesson(vWeekId, vWeekCnt, vSubjSchId, vContentType, vContentsDir, vContentsIdxFile, vUrl, vPageInfo, contentArray[contentArrayIndex].viewLessonId, contentArray[contentArrayIndex].spanId);
	}
	
	$("#week-schedule-title").html("[ "+contentArray[contentArrayIndex].weekCnt+"주차 ] "+contentArray[contentArrayIndex].subjStep+"차시&nbsp;&nbsp;-&nbsp;&nbsp;"+contentArray[contentArrayIndex].subTitle+"");
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
function movieContentArrayPositionSearch(vWeekId,vSubjSchId){
	var pos=0;
	for (i = 0; i < contentArray.length; i++) {
       if (contentArray[i].weekId == vWeekId && contentArray[i].subjSchId == vSubjSchId) {
    	   pos=i;
           break;
       }
	}	
	return pos;
}



//컨텐츠 내용 조회
function viewLesson(vWeekId,vWeekCnt,vSubjSchId, vlessonId,vlessonItemId,vlessonSubItemId, vPageInfo, viewLessonId, spanId){
	
	$("#weekId").val(vWeekId);
	$("#subjSchId").val(vSubjSchId);
	$("#cmsLessonId").val(vlessonId);
	$("#cmsLessonItemId").val(vlessonItemId);
	$("#cmsLessonSubItem").val(vlessonSubItemId);
	$("#pageInfo").val(vPageInfo);
	$("#linkContentYn").val("Y");
	$("#viewLessonId").val(viewLessonId);
	$("#spanId").val(spanId);
	
	//alert("stdLessonId : "+$("#stdLessonId").val());
	//alert("weekId : "+$("#weekId").val());
	//alert("subjSchId : "+$("#subjSchId").val());
	//alert("cmsLessonId : "+$("#cmsLessonId").val());
	//alert("cmsLessonItemId : "+$("#cmsLessonItemId").val());
	//alert("cmsLessonSubItem : "+$("#cmsLessonSubItem").val());
	//alert("pageInfo : "+$("#pageInfo").val());
	//alert("linkContentYn : "+$("#linkContentYn").val());

	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val(vlessonItemId);
	$("#lessonSubItemId").val(vlessonSubItemId);
	
	
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcPreviewNote.json";
 	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
 		//com.alert( jqXHR.responseJSON.retMsg );
		setViewLesson(vWeekId,vWeekCnt,vSubjSchId, jqXHR.responseJSON.retMsg);
		
		//alert( jqXHR.responseJSON.progressAttendId );
		//alert( jqXHR.responseJSON.progressTimeId );
		
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
}


function movieLesson(vWeekId, vWeekCnt, vSubjSchId, contentType, contentsDir, contentsIdxFile, url, vPageInfo, viewLessonId , spanId){
	
	$("#weekId").val(vWeekId);
	$("#subjSchId").val(vSubjSchId);
	$("#pageInfo").val(vPageInfo);
	$("#linkContentYn").val("N");
	$("#viewLessonId").val(viewLessonId);
	$("#spanId").val(spanId);
	
	var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcPreviewNote.json";
 	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
 		//com.alert( jqXHR.responseJSON.retMsg );
		
		$("#progressAttendId").val( jqXHR.responseJSON.progressAttendId );
		$("#progressTimeId").val( jqXHR.responseJSON.progressTimeId );
		$(".wait-img").attr("style", "display:none;");
		$("#contentFrame").attr("width","1000px");
		$("#contentFrame").attr("height","660px");
		$("#index").attr("height",(660+100)+"px");
		$("#index-list").attr("height",(660-100)+"px");
		//learningPopSort();
		if(contentType == "MP4"){
			var movieUrl = contentsDir+"/"+contentsIdxFile;
			$("#contentFrame").attr("src","/lu/currproc/getCurrProcMovieViewer.do?movieUrl="+movieUrl+"&width=1000&height=660");
		} else { // 아닐경우
	   		$("#contentFrame").attr("src",url);
		}
		

	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
	
}


//컨텐츠 paging 처리
function setPage(){
	$("#contentPage").html("<b>"+curPage+"</b>"+"/"+totPage);
}


//컨텐츠 동영상/플래쉬 변경처리
function setViewLesson(vWeekId,vWeekCnt,vSubjSchId,data){
	
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
   		//learningPopSort();
		// 동영상일 경우
		if(fileExt == ".mp4"){
			$("#contentFrame").attr("src","/lu/currproc/getCurrProcMovieViewer.do?movieUrl="+contentUrl+"&width="+width+"&height="+height);
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
	var tagVal = contentArray[pos].viewLessonId;
	var tagSubjStep = contentArray[pos].subjStep;
	var linkContentYn = contentArray[pos].linkContentYn;
	
	
	$("#"+tagVal).addClass('current');
	$("#"+tagVal).siblings().removeClass('current');
	//메뉴접기
	$("#"+tagVal).parent().parent().siblings().find('li').removeClass('current');
	
	$("#"+tagVal).parent().parent().siblings().find('ul').removeClass('on');
	$("#"+tagVal).parent().addClass('on');
	
	//$("#"+tagVal).parent().parent().siblings().find('ul').slideUp();
	//$("#"+tagVal).parent().slideToggle();
	
	
	//$("#high_"+tagSubjStep).parent().siblings().find('dd').removeClass('highlight');
	//$("#high_"+tagSubjStep).addClass('highlight');
	//alert($("#"+tagVal).parent().parent().parent().parent().find('dd').attr('class'));
	
	//alert($("#"+tagVal).parent().parent().parent().attr('class'));
	
	//goViewLesson(contentArray[pos].stdLessonId, contentArray[pos].weekId, contentArray[pos].weekCnt, contentArray[pos], contentArray[pos].subjSchId, contentArray[pos].linkContentYn, contentArray[pos].lessonId,  contentArray[pos].lessonItemId,  contentArray[pos].subitemListId, contentArray[pos].contentType, contentArray[pos].contentsDir, contentArray[pos].contentsIdxFile, contentArray[pos].url, contentArray[pos].pageInfo);
	
	
	if(linkContentYn == "Y"){
		viewLesson(contentArray[pos].weekId,contentArray[pos].weekCnt,contentArray[pos].subjSchId,contentArray[pos].lessonId,contentArray[pos].lessonItemId,contentArray[pos].subitemListId, contentArray[pos].pageInfo,contentArray[pos].viewLessonId,contentArray[pos].spanId);
	} else {
		movieLesson(contentArray[pos].weekId, contentArray[pos].weekCnt, contentArray[pos].subjSchId, contentArray[pos].contentType, contentArray[pos].contentsDir, contentArray[pos].contentsIdxFile, contentArray[pos].url, contentArray[pos].pageInfo,contentArray[pos].spanId);
	}
	$("#week-schedule-title").html("[ "+contentArray[pos].weekCnt+"주차 ] "+contentArray[pos].subjStep+"차시&nbsp;&nbsp;-&nbsp;&nbsp;"+contentArray[pos].subTitle+"");
}

function startView(){
	var tagVal = contentArray[0].viewLessonId;
	var linkContentYn = contentArray[0].linkContentYn;

	$("#"+tagVal).addClass('current');
	$("#"+tagVal).siblings().removeClass('current');
	//메뉴접기
	$("#"+tagVal).parent().parent().siblings().find('li').removeClass('current');
	$("#"+tagVal).parent().parent().siblings().find('ul').removeClass('on');
	$("#"+tagVal).parent().addClass('on');
	
	if(linkContentYn == "Y"){
		viewLesson(contentArray[0].weekId,contentArray[0].weekCnt,contentArray[0].subjSchId,contentArray[0].lessonId,contentArray[0].lessonItemId,contentArray[0].subitemListId,contentArray[0].pageInfo,contentArray[0].viewLessonId,contentArray[0].spanId);
	} else {
		movieLesson( contentArray[0].weekId, contentArray[0].weekCnt, contentArray[0].subjSchId, contentArray[0].contentType, contentArray[0].contentsDir, contentArray[0].contentsIdxFile, contentArray[0].url, contentArray[0].pageInfo, contentArray[0].viewLessonId,contentArray[0].spanId);
	}
	$("#week-schedule-title").html("[ "+contentArray[0].weekCnt+"주차 ] "+contentArray[0].subjStep+"차시&nbsp;&nbsp;-&nbsp;&nbsp;"+contentArray[0].subTitle+"");
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