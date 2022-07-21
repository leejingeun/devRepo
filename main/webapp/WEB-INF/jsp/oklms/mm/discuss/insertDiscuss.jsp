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
	var reqUrl = "/mm/discuss/insertDiscuss.do";
	$("#frmDiscuss").attr("target", "_self");
	$("#frmDiscuss").attr("action", reqUrl);
	$("#frmDiscuss").submit();
	 
}

function fn_list()
{
	var reqUrl = "/mm/discuss/getDiscuss.do";
	$("#frmDiscuss").attr("target", "_self");
	$("#frmDiscuss").attr("action", reqUrl);
	$("#frmDiscuss").submit();
	 
}
//-->
</script>
			<div id="container">
				<div id="contents-area">
<form id="frmDiscuss" name="frmDiscuss" method="post" enctype="multipart/form-data" >
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="discussOpinionId" name="discussOpinionId" value="${discussVO.discussOpinionId}" />
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
 
					<table class="type-1 mt-020">
						<colgroup>
							<col style="width:130px" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td><input type="text" id="title" name="title"  style="width:100%" placeholder="토론의견"  /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea id="content" name="content"  style="width:100%;height:100px" placeholder="토론의견 내용"></textarea></td>
							</tr>
							 
						</tbody>
					</table><!-- E : 토론 -->
					
					<div class="btn-area align-center mt-010"><a href="#!" onclick="javascript:fn_list();" class="gray-2">이전</a><a href="#!" onclick="javascript:fn_insert();" class="orange">저장</a></div>
					
</form>			 
				</div><!-- E : contents-area -->
			</div><!-- E : container -->