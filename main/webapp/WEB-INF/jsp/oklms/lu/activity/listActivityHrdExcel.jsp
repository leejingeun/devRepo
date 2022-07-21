<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 					<br/>
					<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder"> 
							<colgroup>
								<col style="width:*" />
								<col style="width:250px" />
								<col style="width:200px" />
								<col style="width:200px" />
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
										 미제출(${result.ojtCount} / ${result.ojtWorkCount }) 
										</c:if>
										<c:if test="${result.ojtCount eq result.ojtWorkCount}">
										완료
										</c:if>
									</c:if>
										</td>
										<td>
									<c:if test="${result.offWorkCount eq '0' }">
									-
									</c:if>										
									<c:if test="${result.offWorkCount ne '0' }">
										<c:if test="${result.offCount ne result.offWorkCount}">
										미제출(${result.offCount} / ${result.offWorkCount })
										</c:if>
										<c:if test="${result.offCount eq result.offWorkCount}">
										완료
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