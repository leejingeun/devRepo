<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("br", "<br/>");  //br 태그
      pageContext.setAttribute("cn", "\n"); //Space, Enter
%> 

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
		var scheduleViewYn = '${scheduleViewYn}';
		var subjctTitle = '${subjctTitle}';
		
		if(subjctTitle == '' || subjctTitle == null){
			$("#scheduleViewDisplay1").hide();
		}else{
			$("#scheduleViewDisplay1").show();
		}
		
		if(scheduleViewYn == 'Y'){
			if('${fn:length(resultScheduleViewList)}' == 0){
				$("#scheduleViewDisplay2").hide();
			}else{
				$("#scheduleViewDisplay2").show();
			}
		}else{
			$("#scheduleViewDisplay2").hide();
		}		
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	
	//개설강좌 조회
	function fn_goSearch(){
		
/* 		if($("#companyId").val() == ''){
			alert("기업체 정보가 없습니다. 기업체 검색 버튼을 클릭하여 기업체를 선택하여주십시오.");
			return false;
		} */
		
		if($("#searchYyyy").val() == ''){
			alert("학년도를 선택하여주십시오.");
			$("#searchYyyy").focus();
			return false;
		}
		
		if($("#searchTerm").val() == ''){
			alert("학기를 선택하여주십시오.");
			$("#searchTerm").focus();
			return false;
		}
		
		if($("#searchSubClass").val() == ''){
			alert("분반을 선택하여주십시오.");
			$("#searchSubClass").focus();
			return false;
		}
		
		if($("#searchSubjectCode").val() == ''){
			alert("개설강좌명을 선택하여주십시오.");
			$("#searchSubjectCode").focus();
			return false;
		}
		
		$("#scheduleViewYn").val('N');
		
		var reqUrl = CONTEXT_ROOT + "/lu/traning/getTraningSchedule.do";
		
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}
	
	//첫화면으로 이동
	function fn_goList( ){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		
		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";
		
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}
	
	/* 훈련시간표보기 목록 조회 */
	function fn_scheduleList(){
		$("#scheduleViewYn").val('Y');
		
		var reqUrl = CONTEXT_ROOT + "/lu/traning/getTraningSchedule.do";
		
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}
	
	function fn_saveScheduleList(){
		if(confirm("임시등록된 훈련시간표를 실제 주차정보에 등록 하시겠습니까?")){
			var reqUrl = CONTEXT_ROOT + "/lu/traning/insertTraningSchedule.do";
			
			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").attr("target", "_self");
			$("#frmTraning").submit();	
		}
	}
	
	function fn_searchOnChange(){
		if($("#subjectCode").val() != $("#searchSubjectCode").val()){
			$("#scheduleViewDisplay1").hide();
			$("#scheduleViewDisplay2").hide();
		}else{
			$("#scheduleViewDisplay1").show();
			$("#scheduleViewDisplay2").show();
		}
	}
	
function fn_delete(flag){
		
		if( flag == "" ){
			alert("등록 된 훈련시간표가 없습니다.");
			return;
		} else {
			// flag : 1 승인대기 , 2 승인 3 반려
			if( flag == "2" || flag == "3" ){
				alert("반려나 승인중인 훈련시간표는 삭제가 불가능합니다.");
				return;
			} else {
				if(confirm("승인대기중인 시간표를 삭제하시겠습니까?")){
					var reqUrl = CONTEXT_ROOT + "/lu/traning/deleteTraningSchedule.do";

					$("#frmTraning").attr("action", reqUrl);
					$("#frmTraning").attr("target", "_self");
					$("#frmTraning").submit(); 
				}
			}
		}
}
	
</script>

