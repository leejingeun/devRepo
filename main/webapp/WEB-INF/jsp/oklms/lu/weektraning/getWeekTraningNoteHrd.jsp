<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${traningNoteVO.addyn eq 'Y' }"><h2>보강훈련일지 조회</h2></c:if>
<c:if test="${traningNoteVO.addyn eq 'N' }"><h2>주간훈련일지 조회</h2></c:if>
						
<script type="text/javascript">
<!--

function fn_search(){ 
	var reqUrl = "/lu/weektraning/listWeekTraningNoteHrd.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
}

//-->
</script>
<c:set var="stateName" value="" />
						<c:if test="${!empty resultList}" >							
							<c:set var="stateName" value="${resultList[0].state }" />
							<c:if test="${stateName eq 'C'}" >
							<c:set var="stateName" value="확정" />
							</c:if>
							<c:if test="${stateName eq 'X'}" >
							<c:set var="stateName" value="반려" />
							</c:if>
							<c:if test="${stateName eq 'I'}" >
							<c:set var="stateName" value="제출" />
							</c:if>
						</c:if>
						
						<table class="type-1-1">
							<colgroup>
								<col style="width:100px" />
								<col style="width:100px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:120px" />
								<col style="width:90px" />
								<col style="width:90px" />
							</colgroup>
							<thead>
								<tr>
									<th>상태</th>
									<th>구분</th>
									<th>기업명</th>
									<th>훈련과정명</th>
									<th>학년도</th>
									<th>학기</th>
									<th>주차</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty result}">
								<tr>
									<td>${stateName}</td>
									<td>${traningNoteVO.traningType}</td>
									<td>${result.companyName }</td>
									<td>${result.hrdTraningName }</td>
									<td>${traningNoteVO.yyyy}</td>
									<td>${traningNoteVO.term}</td>
									<td>${traningNoteVO.weekCnt }</td>
								</tr>
								</c:if>
							 
							</tbody>
						</table>
<c:set var="allReview" value="" />
<form:form commandName="frmWeekTraning" name="frmWeekTraning" method="post"  >

<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
<input type="hidden" name="weekCnt"  value="${traningNoteVO.weekCnt }"/>
<input type="hidden" name="yyyy"  value="${traningNoteVO.yyyy}"/>
<input type="hidden" name="term"  value="${traningNoteVO.term}"/>
<input type="hidden" name="statusType" id="statusType" value="" />
<input type="hidden" name="traningType" id="traningType" value="${traningNoteVO.traningType}"  />	
<input type="hidden" name="addyn" id="addyn" value="${traningNoteVO.addyn}" />
 
						<table class="type-2 mt-020">
							<colgroup>
								<col style="width:60px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:70px" />
								<col style="width:110px" />
								<col style="width:120px" />
								<col style="width:80px" />
								<col style="width:80px" />
								<col style="width:160px" />
							</colgroup>
							<thead>
								<tr>
									<th>일자</th>
									<th>개설강좌명</th>
									<th>능력단위요소</th>
									<th>주간<br />훈련시간</th>
									<th>성명</th>
									<th>주민등록번호</th>
									<th>실제<br />훈련시간</th>
									<th>성취도</th>
									<th>비고<br />(교육 중 특이사항 등)</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="tempId" value="xxx" />

							<c:forEach var="resultList" items="${resultList}" varStatus="status">
							<c:set var="newtempId" value="${resultList.subjectName}${resultList.classId}${resultList.traningDate}" />
								<tr>
								<c:if test="${tempId ne newtempId }" >
								<c:set var="allReview" value="${allReview} 
								${resultList.review}" />
									<td rowspan="${resultList.llCount}">${resultList.traningDate}</td>
									<td rowspan="${resultList.llCount}">${resultList.subjectName}</td>
									<td rowspan="${resultList.llCount}">${resultList.ncsElemName}</td>
									<td rowspan="${resultList.llCount}">${resultList.studyTimePlan}</td>									
								</c:if>
									<td <c:if test="${tempId eq newtempId }" > class="border-left" </c:if>>${resultList.memName}</td>
									<td></td>
									<td>${resultList.studyTime}</td>
									<td>${resultList.achievement}</td>
									<td>${resultList.bigo}</td>									
								</tr>
								<c:set var="tempId" value="${resultList.subjectName}${resultList.classId}${resultList.traningDate}" />
							</c:forEach>
							</tbody>
						</table>

						<table class="type-write mt-040">
							<colgroup>
								<col style="width:150px" />
								<col style="width:*" />
							</colgroup>
							<tbody>
								<tr>
									<th>총평</th>
									<td class="left">
										<textarea name="textarea" rows="5" readonly="readonly" disabled>${allReview}</textarea>
									</td>
								</tr>
							</tbody>
						</table>

</form:form>

						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_search();" class="gray-1 float-left">목록</a>
						</div>
