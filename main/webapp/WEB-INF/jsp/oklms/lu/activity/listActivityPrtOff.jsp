<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

						<h2>학습활동서</h2>

						<dl id="tab-type">
							<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();" id="selectTab">개설교과별 결과</a></dt>
							<dd id="tab-content-11" style="display:block;">

								<div class="search-box-1 mt-020">
									성명:<input type="text" name="searchName" id="searchName" value="${activityVO.searchName}"/>
									<a href="#!" onclick="javascript:fn_search_search();">검색</a>
								</div><!-- E : search-box-1 -->
								

								<h4 class="mt-020"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>
<form:form commandName="frmActivity" name="frmActivity" method="post" >
<input type="hidden" name="activityNoteId"  value="${result.activityNoteId}" >

<input type="hidden" name="yyyy"  value="${activityVO.yyyy}" >
<input type="hidden" name="term"  value="${activityVO.term}" >
<input type="hidden" name="subjectCode"  value="${activityVO.subjectCode}" >
<input type="hidden" name="classId"  value="${activityVO.classId}" >
<input type="hidden" name="traningType"  value="${activityVO.traningType}" >
<input type="hidden" name="state"  value="${activityVO.state}" >
<input type="hidden" name="searchName" id="searchMemName" value=""/>
								<table class="type-1-1 mt-010">
									<colgroup>
										<col style="width:60px" />
										<col style="width:150px" />
										<col style="width:*" />
										<col style="width:*" />
										<col style="width:*" />
										<col style="width:70px" />
									</colgroup>
									<thead>
										<tr>
											<th>주차</th>
											<th>기간</th>
											<th>능력단위</th>
											<th>능력단위요소</th>
											<th>수업내용</th>
											<th>주간<br />훈련시간</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<select id="weekCnt" name="weekCnt"  onchange="javascript:fn_Search(this.value);">
													<c:forEach var="result" items="${onlineTraningSchVO}" varStatus="status">
													<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq activityVO.weekCnt }">selected</c:if>>${result.weekCnt}</option>
													</c:forEach>
												</select>
											</td>
											<td>${subjweekStdVO.traningStDate} ~ ${subjweekStdVO.traningEndDate}</td>
											<td>${subjweekStdVO.ncsUnitName}</td>
											<td>${subjweekStdVO.ncsElemName}</td>
											<td class="left">${subjweekStdVO.contentName}</td>
											<td>${subjweekStdVO.weekTimeHour}</td>
										</tr>
									</tbody>
								</table>



								<table class="type-2 mt-030">
									<colgroup>
										<col style="width:50px" />
										<col style="width:*" />
										<col style="width:135px" />
										<col style="width:135px" />
										<col style="width:95px" />
										<col style="width:85px" />
									</colgroup>
									<tr>
										<th><input type="checkbox" name="checkbox" id="checkbox"  class="choice" /></th>
										<th>성명</th>
										<th>훈련시간 (계획)</th>
										<th>훈련시간 (실제)</th>
										<th>학습활동서</th>
										<th>과제물</th>
									</tr>									
									<c:forEach var="result" items="${memberlist}" varStatus="status1">
									<tr>
										<td><input type="checkbox" name="memSeqs" value="${result.memId},${result.atchFileId}"  class="choice" /></td>
										<td><a href="#!" id="${result.memId}" onclick="javascript:showTabbtn2();$('#current_${status1.count}').click();" class="text">${result.memName } </a></td>
										<td>${subjweekStdVO.weekTimeHour}</td>
										<td>
										<c:if test="${result.studyTime != null and result.studyTime ne '' }">${result.studyTime }</c:if> 		
										</td>
										<td>
											<c:if test="${empty result.content }">
												<a href="#!" class="btn-line-orange">미작성</a>
											</c:if>
											<c:if test="${!empty result.content }">
												<span id="tooltip"></span>
												<a href="#!" onmouseover="showtip('<c:out value="${fn:escapeXml(result.content)}"/>');" onmouseout="hidetip();" class="btn-line-gray">작성</a>
											</c:if>
										</td>
										<td>
											<c:if test="${empty result.atchFileId }">
												<a href="#!" class="btn-line-orange">미제출</a>
											</c:if>
											<c:if test="${!empty result.atchFileId }">									
												<a  href="#!" onclick="javascript:com.downFile('${result.atchFileId}','1');" class="btn-line-gray">제출</a>
											</c:if>
										</td>
									</tr>									
									</c:forEach>

						 
								</table><!-- E :OJT 학습활동서 -->
