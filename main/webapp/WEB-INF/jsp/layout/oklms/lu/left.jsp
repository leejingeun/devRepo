<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- ############### LA Left ############### -->
<ul id="lnb-area">
	<li id="info-area">
		<dl>
			<dt>회원정보</dt>
			<dd class="info-photo"><img src="/commbiz/photo/getAunuriUserImage.do?memId=${loginMemId}" style="width: 60px; height: 50px;" onerror="this.src='/images/oklms/nonimg.gif'" alt="" /></dd>
			<dd class="info-text"><b>${loginMemName} ${loginAuthGroupName}님</b>환영합니다.</dd>
			<dd class="info-btn"><a href="/lu/main/lmsUserMainPage.do" class="btn-mypage">마이페이지</a><a href="/commbiz/login/logout.do" class="btn-logout">로그아웃</a>
				<c:if test="${loginAuthGroupId eq '2016AUTH0000008'}">
					<br/>
					<p style="border-top:1px solid #DDD; padding-top:20px; margin-top:20px;"><c:if test="${loginAuthGroupId eq '2016AUTH0000008'}">교직원번호 : <b style="font-size:18px; color:#f76000">${loginMemId}</b></p></c:if><a href="javascript:popCreateAccount();" class="btn-logout" style="float:left; width:205px;">계정생성</a>
				</c:if>
			</dd>
		</dl>
	</li><!-- E : lnb-area -->
 
	<c:set var="upperMenuNo" value="TOP"/>
	<c:set var="menuLevel" value="1"/>
	<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>
	<input type="hidden" name="keyParam" value="${ upperMenuNo}_${menuLevel}"/>
	
	<li id="lnb">
	
	<!-- S : depth-1 [기업현장교사, 센터전담자, 학과전담자용 공통 메뉴] -->
	<c:if test="${loginAuthGroupId eq '2016AUTH0000005' || loginAuthGroupId eq '2016AUTH0000006' || loginAuthGroupId eq '2016AUTH0000008'}">
	
	<!-- S : depth-1 [센터전담자, 학과전담자, HRD전담자용 공통 메뉴] -->
	<c:if test="${loginAuthGroupId eq '2016AUTH0000005' || loginAuthGroupId eq '2016AUTH0000006' || loginAuthGroupId eq '2016AUTH0000004'}">
	<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
		<!-- S : depth-1 [센터전담자용 공통 메뉴] -->
		<c:if test="${menu1.menuId eq'2017MENU0000443'}">
		<dl>
			<dt>
				<a id="a_${menu1.menuId }" class="today" href="#" onclick="javascript:fn_menu_display('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }','N');">${menu1.menuTitle }</a>
			</dt>
		</dl><!-- E : depth-1 -->
		</c:if>
		<!-- S : depth-1 [학과전담자용 공통 메뉴] -->
		<c:if test="${menu1.menuId eq'2016MENU0000477' || menu1.menuId eq'2017MENU0000560'}">
		<dl>
			<dt>
				<a id="a_${menu1.menuId }" class="today" href="#" onclick="javascript:fn_menu_today_display('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }');">${menu1.menuTitle }</a>
			</dt>
		</dl><!-- E : depth-1 -->
		</c:if>
	</c:forEach>
	</c:if>
	
	<!-- S : depth-1 [센터전담자는 진행중인 개설교과 메뉴 제외] -->
	<c:if test="${loginAuthGroupId != '2016AUTH0000005'}">
	<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
		<!-- S : depth-1 [기업현장교사 진행중인 개설교과 메뉴] -->
		<c:if test="${menu1.menuId eq '2017MENU0000528'}">
		<dl>
			<dt>
				<a href="#!" class="course-title" name="menuChoice">${menu1.menuTitle }</a>
			</dt>
			<dd  ${empty SESSION_SUBJECT_CODE ? 'style=display:none;' : 'style=display:block;' }>
				<ul class="course-list">
					<li class="course-menu-3" style="display:block;">
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
							<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode and SESSION_CLASS eq menuProc.subClass}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');">${menuProc.subjectName} ${menuProc.subClass}반<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
						</c:forEach>
					</li>
				</ul><!-- E : depth-1 -->
			</dd>
		</dl><!-- E : depth-1 -->
		</c:if>
	</c:forEach>
	</c:if>
	<!-- S : depth-1 [센터전담자는 진행중인 개설교과 메뉴 제외] -->
	<c:if test="${loginAuthGroupId != '2016AUTH0000005'}">
	<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
		<!-- S : depth-1 [학과전담자 진행중인 개설교과 메뉴] -->
		<c:if test="${menu1.menuId eq'2017MENU0000530'}">
		<dl>
			<dt>
				<a href="#" class="course-title" >${menu1.menuTitle }</a>
			</dt>
			<dd ${empty param.subjectCode ? 'style=display:none;' : 'style=display:block;' }>
				<ul class="course-list">
					<li class="course-menu-3" style="display:block;">
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode and SESSION_CLASS eq menuProc.subClass}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');"><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
						</c:forEach>
					</li>
				</ul><!-- E : depth-1 -->
			</dd>
		</dl><!-- E : depth-1 -->
		</c:if>
	</c:forEach>
	</c:if>
	
	</c:if>
	<!-- E : depth-1 [기업현장교사, 센터전담자, 학과전담자용 공통 메뉴] -->
	
	<!-- S : depth-1 [학습근로자, 담당교사 공통 메뉴] -->
	<div class="lecMenuMarkYn-1" style="display:none;">
		<dl>
			<dt><a href="#!" class="course-title" name="menuChoice">진행중인 개설교과</a></dt>
			<dd id="course-dd">
				<ul class="course-list">
					<li class="course-tab-1 on"><a href="javascript:lnbTab1();">Off-JT</a></li>
					<li class="course-menu-1" style="display:block;">
						<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
							<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode and SESSION_CLASS eq menuProc.subClass}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');"><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
						</c:forEach>
					</li>

					<li class="course-tab-2"><a href="javascript:lnbTab2();">OJT</a></li>
					<li class="course-menu-2">
						<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
						     <c:choose>
						     	<c:when test="${loginAuthGroupId eq '2016AUTH0000002'}">
						     		<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode and SESSION_CLASS eq menuProc.subClass}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');">${menuProc.subjectName} ${menuProc.subClass}반</a>
						     	</c:when>
						     	<c:otherwise>
						     	  	<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');">${menuProc.subjectName}<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
						     	</c:otherwise>
						     </c:choose>
							
						</c:forEach>
					</li>
				</ul><!-- E : depth-1 -->
			</dd>
		</dl><!-- E : depth-1 -->
	</div>
	
	<!-- <div id="lecMenuMarkYn" style="display:none;"></div> -->
	<c:if test="${not empty menuList[key1]}">
		<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
		<c:set var="key2" value="${ menu1.menuId}_${menu1.menuLevel+1}"/>
			
			<div class="lecMenuMarkYn-${menu1.lectureMenuMarkYn}" style="display:none;">
				<c:if test="${menu1.menuId != '2017MENU0000528'}"> 
				<dl>
					<c:if test="${menu1.menuId != '2017MENU0000443' && menu1.menuId != '2016MENU0000477'}">
					<c:choose>
						<c:when test="${menu1.menuId == '2016MENU0000063' || menu1.menuId == '2017MENU0000527'}"> <!-- S : depth-1 [진행중인 개설교과 메뉴] -->
						<dt id="dt_${menu1.menuId }">
							<a id="a_${menu1.menuId }" href="#" >${menu1.menuTitle}</a>
						</dt>
						</c:when>
						<c:otherwise> <!-- S : depth-1 [일반 공통 메뉴] -->
							<c:if test="${menu1.menuId != '2017MENU0000530'}">
							<dt id="dt_${menu1.menuId }">
								<c:choose>
									<c:when test="${0 eq menu1.isLeafMenu}">
										<a id="a_${menu1.menuId }" name="menuChoice"  href="#" >${menu1.menuTitle}</a>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${SESSION_SUBJECT_TRANING_TYPE ne 'OJT' && menu1.menuId ne '2016MENU0000391'}"> <!-- OJT 아닐 경우(Off-jt / 마이페이지)에만 과제,팀프로젝트,토론 노출 -->
												
												<a id="a_${menu1.menuId }" <c:if test="${ menu1.menuTitle  eq 'TODAY'}">  class="today" </c:if> href="#" onclick="javascript:fn_menu_display('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }' , '${menu1.lectureMenuMarkYn }');">${menu1.menuTitle }</a>
												<c:if test="${menu1.menuId eq '2017MENU0000444' and loginAuthGroupId eq '2016AUTH0000005'}">
												<div class="company">
													<input type="text" name="companyName" id="companyName" maxlength="40" value="${param.searchCompanyName}" placeholder="" /><a href="#!" onclick="fn_comp_search();">검색</a>
												</div>
												</c:if>												
											</c:when>
											<c:otherwise>
												<c:if test="${hSkillCnt ne '0' && menu1.menuId eq '2016MENU0000391'}"> <!-- OJT and 고숙련과정담당 and 면담일지 메뉴일경우 -->
													<a id="a_${menu1.menuId }" href="#" onclick="javascript:fn_menu_display('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }' , '${menu1.lectureMenuMarkYn }');">${menu1.menuTitle }</a>
												</c:if>
											</c:otherwise>
										</c:choose>
										
									</c:otherwise>
								</c:choose>
							</dt>
							</c:if>
						</c:otherwise>
					</c:choose>
					</c:if>
					<!-- S : depth-1 [진행중인 개설교과] 학습근로자, 담당교수 -->
					<c:if test="${menu1.menuId == '2016MENU0000063' || menu1.menuId == '2017MENU0000527'}">
					<dd id="course-dd">
						<ul class="course-list">
							<li class="course-tab-1 on"><a href="javascript:lnbTab1();">Off-JT</a></li>
							<li class="course-menu-1" style="display:block;">
								<c:forEach var="menuProc" items="${listOffJtAunuriSubject}" varStatus="statusProc">
									<a href="#"<c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode and SESSION_CLASS eq menuProc.subClass}">class="current"</c:if> onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');"><c:if test="${menuProc.onlineType == 'ONLINE'}">[${menuProc.onlineType}]</c:if> ${menuProc.subjectName} ${menuProc.subClass}반<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
								</c:forEach>
							</li>
	
							<li class="course-tab-2"><a href="javascript:lnbTab2();">OJT</a></li>
							<li class="course-menu-2">
								<c:forEach var="menuProc" items="${listOjtAunuriSubject}" varStatus="statusProc">
									<a href="#" <c:if test="${empty SESSION_PRE_SUBJECT_CODE and SESSION_YYYY eq menuProc.year and SESSION_TERM eq menuProc.term and SESSION_SUBJECT_CODE eq menuProc.subjectCode }">class="current"</c:if>  onclick="javascript:fn_lec_menu_display('${menuProc.subjectTraningType}','${menuProc.year}','${menuProc.term}','${menuProc.subjectCode}','${menuProc.subClass}','${menuProc.subjectName}','${menuProc.subjectType}','${menuProc.onlineType}');">${menuProc.subjectName}<c:if test="${loginAuthGroupId ne '2016AUTH0000002'}">(${menuProc.stuCnt}명)</c:if></a>
								</c:forEach>
							</li>
						</ul><!-- E : depth-1 -->
					</dd>
					</c:if>
					<!-- S : depth-2 -->
					<c:if test="${not empty menuList[key2]}">
						<dd id="dd_${menu1.menuId }">
							<c:forEach var="menu2" items="${menuList[key2]}" varStatus="status2">
							<dl class="depth-2">
								<dt id="dt_${menu2.menuId }">
									<c:choose>
										<c:when test="${menu2.menuId eq '2017MENU0000480'}"> <!-- 온라인 훈련관리 메뉴일경우 -->
											<c:if test="${SESSION_ONLINE_TYPE ne 'NONE'}"> <!-- 온라인 훈련에 해당하는 과정만 해당 메뉴노출 -->
												<a id="a_${menu2.menuId }" href="#" onclick="javascript:fn_menu_display('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , '${menu1.lectureMenuMarkYn }');">${menu2.menuTitle }</a>
											</c:if>
										</c:when>
										<c:otherwise>
											<a id="a_${menu2.menuId }" href="#" onclick="javascript:fn_menu_display('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , '${menu1.lectureMenuMarkYn }');">${menu2.menuTitle }</a>
										</c:otherwise>
									</c:choose>
								</dt>
							</dl><!-- E : depth-2 -->
							</c:forEach><!-- E : depth-2 [c:forEach] -->
						</dd>
					</c:if>
				</dl><!-- E : depth-1 -->
				</c:if>
			</div>
				
			</c:forEach><!-- E : depth-1 [c:forEach] -->
		</c:if>
		
	</li>
 	
 	<form id="frmCompany" name="frmCompany" method="post">
 		<input type="hidden" id="searchCompanyName" name="searchCompanyName" />
 		<input type="hidden" id="searchStatusType" name="searchStatusType" value="COMP" />
 		
 	</form>
 
	<li id="customer">
		<p>041.560.1114</p>
		운영시간 : 평일 9AM - 6PM<br />
		<span>이메</span>일 : <a href="mailto:e-koreatech@koreatech.ac.kr" title="메일보내기">koreatech@koreatech.ac.kr</a>
	</li><!-- E : customer -->
 
 	
 
	<li id="site-info">
		<div class="dropdown">
			<a href="#!" class="dropdown-toggle">교내주요기관사이트</a>
			<div class="dropdown-site">
				<a href="#!" target="_blank" title="새창열림">관련 사이트 1</a>
				<a href="#!" target="_blank" title="새창열림">관련 사이트 2</a>
			</div>
		</div>
 
		(31253) 충청남도 천안시 동남구 병천면<br />
		충절로 1600 (가전리, 한국기술교육대학교)<br />
		COPYRIGHT KOREA UNIVERSITY OF<br />
		TECHNOLOGY AND EDUCATION.<br />
		ALL RIGHTS RESERVED.
	</li><!-- E : site-info -->
	
	
