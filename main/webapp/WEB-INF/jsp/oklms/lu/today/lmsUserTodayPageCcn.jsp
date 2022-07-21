<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
				 
								<h2>	면담일지 미제출기업</h2>
								<div class="group-area">
									<table class="type-2  mb-010 mt-010">
									<thead>
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>훈련과정명</th>
											<th>월</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="listInterviewCcn" items="${listInterviewCcn}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listInterviewCcn.companyName}</td>
											<td><a href="/lu/interview/listInterviewCenter.do?companyId=${listInterviewCcn.companyId }&traningProcessId=${listInterviewCcn.traningProcessId }&mm=${listInterviewCcn.mm }" class="text">${listInterviewCcn.hrdTraningName }</a></td>
											<td>${listInterviewCcn.mm } 월</td>
										</tr>
									</c:forEach>
 									<c:if test="${empty listInterviewCcn}">
										<tr>
											<td colspan="4">데이터가 없습니다.</td>
										</tr>
									</c:if> 
									</tbody>
									</table>
								</div>

								<h2>담당자 변경신청</h2>
								<div class="group-area">
									<table class="type-2  mb-010 mt-010">
										<thead>
											<tr>
												<th>번호</th>
												<th>기업명</th>
												<th>구분</th>
												<th>성명</th>
												<th>신청내용</th>
											</tr>
										</thead>
										<tbody>
										 
											<c:forEach var="result" items="${listComMemberCcm}" varStatus="status">
											<tr>
												<td>${status.count}</td>
												<td>${result.companyName}</td>
												<td>											
													<c:if test="${result.memtype eq 'CCM'}" >HRD담당자</c:if>
													<c:if test="${result.memtype eq 'COT'}" >기업현장교사</c:if>											
												</td>
												<td>${result.memName}</td>
												<td>
													<c:if test="${result.updtApplicationStatus eq '1'}" >
													<a href="#!" class="btn-full-blue">${result.updtApplicationName}</a>
													</c:if>
													<c:if test="${result.updtApplicationStatus eq '2'}" >
													<a href="#!" class="btn-full-gray">${result.updtApplicationName}</a>													
													</c:if>
												</td>
											</tr>										
											</c:forEach>
											<c:if test="${empty listComMemberCcm}">
											<tr>
												<td colspan="5">변경신청이 없습니다.</td>
											</tr>
									    	</c:if>	 
									    	
										</tbody>
									</table>
									<div class="btn-area align-right mt-010">
										<a href="/lu/member/listMemberChangeApplication.do"  class="orange">열기</a>
									</div><!-- E : btn-area -->
	 							</div>
 
 
  
 
								<h2>훈련시간표 변경신청</h2>
								<div class="group-area">
									<table class="type-2">
										<colgroup>
											<col style="width:50px" />
											<col style="width:*" />
											<col style="width:*" />
											
											<col style="width:100px" />
											<col style="width:100px" />
											<col style="width:*" />
										</colgroup>
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>훈련과정명</th>
											
											<th>신청자</th>
											<th>변경신청일</th>
											<th>변경사유</th>
										</tr>
										<c:forEach var="result" items="${listTraningChangeScheduleDisapproved}" varStatus="status">
										
										<tr>
											<td>${status.count}</td>
											<td>${result.companyName}</td>
											<td>${result.hrdTraningName}</td>
											
											<td>${result.memName}</td>
											<td>${result.updateDate}</td>
											<td>${result.changeReason}</td>
										</tr>
																				
										</c:forEach>
										<c:if test="${empty listTraningChangeScheduleDisapproved}">
										<tr>
											<td colspan="6">변경신청이 없습니다.</td>
										</tr>
								    	</c:if>	
									</table>
		
									<div class="btn-area align-right mt-010">
										<a href="/lu/traningChange/listTraningChangeScheduleApplication.do" class="orange">열기</a>
									</div><!-- E : btn-area -->
		
								</div><!-- E : 훈련시간표 변경신청 -->
 
 
								<h2>주간훈련일지 미제출기업</h2>
								<div class="group-area">
									<table class="type-2  mb-010 mt-010">
										<thead>
											<tr>
												<th>번호</th>
												<th>기업명</th>
												<th>훈련과정명</th>
												<th>주차</th>
												<th>OJT</th>
												<th>OFF</th>
											</tr>
										</thead>
										<tbody>
										 
											<c:forEach var="result" items="${listWeekTraningNoteCompany}" varStatus="status">
											<tr>
												<td>${status.count}</td>
												<td>${result.companyName}</td>
												<td><a href="/lu/weektraning/listWeekTraningNoteCcn.do?companyId=${result.companyId}&traningProcessId=${result.traningProcessId}">${result.traningProcessName}</a></td>
												<td>${result.weekCnt}</td>
												<td>
													<c:if test="${result.stateOjt eq 'W'}">미제출</c:if>
													<c:if test="${result.stateOjt eq 'C'}">제출</c:if>
													<c:if test="${result.stateOjt eq 'X'}">반려</c:if>
													<c:if test="${empty result.stateOjt }">미작성</c:if>
												</td>
												<td>
													<c:if test="${result.stateOff eq 'W'}">미제출</c:if>
													<c:if test="${result.stateOff eq 'C'}">제출</c:if>
													<c:if test="${result.stateOff eq 'X'}">반려</c:if>
													<c:if test="${empty result.stateOff }">미작성</c:if>
												</td>
											</tr>										
											</c:forEach>
											<c:if test="${empty listWeekTraningNoteCompany}">
											<tr>
												<td colspan="6">미제출 기업이 없습니다.</td>
											</tr>
									    	</c:if>	 
									    	
										</tbody>
									</table>
									<div class="btn-area align-right mt-010">
										<a href="/lu/weektraning/listWeekTraningNoteCcn.do"  class="orange">열기</a>
									</div><!-- E : btn-area -->
	 							</div>
 
 <!--
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										주차별 학습활동서 미제출자
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과</th>
											<th>회차</th>
											<th>과제제출구분</th>
											<th>제출마감</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>00건</td>
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
			