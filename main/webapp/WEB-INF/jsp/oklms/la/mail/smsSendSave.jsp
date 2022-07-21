<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 14 오후 1:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<c:set var="targetUrl" value="/la/mail/mail/"/>

<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	
	$(document).ready(function() {
	//	alert("${mailVO.trCallback}");
		loadPage();	
		
		document.onkeydown = function(){
			if(event.keyCode == 116){
				window.event.keyCode = "";	
				alert("새로고침 버튼은 사용할 수 없습니다.");
				return false;
			}
		}
	 	<c:if test="${not empty param.sendSuccess}">
			alert('SMS를 발송하였습니다.');
		</c:if>
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

// 		doAction('formReset');
	}
	

	/*====================================================================
		조회버튼이나 페이지 클릭시 실행되는 함수는 꼭 doAction(sAction)으로 만들어 사용해 주시기 바랍니다.
	====================================================================*/

	

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	
	function fn_send_sms_manual(){
		// 전체 선택을 위해 속성 변경
		$("#trPhone").attr("multiple", "multiple");
	  	//중복방지 
		$("#trPhone option").attr("selected", "selected");
		if($("#trPhone").val() == null){
			alert("수신번호가 없습니다.");
			return;
		}
		if($("#smsContent").val() == ""){
			alert("내용을 입력하세요.");
			return;
		}
		if($("#trCallback").val() == ""){
			alert("발신번호가 없습니다.");
			return;
		}
		if(confirm("메세지를 전송하시겠습니까?")){
		  	//if(avoidDoubleClick()){
				$("#sendOk").removeAttr("href");
			//}
		  	
		  	var reqUrl = CONTEXT_ROOT + targetUrl + "saveSendSms.do";
			$("#frmMail").attr("method", "post" );
			$("#frmMail").attr("action", reqUrl);
			$("#frmMail").submit();	
			
		}else{
			$("#trPhone").attr("multiple", "");
		}
	}
	
	// 새로운 아이템 생성
	function fn_add_phoneNo(){
		var strName = $("#phoneNo").val();
		
		if(strName != ""){
			$("#trPhone").append("<option value='"+strName+"'>"+strName+"</option>");
			$("#phoneNo").val("");
		}
	}
	
	// 선택한 아이템을 삭제 함
	function fn_delete_phoneNo(){
		$("select[name='trPhone'] option:selected").remove();
	}
	
	//SMS 바이트 제한
	function fn_limitByte(){ 
		var len=0, j; 
		var smsValue = $("#smsContent").val()
		for (i=0, j=smsValue.length;i<j;i++, len++) { 
	 		if ( (smsValue.charCodeAt(i)<0)||(smsValue.charCodeAt(i)>127) ){ 
				len = len+1; 
			} 
			if (len >= 90) {
				alert( "최대 90 Bytes 까지만 보낼 수 있습니다." );
				$("#smsContent").val(smsValue.substring(0,i)); 
				$("#smsContent").focus(); 
				return; 
			} 
		}
		$("#inputByte").text(len);
	}
	
</script>
<div class="title-name-1">기본정보</div>

<form id="frmMail" name="frmMail"  method="post">
	<input type="hidden" 		id="scLogTable"  						name="scLogTable"	 				value="${mailVO.scLogTable }" />
	<input type="hidden"	    id ="senderMemSeq"  				name="senderMemSeq"				value ="${mailVO.senderMemSeq}"/>
	<input type="hidden"	    id ="tFlag"  				name="tFlag"				value ="0"/>
 


<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
		<tr>
			<th width="10%">Byte 수</th>
			<td colspan="5"><span id="inputByte">0</span>byte</td>
		</tr>
		<tr>
			<th>수신번호</th>
			<td colspan="5"><input name="phoneNo"  id="phoneNo" style="width:80%;" enter="fn_add_phoneNo()"/> <a href="#fn_add_phoneNo" onclick="javascript:fn_add_phoneNo();" class="btn-1">추가</a></td>
		</tr>
		<tr>
			<th>번호목록</th>
			<td colspan="5">
				<select name="trPhone"  id="trPhone" multiple="multiple" style="width:100%; height:154px;" title="번호목록을 선택하세요"></select>
				<a href="#fn_delete_phoneNo" onclick="fn_delete_phoneNo()" class="btn-1">삭제</a></td>
		</tr>
		<tr>
			<th>발신번호</th>
			<td colspan="5"><input type="text" name="trCallback" id="trCallBack" value="${mailVO.trCallback}" class="input_style number" maxlength="11" /></td>
		</tr>
		<tr>
			<th>SMS 내용</th>
			<td colspan="5"><textarea name="smsContent"  id="smsContent" onkeyup="fn_limitByte();"></textarea></td>
		</tr>
	</tbody>
</table><!-- E : view-1 -->
</form>

<div class="page-btn">
	<a href="#fn_send_sms_manual" onclick="javascript:fn_send_sms_manual();" onkeypress="this.onclick;">보내기</a>
</div><!-- E : page-btn -->	

