<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../include/sso_entry.jsp" 
%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>

<h2>Hello!! <%= eXSignOnUserId %></h2>

<br/>

<% 
if(SSO_SESSION_ANONYMOUSE.equals(eXSignOnUserId)) {
%>
<form id="idpLoginFrm" name="idpLoginFrm" method="post" action="../sso/sso_idp_login.jsp">
    <input  value="IDP로그인" type="submit" />
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= addContextPath(request, "/sample/main.jsp") %>" />
</form>
<form id="spLoginFrm" name="spLoginFrm" method="post" action="sp_login.jsp">
    <input value="SP로그인" type="submit" />
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= addContextPath(request, "/sample/main.jsp") %>" />
</form>
<form id="assertLoginFrm" name="assertLoginFrm" method="post" action="../sso/sso_assert.jsp">
  <input type="text" name="<%= NAMEID_NAME %>" />
  <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= addContextPath(request, "/sample/main.jsp") %>" />
  <input type="hidden" name="<%= TARGET_ID_NAME %>" value="<%= SP_ID %>" />
  <input value="강제로그인" type="submit" />
</form>
<%
} else {
%>
<form id="logoutFrm" name="logoutFrm" method="post" action="../sso/logout.jsp">
    <input type="submit" value="로그아웃"  />
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= addContextPath(request, "/sample/main.jsp") %>" />
</form>
<%
}
%>

</body>
</html>