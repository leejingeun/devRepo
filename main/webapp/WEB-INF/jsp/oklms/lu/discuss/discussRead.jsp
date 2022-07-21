<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/discuss/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {

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
	}

	/*====================================================================
	사용자 정의 함수
	====================================================================*/
	/* 토론의견 목록 검색어 엔터 이벤트 */
	function press(event) {
		if (event.keyCode==13) {
		}
	}

	/* 토론의견 목록 검색 */
	function fn_opinionSearch(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "getDiscuss.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론 수정 페이지로 이동 */
	function fn_updt(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateDiscuss.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론 목록 페이지로 이동 */
	function fn_list(){
		$("#discussId").val('');
		$("#discussOpinionId").val('');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listDiscuss.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론삭제 */
	function fn_delt(){
		if( confirm("생성된 토론을 삭제하겠습니까?") ) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteDiscuss.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견 신규 페이지로 이동 */
	function fn_cret(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertDiscussOpinion.do";

		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론의견 상세조회 페이지로 이동 */
	function fn_read( param1, param2 ){

		$("#discussOpinionId").val( param1 );
		$("#discussId").val( param2 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "getDiscussOpinion.do";

		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

</script>

<form id="frmDiscuss" name="frmDiscuss" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="discussOpinionId" name="discussOpinionId" value="${discussVO.discussOpinionId}" />

<div id="">
<h2>토론</h2>
<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

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

<h2>토론상세조회</h2>
<div class="group-area mb-010">
	<table class="type-write">
		<colgroup>
			<col style="width:130px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th>제목</th>
				<td>${discussReadVO.title}</td>
			</tr>
			<tr>
				<th>공개일</th>
				<td>${discussReadVO.startDate} ${discussReadVO.startHour}:${discussReadVO.startMin}</td>
			</tr>
			<tr>
				<th>마김일</th>
				<td>${discussReadVO.endDate} ${discussReadVO.endHour}:${discussReadVO.endMin}</td>
			</tr>
			<tr>
				<th>평가</th>
				<td>${discussReadVO.evalYnName}</td>
			</tr>
			<tr>
				<th>배점</th>
				<td>${discussReadVO.evalScore}<c:if test="${discussReadVO.evalYnName != '미평가'}">점</c:if></td>
			</tr>
			<tr>
				<th>점수공개</th>
				<td>${discussReadVO.scoreOpenYnName} (본인에게만 점수가 공개됩니다.) </td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${discussReadVO.content}</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td class="left">
				<c:if test="${!empty resultFile.atchFileId }">
					<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
				</td>
			</tr>
		</tbody>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">취소</a>
		<c:if test="${discussVO.sessionMemType eq 'PRT' || discussVO.sessionMemType eq 'CDP'}">
		<a href="#fn_delt" onclick="javascript:fn_delt(); return false" class="gray-1">삭제</a>
		<a href="#fn_updt" onclick="javascript:fn_updt(); return false" class="yellow">수정</a>
		</c:if>
	</div><!-- E : btn-area -->
</div><!-- E : 토론상세조회 -->

<h2>의견</h2>
<div class="search-box-1 mb-010">
	<select id="searchCondition" name="searchCondition" onchange="">
		<option value="searchTitle" ${'searchTitle' == discussVO.searchCondition ? 'selected="selected"' : '' }>제목</option>
		<option value="searchInsUserName" ${'searchInsUserName' == discussVO.searchCondition ? 'selected="selected"' : '' }>작성자</option>
		<option value="searchContent" ${'searchContent' == discussVO.searchCondition ? 'selected="selected"' : '' }>내용</option>
	</select>
	<input type="text" id="searchKeyword" name="searchKeyword" placeholder="(ex)검색어 입력" value="${discussVO.searchKeyword}"/>
	<a href="#fn_opinionSearch" onclick="javascript:fn_opinionSearch(); return false" onkeypress="this.onclick();">검색</a>
</div><!-- E : search-box-1 -->

<table class="type-2">
	<colgroup>
		<col style="width:50px" />
		<col style="width:*" />
		<col style="width:140px" />
		<col style="width:140px" />
		<col style="width:140px" />
		<col style="width:50px" />
		<col style="width:50px" />
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>학번</th>
			<th>작성일</th>
			<th>추천</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultDiscussOpinionList}" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td class="left">
				<a href="fn_read" onclick="javascript:fn_read('${result.discussOpinionId}','${result.discussId}'); return false" class="text">
					${result.title} <c:if test="${result.commentCnt > 0}"> [${result.commentCnt}] </c:if>
				</a>
			</td>
			<td>${result.memName}</td>
			<td>${result.memId}</td>
			<td>${result.insertDate}</td>
			<td>${result.goodCnt}</td>
			<td>${result.hitCnt}</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultDiscussOpinionList) == 0}">
		<tr>
			<td colspan="7"><spring:message code="common.nosarch.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E : 의견 -->

<div class="btn-area align-right mt-010">
	<c:if test="${discussVO.sessionMemType eq 'STD'}">
	<a href="#fn_cret" onclick="javascript:fn_cret(); return false" class="orange">글쓰기</a>
	</c:if>
</div><!-- E : btn-area -->
</div><!-- E : content-area -->
</form>

