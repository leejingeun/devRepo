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
 
 

		$(document).ready(function() {
			  
		});
		function fn_lec_menu_display(value){
			
			var reqUrl = "/mm/qestnr/listQestnr.do";
			var params = value.split("||");
			
			$("#subjectTraningType").val(params[0]);
			$("#yyyy").val(params[1]);
			$("#term").val(params[2]);
			$("#subjectCode").val(params[3]);
			$("#subClass").val(params[4]);
			$("#subjectName").val(params[5]);
			$("#subjectType").val(params[6]); 
			 
			$("#frmQestnr").attr("target", "_self");
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
			 
		}
		function fn_egov_select_noticeListAdd() {

			$("#pageIndex").val( "1" );
			$("#recordCountPerPage").val( "<c:out value='${qestnrVO.recordCountPerPage+10}'/>" );
			
			var reqUrl = "<c:url value='/mm/qestnr/listQestnr.do'/>";
			$("#frmQestnr").attr("target", "_self");
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
		}
		/* 설문 상세조회 페이지로 이동 */
		function fn_read( param1, param2, param3,param4){
			$("#qestnrId").val( param1 );
			$("#joinAt").val( param2 );
			$("#lessonStdCnt").val( param3 );
			$("#progrressStatus").val( param4 );
			
			var reqUrl = "/mm/qestnr/getQestnr.do";
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
		}
		/* 설문 상세조회 페이지로 이동 */
		function fn_cret(){
			var reqUrl = "/mm/qestnr/goInsertQestnr.do";
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
		}		
//-->
</script>

			<div id="container"> 
			
<form:form commandName="frmQestnr" name="frmQestnr" method="post"   > 

 
<input type="hidden" id="yyyy" name="yyyy" value="${qestnrVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${qestnrVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${qestnrVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${qestnrVO.subClass}" />

<input type="hidden" name="lecMenuMarkYn" id="lecMenuMarkYn" value="" />
<input type="hidden" name="subjectType" id="subjectType" value="" /> 

<input type="hidden" id="pageIndex" name="pageIndex" value="<c:out value='${qestnrVO.pageIndex}'/>"/>
<input type="hidden" id="recordCountPerPage"  name="recordCountPerPage"  value="<c:out value='${paginationInfo.recordCountPerPage}'/>"  />

<input type="hidden" name="memId" id="memId" value="" /> 
<input type="hidden" id="qestnrId" name="qestnrId" value="${qestnrVO.qestnrId}" />
<input type="hidden" id="qustnrQesitmId" name="qustnrQesitmId" value="${readQestnrItemVO.qustnrQesitmId}" />
<input type="hidden" id="joinAt" name="joinAt" value="${qestnrVO.joinAt}" />
<input type="hidden" id="etcAnswerAt" name="etcAnswerAt" value="${readQestnrInfoVO.etcAnswerAt}" />


<input type="hidden" id="progrressStatus" name="progrressStatus" />
<input type="hidden" id="lessonStdCnt" name="lessonStdCnt" />
			
				<ul id="search-area">				
					<li>
						<select id="subjectCodeValue" name="subjectCodeValue" onchange="javascript:fn_lec_menu_display(this.value);" style="display:block; width:100%; margin:3px 0;">
							<option value="">개설교과 선택</option>
							
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<option value="${menuProc.subjectTraningType}||${menuProc.year}||${menuProc.term}||${menuProc.subjectCode}||${menuProc.subClass}||${menuProc.subjectName}||${menuProc.subjectType}||${menuProc.onlineType}" <c:if test="${qestnrVO.subjectCode eq menuProc.subjectCode }">selected</c:if> ><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반</option>
						</c:forEach>

						</select>
						
						
											
						<select id="searchCondition" name="searchCondition" style="display:block; width:100%; margin:3px 0;">
							<option value="" selected="selected">::전체::</option>
							<option value="NAME" <c:if test="${qestnrVO.searchCondition eq 'NAME'}">selected="selected"</c:if>>작성자</option>
							<option value="MEMO" <c:if test="${qestnrVO.searchCondition eq 'MEMO'}">selected="selected"</c:if>>내용</option>
						</select>
						<select id="searchJoinAt" name="searchJoinAt"  style="display:block; width:100%; margin:3px 0;">
							<option value="" selected="selected">::전체::</option>
							<option value="NO" <c:if test="${qestnrVO.searchJoinAt eq 'NO'}">selected="selected"</c:if>>미참여</option>
							<option value="YES" <c:if test="${qestnrVO.searchJoinAt eq 'YES'}">selected="selected"</c:if>>참여</option>
						</select>
						<select id="searchStatus" name="searchStatus" style="display:block; width:100%; margin:3px 0;">
							<option value="" selected="selected">::전체::</option>
							<option value="BEFOR" <c:if test="${qestnrVO.searchStatus eq 'BEFOR'}">selected="selected"</c:if>>예정</option>
							<option value="START" <c:if test="${qestnrVO.searchStatus eq 'START'}">selected="selected"</c:if>>진행</option>
							<option value="END"   <c:if test="${qestnrVO.searchStatus eq 'END'}">selected="selected"</c:if>>종료</option>
						</select>
						<input type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어 입력" style="width:100%;"  value="${qestnrVO.searchKeyword }" />						
						
						<a href="#!" onclick="javascript:fn_lec_menu_display($('#subjectCodeValue').val());"  class="type-2">검색</a>
					</li>
				</ul><!-- E : search-area -->
