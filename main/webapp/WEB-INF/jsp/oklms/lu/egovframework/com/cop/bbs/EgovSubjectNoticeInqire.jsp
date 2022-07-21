<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : /lu/egovframework/com/cop/bbs/EgovNoticeInqire.jsp
 * @Description : 게시물 조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2017.01.02   이진근          최초 생성
 *
 */
%>
<!-- la : EgovNoticeInqire.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />"></script>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">

function fn_egov_select_noticeList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}

function fn_egov_delete_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}
	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/deleteBoardArticle.do'/>";
		document.frm.submit();
	}
}

function fn_egov_moveUpdt_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}

	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/forUpdateBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_addReply() {
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticleReply.do'/>";
	document.frm.submit();
}
</script>

<!-- S : 상세 보기 영역 (3 단 상세보기) -->
<form name="frm" method="post" enctype="multipart/form-data" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" >
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" >
<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
<input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}'/>" >
<input type="hidden" name="replyPosblAt" value="<c:out value='${result.replyPosblAt}'/>" >
<input type="hidden" name="lectureMenuMarkYn" value="<c:out value='${lectureMenuMarkYn}'/>" />

<input type="hidden" id="yyyy" name="yyyy"  value="<c:out value='${yyyy}'/>" />
<input type="hidden" id="term" name="term"  value="<c:out value='${term}'/>" />
<input type="hidden" id="subjectCode" name="subjectCode"  value="<c:out value='${subjectCode}'/>" />
<input type="hidden" id="subClass" name="subClass"  value="<c:out value='${subClass}'/>" />

<!-- S : view-1 -->
<div id="">
	<c:if test="${result.bbsId == 'BBSMSTR_000000000008'}">
	<h2>교과별 공지사항-상세</h2>
	</c:if>
	<c:if test="${result.bbsId == 'BBSMSTR_000000000009'}">
		<h2>교과별 학습자료실-상세</h2>
	</c:if>
	<c:if test="${result.bbsId == 'BBSMSTR_000000000010'}">
		<h2>교과별 Q&A-상세</h2>
	</c:if>

	<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}(${currProcReadVO.subClass}분반)</span> ㅣ <c:out value='${yyyy}'/>학년도 / <c:out value='${term}'/>학기</h4>
	<table class="type-1 mb-020">
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

	<div class="group-area mb-025">
		<table class="type-2">
			<colgroup>
				<col style="width:100px" />
				<col style="width:35%" />
				<col style="width:100px" />
				<col style="width:*" />
			</colgroup>
			<tbody>
				<tr>
					<th class="title" colspan="6"><c:out value="${result.nttSj}" /> </th>
				</tr>
<%-- 				<tr>
					<th>카테고리</th>
					<td class="left">
					<c:choose>
				    	<c:when test="${result.categoryCd eq '01'}">
				    		전체공지
				    	</c:when>
				    	<c:otherwise>
				    		과정공지
				    	</c:otherwise>
				    </c:choose>
					</td>
					<th>과정명</th>
					<td class="left"><c:out value="${result.subjectName}" /></td>
				</tr> --%>
				<c:if test="${result.ntceBgnde != '10000101'}">
				<tr>
					<th>게시기간</th>
					<td class="left"><c:out value="${result.ntceBgnde}" /> ~ <c:out value="${result.ntceEndde}" /></td>
					<th>조회수</th>
					<td class="left"><c:out value="${result.inqireCo}" /></td>
				</tr>
				</c:if>
				<tr>
					<th>상단노출</th>
					<td class="left">
					<c:choose>
				    	<c:when test="${result.topNoticeYn eq 'N'}">
				    		미노출
				    	</c:when>
				    	<c:otherwise>
				    		노출
				    	</c:otherwise>
				    </c:choose>
					</td>
					<th>첨부파일</th>
					<td class="left" colspan="3">
					<c:if test="${!empty resultFile.atchFileId }">
						<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>
					</c:if>
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td class="left">
					<c:choose>
				    	<c:when test="${anonymous == 'true'}">
				    		******
				    	</c:when>
				    	<c:when test="${result.ntcrNm == null || result.ntcrNm == ''}">
				    		<c:out value="${result.frstRegisterNm}" />
				    	</c:when>
				    	<c:otherwise>
				    		<c:out value="${result.ntcrNm}" />
				    	</c:otherwise>
				    </c:choose>
					</td>
					<th>작성일</th>
					<td class="left"><c:out value="${result.frstRegisterPnttm}" /></td>
				</tr>
				<c:if test="${result.ntceBgnde eq '10000101'}">
				<tr>
					<th>조회수</th>
					<td class="left"><c:out value="${result.inqireCo}" /></td>
				</tr>
				</c:if>
				<tr>
					<td class="text-area" colspan="4">
					<c:out value="${result.nttCn}" escapeXml="false" />
				</tr>
			</tbody>
		</table>

		<div class="btn-area align-right mt-010">
			<a href="#!" onclick="fn_egov_select_noticeList('1');return false;" class="gray-1 float-left">목록</a>

			<c:if test="${memType == 'PRT'||memType == 'CDP'|| memType == 'COT' || memType == 'STD'}">
				<c:if test="${result.frstRegisterId == sessionUniqId && result.commnetCount == 0}">
					<a href="#!" onclick="fn_egov_delete_notice();return false;" class="gray-1">삭제</a>
				</c:if>
				<c:if test="${result.frstRegisterId == sessionUniqId && result.commnetCount == 0}">
					<a href="#!" onclick="fn_egov_moveUpdt_notice();return false;" class="yellow">수정</a>
				</c:if>
				<c:if test="${result.replyPosblAt == 'Y' && memType != 'STD'}">
					<a href="#!" onclick="fn_egov_addReply();return false;" class="orange">답글작성</a>
				</c:if>
			</c:if>
			<c:if test="${memType == 'STD'}">
				<c:if test="${result.replyPosblAt == 'Y'}">
					<c:if test="${result.frstRegisterId == sessionUniqId}">
						<a href="#!" onclick="fn_egov_addReply();return false;" class="orange">답글작성</a>
					</c:if>
				</c:if>
			</c:if>
		</div>

	</div><!-- E : view -->

	<c:if test="${useComment == 'true'}">
	<c:if test="${bbsId == ''}">
		<c:import url="/lu/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${result.bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
			<c:param name="parm_lectureMenuMarkYn" value="${lectureMenuMarkYn}" />
		</c:import>

		<input type="hidden" id="temp_bbsId1" name="temp_bbsId1"  value="<c:out value='${result.bbsId}'/>" />
		<input type="hidden" id="temp_nttId1" name="temp_nttId1"  value="<c:out value='${result.nttId}'/>" />
		</c:if>
		<c:if test="${bbsId != ''}">
		<c:import url="/lu/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
			<c:param name="parm_lectureMenuMarkYn" value="${lectureMenuMarkYn}" />
		</c:import>

		<input type="hidden" id="temp_bbsId2" name="temp_bbsId2"  value="<c:out value='${bbsId}'/>" />
		<input type="hidden" id="temp_nttId2" name="temp_nttId2"  value="<c:out value='${result.nttId}'/>" />
		</c:if>
	</c:if>

</div><!-- E : content-area -->

</form>
<!-- E : 상세 보기 영역 (3 단 상세보기) -->
