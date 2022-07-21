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

		$("#pageIndex").val( param1 );
		//$("#uiGubun").val( 'tableTraningProcessPop' );
		//$("#returnUrl").val( '/lu/popup/searchSubjectNameListPopup' );
		//var reqUrl = "/lu/popup/goPopupSearch.do";

		var reqUrl = "/lu/traning/popupTraningNote.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	function fn_selectClick(companyId, companyName, memName, memId){


		//alert(companyId);

		opener.fn_appendData(companyId,companyName, memName, memId );
		window.close();

		alert("클릭 체크 ");

	}

	//선택 버튼을 클릭시 부모창에 필요항목 셋팅
	function fn_selectInfo(){
		if( opener ){
			var selectInfo = $("input:radio[name='tempSubjectCode']:checked").val();

			if(undefined == selectInfo){
				alert("추가할 개설강좌명을 선택하여주십시오.");
				return false;
			}

			opener.fn_setSubjectNmInfo(selectInfo);
			window.close();
		}
	}

	function fn_closeWin(){
		window.close();
	}

</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmTraning" name="frmTraning" method="post">

<!-- <input type="hidden" id="uiGubun" name="uiGubun" />
<input type="hidden" id="returnUrl" name="returnUrl" /> -->

	<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>컨텐츠등록</h1>

			<dl id="tab-type">
							<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">학습근로자</a></dt>
							<dd id="tab-content-11" style="display:block;">
								<div class="search-box-1 mt-020 mb-020">
									<select id="" onchange="" name="companyId">
									<option value="" >전체</option>
										<c:forEach var="result" items="${listCompany}" varStatus="status">
											<option  <c:if test="${seachVO.companyId eq  result.companyId }"> selected="selected"</c:if> value="${result.companyId}">${result.companyName}</option>
										</c:forEach>
									</select>

									<input type="text" name="memName" value="${seachVO.memName}" style="width:10%"/>
									<a href="javascript:fn_search();">검색</a>
								</div><!-- E : search-box-1 -->

								<div class="group-area mb-040">
									<table class="type-2">
										<colgroup>
											<col style="width:15%" />
											<col style="width:10%" />
											<col style="width:*px" />
										</colgroup>
										<tr>
											<th>기업명</th>
											<th>학번 </th>
											<th>성명</th>
										</tr>

									<c:forEach var="result" items="${resultTraningMamberList}" varStatus="status">
										<tr><!-- selectClick(companyId, companyName, memName, memId) -->
											<td><a href="#" onclick="fn_selectClick('${result.companyId}', '${result.companyName}', '${result.memName}' , '${result.companyName}')">${result.memId}</td>
											<td>${result.memId }</td>
											<td>${result.memName }</td>
										</tr>
									</c:forEach>
										<c:if test="${fn:length(resultTraningMamberList) == 0}">
										<tr>
											<td colspan="3"><spring:message code="common.nodata.msg" /></td>
										</tr>
										</c:if>
									</table>
		<!--
									<div class="page-num mt-015">
										<a href="#" class="page-btn1">처음 페이지</a>
										<a href="#" class="page-btn2">이전 페이지</a>
										<a href="#" class="num">1</a>
										<a href="#" class="num">2</a>
										<a href="#" class="num ing">3</a>
										<a href="#" class="num">4</a>
										<a href="#" class="num">5</a>
										<a href="#" class="num">6</a>
										<a href="#" class="num">7</a>
										<a href="#" class="num">8</a>
										<a href="#" class="num">9</a>
										<a href="#" class="num">10</a>
										<a href="#" class="page-btn3">다음 페이지</a>
										<a href="#" class="page-btn4">마지막 페이지</a>
									</div><!-- E : page-num -->


								<div class="btn-area align-right mt-010">
									<a href="#!" class="gray-1 float-left">닫기</a>
									<a href="#!" class="orange">확인</a>
								</div><!-- E : btn-area -->

								</div><!-- E : 기존 컨텐츠 -->
							</dd>




						</dl>


		</div><!-- E : wrapper -->
</form>


