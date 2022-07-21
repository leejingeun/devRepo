<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

						<h2>학습근로자 훈련현황</h2>
<script type="text/javascript">
<!--

$(document).ready(function() {
	 
 
});

function imgError()
{
	event.srcElement.src = "/images/oklms/nonimg.png";
}

function imgonerror(){
	var all_img=document.getElementsByTagName("img");
	if(all_img.length > 0)
	{
		for(var i=0;i<all_img.length;i++)
		{
		  all_img[i].onerror=imgError;
		}
	} 
}

function fn_search(){
	 
	var reqUrl = "/lu/traningstatus/listTraningstatusCdp.do";
	$("#mode").val("0");
	$("#frmTraningstatus").attr("target", "_self");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}
function fn_print(){
	 
	var reqUrl = "/lu/traningstatus/listTraningstatusCdp.do";
	$("#mode").val("1");
	$("#frmTraningstatus").attr("target", "_blank");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}
 
function fn_checkboxAll(){
	 
	$('#frmTraningstatus input:checkbox[name="lessonId"]').each(function(){
		if($(this).is(":checked") == true){
			$('.lessonId').prop('checked',false);
		}else{
			$('.lessonId').prop('checked',true);			
		}
	});
} 

 
function fn_offonlinePopup(){
	$("#mode").val("0"); 
	var reqUrl = "/lu/traningstatus/popupTraningstatusCdp.do";
	$("#searchCondition").val("online");	
	$("#frmTraningstatus").attr("target", "_blank");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}



