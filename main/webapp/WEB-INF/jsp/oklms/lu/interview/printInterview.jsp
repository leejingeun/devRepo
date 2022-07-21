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
<c:set var="indexnum" value="8" />
			<c:if test="${!empty result.tempSj1}">
<c:set var="indexnum" value="${indexnum+1 }" />
			</c:if>
			<c:if test="${!empty result.tempSj2}">
<c:set var="indexnum" value="${indexnum+1 }" />
			</c:if>
			<c:if test="${!empty result.tempSj3}">
<c:set var="indexnum" value="${indexnum+1 }" />			
			</c:if>				
			<div id="pop-wrapper" class="min-w650" >			
						<h2>면담일지</h2>

						<table class="type-write">
							<colgroup>
								<col style="width:100px" />
								<col style="width:100px" />
								<col style="width:*" />
							</colgroup>
							<tr>
								<th>일자</th>
								<th>구분</th>
								<th>면담내용</th>
							</tr>
							<tr>
								<th rowspan="${indexnum}" class="sub-name">${result.interviewNoteDate }</th>
								<th class="sub-name">학습근로자</th>
								<td>${result.interviewMemberNames } </td>
							</tr>
							<tr>
								<th class="border-left sub-name">훈련과정</th>
								<td>${result.traningProcessName }</td>
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
									<c:if test="${!empty resultFile2}">
											<a href="javascript:com.downFile('${resultFile2.atchFileId}','${resultFile2.fileSn}');" class="text-file">
											<img src="/cmm/fms/getImageAtch.do?atchFileId=${resultFile2.atchFileId}" alt="${resultFile2.orgFileName}"/>
											</a>
									</c:if>										
								</td>
							</tr>
							<tr>
								<th rowspan="2" class="border-left sub-name">면담내용</th>
								<td><textarea name="textarea" rows="5">${result.totalTalk }</textarea></td>
							</tr>
							<tr>
								<td class="border-left">
									<c:if test="${!empty resultFile1}">
											<a href="javascript:com.downFile('${resultFile1.atchFileId}','${resultFile1.fileSn}');" class="text-file">${resultFile1.orgFileName}</a>
									</c:if>								
								</td>
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
function fn_cancel(){

	var reqUrl =  "/lu/interview/listInterview.do";
	$("#frmInterview").attr("target", "_self");
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").submit();		

}
//--> 
</script>						