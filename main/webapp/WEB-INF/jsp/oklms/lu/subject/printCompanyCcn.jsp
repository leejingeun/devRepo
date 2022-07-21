<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>

			
 <div id="pop-wrapper" class="min-w650" >
			<h2>참여기업현황</h2>
			<!-- E : search-list-1 (검색조건 영역) -->

					<table class="type-2">
						<colgroup>
							<col style="width:*" />
							<col style="width:*" />
							<col style="width:70px" />
							<col style="width:70px" />
							<col style="width:*" />
							<col style="width:80px" />
							<col style="width:80px" />
							<col style="width:50px" />
							<col style="width:50px" />
							<col style="width:50px" />
							<col style="width:*" />
						</colgroup>
						<thead>
							<tr>
								<th>기업명</th>
								<th>소재지</th>
								<th>기업<br />현장교사</th>
								<th>HRD<br />담당자</th>
								<th>과정명 </th>
								<th>시작일자</th>
								<th>종료일자</th>
								<th>시작<br />인원</th>
								<th>재직중</th>
								<th>중도<br />탈락</th>
								<th>학과명</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td>${result.companyName}</td>
									<td>${result.address}</td>
									<td>${result.tutNames}</td>
									<td>${result.hrdNames}</td>
									<td>${result.hrdTraningName}</td>
									<td>${result.hrdStartDate}</td>
									<td>${result.hrdEndDate}</td>
									<td>${result.stuCnt}</td>
									<td>${result.stuCnt-result.outCnt}</td>
									<td>${result.outCnt}</td>
									<td>${result.deptName}</td>
								</tr>
							</c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td colspan="12"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>	
						</tbody>
					</table><!-- E : type-2 -->
					<div class="btn-area align-right mt-010">
						<a href="#!" onclick="javascript:fn_print();" class="gray-2">인쇄</a>	
					</div><!-- E : btn-area -->

	
<script type="text/javascript">
<!--

/*  수정,삭제 */
function fn_print(){ 
	window.print();
}  
//--> 
</script>		
	
	
					

	