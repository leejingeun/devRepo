<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- ############### LA popup Title ############### -->
			<c:choose>
				<c:when test="${null != popupTitle }">${popupTitle }</c:when>
				<c:otherwise></c:otherwise>
			</c:choose></h3>
			<!-- <a class="btn_popclose" href="javascript://" onclick="window.close();">닫기</a> -->
<!-- ############### // LA popup Title ############### -->