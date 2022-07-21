<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
						<h2>팀프로젝트</h2>
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
	
	// 팀구성방식에 따른 세부영역 표시
	$("[name='compositionType']").change(function() {
		if(this.value == 'N'){
			$("#custTeamArea").show();
		}else if(this.value == 'C'){
			$("#custTeamArea").hide();
			$("#teamCnt").val( $("#compCount").val() );
		}else if(this.value == 'M'){
			$("#custTeamArea").show();
		}
		
		$("#teamCnt").focus();
	});
	
	// 팀 수에 따른 팀별 소속인원 수 자동계산
	$("#teamCnt").blur(function() {
		$("#numberOfTeamMembers").html(Math.ceil( Number($("#totStdCnt").val())  / this.value ));
	});
}

/* 화면이 로드된후 처리 초기화 */ 
function initHtml() {
		// 제출기간
		com.datepickerDateFormat('submitStartDate');
		com.datepickerDateFormat('submitEndDate');
		
		// 프로젝트기간
		com.datepickerDateFormat('projectStartDate');
		com.datepickerDateFormat('projectEndDate');
}


/* 화면이 로드된후 에디터 기본옵션 설정 초기화 */ 
function initEditor() {
	//Smart Editor
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "projectDesc",
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
	
	// 레이어팝업의 임시엘리먼트 모두 제거.
	$("#selPopTbl tbody").html("");
	
	var score = $("#score").val();

	
	if( $("#projectName").val()=="" ){
		alert("팀프로젝트 주제를 입력하세요.");
		return;
	}
	
	if( $("#projectStartDate").val()==""|| $("#projectEndDate").val()==""){
		alert("프로젝트기간을 입력하세요.");
		return;
	}
	
	if( $("#submitStartDate").val()==""|| $("#submitEndDate").val()==""){
		alert("제출기간을 입력하세요.");
		return;
	}

	if(score>10){
		alert("배점은 10점이하로 입력하셔야합니다.");
		$("#score").val("10");
		return;
	}
	
	if(!validTeam()){
		return;
	}
	
	
	
	if (  confirm("저장 하시겠습니까?")) {
		var data =oEditors.getById["projectDesc"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("필수항목을 입력해 주세요.");
			oEditors.getById["projectDesc"].exec("FOCUS"); 
			return false;
		}
		$("#projectDesc").val(data);
		var reqUrl =  "/lu/teamproject/insertTeamproject.do";
		$("#frmTeamproject").attr("target", "_self");
		$("#frmTeamproject").attr("action", reqUrl);
		$("#frmTeamproject").submit();
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

// 팀생성 및 수동 선택에 사용될 학생정보.
var teamGroupStd = ${listStudents};

// 팀생성 유효성 체크
function validTeam(){
	var result = true;
	var teamCnt = $("#teamCnt").val();
	
	
	if( !(teamCnt.length == 0 || !Number(teamCnt)) ){
		
		// 팀생성을 하지않았다면 패스.
		if( $("[type='checkbox'][name='teamGroup_01']").length == 0){
			return result;
		}
		
		for(var i=1; i <= teamCnt; i++){
			groupNum = com.addToZero(i,2);
			
			// 팀인원 존재여부체크, 팀원배정 안되있으면 아래 처리.
			if($("[type='radio'][name='teamGroup_"+groupNum+"_leaderYn']").length == 0){
				alert("팀 인원은 1명 이상으로 설정 가능합니다.");
				result = false;
				break;
			}
		}
		
	}
	
	return result;
}

// 팀생성
function generateTeam(){
	
	var teamCnt = $("#teamCnt").val();
	
	if(teamCnt.length == 0 || !Number(teamCnt)){
		alert("생성하려는 팀 수를 입력 해 주세요.");
		$("#teamCnt").focus();
		return;
	}
	
	// 팀 그룹생성
	$("#groupTbl tbody").html("");
	var selVal = $("[name='compositionType']:checked").val();
	var groupNum="";
	for(var i=1; i <= teamCnt; i++){
		
		groupNum = com.addToZero(i,2);
		
		generateTeamGroup(groupNum);
	}
	
	
	var student;
	var cnt=0;
		
	if(selVal == "N"){ // 학번순
		
		groupNum = '01';
		for(var j=0; j < teamGroupStd.length; j++){
			
			student = teamGroupStd[j];
			generateTeamGroupStd(groupNum, student.memId, student.memName);
			cnt++;
			if(cnt == Number($("#numberOfTeamMembers").html())){
				groupNum = com.addToZero(Number(groupNum) +1, 2);
				cnt = 0;
			}
		}
	
	}else if(selVal == "C"){ // 기업별
		
		for(var j=0; j < teamGroupStd.length; j++){
			
			student = teamGroupStd[j];
			
			
			if( $("[name='teamCompany_"+student.companyId+"']").length == 0){
				// 기 생성된 회사 그룹이 없다면.
				
				// 비어있는 그룹을 찾는다
				for(var i=1; i <= teamCnt; i++){
					groupNum = com.addToZero(i,2);
					if($("#teamGroup_"+groupNum).html().length == 0){
						break;
					}
				}
				
				// 찾아낸 비어있는 그룹에 - 새로운 회사별 묶음을 구분할 오브젝트를 추가한다.
				$("#teamGroup_"+groupNum).append("<input type='hidden' name='teamCompany_"+student.companyId+"' id='teamCompany_"+student.companyId+"' />");
			}else{
				// 기 생성된 회사 그룹이 있다면 상위 엘리먼트에서 그룹값을 가져옴.
				groupNum = $("#teamCompany_"+student.companyId)[0].parentElement.id.replace("teamGroup_","");
			}
			generateTeamGroupStd(groupNum, student.memId, student.memName);
		}
	}else if(selVal == "M"){ // 수동
		
	}
	
	// 팀장 자동 선택
	if($("#teamLeaderAutoYn:checked").val() =='Y'){
		for(var i=1; i <= teamCnt; i++){
			groupNum = com.addToZero(i,2);
			
			// 그룹별 첫번째 대상을 기본 팀장으로 설정.
			$("[name='teamGroup_"+groupNum+"_leaderYn']:first").attr({"checked":"checked"});
		}
	}
}

function generateTeamGroup(groupNum){
	var groupHtml = "";
	
	groupHtml += "<tr>\r\n";
	groupHtml += "		<td>";
	groupHtml += groupNum+"팀";
	groupHtml += "<input type='checkbox' name='teamGroup_"+groupNum+"' value='"+groupNum+"' checked='checked' style='display:none;' />";
	groupHtml += "		</td>\r\n";
	
	groupHtml += "		<td class=\"left\" id='teamGroup_"+groupNum+"'></td>\r\n";
	
	groupHtml += "<td><a href='#!' onclick='javascript:showLearningPop(\""+groupNum+"\");' class='btn-full-blue'>선택</a></td>\r\n";
	
	groupHtml += "</tr>\r\n";
	$("#groupTbl tbody").append(groupHtml);
	
}

function generateTeamGroupStd(groupNum, memId, memName){
	var tdEl = $("#teamGroup_"+groupNum);
	stdHtml = "<span id=\"teamGroupMemId_"+memId+"\" style=\"width: auto; float: none; color: #666; \">";
	stdHtml += "<input type='checkbox' name='teamGroup_"+groupNum+"_memId' value='"+memId+"' checked='checked' style='display:none;' />";
	stdHtml += "<input type='radio' name='teamGroup_"+groupNum+"_leaderYn' value='"+memId+"' class='choice'/> "+memName;
	stdHtml += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	stdHtml += "</span>";
	
	tdEl.append(stdHtml);
}

function showLearningPop(targetGroupNum){
	groupNum = targetGroupNum;
	
	var tbody = $("#selPopTbl tbody");
	var saveBtnArea = $(".saveBtnArea");
	
	tbody.html("");
	saveBtnArea.html("");
	
	var tmpHtml = "";
	var isDisableTarget = false;
	var target;
	var isDefaultSelObj = false;
	for(var j=0; j < teamGroupStd.length; j++){
		
		student = teamGroupStd[j];
		
		target = $("#teamGroupMemId_"+student.memId);
		if(target.length > 0 && target[0].parentElement.id.replace("teamGroup_","") != groupNum){
			isDisableTarget = true;
			isDefaultSelObj = false;
		}else{
			isDisableTarget = false;
			if(target.length > 0){
				isDefaultSelObj = true;
			}
		}
		
		tmpHtml = "<tr>";
		tmpHtml += "<td><input type='checkbox' value='"+student.memId+"' data-mem-name='"+student.memName+"' "+((isDisableTarget)? " disabled='disabled' ":"")+((isDefaultSelObj)? "checked='checked'":"")+"  /></td>";
		tmpHtml += "<td>"+student.memId+"</td>";
		tmpHtml += "<td>"+student.memName+"</td>";
		tmpHtml += "<td>"+((!!student.companyName)? student.companyName:"")+"</td>";
		tmpHtml += "<tr>";
		
		tbody.append(tmpHtml);
		
	}
	
	// 저장버튼 추가
	saveBtnArea.append("<a href='#!' class='orange' onclick='javascript:saveGroup(\""+groupNum+"\");' >저장</a>");
	
	layer_open('manualSelection');
	
}

function saveGroup(targetGroupNum){
	
	var target;
	
	// 선택되지 않은객체는 삭제한다.
	$("input:enabled:not(:checked)", $(".layer")).each(function( idx ) {
		$("#teamGroupMemId_"+this.value).remove();
	});
	
	// 선택된 객체는 현재그룹에 추가한다.
	$("input:enabled:checked", $(".layer")).each(function( idx ) {
		target = $("#teamGroupMemId_"+this.value);
		if( target.length == 0){
			// 기존재 하지 않는, 추가선택자만 현재 그룹에 추가.
			generateTeamGroupStd(targetGroupNum, this.value, this.dataset.memName);
		}
	});
	
	$('a.cbtn',$('.layer')).click();
}

// 레이어 팝업용 함수.
function layer_open(el){

	var temp = $('#' + el);
	var bg = temp.prev().hasClass('bg');	//dimmed 레이어를 감지하기 위한 boolean 변수

	if(bg){
		$('.layer').fadeIn();	//'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
	}else{
		temp.fadeIn();
	}

	// 화면의 중앙에 레이어를 띄운다.
	if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
	else temp.css('top', '0px');
	if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
	else temp.css('left', '0px');

	temp.find('a.cbtn').click(function(e){
		if(bg){
			$('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
		}else{
			temp.fadeOut();
		}
		e.preventDefault();
	});

	$('.layer .bg').click(function(e){	//배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
		$('.layer').fadeOut();
		e.preventDefault();
	});

}


//--> 
</script>

						<h2>팀프로젝트 생성</h2> 
<form:form commandName="frmTeamproject" name="frmTeamproject" method="post" enctype="multipart/form-data" >

<input type="hidden" name="yyyy" value="${currProcVO.yyyy}" />
<input type="hidden" name="term" value="${currProcVO.term}" />
<input type="hidden" name="subjectCode" value="${currProcVO.subjectCode}" />
<input type="hidden" name="subClass" value="${currProcVO.subClass}" />
<input type="hidden" name="compCount"  id="compCount" value="${currProcVO.compCount}" />
<input type="hidden" name="totStdCnt"  id="totStdCnt" value="${currProcVO.count}" />



					<div class="group-area">
						<table class="type-write">
							<colgroup>
									<col style="width:130px" />
									<col style="width:*" />
									<col style="width:130px" />
									<col style="width:*" />
								</colgroup>
							<tbody>
								<tr>
									<th>프로젝트 주제</th>
									<td colspan="3"><input type="text" name="projectName" id="projectName" style="width:97%;" /></td>
								</tr>
								<tr>
									<th>프로젝트 기간</th>
									<td colspan="3">
										<input type="text" name="projectStartDate" id="projectStartDate" value="" style="width:65px" />
										  ~ 
										<input type="text" name="projectEndDate" id="projectEndDate"  value="" style="width:65px" />
									</td>
								</tr>
								<tr>
									<th>과제제출 기간</th>
									<td colspan="3">
										<input type="text" name="submitStartDate" id="submitStartDate" value="" style="width:65px" />
										  ~ 
										<input type="text" name="submitEndDate" id="submitEndDate"  value="" style="width:65px" />
									</td>
								</tr>
								<tr>
									<th>제출구분</th>
									<td>
										<input type="radio" name="submitType" value="T" class="choice" checked />&nbsp;&nbsp;팀장만 제출
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="submitType" value="I" class="choice" />&nbsp;&nbsp;개별 제출
									</td>
									<th>지각제출</th>
									<td>
										<input type="radio" name="submitLateYn" value="Y" class="choice" checked />&nbsp;&nbsp;허용
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="submitLateYn" value="N" class="choice" />&nbsp;&nbsp;불허
									</td>
								</tr>
								<tr>
									<th>평가</th>
									<td>
										<input type="radio" name="evalYn" value="Y" class="choice" checked />&nbsp;&nbsp;Y
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="evalYn" value="N" class="choice" />&nbsp;&nbsp;N
									</td>
									<th>배점</th>
									<td><input type="number" name="score" id="score" value="0"  min="0" max="9" style="width:30px; text-align:right;"  /> 점
									</td>
								</tr>
								<tr>
									<th>내용</th>
									<td colspan="3"><textarea name="projectDesc" id="projectDesc" rows="5"></textarea></td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td colspan="3">
										<input type="text" id="atchFiles" name="atchFiles" style="width:50%;" readonly  onchange="fileCheck(this.form.atchFiles)">
										<span>
											<a href="#@" class="checkfile">파일선택</a>
											<input type="file" class="file_input_hidden" name="file-input" id="file-input"  onchange="javascript:document.getElementById('atchFiles').value = this.value" />
										</span>
										<span id="fileSizeName">0KB / 10M</span>
									</td>
								</tr>
								<tr>
									<th>팀구성</th>
									<td colspan="3">
										<input type="radio" name="compositionType" value="N" class="choice" checked />&nbsp;&nbsp;학번
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="compositionType" value="C" class="choice" />&nbsp;&nbsp;기업
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="compositionType" value="M" class="choice" />&nbsp;&nbsp;수동
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										(총인원:${currProcVO.count })
										<input type="text" name="teamCnt" id="teamCnt" value="" style="width:30px" /> 팀 
										<span id="custTeamArea" style="width: auto;" >
										(<span id="numberOfTeamMembers" style="width: auto;" >5</span>명/1팀)
										</span>
										<span id="createBtn" style="width: auto;" >
										&nbsp;&nbsp;<a href="#!" class="btn-full-blue" onclick="generateTeam();">생성</a>
										</span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="checkbox" name="teamLeaderAutoYn" id="teamLeaderAutoYn" value="Y" class="choice" /> 팀장 자동선정
									</td>
								</tr>
							</tbody>
						</table>
						
						
						<table class="type-2 mt-010" id="groupTbl">
							<colgroup>
								<col style="width:130px" />
								<col style="width:*" />
								<col style="width:60px" />
							</colgroup>
							<thead>
								<tr>
									<th>팀명</th>
									<th>팀원</th>
									<th>선택</th>
								</tr>	
							</thead>
							<tbody>
							
							</tbody>
						</table>
					</div>
</form:form>
						<div class="btn-area align-right mt-010">
							<a href="/lu/teamproject/listTeamproject.do" class="gray-1 float-left">취소</a><a href="#" onclick="javascript:fn_formSave();" class="orange">등록</a>
						</div><!-- E : btn-area -->

<style type="text/css">
	.layer {display:none; position:fixed; _position:absolute; top:0; left:0; width:100%; height:100%; z-index:100; }
		.layer .bg {position:absolute; top:0; left:0; width:100%; height:100%; background:#000; opacity:.5; filter:alpha(opacity=50);}
		.layer .pop-layer {display:block;}

	.pop-layer {display:none; position: absolute; top: 50%; left: 50%; width: 520px; height:auto;  background-color:#fff; border: 5px solid #3571B5; z-index: 10; }	
	.pop-layer .pop-container {padding: 20px 25px;}
	.pop-layer p.ctxt {color: #666; line-height: 25px;}
	.pop-layer .btn-r {width: 100%; margin:10px 0 20px; padding-top: 10px; border-top: 1px solid #DDD; text-align:right;}

	a.cbtn {display:inline-block; height:25px; padding:0 14px 0; border:1px solid #304a8a; background-color:#3f5a9d; font-size:13px; color:#fff; line-height:25px;}	
	a.cbtn:hover {border: 1px solid #091940; background-color:#1f326a; color:#fff;}
</style>
						
						<div class="layer">
							<div class="bg"></div>
							<div id="manualSelection" class="pop-layer">
								<div class="pop-container">
									<div class="pop-conts">
										<div class="btn-r">
											<a href="#" class="cbtn">닫기</a>
										</div>
										<!--content //-->
										<div style="width:480px; height: 600px; overflow: scroll;" >
											<table class="type-2 mt-010" id="selPopTbl" >
												<colgroup>
													<col style="width:60px" />
													<col style="width:120px" />
													<col style="width:90px" />
													<col style="width:180px" />
												</colgroup>
												<thead>
													<tr>
														<th>선택</th>
														<th>학번</th>
														<th>이름</th>
														<th>기업명</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
										<div class="btn-r btn-area saveBtnArea">
										</div>
										<!--// content-->
									</div>
								</div>
							</div>
						</div>

