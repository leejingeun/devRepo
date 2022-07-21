<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 
						<table   width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
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
 						<br />
 
						<table   width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
							<colgroup>
								<col style="width:50px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<thead>
								<tr>
									<th rowspan="2">주차</th>
									<th rowspan="2">학습기간</th>
									<th colspan="2">제출현황 (정규훈련)</th>
									<th colspan="2">제출현황(보강훈련)</th>
								</tr>
								<tr>
									<th class="border-left">OJT</th>
									<th>OFF-JT</th>
									<th class="border-left">OJT</th>
									<th>OFF-JT</th>
								</tr>
							</thead>
							<tbody  id="tableList">
								<c:forEach var="result" items="${bottomList}" varStatus="status">
								<tr>
									<td>${result.weekCnt}주차</td>
									<td>${result.traningSt} ~ ${result.traningEd}</td>
									<td>
										<c:if test="${empty result.ojtState}" >
											-
										</c:if>
										<c:if test="${result.ojtState eq 'W'}" >
											<span class="btn-line-orange"  style="float: none;">미제출</span>
										</c:if>
										<c:if test="${result.ojtState  eq 'I'}" >
											<span class="btn-line-blue"  style="float: none;">조회</span>
										</c:if>
										<c:if test="${result.ojtState  eq 'X'}" >
											<span class="btn-line-orange"  style="float: none;">반려</span>
										</c:if>										
										<c:if test="${result.ojtState  eq 'C'}" >
											<span class="btn-line-gray"  style="float: none;">확정</span>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.state}" >
											-
										</c:if>
										<c:if test="${result.state eq 'W'}" >
											<span class="btn-line-orange"  style="float: none;">미제출</span>
										</c:if>
										<c:if test="${result.state  eq 'I'}" >
											<span class="btn-line-blue"  style="float: none;">조회</span>
										</c:if>
										<c:if test="${result.state  eq 'X'}" >
											<span class="btn-line-orange"  style="float: none;">반려</span>
										</c:if>										
										<c:if test="${result.state  eq 'C'}" >
											<span class="btn-line-gray"  style="float: none;">확정</span>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.ojtAddState}" >
											-
										</c:if>
										<c:if test="${ result.ojtAddState eq 'W' }" >
											<span class="btn-line-orange"  style="float: none;">미제출</span>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'I' }" >
											<span class="btn-line-blue"  style="float: none;">조회</span>
										</c:if>
										<c:if test="${ result.ojtAddState eq 'X' }" >
											<span class="btn-line-orange"  style="float: none;">반려</span>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'C' }" >
											<span class="btn-line-gray"  style="float: none;">확정</span>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.addState}" >
											-
										</c:if>
										<c:if test="${result.addState  eq 'W'}" >
											<span class="btn-line-orange"  style="float: none;">미제출</span>
										</c:if>										
										<c:if test="${result.addState  eq 'I'}" >
											<span class="btn-line-blue"  style="float: none;">조회</span>
										</c:if>
										<c:if test="${result.addState  eq 'X'}" >
											<span class="btn-line-orange"  style="float: none;">반려</span>
										</c:if>
										<c:if test="${result.addState  eq 'C'}" >
											<span class="btn-line-gray"  style="float: none;">확정</span>
										</c:if>										
									</td>									
								</tr>
							</c:forEach>
							</tbody>
						</table>