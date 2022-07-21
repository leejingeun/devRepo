<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /lu/egovframework/com/cop/bbs/SearchEgovNoticeListPopup.jsp
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
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();
}

function fn_egov_select_paging_noticeList(pageNo) {

	document.frm.pageIndex.value = pageNo;
	document.frm.action = "<c:url value='/lu/cop/bbs${prefix}/${pathBbsIdStr}/popupSelectBoardList.do'/>";
	document.frm.submit();
}

//공지사항, 주요학사일정, 자묻는질문팝업 상세
function fn_egov_inqire_notice(i, nttId, bbsId) {
	if(nttId == "") return false;
	if(bbsId == "") return false;
	
	document.frm.nttId.value = nttId;
	document.frm.bbsId.value = bbsId;
	
	var reqUrl = "/lu/cop/bbs/"+bbsId+"/popupSelectBoardArticle.do";

	$("#frm").attr("action", reqUrl); 
	$("#frm").attr("target", "_self");
	$("#frm").submit();
}

</script>

<!-- 회원정보 팝업용 끝 -->
<!-- E : search-list-1 (검색조건 영역) --> 
<form id="frm" name="frm" action ="" method="post">
<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
<input type="hidden" name="nttId"  value="0" />
<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="memType"  value="<c:out value='${memType}'/>" />
<input type="hidden" name="selectRecordIndex"  value="10" />

<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000000'}">
			<h1>공지사항</h1>
			</c:if>
			<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000001'}">
			<h1>FAQ</h1>
			</c:if>
			<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000004'}">
			<h1>학사일정</h1>
			</c:if>

			<div class="search-box-1">
				<select id="searchCnd" name="searchCnd" title="검색조건선택">
				<option selected="selected" value=''>--선택--</option>
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
			</select>
			<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
				<a href="#!" onclick="fn_egov_select_noticeList('1'); return false;" >검색</a>
			</div><!-- E : search-box-1 -->

			<div class="page-sum mt-020">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b>건 </div><!-- E : page-sum -->
</form>

			<table class="type-2">
				<colgroup>
					<col width="2%" />
					<%-- <col width="8%" />
					<col width="7%" /> --%>
					<col width="*" />
					<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
					<col width="15%" />
					<col width="15%" />
					</c:if>
					<col width="15%" />
					<col width="10%" />
					<col width="7%" />
				</colgroup>
				<thead>
					<tr>
						<th>No</th>
						<!-- <th>학년도</th>
						<th>학기</th> -->
						<th>제목</th>
						<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
						<th>게시시작일</th>
						<th>게시종료일</th>
						</c:if>
						<th>등록일</th>
						<th>등록자</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="7">검색된 데이터가 없습니다.</th>
					</tr>
					</c:if>
					<c:if test="${fn:length(resultList) != 0}">
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<c:choose>
						<c:when test="${result.replyPosblAt=='Y'}">
							<!-- 답장가능 게시판은 상단노출 선택 항목이 없다. -->
							<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						</c:when>
						<c:otherwise>
							<!-- 답장불가능 게시판은 상단노출 선택 항목이 있다. -->
							<td>
								<c:if test="${'Y' == result.topNoticeYn}">
									<a href="#!" class="btn-line-yellow">공지</a>
								</c:if>
								<c:if test="${'N' == result.topNoticeYn}">
									<c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/>
								</c:if>
							</td>
						</c:otherwise>
			  	  		</c:choose>
						<!-- <td>2016</td>
						<td>1</td> -->
						<td class="left">
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
						  		<c:if test="${'Y' == result.topNoticeYn}">
									<a href="#!"  class="text-link" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
								</c:if>
								<c:if test="${'N' == result.topNoticeYn}">
									<a href="#!"  class="text-link" onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
								</c:if>
								</c:otherwise>
							</c:choose>
						</td>
						<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
						<td><c:out value="${result.ntceBgnde}"/></td>
						<td><c:out value="${result.ntceEndde}"/></td>
						</c:if>
						<td><c:out value="${result.frstRegisterPnttm}"/></td>
						<td><c:out value="${result.frstRegisterNm}"/></td>
						<td class="none"><c:out value="${result.inqireCo}"/></td>
					</tr>
					</c:forEach>
					</c:if>
				</tbody>
			</table><!-- E : type-2 -->


	<!-- S : page-num (페이징 영역) -->
		<div class="page-num mt-015">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_paging_noticeList" />
		</div>
		<!-- E : page-num (페이징 영역) -->


		</div><!-- E : wrapper -->



