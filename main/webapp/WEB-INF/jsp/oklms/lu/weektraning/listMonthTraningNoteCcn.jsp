<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>


						<h2>월간 학습근로자 출석부</h2>

		<script type="text/javascript" src="/js/oklms/jquery-latest.js"></script>
		<script type="text/javascript" src="/js/oklms/jquery-common.js"></script>
		<script type="text/javascript" src="/js/oklms/jquery_interview.js"></script>
<script type="text/javascript">
<!--

/* 학습활동서 권한 체크 */
$(document).ready(function() {
	$.ajax({
		  type: "POST",
		  url: "/lu/interview/ajaxInterviewCompany.do",
		  data: { "searchCompanyName": "","searchHrdTraningName":"","pageIndex":"1"},
		  success:function( html ) {
			$( "#interviewCompanyList" ).html( html );			
		  }
	});
 
	$("#tableList tr").each(function(){ 
		   $(this).bind('mouseover focusin', function(){ 
		         $(this).addClass("highlight");
		   }),$(this).bind('mouseout focusout', function(){
		         $(this).removeClass("highlight");
		   });
	}); 
});




/* 학습활동서 권한 체크 */
function searchCompany(param){
	var searchCompanyName = $("#searchCompanyName").val( );
	$.ajax({
		  type: "POST",
		  url: "/lu/interview/ajaxInterviewCompany.do",
		  data: { "searchCompanyName": searchCompanyName,"searchHrdTraningName":"","pageIndex":param},
		  success:function( html ) {
			$( "#interviewCompanyList" ).html( html );			
		  }
	});	
}

var hideCompanypop = function(){ 
	var reqUrl = "/lu/weektraning/listMonthTraningNoteCcn.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
};
function fn_search(){ 
	var reqUrl = "/lu/weektraning/listMonthTraningNoteCcn.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
}
function checkboxClick(companyIdstd,traningProcessIdstd){	 
   	$("#companyId").val( companyIdstd );
   	$("#traningProcessId").val( traningProcessIdstd );
}

function fn_excel(){
	var reqUrl = "/lu/weektraning/excelMonthTraningNoteCcn.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	
}

function fn_print(){
	var reqUrl = "/lu/weektraning/printMonthTraningNoteCcn.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_blank");
	$("#frmWeekTraning").submit();	
}
//-->
</script>
						<div class="group-area">
							<table class="type-2">
								<colgroup>
									<col style="width:250px" />
									<col style="width:*" />
									<col style="width:*" />
									<col style="width:*" />
								</colgroup>
								<tr>
									
									<th>기업명</th>
									<th>소재지</th>
									<th>선정일</th>
									<th>훈련과정명</th>
								</tr>
								<c:if test="${!empty result}">
								<tr>
									<td>${result.companyName }</td>
									<td>${result.address }</td>
									<td>${result.insertDate }</td>
									<td>${result.hrdTraningName }</td>
								</tr>
								</c:if>
								<c:if test="${empty result}">
								<tr>
									<td colspan="4">기업체를 선택하세요</td>
								</tr>								
								</c:if>
							</table>
							<div class="btn-area align-right mt-010">
								<a href="javascript:showCompanypop();" class="yellow">기업체검색</a>
							</div><!-- E : btn-area -->
						</div>

<form:form commandName="frmWeekTraning" name="frmWeekTraning" method="post"  >
		<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
		<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
		<input type="hidden" name="weekCnt" id="weekCnt"  />
		<input type="hidden" name="traningType" id="traningType"  />	
<input type="hidden" name="addyn" id="addyn"  />
						<div class="search-box-1 mt-020 ">
							<select id="yyyy" name="yyyy" onchange="javascript:fn_search();" > 
									<c:forEach var="i" begin="0" end="5" step="1">
								      <option value="${nowYear-i}" <c:if test="${traningNoteVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
								    </c:forEach>								
							</select> 

							<select name="mm" onchange="javascript:fn_search();"> 
									<c:forEach var="i" begin="1" end="12" step="1">
								      <option value="${i}" <c:if test="${traningNoteVO.mm eq i }" > selected="selected"  </c:if>>${i}월</option>
								    </c:forEach>
							</select>
							
							<a href="#!" onclick="javascript:fn_search();">조회</a>
						</div><!-- E : search-box-1 -->
