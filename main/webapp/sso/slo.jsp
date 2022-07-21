<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%

HttpSession session = request.getSession();
session.removeAttribute(SSO_SESSION_NAME);

%>