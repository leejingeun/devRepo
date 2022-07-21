<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<script type="text/javascript" src="${contextRootJS }/js/oklms/jquery.cookie.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/oklms/popupApi.js"></script>
<script type="text/javascript">
<!--
var jsonObj = eval('${lms:objectToJson(popupList)}');
PopupOpenerAPI.dataList = jsonObj;
PopupOpenerAPI.contextPath = "${pageContext.request.contextPath}";

$(document).ready(function() {
	//팝업 알림.
	for (var i=0; i < PopupOpenerAPI.dataList.length; i++) {
		var popup = PopupOpenerAPI.dataList[i];
		//PopupOpenerAPI.open(popup, true);
	}
});

function fn_board_list(bbsId) {
	var reqUrl = "/lu/cop/bbs/"+bbsId+"/selectBoardList.do";

	$("#frmMainPage").attr("action", reqUrl);
	$("#frmMainPage").submit();
}

function fn_board_detail(nttId, bbsId) {
	var reqUrl = "/lu/cop/bbs/"+bbsId+"/selectBoardArticle.do?nttId="+nttId;

	$("#frmMainPage").attr("action", reqUrl);
	$("#frmMainPage").submit();
}
function fn_searchDay(day){
	var reqUrl = "/lu/main/lmsUserMainPage.do";
	$("#nowDay").val(day);
	$("#frmMainPage").attr("action", reqUrl);
	$("#frmMainPage").submit();	
}
//-->
</script>
<!-- ############### START : /la/main/lmsAdminMainPage ############### -->
<form name="frmMainPage" id="frmMainPage" method="post">
<input type="hidden" name="nowDay" id="nowDay" >
<!-- HRD전담자 -->
	<div class="half-left-area mt-100">
	<h3>TODAY</h3>
	<table class="type-3" style="table-layout:fixed;">
		<thead>
			<tr>
				<th>활동내역서<br />작성</th>
				<th>주간 훈련일지<br />미제출</th>
				<th>주차별 학습활동서<br />미제출</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><span>${readTodayCnt[0].myPageTodayCnt1}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt2}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt3}</span>건</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="half-right-area mt-100">
	<h3>담당훈련과정 현황</h3>
	<table class="type-2">
		<colgroup>
			<col width="*" />
			<col width="60px" />
			<col width="75px" />
			<col width="75px" />
		</colgroup>
		<thead>
			<tr>
				<th>훈련과정명</th>
				<th>학년</th>
				<th>전체 학생수</th>
				<th>중도 탈락자</th>
			</tr>
		</thead>
		<tbody>
		
		 
		<c:forEach var="result" items="${listLmsUserMainPageStatusCnt}" varStatus="status">
			<tr>
				<td rowspan="2" class="left"> ${result.hrdTraningName }</td>
				<td>${result.schoolYear } 학년</td>
				<td>${result.stuCnt } 명</td>
				<td>${result.outCnt } 명</td>
			</tr>		
		</c:forEach>
		<c:if test="${ empty listLmsUserMainPageStatusCnt}">
			<tr>
				<td colspan="4">강의가 없습니다.</td>
			</tr>					 
		 </c:if> 
		</tbody>
	</table>
</div>

<div class="full-area">

	<div class="daily-schedule">
		<a href="#!" class="btn-pre" onclick="javascript:fn_searchDay('${yesDay}');" >이전</a> ${nowDay} <c:if test="${not empty nowDayName }"> (${nowDayName })</c:if><a href="#!"  onclick="javascript:fn_searchDay('${tomDay}');"  class="btn-next">다음</a>
	</div>
	
	<h3>금일 훈련일정</h3>
	<table class="type-2 mb-030">
		<colgroup>
			<col width="100px" />
			<col width="50px" />
			<col width="*" />
			<col width="65px" />
			<col width="*" />
			<col width="50px" />
			<col width="90px" />
			<col width="90px" />
		</colgroup>
		
		<thead>
			<tr>
				<th>훈련시간</th>
				<th>학년</th>
				<th>훈련 과정명</th>
				<th>구분</th>
				
				<th>개설 교과명</th>
				<th>분반</th>
				<th>기업현장교사</th>
				<th>교수</th>
			</tr>
		</thead>
		<tbody>
		 
		
		<c:forEach var="result" items="${listLectureScheduleCcm}" varStatus="status">
			<tr>
				<td>
				<c:if test="${not empty result.traningStHour}"> 
					${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}
				</c:if>
				</td>
				<td>${result.schoolYear}</td>
				<td class="left">${result.hrdTraningName}</td>
				<td>${result.subjectTraningType}</td>
				
				<td class="left">${result.subjectName}</td>
				<td>${result.subClass}</td>
				<td>${result.memberCotName}</td>
				<td>${result.memberPrtName}</td>
			</tr>	
		</c:forEach>
		<c:if test="${ empty listLectureScheduleCcm}">
			<tr>
				<td colspan="8">강의가 없습니다.</td>
			</tr>					 
		 </c:if> 
		 
		</tbody>
	</table>

 
</div>
</form>
<!-- ############### END : /la/main/lmsAdminMainPage ############### -->