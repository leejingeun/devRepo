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

<script type="text/javascript">
<!--

$(document).ready(function() {
	
	// 그룹테이블 로드 된 후, 팀원수 table row merge
	mergeCommonRows($("#tblGroup"), 4);
	// 그룹테이블 로드 된 후, 팀명으로 table row merge
	mergeCommonRows($("#tblGroup"), 2);
	
	if($("#checkAll").length > 0){
		$("#checkAll").click(function(){
			$("[name='arrTeamprojectSubmitId']:enabled").prop("checked",$(this).is(":checked"));
		});
	}
	
	
	var score=0;
	// 팀장만 제출일 경우, 팀장점수 입력시 - 팀원점수 동일기입.
	if(${(teamprojectVO.submitType eq 'T')? 'true':'false'}){
		$(".isLeader").each(function(idx){
			$(this).change(function(){
				score = this.value;
				$(".group_id_"+$(this).data("groupId")).each(function(idx){
					if(idx != 0){
						this.value = score;
						$(this).change();
					}
				});
			});
		});
	}
	
    $('.arrEvalScore').keyup(function () {
    	var num10=${teamprojectVO.score};
    	if(num10< $(this).val()){
        	alert("배점보다 큰점수를 입력할수 없습니다.");
        	 $(this).val(num10);
        	 $(this).change();
    	}
    });

    // 수정되면 체크박스 자동체크.
    $(".arrEvalScore").change(function(){
    	$("[type='checkbox']",this.parentElement.parentElement).attr({"checked":"checked"});
    });
	
});

 
function fn_formSave(){
		
	//점수저장
	if (  confirm("점수를 저장 하시겠습니까?")) {
		var reqUrl =  "/lu/teamproject/scoreTeamproject.do";
		$("#frmTeamproject").attr("target", "_self");
		$("#frmTeamproject").attr("action", reqUrl);
		$("#frmTeamproject").submit();	
	} 
}
//alert("${loginAuthGroupId}");
//--> 
</script>
<form:form commandName="frmTeamproject" name="frmTeamproject" method="post"  >
						<table class="type-2"  id="tblGroup">
								<colgroup>
									<col style="width:50px" />
									<col style="width:*" />
									<col style="width:120px" />
									<col style="width:80px" />
									<col style="width:80px" />
									<col style="width:100px" />
									<col style="width:90px" />
								</colgroup>
								<thead>
									<tr>
										<th>
										<c:if test="${loginAuthGroupId ne '2016AUTH0000006' }">
										<input type="checkbox" name="checkAll" id="checkAll" value="" class="choice" />
										</c:if>
										</th>
										<th>팀명</th>
										<th>팀장</th>
										<th>팀원 수</th>
										<th>과제제출일</th>
										<th>제출현황</th>
										<th>점수</th>
									</tr>
								</thead>
								<tbody>

							<c:forEach var="result" items="${result}" varStatus="status">
							
								<tr>
									<td>
									<c:if test="${loginAuthGroupId ne '2016AUTH0000006' }">
									<input type="checkbox" name="arrTeamprojectSubmitId" value="${result.teamprojectSubmitId }" <c:if test="${empty result.atchFileId }">disabled="disabled"</c:if> />
									</c:if>
									</td>
									<td class="left"><c:if test='${result.leaderYn eq "Y"}' ><fmt:formatNumber type="number" minIntegerDigits="2"  value="${result.groupId}" />팀</c:if></td>
									<td><c:if test='${result.leaderYn eq "Y"}' ><font color="orange">팀장 </font></c:if>${result.memName }</td>
									<td><c:if test='${result.leaderYn eq "Y"}' >${result.teamMemberCnt }</c:if></td>
									<td>${result.submitDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="javascript:com.downFile('${result.atchFileId}','1');"  class="btn-line-orange">제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											미제출
										</c:if>										
									</td>
									<td>
										<c:choose>
											<c:when test="${!empty result.atchFileId }">											
												<c:if test="${loginAuthGroupId eq '2016AUTH0000006' }">
												${result.evalScore }
												</c:if>
												<c:if test="${loginAuthGroupId ne '2016AUTH0000006' }">
												<input  type="number" min="0" max="9" class="arrEvalScore <c:if test='${result.leaderYn eq "Y"}' >isLeader</c:if> group_id_${result.groupId}" data-group-id="${result.groupId}"  name="arrEvalScore_${result.teamprojectSubmitId }" id="arrEvalScore_${result.teamprojectSubmitId }" style="width:40px; text-align:center;" value="${result.evalScore }" />
												</c:if>
											</c:when>
											<c:otherwise>
												${result.evalScore }
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
						</table>
</form:form>
						<div class="btn-area align-right mt-010">
							<a href="/lu/teamproject/listTeamproject.do" class="gray-1 float-left">&lt; 이전화면</a>
							<c:if test="${loginAuthGroupId ne '2016AUTH0000006' }">
							<a href="javascript:fn_formSave();" class="orange">저장</a>
							</c:if>
						</div><!-- E : btn-area -->
