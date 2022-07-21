<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

					<h1>기업체 검색</h1>
					<div class="search-box-1 mb-020">
						<input type="text" name="searchCompanyName"  id="searchCompanyName" value="${searchCompanyName}" style="300px" />
						<a href="#!" onclick="javascript:searchCompany(1);">조회</a>
					</div><!-- E : search-box-1 -->

					<table class="type-2"  id="interviewCompanyList">
						
					
						<colgroup>
							<col width="40px" />
							<col width="170px" />
							<col width="*" />
							<col width="180px" />
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>기업명</th>
								<th>훈련과정명</th>
							</tr>
						</thead>
						<tbody>
	
							<c:forEach var="result" items="${result}" varStatus="status">
								<tr>
									<td><input type="radio" name="interviewCompanySeq" onclick="javascript:checkboxClick('${result.companyId }','${result.traningProcessId }');" value="${result.companyId },${result.traningProcessId }" class="choice" /></td>
									<td>${result.companyName }</td>
									<td>${result.hrdTraningName }</td>
								</tr>							
							</c:forEach>
							<c:if test="${empty result}">
								<tr>
									<td colspan="3">조회된 기업이 없습니다.</td>								
								</tr>						
							</c:if>	 
							
						</tbody>

					</table>
					<c:if test="${!empty result}">
					<!-- S : page-num (페이징 영역) -->
					<div class="page-num mt-015">
						<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="searchCompany" />
					</div>
					<!-- E : page-num (페이징 영역) -->
					</c:if>
					<div class="btn-area align-center mt-010">
						<a href="javascript:hideCompanypop();" class="orange close">확인</a>
					</div>	 