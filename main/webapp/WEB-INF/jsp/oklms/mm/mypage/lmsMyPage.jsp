<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">
<!--
function fn_egov_inqire_notice(i, nttId, bbsId) {
	 if(bbsId == "") return false;
	document.frm.nttId.value = nttId;
	document.frm.bbsId.value = bbsId;
	document.frm.action = "<c:url value='/mm/cop/bbs/"+bbsId+"/selectBoardArticle.do'/>";
	document.frm.submit();
}

//-->
</script>
<form name="frm"   method="post">
<input type="hidden" name="bbsId"  />
<input type="hidden" name="nttId"   />  
</form>
 			<div id="container">
				<div id="contents-area"> 
					<h3>TODAY</h3>
					<table class="today-type" style="table-layout:fixed;">
						<thead>
							<tr>
								<th>학습활동서작성</th>
								<th>질문과답변</th>
								<th>과제제출</th>
								<th>팀프로젝트과제제출</th>
								<th>토론개설</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><span>${readTodayCnt[0].myPageTodayCnt1}</span>건</td>
								<td><span>${readTodayCnt[0].myPageTodayCnt2}</span>건</td>
								<td><span>${readTodayCnt[0].myPageTodayCnt3}</span>건</td>
								<td><span>${readTodayCnt[0].myPageTodayCnt4}</span>건</td>
								<td><span>${readTodayCnt[0].myPageTodayCnt5}</span>건</td>
							</tr>
						</tbody>					
					</table>
 
					<c:if test="${not empty noticeResultList}">
					<h3>공지사항</h3>
					<ul class="board-list">
 
					<c:forEach var="noticeResult" items="${noticeResultList}" varStatus="status">
						<li> 
						<a href="#!" onclick="fn_egov_inqire_notice('${status.count}','${noticeResult.nttId}','${noticeResult.bbsId}');" >
							<span>[ ${noticeResult.subjectName} ]</span>
							<c:if test="${'Y' == noticeResult.topNoticeYn}"><B></c:if>${noticeResult.nttSj}<c:if test="${'Y' == noticeResult.topNoticeYn}"></B></c:if>
						</a> 
 
						</li>	
					</c:forEach>
					<li class="more"><a href="/mm/cop/bbs/BBSMSTR_000000000005/selectBoardList.do">공지사항 더 보기</a></li>
					 
					</ul>
					</c:if> 

					<c:if test="${not empty qaResultList}"> 
					<h3>Q&amp;A</h3>
					<ul class="board-list">
					
					<c:forEach var="qaResult" items="${qaResultList}" varStatus="status">
						<li>
							<c:choose>
									<c:when test="${qaResult.isExpired == 'T' && qaResult.isExpired !='Y'}">
										<c:if test="${'Y' == qaResult.topNoticeYn}">
											<span>[(<c:out value="${qaResult.subjectName}"/>)<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <B><c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]</B>
										</c:if>
										<c:if test="${'N' == qaResult.topNoticeYn}">
											<span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]
										</c:if>
									</c:when>
									<c:when test="${qaResult.isExpired !='T' && qaResult.isExpired =='Y'}">
										<c:if test="${'Y' == qaResult.topNoticeYn}">
											<span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <B><c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]</B>
										</c:if>
										<c:if test="${'N' == qaResult.topNoticeYn}">
											<span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]
										</c:if>
									</c:when>
									<c:when test="${qaResult.useAt == 'N'}">
								  		<c:if test="${qaResult.replyLc!=0}">
								  		<c:forEach begin="0" end="${qaResult.replyLc}" step="1">
								  			&nbsp;
								  		</c:forEach>
								  		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
								  		</c:if>
										<c:if test="${'Y' == qaResult.topNoticeYn}">
											<span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <B><c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]</B>
										</c:if>
										<c:if test="${'N' == qaResult.topNoticeYn}">
											 [<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]<c:out value="${qaResult.nttSj}" /> [<c:out value="${qaResult.commnetCount}" />]
										</c:if>
									</c:when>
									<c:otherwise>
								   	<c:if test="${qaResult.replyLc!=0}">
								   		<c:forEach begin="0" end="${qaResult.replyLc}" step="1">
								   			&nbsp;
								   		</c:forEach>
								   		<img src="<c:url value='/images/egovframework/com/cmm/icon/reply_arrow.gif'/>" alt="reply arrow">
								   	</c:if>
							  		<c:if test="${'Y' == qaResult.topNoticeYn}">
										<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${qaResult.nttId}','${qaResult.bbsId}');"><span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <B><c:out value="${qaResult.nttSj}"/> [<c:out value="${qaResult.commnetCount}" />]</B></a>
									</c:if>
									<c:if test="${'N' == qaResult.topNoticeYn}">
										<a href="#"  class="text" onclick="fn_egov_inqire_notice('${status.count}','${qaResult.nttId}','${qaResult.bbsId}');"><span>[<c:out value="${qaResult.subjectName}"/>(<c:out value="${qaResult.subClass}"/>)분반]</span> <c:out value="${qaResult.nttSj}"/> [<c:out value="${qaResult.commnetCount}" />]</a>
									</c:if>
									</c:otherwise>
							</c:choose>
						</li>	
					</c:forEach>
					<li class="more"><a href="/mm/cop/bbs/BBSMSTR_000000000007/selectBoardList.do">Q&amp;A 더 보기</a></li>			
					</ul>
					</c:if> 


					<h3>강의일정</h3>
					<table class="type-2">
						<colgroup>
							<col width="30%" />
							<col width="*" />
						</colgroup>
						<tbody>
							<tr>
								<th>교과목명</th>
								<td class="left"><a href="#!">시퀀스제어 및 실습</a></td>
							</tr>
							<tr>
								<th>시간</th>
								<td class="left">09:00 ~ 12:00</td>
							</tr>
							<tr>
								<th>분반 / 주차</th>
								<td class="left">01 / 04</td>
							</tr>
							<tr>
								<th>온라인 강의</th>
								<td class="left"><a href="#!" class="more">플립러닝</a></td>
							</tr>
							<tr>
								<th>능력단위요소</th>
								<td class="left">능력단위요소_D.1</td>
							</tr>
							<tr>
								<th>장소</th>
								<td class="left">공학관 - C410</td>
							</tr>
						</tbody>
					</table>


				</div><!-- E : contents-area -->
			</div>