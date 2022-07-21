<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /mm/egovframework/com/cop/bbs/SearchEgovNoticeListPopup.jsp
  * @Description : 게시물 팝업 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2017.01.05   이진근          최초 생성
  *
  */
%>
<c:if test="${anonymous == 'true'}"><c:set var="prefix" value="/anonymous"/></c:if>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />" ></script>
<script type="text/javascript">
var selectRecordIndex = "";
/*====================================================================
화면 초기화
====================================================================*/
$(document).ready(function() {
	loadPage();
});

function loadPage() {
	selectRecordIndex = "<c:out value='${selectRecordIndex}'/>";
	if(selectRecordIndex == ''){
		$("input:radio[name=selectRecordIndex][value=10]" ).attr("checked", "checked");
	}else{
		$("input:radio[name=selectRecordIndex][value=${selectRecordIndex}]" ).attr("checked", "checked");
	}

}

/*====================================================================
화면 사용 함수
====================================================================*/
function press(event) {
	if (event.keyCode==13) {
		fn_egov_select_noticeList('1');
	}
}

function fn_egov_select_noticeList(pageNo) {
	var searchCnd = "";
	searchCnd = document.frm.searchCnd.value;

	if(searchCnd == ''){
		alert("제목 혹은 작성자를 선택후 검색하세요");
		$("#searchCnd").focus();
		return false;
	}

	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();
}

function fn_egov_select_paging_noticeList(pageNo) {

	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();
}

//공지사항, 주요학사일정, 자묻는질문팝업 상세
function fn_egov_notice(i, nttId, bbsId) {
 
	var reqUrl = "/mm/cop/bbs/"+bbsId+"/popupSelectBoardArticle.do";
	document.frm.nttId.value = nttId;
	document.frm.action = reqUrl;
	document.frm.submit();
}
function fn_egov_select_noticeListAdd(){
	document.frm.recordCountPerPage.value = <c:out value='${searchVO.recordCountPerPage+10}'/>;
	document.frm.action = "<c:url value='/mm/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();	
}
</script>

<!-- 회원정보 팝업용 끝 -->
<!-- E : search-list-1 (검색조건 영역) -->
<form id="popupForm" name="popupForm"  method="post">
</form>

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="container">
<form id="frm" name="frm" action ="" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="memType"  value="<c:out value='${memType}'/>" />
<input type="hidden" name="selectRecordIndex"  value="10" />
<input type="hidden" name="recordCountPerPage"  value="<c:out value='${searchVO.recordCountPerPage}'/>"  />
 
				<ul id="search-area">
					<li style="text-align:left">
						<select id="searchCnd" name="searchCnd" style="width:100%" title="검색조건선택">
						<option selected="selected" value=''>--선택--</option>
						<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
						<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
						</select>
						<input name="searchWrd" type="text" style="width:100%" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
						<a href="#!"  class="type-2" onclick="fn_egov_select_noticeList('1'); return false;" >검색</a>
					</li>
				</ul>

</form>

			<div id="contents-area">		
				<div class="bbs-total mt-020">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b> 건 </div>
				<dl class="bbs-list mt-005">
				
					<c:forEach var="result" items="${resultList}" varStatus="status">
					
					<dt>				
						<span>
							<c:if test="${'BBSA02' eq brdMstrVO.bbsAttrbCode}">
							<img src='<c:out value="${result.nttCnImg}" />' width="100px;" height="100px;"/>
							</c:if>
							<input type="hidden" name="isExpiredTemp_<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>"  value="<c:out value='${result.isExpired}'/>" />
							<input type="hidden" name="topNoticeYnTemp_<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>"  value="<c:out value='${result.topNoticeYn}'/>" />
							<c:choose>
								<c:when test="${result.isExpired == 'T' && result.isExpired !='Y'}">
									<a href="#!"  class="text-link" onclick="javascript:fn_egov_notice('${status.count}','${result.nttId}','${result.bbsId}');return false;">
									<c:if test="${'Y' == result.topNoticeYn}">
										<B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
									</c:if>
									<c:if test="${'N' == result.topNoticeYn}">
										<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
									</c:if>
									</a>
								</c:when>
								<c:when test="${result.isExpired !='T' && result.isExpired =='Y'}">
									<a href="#!"  class="text-link" onclick="javascript:fn_egov_notice('${status.count}','${result.nttId}','${result.bbsId}');return false;">
									<c:if test="${'Y' == result.topNoticeYn}">
										<B><c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]</B>
									</c:if>
									<c:if test="${'N' == result.topNoticeYn}">
										<c:out value="${result.nttSj}" /> [<c:out value="${result.commnetCount}" />]
									</c:if>
									</a>
								</c:when>
								<c:when test="${result.useAt == 'N'}">
									<a href="#!"  class="text-link" onclick="javascript:fn_egov_notice('${status.count}','${result.nttId}','${result.bbsId}');return false;">
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
									</a>
								</c:when>
								<c:otherwise>
							   	<c:if test="${result.replyLc!=0}">
							   		<c:forEach begin="0" end="${result.replyLc}" step="1">
							   			&nbsp;
							   		</c:forEach>
							   		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
							   	</c:if>
							   	
						  		<c:if test="${'Y' == result.topNoticeYn}">
									<a href="#!"  class="text-link" onclick="javascript:fn_egov_notice('${status.count}','${result.nttId}','${result.bbsId}');return false;"><B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
								</c:if>
								<c:if test="${'N' == result.topNoticeYn}">
									<a href="#!"  class="text-link" onclick="javascript:fn_egov_notice('${status.count}','${result.nttId}','${result.bbsId}');return false;"><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
								</c:if>
								
								</c:otherwise>
							</c:choose>
													
						</span>
					</dt>
					<dd>
						<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
						<span>게시시작일</span>:<c:out value="${result.ntceBgnde}"/>
						<span>게시종료일</span>:<c:out value="${result.ntceEndde}"/>
						</c:if>					
					</dd>
					<dd>
						<span>작성일</span>:<c:out value="${result.frstRegisterPnttm}"/>
						<span>작성자</span>:<c:out value="${result.frstRegisterPnttm}"/>
					</dd>
					<dd>
						<span>조회수</span>:<c:out value="${result.inqireCo}"/>
					</dd>
		
				 	</c:forEach>
				</dl>
			 
				<c:if test="${paginationInfo.lastPageNoOnPageList >1}">
					<c:if test="${searchVO.pageIndex < paginationInfo.lastPageNoOnPageList}">
						<div class="bbs-more"><a href="#!" onclick="javascript:fn_egov_select_noticeListAdd();">더 보기</a></div>
					</c:if>
				</c:if>

	
			</div><!-- E : contents-area -->
		</div><!-- E : content-area -->