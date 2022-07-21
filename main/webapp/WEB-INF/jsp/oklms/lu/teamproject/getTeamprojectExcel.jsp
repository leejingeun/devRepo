<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<br/>
<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
							<colgroup>
								<col style="width:130px" />
								<col style="width:130px" />
								<col style="width:100px" />
								<col style="width:120px" />
								<col style="width:100px" />
							</colgroup>
							<thead>
								<tr>
									<th>팀명</th>
									<th>팀장</th>
									<th>팀원 수</th>
									<th>과제제출일</th>
									<th>제출현황</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach var="result" items="${result}" varStatus="status">
								<c:if test="${(teamprojectVO.submitType eq 'I') or (teamprojectVO.submitType eq 'T' and result.leaderYn eq 'Y')}">
								<tr>
									<td class="left"><fmt:formatNumber type="number" minIntegerDigits="2"  value="${result.groupId}" />팀</td>
									<td>${result.memName }</td>
									<td>${result.teamMemberCnt }</td>
									<td>${result.submitDate }</td>
									<td>
										<c:choose>
											<c:when test="${!empty result.atchFileId }">
												제출
											</c:when>
											<c:otherwise>
												미제출
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
								</c:if>
							</c:forEach>
							<c:if test="${empty result}">
						          <tr>
						            <td colspan="5">자료가 없습니다.</td>
						          </tr>
						    </c:if>
							</tbody>
						</table>