<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

						<h2>학습활동서</h2>

						<div class="group-area mb-040">
							<h4 class="mb-010"><span>${currProcVO.subjectName }</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>
							
<form:form commandName="frmActivity" name="frmActivity" method="post" enctype="multipart/form-data" >
<input type="hidden" name="activityNoteId"  value="${result.activityNoteId}" >

<input type="hidden" name="yyyy"  value="${activityVO.yyyy}" >
<input type="hidden" name="term"  value="${activityVO.term}" >
<input type="hidden" name="subjectCode"  value="${activityVO.subjectCode}" >
<input type="hidden" name="classId"  value="${activityVO.classId}" >
<input type="hidden" name="traningType"  value="${activityVO.traningType}" >
<input type="hidden" name="state"  value="${activityVO.state}" >

<c:set var="sumNumber" value="0" />
<c:set var="totalSumNumber" value="0" />  
<c:forEach var="result" items="${resultMember}" varStatus="status">
	<c:set var="sumNumber" value="${result.studyTime + sumNumber }" />
	<c:if test="${result.addyn eq 'N' }"><c:set var="totalSumNumber" value="${result.timeHour + totalSumNumber}" /></c:if>
</c:forEach>
							<table class="type-1-1 mb-030">
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
										<select id="weekCnt" name="weekCnt"  onchange="javascript:fn_Search();">
											<c:forEach var="result" items="${onlineTraningSchVO}" varStatus="status">
											<option value="${result.weekCnt}" <c:if test="${result.weekCnt eq activityVO.weekCnt }">selected</c:if>>${result.weekCnt}</option>
											</c:forEach>
										</select>
										</td>
										<td>${subjweekStdVO.traningStDate} ~ ${subjweekStdVO.traningEndDate}</td>
										<td>${subjweekStdVO.ncsUnitName}</td>
										<td>${subjweekStdVO.ncsElemName}</td>
										<td class="left">${subjweekStdVO.contentName}</td>
										<td>${totalSumNumber}</td>
									</tr>
								</tbody>
							</table>



						<table class="type-2 mb-015">
							<colgroup>
								<col style="width:20%" />
								<col style="width:20%" />
								<col style="width:20%" />
								<col style="width:20%" />
								<col style="width:20%" />
							</colgroup>
							<thead>
								<tr>
									<th>이름 (학번)</th>
									<th>실시일자</th>
									<th>훈련시간<br />(계획)</th>
									<th>훈련시간<br />(결과)</th>
									<th>성취도</th>
								</tr>
							</thead>
							<tbody>

							<c:forEach var="result" items="${resultMember}" varStatus="status">
							 
								<tr>
									<c:if test="${status.index eq '0'}"> 
										<td rowspan="${fn:length(resultMember)+1 }">${activityVO.sessionMemName}<br /><c:if test="${!empty activityVO.sessionMemId  }">( ${activityVO.sessionMemId} )</c:if></td>
										<td>${result.traningDate }
											<c:if test="${ !empty result.dayOfWeek }"> (${result.dayOfWeek })</c:if>
										</td>
									</c:if>
									<c:if test="${status.index ne '0'}">
									<td class="border-left">${result.traningDate } <c:if test="${!empty result.dayOfWeek  }">(${result.dayOfWeek })</c:if></td>
									</c:if>
									<td>
									
									<c:if test="${result.addyn eq 'N' }">${result.timeHour }시간</c:if>
									<c:if test="${result.addyn eq 'Y' }">보강훈련</c:if>
									
									</td>
									<td>
										<c:if test="${!empty result.studyTime}">${result.studyTime }시간</c:if> 																						
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
									<td>${totalSumNumber}시간</td>
									<td>${sumNumber}시간</td>
									<td>-</td>
								</tr>
								</c:if>							
							</c:forEach>
 
							</tbody>
						</table><!-- E : 교육시간 -->


 

							
						<table class="type-write">
							<colgroup>
								<col width="20%" />
								<col width="10%" />
								<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th>교육내용</th>
									<td colspan="2" class="left">										
										<p id="tooltip"></p><a href="#!" onmouseover="javascript:showtip('예시 : [ <c:out value="${fn:escapeXml(subjweekStdVO.contentName)}"/>  ]+ ~를 훈련하였습니다.');" onmouseout="javascript:hidetip();" class="checkfile">작성요령</a>
										<input type="text" name="content"  id="content" value="${result.content }" style="width:81%;" />
									</td>
								</tr>
								<tr>
									<th>요청사항</th>
									<td colspan="2" class="left"><input type="text" name="reqContent" id="reqContent"   value="${result.reqContent }"  style="width:97%;"/></td>
								</tr>
								<tr>
									<th>과제물첨부</th>
					
									<td  colspan="2" class="left">
																				
									<c:if test="${!empty resultFile}">
											<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="javascript:com.deleteFile('${resultFile.atchFileId}|${resultFile.fileSn}', '/lu/activity/listActivityStd.do');"  class="btn-del">[삭제]</a><br />
									</c:if>					
									<c:if test="${empty resultFile}">				
										<input type="text" id="atchFiles" name="atchFiles" style="width:50%;" readonly  onchange="fileCheck(this.form.atchFiles)">
										<span>
											<a href="#@" class="checkfile">파일선택</a>
											<input type="file" class="file_input_hidden" name="file-input" id="file-input"  onchange="javascript:document.getElementById('atchFiles').value = this.value" />
										</span>
									</c:if>											
										
										
										
									</td>
								</tr>
								<tr>
									<th>상태</th>
									<td colspan="2" class="left">
									<c:if test="${!empty result}">
									작성
									</c:if>
									<c:if test="${ empty result}">
									미작성
									</c:if>									
										
									</td>
								</tr>
							</tbody>
						</table><!-- E : 교육정보 -->
