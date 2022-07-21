<%--
  Class Name : EgovQustnrManageList.jsp
  Description : 설문관리 목록 페이지
  Modification Information

      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2016.12.20    이진근          모듈 수정

    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09

--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/la/uss/olp/qqm/"/>

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
<script type="text/javascript">

var targetUrl = "${targetUrl}";

$(document).ready(function() {
	loadPage();
});

/*====================================================================
	화면 초기화 
====================================================================*/
function loadPage() {
	initEvent();
	initHtml();
}


/* 각종 버튼에 대한 액션 초기화 */
function initEvent() {
}

/* 화면이 로드된후 처리 초기화 */
function initHtml() {
}

/*====================================================================
	사용자 정의 함수 
====================================================================*/
function press(event) {
	if (event.keyCode==13) {
		fn_search('1');
	}
}

/* 검색 함수 */
function fn_search(pageNo){
	$("#pageIndex").val( pageNo );
	
	var reqUrl = CONTEXT_ROOT + targetUrl + "popupEgovQustnrQestnManageList.do";
	$("#frm").attr("action", reqUrl);
	$("#frm").submit();
}

/* 선택 버튼 클릭시 처리 함수 */
function doSelectInfo(){
	if( opener ){
		var selectInfo = $("input:radio[name='qestnrQesitmCn']:checked").val();
		
		if(undefined == selectInfo){
			alert("추가할 설문문항정보를 선택하여주십시오.");
			return false;
		}
		
		opener.setSelectInfo1(selectInfo);
		window.close();
	}
}

/* 제목 클릭시 처리 함수 */	
function fn_egov_select_Subject(qestnrQesitmIdParm, qestnTyCodeParm, cntParam){
	var qestnrQesitmId = "";
	var qestnTyCode = "";
	var cnt = "";	
	var qestnrQesitmCn = "";
	
	qestnrQesitmId = qestnrQesitmIdParm;
	qestnTyCode = qestnTyCodeParm;
	cnt = cntParam;

	if(qestnrQesitmId == ''){
		alert("추가할 설문문항정보를 선택하여주십시오.");
		return false;
	}
	if(qestnTyCode == ''){
		alert("추가할 설문문항정보를 선택하여주십시오.");
		return false;
	}
	if(cnt == ''){
		alert("추가할 설문문항정보를 선택하여주십시오.");
		return false;
	}
	
	qestnrQesitmCn = document.getElementById("iptText_"+ cnt).value;
	
	opener.setSelectInfo2(qestnrQesitmId, qestnTyCode, qestnrQesitmCn);
	window.close();
}
</script>

<form name="frm" id="frm" method="post">
<input name="qestnrTmplatId" id="qestnrTmplatId" type="hidden" value="${qustnrQestnManageVO.qestnrTmplatId}">
<input name="qestnrId" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.qestnrId}">
<input name="searchMode" id="qestnrId" type="hidden" value="${qustnrQestnManageVO.searchMode}">
<input name="qestnrQesitmId" id="qestnrQesitmId" type="hidden" value="">
<input name="cmd" type="hidden" value="">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">

<div id="popup-wrarpr">
	<div id="popup-header">
		<h1><img src="/images/oklms/adm/inc/pop_border_02.png" />설문문항정보 검색</h1>
		<p><a href="#" onclick="parent.close();"></a></p>
	</div><!-- E : p-header -->
	
	<div id="popup-content-area">
		<div id="popup-container">

<!-- 팝업 내용 영역 시작 : 가로 650px , 세로 560px -->

			<div class="name-area" style="width:99%">
				<span>검색 조건</span> :
				<select name="searchCondition" id="searchCondition" class="select" title="검색조건선택" style="width: 150px;">
					<option value="">--선택--</option>
					<option value='QESTN_CN' <c:if test="${searchVO.searchCondition == 'QESTN_CN'}">selected</c:if>>질문내용</option>
		   <option value='MXMM_CHOISE_CO' <c:if test="${searchVO.searchCondition == 'MXMM_CHOISE_CO'}">selected</c:if>>최대선택건수</option>
				</select>
				</br> 
				<span>검색 단어</span> : <input name="searchKeyword" name="searchKeyword" type="text" size="35" value='<c:out value="${searchKeyword}"/>' maxlength="35" onkeypress="press(event);" title="검색어 입력">   
				<a href="#fn_search()" onclick="javascript:fn_search(1); return false;" onkeypress="this.onclick;" class="btn">검색</a>
			</div>

			<table border="0" cellpadding="0" cellspacing="0" class="list-1">
					<thead>
						<tr>
							<th scope="col"  width="10%" nowrap>번호</th>
						    <th scope="col"  nowrap>질문내용</th>
						    <th scope="col"  width="20%" nowrap>질문유형</th>
						    <th scope="col"  width="15%" nowrap>등록자</th>
						    <th scope="col"  width="15%" nowrap>등록일자</th>
						    <th scope="col"  width="30px" nowrap>선택</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="resultInfo" items="${resultList}" varStatus="status">
						<tr>
						<td class="lt_text1" nowrap>${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}</td>
						<td class="lt_text3L" nowrap>
						 	<a href="#LINK" onClick="fn_egov_select_Subject('${resultInfo.qestnrQesitmId}', '${resultInfo.qestnTyCode}', '${status.count}')"><span color="blue">${resultInfo.qestnCn}</span></a>
						</td>
						<td class="lt_text1" nowrap>
							<c:if test="${resultInfo.qestnTyCode == '1'}">객관식</c:if>
    						<c:if test="${resultInfo.qestnTyCode == '2'}">주관식</c:if>
						</td>
						<td class="lt_text1" nowrap>${resultInfo.frstRegisterNm}</td>
						<td class="lt_text1" nowrap>${fn:substring(resultInfo.frstRegisterPnttm, 0, 10)}</td>
						<td class="lt_text1"><input type="radio" name="qestnrQesitmCn" id="qestnrQesitmCn" value="${resultInfo.qestnCn}||${resultInfo.qestnrQesitmId}||${resultInfo.qestnTyCode}"></td>
						<input name="iptText_${status.count}" id="iptText_${status.count}" type="hidden" value="${resultInfo.qestnCn}">
						</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td class="lt_text1" colspan="6"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
					</tbody>
				</table><!-- E : list -->

<!-- 팝업 내용  영역 끝 -->

				<div class="page-btn">
					<a href="#doSelectInfo()" onclick="javascript:doSelectInfo();" onkeypress="this.onclick;">선택</a>
				</div><!-- E : page-btn -->

				<div class="page-num">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
				</div><!-- E : page-num -->

		</div><!-- E : p-contentiner -->
	</div><!-- E : p-content-area -->
	
	<div id="popup-footer"></div><!-- E : p-footer -->
</div><!-- E : p-wrapper -->
</form>


