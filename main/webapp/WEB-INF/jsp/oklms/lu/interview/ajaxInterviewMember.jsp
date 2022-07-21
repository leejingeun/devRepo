<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

					<h1>학습근로자 선택${totalCnt}</h1>
					<table class="type-2">
						<colgroup>
							<col width="50px" />
							<col width="110px" />
							<col width="*" />
						</colgroup>
						<thead>
							<tr>
								<th>선택</th>
								<th>학번</th>
								<th>이름</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${result}" varStatus="status">
								<tr>
									<td><input type="checkbox" name="interviewMemSeq" onclick="javascript:checkboxClick(this.checked,'${result.memSeq },${result.memName }');" value="${result.memSeq },${result.memName }" class="choice" <c:if test="${fn:indexOf(interviewVO.interviewMemberSeqs, result.memSeq) >0}">checked</c:if> /></td>
									<td>${result.memId }</td>
									<td>${result.memName }</td>
								</tr>							
							</c:forEach>
							<c:if test="${empty result}">
								<tr>
									<td colspan="3">조회된 학습근로자가 없습니다.</td>								
								</tr>						
							</c:if>	 
							 							
						</tbody>
					</table><!-- E : 학습근로자 선택 -->
					<!-- S : page-num (페이징 영역) -->
					<div class="page-num mt-015">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_memberLList" />
					</div>
					<!-- E : page-num (페이징 영역) -->
					
					<div class="btn-area align-center mt-010">
						<a href="javascript:hideLearningpop();" class="orange close">확인</a>
					</div><!-- E : btn-area -->
					
