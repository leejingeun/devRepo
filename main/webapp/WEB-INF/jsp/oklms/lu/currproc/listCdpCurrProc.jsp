<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/currproc/"/>
<script type="text/javascript" src="${contextRootJS }/js/oklms/onlinePreview.js"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보
	
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

//         com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function fn_subject_schedule_form(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "getCurrProcScheduleForm.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	
	function fn_subject_eval_form(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "getCurrProcEvalForm.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	
	function fn_online_traing(){
		var reqUrl = "/lu/online/listOnlineTraning.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	
	function fn_online_traing(){
		var reqUrl = "/lu/online/listOnlineTraning.do";
		$("#frmLesson").attr("action", reqUrl);
		$("#frmLesson").submit();
	}
	
	
	function fn_excel(pageType){ 
		var reqUrl ="/lu/subject/excelSubjectScheduleCdp.do";
		$("#pageType").val(pageType);
		$("#frmExcel").attr("action", reqUrl);
		$("#frmExcel").attr("target","iframe");
		$("#frmExcel").submit();	 
	}
	
</script>

<form id="frmExcel" name="frmExcel" method="post">
	<input type="hidden" id="pageTyoe" name="pageTyoe"  value="" /> 
	<input type="hidden" id="yyyy" name="yyyy" value="${subjectVO.yyyy}"/>
	<input type="hidden" id="term" name="term" value="${subjectVO.term}" />
	<input type="hidden" id="subjectCode" name="subjectCode" value="${subjectVO.subjectCode}"/>
	<input type="hidden" id="subClass" name="subClass" value="${subjectVO.subjectClass}"/>
</form>


<!-- <iframe name="iframe" id="" src="" width="0" height="0" frameborder="0" scrolling="no"></iframe> 
 -->
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
	<input type="hidden" id="subjPoint" name="subjPoint" value="${subjectVO.point}"/>
		<input type="hidden" id="estblYy" name="estblYy" value="${subjectVO.estblYy}" />
	<input type="hidden" id="estblSemstrCd" name="estblSemstrCd" value="${subjectVO.estblSemstrCd}" />
	<input type="hidden" id="courseNo" name="courseNo" value="${subjectVO.courseNo}" />
	<input type="hidden" id="partitnClasSeCd" name="partitnClasSeCd" value="${subjectVO.partitnClasSeCd}" />
</form>

	<ul id="preview-popup">
		<li class="preview-area" id="preview-area">
			<div class="title">
				<span>미리보기</span>
				<a href="javascript:hidePreviewPop();">종료</a>
			</div>
	
			<div id="preview-container">
				<div id="preview-index">
					<p>학습목차</p>
					<div style="height:645px" id="title-index">
					</div>
				</div>
	
				<!-- iframe 논이 값 height 값에 
				<p>학습목차</p>
				<div style="height:645px"> - 15px;
				-->
	
				<div id="preview-movie-zone">
					<div class="wait-img" style="height:660px">잠시만 기다려주세요.</div>
					<iframe id="contentFrame" src="" width="1000px" height="660px" frameborder="0" allowfullscreen></iframe>
					<div class="movie-control">
						<a href="javascript:prevView();" class="pre">이전</a>
						<span id="contentPage"><b>01</b> / 24</span>
						<a href="javascript:nextView();" class="next">다음</a>
					</div>
				</div><!-- E : movie-zone -->
			</div>
		</li>
		<li class="popup-bg"></li>
	</ul><!-- E : preview-popup -->
	
			<div id="content-area">
				<h2>교과목 정보</h2>
				<h4 class="mb-010"><span>${subjectVO.subjectName}</span> (${subjectVO.subjectClass}반) ㅣ ${subjectVO.yyyy}학년도 ${subjectVO.term}학기</h4>
	
				<div class="group-area mb-010">
					<table class="type-1">
						<colgroup>
							<col style="width:15%" />
							<col style="width:*px" />
							<col style="width:15%" />
							<col style="width:*px" />
							<col style="width:15%" />
							<col style="width:*px" />
						</colgroup>
						<tr>
							<th>교과형태</th>
							<td>${subjectVO.subjectTraningTypeName}</td>
							<th>과정구분</th>
							<td>${subjectVO.subjectTypeName}</td>
							<th>훈련시간</th>
							<td>${subjectVO.traningHour}시간</td>
						</tr>
						<tr>
							<th>담당교수</th>
							<td>${subjectVO.insNames}</td>
							<th>온라인 교육형태</th>
							<td>${subjectVO.onlineTypeName} (성적비율 ${subjectVO.gradeRatio}%)</td>
							<th>선수여부</th>
							<td>${subjectVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
						</tr>
					</table>
	
					<div class="btn-area align-right mt-010">
						<a href="#none" onclick="fn_subject_schedule_form();" class="yellow">강의계획서</a><!-- <a href="#none" onclick="fn_subject_eval_form();" class="orange">체크리스트</a> -->
					</div><!-- E : btn-area -->
				</div><!-- E : 교과목 정보 -->



						<dl id="tab-type">
							<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">OFF-LINE</a></dt>
							<dd id="tab-content-11" style="display:block;">

								<h2>진행율</h2>
								<div class="progress-area mb-040">
									<p>전체 진행율 [ 0% : 0 페이지 / 0 페이지 ]</p>
									<ul>
										<li style="width:0%;"><span><b>0</b> %</span></li>
									</ul>
								</div><!-- E : 진행율 -->


								<div class="group-area mt-020">
									<table class="type-2">
										<colgroup>
											<col style="width:70px" />
											<col style="width:*" />
											<col style="width:80px" />
											<col style="width:120px" />
											<col style="width:120px" />
											<col style="width:120px" />
											<col style="width:180px" />
										</colgroup>
										<thead>
											<tr>
												<th>주차</th>
												<th>훈련일자</th>
												<th>교육시간</th>
												<th>개별과제</th>
												<th>팀프로젝트</th>
												<th>토론</th>
												<th>강의실</th>
											</tr>
										</thead>
										<tbody>
										<c:set var="totTimeHour" value="0"/>
										<c:set var="totReport" value="0"/>
										<c:set var="totTeamProject" value="0"/>
										<c:set var="totDiscuss" value="0"/>
										<c:forEach var="result" items="${resultList1}" varStatus="status">
											<tr  <c:if test="${result.nowWeekYn eq 'Y'}">class="highlight"</c:if>>
												<td>${result.weekCnt}</td>
												<td>${result.traningDate}(${result.dayWeek})<br />${result.traningSt} ~ ${result.traningEd}</td>
												<td>${result.timeHour}</td>
												<c:set var="totTimeHour" value="${totTimeHour+result.timeHour}"/>
												<td>
												
												<c:if test="${!empty  result.reportId}">
													<c:set var="totReport" value="${totReport+1}"/>
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-search-gray">과제</a>
												</c:if>
												</td>
												<td>
												<c:if test="${!empty  result.teamProjectId}">
													<c:set var="totTeamProject" value="${totTeamProject+1}"/>
													<a href="/lu/report/getReport.do?reportId=${result.reportId}" class="btn-search-gray">과제</a>
												</c:if>
												</td>
												<td>
												<c:if test="${!empty  result.discussId}">
													<c:set var="totDiscuss" value="${totDiscuss+1}"/>
													<a href="/lu/discuss/getDiscuss.do?discussId=${result.discussId}&yyyy=${result.yyyy}&term=${result.term}&subjectCode=${result.subjectCode}&subClass=${result.subjectClass}" class="btn-search-gray">토론</a>
												</c:if>
												</td>
												<td>${result.lctrumNm}</td>
											</tr>
										</c:forEach>	
											<tr>
												<th></th>
												<th>계</th>
												<th>${totTimeHour}</th>
												<th>${totReport}</th>
												<th>${totTeamProject}</th>
												<th>${totDiscuss}</th>
												<th></th>
											</tr>
										</tbody>
									</table>

									<div class="btn-area align-right mt-010">
										<a href="#!" onclick="fn_excel('OFF');" class="orange">엑셀 다운로드</a>
									</div><!-- E : btn-area -->
								</div>
							</dd>



							
							
					<c:if test="${subjectVO.onlineType ne 'NONE'}">
							<dt class="tab-name-12"><a href="javascript:showTabbtn2();">ON-LINE</a></dt>
							<dd id="tab-content-12">

								<h2>진행율</h2>
								<div class="progress-area mb-040">
									<p>전체 진행율 [ 0% : 0 페이지 / 0 페이지 ]</p>
									<ul>
										<li style="width:0%;"><span><b>0</b> %</span></li>
									</ul>
								</div><!-- E : 진행율 -->



								<!-- <div class="btn-area align-right mt-010">
									<a href="#fn_online_traing" onclick="fn_online_traing();" class="yellow">온라인훈련 편집</a>
								</div> --><!-- E : btn-area -->



								<div class="group-area mt-010 mb-040">
									<table class="type-2">
										<colgroup>
											<col style="width:50px" />
											<col style="width:50px" />
											<col style="width:*" />
											<col style="width:190px" />
											<col style="width:70px" />
											<col style="width:90px" />
											<col style="width:90px" />
										</colgroup>
										<thead>
											<tr>
												<th>주차</th>
												<th>차시</th>
												<th>콘텐츠명</th>
												<th>학습기간</th>
												<th>학습시간</th>
												<th>미리보기</th>
												<th>보조자료</th>
											</tr>
										</thead>
										<tbody>
										<c:set var="totWeekMin" value="0"/>
										<c:forEach var="result" items="${resultList}" varStatus="status">
											<tr <c:if test="${result.nowWeekYn eq 'Y'}">class="highlight"</c:if>>
												<td>${result.weekCnt}주차</td>
												<td></td>
												<td class="left">${result.contentName}</td>
												<td>${result.weekStDate} ~ ${result.weekEdDate}</td>
												<td>${result.weekMin}</td>
												<c:set var="totWeekMin" value="${totWeekMin+result.weekMin}"/>
												<td></td>
												<td><!-- <a href="javascript:alert('준비중입니다');" class="btn-search-gray"></a> --></td>
											</tr>
											<c:forEach var="sch" items="${scheduleList}" varStatus="sch_status">
												<c:if test="${result.weekId eq sch.weekId}">
													<tr <c:if test="${result.nowWeekYn eq 'Y'}">class="highlight"</c:if>>
														<td>ㄴ</td>
														<td>${sch.subjStep}차시</td>
														<td class="left">${sch.subjTitle}</td>
														<td>${sch.startDate} ~ ${sch.endDate}</td>
														<td>${sch.studyTime}</td>
														<td><a href="javascript:goPreview('${sch.linkContentYn}','${result.weekCnt}','${result.contentName}','${sch.subjStep}','${sch.subjTitle}','${sch.cmsLessonId}','${sch.contentType}','${sch.contentsDir}','${sch.contentsIdxFile}','${sch.url}');" class="btn-search-gray"></a></td>
														<td><!-- <a href="javascript:alert('준비중입니다');" class="btn-search-gray"></a> --></td>
													</tr>
												</c:if>
											</c:forEach>
										</c:forEach>
											<tr>
												<th></th>
												<th></th>
												<th>계</th>
												<th></th>
												<th>${totWeekMin}분</th>
												<th></th>
												<th></th>
											</tr>
										</tbody>
									</table>

									<!-- <div class="btn-area align-right mt-010">
										<a href="#!" onclick="fn_excel('ON');" class="orange">엑셀다운로드</a>
									</div> --><!-- E : btn-area -->
								</div><!-- E : 훈련정보 -->

							</dd>
				</c:if>			
						</dl>





