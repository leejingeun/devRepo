<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="false"

%><%@ include file="../common/sso_common.jsp" 
%><%

String relayState = request.getParameter(RELAY_STATE_NAME);
String errCode = request.getParameter(FAILURE_CAUSE);

%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
alert("<%= errCode %>");
location.href = "<%= relayState %>";
</script>
</head>
</html>