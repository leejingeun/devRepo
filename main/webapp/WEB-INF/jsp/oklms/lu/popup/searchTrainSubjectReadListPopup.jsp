<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">

	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보

	$(document).ready(function() {

		if ('' == pageSize) {
			pageSize = 10;
		}
		if ('' == totalCount) {
			totalCount = 0;
		}
		if ('' == pageIndex) {
			pageIndex = 1;
		}

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
		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	function fn_closeWin(){
		window.close();
	}

</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmMember" name="frmMember" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="companyId" name="companyId" />
<input type="hidden" id="traningProcessId" name="traningProcessId"/>
<input type="hidden" id="memSeq" name="memSeq" />

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>(${memName}) 기업현장 교사에 메핑된 교과목 목록</h1>

			<table class="type-2">
				<colgroup>
					<col width="140px" />
					<col width="70px" />
					<col width="50px" />
					<col width="*" />
					<col width="60px" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>학과명</th>
						<th>학년도</th>
						<th>학기</th>
						<th>개설강좌명</th>
						<th>분반</th>
						<th>훈련과정명</th>
					</tr>
				</thead>
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td>${result.departmentName}</td>
						<td>${result.yyyy}</td>
						<td>${result.term}</td>
						<td class="left">${result.subjectName}</td>
						<td>${result.subClass}</td>
						<td>${result.hrdTraningName}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 기업체검색 -->

			<ul class="page-num-btn mt-015">
				<li class="page-num-area">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
				</li>
				<li class="page-btn-area">
					<a href="#fn_closeWin" class="yellow close" onclick="javascript:fn_closeWin(); return false" onkeypress="this.onclick();">닫기</a>
				</li>
			</ul><!-- E : page-num-btn -->

		</div><!-- E : wrapper -->
</form>


