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
		function fn_lec_menu_display(value)
		{
			
			var reqUrl = "/mm/discuss/listDiscuss.do";
			var params = value.split("||");
			
			$("#subjectTraningType").val(params[0]);
			$("#yyyy").val(params[1]);
			$("#term").val(params[2]);
			$("#subjectCode").val(params[3]);
			$("#subClass").val(params[4]);
			 
			$("#frmDiscuss").attr("target", "_self");
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
			 
		}  
		/* 토론 상세조회 페이지로 이동 */
		function fn_read( param1, param2, param3, param4, param5)
		{
			var startDt = param2;
			var endDt = param3;
			var currentDt = param4;
			var title = param5;
			
			if(startDt > currentDt){
				alert("토론 제목 = ["+title+"] 공개시작전 입니다.");
				return false;
			} else if(currentDt > endDt){
				alert("토론 제목 = ["+title+"] 마감 완료되없습니다.");
				return false;
			}
			
			$("#discussId").val( param1 );
			
			var reqUrl = "/mm/discuss/getDiscuss.do";
			
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
		/* 채점결과 상세화면 이동 */
		function fn_std_result_evalScore( param1, param2, param3, param4, param5)
		{
<c:if test="${ loginAuthGroupId eq '2016AUTH0000003' }">
			var startDt = param2;
			var endDt = param3;
			var currentDt = param4;
			var title = param5;
			
			if(startDt > currentDt){
				alert("토론 제목 = ["+title+"] 공개시작전 입니다.");
				return false;
			} else if(currentDt > endDt){
				alert("토론 제목 = ["+title+"] 마감 완료되없습니다.");
				return false;
			}
			
			$("#discussId").val( param1 );
			
			var reqUrl = "/mm/discuss/selectDiscuss.do";
			
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
</c:if>			
		}
//-->
</script>

			<div id="container">
<form id="frmDiscuss" name="frmDiscuss" method="post">
	<input type="hidden" id="discussId" name="discussId" />
	<input type="hidden" id="discussOpinionId" name="discussOpinionId" />
	<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
	<input type="hidden" id="term" name="term" value="${discussVO.term}" />
	<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
	<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />			
			
				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue"  style="margin: 3px 0px; width:100%;" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${discussVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>

						</select>
						<a href="#!"  class="type-2" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
					</li>
				</ul><!-- E : search-area -->

</form>
				<div id="contents-area">
<c:if test="${!empty currProcReadVO.subjectCode }">
					<h4>
						<p>${currProcReadVO.subjectName}</p>
						<span>${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</span>
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
								<td>
									<c:choose>
									<c:when test="${currProcReadVO.subjectTraningType eq 'OFF'}">
										Off-JT
									</c:when>
									<c:otherwise>
										OJT
									</c:otherwise>
									</c:choose>
								</td>
								<th>과정구분</th>
								<td>
									<c:choose>
									<c:when test="${currProcReadVO.subjectType eq 'HSKILL'}">
										고숙련
									</c:when>
									<c:otherwise>
										일반
									</c:otherwise>
									</c:choose>								
								</td>
							</tr>
							<tr>
								<th>교수</th>
								<td>${currProcReadVO.memName}</td>
								<th>학점</th>
								<td>${currProcReadVO.point}학점</td>
							</tr>
							<tr>
								<th>온라인교육</th>
								<td>${currProcReadVO.onlineTypeName}</td>
								<th>선수여부</th>
								<td>${currProcReadVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
							</tr>
						</tbody>
					</table>


					<table class="type-2 mt-020">
						<colgroup>
							<col width="10%" />
							<col width="*" />
							<col width="32%" />
							<col width="14%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>기간</th>
								<th>의견</th>
								<th>채점<br />현황</th>
							</tr>
						</thead>
						<tbody>
		<c:forEach var="result" items="${resultDiscussList}" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td class="left"><a href="#fn_read" onclick="javascript:fn_read('${result.discussId}','${result.startDt}','${result.endDt}','${result.currentDt}','${result.title}'); return false" class="text">${result.title}</a></td>
								<td class="left">${result.startDate} ~ ${result.endDate}</td>
								<td>${result.opinionCnt}</td>
								<td>
									<c:if test="${result.evalYn eq 'N'}">
										미평가
									</c:if>
									<c:if test="${result.evalYn eq 'Y'}">
										<c:choose>
										<c:when test="${result.stdMarkResultState eq '완료'}">
											<a href="#fn_std_result_evalScore" onclick="javascript:fn_std_result_evalScore('${result.discussId}','${result.startDt}','${result.endDt}','${result.currentDt}','${result.title}'); return false" >${result.stdMarkResultState}</a>
										</c:when>
										<c:otherwise>
											<a href="#fn_std_result_evalScore" onclick="javascript:fn_std_result_evalScore('${result.discussId}','${result.startDt}','${result.endDt}','${result.currentDt}','${result.title}'); return false"  >${result.stdMarkResultState}</a>
										</c:otherwise>
										</c:choose>
									</c:if>								
								</td>
							</tr>
		</c:forEach>
		<c:if test="${empty resultDiscussList}">
							<tr>
								<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
							</tr>
		</c:if>
						</tbody>
					</table>
</c:if>
<c:if test="${empty currProcReadVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					
					</h4>
</c:if>				
				</div><!-- E : contents-area -->
			</div><!-- E : container -->