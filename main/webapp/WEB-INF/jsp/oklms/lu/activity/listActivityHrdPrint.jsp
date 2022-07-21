<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
		<div id="pop-wrapper" class="min-w850">
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>
						<h2>학습활동서 조회</h2>

						<div class="gropu-area">
							<table class="type-2">
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
													 
							<div class="btn-area align-right mt-010">
								<a href="#!" onclick="javascript:window.print();" class="gray-1">인쇄</a>
							</div><!-- E : btn-area -->
						</div>

 
<form:form commandName="frmActivity" name="frmActivity" method="post"  >
		<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
		<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
		<input type="hidden" name="weekCnt" id="weekCnt"  />
		<input type="hidden" name="traningType" id="traningType"  />	

						<div class="search-box-1 mt-020 ">
							${activityVO.yyyy }학년도  ${activityVO.term} 학기							
						</div><!-- E : search-box-1 -->

</form:form>

						<table class="type-2 mt-010">
							<colgroup>

								<col style="width:100px" />
								<col style="width:250px" />
								<col style="width:100px" />
								<col style="width:100px" />
							</colgroup>
							<thead>
								<tr>

									<th rowspan="2">주차</th>
									<th rowspan="2">학습기간</th>
									<th colspan="2">제출현황</th>
								</tr>
								<tr>
									<th class="border-left">OJT</th>
									<th>Off-JT</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultlist}" varStatus="status">
									<tr>

										<td>${result.weekCnt} 주차</td>
										<td>${result.weekStDate}-${result.weekEdDate}</td>
										<td>

									<c:if test="${result.ojtWorkCount eq '0' }">
									-
									</c:if>										
									<c:if test="${result.ojtWorkCount ne '0' }">
										<c:if test="${result.ojtCount ne result.ojtWorkCount}">
										<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');" >미제출(${result.ojtCount} / ${result.ojtWorkCount }) </a>
										</c:if>
										<c:if test="${result.ojtCount eq result.ojtWorkCount}">
										<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');" class="btn-line-blue">조회</a>
										</c:if>
									</c:if>

										</td>
										<td>

									<c:if test="${result.offWorkCount eq '0' }">
									-
									</c:if>										
									<c:if test="${result.offWorkCount ne '0' }">
										<c:if test="${result.offCount ne result.offWorkCount}">
										<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');" >미제출(${result.offCount} / ${result.offWorkCount }) </a>
										</c:if>
										<c:if test="${result.offCount eq result.offWorkCount}">
										<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');" class="btn-line-blue">조회</a>
										</c:if>
									</c:if>									
										
										</td>
									</tr>								
								</c:forEach>
								<c:if test="${empty resultlist}">
								<tr>
									<td colspan="4">등록된 데이터가 없습니다.</td>
								</tr>							
								</c:if>
   
								
							</tbody>
						</table>
						 
			</div>