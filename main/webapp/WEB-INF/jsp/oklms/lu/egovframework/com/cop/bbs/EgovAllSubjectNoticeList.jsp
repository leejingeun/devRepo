<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /lu/egovframework/com/cop/bbs/EgovSubjectNoticeList.jsp
  * @Description : 게시물 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2017.04.07   이진근          최초 생성
  *
  */
%>

<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />" ></script>
<script type="text/javascript">

/*====================================================================
화면 초기화
====================================================================*/
$(document).ready(function() {
	loadPage();
});

function loadPage() {

}

/*====================================================================
화면 사용 함수
====================================================================*/
function press(event) {
	if (event.keyCode==13) {
		fn_egov_select_noticeList('1');
	}
}

function fn_egov_addNotice() {
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_select_noticeList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}

//function fn_egov_inqire_notice(i, nttId, bbsId, yyyy, term, subjectCode, subClass) {
function fn_egov_inqire_notice(i, nttId, bbsId) {
	 if(bbsId == "") return false; //20150508
	document.frm.nttId.value = nttId;
	document.frm.bbsId.value = bbsId;
/* 	document.frm.yyyy.value = yyyy;
	document.frm.term.value = term;
	document.frm.subjectCode.value = subjectCode;
	document.frm.subClass.value = subClass; */
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_selectLastIndex() {
	document.frm.pageIndex.value = '1';
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}
</script>

<div id="">

<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000005'}">
	<h2>전체 공지사항</h2>
</c:if>
<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000006'}">
	<h2>전체 학습자료실</h2>
</c:if>
<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000007'}">
	<h2>전체 Q&A</h2>
</c:if>

<form name="frm" action ="<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="memType"  value="<c:out value='${memType}'/>" />
<input type="hidden" name="lectureMenuMarkYn"  value="<c:out value='${lectureMenuMarkYn}'/>" />

<div class="search-box-1 mb-020">
	<select id="searchCnd" name="searchCnd" onchange="">
		<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
		<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
		<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
	</select>
	<input type="text" id="searchWrd" name="searchWrd" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" placeholder="(ex)검색어 입력" onkeypress="press(event);">
	<a href="#@" onclick="fn_egov_select_noticeList('1'); return false;">검색</a>
</div><!-- E : search-box-1 -->
</form>

<ul class="page-sum mb-007">
	<li class="float-left">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b>건</li>
	<!-- <li class="float-left">총 24건 (1 / 1 Page)</li>
	<li class="float-right">
		PAGE VIEW : <input type="radio" name="radio" value="" checked> 10
		<input type="radio" name="radio" value=""> 20
		<input type="radio" name="radio" value=""> 50
		Lines
	</li> -->
</ul>

<table class="type-2">
	<colgroup>
		<%-- <col style="width:40px" /> --%>
		<col style="width:50px" />
		<col style="width:*" />
		<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
			<col style="width:120px" />
			<col style="width:120px" />
		</c:if>
		<col style="width:40px" />
		<col style="width:80px" />
		<col style="width:120px" />
		<col style="width:60px" />
	</colgroup>
	<thead>
		<tr>
			<!-- <th><input type="checkbox" id="check0" name="check0" onclick="javascript:com.checkAll('check0','check1');" class="choice" /></th> -->
			<th>No</th>
			<th>제목</th>
			<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<th>게시시작일</th>
				<th>게시종료일</th>
			</c:if>
			<th>첨부</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<%-- <td><input type="checkbox" id="check1" name="check1" value="${result.nttId}" class="choice"></td> --%>
			<c:choose>
				<c:when test="${result.replyPosblAt=='Y'}">
					<!-- 답장가능 게시판은 상단노출 선택 항목이 없다. -->
					<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
				</c:when>
				<c:otherwise>
					<!-- 답장불가능 게시판은 상단노출 선택 항목이 있다. -->
					<td>
						<c:if test="${'Y' == result.topNoticeYn}">
							<B>공지</B>
						</c:if>
						<c:if test="${'N' == result.topNoticeYn}">
							<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>
						</c:if>
					</td>
				</c:otherwise>
	  	  	</c:choose>
			<td class="left">
				<%-- <a href="#!" class="text"><span class="course">[<c:out value="${result.subjectName}"/>]</span> <c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}"/>]</a> --%>

				<c:choose>
						<c:when test="${result.isExpired == 'T' && result.isExpired !='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<span class="course">[(<c:out value="${result.subjectName}"/>)<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
							</c:if>
						</c:when>
						<c:when test="${result.isExpired !='T' && result.isExpired =='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
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
								<span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								 [<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
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
							<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
						</c:if>
						<c:if test="${'N' == result.topNoticeYn}">
							<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><span class="course">[<c:out value="${result.subjectName}"/>(<c:out value="${result.subClass}"/>)분반]</span> <c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
						</c:if>
						</c:otherwise>
				</c:choose>

			</td>
			<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<td><c:out value="${result.ntceBgnde}"/></td>
				<td><c:out value="${result.ntceEndde}"/></td>
			</c:if>
			<td>
				<c:if test="${'' != result.atchFileId && result.fileCount > 0}">
					<img src='/images/oklms/std/icon_attach.png'>
				</c:if>
			</td>
			<td><c:out value="${result.frstRegisterNm}"/></td>
			<td><c:out value="${result.frstRegisterPnttm}"/></td>
			<td><c:out value="${result.inqireCo}"/></td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
			<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<tr>
				 	<td class="none" colspan="9" ><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
			<c:if test="${brdMstrVO.bbsAttrbCode != 'BBSA01'}">
				<tr>
				 	<td class="none" colspan="7" ><spring:message code="common.nodata.msg" /></td>
				</tr>
			</c:if>
	    </c:if>
	</tbody>
</table><!-- E :  list-->

<ul class="page-num-btn mt-015">
	<li class="page-num-area">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_noticeList" />
	</li>

	<c:if test="${memType == 'PRT'|| memType == 'COT' || memType == 'CDP'}">
	<li class="page-btn-area">
		<!-- <a href="#!" onclick="fn_egov_delNotice();return false;" class="gray-1">선택 삭제</a> -->
		<c:if test="${boardVO.bbsId != 'BBSMSTR_000000000007'}">
			<a href="#!" onclick="fn_egov_addNotice();return false;" class="orange">작성</a>
		</c:if>
	</li>
	</c:if>

	<c:if test="${memType == 'STD'}">
	<li class="page-btn-area">
		<!-- <a href="#!" onclick="fn_egov_delNotice();return false;" class="gray-1">선택 삭제</a> -->
		<c:if test="${boardVO.bbsId eq 'BBSMSTR_000000000007'}">
			<a href="#!" onclick="fn_egov_addNotice();return false;" class="orange">작성</a>
		</c:if>
	</li>
	</c:if>
</ul><!-- E : page-num-btn -->

</div><!-- E : content-area -->



