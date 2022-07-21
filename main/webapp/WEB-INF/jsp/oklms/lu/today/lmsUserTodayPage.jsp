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
	$("#subClass").val(classId);
	$("#weekCnt").val(weekCnt);
	var reqUrl = "/lu/activity/listActivityStd.do";
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
	
	var reqUrl = "/lu/report/listReportStd.do";
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
	
	var reqUrl = "/lu/teamproject/listTeamprojectStd.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();				
} 

function fn_searchDiscuss(yyyy,term,subjectCode,classId){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#subClass").val(classId); 
	
	var reqUrl = "/lu/discuss/listDiscuss.do";
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
<input type="hidden" name="subClass"   id="subClass"  >
<input type="hidden" name="weekCnt"   id="weekCnt" value="0" >
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
							
								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										학습활동서 미작성건
									</li>
								</ul>
								
								<table class="type-2 mt-010 mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>교과구분</th>
											<th>개설교과명</th>
											<th>주차</th>
											<th>작성마감</th>
											<th>작성현황</th>
										</tr>
									</thead>
									<tbody> 
									
									<c:forEach var="result" items="${listActivityNotMake}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${result.subjectTraningType}</td>
											<td><a href="#!" onclick="javascript:fn_searchActivity('${result.yyyy}','${result.term}','${result.subjectCode}','${result.classId}','${result.weekCnt}');">${result.subjectName}</a></td>
											<td>${result.weekCnt}</td>
											<td>${result.traningDate}</td>
											<td>미작성</td>
										</tr>									
									</c:forEach>
									<c:if test="${empty listActivityNotMake}">
										<tr>
											<td colspan="6">자료가 없습니다.</td>
										</tr>
								    </c:if>
									</tbody>
								</table>

								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										주차별 학습활동서 미제출건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>교과구분</th>
											<th>주차</th>
											<th>제출마감</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>
									
									<c:set var="listNum" value="0" /> 
									<c:forEach var="result" items="${listWeekActivityMakeStd}" varStatus="status">
									
										<c:if test="${result.ojtCountSubmit ne 'C'}">
											<c:set var="listNum" value="${listNum+1 }" />
											<tr>
												<td>${listNum}</td>
												<td>OJT</td>
												<td>${result.weekCnt}주차</td>
												<td>${result.traningDate}</td>
	
												<td>
												
												<c:if test="${result.ojtCount ne result.ojtWorkCount}">
												<a href="/lu/activity/listWeekActivityStd.do" >미작성	(${result.ojtCount} / ${result.ojtWorkCount })</a>
												</c:if>
												<c:if test="${result.ojtCount eq result.ojtWorkCount}">
												<a href="/lu/activity/listWeekActivityStd.do" >미제출</a>
												</c:if>
												
												</td>
		
											
											</tr>										
										</c:if>
																	
										<c:if test="${result.offCountSubmit ne 'C'}">									
											<c:set var="listNum" value="${listNum+1 }" />
											<tr>
												<td>${listNum}</td>
												<td>OFF</td>
												<td>${result.weekCnt}주차</td>
												<td>${result.traningDate}</td>
	
												<td>
												
												<c:if test="${result.offCount ne result.offWorkCount}">
												미작성(${result.offCount} / ${result.offWorkCount })
												</c:if> 
												<c:if test="${result.offCount eq result.offWorkCount}">
												<a href="/lu/activity/listWeekActivityStd.do"   >미제출</a>
												</c:if>
												
												</td>
																						
											</tr>										
										</c:if>
																													
									</c:forEach>
									<c:if test="${empty listWeekActivityMakeStd}">
										<tr>
											<td colspan="6">자료가 없습니다.</td>
										</tr>
								    </c:if>		
								    							
									</tbody>
								</table>
  

								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										Q & A 답변확인
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>제목</th>
											<th>작성자</th>
											<th>작성일</th>
										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="result" items="${listQnaStd}" varStatus="status">
										<tr>
 											<td>${status.count}</td>
 											<td>${result.subjectName}</td>
 											<td><a href="#" onclick="javascript:fn_egov_inqire_notice( '${result.nttId }', '${result.bbsId }')" >${result.nttSj}</a></td>
 											<td>${result.ntcrNm}</td>
 											<td>${result.frstRegisterPnttm}</td>
										</tr>									
									</c:forEach>
									<c:if test="${empty listQnaStd}">
										<tr>
											<td colspan="5">자료가 없습니다.</td>
										</tr>
								    </c:if>
								    									
		 
									</tbody>
								</table>


 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										과제 미제출건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과</th>
											<th>주차</th>
											<th>과제제출마감</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${listReportSubmitStd}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="#!" onclick="javascript:fn_searchReport('${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}','${result.weekCnt}');" class="text" >${result.subjectName}</a></td>
											<td>${result.weekCnt}</td>
											<td>${result.traningEndDate}</td>
											<td>미제출</td>
										</tr>										
										</c:forEach>
										<c:if test="${empty listReportSubmitStd}">
										<tr>
											<td colspan="5">미제출 과제가 없습니다.</td>
										</tr>
								    	</c:if>
									</tbody>
								</table>

 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										팀프로젝트미제출건
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
										<c:forEach var="result" items="${listTeamProjectSubmitStd}" varStatus="status">
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
											<td>미제출</td>
										</tr>										
										</c:forEach>
										<c:if test="${empty listTeamProjectSubmitStd}">
										<tr>
											<td colspan="6">미제출 과제가 없습니다.</td>
										</tr>
								    	</c:if>
									</tbody>
								</table>
 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										토론 미참여건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>주제</th>
											<th>마감일</th>
											<th>참여현황</th>
										</tr>
									</thead>
									<tbody> 
										<c:forEach var="result" items="${listDiscussStd}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="#!" onclick="javascript:fn_searchDiscuss('${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}');" class="text" >${result.subjectName}</a></td>
											<td>${result.title}</td>
 									
											<td>${result.endDate}</td>
											<td>미참여</td>
										</tr>										
										</c:forEach>
										<c:if test="${empty listDiscussStd}">
										<tr>
											<td colspan="5">미참여 토론이 없습니다.</td>
										</tr>
								    	</c:if>
									</tbody>
								</table>
  
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										온라인교과 미수강건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과</th>
											<th>주차</th>
											<th>출석마감</th>
											<th>진도율</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="result" items="${listOnlineStd}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="/lu/currproc/listCurrProc.do?subjectTraningType=${result.subjectTraningType}&year=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subClass}" class="text">${result.subjectName}</a></td>
											<td>${result.weekCnt}주차</td>
											<td>${result.weekEdDate}</td>
											<td>${result.rate}%</td>
										</tr>									
										</c:forEach>
 										<c:if test="${empty listOnlineStd}">
										<tr>
											<td colspan="5">등록된 내용이 없습니다.</td>
										</tr>
								    	</c:if>
 
									</tbody>
								</table>
  
 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										재직증빙서류 미승인건
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>학년도</th>
											<th>학기</th>
											<th>4대보험가입증명서</th>
											<th>원천징수영수증</th>
											<th>제출마감</th>
											<th>승인현황</th>
										</tr>
									</thead>
									<tbody>
									 
									<c:forEach var="result" items="${listWorkCertStd}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${result.yyyy}</td>
											<td>${result.term}</td>

											<td>
											<c:if test="${result.insuranceYn eq 'Y'}">
												<c:if test="${empty result.atchFileIdInc }">미제출</c:if>
												<c:if test="${not empty result.atchFileIdInc }">제출</c:if>
											</c:if>												
											</td>

											<td>
											<c:if test="${result.receiptYn eq 'Y'}">
												<c:if test="${empty result.atchFileIdRec }">미제출</c:if>
												<c:if test="${not empty result.atchFileIdRec }">제출</c:if>
											</c:if>	
											</td>

											<td>${result.endTime}</td>
											<td>
												<c:if test="${not empty result.state}">
													<c:if test="${result.state eq '00'}">승인대기</c:if>
													<c:if test="${result.state eq '01'}">승인</c:if>
													<c:if test="${result.state eq '02'}">반려</c:if>
												</c:if>
											</td>
										</tr>									
									</c:forEach>
 										<c:if test="${empty listWorkCertStd}">
										<tr>
											<td colspan="7">등록된 내용이 없습니다.</td>
										</tr>
								    	</c:if>
									</tbody>
								</table>
 