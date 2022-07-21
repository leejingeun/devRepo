<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%@ include file="../common/sp_const.jsp" 
%><%

java.util.Map paramMap = new java.util.HashMap();
paramMap.put(ID_NAME, SP_ID);
paramMap.put(AC_NAME, "Y");
paramMap.put(IFA_NAME, "N");

String relayState = request.getParameter(RELAY_STATE_NAME);
if(relayState != null && !"".equals(relayState.trim())) {
    paramMap.put(RELAY_STATE_NAME, relayState);    
}

String redirectUrl = this.generateUrlWithParam(IDP_URL, AUTH_URL, paramMap);
response.sendRedirect(redirectUrl);

return;

%>