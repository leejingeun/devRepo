<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>


							
		 						<h2>	활동내역서 미작성건</h2>
								<div class="group-area">
									<table class="type-2  mb-010 mt-010">
									<thead>
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>훈련과정명</th>
											<th>월</th>
											<th>기업현장교사</th>
											<th>HRD담당자</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="result" items="${listActivityHrd}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${result.companyName}</td>
											<td><a href="/lu/activitydesc/listActivityDesc.do?companyId=${result.companyId }&traningProcessId=${result.traningProcessId }" class="text">${result.traningProcessName }</a></td>
											<td>${result.mm } 월</td>
											<td>
												<c:if test="${empty result.bigoCot}">미작성</c:if>
												<c:if test="${!empty result.bigoCot}">작성</c:if>
											</td>
											<td>
												<c:if test="${empty result.bigoHrd}">미작성</c:if>
												<c:if test="${!empty result.bigoHrd}">작성</c:if>											
											</td>
										</tr>
									</c:forEach>
 									<c:if test="${empty listActivityHrd}">
										<tr>
											<td colspan="6">데이터가 없습니다.</td>
										</tr>
									</c:if> 
									</tbody>
									</table>
								</div>
 
 
 						
		 						<h2>	주간훈련일지 미제출건</h2>
								<div class="group-area">
									<table class="type-2  mb-010 mt-010">
									<thead>
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>훈련과정명</th>
											<th>주차</th>
											<th>OJT</th>
											<th>OFF-JT</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="result" items="${listWeekTraningNoteHrd}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${result.companyName}</td>
											<td><a href="/lu/weektraning/listWeekTraningNoteHrd.do?companyId=${result.companyId }&traningProcessId=${result.traningProcessId }" class="text">${result.traningProcessName }</a></td>
											<td>${result.weekCnt } 주차</td>
											<td>
												<c:if test="${empty result.ojtState}">미작성</c:if>
												<c:if test="${result.ojtState eq 'W'}">미제출</c:if>
												<c:if test="${result.ojtState eq 'X'}">반려</c:if>
												<c:if test="${result.ojtState eq 'I'}">제출</c:if>
											</td>											
											<td>
												<c:if test="${empty result.state}">미작성</c:if>
												<c:if test="${result.state eq 'W'}">미제출</c:if>
												<c:if test="${result.state eq 'X'}">반려</c:if>
												<c:if test="${result.state eq 'I'}">제출</c:if>
											</td>
										</tr>
									</c:forEach>
 									<c:if test="${empty listWeekTraningNoteHrd}">
										<tr>
											<td colspan="6">데이터가 없습니다.</td>
										</tr>
									</c:if> 
									</tbody>
									</table>
								</div> 
								
<!-- 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										주차별 학습활동서 미제출건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>주차</th>
											<th>마감일</th>
											<th>참여현황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>00건</td>
											<td>00건</td>
											<td>00건</td>
											<td>00건</td>
											<td>00건</td>
										</tr>
									</tbody>
								</table>
-->								
  
 
<script type="text/javascript">
<!--

	
function fn_searchActivity(yyyy,term,subjectCode,classId,weekCnt){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#weekCnt").val(weekCnt);
	var reqUrl = "/lu/activity/listActivityStd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();				
} 

function fn_egov_inqire_notice( nttId, bbsId) {
	 if(bbsId == "") return false;
	 
	var reqUrl =  "/lu/cop/bbs/"+bbsId+"/selectBoardArticle.do";
	document.frm.nttId.value = nttId;
	document.frm.bbsId.value = bbsId;
	
	$("#frm").attr("action", reqUrl);
	$("#frm").attr("target","_self");
	$("#frm").submit();
}
//-->
</script>
<form:form commandName="frmActivity" name="frmActivity" method="post">
					  
<input type="hidden" name="yyyy"  id="yyyy" >
<input type="hidden" name="term"  id="term"  >
<input type="hidden" name="subjectCode"   id="subjectCode"  >
<input type="hidden" name="classId"   id="classId"  >
<input type="hidden" name="weekCnt"   id="weekCnt"  >
</form:form>

<form name="frm" id="frm"  method="post">
<input type="hidden" name="bbsId" value="" />
<input type="hidden" name="nttId"  value="" />
<input type="hidden" name="bbsTyCode" value="BBST01" />
<input type="hidden" name="bbsAttrbCode" value="BBSA03" />
<input type="hidden" name="authFlag" value="" />
<input name="pageIndex" type="hidden" value="1"/>
<input type="hidden" name="memType"  value="STD" />
<input type="hidden" name="lectureMenuMarkYn"  value="N" />
</form>