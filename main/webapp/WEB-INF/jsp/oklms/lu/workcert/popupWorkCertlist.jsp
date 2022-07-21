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
 
	var reqUrl = "/lu/workcert/excelWorkCert.do";
	$("#frmWorkCertListpopup").attr("target", "_self");
	$("#frmWorkCertListpopup").attr("action", reqUrl);
	$("#frmWorkCertListpopup").submit();
} 

//--> 
</script>
		<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>재직증빙서류 제출현황 (학기별)</h1>
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



			<table class="type-2">
				<colgroup>
					<col style="width:50px" />
					<col style="width:70px" />
					<col style="width:115px" />
					<col style="width:*" />
					<col style="width:115px" />
					<col style="width:100px" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>입학년도</th>
						<th>학습근로자 인원</th>
						<th>4대보험 가입증명서</th>
						<th>원천징수 영수증</th>
						<th>보완서류</th>
					</tr>
				</thead>
				<tbody>
<c:set var="total" value="0" />				
<c:set var="ins" value="0" />
<c:set var="rec" value="0" />
<c:set var="doc" value="0" />
				<c:forEach var="workCertList" items="${resultList}" varStatus="status">
<c:set var="total" value="${total + workCertList.memberTot}" />				
<c:set var="ins" value="${ins + workCertList.insTot }" />
<c:set var="rec" value="${rec + workCertList.recTot}" />
<c:set var="doc" value="${doc + workCertList.docTot}" />				
					<tr>
						<td>${status.count}</td>
						<td>${workCertList.yyyy }</td>
						<td>${workCertList.memberTot } 명</td>
						<td>${workCertList.insTot } 명</td>
						<td>${workCertList.recTot } 명</td>
						<td>${workCertList.docTot } 명</td>
					</tr>
				</c:forEach>
 
					<tr>
						<td colspan="2">계</td>
						<td>${total} 명</td>
						<td>${ins} 명</td>
						<td>${rec} 명</td>
						<td>${doc} 명</td>
					</tr>
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