<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="egovframework.com.cmm.service.EgovProperties"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%@ include file="/common/sso_common.jsp"%> 
<%@ include file="/common/sp_const.jsp"%> 
<% 
	String contextRootJS = request.getContextPath();
	String ssoYn =  EgovProperties.getProperty("Globals.ssoYn");
%>
<c:set var="ssoYn" value="<%=ssoYn %>"/>
<div id="container" class="main-img">
<script type="text/javascript">
<!--
function fn_egov_notice( nttId, bbsId) {
	 if(bbsId == "") return false; //20150508
	document.frm.nttId.value = nttId;
	document.frm.bbsId.value = bbsId;  
	document.frm.action = "/mm/cop/bbs/"+bbsId+"/popupSelectBoardArticle.do";
	document.frm.submit();
	 
}
//-->
</script>
				<script  type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/mvisual.js"></script>
<form name="frm"  id="frm" method="post">
<input type="hidden" name="bbsId"  />
<input type="hidden" name="nttId" />
</form>				
				<div id="mvisual-wrap">
					<div id="mvisual">
						<div class="visImgEffectWrap">
					<c:if test="${fn:length(popupzoneResultList) != 0}">
						<c:forEach var="mainResult" items="${popupzoneResultList}" varStatus="status">
						<div class='visImgEffect'><div class="visImg"><img src="/cmm/fms/getImage.do?atchFileId=${mainResult.bannerImageFile}" alt="New Start! 일과 학습을 동시에, 취업난도 해결하고 기업이 원하는 맞춤형 인력을 양성할 수 있는 제도" /></div></div>
						</c:forEach>
					</c:if>
						</div>
					</div>

					<div class="nums-wrap">
						<div class="ctrl-nums">
							<c:forEach var="a" begin="1" end="${fn:length(mainResultList)}" step="1">
							<a href="#visImg${a}"><span>${a}</span></a>
						    </c:forEach>
						</div>					

						<a href="#!" class="ctrl-btn btn-play"><span>재생</span></a>
						<a href="#!" class="ctrl-btn btn-stop"><span>정지</span></a>
					</div>
				</div>

				<script type="text/javascript">
					var mVisual = new mainVisualEffect({obj:$("#mvisual-wrap"), contents:'.visImgEffect', isPlay:true});
				</script><!-- E : mvisual-wrap -->
				<hr />



				<div id="banner">
					<a href="#!">이용자 메뉴얼</a>
					<a href="/mm/cop/bbs/BBSMSTR_000000000010/popupSelectBoardList.do"  >자주묻는 질문</a>
				</div><!-- E : banner-area -->
				<hr />



				<dl id="notice">
					<dt><span>공지사항</span></dt>
					
					<c:if test="${fn:length(noticeResultList) == 0}">
					<dd>등록된 공지사항이 없습니다.</dd>
					</c:if>
					<c:forEach var="noticeResult" items="${noticeResultList}" varStatus="status">
					<dd>
						<c:choose>
						<c:when test="${'Y' == noticeResult.topNoticeYn}">
							<a href="#!"  onclick="javascript:fn_egov_notice('${noticeResult.nttId}','${noticeResult.bbsId}');"><B><c:out value="${noticeResult.nttSj}"/></B>
								<c:if test="${noticeResult.isNowdateFlag == 'NOW'}">
								<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
								</c:if>
							</a>
						</c:when>
						<c:otherwise>
							<a href="#!"  onclick="javascript:fn_egov_notice('${noticeResult.nttId}','${noticeResult.bbsId}');"><c:out value="${noticeResult.nttSj}"/>
								<c:if test="${noticeResult.isNowdateFlag == 'NOW'}">
								<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
								</c:if>
							</a>
						</c:otherwise>
						</c:choose>
					</dd>
					</c:forEach>
					<dd class="more"><a  href="/mm/cop/bbs/BBSMSTR_000000000000/popupSelectBoardList.do"  >공지사항 더보기</a></dd>
				</dl><!-- E : notice -->
				<hr />



				<div id="customer">
					<p>041.0123.4567</p>
					<span>운영시간 : 평일 9AM ~ 6PM</span>
					<span>이메일 : <a href="mailto:e-koreatech@koreatech.ac.kr">e-koreatech@koreatech.ac.kr</a></span>
				</div>
</div>