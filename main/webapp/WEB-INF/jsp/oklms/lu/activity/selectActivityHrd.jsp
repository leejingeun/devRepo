<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <c:set var="memSeqs" value="" />
						<h2>학습활동서 조회</h2>

						<table class="type-1-1">
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
								 
									<td>${activityVO.traningType }</td>
									<td>${result.companyName }</td>
									<td>${result.hrdTraningName }</td>
									<td>${activityVO.yyyy }</td>
									<td>${activityVO.term }</td>
									<td>${activityVO.weekCnt }</td>
					 
								</tr>
							</tbody>
						</table>



						<table class="type-2 mt-020">
							<colgroup>
								<col style="width:50px" />
								<col style="width:110px" />
								<col style="width:120px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:70px" />
								<col style="width:70px" />
								<col style="width:50px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:60px" />
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>성명</th>
									<th>주민등록번호</th>
									<th>개설강좌명</th>
									<th>능력단위요소</th>
									<th>주간<br />훈련시간</th>
									<th>실제<br />훈련시간</th>
									<th>성취도</th>
									<th>보강결과</th>
									<th>보강<br />성취도</th>
									<th>조회</th>
								</tr>
							</thead>
							<tbody>
 <c:set var="tempId" value="xxx" />
							<c:forEach var="result" items="${resultlist}" varStatus="status">
							<c:set var="newtempId" value="${result.memSeq}" />
								<tr> 
		<c:if test="${tempId ne newtempId }" >
									<td rowspan="${result.tableCnt }">${tableCount}</td>									
									<td rowspan="${result.tableCnt }">${result.memName}</td>
									<td rowspan="${result.tableCnt }"></td>
		</c:if>
		
									<td  <c:if test="${tempId eq newtempId }" > class="border-left" </c:if>>${result.subjectName}</td>
									<td>${result.ncsUnitName} ${result.ncsElemName}</td>
									<td>${result.weekTimeHour}시간  </td>
									<td>
										<c:if test="${!empty result.weekWorkTimeHour }">
										${result.weekWorkTimeHour}시간
										</c:if>
									</td>
									<td>
										<c:if test="${!empty result.weekWorkAchievement }">
										${result.weekWorkAchievement}
										</c:if>
									</td>
									<td>
										<c:if test="${!empty result.weekAddTimeHour }">
										${result.weekAddTimeHour}
										</c:if>
									</td>
									<td>
										<c:if test="${!empty result.weekAddAchievement }">
										${result.weekAddAchievement}
										</c:if>
									</td>
		
		<c:if test="${tempId ne newtempId }" >
									<td rowspan="${result.tableCnt }">									
										<c:if test="${empty result.activityNoteId }">
										<a href="#!" class="btn-line-gray">미제출</a>
										</c:if>
										<c:if  test="${!empty result.activityNoteId }">
										<a href="#!" onclick="javascript:fn_detail('${result.memSeq}');" class="btn-line-blue">조회</a>
										</c:if>										
									</td>
		</c:if>
							
								<c:set var="tempId" value="${result.memSeq}" />
								</tr>
														
							</c:forEach> 
							</tbody>
						</table>
				 
<script type="text/javascript">
<!-- 
 
function fn_search(){ 
	var reqUrl = "/lu/activity/listActivityHrd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
}; 
function fn_detail(memSeq){
	$("#memSeq").val(memSeq );
	var reqUrl = "/lu/activity/getActivityHrd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
};
//-->
</script>
<form:form commandName="frmActivity" name="frmActivity" method="post"  >
		<input type="hidden" name="companyId" id="companyId" value="${activityVO.companyId }"  />
		<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${activityVO.traningProcessId }" />
		<input type="hidden" name="weekCnt" id="weekCnt"  value="${activityVO.weekCnt}"  />
		<input type="hidden" name="traningType" id="traningType"  value="${activityVO.traningType}"  />
		<input type="hidden" name="yyyy" id="yyyy"   value="${activityVO.yyyy}"  />
		<input type="hidden" name="term" id="term" value="${activityVO.term}" />
		<input type="hidden" name="memSeq" id="memSeq"  />
		<input type="hidden" name="memSeqs" id="memSeqs" value="${memSeqs}"  />
</form:form>
						<div class="btn-area align-right mt-010"> 
							<a href="#!" onclick="javascript:fn_search();" class="gray-1 ">이전화면</a>
						</div><!-- E : btn-area -->
				 