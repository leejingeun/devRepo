<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String toDay = new SimpleDateFormat("yyyy.MM.dd").format(new Date()).toString();
	String toDayHour = new SimpleDateFormat("HH").format(new Date()).toString();
	String toDayMin = new SimpleDateFormat("mm").format(new Date()).toString();
%>
<style>
.num {
  mso-number-format:General;
}
.date1 {
/*    mso-number-format:"Short Date"; */
/*     mso-number-format:yyyy\/mm\/dd; */
    mso-number-format:\@;
}
.date2 {
/*   mso-number-format:"Short Date"; */
/*    mso-number-format:yyyy\/mm\/dd; */
   mso-number-format:\@;
}
.text {
   mso-number-format:\@;
}
<%--
	Styling Excel cells with mso-number-format
	mso-number-format:"0"							:		NO Decimals
	mso-number-format:"0\.000"						:		3 Decimals
	mso-number-format:"\#\,\#\#0\.000"				:		Comma with 3 dec
	mso-number-format:"mm\/dd\/yy"					:		Date7
	mso-number-format:"mmmm\ d\,\ yyyy"				:		Date9
	mso-number-format:"m\/d\/yy\ h\:mm\ AM\/PM"		:		D -T AMPM
	mso-number-format:"Short Date"					:		01/03/1998
	mso-number-format:"Medium Date"					:		01-mar-98
	mso-number-format:"d\-mmm\-yyyy"				:		01-mar-1998
	mso-number-format:"Short Time"					:		5:16
	mso-number-format:"Medium Time"					:		5:16 am
	mso-number-format:"Long Time"					:		5:16:21:00
	mso-number-format:"Percent"						:		Percent - two decimals
	mso-number-format:"0%"							:		Percent - no decimals
	mso-number-format:"0\.E+00"						:		Scientific Notation
	mso-number-format:"\@"							:		Text
	mso-number-format:"\#\ ???\/???"				:		Fractions - up to 3 digits (312/943)
	mso-number-format:"\0022￡\0022\#\,\#\#0\.00"	:		￡12.76
	mso-number-format:"\#\,\#\#0\.00_ \;\[Red\]\-\#\,\#\#0\.00\ "		:		2 decimals, negative numbers in red and signed	(1.56 -1.56)

	<fmt:formatNumber value="${result.prototypeViewCnt}" pattern="#,	<fmt:parseDate value="${result.cretYmdtm}" pattern="yyyyMMddHHmmss" var="tempDateCretYmdtm"/>
--%>
</style>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
        <tr align="center"><td colspan="6"><h3>학습근로자별 평가결과서 목록</h3></td></tr>
        <tr align="center">
          <td width="5%" class="tableTitle" style="background-color:yellow"><h4>번호</h4></td>
          <td width="15%" class="tableTitle" style="background-color:yellow"><h4>학번</h4></td>
          <td width="15%" class="tableTitle" style="background-color:yellow"><h4>성명</h4></td>
          <td width="25%" class="tableTitle" style="background-color:yellow"><h4>기업명</h4></td>
          <td width="32%" class="tableTitle" style="background-color:yellow"><h4>훈련과정명</h4></td>
          <td width="8%" class="tableTitle" style="background-color:yellow"><h4>학적상태</h4></td>
        </tr>
      </table>
      <table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
       <c:forEach var="result" items="${resultMemberStdList}" varStatus="status">
        <tr align="center" class="tableWhite2">
          <td align="center">${status.count}</td>
          <td align="center">${result.memId}</td>
          <td align="center">${result.memName}</td>
          <td align="center">${result.companyName}</td>
          <td align="left">${result.hrdTraningName}</td>
          <td align="center">${result.deleteName}<c:if test="${result.deleteYn eq 'Y'}"><br />(${result.deleteDt})</c:if></td>
        </tr>
        </c:forEach>
        <c:if test="${fn:length(resultMemberStdList) == 0}">
        <tr align="center" height="30" class="tableWhite2">
          <td colspan="6">조회된 정보가 없습니다.</td>
        </tr>
        </c:if>
      </table>
    </td>
  </tr>
</table>
