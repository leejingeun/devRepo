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

	//var cnt1 = 0;
	//var cnt2 = 0;

	$(document).ready(function() {
		loadPage();

		$(document).on("click","input[name=firstCheck]",function(){

			var firstCheckId = this.id;
			var subjch = "choiceArea-"+firstCheckId.split("-")[1];
			//alert(subjch);
			if($(this).is(":checked")){
				$("#"+subjch+" > input[name='subCheck']").each(function(){
				      $(this).attr("disabled",false);
				});
			} else {
				$("#"+subjch+" > input[name='subCheck']").each(function(){
				    $(this).attr("checked",false);
					$(this).attr("disabled",true);
				});
			}
		});


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
		com.datepickerDateFormat('evDivDate');
		//cnt1 = '${resultListCnt1}'; //NCS 요소아이디에 수행준거 Size
		//cnt2 = '${resultListCnt2}'; //NCS 요소아이디에 평가방법 Size
		makeFileAttachment();
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 평가계획 등록 (직무수행능력평가) 처음화면으로 이동 */
	function fn_list( ){
		var reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";

		$("#frmEvalPlan").attr("action", reqUrl);
		$("#frmEvalPlan").submit();
	}

	/* 첨부파일 정보 셋팅 */
	function makeFileAttachment(){
		 var multi_selector = new MultiSelector( document.getElementById( 'egovComFileList' ), 3 );
		 multi_selector.addElement( document.getElementById( 'egovComFileUploader' ) );
	}

	/* 평가계획 등록 */
	function fn_formSave( ){

		if (confirm("저장 하시겠습니까?")) {

			if($("#evDivCd").val() == ""){
				alert("평가계획을 선택하여 주십시오.");
				$("#evDivCd").focus();
				return false;
			}

			if($("#evDivDate").val() == ""){
				alert("평가일자를 선택하여 주십시오.");
				$("#evDivDate").focus();
				return false;
			}

			var reqUrl = "/lu/evalPlan/insertNcsEvalPlan.do";

			$("#frmEvalPlan").attr("enctype", "multipart/form-data" );
			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

	function fn_listFormSat(){
		var firstCheckArr = new Array();
		var firstCheckSplitArr = new Array();
		var subCheckArr = new Array();

		$("#labelItem").html(null);

		$("input[name='firstCheck']:checked").each(function(){
			firstCheckArr.push($(this).val());
			firstCheckSplitArr.push($(this).val().split("-")[1]);

			$("#labelItem").append($(this).val().split("-")[0]).append("<BR>");
			$("#formSave").show();
			$("#listFormSat").hide();
		});

		$("input[name='firstCheck']:checked").each(function(){
			var firstCheckId = this.id;

			var subjch = "choiceArea-"+firstCheckId.split("-")[1];

			var value = "";
			$("#"+subjch+" > input[name='subCheck']:checked").each(function(){
		      	value = $(this).val();

		      	if(value != ''){
		      		subCheckArr.push($(this).val());
		      	}
			 });

			if(value == ''){
				$("#labelItem").html(null);
				$("#formSave").hide();
				$("#listFormSat").show();
				alert("평가계획에 평가방법을 선택해주십시오.!");
				return false;
			}
		});

		if(firstCheckSplitArr == ''){
			$("#labelItem").html(null);
			$("#formSave").hide();
			$("#listFormSat").show();
			alert("평가계획에 선택을 체크해주십시오.!");

			return false;
		}

		$("#firstCheckArr").val(firstCheckArr);
		$("#firstCheckSplitArr").val(firstCheckSplitArr);
		$("#subCheckArr").val(subCheckArr);
	}

	/* 평가계획등록 수정화면 이동 */
	function fn_goUpdate( param1 ){
		if (confirm("등록된 평가계획에 수정화면으로 이동 하시겠습니까?")) {
			$("#evDivCd").val( param1 );

			var reqUrl = "/lu/evalPlan/goUpdateNcsEvalPlan.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

	/* 평가계획등록 삭제 */
	function fn_delt( param1 ){
		if (confirm("등록된 평가계획을 삭제 하시겠습니까?")) {
			$("#evDivCd").val( param1 );

			var reqUrl = "/lu/evalPlan/deleteNcsEvalPlan.do";

			$("#frmEvalPlan").attr("action", reqUrl);
			$("#frmEvalPlan").attr("target", "_self");
			$("#frmEvalPlan").submit();
		}
	}

	/* Tab 클릭시 이벤트 */
	function fn_goTab( param1 ){
		var reqUrl = "";

		if(param1 == '01'){
			reqUrl = "/lu/evalPlan/listNcsEvalPlan.do";
		} else if(param1 == '02'){
			var cnt = '${resultNcsEvalPlanInfoListCnt}';
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

/*
	$("#choice-area_career_job_type input:checked").each(function(){
	      careerType.push($(this).val());
	   });
 */

</script>


<form id="frmEvalPlan" name="frmEvalPlan" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${planEvalVO.yyyy}" />
<input type="hidden" id="term" name="term" value="<c:out value="${fn:replace(planEvalVO.term,'10','')}" escapeXml="false" />" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${planEvalVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${planEvalVO.subClass}" />
<input type="hidden" id="cnt1" name="cnt1" value="${resultListCnt1}" />
<input type="hidden" id="cnt2" name="cnt2" value="${resultListCnt2}" />
<input type="hidden" id="firstCheckArr" name="firstCheckArr" />
<input type="hidden" id="firstCheckSplitArr" name="firstCheckSplitArr" />
<input type="hidden" id="subCheckArr" name="subCheckArr" />
<input type="hidden" id="cnt" name="cnt" value="${resultNcsEvalPlanInfoListCnt}" />

<div id="">
	<h2>직무수행능력평가</h2>

	<dl id="tab-type-2">
		<dt class="tab-name-11 on"><a href="#!"  onclick="javascript:fn_goTab('01'); return false">평가계획 등록</a></dt>
		<dd id="tab-content-11" style="display:block;">

			<table class="type-2 mt-010">
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
							<input type="checkbox" id="firstCheck-${status1.count}" name="firstCheck" value="${result.ncsElemName}-${result.ncsElemId}" class="choice" />
						</td>
						<td rowspan="${result.rowspan}">${result.elemVw}</td>
						<td rowspan="${result.rowspan}">${result.ncsElemName}</td>
						<td class="left">${result.lessonCn}</td>
						<td rowspan="${result.rowspan}" class="left" id="choiceArea-${status1.count}">
							<c:forEach var="result2" items="${resultList2}" varStatus="status2">
								<input type="checkbox" id="subCheck-${status1.count}" name="subCheck" disabled="disabled" value="${result.ncsElemId}-${result2.dtlCd}-${result2.dtlCdName}-${result2.entDataName}" class="choice" />&nbsp;&nbsp;${result2.dtlCd}.${result2.dtlCdName}</br>
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

			<table class="type-2 mt-010">
				<colgroup>
					<col style="width:170px" />
					<col style="width:*" />
					<col style="width:250px" />
				</colgroup>
				<thead>
					<tr>
						<th>평가계획 / 평가일자</th>
						<th>능력단위요소</th>
						<th>평가자료</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<select id="evDivCd" name="evDivCd" onchange="" style="width:120px;">
								<option value="" selected="selected">::선택::</option>
								<c:forEach var="evalPlanCd" items="${evalPlanCode}" varStatus="status">
									<option value="${evalPlanCd.codeId}">${evalPlanCd.codeName}</option>
								</c:forEach>
							</select>
						</td>
						<td rowspan="2" class="left">
							<label id="labelItem"></label>
						</td>
						<td rowspan="2" class="left">
<!-- 							<input type="text" id="fileName-3" style="width:50%;" readonly="readonly">
							<p class="file-find">
								<a href="#@" class="checkfile">찾아보기</a>
								<input type="file" class="file_input_hidden" onchange="javascript: document.getElementById('fileName-3').value = this.value" />
							</p> -->

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
						</td>
					<tr>
						<td>
							<input type="text" id="evDivDate" name="evDivDate" placeholder="(ex)2017.03.01" value="" style="width:85px;" readonly="readonly" />
						</td>
					</tr>
				</tbody>
			</table>

			<div class="btn-area align-right mt-010">
				<span class="float-left">※ 평가계획을 등록할 <font color="blue">"구분"</font> 및 <font color="blue"> "평가방법"</font>을 체크박스에서 선택후 <font color="blue">평가계획 목록 표시 버튼</font>을 클릭한다음 <font color="blue">저장버튼</font>을 클릭하여주십시오.</span>
				<a href="#!" onclick="javascript:fn_list(); return false" class="gray-1">취소</a>
				<a href="#!" onclick="javascript:fn_listFormSat(); return false" class="yellow" id="listFormSat">평가계획 목록 표시</a>
				<a href="#!" onclick="javascript:fn_formSave(); return false" class="orange" id="formSave" style="display:none;">저장</a>
			</div>

			<table class="type-2 mt-040">
				<colgroup>
					<col style="width:130px" />
					<col style="width:*" />
					<col style="width:250px" />
					<col style="width:120px" />
					<col style="width:120px" />
				</colgroup>
				<tr>
					<th>평가계획 / 평가일자</th>
					<th>능력단위요소</th>
					<th>평가자료</th>
					<th>상태</th>
					<th>삭제</th>
				</tr>

				<c:forEach var="result" items="${resultNcsEvalPlanInfoList}" varStatus="status">
				<tr>
					<td>${result.evDivName}<br />${result.evDivDate}(${result.dayOfWeek})</td>
					<td class="left">
						<input type="hidden" id="tmpEvDivCd" name="tmpEvDivCd" value="${result.evDivCd}" />
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
					<td>
						<c:if test="${result.updateCnt eq 0}">
						<%-- <a href="#!"  onclick="javascript:fn_goUpdate('${result.evDivCd}'); return false" class="btn-line-yellow">수정</a> --%>
						<a href="#!"  onclick="javascript:fn_delt('${result.evDivCd}'); return false" class="btn-line-gray">삭제</a>
						</c:if>
					</td>

				</tr>
				</c:forEach>
				<c:if test="${fn:length(resultNcsEvalPlanInfoList) == 0}">
				<tr>
					<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
				</tr>
				</c:if>
			</table>


			<!-- <div class="btn-area align-right mt-010">
				<a href="#!" class="gray-1 float-left">&lt; 이전 교과</a>
				<a href="#!" class="gray-1 float-left">다음 교과 &gt;</a>
			</div> --><!-- E : btn-area -->
		</dd>

		<dt class="tab-name-12"><a href="#!"  onclick="javascript:fn_goTab('02'); return false">1차 평가 등록</a></dt>
		<dt class="tab-name-13"><a href="#!"  onclick="javascript:fn_goTab('03'); return false">개설교과별 조회</a></dt>
		<dt class="tab-name-14"><a href="#!"  onclick="javascript:fn_goTab('04'); return false">학습근로자별 조회</a></dt>
	</dl>

</div><!-- E : content-area -->

</form>



