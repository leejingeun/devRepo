<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

						<h2>면담일지</h2>

<script type="text/javascript">
<!--

$(document).ready(function() {

	
});
/*  수정,삭제 */
function fn_formSave(type){ 
	 
	if(type=="C"){
		var reqUrl =  "/lu/interview/goInsertInterview.do";
		$("#frmInterview").attr("target", "_self");
		$("#frmInterview").attr("action", reqUrl);
		$("#frmInterview").submit();	
		
	}else{
	 	if(!$(":input:radio[name=InterviewId]:checked").val()){
	 		alert("면담을 선택하세요!");
	 		return;
	 	}
		$("#interviewNoteId").val($(":input:radio[name=InterviewId]:checked").val());
		//수정
		if(type=="U"){
			var reqUrl =  "/lu/interview/goUpdateInterview.do";
			$("#frmInterview").attr("target", "_self");
			$("#frmInterview").attr("action", reqUrl);
			$("#frmInterview").submit();
		//삭제	
		}else if(type=="D"){
			if (  confirm("삭제 하시겠습니까?")) {
				var reqUrl =  "/lu/interview/deleteInterview.do";
				$("#frmInterview").attr("target", "_self");
				$("#frmInterview").attr("action", reqUrl);
				$("#frmInterview").submit();	
			}
		}		
	}	
 
}
 
function fn_formview(interviewNoteId){
	
	$("#interviewNoteId").val( interviewNoteId );
	var reqUrl = "/lu/interview/getInterview.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();	
	
}

function fn_search(param1){
	$("#pageIndex").val( param1 );
	var reqUrl = "/lu/interview/listInterview.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();				
}
//--> 
</script>
<form:form commandName="frmInterview" name="frmInterview" method="post">
						<ul class="page-sum mb-007">
							<li class="float-left">총 ${paginationInfo.totalRecordCount} 건 ( ${interviewVO.pageIndex}  /  ${paginationInfo.totalPageCount}   Page)</li>
							<li class="float-right">
 
							</li>
						</ul>
 
	<input type="hidden" name="interviewNoteId" id="interviewNoteId" value="" />
	<input type="hidden" name="pageIndex"  id="pageIndex" value="${interviewVO.pageIndex}" /> 


						<table class="type-2">
							<colgroup>
								<col style="width:40px" />
								<col style="width:80px" />
								<col style="width:80px" />
								<col style="width:140px" />
								<col style="width:140px" />
								<col style="width:*" />
								<col style="width:80px" />
							</colgroup>
							<thead>
								<tr>
									<th>선택</th>
									<th>월별</th>
									<th>작성일</th>
									<th>기업명</th>
									<th>훈련과정명</th>
									
									<th>면담자</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
								<tr>
									<td><input type="radio" name="InterviewId"  value="${result.interviewNoteId }" class="radio" /></td>
									<td>${result.interviewNoteMm }월</td>
									<td>${result.interviewNoteDate }</td>
									<td><a href="#" onclick="javascript:fn_formview('${result.interviewNoteId }');" >${result.companyName }</a></td>
									<td class="left">${result.traningProcessName }</td>									
									<td class="left">${result.interviewMemberNames }</td>
									<td>
									<c:if test="${result.sendYn eq 'Y' }" >	
									종료
									</c:if>
									<c:if test="${result.sendYn ne 'Y' }" >	
									진행
									</c:if>									
									</td>
								</tr>							
							</c:forEach>
								<!--tr>
									<td><input type="radio" name="InterviewId" value="00002" class="radio" /></td>
									<td>1월</td>
									<td>2016.01.01</td>
									<td>기업명_02</td>
									<td class="left">학습근로자1,학습근로자2</td>
									<td>종료</td>
								</tr-->	
								
							<c:if test="${empty result}">
						          <tr>
						            <td colspan="7">자료가 없습니다.</td>
						          </tr>
						    </c:if>
						    
						    							 
							</tbody>
						</table><!-- E :  list-->

</form:form>

				
						<!-- S : page-num (페이징 영역) -->
						<div class="page-num mt-015">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
						</div>
						<!-- E : page-num (페이징 영역) -->
						
						
						<div class="btn-area mt-010  align-right">						
							<a href="#!" class="gray-2"  onclick="javascript:fn_formSave('C');">새로작성</a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#!" class="gray-1" onclick="javascript:fn_formSave('D');">삭제</a>
							<a href="#!" class="orange"  onclick="javascript:fn_formSave('U');">수정</a>
						</div>
 