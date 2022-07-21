<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
						<h2>과제</h2>
						<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>


						<table class="type-1 mb-030">
							<colgroup>
								<col style="width:15%" />
								<col style="width:*px" />
								<col style="width:15%" />
								<col style="width:*px" />
								<col style="width:15%" />
								<col style="width:*px" />
							</colgroup>
							<tbody>
									<tr>
										<th>교과형태</th>
									<td>${currProcVO.subjectTraningTypeName}</td>
										<th>과정구분</th>
									<td>${currProcVO.subjectTypeName}</td>
										<th>학점</th>
										<td>${currProcVO.point }학점</td>
									</tr>
									<tr>
										<th>교수</th>
										<td>${currProcVO.insNames}</td>
										<th>온라인 교육형태</th>
										<td>${currProcVO.onlineTypeName} (성적비율 ${currProcVO.gradeRatio}%)</td>
										<th>선수여부</th>
										<td>${currProcVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
									</tr>
								</tbody>
						</table>


<script type="text/javascript" src="/common/smartEditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
<!--
var oEditors = [];

$(document).ready(function() {
	loadPage();	
	$('#file-input').on("change", previewImages);
});

/*====================================================================
jqGrid 및 화면 초기화 
====================================================================*/
function loadPage() {
	initEvent();
	initHtml();
	initEditor();
}

/* 각종 버튼에 대한 액션 초기화 */ 
function initEvent() {
}

/* 화면이 로드된후 처리 초기화 */ 
function initHtml() {
		com.datepickerDateFormat('submitStartDate');
		com.datepickerDateFormat('submitEndDate');
}


/* 화면이 로드된후 에디터 기본옵션 설정 초기화 */ 
function initEditor() {
	//Smart Editor
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "reportDesc",
		sSkinURI: "/common/smartEditor/SmartEditor2Skin.html",	
		htParams : {bUseToolbar : true,
			fOnBeforeUnload : function(){
				//alert("아싸!");	
			}
		}, //boolean
		fOnAppLoad : function(){
			//예제 코드
			//oEditors.getById["textAreaContent"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	});
}

/* HTML Form 신규, 수정 */
function fn_formSave(){

	var startDate = $("#submitStartDate").val();
	var endDate = $("#submitEndDate").val();

	startDate = startDate.replace(".","");
	startDate = startDate.replace(".","");
	endDate = endDate.replace(".","");
	endDate = endDate.replace(".","");

	// 공개일이 마감일보다 큰지 비교
	if(Number(startDate) > Number(endDate)){
		alert("마감일이 공개일보다 전날짜입니다.");
		$("#submitEndDate").focus();
		return false;
	}

	var score = $("#score").val();

	if(score>10){
		alert("배점은 10점이하로 입력하셔야합니다.");
		$("#score").val("10");
		return;
	}
	
	if( $("#reportName").val()=="" ){
		alert("과제 제목을 입력하세요.");
		return;
	}
	
	if(checkStringFormat($("#reportName").val())){
		alert("과제 제목에 특수문자가 들어있습니다.");
		$("#reportName").focus();
		return;
	}
	
	
 	
	if(frmReport.evalYn[0].checked == true){
		if($("#score").val()=="0" || $("#score").val()==""){
			alert("평가 배점을 입력하세요.");
			return;
		}
	}
	
	if( $("#submitStartDate").val()==""|| $("#submitEndDate").val()==""){
		alert("제출기간을 입력하세요.");
		return;
	}
	
	if (  confirm("저장 하시겠습니까?")) {
		var data =oEditors.getById["reportDesc"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("필수항목을 입력해 주세요.");
			oEditors.getById["reportDesc"].exec("FOCUS"); 
			return false;
		}
		$("#reportDesc").val(data);
		var reqUrl =  "/lu/report/insertReport.do";
		$("#frmReport").attr("target", "_self");
		$("#frmReport").attr("action", reqUrl);
		$("#frmReport").submit();
	}
}

function previewImages() {
	  var $preview = $('#preview').empty();
	  $("#fileName").val($('#file-input').val());
	  var filesize = 0;
	  if (this.files) {
		  $.each(this.files, readAndPreview);
	  }
	  
	  function readAndPreview(i, file) {
		if (window.FileReader) { // FireFox, Chrome, Opera 확인.
			//if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
			//      return alert(file.name +" is not an image");
		    //} // else...
		    filesize = filesize + file.size;
			if(filesize > 10000000){ //Checking Sum 10M Size 
				alert("전체 사이즈 10M이상 업로드 할수 없습니다.");
				
				$("#atchFiles").val("");
				
				return false;
			}else{
				
				var filesizeNumber = "";
				if(filesize>1000000){
					filesizeNumber = ((filesize/1000000).toFixed(2))+" M";
				}else if(filesize>1000){
					filesizeNumber = ((filesize/1000).toFixed(2))+" KB";
				}else{
					filesizeNumber = filesize+" B";
				}		
				
				$("#fileSizeName").html( filesizeNumber +" / 10M");
			}
		}else{ // safari is not supported FileReader
	        alert('not supported Webbrowser!!');
	    }
	  }
} 
function checkStringFormat(string) { 
	  
    var stringRegx = /[~!@\#$%<>^&*\()\-=+_\’]/gi; 
    var isValid = true; 
 
    return stringRegx.test(string); 
}
//--> 
</script>

						<h2>과제 출제</h2> 
<form:form commandName="frmReport" name="frmReport" method="post" enctype="multipart/form-data" >

<input type="hidden" name="yyyy" value="${currProcVO.yyyy}" />
<input type="hidden" name="term" value="${currProcVO.term}" />
<input type="hidden" name="subjectCode" value="${currProcVO.subjectCode}" />
<input type="hidden" name="classId" value="${currProcVO.subClass}" />

						<table class="type-write">
							<colgroup>
								<col style="width:130px" />
								<col style="width:*" />
							</colgroup>
							<tbody>
								<tr>
									<th>주차</th>
									<td class="left">
										<select id="weekCnt" name="weekCnt"  >
											<c:forEach var="result" items="${onlineTraningSchVO}" varStatus="status">
											<option value="${result.weekCnt}">${result.weekCnt}</option>
											</c:forEach>
										</select>&nbsp;&nbsp;&nbsp;
										주차
									</td>
								</tr>
								<tr>
									<th>제목</th>
									<td class="left"><input type="text" name="reportName" id="reportName" style="width:97%;" /></td>
								</tr>
								<tr>
									<th>과제제출 기간</th>
									<td class="left">
										<!-- 
										<input type="radio" name="submitDateType" value="" class="choice"  />&nbsp;&nbsp;										
										학습활동서 제출마감일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										-->
										선택기간
										<input type="radio" name="submitDateType" value="" class="choice" checked />&nbsp;&nbsp;
										<input type="text" name="submitStartDate" id="submitStartDate" value="" style="width:70px" />  ~ <input type="text" name="submitEndDate" id="submitEndDate"  value="" style="width:70px" />
									</td>
								</tr>
								<tr>
									<th>평가</th>
									<td class="left">
										<input type="radio" name="evalYn" value="Y" class="choice" checked />&nbsp;&nbsp;Y&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="evalYn" value="N" class="choice" />&nbsp;&nbsp;N
									</td>
								</tr>
								<tr>
									<th>배점</th>
									<td class="left"><input type="number" name="score" id="score" value="0"  min="0" max="9" style="width:30px; text-align:right;"  /> 점
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td class="left"><textarea name="reportDesc" id="reportDesc" rows="5"></textarea></td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td class="left">
										<input type="text" id="atchFiles" name="atchFiles" style="width:50%;" readonly  onchange="fileCheck(this.form.atchFiles)">
										<span>
											<a href="#@" class="checkfile">파일선택</a>
											<input type="file" class="file_input_hidden" name="file-input" id="file-input"  onchange="javascript:document.getElementById('atchFiles').value = this.value" />
										</span>
										<span id="fileSizeName">0KB / 10M</span>
									</td>
								</tr>
							</tbody>
						</table>
</form:form>
						<div class="btn-area align-right mt-010">
							<a href="/lu/report/listReport.do" class="gray-1 float-left">취소</a><a href="#" onclick="javascript:fn_formSave();" class="orange">등록</a>
						</div><!-- E : btn-area -->

