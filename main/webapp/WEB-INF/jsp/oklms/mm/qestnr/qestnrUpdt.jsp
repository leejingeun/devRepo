<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${contextRootJS }/js/oklms/common.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/plugins/blockUI/jquery.blockUI.js"></script>
<script type="text/javascript" src="${contextRootJS }/js/jquery/jquery.maskedinput.js"></script>
 <script type="text/javascript">
<!--
		 
				 
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

			com.datepickerDateFormat('qustnrStartDate');
		    com.datepickerDateFormat('qustnrEndDate');
		    $("#qustnrSj").focus();
		}
		
		/* 설문 상세조회 페이지로 이동 */
		function fn_list(){
			
			var reqUrl = "/mm/qestnr/listQestnr.do";
			
			$("#frmQestnr").attr("target", "_self");			
			$("#frmQestnr").attr("action", reqUrl);
			$("#frmQestnr").submit();
			
		}
		/*====================================================================
		사용자 정의 함수
		====================================================================*/

		/* .저장 */
		function fn_formSave(){
			if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
				var reqUrl = "/mm/qestnr/updateQestnr.do";

				$("#frmQestnr").attr("method", "post" );
				$("#frmQestnr").attr("action", reqUrl);
				$("#frmQestnr").submit();
			}
		}

		//저장버튼 클릭시 입력form Validate 체크
		function fn_formValidate(){
			var startDate = "";
			var endDate = "";

			if($("#qustnrSj").val() == ""){
				alert("설문 제목을 입력 주세요.");
				$("#qustnrSj").focus();
				return false;
			}

			if($("#qustnrStartDate").val() == ""){
				alert("설문 시작일을 선택 주세요.");
				$("#qustnrStartDate").focus();
				return false;
			}

			if($("#qustnrEndDate").val() == ""){
				alert("설문 종료일을 선택 주세요.");
				$("#qustnrEndDate").focus();
				return false;
			}

			startDate = $("#qustnrStartDate").val();
			endDate = $("#qustnrEndDate").val();

			startDate = startDate.replace(".","");
			startDate = startDate.replace(".","");
			endDate = endDate.replace(".","");
			endDate = endDate.replace(".","");

			// 시작일이 종료일보다 큰지 비교
			if(Number(startDate) > Number(endDate)){
				alert("설문 종료일이 시작일보다 전날짜입니다.");
				$("#qustnrEndDate").focus();
				return false;
			}

			if($("#qustnrPurps").val() == ""){
				alert("설문 내용을 입력 주세요.");
				$("#qustnrPurps").focus();
				return false;
			}

			if($("#qestnCn1").val() == ""){
				alert("설문보기1를 입력 주세요.");
				$("#qestnCn1").focus();
				return false;
			}

			if($("#qestnCn2").val() == ""){
				alert("설문보기2를 입력 주세요.");
				$("#qestnCn2").focus();
				return false;
			}

			if($("#qestnCn3").val() == ""){
				alert("설문보기3를 입력 주세요.");
				$("#qestnCn3").focus();
				return false;
			}

			if($("#qestnCn4").val() == ""){
				alert("설문보기4를 입력 주세요.");
				$("#qestnCn4").focus();
				return false;
			}

			return true;
		}
//-->
</script>
 
			<div id="container"> 
	<form:form commandName="frmQestnr" name="frmQestnr" method="post"   > 
<input type="hidden" id="qestnrId" name="qestnrId" value="${qestnrVO.qestnrId}" />
<input type="hidden" id="yyyy" name="yyyy" value="${qestnrVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${qestnrVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${qestnrVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${qestnrVO.subClass}" />
	
				<div id="contents-area">
						<dl class="bbs-view mt-020">
							<dt> <span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName} (${currProcReadVO.subClass}분반) ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</span></dt>
			
							<dd><span>제목</span>:<input type="text" id="qustnrSj" name="qustnrSj"  maxlength="497" placeholder="(ex)설문제목 입력"  value="${readQestnrInfoVO.qustnrSj}" style="width:80%;" /></dd>		
			
							<dd><span>시작일</span>:<input type="text" id="qustnrStartDate" name="qustnrStartDate"  placeholder="(ex)2016.09.01" value="${readQestnrInfoVO.qustnrStartDate}" style="width:80px" readonly="readonly" /></dd>
						
							<dd><span>종료일</span>:<input type="text" id="qustnrEndDate" name="qustnrEndDate"  placeholder="(ex)2016.09.01" value="${readQestnrInfoVO.qustnrEndDate}" style="width:80px" readonly="readonly" /></dd>
						
							<dd><span>설문내용</span>:<input type="text" id="qustnrPurps" name="qustnrPurps" maxlength="995" placeholder="(ex)설문내용 입력" value="${readQestnrInfoVO.qustnrPurps}" style="width:80%;" /></dd>
			
							<dt><span>설문보기</span></dt>							
							<dd>[보기1]&nbsp;&nbsp;<input type="text" id="qestnCn1" name="qestnCn1" maxlength="2047" placeholder="(ex)설문보기1 입력" value="${readQestnrItemVO.qestnCn1}"  style="width:85%;" /></dd>					
							<dd>[보기2]&nbsp;&nbsp;<input type="text" id="qestnCn2" name="qestnCn2" maxlength="2047" placeholder="(ex)설문보기2 입력" value="${readQestnrItemVO.qestnCn2}"  style="width:85%;" /></dd>												
							<dd>[보기3]&nbsp;&nbsp;<input type="text" id="qestnCn3" name="qestnCn3" maxlength="2047" placeholder="(ex)설문보기3 입력" value="${readQestnrItemVO.qestnCn3}"  style="width:85%;" /></dd>												
							<dd>[보기4]&nbsp;&nbsp;<input type="text" id="qestnCn4" name="qestnCn4" maxlength="2047" placeholder="(ex)설문보기4 입력" value="${readQestnrItemVO.qestnCn4}"  style="width:85%;" /></dd>												
							<dd>[보기5]&nbsp;&nbsp;<input type="text" id="qestnCn5" name="qestnCn5" maxlength="2047" placeholder="(ex)설문보기5 입력" value="${readQestnrItemVO.qestnCn5}"  style="width:85%;" /></dd>	
							<dd>
								<input type="radio" id="etcAnswerAt" name="etcAnswerAt" value="Y" <c:if test="${ 'Y' eq readQestnrInfoVO.etcAnswerAt }"> checked="checked"</c:if> class="choice" /> 기타 입력 있음&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" id="etcAnswerAt" name="etcAnswerAt" value="N" <c:if test="${ 'N' eq readQestnrInfoVO.etcAnswerAt }"> checked="checked"</c:if> class="choice"  /> 기타 입력 없음
							</dd>
			
						</dl>
						<br/>
						<div class="btn-area align-right mt-10">
								<a href="#!" onclick="javascript:fn_list(); return false" class="gray-1">목록</a>
								<a href="#!" onclick="javascript:fn_formSave(); return false" class="orange">수정</a>
						</div>  					 						   					
				</div><!-- E : contents-area -->
				
</form:form>				
			</div><!-- E : container -->