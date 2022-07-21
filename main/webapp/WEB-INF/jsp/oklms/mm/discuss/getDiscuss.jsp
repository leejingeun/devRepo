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
function fn_insert()
{
	var reqUrl = "/mm/discuss/goInsertDiscuss.do";
	$("#frmDiscuss").attr("target", "_self");
	$("#frmDiscuss").attr("action", reqUrl);
	$("#frmDiscuss").submit();
	 
}
function fn_list()
{
	var reqUrl = "/mm/discuss/listDiscuss.do";
	$("#frmDiscuss").attr("target", "_self");
	$("#frmDiscuss").attr("action", reqUrl);
	$("#frmDiscuss").submit();
	 
}

/* 토론의견 댓글신규 저장 */
function fn_comment_save( value ){
	
	if( confirm("토론의견에 입력한 댓글을 저장하겠습니까?") ) {
		
		if($("#"+value+"_content").val() == ""){
			alert("댓글에 내용을 입력 주세요.");
			$("#"+value+"_content").focus();
			return false;
		}
		$("#content").val($("#"+value+"_content").val());
		$("#discussOpinionId").val(value);
		var reqUrl = "/mm/discuss/insertDiscussComment.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();	
	}
	
}



//-->
</script>
			<div id="container">
				<div id="contents-area">
<form id="frmDiscuss" name="frmDiscuss" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="discussOpinionId" name="discussOpinionId" value="${discussVO.discussOpinionId}" />
<input type="hidden" name="content" id="content" />
					<h4>
						<p>${currProcReadVO.subjectName}</p>
						<span>${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</span>
					</h4>

					<table class="type-1">
						<colgroup>
							<col width="22%" />
							<col width="*" />
							<col width="22%" />
							<col width="23%" />
						</colgroup>
						<tbody>
							<tr>
								<th>토론제목</th>
								<td colspan="3">${discussReadVO.title}</td>
							</tr>
							<tr>
								<th>기간</th>
								<td colspan="3">${discussReadVO.startDate} ~ ${discussReadVO.endDate}</td>
							</tr>
							<tr>
								<th>배점</th>
								<td colspan="3">${discussReadVO.evalScore}<c:if test="${discussReadVO.evalYnName != '미평가'}">점</c:if></td>
							</tr>							
							<tr>
								<th>내용</th>
								<td colspan="3">${discussReadVO.content}</td>
							</tr>
						</tbody>
					</table>
</form>

					<table class="type-2 mt-020">
						<colgroup>
							<col width="10%" />
							<col width="*" />
							<col width="15%" />
							<col width="28%" />
							<col width="10%" />
						</colgroup>
 

				<c:forEach var="result" items="${resultDiscussOpinionList}" varStatus="status">
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>추천</th>
							</tr>
							<tr>
								<td rowspan="3">${status.count}</td>
								<td class="left">${result.title}</td>
								<td>${result.memName}</td>
								<td>${result.insertDate}</td>
								<td>${result.goodCnt}</td>
							</tr>
							<tr>
								<td colspan="4" class="border-left left">${result.content}</td>
							</tr>
							<tr>
								<td colspan="4" class="border-left left">
									<dl class="reply-list">
										<dt>댓글 <b>${fn:length(result.listDiscussOpinionComment)}</b>개</dt>
										<c:forEach var="listDiscussOpinionCommentrs" items="${result.listDiscussOpinionComment}" varStatus="status1">
										<dd><span>[${listDiscussOpinionCommentrs.memName}]</span> ${listDiscussOpinionCommentrs.content}</dd>
										</c:forEach>
										<c:if test="${ loginAuthGroupId eq '2016AUTH0000002' }">
											<dd><span>[${discussVO.sessionMemName}]</span>
												<input type="text" name="${result.discussOpinionId}_content" id="${result.discussOpinionId}_content" /><div class="btn-area  align-right"><a href="#!" style="color:#FFF;" onclick="javascript:fn_comment_save('${result.discussOpinionId}');" class="orange">저장</a></div>
											</dd>											
										</c:if>
									</dl>
								</td>
							</tr>				
				
				</c:forEach>						
				<c:if test="${empty resultDiscussOpinionList }">
							<tr>
								<td colspan="5"><spring:message code="common.nosarch.nodata.msg" /></td>
							</tr>
				</c:if>
						
							
						</tbody>
					</table>
				<!--  학습근로자만 의견작성 -->	
				<c:if test="${ loginAuthGroupId eq '2016AUTH0000002' }">
					<div class="btn-area align-center mt-010"><a href="#!" onclick="javascript:fn_list();" class="gray-2">목록</a><a href="#!" onclick="javascript:fn_insert();" class="orange">의견 작성</a></div>
				</c:if>
				
				</div><!-- E : contents-area -->
			</div><!-- E : container -->