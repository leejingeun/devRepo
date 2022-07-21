<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
<!--


//-->
</script>
			<div id="container">
				<div id="contents-area">

					<h4>
						<p>${currProcReadVO.subjectName}</p>
						<span>${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</span>
					</h4>

					<table class="type-1">
						<colgroup>
							<col width="22%" />
							<col width="*" />
							<col width="22%" />
							<col width="23%" />
						</colgroup>
						<tbody>
							<tr>
								<th>토론제목</th>
								<td colspan="3">${discussReadVO.title}</td>
							</tr>
							<tr>
								<th>기간</th>
								<td colspan="3">${discussReadVO.startDate} ~ ${discussReadVO.endDate}</td>
							</tr>
							<tr>
								<th>배점</th>
								<td colspan="3">${discussReadVO.evalScore}<c:if test="${discussReadVO.evalYnName != '미평가'}">점</c:if></td>
							</tr>							
							<tr>
								<th>내용</th>
								<td colspan="3">${discussReadVO.content}</td>
							</tr>
						</tbody>
					</table>
 
 
 
					<table class="type-2 mt-020">
						<colgroup>
							<col width="12%" />
							<col width="*" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr>
								<th rowspan="2">번호</th>
								<th rowspan="2">이름</th>
								<th colspan="3">활동내역</th>
								<th rowspan="2">점수</th>
							</tr>
							<tr>
								<th class="border-left">의견</th>
								<th>덧글</th>
								<th>추천</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="result" items="${resultEvalScoreStdList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${result.memName}</td>
								<td>${result.opinionCnt}</td>
								<td>${result.commentCnt}</td>
								<td>${result.opinionGoodCnt}</td>
								<td>${result.evalScore }</td>
							</tr>
						</c:forEach>
							
						</tbody>
					</table>
	 
				</div><!-- E : contents-area -->
			</div><!-- E : container -->