<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript" src="/js/oklms/common.js"></script>		
<script type="text/javascript">
<!--
 
 

		$(document).ready(function() {
			  
		});
		 
		

//-->
</script>
			<div id="container">
 
				<div id="contents-area">
<c:if test="${not empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span>${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
					</h4>
<form:form commandName="frmActivity1" name="frmActivity" method="post" enctype="multipart/form-data" >
<input type="hidden" name="activityNoteId"  value="${result.activityNoteId}" >

<input type="hidden" name="yyyy"  value="${activityVO.yyyy}" >
<input type="hidden" name="term"  value="${activityVO.term}" >
<input type="hidden" name="subjectCode"  value="${activityVO.subjectCode}" >
<input type="hidden" name="classId"  value="${activityVO.classId}" >
<input type="hidden" name="traningType"  value="${activityVO.traningType}" >
<input type="hidden" name="state"  value="${activityVO.state}" >
<input type="hidden" name="weekCnt" value="${activityVO.weekCnt}"/>
<c:set var="sumNumber" value="0" />
<c:set var="totalSumNumber" value="0" />  
<c:forEach var="result" items="${resultMember}" varStatus="status">
	<c:set var="sumNumber" value="${result.studyTime + sumNumber }" />
	<c:if test="${result.addyn eq 'N' }"><c:set var="totalSumNumber" value="${result.timeHour + totalSumNumber}" /></c:if>
</c:forEach>
					<table class="type-1">
						<colgroup>
							<col width="22%" />
							<col width="*" />
							<col width="30%" />
							<col width="22%" />
						</colgroup>
						<tbody>
							<tr>
								<th>주차</th>
								<td>${activityVO.weekCnt}주차</td>
								<th>주간 훈련시간</th>
								<td>${totalSumNumber} 시간</td>
							</tr>
							<tr>
								<th>수업내용</th>
								<td colspan="3">${subjweekStdVO.contentName}</td>
							</tr>
						</tbody>
					</table>


					<table class="type-2 mt-020">
						<colgroup>
							
							<col width="*" />
							<col width="25%" />
							<col width="17%" />
							<col width="17%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th>이름/학번</th>
								<th>실시일자</th>
								<th>훈련시간<br />(계획)</th>
								<th>훈련시간<br />(결과)</th>
								<th>성취도</th>
								 
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultMember}" varStatus="status">
							 
								<tr>
									<c:if test="${status.index eq '0'}"> 
										<td rowspan="${fn:length(resultMember)+1 }">${result.memNm}<br /><c:if test="${!empty result.memId  }">( ${result.memId} )</c:if></td>
										<td>${result.traningDate }
											<c:if test="${ !empty result.dayOfWeek }"> (${result.dayOfWeek })</c:if>
										</td>
									</c:if>
									<c:if test="${status.index ne '0'}">
									<td class="border-left">${result.traningDate } <c:if test="${!empty result.dayOfWeek  }">(${result.dayOfWeek })</c:if></td>
									</c:if>
									<td>
									
									<c:if test="${result.addyn eq 'N' }">${result.timeHour }시간</c:if>
									<c:if test="${result.addyn eq 'Y' }">보강훈련</c:if>
									
									</td>
									<td>
										<c:if test="${!empty result.studyTime}">${result.studyTime }시간</c:if> 																						
									</td>
									<td>	${result.achievement} </td>
								</tr>
								<c:if test="${status.last}"> 
								<tr>
									<td class="border-left">계</td>
									<td>${totalSumNumber}시간</td>
									<td>${sumNumber}시간</td>
									<td>-</td>
								</tr>
								</c:if>							
							</c:forEach>
 
							<tr>
								<th>교육내용</th>
								<td colspan="4" class="left">${result.content }</td>
							</tr>
							<tr>
								<th>요청사항</th>
								<td colspan="4" class="left">${result.reqContent }</td>
							</tr>
							
							<c:if test="${ !empty resultFile}">
							<tr>
								<th>과제물첨부</th>
								<td colspan="4" class="left">
									<a href="javascript:com.downFile('${resultFile.atchFileId}','1');" class="text-file">${resultFile.orgFileName}</a>											 
								</td>
							</tr>
							</c:if>				
						</tbody>
					</table>
</form:form>

 

</c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>					
					</h4>
					
</c:if>

				</div><!-- E : contents-area -->
			</div><!-- E : container -->