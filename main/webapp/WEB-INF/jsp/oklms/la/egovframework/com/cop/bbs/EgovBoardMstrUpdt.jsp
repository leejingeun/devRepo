<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : /la/egovframework/com/cop/bbs/EgovBoardMstrUpdt.jsp
  * @Description : 게시판 속성정보 변경화면
  * @Modification Information
  * @
  * @  수정일      수정자            수정내용
  * @ -------        --------    ---------------------------
  * @ 2009.03.12   이삼섭          최초 생성
  * @ 2009.06.26   한성곤          2단계 기능 추가 (댓글관리, 만족도조사)
  *   2011.09.15   서준식          2단계 기능 추가 (댓글관리, 만족도조사) 적용 방법 변경
  *   2011.11.11   이기하		   첨부파일선택 여부에 따른 첨부가능파일 숫자에 대한 체크추가
  *  @author 공통서비스 개발팀 이삼섭
  *  @since 2009.03.12
  *  @version 1.0
  *  @see
  *
  */
%>
<!-- la : EgovBoardMstrUpdt.jsp -->
<script type="text/javascript" src="<c:url value="/js/egovframework/com/cop/bbs/EgovBBSMng.js" />" ></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMaster" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_validateForm(obj){
		return true;
	}

	function fn_egov_update_brdMstr(){
		/* if (!validateBoardMaster(document.boardMaster)){
			return;
		} */

		//----------------------------
		// 2009.06.26 : 2단계 기능 추가
		//----------------------------
		var theForm = document.boardMaster;
		if ('<c:out value="${result.bbsTyCode}"/>' == 'BBST04' &&
				(theForm.option.options[theForm.option.selectedIndex].value == 'comment' ||
				 theForm.option.options[theForm.option.selectedIndex].value == 'stsfdg')) {
			alert('방명록의 경우는 추가 선택사항을 지원하지 않습니다.');
			theForm.option.focus();
			return;
		}
		////--------------------------
		
		// 2011.11.11 : 첨부파일가능 선택 시 파일숫자를 선택하도록 함, 첨부파일가능 미선택시 파일 숫자를 없음으로 변경
		var list = document.getElementsByName('fileAtchPosblAt');
		var fileAtchPosblAt_value;
		for (var i=0; i < list.length; i++) {
			if(list[i].checked == true) {
				fileAtchPosblAt_value = list[i].value;
			}
		}
		
		if (fileAtchPosblAt_value == "Y" && document.boardMaster.posblAtchFileNumber.value == 0 ) {
			alert("첨부가능파일 숫자를 1개이상 선택하세요.");
			return;
		}
		
		if (fileAtchPosblAt_value == "N" && document.boardMaster.posblAtchFileNumber.value != 0 ) {
			document.boardMaster.posblAtchFileNumber.value = 0;
		}
		////--------------------------


		if(confirm('<spring:message code="common.update.msg" />')){
			document.boardMaster.action = "<c:url value='/la/cop/bbs/updateBBSMasterInf.do'/>";
			document.boardMaster.submit();
		}
	}

	function fn_egov_select_brdMstrList(){
		document.boardMaster.action = "<c:url value='/la/cop/bbs/selectBBSMasterInfs.do'/>";
		document.boardMaster.submit();
	}

	function fn_egov_delete_brdMstr(){
		if(confirm('<spring:message code="common.delete.msg" />')){
			document.boardMaster.action = "<c:url value='/la/cop/bbs/deleteBBSMasterInf.do'/>";
			document.boardMaster.submit();
		}
	}

	function fn_egov_inqire_tmplatInqire(){
		var retVal;
		var url = "<c:url value='/la/commbiz/popup/openPopupWindow.do?requestUrl=/la/cop/tpl/selectTemplateInfsPop.do&typeFlag=BBS&width=850&height=360'/>";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_tmplatInqire", openParam);
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardMaster.tmplatId.value = tmp[0];
			document.boardMaster.tmplatNm.value = tmp[1];
		}
	}
	
	function showModalDialogCallback(retVal) {
		if (retVal != null) {
			var tmp = retVal.split("|");
			document.boardMaster.tmplatId.value = tmp[0];
			document.boardMaster.tmplatNm.value = tmp[1];
		}
	}
</script>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
						<!-- S : 입력 폼 영역 -->
