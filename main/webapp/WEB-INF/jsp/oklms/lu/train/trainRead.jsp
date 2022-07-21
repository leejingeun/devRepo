<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<% response.addHeader("Access-Control-Allow-Origin","*"); %>

<c:set var="targetUrl" value="/lu/train/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script> -->

<script type="text/javascript">

var targetUrl = "${targetUrl}";
var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
var totalCount = '${totalCount}'; //전체 데이터 갯수
var pageIndex = '${pageIndex}'; //현재 페이지 정보

//교과별 컨텐츠 보기 팝업관련 변수
var curView;
var curPage=1;
var totPage=0;
var contentArray = [];

$(document).ready(function() {
	if ('' == pageSize) {
		pageSize = 10;
	}
	if ('' == totalCount) {
		totalCount = 0;
	}
	if ('' == pageIndex) {
		pageIndex = 1;
	}

	loadPage();
});

/*====================================================================
	화면 초기화 
====================================================================*/
function loadPage() {
	initEvent();
	initHtml();
}


/* 각종 버튼에 대한 액션 초기화 */
function initEvent() {
	//초기 셋팅
	$(".list-zone dt").click(function(){
		alert(11);
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();
	});

	$(".list-zone dd a").click(function(){
		alert(22);
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
		$(this).parent().parent().siblings().find('a').removeClass('current');
	});
}

/* 화면이 로드된후 처리 초기화 */
function initHtml() {

//     com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

	$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
	$("#pageIndex").val(pageIndex); //현재 페이지 정보
	$("#totalRow").text(totalCount);
}

/*====================================================================
	사용자 정의 함수 
====================================================================*/

