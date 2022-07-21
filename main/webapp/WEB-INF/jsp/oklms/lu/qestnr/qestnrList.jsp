<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>

<c:set var="targetUrl" value="/lu/qestnr/"/>
<script type="text/javascript">

	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보
	var targetUrl = "${targetUrl}";

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

		$(document).on("click","input[name=rowNumber]",function(){
			var val = $(this).val();
			$("#pageSize").val( val );

			var reqUrl = CONTEXT_ROOT + "/lu/qestnr/listQestnr.do";

			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
		});
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/
	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + "/lu/qestnr/listQestnr.do";

		$("#frmQestnr").attr("action", reqUrl);
		$("#frmQestnr").submit();
	}

	/* 설문 상세조회 페이지로 이동 */
	function fn_read( param1, param2){
		$("#qestnrId").val( param1 );
		$("#lessonStdCnt").val( param2 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "getQestnr.do";

		$("#frmQestnr").attr("action", reqUrl);
		$("#frmQestnr").submit();
	}

	/* 설문 삭제 */
	function fn_delt(){
		if (confirm("삭제 하시겠습니까?")) {
			var title = "";
			var progrressStatus = "";
			var qestnrId = "";

			var selectInfo = $("input:radio[name='tmpQestnrId']:checked").val();

			if(undefined == selectInfo){
				alert("삭제할 설문을 선택하여주십시오.");
				return false;
			}

			var arrInfo = selectInfo.split("||");
			qestnrId = arrInfo[0];
			title = arrInfo[1];
			progrressStatus = arrInfo[2];

			if('예정' == progrressStatus){
				$("#qestnrId").val( qestnrId );

				var reqUrl = CONTEXT_ROOT + targetUrl + "deleteQestnr.do";

				$("#frmQestnr").attr("action", reqUrl);
				$("#frmQestnr").submit();
			} else {
				alert("설문 = ["+title+"] ["+progrressStatus+"]중 입니다.");
				return false;
			}
		}
	}

	/* 설문 수정 */
	function fn_updt(param2, param3, param4, param5){
		if (confirm("수정화면으로 이동 하시겠습니까?")) {
			var title = "";
			var progrressStatus = "";
			var qestnrId = "";

			var selectInfo = $("input:radio[name='tmpQestnrId']:checked").val();

			if(undefined == selectInfo){
				alert("수정할 설문을 선택하여주십시오.");
				return false;
			}

			var arrInfo = selectInfo.split("||");
			qestnrId = arrInfo[0];
			title = arrInfo[1];
			progrressStatus = arrInfo[2];

			if('예정' == progrressStatus){
				$("#qestnrId").val( qestnrId );

				var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateQestnr.do";

				$("#frmQestnr").attr("action", reqUrl);
				$("#frmQestnr").submit();
			} else {
				alert("설문 = ["+title+"] ["+progrressStatus+"]중 입니다.");
				return false;
			}
		}
	}

	/* 설문 신규 페이지로 이동 */
	function fn_cret(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertQestnr.do";

		$("#frmQestnr").attr("action", reqUrl);
		$("#frmQestnr").submit();
	}

</script>


<form id="frmQestnr" name="frmQestnr" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="yyyy" name="yyyy" value="${qestnrVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${qestnrVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${qestnrVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${qestnrVO.subClass}" />
<input type="hidden" id="qestnrId" name="qestnrId" />
<input type="hidden" id="lessonStdCnt" name="lessonStdCnt" />

<div id="">
	<h2>설문</h2>
	<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName} (${currProcReadVO.subClass}분반)</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

		<table class="type-1 mb-040">
			<colgroup>
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
				<col style="width:15%" />
				<col style="width:*px" />
			</colgroup>
			<tbody>
			<tr>
				<th>교과형태</th>
			<td>${currProcReadVO.subjectTraningTypeName}</td>
				<th>과정구분</th>
			<td>${currProcReadVO.subjectTypeName}</td>
				<th>학점</th>
				<td>${currProcReadVO.point }학점</td>
			</tr>
			<tr>
				<th>교수</th>
				<td>${currProcReadVO.insNames}</td>
				<th>온라인 교육형태</th>
				<td>${currProcReadVO.onlineTypeName} (성적비율 ${currProcReadVO.gradeRatio}%)</td>
				<th>선수여부</th>
				<td>${currProcReadVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
			</tr>
		</tbody>
</table>

<h2>(${currProcReadVO.subjectName}) 설문 목록</h2>
<div class="search-box-1 mb-020">
	<select id="searchCondition" name="searchCondition" onchange="">
		<option value="" selected="selected">::전체::</option>
		<option value="NAME" <c:if test="${qestnrVO.searchCondition eq 'NAME'}">selected="selected"</c:if>>작성자</option>
		<option value="MEMO" <c:if test="${qestnrVO.searchCondition eq 'MEMO'}">selected="selected"</c:if>>내용</option>
	</select>
	<select id="searchStatus" name="searchStatus" onchange="">
		<option value="" selected="selected">::전체::</option>
		<option value="BEFOR" <c:if test="${qestnrVO.searchStatus eq 'BEFOR'}">selected="selected"</c:if>>예정</option>
		<option value="START" <c:if test="${qestnrVO.searchStatus eq 'START'}">selected="selected"</c:if>>진행</option>
		<option value="END"   <c:if test="${qestnrVO.searchStatus eq 'END'}">selected="selected"</c:if>>종료</option>
	</select>
	<input type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어 입력" value="${qestnrVO.searchKeyword}" />
	<a href="#!" onclick="javascript:fn_search(1); return false" onkeypress="this.onclick();">검색</a>
</div><!-- E : search-box-1 -->

<ul class="page-sum mb-007">
	<li class="float-left"><span>총 </span><span id="totalRow">0</span><span> 건</span> </li>
	<li class="float-right">
		PAGE VIEW : <input type="radio" id="rowNumber" name="rowNumber" value="10" <c:if test="${rowNumber eq 10 || rowNumber eq ''}">checked</c:if>> 10
		<input type="radio" id="rowNumber" name="rowNumber" value="20" <c:if test="${rowNumber eq 20}">checked</c:if>> 20
		<input type="radio" id="rowNumber" name="rowNumber" value="50" <c:if test="${rowNumber eq 50}">checked</c:if>> 50
		Lines
	</li>
</ul>

<table class="type-2">
	<colgroup>
		<col style="width:40px" />
		<col style="width:40px" />
		<col style="width:*" />
		<col style="width:120px" />
		<col style="width:80px" />
		<col style="width:60px" />
		<col style="width:60px" />
	</colgroup>
	<thead>
		<tr>
			<th>선택</th>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>참여자</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td><input type="radio" id="tmpQestnrId" name="tmpQestnrId" value="${result.qestnrId}||${result.qustnrSj}||${result.progrressStatus}"></td>
			<td>${status.count}</td>
			<td class="left"><a href="#fn_read" onclick="javascript:fn_read('${result.qestnrId}','${result.lessonStdCnt}'); return false" class="text">[${currProcReadVO.subjectName}/${currProcReadVO.subClass}분반] ${result.qustnrSj}</a></td>
			<td>${result.memName}</td>
			<td>${result.insertDate}</td>
			<td>${result.answerResultCnt} / ${result.lessonStdCnt}</td>
			<c:choose>
			<c:when test="${result.progrressStatus eq '예정'}">
				<td><span class="btn-line-gray">${result.progrressStatus}</span></td>
			</c:when>
			<c:when test="${result.progrressStatus eq '진행'}">
				<td><span class="btn-line-blue">${result.progrressStatus}</span></td>
			</c:when>
			<c:otherwise>
				<td><span class="btn-line-orange">${result.progrressStatus}</span></td>
			</c:otherwise>
			</c:choose>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="7"><spring:message code="common.nosarch.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E :  list-->

<ul class="page-num-btn mt-015">
	<li class="page-num-area">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
	</li>
	<li class="page-btn-area">
		<a href="#!" onclick="javascript:fn_delt(); return false" class="gray-1">선택 삭제</a>
		<a href="#!" onclick="javascript:fn_updt(); return false" class="yellow">수정</a>
		<a href="#!" onclick="javascript:fn_cret(); return false" class="orange">작성</a>
	</li>
</ul><!-- E : page-num-btn -->

</div><!-- E : content-area -->

</form>



