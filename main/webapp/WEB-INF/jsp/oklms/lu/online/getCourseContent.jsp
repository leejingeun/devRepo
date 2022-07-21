<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

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
<c:set var="targetUrl" value="/lu/online/"/>
<script type="text/javascript" src="${contextRootJS }/js/jquery/plugins/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery.maskedinput.js"></script>
<%-- <script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-3.1.1.min.js"></script> 
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery.poshytip.js"></script> 
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-admin.js"></script> 
<script type="text/javascript" src="${contextRootJS }/js/jquery/mvisual.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-common.js"></script>  --%>

<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	
	$(document).ready(function() {
		loadPage();
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
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		$("#page").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "popupOnlineTraning.do";
		$("#frmPop").attr("action", reqUrl);
		$("#frmPop").submit();
	}
	
	/* 상세조회 페이지로 이동 */
	function fn_read( param1 ){
		
		//컨테츠 미리보기 테스트때문에 주석처리함.
		$("#id").val( param1 );
		//$("#id").val( '2' );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "popupOnlineTraning.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_showLearningpop(){
		$(".learning-area,.popup-bg").addClass("open"); 
		window.location.hash = "#open";
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_hideLearningpop(){
		$(".learning-area,.popup-bg").removeClass("open");
	}
	function fn_get_course_content(id){
		$("#id").val( id );
		var reqUrl = CONTEXT_ROOT + "/lu/online/getOnlineTraning.json";
	 	var param = $("#frmPop").serializeArray();
		com.ajax(reqUrl, param, function(obj, resultData, textStatus, jqXHR){	
			alert(jqXHR.status);
		if (200 == jqXHR.status ) {
			var itemList = resultData.rows;
			alert(itemList);
			
		}
		}, {
			async : true,
			type : "POST",
			errorCallback : null
		});
	}
	
	
</script>

<!-- 회원정보 팝업용 시작 -->
<form id="frmPop" name="frmPop" method="post">
	<input type="hidden" name="id" id="id"/>
</form>
<!-- 회원정보 팝업용 끝 -->
			
<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>콘텐츠 등록</h1>

			<dl id="tab-type">
				<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">기존 콘텐츠</a></dt>
				<dd id="tab-content-11" style="display:block;">
					<div class="search-box-1 mt-020 mb-010">
						<select id="" name="year" onchange="">
							<option value="">산하기관</option>
						</select>
						<select id="year" name="year" onchange="">
							<option value="">개발년도</option>
						</select>
						<input type="text" name="" style="width:65px" /> <img src="../../../image/std/inc/icon_calendar.png"> ~<input type="text" name="" style="width:65px" /> <img src="../../../image/std/inc/icon_calendar.png">
						<input type="text" name="" value="콘텐츠명" style="width:150px"/>
						<a href="#!">검색</a>
					</div><!-- E : search-box-1 -->


					<table class="type-2">
						<colgroup>
							<col style="width:35px" />
							<col style="width:90px" />
							<col style="width:*" />
							<col style="width:140px" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th>산하기관</th>
								<th>버전명</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr>
							    <td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td>${result.institution_name}</td>
								<td id="course_tree_${result.id}"><a href="#" onclick="fn_get_course_content(${result.id});" class="text">${result.subtitle}</a></td>
								<td>${fn:substring(result.registered_date,0,10)}</td>
							<!-- 	<td><a href="javascript:showLearningpop();" class="btn-search-gray"></a></td> -->
							</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="9"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						</tbody>
					</table>

					<form name="frmTraning" id="frmTraning" method="post">
						<input type="hidden" id="lessonId" name="lessonId" />
						<input type="hidden" id="lessonItemId" name="lessonItemId" />
						<input type="hidden" id="lessonSubItemId" name="lessonSubItemId" />
					</form>
					<div id="content-area">
						<a href="javascript:showLearningpop();" style="position:absolute; display:inline-block; overflow:hidden; width:120px; left:50%; font:15px nbgM; color:#FFF; text-align:center; background:#F76000; border-radius:3px; padding:15px 0; margin:300px 0 0 -60px;">학습하기</a>
					</div><!-- E : content-area -->

					<ul class="page-num-btn mt-015">
						<li class="page-num-area" id="page-num-area">
						</li>
						<li class="page-btn-area">
							<a href="#!" class="orange">목록에 추가</a><a href="#!" class="gray-3">닫기</a>
						</li>
					</ul><!-- E : page-num-btn -->
				</dd>
				
				

			</dl>


		</div><!-- E : wrapper -->	
	
	
					

	