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
 
function fn_excel(){
	 
	var reqUrl = "/lu/traningstatus/excelTraningstatusCdp.do";
	$("#frmTraningstatus").attr("target", "_self");
	$("#frmTraningstatus").attr("action", reqUrl);
	$("#frmTraningstatus").submit();

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
							
								<c:if test="${!empty onlineTraningVO}">
								<table  class="type-3">
									<tr>
										<th>ON-LINE 출석정책</th>
										<th> 출석 </th>
										<td>${onlineTraningVO.attendProgress}이상</td>
										<th>지각</th>
										<td> ${onlineTraningVO.attendProgress} 미만&nbsp; ${onlineTraningVO.cutProgress}이상 </td>
										<th> 결석 </th>
										<td>${onlineTraningVO.cutProgress}이상</td>
									</tr>
								</table>		
								</c:if>
								   

								<table class="type-2">
									<colgroup>
										<col style="width:40px" />
										<col style="width:100px" />
										<col style="width:*" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:80px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:40px" />
										<col style="width:80px" />
									</colgroup>
									<thead>
										<tr>
											<th>번호</th>
											<th>학번</th>
											<th>이름</th>
											<th>학년</th>
											<th>출석</th>
											<th>지각</th>
											<th>결석</th>
											<th>진도율</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="resultlists" items="${resultlist}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${resultlists.memId }</td>
											<td class="left"><img src="/commbiz/photo/getAunuriUserImage.do?memId=${resultlists.memId }" width="65" height="55" alt="${resultlists.memName }"  />&nbsp;&nbsp;&nbsp;${resultlists.memName }</td>
											<td>${resultlists.level}</td>
											<td>${resultlists.onAttend }</td>
											<td>${resultlists.onLateness }</td>
 											<td>
 											<c:if test="${!empty onlineTraningVO}">
 											${resultlists.onTotalTime - resultlists.onAttend - resultlists.onLateness}
 											</c:if>
 											<c:if test="${empty onlineTraningVO}">
 											0
 											</c:if>
 											</td>
											<td>
												<div class="progress-area2">
													<p>${resultlists.onRealProgressRate}%</p>
													<progress value="${resultlists.onRealProgressRate}" max="100"></progress>
												</div>
											</td>
										</tr>
									</c:forEach> 
								<c:if test="${empty resultlist}">
									<tr>
										<td colspan="9">등록된 데이터가 없습니다.</td>
									</tr>							
								</c:if>
								
									</tbody>
								</table><!-- E : 전체 학습근로자관리-->
 
 							</dd>
					  
						</dl>
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
								<a href="#" onclick="javascript:self.close();" class="gray-3">닫기</a>
							</li>
						</ul><!-- E : page-num-btn -->
						
</div>
 