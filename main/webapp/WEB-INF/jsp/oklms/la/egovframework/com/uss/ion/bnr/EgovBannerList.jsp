<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javaScript" language="javascript" defer="defer">
<!--
var targetUrl = "${targetUrl}";
var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
var totalCount = '${totalCount}'; //전체 데이터 갯수
var pageIndex = '${pageIndex}'; //현재 페이지 정보

function fncCheckAll() {
    var checkField = document.listForm.delYn;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  배너가 없습니다.");
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("선택된 배너가 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("조회된 결과가 없습니다.");
    }

    document.listForm.bannerIds.value = returnValue;
    return returnBoolean;
}

function fncSelectBannerList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/la/banner/listBanner.do'/>";
    document.listForm.submit();
}

function fncSelectBanner(bannerId) {
    document.listForm.bannerId.value = bannerId;
    document.listForm.action = "<c:url value='/la/banner/getBanner.do'/>";
    document.listForm.submit();
}

function fncAddBannerInsert() {
	if(document.listForm.pageIndex.value == "") {
		document.listForm.pageIndex.value = 1;
	}
    document.listForm.action = "<c:url value='/la/banner/goInsertBanner.do'/>";
    document.listForm.submit();
}

function fncBannerListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) {
            document.listForm.action = "<c:url value='/la/banner/deleteAllBanner.do'/>";
            document.listForm.submit();
        }
    }
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/la/banner/listBanner.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectBannerList('1');
    }
}

/* 리스트 조회 */
function fn_search() {
	var reqUrl = "/la/banner/listBanner.do";
	$("#listForm").attr("action", reqUrl);
	$("#listForm").submit();
}

/* 미리보기 조회 */
function fn_preview() {
	var reqUrl = "/la/banner/getImageBanner.do";
	$("#listForm").attr("action", reqUrl);
	$("#listForm").submit();
}



-->
</script>
</head>
<body>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form name="listForm" id="listForm" method="post">
 	<input type="hidden" name="resultType" id="resultType" value="vertical"><!-- horizontal,vertical -->
	<input type="hidden" name="bannerId" id="bannerId">
	<input type="hidden" name="pageIndex" value="<c:if test="${empty bannerVO.pageIndex }">1</c:if><c:if test="${!empty bannerVO.pageIndex }"><c:out value='${bannerVO.pageIndex}'/></c:if>">
	<input type="hidden" name="searchCondition" value="1">
	<!-- E : search-list-1 (검색조건 영역) -->
	<ul class="search-list-1">
		<li><span>배너 종류</span> 
		<select name="bannerType" class="selectpicker btn-xs reset" data-width="130px">
				<option value="">-선택-</option>
				<c:forEach items="${ccBannerTypeList}" var="ccBannerType" varStatus="status">
					<option value="${ccBannerType.codeCode}" ${param.bannerType == ccBannerType.codeCode ? 'selected="selected"' : ''}>${ccBannerType.codeName}</option>
				</c:forEach>
		</select>
		</li>
		<li><span>배너명</span> 
			<input name="searchKeyword" type="text" value="<c:out value="${bannerVO.searchKeyword}"/>" size="25" onkeypress="press();" id="searchKeyword" title="검색단어입력">
		</li>
	</ul>
</form>
<!-- E : search-list-1 (검색조건 영역) -->
<div class="search-btn-area">
	<a href="#@" onclick="fn_search(); return false;">조회</a>
</div>
<ul class="board-info">
	<li class="info-area"><span>전체</span> : <c:out value="${paginationInfo.totalRecordCount }" /> 건</li>
	<li class="btn-area"><a href="javascript:fn_preview();">미리보기</a></li>
</ul>
<table width="100%" cellpadding="8"  class="list-1"  summary="메인화면에서 배너에 대한 목록으로 배너명, 링크url,배너설명,반영여부를 제공한다.">
<caption>배너 목록</caption>
 <thead>
  <tr>
  	<th class="title" width="5%" scope="col">순서</th>
  	<th class="title" width="10%" scope="col">배너 종류</th>
    <th class="title" width="20%" scope="col">배너 명</th>
    <th class="title" width="30%" scope="col">링크 URL</th>
    <th class="title" width="25%" scope="col">배너 설명</th>
    <th class="title" width="10%" scope="col">반영여부</th>
  </tr>
 </thead>
 <%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
 <c:if test="${fn:length(bannerList) == 0}">
 <tr>
 <td class="lt_text3" colspan="9">
 	<spring:message code="common.nodata.msg" />
 </td>
 </tr>
 </c:if>
 <tbody>
 <c:forEach var="banner" items="${bannerList}" varStatus="status">
  <tr>
  	<td class="lt_text3" nowrap><c:out value="${banner.sortOrdr}"/></td>
  	<td class="lt_text3" nowrap>
  	<c:forEach items="${ccBannerTypeList}" var="listBannerType" varStatus="status">
  	<c:if test="${listBannerType.codeCode == banner.bannerType}"><c:out value='${listBannerType.codeName}'/></c:if>
  	</c:forEach>
  	</td>
    <td class="lt_text3" nowrap>
        <form name="item" method="post" action="<c:url value='/la/banner/getBanner.do'/>">
            <input type="hidden" name="bannerId" value="<c:out value="${banner.bannerId}"/>">
            <input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>">
            <input type="hidden" name="searchCondition" value="<c:out value='${bannerVO.searchCondition}'/>">
            <input type="hidden" name="searchKeyword" value="<c:out value="${bannerVO.searchKeyword}"/>">
            <a href="javascript:fncSelectBanner('<c:out value="${banner.bannerId}"/>');"><span color="blue"><c:out value="${banner.bannerNm}"/></span></a>
        </form>
    </td>
    <td class="lt_text3" nowrap><c:out value="${banner.linkUrl}"/></td>
    <td class="lt_text3" nowrap><c:out value="${banner.bannerDc}"/></td>
    <td class="lt_text3" nowrap><c:out value="${banner.reflctAt}"/></td>
  </tr>
 </c:forEach>
 </tbody>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="10"></td>
  </tr>
</table>

<!-- E : list (게시판 목록 영역) -->
<div class="page-num">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
</div>
<!-- E : page-num -->

<div class="page-btn">
	<a href="#" onclick="fncAddBannerInsert();">등록</a>
</div>
<!-- E : page-btn -->