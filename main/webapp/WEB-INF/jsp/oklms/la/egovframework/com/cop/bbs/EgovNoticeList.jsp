<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images/egovframework/com/cop/bbs/"/>
<%
 /**
  * @Class Name : /la/egovframework/com/cop/bbs/EgovNoticeList.jsp
  * @Description : 게시물 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.19   이삼섭          최초 생성
  * @ 2011.11.11   이기하          익명게시판 검색시 작성자 제거
  * @ 2015.05.08   조정국          표시정보 클릭시 이동링크 제한 : bbsId가 없는 요청은 처리제한.
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.19
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- la : EgovNoticeList.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
<style>
<!--
table.list-1 td {
/*  @import url(/html/egovframework/com/cmm/utl/htmlarea3.0/htmlarea.css); */
 text-align: left;
 } 
 
 table.list-1 td.lt_text1	{text-align:center;}
 
}
-->
</style>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />" ></script>
<c:choose>
<c:when test="${preview == 'true'}">
<script type="text/javascript">
<!--
	function press(event) {
	}

	function fn_egov_addNotice() {
	}

	function fn_egov_select_noticeList(pageNo) {
	}

	function fn_egov_inqire_notice(nttId, bbsId) {
	}
//-->
</script>
</c:when>
<c:otherwise>
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_noticeList('1');
		}
	}

	function fn_egov_addNotice() {
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_noticeList(pageNo) {
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_notice(i, nttId, bbsId) {
		 if(bbsId == "") return false; //20150508
		document.frm.nttId.value = nttId;
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardArticle.do'/>";
		document.frm.submit();
	}
</script>
</c:otherwise>
</c:choose>
<%-- <title><c:out value="${brdMstrVO.bbsNm}"/></title> --%>

<!-- E : search-list-1 (검색조건 영역) -->
<form name="frm" action ="<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<ul class="search-list-1">
	<li>
		<span>검색 조건 선택</span>
		<select name="searchCnd" class="select" title="검색조건선택" style="width: 150px;">
			<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
			<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
			<c:if test="${anonymous != 'true'}">
			<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
			</c:if>
		</select>
	</li>
	<li>
		<span>검색 단어</span>
		<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
	</li>
</ul>
</form>
<!-- E : search-list-1 (검색조건 영역) -->
<div class="search-btn-area"><a href="#@" onclick="fn_egov_select_noticeList('1'); return false;">조회하기</a></div>


<!-- S : board-info (게시판 버튼 및 구분selectBox ) -->
<ul class="board-info">
	<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }"/> 건</li>
</ul>
<!-- E : board-info (게시판 버튼 및 구분selectBox ) -->


<!-- S : list (게시판 목록 영역) -->
<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="30px">번호</th>
			<th>제목</th>
	   	<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
		    <th width="70px">게시시작일</th>
		    <th width="70px">게시종료일</th>
	   	</c:if>
	   	<c:if test="${anonymous != 'true'}">
	    	<th width="50px">작성자</th>
	    </c:if>
		    <th width="70px">작성일</th>
		    <th width="40px">조회수</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
		  <!--td class="lt_text3"><input type="checkbox" name="check1" class="check2"></td-->
		  <c:choose>
				<c:when test="${result.replyPosblAt=='Y'}">
					<!-- 답장가능 게시판은 상단노출 선택 항목이 없다. -->
					<td class="lt_text1"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				</c:when>
				<c:otherwise>
					<!-- 답장불가능 게시판은 상단노출 선택 항목이 있다. -->
					<td class="lt_text1">
						<c:if test="${'Y' == result.topNoticeYn}">
							<B>공지</B>
						</c:if>			
						<c:if test="${'N' == result.topNoticeYn}">
							<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>
						</c:if>
					</td>
				</c:otherwise>
		  </c:choose>
		<td class="listLeft">
			<c:if test="${'BBSA02' eq brdMstrVO.bbsAttrbCode}">
			<img src='<c:out value="${result.nttCnImg}" />' width="100px;" height="100px;"/>
			</br/>
			</c:if>
			
			<input type="hidden" name="isExpiredTemp_<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>"  value="<c:out value='${result.isExpired}'/>" />
			<input type="hidden" name="topNoticeYnTemp_<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>"  value="<c:out value='${result.topNoticeYn}'/>" />
			
			<c:choose>
				<c:when test="${result.isExpired == 'T' && result.isExpired !='Y'}">
					<c:if test="${'Y' == result.topNoticeYn}">
						<B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
					</c:if>			
					<c:if test="${'N' == result.topNoticeYn}">
						<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
					</c:if>
				</c:when>
				<c:when test="${result.isExpired !='T' && result.isExpired =='Y'}">
					<c:if test="${'Y' == result.topNoticeYn}">
						<B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
					</c:if>			
					<c:if test="${'N' == result.topNoticeYn}">
						<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
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
							<B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
					</c:if>			
					<c:if test="${'N' == result.topNoticeYn}">
						<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
					</c:if>
				</c:when>
				<c:otherwise>
			   	<c:if test="${result.replyLc!=0}">
			   		<c:forEach begin="0" end="${result.replyLc}" step="1">
			   			&nbsp;
			   		</c:forEach>
			   		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
			   	</c:if>
			  		<span class="link">
				  		<c:if test="${'Y' == result.topNoticeYn}">
							<a href="#"  onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
						</c:if>			
						<c:if test="${'N' == result.topNoticeYn}">
							<a href="#"  onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
						</c:if>
			  		</span>
				</c:otherwise>
			</c:choose>
		</td>
		<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
		 <td class="lt_text1"><c:out value="${result.ntceBgnde}"/></td>
		 <td class="lt_text1"><c:out value="${result.ntceEndde}"/></td>
		</c:if>
		<c:if test="${anonymous != 'true'}">
			<td class="lt_text1"><c:out value="${result.frstRegisterNm}"/></td>
		</c:if>
		<td class="lt_text1"><c:out value="${result.frstRegisterPnttm}"/></td>
		<td class="lt_text1"><c:out value="${result.inqireCo}"/></td>
		</tr>
	</c:forEach>
	<c:if test="${fn:length(resultList) == 0}">
		<tr>
		 	<c:choose>
			<c:when test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<td class="lt_text1" colspan="7" ><spring:message code="common.nodata.msg" /></td>
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test="${anonymous == 'true'}">
		 			<td class="lt_text1" colspan="4" ><spring:message code="common.nodata.msg" /></td>
		 		</c:when>
		 		<c:otherwise>
		 			<td class="lt_text1" colspan="5" ><spring:message code="common.nodata.msg" /></td>
		 		</c:otherwise>
		 	</c:choose>
			</c:otherwise>
		</c:choose>
		</tr>
	</c:if>
	</tbody>
</table>
<!-- E : list (게시판 목록 영역) -->


<!-- S : page-num (페이징 영역) -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_noticeList" />
</div>
<!-- E : page-num (페이징 영역) -->

<!-- S : page-btn -->
<div class="page-btn">
	<c:if test="${brdMstrVO.authFlag == 'Y'}"><a href="<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>" onClick="javascript:fn_egov_addNotice(); return false;"><spring:message code="button.create" /></a></c:if>
</div>
<!-- E : page-btn -->	