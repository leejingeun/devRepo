<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>

					<h2>활동내역서 상세 조회 </h2>

<script type="text/javascript">
<!--
function fn_search(){
	$("#mm").val("");
	var reqUrl = "/lu/activitydesc/listActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
}
function fn_getActivityDesc(activityId){
 
	var reqUrl = "/lu/activitydesc/getActivityDesc.do";
	$("#activityId").val(activityId); 
	$("#frmActivity").attr("target","_blank");
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}

//-->
</script>
<c:set var="bigoDetailCot" value="" />
<c:set var="bigoDetailHrd" value="" />
<form method="post" name="frmActivity" id="frmActivity">
<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
<input type="hidden" id="activityId" name="activityId" value="${activityVO.activityId}" />
<input type="hidden" id="mm" name="mm" value="${activityVO.mm}" />
<input type="hidden" id="yyyy" name="yyyy" value="${activityVO.yyyy}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="print" />

					<div class="group-area mb-010">
						<table class="type-1-1">
							<colgroup>
								<col style="width:250px" />
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



					<div class="group-area mb-010">
						<h5>${activityVO.yyyy}년 ${activityVO.mm}월 기업현장교사  </h5>
						<table class="type-1-1">
							<colgroup>
								<col style="width:200px" />
								<col style="width:200px" />
								<col style="width:*" />
								<col style="width:200px" />
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
					<div class="group-area mb-010">
						<h5>${activityVO.yyyy}년 ${activityVO.mm}월 HRD담당자</h5>
						<table class="type-1-1">
							<colgroup>
								<col style="width:200px" />
								<col style="width:200px" />
								<col style="width:*" />
								<col style="width:200px" />
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
					
					<div class="btn-area align-right mt-010">
						<a href="#!" onclick="javascript:fn_search();" class="gray-1  float-left">이전</a>
						<a href="javascript:fn_getActivityDesc('${activityVO.activityId}');" class="gray-2 ">출력</a>
					</div>
 
</form>