<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>

<script type="text/javascript">
<!-- 
function fn_excel(){ 
 
	var reqUrl = "/lu/workcert/excelWorkCertDetail.do";
	$("#frmWorkCertListpopup").attr("target", "_self");
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").submit();
} 

//--> 
</script>
		<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>재직증빙서류 제출현황</h1>
			<table class="type-1 mb-010">
				<colgroup>
					<col style="width:120px" />
					<col style="width:190px" />
					<col style="width:120px" />
					<col style="width:*" />
				</colgroup>
				<tbody>
					<tr>
						<th>학년도</th>
						<td>${workCertVO.yyyy } 학년도</td>
						<th>학기</th>
						<td>${workCertVO.term } 학기</td>
					</tr>
				</tbody>
			</table>
<c:set var="total" value="0" />				
<c:set var="ins" value="0" />
<c:set var="rec" value="0" />
<c:set var="doc" value="0" />

 
			<table class="type-2">
				<colgroup>
					<col style="width:40px" />
					<col style="width:*" />
					<col style="width:50px" />
					<col style="width:90px" />
					<col style="width:70px" />
					<col style="width:100px" />
					<col style="width:80px" />
					<col style="width:80px" />
					<col style="width:80px" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>기업명</th>
						<th>학년</th>
						
						<th>학번</th>
						<th>입학년도</th>
						<th>이름</th>
						
						<th>4대보험<br />가입증명서</th>
						<th>원천징수<br />영수증</th>
						<th>보완서류</th>
					</tr>
				</thead>
				<tbody>
										<c:forEach var="workCertDetailList" items="${resultList}" varStatus="status">
									
											<tr>
												<td>${status.count}</td>
												<td>${workCertDetailList.companyName}</td>
												<td>${workCertDetailList.level}</td>
												
												<td>${workCertDetailList.memId}</td>
												
												<td>${workCertDetailList.yearAdmission}</td>
												
												<td>${workCertDetailList.memName}</td>
												
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdRec}" >
													<c:set var="rec" value="${rec + 1}" />
														제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdRec}" >
														미제출
													</c:if>													
												</td>
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdInc}" >
													<c:set var="ins" value="${ins + 1 }" />
														제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdInc}" >
														미제출
													</c:if>
												</td>
												<td>
													<c:if test="${!empty workCertDetailList.atchFileIdDoc}" >
													<c:set var="doc" value="${doc + 1}" />
													제출
													</c:if>
													<c:if test="${empty workCertDetailList.atchFileIdDoc}" >
														미제출
													</c:if>
												</td>
											</tr>										
										
										
										</c:forEach>	
										
										<c:if test="${!empty resultList}" >
											<tr>
												<td colspan="3">계</td>
												<td colspan="3">${fn:length(resultList)} 명</td>
												<td>${rec} 명</td>
												<td>${ins} 명</td>
												<td>${doc} 명</td>
											</tr>
										</c:if>
										<c:if test="${empty resultList}" >
											<tr>
												<td colspan="9"><spring:message code="common.nodata.msg" /></td>												
											</tr>
										</c:if>
					
				</tbody>
			</table>



			<div class="btn-area align-center mt-010 not_print" style="border-top:1px solid #CCC; padding-top:20px;">
				<a href="#!" onclick="javascript:window.print();" class="yellow">프린트</a>
				<a href="#!" onclick="javascript:fn_excel();" class="orange">엑셀 다운로드</a>
				<a href="javascript:self.close();" class="gray-3">닫기</a>
			</div>


		</div><!-- E : wrapper -->
<form name="frmWorkCertListpopup" id="frmWorkCertListpopup" method="post" >
	<input type="hidden" name="yyyy" value="${workCertVO.yyyy}"/>
	<input type="hidden" name="term" value="${workCertVO.term}"/>
</form>			