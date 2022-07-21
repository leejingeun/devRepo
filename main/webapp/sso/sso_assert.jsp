<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%@ include file="../common/sp_const.jsp" 
%><%

//사용자 인증정보 (파라미터키 : nameId)
String nameId = request.getParameter(NAMEID_NAME);
//인증 후 리턴 될 URL (파라미터키 : RelayState)
String relayState = request.getParameter(RELAY_STATE_NAME);
//인증 시킬 연계서버 ID (파라미터키 : targetId)
String targetSp = request.getParameter(TARGET_ID_NAME);

if((nameId == null || "".equals(nameId.trim())) || (targetSp == null || "".equals(targetSp.trim()))) {
    response.sendError(HttpServletResponse.SC_FORBIDDEN);
    return;
}

//전달 받은 파라미터를 기반으로 토큰 생성
java.util.Map paramMap = new java.util.HashMap();
paramMap.put(ID_NAME, SP_ID);
paramMap.put(SECRET_NAME, SP_SECRET);
paramMap.put(NAMEID_NAME, nameId);

String url = this.generateUrl(IDP_URL, AUTH_ASSERT_URL);
String resultData = this.httpRequest(url, paramMap);

org.json.simple.JSONObject obj = (org.json.simple.JSONObject) org.json.simple.JSONValue.parse(resultData);

String success = (String) obj.get(STATUS_NAME);

if(SUCCESS.equals(success)) {
    String data = (String) obj.get(DATA_NAME);
    
    java.util.Map fedParam = new java.util.HashMap();
    fedParam.put(TOKEN_NAME, data);
    fedParam.put(ID_NAME, SP_ID);
    fedParam.put(TARGET_ID_NAME, targetSp);
    fedParam.put(RELAY_STATE_NAME, relayState);
    
    String federateUrl = this.generateUrlWithParam(IDP_URL, AUTH_FEDERATE_URL, fedParam);
    
    //인증서버로부터 전달 받은 토큰을 포함하여 인증 시킬 연계서버로 Federate (인증서버 인증 및 연계서버 이동)
    response.sendRedirect(federateUrl); 
    
    return;
} else {
    String errCode = null;
    errCode = (String) obj.get(FAILURE_CAUSE);    
    
    java.util.Map param = new java.util.HashMap();
    param.put(FAILURE_CAUSE, errCode);
    param.put(RELAY_STATE_NAME, relayState);
    
    String failUrl = addContextPath(request, TOKEN_VERIFY_FAIL_URL);
    response.sendRedirect(failUrl + "?" + generateParam(param));
    
    return;
}
%>