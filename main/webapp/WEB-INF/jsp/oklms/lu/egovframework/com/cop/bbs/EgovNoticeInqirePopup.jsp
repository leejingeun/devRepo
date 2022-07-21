<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : /lu/egovframework/com/cop/bbs/EgovNoticeInqirePopup.jsp
 * @Description : 게시물 조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2017.01.05   이진근          최초 생성
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
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();
}

</script>

<!-- S : 상세 보기 영역 (3 단 상세보기) -->
<form name="frm" method="post" action="">
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
<input type="hidden" name="bbsId" value="<c:out value='${result.bbsId}'/>" >
<input type="hidden" name="nttId" value="<c:out value='${result.nttId}'/>" >
<input type="hidden" name="parnts" value="<c:out value='${result.parnts}'/>" >
<input type="hidden" name="sortOrdr" value="<c:out value='${result.sortOrdr}'/>" >
<input type="hidden" name="replyLc" value="<c:out value='${result.replyLc}'/>" >
<input type="hidden" name="nttSj" value="<c:out value='${result.nttSj}'/>" >
<input type="hidden" name="atchFileId" value="<c:out value='${result.atchFileId}'/>" >
<input type="hidden" name="replyPosblAt" value="<c:out value='${result.replyPosblAt}'/>" >
</form>

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<c:if test="${result.bbsId == 'BBSMSTR_000000000000'}">
			<h1>공지사항-상세</h1>
			</c:if>
			<c:if test="${result.bbsId == 'BBSMSTR_000000000001'}">
			<h1>FAQ-상세</h1>
			</c:if>
			<c:if test="${result.bbsId == 'BBSMSTR_000000000004'}">
			<h1>학사일정-상세</h1>
			</c:if>

			<table class="type-2">
				<colgroup>
					<col width="10%" />
					<col width="*" />
					<col width="10%" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th colspan="4"><c:out value="${result.nttSj}" /></th>
					</tr>
				</thead>
				<tbody>
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
						<td class="left">
							<c:out value="${result.frstRegisterPnttm}" />
						</td>
					</tr>
					<tr>
						<th>상단노출</th>
						<td class="left">
							<c:choose>
					    	<c:when test="${result.topNoticeYn == 'Y'}">
					    		노출
					    	</c:when>
					    	<c:otherwise>
					    		미노출
					    	</c:otherwise>
					    	</c:choose>
						</td>
						<th>조회수</th>
						<td class="left">
							<c:out value="${result.inqireCo}" />
						</td>
					</tr>
					<tr>
						<td colspan="4"  class="left">
							<!-- 네이버 에디터에서 팝업화면 내용에 이미지 추가시 익스플로러화면에서 with가 벗어나는 현상 아래구문 fn: replace로 해결함.-->
							<c:out value="${fn:replace(result.nttCn,'max-width','width')}" escapeXml="false" />
							<%-- <c:out value="${result.nttCn}" escapeXml="false" /> --%>
						</td>
					</tr>
				<c:if test="${not empty result.atchFileId}">
				<c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					<tr>
						<th>첨부파일</th>
						<td colspan="4"  class="left">
						<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${result.atchFileId}" />
						</c:import>
						</td>
					</tr>
				</c:if>
				<tr>
					<th>첨부파일 목록</th>
					<td colspan="4"  class="left">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${result.atchFileId}" />
					</c:import>
					</td>
				</tr>
				</c:if>

				</tbody>
			</table><!-- E : type-2 -->

			<div class="btn-area align-right mt-020">
				<a href="#" onclick="fn_egov_select_noticeList('1');return false;" class="gray-1  float-left">목록</a>
			</div><!-- E : btn-area -->
		</div><!-- E : wrapper -->