//--> 
</script>
 

							<h4 class="mt-020 mb-010"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

								<div class="progress-area mb-030">
									<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
									<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
									<p>실제 진도율 <c:if test="${ getProgress.realProgressRate > 0}" >(<fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" />)</c:if></p>
									<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
								</div><!-- E : 진행율 -->
								
									<c:if test="${!empty onlineTraningVO}">
									<table  class="type-3">
										<tr>
											<th>ON-LINE 출석정책</th>
											<th> 출석 </th>
											<td>${onlineTraningVO.attendProgress}이상</td>
											<th>지각</th>
											<td> ${onlineTraningVO.attendProgress} 미만&nbsp; ${onlineTraningVO.cutProgress}이상 </td>
											<th> 결석 </th>
											<td>${onlineTraningVO.cutProgress}이상</td>
										</tr>
									</table>		
									</c:if>
									
<form name="frmTraningstatus" id="frmTraningstatus" method="post">


<input type="hidden" id="yyyy" name="yyyy" value="${traningStatusVO.yyyy }"/>
<input type="hidden" id="term" name="term" value="${traningStatusVO.term}"/>
<input type="hidden" id="classId" name="classId" value="${traningStatusVO.classId}"/>
<input type="hidden" id="subjectCode" name="subjectCode" value="${traningStatusVO.subjectCode}"/>

<input type="hidden" id="mode" name="mode" value="0"/>

<input type="hidden" id="searchCondition" name="searchCondition" value=""/>

								<div class="search-box-1 mt-020 mb-020">
									<select id="searchType"  name="searchType" >
										<option value="O1" <c:if test="${traningStatusVO.searchType eq 'O1' }" >selected</c:if> > 온라인진도</option>
									</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<select name="searchStartRate"id="searchStartRate" >
										<option value="0" <c:if test="${traningStatusVO.searchStartRate eq '0' }" >selected</c:if>>-</option>									
										<option value="100" <c:if test="${traningStatusVO.searchStartRate eq '100' }" >selected</c:if> >100%</option>
										<option value="90" <c:if test="${traningStatusVO.searchStartRate eq '90' }" >selected</c:if>>90%</option>
										<option value="80" <c:if test="${traningStatusVO.searchStartRate eq '80' }" >selected</c:if>>80%</option>
										<option value="70" <c:if test="${traningStatusVO.searchStartRate eq '70' }" >selected</c:if>>70%</option>
										<option value="60" <c:if test="${traningStatusVO.searchStartRate eq '60' }" >selected</c:if>>60%</option>
										<option value="50" <c:if test="${traningStatusVO.searchStartRate eq '50' }" >selected</c:if>>50%</option>
										<option value="40" <c:if test="${traningStatusVO.searchStartRate eq '40' }" >selected</c:if>>40%</option>
										<option value="30" <c:if test="${traningStatusVO.searchStartRate eq '30' }" >selected</c:if>>30%</option>
										<option value="20" <c:if test="${traningStatusVO.searchStartRate eq '20' }" >selected</c:if>>20%</option>
										<option value="10" <c:if test="${traningStatusVO.searchStartRate eq '10' }" >selected</c:if>>10%</option>
									</select> 이상 ~ 
									<select  name="searchEndRate"id="searchEndRate">
										<option value="100" <c:if test="${traningStatusVO.searchEndRate eq '100' }" >selected</c:if>>100%</option>
										<option value="90" <c:if test="${traningStatusVO.searchEndRate eq '90' }" >selected</c:if>>90%</option>
										<option value="80" <c:if test="${traningStatusVO.searchEndRate eq '80' }" >selected</c:if>>80%</option>
										<option value="70" <c:if test="${traningStatusVO.searchEndRate eq '70' }" >selected</c:if>>70%</option>
										<option value="60" <c:if test="${traningStatusVO.searchEndRate eq '60' }" >selected</c:if>>60%</option>
										<option value="50" <c:if test="${traningStatusVO.searchEndRate eq '50' }" >selected</c:if>>50%</option>
										<option value="40" <c:if test="${traningStatusVO.searchEndRate eq '40' }" >selected</c:if>>40%</option>
										<option value="30" <c:if test="${traningStatusVO.searchEndRate eq '30' }" >selected</c:if>>30%</option>
										<option value="20" <c:if test="${traningStatusVO.searchEndRate eq '20' }" >selected</c:if>>20%</option>
										<option value="10" <c:if test="${traningStatusVO.searchEndRate eq '10' }" >selected</c:if>>10%</option>
									</select> 이하
									<input type="text" name="searchMemName" value="${traningStatusVO.searchMemName }" />
									<a href="#!" onclick="javascript:fn_search();">검색</a>
								</div><!-- E : search-box-1 -->


								<table class="type-2">
									<colgroup>
										<col style="width:40px" />
										<col style="width:60px" />
										<col style="width:120px" />
										<col style="width:*" />
										<col style="width:50px" />
										<col style="width:50px" />
										<col style="width:50px" />
										<col style="width:50px" />
										<col style="width:100px" />
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" name="lessonId"   onclick="javascript:fn_checkboxAll();" class="choice" /></th>
											<th>번호</th>
											<th>학번</th>
											<th>이름</th>
											<th>학년</th>
											<th>출석</th>
											<th>지각</th>
											<th>결석</th>
											<th>진도율</th>
										</tr>
									 
									</thead>
									<tbody>
									<c:forEach var="resultlists" items="${resultlist}" varStatus="status">
										<tr>
											<td><input type="checkbox" id="lessonId" name="lessonId" value="" class="lessonId" /></td>
											<td>${status.count}</td>
											<td>${resultlists.memId }</td>
											<td class="left"><img src="/commbiz/photo/getAunuriUserImage.do?memId=${resultlists.memId }" width="65" height="55" alt="${resultlists.memName }"  /> &nbsp;&nbsp;&nbsp;${resultlists.memName }</td>
											<td>${resultlists.level}</td>

											<td>${resultlists.onAttend }</td>
											<td>${resultlists.onLateness }</td>
 											<td>
 											<c:if test="${!empty onlineTraningVO}">
 											${resultlists.onTotalTime - resultlists.onAttend - resultlists.onLateness}
 											</c:if>
 											<c:if test="${empty onlineTraningVO}">
 											0
 											</c:if>
 											</td>
											<td>
												<div class="progress-area2">
													<p>${resultlists.onRealProgressRate}%</p>
													<progress value="${resultlists.onRealProgressRate}" max="100"></progress>
												</div>
											</td>
										</tr>
									</c:forEach> 
								<c:if test="${empty resultlist}">
									<tr>
										<td colspan="9">등록된 데이터가 없습니다.</td>
									</tr>							
								</c:if>
								
									</tbody>
								</table><!-- E : 전체 학습근로자관리-->
</form>
								<div class="btn-area align-right mt-010"> 
									<a href="#!" onclick="javascript:fn_offonlinePopup();" class="gray-1 float-left">온라인 출석부</a>									
									<a href="javascript:fn_print();" class="orange">훈련현황 출력</a>
									<!-- 임시주석 -->
									<!--
									<a href="javascript:alert('준비중입니다.');" class="gray-2">SMS 발송</a>
									<a href="javascript:alert('준비중입니다.');"  class="gray-2">E-MAIL 발송</a>
									 -->
								</div><!-- E : btn-area -->
			  
 