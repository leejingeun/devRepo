<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /mm/egovframework/com/cop/bbs/EgovSubjectNoticeList.jsp
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
<!--
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
	if("<c:out value='${boardVO.subjectCode}'/>"==""){
		alert("먼저 교과를 선택하세요!");
		return;
	}
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/addBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_select_noticeList(pageNo) {
	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
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
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardArticle.do'/>";
	document.frm.submit();
}

function fn_egov_selectLastIndex() {
	document.frm.pageIndex.value = '1';
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}
function fn_lec_menu_display(value){
	
	var reqUrl = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	var params = value.split("||");
	
	$("#subjectTraningType").val(params[0]);
	$("#yyyy").val(params[1]);
	$("#term").val(params[2]);
	$("#subjectCode").val(params[3]);
	$("#subClass").val(params[4]);
	$("#subjectName").val(params[5]);
	$("#subjectType").val(params[6]);
	$("#onlineType").val(params[7]);
	 
	$("#frm").attr("target", "_self");
	$("#frm").attr("action", reqUrl);
	$("#frm").submit();
	 
}
function fn_egov_select_noticeListAdd() {
	document.frm.pageIndex.value = 1;
	document.frm.recordCountPerPage.value = <c:out value='${searchVO.recordCountPerPage+10}'/>;
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>";
	document.frm.submit();
}
//-->
</script>

			<div id="container">
 

<form name="frm"  id="frm" action ="<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="recordCountPerPage"  value="<c:out value='${searchVO.recordCountPerPage}'/>"  />
<input type="hidden" name="memType"  value="<c:out value='${memType}'/>" />
<input type="hidden" name="lectureMenuMarkYn"  value="<c:out value='${lectureMenuMarkYn}'/>" />

<input type="hidden" name="yyyy" id="yyyy"  value="<c:out value='${boardVO.yyyy}'/>" />
<input type="hidden" name="term" id="term"  value="<c:out value='${boardVO.term}'/>" />
<input type="hidden" name="subjectCode" id="subjectCode"  value="<c:out value='${boardVO.subjectCode}'/>" />
<input type="hidden" name="subClass" id="subClass"  value="<c:out value='${boardVO.subClass}'/>" />

				<ul id="search-area">
					<li style="text-align:left">
					
						<select id="subjectCodeValue" name="subjectCodeValue" style="display:block; width:100%; margin:3px 0;" onchange="javascript:fn_lec_menu_display(this.value);">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${boardVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}"  <c:if test="${boardVO.subjectCode eq menuProc.subjectCode }">selected</c:if> >${menuProc.subjectName} ${menuProc.subClass}반 </option>
						</c:forEach>							
							
						</select>
											
						<select id="searchCnd" name="searchCnd"   style="display:block; width:100%; margin:3px 0;">
							<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
							<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >내용</option>
							<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
						</select>
						<input type="text" id="searchWrd" name="searchWrd"  style="width:100%;"  value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" placeholder="(ex)검색어 입력" onkeypress="press(event);">
						<a href="#@" onclick="fn_egov_select_noticeList('1'); return false;" class="type-2">검색</a>
	
					</li>
				</ul>

</form>

			<div id="contents-area">		
				<div class="bbs-total mt-020">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b> 건의 게시물이 있습니다.</div>
				<c:if test="${memType == 'PRT'|| memType == 'COT' || memType == 'CDP'}">
					<c:if test="${not empty boardVO.subjectCode}">
						<div class="btn-area align-right"><a href="#!" onclick="fn_egov_addNotice();return false;" class="orange">작성</a></div>
					</c:if>
				</c:if>
				<c:if test="${memType == 'STD' }">
					<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000010'}">
						<div class="btn-area align-right"><a href="#!" onclick="fn_egov_addNotice();return false;" class="orange">작성</a></div>
					</c:if>
				</c:if>
				<dl class="bbs-list mt-005">
				<c:forEach var="result" items="${resultList}" varStatus="status">
			
					<dt>				
						<span>
				<c:choose>
						<c:when test="${result.isExpired == 'T' && result.isExpired !='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<p>[(<c:out value="${result.subjectName}"/>)]</p> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
							</c:if>
						</c:when>
						<c:when test="${result.isExpired !='T' && result.isExpired =='Y'}">
							<c:if test="${'Y' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								<p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
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
								<p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
							</c:if>
							<c:if test="${'N' == result.topNoticeYn}">
								 <p>[<c:out value="${result.subjectName}"/>]</p><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
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
							<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><p>[<c:out value="${result.subjectName}"/>]</p> <B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
						</c:if>
						<c:if test="${'N' == result.topNoticeYn}">
							<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><p>[<c:out value="${result.subjectName}"/>]</p> <c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
						</c:if>
						</c:otherwise>
				</c:choose>							
							</span>
							
					
					</dt>
					<dd>
						<span>구분</span> : 

						<c:if test="${'Y' == result.topNoticeYn}">
							공지
						</c:if>
						<c:if test="${'N' == result.topNoticeYn}">
							일반
						</c:if>
					</dd>
				<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
					<dd>
						<span>게시시작일</span> : ${result.ntceBgnde}
						<span>게시종료일</span> : ${result.ntceEndde}
					</dd>
				</c:if>
					<dd>
						<span>작성자</span> : <c:out value="${result.frstRegisterNm}"/>
						<span>조회수</span> : <c:out value="${result.inqireCo}"/>
						<span>등록일</span> : <c:out value="${result.frstRegisterPnttm}"/>
					</dd>
 				<c:if test="${'' != result.atchFileId}">
					<dd class="file"><span>파일</span> : <a href="#!">첨부파일</a></dd>
				</c:if>
				<c:if test="${'' == result.atchFileId}">
					<dd class="file"><span>파일</span> : <b>첨부파일 없음</b></dd>
				</c:if>
				</c:forEach>
				  
				</dl>

				<c:if test="${paginationInfo.lastPageNoOnPageList >1}">
					<c:if test="${searchVO.pageIndex < paginationInfo.lastPageNoOnPageList}">
						<div class="bbs-more"><a href="#!" onclick="javascript:fn_egov_select_noticeListAdd();">더 보기</a></div>
					</c:if>
				</c:if>

			</div><!-- E : contents-area -->
		</div><!-- E : content-area -->