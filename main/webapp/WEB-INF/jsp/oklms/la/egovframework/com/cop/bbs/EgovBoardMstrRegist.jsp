<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%
 /**
  * @Class Name : /la/egovframework/com/cop/bbs/EgovBoardMstrRegist.jsp
  * @Description : 게시판 생성 화면
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
<!-- la : EgovBoardMstrRegist.jsp -->
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cop/bbs/EgovBBSMng.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="boardMaster" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">
	function fn_egov_regist_brdMstr(){
		/* if (!validateBoardMaster(document.boardMaster)){
			return;
		} */

		//----------------------------
		// 2009.06.26 : 2단계 기능 추가
		//----------------------------
		var theForm = document.boardMaster;
		if (theForm.bbsTyCode.options[theForm.bbsTyCode.selectedIndex].value == 'BBST04' &&
				theForm.option.options[theForm.option.selectedIndex].value != '') {
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

		if (confirm('<spring:message code="common.regist.msg" />')) {
			form = document.boardMaster;
			form.action = "<c:url value='/la/cop/bbs/insertBBSMasterInf.do'/>";
			form.submit();
		}
	}

	function fn_egov_select_brdMstrList(){
		form = document.boardMaster;
		form.action = "<c:url value='/la/cop/bbs/selectBBSMasterInfs.do'/>";
		form.submit();
	}

/* 	function fn_egov_inqire_tmplatInqire(){
		form = document.boardMaster;
		var retVal;
		var url = "<c:url value='/la/commbiz/popup/openPopupWindow.do'/>"+"?requestUrl=/la/cop/tpl/selectTemplateInfsPop.do&typeFlag=BBS&width=850&height=360";
		var openParam = "dialogWidth: 850px; dialogHeight: 360px; resizable: 0, scroll: 1, center: 1";

		retVal = window.showModalDialog(url,"p_tmplatInqire", openParam);
		if (retVal != null) {
			var tmp = retVal.split("|");
			form.tmplatId.value = tmp[0];
			form.tmplatNm.value = tmp[1];
		}
	}
	
	function showModalDialogCallback(retVal) {
		if (retVal != null) {
			var tmp = retVal.split("|");
			form.tmplatId.value = tmp[0];
			form.tmplatNm.value = tmp[1];
		}
	} */
</script>

<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
						<!-- S : 입력 폼 영역 -->
<form:form commandName="boardMaster" name="boardMaster" method="post" action="${pageContext.request.contextPath}/cop/bbs/selectBBSMasterInfs.do">
<input type="hidden" name="pageIndex"  value="<c:out value='${searchVO.pageIndex}'/>"/>
<input type="hidden" name="tmplatId" value="TMPLAT_0000000000001" />
<input type="hidden" name="tmplatNm" value="전자정부 게시판 템플릿" />


						<!-- S : view-1 -->
						<table border="0" cellpadding="0" cellspacing="0" class="view-1">
							<tbody>
								<tr>
									<th width="150px">게시판 명</th>
									<td colspan="3"><form:input title="게시판명입력" path="bbsNm" size="60" cssStyle="width:100%" /><form:errors path="bbsNm" /></td>
								</tr>
								<tr>
									<th>게시판 소개</th>
									<td colspan="3"><form:textarea title="게시판소개입력" path="bbsIntrcn" cols="75" rows="4" cssStyle="width:100%" /><br/><form:errors path="bbsIntrcn" /></td>
								</tr>
								<tr>
									<th>게시판 유형</th>
									<td> <form:select path="bbsTyCode" title="게시판유형선택">
							    	  		<form:option value='' label="--선택하세요--" />
								      		<form:options items="${typeList}" itemValue="code" itemLabel="codeNm"/>
							      		</form:select>
							      		<form:errors path="bbsTyCode" />
							      	</td>
									<th>게시판 속성</th>
									<td>
										<form:select path="bbsAttrbCode" title="게시판속성선택">
							    	  		<form:option value='' label="--선택하세요--" />
								      		<form:options items="${attrbList}" itemValue="code" itemLabel="codeNm"/>
							      		</form:select>
								  	    <form:errors path="bbsAttrbCode" />
									</td>
								</tr>
								<tr>
									<th>답장 가능 여부</th>
									<td>
								     	<spring:message code="button.possible" /> : <form:radiobutton path="replyPosblAt"  value="Y" />&nbsp;
								     	<spring:message code="button.impossible" /> : <form:radiobutton path="replyPosblAt"  value="N"  />
								     	<form:errors path="replyPosblAt" />
									</td>
									<th>첨부파일 가능 여부</th>
									<td>
								     	<spring:message code="button.possible" /> : <form:radiobutton path="fileAtchPosblAt"  value="Y" />&nbsp;
								     	<spring:message code="button.impossible" /> : <form:radiobutton path="fileAtchPosblAt"  value="N"  />
								     	<br/><form:errors path="fileAtchPosblAt" />							     	 
							     	 </td>
								</tr>
								<tr>
									<th>첨부파일 가능 숫자</th>
									<td colspan="3">
								     	<form:select path="posblAtchFileNumber" title="첨부가능파일 숫자선택"	>
								  		   <form:option value="0"  label="없음" />
								  		   <form:option value='1'>1개</form:option>
								  		   <form:option value='2'>2개</form:option>
								  		   <form:option value='3'>3개</form:option>
								  	   </form:select>
								  	   <form:errors path="posblAtchFileNumber" />
							  	   </td>
								</tr>
<%-- 								<tr>
									<th>템플릿 정보</th>
									<td colspan="3"><form:input path="tmplatNm" size="20" readonly="true"/><form:input path="tmplatId"  readonly="true"/>
									     &nbsp;<a class="checkfile" href="javascript:fn_egov_inqire_tmplatInqire()" style="selector-dummy: expression(this.hideFocus=false);">찾아보기</a>
										 <br/><form:errors path="tmplatId" />
									 </td>
								</tr> --%>
		<!-- 2011.09.15 : 2단계 기능 추가 방법 변경  -->
		<c:if test="${useComment == 'true' || useSatisfaction == 'true'}">
								<tr>
									<th>추가 선택사항</th>
									<td colspan="3">
								     	<form:select path="option" title="추가선택사항선택" >
								  		   <form:option value=""  label="미선택" />
								  		   <c:if test="${useComment == 'true'}">
								  		   	 <form:option value='comment'>댓글</form:option>
								  		   </c:if>
								  		   <c:if test="${useSatisfaction == 'true'}">
								  		   	<form:option value='stsfdg'>만족도조사</form:option>
								  		   </c:if>
								  	   </form:select>
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
							<a href="#@" onclick="javascript:fn_egov_regist_brdMstr(); return false;">등록</a><a href="#@" onclick="fn_egov_select_brdMstrList(); return false;">목록</a>
						</div>
						<!-- E : page-btn -->						
						<!-- E : 입력 폼 영역 -->

						
						
						
						