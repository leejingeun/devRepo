<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

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
		var count = 0;

		count = '${resultTraningChangeReadListCount}';
		$("#count").val(count);

		//변경신청 버튼 활성화
		if($("#count").val() == 0){
			$("#saveBtn").hide();
		}else{
			$("#saveBtn").show();
		}

		//신규 변경신청 목록에 달력 표시
		<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="scriptStatus1">
			com.datepickerDateFormat('changeTraningDate-'+'${scriptStatus1.count}');
		</c:forEach>
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	//첫화면으로 이동
	function fn_goList( ){
		history.back(-2);
	}

	//변경 신청건 저장
	function fn_formSave(){

		if (confirm("변경신청한 입력값대로 변경 하시겠습니까?")) {
			if($("#changeReason").val() == ""){
				$("#changeReason").focus();
				alert("훈련시간표 변경사유는 필수입력사항입니다.");
				return false;
			}

			<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="scriptStatus2">
				if($("#changeTraningDate-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningDate-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 일자가 비어있습니다.");
					return false;
				}
				if($("#changeTraningStHour-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningStHour-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 시작시간이 비어있습니다.");
					return false;
				}
				if($("#changeTraningEdHour-"+${scriptStatus2.count}).val() == ""){
					$("#changeTraningEdHour-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 종료시간이 비어있습니다.");
					return false;
				}
				if($("#changeLeadTime-"+${scriptStatus2.count}).val() == ""){
					$("#changeLeadTime-"+${scriptStatus2.count}).focus();
					alert("신규 변경신청 목록에 변경 후 (신청내용) 소요시간이 비어있습니다.");
					return false;
				}
			</c:forEach>

			var reqUrl = CONTEXT_ROOT + "/lu/traningChange/updateTraningChangeScheduleApplication.do";

			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").submit();
		}
	}

</script>

<!-- 훈련시간표 변경신청등록 관련 항목 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="companyId" name="companyId" value="${traningSchVO.companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningSchVO.traningProcessId}" />
<input type="hidden" id="updateStatusYn" name="updateStatusYn" value="Y" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="yyyy" name="yyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${currProcReadVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${currProcReadVO.subClass}" />

