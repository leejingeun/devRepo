<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
<!--

function fn_lec_menu_display(value){
	
	var reqUrl = "/mm/traning/listTraning.do";
	var params = value.split("||");
	
	$("#subjectTraningType").val(params[0]);
	$("#yyyy").val(params[1]);
	$("#term").val(params[2]);
	$("#subjectCode").val(params[3]);
	$("#classId").val(params[4]);
	$("#subjectName").val(params[5]);
	$("#subjectType").val(params[6]);
	$("#onlineType").val(params[7]);
	 
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").submit();
	 
}

function fn_insert(index){
	
	if($("#review"+index).val()==""){
		alert("총평을 입력하세요");
		$("#review"+index).focus();
		return;
	} 
	var reqUrl = "/mm/traning/goInsertTraning.do";
	$("#frmTraning"+index).attr("action", reqUrl);
	$("#frmTraning"+index).submit();

}

function fn_insertEnrichment(index){

	var reqUrl = "/mm/traning/insertTraning.do";
	$("#frmTraningEnrichment"+index).attr("action", reqUrl);
	$("#frmTraningEnrichment"+index).submit();

}
 
//삭제 처리
function removeMemberHtml(index,memberId){
	$("#"+index+memberId).remove();
}
function removeMember(index,traniningNoteDetailId,traniningNoteMasterId ){
 

	$("#traniningNoteDetailId"+index).val(traniningNoteDetailId);
	$("#traniningNoteMasterId"+index).val(traniningNoteMasterId);
	
	var formData = $("#frmTraningEnrichment"+index).serialize();
	$.ajax({
		type:"POST",
		url:"/mm/traning/deleteTraning.do",
		cache:false,
		data:formData,
		success:function(html){
			alert("삭제되었습니다.");
		},
		error:function(e){
			alert(e);
		}
	});
}
//--> 
</script>
			<div id="container">
<form name="frmTraning" id="frmTraning" method="post">

<input type="hidden" name="subjectTraningType" id="subjectTraningType" value="${traningNoteVO.subjectTraningType}">
<input type="hidden" name="yyyy" id="yyyy" value="${traningNoteVO.yyyy}">
<input type="hidden" name="term" id="term" value="${traningNoteVO.term}">
<input type="hidden" name="subjectCode" id="subjectCode" value="${traningNoteVO.subjectCode}">

<input type="hidden" name="subjectName" id="subjectName" value="${traningNoteVO.subjectName}">
<input type="hidden" name="classId" id="classId" value="${traningNoteVO.classId}">
<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="">
<input type="hidden" name="subjectType" id="subjectType" value="">
<input type="hidden" name="onlineType" id="onlineType" value="">
				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${traningNoteVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}"  <c:if test="${traningNoteVO.subjectCode eq menuProc.subjectCode }">selected</c:if> >${menuProc.subjectName} ${menuProc.subClass}반 </option>
						</c:forEach>							
							
						</select>
						<select id="weekCnt" name="weekCnt" onchange="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">
							<option value="">주차 선택</option>
							<c:forEach var="weeklist" items="${weeklist}" varStatus="status">
							<option value="${weeklist.weekCnt}" <c:if test="${traningNoteVO.weekCnt eq weeklist.weekCnt }">selected</c:if> >${weeklist.weekCnt}</option>
							</c:forEach>
							 
						</select>
						<a href="#!" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
					</li>
				</ul><!-- E : search-area -->
				
</form>					

				<div id="contents-area">
<c:if test="${not empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span> ${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
					</h4>

					<table class="type-1">
						<colgroup>
							<col width="25%" />
							<col width="*" />
							<col width="20%" />
							<col width="18%" />
						</colgroup>
						<tbody>
							<tr>
								<th>교과구분</th>
								<td>${currProcVO.subjectTraningType }</td>
								<th>과정구분</th>
								<td>
								  <c:choose>
								       <c:when test="${currProcVO.subjectType eq 'NORMAL' }">
											일반
								       </c:when>
								       <c:when test="${currProcVO.subjectType eq 'HSKILL'}">
											고숙련
								       </c:when>
								       <c:otherwise>
								
								       </c:otherwise>
								  </c:choose>
								</td>
							</tr>
							<tr>
								<th>훈련시간</th>
								<td>${currProcVO.traningHour }시간</td>
								<th>학점</th>
								<td>${currProcVO.point }학점</td>
							</tr>
							<tr>
								<th>온라인교육</th>
								<td>
								    <c:choose>
								       <c:when test="${currProcVO.onlineType eq 'NONE' }">
											없음
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'ONLINE' }">
											온라인
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'FLIPPED'}">
											플립러닝
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'BLENDED'}">
											블렌디드
								       </c:when>
								       <c:otherwise>
								
								       </c:otherwise>
								   </c:choose>								
								</td>
								<th>선수여부</th>
								<td>
									${currProcVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}
									
								</td>
							</tr>
						</tbody>
					</table>
 
