<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.apache.commons.lang3.time.DateUtils" %>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils" %>
<%@ page import="kr.co.sitglobal.oklms.commbiz.util.BizUtil" %>
<%@ page import="kr.co.sitglobal.oklms.comm.vo.LoginInfo" %>
<%@ page import="egovframework.com.cmm.service.Globals" %>
<%@ page import="egovframework.com.cmm.LoginVO" %>
<%-- <%@ page import="kr.co.sitglobal.oklms.sys.menu.vo.MenuMngmVO" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- oklms Header -->
<%
LoginInfo loginInfo = new LoginInfo(); 
%>
<!-- ############### LU Header ############### -->
<ul id="header">
	<li class="logo-area"><h1><a href="/lu/main/lmsUserMainPage.do">한국기술교육대학교</a></h1></li>
	<li class="gnb-area">
<%
if("STD".equals(loginInfo.getMemType())){
	// 학습근로자
%>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a1" class="type-1<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1 == 0}">-on</c:if>">학습활동서<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a2" class="type-3<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2 == 0}">-on</c:if>">Q&A<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a3" class="type-7<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3 == 0}">-on</c:if>">과제<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a4" class="type-6<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4 == 0}">-on</c:if>">팀프로젝트<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a5" class="type-5<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt5 == 0}">-on</c:if>">토론<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt5}</span>건</a>
<%   
}else if("CDP".equals(loginInfo.getMemType())){
	// 학과전담자
%>
	<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a1" class="type-3<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1 == 0}">-on</c:if>">Q&A<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1}</span>건</a>
	<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a2" class="type-7<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2 == 0}">-on</c:if>">과제<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2}</span>건</a>
	<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a3" class="type-6<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3 == 0}">-on</c:if>">팀프로젝트<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3}</span>건</a>
	<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a4" class="type-5<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4 == 0}">-on</c:if>">토론<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4}</span>건</a>
	<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a5" class="type-1<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt6 == 0}">-on</c:if>">제직증빙서류<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt6}</span>건</a>
<%   
}else if("CCN".equals(loginInfo.getMemType())){
	// 조회시간 문제로 예외처리 센터담당자
%>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a1" class="type-1<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1 == 0}">-on</c:if>">주간훈련일지<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a2" class="type-3<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2 == 0}">-on</c:if>">학습활동서<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a3" class="type-7<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3 == 0}">-on</c:if>">면담일지<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a4" class="type-6<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4 == 0}">-on</c:if>">훈련시간표<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt4}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a5" class="type-5<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt5 == 0}">-on</c:if>">담당자변경<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt5}</span>건</a>

<%   
}else if("COT".equals(loginInfo.getMemType())){
	// 기업현장교사
%><%   
}else if("PRT".equals(loginInfo.getMemType())){
	// 교수
%><%   
}else if("CCM".equals(loginInfo.getMemType())){
	// HRD담당자	
%>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a1" class="type-1<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1 == 0}">-on</c:if>">활동내역서<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt1}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a2" class="type-3<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2 == 0}">-on</c:if>">주간훈련일지<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt2}</span>건</a>
		<a href="/lu/today/lmsUserTodayPage.do?areaFocus=a3" class="type-7<c:if test="${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3 == 0}">-on</c:if>">학습활동서<span>${listLmsUserMainPageTodayCnt[0].myPageTodayCnt3}</span>건</a>
<%   
}else{
%>

<%		
}
%>
	</li>
</ul><!-- E : header : 상단 헤더 영역-->
<!-- ############### // LU Header ############### -->
 