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
			
			var reqUrl = "/mm/activity/listActivity.do";
			var params = value.split("||");
			
			$("#subjectTraningType").val(params[0]);
			$("#year").val(params[1]);
			$("#term").val(params[2]);
			$("#subjectCode").val(params[3]);
			$("#classId").val(params[4]);
			$("#subjectName").val(params[5]);
			$("#subjectType").val(params[6]);
			$("#onlineType").val(params[7]);
			 
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();
			 
		}
		 
		/* HTML Form 신규, 수정 */
		function fn_formSave(){
			
			if($("#content").val()==""){
				alert("교육내용을 입력해 주세요.");
				$("#content").focus(); 
				return;
			}
			var reqUrl =  "/mm/activity/insertActivity.do";
			$("#frmActivity1").attr("target", "_self");
			$("#frmActivity1").attr("action", reqUrl);
			$("#frmActivity1").submit();

		}
		 

//-->
</script>
			<div id="container">
<form:form commandName="frmActivity" name="frmActivity" method="post"  >
<input type="hidden" name="activityNoteId"  value="${result.activityNoteId}" >

 
<input type="hidden" name="yyyy" id="year" value="${activityVO.yyyy}">
<input type="hidden" name="term" id="term" value="${activityVO.term}">
<input type="hidden" name="subjectCode" id="subjectCode" value="${activityVO.subjectCode}">
<input type="hidden" name="classId" id="classId" value="${activityVO.classId}">

<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="">
<input type="hidden" name="subjectType" id="subjectType" value="">
<input type="hidden" name="onlineType" id="onlineType" value="">


 
<input type="hidden" name="traningType"  value="${activityVO.traningType}" >
<input type="hidden" name="state"  value="${activityVO.state}" >

				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${activityVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}"  <c:if test="${activityVO.subjectCode eq menuProc.subjectCode }">selected</c:if> >${menuProc.subjectName} ${menuProc.subClass}반 </option>
						</c:forEach>							
							
						</select>
						<select id="weekCnt" name="weekCnt" onchange="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">
							<option value="">주차 선택</option>
							<c:forEach var="weeklist" items="${weeklist}" varStatus="status">
							<option value="${weeklist.weekCnt}" <c:if test="${activityVO.weekCnt eq weeklist.weekCnt }">selected</c:if> >${weeklist.weekCnt}</option>
							</c:forEach>
							 
						</select>
						<a href="#!" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
					</li>
				</ul><!-- E : search-area -->
				
</form:form>
				<div id="contents-area">
<c:if test="${not empty currProcVO.subjectCode }">
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span>${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
					</h4>
<form:form commandName="frmActivity1" name="frmActivity" method="post" enctype="multipart/form-data" >
<input type="hidden" name="activityNoteId"  value="${result.activityNoteId}" >

<input type="hidden" name="yyyy"  value="${activityVO.yyyy}" >
<input type="hidden" name="term"  value="${activityVO.term}" >
<input type="hidden" name="subjectCode"  value="${activityVO.subjectCode}" >
<input type="hidden" name="classId"  value="${activityVO.classId}" >
<input type="hidden" name="traningType"  value="${activityVO.traningType}" >
<input type="hidden" name="state"  value="${activityVO.state}" >
<input type="hidden" name="weekCnt" value="${activityVO.weekCnt}"/>
<c:set var="sumNumber" value="0" />
<c:set var="totalSumNumber" value="0" />  
<c:forEach var="result" items="${resultMember}" varStatus="status">
	<c:set var="sumNumber" value="${result.studyTime + sumNumber }" />
	<c:if test="${result.addyn eq 'N' }"><c:set var="totalSumNumber" value="${result.timeHour + totalSumNumber}" /></c:if>
</c:forEach>
					<table class="type-1">
						<colgroup>
							<col width="22%" />
							<col width="*" />
							<col width="30%" />
							<col width="22%" />
						</colgroup>
						<tbody>
							<tr>
								<th>주차</th>
								<td>${activityVO.weekCnt}주차</td>
								<th>주간 훈련시간</th>
								<td>${totalSumNumber} 시간</td>
							</tr>
							<tr>
								<th>수업내용</th>
								<td colspan="3">${subjweekStdVO.contentName}</td>
							</tr>
						</tbody>
					</table>


					<table class="type-2 mt-020">
						<colgroup>
							
							<col width="*" />
							<col width="25%" />
							<col width="17%" />
							<col width="17%" />
							<col width="14%" />
						</colgroup>
						<thead>
							<tr>
								<th>이름/학번</th>
								<th>실시일자</th>
								<th>훈련시간<br />(계획)</th>
								<th>훈련시간<br />(결과)</th>
								<th>성취도</th>
								 
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultMember}" varStatus="status">
							 
								<tr>
									<c:if test="${status.index eq '0'}"> 
										<td rowspan="${fn:length(resultMember)+1 }">${activityVO.sessionMemName}<br /><c:if test="${!empty activityVO.sessionMemId  }">( ${activityVO.sessionMemId} )</c:if></td>
										<td>${result.traningDate }
											<c:if test="${ !empty result.dayOfWeek }"> (${result.dayOfWeek })</c:if>
										</td>
									</c:if>
									<c:if test="${status.index ne '0'}">
									<td class="border-left">${result.traningDate } <c:if test="${!empty result.dayOfWeek  }">(${result.dayOfWeek })</c:if></td>
									</c:if>
									<td>
									
									<c:if test="${result.addyn eq 'N' }">${result.timeHour }시간</c:if>
									<c:if test="${result.addyn eq 'Y' }">보강훈련</c:if>
									
									</td>
									<td>
										<c:if test="${!empty result.studyTime}">${result.studyTime }시간</c:if> 																						
									</td>
									<td>	${result.achievement} </td>
								</tr>
								<c:if test="${status.last}"> 
								<tr>
									<td class="border-left">계</td>
									<td>${totalSumNumber}시간</td>
									<td>${sumNumber}시간</td>
									<td>-</td>
								</tr>
								</c:if>							
							</c:forEach>
 
							<tr>
								<th>교육내용</th>
								<td colspan="4" class="left"><input type="text" name="content"  id="content" value="${result.content }" style="width:97%;" /></td>
							</tr>
							<tr>
								<th>요청사항</th>
								<td colspan="4" class="left"><input type="text" name="reqContent" id="reqContent"   value="${result.reqContent }"  style="width:97%;"/></td>
							</tr>
							
													
						</tbody>
					</table>
</form:form>


					<div class="btn-area align-center mt-010">
							<c:if test="${result.activityNoteId eq null}">
								<a href="#!" onclick="javascript:fn_formSave();" class="orange">등록</a>
							</c:if>
							<c:if test="${result.activityNoteId ne null}">
								<a href="#!" onclick="javascript:fn_formSave();" class="orange">수정</a>
							</c:if>							
					
					</div>

</c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>					
					</h4>
					
</c:if>

				</div><!-- E : contents-area -->
			</div><!-- E : container -->