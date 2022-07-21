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

						<h2>재직증빙서류</h2>

<script type="text/javascript">
<!--
$(document).ready(function() {

	com.datepickerDateFormat('startTime');
	com.datepickerDateFormat('endTime');
	<c:if test="${workCertVO.seach eq 'detail'}">showTabbtn2();</c:if>

}); 

function fn_search(){
	var reqUrl = "/lu/workcert/listWorkCert.do";
	$("#frmWorkCert").attr("target", "_self");
	$("#frmWorkCert").attr("action", reqUrl);
	$("#frmWorkCert").submit();
}

function fn_searchdetail(){
	 
	if($("#searchCompanyName").val().trim() == '기업명'){
		$("#searchCompanyName").val("");
	}
	if($("#searchValue").val().trim() == '학생명,학번 검색'){
		$("#searchValue").val("");
	}
	var reqUrl = "/lu/workcert/listWorkCert.do";
	$("#frmWorkCertListDetail").attr("target", "_self");
	$("#frmWorkCertListDetail").attr("action", reqUrl);
	$("#frmWorkCertListDetail").submit();

}
function fn_save(){
	if($( "#startTime").val() == ""){
 		alert("기간을 입력해주세요.");
 		return;		
	}
	if($( "#endTime").val() == ""){
 		alert("기간을 입력해주세요.");
 		return;		
	}
 	if($( "#relativeRegulation").val().trim() == ""){
 		alert("관련규정을 입력해주세요.");
 		return;
 	}

 	if($('#insurance').is(':checked')){	
 		$( "#insuranceYn").val("Y");
 	}else{
 		$( "#insuranceYn").val("N");
 	}
 	if($('#receipt').is(':checked')){	
 		$( "#receiptYn").val("Y");
 	}else{
 		$( "#receiptYn").val("N");
 	}

	var reqUrl = "/lu/workcert/insertWorkCertPeriod.do";
	$("#frmWorkCert").attr("target", "_self");
	$("#frmWorkCert").attr("action", reqUrl);
	$("#frmWorkCert").submit();

}

function fn_delete(){
  
}

function fn_update(){ 
 	if($(':radio[name="periodId"]:checked').val() == ""){
 		alert("수정할 대상을 선택해주세요.");
 		return;
 	}	
	var reqUrl = "/lu/workcert/listWorkCert.do";
	$("#frmWorkCertList").attr("target", "_self");
	$("#frmWorkCertList").attr("action", reqUrl);
	$("#frmWorkCertList").submit();
}
function fn_popup(){
	
	popOpenWindow("", "popSearch", 650, 710);
	var reqUrl = "/lu/workcert/popupWorkCert.do";
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").attr("target", "popSearch");
	$("#frmWorkCertListpopup").submit();
}
function fn_popupdeatil(){
	
	popOpenWindow("", "popSearch2", 850, 710);
	var reqUrl = "/lu/workcert/popupWorkCertDetail.do";
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").attr("target", "popSearch2");
	$("#frmWorkCertListpopup").submit();
}
function fn_checkboxAll(){
 
		if($('#checkMember').is(":checked") == true){
			$(".checkMember").attr('checked',true);
		}else{
			$(".checkMember").attr('checked',false);
		} 
	
}

function fn_popupdeatil(){
	
	popOpenWindow("", "popSearch2", 850, 710);
	var reqUrl = "/lu/workcert/popupWorkCertDetail.do";
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").attr("target", "popSearch2");
	$("#frmWorkCertListpopup").submit();
}

function fn_popupdeatil(){
	
	popOpenWindow("", "popSearch2", 850, 710);
	var reqUrl = "/lu/workcert/popupWorkCertDetail.do";
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").attr("target", "popSearch2");
	$("#frmWorkCertListpopup").submit();
}

// 승인,반려처리
function fn_updateDetail(type){

	var memIdArr = $('input:checkbox[name="memIdArr"]').is(":checked");
	$( "#state").val(type);
	
	if(!memIdArr){
		if(type=="01"){
			alert("승인할 학생을 선택하세요.");	
		}else if(type=="02"){
			alert("반려할 학생을 선택하세요.");
		}else{
			alert("새로고침후 다시 시도해주세요.");
		}		
		 $('input:checkbox[name="memIdArr"]').focus();
		return;
	}

	if(type=="02"){
		if($("#returnReason").val().trim() == '반려시 반려사유 필수 입력'){
			$("#returnReason").val("");
		}	
		if($("#returnReason").val().trim() == ''){
			alert("반려시 반려사유 필수 입력해주세요.");
			return;
		}
	}
	
	var reqUrl = "/lu/workcert/updateWorkCertMember.do";
	$("#frmWorkCertListDetail").attr("action", reqUrl);
 	$("#frmWorkCertListDetail").submit();
}

