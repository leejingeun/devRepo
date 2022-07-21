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
function fn_searchTraningNote(yyyy,term,subjectCode,classId,weekCnt,subjectTraningType){
	
	$("#yyyy").val(yyyy);
	$("#term").val(term);
	$("#subjectCode").val(subjectCode);
	$("#classId").val(classId);
	$("#weekCnt").val(weekCnt);
	$("#subjectTraningType").val(subjectTraningType);
	
	var reqUrl = "/lu/traning/listTraningNoteOt.do";
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
<input type="hidden" name="weekCnt"   id="weekCnt"  >
<input type="hidden" name="subjectTraningType"   id="subjectTraningType"  >
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
										훈련일지 작성
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>교과구분</th>
											<th>학년</th>
											<th>반</th>
											<th>개설교과명</th>
											<th>주차</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="listTraningNoteCot" items="${listTraningNoteCot}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listTraningNoteCot.subjectTraningType }</td>
											<td>${listTraningNoteCot.schoolYear }</td>
											<td>${listTraningNoteCot.subClass }</td>
											<td>
												<a href="#!" onclick="javascript:fn_searchTraningNote('${listTraningNoteCot.yyyy }','${listTraningNoteCot.term }','${listTraningNoteCot.subjectCode}','${listTraningNoteCot.subClass}','${listTraningNoteCot.weekCnt}','${listTraningNoteCot.subjectTraningType }');" class="text">
												${listTraningNoteCot.subjectName }
												</a>
											</td>
											<td>${listTraningNoteCot.weekCnt}</td>
										</tr>
									</c:forEach>
									<c:if test="${empty listTraningNoteCot}">
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
											<th>반</th>
											<th>개설교과명</th>
											<th>주차</th>
											<th>제출현황</th>
										</tr>
									</thead>
									<tbody>

									<c:forEach var="listActivityNoteCot" items="${listActivityNoteCot}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listActivityNoteCot.subjectTraningType}</td>
											<td>${listActivityNoteCot.schoolYear}</td>
											<td>${listActivityNoteCot.subClass}</td>
											<td>${listActivityNoteCot.subjectName}</td>
											<td>${listActivityNoteCot.weekCnt}</td>
											<td>${listActivityNoteCot.noteCnt} / ${listActivityNoteCot.memberCnt} 명</td>
										</tr>
									</c:forEach>
 									<c:if test="${empty listActivityNoteCot}">
										<tr>
											<td colspan="6">미작성 훈련일지가 없습니다.</td>
										</tr>
									</c:if>
									</tbody>
								</table>

								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										질문과 답변
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>개설교과명</th>
											<th>제목</th>
											<th>작성일</th>
										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="result" items="${listQnaCot}" varStatus="status">
										<tr>
 											<td>${status.count}</td>
 											<td>${result.subjectName}</td>
 											<td><a href="#" onclick="javascript:fn_egov_inqire_notice( '${result.nttId }', '${result.bbsId }')" >${result.nttSj}</a></td>
 											<td>${result.frstRegisterPnttm}</td>
										</tr>									
									</c:forEach>
									<c:if test="${empty listQnaCot}">
										<tr>
											<td colspan="5">자료가 없습니다.</td>
										</tr>
								    </c:if>
								    									
		 
									</tbody>
								</table>
 
 								<ul class="page-sum mb-010 mt-010">
									<li class="float-left">
										면담일지 작성
									</li>
								</ul>
								
								<table class="type-2 mt-010  mb-010" >
									<thead>
										<tr>
											<th>번호</th>
											<th>기업명</th>
											<th>훈련과정명</th>
											<th>작성월</th>
											<th>작성수</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="listInterviewCot" items="${listInterviewCot}" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${listInterviewCot.companyName}</td>
											<td><a href="/lu/interview/listInterview.do" class="text">${listInterviewCot.hrdTraningName }</a></td>
											<td>${listInterviewCot.mm } 월</td>
											<td>${listInterviewCot.interviewCnt }</td> 
										</tr>
									</c:forEach>
 									<c:if test="${empty listInterviewCot}">
										<tr>
											<td colspan="5">데이터가 없습니다.</td>
										</tr>
									</c:if> 
									</tbody>
								</table>
 
  