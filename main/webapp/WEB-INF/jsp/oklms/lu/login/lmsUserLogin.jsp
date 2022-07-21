
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
<style>
#main-visual .member-area .log-type dd.item{float:left; position:relative; display:inline-block; width:175px; margin-bottom:5px; top:10px;}
#main-visual .member-area .log-type dd.btn{position:absolute; width:100px; height:61px; right:0; top:10px;}
#main-visual .member-area span{float:left; margin-top:20px;}
</style>
<script type="text/javascript" src="${contextRootJS }/js/oklms/popup.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	// 로그인 첫페이지나 호출시나 로그아웃시 아이디 입력 창으로 포커스 이동
	$("#bodyMemId").focus();
});

/**
 * browser 버전 체크
 * @returns
 */
 function browserVer(){

	var bwVer = "";
	var browserInfo = navigator.userAgent.toLowerCase();
	if( "Explorer" == browserType() ){
		bwVer = getIEVersion();
	}else if( "Chrome" == browserType() ){
		bwVer = getChromeVersion();
	}else{
		var bw = $.browser;
		if( undefined != bw ){
			bwVer = $.browser.version;
		}
	}
	return bwVer;
}

function getIEVersion() {
    var match = navigator.userAgent.match(/(?:MSIE |Trident\/.*; rv:)(\d+)/);
    return match ? parseInt(match[1]) : undefined;
}

function getChromeVersion() {
    var vMatch = navigator.userAgent.match(/(?:Chrome\/.*)(\d+)/);
    vMatch = vMatch + "";
    vMatch = vMatch.split(" ");
    return vMatch ? vMatch[0] : undefined;
}
/**
 * browser  타입 체크
 * @returns
 */
 function browserType(){
	var browserInfo = navigator.userAgent.toLowerCase();
	var browser     = browserInfo;

	if(browserInfo.indexOf('msie') != -1)    browser = "Explorer";
	if(browserInfo.indexOf('trident') != -1) browser = "Explorer";
	if(browserInfo.indexOf('safari') != -1)  browser = "Safari";
	if(browserInfo.indexOf('chrome') != -1)  browser = "Chrome";
	if(browserInfo.indexOf('opr') != -1)     browser = "Opera";
	if(browserInfo.indexOf('firefox') != -1) browser = "Firefox";
	if(browserInfo == browser)               browser = "등록되지 않은 브라우저 입니다";

	/*
	alert("Explorer : "+$.browser.msie);
	alert("safari : "  +$.browser.safari);
	alert("opera : "   +$.browser.opera);
	alert("mozilla : " +$.browser.mozilla);
	alert("chrome : "  +$.browser.chrome);
	*/
	return  browser;
}

