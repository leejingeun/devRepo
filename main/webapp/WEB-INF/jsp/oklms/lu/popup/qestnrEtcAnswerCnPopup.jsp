<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	$(document).ready(function() {

	});

	function pop_closeWin() {
		self.close();
	}

</script>

<form id="frmQestnr" name="frmQestnr" method="post">

<!-- 팝업 사이즈 : 가로 최소 500px 이상 설정 -->
<div id="pop-wrapper" class="min-w500">

	<h1>설문 기타의견 보기</h1>
	<div class="poll-etc">
		<ul>
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<li><span>[ ${result.memId}-${result.memName} / ${result.insertDate} ]</span>${result.etcAnswerCn}</li>
			</c:forEach>	
			<c:if test="${fn:length(resultList) == 0}">
				<li>기타의견이 없습니다.</li>
			</c:if>
			
		</ul>
	</div>


	<div class="btn-area align-right mt-010">
		<a href="#!" onclick="javascript:pop_closeWin(); return false" class="gray-3">닫기</a>
	</div>


</div><!-- E : wrapper -->

</form>

