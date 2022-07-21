<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%@ include file="../common/sp_const.jsp" 
%><%

HttpSession session = request.getSession();
String eXSignOnUserId = (String) session.getAttribute(SSO_SESSION_NAME);
//System.out.println("=========== eXSignOnUserId : "+eXSignOnUserId);
if(eXSignOnUserId == null || "".equals(eXSignOnUserId) || SSO_SESSION_ANONYMOUSE.equals(eXSignOnUserId)) {

    if(SSO_SESSION_ANONYMOUSE.equals(eXSignOnUserId)) {
        session.removeAttribute(SSO_SESSION_NAME);
    }
    
    java.util.Map paramMap = new java.util.HashMap();
    paramMap.put(ID_NAME, SP_ID);
    paramMap.put(AC_NAME, "N");
    paramMap.put(IFA_NAME, "N");
        
    StringBuffer requestUrl = new StringBuffer(request.getRequestURI());
    String queryStr = request.getQueryString();
    
    if(queryStr != null && !"".equals(queryStr.trim())) {
        requestUrl.append("?");
        requestUrl.append(queryStr);
    }
    
    paramMap.put(RELAY_STATE_NAME, requestUrl.toString());

    String redirectUrl = this.generateUrlWithParam(IDP_URL, AUTH_URL, paramMap);

    response.sendRedirect(redirectUrl);
    
    return;
} else if(SSO_SESSION_ANONYMOUSE_IDENTIFY.equals(eXSignOnUserId)) {
    session.setAttribute(SSO_SESSION_NAME, SSO_SESSION_ANONYMOUSE);
    eXSignOnUserId = (String) session.getAttribute(SSO_SESSION_NAME);
    
    //System.out.println("=========== eXSignOnUserId : "+eXSignOnUserId);
}
%>