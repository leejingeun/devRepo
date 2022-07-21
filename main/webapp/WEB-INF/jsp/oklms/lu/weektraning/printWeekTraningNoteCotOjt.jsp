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
<c:set var="allReview" value="" />
<c:set var="cotName" value="" />
<c:set var="prtName" value="" />
  <div  class="mt-050 mb-020" style="margin-left:60px;margin-right:50px;" >
						
						<div class="btn-area align-right mt-010 not_print">
							<a href="#!" onclick="javascript:fn_formSave('P');" class="gray-2">인쇄</a>											
						</div><!-- E : btn-area -->
						
			
						<table  class="mt-020" style="width:100%">
							<tr>
								<td colspan="2">
									<h2>학습근로자 교육훈련일지<c:if test="${traningNoteVO.addyn eq 'Y' }">(보강)</c:if></h2>
								</td>
							</tr>
							<tr>
								<td style="width:60%" >
									<div>No. ${traningNoteVO.yyyy}- -${traningNoteVO.weekCnt} </div>
									<div>주간 일학습병행제 학습근로자 교육일지</div> 									
								</td>
								<td style="width:40%" >
									<div>
									<c:forEach var="detailTraningNotePrd" items="${detailTraningNotePrd}" varStatus="status">
										<c:if test="${status.first}">
										
										<li>(협약)기업명&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;${detailTraningNotePrd.companyName} </li>
										<li>직무분야&nbsp;&nbsp;: - </li>																														
										<li>기&nbsp;&nbsp;간&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; ${detailTraningNotePrd.traningSt}&nbsp; ~ &nbsp;${detailTraningNotePrd.traningEd}</li>
										<c:set var="cotName" value="${detailTraningNotePrd.cotName}" />
										<c:set var="prtName" value="${detailTraningNotePrd.prtName}" />
										</c:if>					
									</c:forEach>
									</div>
								</td>
							</tr>
							
						</table>
						
						<table class="type-2 mt-020">
							<colgroup>
								<col style="width:*" />
								<col style="width:*" />								
								<col style="width:*" />
		
							</colgroup>
							<thead>
								<tr>
									<th>기업명</th>
									<th>학습근로자명</th>																	
									<th>서명</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach var="resultList" items="${resultList}" varStatus="status">
								<tr>
									<td>${resultList.companyName}</td>
									<td>${resultList.memName}</td>
									<td></td>																	
								</tr>
							</c:forEach>
						 							 
							</tbody>
						</table>

 
</div>