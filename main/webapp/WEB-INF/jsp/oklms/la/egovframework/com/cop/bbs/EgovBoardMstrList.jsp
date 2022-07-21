<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /la/egovframework/com/cop/bbs/EgovBoardMstrList.jsp
  * @Description : 게시판 속성 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.12   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.12
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- la : EgovBoardMstrList.jsp -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_brdMstr('1');
		}
	}

	function fn_egov_insert_addBrdMstr(){
		var pageNo = 1;
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/la/cop/bbs/addBBSMaster.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_brdMstr(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/la/cop/bbs/selectBBSMasterInfs.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_brdMstr(bbsId){
		document.frm.bbsId.value = bbsId;
		document.frm.action = "<c:url value='/la/cop/bbs/selectBBSMasterInf.do'/>";
		document.frm.submit();
	}
</script>

<form name="frm" action="<c:url value='/la/cop/bbs/selectBBSMasterInfs.do'/>" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" >
<input type="hidden" name="bbsId">
<input type="hidden" name="trgetId">

						<!-- E : search-list-1 (검색조건 영역) -->
						<ul class="search-list-1">
							<li>
								<span>검색조건</span>
						   		<select name="searchCnd" class="select" title="검색유형선력">
								   <option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >게시판명</option>
								   <option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >게시판유형</option>
							   </select>
								<input type="text" style="width:230px;" name="searchWrd" type="text" size="35" maxlength="35" value='<c:out value="${searchVO.searchWrd}" />' onkeypress="press(event);"/>
							</li>
						</ul>
						<!-- E : search-list-1 (검색조건 영역) -->
						<div class="search-btn-area"><a href="#@" onclick="javascript:fn_egov_select_brdMstr('1');">조회하기</a></div>


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
									<th>게시판명</th>
									<th width="70px">게시판 유형</th>

									<th width="70px">게시판 속성</th>

									<th width="70px">생성일</th>
									<th width="70px">사용여부</th>
								</tr>
							</thead>
							<tbody>
		 <c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
									<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
									<td><a href="<c:url value='/la/cop/bbs/selectBBSMasterInf.do'/>?bbsId=<c:out value='${result.bbsId}'/>"><c:out value="${result.bbsNm}"/></a></td>
									<td><c:out value="${result.bbsTyCodeNm}"/></td>
									<td><c:out value="${result.bbsAttrbCodeNm}"/></td>
									<td><c:out value="${result.frstRegisterPnttm}"/></td>
									<td><c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if><c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if></td>
								</tr>
		 </c:forEach>
		 <c:if test="${fn:length(resultList) == 0}">
								<tr>
									<td class="lt_text3" nowrap colspan="6"><spring:message code="common.nodata.msg" /></td>
								</tr>
		 </c:if>
							</tbody>
						</table>
						<!-- E : list (게시판 목록 영역) -->


						<!-- S : page-num (페이징 영역) -->
						<div class="page-num">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_brdMstr" />
						</div>
						<!-- E : page-num (페이징 영역) -->
						

</form>

<div class="page-btn">
	<a href="#@" onclick="fn_egov_insert_addBrdMstr();">등록</a>
</div><!-- E : page-btn -->