</ul><!-- E : lnb-area -->
<script type="text/javascript">

// $("#a_${menuId }").css('background', '#1b77d3 url("/images/lnb_bg.png") no-repeat right top');
// $("#a_${menuId }").css('color', '#FFF');
 
// $("#a_${menuId }").css('color', '#1b77d3');
// $("#a_${menuId }").css('background', 'white');
 
// $("#a_${menuId }").parent().addClass("on");

function fn_comp_search(){
	if($("#companyName").val() == ""){
		alert("검색어를 입력해주세요.");
		$("#companyName").focus();
		return;
	}
	$("#searchCompanyName").val($("#companyName").val());
	
	var reqUrl = "/lu/subject/listCompanyCcn.do";
	$("#frmCompany").attr("action", reqUrl);
	$("#frmCompany").submit();
	
}

function popCreateAccount() {
	
	var reqUrl = CONTEXT_ROOT + "/lu/main/lmsUserMainPageUpdate.json";
	var param = $("#frmLesson").serializeArray();
	com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){			
	if (200 == jqXHR.status ) {
	} else {
	}
	}, {
		async : true,
		type : "POST",
		errorCallback : null
	});
		
	
	var url = "https://portal.koreatech.ac.kr/kut/page/createAccount.jsp";
	
	var width = "700";
	var height= "860";
	var targetNm = "_ptlcreateuser";
	var parentX = window.screenTop;
	var parentY = window.screenLeft;

	if(parentX == null) {
		parentX = window.screenX;
	}

	if(parentY == null) {
		parentY = window.screenY;
	}
	
	var option = 'width=' + width + ',top=' + (parentX + 100) + ',left=' + (parentY + 100) + ',height=' + height + ',scrollbars=no,resizable=no';
	
	var popup = window.open(url, targetNm, option);
	popup.focus();
	
}


