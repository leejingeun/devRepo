<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>

<script type="text/javascript">
<!--

function fn_print(activityId){
 
	var reqUrl = "/lu/activitydesc/updateActivityDescPrint.do";
	
	$.ajax({
		  type: "POST",
		  url: reqUrl,
		  data: { "activityId": activityId },
		  success:function( html ) {
				 		
		  }
	});
	
	window.print();
}

//-->
</script>
 

<c:set var="bigoDetailCot" value="" />
<c:set var="bigoDetailHrd" value="" />
<div id="pop-wrapper" class="min-w650">
					<h2>활동내역서 상세 조회 </h2>
 
					<div class="group-area mb-010">
						<table class="type-1-1">
							<colgroup>
								<col style="width:200px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<tr>
								
								<th>기업명</th>
								<th>소재지</th>
								<th>선정일</th>
								<th>훈련과정명</th>
							</tr>
							<c:if test="${!empty result}">
							<tr>
								<td>${result.companyName }</td>
								<td>${result.address }</td>
								<td>${result.insertDate }</td>
								<td>${result.hrdTraningName }</td>
							</tr>
							</c:if>
							<c:if test="${empty result}">
							<tr>
								<td colspan="4">기업체를 선택하세요</td>
							</tr>								
							</c:if>
						</table>
					</div>


					<br />
					<div class="group-area mb-010">
						<h5>${activityVO.yyyy}년 ${activityVO.mm}월 기업현장교사  </h5>
						<table class="type-1-1">
							<colgroup>
								<col style="width:150px" />
								<col style="width:150px" />
								<col style="width:*" />
								<col style="width:150px" />
							</colgroup>
							<thead>
								<tr>
									<th>일자</th>
									<th>성명</th>
									<th>업무수행내용</th>
									<th>소요시간</th>
								</tr>
							</thead>
							<tbody>
							 <c:forEach var="result" items="${resultList}" varStatus="status" >
							 	<c:if test="${result.activityType eq 'COT'}">
									<tr>
										<td>${result.activityDate} &nbsp;</td>
										<td>${result.memName}</td>
										<td>${result.activityContent}</td>
										<td>${result.leadTime}</td>
									</tr>
<c:set var="bigoDetailCot" value="${result.bigoCot}" />										
								</c:if>
							</c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="4"><spring:message code="common.nodata.msg" /></td>
									</tr>
							</c:if>
							<tr>
								<th>
									비고
								</th>
								<td colspan="3">
									${bigoDetailCot}
								</td>
							</tr>
							</tbody>
						</table>

					</div>
					<br />
					<div class="group-area mb-010">
						<h5>${activityVO.yyyy}년 ${activityVO.mm}월 HRD담당자</h5>
						<table class="type-1-1">
							<colgroup>
								<col style="width:150px" />
								<col style="width:150px" />
								<col style="width:*" />
								<col style="width:150px" />
							</colgroup>
							<thead>
								<tr>
									<th>일자</th>
									<th>성명</th>
									<th>업무수행내용</th>
									<th>소요시간</th>
								</tr>
							</thead>
							<tbody>
							 <c:forEach var="result" items="${resultList}" varStatus="status" >
							 	<c:if test="${result.activityType eq 'HRD'}">
								<tr>
									<td>${result.activityDate} &nbsp;</td>
									<td>${result.memName}</td>
									<td>${result.activityContent}</td>
									<td>${result.leadTime}</td>
								</tr>
<c:set var="bigoDetailHrd" value="${result.bigoHrd}" />									
								</c:if>								
							</c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="4"><spring:message code="common.nodata.msg" /></td>
									</tr>
							</c:if>
							<tr>
								<th>
									비고
								</th>
								<td colspan="3">
									${bigoDetailHrd}
								</td>
							</tr>
							</tbody>
						</table>
					</div>
					
					<div class="btn-area align-right mt-010 not_print">
						<a href="javascript:fn_print('${activityVO.activityId}');" class="gray-2 ">인쇄</a>
					</div>
</div>					
 