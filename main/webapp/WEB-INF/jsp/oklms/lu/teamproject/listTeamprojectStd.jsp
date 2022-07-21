<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 
					 
						<h2>팀프로젝트</h2>
						<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

						<table class="type-1 mb-040">
								<colgroup>
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
								</colgroup>
								<tbody>
									<tr>
										<th>교과형태</th>
									<td>${currProcVO.subjectTraningTypeName}</td>
										<th>과정구분</th>
									<td>${currProcVO.subjectTypeName}</td>
										<th>학점</th>
										<td>${currProcVO.point }학점</td>
									</tr>
									<tr>
										<th>교수</th>
										<td>${currProcVO.insNames}</td>
										<th>온라인 교육형태</th>
										<td>${currProcVO.onlineTypeName} (성적비율 ${currProcVO.gradeRatio}%)</td>
										<th>선수여부</th>
										<td>${currProcVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
									</tr>
								</tbody>
							</table>



						<h2>팀프로젝트 목록</h2>
						<table class="type-2">
							<colgroup>
								<col style="width:50px" />
								<col style="width:*" />
								<col style="width:175px" />
								<col style="width:95px" />
								<col style="width:75px" />
								<col style="width:50px" />
							</colgroup>
							<thead>
								<tr>
									<th>회차</th>
									<th>팀프로젝트 주제</th>
									<th>팀프로젝트 기간</th>
									<th>과제 마감일</th>
									<th>제출현황</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
									<c:forEach var="result" items="${result}" varStatus="status">
									<tr>
										<td>${status.index+1}</td>
										<td class="left"><a href="/lu/teamproject/getTeamprojectStd.do?teamprojectId=${result.teamprojectId}" class="text">${result.projectName}</a></td>
										<td>${result.projectStartDate} ~ ${result.projectEndDate}</td>
										<td>${result.submitStartDate} ~ ${result.submitEndDate}</td>
										<td>
											<c:choose>
												<c:when test="${result.submitCnt > 0 }">
													<a href="#!" class="btn-full-gray" style="width:29px;">제출</a>
												</c:when>
												<c:otherwise>
													<a href="/lu/teamproject/getTeamprojectStd.do?teamprojectId=${result.teamprojectId}"  class="btn-full-blue">미제출</a>
												</c:otherwise>
											</c:choose>
										</td>
										<td>
										<c:if test="${result.submitStatus eq '완료'}">
											<span class="btn-line-gray">${result.submitStatus}</span>										
										</c:if>
										<c:if test="${result.submitStatus eq '진행'}">
											<span class="btn-line-blue">${result.submitStatus}</span>										
										</c:if>
										<c:if test="${result.submitStatus eq '예정'}">
											<span class="btn-line-blue">${result.submitStatus}</span>										
										</c:if>										
										</td>
									</tr>
									</c:forEach>
								<c:if test="${empty result}">
						          <tr>
						            <td colspan="6">자료가 없습니다.</td>
						          </tr>
						        </c:if>
								</tbody>
							</table>							 
