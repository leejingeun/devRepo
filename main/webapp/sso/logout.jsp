<%@page import="kr.co.sitglobal.oklms.commbiz.login.web.CommBizLoginController"%>
<%@page import="egovframework.com.cmm.service.Globals"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%@ include file="../common/sp_const.jsp"
%><%

String relayState = request.getParameter(RELAY_STATE_NAME);

if(relayState == null) {
    relayState = "";
}

HttpSession session = request.getSession();
session.removeAttribute(SSO_SESSION_NAME);

/* 로컬 세션 삭제 코드 추가 */

if(session != null){
	
	CommBizLoginController loginCon = new CommBizLoginController();
	
	loginCon.cookieRemove( Globals.SESSION_SYNC_ID , request , response );

	// session 값 초기화
     String key;
     Object value;
     for (Enumeration e = session.getAttributeNames() ; e.hasMoreElements() ;) {
         key = (String) e.nextElement();
         value = session.getAttribute(key);
         session.setAttribute(key, "");
     }

     session.invalidate();
}


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript">
function logout() {
  document.logoutFrm.submit();  
}

</script>
</head>
<body onload="logout()">
  <form id="logoutFrm" name="logoutFrm" action="<%= this.generateUrl(IDP_URL, LOGOUT_URL) %>" method="post">
    <input type="hidden" name="<%= ID_NAME %>" value="<%= SP_ID %>" />
    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="<%= relayState %>" />
  </form>
</body>
</html>