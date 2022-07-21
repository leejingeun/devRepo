<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>

<script type="text/javascript" src="${contextRootJS }/js/oklms/onlineStudy.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/oklms/jquery.cookie.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/oklms/popupApi.js"></script>
<script type="text/javascript">
<!--
var jsonObj = eval('${lms:objectToJson(popupList)}');
var pSubjectTraningType = "${param.subjectTraningType}";
PopupOpenerAPI.dataList = jsonObj;
PopupOpenerAPI.contextPath = "${pageContext.request.contextPath}";

$(document).ready(function() {
	//팝업 알림.
	for (var i=0; i < PopupOpenerAPI.dataList.length; i++) {
		var popup = PopupOpenerAPI.dataList[i];
		//PopupOpenerAPI.open(popup, true);
	}
	
	if(pSubjectTraningType == "OFF"){
		showTabbtn2();
	} else {
		showTabbtn1();
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

function goWeekLessonSubmit(lessonId,weekId,weekCnt,contentName,weekProgressRate ,attendProgress, cutProgress,progressAttendTypeCode){

	$("#attendProgress").val(attendProgress);
	$("#cutProgress").val(cutProgress);
	$("#progressAttendTypeCode").val(progressAttendTypeCode);
	goWeekLesson(lessonId,weekId,weekCnt,contentName,weekProgressRate);

}

function fn_setTraning(subjectTraningType){
	$("#subjectTraningType").val(subjectTraningType);
}

//-->
</script>
<!-- ############### START : /la/main/lmsAdminMainPage ############### -->
<form id="frmLesson" name="frmLesson" method="post">
	<input type="hidden" id="id" name="id" value="" /> 
	<input type="hidden" id="courseContentId" name="courseContentId"/>
	<input type="hidden" id="lessonId" name="lessonId" />
	<input type="hidden" id="lessonItemId" name="lessonItemId" />
	<input type="hidden" id="lessonSubItemId" name="lessonSubItemId" />
	<input type="hidden" id="contentsDir" name="contentsDir" />
	<input type="hidden" id="contentsIdxFile" name="contentsIdxFile" />
	<input type="hidden" id="contentsRealFile" name="contentsRealFile" />
	<input type="hidden" id="weekCnt" name="weekCnt" value="" />
	<input type="hidden" id="weekId" name="weekId" value="" />
	<input type="hidden" id="subjSchId" name="subjSchId" value="" />
	<input type="hidden" id="stdLessonId" name="stdLessonId" value="" />
	<input type="hidden" id="cmsLessonId" name="cmsLessonId" value="" />
	<input type="hidden" id="cmsLessonItemId" name="cmsLessonItemId" value="" />
	<input type="hidden" id="cmsLessonSubItem" name="cmsLessonSubItem" value="" />
	<input type="hidden" id="linkContentYn" name="linkContentYn" value="" />
	<input type="hidden" id="progressAttendId" name="progressAttendId" value="" />
	<input type="hidden" id="progressTimeId" name="progressTimeId" value="" />
	<input type="hidden" id="pageInfo" name="pageInfo" value="" />
	<input type="hidden" id="viewLessonId" name="viewLessonId" value="" />
	<input type="hidden" id="spanId" name="spanId" value="" />
	<input type="hidden" id="attendProgress" name="attendProgress" value="" />
	<input type="hidden" id="cutProgress" name="cutProgress" value="" />
	
	<input type="hidden" id="progressAttendTypeCode" name="progressAttendTypeCode" value="" />
</form>
<ul id="learning-popup">
	<li class="learning-area"  id="learning-area" style="height:820px; margin-top:-410px;">
		<div class="title">
			<span>${subjectVO.subjectName}</span>
			<!-- <a href="javascript:alert('준비중입니다.');" class="btn-1">과정소개</a>
			<a href="javascript:alert('준비중입니다.');" class="btn-2">사용안내</a>
			<a href="javascript:alert('준비중입니다.');" class="btn-3">오류신고</a> -->
			<a href="javascript:hideLearningPop();" class="btn-4">종료</a>
		</div>

		<div id="learn-container">
			<div id="index" style="height:760px">
				<div class="index-title"><span>INDEX</span></div>
				<div class="index-list" id="index" style="height:560px">
					<dl id="index-schedule"></dl>
				</div>

				<div class="index-guide">
					<span>○ : 출석인정 진도율 ${onlineTraningVO.attendProgress}이상, △ : 지각인정 진도율 ${onlineTraningVO.cutProgress}이상 ~ 출석인정 진도율 ${onlineTraningVO.attendProgress}미만, × : 지각인정 진도율 ${onlineTraningVO.cutProgress}미만</span>
				</div>
			</div>


			<div id="movie-zone">
				<div class="movie-name"><span id="week-schedule-title">[ 01주차 ] 01차시&nbsp;&nbsp;-&nbsp;&nbsp;전기공압의 기초회로</span></div>
				<div class="wait-img" style="height:660px">잠시만 기다려주세요.</div>
				<iframe src="" id="contentFrame" width="512px" height="660px" frameborder="0" allowfullscreen></iframe>

				<div class="movie-control">
					<a href="javascript:prevView();" class="pre">이전</a>
					<span id="contentPage"><b>01</b> / 24</span>
					<a href="javascript:nextView();" class="next">다음</a>
				</div>
			</div><!-- E : movie-zone -->
		</div>

	</li>
	<li class="popup-bg"></li>
</ul><!-- E : learning-popup -->

<form name="frmMainPage" id="frmMainPage" method="post">
<input type="hidden" name="nowDay" id="nowDay" >
<input type="hidden" name="subjectTraningType" id="subjectTraningType" value="${param.subjectTraningType}" >
<!-- 학습근로자 -->

	<div class="half-left-area mt-100">
		<h3>TODAY</h3>
		<table class="type-3" style="table-layout:fixed;">
			<thead>
				<tr>
					<th>학습활동서작성</th>
					<th>질문과답변</th>
					<th>과제제출</th>
					<th>팀프로젝트과제제출</th>
					<th>토론개설</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><span>${readTodayCnt[0].myPageTodayCnt1}</span>건</td>
					<td><span>${readTodayCnt[0].myPageTodayCnt2}</span>건</td>
					<td><span>${readTodayCnt[0].myPageTodayCnt3}</span>건</td>
					<td><span>${readTodayCnt[0].myPageTodayCnt4}</span>건</td>
					<td><span>${readTodayCnt[0].myPageTodayCnt5}</span>건</td>
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

		<dl id="tab-type-1">
			<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();fn_setTraning('OJT');">OJT</a></dt>
			<dd id="tab-content-11" style="display:block;">
				<h3>강의일정</h3>
				<table class="type-2 mb-030">
					<colgroup>
						<col width="110px" />
						<col width="*" />
						<col width="60px" />
						<col width="50px" />
						<col width="130px" />
						<col width="100px" />
						<col width="140px" />
						<col width="90px" />
					</colgroup>
					<thead>
						<tr>
							<th>시간</th>
							<th>개설 교과명</th>
							<th>대상학년</th>
							<th>분반</th>
							<th>기업명</th>
							<th>주차</th>
							<th>단원(능력단위요소)</th>
							<th>기업현장교사</th>
						</tr>
					</thead>
					<tbody>
					
					 <c:forEach var="ojtListSchedule" items="${ojtListSchedule}" varStatus="status">
					 
					 	<tr>
							<td>${ojtListSchedule.traningStHour}:${ojtListSchedule.traningStMin} ~ ${ojtListSchedule.traningEdHour}:${ojtListSchedule.traningEdMin}</td>
							<td class="left">
								<a href="#!" onclick="javascript:fn_lec_menu_display('${ojtListSchedule.subjectTraningType}','${ojtListSchedule.yyyy}','${ojtListSchedule.term}','${ojtListSchedule.subjectCode}','${ojtListSchedule.subClass}','${ojtListSchedule.subjectName}','${ojtListSchedule.subjectType}','${ojtListSchedule.onlineType}');" class="text">${ojtListSchedule.subjectName}</a>
							</td>
							<td>${mainPageVO.sessionLevel}</td>
							<td>${ojtListSchedule.subClass}</td>
							<td>${ojtListSchedule.companyName}</td>
							<td>${ojtListSchedule.weekCnt} / ${ojtListSchedule.maxWeekCnt}</td>
							<td>
								<c:if test="${not empty ojtListSchedule.ncsUnitName}">${ojtListSchedule.ncsUnitName}</c:if>
								<c:if test="${not empty ojtListSchedule.ncsElemName}">(${ojtListSchedule.ncsElemName})</c:if>
							</td>
							<td>${ojtListSchedule.tutNames} </td>
						</tr>
						
					 </c:forEach>
					 <c:if test="${ empty ojtListSchedule}">
						<tr>
							<td colspan="8">강의가 없습니다.</td>
						</tr>					 
					 </c:if> 
					</tbody>
				</table>
			</dd>

			<dt class="tab-name-12"><a href="javascript:showTabbtn2();fn_setTraning('OFF');">Off-JT</a></dt>
			<dd id="tab-content-12">
				<h3>강의일정</h3>
				<table class="type-2 mb-030">
					<colgroup>
						<col width="110px" />
						<col width="*" />
						<col width="60px" />
						<col width="60px" />
						<col width="110px" />
						<col width="180px" />
						<col width="130px" />
					</colgroup>
					<thead>
						<tr>
							<th>시간</th>
							<th>개설 교과명</th>
							<th>대상학년</th>
							<th>주차</th>
							<th>온라인 교육형태</th>
							<th>단원 (능력단위요소)</th>
							<th>장소</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="offListSchedule" items="${offListSchedule}" varStatus="status">
						<tr>
							<td>${offListSchedule.traningStHour}:${offListSchedule.traningStMin} ~ ${offListSchedule.traningEdHour}:${offListSchedule.traningEdMin}</td>
							<td class="left">
							<a href="#!" onclick="javascript:fn_lec_menu_display('${offListSchedule.subjectTraningType}','${offListSchedule.yyyy}','${offListSchedule.term}','${offListSchedule.subjectCode}','${offListSchedule.subClass}','${offListSchedule.subjectName}','${offListSchedule.subjectType}','${offListSchedule.onlineType}');"  class="text">${offListSchedule.subjectName}</a>
							</td>
							<td>${mainPageVO.sessionLevel}</td>
							<td>${offListSchedule.weekCnt} / ${offListSchedule.maxWeekCnt}</td>
							<td>
					<c:if test="${offListSchedule.subjectTraningType eq 'OFF'}">
						<c:choose>
							<c:when test="${offListSchedule.weekMappingCnt ne '0'}">
								${offListSchedule.onlineTypeName} <p id="weekProgress_${offListSchedule.weekId}">${offListSchedule.weekProgressRate}%</p><a href="javascript:goWeekLessonSubmit('${offListSchedule.lessonId}','${offListSchedule.weekId}','${offListSchedule.weekCnt}','${offListSchedule.contentName}',${offListSchedule.weekProgressRate},'${offListSchedule.attendProgress}', '${offListSchedule.cutProgress}','${offListSchedule.progressAttendTypeCode}');" class="btn-full-gray">학습</a>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:if>
												</td>
							<td>
								<c:if test="${not empty offListSchedule.ncsUnitName}">${offListSchedule.ncsUnitName}</c:if>
								<c:if test="${not empty offListSchedule.ncsElemName}">(${offListSchedule.ncsElemName})</c:if>
							</td>
							<td>${offListSchedule.lctrumNm}</td>
						</tr>					
					</c:forEach>

					<c:if test="${ empty offListSchedule}">
						<tr>
							<td colspan="7">강의가 없습니다.</td>
						</tr>					 
					</c:if>

					</tbody>
				</table>
			</dd>
		</dl>
	</div>

	<div class="half-left-area">
		<h3 class="mt-030">학습관리</h3>
		<table class="type-2">
			<colgroup>
				<col width="*" />
				<col width="80px" />
				<col width="50px" />
				<col width="50px" />
				<col width="50px" />
			</colgroup>
			<thead>
				<tr>
					<th>교과목</th>
					<th>진도율</th>
					<th>결석</th>
					<th>온라인<br />미수강</th>
					<th>활동서<br />미작성</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="rs" items="${listLmsUserMainPageStatusCnt }" varStatus="status">
					<tr>
						<td class="left"><a href="#!"  onclick="javascript:fn_lec_menu_display('${rs.subjectTraningType}','${rs.yyyy}','${rs.term}','${rs.subjectCode}','${rs.subClass}','${rs.subjectName}','${rs.subjectType}','${rs.onlineType}');"  class="text">${rs.subjectName}</a></td>
						<td>${rs.realProgressRate} %</td>
						<td>${rs.totalTime - rs.attend }</td>
						<td>
							<c:if test="${rs.subjectTraningType eq 'OFF' }">${rs.onTotalTime - rs.onAttend }</c:if>
							<c:if test="${rs.subjectTraningType ne 'OFF' }">-</c:if>
						</td>
						<td>${rs.activityNoteCnt}</td>
					</tr>				
				</c:forEach>
				
				<c:if test="${ empty listLmsUserMainPageStatusCnt}">
					<tr>
						<td colspan="5">강의가 없습니다.</td>
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