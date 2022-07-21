<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
						<h2>면담일지</h2>
<form:form commandName="frmInterview" name="frmInterview" method="post"    enctype="multipart/form-data" >
<input type="hidden" name="interviewMemberSeqs" id="interviewMemberSeqs"  value="" />
<input type="hidden" name="companyId" value="${interviewVO.companyId }" />
<input type="hidden" name="interviewMemSeq" value="${interviewVO.interviewMemSeq }" />
						<table class="type-write">
							<colgroup>
								<col style="width:100px" />
								<col style="width:100px" />
								<col style="width:*" />
							</colgroup>
							<tbody>
								<tr>
									<th>일자</th>
									<th>구분</th>
									<th>면담내용</th>
								</tr>
								<tr>
									<th rowspan="8" class="sub-name"><input type="text" name="interviewNoteDate" id="interviewNoteDate" value="" style="width:65px; text-align:center;" /></th>
									<th class="sub-name">학습근로자</th>
									<td>
										<a href="javascript:showLearningpop();" class="btn-full-blue">선택</a>&nbsp;&nbsp;<span id="interviewMemberName" style="width:100%;display:inline;"></span>
									</td>								
								</tr>

								<tr>
									<th class="border-left sub-name">훈련과정</th>
									<td>
										<select name="traningProcessId" id="traningProcessId" onchange="javascript:traningProcessCk(this.value);" >
										<option value=""> =훈련과정선택=</option>
										<c:forEach var="result" items="${resultlist}" varStatus="status">
										<option value="${result.companyId},${result.traningProcessId}">${result.hrdTraningName }</option>
										</c:forEach>										 
										</select>
									</td>
								</tr>
								
								
								<tr>
									<th class="border-left sub-name">훈련</th>
									<td><input type="text" name="trainingTalk"  id="trainingTalk" style="width:98%;" /></td>
								</tr>
								<tr>
									<th class="border-left sub-name">근로</th>
									<td><input type="text" name="workTalk" id="workTalk" style="width:98%;" /></td>
								</tr>
								<tr>
									<th class="border-left sub-name">기타</th>
									<td><input type="text" name="tempTalk" id="tempTalk" style="width:98%;" /></td>
								</tr>
								<tr>
									<th class="border-left sub-name">지도사항</th>
									<td>
										<input type="text" id="fileName-3" style="width:200px;" readonly="readonly">
										<span>
											<a href="#@" class="checkfile">파일선택</a>
											<input type="file" class="file_input_hidden" id="fileName-2"  name="fileName-2" onchange="javascript: document.getElementById('fileName-3').value = this.value" />
										</span>
										3MB 이내의 Gif, png, bmp 파일 가능합니다.
									</td>
								</tr>
								<tr>
									<th rowspan="2" class="border-left sub-name">면담내용</th>
									<td><textarea name="totalTalk" id="totalTalk"></textarea></td>
								</tr>
								<tr>
									<td class="border-left">
										<input type="text" id="fileName-4" style="width:200px;" readonly="readonly">
										<span>
											<a href="#@" class="checkfile">파일선택</a>
											<input type="file" class="file_input_hidden" id="fileName-1"   name="fileName-1"  onchange="javascript: document.getElementById('fileName-4').value = this.value" />
										</span>
									</td>
								</tr>
								
								
								<tr>
									<th rowspan="3" class="sub-name">추가항목</th>
									<th class="sub-name"><input type="text" name="tempSj1" style="width:65px; text-align:center;" /></th>
									<td><input type="text" name="tempCn1" style="width:98%;" /></td>
								</tr>
								<tr>
									<th class="border-left sub-name"><input type="text" name="tempSj2" style="width:65px; text-align:center;" /></th>
									<td><input type="text" name="tempCn2" style="width:98%;" /></td>
								</tr>
								<tr>
									<th class="border-left sub-name"><input type="text" name="tempSj3" style="width:65px; text-align:center;" /></th>
									<td><input type="text" name="tempCn3" style="width:98%;" /></td>
								</tr>
							</tbody>
						</table>
</form:form>
						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_cancel();" class="gray-1">취소</a>
							<a href="#!" onclick="javascript:fn_formSave();" class="orange">저장</a>
						</div><!-- E : btn-area -->

<script type="text/javascript">
<!--

