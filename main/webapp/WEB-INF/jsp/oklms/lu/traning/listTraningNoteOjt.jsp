<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
 <%
     //치환 변수 선언합니다.
      pageContext.setAttribute("br", "<br/>");  //br 태그
      pageContext.setAttribute("cn", "\n"); //Space, Enter
%> 
 						<h2>훈련일지</h2>
						<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
						<script type="text/javascript" src="/js/oklms/iscroll.js"></script>
<script type="text/javascript">
<!--
 

function fn_search(classId,companyId){
	
	$("#classId").val(classId);
	$("#companyId").val(companyId);
	
	var reqUrl = "/lu/traning/listTraningNote.do";
 
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").submit();

}
var myScroll;

function loaded() {
	myScroll = new iScroll('wrap', {
		snap: true,
		momentum: false,
		hScrollbar: false,
	});
}

document.addEventListener('DOMContentLoaded', loaded, false);
//--> 
</script>
<c:set var="tempCompany" value=""/>
<c:set var="tempClass" value=""/>
<c:set var="prevCompany" value=""/>
<c:set var="prevClass" value=""/>
<c:set var="nextCompany" value=""/>
<c:set var="nextClass" value=""/>
						<ul id="learner-wrap" class="mb-010">
							<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
							<li id="wrap">
								<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
								<div id="scroller" style="width:${fn:length(subjectNameList)*128}px;">
									<ul id="thelist" class="name-tab-btn">
									<c:forEach var="result" items="${subjectNameList}" varStatus="status">
										<li <c:if test="${ result.classId eq TraningNoteVO.classId }">  class="current" </c:if> >
											<a href="#!" onclick="javascript:fn_search('${result.classId}','${result.companyId }')">${result.classId}반_${result.companyName }</a>
										</li>

										<c:if test="${result.companyId eq TraningNoteVO.companyId }">
											<c:set var="prevCompany" value="${tempCompany}"/>
											<c:set var="prevClass" value="${tempClass }"/>
											
											<c:if test="${status.first}">
												<c:set var="prevCompany" value="${subjectNameList[0].companyId }"/>
												<c:set var="prevClass" value="${subjectNameList[0].classId}"/>
											</c:if>	
											<c:if test="${!status.last}">
												<c:set var="nextCompany" value="${subjectNameList[status.index+1].companyId}"/>
												<c:set var="nextClass" value="${subjectNameList[status.index+1].classId}"/>
											</c:if>
											<c:if test="${status.last}">
												<c:set var="nextCompany" value="${subjectNameList[0].companyId }"/>
												<c:set var="nextClass" value="${subjectNameList[0].classId}"/>
											</c:if>											
										</c:if>
										<c:if test="${empty prevCompany }">
											<c:set var="tempCompany" value="${result.companyId }"/>
											<c:set var="tempClass" value="${result.classId}"/>									
										</c:if>		

										
									</c:forEach>
									</ul>
								</div>
							</li>
							<li id="next" onclick="myScroll.scrollToPage('next', 0);return false">next</li>
						</ul><!-- E : learner-wrap -->

						<div class="group-area name-tab-content">
<form name="frmTraning" id="frmTraning" method="post">
				<input type="hidden" id="classId" name="classId" value="${TraningNoteVO.classId}" />
				<input type="hidden" id="companyId" name="companyId" value="${TraningNoteVO.companyId}" />
						
							<h4 class="mb-010"><span>${TraningNoteVO.subjectName}</span> (${TraningNoteVO.classId }반_${TraningNoteVO.companyName})ㅣ ${TraningNoteVO.yyyy}학년도 ${TraningNoteVO.term}학기 </h4>
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
											<select id="weekCnt" name="weekCnt" onchange="javascript:fn_search('${TraningNoteVO.classId}','${TraningNoteVO.companyId}');">
												<c:forEach var="result" items="${weeklist}" varStatus="status">
												<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq TraningNoteVO.weekCnt }">selected</c:if>>${result.weekCnt}</option>
												</c:forEach>
											</select>											
										</td>
										<td>${TraningNoteVO.traningStDate} ~ ${TraningNoteVO.traningEndDate}</td>
										<td>${TraningNoteVO.ncsUnitName}</td>
										<td>${fn:replace(TraningNoteVO.ncsElemName, cn , br )}</td>
										<td class="left">${TraningNoteVO.lessonCn}</td>
										<td>${TraningNoteVO.timeHour}</td>

									</tr>
								</tbody>
							</table>

