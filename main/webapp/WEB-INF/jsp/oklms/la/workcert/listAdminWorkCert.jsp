<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<c:set var="targetUrl" value="/lu/workcert/"/>
<script type="text/javascript">
var targetUrl = "${targetUrl}";
var pageSize 	= '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
var totalCount = '${totalCount}'; //전체 데이터 갯수
var pageIndex = '${pageIndex}'; //현재 페이지 정보

/* 리스트 조회 */
function fn_search(param1) {
	$("#pageIndex").val(param1);
	var reqUrl = CONTEXT_ROOT + targetUrl + "listAdminWorkCert.do";
	$("#frmWork").attr("action", reqUrl);
	$("#frmWork").submit();
}

function fn_searchCondition() {
	fn_search(pageIndex);
}

function fn_searchPageView(val) {
	$("#pageSize").val(val);
	fn_search(pageIndex);
}

function fn_download(workProofId) {
	$("#workProofId").val(workProofId);
	$("input[id="+workProofId+"]").css("display","none");
	$("input[id=com_"+workProofId+"]").css("display","block");
	var reqUrl = CONTEXT_ROOT + targetUrl + "downloadAdminWorkCert.do";
	$("#frmWorkDownLoad").attr("action", reqUrl);
	$("#frmWorkDownLoad").submit();
}


function fn_downloadRemove(workProofId) {
	$("#workProofId").val(workProofId);
	var reqUrl = CONTEXT_ROOT + targetUrl +"downloadAdminWorkCert.json";
	var param = $("#frmWorkDownLoad").serializeArray();
	com.ajax4confirm( "재직증빙서류 서버에서 삭제 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
		com.alert( jqXHR.responseJSON.retMsg );
		if( "SUCCESS" == jqXHR.responseJSON.retCd ){
			fn_search(1);
		}
	}
	}, {
		async : true,
		type : "POST",
		spinner : true,
		errorCallback : null,
		timeout : 1000			// 기본 30초
	});
}
</script>
<div class="content">
	<h1>재직증빙서류 제출</h1>
<form name="frmWorkDownLoad" id="frmWorkDownLoad" method="post" >
<input type="hidden" id="workProofId" name="workProofId"/>
</form>
<form name="frmWork" id="frmWork" method="post" >
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" /> 
<input type="hidden" id="orderKey" 	name="orderKey" value="${param.orderKey}" /> 
<input type="hidden" id="orderDir" name="orderDir" value="${param.orderDir}" />
<input type="hidden" id="popupId" name="popupId"  />
		<div class="search">
			<select name="yyyy" id="yyyy">
					<option value="">-학년도-</option>
					<c:forEach items="${yyyyList}" var="ccYyyy" varStatus="status">
						<option value="${ccYyyy.yyyy}" ${param.yyyy == ccYyyy.yyyy ? 'selected="selected"' : ''}>${ccYyyy.yyyy}학년도</option>
					</c:forEach>
			</select>
			<select name="term" id="term">
				<option value="">-학기-</option>
				<option value="1" ${param.term == '1' ? 'selected="selected"' : ''}>1학기</option>
				<option value="2" ${param.term == '2' ? 'selected="selected"' : ''}>2학기</option>
			</select>
		
			 <select name="searchKeyword" id="searchKeyword" >
	 			<option value=''>전체</option>
	 			<option ${param.searchKeyword == 'NAME' ? 'selected="selected"' : ''} value='NAME'>이름</option>
	 			<option ${param.searchKeyword == 'ID' ? 'selected="selected"' : ''} value='ID'>아이디</option>
			</select>
			&nbsp;
			<input type="text" name="searchCondition" id="searchCondition" value="${param.searchCondition}" />
			<input type="button" class="grey_search" value="검색"  onClick="javascript:fn_searchCondition();"/>
		</div>
		<div class="float_left">총  ${paginationInfo.totalRecordCount}건 (${pageIndex} / ${paginationInfo.totalPageCount} Page)</div>
		<div class="float_right">Page View :  
		10&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="10" ${pageSize == '10' ? 'checked' : ''} onclick="javascript:fn_searchPageView('10');">
		20&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="20" ${pageSize == '20' ? 'checked' : ''} onclick="javascript:fn_searchPageView('20');"> 
		50&nbsp;<input type="radio" name="pageSizeCnt" id="pageSizeCnt" value="50" ${pageSize == '50' ? 'checked' : ''} onclick="javascript:fn_searchPageView('50');"> 
		Lines</div>
</form>
		<table class="table" id="rptbl">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:100px;" />
			</colgroup>
			<tr>
				<th>이름</th>
				<th>학년도</th>
				<th>학기</th>
				<th>상태</th>
				<th class="none">다운로드</th>
			</tr>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td style="text-align: left;"><c:out value="${result.memName}" /></td>
					<td><c:out value="${result.yyyy}" /></td>
					<td><c:out value="${result.term}" /></td>
					<td style="text-align: left;">
						<c:if test="${result.removeId eq null}">
							<c:if test="${result.downId eq null}">
								<c:if test="${result.sendYn == 'Y'}">제출완료</c:if>
								<c:if test="${result.sendYn != 'Y'}">미제출</c:if>
							</c:if>
							<c:if test="${result.downId ne null}">
								완료처리 진행
							</c:if>
						</c:if>
						<c:if test="${result.removeId ne null}">
							다운로드 완료
						</c:if>
					</td>
					<td style="text-align: left;">
						<c:if test="${result.removeId eq null}">
							<c:if test="${result.downId eq null}">
									<input type='button' class='table_btn_orenge' id="${result.workProofId}" onClick="javascript:fn_download('${result.workProofId}');" value='다운로드' />
							</c:if>
							<c:if test="${result.downId ne null}">
								<input type='button' class='table_btn_orenge' onClick="javascript:fn_downloadRemove('${result.workProofId}');" value='완료처리' />
							</c:if>
						</c:if>
						<input type='button' class='table_btn_orenge' style="display:none;" id="com_${result.workProofId}" onClick="javascript:fn_downloadRemove('${result.workProofId}');" value='완료처리' />
					</td>
					
				</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				<tr id="disHide">
					<td class="none" colspan="5">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</table>
</div>
<!-- E : list (게시판 목록 영역) -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image"
		jsFunction="fn_search" />
</div>
<!-- E : page-num -->
