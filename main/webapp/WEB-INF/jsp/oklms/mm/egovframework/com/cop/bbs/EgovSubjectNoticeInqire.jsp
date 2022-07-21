<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
/**
 * @Class Name : /mm/egovframework/com/cop/bbs/EgovNoticeInqire.jsp
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
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}

function fn_egov_delete_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}
	if (confirm('<spring:message code="common.delete.msg" />')) {
		document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/deleteBoardArticle.do'/>";
		document.frm.submit();
	}
}

function fn_egov_moveUpdt_notice() {
	if ("<c:out value='${anonymous}'/>" == "true" && document.frm.password.value == '') {
		alert('등록시 사용한 패스워드를 입력해 주세요.');
		document.frm.password.focus();
		return;
	}

	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/forUpdateBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_addReply() {
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticleReply.do'/>";
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

<input type="hidden" name="yyyy" id="yyyy"  value="<c:out value='${yyyy}'/>" />
<input type="hidden" name="term" id="term"  value="<c:out value='${term}'/>" />
<input type="hidden" name="subjectCode" id="subjectCode"  value="<c:out value='${subjectCode}'/>" />
<input type="hidden" name="subClass" id="subClass"  value="<c:out value='${subClass}'/>" />

<!-- S : view-1 -->
<div id="container">
	
	
	<div id="contents-area">
	
		<dl class="bbs-view mt-020"> 
			<dt><span>
			
				<c:choose>
						<c:when test="${result.isExpired == 'T' && result.isExpired !='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<p>[(<c:out value="${result.subjectName}"/>)]</p> <B><c:out value="${result.nttSj}" /></B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}" />
							</c:if>
						</c:when>
						<c:when test="${result.isExpired !='T' && result.isExpired =='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}" /></B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}" />
							</c:if>
						</c:when>
						<c:when test="${result.useAt == 'N'}">
					  		<c:if test="${result.replyLc!=0}">
					  		<c:forEach begin="0" end="${result.replyLc}" step="1">
					  			&nbsp;
					  		</c:forEach>
					  		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
					  		</c:if>
							<c:if test="${'Y' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}" /></B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								 <p>[<c:out value="${result.subjectName}"/>]</p><c:out value="${result.nttSj}" />
							</c:if>
						</c:when>
						<c:otherwise>
					   	<c:if test="${result.replyLc!=0}">
					   		<c:forEach begin="0" end="${result.replyLc}" step="1">
					   			&nbsp;
					   		</c:forEach>
					   		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
					   	</c:if>
				  		<c:if test="${'Y' == result.topNoticeYn}">
							<a href="#"  class="text" ><p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}"/></B></a>
						</c:if>
						<c:if test="${'N' == result.topNoticeYn}">
							<a href="#"  class="text" ><p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}"/></a>
						</c:if>
						</c:otherwise>
				</c:choose>						
			
			</span></dt>
			<dd>
				<span>등록일</span> : <c:out value="${result.frstRegisterPnttm}" />
			</dd>
			
			<c:if test="${result.ntceBgnde != '10000101'}">
			<dd>
				<span>게시기간</span> : <c:out value="${result.ntceBgnde}" /> ~ <c:out value="${result.ntceEndde}" />
				<span>조회수</span> : <c:out value="${result.inqireCo}" />
			</dd>
			</c:if>

			<dd>
				<span>구분</span> : 		
								<c:choose>
							    	<c:when test="${result.topNoticeYn eq 'N'}">
							    		미노출
							    	</c:when>
							    	<c:otherwise>
							    		노출
							    	</c:otherwise>
							    </c:choose>
				<span>작성자</span> : 
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
			<dd><p><c:out value="${result.nttCn}" escapeXml="false" /></p></dd>

			<c:if test="${!empty resultFile.atchFileId }">
				<dd class="file"><span>첨부파일</span> : <a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a></dd>
			</c:if>

		</dl>



	<c:if test="${useComment == 'true'}">
		<c:if test="${bbsId == ''}">
		<c:import url="/mm/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${result.bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
			<c:param name="parm_lectureMenuMarkYn" value="${lectureMenuMarkYn}" />
		</c:import>
		
		<input type="hidden" id="temp_bbsId1" name="temp_bbsId1"  value="<c:out value='${result.bbsId}'/>" />
		<input type="hidden" id="temp_nttId1" name="temp_nttId1"  value="<c:out value='${result.nttId}'/>" />
		</c:if>
		<c:if test="${bbsId != ''}">
		<c:import url="/mm/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${result.bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
			<c:param name="parm_lectureMenuMarkYn" value="${lectureMenuMarkYn}" />
		</c:import>
		
		<input type="hidden" id="temp_bbsId2" name="temp_bbsId2"  value="<c:out value='${result.bbsId}'/>" />
		<input type="hidden" id="temp_nttId2" name="temp_nttId2"  value="<c:out value='${result.nttId}'/>" />
		</c:if>
	</c:if>

		<div class="btn-area align-right mt-010">
			<a href="#!" onclick="fn_egov_select_noticeList('1');return false;" class="gray-1 float-left">목록</a>

			<c:if test="${memType == 'PRT'||memType == 'CDP'||memType == 'COT'}">
				<c:if test="${result.frstRegisterId == sessionUniqId && result.commnetCount == 0}">
					<a href="#!" onclick="fn_egov_delete_notice();return false;" class="gray-1">삭제</a>
				</c:if>
				<c:if test="${result.frstRegisterId == sessionUniqId}">
					<a href="#!" onclick="fn_egov_moveUpdt_notice();return false;" class="yellow">수정</a>
				</c:if>
				<c:if test="${result.replyPosblAt == 'Y'}">
					<a href="#!" onclick="fn_egov_addReply();return false;" class="orange">답글</a>
				</c:if>
			</c:if>
			<c:if test="${memType == 'STD'}">
				<c:if test="${result.replyPosblAt == 'Y'}">
					<a href="#!" onclick="fn_egov_addReply();return false;" class="orange">답글</a>
				</c:if>
			</c:if>
		</div>
	</div><!-- E : view -->
</div><!-- E : content-area -->

</form>
<!-- E : 상세 보기 영역 (3 단 상세보기) -->
