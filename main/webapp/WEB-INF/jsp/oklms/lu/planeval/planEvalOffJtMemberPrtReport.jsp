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

	function fn_goMemberReport ( param1 ){

		$("#memSeq").val( param1 );

		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	function fn_searchMember (  ){

		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

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
<input type="hidden" id="tabPageGubun" name="tabPageGubun" value="02" />
<input type="hidden" id="memSeq" name="memSeq" />
<input type="hidden" id="memId" name="memId" />
<input type="hidden" id="memName" name="memName" />

<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type">
		<dt class="tab-name-11"><a href="#!" onclick="javascript:fn_showTabbtn('01'); return false">개설교과별</a></dt>
		<dd id="tab-content-11"></dd><!-- E : 평가결과조회 -->

		<dt class="tab-name-12 on"><a href="#!" onclick="javascript:fn_showTabbtn('02'); return false">학습근로자별</a></dt>
		<dd id="tab-content-12" style="display:block;">
		<div class="search-box-1 mt-020">
			<input type="text" id="searchKeyword" name="searchKeyword" placeholder="(ex)이름 입력" value="${planEvalVO.searchKeyword}"/>
			<a href="#!" onclick="javascript:fn_searchMember(); return false">검색</a>
		</div><!-- E : search-box-1 -->

			<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
			<script type="text/javascript" src="/js/oklms/iscroll.js"></script>

			<ul id="learner-wrap" class="mt-030 mb-010">
				<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
				<li id="wrap">
					<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
					<div id="scroller" style="width:${fn:length(resultMemberList)*128}px;">
						<ul id="thelist" class="name-tab-btn">
							<!-- <li class="current"><a href="#!">김수한무거북이와두루미</a></li> -->
							<c:forEach var="resultMember" items="${resultMemberList}" varStatus="status">
							<li <c:if test="${resultMember.memName eq memName}"> class="current" </c:if>>
								<a href="#!" onclick="javascript:fn_goMemberReport('${resultMember.memSeq}'); return false">${resultMember.memName}</a>
							</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li id="next" onclick="myScroll.scrollToPage('next', 0);return false">next</li>

				<script type="text/javascript">
					var myScroll;

					function loaded() {
						myScroll = new iScroll('wrap', {
							snap: true,
							momentum: false,
							hScrollbar: false,
						});
					}

					document.addEventListener('DOMContentLoaded', loaded, false);
				</script>
			</ul><!-- E : learner-wrap -->


			<div class="group-area name-tab-content">
				<div class="align-center mb-040">
					<c:if test="${memId != ''}">
						<iframe src="http://kut80.kut.ac.kr/ozrpt/NCS/NC0213R.jsp?I_ESTBL_YY=${planEvalVO.yyyy}&I_ESTBL_SEMSTR_CD=${planEvalVO.tmpTerm}&I_COURSE_NO=${planEvalVO.subjectCode}&I_PARTITN_CLAS_SE_CD=${planEvalVO.subClass}&I_STUD_ID=${memId}" width="100%" height="900px"></iframe>
					</c:if>
				</div><!-- E : btn-area -->
			</div>

		</dd><!-- E : 평가결과입력 -->
	</dl>

</div><!-- E : content-area -->

</form>