<c:forEach var="timeList" items="${timeList}" varStatus="status">
						 <c:if test="${empty resultSum[status.index].weekId}">

						 <dl class="learner-training">
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
							<input type="hidden" id="weekCntId${status.index}" name="weekCnt" value="${traningNoteVO.weekCnt}"/>
							<input type="hidden"   name="addyn" value="N"/>
							
<input type="hidden" name="yyyy"   value="${traningNoteVO.yyyy}" />
<input type="hidden" name="term"   value="${traningNoteVO.term}" />
<input type="hidden" name="subjectCode" value="${traningNoteVO.subjectCode}" />							
<input type="hidden" name="subjectTraningType"   value="${traningNoteVO.subjectTraningType}" />
<input type="hidden" name="classId" id="classId" value="${traningNoteVO.classId}" />

<input type="hidden" id="state" name="state" value="W" />
							<dl class="learner-training">
								<dt>정규수업 : ${resultSum[status.index].traningDate}(${resultSum[status.index].dayOfWeek}) ${resultSum[status.index].traningSt}~${resultSum[status.index].traningEd} (${resultSum[status.index].timeHour}시간)  ${result.traniningNoteMasterId } </dt>
								<dd>
									<table class="type-2">
										<colgroup>
										
											<col width="*" />
											<col width="18%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />											
											
										</colgroup>
										<thead>
											<tr>
												<th>기업명</th>
												<th>성명</th>
												<th>훈련<br />시간</th>
												<th>성취도</th>
												<th>온라인</th>
												<th>비고</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="resultlist" items="${resultlistSum[status.index]}" varStatus="statuslist">
											<tr>
 
												<td>${result.companyName}
												<input type="hidden" id="traniningNoteDetailIdArr1" name="traniningNoteDetailIdArr" value="${resultlist.traniningNoteDetailId}" />
													<input type="hidden" id="" name="traniningNoteMasterIdArr" value="${resultlist.traniningNoteMasterId}" /></td>
												<td>${resultlist.memName} <input type="hidden" name="memNmArr" id="memNmArr" value="${resultlist.memName}"><input type="hidden" name="memIdArr" id="memIdArr" value="${resultlist.memId}"></td>
												<td>
													<c:if test="${!empty resultlist.studyTime }" >
													<input type="text" id="studyTimeArr" name="studyTimeArr" style="width:70%;" onchange="javascript:studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');" value="${resultlist.studyTime}"/>
													</c:if>
													<c:if test="${empty resultlist.studyTime }" >
													<input type="text" id="studyTimeArr" name="studyTimeArr" style="width:70%;" onchange="javascript:studyTimeArrZeor(this.value,'${resultSum[status.index].timeHour}','${resultlist.memId}','${status.index+1}');"  value="${resultSum[status.index].timeHour}"/>
													</c:if>
												</td>
 												<td>
													<input type="text" id="score" style="width:70%;" name="score" value="<c:if test="${!empty resultlist.traniningNoteMasterId}">${resultlist.achievement}</c:if><c:if test="${empty resultlist.traniningNoteMasterId}">5</c:if>" />												
												</td>								
												<td>${resultlist.attendProgress}%</td>
												<td><input type="text" style="width:70%;" name="bigoArr" value="${resultlist.bigo}"/></td>
											</tr>
											</c:forEach>
											<tr>
												<th>총평</th>
												<td colspan="5" ><textarea name="review" id="review${status.index}" style="width:90%;"  rows="5" >${resultSum[status.index].review}</textarea></td>
											</tr>
										</tbody>
									</table>

									<div class="btn-area align-center mt-010">
										<a href="#!" class="yellow" onclick="javascript:fn_insert('${status.index}');">저장</a>
									</div>
								</dd>
							</dl><!-- E : 정규수업 -->
							</form>
							</c:if>
  </c:forEach> 
 
 <c:set var="maxNum" value="0" />
  <c:forEach var="TraningEnrichmentTimeVO" items="${TraningEnrichmentTimeVO}" varStatus="statustop">
 <c:set var="maxNum" value="${statustop.index+1}" />
 <form name="frmTraningEnrichment${statustop.index}" id="frmTraningEnrichment${statustop.index}" method="post" > 