function fn_fileDownload(){
	var memIdArr = $('input:checkbox[name="memIdArr"]').is(":checked");
 
	if(!memIdArr){

		alert("제출서류 다운로드 하실 학생을 선택하세요");
		 $('input:checkbox[name="memIdArr"]').focus();
		return;
	}
 
	var reqUrl = "/lu/workcert/downloadWorkCertMember.do";
	$("#frmWorkCertListDetail").attr("action", reqUrl);
	$("#frmWorkCertListDetail").submit();

}


function fn_updateoffwork(workProofId,type){
 
 
	var reqUrl = "/lu/workcert/updateOffWorkCertMember.do";
	$( "#workProofId").val(workProofId) ;
	if(type=="1"){
		$( "#offInsYn").val("Y") ;
	}else if(type=="2"){
		$( "#offRecYn").val("Y") ;
	}else if(type=="3"){
		$( "#offDocYn").val("Y") ;	
	}	
	$("#frmWorkCertListDetail").attr("action", reqUrl);
	$("#frmWorkCertListDetail").submit();

}

//--> 
</script>
 

						<dl id="tab-type">
							<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">제출기간</a></dt>
							<dd id="tab-content-11" style="display:block;">
								<div class="group-area">
							
							<form name="frmWorkCert" id="frmWorkCert" method="post" >
								<input type="hidden" name="seach" value="front" />
								<input type="hidden" name="search" value="top">
								<input type="hidden" name="periodId" value="${workCertVO.periodId }" />
								<input type="hidden" name="insuranceYn" id="insuranceYn" value="${workCertVO.insuranceYn }" />	
								<input type="hidden" name="receiptYn"  id="receiptYn" value="${workCertVO.receiptYn }" />	
																		
									<table class="type-write mt-025">
										<colgroup>
											<col style="width:130px" />
											<col style="width:*" />
										</colgroup>
																		
										<tbody>
 								
											<tr>
												<th>학년도</th>
												<td>
 
													<select id="yyyy" name="yyyy"  onchange="javascript:fn_search();" > 
															<c:forEach var="i" begin="0" end="5" step="1">
														      <option value="${nowYear-i}" <c:if test="${workCertVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
														    </c:forEach>								
													</select> 													
													
												</td>
											</tr>
											<tr>
												<th>학기</th>
												<td>
													<select id="term" name="term" onchange="javascript:fn_search();">
														<option value="1" <c:if test="${workCertVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
														<option value="2" <c:if test="${workCertVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
														<option value="3" <c:if test="${workCertVO.term eq '3' }" > selected="selected"  </c:if>>3학기</option>
														<option value="4" <c:if test="${workCertVO.term eq '4' }" > selected="selected"  </c:if>>4학기</option>
													</select>
												</td>
											</tr>
											<tr>
												<th>증빙서류</th>
												<td>
													<input type="checkbox"   id="insurance" name="insurance" <c:if test="${workCertVO.insuranceYn eq 'Y' }" >checked</c:if> class="choice" /> 4대보험 가입증명서&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="checkbox"   id="receipt"  name="receipt"  <c:if test="${workCertVO.receiptYn eq 'Y' }" >checked</c:if> class="choice" /> 원천징수영수증
												</td>
											</tr>
											<tr>
												<th>제출기간</th>
												<td>
													<input type="text"  id="startTime" name="startTime" value="${workCertVO.startTime}"  style="width:65px" /> ~
													<input type="text"  id="endTime" name="endTime" value="${workCertVO.endTime}" style="width:65px" />
												</td>
											</tr>
											<tr>
												<th>관련규정</th>
												<td>
													<textarea name="relativeRegulation" id="relativeRegulation">${workCertVO.relativeRegulation}</textarea>
												</td>
											</tr>														
										</tbody>
					
									</table><!-- E : 재직증비서류제출기간 -->
								</form>										
									<div class="btn-area align-right mt-010">
										<a href="#"  onclick="javascript:fn_save();" class="orange">저장</a>
									</div>
								</div>



								<div class="group-area mt-030">
								