</form:form>
								<script type="text/javascript">
								$(document).ready(function() { 
									$("#searchName").keypress(function(e) { 
										// 엔터키가 들어오면
										        if (e.keyCode == 13){
										           // 함수실행
										        	fn_search_search();
										        }
								    });


									
									$("#checkbox").click(function(){
											
											if($("#checkbox").is(":checked")==true){
												$("input:checkbox[name='memSeqs']").prop("checked",true);
											}else{
												$("input:checkbox[name='memSeqs']").prop("checked",false);
											} 
									});
									
									<c:if test="${!empty activityVO.memId}">
										 	
										setTimeout(function() { $('#${activityVO.memId}').click();	 }, 1000); 
									</c:if>

									
								});
									var tooltip=document.getElementById("tooltip");
									function showtip(text) {
										tooltip.innerText=text;
										tooltip.style.display="inline";
									}

									function hidetip() {
										tooltip.style.display="none";
									}

									function movetip() {
										if(tooltip){
											tooltip.style.pixelTop=event.y+document.body.scrollTop; 
											tooltip.style.pixelLeft=event.x+document.body.scrollLeft-240;											
										}
 
									}

									document.onmousemove=movetip;
									
									/* 조회 */
									function fn_Search(value){
										$("#weekCnt").val(value);
										var reqUrl =  "/lu/activity/listActivityPrt.do";
										$("#frmActivity").attr("target", "_self");
										$("#frmActivity").attr("action", reqUrl);
										$("#frmActivity").submit();
									}
									
									function fn_search_search(){
										$("#searchMemName").val($("#searchName").val());
										var reqUrl =  "/lu/activity/listActivityPrt.do";
										$("#frmActivity").attr("target", "_self");
										$("#frmActivity").attr("action", reqUrl);
										$("#frmActivity").submit();
									}
									
									/* 첨부파일다운로드 */
									function fn_Download(){
										if(!$("input:checkbox[name='memSeqs']").is(":checked")){
											alert("다운받을 학습근로자를 선택하세요.");
											return;
										}
										var reqUrl =  "/lu/activity/downloadActivityPrt.do";
										$("#frmActivity").attr("target", "_self");
										$("#frmActivity").attr("action", reqUrl);
										$("#frmActivity").submit();
									}
									var myScroll;

									function loaded() {
										myScroll = new iScroll('wrap', {
											snap: true,
											momentum: false,
											hScrollbar: false,
										});
									}

									document.addEventListener('DOMContentLoaded', loaded, false);
								</script>

								
								<div class="btn-area align-right mt-010">
										<a href="#!" onclick="javascript:fn_Download();" class="orange">과제물 다운로드</a>
										<!-- 임시주석 -->
							<!--<a href="#!" class="yellow">SMS 발송</a>
							<a href="#!" onclick="javascript:fn_excelDown();" class="orange">엑셀 저장</a> -->
								</div><!-- E : btn-area -->
							</dd>



							<dt class="tab-name-12"><a href="javascript:showTabbtn2();">학습근로자별 결과</a></dt>
							<dd id="tab-content-12">

								<script type="text/javascript" src="/js/oklms/ui_tab.js"></script>
								<script type="text/javascript" src="/js/oklms/iscroll.js"></script>

								<ul id="learner-wrap" class="mt-030 mb-010">
									<li id="prev" onclick="myScroll.scrollToPage('prev', 0);return false">prev</li>
									<li id="wrap">
										<!-- 학습자수 * 128px의 값을 아래 style width에 넣어줘야함 -->
										<div id="scroller" style="width:${fn:length(memberlist)*128}px;">
											<ul id="thelist" class="name-tab-btn">											
											<c:forEach var="result" items="${memberlist}" varStatus="status">
												<c:if test="${status.index eq '0' }">
													<li id="current_${status.count}" class="current"><a href="#!">${result.memName }</a></li>
												</c:if><c:if test="${status.index ne '0' }">
													<li id="current_${status.count}" ><a href="#!">${result.memName }</a></li>
												</c:if>
											</c:forEach>											 	
											</ul>
										</div>
									</li>
									<li id="next" onclick="myScroll.scrollToPage('next', 0);return false">next</li>

								</ul><!-- E : learner-wrap -->

<c:forEach var="memberVO" items="${memberlist}" varStatus="status1">

								<div class="group-area name-tab-content">
									<h4 class="mt-020"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>
