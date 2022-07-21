<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<c:set var="targetUrl" value="/lu/discuss/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript" src="${contextRootJS }/common/smartEditor/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	var targetUrl = "${targetUrl}";
	var oEditors = [];

	$(document).ready(function() {
		loadPage();
		$('#file-input').on("change", previewImages);

		$(document).on("keyup", "input:text[name='title']", function() {
			var text = $(this).val();
			if(Check_nonTag(text) == false){
				  alert("HTML 태그는 입력하실 수 없습니다! 예)< > 기호...");
				  $("#title").val('');
				  $("#title").focus();
				  return false;
			}
		});
	});

	/*====================================================================
		화면 초기화
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
		$(".requare").css("color", "red"); //필수값 * 빨간색 표시 css
		$(".req").css("color", "red"); //필수값 * 빨간색 표시 css

		com.datepickerDateFormat('startDate');
 	    com.datepickerDateFormat('endDate');

 	    var scoreOpenYnChk = '${discussReadVO.scoreOpenYn}';
 	    if('Y' == scoreOpenYnChk){
 	    	$("input:checkbox[name=scoreOpenYnChk]").attr("checked", "checked");
 	    }

 	    var evalYn = '${discussReadVO.evalYn}';
 	    if('N' == evalYn){
 	    	$("#evalScore").val('');
			$('#evalScore').attr("disabled",true);
			$('#scoreOpenYnChk').attr("disabled",true);
			$("input:checkbox[name='scoreOpenYnChk']").prop("checked", false);
			$(".req").hide();
 	    }
	}

	/* 화면이 로드된후 에디터 기본옵션 설정 초기화 */
	function initEditor() {
		//Smart Editor
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
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

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* HTML 태그 검사 */
	function Check_nonTag(text){
	 var opentag = '><';
	 var i ;

	 for ( i=0; i < text.length; i++ ){
	  if( opentag.indexOf(text.substring(i,i+1)) > 0){
	   break ;
	  }
	 }

	 if ( i != text.length ){
	  return false;
	 }else{
	  return true ;
	 }

	 return false;
	}

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmDiscuss").find("input,select").val("");
	}

	/* .저장 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "updateDiscuss.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	//저장버튼 클릭시 입력form Validate 체크
	function fn_formValidate(){
		var startDate = "";
		var endDate = "";
		var scoreOpenChecked = $("input:checkbox[name=scoreOpenYnChk]:checked");
		var evalYnChecked = $("input:radio[name='evalYn']:checked").val();

		if(scoreOpenChecked.length == 0){
			$("#scoreOpenYn").val('N');
		}else{
			$("#scoreOpenYn").val('Y');
		}

		if($("#title").val() == ""){
			alert("제목을 입력 주세요.");
			$("#title").focus();
			return false;
		}

		if($("#startDate").val() == ""){
			alert("공개일을 선택 주세요.");
			$("#startDate").focus();
			return false;
		}

		if($("#startHour").val() == ""){
			alert("공개일 시간을 선택 주세요.");
			$("#startHour").focus();
			return false;
		}

		if($("#startMin").val() == ""){
			alert("공개일 분을 선택 주세요.");
			$("#startMin").focus();
			return false;
		}

		if($("#endDate").val() == ""){
			alert("마감일을 선택 주세요.");
			$("#endDate").focus();
			return false;
		}

		if($("#endHour").val() == ""){
			alert("마감일 시간을 선택 주세요.");
			$("#endHour").focus();
			return false;
		}

		if($("#endMin").val() == ""){
			alert("마감일 분을 선택 주세요.");
			$("#endMin").focus();
			return false;
		}

		startDate = $("#startDate").val();
		endDate = $("#endDate").val();

		startDate = startDate.replace(".","");
		startDate = startDate.replace(".","");
		endDate = endDate.replace(".","");
		endDate = endDate.replace(".","");

		// 공개일이 마감일보다 큰지 비교
		if(Number(startDate) > Number(endDate)){
			alert("마감일이 공개일보다 전날짜입니다.");
			$("#endDate").focus();
			return false;
		}

		if("Y" == evalYnChecked){
			if($("#evalScore").val() == ""){
				alert("배점을 입력 주세요.");
				$("#evalScore").focus();
				return false;
			}
		}

		var data =oEditors.getById["content"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("내용을 입력해 주세요.");
			oEditors.getById["content"].exec("FOCUS");
			return false;
		}

		$("#content").val(data);

		/* if($("#content").val() == ""){
			alert("내용을 입력 주세요.");
			$("#content").focus();
			return false;
		} */

		return true;
	}

	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#discussId").val('');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listDiscuss.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 평가유무 라디오버튼 Change 이벤트 */
	function fn_change_eval_yn(obj){

		var evalYnChk = obj.value;
		if("N" == evalYnChk){
			$("#evalScore").val('');
			$('#evalScore').attr("disabled",true);
			$('#scoreOpenYnChk').attr("disabled",true);
			$("input:checkbox[name='scoreOpenYnChk']").prop("checked", false);
			$(".req").hide();
		}else{
			$('#evalScore').attr("disabled",false);
			$('#scoreOpenYnChk').attr("disabled",false);
			$(".req").show();
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

					$("#file1").val("");

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

</script>

<%-- 팝업폼 --%>
<form id="frmDiscuss" name="frmDiscuss" method="post" enctype="multipart/form-data" >
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="scoreOpenYn" name="scoreOpenYn" value="${discussVO.scoreOpenYn}" />

<div id="">
	<h2>토론</h2>
	<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

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
			<td>${currProcReadVO.subjectTraningTypeName}</td>
				<th>과정구분</th>
			<td>${currProcReadVO.subjectTypeName}</td>
				<th>학점</th>
				<td>${currProcReadVO.point }학점</td>
			</tr>
			<tr>
				<th>교수</th>
				<td>${currProcReadVO.insNames}</td>
				<th>온라인 교육형태</th>
				<td>${currProcReadVO.onlineTypeName} (성적비율 ${currProcReadVO.gradeRatio}%)</td>
				<th>선수여부</th>
				<td>${currProcReadVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
			</tr>
		</tbody>
		</table>

	<h2>토론생성</h2>
	<table class="type-write">
		<colgroup>
			<col style="width:110px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th><span class="requare">*</span>제목</th>
				<td><input type="text" id="title" name="title" maxlength="598" value="${discussReadVO.title}" placeholder="(ex)전체토론" style="width:98%;" /></td>
			</tr>
			<tr>
				<th><span class="requare">*</span>공개일</th>
				<td>
					<input type="text" id="startDate" name="startDate" value="${discussReadVO.startDate}" placeholder="(ex)2017.03.01" style="width:10%" readonly="readonly"/> <!-- <a href="#!" class="text"><img src="../../../image/std/inc/icon_calendar.png"></a> -->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="startHour" name="startHour" onchange="">
						<option value=''>::선택::</option>
						<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="status">
							<option value="${timeHourCd.codeId}" ${timeHourCd.codeId == discussReadVO.startHour ? 'selected' : '' }>${timeHourCd.codeName}</option>
						</c:forEach>
					</select> 시&nbsp;&nbsp;&nbsp;
					<select id="startMin" name="startMin" onchange="">
						<option value=''>::선택::</option>
						<c:forEach var="timeMinCd" items="${timeMinCode}" varStatus="status">
							<option value="${timeMinCd.codeId}" ${timeMinCd.codeId == discussReadVO.startMin ? 'selected' : '' }>${timeMinCd.codeName}</option>
						</c:forEach>
					</select> 분
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>마감일</th>
				<td>
					<input type="text" id="endDate" name="endDate" value="${discussReadVO.endDate}" placeholder="(ex)2017.06.30" style="width:10%" readonly="readonly" /> <!-- <a href="#!" class="text"><img src="../../../image/std/inc/icon_calendar.png"></a> -->&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<select id="endHour" name="endHour" onchange="">
						<option value=''>::선택::</option>
						<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="status">
							<option value="${timeHourCd.codeId}" ${timeHourCd.codeId == discussReadVO.endHour ? 'selected' : '' }>${timeHourCd.codeName}</option>
						</c:forEach>
					</select> 시&nbsp;&nbsp;&nbsp;
					<select id="endMin" name="endMin" onchange="">
						<option value=''>::선택::</option>
						<c:forEach var="timeMinCd" items="${timeMinCode}" varStatus="status">
							<option value="${timeMinCd.codeId}" ${timeMinCd.codeId == discussReadVO.endMin ? 'selected' : '' }>${timeMinCd.codeName}</option>
						</c:forEach>
					</select> 분
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>평가</th>
				<td><input type="radio" id="evalYn" name="evalYn" value="Y" onchange="fn_change_eval_yn(this);" class="choice" <c:if test="${ 'Y' eq discussReadVO.evalYn }"> checked="checked"</c:if> /> Y&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="evalYn" name="evalYn" value="N" onchange="fn_change_eval_yn(this);" <c:if test="${ 'N' eq discussReadVO.evalYn }"> checked="checked"</c:if> class="choice" /> N</td>
			</tr>
			<tr>
				<th><span class="req">*</span>배점</th>
				<td><input type="text" id="evalScore" name="evalScore" value="${discussReadVO.evalScore}" placeholder="(ex)10" onchange="javascript:com.checkNumber(document.getElementById('evalScore'));" style="width:30px; text-align:right;" /> 점</td>
			</tr>
			<tr>
				<th>점수공개</th>
				<td><input type="checkbox" id="scoreOpenYnChk" name="scoreOpenYnChk" class="choice" /> 네 (본인에게만 점수가 공개됩니다.) </td>
			</tr>
			<tr>
				<th><span class="requare">*</span>내용</th>
				<td><textarea id="content" name="content" rows="5" placeholder="(ex)토론내용">${discussReadVO.content}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td class="left">
				<c:if test="${!empty resultFile}">
						<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:com.deleteFile('${resultFile.atchFileId}|${resultFile.fileSn}', '/lu/discuss/goUpdateDiscuss.do?discussId=${discussVO.discussId}' )">[삭제]</a><br />
				</c:if>
				<c:if test="${empty resultFile}">
					<input type="text" id="file1" name="file1" style="width:50%;" readonly ">
					<span>
						<a href="#@" class="checkfile">파일선택</a>
						<input type="file" class="file_input_hidden" name="file-input" id="file-input" onchange="javascript: document.getElementById('file1').value = this.value" />
					</span>
					<span id="fileSizeName">0KB / 10M</span>
				</c:if>
				</td>
			</tr>
		</tbody>
	</table><!-- E : 토론 -->

	<div class="btn-area align-right mt-010">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">취소</a>
		<a href="#fn_formSave" onclick="javascript:fn_formSave(); return false" class="orange">저장</a>
	</div><!-- E : btn-area -->

</div><!-- E : content-area -->

</form>




