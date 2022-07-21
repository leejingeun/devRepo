<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : /la/egovframework/com/cop/bbs/EgovNoticeInqire.jsp
 * @Description : 게시물 조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자            수정내용
 * @ -------        --------    ---------------------------
 * @ 2009.03.23   이삼섭          최초 생성
 * @ 2009.06.26   한성곤          2단계 기능 추가 (댓글관리, 만족도조사)
 *
 *  @author 공통서비스 개발팀 이삼섭
 *  @since 2009.03.23
 *  @version 1.0
 *  @see
 *
 */
%>
<!-- la : EgovNoticeInqire.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />"></script>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript">

function onloading() {
	if ("<c:out value='${msg}'/>" != "") {
		alert("<c:out value='${msg}'/>");
	}
}

function fn_egov_select_noticeList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}

function fn_egov_delete_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}

	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/deleteBoardArticle.do'/>";
		document.frm.submit();
	}
}

function fn_egov_moveUpdt_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}

	document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/forUpdateBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_addReply() {
	document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/addReplyBoardArticle.do'/>";
	document.frm.submit();
}
</script>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<%-- <c:if test="${useComment == 'true'}">
<c:import url="/la/cop/cmt/selectCommentList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if> --%>
<%--
<c:if test="${useSatisfaction == 'true'}">
<c:import url="/la/cop/stf/selectSatisfactionList.do" charEncoding="utf-8">
	<c:param name="type" value="head" />
</c:import>
</c:if>
<c:if test="${useScrap == 'true'}">
<script type="text/javascript">
	function fn_egov_addScrap() {
		document.frm.action = "<c:url value='/la/cop/scp/addScrap.do'/>";
		document.frm.submit();
	}
</script>
</c:if>
 --%>
<!-- 2009.06.29 : 2단계 기능 추가  -->
<%-- <title><c:out value='${result.bbsNm}'/> - 글조회</title> --%>

<!-- <body onload="onloading();"> -->

<!-- S : 상세 보기 영역 (3 단 상세보기) -->

<div class="title-name-1"><c:out value='${result.bbsNm}'/> - 글조회</div>
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

<!-- S : view-1 -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
	<!-- 상세화면에서 답글작성여부가 N인 게시판은 노출여부 항목을 표시 -->
	<c:choose>
		<c:when test="${result.replyPosblAt != 'Y'}">
			<tr>
				<th>제목</th>
				<td colspan="8"><c:out value="${result.nttSj}" /></td>
			</tr>
			<tr>
				<th width="120px">작성자</th>
				<td><c:choose>
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
				<th width="80px">작성시간</th>
				<td><c:out value="${result.frstRegisterPnttm}" /></td>
				<th width="80px">조회수</th>
				<td><c:out value="${result.inqireCo}" /></td>
				<th width="80px">상단노출</th>
				<td>
					<c:choose>
				    	<c:when test="${result.topNoticeYn == 'Y'}">
				    		노출
				    	</c:when>
				    	<c:otherwise>
				    		미노출
				    	</c:otherwise>
				    </c:choose>
				</td>
			</tr>
			<tr>
				<th>글내용</th>
				<td colspan="8"><c:out value="${result.nttCn}" escapeXml="false" /></td>
			</tr>
			<c:if test="${not empty result.atchFileId}">
				<c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					<tr>
						<th height="23" >첨부이미지</th>
						<td colspan="8">
						<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${result.atchFileId}" />
						</c:import>
						</td>
					</tr>
				</c:if>
			<tr>
				<th height="23">첨부파일 목록</th>
				<td colspan="8">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
				</td>
			</tr>
			</c:if>
			<c:if test="${anonymous == 'true'}">
			<tr>
				<th height="23"><spring:message code="cop.password" /></th>
				<td colspan="8">
				<input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
				</td>
			</tr>
			</c:if>
		</c:when>
		<c:otherwise>
			<tr>
				<th>제목</th>
				<td colspan="6"><c:out value="${result.nttSj}" /></td>
			</tr>
			<tr>
				<th width="120px">작성자</th>
				<td><c:choose>
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
				<th width="80px">작성시간</th>
				<td><c:out value="${result.frstRegisterPnttm}" /></td>
				<th width="80px">조회수</th>
				<td><c:out value="${result.inqireCo}" /></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td colspan="6"><c:out value="${result.nttCn}" escapeXml="false" /></td>
			</tr>
			<c:if test="${not empty result.atchFileId}">
				<c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					<tr>
						<th height="23" >첨부이미지</th>
						<td colspan="6">
						<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${result.atchFileId}" />
						</c:import>
						</td>
					</tr>
				</c:if>
			<tr>
				<th height="23">첨부파일 목록</th>
				<td colspan="6">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
				</td>
			</tr>
			</c:if>
			<c:if test="${anonymous == 'true'}">
			<tr>
				<th height="23"><spring:message code="cop.password" /></th>
				<td colspan="6">
				<input name="password" type="password" size="20" value="" maxlength="20" title="비밀번호입력">
				</td>
			</tr>
			</c:if>    		
		</c:otherwise>
	</c:choose>
	</tbody>
</table>
<!-- E : view-1 -->
<!-- S : page-btn -->
<div class="page-btn">
<%-- 							 <c:if test="${result.frstRegisterId == sessionUniqId}"> --%>
	      <a href="javascript:fn_egov_moveUpdt_notice()">수정</a>
	      <a href="javascript:fn_egov_delete_notice()">삭제</a>
<%-- 						     </c:if> --%>
	     <c:if test="${result.replyPosblAt == 'Y'}"> 
	      <a href="javascript:fn_egov_addReply()">답글작성</a>
          </c:if> 
      <a href="javascript:fn_egov_select_noticeList('1')">목록</a>
      <!-- 2009.06.29 : 2단계 기능 추가  -->
      <c:if test="${useScrap == 'true'}">
	      <a href="javascript:fn_egov_addScrap()">스크랩</a>
         </c:if>
</div>
<!-- E : page-btn -->

<!-- 2009.06.29 : 2단계 기능 추가  -->
	<c:if test="${useComment == 'true'}"> 
	<br/>
	<c:import url="/la/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
		<c:param name="type" value="body" />
		<c:param name="parm_bbsId" value="${result.bbsId}" />
		<c:param name="parm_nttId" value="${result.nttId}" />
	</c:import>
	</c:if> 
<%--
						<c:if test="${useSatisfaction == 'true'}">
							<br/>
							<c:import url="/la/cop/stf${prefix}/selectSatisfactionList.do" charEncoding="utf-8">
								<c:param name="type" value="body" />
							</c:import>
						</c:if>
 --%>
<!-- 2009.06.29 : 2단계 기능 추가  -->						
</form>
						<!-- E : 상세 보기 영역 (3 단 상세보기) -->
