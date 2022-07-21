//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];
var weekProgressYn = "Y";

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
	//  학습시에만 진도율 적용 예습,복습시 미적용
	if(weekProgressYn == 'Y'){
		endAttend();
	}
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



function goWeekLesson(stdLessonId, weekId, weekCnt, contentName, weekProgressRate){
	$("#stdLessonId").val(stdLessonId);
	$("#weekId").val(weekId);
	$("#weekCnt").val(weekCnt);
	$("#weekContentName").text(contentName);
	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcStdWeekSchedule.json";
	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		//com.alert( jqXHR.responseJSON.retMsg );
		var retData 	= jqXHR.responseJSON.retData;
		weekProgressYn = jqXHR.responseJSON.weekProgressYn;
		//console.log("=================== weekProgressYn : "+weekProgressYn);
		addTree( stdLessonId, retData, weekProgressRate );
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

function addTree(stdLessonId,data, weekProgressRate){
	var weekId = $("#weekId").val();
	var weekCnt = $("#weekCnt").val();
	var attendProgress =  $("#attendProgress").val();
	var cutProgress 		=  $("#cutProgress").val();
	var row = 0;
	var weekMark = "";
	
	if(weekProgressRate >= parseInt(attendProgress)){
		weekMark = "○";
	} else if (weekProgressRate <= parseInt(cutProgress)){
		weekMark = "×";
	} else {
		weekMark = "△";
	}
	
	$("#index-schedule").append("<dt><span><b>"+weekCnt+"</b> 주차</span> <b id='b_"+weekId+"'>출석 : "+weekMark+"</b></dt>");
	
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
			var cmsData = cmsLessonList(stdLessonId, weekId, list.subjSchId, list.cmsLessonId, weekSchId);
			for (var x = 0; x < cmsData.length; x++) {
				var cmsList = cmsData[x];
				var cmsPageNo = (x+1);
				var spanId = list.subjSchId+"_"+cmsList.lesson_item_id;
				
				//var cmsData = cmsLessonList(stdLessonId, weekId, list.subjSchId, list.cmsLessonId, weekSchId);
				
				if(cmsList.display_order == "1") {
					var lessonProgress = parseInt(cmsList.lessonProgress);
					var mark = "";
					if( lessonProgress >= 100 ){
		 				mark = "○";
		 			} else {
		 				mark = "×";
		 			}
					
					$("#"+weekSchId).append("<li id='"+weekSchId+"_"+totPage+"'><a href='javascript:goViewLesson(\""+stdLessonId+"\",\""+weekId+"\",\""+weekCnt+"\",\""+list.subjSchId+"\",\""+list.linkContentYn+"\",\""+list.cmsLessonId+"\",\""+cmsList.lesson_item_id+"\",\""+cmsList.lesson_sub_item+"\",\""+list.contentType+"\",\""+list.contentsDir+"\",\""+list.contentsIdxFile+"\",\""+list.url+"\",\""+cmsPageNo+"\");'><span id='"+spanId+"' name='span_name'>"+mark+"</span>"+cmsList.title+"</a></li>");
				}	
				contentArray.push({stdLessonId : stdLessonId, weekId : weekId, weekCnt : weekCnt, subjSchId : list.subjSchId, subjStep : list.subjStep , subTitle : list.subjTitle, linkContentYn : list.linkContentYn, lessonId: list.cmsLessonId, lessonItemId: cmsList.lesson_item_id, subitemListId : cmsList.lesson_sub_item, contentType : list.contentType, contentsDir : list.contentsDir, contentsIdxFile : list.contentsIdxFile, url : list.url, viewLessonId : weekSchId+"_"+totPage, pageInfo : cmsPageNo , spanId : spanId});
				totPage++;	
			}
		} else {
			var pageNo = (i+1);
			var spanId = list.subjSchId+"_"+i;
			
			var lessonProgress = parseInt(list.lessonProgress);
			
			var mark = "";
			if( lessonProgress >= 100 ){
 				mark = "○";
 			} else {
 				mark = "×";
 			}
			
			$("#"+weekSchId).append("<li id='"+weekSchId+"_"+totPage+"'><a href='javascript:goViewLesson(\""+stdLessonId+"\",\""+weekId+"\",\""+weekCnt+"\",\""+list.subjSchId+"\",\""+list.linkContentYn+"\",\"\",\"\",\"\",\""+list.contentType+"\",\""+list.contentsDir+"\",\""+list.contentsIdxFile+"\",\""+list.url+"\",\""+pageNo+"\");'><span  id='"+spanId+"' name='span_name'>"+mark+"</span>"+list.subjTitle+"</a></li>");
			contentArray.push({stdLessonId : stdLessonId, weekId : weekId, weekCnt : weekCnt, subjSchId : list.subjSchId, subjStep : list.subjStep , subTitle : list.subjTitle, linkContentYn : list.linkContentYn, lessonId: "", lessonItemId: "", subitemListId : "", contentType : list.contentType, contentsDir : list.contentsDir, contentsIdxFile : list.contentsIdxFile, url : list.url, viewLessonId : weekSchId+"_"+totPage, pageInfo : pageNo, spanId : spanId });
			totPage++;	
		}
	   
	   setPage();
	} 
	if(weekProgressYn == 'N'){
		alert("에습, 복습시에는 진도율이 적용되지 않습니다.");
	}
	
	startView();
	$(".wait-img").attr("style", "display:none;");
	
}

