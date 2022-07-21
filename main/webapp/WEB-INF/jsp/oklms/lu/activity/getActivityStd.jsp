<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="submitCheck" value="C"/>

						<h2>주차별 학습활동서 제출</h2>

						<table class="type-1-1 mb-040">
							<colgroup>
								<col style="width:120px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:120px" />
								<col style="width:100px" />
								<col style="width:100px" />
							</colgroup>
							<thead>
								<tr>
									<th>구분</th>
									<th>기업명</th>
									<th>훈련과정명</th>
									<th>학년도</th>
									<th>학기</th>
									<th>주차</th>
								</tr>
							</thead>
							<tbody>
								<tr>
								<tr>
								 
									<td>${activityVO.traningType }</td>
									<td>${result.companyName }</td>
									<td>${result.hrdTraningName }</td>
									<td>${activityVO.yyyy }</td>
									<td>${activityVO.term }</td>
									<td>${activityVO.weekCnt }</td>
					 
								</tr>
								</tr>
							</tbody>
						</table>



						<h2 class="sub_tit">학습근로자별 학습활동서</h2>
						<table class="type-1-1">
							<colgroup>
								<col style="width:180px" />
								<col style="width:*" />
								<col style="width:120px" />
								<col style="width:250px" />
							</colgroup>
							<thead>
								<tr>
									<th>성명</th>
									<th>주민등록번호</th>
									<th>주차</th>
									<th>학습기간</th>
								</tr>
							</thead>
							<c:set var="memName" value="" />
							<c:forEach var="result" items="${resultlist}" varStatus="status">
							<c:set var="memName" value="${result.memName }" />	
							</c:forEach>
							<tbody>
								<tr>
									<td>${memName}</td>
									<td> </td>
									<td>${activityVO.weekCnt } 주차</td>
									<td>${subjweekStdVO.traningStDate} ~ ${subjweekStdVO.traningEndDate}</td>
								</tr>
							</tbody>
						</table>



							<table class="type-2 mt-030">
								<colgroup>
									<col style="width:*" />
									<col style="width:*" />
									<col style="width:80px" />
									<col style="width:80px" />
									<col style="width:80px" />
									<col style="width:100px" />
									<col style="width:*" />
								</colgroup>
								<tr>
									<th rowspan="2">개설강좌명</th>
									<th rowspan="2">능력단위요소</th>
									<th colspan="3">교육시간</th>
									<th rowspan="2">성취도</th>
									<th rowspan="2">학습활동내역</th>
								</tr>
								<tr>
									<th class="border-left">전체</th>
									<th>계획</th>
									<th>결과</th>
								</tr>
								<c:forEach var="result" items="${resultlist}" varStatus="status">
								
								<tr>								
									<td>${result.subjectName}</td>
									<td>${result.ncsUnitName} ${result.ncsElemName}</td>
									
									<td>${result.allTimeHour}시간</td>
									<td>${result.weekTimeHour}시간</td>
									<td>
									<c:if test="${!empty result.weekAddTimeHour}">
									${result.weekWorkTimeHour} + ${result.weekAddTimeHour}시간
									</c:if>
									<c:if test="${empty result.weekAddTimeHour}">
									${result.weekWorkTimeHour} 시간									
									</c:if>									

									</td>
									<td>
									<c:if test="${!empty result.weekAddTimeHour}">
										${result.weekWorkAchievement} / ${result.weekAddAchievement}
									</c:if>
									<c:if test="${empty result.weekAddTimeHour}">
										${result.weekWorkAchievement} 
									</c:if>									
									</td> 																										
									<td>${result.content}</td>
								</tr>
								
								</c:forEach>
								 
							</table>



							<table class="type-write mt-020	">
								<colgroup>
									<col style="width:150px" />
									<col style="width:*" />
								</colgroup>
								<tbody>
								<c:forEach var="result" items="${resultlist}" varStatus="status">
								
									<c:if test="${!empty result.reqContent and !empty result.atchFileId}">
									<tr>
										<th>요청사항</th>
										<td>${result.reqContent}</td>
									</tr>
									<tr>
										<th>과제물첨부</th>
										<td class="left">
											<a href="javascript:com.downFile('${result.atchFileId}','1');" class="text-file">과제물파일</a>											 
										</td>
									</tr>
									</c:if>
									<c:if test="${empty result.reqContent and !empty result.atchFileId}">
									<tr>
										<th>과제물첨부</th>
										<td class="left">
											<a href="javascript:com.downFile('${result.atchFileId}','1');" class="text-file">과제물파일</a>											 
										</td>
									</tr>
									</c:if>
									<c:if test="${!empty result.reqContent and empty result.atchFileId}">
									<tr>
										<th>요청사항</th>
										<td>${result.reqContent}</td>
									</tr>
									</c:if>

									<c:if test="${result.state ne 'C' }">
										<c:set var="submitCheck" value="W"/>
									</c:if>

								</c:forEach>	
								</tbody>
							</table>
			 
<script type="text/javascript">
<!-- 
 
function fn_search(){ 
	var reqUrl = "/lu/activity/listWeekActivityStd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
};  
function fn_submit(){
	var reqUrl = "/lu/activity/updateWeekActivityStd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	
}

//-->
</script>
<form:form commandName="frmActivity" name="frmActivity" method="post"  >
		<input type="hidden" name="companyId" id="companyId" value="${activityVO.companyId }"  />
		<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${activityVO.traningProcessId }" />
		<input type="hidden" name="weekCnt" id="weekCnt"  value="${activityVO.weekCnt}"  />
		<input type="hidden" name="traningType" id="traningType"  value="${activityVO.traningType}"  />
		<input type="hidden" name="yyyy" id="yyyy"   value="${activityVO.yyyy}"  />
		<input type="hidden" name="term" id="term" value="${activityVO.term}" />
		<input type="hidden" name="memSeq" id="memSeq"  /><input type="hidden" name="memSeqs" id="memSeqs" value="${activityVO.memSeqs}"  />
</form:form>


							<div class="btn-area align-right mt-010">
								<a href="#!" onclick="javascript: fn_search();" class="gray-1 ">이전화면</a>
								<c:if test="${submitCheck eq 'W' }">
								<a href="#!" onclick="javascript: fn_submit();" class="orange ">제출</a>
								</c:if>
							</div>
