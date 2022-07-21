<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
			<div class="progress-area mb-030">
				<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
				<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
				<p>실제 진도율 (<c:if test="${ getProgress.realProgressRate > 0}" ><fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" /></c:if>)</p>
				<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
			</div><!-- E : 진행율 -->

			<table width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
				  
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
  
			</table><!-- E : 전체 학습근로자관리-->
  