//컨텐츠 내용 조회
function cmsLessonList(stdLessonId,weekId, subjSchId, vlessonId, addId){
	$("#subjSchId").val(subjSchId);
	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val("");
	$("#lessonSubitemId").val("");
	var retData = "";
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcStdLesson.json";
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

//컨텐츠 내용 조회
function cmsLessonProgress(stdLessonId,weekId, subjSchId, vlessonId, addId){
	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val("");
	$("#lessonSubitemId").val("");
	var retData = "";
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/listCurrProcStdLesson.json";
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
function goViewLesson(vStdLessonId,vWeekId,vWeekCnt,vSubjSchId,vLinkContentYn,vlessonId,vlessonItemId,vlessonSubItem,vContentType,vContentsDir,vContentsIdxFile,vUrl ,vPageInfo, spanId){
	
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
		viewLesson(vStdLessonId,vWeekId,vWeekCnt,vSubjSchId,vlessonId,vlessonItemId,contentArray[contentArrayIndex].subitemListId,vPageInfo,contentArray[contentArrayIndex].viewLessonId, contentArray[contentArrayIndex].spanId);
	} else {
		movieLesson(vStdLessonId, vWeekId, vWeekCnt, vSubjSchId, vContentType, vContentsDir, vContentsIdxFile, vUrl, vPageInfo, contentArray[contentArrayIndex].viewLessonId, contentArray[contentArrayIndex].spanId);
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
function viewLesson(vStdLessonId,vWeekId,vWeekCnt,vSubjSchId, vlessonId,vlessonItemId,vlessonSubItemId, vPageInfo, viewLessonId, spanId){
	
	// 기존콘텐츠 시간업데이트
	//updateAttend();
	
	$("#stdLessonId").val(vStdLessonId);
	$("#weekId").val(vWeekId);
	$("#subjSchId").val(vSubjSchId);
	$("#cmsLessonId").val(vlessonId);
	$("#cmsLessonItemId").val(vlessonItemId);
	$("#cmsLessonSubItem").val(vlessonSubItemId);
	$("#pageInfo").val(vPageInfo);
	$("#linkContentYn").val("Y");
	$("#viewLessonId").val(viewLessonId);
	$("#spanId").val(spanId);
	$("#weekProgressYn").val(weekProgressYn);
	

	$("#lessonId").val(vlessonId);
	$("#lessonItemId").val(vlessonItemId);
	$("#lessonSubItemId").val(vlessonSubItemId);
	
	attendInterVal(false);
	
 	var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcStdNote.json";
 	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
 		//com.alert( jqXHR.responseJSON.retMsg );
		setViewLesson(vStdLessonId,vWeekId,vWeekCnt,vSubjSchId, jqXHR.responseJSON.retMsg);
		
		//alert( jqXHR.responseJSON.progressAttendId );
		//alert( jqXHR.responseJSON.progressTimeId );
		
		$("#progressAttendId").val( jqXHR.responseJSON.progressAttendId );
		$("#progressTimeId").val( jqXHR.responseJSON.progressTimeId );
		
		attendInterVal(true);
		
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
}


function movieLesson(vStdLessonId, vWeekId, vWeekCnt, vSubjSchId, contentType, contentsDir, contentsIdxFile, url, vPageInfo, viewLessonId , spanId){
	 attendInterVal(false);
	
	$("#stdLessonId").val(vStdLessonId);
	$("#weekId").val(vWeekId);
	$("#subjSchId").val(vSubjSchId);
	$("#pageInfo").val(vPageInfo);
	$("#linkContentYn").val("N");
	$("#viewLessonId").val(viewLessonId);
	$("#spanId").val(spanId);
	
	var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcStdNote.json";
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
		
		attendInterVal(true);

	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
	
}

//출석 인터발체크
function attendInterVal(flag){
	// 진도타입을 가져옴
	var progressAttendTypeCode = $("#progressAttendTypeCode").val();
	// 진도체크 방식이 페이지나 시간+페이지일 경우에만 인터벌 적용 및 초기화
	if(progressAttendTypeCode == "T" || progressAttendTypeCode == "H"){
		if(flag && weekProgressYn == "Y"){ // 학습상태일때만 진도율 적용 예습,복습 미적용
			upateInterval = setInterval(updateAttend, 1000*interValTime);
		} else {
			clearInterval(upateInterval);
		}
	}
}

//컨텐츠 paging 처리
function setPage(){
	$("#contentPage").html("<b>"+curPage+"</b>"+"/"+totPage);
}


//컨텐츠 동영상/플래쉬 변경처리
function setViewLesson(vStdLessonId,vWeekId,vWeekCnt,vSubjSchId,data){
	
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
		viewLesson(contentArray[pos].stdLessonId,contentArray[pos].weekId,contentArray[pos].weekCnt,contentArray[pos].subjSchId,contentArray[pos].lessonId,contentArray[pos].lessonItemId,contentArray[pos].subitemListId, contentArray[pos].pageInfo,contentArray[pos].viewLessonId,contentArray[pos].spanId);
	} else {
		movieLesson(contentArray[pos].stdLessonId, contentArray[pos].weekId, contentArray[pos].weekCnt, contentArray[pos].subjSchId, contentArray[pos].contentType, contentArray[pos].contentsDir, contentArray[pos].contentsIdxFile, contentArray[pos].url, contentArray[pos].pageInfo,contentArray[pos].spanId);
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
		viewLesson(contentArray[0].stdLessonId,contentArray[0].weekId,contentArray[0].weekCnt,contentArray[0].subjSchId,contentArray[0].lessonId,contentArray[0].lessonItemId,contentArray[0].subitemListId,contentArray[0].pageInfo,contentArray[0].viewLessonId,contentArray[0].spanId);
	} else {
		movieLesson(contentArray[0].stdLessonId, contentArray[0].weekId, contentArray[0].weekCnt, contentArray[0].subjSchId, contentArray[0].contentType, contentArray[0].contentsDir, contentArray[0].contentsIdxFile, contentArray[0].url, contentArray[0].pageInfo, contentArray[0].viewLessonId,contentArray[0].spanId);
	}
	$("#week-schedule-title").html("[ "+contentArray[0].weekCnt+"주차 ] "+contentArray[0].subjStep+"차시&nbsp;&nbsp;-&nbsp;&nbsp;"+contentArray[0].subTitle+"");
}



//Next 처리
function nextView(){
	if(curPage != totPage){
		if(weekProgressYn == 'Y') updateAttend();
		curPage++;
		setNPMenu(curPage,"next");
		setPage();
	}else{
		if(weekProgressYn == 'Y') updateAttend();
		alert("마지막 강의입니다.");
	}
}

//Prev 처리
function prevView(){
	if(curPage != 1){
		if(weekProgressYn == 'Y') updateAttend();
		curPage--;
		setNPMenu(curPage,"prev");
		setPage();
	}else{
		alert("첫 강의입니다.");
	}
}

function updateAttend(){
	var progressAttendId 				= $("progressAttendId").val();
	var progressTimeId 				= $("progressTimeId").val();
	var iframeSrc							= $("#contentFrame").attr("src");
	var linkContentYn 					= $("#linkContentYn").val();
	var viewLessonId 					= $("#viewLessonId").val();
	var attendProgress 				=  $("#attendProgress").val();
	var cutProgress 						=  $("#cutProgress").val();
	var progressAttendTypeCode	= $("#progressAttendTypeCode").val();
	var spanId								= $("#spanId").val();
	var weekId								= $("#weekId").val();
	var mark = "";
	// 진도아이디, 시간아이디가 있을경우에만 진행
	if(progressAttendId != "" && progressTimeId != "" && iframeSrc != "#"){
		var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcStdUpdateAttend.json";
	 	var param = $("#frmLesson").serializeArray();
		com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
		if (200 == jqXHR.status ) {
			
			// 클립별 시간진도율
	 		var timeProgressRatio = parseInt( jqXHR.responseJSON.timeProgressRatio );
			
	 		// 진도율
	 		var progressRatio = parseInt(jqXHR.responseJSON.lessonProgress);
	 		
	 		
	 		// 주차별 진도율
	 		var weekProgressRate = parseInt(jqXHR.responseJSON.weekProgressRate);
	 		$("#weekProgress_"+weekId).text(weekProgressRate+"%");
	 		//com.alert( progressRatio );
	 		
	 		/*if(progressRatio >= parseInt(attendProgress)){
 				mark = "○";
 			} else if (progressRatio <= parseInt(cutProgress)){
 				mark = "×";
 			} else {
 				mark = "△";
 			}*/
	 		
	 		if(progressAttendTypeCode == "T" || progressAttendTypeCode == "H"){
	 			
	 			if( progressRatio >=100 || timeProgressRatio >= 100) attendInterVal(false);
	 			
	 			if( progressRatio >=100 && timeProgressRatio >= 100){
	 				mark = "○";
		 		} else {
		 			mark = "×"
		 		}
	 		} else {
	 			if( progressRatio >= 100 ){
	 				mark = "○";
	 			} else {
	 				mark = "×";
	 			}
	 			attendInterVal(false);
	 		}
	 		
	 		$("#"+spanId).html(mark);
	 		
	 		var markProgress = 0;
	 		var markLen = $("span[name='span_name']").length;
	 		
	 		//console.log("============= markLen : "+markLen);
	 		
	 		$("span[name='span_name']").each(function(index) { 
	 			var str = $(this).text();
	 			if(str.indexOf("○") != -1){
	 				markProgress = markProgress+100;
	 			}
	 		});
	 		
	 		//console.log("============= markProgress : "+markProgress);
	 		
	 		if(markProgress > 0) {
	 			var weekProgress = parseInt(markProgress / ( markLen*100 ) * 100);
	 			//console.log("============= weekProgress : "+weekProgress);
	 			
	 			if(weekProgress >= parseInt(attendProgress)){
	 				$("#b_"+weekId).text("출석 : ○");
	 			} else if (weekProgress <= parseInt(cutProgress)){
	 				$("#b_"+weekId).text("출석 : ×");
	 			} else {
	 				$("#b_"+weekId).text("출석 : △");
	 			}
	 		}
	 		
		}
		}, {
			async : true,
			type : "POST",
			errorCallback : null
		});
	}
}

