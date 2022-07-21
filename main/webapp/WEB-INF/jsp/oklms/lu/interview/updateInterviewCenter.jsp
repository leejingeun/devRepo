<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
 <c:set var="interviewNoteDateMmTemp" value=""/> 

						<h2>월별 면담일지 수정</h2>
						<c:forEach var="result" items="${resultlist}" varStatus="status">
						<iframe name="frameName" id="frameName"  width="100%" height="800px" src="/lu/interview/goUpdateInterviewiframe.do?companyId=${result.companyId }&interviewMemSeq=${result.interviewMemSeq }&interviewNoteId=${result.interviewNoteId }" frameborder="0" allowfullscreen></iframe>
						</c:forEach>
						
						<div class="btn-area align-right mt-010">
							<a href="#!" onclick="javascript:fn_cancel();" class="gray-1">취소</a>
						</div><!-- E : btn-area -->
<form:form commandName="frmInterview" name="frmInterview" method="post">
<input type="hidden" name="companyId"  value="${result.companyId }" />
<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
<input type="hidden" name="interviewNoteDateMmParam" id="interviewNoteDateMm" value="${interviewNoteDateMmTemp }"  />
<input type="hidden" name="yyyy"  value="${interviewVO.yyyy }" />
</form:form>
<script type="text/javascript">
<!--

$(document).ready(function() {

}); 

function fn_cancel(){

	var reqUrl =  "/lu/interview/listInterviewCenter.do";
	$("#frmInterview").attr("target", "_self");
	$("#frmInterview").attr("action", reqUrl);
	$("#frmInterview").submit();		

}
//--> 
</script>	
 