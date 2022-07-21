<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/discuss/"/>
<c:set var="scrollNum" value="${param.scrollNum}"/>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	var scrollNum = "${scrollNum}";

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

	function fn_goCompnayReport ( param1, param2, param3 ){

		$("#searchSubClass").val( param1 );
		$("#companyId").val( param2 );
		$("#scrollNum").val( param3 );

		var reqUrl = "/lu/evalPlan/listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	function fn_goMemberReport ( param1, param2 ){

		$("#memSeq").val( param1 );
		$("#memId").val( param2 );

		var reqUrl = "/lu/evalPlan/listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

	function fn_searchMember (  ){
		if($("#companyId").val() == ""){
			alert("하나의 기업명_분반 선택후 검색해주세요.");
			return false;
		}

		/* if($("#memSeq").val() == ""){
			alert("하나의 학습근로자 성명 선택후 검색해주세요.");
			return false;
		} */

		var reqUrl = "/lu/evalPlan/listEvalResultMember.do";

		$("#frmEvalResult").attr("action", reqUrl);
		$("#frmEvalResult").attr("target", "_self");
		$("#frmEvalResult").submit();
	}

</script>

<form id="frmEvalResult" name="frmEvalResult" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${planEvalVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${planEvalVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${planEvalVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${planEvalVO.subClass}" />
<input type="hidden" id="companyId" name="companyId" value="${planEvalVO.companyId}" />
<input type="hidden" id="searchSubClass" name="searchSubClass" value="${searchSubClass}" />
<input type="hidden" id="scrollNum" name="scrollNum" />
<input type="hidden" id="memSeq" name="memSeq" />
<input type="hidden" id="memId" name="memId" />

<div id="">
	<h2>평가결과</h2>

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
					<div id="scroller" style="width:${fn:length(listOjtClassName)*128}px;">
						<ul id="thelist" class="name-tab-btn">
							<c:forEach var="result" items="${listOjtClassName}" varStatus="status">
							<li <c:if test="${result.companyId eq planEvalVO.companyId and result.subjectClass eq planEvalVO.subClass}"> class="current" </c:if>>
								<a href="#!" onclick="javascript:fn_goCompnayReport('${result.subjectClass}','${result.companyId }','${status.count }'); return false">${result.subjectClass}반_${result.companyName }</a>
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

<c:if test="${fn:length(resultMemberStdList) != 0}">
			<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
			<script type="text/javascript" src="/js/oklms/iscroll.js"></script>

			<ul id="learner-wrap" class="mt-030 mb-010">
				<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
				<li id="wrap">
					<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
					<div id="scroller" style="width:${fn:length(resultMemberStdList)*128}px;">
						<ul id="thelist" class="name-tab-btn">
							<!-- <li class="current"><a href="#!">김수한무거북이와두루미</a></li> -->
							<c:forEach var="resultMember" items="${resultMemberStdList}" varStatus="status">
							<li <c:if test="${resultMember.memSeq eq memSeq}"> class="current" </c:if>>
								<a href="#!" onclick="javascript:fn_goMemberReport('${resultMember.memSeq}','${resultMember.memId}'); return false">${resultMember.memName}</a>
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
</c:if>

			<div class="group-area name-tab-content">
				<div class="align-center mb-040">
					<c:if test="${memId != ''}">
						<iframe src="http://kut80.kut.ac.kr/ozrpt/NCS/NC0213R.jsp?I_ESTBL_YY=${planEvalVO.yyyy}&I_ESTBL_SEMSTR_CD=${planEvalVO.tmpTerm}&I_COURSE_NO=${planEvalVO.subjectCode}&I_PARTITN_CLAS_SE_CD=${planEvalVO.subClass}&I_STUD_ID=${memId}" width="100%" height="900px"></iframe>
					</c:if>
				</div><!-- E : btn-area -->
			</div>


</div><!-- E : content-area -->

</form>

<script>
	if(scrollNum > 9){
		setTimeout(function() { $("#next").trigger("click"); }, 1000);
	}
</script>