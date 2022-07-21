<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="targetUrl" value="/lu/member/"/>

<script type="text/javascript">
	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
		loadPage();
	});

	/*====================================================================
		화면 초기화
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}

	/* 각종 버튼에 대한 액션 초기화 */

	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

 	 	var memType = '${MemberReadVO.memType}';
 	 	var topStatusName = '${MemberReadVO.topStatusName}';

 	   	if('반려' == topStatusName){
 		  	$("#display3").show();
 	   	}
	}

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#companyId").val('');
		$("#traningProcessId").val('');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listMemberChangeApplication.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

</script>

<form id="frmMember" name="frmMember" method="post" >

<div id="">
	<h2>담당자 변경내역 조회</h2>

	<table class="type-2">
		<colgroup>
			<col style="width:100px" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:170px" />
		</colgroup>
		<thead>
			<tr>
				<th class="bg-highlight">상태</th>
				<th>기업명</th>
				<th>소재지</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${MemberReadVO.topStatusName}</td>
				<td>${MemberReadVO.topCompanyName}</td>
				<td>${MemberReadVO.topAddress}</td>
				<td>${MemberReadVO.topHrdTraningName}</td>
				<td>${MemberReadVO.topHrdTraningNo}</td>
			</tr>
		</tbody>
	</table>

	<table class="type-write mt-030">
		<colgroup>
			<col style="width:100px" />
			<col style="width:45%" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th>회원ID</th>
				<td>${MemberReadVO.memId}</td>
				<th>성명</th>
				<td>${MemberReadVO.memName}</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<c:if test="${ 'F' eq MemberReadVO.sex }"> 여자 </c:if>
					<c:if test="${ 'M' eq MemberReadVO.sex }"> 남자 </c:if>
				</td>
				<th>생년월일</th>
				<td>${MemberReadVO.memBirth}</td>
			</tr>
			<tr>
				<th>주민번호</th>
				<td colspan="3">${MemberReadVO.memRegiNo1}-<c:if test="${ 'F' eq MemberReadVO.sex }">2</c:if><c:if test="${ 'M' eq MemberReadVO.sex }">1</c:if>******</td>
			</tr>
			<tr>
				<th>핸드폰번호</th>
				<td>${MemberReadVO.hpNo1}-${MemberReadVO.hpNo2}-${MemberReadVO.hpNo3}</td>
				<th>이메일</th>
				<td>${MemberReadVO.email}</td>
			</tr>
			<tr>
				<th>직위</th>
				<td>${MemberReadVO.titleName}</td>
				<th>학력</th>
				<td>${MemberReadVO.scholarshipName}</td>
			</tr>
			<tr>
				<th>관련연구경력</th>
				<td>기관명&nbsp;&nbsp;&nbsp;${MemberReadVO.relatedResearchOrganizationName}</td>
				<th>기간</th>
				<td>${MemberReadVO.relatedResearchYear}년</td>
			</tr>
			<tr>
				<th>동일분야<br />현장경력</th>
				<td>
					<c:if test="${MemberReadVO.sameSiteWorkCd eq '01'}">3년 미만</c:if>
					<c:if test="${MemberReadVO.sameSiteWorkCd eq '02'}">3년 이상</c:if>
					<c:if test="${MemberReadVO.sameSiteWorkCd eq '03'}">5년 이상</c:if> ( ${MemberReadVO.sameSiteWorkYear}년)
				</td>
				<th>근무형태</th>
				<td>
					<c:if test="${MemberReadVO.workingPlace eq '01'}">정규직</c:if>
					<c:if test="${MemberReadVO.workingPlace eq '02'}">비정규직</c:if>
				</td>
			</tr>
			<tr>
				<th>개설교과</th>
				<td>${MemberReadVO.subjectName}</td>
				<th>인력구분</th>
				<td>
					 <c:if test="${MemberReadVO.memType eq 'COT'}">기업현장교사</c:if>
					 <c:if test="${MemberReadVO.memType eq 'CCM'}">HRD전담자</c:if>
				</td>
			</tr>
		</tbody>
	</table>

	<table class="type-write mt-005" style="border-top:1px solid #CCC;">
		<colgroup>
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th rowspan="2">전담인력<br />이력관리</th>
			<th>구분</th>
			<td>
				<c:if test="${MemberReadVO.updtApplicationStatus eq '1'}">기존정보변경</c:if>
				<c:if test="${MemberReadVO.updtApplicationStatus eq '2'}">신규등록</c:if>
				<c:if test="${MemberReadVO.updtApplicationStatus eq '3'}">삭제</c:if>
			</td>
		</tr>
		<tr>
			<th class="border-left">참여일자 입력</th>
			<td>${MemberReadVO.participateStartDate} ~ ${MemberReadVO.participateEndDate}</td>
		</tr>
	</table>

	<div id="display3" style="display:none;">
	<table class="type-write mt-005" style="border-top:1px solid #CCC;">
		<colgroup>
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th>반려사유</th>
			<td>${MemberReadVO.retunReason}</td>
		</tr>
	</table>
	</div>

	<div class="btn-area align-right mt-010">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">목록</a>
	</div>

</div><!-- E : content-area -->

</form>




