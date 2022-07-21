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
		$('.arrEvalScore').keyup(function () {
			var checkNumber = $(this).val();
	    	var evalScore=${discussReadVO.evalScore};
	    	if(evalScore < $(this).val()){
	        	alert("배점보다 큰점수를 입력할수 없습니다.");
	        	 $(this).val('');
	    	}
	    });
	});

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	function checkNumber(param1){
	     var numPattern = /([^0-9])/;
	     var numPattern = param1.match(numPattern);
	     if(numPattern != null){
	         alert("숫자만 입력해 주세요!");
	         //check_form.value = "";
	         //check_form.focus();
	         return false;
	     }
	 }

	/* 입력한 학습자별 평가점수를 저장 */
	function fn_updt(){

		if (fn_formValidate() && confirm("입력한 학습자별 평가점수를 저장 하시겠습니까?")) {
			var stdEvalScoreCnt = $("#stdEvalScoreCnt").val();

			if(stdEvalScoreCnt == 0){
				var reqUrl = CONTEXT_ROOT + targetUrl + "insertDiscussStdEvalScore.do";
			}else{
				var reqUrl = CONTEXT_ROOT + targetUrl + "updateDiscussStdEvalScore.do";
			}

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론 목록 페이지로 이동 */
	function fn_list(){
		$("#discussId").val('');
		$("#discussOpinionId").val('');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listDiscuss.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	function fn_formValidate(){

		return true;
	}




</script>

<form id="frmDiscuss" name="frmDiscuss" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="discussOpinionId" name="discussOpinionId" value="${discussVO.discussOpinionId}" />
<input type="hidden" id="stdEvalScoreCnt" name="stdEvalScoreCnt" value="${stdEvalScoreCnt}" />
<input type="hidden" id="tempEevalScore" name="tempEevalScore" value="${discussReadVO.evalScore}" />

<div id="">
<h2>토론</h2>
<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

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

<h2>토론상세조회</h2>
<div class="group-area mb-010">
	<table class="type-write">
		<colgroup>
			<col style="width:130px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>${discussReadVO.title}</td>
			</tr>
			<tr>
				<th>공개일</th>
				<td>${discussReadVO.startDate} ${discussReadVO.startHour}:${discussReadVO.startMin}</td>
			</tr>
			<tr>
				<th>마김일</th>
				<td>${discussReadVO.endDate} ${discussReadVO.endHour}:${discussReadVO.endMin}</td>
			</tr>
			<tr>
				<th>평가</th>
				<td>${discussReadVO.evalYnName}</td>
			</tr>
			<tr>
				<th>배점</th>
				<td>${discussReadVO.evalScore}<c:if test="${discussReadVO.evalYnName != '미평가'}">점</c:if></td>
			</tr>
			<tr>
				<th>점수공개</th>
				<td>${discussReadVO.scoreOpenYnName} (본인에게만 점수가 공개됩니다.) </td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${discussReadVO.content}</td>
			</tr>
			<!-- <tr>
				<th>첨부파일</th>
				<td><a href="#!" class="text-file">과제물파일_01.hwp</a></td>
			</tr> -->
		</tbody>
	</table>

	<div class="group-area mt-020">
	<table class="type-2">
		<colgroup>
			<col style="width:50px" />
			<col style="width:150px" />
			<col style="width:150px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<thead>
			<tr>
				<th rowspan="2">번호</th>
				<th rowspan="2">학번</th>
				<th rowspan="2">이름</th>
				<th colspan="3">활동내역</th>
				<th rowspan="2">점수</th>
			</tr>
			<tr>
				<th class="border-left">의견수</th>
				<th>댓글수</th>
				<th>추천수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultEvalScoreStdList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${result.memId}</td>
				<td>${result.memName}</td>
				<td>${result.opinionCnt}</td>
				<td>${result.commentCnt}</td>
				<td>${result.opinionGoodCnt}</td>
				<td>
					<input type="number" min="0" max="100" class="arrEvalScore"  name="arrEvalScore" id="arrEvalScore" style="width:40px; text-align:center;" value="${result.evalScore }" />
					<input type="hidden" id="arrMemSeq" name="arrMemSeq" value="${result.memSeq}" />
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table><!-- E : 의견 -->

	<div class="btn-area align-right mt-010">
		<c:if test="${discussVO.sessionMemType eq 'PRT'}">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">취소</a>
		<a href="#fn_updt" onclick="javascript:fn_updt(); return false" class="yellow">저장</a>
		</c:if>
		<c:if test="${discussVO.sessionMemType eq 'CDP'}">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1">취소</a>
		</c:if>
	</div><!-- E : btn-area -->
</div><!-- E : 토론상세조회 -->

</div><!-- E : content-area -->
</form>