<form name="frmWorkCertList" id="frmWorkCertList" method="post" >
<input type="hidden" name="search" value="bottom">
<input type="hidden" name="seach" value="front" />
									<table class="type-2">
										<colgroup>
											<col style="width:50px" />
											<col style="width:50px" />
											<col style="width:80px" />
											<col style="width:50px" />
											<col style="width:*" />
											<col style="width:*" />
											<col style="width:*" />
										</colgroup>
										<thead>
											<tr>
												<th>선택</th>
												<th>번호</th>
												<th>학년도</th>
												<th>학기</th>
												<th>증빙서류</th>
												<th>제출기간</th>
												<th>관련규정</th>
											</tr>
										</thead>
										<tbody>
										
										<c:forEach var="workCertList" items="${workCertList}" varStatus="status">
											<tr <c:if test="${workCertList.periodId eq workCertVO.periodId}" >class="highlight"</c:if> >
												<td><input type="radio" name="periodId" value="${workCertList.periodId}"  <c:if test="${workCertList.periodId eq workCertVO.periodId}" >checked</c:if>></td>
												<td>${status.count}</td>
												<td>${workCertList.yyyy}</td>
												<td>${workCertList.term}</td>
												<td class="left">
													<c:if test="${workCertList.insuranceYn eq 'Y'}" >4대보험 가입증명서</c:if>
													<c:if test="${workCertList.receiptYn eq 'Y'}" >
														<c:if test="${workCertList.insuranceYn eq 'Y'}" >,</c:if>원천징수영수증
													</c:if>													
												</td>
												<td>${workCertList.startTime}-${workCertList.endTime}</td>
												<td>${workCertList.relativeRegulation}</td>
											</tr>										
										</c:forEach>
										<c:if test="${empty workCertList}" >
											<tr>
												<td colspan="7"><spring:message code="common.nodata.msg" /></td>
											</tr>
										</c:if>
										</tbody>
									</table>
</form>
									<div class="btn-area align-right mt-010">
										<a href="#!" onclick="javascript:fn_popup();" class="orange float-left">학기별 제출현황 출력</a> 
										<a href="#" onclick="javascript:fn_delete();" class="gray-1">삭제</a> 
										<a href="#" onclick="javascript:fn_update();" class="yellow">수정</a>
									</div>

								</div><!-- E :  재직증비서류제출현황1-->
							</dd>





							<dt class="tab-name-12"><a href="javascript:showTabbtn2();">제출현황</a></dt>
							<dd id="tab-content-12">
<c:set var="total" value="0" />				
<c:set var="ins" value="0" />
<c:set var="rec" value="0" />
<c:set var="doc" value="0" />
<form name="frmWorkCertListDetail" id="frmWorkCertListDetail" method="post" >
<input type="hidden" name="search" value="top">
<input type="hidden" name="seach" value="detail" />
<input type="hidden" name="state" id="state" value="00" />
<input type="hidden" name="periodId" value="${workCertVO.periodId }" />


