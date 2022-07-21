<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta http-equiv="Content-Type"
	content="application/xhtml+xml; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<%@include file="/includeCss.jsp"%>
<%@include file="/includeJs.jsp"%>
<!-- blankLayout -->
<script type="text/javascript">

	//Controller에서  전달된 메시지를 출력한다. ( gridUtil.setRetMsg 값 )
	$(document).ready(function(){
		if(""!="${retMsg}"){
			alert("${retMsg}");
		}
	});
</script> 
 
<!-- ############### blankLayout ############### -->
<tiles:insertAttribute name="body" />
<!-- ############### // blankLayout ############### -->
 
</html>
