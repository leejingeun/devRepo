<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>
<c:set var="noteInput"  value="fn_insert" />
						<h2>주간훈련일지</h2>
					
<script type="text/javascript">
<!--
	 
		
		/* 조회 */
		function fn_Search(){

			var reqUrl =  "/lu/weektraning/listWeekTraningNoteCot.do";
			$("#frmWeekTraning").attr("target", "_self");
			$("#frmWeekTraning").attr("action", reqUrl);
			$("#frmWeekTraning").submit();
			
		}
		/* 제출 */
		function fn_insert(){
		
			var reqUrl =  "/lu/weektraning/insertWeekTraningNoteCot.do";
			$("#frmWeekTraning").attr("target", "_self");
			$("#frmWeekTraning").attr("action", reqUrl);
			$("#frmWeekTraning").submit();
			
		}		 
		function fn_cancel(){
			
			alert("훈련일지를 등록해야 제출할수있습니다.");
		}	
		/* 출력*/
		function fn_print(addyn){

			if(addyn=="Y"){
				if(!$("#addTraning").val()){
					alert("보강훈련이 없습니다.");
					return;					
				}
			}
			var reqUrl =  "/lu/weektraning/printWeekTraningNoteCot.do";
			$("#addyn").val(addyn);
			$("#current").val("");
			$("#frmWeekTraning").attr("target", "_blank");
			$("#frmWeekTraning").attr("action", reqUrl);
			$("#frmWeekTraning").submit();
			
		}		
		/* 출력*/
		function fn_printlist(addyn){

			if(addyn=="Y"){
				if(!$("#addTraning").val()){
					alert("보강훈련이 없습니다.");
					return;					
				}
			}
			var reqUrl =  "/lu/weektraning/printWeekTraningNoteCot.do";
			$("#addyn").val(addyn); 
			$("#current").val("sign");
			$("#frmWeekTraning").attr("target", "_blank");
			$("#frmWeekTraning").attr("action", reqUrl);
			$("#frmWeekTraning").submit();
			
		}		
		 	 
//-->
</script>
						<div class="search-box-1">
<form:form commandName="frmWeekTraning" name="frmWeekTraning" method="post" >		
<input type="hidden" name="traningType" id="traningType" value="OJT" />
<input type="hidden" name="addyn" id="addyn"  value="" />
<input type="hidden" id="addTraning" name="addTraning" />
<input type="hidden" id="current" name="current" />
<input type="hidden" id="companyId" name="companyId" />	
<input type="hidden" id="traningProcessId" name="traningProcessId" />
	
							<select id="yyyy" name="yyyy"  > 
									<c:forEach var="i" begin="0" end="5" step="1">
								      <option value="${nowYear-i}" <c:if test="${traningNoteVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
								    </c:forEach>								
							</select> 

							<select name="term" > 
								<option value="1" <c:if test="${traningNoteVO.term eq '1' }" > selected="selected"  </c:if>>1학기</option>
								<option value="2" <c:if test="${traningNoteVO.term eq '2' }" > selected="selected"  </c:if>>2학기</option>
								<option value="3" <c:if test="${traningNoteVO.term eq '3' }" > selected="selected"  </c:if>>3학기</option>
								<option value="4" <c:if test="${traningNoteVO.term eq '4' }" > selected="selected"  </c:if>>4학기</option>
							</select>
				
							<select id="weekCnt" name="weekCnt"  onchange="javascript:fn_Search();">
								<option value="" >- 주차 -</option>
								<c:forEach var="result" items="${bottomList}" varStatus="status">
								<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq traningNoteVO.weekCnt }">selected</c:if>>${result.weekCnt}주차</option>
								</c:forEach>
								 
							</select>
							
							<a href="#!" onclick="javascript:fn_Search();">조회</a>
