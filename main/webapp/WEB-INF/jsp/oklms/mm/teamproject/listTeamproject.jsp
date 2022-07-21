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
 
 

		$(document).ready(function() {
			  
		});
		function fn_lec_menu_display(value){
			
			var reqUrl = "/mm/teamproject/listTeamproject.do";
			var params = value.split("||");
			
			$("#subjectTraningType").val(params[0]);
			$("#year").val(params[1]);
			$("#term").val(params[2]);
			$("#subjectCode").val(params[3]);
			$("#subClass").val(params[4]);
			$("#subjectName").val(params[5]);
			$("#subjectType").val(params[6]); 
			 
			$("#frmTeamproject").attr("target", "_self");
			$("#frmTeamproject").attr("action", reqUrl);
			$("#frmTeamproject").submit();
			 
		}
		function fn_detail(value){
			
			var reqUrl = "/mm/teamproject/getTeamproject.do";  
			$("#teamprojectId").val(value);
			
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

<input type="hidden" name="teamprojectId" id="teamprojectId"  />

				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue"  style="margin: 3px 0px; width:100%;" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${teamprojectVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>

						</select>
						<a href="#!" class="type-2" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
					</li>
				</ul><!-- E : search-area -->
</form:form>

				<div id="contents-area">
<c:if test="${!empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span>${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
					</h4> 

					<table class="type-1">
						<colgroup>
							<col width="25%" />
							<col width="*" />
							<col width="20%" />
							<col width="18%" />
						</colgroup>
						
						<tbody>
							<tr>
								<th>교과구분</th>
								<td>${currProcVO.subjectTraningType }</td>
								<th>과정구분</th>
								<td>
								  <c:choose>
								       <c:when test="${currProcVO.subjectType eq 'NORMAL' }">
											일반
								       </c:when>
								       <c:when test="${currProcVO.subjectType eq 'HSKILL'}">
											고숙련
								       </c:when>
								       <c:otherwise>
								
								       </c:otherwise>
								  </c:choose>
								</td>
							</tr>
							<tr>
								<th>훈련시간</th>
								<td>${currProcVO.traningHour }시간</td>
								<th>학점</th>
								<td>${currProcVO.point }학점</td>
							</tr>
							<tr>
								<th>온라인교육</th>
								<td>
								    <c:choose>
								       <c:when test="${currProcVO.onlineType eq 'NONE' }">
											없음
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'ONLINE' }">
											온라인
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'FLIPPED'}">
											플립러닝
								       </c:when>
								       <c:when test="${currProcVO.onlineType eq 'BLENDED'}">
											블렌디드
								       </c:when>
								       <c:otherwise>
								
								       </c:otherwise>
								   </c:choose>								
								</td>
								<th>선수여부</th>
								<td>
									${currProcVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}
									
								</td>
							</tr>
						</tbody> 
					</table>


					<table class="type-2 mt-020">
						<colgroup>
							<col width="10%" />
							<col width="*" />
							<col width="26%" />
							<col width="14%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th>회차</th>
								<th>프로젝트 주제</th>
								<th>마감일</th>
								<th>제출<br />현황</th>
								<th>채점<br />현황</th>
							</tr>
						</thead>
						<tbody>
									<c:forEach var="result" items="${result}" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td class="left"><a href="#!" onclick="javascript:fn_detail('${result.teamprojectId}');" >${result.projectName}</a></td>									
										<td>${result.submitEndDate}</td>
										<td>${result.submitCnt}/${result.totCnt}</a></td>
										<td>
											<c:choose>
										       <c:when test="${result.scoreOn eq 'N' }">
													미채점
										       </c:when>
										       <c:when test="${result.scoreOn ne 'Y' }">
													채점
										       </c:when>
 											   <c:otherwise>
													미채점
										       </c:otherwise>										       
										    </c:choose>
										</td>
									</tr>
									</c:forEach>
								<c:if test="${empty result}">
						          <tr>
						            <td colspan="5">자료가 없습니다.</td>
						          </tr>
						        </c:if>		
						</tbody>

					</table>

</c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					
					</h4>
</c:if>

				</div><!-- E : contents-area -->
			</div><!-- E : container -->