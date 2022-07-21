<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript" src="/js/oklms/common.js"></script>
<script type="text/javascript">
<!--

 		$(document).ready(function() {
			  
		});
	 
//-->
</script>

			<div id="container"> 
<form name="frmReport" id="frmReport" action=""  method="post" >
	<input type="hidden" name="reportId" value="${reportVO.reportId}"/>
</form>				

				<div id="contents-area">
<c:if test="${!empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span>${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
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
								<th>주차</th>
								<td>${reportVO.weekCnt} 주차</td>
								<th>배점</th>
								<td>${reportVO.score} 점</td>
							</tr>
							<tr>
								<th>과제제목</th>
								<td colspan="3">${reportVO.reportName}</td>
							</tr>
							<tr>
								<th>제출기간</th>
								<td colspan="3">${reportVO.submitStartDate} ~ ${reportVO.submitEndDate}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3">${reportVO.reportDesc}</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3">
							<c:if test="${!empty resultFile}">
											<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</c:if>
								</td>
							</tr>
						</tbody>
					</table>


					<table class="type-2 mt-020">
						<colgroup>
							<col width="9%" />
							<col width="12%" />
							<col width="*" />
							<col width="25%" />
							<col width="17%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th>번호</th>
								<th>성명</th>
								<th>제출일</th>
								<th>제출<br />현황</th>
								<th>점수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
							
								<tr>
									<td><input type="checkbox" name="check1" value="" class="choice" /></td>
									<td>${status.count}</td>
									<td>${result.memName }</td>
									<td>${result.insertDate }
									<c:if test="${!empty result.insertDate }">
										${result.insertDate }
									</c:if>
									<c:if test="${empty result.insertDate }">
										-
									</c:if>									
									</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');" >제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>										
									</td>
									<td>
									<c:if test="${!empty result.evalScore }">
										${result.evalScore }
									</c:if>
									<c:if test="${empty result.evalScore }">
										-
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
					<div class="btn-area align-center mt-010"><a href="#!" class="gray-2">SMS 발송</a><a href="#!" class="gray-2">E-MAIL 발송</a></div>

					
</c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					</h4>
					
</c:if>

				</div><!-- E : contents-area -->
			</div><!-- E : container -->