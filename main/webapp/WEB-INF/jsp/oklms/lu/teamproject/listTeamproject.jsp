<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
						<h2>팀프로젝트</h2>

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
								<a href="/lu/teamproject/goInsertTeamproject.do" class="orange">프로젝트 생성</a>
							</div><!-- E : btn-area -->
						</div>
<script type="text/javascript">
	<!--
	
	$(document).ready(function() {
	
		
	});
	/*  수정,삭제 */
	function fn_formSave(type){ 
		 
	 	if(!$(":input:radio[name=teamprojectId]:checked").val()){
	 		alert("팀프로젝트를 선택하세요!");
	 		return;
	 	} 
		//수정
		if(type=="U"){
			var reqUrl =  "/lu/teamproject/goUpdateTeamproject.do";
			$("#frmTeamproject").attr("target", "_self");
			$("#frmTeamproject").attr("action", reqUrl);
			$("#frmTeamproject").submit();
		//삭제	
		}else if(type=="D"){
			if (  confirm("삭제 하시겠습니까?")) {
				var reqUrl =  "/lu/teamproject/deleteTeamproject.do";
				$("#frmTeamproject").attr("target", "_self");
				$("#frmTeamproject").attr("action", reqUrl);
				$("#frmTeamproject").submit();	
			}
		}
	}
	function fn_popupTeamproject(teamprojectId){
		var url = "/lu/teamproject/popupTeamproject.do?teamprojectId="+teamprojectId;
		var wndName = "Teamproject";
		var wWidth = "680";
		var wHeight = "840";
	
		popOpenWindow( url, wndName, wWidth, wHeight, 0, 0, 'scrollbars=yes' );
	}
	//--> 
</script>

<form:form commandName="frmTeamproject" name="frmTeamproject" method="post"   >
						<h2>팀프로젝트 목록</h2>
						<div class="group-area">
							<table class="type-2">
								<colgroup>
									<col style="width:40px" />
									<col style="width:50px" />
									<col style="width:*" />
									<col style="width:160px" />
									<col style="width:100px" />
									<col style="width:60px" />
									<col style="width:70px" />
									<col style="width:80px" />
								</colgroup>
								<tbody>
									<tr>
										<th>선택</th>
										<th>회차</th>
										<th>팀프로젝트 주제</th>
										<th>팀프로젝트 기간</th>
										<th>과제 마감일</th>
										<th>팀수</th>
										<th>제출현황</th>
										<th>채점현황</th>
									</tr>
									<c:forEach var="result" items="${result}" varStatus="status">
									<tr>
										<td>
											<c:if test="${result.scoreCnt==0}">
												<input type="radio" name="teamprojectId"  value="${result.teamprojectId}" class="choice"  <c:if test="${result.submitCnt > 0 }"> disabled="disabled" </c:if>  />
											</c:if>
										</td>
										<td>${status.index+1}</td>
										<td class="left"><a href="/lu/teamproject/getTeamproject.do?teamprojectId=${result.teamprojectId}" class="text">${result.projectName}</a></td>
										<td>${result.projectStartDate} ~ ${result.projectEndDate}</td>
										
										<td>${result.submitEndDate}</td>
										<td>${result.teamCnt}</td>
										<td><a href="#!" onclick="javascript:fn_popupTeamproject('${result.teamprojectId}');" class="btn-full-blue">${result.submitCnt}/${result.totCnt}</a></td>
										<td>
											<c:choose>
										       <c:when test="${result.scoreOn eq 'N' }">
													<a href="/lu/teamproject/goScoreTeamproject.do?teamprojectId=${result.teamprojectId}" class="btn-line-gray">미채점</a>
										       </c:when>
										       <c:when test="${result.scoreOn ne 'Y' }">
													<a href="/lu/teamproject/goScoreTeamproject.do?teamprojectId=${result.teamprojectId}" class="btn-line-blue">열기</a>
										       </c:when>
 											   <c:otherwise>
													<a href="/lu/teamproject/goScoreTeamproject.do?teamprojectId=${result.teamprojectId}" class="btn-line-gray">미채점</a>
										       </c:otherwise>										       
										    </c:choose>
											 
											
										</td>
									</tr>
									</c:forEach>
								<c:if test="${empty result}">
						          <tr>
						            <td colspan="8">자료가 없습니다.</td>
						          </tr>
						        </c:if>
								</tbody>
							</table><!-- E : 팀프로젝트관리 -->
							<c:if test="${!empty result}">
							<div class="btn-area align-right mt-010">
								<a href="#" onclick="javascript:fn_formSave('D');" class="gray-1">삭제</a><a href="#"  onclick="javascript:fn_formSave('U');" class="yellow">수정</a>
							</div><!-- E : btn-area -->
							</c:if>
						</div>
</form:form>