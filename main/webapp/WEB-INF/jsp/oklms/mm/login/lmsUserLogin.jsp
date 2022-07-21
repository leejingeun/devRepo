<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%@ include file="/common/sso_common.jsp"%> 
<%@ include file="/common/sp_const.jsp"%> 

<% 
	String contextRootJS = request.getContextPath();
	String ssoYn =  EgovProperties.getProperty("Globals.ssoYn");
%>
<c:set var="ssoYn" value="<%=ssoYn %>"/>
<script type="text/javascript">
<!--


function fnAfterCheckLogin(){
	var bodyMemId = "";
	var bodyMemPasswordEncript = "";
	bodyMemId = $('#bodyMemId').val();
	bodyMemPasswordEncript = $('#bodyMemPasswordEncript').val();

	if(bodyMemId == ""){
		alert("아이디를 입력해 주세요.");
		$("#bodyMemId").focus();
		return false;
	} 
	
	if(bodyMemPasswordEncript == ""){
		alert("패스워드를 입력해 주세요.");
		$("#bodyMemPasswordEncript").focus();
		return false;
	}
	
	
	<c:choose>
		<c:when test="${ssoYn eq 'Y'}">
			$("#user_id").val(bodyMemId);
			$("#user_password").val(bodyMemPasswordEncript);
			if($("#sso-login").is(":checked") == true){
				fn_loginProcSso();
			} else {
				fn_loginProcBody();
			}
		</c:when>
		<c:otherwise>
			fn_loginProcBody();
		</c:otherwise>
	</c:choose>
}

function fn_loginProcSso(){
	var $form  = $("#spLoginFrm");			
	$form.attr("target", "");
	$form.submit();
}
function fn_loginProcBody(){
	var url = "/commbiz/login/loginProc.do";
	var $form  = $("#loginProcBodyForm");			
	$form.attr("target", "");
	$form.attr("action", url);		
	$form.submit();
}


$(document).ready(function() {

	var checkId = getId('id-save');
	if(checkId != null && checkId != ""){
		window.onload=function(){
			document.getElementById("uid").value=checkId;
			document.getElementById("id-save").setAttribute('checked','checked');
		}
	}
	
});

function idSave(name,idValue,day){
	//체크여부
	var check = document.getElementById("id-save").checked;
	if(check == true){
		var idValue = document.getElementById("uid").value;
	}else{
		day=-1;
	}
	var expire =new Date();
	
	expire.setDate(expire.getDate()+day);
	
	cookies = name + '=' + escape(idValue) + ';path=/';
	if(typeof day != 'undefined' ) cookies += ';expires='+expire.toGMTString() +';';
	document.cookie = cookies;
}
 
function getId(name){
	name =name+'=';
	var cookieData= document.cookie;
	var start =cookieData.indexOf(name);
	var idValue='';
	if(start != -1){
		start += name.length;
		var end =cookieData.indexOf(';',start);
		if(end==-1)end=cookieData.length;
		idValue=cookieData.substring(start,end);
	}
	return unescape(idValue);
}
//-->
</script>
<form id="spLoginFrm" name="spLoginFrm" method="post" action="<%= this.generateUrl(IDP_URL, LOGIN_URL) %>">
     
    <input type="hidden" id="user_id" name="user_id" />
    <input type="hidden" id="user_password" name="user_password"/>
     
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="/commbiz/login/ssoLoginProc.do" />
    <input type="hidden" name="<%= ID_NAME %>" value="<%= SP_ID %>" />
    <input type="hidden" name="<%= TARGET_ID_NAME %>" value="<%= SP_ID %>" />
    <!-- <input type="submit" value="SP로그인" /> -->
</form>
			<div id="container">
				<div id="login-area" class="login-area gLogin">

					<form id="loginProcBodyForm" method="post" action="/commbiz/login/loginProc.do" class="gLogin">
						<input id="reqUri" name="reqUri" type="hidden" value="${reqUri }">
						<input id="menuArea" name="menuArea" type="hidden" value="MOBILE">
						 
						<input id="nextUri" name="nextUri" type="hidden" value="<c:if test="${empty reqUri}" >/mm/main/lmsMainPage.do</c:if><c:if test="${!empty reqUri}" >${reqUri}</c:if>">
						<fieldset>
							<legend>학습관리통합시스템<b>OK-LMS</b></legend>


							<p class="item"><label for="uid" class="iLabel">ID</label><input type="text" name="memId" type="text" id="uid" class="iText uid" /></p>
							<p class="item"><label for="upw" class="iLabel">PASSWORD</label><input type="password" name="memPasswordEncript" type="password" id="upw" class="iText upw"  /></p>
							<p class="btn"><input name="login-btn" type="button" value="로그인" onclick="javascript:fnAfterCheckLogin();return false;"/></p>


							<span>
								<label for="id-save"><input name="checkbox" type="checkbox" id="id-save" onclick="javascript:idSave('id-save','',1);"/>아이디 저장</label>
								<label for="login-keep"><input name="checkbox" type="checkbox" id="login-keep" />로그인 상태 유지</label>
							</span>
						</fieldset>
					</form>

					<ul>
						<li>정보보호를 위해 비밀번호는 주기적으로 변경하시고, 타인에게 노출되지 않도록 주의하시기 바랍니다.</li>
						<li>원하시는 서비스를 이용하신 후 정보보호를 위해 반드시 로그아웃을 하여 주십시오.</li>
					</ul>

				</div><!-- E : login-area -->
			</div><!-- E : container -->