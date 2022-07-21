<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%@ include file="../common/sp_const.jsp" 
%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<h2>SP Login</h2>
<br/>

<form id="spLoginFrm" name="spLoginFrm" method="post" action="<%= this.generateUrl(IDP_URL, LOGIN_URL) %>">
    아이디 : <input type="text" name="user_id" /><br/>
    비밀번호 : <input type="text" name="user_password" />
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= addContextPath(request, "/sample/main.jsp") %>" />
    <input type="hidden" name="<%= ID_NAME %>" value="<%= SP_ID %>" />
    <input type="hidden" name="<%= TARGET_ID_NAME %>" value="<%= SP_ID %>" />
    
    <input type="submit" value="SP로그인" />
</form>

</body>
</html>