var loginAuthGroupId = "";

function fn_subPageMove(menuArea, rootMenuId, menuId, isPopUp, lecMenuMarkYn){	
	
	// 메뉴 링크시 사용됨.
	if( null != document.getElementById("menuArea") ){
		document.getElementById("menuArea").setAttribute("value", "");
	}
	
	if( null != document.getElementById("rootMenuId") ){
		document.getElementById("rootMenuId").setAttribute("value", "");
	}
	
	if( null != document.getElementById("menuNo") ){
		document.getElementById("menuNo").setAttribute("value", "");
	}
	
	if( null != document.getElementById("isPopUp")){
		document.getElementById("isPopUp").setAttribute("value", "");
	}
	
	if( null != document.getElementById("lecMenuMarkYn")){
		document.getElementById("lecMenuMarkYn").setAttribute("value", "");
	}
	
	targetURL = "/commbiz/menu/menuMove.do";

	/* alert("targetURL=" + targetURL);
	alert("menuArea="+menuArea);
	alert("rootMenuId="+rootMenuId);
	alert("menuId="+menuId);
	alert("isPopUp="+isPopUp);
	alert("lecMenuMarkYn2="+lecMenuMarkYn); */
	
	//동적으로 생성할 form 생성
	var $form  = $("#subPageMoveFrm");			
		
	if($form.length < 1) {
		$form = $("<form id='subPageMoveFrm', method='post', action='"+targetURL+"' target=''></form>");
		$(document.body).append($form);
	}

	$("<input></input>").attr({type:"hidden", name:"menuArea",  value:$.trim(menuArea)}).appendTo($form);
	$("<input></input>").attr({type:"hidden", name:"rootMenuId",  value:$.trim(rootMenuId)}).appendTo($form);
	$("<input></input>").attr({type:"hidden", name:"menuId",  value:$.trim(menuId)}).appendTo($form);
	$("<input></input>").attr({type:"hidden", name:"isPopUp",  value:$.trim(isPopUp)}).appendTo($form);
	$("<input></input>").attr({type:"hidden", name:"lecMenuMarkYn",  value:$.trim(lecMenuMarkYn)}).appendTo($form);

	if (isPopUp) {
		
		var strUrl="";
		var strTitle="openTarger";
		var scrollbars=true;
		var resizable=true;
		var intWidth=800;
		var intHeight=800;
		var w = (screen.availWidth - intWidth) / 2;
		var h = (screen.availHeight - intHeight) / 2;

		var features = "toolbar=no,location=no,status=yes,menubar=no,scrollbars=" + scrollbars + ",resizable=" + resizable + ",width=" + intWidth + ",height=" + intHeight + ",top=" + h + ",left=" + w;

		newWin = window.open(strUrl, strTitle, features);
		newWin.window.focus();
		
		formObject.setAttribute("target", "openTarger");

		$form.attr({target:"openTarger"});
		$form.submit();
	} else {
		$form.submit();
	}
}

