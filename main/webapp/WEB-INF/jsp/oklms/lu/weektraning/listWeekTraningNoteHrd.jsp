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


						<h2>주간훈련일지 조회</h2>

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
	var reqUrl = "/lu/weektraning/listWeekTraningNoteHrd.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
};
function fn_search(){ 
	var reqUrl = "/lu/weektraning/listWeekTraningNoteHrd.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
}
function checkboxClick(companyIdstd,traningProcessIdstd){	 
   	$("#companyId").val( companyIdstd );
   	$("#traningProcessId").val( traningProcessIdstd );
}
function fn_print(){ 
	var reqUrl = "/lu/weektraning/printWeekTraningNote.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_blank");
	$("#frmWeekTraning").submit();	 
}
function fn_excel(){ 
	var reqUrl = "/lu/weektraning/excelWeekTraningNote.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
}

function fn_detail(weekCnt , traningType){ 
	$("#weekCnt").val( weekCnt );
	$("#addyn").val( "N" );
	$("#traningType").val( traningType );
	var reqUrl = "/lu/weektraning/getWeekTraningNoteHrd.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
	$("#frmWeekTraning").submit();	 
}

function fn_detailAdd(weekCnt , traningType){ 
	$("#weekCnt").val( weekCnt );
	$("#addyn").val( "Y" );
	$("#traningType").val( traningType );
	var reqUrl = "/lu/weektraning/getWeekTraningNoteHrd.do";
	$("#frmWeekTraning").attr("action", reqUrl);
	$("#frmWeekTraning").attr("target","_self");
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

							<select name="term" onchange="javascript:fn_search();"> 
								<option value="1" <c:if test="${traningNoteVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
								<option value="2" <c:if test="${traningNoteVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
								<option value="3" <c:if test="${traningNoteVO.term eq '3' }" > selected="selected"  </c:if>>3학기</option>
								<option value="4" <c:if test="${traningNoteVO.term eq '4' }" > selected="selected"  </c:if>>4학기</option>
							</select>
							<a href="#!">조회</a>
						</div><!-- E : search-box-1 -->
</form:form>
						<table class="type-2 mt-010">
							<colgroup>
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<thead>
								<tr>
									<th rowspan="2">주차</th>
									<th rowspan="2">학습기간</th>
									<th colspan="2">제출현황 (정규훈련)</th>
									<th colspan="2">제출현황(보강훈련)</th>
								</tr>
								<tr>
									<th class="border-left">OJT</th>
									<th>OFF-JT</th>
									<th class="border-left">OJT</th>
									<th>OFF-JT</th>
								</tr>
							</thead>
							<tbody  id="tableList">
								<c:forEach var="result" items="${bottomList}" varStatus="status">
								<tr>
									<td>${result.weekCnt}주차</td>
									<td>${result.traningSt} ~ ${result.traningEd}</td>
									<td>
										<c:if test="${empty result.ojtState}" >
											-
										</c:if>
										<c:if test="${result.ojtState eq 'W'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');" class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>
										<c:if test="${result.ojtState  eq 'I'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.ojtState  eq 'X'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');"  class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${result.ojtState  eq 'C'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OJT');"  class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.state}" >
											-
										</c:if>
										<c:if test="${result.state eq 'W'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');"  class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>
										<c:if test="${result.state  eq 'I'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.state  eq 'X'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');" class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${result.state  eq 'C'}" >
											<a href="#!" onclick="javascript:fn_detail('${result.weekCnt}','OFF');" class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.ojtAddState}" >
											-
										</c:if>
										<c:if test="${ result.ojtAddState eq 'W' }" >
											<a href="#!"  onclick="javascript:fn_detailAdd('${result.weekCnt}','OJT');"   class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'I' }" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OJT');"  class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${ result.ojtAddState eq 'X' }" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OJT');"  class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'C' }" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OJT');"  class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.addState}" >
											-
										</c:if>
										<c:if test="${result.addState  eq 'W'}" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OFF');"   class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>										
										<c:if test="${result.addState  eq 'I'}" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OFF');"  class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.addState  eq 'X'}" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OFF');"  class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>
										<c:if test="${result.addState  eq 'C'}" >
											<a href="#!" onclick="javascript:fn_detailAdd('${result.weekCnt}','OFF');"  class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>										
									</td>									
								</tr>
							</c:forEach>
							
							<c:if test="${empty bottomList}" >
								<tr>
									<td colspan="6"> 훈련정보가 없습니다. </td>
								</tr>
							</c:if>
							
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_excel();" class="gray-1">엑셀저장</a>	
							<a href="#!" onclick="javascript:fn_print();" class="gray-1">출력</a>
						</div>
						
						
						
			<ul id="student-popup">
				<li class="company-area" id="interviewCompanyList">
				</li>
				<li class="popup-bg"></li>
			</ul><!-- E : student-popup -->