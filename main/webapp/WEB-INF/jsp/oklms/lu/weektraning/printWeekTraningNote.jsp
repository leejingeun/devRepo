<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>
  <div id="pop-wrapper" class="min-w650" >
						<h2>주간훈련일지 조회</h2> 
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
						</div>
 
 
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
											<a href="#!" class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>
										<c:if test="${result.ojtState  eq 'I'}" >
											<a href="#!" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.ojtState  eq 'X'}" >
											<a href="#!" class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${result.ojtState  eq 'C'}" >
											<a href="#!" class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.state}" >
											-
										</c:if>
										<c:if test="${result.state eq 'W'}" >
											<a href="#!" class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>
										<c:if test="${result.state  eq 'I'}" >
											<a href="#!" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.state  eq 'X'}" >
											<a href="#!" class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${result.state  eq 'C'}" >
											<a href="#!" class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.ojtAddState}" >
											-
										</c:if>
										<c:if test="${ result.ojtAddState eq 'W' }" >
											<a href="#!" class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'I' }" >
											<a href="#!" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${ result.ojtAddState eq 'X' }" >
											<a href="#!" class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>										
										<c:if test="${ result.ojtAddState eq 'C' }" >
											<a href="#!" class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>
									</td>
									<td>
										<c:if test="${empty result.addState}" >
											-
										</c:if>
										<c:if test="${result.addState  eq 'W'}" >
											<a href="#!" class="btn-line-orange"  style="float: none;">미제출</a>
										</c:if>										
										<c:if test="${result.addState  eq 'I'}" >
											<a href="#!" class="btn-line-blue"  style="float: none;">조회</a>
										</c:if>
										<c:if test="${result.addState  eq 'X'}" >
											<a href="#!" class="btn-line-orange"  style="float: none;">반려</a>
										</c:if>
										<c:if test="${result.addState  eq 'C'}" >
											<a href="#!" class="btn-line-gray"  style="float: none;">확정</a>
										</c:if>										
									</td>									
								</tr>
							</c:forEach>
							</tbody>
						</table>
 
						
						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_formSave('P');" class="gray-2">인쇄</a>											

						</div><!-- E : btn-area -->
	</div>
<script type="text/javascript">
<!--

$(document).ready(function() {

	
});
/*  수정,삭제 */
function fn_formSave(type){ 
	  
	if(type=="P"){
		window.print();
	}
 
 
}  
//--> 
</script>		