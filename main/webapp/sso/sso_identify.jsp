<%@ page language="java"
contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"
session="false"
%>
<%@ include file="../common/sso_common.jsp" %>
<%@ include file="../common/sp_const.jsp"%>
<%

response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setDateHeader("Expires",0);   
if("HTTP/1.1".equals(request.getProtocol())) {
    response.setHeader("Cache-Control", "no-cache");
}

String token = request.getParameter(TOKEN_NAME);
String status = request.getParameter(STATUS_NAME);
String relayState = request.getParameter(RELAY_STATE_NAME);

if((token == null || "".equals(token.trim())) && (status == null || "".equals(status.trim()))) {
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
}

//매 접근마다 session id 변경
HttpSession session = request.getSession(false);
if(session != null) {
  java.util.Enumeration attrNames = session.getAttributeNames();
  
  java.util.Map attrMap = null;
  if(attrNames != null) {
      attrMap = new java.util.HashMap();
      
      while(attrNames.hasMoreElements()) {
          String name = (String) attrNames.nextElement();
          attrMap.put(name, session.getAttribute(name));
      }
  }
  
  session.invalidate();    
  
  session = request.getSession();
  
  if(attrMap != null && !attrMap.isEmpty()) {
      java.util.Iterator nameIter = attrMap.keySet().iterator();
      while(nameIter.hasNext()) {
          String key = (String) nameIter.next();
          session.setAttribute(key, attrMap.get(key));
      }
  }
} else {
  session = request.getSession();
}

if(token != null && !"".equals(token.trim())) {
    java.util.Map paramMap = new java.util.HashMap();
    paramMap.put(ID_NAME, SP_ID);
    paramMap.put(SECRET_NAME, SP_SECRET);
    paramMap.put(TOKEN_NAME, token);

    String url = this.generateUrl(IDP_URL, AUTH_RESOLVE_URL);

    String resultData = this.httpRequest(url, paramMap);

    org.json.simple.JSONObject obj = (org.json.simple.JSONObject) org.json.simple.JSONValue.parse(resultData);

    String success = (String) obj.get(STATUS_NAME);

    if(!SUCCESS.equals(success)) {
        String id = (String) session.getAttribute(SSO_SESSION_NAME);
        
        if(id != null && !"".equals(id.trim())) {
            String errCode = null;
            
            errCode = (String) obj.get(FAILURE_CAUSE);    
            
            if((errCode == null && "".equals(errCode.trim())) && (status != null && !"".equals(status.trim()))) {
                errCode = request.getParameter(FAILURE_CAUSE);
            }

            /* 토큰 검증 실패에 대한 에러 처리 */
            String failUrl = addContextPath(request, TOKEN_VERIFY_FAIL_URL);
            
            java.util.Map param = new java.util.HashMap();
            param.put(RELAY_STATE_NAME, relayState);
            param.put(FAILURE_CAUSE, errCode);
            
            response.sendRedirect(failUrl + "?" + generateParam(param));
            
            return;
            
        } else {
            session.setAttribute(SSO_SESSION_NAME, SSO_SESSION_ANONYMOUSE_IDENTIFY);
        }

    } else {
        String data = (String) obj.get(DATA_NAME);
        session.setAttribute(SSO_SESSION_NAME, data);
    }
} else {
    session.setAttribute(SSO_SESSION_NAME, SSO_SESSION_ANONYMOUSE_IDENTIFY);    
}

if(relayState != null && !"".equals(relayState.trim())) {
    response.sendRedirect(relayState);
    return;
}

%>