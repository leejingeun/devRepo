<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

						<h2>학습근로자관리</h2>
<script type="text/javascript">
<!--

$(document).ready(function() {
	 
	<c:if test="${traningStatusVO.searchType eq 'C2' or traningStatusVO.searchType eq 'O2' }">
		showTabbtn2();
	</c:if>
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
	 
	var reqUrl = "/lu/traningstatus/listTraningstatusPrt.do";
	$("#mode").val("0");
	$("#frmTraningstatus").attr("target", "_self");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}
function fn_print(){
	 
	var reqUrl = "/lu/traningstatus/listTraningstatusPrt.do";
	$("#mode").val("1");
	$("#frmTraningstatus").attr("target", "_blank");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}

function fn_searchDetail(){
	 
	var reqUrl = "/lu/traningstatus/listTraningstatusPrt.do";
 
	$("#frmTraningstatusDetail").attr("target", "_self");
	$("#frmTraningstatusDetail").attr("action", reqUrl);
	$("#frmTraningstatusDetail").submit();

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
function fn_checkboxAllDetail(){
	 
	$('#frmTraningstatusDetail input:checkbox[name="memSeq"]').each(function(){
		if($(this).is(":checked") == true){
			$('.memSeq').prop('checked',false);
		}else{
			$('.memSeq').prop('checked',true);			
		}
	});
}


function fn_offPopup(){
	 
	var reqUrl = "/lu/traningstatus/popupTraningstatusPrt.do";
 
	$("#searchCondition").val("offline");
	$("#frmTraningstatus").attr("target", "_blank");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}




//--> 
</script>
						<dl id="tab-type">
							<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">전체 조회</a></dt>
							<dd id="tab-content-11" style="display:block;">
								<h4 class="mt-020 mb-010"><span>${currProcVO.subjectName } </span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>


 

								<div class="progress-area mb-030">
									<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
									<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
									<p>실제 진도율 <c:if test="${ getProgress.realProgressRate > 0}" >(<fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" />)</c:if></p>
									<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
								</div><!-- E : 진행율 -->
								

<form name="frmTraningstatus" id="frmTraningstatus" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${traningStatusVO.yyyy }"/>
<input type="hidden" id="term" name="term" value="${traningStatusVO.term}"/>
<input type="hidden" id="subjectCode" name="subjectCode" value="${traningStatusVO.subjectCode}"/>
<input type="hidden" id="mode" name="mode" value="0"/>
<input type="hidden" id="searchCondition" name="searchCondition" value=""/>

								<div class="search-box-1 mt-020 mb-020">
									<select id="searchType"  name="searchType" >
										<option value="C1" <c:if test="${traningStatusVO.searchType eq 'C1' }" >selected</c:if> > 강의실진도</option> 
										<!--option value="">강의실결석</option>
										<option value="">온라인결석</option-->
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
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:120px" />
										<col style="width:120px" />
										<col style="width:*" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:80px" />
									</colgroup>
									<thead>
										<tr>
											<th rowspan="2"><input type="checkbox" name=""   onclick="javascript:fn_checkboxAll();"   class="choice" /></th>
											<th rowspan="2">번호</th>
											<th rowspan="2">분반</th>
											<th rowspan="2">기업명</th>
											<th rowspan="2">학번</th>
											<th rowspan="2">이름</th>
											<th rowspan="2">학년</th>
											<th colspan="4">학습현황</th>
										</tr>
										<tr>
											<th class="border-left">출석</th>
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
											
											<td>${resultlists.classId }</td>
											<td>${resultlists.companyName }</td>
											
											<td>${resultlists.memId }</td>
											<td class="left"><img src="/commbiz/photo/getAunuriUserImage.do?memId=${resultlists.memId }" width="65" height="55" alt="${resultlists.memName }"  />&nbsp;&nbsp;&nbsp;${resultlists.memName }</td>
											<td>${resultlists.level}</td>
											
											<td>${resultlists.attend }</td>
											<td>${resultlists.lateness }</td>
 											<td>${resultlists.totalTime - resultlists.attend - resultlists.lateness}</td>
											<td>
												<div class="progress-area2">
													<p>${resultlists.realProgressRate} %</p>
													<progress value="${resultlists.realProgressRate }" max="100"></progress>
												</div>
											</td>
 
										</tr>
											 
									</c:forEach>
								<c:if test="${empty resultlist}">
									<tr>
										<td colspan="11">등록된 데이터가 없습니다.</td>
									</tr>							
								</c:if>
 
									</tbody>
								</table><!-- E : 전체 학습근로자관리-->
</form>
								<div class="btn-area align-right mt-010"> 
									<a href="#!"  onclick="javascript:fn_offPopup();" class="gray-1 float-left">출석부</a>
									<a href="javascript:fn_print();" class="orange">훈련현황 출력</a>
									<!-- 임시주석 -->
									<!--
									<a href="javascript:alert('준비중입니다.');" class="gray-2">SMS 발송</a>
									<a href="javascript:alert('준비중입니다.');"  class="gray-2">E-MAIL 발송</a>
									 -->
								</div><!-- E : btn-area -->
							</dd>





							<dt class="tab-name-12"><a href="javascript:showTabbtn2();">주차별 조회</a></dt>
							<dd id="tab-content-12">
								<h4 class="mt-020 mb-010"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>
<form name="frmTraningstatusDetail" id="frmTraningstatusDetail" method="post">		
								<div class="search-box-1 mb-020">
									<select id="weekCnt" name="weekCnt"  onchange="javascript:fn_searchDetail();">
										<c:forEach var="result" items="${onlineTraningSchVO}" varStatus="status">
										<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq traningStatusVO.weekCnt }">selected</c:if>>${result.weekCnt}주차</option>
										</c:forEach>
									</select>									
									
									<select id="searchType"  name="searchType" >
										<option value="C2" <c:if test="${traningStatusVO.searchType eq 'C2' }" >selected</c:if> > 훈련시간</option> 
									</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="text" name="searchStartRateDetail" style= "width:100px;" value="${traningStatusVO.searchStartRateDetail}" />이상 ~ 
									<input type="text" name="searchEndRateDetail" style= "width:100px;"  value="${traningStatusVO.searchEndRateDetail}" />이하
									
									 
									<input type="text" name="searchMemNameDetail" value="${traningStatusVO.searchMemNameDetail }" />
									<a href="#!" onclick="javascript:fn_searchDetail();">검색</a>
								</div><!-- E : search-box-1 -->



								<table class="type-2">
									<colgroup>
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:120px" />
										<col style="width:120px" />
										<col style="width:*" />
										<col style="width:140px" />
										<col style="width:80px" />
										<col style="width:80px" />
									</colgroup>
									<thead>
										<tr>
											<th><input type="checkbox" name="" value="" onclick="javascript:fn_checkboxAllDetail();" class="choice" /></th>
											<th>분반</th>
											<th>기업명</th>
											<th>학번</th>
											<th>이름</th>
											<th>훈련시간 (실제/계획)</th>
											<th>학습활동서</th>
										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="resultBottomlist" items="${resultBottomlist}" varStatus="status">
										<tr>
											<td><input type="checkbox" name="memSeq"   class="memSeq" /></td>
											<td>${resultBottomlist.classId }</td>
											<td>${resultBottomlist.companyName }</td>
																						
											<td>${resultBottomlist.memId }</td>
											<td class="left">${resultBottomlist.memName}</td>
											<td>${resultBottomlist.studyTime}/${resultBottomlist.studyPlanTime}</td>
											<td>
												<c:if test="${!empty resultBottomlist.content}">
												<a href="/lu/activity/listActivityPrt.do?weekCnt=${traningStatusVO.weekCnt}&memId=${resultBottomlist.memId}&classId=${resultBottomlist.classId}" class="btn-line-gray">작성</a>
												</c:if>
												<c:if test="${empty resultBottomlist.content}">
												<a href="/lu/activity/listActivityPrt.do?weekCnt=${traningStatusVO.weekCnt}&memId=${resultBottomlist.memId}&classId=${resultBottomlist.classId}" class="btn-line-orange">미작성</a>
												</c:if>
												
											</td>
										</tr>
									</c:forEach>
																		
								<c:if test="${empty resultBottomlist}">
									<tr>
										<td colspan="7">등록된 데이터가 없습니다.</td>
									</tr>							
								</c:if>
									</tbody>
								</table><!-- E : 주차별 학습근로자관리-->

</form>

								<div class="btn-area align-right mt-010">
								<!-- 임시주석 -->
									<!--
									<a href="javascript:alert('준비중입니다.');" class="gray-2">SMS 발송</a>
									<a href="javascript:alert('준비중입니다.');" class="gray-2">E-MAIL 발송</a>
									-->
								</div><!-- E : btn-area -->
							</dd>
						</dl>

