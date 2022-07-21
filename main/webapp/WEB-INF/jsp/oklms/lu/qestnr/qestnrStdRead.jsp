<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/qestnr/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
		$(".requare").css("color", "red"); //필수값 * 빨간색 표시 css
		
		$(document).on("click","input[name=qestnAnswerSn]",function(){
			if($("#etcAnswerAt").val() == "Y"){
				var val = $(this).val();
				if('etc' == val){
					$("#etcAnswerCn").attr("disabled",false);
					$("#etcAnswerCn").focus();
				}else{
					$("#etcAnswerCn").val(null);
					$("#etcAnswerCn").attr("disabled",true);
				}	
			}
		});
		
	});

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* 설문 참여하기 */
	function fn_save(){

		if (confirm("저장 하시겠습니까?")) {

			var selectInfo = $("input:radio[name='qestnAnswerSn']:checked").val();
			
			if(selectInfo == ""){
				alert("설문보기를 선택해주세요.");
				return false;
			}

			if(selectInfo == 'etc' && $("#etcAnswerAt").val() == "Y"){
				if($("#etcAnswerCn").val() == ""){
					alert("기타을 입력 주세요.");
					$("#etcAnswerCn").focus();
					return false;
				}
			}

			var reqUrl = CONTEXT_ROOT + targetUrl + "insertQestnrAnswerResult.do";

			$("#frmQestnr").attr("method", "post" );
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
		}
	}

	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#qestnrId").val( null );

		var reqUrl = CONTEXT_ROOT + targetUrl + "listQestnr.do";

		$("#frmQestnr").attr("method", "post" );
		$("#frmQestnr").attr("action", reqUrl);
		$("#frmQestnr").submit();
	}

	/* 설문 결과보기 */
	function fn_answerResultView(){
		
		if (confirm("설문응답결과를 보시겠습니까?")) {
			$("#answerResultId").show();	
		}
	}
	
	/* 설문 기타의견보기 */
	function fn_etcAnswerCnView(){
		

		popOpenWindow("", "popSearch", 850, 560);

		var reqUrl = CONTEXT_ROOT + targetUrl + "popupQestnrEtcAnswerCnView.do";

		$("#frmQestnr").attr("target", "popSearch");
		$("#frmQestnr").attr("action", reqUrl);
		$("#frmQestnr").submit();
	}

</script>

<form id="frmQestnr" name="frmQestnr" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${qestnrVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${qestnrVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${qestnrVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${qestnrVO.subClass}" />
<input type="hidden" id="qestnrId" name="qestnrId" value="${qestnrVO.qestnrId}" />
<input type="hidden" id="qustnrQesitmId" name="qustnrQesitmId" value="${readQestnrItemVO.qustnrQesitmId}" />
<input type="hidden" id="joinAt" name="joinAt" value="${qestnrVO.joinAt}" />
<input type="hidden" id="etcAnswerAt" name="etcAnswerAt" value="${readQestnrInfoVO.etcAnswerAt}" />

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

<h2>설문 참여</h2>
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
				<th><span class="requare">*</span>내용</th>
				<td>
					<dl class="poll-result">
						<dt>${readQestnrInfoVO.qustnrPurps}</dt>
						<c:if test="${!empty readQestnrItemVO.qestnCn1}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="1" checked="checked"> ${readQestnrItemVO.qestnCn1}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn2}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="2"> ${readQestnrItemVO.qestnCn2}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn3}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="3"> ${readQestnrItemVO.qestnCn3}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn4}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="4"> ${readQestnrItemVO.qestnCn4}</dd></c:if>
						<c:if test="${!empty readQestnrItemVO.qestnCn5}"><dd class="pb-005"><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="5"> ${readQestnrItemVO.qestnCn5}</dd></c:if>
						<dd class="pb-005">
						<input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="etc"> 기타<br />
						<c:if test="${readQestnrInfoVO.etcAnswerAt eq 'Y' }">
							<input type="text" id="etcAnswerCn" name="etcAnswerCn" maxlength="999" placeholder="(ex)기타의견 입력" style="width:100%;" disabled="disabled" />
						</c:if>
						</dd>
					</dl>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#!" onclick="javascript:fn_list(); return false" class="gray-1">목록</a>
		<a href="#!" onclick="javascript:fn_answerResultView(); return false" class="yellow">결과보기</a>
		<c:if test="${qestnrVO.joinAt eq 'N' }">
			<a href="#!" onclick="javascript:fn_save(); return false" class="orange">참여</a>
		</c:if>
	</div>

	<div id="answerResultId" style="display:none;">
		<h2>설문 결과보기</h2>
		<table class="type-write">
			<colgroup>
				<col style="width:130px" />
				<col style="width:*" />
			</colgroup>
			<tbody>
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
			<a href="#!" onclick="javascript:fn_etcAnswerCnView(); return false" class="gray-2">기타 의견 확인하기</a>
		</div>
      </div>
    
</div><!-- E : content-area -->
</form>