</form>

							<div class="learner-training">
<c:forEach var="timeList" items="${timeList}" varStatus="status">
 
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

							<dl>
								<dt>정규수업 : ${resultSum[status.index].traningDate}(${resultSum[status.index].dayOfWeek}) ${resultSum[status.index].traningSt}~${resultSum[status.index].traningEd} (${resultSum[status.index].timeHour}시간)  ${result.traniningNoteMasterId } </dt>
								<dd style="display:block;">


									<table class="type-2">
										<colgroup>
										
										
												<col width="15%" />
												<col width="15%" />
												<col width="20%" />
												<col width="*" />
												
										</colgroup>
										<thead>
											<tr>
											
												<th>성명</th>
												<th>훈련시간</th>
												<th>성취도</th>
 
												<th>비고 (교육 중 특이사항 등)</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="resultlist" items="${resultlistSum[status.index]}" varStatus="statuslist">
											<tr>
												<td>${resultlist.memName}</td>
												<td>${resultlist.studyTime}</td>									 
												<td>												
									<c:forEach var="i" begin="1" end="${resultlist.achievement}" step="1">
								      <img src="/images/oklms/std/inc/icon_star_on.png" />
								    </c:forEach>												
												</td>																															
												<td class="left">${resultlist.bigo}</td>
											</tr>
											</c:forEach>
											<tr>
												<th>총평</th>
												<td colspan="4" class="left">${resultSum[status.index].review}</td>
											</tr>
										</tbody>
									</table>									 
								</dd>
							</dl><!-- E : 정규수업 -->
							</form>
							
							</c:if>
</c:forEach>							

<c:forEach var="TraningEnrichmentTimeVO" items="${TraningEnrichmentTimeVO}" varStatus="statustop">

 							<dl>					
								<dt>
								 보강훈련 :
									 ${TraningEnrichmentTimeVO.traningDate} 
									 ${TraningEnrichmentTimeVO.traningStHour}:${TraningEnrichmentTimeVO.traningStMin} ~ 
									 ${TraningEnrichmentTimeVO.traningEdHour}:${TraningEnrichmentTimeVO.traningStMin}
								</dt>
								<dd style="display:block;"> 
									<table class="type-2">
										<colgroup>
											<col width="15%" />
											<col width="15%" />
											<col width="20%" />
											<col width="*" />
										</colgroup>
										<thead>
											<tr>
												<th>성명</th>
												<th>훈련시간 (실제)</th>
												<th>성취도</th>
												<th>비고 (교육 중 특이사항 등)</th>
											</tr>
										</thead>
										<tbody>

											<c:forEach var="resultlist" items="${TraningNoteList2[statustop.index]}" varStatus="status">
											<tr id="tr${status.index}">
												<td>${resultlist.memName} <input type="hidden" name="memNmArr" id="memNmArr" value="${resultlist.memName}">  </td>
												<td>${resultlist.studyTime}</td>
												<td>
												 
									<c:forEach var="i" begin="1" end="${resultlist.achievement}" step="1">
								      <img src="/images/oklms/std/inc/icon_star_on.png" />
								    </c:forEach>												
												</td>	
												<td class="left">${resultlist.bigo}</td>
											</tr>
											</c:forEach>
											
											 
											<tr>
												<th>보강총평</th>
												<td colspan="4" class="left"> ${TraningEnrichmentTimeVO.review} </td>
											</tr>
										</tbody>
									</table>
 
									 
								</dd>

							</dl><!-- E : 보강수업 -->
	
</c:forEach>		
							</div>							  
 
							<div class="btn-area align-right mt-010">
								<a href="#!" onclick="javascript:fn_search('${prevClass}','${prevCompany }')" class="gray-1">&lt; 이전 분반</a> 
								<a href="#!" onclick="javascript:fn_search('${nextClass}','${nextCompany }')" class="gray-1">다음 분반 &gt;</a>
							</div>

						</div> 

 