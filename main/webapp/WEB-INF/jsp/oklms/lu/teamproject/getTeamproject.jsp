<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

						<h2>팀프로젝트</h2>
						<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

						<table class="type-1 mb-030">
							<colgroup>
								<col style="width:110px" />
								<col style="width:130px" />
								<col style="width:110px" />
								<col style="width:*" />
								<col style="width:110px" />
								<col style="width:120px" />
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
 
	// 그룹테이블 로드 된 후, 팀명으로 table row merge
	mergeCommonRows($("#tblGroup"), 2);
	
});

/* 엑셀 다운로드 */
function fn_exclDownload(){
	var reqUrl = "/lu/teamproject/getTeamprojectExcel.do";
	$("#frmTeamproject").attr("action", reqUrl);
	$("#frmTeamproject").attr("target","_self");
	$("#frmTeamproject").submit();
}
/*  일괄다운로드 */
function fn_formSave_file(){

	var reqUrl =  "/lu/teamproject/listTeamprojectExcel.do";
	$("#frmTeamproject").attr("target", "_self");
	$("#frmTeamproject").attr("action", reqUrl);
	$("#frmTeamproject").submit();

}
//--> 
</script>
<form name="frmTeamproject" id="frmTeamproject" action=""  method="post" >
	<input type="hidden" name="teamprojectId" value="${teamprojectVO.teamprojectId}"/>
</form>


						<h2>팀프로젝트 제출 현황</h2>

						<div class="group-area">
							<table class="type-write mb-010">
								<colgroup>
									<col style="width:130px" />
									<col style="width:*" />
									<col style="width:130px" />
									<col style="width:*" />
								</colgroup>
							<tbody>
								<tr>
									<th>팀프로젝트 주제</th>
									<td colspan="3">${teamprojectVO.projectName}</td>
								</tr>
								<tr>
									<th>팀프로젝트 기간</th>
									<td>${teamprojectVO.projectStartDate} ~ ${teamprojectVO.projectEndDate}</td>
									<th>과제제출 기간</th>
									<td>${teamprojectVO.submitStartDate} ~ ${teamprojectVO.submitEndDate}</td>
								</tr>
								<tr>
									<th>제출구분</th>
									<td>${(teamprojectVO.submitType eq 'T')? '팀장만제출':'개별제출'}</td>
									<th>지각제출</th>
									<td>${(teamprojectVO.submitLateYn eq 'Y')? '허용':'불허'}</td>
								</tr>
								<tr>
									<th>평가</th>
									<td>${teamprojectVO.evalYn }</td>
									<th>배점</th>
									<td>${teamprojectVO.score } 점</td>
								</tr>
								<tr>
									<th>내용</th>
									<td colspan="3">${teamprojectVO.projectDesc}</td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td>
										<c:if test="${!empty resultFile}">
											<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
										</c:if>
									</td>
									<th>팀 구성</th>
									<td>${teamprojectVO.teamCnt }팀</td>
								</tr>
							</tbody>
						</table><!-- E : 팀프로젝트관리1 -->



						<table class="type-2" id="tblGroup">
							<colgroup>
								<col style="width:50px" />
								<col style="width:*" />
								<col style="width:130px" />
								<col style="width:100px" />
								<col style="width:120px" />
								<col style="width:100px" />
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" name="" value="" class="choice" /></th>
									<th>팀명</th>
									<th>팀장</th>
									<th>팀원 수</th>
									<th>과제제출일</th>
									<th>제출현황</th>
								</tr>
							</thead>
							<tbody>
							
							<c:forEach var="result" items="${result}" varStatus="status">
								<c:if test="${(teamprojectVO.submitType eq 'I') or (teamprojectVO.submitType eq 'T' and result.leaderYn eq 'Y')}">
								<tr>
									<td><input type="checkbox" name="arrTeamprojectId" value="${result.teamprojectSubmitId }" class="choice" /></td>
									<td class="left"><fmt:formatNumber type="number" minIntegerDigits="2"  value="${result.groupId}" />팀</td>
									<td>${result.memName }</td>
									<td>${result.teamMemberCnt }</td>
									<td>${result.submitDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');"  class="btn-line-orange">제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>										
									</td>
								</tr>									
								</c:if>								 				
							</c:forEach>
							<c:if test="${empty result}">
						          <tr>
						            <td colspan="6">자료가 없습니다.</td>
						          </tr>
						    </c:if>
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<a href="/lu/teamproject/listTeamproject.do" class="gray-3 float-left">&lt; 이전화면</a>
							
							<a href="#" onclick="javascript:fn_exclDownload();" class="yellow">제출현황 출력</a>
							<a href="#" onclick="javascript:fn_formSave_file();" class="yellow">과제물 다운로드</a>
							<!-- 임시주석 -->
							<!-- <a href="#" onclick="javascript:alert('작업중')" class="orange">SMS발송</a> -->
						</div><!-- E : btn-area -->