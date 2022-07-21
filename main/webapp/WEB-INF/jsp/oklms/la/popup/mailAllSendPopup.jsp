<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

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
		if("${successYn}" == 'Y'){
			alert("메일발송이 성공하였습니다.");
			self.close();
		}
		$(".requare").css("color", "red");
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	
	function fn_send_mail(){
		if( com.isBlank( $("#authGroupId").val() ) ){
			alert("받는 사람를 선택하세요.");
			return false;
		}
		if( com.isBlank( $("#mailTitle").val() ) ){
			alert("제목을 입력하세요.");
			return false;
		}
		if( com.isBlank( $("#mailContent").val() ) ){
			alert("내용을 입력하세요.");
			return false;
		}
		if(confirm("메일을 발송하시겠습니까?")){
			
		  	var reqUrl = CONTEXT_ROOT + "/la/popup/popup/saveMemberInfoAllMailSend.do";
		  	
			$("#frmMail").attr("method", "post" );
			$("#frmMail").attr("target","_self");
			$("#frmMail").attr("action", reqUrl);
			$("#frmMail").submit();	
		}
	}
	
</script>

<form id="frmMail" name="frmMail"  method="post">
<input type="hidden" name="templateId" id="templateId"/>
 
<div id="popup-wrarpr">
			<div id="popup-header">
				<h1><img src="../../image/inc/pop_border_02.png" alt="" />메일보내기</h1>
				<p><a href="#" onclick="parent.close();">닫기</a></p>
			</div><!-- E : p-header -->

			<div id="popup-content-area">
				<div id="popup-container">

<!-- 팝업 내용 영역 시작 : 가로 650px , 세로 560px -->

<p style="display:block;text-align:left;"><font color="red">*</font> 는 필수입력사항입니다.</p>
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th width="132px">보내는 사람</th>
			<td>
				<input type="text" id="sendName" name="sendName" value="${memberVO.sessionMemName}" style="width:99%" readOnly/>		
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>받는 사람</th>
			<td>
				<select name="authGroupId" id="authGroupId" style="width:120px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="authGroupCd" items="${authGroupCode}" varStatus="status">
						<option value="${authGroupCd.codeId}">${authGroupCd.codeName}</option>
					</c:forEach>
				</select>
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>제목</th>
			<td>
				<input type="text" id="mailTitle" name="mailTitle" maxlenght="200" style="width:99%" />
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>내용</th>
			<td>
				<textarea name="mailContent" id="mailContent" style="width:99%;height:150px"></textarea>
			</td>      
		</tr>
	</tbody>
</table><!-- E : view-1 -->

<!-- 팝업 내용  영역 끝 -->

			<div class="page-btn">
				<a href="#fn_send_mail" onclick="javascript:fn_send_mail();" onkeypress="this.onclick;">메일보내기</a>
			</div><!-- E : page-btn -->

	</div><!-- E : p-contentiner -->
</div><!-- E : p-content-area -->


<div id="popup-footer"></div><!-- E : p-footer -->
</div><!-- E : p-wrapper -->
</form>

