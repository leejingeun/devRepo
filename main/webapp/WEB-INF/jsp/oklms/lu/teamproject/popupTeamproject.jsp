<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css" >
	table.type-2 th:first-child {border-left: 1px solid #DDD;}
	table.type-2 td:first-child {border-left: 1px solid #DDD;}
	table.type-2 th:last-child {border-right: 1px solid #DDD;}
	table.type-2 td:last-child {border-right: 1px solid #DDD;}
</style>
<script type="text/javascript">
<!--

			$(document).ready(function() {
				
				// 팀장만 제출이면, 5-6번 필드 병합작업.
				if(${(teamprojectVO.submitType eq 'T')? 'true':'false'}){
					mergeCommonRows($("#tblGroup"), 6);
					mergeCommonRows($("#tblGroup"), 5);
				}
				
				// 그룹테이블 로드 된 후, 팀명으로 table row merge
				mergeCommonRows($("#tblGroup"), 1);
				
				
			});
			
			function pop_closeWin() {
				self.close();
			}
			/* 엑셀 다운로드 */
			function fn_exclDownload(){
				var reqUrl = "/lu/teamproject/popupTeamprojectExcel.do";
				$("#frmTeamproject").attr("action", reqUrl);
				$("#frmTeamproject").attr("target","_self");
				$("#frmTeamproject").submit();
			}
			function fn_search(param1){
				$("#pageIndex").val( param1 ); 
				var reqUrl = "/lu/teamproject/popupTeamproject.do";
				$("#frmTeamproject").attr("action", reqUrl);
				$("#frmTeamproject").attr("target","_self");
				$("#frmTeamproject").submit();				
			}
			function printTeamproject(){
				if(confirm("출력하시겠습니까?")){
					window.print();				
				}
			}
//-->
</script>

	<body>
<form name="frmTeamproject" id="frmTeamproject" action=""  method="post" >
	<input type="hidden" name="teamprojectId" value="${teamprojectVO.teamprojectId}"/>
	<input type="hidden" name="pageIndex"  id="pageIndex" value="${teamprojectVO.pageIndex}" />
</form>
		<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>팀프로젝트 제출현황</h1>
			<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>



			<table class="type-1 mb-020">
				<colgroup>
					<col style="width:120px" />
					<col style="width:190px" />
					<col style="width:120px" />
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
				</tbody>
			</table>


			<table class="type-2" id="tblGroup">
				<colgroup>
					<col style="width:60px" />
					<col style="width:60px" />
					<col style="width:120px" />
					<col style="width:*" />
					<col style="width:80px" />
					<col style="width:60px" />
				</colgroup>
				<thead>
					<tr>
						<th>팀명</th>
						<th>팀장</th>
						<th>학번</th>
						<th>이름</th>
						<th>제출일</th>
						<th>제출현황</th>
					</tr>
				</thead>
				<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
<%-- 							<input type="hidden" name="arrTeamprojectSubmitId" value="${result.teamprojectSubmitId }"/> --%>
								<tr>
									<td class="left"><fmt:formatNumber type="number" minIntegerDigits="2"  value="${result.groupId}" />팀</td>
									<td><c:if test='${result.leaderYn eq "Y"}' ><font color="orange">O</font></c:if></td>
									<td>${result.memId }</td>
									<td>${result.memName }</td>
									<td>${result.submitDate }</td>
									<td>
										<c:if test="${!empty result.atchFileId }">
											<a href="#" class="btn-line-gray" onclick="javascript:com.downFile('${result.atchFileId}','1');" >제출</a>
										</c:if>
										<c:if test="${empty result.atchFileId }">
											<span class="btn-line-orange">미제출</span>
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
<%--
		<!-- S : page-num (페이징 영역) -->
		<div class="page-num mt-015">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</div>
		<!-- E : page-num (페이징 영역) -->
 --%>

			<div class="btn-area align-center mt-010" style="border-top:1px solid #CCC; padding-top:20px;">
				<a href="#!" onclick="javascript:printTeamproject();" class="yellow">프린트</a>
				<a href="#!" onclick="javascript:fn_exclDownload();" class="orange">엑셀 다운로드</a>
				<a href="javascript:pop_closeWin();" class="gray-3">닫기</a>
			</div>

		</div><!-- E : wrapper -->
	</body>