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
			if(params[4]!=null && params[4]!=""){
				$("#classId").val(params[4]);
			}
			
			$("#subjectName").val(params[5]);
			$("#subjectType").val(params[6]);
			$("#onlineType").val(params[7]);
			 
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();
			 
		}
		 
		function fn_lec_company_display(value){
			
			var reqUrl = "/mm/activity/listActivity.do";
			var params = value.split("||");
			
			$("#classId").val(params[0]);
			$("#companyId").val(params[1]);
			
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();
			 
		}

		function fn_activity_mem( memId ){
			
			var reqUrl = "/mm/activity/getActivity.do";
			
			$("#memId").val(memId);
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();
			
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

<input type="hidden" name="companyId" id="companyId" value="${activityVO.companyId}">
<input type="hidden" name="memId" id="memId" value="">
 
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
<c:set var="tempCompany" value=""/>
<c:set var="tempClass" value=""/>
<c:set var="prevCompany" value=""/>
<c:set var="prevClass" value=""/>
<c:set var="nextCompany" value=""/>
<c:set var="nextClass" value=""/>				

<c:forEach var="result" items="${subjectNameList}" varStatus="status">
	<c:if test="${result.companyId eq activityVO.companyId }">
		<c:set var="prevCompany" value="${tempCompany}"/>
		<c:set var="prevClass" value="${tempClass }"/>
		
		<c:if test="${status.first}">
			<c:set var="prevCompany" value="${subjectNameList[0].companyId }"/>
			<c:set var="prevClass" value="${subjectNameList[0].classId}||${subjectNameList[0].companyId }"/>
		</c:if>	
		<c:if test="${!status.last}">
			<c:set var="nextCompany" value="${subjectNameList[status.index+1].companyId}"/>
			<c:set var="nextClass" value="${subjectNameList[status.index+1].classId}||${subjectNameList[status.index+1].companyId}"/>
		</c:if>
		<c:if test="${status.last}">
			<c:set var="nextCompany" value="${subjectNameList[0].companyId }"/>
			<c:set var="nextClass" value="${subjectNameList[0].classId}||${subjectNameList[0].companyId }"/>
		</c:if>											
	</c:if>
	<c:if test="${empty prevCompany }">
		<c:set var="tempCompany" value="${result.companyId }"/>
		<c:set var="tempClass" value="${result.classId}||${result.companyId}"/>									
	</c:if>		
</c:forEach>
				<ul id="name-tab-area">
					<li class="left"><a href="#!" onclick="javascript:fn_lec_company_display('${prevClass}');" >이전 기업명</a></li>
					<li class="choice"> 
						<select id="companyClassId" name="companyClassId"  onchange="javascript:fn_lec_company_display(this.value);" >
								<c:forEach var="result" items="${subjectNameList}" varStatus="status">
									<option value="${result.classId}||${result.companyId}" <c:if test="${result.companyId eq activityVO.companyId }">selected</c:if>>
										${result.companyName}
									</option>							
								</c:forEach>							
						</select>
					</li>
					<li class="right"><a href="#!" onclick="javascript:fn_lec_company_display('${nextClass}');" >다음 기업명</a></li>
				</ul>
				

				<div id="contents-area">
<c:if test="${not empty currProcVO.subjectCode }">
<c:set var="sumNumber" value="0" />
<c:set var="totalSumNumber" value="0" />
<c:forEach var="result" items="${resultMember}" varStatus="status">
	<c:set var="sumNumber" value="${result.studyTime + sumNumber }" />
	<c:if test="${result.addyn eq 'N' }"><c:set var="totalSumNumber" value="${result.timeHour + totalSumNumber}" /></c:if>
</c:forEach>  
					<h4>
						<p>${currProcVO.subjectName }</p>
						<span>${currProcVO.yyyy}학년도 ${currProcVO.term}학기</span>
					</h4>

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
							<col width="9%" />
							<col width="*" />
							<col width="18%" />
							<col width="18%" />
							<col width="17%" />
							<col width="15%" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th>성명</th>
								<th>훈련시간<br />(계획)</th>
								<th>훈련시간<br />(실제)</th>
								<th>학습<br />활동서</th>
								<th>과제물</th>
							</tr>
						</thead>
						<tbody>
								<c:forEach var="result" items="${memberlist}" varStatus="status1">
								<tr>
									<td><input type="checkbox" name="memSeqs" value="${result.memId},${result.atchFileId}"  class="choice" /></td>
									<td><a href="#!" id="${result.memId}" onclick="javascript:fn_activity_mem('${result.memSeq}');"  class="text">${result.memName } </a></td>
									<td>${subjweekStdVO.weekTimeHour}</td>
									<td>
									<c:if test="${result.studyTime != null and result.studyTime ne '' }">${result.studyTime }</c:if> 		
									</td>
									<td>
										<c:if test="${empty result.content }">
											<a href="#!" >미작성</a>
										</c:if>
										<c:if test="${!empty result.content }">
											<a href="#!"  >작성</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.atchFileId }">
											<a href="#!" >미제출</a>
										</c:if>
										<c:if test="${!empty result.atchFileId }">									
											<a  href="#!" onclick="javascript:com.downFile('${result.atchFileId}','1');" >제출</a>
										</c:if>
									</td>
								</tr>									
								</c:forEach>
						</tbody>
					</table>

					<div class="btn-area align-center mt-010">
						<a href="#!" onclick="javascript:alert('준비중');" class="gray-2">SMS 발송</a>
						<a href="#!" onclick="javascript:alert('준비중');" class="gray-2">E-MAIL 발송</a>
					</div>

</c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>					
					</h4>					
</c:if>
				</div><!-- E : contents-area -->
			</div><!-- E : container -->