<input type="hidden" name="yyyy"   value="${traningNoteVO.yyyy}" />
<input type="hidden" name="term"   value="${traningNoteVO.term}" />
<input type="hidden" name="subjectCode" value="${traningNoteVO.subjectCode}"/>							
<input type="hidden" name="subjectTraningType"   value="${traningNoteVO.subjectTraningType}" />
<input type="hidden" name="classId" id="classId" value="${traningNoteVO.classId}"/>

										<input type="hidden" id="achievementEnrichment" name="achievementEnrichment" />
										<input type="hidden" id="state" name="state" value="W" />
										<input type="hidden" id="startTime" name="startTime" value="${TraningRegularTimeVO.traningDate }"/>
										<input type="hidden" id="finishTime" name="finishTime" value="${TraningRegularTimeVO.traningDate }" />
										<input type="hidden" id="planTime" name="planTime" value="${TraningRegularTimeVO.timeHour}" />
										<input type="hidden" id="weekId" name="weekId" value="${traningNoteVO.weekId}"/>
										<input type="hidden" id="timeId" name="timeId" value="${TraningEnrichmentTimeVO.timeId}"/>
										<input type="hidden" name="weekCnt" value="${traningNoteVO.weekCnt}" />
										<input type="hidden" id="memId" name="memId"/>
										<input type="hidden" id="traniningNoteDetailId${statustop.index}" name="traniningNoteDetailId"  />
										<input type="hidden" id="traniningNoteMasterId${statustop.index}" name="traniningNoteMasterId" value="${TraningEnrichmentTimeVO.traniningNoteMasterId}" />
  <dl  id="regular${statustop.index}"   class="learner-training" >

							
								<dt> 보강훈련</dt>
								<dd> 
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
								 
									<table class="type-2">
										<colgroup>
								 			<col width="*" />
											<col width="18%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />	
										</colgroup>
										<thead>
											<tr>
												<th>기업명</th>
												<th>성명</th>
												<th>훈련<br/>시간</th>
												<th>성취도</th>
												<th>보강비고</th>
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
												</td>											
												<td>${resultlist.memName} <input type="hidden" name="memNmArr" id="memNmArr" value="${resultlist.memName}"><input type="hidden" name="memIdArr" id="memIdArr" value="${resultlist.memId}"></td>
												<td><input type="text" style="width:70%;" name="studyTimeArr" value="${resultlist.studyTime}"/></td>											
												<td><input type="text" name="score"  style="width:70%;" value="${resultlist.achievement}"></td>	
												<td><input type="text" style="width:70%;" name="bigoArr" value="${resultlist.bigo}"/></td>
												<td><a href="#" onclick="removeMember('${statustop.index}','${resultlist.traniningNoteDetailId}' , '${resultlist.traniningNoteMasterId}');removeMemberHtml('${statustop.index}','tr${status.index}');" >삭제</a>  </td>
											</tr>
											</c:forEach>
											<input type="hidden" name="traningNoteMemberSeqs" id="traningNoteMemberSeqs${statustop.index}"  value="${memSeqs}" />
											<input type="hidden" name="traningNoteMemberIds" id="traningNoteMemberIds${statustop.index}"  value="${memIds}" />
											<input type="hidden" name="traningNoteMemberNames" id="traningNoteMemberNames${statustop.index}"  value="${memNames}" />
											<tr>
												<th>보강총평</th>
												<td colspan="5"  ><textarea name="review" style="width:90%;"  rows="6">${TraningEnrichmentTimeVO.review}</textarea></td>
											</tr>
										</tbody>
									</table>

									<div class="btn-area align-center mt-010">
										<a href="#!" onclick="javascript:fn_insertEnrichment('${statustop.index}');" class="yellow">저장</a>
									</div>
								</dd>
							</dl><!-- E : 보강수업 -->
		</form> 						
  </c:forEach>
  
 </c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					
					</h4>
					
</c:if>
				</div><!-- E : contents-area -->
			</div><!-- E : container -->