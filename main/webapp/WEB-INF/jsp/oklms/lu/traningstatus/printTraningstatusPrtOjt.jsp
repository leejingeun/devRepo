<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<script type="text/javascript">
<!--

$(document).ready(function() {
 
});

function imgError()
{
	event.srcElement.src = "/images/oklms/nonimg.png";
}

function imgonerror(){
	var all_img=document.getElementsByTagName("img");
	if(all_img.length > 0)
	{
		for(var i=0;i<all_img.length;i++)
		{
		  all_img[i].onerror=imgError;
		}
	} 
}
 

//--> 
</script>
<div  class="mt-050 mb-020" style="margin-left:60px;margin-right:50px;" >
						<dl id="tab-type"> 
							<h4 class="mt-020 mb-010"><span>${currProcVO.subjectName } ${currProcVO.subClass }반</span> ㅣ ${currProcVO.yyyy}학년도 / ${currProcVO.term}학기</h4>

								<div class="progress-area mb-030">
									<p>권장 진도율 (<fmt:formatNumber value="${ getProgress.allTimeHourNow/getProgress.totalTime }"  type="percent" />)</p>
									<progress value="${ getProgress.allTimeHourNow/getProgress.totalTime *100 }" max="100"></progress>
									<p>실제 진도율 (<c:if test="${ getProgress.realProgressRate > 0}" ><fmt:formatNumber value="${ getProgress.realProgressRate/100 }"  type="percent" /></c:if>)</p>
									<progress value="${ getProgress.realProgressRate }" max="100"></progress>								
								</div><!-- E : 진행율 -->							   								   

								<table class="type-2">
									<colgroup>
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:120px" />
										<col style="width:120px" />
										<col style="width:*" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:80px" />
									</colgroup>
									<thead>
										<tr>

											<th rowspan="2">번호</th>
											<th rowspan="2">분반</th>
											<th rowspan="2">기업명</th>
											<th rowspan="2">학번</th>
											<th rowspan="2">이름</th>
											<th rowspan="2">학년</th>
											<th colspan="4">학습현황</th>
										</tr>
										<tr>
											<th class="border-left">출석</th>
											<th>지각</th>
											<th>결석</th>
											<th>진도율</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="resultlists" items="${resultlist}" varStatus="status">
										<tr>

											<td>${status.count}</td>
											
											<td>${resultlists.classId }</td>
											<td>${resultlists.companyName }</td>
											
											<td>${resultlists.memId }</td>
											<td class="left"><img src="/commbiz/photo/getAunuriUserImage.do?memId=${resultlists.memId }" width="65" height="55" alt="${resultlists.memName }"  />&nbsp;&nbsp;&nbsp;${resultlists.memName }</td>
											<td>${resultlists.level}</td>
											
											<td>${resultlists.attend }</td>
											<td>${resultlists.lateness }</td>
 											<td>${resultlists.totalTime - resultlists.attend - resultlists.lateness}</td>
											<td>
												<div class="progress-area2">
													<p>${resultlists.realProgressRate} %</p>
													<progress value="${resultlists.realProgressRate }" max="100"></progress>
												</div>
											</td>
 
										</tr>
											 
									</c:forEach>
								<c:if test="${empty resultlist}">
									<tr>
										<td colspan="11">등록된 데이터가 없습니다.</td>
									</tr>							
								</c:if>
 
									</tbody>
								</table><!-- E : 전체 학습근로자관리-->
  
					  
						</dl>
						<ul class="page-num-btn mt-015 not_print">
							<li class="page-btn-area">
								<a href="#" onclick="javascript:window.print();" class="orange">프린트</a> 
								<a href="#" onclick="javascript:self.close();" class="gray-3">닫기</a>
							</li>
						</ul><!-- E : page-num-btn -->
						
</div>
 