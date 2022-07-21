<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : EgovTemplateUpdt.jsp
  * @Description : 템플릿 속성 수정화면
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
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="templateInf" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_update_tmplatInfo() {
		if (!validateTemplateInf(document.templateInf)){
			return;
		}

		if (confirm('<spring:message code="common.update.msg" />')) {
			document.templateInf.action = "<c:url value='/la/cop/tpl/updateTemplateInf.do'/>";
			document.templateInf.submit();
		}
	}

	function fn_egov_select_tmplatInfo() {
		document.templateInf.action = "<c:url value='/la/cop/tpl/selectTemplateInfs.do'/>";
		document.templateInf.submit();
	}

	function fn_egov_selectTmplatType(obj) {
		if (obj.value == 'TMPT01') {
			document.getElementById('sometext').innerHTML = "게시판 템플릿은 CSS만 가능합니다.";
		} else if (obj.value == '') {
			document.getElementById('sometext').innerHTML = "";
		} else {
			document.getElementById('sometext').innerHTML = "템플릿은 JSP만 가능합니다.";
		}
	}

	function fn_egov_previewTmplat() {
		var frm = document.templateInf;

		var url = frm.tmplatCours.value;

		var target = "";
		var width = "";

		if (frm.tmplatSeCode.value == 'TMPT01') {
			target = "<c:url value='/la/cop/bbs/previewBoardList.do' />";
			width = "750";
		} else if (frm.tmplatSeCode.value == 'TMPT02') {
			target = "<c:url value='/la/cop/cmy/previewCmmntyMainPage.do' />";
			width = "980";
		} else if (frm.tmplatSeCode.value == 'TMPT03') {
			target = "<c:url value='/la/cop/cus/previewClubMainPage.do' />";
			width = "980";
		} else {
			alert('<spring:message code="cop.tmplatCours" /> 지정 후 선택해 주세요.');
		}

		if (target != "") {
			window.open(target + "?searchWrd="+url, "preview", "width=" + width + "px, height=500px;");
		}
	}
</script>

						<!-- S : 입력 폼 영역 -->
<form:form commandName="templateInf" name="templateInf" method="post" >
<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>" />
<input name="tmplatNm" type="hidden" value='<c:out value="${TemplateInfVO.tmplatNm}"/>' />

						<!-- S : view-1 -->
						<table border="0" cellpadding="0" cellspacing="0" class="view-1">
							<tbody>
								<tr>
									<th width="150px"><spring:message code="cop.tmplatNm" /></th>
									<td colspan="3"><input name="tmplatId" type="hidden" size="60" value='<c:out value="${TemplateInfVO.tmplatId}"/>'  maxlength="60" >
												      <c:out value="${TemplateInfVO.tmplatNm}"/>
												      <br/><form:errors path="tmplatId" />
									</td>
								</tr>
								<tr>
									<th width="150px"><spring:message code="cop.tmplatSeCode" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
									<td colspan="3">
									   	<select name="tmplatSeCode" class="select" onchange="fn_egov_selectTmplatType(this)" title="검색조건선택">
											   <option selected value=''>--선택하세요--</option>
											<c:forEach var="result" items="${resultList}" varStatus="status">
												<option value='<c:out value="${result.code}"/>' <c:if test="${TemplateInfVO.tmplatSeCode == result.code}">selected="selected"</c:if> ><c:out value="${result.codeNm}"/></option>
											</c:forEach>
										</select>&nbsp;&nbsp;&nbsp;<span id="sometext"></span>
										<br/><form:errors path="tmplatSeCode" />
									</td>
								</tr>
								<tr>
									<th width="150px"><spring:message code="cop.tmplatCours" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
									<td colspan="3">
										<input name="tmplatCours" type="text" size="60" value='<c:out value="${TemplateInfVO.tmplatCours}"/>' maxlength="60" style="width:100%" title="템플릿경로입력">
										<br/><form:errors path="tmplatCours" />
									</td>
								</tr>
								<tr>
									<th width="150px"><spring:message code="cop.useAt" /><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
									<td colspan="3">
								     	Y : <input type="radio" name="useAt" class="radio2" value="Y"  <c:if test="${TemplateInfVO.useAt == 'Y'}"> checked="checked"</c:if> >&nbsp;
								     	N : <input type="radio" name="useAt" class="radio2" value="N" <c:if test="${TemplateInfVO.useAt == 'N'}"> checked="checked"</c:if>>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- E : view-1 -->
</form:form>
						<!-- S : page-btn -->
						<div class="page-btn">
							<a href="#@" onclick="javascript:fn_egov_update_tmplatInfo();return false;">수정</a><a href="#@" onclick="javascript:fn_egov_select_tmplatInfo();return false;">목록</a><a href="#@" onclick="javascript:fn_egov_previewTmplat();return false;">미리보기</a>
						</div>
						<!-- E : page-btn -->						
						<!-- E : 입력 폼 영역 -->
