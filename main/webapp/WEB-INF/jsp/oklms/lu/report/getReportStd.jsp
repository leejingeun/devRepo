<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
  
						<h2>과제</h2>
						<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

						<table class="type-1 mb-030">
							<colgroup>
								<col style="width:15%" />
								<col style="width:*px" />
								<col style="width:15%" />
								<col style="width:*px" />
								<col style="width:15%" />
								<col style="width:*px" />
							</colgroup>
							<tbody>
								<tr>
										<th>교과형태</th>
									<td>${currProcVO.subjectTraningTypeName}</td>
										<th>과정구분</th>
									<td>${currProcVO.subjectTypeName}</td>
										<th>학점</th>
										<td>${currProcVO.point }학점</td>
									</tr>
									<tr>
										<th>교수</th>
										<td>${currProcVO.insNames}</td>
										<th>온라인 교육형태</th>
										<td>${currProcVO.onlineTypeName} (성적비율 ${currProcVO.gradeRatio}%)</td>
										<th>선수여부</th>
										<td>${currProcVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
									</tr>
							</tbody> 
						</table>

<script type="text/javascript">
<!--

$(document).ready(function() {

	
});
/*  수정,삭제 */
function fn_formSave(type){
	if(type=='I'){ 

		if($("#fileName-3").val()==""){
			alert("과제물을 첨부하세요.");
			return;
		}
		var reqUrl =  "/lu/report/insertReportStd.do";
		$("#frmReport").attr("target", "_self");
		$("#frmReport").attr("action", reqUrl);
		$("#frmReport").submit();		
	}else if(type=='D'){
		var reqUrl =  "/lu/report/deleteReportSubmit.do";
		$("#frmReport").attr("target", "_self");
		$("#frmReport").attr("action", reqUrl);
		$("#frmReport").submit();
	}
 
}

//--> 
</script>
<form:form commandName="frmReport" name="frmReport" method="post" enctype="multipart/form-data" >
<input name="reportId" type="hidden" value="${reportVO.reportId }" />
<input name="reportSubmitId" type="hidden" value="${reportVO1.reportSubmitId }" />
						<table class="type-2">
							<colgroup>
								<col style="width:130px" />
								<col style="width:*" />
							</colgroup>
							<tbody>
								<tr>
									<th>주차</th>
									<td class="left">${reportVO.weekCnt}주차</td>
								</tr>
								<tr>
									<th>제목</th>
									<td class="left">${reportVO.reportName}</td>
								</tr>
								<tr>
									<th>과제제출 기간</th>
									<td class="left">${reportVO.submitStartDate} ~ ${reportVO.submitEndDate}</td>
								</tr>
								<tr>
									<th>평가</th>
									<td class="left">${reportVO.evalYn}</td>
								</tr>
								<tr>
									<th>배점</th>
									<td class="left">${reportVO.score} 점</td>
								</tr>
								<tr>
									<th>내용</th>
									<td class="left">${reportVO.reportDesc}</td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td class="left">
									<c:if test="${!empty resultFile}">
											<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</c:if>
									</td>
								</tr>
								<tr>
									<th>과제물</th>
									<td class="left">
									<c:if test="${!empty resultFile1}">
											<a href="javascript:com.downFile('${resultFile1.atchFileId}','${resultFile1.fileSn}');" class="text-file">${resultFile1.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${reportVO.submitStatus ne '완료'}">
											<a href="#" onclick="javascript:fn_formSave('D');">[삭제]</a>
											</c:if>
									</c:if>		
									<c:if test="${empty resultFile1}">								
										<input type="text" id="fileName-3" style="width:50%;" readonly="readonly">
										<p class="file-find">
											<a href="#@" class="checkfile">파일찾기</a>
											<input type="file" class="file_input_hidden" name="file-input" onchange="javascript: document.getElementById('fileName-3').value = this.value" />
										</p>
									</c:if>	
									</td>
								</tr>
							</tbody>
						</table><!-- E : 과제관리1 --> 
</form:form>
						<div class="btn-area align-right mt-010">
							<a href="/lu/report/listReportStd.do" class="gray-1 float-left">목록</a>
							<c:if test="${reportVO.submitStatus ne '완료'}">
								<a href="#!" onclick="javascript:fn_formSave('I');" class="orange">제출</a>
							</c:if>
							
							
						</div><!-- E : btn-area -->


  