<div id="">									
									<table class="type-1-1 mt-010">
										<colgroup>
											<col style="width:60px" />
											<col style="width:150px" />
											<col style="width:*" />
											<col style="width:*" />
											<col style="width:*" />
											<col style="width:70px" />
										</colgroup>
										<thead>
											<tr>
												<th>주차</th>
												<th>기간</th>
												<th>능력단위</th>
												<th>능력단위요소</th>
												<th>수업내용</th>
												<th>주간<br />훈련시간</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>
													<select id="weekCnt" name="weekCnt"   onchange="javascript:fn_Search(this.value);">
														<c:forEach var="result" items="${onlineTraningSchVO}" varStatus="status2">
														<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq activityVO.weekCnt }">selected</c:if>>${result.weekCnt}</option>
														</c:forEach>
													</select>
												</td>
												<td>${subjweekStdVO.traningStDate} ~ ${subjweekStdVO.traningEndDate}</td>
												<td>${subjweekStdVO.ncsUnitName}</td>
												<td>${subjweekStdVO.ncsElemName}</td>
												<td class="left">${subjweekStdVO.contentName}</td>
												<td>${subjweekStdVO.weekTimeHour}</td>
											</tr>
										</tbody>
									</table>



									<table class="type-2 mt-030">
										<colgroup>
											<col style="width:20%" />
											<col style="width:20%" />
											<col style="width:20%" />
											<col style="width:20%" />
											<col style="width:20%" />
										</colgroup>
										<thead>
											<tr>
												<th>이름/학번</th>
												<th>실시일자</th>
												<th>훈련시간(계획)</th>
												<th>훈련시간(결과)</th>
												<th>성취도</th>
											</tr>
										</thead>
										<tbody>
										
										
										
						<c:set var="sumNumber" value="0" />
							<c:forEach var="result" items="${memberVO.arrActivityVO}" varStatus="status">
								<c:set var="sumNumber" value="${result.studyTime + sumNumber }" />
								<tr>
									<c:if test="${status.index eq '0'}"> 
										<td rowspan="${fn:length(memberVO.arrActivityVO)+1 }">${memberVO.memName }<br />( ${memberVO.memId } )</td>
										<td>${result.traningDate }<c:if test="${result.dayOfWeek ne '' }" > (${result.dayOfWeek })</c:if></td>
									</c:if>
									<c:if test="${status.index ne '0'}">
									<td class="border-left">
											${result.traningDate }<c:if test="${result.dayOfWeek ne '' }" >(${result.dayOfWeek }) </c:if>
									</td>
									</c:if>
									<td>
									
									<c:if test="${result.addyn eq 'N' }">${result.timeHour }시간</c:if>
									<c:if test="${result.addyn eq 'Y' }">보강훈련</c:if>
									
									</td>
									<td>
										<c:if test="${result.studyTime != null and result.studyTime ne '' }"><c:if test="${result.studyTime ne '' }" >${result.studyTime }시간</c:if></c:if> 																						
									</td>
									<td>																				
									<c:forEach var="i" begin="1" end="${result.achievement}" step="1">
								      <img src="/images/oklms/std/inc/icon_star_on.png" />
								    </c:forEach>
									</td>
								</tr>
								<c:if test="${status.last}"> 
								<tr>
									<td class="border-left">계</td>
									<td><c:if test="${subjweekStdVO.weekTimeHour ne '' }" >${subjweekStdVO.weekTimeHour}시간</c:if></td>
									<td><c:if test="${sumNumber ne '' }" >${sumNumber}시간</c:if></td>
									<td>-</td>
								</tr>
								</c:if>							
							</c:forEach>						
										 
										</tbody>
									</table>

									<table class="type-write mt-005" style="border-top:1px solid #DDD;">
										<colgroup>
											<col width="20%" />
											<col width="10%" />
											<col width="*" />
										</colgroup>
										<tbody>
											<tr>
												<th>교육내용</th>
												<td colspan="2" class="left">${memberVO.content }</td>
											</tr>
											<tr>
												<th>요청사항</th>
												<td colspan="2" class="left">${memberVO.reqContent }</td>
											</tr>
											<tr>
												<th>과제물첨부</th>
												<td colspan="2" class="left">
											<c:if test="${!empty memberVO.atchFileId }">
												<a href="#!"  onclick="javascript:com.downFile('${memberVO.atchFileId}','1');" class="text-file">${memberVO.orgFileName }</a>&nbsp;&nbsp;&nbsp;									
												<a  href="#!" onclick="javascript:com.downFile('${memberVO.atchFileId}','1');" class="btn-full-blue" >저장</a>
											</c:if>
												</td>
											</tr>
											 
										</tbody>
									</table>
</div>
									<div class="btn-area align-right mt-010">
										<c:if test="${status1.first}" >
											<a href="#!" onclick="javascript:$('#current_${fn:length(memberlist)}').click();" class="gray-1 float-left">&lt; 이전 학생</a>
											<a href="#!" onclick="javascript:$('#current_${status1.count+1}').click();" class="gray-1 float-left">다음 학생 &gt;</a>
										</c:if>
																				
										<c:if test="${!status1.first and !status1.last  }" >
											<a href="#!" onclick="javascript:$('#current_${status1.count-1}').click();" class="gray-1 float-left">&lt; 이전 학생</a>
											<a href="#!" onclick="javascript:$('#current_${status1.count+1}').click();" class="gray-1 float-left">다음 학생 &gt;</a>
										</c:if>
										
										<c:if test="${status1.last }" >
											<a href="#!" onclick="javascript:$('#current_${status1.count-1}').click();" class="gray-1 float-left">&lt; 이전 학생</a>
											<a href="#!" onclick="javascript:$('#current_1').click();" class="gray-1 float-left">다음 학생 &gt;</a>										
										</c:if>
										<a href="#!" onclick="javascript:showTabbtn1();" class="gray-3">목록</a>
									</div><!-- E : btn-area -->
								</div>
								
</c:forEach>

							</dd>
						</dl>


