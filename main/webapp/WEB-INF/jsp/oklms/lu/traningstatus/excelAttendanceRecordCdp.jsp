<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<c:if test="${!empty onlineTraningVO}">
			<table  class="type-3">
				<tr>
					<th>ON-LINE 출석정책</th>
					<th> 출석 </th>
					<td>${onlineTraningVO.attendProgress}이상</td>
					<th>지각</th>
					<td> ${onlineTraningVO.attendProgress} 미만&nbsp; ${onlineTraningVO.cutProgress}이상 </td>
					<th> 결석 </th>
					<td>${onlineTraningVO.cutProgress}이상</td>
				</tr>
			</table>		
			</c:if>

			<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
				  
										<tr>
											<th>번호</th>
											<th>학번</th>
											<th>이름</th>
											<th>학년</th>
											<th>출석</th>
											<th>지각</th>
											<th>결석</th>
											<th>진도율</th>
										</tr>
		 
									<c:forEach var="resultlists" items="${resultlist}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${resultlists.memId }</td>
											<td class="left">${resultlists.memName }</td>
											<td>${resultlists.level}</td>
											<td>${resultlists.onAttend }</td>
											<td>${resultlists.onLateness }</td>
 											<td>
 											<c:if test="${!empty onlineTraningVO}">
 											${resultlists.onTotalTime - resultlists.onAttend - resultlists.onLateness}
 											</c:if>
 											<c:if test="${empty onlineTraningVO}">
 											0
 											</c:if>
 											</td>
											<td>
												<div class="progress-area2">
													<p>${resultlists.onRealProgressRate}%</p>
													<progress value="${resultlists.onRealProgressRate}" max="100"></progress>
												</div>
											</td>
										</tr>
									</c:forEach>
  
			</table><!-- E : 전체 학습근로자관리-->
  