<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근C    2017. 01. 07 오후 1:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
      <table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
        <tr align="center"><td colspan="12"><h3>회원목록</h3></td></tr>
        <tr align="center">
          <td width="10%" class="tableTitle"><h4>아이디</h4></td>
          <td width="10%" class="tableTitle"><h4>성명</h4></td>
          <td width="10%" class="tableTitle"><h4>생년월일</h4></td>
          <td width="10%" class="tableTitle"><h4>회원유형코드</h4></td>
          <td width="10%" class="tableTitle"><h4>회원유형</h4></td>
          <td width="10%" class="tableTitle"><h4>우편번호</h4></td>
          <td width="10%" class="tableTitle"><h4>주소</h4></td>
          <td width="10%" class="tableTitle"><h4>휴대폰</h4></td>
          <td width="10%" class="tableTitle"><h4>E-mail</h4></td>
          <td width="10%" class="tableTitle"><h4>가입일</h4></td>
          <td width="10%" class="tableTitle"><h4>소속코드</h4></td>
          <td width="10%" class="tableTitle"><h4>소속명</h4></td>
        </tr>
      </table>
      <table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
       <c:forEach var="result" items="${resultList}" varStatus="status">
        <tr align="center" class="tableWhite2">
          <td width="10%">${result.memId}</td>
          <td width="10%">${result.memName}</td>
          <td width="10%">${result.memBirth}</td>
          <td width="10%">${result.authGroupId}</td>
          <td width="10%">${result.authGroupName}</td>
          <td width="10%">${result.zipCode}</td>
          <td width="10%">${result.address}&nbsp;${result.addressDtl}</td>
          <c:if test="${result.hpNo1 ne ''}">
          	<td width="10%">${result.hpNo1}-${result.hpNo2}-${result.hpNo3}</td>
          </c:if>
          <c:if test="${result.hpNo1 eq ''}">
          	<td width="10%"></td>
          </c:if>
          <td width="10%">${result.email}</td>
          <td width="10%">${result.insertDate}</td>
          <td width="10%">${result.companyId}</td>
          <td width="10%">${result.compNm}</td>
        </tr>
        </c:forEach>
        <c:if test="${fn:length(resultList) == 0}">
        <tr align="center" height="30" class="tableWhite2">
          <td colspan="10">조회된 정보가 없습니다.</td>
        </tr>
        </c:if>
      </table>
    </td>
  </tr>
</table>
