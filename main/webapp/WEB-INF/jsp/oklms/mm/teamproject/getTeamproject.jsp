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
		
 		function fn_search( groupId ){
			var reqUrl = "/mm/teamproject/getTeamproject.do"; 
			$("#groupId").val(groupId);
			$("#frmTeamproject").attr("target", "_self");
			$("#frmTeamproject").attr("action", reqUrl);
			$("#frmTeamproject").submit();
			 
			 
 		}
//-->
</script>
			<div id="container">
<form:form commandName="frmTeamproject" name="frmTeamproject" method="post"   >		
	<input type="hidden" name="yyyy" id="year" value="${teamprojectVO.yyyy}" />
	<input type="hidden" name="term" id="term" value="${teamprojectVO.term}" />
	<input type="hidden" name="subjectCode" id="subjectCode" value="${teamprojectVO.subjectCode}" />
	<input type="hidden" name="subClass" id="subClass" value="${teamprojectVO.subClass}" />
	
	<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="" />
	<input type="hidden" name="subjectType" id="subjectType" value="" /> 
	
	<input type="hidden" name="memId" id="memId" value="" />	
	<input type="hidden" name="groupId" id="groupId" value="" />	
	<input type="hidden" name="teamprojectId" id="teamprojectId" value="${teamprojectVO.teamprojectId }" />

</form:form>


<c:if test="${!empty currProcVO.subjectCode }">
				<div id="contents-area">
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
								<th>주제</th>
								<td colspan="3">${teamprojectVO.projectName}</td>
							</tr>
						
							<tr>
								<th>배점</th>
								<td colspan="3">${teamprojectVO.score } 점</td>
							</tr>
							<tr>
								<th>기간</th>
								<td colspan="3">${teamprojectVO.projectStartDate} ~ ${teamprojectVO.projectEndDate}</td>
							</tr>
							<tr>
								<th>마감일</th>
								<td colspan="3">${teamprojectVO.submitEndDate}</td>
							</tr>
							<tr>
								<th>제출구분</th>
								<td colspan="3">${(teamprojectVO.submitType eq 'T')? '팀장만제출':'개별제출'}</td>
							</tr>
							<tr>
								<th>내용</th>
								<td colspan="3">${teamprojectVO.projectDesc}</td>
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
				</div><!-- E : contents-area -->

				<c:if test="${teamprojectVO.submitType ne 'T'}">				

<c:set var="value1" value="0" />
<c:set var="value2" value="0" />				
<c:if test="${fn:length(result)>1}">
	<c:set var="value2" value="1" />
</c:if>
<c:if test="${teamprojectVO.groupId > 0 }" >
	<c:set var="value1" value="${teamprojectVO.groupId -1}" />
	<c:set var="value2" value="${teamprojectVO.groupId +1}" />
</c:if>
<c:if test="${teamprojectVO.teamCnt < value2}">
	<c:set var="value2" value="0" />
</c:if>

				<ul id="name-tab-area" class="mt-020">
					<li class="left"><a href="#!" onclick="javascript:fn_search('${value1}');" >이전 팀</a></li>
					<li class="choice">
						<select   name="groupId" onchange="javascript:fn_search(this.value);">
							<option value="0"> 전체</option>
						<c:forEach var="result" items="${result}" varStatus="status">
							<c:if test="${result.leaderYn eq 'Y'}">
							<option value="${result.groupId}" <c:if test="${result.groupId eq teamprojectVO.groupId }" >selected</c:if> >${result.groupId} 팀</option>
							</c:if>
						</c:forEach>
							 
						</select>
					</li>
					<li class="right"><a href="#!" onclick="javascript:fn_search('${value2}');">다음 팀</a></li>
				</ul><!-- E : name-tab-area -->
				</c:if>

				<div id="contents-area-1">
				
				<c:if test="${teamprojectVO.submitType eq 'T'}">
					<table class="type-2 mt-010">
						<colgroup>
							<col width="9%" />
							<col width="*" />
							<col width="15%" />
							<col width="25%" />
							<col width="17%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th>팀명</th>
								<th>팀장</th>
								<th>제출일</th>
								<th>제출<br />현황</th>
								<th>점수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
								<c:if test="${(teamprojectVO.submitType eq 'I') or (teamprojectVO.submitType eq 'T' and result.leaderYn eq 'Y')}">
								<tr>
									<td><input type="checkbox" name="arrTeamprojectId" value="${result.teamprojectSubmitId }" class="choice" /></td>
									<td class="left"> ${result.groupId} 팀</td>
									<td>${result.memName }</td>

									<td>${result.submitDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');"   >제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>
									</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											${result.evalScore}
										</c:if>
										<c:if test="${empty result.atchFileId }">
											-
										</c:if>
									</td>
								</tr>									
								</c:if>								 				
							</c:forEach>
							<c:if test="${empty result}">
						          <tr>
						            <td colspan="7">자료가 없습니다.</td>
						          </tr>
						    </c:if>
						</tbody>

					</table>
					</c:if>
					<c:if test="${teamprojectVO.submitType ne 'T'}">
					
					<table class="type-2 mt-010">
						<colgroup>
							<col width="9%" />
							<col width="14%" />
							<col width="*" />
							<col width="25%" />
							<col width="17%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th colspan="2">팀원</th>
								<th>제출일</th>
								<th>제출<br />현황</th>
								<th>점수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
								<c:if test="${(teamprojectVO.groupId == 0) or (not empty teamprojectVO.groupId  and   result.groupId eq teamprojectVO.groupId )}">
							
								<tr>
									<td><input type="checkbox" name="checkbox" class="choice" /></td>
									<td>
										<c:if test="${  result.leaderYn eq 'Y'}">
										팀장
										</c:if>
									</td>
									<td>${result.memName }</td>
									<td>${result.submitDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');"   >제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>
									</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											${result.evalScore}
										</c:if>
										<c:if test="${empty result.atchFileId }">
											-
										</c:if>
									</td>
								</tr>
								
							 	</c:if>
							</c:forEach>
						</tbody>
					</table>					
					
					
					</c:if>
					
					
					<div class="btn-area align-center mt-010"><a href="#!" class="gray-2">SMS 발송</a><a href="#!" class="gray-2">E-MAIL 발송</a></div>
				</div><!-- E : contents-area-1 -->
</c:if>
<c:if test="${empty currProcVO.subjectCode }">
				<div id="contents-area">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					</h4>
				</div>
</c:if>

			</div><!-- E : container -->