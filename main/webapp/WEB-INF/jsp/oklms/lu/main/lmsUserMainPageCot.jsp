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
<input type="hidden" name="nowDay"  id="nowDay" />
  <!-- 기업현장교사 -->
<div class="half-left-area mt-100">
<h3>TODAY</h3>
<table class="type-3" style="table-layout:fixed;">
		<thead>
			<tr>
				<th>훈련일지 작성</th>
				<th>학습활동서 확인</th>
				<th>질문과 답변</th>
				<th>면담일지 작성</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><span>${readTodayCnt[0].myPageTodayCnt1}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt2}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt3}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt4}</span>건</td>
			</tr>
		</tbody>
	</table>
</div>

<div class="half-right-area mt-100">
	<h3>공지사항</h3>
	<ul class="board-list">
		<c:if test="${fn:length(noticeResultList) == 0}">
			<li>등록된 공지사항이 없습니다.</li>
		</c:if>
		<c:forEach var="noticeResult" items="${noticeResultList}" varStatus="status">
			<li>
				<a href="#!" onclick="fn_board_detail('${noticeResult.nttId}','${noticeResult.bbsId}');">
					<p>[ ${noticeResult.subjectName} ]</p>
					<c:if test="${'Y' == noticeResult.topNoticeYn}"><B></c:if>${noticeResult.nttSj}<c:if test="${'Y' == noticeResult.topNoticeYn}"></B></c:if>
				</a><span>${noticeResult.frstRegisterPnttm}</span>
			</li>
		</c:forEach>
		<li class="more"><a href="#!" onclick="fn_board_list('BBSMSTR_000000000005');" title="더 보기">공지사항 더 보기</a></li>
	</ul>
</div>

<div class="full-area">

 
	<div class="daily-schedule">
		<a href="#!" class="btn-pre" onclick="javascript:fn_searchDay('${yesDay}');" >이전</a> ${nowDay} <c:if test="${not empty nowDayName }"> (${nowDayName })</c:if><a href="#!"  onclick="javascript:fn_searchDay('${tomDay}');"  class="btn-next">다음</a>
	</div>
		
	<h3>강의 일정</h3>
	<table class="type-2">
		<colgroup>
		<col width="110px" />
		<col width="*" />
		<col width="60px" />
		<col width="50px" />
		<col width="60px" />
		<col width="140px" />
		<col width="12%" />
	</colgroup>
		<thead>
			<tr>
				<th>시간</th>
				<th>개설교과명</th>
				<th>대상학년</th>
				<th>분반</th>
				
				<th>주차</th>
				<th>단원 (능력단위요소)</th>
				<th>교수</th>
			</tr>
		</thead>
		<tbody>

		 <c:forEach var="result" items="${listLectureCotSchedule}" varStatus="status">
					<tr>
						<td>${result.traningStHour}:${result.traningStMin} ~ ${result.traningEdHour}:${result.traningEdMin}</td>
						<td class="left">
<a href="#!" onclick="javascript:fn_lec_menu_display('${result.subjectTraningType}','${result.yyyy}','${result.term}','${result.subjectCode}','${result.subClass}','${result.subjectName}','${result.subjectType}','${result.onlineType}');"  class="text">${result.subjectName}</a>
						</td>
						<td>${result.schoolYear}</td>
						<td>${result.subClass}</td>
						
						<td>${result.weekCnt} / ${result.maxWeekCnt}</td>
						<td>${result.ncsUnitName} </td>
						<td>${result.memberPrtName}</td>
					</tr> 
		 </c:forEach>		
		 <c:if test="${ empty listLectureCotSchedule}">
			<tr>
				<td colspan="7">데이터가 없습니다.</td>
			</tr>					 
		 </c:if>		 
		</tbody>
	</table>
</div>

<div class="half-left-area">
	<h3 class="mt-030">학습근로자관리</h3>
	<table class="type-2">
		<colgroup>
		<col width="*" />
		<col width="50px" />
		<col width="50px" />
		<col width="50px" />
	</colgroup>
		<thead>
			<tr>
				<th>교과목</th>
				<th>주차</th>
				<th>결석</th>
				<th>활동서<br />미작성</th>
			</tr>
		</thead>
		<tbody>
				<c:forEach var="rs" items="${listLmsUserMainPageStatusCnt }" varStatus="status">
					<tr>
					 	<td>${rs.subjectName}</td>
					 	<td>${rs.weekCnt}</td>
					 	<td>${rs.totalTime-rs.attend}</td>
					 	<td>${rs.activityNoteCnt}</td>
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

<div class="half-right-area">
	<h3 class="mt-030">Q &amp; A</h3>
	<ul class="board-list">
		<c:if test="${fn:length(quAndAnResultList) == 0}">
			<li>등록된 Q &amp; A가 없습니다.</li>
		</c:if>
		<c:forEach var="quAndAnResult" items="${quAndAnResultList}" varStatus="status">
			<li>
				<a href="#!" onclick="fn_board_detail('${quAndAnResult.nttId}','${quAndAnResult.bbsId}');">
					<p>[ ${quAndAnResult.subjectName} ]</p>
					<c:if test="${'Y' == quAndAnResult.topNoticeYn}"><B></c:if>${quAndAnResult.nttSj}<c:if test="${'Y' == quAndAnResult.topNoticeYn}"></B></c:if>
				</a><span>${quAndAnResult.frstRegisterPnttm}</span>
			</li>
		</c:forEach>
		<li class="more"><a href="#!" onclick="fn_board_list('BBSMSTR_000000000007');" title="더 보기">Q &amp; A 더 보기</a></li>
	</ul>
</div>
 

</form>
<!-- ############### END : /la/main/lmsAdminMainPage ############### -->