<input type="hidden" name="workProofId" id="workProofId"  />
<input type="hidden" name="offInsYn" id="offInsYn"  />
<input type="hidden" name="offRecYn" id="offRecYn"  />
<input type="hidden" name="offDocYn" id="offDocYn"  />
 
								<div class="search-box-1 mt-020">
									<select name="yyyy"  onchange="javascript:fn_searchdetail();" > 
											<c:forEach var="i" begin="0" end="5" step="1">
										      <option value="${nowYear-i}" <c:if test="${workCertVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
										    </c:forEach>								
									</select> 	
									
									<select name="term" onchange="javascript:fn_searchdetail();">
										<option value="1" <c:if test="${workCertVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
										<option value="2" <c:if test="${workCertVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
										<option value="3" <c:if test="${workCertVO.term eq '3' }" > selected="selected"  </c:if>>3학기</option>
										<option value="4" <c:if test="${workCertVO.term eq '4' }" > selected="selected"  </c:if>>4학기</option>
									</select>
					 
									<input type="text" name="searchCompanyName" id="searchCompanyName"  value="${workCertVO.searchCompanyName}<c:if test="${workCertVO.searchCompanyName eq '' }" >기업명</c:if>"  onfocus="if(this.value=='');this.value=''" style="width:150px;" />
									<input type="text" name="searchValue"  id="searchValue" value="${workCertVO.searchValue}<c:if test="${workCertVO.searchValue eq '' }" >학생명,학번 검색</c:if>"  onfocus="if(this.value=='');this.value=''" style="width:150px;" />
									
									<select name="searchState" >
										<option value="" <c:if test="${workCertVO.searchState eq '' }" > selected="selected"  </c:if>>-전체-</option>									
										<option value="00" <c:if test="${workCertVO.searchState eq '00' }" > selected="selected"  </c:if>>미승인</option>
										<option value="01" <c:if test="${workCertVO.searchState eq '01' }" > selected="selected"  </c:if>>승인</option>
										<option value="02" <c:if test="${workCertVO.searchState eq '02' }" > selected="selected"  </c:if>>반려</option>
									</select>
									<a href="#!" onclick="javascript:fn_searchdetail();">검색</a>
								</div><!-- E : search-box-1 -->



								<div class="group-area mt-010">
									<table class="type-2">
										<colgroup>
											<col style="width:40px" />
											<col style="width:*" />
											<col style="width:50px" />
											<col style="width:120px" />
											<col style="width:120px" />
											<col style="width:*" />
											<col style="width:*" />
											<col style="width:*" />
											<col style="width:70px" />
										</colgroup>
										<thead>
											<tr>
												<th><input type="checkbox" id="checkMember" onclick="javascript:fn_checkboxAll();" class="choice" /></th>
												<th>기업명</th>
												<th>학년</th>
												<th>학번</th>
												<th>이름</th>
												<th>4대보험가입증명서</th>
												<th>원천징수영수증</th>
												<th>보완서류</th>
												<th>승인여부</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="workCertDetailList" items="${workCertDetailList}" varStatus="status">
									
											<tr>
												<td><input type="checkbox" name="memIdArr" value="${workCertDetailList.memId}" class="checkMember" /></td>
												<td>${workCertDetailList.companyName}</td>
												<td>${workCertDetailList.level}</td>
												<td>${workCertDetailList.memId}</td>
												<td>${workCertDetailList.memName}</td>
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdRec}" >
													<c:set var="rec" value="${rec + 1}" />
														제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdRec}" >
														<c:if test="${workCertDetailList.offRecYn eq 'Y' }">
														오프라인 제출
														</c:if>
														<c:if test="${workCertDetailList.offRecYn ne 'Y' }">
														미제출<a href="#!" onclick="javascript:fn_updateoffwork('${workCertDetailList.workProofId}','2');" class="btn-line-blue">접수</a>
														</c:if>
														
													</c:if>													
												</td>
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdInc}" >
													<c:set var="ins" value="${ins + 1 }" />
														제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdInc}" >
														<c:if test="${workCertDetailList.offInsYn eq 'Y' }">
														오프라인 제출
														</c:if>
														<c:if test="${workCertDetailList.offInsYn ne 'Y' }">
														미제출<a href="#!"  onclick="javascript:fn_updateoffwork('${workCertDetailList.workProofId}','1');" class="btn-line-blue">접수</a>
														</c:if>
													</c:if>
												</td>
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdDoc}" >
													<c:set var="doc" value="${doc + 1}" />
													제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdDoc}" >
														<c:if test="${workCertDetailList.offDocYn eq 'Y' }">
														오프라인 제출
														</c:if>
														<c:if test="${workCertDetailList.offDocYn ne 'Y' }">
														미제출<a href="#!"  onclick="javascript:fn_updateoffwork('${workCertDetailList.workProofId}','3');" class="btn-line-blue">접수</a>
														</c:if>
													</c:if>
												</td>
												<td>
													<c:if test="${workCertDetailList.state eq '00'}" >미승인</c:if>
													<c:if test="${workCertDetailList.state eq '01'}" >
													<c:set var="total" value="${total + 1}" />
													승인
													</c:if>
													<c:if test="${workCertDetailList.state eq '02'}" >반려</c:if>
												</td>
											</tr>										
										
										
										</c:forEach>
										<c:if test="${!empty workCertDetailList}" >
											<tr>
												<td colspan="3">계</td>
												<td colspan="2">${fn:length(workCertDetailList)} 명</td>
												<td>${rec} 명</td>
												<td>${ins} 명</td>
												<td>${doc} 명</td>
												<td>${total} 명</td>
											</tr>
										</c:if>
										<c:if test="${empty workCertDetailList}" >
											<tr>
												<td colspan="9"><spring:message code="common.nodata.msg" /></td>												
											</tr>
										</c:if>
											
										</tbody>
									</table>

									<div class="btn-area align-right mt-010">
										<a href="#!" onclick="javascript:fn_popupdeatil();" class="orange float-left">제출현황 출력</a>
										<a href="#"  onclick="javascript:fn_fileDownload();" class="gray-1 float-left">제출서류 다운로드</a>
										
										<!-- 임시주석 -->
										<!-- <a href="javascript:alert('준비중');" class="yellow float-left">SMS 발송</a>
										<a href="javascript:alert('준비중');" class="orange float-left">E-MAIL발송</a> -->
										<input type="text" name="returnReason"  id="returnReason" value="반려시 반려사유 필수 입력"  onfocus="if(this.value=='');this.value=''"  />
										<a href="#" onclick="javascript:fn_updateDetail('02');" class="orange">반려</a>
										<a href="#" onclick="javascript:fn_updateDetail('01');" class="orange">승인</a>
									</div>

								</div><!-- E :  list-->

</form>

							</dd>
						</dl>
<form name="frmWorkCertListpopup" id="frmWorkCertListpopup" method="post" >
	<input type="hidden" name="yyyy" value="${workCertVO.yyyy}"/>
	<input type="hidden" name="term" value="${workCertVO.term}"/>
</form>						