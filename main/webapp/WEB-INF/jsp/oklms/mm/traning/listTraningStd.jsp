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

function fn_lec_menu_display(value){
	
	var reqUrl = "/mm/traning/listTraning.do";
	var params = value.split("||");
	
	$("#subjectTraningType").val(params[0]);
	$("#year").val(params[1]);
	$("#term").val(params[2]);
	$("#subjectCode").val(params[3]);
	$("#classId").val(params[4]);
	$("#subjectName").val(params[5]);
	$("#subjectType").val(params[6]);
	$("#onlineType").val(params[7]);
	 
	$("#frmTraning").attr("target", "_self");
	$("#frmTraning").attr("action", reqUrl);
	$("#frmTraning").submit();
	 
}
 
//--> 
</script>
			<div id="container">
<form name="frmTraning" id="frmTraning" method="post">

<input type="hidden" name="subjectTraningType" id="subjectTraningType" value="${traningNoteVO.subjectTraningType}">
<input type="hidden" name="yyyy" id="year" value="${traningNoteVO.yyyy}">
<input type="hidden" name="term" id="term" value="${traningNoteVO.term}">
<input type="hidden" name="subjectCode" id="subjectCode" value="${traningNoteVO.subjectCode}">

<input type="hidden" name="subjectName" id="subjectName" value="${traningNoteVO.subjectName}">
<input type="hidden" name="classId" id="classId" value="${traningNoteVO.classId}">
<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="">
<input type="hidden" name="subjectType" id="subjectType" value="">
<input type="hidden" name="onlineType" id="onlineType" value="">
				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${traningNoteVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}"  <c:if test="${traningNoteVO.subjectCode eq menuProc.subjectCode }">selected</c:if> >${menuProc.subjectName} ${menuProc.subClass}반 </option>
						</c:forEach>							
							
						</select>
						<select id="weekCnt" name="weekCnt" onchange="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">
							<option value="">주차 선택</option>
							<c:forEach var="weeklist" items="${weeklist}" varStatus="status">
							<option value="${weeklist.weekCnt}" <c:if test="${traningNoteVO.weekCnt eq weeklist.weekCnt }">selected</c:if> >${weeklist.weekCnt}</option>
							</c:forEach>
							 
						</select>
						<a href="#!" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());">검색</a>
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
							 
						</tbody>
					</table>
 
<c:forEach var="timeList" items="${timeList}" varStatus="status">
						 <c:if test="${empty resultSum[status.index].weekId}">

						 <dl class="learner-training">
								<dt>
						 	 훈련시간표를 등록해주세요.  
						 		</dt>
						 </dl> 
						 	 
						 </c:if>
						<c:if test="${!empty resultSum[status.index].weekId}" >
 
							<dl class="learner-training">
								<dt>정규수업 : ${resultSum[status.index].traningDate}(${resultSum[status.index].dayOfWeek}) ${resultSum[status.index].traningSt}~${resultSum[status.index].traningEd} (${resultSum[status.index].timeHour}시간)  ${result.traniningNoteMasterId } </dt>
								<dd>
									<table class="type-2">
										<colgroup>
										
											<col width="*" />
											<col width="18%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />
											<col width="15%" />											
											
										</colgroup>
										<thead>
											<tr>
												<th>기업명</th>
												<th>성명</th>
												<th>훈련<br />시간</th>
												<th>성취도</th>
												<th>온라인</th>
												<th>비고</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="resultlist" items="${resultlistSum[status.index]}" varStatus="statuslist">
											<tr>
 
												<td>${result.companyName}</td>
												<td>${resultlist.memName}</td>
												<td>
													<c:if test="${!empty resultlist.studyTime }" >
													${resultlist.studyTime}
													</c:if>
													<c:if test="${empty resultlist.studyTime }" >
													${resultSum[status.index].timeHour}
													</c:if>
												</td>
 												<td> ${resultlist.achievement}</td>								
												<td>${resultlist.attendProgress}%</td>
												<td class="left">${resultlist.bigo}</td>
											</tr>
											</c:forEach>
											<tr>
												<th>총평</th>
												<td colspan="5" class="left">${resultSum[status.index].review}</td>
											</tr>
										</tbody>
									</table>


								</dd>
							</dl><!-- E : 정규수업 -->
							</c:if>
  </c:forEach>
 
 </c:if>
<c:if test="${empty currProcVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>					
					</h4>
					
</c:if>
				</div><!-- E : contents-area -->
			</div><!-- E : container -->