if( "Explorer" == browserType() && 10 > browserVer() ){
	alert("Internet Explorer 10 이상 버전에 최적화 되어있으므로 업그레이드후 이용을 권장합니다.\n 브라우저 모드 : IE 10 , 문서 모드 : 표준");
}

	<%--
	function fn_loginProcBody(){

		var url = "/commbiz/login/loginProc.do";
		var $form  = $("#loginProcBodyForm");

		if($form.length < 1) {
			$form = $("<form id='loginProcBodyForm', method='post', action='"+url+"' target=''></form>");
			$(document.body).append($form);
		}
		//이전에 처리한 정보 삭제
		$form.empty();

		//정보 세팅
		$("<input></input>").attr({type:"hidden", name:'reqUri',  value:$.trim($("#reqUri").val())}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:'memId',  value:$.trim($("#bodyMemId").val())}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:'memPasswordEncript',  value:$.trim($("#bodyMemPasswordEncript").val())}).appendTo($form);
		$form.submit();
	}
	--%>
	function fn_loginProcBody(){
		var url = "/commbiz/login/loginProc.do";
		var $form  = $("#loginProcBodyForm");
		$form.attr("target", "");
		$form.attr("action", url);
		$form.submit();
	}

	function fn_loginProcSso(){
		var $form  = $("#spLoginFrm");
		$form.attr("target", "");
		$form.submit();
	}


	$(document).ready(function () {
		initEvent();
	});

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {

		// input Type이 Text인 경우 Enter Key 입력시 조회
		$("#bodyMemId").keydown(function (key){
			if(key.keyCode == "13") {
				fnAfterCheckLogin();
			}
		});

		$("#bodyMemPasswordEncript").keydown(function (key){
			if(key.keyCode == "13") {
				fnAfterCheckLogin();
			}
		});

// 		$("#loginProcBodyForm").submit(function(event){
// 			fn_loginProcBody();
// 		});
	}

	function fnAfterCheckLogin(){
		var bodyMemId = "";
		var bodyMemPasswordEncript = "";
		bodyMemId = $('#bodyMemId').val();
		bodyMemPasswordEncript = $('#bodyMemPasswordEncript').val();

		if(bodyMemId == ""){
			alert("아이디를 입력해 주세요.");
			$("#bodyMemId").focus();
			return false;
		}

		if(bodyMemPasswordEncript == ""){
			alert("패스워드를 입력해 주세요.");
			$("#bodyMemPasswordEncript").focus();
			return false;
		}


		<c:choose>
			<c:when test="${ssoYn eq 'Y'}">
				$("#user_id").val(bodyMemId);
				$("#user_password").val(bodyMemPasswordEncript);
				if($("#sso-login").is(":checked") == true){
					fn_loginProcSso();
				} else {
					fn_loginProcBody();
				}
			</c:when>
			<c:otherwise>
				fn_loginProcBody();
			</c:otherwise>
		</c:choose>
	}


	function fn_OpenPopupCert(type){

			var reqUrl = "/commbiz/member/usrCrtt.do?rtnType="+type;
			com.openPopup("usrCrtt", reqUrl, 700, 350, "CENTER");
	}

	function fnOpenPopup(type){

		switch (type) {

			/* 아이디 찾기 */
			case "idFind":
				var reqUrl = "/commbiz/member/idFind.do";
				com.openPopup("idFind", reqUrl, 400, 275, "CENTER");
				break;

			/* 비밀번호 찾기 */
			case "pwFind":
				var reqUrl = "/commbiz/member/pwFind.do";
				com.openPopup("idFind", reqUrl, 400, 300, "CENTER");
				break;

			default:
				break;
		}
	}

	//공지사항, 주요학사일정, 자묻는질문팝업 상세
	function fn_board_pop(param1, param2){
		popOpenWindow("", "popSearch", 720, 500);

		var reqUrl = "/lu/cop/bbs/"+param2+"/popupSelectBoardArticle.do?nttId="+param1;

		$("#loginProcBodyForm").attr("action", reqUrl);
		$("#loginProcBodyForm").attr("target", "popSearch");
		$("#loginProcBodyForm").submit();
	}

	//공지사항, 주요학사일정, 자묻는질문팝업 목록
	function fn_board_list_pop(param1) {
		popOpenWindow("", "popSearch", 720, 500);

		var reqUrl = "/lu/cop/bbs/"+param1+"/popupSelectBoardList.do";

		$("#loginProcBodyForm").attr("action", reqUrl);
		$("#loginProcBodyForm").attr("target", "popSearch");
		$("#loginProcBodyForm").submit();
	}

	function fn_flagPlay() {
		if($("#playstop").is(".play")) {
			$("#playstop").attr('class','stop event_popup_stop');
		}else{
			$("#playstop").attr('class','play');
		}
	}

	function fn_createAccount(){

	}

</script>

<form id="spLoginFrm" name="spLoginFrm" method="post" action="<%= this.generateUrl(IDP_URL, LOGIN_URL) %>">
    <!--
    아이디 : <input type="text" name="user_id" /><br/>
    비밀번호 : <input type="text" name="user_password" />
     -->

    <input type="hidden" id="user_id" name="user_id" />
    <input type="hidden" id="user_password" name="user_password"/>

    <input type="hidden" name="<%= RELAY_STATE_NAME %>" value="/commbiz/login/ssoLoginProc.do" />
    <input type="hidden" name="<%= ID_NAME %>" value="<%= SP_ID %>" />
    <input type="hidden" name="<%= TARGET_ID_NAME %>" value="<%= SP_ID %>" />
    <!-- <input type="submit" value="SP로그인" /> -->
