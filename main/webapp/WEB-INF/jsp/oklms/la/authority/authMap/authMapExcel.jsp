<%--
  Class Name : list.jsp 
  Description : 기본형
  Modification Information
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
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
	mso-number-format:"\0022£\0022\#\,\#\#0\.00"	:		£12.76
	mso-number-format:"\#\,\#\#0\.00_ \;\[Red\]\-\#\,\#\#0\.00\ "		:		2 decimals, negative numbers in red and signed	(1.56 -1.56)
	
	<fmt:formatNumber value="${result.prototypeViewCnt}" pattern="#,	<fmt:parseDate value="${result.cretYmdtm}" pattern="yyyyMMddHHmmss" var="tempDateCretYmdtm"/>
--%>
</style>
	<table id="itemList" border="1" cellpadding="5" cellspacing="1" align="center" class="scrollable">
		<thead>
		<tr bgcolor="#d4e4fa">
			<th>순번</th>
					<th>메뉴ID</th>
					<th>권한그룹아이디</th>
					<th>삭제여부</th>
					<th>등록자</th>
					<th>등록일</th>
					<th>수정자</th>
					<th>수정일</th>
					<th>생성 권한 여부</th>
					<th>상세 조회 권한 여부</th>
					<th>수정 권한 여부</th>
					<th>삭제 권한 여부</th>
					<th>출력 권한 여부</th>
					<th>다운로드 권한 여부</th>
					<th>기타 권한 여부</th>
					<th>목록 조회 권한 여부</th>
				</tr>
		</thead>
		<tbody>
		<c:forEach var="result" items="${dataList}" varStatus="status">
		<tr>
			<th nowrap="nowrap" scope="row">${status.count}</th>
					<td nowrap="nowrap">${result.menuId}</td>
					<td nowrap="nowrap">${result.authGroupId}</td>
					<td nowrap="nowrap">${result.deleteYn}</td>
					<td nowrap="nowrap">${result.insertUser}</td>
					<td nowrap="nowrap">${result.insertDate}</td>
					<td nowrap="nowrap">${result.updateUser}</td>
					<td nowrap="nowrap">${result.updateDate}</td>
					<td nowrap="nowrap">${result.createAuthYn}</td>
					<td nowrap="nowrap">${result.readAuthYn}</td>
					<td nowrap="nowrap">${result.updateAuthYn}</td>
					<td nowrap="nowrap">${result.deleteAuthYn}</td>
					<td nowrap="nowrap">${result.printAuthYn}</td>
					<td nowrap="nowrap">${result.downloadAuthYn}</td>
					<td nowrap="nowrap">${result.otherAuthYn}</td>
					<td nowrap="nowrap">${result.listAuthYn}</td>
				</tr>
		</c:forEach>
		</tbody>
	</table>
