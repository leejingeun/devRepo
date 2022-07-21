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

/* 엑셀 다운로드 */
function fn_exclDownload(){
	var reqUrl = "/lu/report/getReportExcel.do";
	$("#frmReport").attr("action", reqUrl);
	$("#frmReport").attr("target","_self");
	$("#frmReport").submit();
}
/*  일괄다운로드 */
function fn_formSave_file(){

	var reqUrl =  "/lu/report/listReportExcel.do";
	$("#frmReport").attr("target", "_self");
	$("#frmReport").attr("action", reqUrl);
	$("#frmReport").submit();		

}
//--> 
</script>
<form name="frmReport" id="frmReport" action=""  method="post" >
	<input type="hidden" name="reportId" value="${reportVO.reportId}"/>
</form>


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
							</tbody>
						</table><!-- E : 과제관리1 -->



						<table class="type-2 mt-015">
							<colgroup>
								<col style="width:50px" />
								<col style="width:70px" />
								<col style="width:*" />
								<col style="width:170px" />
								<col style="width:130px" />
								<col style="width:130px" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="check0" id="check0" class="choice" onclick="javascript:com.checkAll('check0','check1');" /></th>
									<th>번호</th>
									<th>학번</th>
									<th>이름</th>
									<th>제출일</th>
									<th>제출현황</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach var="result" items="${result}" varStatus="status">
							
								<tr>
									<td><input type="checkbox" name="check1" value="" class="choice" /></td>
									<td>${status.count}</td>
									<td>${result.memId }</td>
									<td>${result.memName }</td>
									<td>${result.insertDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');"  class="btn-line-orange">제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>										
									</td>
								</tr>									
								 				
							</c:forEach>
							<c:if test="${empty result}">
						          <tr>
						            <td colspan="6">자료가 없습니다.</td>
						          </tr>
						    </c:if>
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<a href="/lu/report/listReport.do" class="gray-1 float-left">&lt; 이전화면</a>
							
							<a href="#" onclick="javascript:fn_exclDownload();" class="yellow">제출현황 출력</a>
							<a href="#" onclick="javascript:fn_formSave_file();" class="yellow">과제 다운로드</a>
							
							<!-- 임시주석 -->
							<!--<a href="#!" class="yellow">SMS 발송</a> -->
						</div><!-- E : btn-area -->