<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : /lu/egovframework/com/cop/bbs/EgovNoticeList.jsp
  * @Description : 게시물 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2016.12.28   이진근          최초 생성
  *
  */
%>
<!-- la : EgovNoticeList.jsp -->
<link href="<c:url value='${brdMstrVO.tmplatCours}' />" rel="stylesheet" type="text/css">
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
<div class="content">
		<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000013'}">
		<h1>공지사항</h1>
		</c:if>
		<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000014'}">
		<h1>Q&A</h1>
		</c:if>
		<c:if test="${boardVO.bbsId == 'BBSMSTR_000000000015'}">
		<h1>학습자료실</h1>
		</c:if>
			<!-- E : search-list-1 (검색조건 영역) -->
			<form name="frm" action ="<c:url value='/la/cop/bbs${prefix}/${pathBbsIdStr}/selectBoardList.do'/>" method="post">
			<input type="hidden" name="bbsId" value="<c:out value='${boardVO.bbsId}'/>" />
			<input type="hidden" name="nttId"  value="0" />
			<input type="hidden" name="bbsTyCode" value="<c:out value='${brdMstrVO.bbsTyCode}'/>" />
			<input type="hidden" name="bbsAttrbCode" value="<c:out value='${brdMstrVO.bbsAttrbCode}'/>" />
			<input type="hidden" name="authFlag" value="<c:out value='${brdMstrVO.authFlag}'/>" />
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
			<input type="hidden" name="yyyy"  value="<c:out value='${yyyy}'/>" />
			<input type="hidden" name="term"  value="<c:out value='${term}'/>" />
			<input type="hidden" name="subjectCode"  value="<c:out value='${subjectCode}'/>" />
			<input type="hidden" name="subClass"  value="<c:out value='${subClass}'/>" />
			<input type="hidden" name="memType"  value="<c:out value='${memType}'/>" />
			
			<div class="search">
			<select name="searchYyyy" title="학년도 검색조건선택">
				<option value="2010" <c:if test="${searchVO.searchYyyy == '2010'}">selected="selected"</c:if> >2010학년도</option>
				<option value="2011" <c:if test="${searchVO.searchYyyy == '2011'}">selected="selected"</c:if> >2011학년도</option>
				<option value="2012" <c:if test="${searchVO.searchYyyy == '2012'}">selected="selected"</c:if> >2012학년도</option>
				<option value="2013" <c:if test="${searchVO.searchYyyy == '2013'}">selected="selected"</c:if> >2013학년도</option>
				<option value="2014" <c:if test="${searchVO.searchYyyy == '2014'}">selected="selected"</c:if> >2014학년도</option>
				<option value="2015" <c:if test="${searchVO.searchYyyy == '2015'}">selected="selected"</c:if> >2015학년도</option>
				<option value="2016" <c:if test="${searchVO.searchYyyy == '2016'}">selected="selected"</c:if> >2016학년도</option>
			</select>
			<select name="searchTerm">
				<option value="1" <c:if test="${searchVO.searchTerm == '1'}">selected="selected"</c:if> >1학기</option>
				<option value="2" <c:if test="${searchVO.searchTerm == '2'}">selected="selected"</c:if> >2학기</option>
			</select>
<!-- 			<select id="" onchange="">
				<option value="">카데고리</option>
				<option value="">시스템</option>
				<option value="">교과목</option>
			</select> -->
			<select name="searchCnd" title="검색조건선택">
				<option selected="selected" value=''>--선택--</option>
				<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >제목</option>
				<option value="2" <c:if test="${searchVO.searchCnd == '2'}">selected="selected"</c:if> >작성자</option>
			</select>
			<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">
			<input type="button" class="grey_search" value="검색" onclick="fn_egov_select_noticeList('1'); return false;" />
		</div>
		
		<!-- E : search-list-1 (검색조건 영역) -->
		<div class="float_left">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b>건</div>
		<div class="float_right">Page View :  10 <input type="radio" id="selectRecordIndex" name="selectRecordIndex" value="10" onchange="fn_egov_selectLastIndex();"> 20 <input type="radio" id="selectRecordIndex" name="selectRecordIndex" value="20" onchange="fn_egov_selectLastIndex();"> 50 <input type="radio" id="selectRecordIndex" name="selectRecordIndex" value="50" onchange="fn_egov_selectLastIndex();"></div>
		</form>
		
		<table class="table">
			<colgroup>
				<col style="width:40px" />
				<col style="width:40px" />
				<col style="width:45px" />
				<%-- <col style="width:80px" /> --%>
				<col style="width:*" />
				<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<col style="width:50px" />
				<col style="width:50px" />
				</c:if>
				<%-- <col style="width:40px" /> --%>
				<col style="width:90px" />
				<col style="width:60px" />
				<col style="width:60px" />
			</colgroup>
			<tr>
				<th>No</th>
				<th>학년도</th>
				<th>학기</th>
				<!-- <th>카데고리</th>	 -->			
				<th>제목</th>
				<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<th width="70px">게시시작일</th>
				<th width="70px">게시종료일</th>
				</c:if>
				<!-- <th>첨부</th> -->
				<th>등록일</th>
				<th>등록자</th>
				<th class="none">조회수</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
			<tr>
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
				<td><c:out value="${result.yyyy}" /></td>
				<td><c:out value="${result.term}" /></td>
				<!-- <td>시스템</td> -->
				<!-- <td class="left"><a href="11_QA_view.html">제목이 들어갈 자리</a></td> -->
				
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
							<a href="#"  onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><B><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</B></a>
						</c:if>			
						<c:if test="${'N' == result.topNoticeYn}">
							<a href="#"  onclick="fn_egov_inqire_notice('${status.count}','${result.nttId}','${result.bbsId}');"><c:out value="${result.nttSj}"/> [<c:out value="${result.commnetCount}" />]</a>
						</c:if>
						</c:otherwise>
					</c:choose>
			
				</td>
				
				<!-- <td><img src="../images/icon_attach.png"></td> -->
				<c:if test="${brdMstrVO.bbsAttrbCode == 'BBSA01'}">
				<td><c:out value="${result.ntceBgnde}"/></td>
				<td><c:out value="${result.ntceEndde}"/></td>
				</c:if>
				<td><c:out value="${result.frstRegisterPnttm}"/></td>
				<td><c:out value="${result.frstRegisterNm}"/></td>
				<td class="none"><c:out value="${result.inqireCo}"/></td>
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
		</table>
 
		<!-- S : page-num (페이징 영역) -->
		
		<!-- <div class="list_page">
			<div class="paging">						
				<a href='#prev' class='prev'><span>이전</span></a><a href="">1</a><a href="" class="on">2</a><a href="">3</a><a href="">4</a><a href="">5</a><a href="">6</a><a href="">7</a><a href="">8</a><a href="">9</a><a href="">10</a><a href='#next' class='next'><span>다음</span></a>
			</div>
		</div> -->
		<div class="page-num">
			<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_egov_select_noticeList" />
		</div>
		<!-- E : page-num (페이징 영역) -->
 
 		<c:if test="${memType == 'PRT'||memType == 'ATT'||memType == 'COT'}">
			<div class="btn_right">
				<a href="#@" onclick="fn_egov_addNotice();return false;"><input type="button" class="orenge" value="등록" />
			</div>
		</c:if>		
 
	</div>