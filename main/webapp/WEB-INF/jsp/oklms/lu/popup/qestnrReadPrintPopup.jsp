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

	});

	function pop_closeWin() {
		self.close();
	}

</script>

<form id="frmQestnr" name="frmQestnr" method="post">

<!-- 팝업 사이즈 : 가로 최소 500px 이상 설정 -->
<div id="pop-wrapper" class="min-w500">

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

<h2>설문 결과보기</h2>
	<table class="type-write">
		<colgroup>
			<col style="width:130px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>${readQestnrInfoVO.qustnrSj}</td>
			</tr>
			<tr>
				<th>시작일</th>
				<td>${readQestnrInfoVO.qustnrStartDate}(${readQestnrInfoVO.dayOfWeekStart})</td>
			</tr>
			<tr>
				<th>종료일</th>
				<td>${readQestnrInfoVO.qustnrEndDate}(${readQestnrInfoVO.dayOfWeekEnd})</td>
			</tr>
			<tr>
				<th>참여인원</th>
				<td>${readQestnrInfoVO.answerResultCnt}/${readQestnrInfoVO.lessonStdCnt} 명</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<dl class="poll-result">
						<dt>${readQestnrInfoVO.qustnrPurps}</dt>
						<c:if test="${!empty readQestnrItemVO.qestnCn1}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="1" disabled="disabled"> ${readQestnrItemVO.qestnCn1}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn2}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="2" disabled="disabled"> ${readQestnrItemVO.qestnCn2}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn3}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="3" disabled="disabled"> ${readQestnrItemVO.qestnCn3}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn4}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="4" disabled="disabled"> ${readQestnrItemVO.qestnCn4}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn5}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="5" disabled="disabled"> ${readQestnrItemVO.qestnCn5}</dd></c:if>
						<dd class="pb-005">
						<input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="etc" disabled="disabled"> 기타<br />
						<c:if test="${readQestnrInfoVO.etcAnswerAt eq 'Y' }">
							<input type="text" id="etcAnswerCn" name="etcAnswerCn" maxlength="999" placeholder="(ex)기타의견 입력" style="width:100%;" disabled="disabled" />
						</c:if>
						</dd>
					</dl>
				</td>
			</tr>
			<tr>
				<th>설문결과</th>
				<td>
						<dl class="poll-result">
							<c:if test="${!empty readQestnrItemVO.qestnCn1}">
							<dd class="pb-025">
								<p>${readQestnrItemVO.qestnCn1} [ ${readQestnrItemVO.qestnAnswerSn1}명 ]</p>
								<ul>
									<li class="case-1" style="width:${readQestnrItemVO.qestnAnswerPercent1}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent1}</b> %</i></li>
								</ul>
							</dd>
							</c:if>
							<c:if test="${!empty readQestnrItemVO.qestnCn2}">
							<dd class="pb-025">
								<p>${readQestnrItemVO.qestnCn2} [ ${readQestnrItemVO.qestnAnswerSn2}명 ]</p>
								<ul>
									<li class="case-2" style="width:${readQestnrItemVO.qestnAnswerPercent2}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent2}</b> %</i></li>
								</ul>
							</dd>
							</c:if>
							<c:if test="${!empty readQestnrItemVO.qestnCn3}">
							<dd class="pb-025">
								<p>${readQestnrItemVO.qestnCn3} [ ${readQestnrItemVO.qestnAnswerSn3}명 ]</p>
								<ul>
									<li class="case-3" style="width:${readQestnrItemVO.qestnAnswerPercent3}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent3}</b> %</i></li>
								</ul>
							</dd>
							</c:if>
							<c:if test="${!empty readQestnrItemVO.qestnCn4}">
							<dd class="pb-025">
								<p>${readQestnrItemVO.qestnCn4} [ ${readQestnrItemVO.qestnAnswerSn4}명 ]</p>
								<ul>
									<li class="case-4" style="width:${readQestnrItemVO.qestnAnswerPercent4}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent4}</b> %</i></li>
								</ul>
							</dd>
							</c:if>
							<c:if test="${!empty readQestnrItemVO.qestnCn5}">
							<dd class="pb-025">
								<p>${readQestnrItemVO.qestnCn5} [ ${readQestnrItemVO.qestnAnswerSn5}명 ]</p>
								<ul>
									<li class="case-5" style="width:${readQestnrItemVO.qestnAnswerPercent5}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent5}</b> %</i></li>
								</ul>
							</dd>
							</c:if>
							<dd class="pb-025">
								<p>기타 [ ${readQestnrItemVO.qestnAnswerSn6}명 ]</p>
								<ul>
									<li class="case-9" style="width:${readQestnrItemVO.qestnAnswerPercent6}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent6}</b> %</i></li>
								</ul>
							</dd>
						</dl>
					</td>
			</tr>
		</tbody>
	</table>


	<div class="btn-area align-right mt-010">
		<a href="#!" onclick="javascript:pop_closeWin(); return false" class="gray-3">닫기</a>
		<a href="#" onclick="javascript:window.print(); return false" class="orange">프린트</a> 
	</div>


</div><!-- E : wrapper -->

</form>