</form:form>
						
<script type="text/javascript">
<!--
		var tooltip=document.getElementById("tooltip");
		
		function showtip(text) {
			tooltip.innerText=text;
			tooltip.style.display="inline";
		}

		function hidetip() {
			tooltip.style.display="none";
		}

		function movetip() {					
			tooltip.style.pixelTop=event.y+document.body.scrollTop; 
			tooltip.style.pixelLeft=event.x+document.body.scrollLeft-240;		
		}

		document.onmousemove=movetip;

		$(document).ready(function() {
			 
			$('#file-input').on("change", previewImages);
		});
		
		/* 조회 */
		function fn_Search(){

			var reqUrl =  "/lu/activity/listActivityStd.do";
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();
			
		}
		
		/* HTML Form 신규, 수정 */
		function fn_formSave(){
			
			if($("#content").val()==""){
				alert("교육내용을 입력해 주세요.");
				$("#content").focus(); 
				return;
			}
			var reqUrl =  "/lu/activity/insertActivityStd.do";
			$("#frmActivity").attr("target", "_self");
			$("#frmActivity").attr("action", reqUrl);
			$("#frmActivity").submit();

		}
		function previewImages() { 
			  $("#fileName").val($('#file-input').val());
			  var filesize = 0;
			  if (this.files) {
				  $.each(this.files, readAndPreview);
			  }
			  
			  function readAndPreview(i, file) {
				if (window.FileReader) { // FireFox, Chrome, Opera 확인.
		
				    filesize = filesize + file.size;
					if(filesize > 1000000){ //Checking Sum 1M Size 
						alert("사이즈 1M이상 업로드 할수 없습니다.");
						
						$("#atchFiles").val("");
						
						return false;
					}
				}else{ // safari is not supported FileReader
			        alert('not supported Webbrowser!!');
			    }
			  }
		} 

//-->
</script>
						<div class="btn-area align-right mt-010">
							<c:if test="${result.activityNoteId eq null}">
								<a href="#!" onclick="javascript:fn_formSave();" class="orange">등록</a>
							</c:if>
							<c:if test="${result.activityNoteId ne null}">
								<a href="#!" onclick="javascript:fn_formSave();" class="orange">수정</a>
							</c:if>							
							
						</div><!-- E : btn-area -->