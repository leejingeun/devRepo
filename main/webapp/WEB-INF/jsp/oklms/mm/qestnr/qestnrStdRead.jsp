<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<style>
<!--

.poll-result li																						{ float:left; min-width:45px; height:17px; font:12px Dotum; color:#FFF; text-align:right; }
.poll-result li.case-1																			{ background:#CA4036; }
.poll-result li.case-2																			{ background:#E37E2C; }
.poll-result li.case-3																			{ background:#F4B200; }
.poll-result li.case-4																			{ background:#54980C; }
.poll-result li.case-5																			{ background:#22A8A3; }
.poll-result li.case-6																			{ background:#369ACA; }
.poll-result li.case-7																			{ background:#4262BF; }
.poll-result li.case-8																			{ background:#6751C2; }
.poll-result li.case-9																			{ background:#C151C2; }
.poll-result i																						{ display:inline-block; font-style:normal; padding:3px 7px 0 0; }
.poll-result ul																						{ width:100%; height:17px; top:20px; background:#DDD; }
-->
</style>
<script type="text/javascript">
<!--
 
		 
		$(document).ready(function() {
			$(".requare").css("color", "red"); //필수값 * 빨간색 표시 css
			
			$(document).on("click","input[name=qestnAnswerSn]",function(){
				if($("#etcAnswerAt").val() == "Y"){
					var val = $(this).val();
					if('etc' == val){
						$("#etcAnswerCn").attr("disabled",false);
						$("#etcAnswerCn").focus();
					}else{
						$("#etcAnswerCn").val(null);
						$("#etcAnswerCn").attr("disabled",true);
					}	
				}
			});
			
		});
		

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

		/* 설문 참여하기 */
		function fn_save(){

			if (confirm("저장 하시겠습니까?")) {

				var selectInfo = $("input:radio[name='qestnAnswerSn']:checked").val();
				
				if(selectInfo == ""){
					alert("설문보기를 선택해주세요.");
					return false;
				}

				if(selectInfo == 'etc' && $("#etcAnswerAt").val() == "Y"){
					if($("#etcAnswerCn").val() == ""){
						alert("기타을 입력 주세요.");
						$("#etcAnswerCn").focus();
						return false;
					}
				}

				var reqUrl =  "/mm/qestnr/insertQestnrAnswerResult.do";

				$("#frmQestnr").attr("method", "post" );
				$("#frmQestnr").attr("action", reqUrl);
				$("#frmQestnr").submit();
			}
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
	<input type="hidden" name="pageIndex" value="<c:out value='${qestnrVO.pageIndex}'/>"/>
	<input type="hidden" name="memId" id="memId" value="" /> 
	<input type="hidden" id="qestnrId" name="qestnrId" value="${qestnrVO.qestnrId}" />
	<input type="hidden" id="qustnrQesitmId" name="qustnrQesitmId" value="${readQestnrItemVO.qustnrQesitmId}" />
	<input type="hidden" id="joinAt" name="joinAt" value="${qestnrVO.joinAt}" />
	<input type="hidden" id="etcAnswerAt" name="etcAnswerAt" value="${readQestnrInfoVO.etcAnswerAt}" />		
				<div id="contents-area">
<c:if test="${qestnrVO.joinAt eq 'N' }">
					<div>

 
	
						<dl class="bbs-view mt-020">
							<dt> <span>${readQestnrInfoVO.qustnrSj}</span></dt>
							<dd>
								<span>시작일</span> : ${readQestnrInfoVO.qustnrStartDate}(${readQestnrInfoVO.dayOfWeekStart})
								<span>종료일</span> : ${readQestnrInfoVO.qustnrEndDate}(${readQestnrInfoVO.dayOfWeekEnd})
							</dd>
							<dt></dt>
							<dd>
								<span>${readQestnrInfoVO.qustnrPurps}</span> 
	
									
									<c:if test="${!empty readQestnrItemVO.qestnCn1}"><dd><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="1" checked="checked"> ${readQestnrItemVO.qestnCn1}</dd></c:if>
									<c:if test="${!empty readQestnrItemVO.qestnCn2}"><dd><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="2"> ${readQestnrItemVO.qestnCn2}</dd></c:if>
									<c:if test="${!empty readQestnrItemVO.qestnCn3}"><dd><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="3"> ${readQestnrItemVO.qestnCn3}</dd></c:if>
									<c:if test="${!empty readQestnrItemVO.qestnCn4}"><dd><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="4"> ${readQestnrItemVO.qestnCn4}</dd></c:if>
									<c:if test="${!empty readQestnrItemVO.qestnCn5}"><dd><input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="5"> ${readQestnrItemVO.qestnCn5}</dd></c:if>
									<dd>
									<input type="radio" id="qestnAnswerSn" name="qestnAnswerSn" value="etc"> 기타
									<c:if test="${readQestnrInfoVO.etcAnswerAt eq 'Y' }">
										<input type="text" id="etcAnswerCn" name="etcAnswerCn" maxlength="999" placeholder="(ex)기타의견 입력" style="width:80%;" disabled="disabled" />
									</c:if>
									</dd>
								
							</dd>
						</dl>
 						
				    </div>
					<div class="btn-area align-right mt-010">
						<a href="#!" onclick="javascript:fn_list(); return false" class="gray-1">목록</a>
						<a href="#!" onclick="javascript:fn_save(); return false" class="orange">참여</a>
					</div>
</c:if>	
<c:if test="${qestnrVO.joinAt eq 'Y' }">
	<dl class="bbs-view mt-020 poll-result"> 
		<dt> <span>설문결과</span></dt>
	 

		<c:if test="${!empty readQestnrItemVO.qestnCn1}">
		<dd>
			<p>${readQestnrItemVO.qestnCn1} [ ${readQestnrItemVO.qestnAnswerSn1}명 ]</p>
			<ul>
				<li class="case-1" style="width:${readQestnrItemVO.qestnAnswerPercent1}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent1}</b> %</i></li>
			</ul>
		</dd>
		</c:if>
		<c:if test="${!empty readQestnrItemVO.qestnCn2}">
		<dd>
			<p>${readQestnrItemVO.qestnCn2} [ ${readQestnrItemVO.qestnAnswerSn2}명 ]</p>
			<ul>
				<li class="case-2" style="width:${readQestnrItemVO.qestnAnswerPercent2}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent2}</b> %</i></li>
			</ul>
		</dd>
		</c:if>
		<c:if test="${!empty readQestnrItemVO.qestnCn3}">
		<dd>
			<p>${readQestnrItemVO.qestnCn3} [ ${readQestnrItemVO.qestnAnswerSn3}명 ]</p>
			<ul>
				<li class="case-3" style="width:${readQestnrItemVO.qestnAnswerPercent3}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent3}</b> %</i></li>
			</ul>
		</dd>
		</c:if>
		<c:if test="${!empty readQestnrItemVO.qestnCn4}">
		<dd>
			<p>${readQestnrItemVO.qestnCn4} [ ${readQestnrItemVO.qestnAnswerSn4}명 ]</p>
			<ul>
				<li class="case-4" style="width:${readQestnrItemVO.qestnAnswerPercent4}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent4}</b> %</i></li>
			</ul>
		</dd>
		</c:if>
		<c:if test="${!empty readQestnrItemVO.qestnCn5}">
		<dd>
			<p>${readQestnrItemVO.qestnCn5} [ ${readQestnrItemVO.qestnAnswerSn5}명 ]</p>
			<ul>
				<li class="case-5" style="width:${readQestnrItemVO.qestnAnswerPercent5}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent5}</b> %</i></li>
			</ul>
		</dd>
		</c:if>
		<dd>
			<p>기타 [ ${readQestnrItemVO.qestnAnswerSn6}명 ]</p>
			<ul>
				<li class="case-9" style="width:${readQestnrItemVO.qestnAnswerPercent6}%;"><i><b>${readQestnrItemVO.qestnAnswerPercent6}</b> %</i></li>
			</ul>
		</dd>		

    </dl>
	<div class="btn-area align-right mt-010">
			<a href="#!" onclick="javascript:fn_list(); return false" class="gray-1">목록</a>
	</div>      
</c:if>
				</div><!-- E : contents-area -->
</form:form>				
			</div><!-- E : container -->