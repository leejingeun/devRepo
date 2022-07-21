<%@ page language="java" contentType ="text/html; charset=UTF-8" session="false" %>
<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page import="egovframework.com.cmm.service.EgovProperties" %>
<%@page import="egovframework.com.cmm.service.Globals" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- <c:set var="contextRoot" scope="session">${pageContext.request.contextPath}</c:set> --%>

<%

response.setHeader("Pragma","No-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
response.setHeader ("Cache-Control", "no-cache");

// String defaultPage = Globals.MAIN_PAGE"); 
String defaultPage = Globals.LOGIN_PAGE;
// System.out.println("#### defaultPage:" + defaultPage );

String retMsg = StringUtils.defaultIfBlank((String)request.getAttribute("retMsg"),"");
String nextUri = StringUtils.defaultIfBlank((String)request.getAttribute("nextUri"),defaultPage);
// System.out.println("#### nextUri1:" + nextUri );

String inputStr = "";
String[] nextUriParms = nextUri.split("&");
nextUri = nextUriParms[0];
// System.out.println("#### nextUri2:" + nextUri );

if( 1 < nextUriParms.length ){
	for( int i=1; i<nextUriParms.length; i++ ){
		String params = nextUriParms[i];
		while( params.matches("==") ){
			params = params.replace("==" ,"=");
		}
		String[] p2 = nextUriParms[i].split("=");
		String pKey = p2[0];
		String pVal = "";
		if( 2 == p2.length ){
			pVal = p2[1];
		}
		inputStr = inputStr + "<input id=\"" + pKey + "\" name=\"" + pKey + "\" type=\"hidden\" value=\"" + pVal+ "\" />";
	}
}

// System.out.println("#### retMsg:" + retMsg );
// System.out.println("#### nextUri3:" + nextUri );
 
%>
<form name="ceform" id="ceform"  method="get"><%=inputStr %></form>
<script >
<%
		
	if ( !"".equals(retMsg)){
%>
     	alert("<%= retMsg %>");
<%
	}
%>
    var f=document.ceform;
//    	f.action = "${contextRoot}/main.do";
   	f.action = "<%= nextUri %>";
    f.target = "_top";
    f.submit();
</script>
  
