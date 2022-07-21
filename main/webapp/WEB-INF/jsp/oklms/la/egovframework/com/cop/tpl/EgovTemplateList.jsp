<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovTemplateList.jsp
  * @Description : 템플릿 목록화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.18   이삼섭          최초 생성
  *
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.18
  *  @version 1.0
  *  @see
  *
  */
%>
<script type="text/javascript">
	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_tmplatInfo('1');
		}
	}

	function fn_egov_insert_addTmplatInfo(){
		document.frm.action = "<c:url value='/la/cop/tpl/addTemplateInf.do'/>";
		document.frm.submit();
	}

	function fn_egov_select_tmplatInfo(pageNo){
		document.frm.pageIndex.value = pageNo;
		document.frm.action = "<c:url value='/la/cop/tpl/selectTemplateInfs.do'/>";
		document.frm.submit();
	}

	function fn_egov_inqire_tmplatInfor(tmplatId){
		document.frm.tmplatId.value = tmplatId;
		document.frm.action = "<c:url value='/la/cop/tpl/selectTemplateInf.do'/>";
		document.frm.submit();
	}
</script>
<form name="frm" action ="" method="post">
<input type="hidden" name="tmplatId" value="" />
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
						<!-- E : search-list-1 (검색조건 영역) -->
						<ul class="search-list-1">
							<li>
								<span>기간</span>
								<select name="searchCnd" class="select" title="검색조건선택">
									<option selected value=''>선택하세요</option>
									<option value="0" <c:if test="${searchVO.searchCnd == '0'}">selected="selected"</c:if> >템플릿명</option>
									<option value="1" <c:if test="${searchVO.searchCnd == '1'}">selected="selected"</c:if> >템플릿구분</option>
								</select>
							</li>
							<li>
								<span>검색</span>
								<input name="searchWrd" type="text" size="35" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="35" onkeypress="press(event);" title="검색단어입력">
							</li>
						</ul>
						<!-- E : search-list-1 (검색조건 영역) -->
						<div class="search-btn-area"><a href="#@" onclick="javascript:fn_egov_select_tmplatInfo('1');return false;">조회하기</a></div>
						
						<!-- S : board-info (게시판 버튼 및 구분selectBox ) -->
						<ul class="board-info">
							<li class="info-area"><span>전체</span> : 0 건</li>
							<li class="btn-area">
								<a href="#@" onclick="javascript:fn_egov_insert_addTmplatInfo();return false;">등록</a>
							</li>
						</ul>
						<!-- E : board-info (게시판 버튼 및 구분selectBox ) -->


						<!-- S : list (게시판 목록 영역) -->
						<table border="0" cellpadding="0" cellspacing="0" class="list-1">
							<thead>
								<tr>
									<th width="30px">번호</th>
									<th width="210px">템플릿명</th>

									<th width="70px">템플릿 구분</th>
									<th>템플릿 경로</th>

									<th width="70px">사용여부</th>
									<th width="70px">등록일자</th>
								</tr>
							</thead>
							<tbody>
		 					<c:forEach var="result" items="${resultList}" varStatus="status">
								<tr>
								    <!--td class="lt_text3" nowrap><input type="checkbox" name="check1" class="check2"></td-->
								    <td class="lt_text3" nowrap><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						
								    <!-- 2010.10.15
								    <td class="lt_text3" nowrap>
								    	<a href="javascript:fn_egov_inqire_tmplatInfor('<c:out value="${result.tmplatId}"/>')">
								    	<c:out value="${result.tmplatNm}"/></a>
								    </td>
									-->
						
								    <td class="lt_text3" nowrap>
										<a href="<c:url value='/la/cop/tpl/selectTemplateInf.do'/>?tmplatId=<c:out value='${result.tmplatId}'/>" onclick="">
											<c:out value="${result.tmplatNm}"/>
										</a>
								    </td>
						
								    <td class="lt_text3" nowrap><c:out value="${result.tmplatSeCodeNm}"/></td>
								    <td class="lt_text3" nowrap><c:out value="${result.tmplatCours}"/></td>
								    <td class="lt_text3" nowrap>
								    	<c:if test="${result.useAt == 'N'}"><spring:message code="button.notUsed" /></c:if>
								    	<c:if test="${result.useAt == 'Y'}"><spring:message code="button.use" /></c:if>
								    </td>
									<td class="lt_text3" nowrap><c:out value="${result.frstRegisterPnttm}"/></td	>
								</tr>
		 					</c:forEach>
							<c:if test="${fn:length(resultList) == 0}">
							<tr>
							  <td class="lt_text3" nowrap colspan="6" ><spring:message code="common.nodata.msg" /></td>
							</tr>
							</c:if>
							</tbody>
						</table>
						<!-- E : list (게시판 목록 영역) -->


						<!-- S : page-num (페이징 영역) -->
						<div class="page-num">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
						</div>
						<!-- E : page-num (페이징 영역) -->
</form>
