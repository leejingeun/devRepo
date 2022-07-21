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
		var reqUrl =  "/lu/teamproject/insertTeamprojectStd.do";
		$("#frmTeamproject").attr("target", "_self");
		$("#frmTeamproject").attr("action", reqUrl);
		$("#frmTeamproject").submit();		
	}else if(type=='D'){
		var reqUrl =  "/lu/teamproject/deleteTeamprojectStd.do";
		$("#frmTeamproject").attr("target", "_self");
		$("#frmTeamproject").attr("action", reqUrl);
		$("#frmTeamproject").submit();
	}
 
}

//--> 
</script>
<form:form commandName="frmTeamproject" name="frmTeamproject" method="post" enctype="multipart/form-data" >
<input name="teamprojectId" type="hidden" value="${teamprojectVO.teamprojectId }" />
<input name="teamprojectSubmitId" type="hidden" value="${teamprojectSubmitVO.teamprojectSubmitId }" />
<input name="groupId" type="hidden" value="${teamprojectSubmitVO.groupId }" />
						<table class="type-2">
							<colgroup>
								<col style="width:130px" />
								<col style="width:*" />
							</colgroup>
							<tbody>
								<tr>
									<th>팀프로젝트 주제</th>
									<td colspan="3">${teamprojectVO.projectName}1</td>
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
									<th>팀명 / 팀원</th>
									<td><fmt:formatNumber type="number" minIntegerDigits="2"  value="${teamprojectSubmitVO.groupId}" />팀 / ( ${teamprojectSubmitVO.teamMembers} )</td>
								</tr>
								<tr>
									<th>과제물</th>
									<td class="left" colspan="3">
									<c:if test="${!empty resultFile1}">
											<a href="javascript:com.downFile('${resultFile1.atchFileId}','${resultFile1.fileSn}');" class="text-file">${resultFile1.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${teamprojectVO.submitStatus ne '완료'  || (teamprojectVO.submitStatus eq '완료' && teamprojectVO.submitLateYn eq 'Y') }">
												<c:if test="${(teamprojectVO.submitType eq 'T' && teamprojectSubmitVO.leaderYn eq 'Y' ) || teamprojectVO.submitType ne 'T'  }">
											<a href="#" onclick="javascript:fn_formSave('D');">[삭제]</a>
												</c:if>
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
							<a href="/lu/teamproject/listTeamprojectStd.do" class="gray-1 float-left">목록</a>
							<c:if test="${teamprojectVO.submitStatus ne '완료'  || (teamprojectVO.submitStatus eq '완료' && teamprojectVO.submitLateYn eq 'Y') }">
								<c:if test="${(teamprojectVO.submitType eq 'T' && teamprojectSubmitVO.leaderYn eq 'Y' ) || teamprojectVO.submitType ne 'T'  }">
									<a href="#!" onclick="javascript:fn_formSave('I');" class="orange">제출</a>
								</c:if>
							</c:if>
							
							
						</div><!-- E : btn-area -->


  