</form:form>

<c:set var="memCnt" value="0" />
<c:if test="${!empty traningNoteVO.traningNoteVOArr}" >	
	<c:set var="memCnt" value="${fn:length(traningNoteVO.traningNoteVOArr[0])*2}" />	 
</c:if>


						<table class="type-2 mt-020">
							<colgroup>
								<col style="width:120px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<thead>
								<tr>
									<th rowspan="3">일자</th>
									<th colspan="${memCnt}">학습근로자</th>
								</tr>
								<tr>
								<c:if test="${!empty traningNoteVO.traningNoteVOArr}" >	

										<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[0]}" varStatus="status">
										
										<th colspan="2" class="border-left">${result.memName}</th>
										
										</c:forEach>

								</c:if>
								</tr>
								<tr>
										<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[0]}" varStatus="status">

										<th <c:if test="${status.first}">class="border-left"</c:if>>OJT</th>
										<th>Off-JT</th>

										</c:forEach>
								</tr>
							</thead>
							<tbody>
<c:if test="${!empty traningNoteVO.traningNoteVOArr[0] }">
								 <c:forEach var="i" begin="0" end="${lastDay}" step="1">

<c:if test="${!empty traningNoteVO.traningNoteVOArr[i] }">								 
								 <tr>
								 	<td>
								 	<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[i]}" varStatus="status">
								 	<c:if test="${status.first }">
								 		${result.traningDate}
								 	</c:if>
								 	</c:forEach>								 	
								 	</td>
								 	
									<c:forEach var="result" items="${traningNoteVO.traningNoteVOArr[i]}" varStatus="status">									
									<td>${result.studyTimeOjt} ${fn:replace(result.addStudyTimeOjt, 'add', '보강')}</td>
									<td>${result.studyTimeOff} ${fn:replace(result.addStudyTimeOff, 'add', '보강')}</td>
									</c:forEach>									

								</tr>
</c:if>								 
								 </c:forEach>
								  
	<c:if test="${!empty resultTotal }">							  
								  
								 <tr>
								 	<td>수강합계</td>
								 	
									<c:forEach var="result" items="${resultTotal}" varStatus="status">									
									<td>${result.studyTimeOjt} <c:if test="${!empty result.addStudyTimeOjt}">(${result.addStudyTimeOjt})</c:if></td>
									<td>${result.studyTimeOff} <c:if test="${!empty result.addStudyTimeOff}">(${result.addStudyTimeOff})</c:if></td>
									</c:forEach>									

								</tr>								  
	</c:if>							  
 								<tr>
									<th>계획합계</th>
									<td colspan="${memCnt}">OJT : ${resultSum.sumTimeOjt } 시간   Off-JT : ${resultSum.sumTimeOff } 시간</td>
								</tr>
								<tr>
									<th>합계 (보강)</th>
									<td colspan="${memCnt}">OJT : ${resultSum.addSumTimeOjt } 시간   Off-JT : ${resultSum.addSumTimeOff } 시간</td>
								</tr>
								<tr>
									<th>총계</th>
									<td colspan="${memCnt}">OJT : ${resultSum.totTimeOjt } 시간   Off-JT : ${resultSum.totTimeOff } 시간</td>
								</tr>								
</c:if>
<c:if test="${empty traningNoteVO.traningNoteVOArr[0] }">
  								<tr>
  									<td colspan="2"> 데이터가 없습니다.</td>
  								</tr>
</c:if>
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_excel();"  class="orange">엑셀저장</a>
							<a href="#!"  onclick="javascript:fn_print();" class="gray-1">출력</a>
						</div>

		<ul id="student-popup">
				<li class="company-area" id="interviewCompanyList">
				</li>
				<li class="popup-bg"></li>
		</ul><!-- E : student-popup -->