</form:form>				

				<div id="contents-area">
<c:if test="${not empty qestnrVO.subjectCode }">
 
					<div class="bbs-total mt-020">총 <b><c:out value="${paginationInfo.totalRecordCount }"/></b> 건의 게시물이 있습니다.</div>
				<c:if test="${qestnrVO.sessionMemType == 'PRT'|| qestnrVO.sessionMemType == 'COT' || qestnrVO.sessionMemType == 'CDP'}">
						<div class="btn-area align-right"><a href="#!" onclick="fn_cret();return false;" class="orange">작성</a></div>
				</c:if>
					<c:if test="${empty resultList}">
					<dl class="bbs-list mt-005">
						<dt><spring:message code="common.nosarch.nodata.msg" /></dt>
					</dl>
					</c:if>
					
										
					<c:forEach var="result" items="${resultList}" varStatus="status">
					<dl class="bbs-list mt-005"> 
						<dt><span><a href="#fn_read" onclick="javascript:fn_read('${result.qestnrId}','${result.joinAt}','${result.lessonStdCnt}','${result.progrressStatus}'); return false" class="text"> ${result.qustnrSj}</a></span></dt>
						<dd>
							<span> 작성자 </span> :${result.memName} 
							<span> 등록일 </span> :${result.insertDate}
						</dd>
						<dd>
						
						<c:if test="${qestnrVO.sessionMemType ne 'PRT' and  qestnrVO.sessionMemType ne 'COT'  and qestnrVO.sessionMemType ne 'CDP'}">
							<span> 참여여부 </span> : 
							<c:choose>
								<c:when test="${result.joinAt eq 'Y'}">
									참여
								</c:when>
								<c:otherwise>
									미참여
								</c:otherwise>
							</c:choose>
						</c:if>
						
							<c:choose>
								<c:when test="${result.progrressStatus eq '예정'}">
									<span> 상태 </span> :  ${result.progrressStatus}
								</c:when>
								<c:when test="${result.progrressStatus eq '진행'}">
									<span> 상태 </span> : ${result.progrressStatus}
								</c:when>
								<c:otherwise>
									<span> 상태 </span> :${result.progrressStatus}
								</c:otherwise>
							</c:choose>

						</dd>
					</dl>
					</c:forEach>

				<c:if test="${paginationInfo.lastPageNoOnPageList >1}">
					<c:if test="${qestnrVO.pageIndex < paginationInfo.lastPageNoOnPageList}">
						<div class="bbs-more"><a href="#!" onclick="javascript:fn_egov_select_noticeListAdd();">더 보기</a></div>
					</c:if>
				</c:if>

</c:if> 
<c:if test="${empty qestnrVO.subjectCode }">
					<h4>
						<p><spring:message code="common.nosarch.nodata.msg" /></p>
					</h4>
</c:if>

				</div><!-- E : contents-area -->
			</div><!-- E : container -->