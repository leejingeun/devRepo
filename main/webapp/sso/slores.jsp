<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%

String relayState = request.getParameter(RELAY_STATE_NAME);

if(relayState != null && !"".equals(relayState.trim())) {
    response.sendRedirect(relayState);
    return;
} else {
	 response.sendRedirect("/");
}

%>