function press(event) {
	if (event.keyCode==13) {
		fn_list('1');
	}
}

	/* 수정 페이지로 이동 */
	function fn_updt(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateTrain.do";

		$("#frmTrain").attr("method", "post" );
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#pageIndex").val( '1' );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listTrain.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();	
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_showLearningpop(param1){
 		$("#id").val(param1);
//$("#id").val("7");
	 	var reqUrl = CONTEXT_ROOT + "/lu/train/listTrain.json";
	 	var param = $("#frmTrain").serializeArray();
		com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
		if (200 == jqXHR.status ) {
// 	 		com.alert( jqXHR.responseJSON.retMsg );
			//setViewLesson(jqXHR.responseJSON.retMsg);
 			courseContentItemList(jqXHR.responseJSON.retMsg);
		}
		}, {
			async : true,
			type : "POST",
			errorCallback : null
		});
		showLearningpop();
	}
	
	//컨텐츠 목록 가져오기
	function courseContentItemList(data){

		var jsonData = JSON.parse(data);
		$(".list-zone").html("");
		$(".list-zone").append("<p>학습목차</p>");
		$(".list-zone").append("<div id='contentItems'></div>");
		
		for (var i = 0; i < jsonData.body.list.length; i++) {
		    var list = jsonData.body.list[i];
		    
			$("#contentItems").append("<dl id='"+list.id+"'>");
		    $("#"+list.id).append("<dt>"+list.title+"</dt>");
		    //console.log(list.lesson.title);
		    //alert("list.lesson.item_list.length : "+list.lesson.item_list.length);
		    for (var j = 0; j < list.lesson.item_list.length; j++) {
		    	var item_list = list.lesson.item_list[j];
		    	//console.log(item_list.title);
		    	
		    	//alert("item_list.lesson_id : "+item_list.lesson_id);
		    	if(j == 0){
		    		if(i==0){
		    			$("#"+list.id).append("<dd class='on' id='"+item_list.lesson_id+"'></dd>");
		    			$("#"+item_list.lesson_id).append("<a  id='"+item_list.id+"' href='javascript:goViewLesson(\""+item_list.lesson_id+"\",\""+item_list.id+"\");'>"+item_list.title+"</a>");
		    			//class='current'
		    		}else{
		    			$("#"+list.id).append("<dd id='"+item_list.lesson_id+"'></dd>");
		    			$("#"+item_list.lesson_id).append("<a id='"+item_list.id+"' href='javascript:goViewLesson(\""+item_list.lesson_id+"\",\""+item_list.id+"\");'>"+item_list.title+"</a>");
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
// 		console.log(contentArray);
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
		//paging 변경
		curPage = contentArrayIndex+1;
		setPage();
		viewLesson(vlessonId,vlessonItemId,contentArray[contentArrayIndex].subitemListId);
	}

	//컨텐츠 내용 조회
	function viewLesson(vlessonId,vlessonItemId,vlessonSubItemId){
		$("#lessonId").val(vlessonId);
		$("#lessonItemId").val(vlessonItemId);
		$("#lessonSubItemId").val(vlessonSubItemId);
		
	 	var reqUrl = CONTEXT_ROOT + "/lu/train/trainNote.json";
	 	var param = $("#frmTrain").serializeArray();
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
		var width = parseInt(jsonData.result.width,10);
		var height = parseInt(jsonData.result.height,10);
		
		if (contentUrl.indexOf('Contents') > 0 || contentUrl.indexOf('Sources/Contents') > 0) {
		      contentUrl = 'http://cdn.e-koreatech.ac.kr/Portal/'+contentUrl;
		} else {
		      contentUrl = 'http://cdn.e-koreatech.ac.kr/Portal/Sources/Contents/'+contentUrl;
		}
		
		alert(contentUrl);
		if(contentUrl != ""){
			$("#contentFrame").attr("src",contentUrl);
//	   		$("#contentFrame").attr("width",width+"px");
//	   		$("#contentFrame").attr("height",height+"px");

	 		$("#learning-popup .learning-area").css("width",(width+280)+"px");
	 		$("#learning-popup .learning-area").css("left","0px");
	 		$("#learning-popup .learning-area").css("margin-left","0px");
	 		
	  		$("#learning-popup .movie-zone iframe").css("width",width+"px");
	  		$("#learning-popup .movie-zone iframe").css("height",height+"px");
	 		
	 		$("#learning-popup .movie-zone").css("width",width+"px");
	 		$("#learning-popup .list-zone").css("height",height+"px");
	 		
	 		$("#learning-popup .list-zone div").css("height",(height-30)+"px");
	 		
			var top =  Math.max(0, (($(window).height() - (height+100)) / 2) + $(window).scrollTop()) + "px";
			var left =  Math.max(0, (($(window).width() - (width+280)) / 2) + $(window).scrollLeft()) + "px"; 
			$("#learning-area").css("top",top);
			$("#learning-area").css("left",left);
			
			
		}else{
			alert("컨텐츠 URL를 찾지 못했습니다.");
		}
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
	
</script>
<div class="content">
<h1 id="curMenuTitle">훈련일지 상세</h1>
<form:form commandName="frmTrain">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="id" name="id"/>
<input type="hidden" id="courseContentId" name="courseContentId"/>
<input type="hidden" id="lessonId" name="lessonId" />
<input type="hidden" id="lessonItemId" name="lessonItemId" />
<input type="hidden" id="lessonSubItemId" name="lessonSubItemId" />
</form:form>
<table class="table_write">
	<tbody>
		<tr>
			<th width="132px">교과목명</th>
			<td>
				${resultDetailData[0].title}
			</td>      
			<th width="132px">교과목코드</th>
	  		<td>
				${resultDetailData[0].course_code_id}	
	  		</td>  
		</tr>
	</tbody>
</table><!-- E : view-1 -->

<h1 id="curMenuTitle">버젼정보 목록</h1>
<table class="table" width="100%">
<tr>
	<th width="5%">No</th>
	<th width="50%">온라인 교과목명</th>
	<th width="10%">교과목 코드</th>	
	<th width="20%">미리보기</th>
</tr>
<c:forEach var="result" items="${resultList}" varStatus="status">
<tr>
	<td><c:out value="${status.count}"/></td>
<td class="left"><c:out value="${result.subtitle}" /></td>
<td><c:out value="${result.course_code_id}" /></td>
	<td>
		<a href="javascript:fn_showLearningpop(<c:out value="${result.id}" />);"><img src="/images/oklms/std/inc/searchBtn.png" alt="미리보기" /></a>
	</td>
</tr>
</c:forEach>
<c:if test="${fn:length(resultList) == 0}">
<tr>
 	<td class="none" colspan="4" ><spring:message code="common.nodata.msg" /></td>
</tr>
  </c:if>
</table>

<!-- S : learning-popup -->
<!-- <ul id="learning-popup">
	<li class="learning-area">
		<div class="title"><span>기전공학 기초설계</span> / 1주차. 기전공학 기초설계 기전공학 기초설계기전공학 기초설계기전공학 기초설계기전공학 기초설계기전공학 기초설계기전공학 기초설계</div>

		<div class="movie-zone">
			<iframe src="https://www.youtube.com/embed/z1gn-CRc0qQ" frameborder="0" allowfullscreen></iframe>

			<a href="#!" class="pre">이전</a>
			<span><b>01</b> / 24</span>
			<a href="#!" class="next">다음</a>
		</div>

		<div class="list-zone">
			<p>학습목차</p>
			<dl>
				<dt>01주차. 기전공학 기초설계 기초설계 기전공학 기초설계</dt>
				<dd><a href="#!">01차. 기전공학 기초설계</a></dd>
				<dd class="current"><a href="#!">02차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">03차. 기전공학 기초설계 기전공학 기초설계</a></dd>
				<dd><a href="#!">04차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">05차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">06차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">07차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">08차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">09차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">10차. 기전공학 기초설계</a></dd>

				<dt>02주차. 기전공학 기초설계</dt>
				<dd><a href="#!">01차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">02차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">03차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">04차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">05차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">06차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">07차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">08차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">09차. 기전공학 기초설계</a></dd>
				<dd><a href="#!">10차. 기전공학 기초설계</a></dd>
			</dl>
		</div>

		<div class="btn-zone"><a href="#!" class="movie">동영상문제해결</a><a href="#!" class="error">오류신고</a><a href="javascript:hideLearningpop();" class="close">닫기</a></div>
	</li>
	<li class="popup-bg"></li>
</ul> --><!-- E : learning-popup -->

<!-- S : page-btn -->
<div class="btn_left"><a href="#fn_list"><input type="button"  onclick="javascript:fn_list();" onkeypress="this.onclick;" class="black" value="목록" /></a></div>
<div class="btn_right">
	<a href="#fn_updt"><input type="button" class="orenge" onclick="javascript:fn_updt();" onkeypress="this.onclick;" value="수정" /></a>
</div>
<!-- E : page-btn -->
</div>
