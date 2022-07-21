<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>

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
		<c:if test="${MemberStdVO.sessionMemType eq 'CCM'}">
			com.datepickerDateFormat('participateStartDate');
	 	    com.datepickerDateFormat('participateEndDate');
 	    </c:if>

		var cnt1 = '${cnt1}';
		var cnt2 = '${cnt2}';

		//담당자 변경신청 열기 버튼 display style
		if(0 == cnt1){
			$("#display1").hide();
		} else {
			$("#display1").show();
		}

		//담당자 변경내역 열기 버튼 display style
		if(0 == cnt2){
			$("#display2").hide();
		} else {
			$("#display2").show();
		}
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 담당자 변경내역 상세조회 페이지로 이동 */
	function fn_read(  ){

		var checkedVal = "";
		checkedVal = $(":input:radio[name=memSeqSelect]:checked").val();

		if(undefined == checkedVal){
			alert("담당자 변경신청내역 목록에서 라디오버튼을 선택하여주십시오.");
			return false
		}

		$("#memSeq").val( checkedVal );

		var reqUrl = CONTEXT_ROOT + targetUrl + "getMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 담당자 변경신청 수정 페이지로 이동 */
	function fn_updt( ){

		var checkedVal = "";
		checkedVal = $(":input:radio[name=memSeqNewSelect]:checked").val();

		if(undefined == checkedVal){
			alert("담당자 변경신청 목록에서 라디오버튼을 선택하여주십시오.");
			return false
		}

		$("#memSeq").val( checkedVal );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 담당자 변경신청 신규 페이지로 이동 */
	function fn_cret( param1 ){

		//신규작성 인지 기존담당자 변경신청건인지 구분해주는 항목
		$("#cretGubun").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 담당자 변경신청 신규 페이지로 이동 */
	function fn_search(  ){

		var reqUrl = CONTEXT_ROOT + targetUrl + "listMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

</script>


<form id="frmMember" name="frmMember" method="post">
<input type="hidden" name="memSeq" id="memSeq" />
<input type="hidden" name="cretGubun" id="cretGubun" />

<div id="">
	<h2>담당자 변경신청</h2>

	<div class="group-area mb-010">
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<col style="width:*" />
				<col style="width:120px" />
				<col style="width:100px" />
				<col style="width:200px" />
				<col style="width:100px" />
			</colgroup>
			<thead>
				<tr>
					<th>선택</th>
					<th>기업명</th>
					<th>구분</th>
					<th>성명</th>
					<th>주민등록번호</th>
					<th>신청내용</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultChangeAppNewList}" varStatus="status">
				<tr>
					<td>
						<input type="radio" id="memSeqNewSelect" name="memSeqNewSelect" value="${result.memSeq},${result.memType},${result.companyId}" class="choice" />
					</td>
					<td class="left">${result.companyName}</td>
					<td>${result.memTypeName}</td>
					<td>${result.memName}</td>
					<td>
						${result.memBirth}-<c:if test="${result.sex eq 'M'}">1</c:if><c:if test="${result.sex eq 'F'}">2</c:if>******
					</td>
					<td>
						<c:if test="${result.updtApplicationStatus eq '1'}">
							<a href="#!" class="btn-line-orange-50">${result.updtApplicationName}</a>
						</c:if>
						<c:if test="${result.updtApplicationStatus eq '2'}">
							<a href="#!" class="btn-line-blue-50">${result.updtApplicationName}</a>
						</c:if>
						<c:if test="${result.updtApplicationStatus eq '3'}">
							<a href="#!" class="bbtn-line-gray-50-1">${result.updtApplicationName}</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultChangeAppNewList) == 0}">
				<tr>
					<td colspan="6"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_updt" onclick="javascript:fn_updt(); return false" class="gray-1" id="display1">변경신청 열기</a>
			<a href="#fn_cret" onclick="javascript:fn_cret('01'); return false" class="yellow">변경신청</a>
			<a href="#fn_cret" onclick="javascript:fn_cret('02'); return false" class="orange">신규등록</a>
		</div>
	</div><!-- E : 참여기업조회 -->

	<h2>담당자 변경내역</h2>

	<c:if test="${MemberStdVO.sessionMemType eq 'CCM'}">
	<div class="search-box-1 mb-010">
		<select id="authGroupId" name="authGroupId" onchange="">
			<option value="">::전체::</option>
			<option value="COT" ${'COT' == MemberStdVO.authGroupId ? 'selected' : '' }>기업현장교사</option>
			<option value="CCM" ${'CCM' == MemberStdVO.authGroupId ? 'selected' : '' }>HRD전담자</option>
		</select>
		<input type="text" id="participateStartDate" name="participateStartDate" value="${MemberStdVO.participateStartDate}" placeholder="ex)2017.03.01" style="width:65px" readonly="readonly" /> ~
		<input type="text" id="participateEndDate" name="participateEndDate" value="${MemberStdVO.participateEndDate}" placeholder="ex)2017.03.30" style="width:65px" readonly="readonly" />
		<a href="#!" onclick="javascript:fn_search(); return false">검색</a>
	</div><!-- E : search-box-1 -->
	</c:if>

	<table class="type-2">
		<colgroup>
			<col style="width:40px" />
			<col style="width:*" />
			<col style="width:120px" />
			<col style="width:100px" />
			<col style="width:200px" />
			<col style="width:100px" />
			<col style="width:100px" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>기업명</th>
				<th>구분</th>
				<th>성명</th>
				<th>주민등록번호</th>
				<th>변경일자</th>
				<th>신청내용</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultChangeAppList}" varStatus="status">
			<tr>
				<td>
					<input type="radio" id="memSeqSelect" name="memSeqSelect" value="${result.memSeq},${result.memType},${result.companyId}" class="choice" />
				</td>
				<td class="left">${result.companyName}</td>
				<td>${result.memTypeName}</td>
				<td>${result.memName}</td>
				<td>
					${result.memBirth}-<c:if test="${result.sex eq 'M'}">1</c:if><c:if test="${result.sex eq 'F'}">2</c:if>******
				</td>
				<td>${result.updateDate}</td>
				<td>
					<c:if test="${result.status eq '2'}">
						<a href="#!" class="btn-line-blue-50">${result.updtApplicationName}</a>
					</c:if>
					<c:if test="${result.status eq '3'}">
						<a href="#!" class="btn-line-orange-50">${result.updtApplicationName}</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultChangeAppList) == 0}">
			<tr>
				<td colspan="7"><spring:message code="common.nosarch.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#fn_read" onclick="javascript:fn_read(); return false" class="gray-1" id="display2">변경내역 열기</a>
	</div>

</div><!-- E : content-area -->


</form>



