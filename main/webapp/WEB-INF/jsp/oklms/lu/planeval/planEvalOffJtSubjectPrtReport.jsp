<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
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
	//탭버튼 클릭시
	function fn_showTabbtn( param1 ){

		$("#tabPageGubun").val(param1);

		var reqUrl = CONTEXT_ROOT + "/lu/evalPlan/listNcsEvalPlan.do";

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
<input type="hidden" id="tabPageGubun" name="tabPageGubun" />

<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type">
		<dt class="tab-name-11 on"><a href="#!" onclick="javascript:fn_showTabbtn('01'); return false">개설교과별</a></dt>
		<dd id="tab-content-11" style="display:block;">
			<iframe src="http://kut80.kut.ac.kr/ozrpt/NCS/NC0215R.jsp?I_ESTBL_YY=${planEvalVO.yyyy}&I_ESTBL_SEMSTR_CD=${planEvalVO.tmpTerm}&I_COURSE_NO=${planEvalVO.subjectCode}&I_PARTITN_CLAS_SE_CD=${planEvalVO.subClass}" width="100%" height="900px"></iframe>
		</dd><!-- E : 평가결과조회 -->

		<dt class="tab-name-12"><a href="#!" onclick="javascript:fn_showTabbtn('02'); return false">학습근로자별</a></dt>
		<dd id="tab-content-12"></dd>
	</dl>

</div><!-- E : content-area -->

</form>

