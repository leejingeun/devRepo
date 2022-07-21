<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 <c:set var="interviewNoteDateMmTemp" value=""/> 
						<h2>면담일지 조회</h2>
						<table class="type-2 mb-040">
							<colgroup>
								<col style="width:*" />
								<col style="width:*" />
								<col style="width:120px" />
								<!--col style="width:120px" /-->
								<col style="width:120px" />
							</colgroup>
							<thead>
								<tr>
									<th>기업명</th>
									<th>소재지</th>
									<th>학년도</th>
									<!--th>학기</th-->
									<th>월</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${!empty result}">
								<tr>
									<td>${result.companyName }</td>
									<td>${result.address }</td>
									<td>${interviewVO.yyyy }</td>
									<!--td></td-->
									<td>${interviewVO.mm }</td>
								</tr>
								</c:if>
							</tbody>
						</table>



						<h2>월별 면담일지 조회</h2>
<c:forEach var="result" items="${resultlist}" varStatus="status">
<c:set var="interviewNoteDateMmTemp" value="${result.interviewNoteDateMm }"/> 
						<table class="type-write  mb-020">
							<colgroup>
								<col style="width:100px" />
								<col style="width:100px" />
								<col style="width:*" />
							</colgroup>
							<tr>
								<th>월</th>
								<th>구분</th>
								<th>면담내용</th>
							</tr>
							<tr>
								<th rowspan="9" class="sub-name">${result.interviewNoteDateMm } 월</th>
								<th class="sub-name">훈련과정</th>
								<td> ${result.traningProcessName } </td>
							</tr>
							<tr>
								<th class="border-left sub-name">면담자</th>
								<td>${result.interviewMemName }</td>
							</tr>
							<tr>
								<th class="border-left sub-name">학습근로자</th>
								<td>${result.interviewMemberNames }</td>
							</tr>
							<tr>
								<th class="border-left sub-name">훈련</th>
								<td>${result.trainingTalk }</td>
							</tr>
							<tr>
								<th class="border-left sub-name">근로</th>
								<td>${result.workTalk }</td>
							</tr>
							<tr>
								<th class="border-left sub-name">기타</th>
								<td>${result.tempTalk }</td>
							</tr>
							<tr>
								<th class="border-left sub-name">지도상황</th>
								<td>
									<c:if test="${!empty result.resultImgFile}">
											<a href="javascript:com.downFile('${result.resultImgFile.atchFileId}','${result.resultImgFile.fileSn}');" class="text-file">
											<img src="/cmm/fms/getImageAtch.do?atchFileId=${result.resultImgFile.atchFileId}" alt="${result.resultImgFile.orgFileName}"/>
											</a>
									</c:if>		
								</td>
							</tr>
							<tr>
								<th   class="border-left sub-name">면담내용</th>
								<td><textarea name="textarea" rows="5">${result.totalTalk }</textarea></td>
							</tr>


							<c:if test="${!empty result.tempSj1}">
							<tr>
								<th class="border-left sub-name">${result.tempSj1 }</th>
								<td>${result.tempCn1 }</td>
							</tr>
							</c:if>
							
							<c:if test="${!empty result.tempSj2}">
							<tr>
								<th class="border-left sub-name">${result.tempSj2 }</th>
								<td>${result.tempCn2 }</td>
							</tr>
							</c:if>
							
							<c:if test="${!empty result.tempSj3}">
							<tr>
								<th class="border-left sub-name">${result.tempSj3 }</th>
								<td>${result.tempCn3 }</td>
							</tr>							
							</c:if>		
														
							<c:if test="${!empty result.resultFile}">
							<tr>
								<td class="border-left">									
											<a href="javascript:com.downFile('${result.resultFile.atchFileId}','${result.resultFile.fileSn}');" class="text-file">${result.resultFile.orgFileName}</a>									
								</td>
							</tr>
							</c:if>
							
						</table>
						<br/>
						<br/>
</c:forEach>
 <form:form commandName="frmInterview" name="frmInterview" method="post">
	<input type="hidden" name="companyId"  value="${result.companyId }" />
	<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
	<input type="hidden" name="interviewNoteDateMmParam" id="interviewNoteDateMm" value="${interviewNoteDateMmTemp }"  />
	<input type="hidden" name="yyyy"  value="${interviewVO.yyyy }" />
</form:form>						
<script type="text/javascript">
<!--

$(document).ready(function() {

	
});

/*  수정 */
function fn_formSave(type){ 
  
	//수정
	if(type=="U"){
			var reqUrl =  "/lu/interview/goUpdateInterviewCenter.do";
			$("#frmInterview").attr("target", "_self");
			$("#frmInterview").attr("action", reqUrl);
			$("#frmInterview").submit();
	}else if(type=="P"){ 
	//	$("#interviewNoteDateMmParam").val( interviewNoteDateMm );
		var reqUrl = "/lu/interview/printInterviewCenter.do";
		$("#frmInterview").attr("action", reqUrl);
		$("#frmInterview").attr("target","_blank");
		$("#frmInterview").submit();	
	}
 
} 
function fn_cancel(){

	var reqUrl =  "/lu/interview/listInterviewCenter.do";
	$("#frmInterview").attr("target", "_self");
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").submit();		

}
//--> 
</script>	

						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript: fn_formSave('P');" class="gray-1 float-left">출력</a>
							<a href="#!" onclick="javascript:fn_cancel();" class="gray-1">목록</a>
							<a href="#!" onclick="javascript: fn_formSave('U');" class="yellow">수정</a>
						</div><!-- E : btn-area -->
 