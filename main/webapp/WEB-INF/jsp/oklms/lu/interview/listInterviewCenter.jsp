<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

						<h2>면담일지 조회</h2>

						<div class="graup-area">
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

<script type="text/javascript">
<!--
/*************************************************************
학습창 슬라이딩/목차 관련 스크립트
**************************************************************/

var showLearningpop = function(){
	$(".learning-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideLearningpop = function(){
	var reqUrl = "/lu/interview/listInterviewCenter.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();	
	$(".learning-area,.popup-bg").removeClass("open"); 
};

$(function() {
	$('.list-zone dt').click(function(){
		$(this).parent().siblings().find('dd').slideUp();
		$(this).siblings('dd').slideToggle();
	});

	$('.list-zone dd a').click(function(){
		$(this).addClass('current');
		$(this).siblings().removeClass('current');
	});
});


var showCompanypop = function(){
	$(".company-area,.popup-bg").addClass("open"); 
	window.location.hash = "#open"; 
};
var hideCompanypop = function(){ 
	var reqUrl = "/lu/interview/listInterviewCenter.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();	
	//$(".company-area,.popup-bg").removeClass("open"); 
};


$(document).ready(function() {
	$.ajax({
		  type: "POST",
		  url: "/lu/interview/ajaxInterviewCompany.do",
		  data: { "searchCompanyName": "","searchHrdTraningName":"","pageIndex":"1"},
		  success:function( html ) {
			$( "#interviewCompanyList" ).html( html );			
		  }
	});
});

function fn_search(param1){
	$("#pageIndex").val( param1 );
	var reqUrl = "/lu/interview/listInterviewCenter.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();				
}

function checkboxClick(companyIdstd,traningProcessIdstd){	 
   	$("#companyId").val( companyIdstd );
   	$("#traningProcessId").val( traningProcessIdstd );
}

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

function fn_formview(interviewNoteDateMm,sendYn){
	$("#sendYn").val( sendYn );
	$("#interviewNoteDateMm").val( interviewNoteDateMm );
	var reqUrl = "/lu/interview/getInterviewCenter.do";
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").attr("target","_self");
	$("#frmInterview").submit();	
	
}

function fn_printInterview(){
	var interviewNoteDateMmRadio = $("input:radio[name=interviewNoteDateMmRadio]:checked").val(); 
	if(interviewNoteDateMmRadio!=null){
		$("#interviewNoteDateMm").val(interviewNoteDateMmRadio );
		var reqUrl = "/lu/interview/printInterviewCenter.do";
		$("#frmInterview").attr("action", reqUrl);
		$("#frmInterview").attr("target","_blank");
		$("#frmInterview").submit();		
	}else{
		alert("출력할 대상을 선택하세요.");
		return;
		
	}
		
}

//-->
</script>
<form:form commandName="frmInterview" name="frmInterview" method="post"  >
		<input type="hidden" name="companyId" id="companyId" value="${interviewVO.companyId }"  />
		<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${interviewVO.traningProcessId }" />
		<input type="hidden" name="interviewNoteDateMmParam" id="interviewNoteDateMm"  />
		<input type="hidden" name="sendYn" id="sendYn"  />	
		
		
 

						<div class="search-box-1 mt-020 ">
							<select id="yyyy" name="yyyy" onchange="javascript:fn_search();" > 
									<c:forEach var="i" begin="0" end="5" step="1">
								      <option value="${nowYear-i}" <c:if test="${interviewVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
								    </c:forEach>								
							</select>
							<select id="mm" name="mm" onchange="javascript:fn_search();" > 
									<option value="" >-선택-</option>
								    <option value="01" <c:if test="${interviewVO.mm eq '01' }" > selected="selected"  </c:if>>1월</option>
									<option value="02" <c:if test="${interviewVO.mm eq '02' }" > selected="selected"  </c:if>>2월</option>
								    <option value="03" <c:if test="${interviewVO.mm eq '03' }" > selected="selected"  </c:if>>3월</option>
									<option value="04" <c:if test="${interviewVO.mm eq '04' }" > selected="selected"  </c:if>>4월</option>
								    <option value="05" <c:if test="${interviewVO.mm eq '05' }" > selected="selected"  </c:if>>5월</option>
									<option value="06" <c:if test="${interviewVO.mm eq '06' }" > selected="selected"  </c:if>>6월</option>
								    <option value="07" <c:if test="${interviewVO.mm eq '07' }" > selected="selected"  </c:if>>7월</option>
									<option value="08" <c:if test="${interviewVO.mm eq '08' }" > selected="selected"  </c:if>>8월</option>
								    <option value="09" <c:if test="${interviewVO.mm eq '09' }" > selected="selected"  </c:if>>9월</option>
									<option value="10" <c:if test="${interviewVO.mm eq '10' }" > selected="selected"  </c:if>>10월</option>
									<option value="11" <c:if test="${interviewVO.mm eq '11' }" > selected="selected"  </c:if>>11월</option>
									<option value="12" <c:if test="${interviewVO.mm eq '12' }" > selected="selected"  </c:if>>12월</option>
							</select>
														
							<a href="#!" onclick="javascript:fn_search();">검색</a>
						</div><!-- E : search-box-1 -->
						
</form:form> 
						<table class="type-2 mt-010">
							<colgroup>
								<col style="width:50px" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:*" />
							</colgroup>
							<thead>
								<tr>
									<th>선택</th>
									<th>월</th>
									<th>면담자</th>
									<th>작성현황</th>
									<th>제출확인</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="result" items="${resultlist}" varStatus="status">
								<tr>
									<td><input type="radio" name="interviewNoteDateMmRadio" value="${result.interviewNoteDateMmParam}" class="choice" /></td>
									<td><a href="#!" onclick="javascript:fn_formview('${result.interviewNoteDateMmParam}','N');" class="text">${result.interviewNoteDateMmParam} 월</a></td>
									<td>${result.interviewMemName}</td>
									<td><a href="#!"  onclick="javascript:fn_formview('${result.interviewNoteDateMmParam}','N');"  class="btn-line-gray">조회</a></td>
									<td>
										<c:if test="${result.sendYn ne 'Y'}">
											<a href="#!" onclick="javascript:fn_formview('${result.interviewNoteDateMmParam}','Y');"  class="btn-line-gray">확인</a>
										</c:if>
										<c:if test="${result.sendYn eq 'Y'}">
											${result.sendDate}
										</c:if>										
									</td>
								</tr>							
							</c:forEach>
							<c:if test="${empty resultlist}">
								<tr>
									<td colspan="5">등록된 면담일지가 없습니다.</td>
								</tr>							
							</c:if>
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<!--p>
								<input type="checkbox" name="" value="" class="choice" /> 기업현장교사&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="" value="" class="choice" /> HRD담당자
							</p-->
							<!-- 임시주석 -->
							<!--<a href="#!" class="yellow">SMS 발송</a>
							<a href="#!" onclick="javascript:fn_excelDown();" class="orange">엑셀 저장</a> -->
							<a href="#!" onclick="javascript:fn_printInterview();" class="gray-1"> 선택출력</a>
						</div>

 			<ul id="student-popup">
				<li class="company-area" id="interviewCompanyList">
					 
				</li>
				<li class="popup-bg"></li>
			</ul><!-- E : student-popup -->
 