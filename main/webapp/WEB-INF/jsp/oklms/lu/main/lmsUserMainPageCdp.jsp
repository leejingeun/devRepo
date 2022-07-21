<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

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

/* 리스트 조회 */
function fn_search(  ){
	var reqUrl =  "/lu/main/lmsUserMainPage.do";
	$("#frmSubject").attr("action", reqUrl);
	$("#frmSubject").submit();
}
/* 교과로 이동 */
function fn_move_subject(subjectTraningType, year, term, subjectCode, subClass, subjectName, subjectType, onlineType,preSubjectCode){
	subjectName = encodeURIComponent(subjectName);
	if(preSubjectCode != ""){
		location.href = "/lu/currproc/listCurrProc.do?subjectTraningType="+subjectTraningType+"&year="+year+"&term="+term+"&subjectCode="+subjectCode+"&subjectName="+subjectName+"&subClass="+subClass+"&lecMenuMarkYn=Y&subjectType="+subjectType+"&onlineType="+onlineType+"&preSubjectCode="+preSubjectCode;
	} else {
		location.href = "/lu/currproc/listCurrProc.do?subjectTraningType="+subjectTraningType+"&year="+year+"&term="+term+"&subjectCode="+subjectCode+"&subjectName="+subjectName+"&subClass="+subClass+"&lecMenuMarkYn=Y&subjectType="+subjectType+"&onlineType="+onlineType;
	}
}
//-->
</script>
<!-- ############### START : /la/main/lmsAdminMainPage ############### --> 

<!-- 학과전담자 -->

<div class="full-area mt-070 mb-040" style="border-top:0;">
<h3>TODAY</h3>
<table class="type-3" style="table-layout:fixed;">
		<thead>
			<tr>
				<th>Q&amp;A 답변대기</th>
				<th>개별과제미체출</th>
				<th>팀프로젝트과제 미체출</th>
				<th>토론 미체출</th>

				<th>재직증빙서류 미승인</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><span>${readTodayCnt[0].myPageTodayCnt1}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt2}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt3}</span>건</td>
				<td><span>${readTodayCnt[0].myPageTodayCnt4}</span>건</td>
				
				<td><span>${readTodayCnt[0].myPageTodayCnt6}</span>건</td>
			</tr>
		</tbody>
	</table>
</div>
<form name="frmMainPage" id="frmMainPage" method="post">
	<div class="half-left-area">
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
		<li class="more"><a href="#!" onclick="fn_board_list('BBSMSTR_000000000005');" title="더 보기">Q &amp; A 더 보기</a></li>
	</ul>
	</div>

	<div class="half-right-area">
		<h3>Q &amp; A</h3>
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
<form id="frmSubject" name="frmSubject" action="<c:url value='/lu/main/lmsUserMainPage.do'/>" method="post">
    <input type="hidden" id="pageSize" name="pageSize" value="10" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="1" /> 

						<div class="full-area">
							<h3>담당 개설교과 조회</h3>
					
						
							<div class="search-box-1 mt-020 mb-020">
								<select name="yyyy" id="yyyy" > 
									<option value="" >학년도</option>
									<c:forEach var="i" begin="0" end="2" step="1">
										<option value="${nowYear-i}" <c:if test="${subjectVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
									</c:forEach>								
								</select> 
								<select name="term" id="term">
									<option value="" >학기</option>
									<option value="1" <c:if test="${subjectVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
									<option value="2" <c:if test="${subjectVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
									<option value="3" <c:if test="${subjectVO.term eq '3' }" > selected="selected"  </c:if>>여름학기</option>
									<option value="4" <c:if test="${subjectVO.term eq '4' }" > selected="selected"  </c:if>>겨울학기</option>
								</select>
								<select name="deptCode" id="deptCode">
									<option value="">학과명</option>
									<c:forEach var="result" items="${deptCodeList}" varStatus="status">
										<option value="${result.codeId}" <c:if test="${subjectVO.deptCode eq result.codeId }" > selected="selected"  </c:if>>${result.codeName}</option>
									</c:forEach>		
								</select>
								<select name="subjectGrade" id="subjectGrade">
									<option value="">대상학년</option>
									<option value="1" <c:if test="${subjectVO.subjectGrade eq '1' }" > selected="selected"  </c:if>>1학년</option>
									<option value="2" <c:if test="${subjectVO.subjectGrade eq '2' }" > selected="selected"  </c:if>>2학년</option>
									<option value="3" <c:if test="${subjectVO.subjectGrade eq '3' }" > selected="selected"  </c:if>>3학년</option>
									<option value="4" <c:if test="${subjectVO.subjectGrade eq '4' }" > selected="selected"  </c:if>>4학년</option>
								</select>
								<select name="subjectType" id="subjectType">
									<option value="">과정구분</option>
									<option value="NORMAL" <c:if test="${subjectVO.subjectType eq 'NORMAL' }" > selected="selected"  </c:if>>학부</option>
									<option value="HSKILL" <c:if test="${subjectVO.subjectType eq 'HSKILL' }" > selected="selected"  </c:if>>고숙련</option>
								</select>
								<input type="text" name="subjectName" id="subjectName" value="${subjectVO.subjectName}" placeholder="교과명 검색" style="width:100px;" /><a href="#!" onclick="javascript:fn_search();">검색</a>
							</div><!-- E : search-box-1 -->


							<table class="type-2">
								<colgroup>

									<col style="width:50px" />
									<col style="width:80px" />
									<col style="width:100px" />
									<col style="width:60px" />
									
									<col style="width:*" />
									<col style="width:90px" />
									<col style="width:100px" />
									<col style="width:100px" />
								</colgroup>
								<thead>
									<tr>

										<th>학년도</th>
										<th>학기</th>
										<th>학과</th>
										<th>대상학년</th>
										
										<th>개설교과명</th>
										<th>분반</th>
										<th>담당교수</th>
										<th>온라인교육형태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="result" items="${resultList}" varStatus="status">
										<tr>

											<td>${result.yyyy}</td>
											<td>${result.termName}</td>
											<td>${result.deptName}</td>
											<td>${result.subjectGrade}학년</td>
											<td class="left"><a href="#fn_move_subject" onclick="fn_move_subject('${result.subjectTraningType}','${result.yyyy}','${result.term}','${result.subjectCode}','01','${result.subjectName}','${result.subjectType}','${result.onlineType}','${result.preSubjectCode}');" class="text">${result.subjectName }</a></td>
											<td>${result.subjectClass}</td>
											<td>${result.insName}</td>
											<td>${result.onlineTypeName}</td> 
										</tr>
									</c:forEach>
									<c:if test="${fn:length(resultList) == 0}">
										<tr>
											<td colspan="7"><spring:message code="common.nodata.msg" /></td>
										</tr>
									</c:if>	
								</tbody>
							</table><!-- E : type-2 -->
</div>
							
</form>
<!-- ############### END : /la/main/lmsAdminMainPage ############### -->