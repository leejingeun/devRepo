<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근C    2016. 10. 27 오후 1:20         Modify Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<c:set var="targetUrl" value="/la/member/member/"/>

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

	/* function press(event) {
		if (event.keyCode==13) {
			fn_search();
		}
	} */
	
	/* 단체회원 등록 */
	function fn_excel_upload(){
		
		if($("input:file[name=uploadExcelFile]").val() == ""){
			alert("첨부할 파일이 존재하지 않습니다.");
			return;
		}
		
		var src = $("#uploadExcelFile").val();
		
		 if(!src.match(/\.(xls)$/i)) {
		      alert("엑셀(xls) 파일만 업로드 가능합니다.");
		      return;
		}
		
		if(confirm("작성한 엑셀파일로 회원단체 등록 하시겠습니까?")){
			
		    var reqUrl = CONTEXT_ROOT + targetUrl + "insertExcelMember.do";
		    
		    $("#frmMemberExcel").attr("method", "post" );
			$("#frmMemberExcel").attr("enctype", "multipart/form-data" );
			$("#frmMemberExcel").attr("action", reqUrl);
			$("#frmMemberExcel").attr("target","_self");
			$("#frmMemberExcel").submit();
		}
	}
	
	/* 단체회원 양식 다운로드 */
	function fn_file_down(){
		var uploadFilePath = "/downloadfiles/";
	    $("#filename").val("allMemberSaveForm.xls");
	    $("#uploadFilePath").val(uploadFilePath);
	    
	    var reqUrl = CONTEXT_ROOT + "/simpleDown.sv";
	    $("#frmDownLoad").attr("action",reqUrl);
	    $("#frmDownLoad").submit();
	}
	
</script>

<form id="frmDownLoad" >
	<input type="hidden"  name="filename" id="filename"  />
	<input type="hidden"  name="uploadFilePath" id="uploadFilePath" />
</form>
<form id="frmMemberExcel" name="frmMemberExcel" action="<c:url value='/la/memberall/memberall/listExcelMember.do'/>" >
<ul class="search-list-1">
	<li>
		<span>파일 다운로드</span>
		<a href="javascript:fn_file_down();"><b>일괄 회원등록 양식.xls</b></a>&nbsp;서식파일을 다운받아 파일 양식에 맞게 작성해 주세요.<br />
		( 소속[회사명] / 성명[홍길동] / 메일주소[admin@origin.or.kr] / 핸드폰 번호 [011-470-9039] / 생년월일 [1980-0101] )<br />
		<b><font color="blue">* 참고사항 : 당일등록된 단체등록건만 목록에 조회됩니다.</font></b>
	</li>
	<li>
		<span>파일 업로드</span>
		<input name="uploadExcelFile" class="form_01 checkFileType" id="uploadExcelFile" type="file" style="width:50%; height:16px;" />
	</li>
</ul><!-- E : search-list-1 -->
</form>

<table border="0" cellpadding="0" cellspacing="0" class="list-1">
	<thead>
		<tr>
			<th width="15%">소속</th>
			<th width="12%">성명</th>
			<th width="18%">아이디</th>
			<th width="10%">비밀번호</th>
			<th width="18%">이메일</th>
			<th width="18%">핸드폰번호</th>
			<th width="9%">등록결과</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td>${result.compNm}</td>
			<td>${result.memName}</td>
			<td>${result.memId}</td>
			<td>${result.pwdQuestAns}</td>
			<td>${result.email}</td>
			<td>${result.hpNo1}-${result.hpNo2}-${result.hpNo3}</td>
			<td>
				<c:choose>
					<c:when test="${result.pwdQuest eq '1'}">
				정상
					</c:when>
					<c:when test="${result.pwdQuest eq '3'}">
				이메일 중복
					</c:when>
					<c:when test="${result.pwdQuest eq '4'}">
				핸드폰 중복
					</c:when>
					<c:otherwise>
				기타 오류
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		<tr>
			<td colspan="7"><spring:message code="common.nodata.msg" /></td>
		</tr>
		</c:if>
	</tbody>
</table><!-- E : list -->

<div class="page-btn">
	<a href="#" onclick="fn_excel_upload();">등록</a>
</div><!-- E : page-btn -->
					

	