<!-- 훈련시간표등록 관련 항목 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="companyId" name="companyId" value="${companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningProcessId}" />
<input type="hidden" id="yyyy" name="yyyy" value="${searchYyyy}" />
<input type="hidden" id="term" name="term" value="${searchTerm}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${searchSubjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${searchSubClass}" />
<input type="hidden" id="scheduleViewYn" name="scheduleViewYn" />

	<div id="">
	<h2>훈련시간표 상세</h2>
	
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
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td>${result.companyName}</td>
			<td>${result.address}</td>
			<td>${result.choiceDay}</td>
			<td>${result.employInsManageNo}</td>
			<td>${result.hrdTraningName}</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
		</tr>
		</c:if>
	</table>
	
	<div class="search-box-1 mt-040 ">
		학년도:
		<select id="searchYyyy" name="searchYyyy" onchange="">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="yearSubjCd" items="${yearSubjCode}" varStatus="status">
				<option value="${yearSubjCd.codeId}" ${yearSubjCd.codeId == searchYyyy ? 'selected="selected"' : '' }>${yearSubjCd.codeName} 학년도</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		학기:
		<select id="searchTerm" name="searchTerm" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="termSubjCd" items="${termSubjCode}" varStatus="status">
				<option value="${termSubjCd.codeId}" ${termSubjCd.codeId == searchTerm ? 'selected="selected"' : '' }>${termSubjCd.codeName} 학기</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		분반:
		<select id="searchSubClass" name="searchSubClass" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="classSubjCd" items="${classSubjCode}" varStatus="status">
				<option value="${classSubjCd.codeId}" ${classSubjCd.codeId == searchSubClass ? 'selected="selected"' : '' }>${classSubjCd.codeName} 분반</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		개설강좌명:
		<select id="searchSubjectCode" name="searchSubjectCode" onchange="fn_searchOnChange();">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="subjNameSubjCd" items="${subjNameSubjCode}" varStatus="status">
				<option value="${subjNameSubjCd.codeId}" ${subjNameSubjCd.codeId == searchSubjectCode ? 'selected="selected"' : '' }>${subjNameSubjCd.codeName}</option>
			</c:forEach>
		</select>
		<a href="#fn_goSearch" onclick="javascript:fn_goSearch(); return false" onkeypress="this.onclick();">조회</a>
	</div><!-- E : search-box-1 -->
	
	<h4 class="mt-020"><span>${currProcReadVO.subjectName}</span> (${subClass}반) ㅣ ${yyyy}학년도 ${term}학기</h4>
	
	<div id="scheduleViewDisplay1" style="display:none;">
	
	<div class="group-area mt-010">
		<table class="type-write">
			<colgroup>
				<col style="width:100px" />
				<col style="width:150px" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th rowspan="3">훈련<br />시간표 등록</th>
				<th>주요교과목 및 내용</th>
				<td>${subjctTitle}</td>
			</tr>
			<tr>
				<th class="border-left">등록할 시간표</th>
				<td class="border-left">
					<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>
				</td>
			</tr>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#fn_goList" onclick="javascript:fn_delete('${status}');" class="orange" >삭제</a>
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="gray-1" >목록</a>
			<a href="#fn_scheduleList" onclick="javascript:fn_scheduleList();" class="orange">시간표표기</a>
		</div>
	</div>
	
	</div>
	
	<div id="scheduleViewDisplay2" style="display:none;">
	
	<div class="group-area mt-040 mb-040">
		<table class="type-2">
			<colgroup>
				<col style="width:40px" />
				<col style="width:150px" />
				<col style="width:120px" />
				<col style="width:70px" />
				<col style="width:*" />
				<%-- <col style="width:*" /> --%>
				<col style="width:200px" />
				<col style="width:200px" />
				<col style="width:100px" />
				<col style="width:120px" />
			</colgroup>
			<tr>
				<th>주차</th>
				<th>일자</th>
				<th>교육시간</th>
				<th>소요시간</th>
				<th>교과목</th>
				<th>능력단위명</th> 
				<th>능력단위요소명</th> 
				<th>기업현장교사</th>
				<th>장소</th>
			</tr>
			<c:forEach var="result" items="${resultScheduleViewList}" varStatus="status">
			<tr>
				<td>${result.weekCnt}</td>
				<td>${result.traningDate} (${result.dayOfWeek})</td>
				<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
				<td>${result.leadTime}</td>
				<td>${result.subjectName}</td>
				<td>${result.ncsUnitName}</td>
				<td style="text-align: left;">${fn:replace(result.ncsElemName, cn , br )}</td>
				<td>${result.memName}</td> 
				<td>${result.trainPlace}</td>
			</tr>
			</c:forEach>
			<tr>
				<th></th>
				<th>계</th>
				<th></th>
				<th>${leadTimeSum}</th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
				<th></th>
			</tr>
		</table>

		<div class="btn-area align-right mt-010">
			<c:if test="${status == '1'}">
				<a href="#fn_saveScheduleList" onclick="javascript:fn_saveScheduleList();" class="orange" >등록</a>
			</c:if>
		</div>
	</div>

	<h2>훈련시간표 변경내역</h2>

	<table class="type-2">
		<colgroup>
			<col style="width:50px" />
			<col style="width:110px" />
			<col style="width:100px" />
			<col style="width:300px" />
			<col style="width:*" />
			<col style="width:110px" />
			<col style="width:80px" />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>변경 신청일</th>
				<th>변경자</th>
				<th>변경시간표</th>
				<th>변경사유</th>
				<th>IP</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="result" items="${resultScheduleModifyViewList}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${result.insertDate}</td>
				<td>${result.memName}</td>
				<td>${result.orgFileName} <a href="javascript:com.downFile('${result.atchFileId}','1');" class="btn-full-gray">열기</a></td>
				<td>${result.retunReason}</td>
				<td>${result.ipAddress}</td>
				<td class="bg-highlight">
					<c:choose>
					<c:when test="${result.status eq '1'}">
						승인대기
					</c:when>
					<c:when test="${result.status eq '2'}">
						승인
					</c:when>
					<c:otherwise>
						반려
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>

</div><!-- E : content-area -->


</form>
					

	