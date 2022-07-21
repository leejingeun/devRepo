<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

 						<h2>훈련일지</h2>
 
<script type="text/javascript" src="/js/jquery/jquery.raty.js"></script>
<script type="text/javascript">
<!--
$(document).ready(function() {
	
	com.datepickerDateFormat('startDate');

});

var tempListSeq="";
var listSeq="";
var listName="";
var listMemId="";
var tempIndex="0";
var tempSave=true;

/*************************************************************
레이어 팝업창
**************************************************************/
var showLearningpop = function(){
	$(".student-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop = function(){
	$(".student-area,.popup-bg").removeClass("open");
	// 선택값입력
  	showRegularAdd(tempIndex);
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



function fn_search(){
	
	var reqUrl = "/lu/traning/listTraningNote.do";
 
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").submit();

}
function fn_checkboxAll(param ){
	 
	$('#frmTraning' +param +' input:checkbox[name="checkMember"]').each(function(){
		if($(this).is(":checked") == true){
			$('.checkMember'+param).prop('checked',false);
		}else{
			$('.checkMember'+param).prop('checked',true);			
		}
	});
}
function fn_insert(index){
	
	if($("#review"+index).val()==""){
		alert("총평을 입력하세요");
		$("#review"+index).focus();
		return;
	} 
	var reqUrl = "/lu/traning/goInsertTraningNote.do";
	$("#frmTraning"+index).attr("action", reqUrl);
	$("#frmTraning"+index).submit();

}


function showRegularAdd(index){


	var checkMemberId="";
	var tempseq =listSeq.split(",");
	var tempname = listName.split(",");
	var tempid = listMemId.split(",");
	
	// 초기화처리
	listSeq="";
	listName="";
	listMemId="";
	
	var html = "";
		
	for(var i=0; tempseq.length>i ; i++){ 
		if(tempseq[i] && tempListSeq.indexOf(tempseq[i])>=0){
			 
			 var  companyNameText = "" ;
			 var  memIdText =tempid[i];
			 checkMemberId += memIdText+","; 
			 var  memNameText = tempname[i];
			 var  memSeqText = tempseq[i];
			 
			 var trIndex = "tr" + index;
			 var achievement = "achievementEnrichment" +index; 
			  
			 html +="<tr id='"+index+trIndex+"'>";
			 html +="<td>" + companyNameText;
			 html += "<input type='hidden' name='memIdArr' id='memIdArr' value='"+memIdText+"'/>" ;
			 html += "<input type='hidden' name='memNmArr' id='memNmArr' value='"+memNameText+"'/>" ;
			 html += "<input type='hidden' name='traniningNoteDetailIdArr' id='traniningNoteDetailIdArr'/>" ;
			 html += "<input type='hidden' name='traniningNoteMasterIdArr' id='traniningNoteMasterIdArr'/>" ;
			 html += "</td>";
			 html +="<td>"+memIdText+"</td>";
			 html +="<td>"+memNameText+"</td>";
			 html +="<td><input type='text' style='width:95%;' name='studyTimeArr' /></td>";
			 html +="<td id='"+memIdText.trim()+"result'></td>"; 
			 html +="<td class='left'><input type='text' style='width:97%;' name='bigoArr'/></td>";
			 html +="<td> <a href='javascript:removeMemberHtml(\""+index+"\",\""+trIndex+"\");'>삭제</a>  </td>";
			 html +="</tr>";
			 
			 $("#traningNoteMemberSeqs"+index).val($("#traningNoteMemberSeqs"+index).val()+","+memSeqText);
			 $("#traningNoteMemberIds"+index).val($("#traningNoteMemberIds"+index).val()+","+memIdText);
			 $("#traningNoteMemberNames"+index).val($("#traningNoteMemberNames"+index).val()+","+memNameText);
			 tempSave = false;
		}
	 
	}
 

	$("#regularTr"+index).prepend(html);
	
	var memberIdlist = checkMemberId.split(",");
	
	for(var x=0;memberIdlist.length>x ; x++){
		$(function() {
			$('#'+memberIdlist[x].trim()+'result').raty({ 
				cancel :true,
				score: 5,
				path : "/images/oklms/std/inc" 
			});
		});			
	}

}
var indexNum=0;
function showRegular(index){
	if(indexNum >= Number(index)){
		index = Number(indexNum)+1;	
	}
	indexNum = index;
	$("#regular").show(); 
	var html = "";  
	 html +="<form name='frmTraningEnrichment${statustop.index}' id='frmTraningEnrichment"+index+"' method='post' >";
	 html +="										<input type='hidden' id='achievementEnrichment' name='achievementEnrichment' />";
	 html +="										<input type='hidden' id='state' name='state' value='W' />";
	 html +="										<input type='hidden' id='startTime' name='startTime' value=''/>";
	 html +="										<input type='hidden' id='finishTime' name='finishTime' value='' />";
	 html +="										<input type='hidden' id='planTime' name='planTime' value='' />";
	 html +="										<input type='hidden' id='weekId' name='weekId' value='${TraningNoteVO.weekId}'/>";
	 html +="										<input type='hidden' id='timeId' name='timeId' value=''/>";
	 html +="										<input type='hidden' name='weekCnt' value='${TraningNoteVO.weekCnt}' />";
	 html +="										<input type='hidden' id='memId' name='memId'/>";
	 html +="										<input type='hidden' id='traniningNoteDetailId' name='traniningNoteDetailId'  />";
	 html +="										<input type='hidden' id='traniningNoteMasterId' name='traniningNoteMasterId' />";
	 	 
	 html +="	<input type='hidden' name='traningNoteMemberSeqs' id='traningNoteMemberSeqs"+index+"'    />";
	 html +="	<input type='hidden' name='traningNoteMemberIds' id='traningNoteMemberIds"+index+"'   />";
	 html +="	<input type='hidden' name='traningNoteMemberNames' id='traningNoteMemberNames"+index+"' />";
	 	 
	 html +="								<dt> 보강훈련</dt>";
	 html +="								<dd style='display:block;'>";
	 html +="								<div class='mt-010 mb-020'>";
	 html +="								<input type='text' name='studyDate' id='startDate"+index+"' style='width:75px;' />";
	 html +="								시작 :<select name='traningStHour'>";
	 html +="											<option value='09' >오전 9시</option>";
	 html +="											<option value='10' >오전 10시</option>";
	 html +="											<option value='11' >오전 11시</option>";
	 html +="											<option value='12' >오전 12시</option>";
	 html +="											<option value='13' >오후 1시</option>";
	 html +="											<option value='14' >오후 2시</option>";
	 html +="											<option value='15' >오후 3시</option>";
	 html +="											<option value='16' >오후 4시</option>";
	 html +="											<option value='17' >오후 5시</option>";
	 html +="											<option value='18' >오후 6시</option>";
	 html +="									</select>";
	 html +="									<select name='traningStMin'>";
	 html +="										<option value='00' >0 분</option>";
	 html +="										<option value='10' >10 분</option>";
	 html +="										<option value='20' >20 분</option>";
	 html +="										<option value='30' >30 분</option>";
	 html +="										<option value='40' >40 분</option>";
	 html +="										<option value='50' >50 분</option>";
	 html +="									</select> ~";
	 html +="								종료 :<select name='traningEdHour'>";
	 html +="											<option value='09' >오전 9시</option>";
	 html +="											<option value='10' >오전 10시</option>";
	 html +="											<option value='11' >오전 11시</option>";
	 html +="											<option value='12' >오전 12시</option>";
	 html +="											<option value='13' >오후 1시</option>";
	 html +="											<option value='14' >오후 2시</option>";
	 html +="											<option value='15' >오후 3시</option>";
	 html +="											<option value='16' >오후 4시</option>";
	 html +="											<option value='17' >오후 5시</option>";
	 html +="											<option value='18' >오후 6시</option>";
	 html +="									</select>";
	 html +="									<select name='traningEdMin'>";
	 html +="										<option value='00' >0 분</option>";
	 html +="										<option value='10' >10 분</option>";
	 html +="										<option value='20' >20 분</option>";
	 html +="										<option value='30' >30 분</option>";
	 html +="										<option value='40' >40 분</option>";
	 html +="										<option value='50' >50 분</option>";
	 html +="									</select>";
	 
	 html +="									</div>";
	 html +="									<table class='type-2'>";
	 html +="										<colgroup>";
	 html +="											<col width='15%' />";
	 html +="											<col width='15%' />";
	 html +="											<col width='15%' />";
	 html +="											<col width='8%' />";
	 html +="											<col width='17%' />";
	 html +="											<col width='*' />";
	 html +="											<col width='10%' />";
	 html +="										</colgroup>";
	 html +="										<thead>";
	 html +="											<tr>";
	 html +="												<th>기업명</th>";
	 html +="												<th>학번</th>";
	 html +="												<th>성명</th>";
	 html +="												<th>훈련시간</th>";
	 html +="												<th>성취도</th>";
	 html +="												<th>보강비고 (교육 중 특이사항 등)</th>";
	 html +="												<th>삭제</th>";
	 html +="											</tr>";
	 html +="										</thead>";
	 html +="										<tbody  id='regularTr"+index+"'>";
	  
	 
	 
	 html +="											<tr>";
	 html +="												<th>보강총평</th>";
	 html +="												<td colspan='6' class='left'><textarea name='review' rows='6'></textarea></td>";
	 html +="											</tr>";
	 html +="										</tbody>";
	 html +="									</table>";

	 html +="									<div class='btn-area align-center mt-010'>";
	 html +="										<a href='#!' class='orange' onclick='javascript:fn_memberpage(\""+index+"\");'>학습근로자추가</a>";
	 html +="										<a href='#!' onclick='javascript:fn_insertEnrichment(\""+index+"\");' class='yellow'>저장</a>";
//	 html +="										<a href='#!' onclick='javascript:removeMemberHtml(\""+index+"\",\"frmTraningEnrichment"+index+"\");' class='gray-1'>삭제</a>";
	 html +="									</div>";	 
	 html +="								</dd>";
	 html +="								</form>";
	 
	$("#regular").append(html);
	com.datepickerDateFormat('startDate'+index);

}
//삭제 처리
function removeMemberHtml(index,memberId){
	$("#"+index+memberId).remove();
}
// 보강삭제 처리
function removeMemberHtml(index,memberId){
	$("#"+index+memberId).remove();
}
function removeMember(index,traniningNoteDetailId,traniningNoteMasterId ){
 

	$("#traniningNoteDetailId"+index).val(traniningNoteDetailId);
	$("#traniningNoteMasterId"+index).val(traniningNoteMasterId);
	
	var formData = $("#frmTraningEnrichment"+index).serialize();
	$.ajax({
		type:"POST",
		url:"/lu/traning/deleteTraningNoteEnrichment.do",
		cache:false,
		data:formData,
		success:function(html){
			alert("삭제되었습니다.");
			fn_search();
		},
		error:function(e){
			alert(e);
		}
	});
}
// 훈련시간 변경처리
function  studyTimeArrZeor(values,max,memId,index){
	
	if(values == '0'){
		$('#'+memId+'result'+index+'').raty({ 
			cancel :true,
			score:0,					
			path : "/images/oklms/std/inc" 
		});		
	}else if(values==max){
		$('#'+memId+'result'+index+'').raty({ 
			cancel :true,
			score:5,					
			path : "/images/oklms/std/inc" 
		});			
	}

}

function fn_insertEnrichment(index){

	var reqUrl = "/lu/traning/goInsertTraningNoteEnrichment.do";
	$("#frmTraningEnrichment"+index).attr("action", reqUrl);
	$("#frmTraningEnrichment"+index).submit();

}
function fn_memberpage(index){
 		if(!tempSave){
 			alert("추가한 항목을 먼저 저장후 추가해주세요.");
 			return;
 		}
		listSeq = $( "#traningNoteMemberSeqs"+index ).val();
		listMemId = $( "#traningNoteMemberIds"+index ).val();
		listName = $( "#traningNoteMemberNames"+index ).val();		
 
		tempIndex = index;
		fn_memberLList("1");
}
function fn_memberLList( pageIndex){
 
	$.ajax({
		  type: "POST",
		  url: "/lu/traning/ajaxTraningNote.do",
		  data: { "traningNoteMemberSeqs":listSeq ,"traningNoteMemberIds":listMemId ,"traningNoteMemberNames":listName ,"pageIndex": pageIndex,"subjectCode":"${TraningNoteVO.subjectCode}" , "weekCnt":"${TraningNoteVO.weekCnt}" ,"classId":"${TraningNoteVO.classId }" ,"timeId":"" },
		  success:function( html ) {
			$( "#traningNoteMemberList" ).html( html );
			showLearningpop();
		  }
	});
}
//학습근로자 선택시 
function checkboxClick(param1,memSeq,memName,memId){

	//체크박스 선택 
	if(param1){ 
		if(listSeq.indexOf(memSeq) >=0){

		}else{
	    	listSeq += ','+memSeq;
	    	listName += ','+memName;
	    	listMemId += ','+memId;
	    	tempListSeq +=','+memSeq;
		}
	}else{
		listSeq =	listSeq.replace(','+memSeq,'');
		listName = listName.replace(','+memName,'');
		listMemId = listMemId.replace(','+memId,'');
	}
}
//--> 
</script>
						<h4 class="mb-010"><span>${TraningNoteVO.subjectName} ${TraningNoteVO.classId }반</span>ㅣ ${TraningNoteVO.yyyy}학년도 ${TraningNoteVO.term}학기 </h4>
					<form name="frmTraning" id="frmTraning" method="post">

						<table class="type-1-1 mb-030">
							<colgroup>
								<col style="width:60px" />
								<col style="width:150px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:70px" />
							</colgroup>
							<thead>
								<tr>
									<th>주차</th>
									<th>기간</th>
									<th>능력단위</th>
									<th>능력단위요소</th>
									<th>수업내용</th>
									<th>주간<br />훈련시간</th>
								</tr>
							</thead>

							<tbody>

								<tr>
									<td>
										<select id="weekCnt" name="weekCnt" onchange="javascript:fn_search();">
											<c:forEach var="result" items="${weeklist}" varStatus="status">
											<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq TraningNoteVO.weekCnt }">selected</c:if>>${result.weekCnt}</option>
											</c:forEach>
										</select>
									</td>
									<td>${TraningNoteVO.traningStDate} ~ ${TraningNoteVO.traningEndDate}</td>
									<td>${TraningNoteVO.ncsUnitName}</td>
									<td>${TraningNoteVO.ncsElemName}</td>
									<td class="left">${TraningNoteVO.lessonCn}</td>
									<td>${TraningNoteVO.timeHour}</td>
								</tr>
							</tbody>

						</table>
					</form>						
						<div class="learner-training">

<c:forEach var="timeList" items="${timeList}" varStatus="status">
						 <c:if test="${empty resultSum[status.index].weekId}">

						 <dl>
								<dt>
						 	 훈련시간표를 등록해주세요.  
						 		</dt>
						 </dl> 
						 	 
						 </c:if>
						<c:if test="${!empty resultSum[status.index].weekId}" >
						<form name="frmTraning${status.index}" id="frmTraning${status.index}" method="post">
							<input type="hidden" id="achievement" name="achievement" />
							<input type="hidden" id="studyDate" name="studyDate" value="${resultSum[status.index].traningDate }"/>
							<input type="hidden" id="startTime" name="startTime" value="${resultSum[status.index].traningDate }"/>
							<input type="hidden" id="finishTime" name="finishTime" value="${resultSum[status.index].traningDate }" />
							<input type="hidden" id="traningStHour" name="traningsthour" value="${resultSum[status.index].traningStHour }"/>
							<input type="hidden" id="traningStMin" name="TraningStMin" value="${resultSum[status.index].traningStMin }"/>
							<input type="hidden" id="traningEdHour" name="traningEdHour" value="${resultSum[status.index].traningEdHour }"/>
							<input type="hidden" id="traningEdMin" name="traningEdMin" value="${resultSum[status.index].traningEdMin }"/>
							<input type="hidden" id="planTime" name="planTime" value="${resultSum[status.index].timeHour}" />
							<input type="hidden" id="weekId" name="weekId" value="${resultSum[status.index].weekId}"/>
							<input type="hidden" id="timeId" name="timeId" value="${resultSum[status.index].timeId}"/>
							<input type="hidden" id="weekCntId${status.index}" name="weekCnt" value="${TraningNoteVO.weekCnt}"/>
							<input type="hidden"   name="addyn" value="N"/>
<input type="hidden" id="state" name="state" value="W" />
							<dl>
								<dt>정규수업 : ${resultSum[status.index].traningDate}(${resultSum[status.index].dayOfWeek}) ${resultSum[status.index].traningSt}~${resultSum[status.index].traningEd} (${resultSum[status.index].timeHour}시간)  ${result.traniningNoteMasterId } </dt>
								<dd style="display:block;">


									<table class="type-2">
										<colgroup>
											<!-- col width="5%" /-->
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="8%" />
											<col width="17%" />
											<col width="8%" />
											<col width="*" />
										</colgroup>
										<thead>
											<tr>
												<!--th> <input type="checkbox" id="" name="" onclick="javascript:fn_checkboxAll('${status.index}' );" /></th-->
												<th>기업명</th>
												<th>학번</th>
												<th>성명</th>
												<th>훈련시간</th>
												<th>성취도</th>
												<th>온라인</th>
												<th>비고 (교육 중 특이사항 등)</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="resultlist" items="${resultlistSum[status.index]}" varStatus="statuslist">
											<tr>
												<!--td>
													<input type="checkbox" id="checkMember" class="checkMember${status.index}" name="checkMember" />
													 
												</td-->
												<td>${resultlist.companyName}
												<input type="hidden" id="traniningNoteDetailIdArr1" name="traniningNoteDetailIdArr" value="${resultlist.traniningNoteDetailId}" />
													<input type="hidden" id="" name="traniningNoteMasterIdArr" value="${resultlist.traniningNoteMasterId}" /></td>
												<td>${resultlist.memId} <input type="hidden" name="memIdArr" id="memIdArr" value="${resultlist.memId}"></td>
												<td>${resultlist.memName} <input type="hidden" name="memNmArr" id="memNmArr" value="${resultlist.memName}">  </td>
												<td>
													<c:if test="${!empty resultlist.studyTime }" >
													<%-- <input type="text" id="studyTimeArr" name="studyTimeArr"   style="width:95%;" onchange="javascript:if(this.value > ${TraningNoteVO.timeHour} ){alert('훈련시간은 주간훈련시간보다 적어야합니다.');this.value='${TraningNoteVO.timeHour}';return;};studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');" value="${resultlist.studyTime}"/> --%>
													<!-- 훈련시간 입력 벨리데이션 임시주석처리 -->
													<input type="text" id="studyTimeArr" name="studyTimeArr" maxlength="1"  style="width:95%;" onchange="javascript:studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');" value="${resultlist.studyTime}"/>
													</c:if>
													<c:if test="${empty resultlist.studyTime }" >
													<%-- <input type="text" id="studyTimeArr" name="studyTimeArr"   style="width:95%;" onchange="javascript:if(this.value > ${TraningNoteVO.timeHour} ){alert('훈련시간은 주간훈련시간보다 적어야합니다.');this.value='${TraningNoteVO.timeHour}';return;}studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');"  value="${resultSum[status.index].timeHour}"/> --%>
													<!-- 훈련시간 입력 벨리데이션 임시주석처리 -->
													<input type="text" id="studyTimeArr" name="studyTimeArr" maxlength="1"   style="width:95%;" onchange="javascript:studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');"  value="${resultSum[status.index].timeHour}"/>
													</c:if>
												</td>
									<script type="text/javascript">
									<!--
									$(function() {
											$('#${resultlist.memId}result${status.index+1}').raty({ 
												cancel :true,
												<c:if test="${!empty resultlist.traniningNoteMasterId}">
												score: ${resultlist.achievement},
												</c:if>
												<c:if test="${empty resultlist.traniningNoteMasterId}">
												score:5,
												</c:if>												
												path : "/images/oklms/std/inc" 
											});
									});
									//--> 
									</script>
												<td class="result${status.index+1}" id="${resultlist.memId}result${status.index+1}"></td>								
												<td>${resultlist.attendProgress}%</td>
												<td class="left"><input type="text" style="width:95%;" name="bigoArr" value="${resultlist.bigo}"/></td>
											</tr>
											</c:forEach>
											<tr>
												<th>총평</th>
												<td colspan="6" class="left"><textarea name="review" id="review${status.index}" rows="5" >${resultSum[status.index].review}</textarea></td>
											</tr>
										</tbody>
									</table>

									<div class="btn-area align-center mt-010">
										<a href="#!" class="yellow" onclick="fn_insert('${status.index}');">저장</a>
									</div>
								</dd>
							</dl><!-- E : 정규수업 -->
							</form>
							</c:if>
  </c:forEach>
 <c:set var="maxNum" value="0" />
  <c:forEach var="TraningEnrichmentTimeVO" items="${TraningEnrichmentTimeVO}" varStatus="statustop">
 <c:set var="maxNum" value="${statustop.index+1}" /> 
  <dl  id="regular${statustop.index}" >
<form name="frmTraningEnrichment${statustop.index}" id="frmTraningEnrichment${statustop.index}" method="post" >

										<input type="hidden" id="achievementEnrichment" name="achievementEnrichment" />
										<input type="hidden" id="state" name="state" value="W" />
										<input type="hidden" id="startTime" name="startTime" value="${TraningRegularTimeVO.traningDate }"/>
										<input type="hidden" id="finishTime" name="finishTime" value="${TraningRegularTimeVO.traningDate }" />
										<input type="hidden" id="planTime" name="planTime" value="${TraningRegularTimeVO.timeHour}" />
										<input type="hidden" id="weekId" name="weekId" value="${TraningNoteVO.weekId}"/>
										<input type="hidden" id="timeId" name="timeId" value="${TraningEnrichmentTimeVO.timeId}"/>
										<input type="hidden" name="weekCnt" value="${TraningNoteVO.weekCnt}" />
										<input type="hidden" id="memId" name="memId"/>
										<input type="hidden" id="traniningNoteDetailId${statustop.index}" name="traniningNoteDetailId"  />
										<input type="hidden" id="traniningNoteMasterId${statustop.index}" name="traniningNoteMasterId" value="${TraningEnrichmentTimeVO.traniningNoteMasterId}" />							
								<dt> 보강훈련</dt>
								<dd style="display:block;">
								<div class="mt-010 mb-020">
								<input type="text" name="studyDate" id="startDate" value="${TraningEnrichmentTimeVO.traningDate}"  style="width:75px;" />
								시작 :<select name="traningStHour">
											<option value="09" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '09'}">selected="selected" </c:if>>오전 9시</option>
											<option value="10" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '10'}">selected="selected" </c:if>>오전 10시</option>
											<option value="11" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '11'}">selected="selected" </c:if>>오전 11시</option>
											<option value="12" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '12'}">selected="selected" </c:if>>오전 12시</option>
											<option value="13" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '13'}">selected="selected" </c:if>>오후 1시</option>
											<option value="14" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '14'}">selected="selected" </c:if>>오후 2시</option>
											<option value="15" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '15'}">selected="selected" </c:if>>오후 3시</option>
											<option value="16" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '16'}">selected="selected" </c:if>>오후 4시</option>
											<option value="17" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '17'}">selected="selected" </c:if>>오후 5시</option>
											<option value="18" <c:if test="${TraningEnrichmentTimeVO.traningStHour eq '18'}">selected="selected" </c:if>>오후 6시</option>
									</select>
									<select name="traningStMin">
										<option value="00"  <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '00'}"> selected="selected" </c:if> >0 분</option>
										<option value="10" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '10'}">selected="selected" </c:if> >10 분</option>
										<option value="20" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '20'}">selected="selected" </c:if>>20 분</option>
										<option value="30" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '30'}">selected="selected" </c:if>>30 분</option>
										<option value="40" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '40'}">selected="selected" </c:if>>40 분</option>
										<option value="50" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '50'}">selected="selected" </c:if>>50 분</option>
									</select> ~
								종료 :<select name="traningEdHour">
											<option value="09" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '09'}">selected="selected" </c:if>>오전 9시</option>
											<option value="10" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '10'}">selected="selected" </c:if>>오전 10시</option>
											<option value="11" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '11'}">selected="selected" </c:if>>오전 11시</option>
											<option value="12" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '12'}">selected="selected" </c:if>>오전 12시</option>
											<option value="13" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '13'}">selected="selected" </c:if>>오후 1시</option>
											<option value="14" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '14'}">selected="selected" </c:if>>오후 2시</option>
											<option value="15" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '15'}">selected="selected" </c:if>>오후 3시</option>
											<option value="16" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '16'}">selected="selected" </c:if>>오후 4시</option>
											<option value="17" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '17'}">selected="selected" </c:if>>오후 5시</option>
											<option value="18" <c:if test="${TraningEnrichmentTimeVO.traningEdHour eq '18'}">selected="selected" </c:if>>오후 6시</option>
									</select>
									<select name="traningEdMin">
										<option value="00"  <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '00'}"> selected="selected" </c:if> >0 분</option>
										<option value="10" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '10'}">selected="selected" </c:if> >10 분</option>
										<option value="20" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '20'}">selected="selected" </c:if>>20 분</option>
										<option value="30" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '30'}">selected="selected" </c:if>>30 분</option>
										<option value="40" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '40'}">selected="selected" </c:if>>40 분</option>
										<option value="50" <c:if test="${TraningEnrichmentTimeVO.traningStMin eq '50'}">selected="selected" </c:if>>50 분</option>
									</select>
									 																 									 
									</div>
									<table class="type-2">
										<colgroup>
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="8%" />
											<col width="17%" />
											<col width="*" />
											<col width="10%" />
										</colgroup>
										<thead>
											<tr>
												<th>기업명</th>
												<th>학번</th>
												<th>성명</th>
												<th>훈련시간</th>
												<th>성취도</th>
												<th>보강비고 (교육 중 특이사항 등)</th>
												<th>삭제</th>
											</tr>
										</thead>
