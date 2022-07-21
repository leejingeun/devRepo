<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">

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
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	function press(event) {
		if (event.keyCode==13) {
			fn_idUseAtCheck();
		}
	}

</script>

<form id="frmPop" name="frmPop" method="post">
<%-- <input type="hidden" name="memSeq" id ="memSeq" value ="${memSeq}"/> --%>

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
<%-- <div id="pop-wrapper" class="min-w650">

	<h1>훈련과정검색</h1>

	<div class="search-box-1 mb-020">
			<input type="text" name="" value="훈련과정명, 훈련과정번호 검색">
				<a href="#!">조회</a>
			</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="10%" />
					<col width="*" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>훈련과정명</th>
						<th>훈련과정번호</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="radio" name="radio" value=""checked></td>
						<td>훈련과정명_01</td>
						<td>ABC20160000000001</td>
					</tr>
				</tbody>
			</table><!-- E : 훈련과정검색 -->

			<div class="btn-area align-center mt-010">
				<a href="javascript:hideLearningpop();" class="orange close">닫기</a>
				<a href="javascript:hideLearningpop();" class="orange close">확인</a>
			</div><!-- E : btn-area -->	
</div> --%><!-- E : wrapper -->

<ul id="student-popup">
	<li class="company-area">
		<h1>기업체 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" name="" value="검색어입력" style="width:200px" />
			<a href="#!">조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
			<colgroup>
				<col width="40px" />
				<col width="170px" />
				<col width="*" />
				<col width="180px" />
			</colgroup>
			<thead>
				<tr>
					<th><input type="checkbox" name="checkbox" value="" class="choice" /></th>
					<th>기업명</th>
					<th>훈련과정명</th>
					<th>훈련기간</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" name="checkbox" value="" class="choice" checked /></td>
					<td>기업명_01</td>
					<td class="left">훈련과정명_01</td>
					<td>2016.03.01 ~ 2020.02.28</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkbox" value="" class="choice" /></td>
					<td>기업명_01</td>
					<td class="left">훈련과정명_01</td>
					<td>2016.03.01 ~ 2020.02.28</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkbox" value="" class="choice" /></td>
					<td>기업명_01</td>
					<td class="left">훈련과정명_01</td>
					<td>2016.03.01 ~ 2020.02.28</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkbox" value="" class="choice" /></td>
					<td>기업명_01</td>
					<td class="left">훈련과정명_01</td>
					<td>2016.03.01 ~ 2020.02.28</td>
				</tr>
				<tr>
					<td><input type="checkbox" name="checkbox" value="" class="choice" /></td>
					<td>기업명_01</td>
					<td class="left">훈련과정명_01</td>
					<td>2016.03.01 ~ 2020.02.28</td>
				</tr>
			</tbody>
		</table><!-- E : 기업체검색 -->


		<div class="btn-area align-center mt-010">
			<a href="javascript:hideCompanypop();" class="orange close">확인</a>
		</div><!-- E : btn-area -->
	</li>
	
	<li class="popup-bg"></li>
</ul><!-- E : student-popup -->
</form>		
		
		