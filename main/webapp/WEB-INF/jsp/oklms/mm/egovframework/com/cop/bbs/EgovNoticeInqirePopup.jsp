<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : /mm/egovframework/com/cop/bbs/EgovNoticeInqirePopup.jsp
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
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
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

<div id="container">
 	<div id="contents-area">
 	
 		<dl class="bbs-view mt-020"> 
				<dt><span><c:out value="${result.nttSj}" /></span></dt>
				<dd><span>작성자</span> :
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
				</dd>
				<dd>
					<span>등록일</span> : <c:out value="${result.frstRegisterPnttm}" />
				</dd>				
				<dd>
					<span>상단노출</span> : 
							<c:choose>
					    	<c:when test="${result.topNoticeYn == 'Y'}">
					    		노출
					    	</c:when>
					    	<c:otherwise>
					    		미노출
					    	</c:otherwise>
					    	</c:choose>
				</dd>					
				<dd>
					<span>조회수</span> : <c:out value="${result.inqireCo}" />
				</dd>				
				<dd>
					<span>내용</span> : <c:out value="${fn:replace(result.nttCn,'max-width','width')}" escapeXml="false" />
				</dd>				
								 
				<c:if test="${not empty result.atchFileId}">
				<c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					<dd>
						<span>첨부파일</span>:
						<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${result.atchFileId}" />
						</c:import>
					</dd>
				</c:if>
				
				<dd>
					<span>첨부파일 목록</span>:
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${result.atchFileId}" />
					</c:import>
				</dd>
				</c:if>


			</dl>
			<div class="btn-area align-right mt-020">
				<a href="#!" onclick="fn_egov_select_noticeList('1');return false;" class="gray-1  float-left">목록</a>
				<a href="/mm/main/lmsMainPage.do" class="gray-1  float-left">메인</a>
			</div><!-- E : btn-area -->
	</div>
</div><!-- E : wrapper -->
