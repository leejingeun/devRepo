<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>

<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<style>
<!--
table.view-1 td	{font:12px nbgM; line-height:16px; color:#777; border-right:0px solid #DADCE5; border-bottom:1px solid #DADCE5; padding:7px 15px; text-align:left}
table.view-2 td	{font:12px nbgM; line-height:16px; color:#777; border-right:0px solid #DADCE5; border-bottom:1px solid #DADCE5; padding:1px 0px; text-align:left}
-->
</style>

<c:set var="scrollNum" value="${param.scrollNum}"/>

<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
<script type="text/javascript" src="/js/oklms/iscroll.js"></script>
<script type="text/javascript">

	var scrollNum = "${scrollNum}";

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
		var applyApprovalYn = '${resultNcsEvalPlanInfoList[0].applyApprovalYn}';
		var status = '${resultNcsEvalPlanInfoList[0].status}';
		var flag = '${flag}';
		makeFileAttachment();

		/* if("Y" == applyApprovalYn && "1" == status && "Y" == flag){
			$("#btn01").show();
		} else {
			$("#btn01").hide();
		} */

	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/
	/* 입력 필드 초기화 */
	function fn_formReset( ){
		$("#retunReason").val(null);
	}

	/* 평가계획 등록 (직무수행능력평가) 처음화면으로 이동 */
	function fn_list( ){
		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	function fn_goCompnayReport ( param1, param2, param3 ){

		$("#searchSubClass").val( param1 );
		$("#companyId").val( param2 );
		$("#scrollNum").val( param3 );

		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	function fn_goMember ( param1, param2, param3 ){

		$("#searchSubClass").val( param1 );
		$("#memSeq").val( param2 );
		$("#scrollNum").val( param3 );

		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	/* Tab 클릭시 이벤트 */
	function fn_goTab( param1 ){
		var reqUrl = "";

		if(param1 == '01'){
			reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";
		} else if(param1 == '02'){

/* 			if($("#companyId").val( ) == '' || $("#subClass").val( ) == ''){
				alert("회사명_분반을 클릭하세요");
				return false;
			} */

			//$("#searchSubClass").val($("#subClass").val());

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

	function fn_goMove( param1 ){

		$("#evDivCd").val( param1 );

		if(param1 != ''){
			var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

	/* 승인 */
	function fn_approvalUpdt(){
		if (confirm("["+'${subjectVO.stdName}'+"]평가계획건에 대해 승인 하시겠습니까?")) {

			$("#changeStatus").val('2');

			var reqUrl = CONTEXT_ROOT + "/lu/evalPlan/updateNcsEvalPlanApproval.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

	/* 반려 */
	function fn_returnUpdt(){

		if (confirm("["+'${subjectVO.stdName}'+"]평가계획건에 대해 반려 하시겠습니까?")) {

			if($("#retunReason").val() == ""){
				alert("반려시 사유를 입력 주세요.");
				$("#retunReason").focus();
				return false;
			}

			$("#changeStatus").val('3');

			var reqUrl = CONTEXT_ROOT + "/lu/evalPlan/updateNcsEvalPlanApproval.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

</script>

<form id="frmEvalPlan" name="frmEvalPlan" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${planEvalVO.yyyy}" />
<input type="hidden" id="term" name="term" value="<c:out value="${fn:replace(planEvalVO.term,'10','')}" escapeXml="false" />" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${planEvalVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${planEvalVO.subClass}" />
<input type="hidden" id="cnt1" name="cnt1" value="${resultListCnt1}" />
<input type="hidden" id="cnt2" name="cnt2" value="${resultListCnt2}" />
<input type="hidden" id="cnt" name="cnt" value="${resultNcsEvalPlanInfoListCnt}" />
<input type="hidden" id="companyId" name="companyId" value="${planEvalVO.companyId}" />
<input type="hidden" id="evDivCd" name="evDivCd" value="${planEvalVO.evDivCd}" />
<input type="hidden" id="searchSubClass" name="searchSubClass" value="${planEvalVO.subClass}" />
<input type="hidden" id="memSeq" name="memSeq" value="${planEvalVO.memSeq}" />
<input type="text" id="test1" name="test1" value="${resultNcsEvalPlanInfoList[0].applyApprovalYn}" />
<input type="text" id="test2" name="test2" value="${resultNcsEvalPlanInfoList[0].status}" />
<input type="hidden" id="changeStatus" name="changeStatus" value="" />
<input type="hidden" id="scrollNum" name="scrollNum" value="" />


<!-- 고숙련과정 -->
<c:if test="${subjectType eq 'HSKILL'}">
	<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type-2">
		<dt class="tab-name-11 on"><a href="#!"  onclick="javascript:fn_goTab('01'); return false">평가계획 확인</a></dt>
		<dd id="tab-content-11" style="display:block;">

		<ul id="learner-wrap" class="mt-030 mb-010">
				<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
				<li id="wrap">
					<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
					<div id="scroller" style="width:${fn:length(listOjtClassName)*128}px;">
						<ul id="thelist" class="name-tab-btn">
							<c:forEach var="result" items="${listOjtClassName}" varStatus="status">
							<li <c:if test="${result.stdSeq eq memSeq}"> class="current" </c:if>>
								<a href="#!" onclick="javascript:fn_goMember('${result.subjectClass}','${result.stdSeq }','${status.count }'); return false">${result.subjectClass}반_${result.stdName }</a>
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

			<h4 class="mb-010"><span>${subjectVO.subjectName}</span> (${subjectVO.subjectClass}반) ㅣ ${subjectVO.yyyy}학년도 ${subjectVO.term}학기</h4>
			<table class="type-1 mt-010">
			<colgroup>
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
			</colgroup>
			<tr>
				<th>교과형태</th>
				<td>${subjectVO.subjectTraningTypeName}</td>
				<th>과정구분</th>
				<td>${subjectVO.subjectTypeName}</td>
				<th>훈련시간</th>
				<td>${subjectVO.traningHour}시간</td>
			</tr>
			<tr>
				<th>학점</th>
				<td>${subjectVO.point}</td>
				<th>기업명</th>
				<td>${subjectVO.companyName}</td>
				<th>학습근로자</th>
				<td>${subjectVO.stdName}</td>
			</tr>
		</table>

		<table class="type-2 mt-010">
				<colgroup>
					<col style="width:150px" />
					<col style="width:420px" />
					<col style="width:*" />
					<col style="width:120px" />
				</colgroup>
				<tr>
					<th>평가계획 / 평가일자</th>
					<th>능력단위요소</th>
					<th>평가자료</th>
					<th>상태</th>
				</tr>
				<c:forEach var="result" items="${resultNcsEvalPlanInfoList}" varStatus="status">
				<tr>
					<td>
						<a href="#!" onclick="javascript:fn_goMove('${result.evDivCd}'); return false">
							<c:if test="${evDivName eq result.evDivName}">
								<font color="#2A91D4">${result.evDivName}</font>
							</c:if>
							<c:if test="${evDivName != result.evDivName}">
								${result.evDivName}
							</c:if>
						</a><br />
						<c:if test="${evDivName eq result.evDivName}">
							<font color="#2A91D4">${result.evDivDate}(${result.dayOfWeek})</font>
						</c:if>
						<c:if test="${evDivName != result.evDivName}">
							${result.evDivDate}(${result.dayOfWeek})
						</c:if>
					</td>
					<td class="left">
						<c:forEach var="result2" items="${resultNcsEvalPlanElemInfoList}" varStatus="status">
							<c:if test="${result.evDivCd eq result2.evDivCd}">
								${result2.ncsElemName}<br />
							</c:if>
						</c:forEach>
					</td>
					<td class="left">
						<!-- <a href="#!" class="text-file">개발중_01.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a><br />
						<a href="#!" class="text-file">개발중_02.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a> -->

						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="view-2">
							<tr>
								<td>
									<c:import url="/cmm/fms/selectPlanFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${result.atchFileId}" />
										<c:param name="param_returnUrl" value="/lu/evalPlan/listNcsEvalPlan.do" />
									</c:import>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<c:if test="${ 'Y' eq result.applyApprovalYn && '1' == result.status}">
							승인대기
						</c:if>
						<c:if test="${ 'Y' eq result.applyApprovalYn && '2' == result.status}">
							승인<br />${result.insertDate}(${result.dayOfWeek})
						</c:if>
						<c:if test="${ 'Y' eq result.applyApprovalYn && '3' == result.status}">
							반려<br />${result.insertDate}(${result.dayOfWeek})
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultNcsEvalPlanInfoList) == 0}">
				<tr>
					<td colspan="4"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</table>

			<div class="btn-area align-right mt-010">
				<span class="float-left">※ 조회할 평가내용(수행준거) 목록 표시를 위해 <font color="blue">"평가계획 목록"</font> 에서 <font color="blue"> "중간고사/기말고사/기타"</font>중 하나를 클릭하면 아래 목록에 수행준거 정보가 표시됩니다.</span>
			</div>

			<div class="group-area name-tab-content mt-040 mb-020">
				<table class="type-2">
				<colgroup>
					<col style="width:50px" />
					<col style="width:50px" />
					<col style="width:180px" />
					<col style="width:*" />
					<col style="width:170px" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>구분</th>
						<th>영역 (능력단위요소)</th>
						<th>평가내용 (수행준거)</th>
						<th>평가방법</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList1}" varStatus="status1">
					<c:if test="${result.rn eq '1'}">
					<tr>
						<td rowspan="${result.rowspan}">
						<input type="checkbox" id="firstCheck-${status1.count}" name="firstCheck" value="${result.ncsElemName}-${result.ncsElemId}" class="choice"  />
						</td>
						<td rowspan="${result.rowspan}">${result.elemVw}</td>
						<td rowspan="${result.rowspan}">${result.ncsElemName}</td>
						<td class="left">${result.lessonCn}</td>
						<td rowspan="${result.rowspan}" class="left" id="choiceArea-${status1.count}">
							<c:forEach var="result2" items="${resultList2}" varStatus="status2">
								<input type="checkbox" id="subCheck-${status1.count}" name="subCheck" class="choice" />&nbsp;&nbsp;${result2.dtlCd}.${result2.dtlCdName}</br>
							</c:forEach>
						</td>
					</tr>
					</c:if>
					<c:if test="${result.rn != '1'}">
					<tr>
						<td class="border-left left">${result.lessonCn}</td>
					</tr>
					</c:if>
					</c:forEach>
					<c:if test="${fn:length(resultList1) == 0}">
					<tr>
						<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>

			</div><!-- E : 직무수행능력평가_학습근로자별 -->


		<div class="btn-area align-right mt-010" id="btn01">
			<a href="#fn_formReset" onclick="javascript:fn_formReset(); return false" class="gray-1 float-left">취소</a>
			<input type="text" id="retunReason" name="retunReason" placeholder="ex)반려시 사유 필수 입력" />
			<a href="#fn_returnUpdt" onclick="javascript:fn_returnUpdt(); return false" class="yellow float-right">반려</a>
			<a href="#fn_approvalUpdt" onclick="javascript:fn_approvalUpdt(); return false" class="orange float-right">승인</a>
		</div>

		</dd>

		<dt class="tab-name-12"><a href="#!"  onclick="javascript:fn_goTab('02'); return false">1차 평가 확인</a></dt>
		<dt class="tab-name-13"><a href="#!"  onclick="javascript:fn_goTab('03'); return false">개설교과별 조회</a></dt>
		<dt class="tab-name-14"><a href="#!"  onclick="javascript:fn_goTab('04'); return false">학습근로자별 조회</a></dt>
	</dl>

</div><!-- E : content-area -->
</c:if>

<!-- 일반과정 -->
<c:if test="${subjectType != 'HSKILL'}">
	<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type-2">
		<dt class="tab-name-11 on"><a href="#!"  onclick="javascript:fn_goTab('01'); return false">평가계획 확인</a></dt>
		<dd id="tab-content-11" style="display:block;">

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

			<h4 class="mb-010"><span>${subjectVO.subjectName}</span> (${subjectVO.subjectClass}반) ㅣ ${subjectVO.yyyy}학년도 ${subjectVO.term}학기</h4>
			<table class="type-1 mt-010">
			<colgroup>
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
			</colgroup>
			<tr>
				<th>교과형태</th>
				<td>${subjectVO.subjectTraningTypeName}</td>
				<th>과정구분</th>
				<td>${subjectVO.subjectTypeName}</td>
				<th>훈련시간</th>
				<td>${subjectVO.traningHour}시간</td>
			</tr>
			<tr>
				<th>학점</th>
				<td>${subjectVO.point}</td>
				<th>기업명</th>
				<td>${subjectVO.companyName}</td>
				<th>기업현장교사</th>
				<td>${subjectVO.tutNames}</td>
			</tr>
		</table>

		<table class="type-2 mt-010">
				<colgroup>
					<col style="width:150px" />
					<col style="width:420px" />
					<col style="width:*" />
					<col style="width:120px" />
				</colgroup>
				<tr>
					<th>평가계획 / 평가일자</th>
					<th>능력단위요소</th>
					<th>평가자료</th>
					<th>상태</th>
				</tr>
				<c:forEach var="result" items="${resultNcsEvalPlanInfoList}" varStatus="status">
				<tr>
					<td>
						<a href="#!" onclick="javascript:fn_goMove('${result.evDivCd}'); return false">
							<c:if test="${evDivName eq result.evDivName}">
								<font color="#2A91D4">${result.evDivName}</font>
							</c:if>
							<c:if test="${evDivName != result.evDivName}">
								${result.evDivName}
							</c:if>
						</a><br />
						<c:if test="${evDivName eq result.evDivName}">
							<font color="#2A91D4">${result.evDivDate}(${result.dayOfWeek})</font>
						</c:if>
						<c:if test="${evDivName != result.evDivName}">
							${result.evDivDate}(${result.dayOfWeek})
						</c:if>
					</td>
					<td class="left">
						<c:forEach var="result2" items="${resultNcsEvalPlanElemInfoList}" varStatus="status">
							<c:if test="${result.evDivCd eq result2.evDivCd}">
								${result2.ncsElemName}<br />
							</c:if>
						</c:forEach>
					</td>
					<td class="left">
						<!-- <a href="#!" class="text-file">개발중_01.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a><br />
						<a href="#!" class="text-file">개발중_02.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a> -->

						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="view-2">
							<tr>
								<td>
									<c:import url="/cmm/fms/selectPlanFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${result.atchFileId}" />
										<c:param name="param_returnUrl" value="/lu/evalPlan/listNcsEvalPlan.do" />
									</c:import>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<c:choose>
						<c:when test="${result.insertDate != null && result.updateDate eq null}">
							신규등록<br />${result.insertDate}(${result.dayOfWeek})
						</c:when>
						<c:otherwise>
							정보변경<br />${result.updateDate}(${result.dayOfWeek})
						</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultNcsEvalPlanInfoList) == 0}">
				<tr>
					<td colspan="4"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</table>

			<div class="btn-area align-right mt-010">
				<span class="float-left">※ 조회할 평가내용(수행준거) 목록 표시를 위해 <font color="blue">"평가계획 목록"</font> 에서 <font color="blue"> "중간고사/기말고사/기타"</font>중 하나를 클릭하면 아래 목록에 수행준거 정보가 표시됩니다.</span>
			</div>

			<div class="group-area name-tab-content mt-040 mb-020">
				<table class="type-2">
				<colgroup>
					<col style="width:50px" />
					<col style="width:50px" />
					<col style="width:180px" />
					<col style="width:*" />
					<col style="width:170px" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>구분</th>
						<th>영역 (능력단위요소)</th>
						<th>평가내용 (수행준거)</th>
						<th>평가방법</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultList1}" varStatus="status1">
					<c:if test="${result.rn eq '1'}">
					<tr>
						<td rowspan="${result.rowspan}">
						<input type="checkbox" id="firstCheck-${status1.count}" name="firstCheck" value="${result.ncsElemName}-${result.ncsElemId}" class="choice"  />
						</td>
						<td rowspan="${result.rowspan}">${result.elemVw}</td>
						<td rowspan="${result.rowspan}">${result.ncsElemName}</td>
						<td class="left">${result.lessonCn}</td>
						<td rowspan="${result.rowspan}" class="left" id="choiceArea-${status1.count}">
							<c:forEach var="result2" items="${resultList2}" varStatus="status2">
								<input type="checkbox" id="subCheck-${status1.count}" name="subCheck" class="choice" />&nbsp;&nbsp;${result2.dtlCd}.${result2.dtlCdName}</br>
							</c:forEach>
						</td>
					</tr>
					</c:if>
					<c:if test="${result.rn != '1'}">
					<tr>
						<td class="border-left left">${result.lessonCn}</td>
					</tr>
					</c:if>
					</c:forEach>
					<c:if test="${fn:length(resultList1) == 0}">
					<tr>
						<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table>

			</div><!-- E : 직무수행능력평가_학습근로자별 -->


		</dd>

		<dt class="tab-name-12"><a href="#!"  onclick="javascript:fn_goTab('02'); return false">1차 평가 확인</a></dt>
		<dt class="tab-name-13"><a href="#!"  onclick="javascript:fn_goTab('03'); return false">개설교과별 조회</a></dt>
		<dt class="tab-name-14"><a href="#!"  onclick="javascript:fn_goTab('04'); return false">학습근로자별 조회</a></dt>
	</dl>

</div><!-- E : content-area -->
</c:if>

</form>


<script>
	if(scrollNum > 9){
		setTimeout(function() { $("#next").trigger("click"); }, 1000);
	}
</script>
