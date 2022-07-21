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
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/addReplyBoardArticle.do'/>";
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

<input type="hidden" id="yyyy" name="yyyy"  value="<c:out value='${yyyy}'/>" />
<input type="hidden" id="term" name="term"  value="<c:out value='${term}'/>" />
<input type="hidden" id="subjectCode" name="subjectCode"  value="<c:out value='${subjectCode}'/>" />
<input type="hidden" id="subClass" name="subClass"  value="<c:out value='${subClass}'/>" />

<!-- S : view-1 -->
<div class="content">
		<c:if test="${result.bbsId == 'BBSMSTR_000000000013'}">
		<h1>공지사항-상세</h1>
		</c:if>
		<c:if test="${result.bbsId == 'BBSMSTR_000000000014'}">
		<h1>Q&A-상세</h1>
		</c:if>
		<c:if test="${result.bbsId == 'BBSMSTR_000000000015'}">
		<h1>A학습자료실-상세</h1>
		</c:if>	
			<table class="table_view">
				<colgroup>
					<col style="width:150px" />
					<col style="width:*" />
					<col style="width:150px" />
					<col style="width:*" />
					<col style="width:150px" />
					<col style="width:*" />
				</colgroup>
				<tr>
					<th class="title" colspan="6"><c:out value="${result.nttSj}" /></th>
				</tr>
				<tr>
					<th>학년도</th>
					<td><c:out value='${yyyy}'/>학년도</td>
					<th>학기</th>
					<td class="left"><c:out value='${term}'/>학기</td>
					<th>상단노출</th>
					<td class="none">
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
					<th>작성자</th>
					<td>
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
					<th>조회수</th>
					<td class="none"><c:out value="${result.inqireCo}" /></td>
				</tr>	
				<tr>
					<td class="left none" colspan="6" style="padding:30px;">
					<c:out value="${result.nttCn}" escapeXml="false" />
					<p style="height:200px;"></p>
					</td>
				</tr>	
<!-- 				<tr>
					<th>첨부파일</td>
					<td colspan="5"  class="none">
					<input type=file name='file1' style='display: none;'>
					<input type="button" class="table_file"  value="파일선택" onclick='document.all.file1.click(); document.all.file2.value=document.all.file1.value'/>
					<input type="text" class="table_grey" value="과제물파일_01.hwp" style="width:30%;"/> <input type="button" class="table_upload" value="업로드" /> <input type="button" class="table_delete" value="" />
					</td>
				</tr>	 -->
				
			<c:if test="${not empty result.atchFileId}">
				<c:if test="${result.bbsAttrbCode == 'BBSA02'}">
					<tr>
						<th>첨부이미지11</th>
						<td colspan="5"  class="none">
						<c:import url="/cmm/fms/selectImageFileInfs.do" charEncoding="utf-8">
							<c:param name="atchFileId" value="${result.atchFileId}" />
						</c:import>
						</td>
					</tr>
				</c:if>
			<tr>
				<th>첨부파일 목록</th>
				<td colspan="5"  class="none" style="border:0px solid #eb5c01;">
				<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${result.atchFileId}" />
				</c:import>
				</td>
			</tr>
			</c:if>
			</table>

			<!--
			학습근로자   : STD
			담당교수     : PRT,
			지도교수     : ATT,
			기업현장교사 : COT  
			-->
			<div class="btn_left">
				<a href="#@" onclick="fn_egov_select_noticeList('1');return false;"><input type="button" class="black" value="목록" /></a>
			</div>
			<c:if test="${memType == 'PRT'||memType == 'ATT'||memType == 'COT'}">
			<div class="btn_right">
				<c:if test="${result.frstRegisterId == sessionUniqId}">
					<a href="#@" onclick="fn_egov_delete_notice();return false;"><input type="button" class="black" value="삭제" /></a>
				</c:if>
				<c:if test="${result.replyPosblAt == 'Y'}">
					<a href="#@" onclick="fn_egov_addReply();return false;"><input type="button" class="yellow" value="답글작성" /></a>
				</c:if>
				<c:if test="${result.frstRegisterId == sessionUniqId}">
					<a href="#@" onclick="fn_egov_moveUpdt_notice();return false;"><input type="button" class="orenge" value="수정" /></a>
				</c:if>	
			</div>
			</c:if>
			<c:if test="${memType == 'STD'}">
			<div class="btn_right">
				<c:if test="${result.replyPosblAt == 'Y'}">
					<a href="#@" onclick="fn_egov_addReply();return false;"><input type="button" class="yellow" value="답글작성" /></a>
				</c:if>
			</div>	
			</c:if>
	</div>		
	
	<c:if test="${useComment == 'true'}"> 
	<br/>
		<c:if test="${bbsId == ''}">
		<c:import url="/lu/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${result.bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
		</c:import>
		</c:if>
		<c:if test="${bbsId != ''}">
		<c:import url="/lu/cop/cmt${prefix}/selectCommentList.do" charEncoding="utf-8">
			<c:param name="type" value="body" />
			<c:param name="parm_bbsId" value="${bbsId}" />
			<c:param name="parm_nttId" value="${result.nttId}" />
		</c:import>
		</c:if>
	</c:if>
</form>
<!-- E : 상세 보기 영역 (3 단 상세보기) -->
