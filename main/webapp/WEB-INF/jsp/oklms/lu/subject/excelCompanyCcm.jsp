<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 

							<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
								<colgroup>
									<col style="width:*" />
									<col style="width:120px" />
									<col style="width:*" />
									<col style="width:100px" />
									<col style="width:100px" />
									<col style="width:70px" />
									<col style="width:70px" />
									<col style="width:70px" />
									<col style="width:*" />
								</colgroup>
								<thead>
									<tr>
										<th>기업명</th>
										<th>기업현장교사</th>
										<th>과정명</th>
										<th>시작일자</th>
										<th>종료일자</th>
										<th>시작인원</th>
										<th>재직중</th>
										<th>중도탈락</th>
										<th>학과명</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status">
									<tr>
										<td>${result.companyName}</td>
										<td>${result.tutNames}</td>
										<td>${result.hrdTraningName}</td>
										<td>${result.hrdStartDate}</td>
										<td>${result.hrdEndDate}</td>
										<td>${result.stuCnt}</td>
										<td>${result.stuCnt-result.outCnt}</td>
										<td>${result.outCnt}</td>
										<td>${result.deptName}</td>
									</tr>
								</c:forEach>
								<c:if test="${fn:length(resultList) == 0}">
									<tr>
										<td colspan="9"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>	 
								</tbody>
							</table>