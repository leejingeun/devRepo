<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="targetUrl" value="/la/popup/popup/"/>

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
		document.onkeydown = function(){
			if(event.keyCode == 116){
				window.event.keyCode = "";	
				alert("새로고침 버튼은 사용할 수 없습니다.");
				return false;
			}
		}
		
		var sendSuccess = "N";
		sendSuccess = '${sendSuccess}';
	 	if("Y" == sendSuccess){
			alert('SMS를 발송하였습니다.');
			self.close();
	 	}
	 	
		$(".requare").css("color", "red");
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	
	function fn_send_sms(){
		if($("#sendName").val() == null){
			alert("보내는 사람 정보가 없습니다.");
			return;
		}
		if($("#trCallback").val() == ""){
			alert("발신번호가 없습니다.");
			return;
		}
		if($("#receiveName").val() == null){
			alert("받는 사람 정보가 없습니다.");
			return;
		}
		if($("#trPhone").val() == null){
			alert("수신번호가 없습니다.");
			return;
		}
		if($("#smsContent").val() == ""){
			alert("내용을 입력하세요.");
			return;
		}
		
		if(confirm("메세지를 전송하시겠습니까?")){
		  	
		  	var reqUrl = CONTEXT_ROOT + targetUrl + "popupMemberInfoSaveSendSms.do";
			$("#frmMail").attr("method", "post" );
			$("#frmMail").attr("action", reqUrl);
			$("#frmMail").submit();	
			
		}
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

<form id="frmMail" name="frmMail"  method="post">
<%-- <input type="hidden" id="scLogTable"  	name="scLogTable"	 value="${mailVO.scLogTable }" /> 
<input type="hidden" id ="senderMemSeq" name="senderMemSeq"	 value ="${mailVO.senderMemSeq}"/>--%>
<input type="hidden" id ="tFlag"  		name="tFlag"		 value ="0"/>
 
<div id="popup-wrarpr">
			<div id="popup-header">
				<h1><img src="../../image/inc/pop_border_02.png" alt="" />SMS보내기</h1>
				<p><a href="#" onclick="parent.close();">닫기</a></p>
			</div><!-- E : p-header -->

			<div id="popup-content-area">
				<div id="popup-container">

<!-- 팝업 내용 영역 시작 : 가로 650px , 세로 560px -->

<p style="display:block;text-align:left;"><font color="red">*</font> 는 필수입력사항입니다.</p>
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th width="132px"><span class="requare">*</span>보내는 사람</th>
			<td>
				<input type="text" id="sendName" name="sendName" value="${sendName}" style="width:99%" readOnly/>		
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>발신번호</th>
			<td>
				<input type="text" id="trCallback" name="trCallback" value="${trCallback}" style="width:99%" readOnly/>
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>받는 사람</th>
			<td>
				<input type="text" id="receiveName" name="receiveName" value="${receiveName}" style="width:99%" readOnly/>
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>수신번호</th>
			<td>
				<input type="text" id="trPhone" name="trPhone" value="${trPhone}" style="width:99%" readOnly/>
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>SMS 내용</th>
			<td>
				<textarea name="smsContent" id="smsContent" style="width:99%;height:150px" onkeyup="fn_limitByte();"></textarea>
				</br>
				<span id="inputByte">0</span>byte
			</td>      
		</tr>
	</tbody>
</table><!-- E : view-1 -->

<!-- 팝업 내용  영역 끝 -->

			<div class="page-btn">
				<a href="#fn_send_sms" onclick="javascript:fn_send_sms();" onkeypress="this.onclick;">SMS보내기</a>
			</div><!-- E : page-btn -->

	</div><!-- E : p-contentiner -->
</div><!-- E : p-content-area -->


<div id="popup-footer"></div><!-- E : p-footer -->
</div><!-- E : p-wrapper -->
</form>

