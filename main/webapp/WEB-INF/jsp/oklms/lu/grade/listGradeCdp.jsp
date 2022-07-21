<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2017. 01. 09 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>
<c:set var="targetUrl" value="/lu/grade/"/>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
		loadPage();
		
		$("#checkbox").click(function(){
	        if($("#checkbox").prop("checked")){
	            $("input[name=checkIds]").each(function(){
	            	if($(this).prop("disabled") == false){	// disabled 이 아닌것들 만 체크
	            		$(this).prop("checked",true);
	            	}
	    		});
	        }else{
	            $("input[name=checkIds]").prop("checked",false);
	        }
	    });
		
	});

	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}


	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {

	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			//fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listGradeCdp.do";
		$("#frmGrade").attr("action", reqUrl);
		$("#frmGrade").submit();
	}
	
	/* 성적 전송 */
	function fn_submit(){
		
		var arrayVal = new Array();
		$("input:checkbox[name=checkIds]:checked").each(function(){
			arrayVal.push($(this).val());
		});
		
		if(arrayVal == ""){
			alert("선택 된 기업이 없습니다.");
			return;
		}
		
		$("#submitIds").val(arrayVal);
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "insertGradeCdpSend.do";
		$("#frmGrade").attr("action", reqUrl);
		$("#frmGrade").submit();
	}
	
	/* 성적조회 팝업 */
	function fn_popup(yyyy, semstrCd, groupMemIds, companyName){
		popOpenWindow("", "popSearch", 1200, 650);
		var reqUrl = "/lu/grade/popupGradeCdp.do";
		$("#yy").val(yyyy);
		$("#semstrCd").val(semstrCd);
		$("#groupMemIds").val(groupMemIds);
		$("#companyName").val(companyName);
		$("#frmGradePop").attr("action", reqUrl);
		$("#frmGradePop").attr("target", "popSearch");
		$("#frmGradePop").submit();
	}
	
</script>

<!-- 회원정보 팝업용 시작 -->
<form id="frmGradePop" name="frmGradePop" method="post">
	<input type="hidden" name="yy" id="yy"/>
	<input type="hidden" name="semstrCd" id="semstrCd" />
	<input type="hidden" name="groupMemIds" id="groupMemIds" />
	<input type="hidden" name="companyName" id="companyName" />
	<input type="hidden" name="termNm" id="termNm" value="${gradeVO.termNm}" />
</form>
<!-- 회원정보 팝업용 끝 -->
			
<div id="content-area">
			<h2>성적조회</h2>
			<!-- E : search-list-1 (검색조건 영역) -->
<form id="frmGrade" name="frmGrade" action="<c:url value='/lu/grade/listGradeCdp.do'/>" method="post">
	<input type="hidden" name="submitIds" id="submitIds" />				
			
			<div class="group-area mt-020">
							<div class="search-box-1">
								<select name="yyyy" > 
										<c:forEach var="i" begin="0" end="2" step="1">
									      <option value="${nowYear-i}" <c:if test="${gradeVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
									    </c:forEach>								
								</select> 
								<select name="term">
										<option value="1" <c:if test="${gradeVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
										<option value="2" <c:if test="${gradeVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
										<option value="3" <c:if test="${gradeVO.term eq '3' }" > selected="selected"  </c:if>>여름학기</option>
										<option value="4" <c:if test="${gradeVO.term eq '4' }" > selected="selected"  </c:if>>겨울학기</option>
									</select>
								<!-- <select id="" onchange="">
									<option value="">개설 교과명</option>
								</select> -->
								<a href="#!" onclick="javascript:fn_search();">검색</a>
							</div><!-- E : search-box-1 -->



							<table class="type-2 mt-020">
								<colgroup>
									<col style="width:33%" />
									<col style="width:33%" />
									<col style="width:*" />
								</colgroup>
								<thead>
									<tr>
										<th>학년도</th>
										<th>학기</th>
										<th>학과</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>${gradeVO.yyyy}</td>
										<td>${gradeVO.termNm}</td>
										<td>${gradeVO.sessionDeptNm}</td>
									</tr>
								</tbody>
							</table>

							<table class="type-2 mt-010">
								<colgroup>
									<col style="width:10%" />
									<col style="width:*" />
									<col style="width:20%" />
									<col style="width:20%" />
									<col style="width:20%" />
								</colgroup>
								<thead>
									<tr>
										<th>선택<br/><input type="checkbox" name="checkbox" id="checkbox"></th>
										<th>참여기업명</th>
										<th>인원</th>
										<th>발송여부</th>
										<th>조회</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="result" items="${statusList}" varStatus="status">
									<tr>
										<td><input type="checkbox" name="checkIds" id="checkIds${status.count}"  value="${result.companyId }|${result.yyyy}|${result.term}|${result.deptNo}" <c:if test="${result.sendYn eq 'Y' or result.gradeCnt eq '0'}">disabled="disabled"</c:if>  /></td>
										<td>${result.companyName}</td>
										<td>${result.stuCnt}</td>
										<td>${result.sendYn eq 'Y' ? '발송' : '미발송'}</td>
										<td>
										<c:if test="${result.gradeCnt ne '0'}">
											<a class="btn-full-blue" href="#fn_popup" onclick="fn_popup('${result.yyyy}','${result.semstrCd}','${result.groupMemIds}','${result.companyName}')">조회</a>
										</c:if>
										</td>
									</tr>
								</c:forEach>	
								<c:if test="${empty statusList}" >
									<tr>
										<td colspan="5"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>
								</tbody>
							</table>
							<div class="btn-area align-right mt-010">
								<a href="#" onclick="fn_submit();" class="orange">전송</a>
							</div><!-- E : btn-area -->
						</div>

						<table class="type-2 mt-040">
							<colgroup>
								<col style="width:50px" />
								<col style="width:90px" />
								<col style="width:200px" />
								<col style="width:200px" />
								<col style="width:160px" />
								<col style="width:*" />
								<col style="width:90px" />
							</colgroup>
							<tr>
								<th>번호</th>
								<th>전송일</th>
								<th>기업명</th>
								<th>사업자번호</th>
								<th>수신인</th>
								<th>전송사유</th>
								<th>IP</th>
							</tr>
							<c:forEach var="result" items="${sendList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${result.insertDate}</td>
									<td>${result.companyName}</td>
									<td>${result.companyNo}</td>
									<td>${empty result.ccmName ? '담당자 없음' : result.ccmName}</td>
									<td class="left">규정에 의한 정기 전송</td>
									<td>${result.ip}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty sendList}" >
								<tr>
									<td colspan="7"><spring:message code="common.nodata.msg" /></td>
								</tr>
							</c:if>
						</table>
</form>	
					</div><!-- E : content-area -->

	
	