</form:form>							
						</div><!-- E : search-box-1 -->


						<table class="type-2 mt-010  mb-010">
							<colgroup>

								<col style="width:250px" />
								<col style="width:100px" />
								<col style="width:80px" />
								<col style="width:80px" />
								<col style="width:*" />
								<col style="width:15%" />
							</colgroup>
							<thead>
								<tr>
									<th>훈련과정명</th>
									<th>학년도</th>
									<th>학기</th>
									<th>주차</th>
									<th>개설교과</th>
									<th>훈련일지</th>
								</tr>
							</thead>
							<tbody>
							<!-- topList -->
							<c:forEach var="result" items="${topList}" varStatus="status">
							
<script type="text/javascript">
<!-- 
	$('#traningProcessId').val('${result.traningProcessId}');
	$('#companyId').val('${result.companyId}');
//-->
</script>
							
								<tr>
									<td>${result.hrdTraningName }</td>
									<td>${result.yyyy}</td>
									<td>${result.term}</td>
									<td>${result.weekCnt}</td>
									<td class="left">${result.subjectName} ${result.classId}반</td>
									<td>
										<c:if test="${empty result.state }">
										<span class="btn-line-orange" style="float: none;">미등록</span>
										<c:set var="noteInput" value="fn_cancel" />
										</c:if>
										<c:if test="${!empty result.state }">
										<span class="btn-line-gray" style="float: none;">등록</span>
										</c:if>										
									</td>
								</tr>
							</c:forEach>
							 
								
							</tbody>
						</table>

						<div class="btn-area align-right mt-010">
							<c:if test="${!empty topList}" >
							<c:if test="${noteInput eq 'fn_insert' }" >
								<a href="#" onclick="javascript:fn_print('N');" class="orange">교육일지 출력</a>
								<a href="#" onclick="javascript:fn_printlist('N');" class="orange">서명 출력</a>
								<a href="#" onclick="javascript:fn_print('Y');" class="orange">보강일지 출력</a>
								<a href="#" onclick="javascript:fn_printlist('Y');" class="orange">서명 출력</a>
							</c:if>								
							<a href="#" onclick="javascript:${noteInput}();" class="yellow">제출</a>
							</c:if>
						</div><!-- E : btn-area -->
						
						<table class="type-2 mt-030">
							<colgroup>
								<col style="width:100px" />
								<col style="width:*" />
								<col style="width:25%" />
								<col style="width:25%" />
							</colgroup>
							<thead>
								<tr>
									<th>주차</th>
									<th>학습기간</th>
									<th>제출현황(정규훈련)</th>
									<th>제출현황(보강훈련)</th>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach var="result" items="${bottomList}" varStatus="status">
								<tr <c:if test="${result.weekCnt eq traningNoteVO.weekCnt }" >class="highlight"</c:if>>
									<td>${result.weekCnt}주차</td>
									<td>${result.traningSt} ~ ${result.traningEd}</td>
									<td>
									<c:if test="${empty result.state}" >
										-
									</c:if>
									<c:if test="${result.state eq 'W'}" >
										<span class="btn-line-orange"  style="float: none;">미제출</span>
									</c:if>
									<c:if test="${result.state  eq 'I' or result.state  eq 'C'}" >
										<span class="btn-line-gray"  style="float: none;">제출</span>
									</c:if>
									<c:if test="${result.state  eq 'X' }" >
										<span class="btn-line-red"  style="float: none;">반려</span>
									</c:if>									
									</td>
									<td>
									
							<c:if test="${!empty result.addState  }" >
								<c:if test="${result.weekCnt eq traningNoteVO.weekCnt }" >
								<script type="text/javascript">
									$('#addTraning').val('Y');
								</script>								
								</c:if>
							</c:if>									
									<c:if test="${empty result.addState}" >
										-
									</c:if>
									<c:if test="${result.addState eq 'W'}" >
										<span class="btn-line-orange"  style="float: none;">미제출</span>
									</c:if>									
									<c:if test="${result.addState  eq 'I' or result.addState  eq 'C'}" >
										<span class="btn-line-gray"  style="float: none;">제출</span>
									</c:if>
									<c:if test="${result.addState  eq 'X' }" >
										<span class="btn-line-red"  style="float: none;">반려</span>
									</c:if>		
									</td>
								</tr>
							</c:forEach>								
							</tbody>
						</table>
