<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>

			
 <div id="pop-wrapper" class="min-w650" >
			<h2>담당기업체현황</h2>
			<!-- E : search-list-1 (검색조건 영역) -->

					<table class="type-2">
						<colgroup>
							<col style="width:*" />
							<col style="width:*" />
							<col style="width:80px" />
							<col style="width:80px" />
							<col style="width:50px" />
							<col style="width:50px" />
							<c:forEach var="i" begin="0" end="${maxCnt}" step="1">
								<col style="width:80px" />
							</c:forEach>
						</colgroup>
						<thead>
							<tr>
								<th>훈련과정명</th>
								<th>기업명</th>
								<th>시작일자</th>
								<th>종료일자</th>
								<th>훈련<br />일지</th>
								<th>내부<br />평가</th>
								<th colspan="${maxCnt}">학습활동서</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td rowspan="2">${result.hrdTraningName}</td>
									<td rowspan="2">${result.companyName}</td>
									<td rowspan="2">${result.hrdStartDate}</td>
									<td rowspan="2">${result.hrdEndDate}</td>
									<td rowspan="2">${result.noteSumCnt}/${result.weekCnt}</td>
									<td rowspan="2">0/${result.evalPlanCnt}</td>
									<c:forEach var="i" begin="1" end="${maxCnt}" step="1">
										<c:set var="str" value="${fn:split(result.memInfos,',')[i-1]}"/>
										<td>${fn:split(str,'|')[0]}</td>
									</c:forEach>
								</tr>
								<tr>
									<c:forEach var="i" begin="1" end="${maxCnt}" step="1">
										<c:set var="str" value="${fn:split(result.memInfos,',')[i-1]}"/>
										<c:choose>
											<c:when test="${!empty fn:split(str,'|')[1]}"><td <c:if test="${ i == 1 }"> class="border-left" </c:if>>${fn:split(str,'|')[1]}/${result.weekCnt}</td></c:when>
											<c:otherwise><td></td></c:otherwise>
										</c:choose>
									</c:forEach>	
								</tr>
							</c:forEach>
								<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="8"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>	
						</tbody>
					</table>
					<div class="btn-area align-right mt-010">
						<a href="#!" onclick="javascript:fn_print();" class="gray-2">인쇄</a>	
					</div><!-- E : btn-area -->

	
<script type="text/javascript">
<!--

/*  수정,삭제 */
function fn_print(){ 
	window.print();
}  
//--> 
</script>		
	
	
					

	