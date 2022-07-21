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
			fn_search();
		}
	}

	/* 리스트 조회 */
	function fn_search( ){

		var reqUrl = "/lu/weektraning/popupActivityDesc.do";
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();


	}

	//선택 버튼을 클릭시 부모창에 필요항목 셋팅
	function fn_selectInfo(){
		if( opener ){
			var obj = "";
			obj = $("input:radio[name='tempId']:checked").val();

			if(undefined == obj){
				alert("기업체를 선택하여주십시오. ")
				return false;
			}

			opener.fn_setWeekTraningCompanyInfo(obj);

			window.close();
		}
	}

	function fn_closeWin(){
		window.close();
	}

</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmMember" name="frmMember" method="post" target="popSearch">

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
<div id="pop-wrapper" class="min-w650">

	<h1>기업체 검색</h1>

	<div class="search-box-1 mb-010">
		<input type="text" id="companyName" name="companyName" style="width:200px" placeholder="기업을 입력" />
		<a href="#fn_search" onclick="javascript:fn_search(); return false" onkeypress="this.onclick();">조회</a>
	</div><!-- E : search-box-1 -->

	<table class="type-2">
		<colgroup>
			<col width="40px" />
			<col width="100px" />
			<col width="100px" />
			<col width="*" />
			<col width="*" />
		</colgroup>
		<thead>
			<tr>
				<th>선택</th>
				<th>기업체명</th>
				<th>소재지</th>
				<th>신청일</th>
				<th>훈련과정명</th>
			</tr>
		</thead>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
				<td>
					<input type="radio" name="tempId" id="tempId" value="${result.subjectCode}/${result.companyName}/${result.address}/${result.traningStDate}/${result.hrdTraningName}">
				</td>
				<td>${result.companyName}</td>
				<td>${result.address}</td>
				<td>${result.traningStDate}</td>
				<td>${result.hrdTraningName}</td>
			</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
			<tr>
				<td colspan="5"><spring:message code="common.nodata.msg" /></td>
			</tr>
			</c:if>
		</tbody>
	</table><!-- E : 기업체검색 -->

	<ul class="page-num-btn mt-015">
		<li class="page-btn-area">
			<a href="#fn_closeWin" class="yellow close" onclick="javascript:fn_closeWin(); return false" onkeypress="this.onclick();">닫기</a>
			<a href="#fn_selectInfo" class="orange close" onclick="javascript:fn_selectInfo(); return false" onkeypress="this.onclick();">확인</a>
		</li>
	</ul><!-- E : page-num-btn -->

</div><!-- E : wrapper -->
</form>


