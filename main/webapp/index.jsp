<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/includeJs.jsp"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="kr.co.sitglobal.oklms.commbiz.util.BizUtil" %>
<%@ page import="kr.co.sitglobal.oklms.comm.vo.LoginInfo" %>
<%@ page import="egovframework.com.cmm.service.Globals" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<script type="text/javascript">

	// includeJs.jsp에서 파일들을 정상 로드 하지 못하는 경우(urlrewrite되는 경우).
	function isBlank(obj){
		return (!obj || /^\s*$/.test(obj) || 'null' == obj );
	}
	var CONTEXT_ROOT = "<%= request.getContextPath() %>";
	
	var targetURL = CONTEXT_ROOT;
	targetURL = targetURL + "/lu/main/lmsUserMainPage.do";

//  	window.location = targetURL;
	
	//Controller에서  전달된 메시지를 출력한다. ( gridUtil.setRetMsg 값 )
	$(document).ready(function(){
		if(""!="${retMsg}"){
			alert("${retMsg}");
		}
	});
	

</script>
<%-- <c:redirect url="/main/mainPage.do?ipAddress=${ipAddress }&macAddress=${macAddress }" /> --%>


<h1>목록</h1>
<jsp:forward page="/lu/login/goLmsUserLogin.do"/>


<dl> 
  <dt>프로토타입</dt>
  <dd>
    <ol>
      <li><a href="/prototype/protoBoard/protoBoardMain.do">프로토타입Main</a></li>
    </ol>
  </dd>

  <dt>LMS 사용자</dt>
  <dd>
    <ul>
		<li><a href="/lu/main/lmsUserMainPage.do">LMS 사용자 메인페이지</a></li>
		<li><a href="/lu/login/goLmsUserLogin.do">LMS 사용자 로그인</a></li>
    </ul>
  </dd> 
  
  <dt>LMS 관리자</dt>
  <dd>
    <ul>
		<li><a href="/la/main/lmsAdminMainPage.do">LMS 관리자 메인페이지</a></li>
		<li><a href="/la/login/goLmsAdminLogin.do">LMS 관리자 로그인</a></li>
    </ul>
  </dd>
  
<dt>LCMS 관리자</dt>
  <dd>
    <ul>
		<li><a href="/lc/main/lcmsMainPage.do">LCMS 관리자 메인페이지</a></li>
		<li><a href="/lc/login/goLcmsLogin.do">LCMS 관리자 로그인</a></li>
    </ul>
  </dd> 
</dl>


<br/>
<br/>
 
	<%
     String key;
     Object value;
     for (Enumeration e = session.getAttributeNames() ; e.hasMoreElements() ;) {
         key = (String) e.nextElement();
         value = session.getAttribute(key);
         out.println(key+"="+value+"<br/>");
     } 
%>

<%
	LoginInfo loginInfo = new LoginInfo(request, response);
%>

        	<br/>
        	<br/>
	[loginInfo.getUserName() : <%=loginInfo.getUserName()%>]<br/>
	[session.getAttribute : <%=session.getAttribute("SESSION_USER_ID") %>]<br/>
	[session.getAttribute.getUserName() : <%= loginInfo.getUserName() %>]<br/>
	
	[로그인 첵크 값 ==>> session.getAttribute ( CONNECTION_USER_ID ) : <%= (String)session.getAttribute("CONNECTION_USER_ID") %>]
	
        	<br/>
        	
        	
<br/>
<br/>
쿠키 목록<br>
<%@ page import="java.net.URLDecoder"%>
<%
	Cookie[] cookies = request.getCookies();
	if (cookies != null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
%>
<%=cookies[i].getName()%>
<%=URLDecoder.decode(cookies[i].getValue(), "euc-kr")%><br>
<%
	// 디코딩 해서 값을 읽어온다.
		}
	} else {
%>
쿠키가 존재하지 않습니다.
<%
	}
%>
<br/>
<br/>
