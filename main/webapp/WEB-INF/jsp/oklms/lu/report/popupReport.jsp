<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script type="text/javascript">
<!--
			function pop_closeWin() {
				self.close();
			}
			/* 엑셀 다운로드 */
			function fn_exclDownload(){
				var reqUrl = "/lu/report/getReportExcel.do";
				$("#frmReport").attr("action", reqUrl);
				$("#frmReport").attr("target","_self");
				$("#frmReport").submit();
			}
			function fn_search(param1){
				$("#pageIndex").val( param1 ); 
				var reqUrl = "/lu/report/popupReport.do";
				$("#frmReport").attr("action", reqUrl);
				$("#frmReport").attr("target","_self");
				$("#frmReport").submit();				
			}
			function printReport(){
				if(confirm("출력하시겠습니까?")){
					window.print();				
				}
			}
//-->
</script>

	<body>
<form name="frmReport" id="frmReport" action=""  method="post" >
	<input type="hidden" name="reportId" value="${reportVO.reportId}"/>
	<input type="hidden" name="pageIndex"  id="pageIndex" value="${reportVO.pageIndex}" /> 
</form>
		<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>과제 제출현황</h1>
			<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>



			<table class="type-1 mb-020">
				<colgroup>
					<col style="width:110px" />
					<col style="width:200px" />
					<col style="width:110px" />
					<col style="width:*" />
				</colgroup>
				<tbody>
					<tr>
						<th>제목</th>
						<td colspan="3">${reportVO.reportName}</td>
					</tr>
					<tr>
						<th>주차</th>
						<td>${reportVO.weekCnt} 주차</td>
						<th>과제제출 기간</th>
						<td>${reportVO.submitStartDate} ~ ${reportVO.submitEndDate}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td colspan="3">${reportVO.reportDesc}</td>
					</tr>
				</tbody>
			</table>



			<table class="type-2">
				<colgroup>
					<col style="width:60px" />
					<col style="width:120px" />
					<col style="width:*" />
					<col style="width:80px" />
					<col style="width:60px" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>학번</th>
						<th>이름</th>
						<th>제출일</th>
						<th>제출현황</th>
					</tr>
				</thead>
				<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
							<input type="hidden" name="arrReportSubmitId" value="${result.reportSubmitId }"/>
								<tr>
									<td>${status.count}</td>
									<td>${result.memId }</td>
									<td>${result.memName }</td>
									<td>${result.insertDate }</td>
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
						            <td colspan="5">자료가 없습니다.</td>
						          </tr>
						    </c:if>
		 
				</tbody>
			</table>

		<!-- S : page-num (페이징 영역) -->
		<div class="page-num mt-015">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
		</div>
		<!-- E : page-num (페이징 영역) -->

			<div class="btn-area align-center mt-010" style="border-top:1px solid #CCC; padding-top:20px;">
				<a href="#!" onclick="javascript:printReport();" class="yellow">프린트</a>
				<a href="#!" onclick="javascript:fn_exclDownload();" class="orange">엑셀 다운로드</a>
				<a href="javascript:pop_closeWin();" class="gray-3">닫기</a>
			</div>


		</div><!-- E : wrapper -->
	</body>