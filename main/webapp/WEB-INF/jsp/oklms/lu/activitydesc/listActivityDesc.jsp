<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

	<h2>활동내역서</h2>
		<script type="text/javascript" src="/js/oklms/jquery-latest.js"></script>
		<script type="text/javascript" src="/js/oklms/jquery-common.js"></script>
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
var showCompanypop = function(){
	$(".company-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideCompanypop = function(){ 
	var reqUrl = "/lu/activitydesc/listActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
};
function fn_search(){ 
	var reqUrl = "/lu/activitydesc/listActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
}
function checkboxClick(companyIdstd,traningProcessIdstd){	 
   	$("#companyId").val( companyIdstd );
   	$("#traningProcessId").val( traningProcessIdstd );
}
//출력
function fn_print(){

	var reqUrl = "/lu/activitydesc/updateActivityDescPrint.do";

	var insurance = $('input:checkbox[name="activityIdArr"]').is(":checked");
	if(!insurance){
		alert("출력 하실 활동내역을 선택해주세요.");
		 $('input:checkbox[name="activityIdArr"]').focus();
		 return;
	}
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}
function fn_insertNewListActivity(activityType,mm){

	if($("#companyId").val() == "" ){
		showCompanypop();
		return;
	} 
	
	var reqUrl = "/lu/activitydesc/goInsertActivityDesc.do";
	$("#activityType").val(activityType);
	$("#mm").val(mm);	
	$("#activityId").val("");
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();
}
function fn_addNewListActivity( activityId, activityType,mm ){
	
	if($("#companyId").val() == "" ){
		showCompanypop();
		return;
	} 
	$("#mm").val(mm);	
	var reqUrl = "/lu/activitydesc/goInsertActivityDesc.do";
	$("#activityId").val(activityId);
	$("#activityType").val(activityType);
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();
}
function fn_getActivityDesc(activityId ,mm){
	$("#mm").val(mm);	
	$("#activityType").val("");
	var reqUrl = "/lu/activitydesc/getActivityDesc.do";
	$("#activityId").val(activityId); 
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}
function fn_getActivityDescPrint(){ 
	
	if(!$(":input:radio[name=activityIdArr]:checked").val()){
		alert("작성되어있는 출력 대상을 선택하세요.");
 		return;
 	}
	var reqUrl = "/lu/activitydesc/getActivityDesc.do";
	$("#activityId").val($(":input:radio[name=activityIdArr]:checked").val()); 
	$("#subjectCode").val("print");
	$("#frmActivity").attr("target","_blank");
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}
 
//-->
</script>
<form method="post" name="frmActivity" id="frmActivity">
<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
<input type="hidden" id="activityId" name="activityId" value="${activityVO.activityId}" />
<input type="hidden" id="activityType" name="activityType" value="${activityVO.activityType}"/>
<input type="hidden" id="subjectCode" name="subjectCode"  />
<input type="hidden" id="mm" name="mm"  />

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
	 
			<a href="javascript:showCompanypop();" class="orange">훈련과정 선택</a>
			<!--a href="javascript:fn_insertNewListActivity();" class="yellow ">신규 작성</a-->
		</div>
	</div>
	<div class="search-box-1 mt-030 mb-010">
	
			<select id="yyyy" name="yyyy"  > 
					<c:forEach var="i" begin="0" end="5" step="1">
				      <option value="${nowYear-i}" <c:if test="${activityVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
				    </c:forEach>								
			</select> 

			<!--select name="mm" id="mm">
					<option value="">전체</option>
					<c:forEach var="i" begin="1" end="12" step="1">
				      <option value="${i}" <c:if test="${activityVO.mm eq i }" > selected="selected"  </c:if>  >${i}월</option>
				    </c:forEach>
			</select -->
		<a href="javascript:fn_search();">검색</a>
	</div><!-- E : search-box-1 -->

	<table class="type-2">
		<colgroup>
			<col style="width:40px" />
			<col style="width:*" />
			<col style="width:150px" />
			<col style="width:150px" />
			<col style="width:150px" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>월</th>
				<th>기업현장교사</th>
				<th>HRD 담당자</th>
				<th>제출확인</th>
			</tr>
		</thead>
		<tbody>
		
	 	<c:forEach var="result" items="${resultList}" varStatus="status" >
		<tr>
			<td><input type="radio" name="activityIdArr" value="${result.activityId}" class="choice" /></td>
			<td>${result.yyyy }년 ${result.mm }월</td>
			<td> 
				<c:if test="${result.ccmCot > 0}">
				<a href="javascript:fn_addNewListActivity('${result.activityId}','COT' ,'${result.mm }');" class="btn-full-gray">조회</a>
				</c:if>
				<c:if test="${result.ccmCot == 0}">
					<c:if test="${result.ccmCnt > 0}">
					<a href="javascript:fn_addNewListActivity('${result.activityId}','COT' ,'${result.mm }');"> 미작성</a>					
					</c:if>
					<c:if test="${result.ccmCnt == 0}">
					<a href="javascript:fn_insertNewListActivity('COT' ,'${result.mm }');"> 미작성</a>					
					</c:if>
				</c:if> 
			</td>
			<td>

				<c:if test="${result.ccmCnt > 0}">
				<a href="javascript:fn_addNewListActivity('${result.activityId}','HRD' ,'${result.mm }');" class="btn-full-gray">조회</a>
				</c:if>
				<c:if test="${result.ccmCnt == 0}">
					<c:if test="${result.ccmCot > 0}">
					<a href="javascript:fn_addNewListActivity('${result.activityId}','HRD' ,'${result.mm }');" > 미작성</a>
					</c:if>
					<c:if test="${result.ccmCot == 0}">
					<a href="javascript:fn_insertNewListActivity('HRD' ,'${result.mm }');"> 미작성</a>					
					</c:if>					
				</c:if>
				
			</td>
			<td>
			
			<c:if test="${!empty result.activityId}">
				<c:if test="${empty result.printDate}">
					<a href="javascript:fn_getActivityDesc('${result.activityId}','${result.mm }')" class="btn-full-oraange">확인</a>
				</c:if>
				<c:if test="${!empty result.printDate}">
					<a href="javascript:fn_getActivityDesc('${result.activityId}','${result.mm }')">${result.printDate}</a>
				</c:if>
			</c:if>
			</td>
		</tr>
		</c:forEach>
		
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="5"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>

		</tbody>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="javascript:fn_getActivityDescPrint();" class="gray-2 ">출력</a>
	</div>
 
</form>

			<ul id="student-popup">
				<li class="company-area" id="interviewCompanyList">
				</li>
				<li class="popup-bg"></li>
			</ul><!-- E : student-popup -->