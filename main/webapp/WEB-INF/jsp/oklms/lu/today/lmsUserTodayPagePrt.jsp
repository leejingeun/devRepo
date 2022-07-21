<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
								<h2>TODAY</h2>

<script type="text/javascript">
<!--

	
function fn_searchActivity(yyyy,term,subjectCode,classId,weekCnt){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#weekCnt").val(weekCnt);
	var reqUrl = "/lu/activity/listActivityPrt.do";
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
function fn_searchTraningNote(yyyy,term,subjectCode,classId,weekCnt,subjectTraningType){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#weekCnt").val(weekCnt);
	$("#subjectTraningType").val(subjectTraningType);
	
	var reqUrl = "/lu/traning/listTraningNote.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();					
}
function fn_searchReport(yyyy,term,subjectCode,classId,weekCnt){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#subClass").val(classId);
	$("#weekCnt").val(weekCnt);
	
	var reqUrl = "/lu/report/listReport.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();				
} 
function fn_searchTeam(yyyy,term,subjectCode,classId){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#subClass").val(classId); 
	
	var reqUrl = "/lu/teamproject/listTeamproject.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();				
} 
//-->
</script>
<form:form commandName="frmActivity" name="frmActivity" method="post">
					  
<input type="hidden" name="yyyy"  id="yyyy" >
<input type="hidden" name="term"  id="term"  >
<input type="hidden" name="subjectCode"   id="subjectCode"  >
<input type="hidden" name="classId"   id="classId"  >
<input type="hidden" name="subClass"   id="subClass"  >
<input type="hidden" name="weekCnt"   id="weekCnt" value="0" >
 
<input type="hidden" name="subjectTraningType"   id="subjectTraningType"  >

</form:form>

<form name="frm" id="frm"  method="post">
<input type="hidden" name="bbsId" value="" />
<input type="hidden" name="nttId"  value="" />
<input type="hidden" name="bbsTyCode" value="BBST01" />
<input type="hidden" name="bbsAttrbCode" value="BBSA03" />
<input type="hidden" name="authFlag" value="" />
<input name="pageIndex" type="hidden" value="1"/>
<input type="hidden" name="memType"  value="PRT" />
<input type="hidden" name="lectureMenuMarkYn"  value="N" />
</form>

 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										훈련일지 작성
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>교과구분</th>
											<th>학년</th>
											<th>개설교과명</th>
											<th>주차</th>
										</tr>
									</thead>
									<tbody>

									<c:forEach var="listTraningNotePrt" items="${listTraningNotePrt}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listTraningNotePrt.subjectTraningType }</td>
											<td>${listTraningNotePrt.schoolYear }</td>
											<td>
												<a href="#!" onclick="javascript:fn_searchTraningNote('${listTraningNotePrt.yyyy }','${listTraningNotePrt.term }','${listTraningNotePrt.subjectCode}','${listTraningNotePrt.subClass}','${listTraningNotePrt.weekCnt}','${listTraningNotePrt.subjectTraningType }');" class="text">
												${listTraningNotePrt.subjectName }
												</a>
											</td>
											<td>${listTraningNotePrt.weekCnt}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty listTraningNotePrt}">
										<tr>
											<td colspan="5">미작성 훈련일지가 없습니다.</td>
										</tr>
									</c:if>	

									</tbody>
								</table>

 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										학습활동서 확인
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>교과구분</th>
											<th>학년</th>
											<th>개설교과명</th>
											<th>주차</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>

									<c:forEach var="listActivityNotePrt" items="${listActivityNotePrt}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listActivityNotePrt.subjectTraningType}</td>
											<td>${listActivityNotePrt.schoolYear}</td>
											<td><a href="#" class="text" onclick="javascript:fn_searchActivity('${listActivityNotePrt.yyyy}','${listActivityNotePrt.term}','${listActivityNotePrt.subjectCode}','${listActivityNotePrt.subClass}','${listActivityNotePrt.weekCnt}');" >${listActivityNotePrt.subjectName}</a></td>
											<td>${listActivityNotePrt.weekCnt}</td>
											<td>${listActivityNotePrt.noteCnt} / ${listActivityNotePrt.memberCnt} 명</td>
										</tr>
									</c:forEach>
 									<c:if test="${empty listActivityNotePrt}">
										<tr>
											<td colspan="6">미작성 훈련일지가 없습니다.</td>
										</tr>
									</c:if>
									</tbody>
								</table>

 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										과제 제출현황
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
										<c:forEach var="result" items="${listReportSubmitPrt}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="#!" onclick="javascript:fn_searchReport('${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}','${result.weekCnt}');" class="text" >${result.subjectName}</a></td>
											<td>${result.weekCnt}</td>
											<td>${result.submitEndDate}</td>
											<td>${result.scoreCnt}/${result.totCnt}명</td>
										</tr>										
										</c:forEach>
										<c:if test="${empty listReportSubmitPrt}">
										<tr>
											<td colspan="5">미제출 과제가 없습니다.</td>
										</tr>
								    	</c:if>
								    	
									</tbody>
								</table>
 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
									  팀프로젝트 제출현황
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과</th>
											<th>팀프로젝트주제</th>
											
											<th>과제제출구분</th>
											<th>제출마감</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${listTeamProjectSubmitPrt}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="#!" onclick="javascript:fn_searchTeam('${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}');" class="text" >${result.subjectName}</a></td>
											<td>${result.projectName}</td>
											
											<td>
												<c:if test="${result.submitType eq 'T'}">
												팀 제출
												</c:if>
												<c:if test="${result.submitType eq 'I'}">
												개별 제출
												</c:if>	
											</td>
											<td>${result.projectEndDate}</td>
											<td>
												<c:if test="${result.submitType eq 'T'}">
												${result.teamScoreCnt}/${result.teamTotCnt}팀
												</c:if>
												<c:if test="${result.submitType eq 'I'}">
												${result.scoreCnt}/${result.totCnt}명
												</c:if>												
											</td>
										</tr>										
										</c:forEach>
										<c:if test="${empty listTeamProjectSubmitPrt}">
										<tr>
											<td colspan="6">미제출 과제가 없습니다.</td>
										</tr>
								    	</c:if>
									</tbody>
								</table>
 