<form:form commandName="boardMaster" name="boardMaster" action="${pageContext.request.contextPath}/cop/bbs/selectBBSMasterInfs.do" method="post" >
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
<input name="bbsId" type="hidden" value="<c:out value='${result.bbsId}'/>" />
<input name="bbsTyCode" type="hidden" value="<c:out value='${result.bbsTyCode}'/>" />
<input name="bbsAttrbCode" type="hidden" value="<c:out value='${result.bbsAttrbCode}'/>" />
<input name="replyPosblAt" type="hidden" value="<c:out value='${result.replyPosblAt}'/>" />
<input type="hidden" name="tmplatId" value="TMPLAT_0000000000001" />
<input type="hidden" name="tmplatNm" value="전자정부 게시판 템플릿" />

						<!-- S : view-1 -->
						<table border="0" cellpadding="0" cellspacing="0" class="view-1">
							<tbody>
								<tr>
									<th width="150px">게시판 명</th>
									<td colspan="3"><input title="게시판명입력" name="bbsNm" type="text" size="60" value='<c:out value="${result.bbsNm}"/>' maxlength="60" style="width:100%" ><form:errors path="bbsNm" /></td>
								</tr>
								<tr>
									<th>게시판 소개</th>
									<td colspan="3"><textarea title="게시판소개입력" name="bbsIntrcn" class="textarea"  cols="75" rows="4"  style="width:100%"><c:out value="${result.bbsIntrcn}" escapeXml="true" /></textarea><form:errors path="bbsIntrcn" /></td>
								</tr>
								<tr>
									<th>게시판 유형</th>
									<td><c:out value="${result.bbsTyCodeNm}"/></td>
									<th>게시판 속성</th>
									<td><c:out value="${result.bbsAttrbCodeNm}"/></td>
								</tr>
								<tr>
									<th>답장 가능 여부</th>
									<td>
								    	<c:choose>
								    		<c:when test="${result.replyPosblAt == 'Y'}">
								    			<spring:message code="button.possible" />
								    		</c:when>
								    		<c:otherwise>
								    			<spring:message code="button.impossible" />
								    		</c:otherwise>
								    	</c:choose>
									</td>
									<th>첨부파일 가능 여부</th>
									<td>
								     	<spring:message code="button.possible" /> : <input type="radio" name="fileAtchPosblAt" class="radio2" value="Y" <c:if test="${result.fileAtchPosblAt == 'Y'}"> checked="checked"</c:if>>&nbsp;
								     	<spring:message code="button.impossible" /> : <input type="radio" name="fileAtchPosblAt" class="radio2" value="N" <c:if test="${result.fileAtchPosblAt == 'N'}"> checked="checked"</c:if>>
								     	<form:errors path="fileAtchPosblAt" />		     	 
							     	 </td>
								</tr>
								<tr>
									<th>첨부파일 가능 숫자</th>
									<td colspan="3">
								     	<select title="첨부가능파일 숫자선택" name="posblAtchFileNumber" class="select">
								  		   <option selected value="0">없음</option>
								  		   <option value='1' <c:if test="${result.posblAtchFileNumber == '1'}">selected="selected"</c:if>>1개</option>
								  		   <option value='2' <c:if test="${result.posblAtchFileNumber == '2'}">selected="selected"</c:if>>2개</option>
								  		   <option value='3' <c:if test="${result.posblAtchFileNumber == '3'}">selected="selected"</c:if>>3개</option>
								  	   </select>
								  	   <br/><form:errors path="posblAtchFileNumber" />
							  	   </td>
								</tr>
<%-- 								<tr>
									<th>템플릿 정보</th>
									<td colspan="3"><input title="템플릿정보입력" name="tmplatNm" type="text" size="20" value="<c:out value="${result.tmplatNm}"/>"  maxlength="20" readonly >
	     											<input name="tmplatId" type="hidden" size="20" value='<c:out value="${result.tmplatId}"/>' readonly >
									     &nbsp;<a class="checkfile" href="javascript:fn_egov_inqire_tmplatInqire()" style="selector-dummy: expression(this.hideFocus=false);">찾아보기</a>
										 <br/><form:errors path="tmplatId" />
									 </td>
								</tr> --%>
		<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
		<c:if test="${useComment == 'true' || useSatisfaction == 'true'}">
								<tr>
									<th>추가 선택사항</th>
									<td colspan="3">
								     	<select name="option" class="select" <c:if test="${result.option != 'na'}">disabled="disabled"</c:if> title="추가선택사항">
												<option value='na' <c:if test="${result.option == 'na'}">selected="selected"</c:if>>--선택하세요--</option>
												<option value='' <c:if test="${result.option == ''}">selected="selected"</c:if>>미선택</option>
											<c:if test="${useComment == 'true' }">
												<option value='comment' <c:if test="${result.option == 'comment'}">selected="selected"</c:if>>댓글</option>
											</c:if>
											<c:if test="${useSatisfaction == 'true' }">
												<option value='stsfdg' <c:if test="${result.option == 'stsfdg'}">selected="selected"</c:if>>만족도조사</option>
											</c:if>
								  	   </select>
								  	   ※ 추가 선택사항은 수정 불가 (미설정된 기존 게시판의 경우 처음 설정은 가능함)
							  	   </td>
								</tr>
		</c:if>
								<c:if test="${not empty provdUrl}">
								<tr>
									<th width="150px">제공 URL</th>
<%-- 									<td colspan="3">
										관리자 영역 : <a href="${pageContext.request.contextPath }<c:out value="/la${provdUrl}" />" target="_new">${pageContext.request.contextPath }/la<c:out value="${provdUrl}" /></a>
										<br/>사용자 영역 : <a href="${pageContext.request.contextPath }<c:out value="/lu${provdUrl}" />" target="_new">${pageContext.request.contextPath }/lu<c:out value="${provdUrl}" /></a>
										<br/>컨텐츠 관리자 영역 : <a href="${pageContext.request.contextPath }<c:out value="/lc${provdUrl}" />" target="_new">${pageContext.request.contextPath }/lc<c:out value="${provdUrl}" /></a>
									</td> --%>
									
									<td colspan="3">
										관리자 영역 : ${pageContext.request.contextPath }/la<c:out value="${provdUrl}" />
										<br/>사용자 영역 : ${pageContext.request.contextPath }/lu<c:out value="${provdUrl}" />
									</td>
								</tr>
								</c:if>
		<!-- 2009.06.26 : 2단계 기능 추가 방법 변경 -->
							</tbody>
						</table>
						<!-- E : view-1 -->
</form:form>

						<!-- S : page-btn -->
						<div class="page-btn">
							<a href="#@" onclick="javascript:fn_egov_update_brdMstr(); return false;">수정</a><a href="#@" onclick="fn_egov_delete_brdMstr(); return false;">삭제</a><a href="#@" onclick="fn_egov_select_brdMstrList(); return false;">목록</a>
						</div>
						<!-- E : page-btn -->						
						<!-- E : 입력 폼 영역 -->