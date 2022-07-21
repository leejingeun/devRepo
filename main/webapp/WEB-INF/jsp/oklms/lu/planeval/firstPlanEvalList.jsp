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

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>" ></script>
<script type="text/javascript">

	var evDivName = "";
	var memName = "";
	var clickNum = 0;

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
		makeFileAttachment();
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	function fn_search(  ){

	}

	/* 첨부파일 정보 셋팅 */
	function makeFileAttachment(){
		 var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), 3 );
		 multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	}

	/* 1차평가 학습자별 취득점수 및 PASS처리 단건 저장 */
	function fn_formSave( param1 ){

		var insYn = "Y";

			$("td[id='"+param1+"'] > input[name='evalScore']").each(function(index){
				if($(this).val() == ""){
					alert("취득점수를 입력하여 주십시오.");
					insYn = "N";
				} else {
					$("#tmpEvalScore").val( $(this).val() );
				}
			});

			if("Y" == insYn){
				var newLessonCnPassAtArr = new Array();
				$("td[id='"+param1+"'] > select[name='lessonCnPassAt']").each(function(index){
					if($(this).val() == ""){
						alert("수행준거를 선택하여 주십시오.");
						insYn = "N";
					}else{
						newLessonCnPassAtArr.push($(this).val());
					}
				});

				$("#newLessonCnPassAtArr").val( newLessonCnPassAtArr );
			}

			if("Y" == insYn){
				var oldLessonCnPassAtArr = new Array();
				var planEvIdArr = new Array();
				var planEvIdValue = "";
				var oldValue = "";

				$("td[id='"+param1+"'] > input[name='planEvId']").each(function(index){
					planEvIdValue = $(this).val();
					if(planEvIdValue != ""){
						planEvIdArr.push($(this).val());
					}
				});

				$("td[id='"+param1+"'] > input[name='ncsElemId']").each(function(index){
					$("#tmpNcsEmelId").val( $(this).val() );
				});

				$("td[id='"+param1+"'] > input[name='oldEvalScore']").each(function(index){
					$("#tmpOldEvalScore").val( $(this).val() );
				});

				$("td[id='"+param1+"'] > input[name='oldLessonCnPassAt']").each(function(index){
					oldValue = $(this).val();
					if(oldValue != ""){
						oldLessonCnPassAtArr.push($(this).val());
					}
				});

				if(planEvIdValue != ""){
					$("#planEvIdArr").val( planEvIdArr );
				}

				if(oldValue != ""){
					$("#oldLessonCnPassAtArr").val( oldLessonCnPassAtArr );
				}
			}

			if("Y" == insYn){
				/* var reqUrl = "/lu/evalPlan/insertNcsEvalPlanFirst.do";

				$("#frmEvalPlan").attr("action", reqUrl);
				$("#frmEvalPlan").attr("target", "_self");
				$("#frmEvalPlan").submit(); */

				var reqUrl = "/lu/evalPlan/insertNcsEvalPlanFirst.json";
				var param = $("#frmEvalPlan").serializeArray();

				com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){
					if (200 == jqXHR.status ) {

						com.alert( jqXHR.responseJSON.retMsg );

						if( "SUCCESS" == jqXHR.responseJSON.retCd ){
							/* var ncsElemId = "";
							var passAt = "";
							ncsElemId = jqXHR.responseJSON.ncsElemId;
							passAt = jqXHR.responseJSON.passAt;

							alert(ncsElemId);
							alert(passAt);
							$("#failText1-"+ncsElemId).hide();
							$("#failText2-"+ncsElemId).show();
							$("#failText2-"+ncsElemId).html( passAt ); */
						}
					}
				}, {
		    		async : true,
		    		type : "POST",
		    		spinner : true,
					errorCallback : null,
		    		timeout : 30000			// 기본 30초
		    	});
			}
	}

	/* 1차평가 학습자별 취득점수 및 PASS처리 체크된건 일괄저장 */
	function fn_allFormSave( ){

			var firstCheck = "";
			var planEvId = "";
			var evalScore = "";
			var lessonCnPassAt = "";
			var insYn = "Y";
			var tmp = "-";
			var firstCheckArr = new Array();
			var planEvIdArr = new Array();
			var evalScoreArr = new Array();
			var newLessonCnPassAtArr = new Array();

			$("input[name='firstCheck']:checked").each(function(){
				firstCheck = $(this).val();

				$("td[id='"+firstCheck+"'] > input[name='planEvId']").each(function(index){
					planEvId = firstCheck+tmp+$(this).val();
					planEvIdArr.push(planEvId);

					$("td[id='"+firstCheck+"'] > input[name='evalScore']").each(function(index){
						if($(this).val() == ""){
							alert("취득점수를 입력하여 주십시오.");
							insYn = "N";
						} else {
							evalScoreArr.push($(this).val());
						}
					});

				});

				$("td[id='"+firstCheck+"'] > select[name='lessonCnPassAt']").each(function(index){
					if($(this).val() == ""){
						alert("수행준거를 선택하여 주십시오.");
						insYn = "N";
					}else{
						newLessonCnPassAtArr.push($(this).val());
					}
				});

				firstCheckArr.push(firstCheck);
			});

			if(firstCheck == ''){
				alert("일괄저장할 능력단위요소를 선택하여 주십시오.");
				insYn = "N";
			}

			$("#firstCheckArr").val( firstCheckArr );
			$("#evalScoreArr").val( evalScoreArr );
			$("#newLessonCnPassAtArr").val( newLessonCnPassAtArr );
			$("#planEvIdArr").val( planEvIdArr );

			if("Y" == insYn){
				var reqUrl = "/lu/evalPlan/insertNcsEvalPlanFirstAll.json";
				var param = $("#frmEvalPlan").serializeArray();

				com.ajax4confirm( "일괄저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){
					if (200 == jqXHR.status ) {

						com.alert( jqXHR.responseJSON.retMsg );

						if( "SUCCESS" == jqXHR.responseJSON.retCd ){
							/* var ncsElemId = "";
							var passAt = "";
							ncsElemId = jqXHR.responseJSON.ncsElemId;
							passAt = jqXHR.responseJSON.passAt;

							alert(ncsElemId);
							alert(passAt);
							$("#failText1-"+ncsElemId).hide();
							$("#failText2-"+ncsElemId).show();
							$("#failText2-"+ncsElemId).html( passAt ); */
						}
					}
				}, {
		    		async : true,
		    		type : "POST",
		    		spinner : true,
					errorCallback : null,
		    		timeout : 30000			// 기본 30초
		    	});
			}
		}

	function fn_atchFileSave() {
		reqUrl = "/lu/evalPlan/updateNcsEvalPlanFirstAtchFile.do";

		$("#frmEvalPlan").attr("enctype", "multipart/form-data" );
		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").attr("target", "_self");
		$("#frmEvalPlan").submit();
	}

	/* Tab 클릭시 이벤트 */
	function fn_goTab( param1 ){
		var reqUrl = "";

		if(param1 == '01'){
			$("#evDivCd").val( null );
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

	function fn_goMove( param1, param2 ){

		var clickFlag = 'Y';

		if(param1 == '01'){
			//평가계획 링크 함수호출
			$("#evDivCd").val( param2 );
			clickNum = 0;
		} else {
			//학습근로자 링크 함수호출
			$("#memSeq").val( param2 );
			++clickNum;
		}

		if(clickNum == 0){
			alert("학습근로자 성명을 선택하여 주십시오.");
			clickFlag = 'N';
		}

		if(clickFlag == 'Y'){
			var reqUrl = "/lu/evalPlan/listNcsEvalPlanFirst.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}

		/* if($("#evDivCd").val() == "" || $("#memSeq").val() == ""){
			if($("#evDivCd").val() == ""){
				alert("평가계획을 선택하여 주십시오.");
				return false;
			}
			if($("#memSeq").val() == ""){
				alert("학습근로자 성명을 선택하여 주십시오.");
				return false;
			}
		} else {

			var reqUrl = "/lu/evalPlan/listNcsEvalPlanFirst.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		} */
	}

	function fn_searchMember(  ){

		var reqUrl = "/lu/evalPlan/listNcsEvalPlanFirst.do";

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
<input type="hidden" id="evDivCd" name="evDivCd" value="${planEvalVO.evDivCd}" />
<input type="hidden" id="memSeq" name="memSeq" value="${memSeq}" />
<input type="hidden" id="tmpEvalScore" name="tmpEvalScore" />
<input type="hidden" id="tmpOldEvalScore" name="tmpOldEvalScore" />
<input type="hidden" id="tmpNcsEmelId" name="tmpNcsEmelId" />
<input type="hidden" id="evalScoreArr" name="evalScoreArr" />
<input type="hidden" id="planEvIdArr" name="planEvIdArr" />
<input type="hidden" id="newLessonCnPassAtArr" name="newLessonCnPassAtArr" />
<input type="hidden" id="oldLessonCnPassAtArr" name="oldLessonCnPassAtArr" />
<input type="hidden" id="firstCheckArr" name="firstCheckArr" />
<input type="hidden" id="cnt" name="cnt" value="${cnt}" />

<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type-2">
		<dt class="tab-name-11"><a href="#!" onclick="javascript:fn_goTab('01'); return false">평가계획 등록</a></dt>

		<dt class="tab-name-12 on"><a href="#!" onclick="javascript:fn_goTab('02'); return false">1차 평가 등록</a></dt>
		<dd id="tab-content-12" style="display:block;">

			<table class="type-2 mt-010">
				<colgroup>
					<col style="width:150px" />
					<col style="width:420px" />
					<col style="width:*" />
				</colgroup>
				<tr>
					<th>평가계획 / 평가일자</th>
					<th>능력단위요소</th>
					<th>평가자료</th>
				</tr>
				<c:forEach var="result" items="${resultNcsEvalPlanInfoList}" varStatus="status">
				<tr>
					<td>
						<a href="#!" onclick="javascript:fn_goMove('01', '${result.evDivCd}'); return false">
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
<!-- 						<a href="#!" class="text-file">개발중_01.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a><br />
						<a href="#!" class="text-file">개발중_02.hwp</a> <a href="#!" class="btn-del">첨부파일 삭제</a>
						<p class="file-find float-right">
							<a href="#@" class="checkfile">파일찾기</a>
							<input type="file" class="file_input_hidden" onchange="javascript: document.getElementById('fileName-1').value = this.value" />
						</p> -->
						<c:if test="${result.comDetailFileCnt eq 0}">
							<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="view-1">
							<tr>
								<td class="left">
									<input name="file_1" id="egovComFileUploader" type="file" title="첨부파일명 입력"/>
								</td>
							</tr>
							<tr>
								<td>
									<div id="egovComFileList"></div>
								</td>
							</tr>
							</table>
						</c:if>
						<c:if test="${result.comDetailFileCnt > 0}">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="view-2">
							<tr>
								<td>
									<c:import url="/cmm/fms/selectPlanFileInfs.do" charEncoding="utf-8">
										<c:param name="param_atchFileId" value="${result.atchFileId}" />
										<c:param name="param_returnUrl" value="/lu/evalPlan/listNcsEvalPlanFirst.do" />
									</c:import>
								</td>
							</tr>
						</table>
						</c:if>
					</td>
				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultNcsEvalPlanInfoList) == 0}">
				<tr>
					<td colspan="3"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</table>
			<br/>

			<div class="btn-area align-right mt-010">
				<a href="#!" onclick="javascript:fn_atchFileSave(); return false" class="orange">첨부파일저장</a>
			</div><!-- E : btn-area -->

			<div class="search-box-1 mt-040 mb-020">
				<input type="text" id="searchKeyword" name="searchKeyword" placeholder="(ex)이름 입력" value="${planEvalVO.searchKeyword}"/>
				<a href="#!" onclick="javascript:fn_searchMember(); return false">검색</a>
			</div><!-- E : search-box-1 -->

			<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
			<script type="text/javascript" src="/js/oklms/iscroll.js"></script>

			<ul id="learner-wrap" class="mb-010">
				<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
				<li id="wrap">
					<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
					<div id="scroller" style="width:${fn:length(resultMemberList)*128}px;">
						<ul id="thelist" class="name-tab-btn">
							<!-- <li class="current"><a href="#!">김수한무거북이와두루미</a></li> -->
							<c:forEach var="resultMember" items="${resultMemberList}" varStatus="status">
							<li <c:if test="${resultMember.memName eq memName}"> class="current" </c:if>>
								<a href="#!" onclick="javascript:fn_goMove('02','${resultMember.memSeq}'); return false">${resultMember.memName}</a>
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
				<table class="type-2">
					<colgroup>
						<col style="width:40px" />
						<col style="width:180px" />
						<col style="width:*" />
						<col style="width:80px" />
						<col style="width:80px" />
						<col style="width:80px" />
						<col style="width:150px" />
						<col style="width:60px" />
					</colgroup>
					<thead>
						<tr>
							<th rowspan="2">선택</th>
							<th rowspan="2">영역<br />(능력단위요소)</th>
							<th rowspan="2">평가내용 (수행준거)</th>
							<th rowspan="2">취득점수</th>
							<th colspan="2">평가결과</th>
							<th rowspan="2">평가방법</th>
							<th rowspan="2">저장</th>
						</tr>
						<tr>
							<th class="border-left">능력단위요소</th>
							<th>수행준거</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result1" items="${resultRowNumberList}" varStatus="status1">
						<c:if test="${result1.rn eq '1'}">
						<tr>
							<c:forEach var="result2" items="${resultRowSpanList}" varStatus="status2">
							<c:if test="${result1.ncsElemId eq result2.ncsElemId}">
							<td rowspan="${result2.rowspan}">
								<c:set var="rowspan" value="${result2.rowspan}"/>
								<input type="checkbox" id="firstCheck-${status1.count}" name="firstCheck" value="${result1.ncsElemId}" class="choice" <c:if test="${result1.passCnt eq result2.rowspan }">disabled="disabled"</c:if> />
							</td>
							<td rowspan="${result2.rowspan}" class="left">
								<c:forEach var="result3" items="${resultFirstElemIdList}" varStatus="status3">
									<c:if test="${result3.ncsElemId eq result1.ncsElemId}">
										${result3.ncsElemName}
									</c:if>
								</c:forEach>
							</td>
							<td class="left">
								${result1.lessonCn}
							</td>
							<td rowspan="${result2.rowspan}" id="${result1.ncsElemId}">
								<input type="number" min="0" max="100" id="evalScore-${status1.count}" name="evalScore" class="arrEvalScore" value="${result1.evalScore}" <c:if test="${result1.passCnt eq result2.rowspan }">disabled="disabled"</c:if> style="width:35px; text-align:center;" />
								<input type="hidden" id="oldEvalScore" name="oldEvalScore" value="${result1.evalScore}" />
							</td>
							<td rowspan="${result2.rowspan}" id="${result1.ncsElemId}">
								<c:choose>
									<c:when test="${result1.lessonCnPassAt != '' &&  result1.lessonCnPassAt eq 'P'}">
										PASS
										<input type="hidden" id="passAt" name="passAt" value="PASS" />
									</c:when>
									<c:when test="${result1.lessonCnPassAt != '' &&  result1.lessonCnPassAt eq 'F'}">
										FAIL
										<input type="hidden" id="passAt" name="passAt" value="FAIL" />
									</c:when>
									<c:when test="${result1.lessonCnPassAt eq null}">
										FAIL
										<input type="hidden" id="passAt" name="passAt" value="FAIL" />
									</c:when>
								</c:choose>
							</td>
							<td id="${result1.ncsElemId}">
								<select id="lessonCnPassAt-${status1.count}" name="lessonCnPassAt" <c:if test="${result1.passCnt eq result2.rowspan }">disabled="disabled"</c:if> >
									<option value="" selected>::선택::</option>
									<option value="P" <c:if test="${result1.lessonCnPassAt eq 'P' }">selected</c:if>>PASS</option>
									<option value="F" <c:if test="${result1.lessonCnPassAt eq 'F' }">selected</c:if>>FAIL</option>
								</select>
								<input type="hidden" id="planEvId" name="planEvId" value="${result1.planEvId}" />
								<input type="hidden" id="oldLessonCnPassAt" name="oldLessonCnPassAt" value="${result1.lessonCnPassAt}" />
								<input type="hidden" id="ncsElemId" name="ncsElemId" value="${result1.ncsElemId}" />
							</td>
							<td rowspan="${result2.rowspan}" class="left">
								<c:forEach var="result4" items="${resultFirstCtrlSetList}" varStatus="status4">
									<c:if test="${result4.ncsElemId eq result1.ncsElemId}">
										${result4.dtlCd}.${result4.dtlCdName}<br />
									</c:if>
								</c:forEach>
							</td>
							<td rowspan="${result2.rowspan}">
								<c:if test="${result1.passCnt != result2.rowspan }">
									<a href="#!" onclick="javascript:fn_formSave('${result1.ncsElemId}'); return false" class="btn-line-orange">저장</a>
								</c:if>
							</td>
							</c:if>
							</c:forEach>
						</tr>
						</c:if>
						<c:if test="${result1.rn != '1'}">
						<tr>
							<td class="border-left left">${result1.lessonCn}</td>
							<td id="${result1.ncsElemId}">
								<input type="hidden" id="planEvId" name="planEvId" value="${result1.planEvId}" />
								<input type="hidden" id="ncsElemId" name="ncsElemId" value="${result1.ncsElemId}" />
								<input type="hidden" id="oldLessonCnPassAt" name="oldLessonCnPassAt" value="${result1.lessonCnPassAt}" />
								<select id="lessonCnPassAt-${status1.count}" name="lessonCnPassAt" <c:if test="${result1.passCnt eq rowspan }">disabled="disabled"</c:if> >
									<option value="" selected>::선택::</option>
									<option value="P" <c:if test="${result1.lessonCnPassAt eq 'P' }">selected</c:if>>PASS</option>
									<option value="F" <c:if test="${result1.lessonCnPassAt eq 'F' }">selected</c:if>>FAIL</option>
								</select>
							</td>
						</tr>
						</c:if>
						</c:forEach>
						<c:if test="${fn:length(resultRowNumberList) == 0}">
						<tr>
							<td colspan="8"><spring:message code="common.nosarch.nodata.msg" /></td>
						</tr>
						</c:if>
					</tbody>
				</table>

				<div class="btn-area align-right mt-010">
					<!-- <a href="#!" class="gray-1 float-left">&lt; 이전 교과</a>
					<a href="#!" class="gray-1 float-left">다음 교과 &gt;</a> -->
					<a href="#!" onclick="javascript:fn_allFormSave(); return false" class="orange">일괄저장</a>
				</div><!-- E : btn-area -->
			</div><!-- E : 직무수행능력평가_학습근로자별 -->
		</dd>

		<dt class="tab-name-13"><a href="#!" onclick="javascript:fn_goTab('03'); return false">개설교과별 조회</a></dt>
		<dt class="tab-name-14"><a href="#!" onclick="javascript:fn_goTab('04'); return false">학습근로자별 조회</a></dt>
	</dl>

</div><!-- E : content-area -->

</form>



