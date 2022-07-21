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
		$("#companyId").val('');
		$("#traningProcessId").val('');
		
		var reqUrl = CONTEXT_ROOT + "/lu/traningChange/listTraningChangeScheduleApplication.do";
		
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
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
<input type="hidden" id="changeStatus" name="changeStatus" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="yyyy" name="yyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${currProcReadVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${currProcReadVO.subClass}" />

<input type="hidden" id="searchYyyy" name="searchYyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="searchTerm" name="searchTerm" value="${currProcReadVO.term}" />
<input type="hidden" id="searchSubjectCode" name="searchSubjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="searchSubClass" name="searchSubClass" value="${currProcReadVO.subClass}" />

	<div id="">
	<h2>훈련시간표 변경신청 수정</h2>
	
	<table class="type-1-1">
		<colgroup>
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th>기업명</th>
			<th>소재지</th>
			<th>선정일</th>
			<th>기업고용보험관리번호</th>
			<th>훈련과정명</th>
		</tr>
		<c:forEach var="result" items="${resultCompanyTraningProcessList}" varStatus="status">
		<tr>
			<td>${result.companyName}</td>
			<td>${result.address}</td>
			<td>${result.choiceDay}</td>
			<td>${result.employInsManageNo}</td>
			<td>${result.hrdTraningName}</td>
		</tr>
		</c:forEach>
	</table>
	
	<h4 class="mt-020"><span>${currProcReadVO.subjectName}</span> (${currProcReadVO.subClass}반) ㅣ ${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</h4>

	<div class="group-area mt-010">
		<table class="type-write">
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
					<input type="text" id="changeReason" name="changeReason" maxlength="298" placeholder="(ex)훈련시간표 변경사유 입력" value="${traningChangeReadVO.changeReason}" style="width:97%;" />
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
					<c:if test="${traningChangeReadVO.changeStatus eq '3'}"><font color="gray">반려</font></c:if>
				</td>
			</tr>
		</table>

<!-- 훈련변경승인태가 1:승인대기상태일떼 -->
<c:if test="${traningChangeReadVO.changeStatus eq '1'}">
	<div class="group-area mt-010">
		<h2>변경신청 수정</h2>
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
					<td>
						${result.leadTime}
						
						<input type="hidden" id="traningDate-${status.count}" name="traningDate-${status.count}" value="${result.traningDate}" />
						<input type="hidden" id="traningStHour-${status.count}" name="traningStHour-${status.count}" value="${result.traningStHour}" />
						<input type="hidden" id="traningStMin-${status.count}" name="traningStMin-${status.count}" value="${result.traningStMin}" />
						<input type="hidden" id="traningEdHour-${status.count}" name="traningEdHour-${status.count}" value="${result.traningEdHour}" />
						<input type="hidden" id="traningEdMin-${status.count}" name="traningEdMin-${status.count}" value="${result.traningEdMin}" />
						<input type="hidden" id="leadTime-${status.count}" name="leadTime-${status.count}" value="${result.leadTime}" />
					</td>
					<td><input type="text" id="changeTraningDate-${status.count}" name="changeTraningDate-${status.count}" placeholder="(ex)2017.04.01" value="${result.changeTraningDate}" style="width:85px;" readonly="readonly"/></td>
					<td>
						<select id="changeTraningStHour-${status.count}" name="changeTraningStHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">시작시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}" ${timeHourCd.codeId == result.changeTraningStHour ? 'selected' : '' }>${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
						<select id="changeTraningEdHour-${status.count}" name="changeTraningEdHour-${status.count}" onchange="" style="margin-bottom:3px;">
							<option value="" selected="selected">종료시간</option>
							<c:forEach var="timeHourCd" items="${timeHourCode}" varStatus="statusCode">
								<option value="${timeHourCd.codeId}" ${timeHourCd.codeId == result.changeTraningEdHour ? 'selected' : '' }>${timeHourCd.codeName}:00분</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="number" min="0" max="10" name="changeLeadTime-${status.count}" id="changeLeadTime-${status.count}" value="${result.changeLeadTime}" class="arrEvalScore" onchange="javascript:com.checkNumber(document.getElementById('changeLeadTime-${status.count}'));" style="width:40px; text-align:center;" />
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
	</div>
	
</div><!-- E : content-area -->


</form>
					

	