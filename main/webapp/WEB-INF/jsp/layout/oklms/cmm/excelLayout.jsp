<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
	<%
		String excelFileName = (String)request.getAttribute("ExcelName");
 		excelFileName = new String(excelFileName.getBytes("EUC-KR"), "8859_1");
		String toDayDate = new SimpleDateFormat("yyyy.MM.dd").format(new Date()).toString();
		String hour = new SimpleDateFormat("HH").format(new Date()).toString();
		String min = new SimpleDateFormat("mm").format(new Date()).toString();
		String strTmp1 = "_";
		
		String toDay = toDayDate+strTmp1+hour+strTmp1+min;
		response.setHeader("Content-Type","application/vnd.ms-xls");
		response.setHeader("Content-Disposition", "attachment; filename="+excelFileName+"_"+toDay+".xls");
	%>
</head>
<body>
<tiles:insertAttribute name="body" />
</body>
</html>