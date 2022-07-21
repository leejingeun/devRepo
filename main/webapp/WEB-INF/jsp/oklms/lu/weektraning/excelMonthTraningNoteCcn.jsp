<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 
<c:set var="memCnt" value="0" />
<c:if test="${!empty traningNoteVO.traningNoteVOArr}" >	
	<c:set var="memCnt" value="${fn:length(traningNoteVO.traningNoteVOArr[0])*2}" />	 
</c:if>


						<table   width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
							<colgroup>
								<col style="width:120px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<thead>
								<tr>
									<th rowspan="3">일자</th>
									<th colspan="${memCnt}">학습근로자</th>
								</tr>
								<tr>
								<c:if test="${!empty traningNoteVO.traningNoteVOArr}" >	

										<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[0]}" varStatus="status">
										
										<th colspan="2" class="border-left">${result.memName}</th>
										
										</c:forEach>

								</c:if>
								</tr>
								<tr>
										<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[0]}" varStatus="status">

										<th <c:if test="${status.first}">class="border-left"</c:if>>OJT</th>
										<th>Off-JT</th>

										</c:forEach>
								</tr>
							</thead>
							<tbody>
 
								 <c:forEach var="i" begin="0" end="${lastDay}" step="1">
								 <c:if test="${!empty traningNoteVO.traningNoteVOArr[i] }">	
								 <tr>
								 	<td>
								 	<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[i]}" varStatus="status">
								 	<c:if test="${status.first }">
								 		${result.traningDate}
								 	</c:if>
								 	</c:forEach>								 	
								 	</td>
								 	
									<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[i]}" varStatus="status">									
									<td>${result.studyTimeOjt} ${fn:replace(result.addStudyTimeOjt, 'add', '보강')}</td>
									<td>${result.studyTimeOff} ${fn:replace(result.addStudyTimeOff, 'add', '보강')}</td>
									</c:forEach>									

								</tr>
								 </c:if>
								 </c:forEach>
 	<c:if test="${!empty resultTotal }">							  
								  
								 <tr>
								 	<td>수강합계</td>
								 	
									<c:forEach var="result" items="${resultTotal}" varStatus="status">									
									<td>${result.studyTimeOjt} <c:if test="${!empty result.addStudyTimeOjt}">(${result.addStudyTimeOjt})</c:if></td>
									<td>${result.studyTimeOff} <c:if test="${!empty result.addStudyTimeOff}">(${result.addStudyTimeOff})</c:if></td>
									</c:forEach>									

								</tr>								  
	</c:if>	
 								<tr>
									<th>계획합계</th>
									<td colspan="${memCnt}">OJT : ${resultSum.sumTimeOjt } 시간   Off-JT : ${resultSum.sumTimeOff } 시간</td>
								</tr>
								<tr>
									<th>합계 (보강)</th>
									<td colspan="${memCnt}">OJT : ${resultSum.addSumTimeOjt } 시간   Off-JT : ${resultSum.addSumTimeOff } 시간</td>
								</tr>
								<tr>
									<th>총계</th>
									<td colspan="${memCnt}">OJT : ${resultSum.totTimeOjt } 시간   Off-JT : ${resultSum.totTimeOff } 시간</td>
								</tr>	
								
							</tbody>
						</table>