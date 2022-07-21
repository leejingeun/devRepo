<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 
						<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
								<colgroup>
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
									<col style="width:15%" />
									<col style="width:*px" />
								</colgroup>
								<tr>
									<th>교과형태</th>
									<td>${subjectVO.subjectTraningTypeName}</td>
									<th>과정구분</th>
									<td>${subjectVO.subjectTypeName}</td>
									<th>훈련시간</th>
									<td>${subjectVO.traningHour}시간</td>
								</tr>
								<tr>
									<th>담당교수</th>
									<td>${subjectVO.insNames}</td>
									<th>온라인 교육형태</th>
									<td>${subjectVO.onlineTypeName} (성적비율 ${subjectVO.gradeRatio}%)</td>
									<th>선수여부</th>
									<td>${subjectVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
								</tr>
								</tbody>
							</table>
							<br/>

							<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
								<colgroup>
											<col style="width:70px" />
											<col style="width:*" />
											<col style="width:80px" />
											<col style="width:120px" />
											<col style="width:120px" />
											<col style="width:120px" />
											<col style="width:180px" />
										</colgroup>
										<thead>
											<tr>
												<th>주차</th>
												<th>훈련일자</th>
												<th>교육시간</th>
												<th>개별과제</th>
												<th>팀프로젝트</th>
												<th>토론</th>
												<th>강의실</th>
											</tr>
										</thead>
										<tbody>
										<c:set var="totTimeHour" value="0"/>
										<c:set var="totReport" value="0"/>
										<c:set var="totTeamProject" value="0"/>
										<c:set var="totDiscuss" value="0"/>
										<c:forEach var="result" items="${resultList1}" varStatus="status">
											<tr>
												<td>${result.weekCnt}</td>
												<td>${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
												<td>${result.timeHour}</td>
												<c:set var="totTimeHour" value="${totTimeHour+result.timeHour}"/>
												<td>
												
												<c:if test="${!empty  result.reportId}">
													<c:set var="totReport" value="${totReport+1}"/>
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-search-gray">과제</a>
												</c:if>
												</td>
												<td>
												<c:if test="${!empty  result.teamProjectId}">
													<c:set var="totTeamProject" value="${totTeamProject+1}"/>
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-search-gray">과제</a>
												</c:if>
												</td>
												<td>
												<c:if test="${!empty  result.discussId}">
													<c:set var="totDiscuss" value="${totDiscuss+1}"/>
													<a href="/lu/discuss/getDiscuss.do?discussId=${result.discussId}&yyyy=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subjectClass}" class="btn-search-gray">토론</a>
												</c:if>
												</td>
												<td>${result.lctrumNm}</td>
											</tr>
										</c:forEach>	
											<tr>
												<th></th>
												<th>계</th>
												<th>${totTimeHour}</th>
												<th>${totReport}</th>
												<th>${totTeamProject}</th>
												<th>${totDiscuss}</th>
												<th></th>
											</tr>
										</tbody>
									</table>