var listSeq="";
var listName="";
var listcompanyId="";
/*************************************************************
레이어 팝업창
**************************************************************/
var showLearningpop = function(){
	/*
	if(!$("#traningProcessId").val()){
		alert("훈련과정을 먼저선택해주세요!");
		return;
	}
	*/
	$(".student-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop = function(){
	$(".student-area,.popup-bg").removeClass("open");
	// 선택값입력
  $( "#interviewMemberSeqs" ).val( listSeq );    
  $( "#interviewMemberName" ).html( listName );
};
var showLearningpop1 = function(){
	$(".student-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop1 = function(){
	$(".student-area,.popup-bg").removeClass("open"); 
};



var showLearningpop2 = function(){
	$(".student-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop2 = function(){
	$(".student-area,.popup-bg").removeClass("open"); 
};



var showCompanypop = function(){
	$(".company-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideCompanypop = function(){
	$(".company-area,.popup-bg").removeClass("open"); 
};



var showTrainpop = function(){
	$(".training-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideTrainpop = function(){
	$(".training-area,.popup-bg").removeClass("open"); 
};

$(document).ready(function() {
	initHtml();
	$.ajax({
		  type: "POST",
		  url: "/lu/interview/ajaxInterviewMember.do",
		  data: {"interviewMemberSeqs":listSeq,"pageIndex":"1"},
		  success:function( html ) {
			$( "#interviewMemberList" ).html( html );			
		  }
	});	
	$('#fileName-2').on("change", previewImages);
});

function traningProcessCk(thisValue){

	if(!thisValue){
		var thisValues = thisValue.split(",");
		listcompanyId = thisValues[0];
	}

}
function checkboxClick(param1,thisValue){

	//체크박스 선택
	var thisValues = thisValue.split(",");
	if(param1){
		//alert(listSeq.indexOf(thisValues[0]) );
		if(listSeq.indexOf(thisValues[0]) >=0){

		}else{
	    	listSeq += ','+thisValues[0];
	    	listName += thisValues[1]+' ';		
		}
	}else{
		listSeq =	listSeq.replace(','+thisValues[0],'');
		listName = listName.replace(thisValues[1]+' ','');
	}
}
function fn_cancel(){

	var reqUrl =  "/lu/interview/listInterview.do";
	$("#frmInterview").attr("target", "_self");
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").submit();		

}

function previewImages() { 
 
	  var filesize = 0;
	  if (this.files) {
		  $.each(this.files, readAndPreview);
	  }
	  
	  function readAndPreview(i, file) {
		if (window.FileReader) { // FireFox, Chrome, Opera 확인.
			if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
					$("#fileName-2").val("");
					$("#fileName-3").val("");
			      return alert(file.name +" 이미지파일이 아닙니다.");
		    } 
		    filesize = filesize + file.size;
			if(filesize > 3000000){ //Checking Sum 10M Size 
				alert(" 사이즈 3M이상 업로드 할수 없습니다.");
				
				$("#fileName-2").val("");
				$("#fileName-3").val("");
				return false;
			}
			
		} 
	  }
} 

function fn_memberLList( pageIndex){
	
	$.ajax({
		  type: "POST",
		  url: "/lu/interview/ajaxInterviewMember.do",
		  data: { "pageIndex": pageIndex,"interviewMemberSeqs":listSeq},
		  success:function( html ) {
			$( "#interviewMemberList" ).html( html );
			showLearningpop();
		  }
	});
}
/* 화면이 로드된후 처리 초기화 */ 
function initHtml() {
		com.datepickerToDateFormat('interviewNoteDate',0,'text');		
}

/*  등록  */
function fn_formSave( ){ 
	
	 	if($("#interviewMemberSeqs").val() == ""){
	 		alert("학습근로자를 선택하세요.");
	 		return ;	
	 	}  
	 	if(!$("#traningProcessId").val()){
	 		alert("훈련과정을 선택하세요.");
	 		return;
	 	}
	 	if($("#totalTalk").val() == "" && $("#totalTalk").val() == "" && $("#fileName-1").val() == ""  && $("#fileName-2").val() == ""  ){
	 		alert("면담내용 또는 지도사항 이미지 파일을 입력하세요.");
	 		return ;	
	 	}
	 	if (  confirm("등록 하시겠습니까?")) {
			var reqUrl =  "/lu/interview/insertInterview.do";
			$("#frmInterview").attr("target", "_self");
			$("#frmInterview").attr("action", reqUrl);
			$("#frmInterview").submit();	
	 	}
}

//--> 
</script>
			<ul id="student-popup">
				<li class="student-area" id="interviewMemberList" >

				</li>
				<li class="popup-bg"></li>
			</ul><!-- E : student-popup -->
			
  