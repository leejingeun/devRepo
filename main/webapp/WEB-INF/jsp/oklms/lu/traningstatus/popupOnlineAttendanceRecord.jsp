<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
<!--
@media print																{.not_print {display: none;}}
-->
</style>
<script type="text/javascript">
<!--

function pop_closeWin(){
		self.close();
};
function fn_excel(){
	 
	var reqUrl = "/lu/traningstatus/excelTraningstatusPrt.do";
	$("#frmTraningstatus").attr("target", "_self");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

}
//--> 
</script>
		<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>온라인출석부</h1>
			<h4 class="mb-010"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>


			<div class="progress-area mb-030">
				<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
				<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
				<p>실제 진도율 (<c:if test="${ getProgress.realProgressRate > 0}" ><fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" /></c:if>)</p>
				<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
			</div><!-- E : 진행율 -->

			<table class="type-2">
				<colgroup>
					<col style="width:40px" />
					<col style="width:90px" />
					<col style="width:*" />
					<col style="width:100px" />
					<col style="width:40px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
					<col style="width:30px" />
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">번호</th>
						<th rowspan="2">학번</th>
						<th rowspan="2">이름</th>
						<th rowspan="2">학과</th>
						<th rowspan="2">학년</th>
						<th colspan="15">주차</th>
					</tr>
					<tr>
						<th class="border-left">1</th>
						<th>2</th>
						<th>3</th>
						<th>4</th>
						<th>5</th>
						<th>6</th>
						<th>7</th>
						<th>8</th>
						<th>9</th>
						<th>10</th>
						<th>11</th>
						<th>12</th>
						<th>13</th>
						<th>14</th>
						<th>15</th>
					</tr>
				</thead>
				<tbody>
				
				
				
				<c:forEach var="resultlist" items="${resultlist}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${resultlist.memId }</td>
						<td class="left">${resultlist.memName }</td>
						<td> </td>
						<td>${resultlist.level}</td>
						<td>
							<c:if test="${resultlist.te1 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te1 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te1 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te2 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te2 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te2 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te3 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te3 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te3 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te4 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te4 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te4 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te5 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te5 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te5 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te6 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te6 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te6 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te7 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te7 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te7 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te8 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te8 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te8 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te9 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te9 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te9 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te10 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te10 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te10 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te11 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te11 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te11 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te12 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te12 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te12 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te13 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te13 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te13 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te14 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te14 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te14 eq '2'}" >△</c:if>
						</td>
						<td>
							<c:if test="${resultlist.te15 eq '0'}" >X</c:if>
							<c:if test="${resultlist.te15 eq '1'}" >O</c:if> 
							<c:if test="${resultlist.te15 eq '2'}" >△</c:if>
						</td>						
					</tr>
				</c:forEach>
   				<c:if test="${empty resultlist}">
					<tr>
						<td colspan="20">등록된 데이터가 없습니다.</td>
					</tr>							
				</c:if>
				</tbody>
			</table><!-- E : 전체 학습근로자관리-->
  <form name="frmTraningstatus" id="frmTraningstatus" method="post">
 <input type="hidden" id="yyyy" name="yyyy" value="${traningStatusVO.yyyy }"/>
 <input type="hidden" id="term" name="term" value="${traningStatusVO.term }"/>
 <input type="hidden" id="classId" name="classId" value="${traningStatusVO.classId }"/>
 <input type="hidden" id="subjectCode" name="subjectCode" value="${traningStatusVO.subjectCode }"/>
 <input type="hidden" id="searchCondition" name="searchCondition" value="${traningStatusVO.searchCondition }"/>
 </form>

			<ul class="page-num-btn mt-015 not_print">
				<li class="page-btn-area">
					<a href="#" onclick="javascript:window.print();" class="orange">프린트</a>
					<a href="#" onclick="javascript:fn_excel();" class="yellow">엑셀 다운로드</a>
					<a href="#" onclick="javascript:pop_closeWin();" class="gray-3">닫기</a>
				</li>
			</ul><!-- E : page-num-btn -->
 
		</div><!-- E : wrapper -->