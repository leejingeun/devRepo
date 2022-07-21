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

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		var reqUrl = "/lu/popup/goPopupSearchTraingName.do";
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	//선택 버튼을 클릭시 부모창에 필요항목 셋팅
	function fn_selectInfo(){
		if( opener ){
			var obj = ""; 
			obj = $("input:radio[name='tempTraningProcessId']:checked").val();
			
			if(undefined == obj){
				alert("훈련과정관리를 처리할 하나의 기업체를 선택하여주십시오. ")
				return false;
			} 
			
			opener.fn_setTraningPopInfo(obj);
			
			window.close();
		}
	}
	
	function fn_closeWin(){
		window.close();
	}
	
</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmMember" name="frmMember" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="companyId" name="companyId" value="${traningVO.companyId }" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningVO.traningProcessId }" />
	
<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
<div id="pop-wrapper" class="min-w650">

	<h1>훈련과정 검색</h1>

	<div class="search-box-1 mb-010">
		<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="훈련과정명, 훈련과정번호을 입력" />
		<a href="#fn_search" onclick="javascript:fn_search('1'); return false" onkeypress="this.onclick();">조회</a>
	</div><!-- E : search-box-1 -->

	<table class="type-2">
		<colgroup>
			<col width="40px" />
			<col width="*" />
			<col width="180px" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
			</tr>
		</thead>
			<c:forEach var="result" items="${resultTraningProcessSeachList}" varStatus="status">
			<tr>
				<td><input type="radio" name="tempTraningProcessId" id="tempTraningProcessId" value="${result.traningProcessId}||${result.hrdTraningName}||${traningVO.memSeq}"></td>
				<td>${result.hrdTraningName}</td>
				<td>${result.hrdTraningNo}</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultTraningProcessSeachList) == 0}">
			<tr>
				<td colspan="3"><spring:message code="common.nodata.msg" /></td>
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
			<a href="#fn_selectInfo" class="orange close" onclick="javascript:fn_selectInfo(); return false" onkeypress="this.onclick();">확인</a>
		</li>
	</ul><!-- E : page-num-btn -->

</div><!-- E : wrapper -->
</form>
					

	