<div id="">
<h2>훈련시간표 변경신청</h2>

	<h4 class="mt-010"><span>${currProcReadVO.subjectName}</span> (${currProcReadVO.subClass}반) ㅣ ${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</h4>

	<table class="type-write mb-040">
		<colgroup>
			<col style="width:150px" />
			<col style="width:*" />
		</colgroup>
		<tr>
				<th>주요교과목 및 내용</th>
				<td class="left">
					${traningChangeReadVO.subjctTitle}
					<input type="hidden" id="weekId" name="weekId" value="${traningChangeReadVO.weekId}" />
				</td>
			</tr>
			<tr>
				<th>변경사유</th>
				<td class="left">
					<c:if test="${traningChangeReadVO.changeReason eq '' || traningChangeReadVO.changeReason eq null}">
						<input type="text" id="changeReason" name="changeReason" maxlength="298" placeholder="(ex)훈련시간표 변경사유 입력" style="width:97%;" />
					</c:if>
					<c:if test="${traningChangeReadVO.changeReason != ''}">
						${traningChangeReadVO.changeReason}
					</c:if>
				</td>
			</tr>
			<tr>
				<th>변경자</th>
				<td class="left">${traningSchVO.sessionMemName}</td>
			</tr>
			<tr>
				<th>변경승인상태</th>
				<td class="bg-highlight">
					<c:if test="${traningChangeReadVO.changeStatus eq '1'}"><font color="blue">승인대기</font></c:if>
					<c:if test="${traningChangeReadVO.changeStatus eq '2'}"><font color="red">승인</font></c:if>
					<c:if test="${traningChangeReadVO.changeStatus eq '3'}"><font color="red">반려</font></c:if>
				</td>
			</tr>
			<c:if test="${traningChangeReadVO.changeStatus eq '3'}">
			<tr>
				<th>반려사유</th>
				<td class="left">${traningChangeReadVO.retunReason}</td>
			</tr>
			</c:if>
	</table>

	<c:if test="${resultTraningChangeReadList[0].dayOfWeekChange eq null}">
	<div class="group-area mt-010">
		<h2>신규 변경신청</h2>
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<%-- <col style="width:*" /> --%>
				<col style="width:110px" />
				<col style="width:105px" />
				<col style="width:40px" />
				<col style="width:145px" />
				<col style="width:60px" />
				<col style="width:40px" />
				<%-- <col style="width:100px" /> --%>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">주차</th>
					<!-- <th rowspan="2">능력단위요소</th> -->
					<th colspan="3">변경 전</th>
					<th colspan="3">변경 후 (신청내용)</th>
					<!-- <th rowspan="2">추가 / 삭제</th> -->
				</tr>
				<tr>
					<th class="border-left">일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
					<th>일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="status">
				<tr>
					<td>${result.weekCnt}</td>
					<%-- <td class="left">${result.ncsElemName}</td> --%>
					<td>${result.traningDate} (${result.dayOfWeek})</td>
					<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
					<td>${result.leadTime}</td>
					<td><input type="text" id="changeTraningDate-${status.count}" name="changeTraningDate-${status.count}" placeholder="(ex)2017.04.01" value="" style="width:85px;" readonly="readonly"/></td>
					<td>
						<select id="changeTraningStHour-${status.count}" name="changeTraningStHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">시작시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}">${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
						<select id="changeTraningEdHour-${status.count}" name="changeTraningEdHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">종료시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}" >${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="number" min="0" max="10" name="changeLeadTime-${status.count}" id="changeLeadTime-${status.count}" class="arrEvalScore" onchange="javascript:com.checkNumber(document.getElementById('changeLeadTime-${status.count}'));" style="width:40px; text-align:center;" />
						<input type="hidden" id="timeId-${status.count}" name="timeId-${status.count}" value="${result.timeId}" />
					</td>
					<!-- <td><a href="#!" class="btn-full-blue">추가</a> <a href="#!" class="btn-full-gray">삭제</a></td> -->
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_formSave" onclick="javascript:fn_formSave();" class="orange" >변경신청</a>
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="gray-1" >취소</a>
		</div>
	</div><!-- E : 훈련일정변경 -->
</c:if>

<c:if test="${resultTraningChangeReadList[0].dayOfWeekChange != null}">
<div class="group-area mt-010">
		<h2>신규 변경신청</h2>
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<%-- <col style="width:*" /> --%>
				<col style="width:110px" />
				<col style="width:105px" />
				<col style="width:40px" />
				<col style="width:145px" />
				<col style="width:60px" />
				<col style="width:40px" />
				<%-- <col style="width:100px" /> --%>
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2">주차</th>
					<!-- <th rowspan="2">능력단위요소</th> -->
					<th colspan="3">변경 전</th>
					<th colspan="3">변경 후 (신청내용)</th>
					<!-- <th rowspan="2">추가 / 삭제</th> -->
				</tr>
				<tr>
					<th class="border-left">일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
					<th>일자</th>
					<th>교육시간</th>
					<th>소요<br />시간</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${resultTraningChangeReadList}" varStatus="status">
				<tr>
					<td>${result.weekCnt}</td>
					<%-- <td class="left">${result.ncsElemName}</td> --%>
					<td>${result.traningDate} (${result.dayOfWeek})</td>
					<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
					<td>${result.leadTime}</td>
					<td>${result.changeTraningDate} (${result.dayOfWeekChange})</td>
					<td>${result.changeTraningStHour}:00 ~ ${result.changeTraningEdHour}:00</td>
					<td>${result.changeLeadTime}</td>
					<!-- <td><a href="#!" class="btn-full-blue">추가</a> <a href="#!" class="btn-full-gray">삭제</a></td> -->
				</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="gray-1" >취소</a>
		</div>
	</div><!-- E : 훈련일정변경 -->
</c:if>

</div><!-- E : content-area -->


</form>