function fn_menu_today_display(menuArea, rootMenuId, menuId){	
	fn_subPageMove(menuArea, rootMenuId, menuId , false, 'N');
}

function fn_menu_display(menuArea, rootMenuId, menuId, lecMenuMarkYn){
	fn_subPageMove(menuArea, rootMenuId, menuId , false, lecMenuMarkYn);	
}


function fn_lec_menu_display(subjectTraningType, year, term, subjectCode, subClass, subjectName, subjectType, onlineType){
	subjectName = encodeURIComponent(subjectName);
	location.href = "/lu/currproc/listCurrProc.do?subjectTraningType="+subjectTraningType+"&year="+year+"&term="+term+"&subjectCode="+subjectCode+"&subjectName="+subjectName+"&subClass="+subClass+"&lecMenuMarkYn=Y&subjectType="+subjectType+"&onlineType="+onlineType;
	/* <a id="a_${menu1.menuId }" href="/lu/currproc/listCurrProc.do?year=${menuProc.year}&term=${menuProc.term}&subjectCode=${menuProc.subjectCode}&subClass=${menuProc.subClass}" >${menuProc.subjectName} ${menuProc.subClass}반</a> */
}

$(function() {
	<c:if test="${empty SESSION_PRE_SUBJECT_CODE and !empty SESSION_YYYY and !empty SESSION_TERM and !empty SESSION_SUBJECT_CODE }">
		$('#course-dd').slideToggle();
		<c:choose>
			<c:when test="${SESSION_SUBJECT_TRANING_TYPE eq 'OFF'}">lnbTab1();</c:when>
			<c:otherwise>lnbTab2();</c:otherwise>
		</c:choose>
	</c:if>
	
	/*
	  2016AUTH0000002 학습근로자	
	  2016AUTH0000003 담당교수	
	  2016AUTH0000008 기업현장교사
	  2016AUTH0000004 HRD전담자
	  2016AUTH0000005 센터전담자
	  2016AUTH0000006 학과전담자
	*/
	loginAuthGroupId = '${loginAuthGroupId}';
	
	if('2016AUTH0000002' == loginAuthGroupId){
		$("#a_2016MENU0000063").addClass('course-title');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} else if('2016AUTH0000003' == loginAuthGroupId){
		$("#a_2017MENU0000527").addClass('course-title');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} else if('2016AUTH0000008' == loginAuthGroupId){
		$("#a_2017MENU0000528").addClass('course-title');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} else if('2016AUTH0000006' == loginAuthGroupId){
		$("#a_2017MENU0000530").addClass('course-title');
		$("#a_2016MENU0000477").addClass('today');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} else if('2016AUTH0000005' == loginAuthGroupId){ 
		$("#a_2017MENU0000443").addClass('today');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} else {
		$("#a_2017MENU0000560").addClass('today');
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	}
	
	var lecMenuMarkYn = "";
	lecMenuMarkYn = "<%=(String)session.getAttribute("lecMenuMarkYn")%>";
	
	//alert("session.lecMenuMarkYn="+lecMenuMarkYn);
	
	if(lecMenuMarkYn == 'N'){
		$(".lecMenuMarkYn-Y").hide();
		$(".lecMenuMarkYn-N").show();
	} 
	if(lecMenuMarkYn == 'Y'){
		$(".lecMenuMarkYn-Y").show();
		$(".lecMenuMarkYn-N").hide();
		
		if('2016AUTH0000008' != loginAuthGroupId && '2016AUTH0000006' != loginAuthGroupId){
			$(".lecMenuMarkYn-1").show();
		}
	}
	
});
 
 
<c:set var="leftMenuIdPathList" value="${fn:split(menuIdPathLeafNode, '@')}" />
 
<c:forEach var="menuPath" items="${leftMenuIdPathList }" varStatus="status">
$("#dt_${leftMenuIdPathList[status.index]}").addClass('on');
$("#dd_${leftMenuIdPathList[status.index]}").addClass('on');
// 			$("#a_dt_${leftMenuIdPathList[status.index]}").addClass('on');
</c:forEach>
 
 
// $("#dt_2009MENU0000002").addClass('on');
// $("#dd_2009MENU0000002").addClass('on');
// $("#a_2009MENU0000002").addClass('on');
 
// $("#dt_2009MENU0000004").addClass('on');
// $("#dd_2009MENU0000004").addClass('on');
// $("#a_2009MENU0000004").addClass('on');
</script>
		
<!-- ############### // LA Left ############### -->