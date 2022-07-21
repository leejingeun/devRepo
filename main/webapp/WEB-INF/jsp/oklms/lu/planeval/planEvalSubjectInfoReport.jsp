<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/discuss/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {

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
	/* 토론의견 목록 검색어 엔터 이벤트 */
	function press(event) {
		if (event.keyCode==13) {
		}
	}

	/* Tab 클릭시 이벤트 */
	function fn_goTab( param1 ){
		var reqUrl = "";

		if(param1 == '01'){
			reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";
		} else if(param1 == '02'){
			var cnt = '${cnt}';
			if(cnt == '0'){
				alert("평가계획을 등록후 1차 평가등록 탭을 이동할수 있습니다.");
				return false;
			}

			reqUrl = "/lu/evalPlan/listNcsEvalPlanFirst.do";
		} else if(param1 == '03'){
			reqUrl = "/lu/evalPlan/listNcsEvalPlanSubject.do";
		} else {
			reqUrl = "/lu/evalPlan/listNcsEvalPlanMember.do";
		}

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

</script>

<form id="frmEvalPlan" name="frmEvalPlan" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${planEvalVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${planEvalVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${planEvalVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${planEvalVO.subClass}" />
<input type="hidden" id="cnt" name="cnt" value="${cnt}" />

<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type-2">
		<dt class="tab-name-11"><a href="#!"  onclick="javascript:fn_goTab('01'); return false">평가계획 등록</a></dt>
		<dt class="tab-name-12"><a href="#!" onclick="javascript:fn_goTab('02'); return false">1차 평가 등록</a></dt>

		<dt class="tab-name-13 on"><a href="#!" onclick="javascript:fn_goTab('03'); return false">개설교과별 결과조회</a></dt>
		<dd id="tab-content-13" style="display:block;">
			<iframe src="http://kut80.kut.ac.kr/ozrpt/NCS/NC0215R.jsp?I_ESTBL_YY=${planEvalVO.yyyy}&I_ESTBL_SEMSTR_CD=${planEvalVO.tmpTerm}&I_COURSE_NO=${planEvalVO.subjectCode}&I_PARTITN_CLAS_SE_CD=${planEvalVO.subClass}" width="100%" height="900px"></iframe>
		</dd>
		<dt class="tab-name-14"><a href="#!" onclick="javascript:fn_goTab('04'); return false">학습근로자별 조회</a></dt>
	</dl>
</div><!-- E : content-area -->

</form>

