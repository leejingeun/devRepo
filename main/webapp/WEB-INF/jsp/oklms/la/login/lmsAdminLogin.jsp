<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript">
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


</script>
<script>
	<%--
	function fn_loginProc(){
	
		var url = "/commbiz/login/loginProc.do"; 
		var $form  = $("#loginProcForm");			
		
		if($form.length < 1) {
			$form = $("<form id='loginProcForm', method='post', action='"+url+"' target=''></form>");
			$(document.body).append($form);
		}
		//이전에 처리한 정보 삭제
		$form.empty();
			
		//정보 세팅
		$("<input></input>").attr({type:"hidden", name:'reqUri',  value:$.trim($("#reqUri").val())}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:'memId',  value:$.trim($("#memId").val())}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:'memPasswordEncript',  value:$.trim($("#memPasswordEncript").val())}).appendTo($form);
		$form.submit();
	}	
	--%>
	function fn_loginProc(){
		
		
		var url = "/commbiz/login/loginProc.do"; 
		var $form  = $("#loginProcForm");			
		
		$form.submit();
	}
	

	$(document).ready(function () {
		initEvent();
	});
	
	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
		
		// input Type이 Text인 경우 Enter Key 입력시 조회 
		$("#memId").keydown(function (key){
			if(key.keyCode == "13") {
				fnAfterCheckLogin();
			}
		});
		
		$("#memPasswordEncript").keydown(function (key){
			if(key.keyCode == "13") {
				fnAfterCheckLogin();
			}
		});
	}
	
	function fnAfterCheckLogin(){

		if( com.isBlank($('#memId').val())){
			com.alert("아이디를 입력해 주세요.");
			$("#memId").focus();
			return;
		} 
		
		if(com.isBlank($('#memPasswordEncript').val())){
			com.alert("패스워드를 입력해 주세요.");
			$("#memPasswordEncript").focus();
			return;
		}
		
		fn_loginProc();
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
	
		
</script>
		<div id="wrapper">
			<dl>
				<dt>
					<ul>
						<li class="type-01">학습관리통합시스템 : 온라인 교육 시스템</li>
						<li class="type-02">ADMINISTRATOR</li>
					</ul>
				</dt>

				<dd class="log-area-1">
					<div id="login" class="gLogin">
						<form action="/commbiz/login/loginProc.do" id="loginProcForm" method="post" class="gLogin">
							<input id="reqUri" name="reqUri" type="hidden" value="${reqUri }">
							<input id="menuArea" name="menuArea" type="hidden" value="LMS">
							<fieldset>
								<legend>회원 로그인</legend>
								<ul>
									<li class="item"><label for="uid" class="iLabel">아이디</label><input name="memId" type="text" value="sitglobaladmin" id="memId" class="iText uid" /></li>
									<li class="item"><label for="upw" class="iLabel">비밀번호</label><input name="memPasswordEncript" type="password" value="1111" id="memPasswordEncript" class="iText upw" /></li>
								</ul>
								<span class="btn"><input type="submit" name="" value="LOGIN" /></span>
							</fieldset>
						</form>
					</div>
				</dd>

				<dd class="log-area-2" style="display:none;">
					<span class="text">- 로그아웃 후 재접속 해주시기 바랍니다. -</span>
					<span class="btn"><input type="submit" name="" value="LOGOUT" /></span>
				</dd>

				<dd class="footer">COPYRIGHT ⓒ 2015. ALL RIGHTS RESERVED.</dd>
			</dl>
		</div><!-- wrapper -->
<%--
		<div id="wrapper">

			<dl>
				<dt>
					<ul>
						<li class="type-01">학습관리통합시스템 : 온라인 교육 시스템</li>
						<li class="type-02">ADMINISTRATOR</li>
					</ul>
				</dt>

				<dd class="log-area-1">
					<div id="login" class="gLogin">
						<form action="" id="gLogin" class="gLogin">
							<fieldset>
								<legend>회원 로그인</legend>
								<ul>
									<li class="item"><label for="uid" class="iLabel">아이디</label><input name="" type="text" value="" id="uid" class="iText uid" /></li>
									<li class="item"><label for="upw" class="iLabel">비밀번호</label><input name="" type="password" value="" id="upw" class="iText upw" /></li>
								</ul>
								<span class="btn"><input type="submit" name="" value="LOGIN" /></span>
							</fieldset>
						</form>
					</div>
				</dd>

				<dd class="log-area-2" style="display:none;">
					<span class="text">- 로그아웃 후 재접속 해주시기 바랍니다. -</span>
					<span class="btn"><input type="submit" name="" value="LOGOUT" /></span>
				</dd>

				<dd class="footer">COPYRIGHT ⓒ 2015. ALL RIGHTS RESERVED.</dd>
			</dl>

		</div><!-- wrapper -->

 --%>