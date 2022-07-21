<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<!-- 세로배열 -->
<c:if test="${resultType == 'vertical'}">
<table summary="배너이미지 세로배열">
    <caption>배너이미지 배열</caption>
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
      <tr>
      <td>${fileVO.bannerType}</td>
       <td>
            <a href="<c:out value="${fileVO.linkUrl}"/>" target="_blank"  title="새 창으로 이동" ><img alt="배너 이미지" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.bannerImageFile}"/>'></a>
       </td>
      </tr>
    </c:forEach>
</table>
</c:if>

<!-- 가로배열 -->
<c:if test="${resultType == 'horizontal'}">
<table width="700" height="90" style="table-layout:fixed;" summary="배너이미지 가로배열">
  <caption>배너이미지 배열</caption>
  <tr>
    <c:forEach var="fileVO" items="${fileList}" varStatus="status">
       <td>${fileVO.bannerType}</td>
       <td>
            <a href="<c:out value="${fileVO.linkUrl}"/>" target="_blank"  title="새 창으로 이동" ><img alt="배너 이미지" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${fileVO.bannerImageFile}"/>'></a>
       </td>
    </c:forEach>
  </tr>
</table>
</c:if>

<!-- S : page-btn -->
<div class="page-btn">
	<a href="/la/banner/listBanner.do?pageIndex=<c:out value='${bannerVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${bannerVO.searchKeyword}"/>&amp;searchCondition=1">목록</a>
</div>
