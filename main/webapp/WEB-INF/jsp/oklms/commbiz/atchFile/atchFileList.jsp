<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script type="text/javascript">

	function fn_egov_downFile(atchFileId, fileSn){
		window.open("<c:url value='/commbiz/atchfle/atchFileDown.do?importAtchFileId="+atchFileId+"&importfileSn="+fileSn+"'/>");
	}

	function fn_egov_deleteFile(atchFileId, fileSn) {
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof(forms[i].importAtchFileId) != "undefined" &&
					typeof(forms[i].importfileSn) != "undefined" &&
					typeof(forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}

		//form = document.forms[0];
		form.importAtchFileId.value = atchFileId;
		form.importfileSn.value = fileSn;
		form.action = "<c:url value='/commbiz/atchfle/atchFileDelete.do'/>";
		form.submit();
	}

</script>


<!-- <H1>파일목록</H1> -->
<table cellspacing="0" cellpadding="0">
	<c:forEach var="file" items="${fileList}">
		<tr id="file_${file.atchFileId}_${file.fileSn}">
			<td><a href="javascript:com.downFile('${file.atchFileId}','${file.fileSn}');">${file.orgFileName}</a>
				<c:choose>
					<c:when test="${deleYn=='Y'}">
						==> <a href="javascript:com.deleteFile('${file.atchFileId}|${file.fileSn}','${returnUrl }')">[삭제]</a>
<%-- 						<a href="javascript:com.deleteFile('${file.atchFileId}|${file.fileSn}','${returnUrl }')" class="btn_delete" title="첨부파일 삭제"></a> --%>
					</c:when>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	<c:forEach var="cnt" begin="1" end="${item.attachCnt}" varStatus="status">
		<tr>
			<td><input type="file" id="file${cnt}" name="file${cnt}" /></td>
		</tr>
	</c:forEach>
</table>


<%--
<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<div class="mgb5">
	<c:choose>
		<c:when test="${updateFlag=='Y'}">
			<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[ <fmt:formatNumber value="${fileVO.fileMg / 1024}" pattern=".0"/>&nbsp;kbyte ]
			<img src="<c:url value='/images/egovframework/com/cmm/fms/icon/bu5_close.gif' />"  width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" alt="파일삭제">
		</c:when>
		<c:otherwise>
			<a href="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>')">
				<c:out value="${fileVO.orignlFileNm}"/>&nbsp;[ <fmt:formatNumber value="${fileVO.fileMg / 1024}" pattern=".0"/>&nbsp;kbyte ]
			</a>
		</c:otherwise>
	</c:choose>
	</div>
</c:forEach>
 --%>
<c:if test="${fn:length(fileList) == 0}">
	<div></div>		
</c:if>
