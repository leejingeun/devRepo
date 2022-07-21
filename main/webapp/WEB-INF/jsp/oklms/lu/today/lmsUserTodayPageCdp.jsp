<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
		 
  
	 						<h2>과제 제출현황</h2>
							<div class="group-area">
								<table class="type-2  mb-020 mt-010">
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
							</div>
 
		 
							<h2>Q & A 답변대기</h2>
							<div class="group-area">
								<table class="type-2  mb-020 mt-010">
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>제목</th>
											<th>작성일</th>
										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="result" items="${listQnaCdp}" varStatus="status">
										<tr>
 											<td>${status.count}</td>
 											<td>${result.subjectName}</td>
 											<td><a href="#" onclick="javascript:fn_egov_inqire_notice( '${result.nttId }', '${result.bbsId }')" >${result.nttSj}</a></td>
 											<td>${result.frstRegisterPnttm}</td>
										</tr>									
									</c:forEach>
									<c:if test="${empty listQnaCdp}">
										<tr>
											<td colspan="5">자료가 없습니다.</td>
										</tr>
								    </c:if>
								    									
		 
									</tbody>
								</table>
 							</div>
 							 
 
	 						<h2>팀프로젝트 제출현황</h2>
							<div class="group-area">
								<table class="type-2  mb-020 mt-010">
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
							</div>	

							<h2>토론 참여 현황</h2>
							<div class="group-area">
								<table class="type-2  mb-020 mt-010">
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

									<c:forEach var="result" items="${listDiscussCdp}" varStatus="status">
										<tr>
 											<td>${status.count}</td>
 											<td><a href="#" class="text" onclick="javascript:fn_searchDiscuss('${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}');">${result.subjectName}</a></td>
 											<td>${result.title}</td>
 											<td>${result.endDate}</td>
 											<td>${result.totCnt} / ${result.lecCnt}</td>
										</tr>									
									</c:forEach>
									<c:if test="${empty listDiscussCdp}">
										<tr>
											<td colspan="5">자료가 없습니다.</td>
										</tr>
								    </c:if>

									</tbody>
								</table>
 							</div>
 							  
 
 
	 						<h2>온라인교과 수강현황</h2>
							<div class="group-area">
								<table class="type-2  mb-020 mt-010"">
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>주차</th>
											<th>종료일</th>
											<th>수강현황</th>
										</tr>
									</thead>
									<tbody>
									
										<c:forEach var="result" items="${listOnlineCdp}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td><a href="/lu/currproc/listCurrProc.do?subjectTraningType=${result.subjectTraningType}&year=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subClass}" class="text">${result.subjectName}</a></td>
											<td>${result.weekCnt}주차</td>
											<td>${result.weekEdDate}</td>
											<td>${result.attCnt}/${result.totCnt}</td>
										</tr>									
										</c:forEach>
 										<c:if test="${empty listOnlineCdp}">
										<tr>
											<td colspan="5">등록된 내용이 없습니다.</td>
										</tr>
								    	</c:if>
								    	
									</tbody>
								</table>
							</div>
 
 						<c:set var="yyyy" value="" />
 						<c:set var="term" value="" />
 						<h2>재직증빙서류 제출현황</h2>
						<div class="group-area">
							<table class="type-2  mb-020 mt-010">
								<colgroup>
									<col style="width:50px" />
									<col style="width:80px" />
									<col style="width:150px" />
									
									<col style="width:150px" />
									<col style="width:150px" />
									<col style="width:*" />
									<col style="width:150px" />
								</colgroup>
								<thead>
									<tr>
										<th>선택</th>
										<th>학년</th>
										<th>기업체수</th>
										
										<th>4대보험가입증명서</th>
										<th>원천징수영수증</th>
										<th>제출마감일</th>
										<th>승인완료</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="result" items="${listWorkCertCdp}" varStatus="status">
 						<c:set var="yyyy" value="${result.yyyy }" />
 						<c:set var="term" value="${result.term }" />									
									<tr>
										<td>${status.count}</td>
										<td>${result.schoolYear}</td>
										<td>${result.companyCnt}</td>
										
										<td>${result.incCnt}/${result.totCnt}</td>											
										<td>${result.recCnt}/${result.totCnt}</td>
										<td>${result.endTime}</td>
										<td>${result.appCnt}/${result.totCnt}</td>
									</tr>
									</c:forEach>
									<c:if test="${empty listWorkCertCdp}">
									<tr>
										<td colspan="7">등록된 정보가 없습니다.</td>
									</tr>
							    	</c:if>	
							    									
							 
								</tbody>
							</table>

							<div class="btn-area align-right mt-010">
								<!-- 임시주석 -->
								<!--<a href="#!" class="yellow">미제출자 SMS발송</a>--> <a href="/lu/workcert/listWorkCert.do?yyyy=${yyyy}&term=${term}&search=top"  class="orange">열기</a>
							</div><!-- E : btn-area -->

						</div><!-- E : 개별과제 제출현황 -->
						 
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

//-->
</script>
<form:form commandName="frmActivity" name="frmActivity" method="post">
					  
<input type="hidden" name="yyyy"  id="yyyy" >
<input type="hidden" name="term"  id="term"  >
<input type="hidden" name="subjectCode"   id="subjectCode"  >
<input type="hidden" name="classId"   id="classId"  >
<input type="hidden" name="subClass"   id="subClass"  >
<input type="hidden" name="weekCnt"   id="weekCnt" value="0"   >
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