<c:set var="memSeqs" value="" />
<c:set var="memIds" value="" />
<c:set var="memNames" value="" />
										<tbody  id="regularTr${statustop.index}">
											<c:forEach var="resultlist" items="${TraningNoteList2[statustop.index]}" varStatus="status">
											<c:set var="memSeqs" value="${memSeqs},${resultlist.memSeq}" />
											<c:set var="memIds" value="${memIds},${resultlist.memId}" />
											<c:set var="memNames" value="${memNames},${resultlist.memName}" />
											<tr id="${statustop.index}tr${status.index}">
												<td> 
													<input type="hidden" id="" name="traniningNoteDetailIdArr" value="${resultlist.traniningNoteDetailId}" />
													${resultlist.companyName}
												</td>
												<td>${resultlist.memId} <input type="hidden" name="memIdArr" id="memIdArr" value="${resultlist.memId}"></td>
												<td>${resultlist.memName} <input type="hidden" name="memNmArr" id="memNmArr" value="${resultlist.memName}">  </td>
												<td><input type="text" style="width:95%;" name="studyTimeArr" value="${resultlist.studyTime}"/></td>
									<script type="text/javascript">
									<!--
									$(document).ready(function() {
											$('#${resultlist.memId}${statustop.index}resultEnrich${status.index+1}').raty({ 
												cancel :true,
												score: ${resultlist.achievement},
												path : "/images/oklms/std/inc" 
											});
									});	
									//--> 
									</script>												
												<td   id="${resultlist.memId}${statustop.index}resultEnrich${status.index+1}"></td>	
												<td class="left"><input type="text" style="width:95%;" name="bigoArr" value="${resultlist.bigo}"/></td>
												<td><a href="#" onclick="removeMember('${statustop.index}','${resultlist.traniningNoteDetailId}' , '${resultlist.traniningNoteMasterId}');removeMemberHtml('${statustop.index}','tr${status.index}');" >삭제</a>  </td>
											</tr>
											</c:forEach>
											<input type="hidden" name="traningNoteMemberSeqs" id="traningNoteMemberSeqs${statustop.index}"  value="${memSeqs}" />
											<input type="hidden" name="traningNoteMemberIds" id="traningNoteMemberIds${statustop.index}"  value="${memIds}" />
											<input type="hidden" name="traningNoteMemberNames" id="traningNoteMemberNames${statustop.index}"  value="${memNames}" />
											<tr>
												<th>보강총평</th>
												<td colspan="6" class="left"><textarea name="review" rows="6">${TraningEnrichmentTimeVO.review}</textarea></td>
											</tr>
										</tbody>
									</table>

									<div class="btn-area align-center mt-010">
										<a href="#!" class="orange" onclick="javascript:fn_memberpage('${statustop.index}');">학습근로자 추가</a>
										<a href="#!" onclick="javascript:fn_insertEnrichment('${statustop.index}');" class="yellow">저장</a>
									</div>
								</dd>
</form>
							</dl><!-- E : 보강수업 -->
 						
  </c:forEach>
 
  <dl  id="regular" >
  </dl>
  
    
  									<div class="btn-area align-right mt-010">
										<a href="#!" class="orange" onclick="showRegular('${maxNum}');">보강훈련 추가</a>
									</div>
  
 						</div>	
				
<ul id="student-popup">
	<li class="student-area" id="traningNoteMemberList" >

	</li>
	<li class="popup-bg"></li>
</ul><!-- E : student-popup --> 						