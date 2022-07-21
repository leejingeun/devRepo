<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
						<h2>과제</h2>

						<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

						<div class="group-area">
							<table class="type-1">
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

							<div class="btn-area align-right mt-010">
								<a href="/lu/report/goInsertReport.do" onclick="javascript:" class="orange">과제출제</a>
							</div><!-- E : btn-area -->
						</div>
<script type="text/javascript">
<!--

$(document).ready(function() {

	
});
/*  수정,삭제 */
function fn_formSave(type){ 
	 
 	if(!$(":input:radio[name=reportId]:checked").val()){
 		alert("과제를 선택하세요!");
 		return;
 	} 
	//수정
	if(type=="U"){
		var reqUrl =  "/lu/report/goUpdateReport.do";
		$("#frmReport").attr("target", "_self");
		$("#frmReport").attr("action", reqUrl);
		$("#frmReport").submit();
	//삭제	
	}else if(type=="D"){
		if (  confirm("삭제 하시겠습니까?")) {
			var reqUrl =  "/lu/report/deleteReport.do";
			$("#frmReport").attr("target", "_self");
			$("#frmReport").attr("action", reqUrl);
			$("#frmReport").submit();	
		}
	}
}
function fn_popupReport(reportId){
	var url = "/lu/report/popupReport.do?reportId="+reportId;
	var wndName = "report";
	var wWidth = "650";
	var wHeight = "840";

	popOpenWindow( url, wndName, wWidth, wHeight, 0, 0, 'scrollbars=yes' );
}
//--> 
</script>

<form:form commandName="frmReport" name="frmReport" method="post"   >
						<h2>과제 목록</h2>
						<div class="group-area">
							<table class="type-2">
								<colgroup>
									<col style="width:50px" />
									<col style="width:60px" />
									<col style="width:*" />
									<col style="width:180px" />
									<col style="width:120px" />
									<col style="width:120px" />
								</colgroup>
								<tbody>
									<tr>
										<th>선택</th>
										<th>주차</th>
										<th>제목</th>
										<th>제출기간</th>
										<th>제출현황</th>
										<th>채점현황</th>
									</tr>
									<c:forEach var="result" items="${result}" varStatus="status">
									<tr>
										<td>
											<c:if test="${result.scoreCnt=='0'}">
												<input type="radio" name="reportId"  value="${result.reportId}" class="choice"  <c:if test="${status.index==0 }">checked</c:if> />
											</c:if>
										</td>
										<td>${result.weekCnt}</td>
										<td class="left"><a href="/lu/report/getReport.do?reportId=${result.reportId}" class="text">${result.reportName}</a></td>
										<td>${result.submitStartDate} ~ ${result.submitEndDate}</td>
										
										<td>${result.scoreCnt}/${result.totCnt}&nbsp;&nbsp;&nbsp;<a href="#!" onclick="javascript:fn_popupReport('${result.reportId}');" class="btn-full-blue">보기</a></td>
										<td>
											<c:choose>
										       <c:when test="${result.scoreOn ne result.totCnt }">
													<a href="/lu/report/goScoreReport.do?reportId=${result.reportId}" class="btn-line-gray">미채점</a>
										       </c:when>
										       <c:when test="${result.reportId eq result.totCnt }">
													<a href="/lu/report/goScoreReport.do?reportId=${result.reportId}" class="btn-line-blue">열기</a>
										       </c:when>
 											   <c:otherwise>
													<a href="/lu/report/goScoreReport.do?reportId=${result.reportId}" class="btn-line-gray">미채점</a>
										       </c:otherwise>										       
										    </c:choose>
											 
											
										</td>
									</tr>
									</c:forEach>
								<c:if test="${empty result}">
						          <tr>
						            <td colspan="6">자료가 없습니다.</td>
						          </tr>
						        </c:if>
								</tbody>
							</table><!-- E : 과제관리 -->
							<c:if test="${!empty result}">
							<div class="btn-area align-right mt-010">
								<a href="#" onclick="javascript:fn_formSave('D');" class="gray-1">삭제</a><a href="#"  onclick="javascript:fn_formSave('U');" class="yellow">수정</a>
							</div><!-- E : btn-area -->
							</c:if>
						</div>
</form:form>