</form>

<form id="loginProcBodyForm" method="post" action="/commbiz/login/loginProc.do" class="gLogin">
	<input id="reqUri" name="reqUri" type="hidden" value="${reqUri }">
	<input id="menuArea" name="menuArea" type="hidden" value="LMS">
	<ul id="main-header">
		<li class="top-area">
			<h1><a href="#!">한국기술교육대학교</a></h1>
			<p><a href="#!">즐겨찾기 추가</a><a href="#!">아이디/비번찾기</a><a href="#!">학습지원센터</a></p>
			<div>코리아텍에서 일과 학습을 동시에 하라!~</div>
		</li>
		<li class="top-title">일하고, 배우며, 함께 성장하는 <b>일학습병행교육!</b></li>
	</ul><!-- E : main-header -->
	<div id="main-visual">
		<ul>
			<form action="login-form-area" id="gLogin" class="gLogin">
			<li class="member-area">
				<div id="login" class="login-area gLogin">
						<fieldset>
							<legend>LOGIN</legend>
							<dl class="log-type">
							<!-- <dt>초기 아이디/비밀번호는 <b>학생(학번)/교수(사번)</b>입니다.</dt> -->
								<dt><b></b></dt>
								<dd class="item"><label for="uid" class="iLabel">아이디</label><input type="text" name="memId" type="text" id="bodyMemId" placeholder="아이디" class="iText uid"/></dd>
								<dd class="item"><label for="upw" class="iLabel">비밀번호</label><input type="password" name="memPasswordEncript" type="password" id="bodyMemPasswordEncript" placeholder="비밀번호" class="ipass upw"/></dd>
								<dd class="btn"><input name="login-btn" type="button" value="로그인" onclick="javascript:fnAfterCheckLogin();return false;"/></dd>
							</dl>
							<span>
								<label for="id-save" class="save-area"><input name="checkbox" type="checkbox" id="id-save" />아이디 저장</label>
								<label for="sso-login" class="save-area"><input name="checkbox" ${ssoYn eq 'Y' ? 'checked' : ''}  type="checkbox" id="sso-login" />통합 로그인</label>
								<!-- <a href="#!" class="btn-find">비밀번호 찾기</a> -->
								<!-- <a href="#" onclick="fn_createAccount();" class="btn-find">계정 생성</a> -->
							</span>

							<!-- <dl class="info-area">
								<dt>회원정보</dt>
								<dd class="info-photo"><img src="../../image/std/upload/user_photo_161227_0001.png" alt="" /></dd>
								<dd class="info-text"><b>홍길동홍길동홍길동홍길동홍길동홍길동홍길동홍길동홍길동홍길동</b> 님 OK-LMS에 오신것을 환영합니다.</dd>
								<dd class="info-btn"><a href="#!" class="btn-mypage">마이페이지</a><a href="#!" class="btn-logout">로그아웃</a></dd>
							</dl> -->

						</fieldset>

				</div><!-- E : login-area -->

			</li><!-- E : member-area -->
			</form>
			<li class="popup-area">
				<h2>POPUP ZONE</h2>
				<%--
				<div class="pop-img">
					<c:if test="${fn:length(popupzoneResultList) != 0}">
						<c:forEach var="bannerResult" items="${popupzoneResultList}" varStatus="status">
						<c:if test="${bannerResult.sortOrdr == 1}">
							<p class="popupitem" style="display:block;"><a href="${bannerResult.linkUrl}" target="_blank"  title="새 창으로 이동" ><img src="/cmm/fms/getImage.do?atchFileId=${bannerResult.bannerImageFile}" alt="배너${status.count}"  /></a></p>
						</c:if>
						<c:if test="${bannerResult.sortOrdr != 1}">
							<p class="popupitem"><a href="${bannerResult.linkUrl}" target="_blank"  title="새 창으로 이동" ><img src="/cmm/fms/getImage.do?atchFileId=${bannerResult.bannerImageFile}" alt="배너${status.count}" /></a></p>
						</c:if>

						</c:forEach>
					</c:if>
				</div>
				--%>
				<div class="pop-img">
					<p class="popupitem" style="display:block;"><img src="/images/oklms/std/upload/main_banner_01.png" alt="배너1" /></p>
					<p class="popupitem"><img src="/images/oklms/std/upload/main_banner_02.png" alt="배너2" /></p>
					<p class="popupitem"><img src="/images/oklms/std/upload/main_banner_03.png" alt="배너3" /></p>
				</div>
				<div class="pop-btn">
					<span class="prev event_popup_prev"><a href="#!" title="이전">이전</a></span>
					<span id="playstop" class="stop event_popup_stop"><a href="javascript:fn_flagPlay();" title="정지">정지/재생</a></span>
					<!-- 정지 클릭하였을 때 class="play" 로 교체 -->
					<span class="next event_popup_next"><a href="#!" title="다음">다음</a></span>
				</div>
			</li><!-- E : popup-area -->



			<li class="banner-area-1"><a href="#!">이용자매뉴얼</a></li>
			<li class="banner-area-2"><a href="#!" onclick="fn_board_list_pop('BBSMSTR_000000000010');">자주묻는질문</a></li>
		</ul>

		<div id="mvisual-wrap">
			<div class="ctrl-btns">
				<a href="#!" class="ctrl-btn btn-prev" title="이전">이전</a>
				<a href="#!" class="ctrl-btn btn-next" title="다음">다음</a>
				<div class="cnums-wrap">
					<%-- <div class="ctrl-nums">
						<c:forEach var="a" begin="1" end="${fn:length(mainResultList)}" step="1">
						<a href="#visImg${a}"><span>${a}</span></a>
					    </c:forEach>
					</div> --%>
					<div class="ctrl-nums"><a href="#visImg1"><span>1</span></a><a href="#visImg2"><span>2</span></a><a href="#visImg3"><span>3</span></a></div>
					<a href="#!" class="ctrl-btn btn-play"><span>재생</span></a>
					<a href="#!" class="ctrl-btn btn-stop"><span>정지</span></a>
				</div>
			</div>
			<div id="mvisual">
				<div class='visImgEffectWrap'>
					<%-- <c:if test="${fn:length(mainResultList) != 0}">
						<c:forEach var="mainResult" items="${mainResultList}" varStatus="status">
						<div class='visImgEffect'><div class="visImg"><img src="/cmm/fms/getImage.do?atchFileId=${mainResult.bannerImageFile}" alt="New Start! 일과 학습을 동시에, 취업난도 해결하고 기업이 원하는 맞춤형 인력을 양성할 수 있는 제도" /></div></div>
						</c:forEach>
					</c:if> --%>
					<div class='visImgEffect'><div class="visImg"><img src="/images/oklms/std/main/main_visual1.png" alt="New Start! 일과 학습을 동시에, 취업난도 해결하고 기업이 원하는 맞춤형 인력을 양성할 수 있는 제도" /></div></div>
					<div class='visImgEffect'><div class="visImg"><img src="/images/oklms/std/main/main_visual2.png" alt="New Start! 일과 학습을 동시에, 취업난도 해결하고 기업이 원하는 맞춤형 인력을 양성할 수 있는 제도" /></div></div>
					<div class='visImgEffect'><div class="visImg"><img src="/images/oklms/std/main/main_visual3.png" alt="New Start! 일과 학습을 동시에, 취업난도 해결하고 기업이 원하는 맞춤형 인력을 양성할 수 있는 제도" /></div></div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			var mVisual = new mainVisualEffect({obj:$("#mvisual-wrap"), contents:'.visImgEffect',prevBtn:$("#mvisual-wrap .btn-prev"),nextBtn:$("#mvisual-wrap .btn-next"), isPlay:true});
		</script>
	</div><!-- E : main-visual -->
	<div id="main-container">

		<div id="main-content">
			<dl class="notice">
				<dt>공지사항</dt>
				<c:if test="${fn:length(noticeResultList) == 0}">
					<dd>등록된 공지사항이 없습니다.</dd>
				</c:if>
				<c:forEach var="noticeResult" items="${noticeResultList}" varStatus="status">
				<dd>
					<c:choose>
					<c:when test="${'Y' == noticeResult.topNoticeYn}">
						<a href="#!"  onclick="fn_board_pop('${noticeResult.nttId}','${noticeResult.bbsId}');"><B><c:out value="${noticeResult.nttSj}"/></B>
							<c:if test="${noticeResult.isNowdateFlag == 'NOW'}">
							<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
							</c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a href="#!"  onclick="fn_board_pop('${noticeResult.nttId}','${noticeResult.bbsId}');"><c:out value="${noticeResult.nttSj}"/>
							<c:if test="${noticeResult.isNowdateFlag == 'NOW'}">
							<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
							</c:if>
						</a>
					</c:otherwise>
					</c:choose>
				</dd>
				</c:forEach>
				<dd class="more"><a href="#!" onclick="fn_board_list_pop('BBSMSTR_000000000000');" title="더 보기">공지사항 더 보기</a></dd>
			</dl><!-- E : notice -->

			<dl class="schedule">
				<dt>주요학사일정</dt>
				<c:if test="${fn:length(academicCalendarResultList) == 0}">
					<dd>등록된 주요학사일정이 없습니다.</dd>
				</c:if>
				<c:forEach var="academicCalendarResult" items="${academicCalendarResultList}" varStatus="status">
				<dd>
					<c:choose>
					<c:when test="${'Y' == academicCalendarResult.topNoticeYn}">
						<a href="#!"  onclick="fn_board_pop('${academicCalendarResult.nttId}','${academicCalendarResult.bbsId}');"><span>[${academicCalendarResult.frstRegisterPnttm}]</span><B><c:out value="${academicCalendarResult.nttSj}"/></B>
							<c:if test="${academicCalendarResult.isNowdateFlag == 'NOW'}">
							<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
							</c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a href="#!"  onclick="fn_board_pop('${academicCalendarResult.nttId}','${academicCalendarResult.bbsId}');"><span>[${academicCalendarResult.frstRegisterPnttm}]</span><c:out value="${academicCalendarResult.nttSj}"/>
							<c:if test="${academicCalendarResult.isNowdateFlag == 'NOW'}">
							<img src="/images/oklms/std/main/icon_news.png" alt="새글" />
							</c:if>
						</a>
					</c:otherwise>
					</c:choose>
				</dd>
				</c:forEach>
				<dd class="more"><a href="#!" onclick="fn_board_list_pop('BBSMSTR_000000000004');" title="더 보기">주요학사일정 더 보기</a></dd>
			</dl><!-- E : schedule -->

			<dl class="customer">
				<dt>시스템 사용문의</dt>
				<dd class="number">041.560.1114</dd>
				<dd>운영시간 : 평일 9AM - 6PM</dd>
				<dd class="mail"><span>이메</span>일 : <a href="mailto:e-koreatech@koreatech.ac.kr" title="메일보내기">e-koreatech@koreatech.ac.kr</a></dd>
			</dl><!-- E : customer -->
		</div><!-- E : main-content -->

	</div><!-- E : main-container -->

	<div id="main-link">
		<ul>
			<li>
				<a href="http://email.koreatech.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut1.png" alt="웹메일" /></a>
				<!-- <a href="http://el.koreatech.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut2.png" alt="온라인교육" /></a> -->
				<a href="https://uni.webminwon.com" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut3.png" alt="제증명" /></a>
				<a href="http://help.kut.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut4.png" alt="원격지원" /></a>
				<a href="http://dasan.koreatech.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut5.png" alt="다산정보관" /></a>
				<a href="http://knw.koreatech.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_shortcut6.png" alt="웹디스크" /></a>
			</li>
			<li class="main_site">
				<a href="http://www.moel.go.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_site1.png" alt="고용노동부" /></a>
				<a href="http://www.koreatech.ac.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_site2.png" alt="한국기술교육대학교" /></a>
				<a href="http://www.hrd.go.kr" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_site3.png" alt="통합 HRD-Net" /></a>

				<!-- 20170407 배너추가 -->
				<a href="http://www.bizhrd.net" target="_blank" title="새창열림"><img src="/images/oklms/std/main/main_site4.png" alt="기업일학습" /></a>
			</li>
		</ul>
	</div><!-- E : main-link -->

</form>