function endAttend(){
	
	var progressAttendId 				= $("progressAttendId").val();
	var progressTimeId 				= $("progressTimeId").val();
	
	// 진도아이디, 시간아이디가 있을경우에만 진행
	if(progressAttendId != "" && progressTimeId != ""){
		var reqUrl = CONTEXT_ROOT + "/lu/currproc/currProcStdUpdateAttend.json";
	 	var param = $("#frmLesson").serializeArray();
		com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
		if (200 == jqXHR.status ) {
	 		//com.alert( jqXHR.responseJSON.timeProgressRatio );
	 		//cmsLessonItemProgress
	 		//$("#timeProgressRatio").val( jqXHR.responseJSON.timeProgressRatio )
		}
		}, {
			async : true,
			type : "POST",
			errorCallback : null
		});
	}
}



function learningPopSort() {
	/*<ul id="learning-popup">
	<li class="learning-area"  id="learning-area">*/
	var obj = $('#learning-popup');
	var iHeight = (document.body.clientHeight / 2) - obj.height() / 2 + document.body.scrollTop;
	var iWidth = (document.body.clientWidth / 2) - obj.width() / 2 + document.body.scrollLeft;
	obj.css({
		position: 'absolute'
		, display:'block'
		, top: iHeight
		, left: iWidth
	});
}
