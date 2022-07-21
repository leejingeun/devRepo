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
			
			var reqUrl = "/mm/traningstatus/listTraningstatus.do";
			var params = value.split("||");
			
			$("#subjectTraningType").val(params[0]);
			$("#year").val(params[1]);
			$("#term").val(params[2]);
			$("#subjectCode").val(params[3]);
			$("#classId").val(params[4]);
			$("#subjectName").val(params[5]);
			$("#subjectType").val(params[6]);
			$("#onlineType").val(params[7]);
			 
			$("#frmTraningstatus").attr("target", "_self");
			$("#frmTraningstatus").attr("action", reqUrl);
			$("#frmTraningstatus").submit();
			 
		} 
	 

//-->
</script>

			<div id="container">
			
			
<form name="frmTraningstatus" id="frmTraningstatus" method="post">

<input type="hidden" name="subjectTraningType" id="subjectTraningType" value="${traningStatusVO.subjectTraningType}">
<input type="hidden" name="yyyy" id="year" value="${traningStatusVO.yyyy}">
<input type="hidden" name="term" id="term" value="${traningStatusVO.term}">
<input type="hidden" name="subjectCode" id="subjectCode" value="${traningStatusVO.subjectCode}">

<input type="hidden" name="subjectName" id="subjectName" value="${traningStatusVO.subjectName}">
<input type="hidden" name="classId" id="classId" value="${traningStatusVO.classId}">
<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="">
<input type="hidden" name="subjectType" id="subjectType" value="">
<input type="hidden" name="onlineType" id="onlineType" value="">

				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue" style="margin: 3px 0px; width:100%;" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${traningStatusVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}"  <c:if test="${traningStatusVO.subjectCode eq menuProc.subjectCode }">selected</c:if> >${menuProc.subjectName} ${menuProc.subClass}반 </option>
						</c:forEach>							
							
						</select>
						<a href="#!" class="type-2" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
					</li>
				</ul><!-- E : search-area -->
				
</form>



				<div id="contents-area">
<c:if test="${not empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span> ${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
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
							<col width="9%" />
							<col width="*" />
							<col width="17%" />
							<col width="17%" />
							<col width="17%" />
							<col width="17%" />
						</colgroup>
						<thead>
							<tr>
								<th rowspan="2"><input type="checkbox" name="checkbox" class="choice" /></th>
								<th rowspan="2">성명</th>
								<th colspan="2">강의실</th>
								<th colspan="2">On-Line</th>
							</tr>
							<tr>
								<th class="border-left">출결</th>
								<th>진도율</th>
								<th>출결</th>
								<th>진도율</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="resultlists" items="${resultlist}" varStatus="status">
							<tr>
								<td><input type="checkbox" id="lessonId" name="lessonId" value="" class="lessonId" /></td>
								<td>${resultlists.memName }</td>

								<td>${resultlists.attend }/${resultlists.lateness }/${resultlists.totalTime - resultlists.attend - resultlists.lateness}</td>
								<td>${resultlists.realProgressRate} %</td>
								
								
								<td>${resultlists.onAttend }/${resultlists.onLateness }/	
								<c:if test="${!empty onlineTraningVO}">
 											${resultlists.onTotalTime - resultlists.onAttend - resultlists.onLateness}
								</c:if>
								<c:if test="${empty onlineTraningVO}">
 											0
 								</c:if>
								
								
								</td>
								<td>${resultlists.onRealProgressRate} %</td>
							</tr>
						</c:forEach>
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