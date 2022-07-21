/**
 * Class Name : common.js 
 * Description : 사이트에서 사용되는 공통기능 제공
 * Modification Information
 *
 *            수정일                     수정자                   수정내용
 *   ----------------------------------------------- *   
 *   author   : 공통 이진근
 *   since    : 2016.11.21 
 */
//var vConsole = window.cosole || { log:function(){}};
var vConsole;
if (typeof console == "undefined") {
	//window.console = {log: function() {}};
	vConsole = window.console || { log:function(){}};
}else{
	vConsole = window.console; 
}
var isLiveMode = false;

//set cookie
/**********************************/
/*	DATA Cookie */
/**********************************/
// set cookie
function setCookie(name, value, expireDays){
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expireDays);
	document.cookie = name + "=" + escape(value) + ";path=/;expires=" + todayDate.toGMTString() +";";
}

// get cookie
function getCookieValue(cookieName){

	var cookieValue = null;
	if(document.cookie.length > 0){
		var offset = document.cookie.indexOf(cookieName +"=");
		if(offset != -1) {
			offset += cookieName.length;
			end = document.cookie.indexOf(";", offset);
			if (end == -1)	end = document.cookie.length;
			cookieValue = unescape(document.cookie.substring(offset+1, end));
		}
	}

	if(cookieValue == null)
		return "";
	
	return cookieValue;
}

//cookie key to value  getCookieNameToKey("cookiename", "memberid") ) 
function getCookieKeyToValue(cookieName, cookieKey){
	try{
		var cookieValue = getCookieValue(cookieName);
		var arCookie = cookieValue.split("&");
		var returnString = "";
		for(var i=0; i < arCookie.length; i++){
			var str = arCookie[i];			
			if(str.indexOf(""+cookieKey) > -1 ){
				returnString = str.substring( str.indexOf("=") +1, str.length);
				returnString = returnString.replace("\"", "");							
				break;
			}
		}
		return returnString;
	}catch(e){
		return null;		
	}
}

// remove cookie
function removeCookie(name){
	var expireDate = new Date();
	expireDate.setDate(expireDate.getDate()-1);
	document.cookie = name+"=;expires=" + expireDate.toGMTString() + ";path=/";
}

/**********************************/
/*	Regular expression */
/**********************************/
function regExp(value, type){
	var base = "";
	switch(type){
		case "EMAIL":
			// 이메일
			base = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			break;
		case "NUM":
			// 숫자
			base = /^[0-9]+$/;
			break;
		case "ENG":
			// 영문
			base = /^[a-zA-Z]+$/;
			break;
		case "ENG_SPACE":
			// 영문+공백
			base = /^[a-zA-Z\s]+$/;
			break;
		case "ENG_NUM":
			// 영문+숫자
			base = /^[a-zA-Z0-9]+$/;
			break;
		case "HAN":
			// 한글
			base = /^[가-힣]+$/;
			break;
		case "HAN_SPACE":
			// 한글+공백
			base = /^[가-힣\s]+$/;
			break;
		case "HAN_ENG":
			// 한글+영문
			base = /^[가-힣a-zA-Z]+$/;
			break;
		case "HAN_ENG_NUM":
			// 한글+영문+숫자
			base = /^[가-힣a-zA-Z0-9\s]+$/;
			break;
		case "ENG_NUM_SP":
			// 영문+숫자+특수문자
			base = /^(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?])$/i;
			break;
		case "PWD":
			// 패스워드
			base = /((?=.*\d)(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z]).{8,20})/gm;
			break;
		case "ID":
			// 아이디
			base = /((?=.*[a-z])(?=.*[0-9]).{4,15})/gm;
			break;		
	}
	
	if(base.test(value)){
		return true;
	}
	
	return false;	
}

// 아이피 정규식 체크
function checkIP(strIP) {
    var expUrl = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
    return expUrl.test(strIP);
}
var com = {
	/**
	 * 공통라이브러이에서 사용되는 상수 및 문자열 정의
	 */	
	fileDownRequestUrl : "/commbiz/atchfle/atchFileDown.do",
	fileDeleteRequestUrl : "/commbiz/atchfle/atchFileDelete.do",
	comCodeRequestUrl : "/com/code/getCodeString.json",
	splitChar : "|",
	htmlCalendarUrl : "/com/code/htmlCalendar.do",
	calendarObj : "",
	curMenuTitle : "",
	
	/**
	 * 동적으로 페이지 목록 정보 출력을 위해서 사용되는 변수
	 */
	_objId : "",
	_currentPage : 1,
	_totalRow : 0,
	_blockSize : 10,
	_url : "",
	_pageBlockSize : 10,
	_pageCount : 0,
	_startPage : 1,
	_endPage : 1,
	_prevPage : 1,
	_nextPage : 1,
	/**
	 * 빈값 첵크
	 * com.isBlank(" ") //true
	 * com.isBlank("") //true
	 * com.isBlank("\n") //true
	 * com.isBlank("a") //false
	 * com.isBlank(null) //true
	 * com.isBlank(undefined) //true
	 * com.isBlank(false) //true
	 * com.isBlank([]) //true
	 */
	isBlank : function(obj){
	    return(!obj || $.trim(obj) === "" || 'null' == obj );
	},
	defaultIfBlank : function(str, defaultStr){
		if( this.isBlank(str) ){
			return defaultStr;
		}else{
			return str;
		}
	},
	log : function(obj){
		if(!isLiveMode){
			vConsole.log("[LOG][console]", obj);
		}
	},
	setCurMenuTitle : function(menuTitle){
		curMenuTitle = menuTitle;
	},
	getCurMenuTitle : function(){
	    return curMenuTitle;
	},
	/**
	 * 공통메시지 얻어오기
	 * 예) com.getMessage(["{0}은(는) {1}자 이상 입력해야 합니다." ,"qq", "aa" , "bb"])
	 */
	getMessage: function(msg) {		
		
		var message = "";
		
		if (msg instanceof Array) {
			var replaceMsg = msg[0];
			for(var i = 1, len = msg.length; i < len; i++) {
				var re = new RegExp("\\{" + (i-1) + "\\}", "g");
				replaceMsg = replaceMsg.replace(re, msg[i]);
			}
			message = replaceMsg;
		} else {
			message = msg;
		}
		
		return message;
		
//		return msg;
		
	},
	/**
	 * 확인창(confirm) 띄움.
	 * @param {string|array} msg - 메시지 Id (또는 메시지)
	 * 	<ul>
	 * 		<li>메시지 내부에 치환될 문자열 ({1},{2}...) 있는 경우 array 로 넘김 [msgId, replace1, replace2 ...]</li>
	 * 	</ul>			 
	 * @param {function} callback - 콜백함수 (동의한 경우에만 호출됨)
	 * @param {object} options - 추가옵션 (추후 확장용)
	 * 	<ul>
	 *      <li>cancelCalllback : 콜백함수 - null(default)</li>	 
	 * 	</ul>		 
	 */
	confirm : function(msg, callback, options) {
		
		var okCallback = callback;
		
		if (confirm( com.getMessage(msg) ) ){
			okCallback();
		}
	},
	/**
	 * 경고창(alert) 띄움.
	 * @param {string|array} msg - 메시지 Id (또는 메시지)
	 * 	<ul>
	 * 		<li>메시지 내부에 치환될 문자열 ({1},{2}...) 있는 경우 array 로 넘김 [msgId, replace1, replace2 ...]</li>
	 * 	</ul>			 
	 * @param {string} type - 유형 (default - warning)
	 * @param {object} options - 추가옵션 (추후 확장용)
	 */
	alert : function(msg, type, options) {

		alert( com.getMessage(msg) );
		
	},
	/**
	 * 기본(범용) AJAX 처리 (기본은 x-www-form-urlencoded 형태의 POST 방식, Async mode, jQuery.ajax 활용)
	 * @param {string} url - 호출 서버 URL
	 * @param {object} data - 전송할 데이터 
	 * @param {function} callback - 서버 처리가 성공한 경우 호출한 콜백함수 
	 * @param {object} options - 추가 옵션
	 * 	<ul>
	 * 		<li>async - true(default)/false</li>
	 * 		<li>dimmed - true/false(default)</li>
	 * 		<li>type - "post"(default)/"get"</li>
	 * 		<li>contentType - "application/x-www-form-urlencoded; charset=UTF-8"(default) / ... </li>
	 * 		<li>resultTypeCheck : 지정된 데이터 형식 확인여부 - true(default)/false</li>
	 * 		<li>spinner : 대기중 이미지 표시여부 - true(default)/false</li>
	 * 		<li>errorCallback :  서버쪽 응용 에러(NOK) 인경우 처리할 콜백함수 - null(default)</li>
	 * 		<li>timeout :  서버 응답 대기 시간 (단위:millisecond) - 30000(default)</li>
	 * 	</ul>
	 */
	ajax : function(url, reqParam, callback, options) {		
		if(!isLiveMode){
			vConsole.log("[AJAX][Start]", url, reqParam, options);
		}
		
    	options = $.extend({}, {
    		async : true,
    		type  : "POST",
    		traditional : true,
    		contentType : "application/x-www-form-urlencoded; charset=UTF-8",
    		resultTypeCheck : true,
    		spinner : true,
    		dimmed : false,
			errorCallback : null,
    		timeout : 30000			// 기본 30초
    	}, options);


		$.ajax({
			url		: url, 
			async   : options.async,
			type  	: options.type,
			traditional : options.traditional,
			contentType : options.contentType,
			data	: reqParam,
			dataType: options.dataType,  // default : Intelligent Guess
			timeout : options.timeout,
			beforeSend : function(xhr) {
				if( options.spinner ){

					if( "/" == CONTEXT_ROOT ){
						CONTEXT_ROOT = "";
					}

					$.blockUI({
//						message : "<img src='" + CONTEXT_ROOT + "/js/jquery/plugins/blockUI/progress_2_2.gif' />",
						message : "<img src='" + CONTEXT_ROOT + "/js/jquery/plugins/blockUI/progress_4_5.gif' />",
//						message : "<img src='" + CONTEXT_ROOT + "/js/jquery/plugins/blockUI/progress_2_1.gif' />",
						css : {
							top : ($(window).height() - 190) / 2 + 'px',
							left : ($(window).width() - 60) / 2 + 'px',
//							width : '100px'
							width : '100px' ,
							color:		'#fff',
							border:		'0px solid #aaa', 
//							backgroundColor:'#999',
							opacity: 1 ,
							cursor:		'wait'
						}
					});
				}
			},
			success : function (data, textStatus, jqXHR) {

				var resultMsg = "data.retCd=" + data.retCd;
				resultMsg = resultMsg + " | data.retMsg=" + data.retMsg;
				resultMsg = resultMsg + " | data.Data=" + data.Data;
				resultMsg = resultMsg + " | data.rows=" + data.rows;
				if(!isLiveMode){
					vConsole.log("[AJAX][Success]", resultMsg );
				}
				resultMsg = "";
				resultMsg = resultMsg + " | textStatus=" + textStatus;
				if(!isLiveMode){
					vConsole.log("[AJAX][Success]", resultMsg );
				}				
				resultMsg = "";
				resultMsg = resultMsg + " | jqXHR.status=" + jqXHR.status;
				resultMsg = resultMsg + " | jqXHR.statusText=" + jqXHR.statusText;
				if(!isLiveMode){
					vConsole.log("[AJAX][Success]", resultMsg );
				}
				resultMsg = "";
				resultMsg = resultMsg + " | jqXHR.responseJSON.retCd=" + jqXHR.responseJSON.retCd;
				resultMsg = resultMsg + " | jqXHR.responseJSON.Data=" + jqXHR.responseJSON.Data;
				resultMsg = resultMsg + " | jqXHR.responseJSON.rows=" + jqXHR.responseJSON.rows;
				resultMsg = resultMsg + " | jqXHR.responseJSON.retMsg=" + jqXHR.responseJSON.retMsg;
				if(!isLiveMode){
					vConsole.log("[AJAX][Success]", resultMsg );
				}
				
				
				/** 
				 * callback 전 화면 unblock 여부 
				 * true 이면 콜백 후 콜백화면에서 처리..
				 * 디폴트 콜백 전 처리
				 * */
				if( options.isUnblock!=true ) {
					if( options.spinner ){ $.unblockUI(); }
				}
				
				
//				if (options.errorCallback)	options.errorCallback.call(this, data, textStatus, jqXHR);	// 응용에러 콜백지원
				
				if (typeof callback == 'function') {
					vConsole.log("[AJAX][Data Callback]");
					// callback.call(this, data, jqXHR, textStatus);
					// callback.call(this, data, textStatus, jqXHR);
					eval(callback(this, data, textStatus, jqXHR));
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) { 
				if(!isLiveMode){
					vConsole.log("[AJAX][Error][" + jqXHR.status + "] ( " + jqXHR.statusText + ")");
				}
				
				if( options.spinner ){ $.unblockUI(); }
				
				if(textStatus == 'timeout'){
					com.alert('서버 응답이 없습니다.(Timeout)');
				} else if(jqXHR.status == 404){
					com.alert('서버 경로가 잘못되었습니다.');
				} else if(jqXHR.status == 500){

//					com.alert('서버 내부 처리에 문제가 발생했습니다.');
					if( com.isBlank( jqXHR.responseJSON.message ) ){
						com.alert('서버 내부 처리에 문제가 발생했습니다.');
					}else{
						com.alert(jqXHR.responseJSON.message);
					}
				} else {

					com.alert('서버 요청과정에 문제가 발생했습니다.');
//					if( !com.isBlank( jqXHR.responseJSON ) ){
//						if( com.isBlank( jqXHR.responseJSON.message ) ){
//							com.alert('서버 요청과정에 문제가 발생했습니다.');
//						}else{						
//							com.alert('서버 요청과정에 문제가 발생했습니다.\n' + jqXHR.responseJSON.message);
//						}
//					}else if( !com.isBlank( jqXHR.statusText ) ){
//						if( com.isBlank( jqXHR.statusText ) ){
//							com.alert('서버 요청과정에 문제가 발생했습니다.');
//						}else{						
//							com.alert('서버 요청과정에 문제가 발생했습니다.\n' + jqXHR.statusText + "(" + jqXHR.responseText + ")" );
//						}
//					}
				}						

			}
		});

	},
	/**
	 * Confirm 확인후 AJAX 처리 (주로 삭제처리시) 
	 * 	<ul>
	 * 		<li>com.ajax 함수 활용</li>
	 * 		<li>msg 를 제외한 나머지 parameter 는 com.ajax 와 동일</li>
	 * 	</ul>	 
	 * @param {string} msg - Confirm 용 메시지
	 * @param {string} url - 호출 서버 URL
	 * @param {object} data - 전송할 데이터
	 * @param {function} callback - 서버 처리가 성공한 경우 호출한 콜백함수 
	 * @param {object} options - 추가 옵션 
	 */
	ajax4confirm : function(msg, url, data, callback, options) {	
		com.confirm(msg, function() {
			com.ajax(url, data, callback, options);
		});
	},
	
	
	/**
	 * 서버 조회시 페이지 생성
	 * @param object     : div 오브젝트 id명
	 * @param totalCnt   : 전체 데이터 갯수
	 * @param onePageRow : 페이지당 그리드에 조회 할 Row 갯수
	 * @param page       : 현재페이지 번호
	 */
	pageNavi :function(object, totalCnt, onePageRow, page, sheet){
//		if(totalCnt <= 0){
//			$("#temp_"+object).remove();
//			return;
//		}			
		if(sheet!=undefined ) sheet = sheet.id;
		
		var pageBlockSize = 10;  //한번에 화면에 나타낼 페이지 갯수
		var pageCount     = 0;   //총 페이지 갯수
		var lastStartPage = 0;   //마지막 블럭의 시작페이지 번호
		var startPage     = 0;   //시작페이지번호
		var endPage       = 0;   //종료페이지번호
		var prevPage      = 0;   //이전페이지번호
		var nextPage      = 0;   //다음페이지번호
					
		//페이지 갯수 계산
		pageCount = parseInt(totalCnt / onePageRow, 10);	//페이지 갯수
		if((totalCnt % onePageRow) > 0){
			pageCount = pageCount + 1;
		}else{
			pageCount = pageCount;
		}
		
		//페이지 블럭수 계산
		lastStartPage = parseInt(pageCount / pageBlockSize * pageBlockSize, 10);
		page = parseInt(page);
		//현재 페이지 블럭의 마지막 페이지 번호 계산
		if((page % pageBlockSize) == 0){
			endPage = page; 
		}else{
			endPage = ( page + pageBlockSize ) - (page % pageBlockSize); //페이지 목록의 마지막 페이지 번호
		}
		if(endPage > pageCount) endPage = pageCount;
		
		//현재 페이지 블럭의 첫번째 페이지 번호 계산
		if((page % pageBlockSize) == 0){
			startPage = page - (pageBlockSize - 1);
		}else{
			startPage = page - (( page % pageBlockSize ) - 1);
		}
		
		//이전 페이지 블럭의 마지막페이지(선택페이지)
		prevPage = startPage > 1 ? (startPage - 1) : 1;
		
		//다음 페이지 블럭의 첫페이지(선택페이지)
		nextPage = ( endPage+1 ) > pageCount ? pageCount : ( endPage+1 );
		
		//페이지 HTML 초기화
		$("#temp_"+object).remove();
		
		/* 페이지 HTML 생성 시작 ************************************************************/
		
		var text  = "<div class='paging' id='temp_"+object+"' name='temp_"+object+"'>";
		    text += "<ul>";
		//이전 페이지 이동 버튼 활성화			
       	if(prevPage < startPage){           	           		
       		if(startPage > 1) text += "<li><a href=\"javascript:doAction('page', 1, " + sheet + ");\"><img src='/images/paging_first.gif' alt='맨앞'></a></li>";
       		text += "<li><a href=\"javascript:doAction('page'," + prevPage + ", " + sheet + "); \"><img src='/images/paging_prev.gif' alt='이전'></a></li>";           	
   		}
       	
       	//페이지 목록을 생성한다.
       	for(var i=startPage; i<=endPage; i++){
       		text += "<li><a href=\"javascript:doAction('page'," + i + ", " + sheet + ");\"";
       		if(i==page) text += " class='on'";
       		text += ">" + i + "</a></li>";
       	}
       	
       	//다음 페이지 블럭의 이동 버튼 활성화
       	if(nextPage > endPage){
       		text += "<li><a href=\"javascript:doAction('page'," + nextPage + ", " + sheet + "); \"><img src='/images/paging_next.gif' alt='다음'></a></li>";
       		if(endPage < pageCount) text += "<li><a href=\"javascript:doAction('page'," + lastStartPage + ", " + sheet + "); \"><img src='/images/paging_last.gif' alt='맨뒤'></a></li>"; 
       	}           	
   		
   		text += "</ul>";
       	text += "</div>";		
		
		$("#"+object).append(text);						
	},	
	
	/**
	 * inputBox에 입력값이 숫자인지 체크 (onchange 이벤트 사용) 
	 * 예) onchange="com.checkNumber(document.getElementById('evalScore'));"
	 **/
	checkNumber : function(obj) {	
		var numPattern = /([^0-9])/;
	     var numPattern = obj.value.match(numPattern);
	     if(numPattern != null){
	         alert("숫자만 입력해 주세요!");
	         obj.value = "";
	         obj.focus();
	         return false;
	     }
	},
	emailcheck : function(strValue) {
		var regExp = /[0-9a-zA-Z][_0-9a-zA-Z-]*@[_0-9a-zA-Z-]+(\.[_0-9a-zA-Z-]+){1,2}$/;
		// 입력을 안했으면
		if (strValue.lenght == 0) {
			return false;
		}
		// 이메일 형식에 맞지않으면
		if (!strValue.match(regExp)) {
			return false;
		}
		return true;
	},
	macAddrcheck : function(strValue) {
		var regExp = /^([0-9A-F]{2}[:-]?){5}([0-9A-F]{2})$/;
		
		// macAddr에 형식에 맞지않으면
		if (!strValue.match(regExp)) {
			return false;
		}
		return true;
	},
	ipAddrcheck : function(strValue) {
		var regExp = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]){1}$/;
		
		// IP 에 형식에 맞지않으면
		if (!strValue.match(regExp)) {
			return false;
		}
		return true;
	},
	
	
	/**
	 * 프로트프로그램에서 등록된 파일의 다운로드 요청시 호출한다.
	 * @param arg0 : 첨부파일 ID
	 * @param arg1 : 첨부파일 시리얼번호
	 */
	downFile : function(arg0, arg1){
			//동적으로 iframe 생성
			var $iframe = $("#downIFrame");
			if($iframe.length < 1){
				var $iframe = $("<iframe id='downIFrame' name='downIFrame' frameBorder='0' scrolling='no' width='0' height='0'></iframe>");
				$(document.body).append($iframe);
			}
			
			//동적으로 다운로드용 form 생성
			var $form = $("#downForm");
			if($form.length < 1) {
				$form = $("<form id='downForm', method='post', action='"+this.fileDownRequestUrl+"' target='downIFrame'></form>");
			  	$(document.body).append($form);
			}
			//이전에 처리한 다운로드파일정보 삭제
			$form.empty();

			//다운로드파일정보 세팅
			$("<input></input>").attr({type:"hidden", name:"arg0", value:$.trim(arg0)}).appendTo($form);
			$("<input></input>").attr({type:"hidden", name:"arg1", value:$.trim(arg1)}).appendTo($form);

			$form.submit();
	},
    

	/**
	 * 제공된 파일을 삭제한다. 내부적으로 deleteFile2를 호출한다.
	 * @param arg0 : 구분자(|)로 구분되는 첨부파일ID와 시리얼번호 조합 문자열
	 * @param arg1 : 
	 */
	deleteFile : function(arg0, arg1){
		
		
		var confirmMsg = "파일을 삭제하시겠습니까?";
		
		if(!confirm(confirmMsg)){
			return;
		}
		
		
		var arg = arg0.split("|");
		if(arg.length==2) com.deleteFile2(arg[0], arg[1], arg1);
		else alert("파일정보가 정확하지 않습니다.");
	},
	
	/**
	 * 파일삭제를 위해서 서버쪽에 요청을 하는 메소드
	 * @param arg0 : 첨부파일 ID
	 * @param arg1 : 첨부파일 시리얼 번호
	 * @param arg2 : returnUrl
	 */
	deleteFile2 : function(arg0, arg1, arg2){
		//this.deleteFileEnd(arg0, arg1, arg2);
		//return;
		
		//동적으로 iframe 생성
		var $iframe = $("#deleteIFrame");
		if($iframe.length < 1){
			var $iframe = $("<iframe id='deleteIFrame' name='deleteIFrame' frameBorder='0' scrolling='no' width='0' height='0'></iframe>");
			$(document.body).append($iframe);
		}
		
		//동적으로 삭제할 form 생성
		var $form = $("#deleteForm");
		if($form.length < 1) {
//			$form = $("<form id='deleteForm', method='post', action='"+this.fileDeleteRequestUrl+"' target='deleteIFrame'></form>");
			$form = $("<form id='deleteForm', method='post', action='"+this.fileDeleteRequestUrl+"' ></form>");
		  	$(document.body).append($form);
		}
		//이전에 처리한 삭제파일정보 삭제
		$form.empty();

		//다운로드파일정보 세팅
		$("<input></input>").attr({type:"hidden", name:"arg0", value:$.trim(arg0)}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:"arg1", value:$.trim(arg1)}).appendTo($form);
		$("<input></input>").attr({type:"hidden", name:"arg2", value:$.trim(arg2)}).appendTo($form);

		$form.submit();
	},
	
	/**
	 * 파일삭제 후에 화면 처리를 위해서 호출되는 함수. 
	 * @param arg0 : 첨부파일 ID
	 * @param arg1 : 첨부파일 시리얼번호
	 * @param arg2 : 
	 */
	deleteFileEnd : function(arg0, arg1, arg2){
		if("function"==typeof(deleteFileEnd)){
			//웹페이지에서 사용자가 deleteFileEnd() 메소드를 정의한 경우 처리된다.
			deleteFileEnd(arg0, arg1);
		}else{
			//사용자정의 deleteFileEnd()가 없는 경우 디폴트 처리를 한다.
			$('#file_'+arg0+'_'+arg1).remove();
			
			//파일첨부용 INPUT 객체를 생성한다. 전달받은 FORM객체명을 이용해서 처리
			var $object = $("#"+arg2);

			//다운로드파일정보 세팅
			$("<tr><td><input type='file' id='file' name='file'/></td></tr>").appendTo($object);
		}
	},
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 팝업을 하루종일 열지 않는다.
	 * @param popupId	: 팝업ID
	 */
	closePopupToday : function(popupId) {
		if(confirm("오늘 하루 이 창을 열지않겠습니까?")){
			var todayDate = new Date();
			todayDate.setDate( todayDate.getDate() + 1 );
			document.cookie = popupId + "=" + escape( popupId ) + "; path=/; expires=" + todayDate.toGMTString() + ";" ;
			window.close();
		}
	},
			
	
	/**
	 * 두개의 배열을 전달받아서 배열 교집합 반환한다.
	 * @param arg0 : 교집합 대상 첫번째 배열
	 * @param arg1 : 교집합 대상 두번째 배열
	 */
	arrayIntersect : function(arg0, arg1) {
	    return $.grep(arg0, function(i) {
	        return $.inArray(i, arg1) > -1;
	    });
	},

	/**
	 * 구분자(|)에의해 구분되는 두개의 문자열을 받아서 배열 교집합을 반환한다. 내부적으로 arrayIntersect()메소드를 호출한다.
	 * @param arg0 : 교집합 대상 첫번째 문자열 
	 * @param arg1 : 교집합 대상 두번재 문자열
	 */
	sheetIntersect : function(arg0, arg1) {
		var arr_a = arg0.split(this.splitChar);
		var arr_b = arg1.split(this.splitChar);
		
		return com.arrayIntersect(arr_a, arr_b);
	},
	
	/**
	 * 두개의 배열을 전달받아서 첫번째 배열에서 두번째 배열 항목을 뺀 나머지를 반환한다.
	 * @param arg0 : 차집합 대상이 되는 배열
	 * @param arg1 : 차집합 대상 배열에서 빠질 요소를 갖고있는 배열
	 */
	arrayDifference : function(arg0, arg1) {
	    return $.grep(arg0, function(i) {
	        return $.inArray(i, arg1) == -1;
	    });
	},
	
	/**
	 * 구분자(|)에의해 구분되는 두개의 문자열을 받아서 배열 차집합을 반환한다. 내부적으로 arrayDifference()메소드를 호출한다.
	 * @param arg0 : 차집합 대상이 되는 첫번째 문자열
	 * @param arg1 : 차집합 대상 첫번째 문자열에서 빠질 요소를 갖고있는 문자열
	 * @returns
	 */
	sheetDifference : function(arg0, arg1){
		var arr_a = arg0.split(this.splitChar);
		var arr_b = arg1.split(this.splitChar);
		
		return com.arrayDifference(arr_a, arr_b);
	},
	
	/**
	 * 문자열의 바이트수 체크
	 * @param data : 비교할 문자열
	 * @param byte : 체크할 바이트수
	 * @returns
	 */
	//byteLenCheck : function(data, byte){	
	byteLenCheck : function(e, obj, byte){
		var keyCode = "";
		if(com.browserType() == "Firefox"){
			keyCode = e.which;			
		}else{		
			keyCode = e.keyCode;
		}
		
		if(keyCode == '13'){
			return false;
		}
		
		var data = obj.value;
		var dataLen  = com.getByteLen(data);
		var filter   = "[A-Za-z0-9]"; 		 
		var key      = String.fromCharCode(keyCode);		
		var keyCheck = new RegExp(filter);
			        				
		if(dataLen > byte){
			//229는 keydown 한글입력시 keycode
			if(keyCheck.test(key) == true || keyCode == '229'){
				alert("더 이상 입력이 불가능합니다.");
				obj.value = "";
				if(e.preventDefault){
			        e.preventDefault();
			    } else {
			      //  e.returnValue = false;
			    }
			}	
		}
	},
	
	
	/**
	 * 한글(2Byte), 영문,숫자(1Byte)로 계산된 문자열의 실제 바이트수를 리턴하는 함수
	 * @param data : 바이트수를 구할 문자열
	 * @returns
	 */
	getByteLen : function(data){
		if(data==undefined) data = "";		
		var chrLen = 0;
		var str    = data;
		var EscStr;
		
		if(str!="" & str !=null){
			for(var i=0;i<str.length;i++){
				if(str.charAt(i)!=escape(str.charAt(i))){
					EscStr=escape(str.charAt(i));
					if(EscStr.length>=6){
						chrLen+=2;
					}else{
						chrLen+=1;
					}
				}else{
					chrLen+=1;
				}
			}
		}
		return chrLen;
	},

	/**
	 * 특정 url 이동 (submit)
	 * @param url : 이동할 url
	 * @param params : params   
	 * @returns
	 */   
	moveUrl : function(url, params){
		if(params==undefined) params = "";
		//동적으로 생성할 form 생성
		var $form  = $("#moveUrlForm");			
			
		if($form.length < 1) {
			$form = $("<form id='moveUrlForm', method='post', action='"+url+"' target=''></form>");
			$(document.body).append($form);
		}
		//이전에 처리한 정보 삭제
		$form.empty();
			
		//정보 세팅
		var param = params.split("&");
		for(var i=0; i<param.length; i++){
			var text  = param[i].split("=");
			var name  = text[0];
			var value = text[1];			
			$("<input></input>").attr({type:"hidden", name:name,  value:$.trim(value)}).appendTo($form);			
		}
		$form.submit();
	},
	
	subPageMoveMenuLocation : function(rootMenuId, menuId, isPopUp) {
		// 메뉴 로케이션 영역에서의 메뉴명 클릭시 사용됨.
		this.subPageMove('',rootMenuId, menuId, isPopUp);
	},
	
	subPageMove : function(menuArea, rootMenuId, menuId, isPopUp, lecMenuMarkYn) {
		
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

		//console.log("targetURL=" + targetURL+ "    rootMenuId=" + rootMenuId + " , menuId=" + menuId + " , isPopUp=" + isPopUp+ ", lecMenuMarkYn=" + lecMenuMarkYn );
		
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
	},
	
	/**
	 * 공통팝업 호출
	 * @param sTitle : 타이틀 
	 * @param sUrl  : 호출할 url
	 * @param iWidth : 팝업 넓이
	 * @param iHeight : 팝업 높이
	 * @param sPosition	: 팝업 위치( CENTER 또는 center 일 경우에만 팝업을 가운데로 위치)
	 * @returns
	 */   
	openPopup : function(sTitle, sUrl, iWidth, iHeight, sPosition){		
		if(sTitle==undefined || sTitle=="" || sTitle==null) sTitle = "Popup";
		if(sUrl==undefined || sUrl=="" || sTitle==null){
			alert("호출할 페이지 주소가 없습니다.");
			return;
		}
		if(iWidth==undefined || iWidth=="" || iWidth==null) iWidth = "500";
		if(iHeight==undefined || iHeight=="" || iHeight==null) iHeight = "500";
		if(sPosition==undefined || sPosition=="" || sPosition==null) sPosition = "";
		
		//sTitle 특수문자 제거
		var specialLetter = "=,.:;{}[]()<>?_|~`!@#$%^&*-+\"'\\/"; //특수문자 정의.
		var newTitle = sTitle;
		var testValue;
		var testIndex;
		for(var i=0; i<sTitle.length; i++){
			testValue = sTitle.charAt(i);
			testIndex = specialLetter.indexOf(testValue);					
			
			if(-1 != testIndex)newTitle = newTitle.replace(testValue, "");		
		}
		
		var tmp = sUrl.split("?");
		var iScrollbars = 'no';
		var popUrl = ((tmp.length<=1) ? tmp[0] :  "");		
		var popStatus = "toolbar=no, location=no, status=no, menubar=no, scrollbars="+iScrollbars+", resizeble=no, width="+iWidth+"px, height="+iHeight+"px";		
		
		// 팝업창을 가운데로 위치
		if(sPosition=="CENTER" || sPosition=="center"){
			var top =  (screen.height - iHeight) / 2;
			var left = (screen.width - iWidth) / 2;
			popStatus = popStatus + ", top=" + top + "px, left=" + left + "px"; 
		}
		
		
		var	popTitle = newTitle.replace(/ /gi, "");				
				
		//팝업창 오픈
		window.open(popUrl, popTitle, popStatus);

		// 팝업화면에 넘겨줄 값이 있을 경우
		if(tmp.length>1){		
			//동적으로 생성할 form 생성
			
			$("#popupForm").remove();
			
			//var $form  = $("#popupForm");			
			
			//if($form.length < 1) {
				var $form = $("<form id='popupForm', method='post', action='"+tmp[0]+"' target='"+popTitle+"'></form>");
			  	$(document.body).append($form);
			//}
			//이전에 처리한 정보 삭제
			//$form.empty();
			
			//정보 세팅		
			var fld = tmp[1].split("&");
			for(var i=0; i<fld.length; i++){
				var col = fld[i].split("=");
				$("<input></input>").attr({type:"hidden", name:col[0],  value:$.trim(col[1])}).appendTo($form);			
			}
			
			$form.submit();
		}		
	},
	openScrollPopup : function(sTitle, sUrl, iWidth, iHeight, sScrollbars, sPosition){	
		
		if(sTitle==undefined || sTitle=="" || sTitle==null) sTitle = "Popup";
		if(sUrl==undefined || sUrl=="" || sTitle==null){
			alert("호출할 페이지 주소가 없습니다.");
			return;
		}
		if(iWidth==undefined || iWidth=="" || iWidth==null) iWidth = "500";
		if(iHeight==undefined || iHeight=="" || iHeight==null) iHeight = "500";
		if(sPosition==undefined || sPosition=="" || sPosition==null) sPosition = "";
		
		
		//sTitle 특수문자 제거
		var specialLetter = "=,.:;{}[]()<>?_|~`!@#$%^&*-+\"'\\/"; //특수문자 정의.
		var newTitle = sTitle;
		var testValue;
		var testIndex;
		for(var i=0; i<sTitle.length; i++){
			testValue = sTitle.charAt(i);
			testIndex = specialLetter.indexOf(testValue);					
			
			if(-1 != testIndex)newTitle = newTitle.replace(testValue, "");		
		}
		
		var tmp = sUrl.split("?");
		
		var popUrl = ((tmp.length<=1) ? tmp[0] :  "");		
		var popStatus = "toolbar=no, location=no, status=no, menubar=no, scrollbars="+sScrollbars+", resizeble=no, width="+iWidth+"px, height="+iHeight+"px";		
		
		//팝업창 가운데로 위치
		if(sPosition=="CENTER" || sPosition=="center"){
			var top =  (screen.height - iHeight) / 2;
			var left = (screen.width - iWidth) / 2;
			popStatus = popStatus + ", top=" + top + "px, left=" + left + "px"; 
		}
		var	popTitle = newTitle.replace(/ /gi, "");				
				
		//팝업창 오픈
		window.open(popUrl, popTitle, popStatus);

		// 팝업화면에 넘겨줄 값이 있을 경우
		if(tmp.length>1){		
			//동적으로 생성할 form 생성
			
			$("#popupForm").remove();
			
			//var $form  = $("#popupForm");			
			
			//if($form.length < 1) {
				var $form = $("<form id='popupForm', method='post', action='"+tmp[0]+"' target='"+popTitle+"'></form>");
			  	$(document.body).append($form);
			//}
			//이전에 처리한 정보 삭제
			//$form.empty();
			
			//정보 세팅		
			var fld = tmp[1].split("&");
			for(var i=0; i<fld.length; i++){
				var col = fld[i].split("=");
				$("<input></input>").attr({type:"hidden", name:col[0],  value:$.trim(col[1])}).appendTo($form);			
			}
			
			$form.submit();
		}		
	},
	
	/**
	 * 딜리미터가 붙은 번호(전화,회사,폰,팩스)를 split해 해당 object로 보내준다.
	 * @param value : 딜리미터가 붙은 번호(예 : 000-0000-0000)
	 * @param obj1 : 첫번째 오브젝트명
	 * @param obj2 : 두번째 오브젝트명
	 * @param obj3 : 세번째 오브젝트명
	 * @param delimiter : - 가 아닌 다른 딜리미터일때 해당 딜리미터를 입력한다. (번호가 010~0000~0000이면 딜리미터는 ~ ) 
	 * @returns
	 */   
	splitInputNum : function(value, obj1, obj2, obj3, delimiter){		
		if(delimiter==undefined || delimiter==null || delimiter=="") delimiter = "-";
		if(value==undefined || value==null || value=="") value=delimiter+delimiter;
		
		var obj    = obj1+delimiter+obj2+delimiter+obj3; 
		var values = value.split(delimiter);		
		if(values.length != 3){
			value  = delimiter+delimiter;
			values = value.split(delimiter);
		}
		var objs   = obj.split(delimiter);
		for(var i=0;i<values.length; i++){
			$("#"+objs[i]).val(values[i]);			
		}
	},
	
	
	/**
	 * 한글만 입력가능 (onkeydown 이벤트 사용)
	 **/ 	
	inputDataKor : function(e){					
		
		var eKey = "";
		var sKey = "";
		var obj  = "";		
		if(com.browserType() == "Firefox"){
			eKey = e.which;
			sKey = String.fromCharCode(e.which);
			obj  = e.target;
		}else{
			eKey = e.keyCode;
			sKey = String.fromCharCode(e.keyCode);
			obj  = e.srcElement;
		}				
		
		//특수문자여부 확인
		var filterSpl = [59,61,188,189,190,191,192,219,220,221,222];
		var splBool   = false;		
		for(var i=0; i<filterSpl.length; i++){
			if(eKey == filterSpl[i]){
				splBool = true;
			}
		}		
		//영문,숫자여부 확인
		var filter = "[A-Za-z0-9]";
		var re     = new RegExp(filter);		
		if(re.test(sKey) || splBool) {
			alert("한글만 입력할 수 있습니다.");			
			obj.value = "";
			if(e.preventDefault){
				e.preventDefault();
			}else{
				e.returnValue = false;
			}						
		}else {
			
			if(e.keyCode == 96){ //키패드의 0(Ins)키
				if(e.preventDefault){
					e.preventDefault();
				}else{
					e.returnValue = false;
				}
			}
			return true;
		}
	},
	
	/**
	 * 영문만 입력가능 (onkeyup 이벤트 사용)
	 **/
	inputDataEng : function(e){
	    var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
	    
		var objEventVal = objEvent.value;
		var checkValue  = /^[A-Za-z]*$/;
		var testValue   = "";
		var testCheck   = "";
		var lastValue   = objEvent.value;
		var alertCheck  = false;				
		
		for(var i=0; i<objEventVal.length; i++){			
			testValue = objEventVal.charAt(i);
			testCheck = testValue.match(checkValue);
						
			if(testCheck==null){
				alertCheck = true;
				lastValue = lastValue.replace(testValue, "");			
			}			
		} 
		if(alertCheck) alert("영문만 입력할 수 있습니다.");
		objEvent.value = lastValue;
	},
	
	/**
	 * 숫자키 입력가능 (onkeyup 이벤트 사용)
	 **/
	inputDataNum : function(e, format){
	    var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
	    
		var objEventVal = objEvent.value;
		var checkValue  = /^[0-9,\-]*$/;
		var testValue   = "";
		var testCheck   = "";
		var lastValue   = objEvent.value;
		var alertCheck  = false;				
		
		for(var i=0; i<objEventVal.length; i++){			
			testValue = objEventVal.charAt(i);
			testCheck = testValue.match(checkValue);
						
			if(testCheck==null){
				alertCheck = true;
				lastValue  = lastValue.replace(testValue, "");			
			}			
		}
		
		if(alertCheck){
			if(format=="" || format==undefined || format==null)alert("숫자만 입력할 수 있습니다.");
		}
		objEvent.value = lastValue;
	},
	
	/**
	 * 영문,숫자만 입력가능 (onkeyup 이벤트 사용)
	 * @param : 공백허용 여부 
	 **/ 	
	inputDataNotKor : function(e, space){
		if(space==undefined || space==null || space=="") space = false;
		
	    var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
	    
		var objEventVal = objEvent.value;
		var checkValue  = space ? /^[A-Za-z0-9,\ ]*$/ : /^[A-Za-z0-9]*$/;    
		var testValue   = "";
		var testCheck   = "";
		var lastValue   = objEvent.value;
		var alertCheck  = false;
		
		for(var i=0; i<objEventVal.length; i++){			
			testValue = objEventVal.charAt(i);
			testCheck = testValue.match(checkValue);
			if(testCheck==null){
				alertCheck = true;
				lastValue  = lastValue.replace(testValue, "");			
			}			
		}
		
		if(alertCheck){
			alert("숫자 영문으로 입력해 주세요.");
		}
				
		objEvent.value = lastValue;
	},
	
	/**
	 * 숫자만(콤마, -등 사용불가) 입력가능 (onkeyup 이벤트 사용)
	 **/
	inputDataNumOnly : function(e, format){
	    var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
	    
		var objEventVal = objEvent.value;
		var checkValue  = /^[0-9]*$/;
		var testValue   = "";
		var testCheck   = "";
		var lastValue   = objEvent.value;
		var alertCheck  = false;				
		
		for(var i=0; i<objEventVal.length; i++){			
			testValue = objEventVal.charAt(i);
			testCheck = testValue.match(checkValue);
						
			if(testCheck==null){
				alertCheck = true;
				lastValue  = lastValue.replace(testValue, "");			
			}			
		}
		
		if(alertCheck){
			if(format=="" || format==undefined || format==null)alert("숫자만 입력할 수 있습니다.");
		}
		objEvent.value = lastValue;
	},
	
	/**
	 * 숫자와 DOT(.)만 입력가능 (onkeyup 이벤트 사용) 
	 * 예) IP 체크..
	 **/
	inputDataNumDot : function (e, format){
		
		var code = e.keyCode ? e.keyCode : e.which;   
		var KEY_DOT = 190;
		
		var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
		var objEventVal  = objEvent.value;
		if(objEventVal!=""){
			var lastValue = objEventVal.substring(objEventVal.length-1);
			var regExp = /^[0-9.]*$/ ;		// 숫자 + .
			var check = regExp.test(objEventVal); 
			if(!check){
				objEventVal = objEventVal.replace(lastValue, "");
				
				if(format=="" || format==undefined || format==null){
					alert("숫자 또는 . 만 입력 입력할 수 있습니다.");
				}
			}
			objEvent.value = objEventVal;
		}
	},
	
	/**
	 * 숫자 + 영문와 DOT(-)만 입력가능 (onkeyup 이벤트 사용) 
	 * flag가 true면 대소문자 가능 
	 * 예) IP 체크..
	 **/
	inputDataEngNumDash : function ( e, format, flag){
		var code = e.keyCode ? e.keyCode : e.which;   
		
		var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
		var objEventVal  = objEvent.value;
		if(objEventVal!=""){
			var lastValue = objEventVal.substring(objEventVal.length-1);
			var regExp = /^[0-9A-Z\-]*$/ ;		// 숫자, 영문[대문자], -
			
			if(flag==true || flag=="true"){
				regExp = /^[0-9a-zA-Z\-]*$/ ;		// 숫자, 영문[대문자], -
			}
			
			var check = regExp.test(objEventVal); 
			if(!check){
				objEventVal = objEventVal.replace(lastValue, "");
				
				if(format=="" || format==undefined || format==null){
					alert("숫자, 영문[대문자], - 만 입력 입력할 수 있습니다.");
				}
			}
			objEvent.value = objEventVal;
		}
	}, 
	
	
	/**
	 * 숫자와 dash(-)만 입력가능 (onkeyup 이벤트 사용) 
	 * 예) zipno 우편번호
	 **/
	inputDataNumDash : function (e, format){
		var code = e.keyCode ? e.keyCode : e.which;   
		
		var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
		var objEventVal  = objEvent.value;
		if(objEventVal!=""){
			var lastValue = objEventVal.substring(objEventVal.length-1);
			var regExp = /^[0-9\-]*$/ ;		// 숫자 + .
			var check = regExp.test(objEventVal); 
			if(!check){
				objEventVal = objEventVal.replace(lastValue, "");
				
				if(format=="" || format==undefined || format==null){
					alert("숫자 또는 - 만 입력 입력할 수 있습니다.");
				}
			}
			objEvent.value = objEventVal;
		}
	},
	
	/**
	 * 숫자만 입력/ 숫자포멧으로 변경 (onkeyup 이벤트 사용)
	 **/
	formatNumber :function(e){
		com.inputDataNum(e,'formatNumber');
				
		var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
		var objEventVal  = objEvent.value;		
		var indexOfPoint = objEventVal.indexOf(".");
		var formatNum    = 0;
		
		if(indexOfPoint == -1) {
			formatNum  = objEventVal;
		}else{
			formatNum = (objEventVal.substring(0, indexOfPoint)) + objEventVal.substring(indexOfPoint, objEventVal.length);
		}
		
		var re = /,|\s+/g;
		formatNum = formatNum.replace(re, "");
		
		re = /(-?\d+)(\d{3})/;
		while (re.test(formatNum)){
			formatNum = formatNum.replace(re, "$1,$2");
		}
		
		objEvent.value  = formatNum;								
	},
	
	/**
	 * 특수문자 입력 불가능 (onblur 이벤트 사용)
	 **/
	inputDataNotSpl : function(e){
//		var objEvent  = "";
//	    if(com.browserType() == "Firefox"){
//			objEvent  = e.target;
//		}else{
//			objEvent  = e.srcElement;
//		}
//		
//		var tmp = objEvent.value;		
//		var tmp3 = tmp.search(/\W|\s/g);
//		
//		if(tmp3 > -1) {
//			alert("특수문자는 입력할 수 없습니다.");
//			objEvent.value = "";		    
//			}
		
		var objEvent  = "";
	    if(e.target){
	    	objEvent  = e.target;
	    }else{
	    	objEvent  = e.srcElement;
	    }
	    
		var objEventVal = objEvent.value;
		var checkValue  = "=,.:;{}[]()<>?_|~`!@#$%^&*-+\"'\\/"; //입력을 막을 특수문자 기재.
		var testValue   = "";
		var testIndex   = "";
		var lastValue   = objEvent.value;
		var alertCheck  = false;
		
		for(var i=0; i<objEventVal.length; i++){
			testValue = objEventVal.charAt(i);
			testIndex = checkValue.indexOf(testValue);					
			
			if(-1 != testIndex){
				alertCheck = true;
				lastValue = lastValue.replace(testValue, "");							
			}			
		}
		if(alertCheck) alert("특수문자는 입력할 수 없습니다.");
		objEvent.value = lastValue;
	},
	
	/**
	 * 특수문자 입력 여부 체크
	 **/
	checkSpl : function(value){
		var specialLetter = "=,.:;{}[]()<>?_|~`!@#$%^&*-+\"'\\/"; //입력을 막을 특수문자 기재.
		
		for(var i=0; i<value.length; i++){			
			testValue = value.charAt(i);
			testIndex = specialLetter.indexOf(testValue);			
			
			if(-1 != testIndex){
				alert("특수문자가 들어가 있습니다.");
				return false;
			}			
		}
		return true;
	},
	
	/**
	 * 주민등록번호 유효성체크
	 * @param 주민등록번호
	 **/
	residregistNumCheck : function(number){
		//데이터 체크
		if(number=="" || number==undefined || number==null){
			alert("체크할 정보가 없습니다."); return;
		}else{ 
			number.replace(/-/gi, ""); 
		}
		//자릿수 체크
		if(number.length != 13){
			alert("13자리 번호를 입력해 주세요."); return;
		}
		
		var lastDay    = [31,28,31,30,31,30,31,31,30,31,30,31];
		var month      = parseInt(number.substring(2,4), 10);
		var day        = parseInt(number.substring(4,6), 10);
		var genderType = [1,2,3,4,9,0]; 
		var genderNum  = parseInt(number.substring(6,7), 10);
		var genderCek  = false;
		//생월체크
		if(month > 0 && month < 13){
			//생일체크			
			if(day < 0 || day > lastDay[month-1]){
				alert("일자를 다시 확인하세요."); return;
			}
		}else{
			alert("월을 다시 확인하세요."); return;
		}
		//성별 유효성체크
		for(var i=0; i<genderType.length; i++){
			if(genderNum == genderType[i]) genderCek = true; 
		}
		if(!genderCek){ alert("유효성에 적합하지 않습니다.\n뒷자리번호를 다시 확인해 주세요."); return; }		
		
		return true; 
	},
	/**
	 * 외국인등록번호 유효성체크
	 * @param 외국인등록번호
	 **/
	foreignerNumCheck : function(number){
		//데이터 체크
		if(number=="" || number==undefined || number==null){
			alert("체크할 정보가 없습니다."); return;
		}else{ 
			number.replace(/-/gi, ""); 
		}
		//자릿수 체크
		if(number.length != 13){
			alert("13자리 번호를 입력해 주세요."); return;
		}
		
		var lastDay    = [31,28,31,30,31,30,31,31,30,31,30,31];
		var month      = parseInt(number.substring(2,4), 10);
		var day        = parseInt(number.substring(4,6), 10);
		var genderType = [5,6,7,8];
		var genderNum  = parseInt(number.substring(6,7), 10);
		var genderCek  = false;
		//월체크
		if(month > 0 && month < 13){
			//일체크
			if(day < 0 || day > lastDay[month-1]){
				alert("일자를 다시 확인하세요."); return;
			}
		}else{
			alert("월을 다시 확인하세요."); return;
		}
		
		//성별 유효성체크
		for(var i=0; i<genderType.length; i++){
			if(genderNum == genderType[i]) genderCek = true; 
		}
		if(!genderCek){ alert("유효성에 적합하지 않습니다.\n뒷자리번호를 다시 확인해 주세요."); return; }	
		return true;
	},

	/**
	 * hs Tag 의 value 값을 변경한다.
	 * @param name  : hs Tag 사용시 사용한 name 명
	 * @param value : 새로 적용시킬 value 값
	 **/
	inputTagVal: function(name, value){
		$("#"+name).val(value);
		$("#"+name).trigger('change');
	},

	
	/**
	 * 문자열 앞에 0을 추가하는 함수.
	 * @param val : 문자열
	 * @param len : 최종 문자열 길이 
	 */
	addToZero : function(val, len){
		if(len==null) len = 2;
		return com.addToData(val, "0", "L", len);
	},
	
	/**
	 * 특정 문자열을 추가하는 함수.
	 * @param val : 가공될 문자열
	 * @param add : 추가되는 문자열
	 * @param pos : 문자열이 추가되는 위치 ( left : L, right : R )
	 * @param len : 최종 문자열 길이
	 */
	addToData : function(val, add, pos, len){
		var tmp = "";
		
		val = val.toString();
		if(val.length != len){
			for(var i=0; i<(parseInt(len, 10)-val.length); i++){
				tmp += add;
			}
		}
		return (pos=="L") ? tmp+val : val+tmp;
	},
	
	
	/**
	 *특정날짜에 (년,월,일)수 추가. 
	 * @param val  : 기준날짜  (20140101, 2014-01-01)
	 * @param flag : 추가할 곳 선택 (년,월,일) (Y / M / D)
	 * @param add  : 추가할 값 (1 / 10 / 100 / -1 / ......)
	 * 예 ) com.addDate('20140101', 'Y', 2) 
	 */
	addDate: function(val, flag, add){
		var inSta, inEnd;
		var year, month, day;
		var ddAdd = 0;
		//var ddAdd = (isNull(bool)) ? 1 : ((bool) ? 1 : 0);
		
		var date = val.replace(/-/gi, "");
		
		inSta = new Date(date.substr(0,4), date.substr(4,2)-1, date.substr(6,2));	  			  
	  
		switch(flag.toUpperCase()){
			case "Y":				
				inEnd = new Date(inSta.setFullYear(inSta.getFullYear()+add));	      
				year  = inEnd.getFullYear();
				month = com.addToZero(inEnd.getMonth()+ddAdd);
				day   = com.addToZero(inEnd.getDate());	      
				inEnd = new Date(inSta.setDate(inSta.getDate()+ddAdd));
				break;
			case "M":
				inEnd = new Date(inSta.setMonth(inSta.getMonth()+add));	      
				year  = inEnd.getFullYear();
				month = com.addToZero(inEnd.getMonth()+ddAdd);
				day   = com.addToZero(inEnd.getDate());
				inEnd = new Date(inSta.setDate(inSta.getDate()+ddAdd));
				break;
			case "D": 
				inEnd = new Date(inSta.setDate(inSta.getDate()+add)); break;	    
	  }
	  year  = inEnd.getFullYear();
	  month = com.addToZero(inEnd.getMonth()+1);
	  day   = com.addToZero(inEnd.getDate());

	    return year+month+day;	  	  
	},
	
	/**
	 *특정년월의 마지막 일자 가져오기. 
	 * @param year  : 기준년도
	 * @param month : 기준월
	 * 예 ) com.lastDate('2014', '02') 
	 */
	lastDate: function(year, month){
		var date = new Date(year, month, 0);
		return date.getDate();		
	},

	/**
	 * browser 버전 체크 
	 * @returns
	 */   
	browserVer : function(){

		var bwVer = "";
		var browserInfo = navigator.userAgent.toLowerCase();
		if( "Explorer" == com.browserType() ){
			bwVer = com.getIEVersion(); 
		}else if( "Chrome" == com.browserType() ){
			bwVer = com.getChromeVersion();
		}else{
			var bw = $.browser;
			if( undefined != bw ){
				bwVer = $.browser.version;
			}
		}
		return bwVer;
	},
	getIEVersion : function() {
		// mozilla/5.0 (windows nt 6.3; wow64; trident/7.0; .net4.0e; .net4.0c; .net clr 3.5.30729; .net clr 2.0.50727; .net clr 3.0.30729; gwx:reserved; gwx:qualified; rv:11.0) like gecko
	    var match = navigator.userAgent.match(/(?:MSIE |Trident\/.*; rv:)(\d+)/);
	    return match ? parseInt(match[1]) : undefined;
	},
	getChromeVersion : function() {
		// "mozilla/5.0 (windows nt 6.3; wow64) applewebkit/537.36 (khtml, like gecko) chrome/51.0.2704.84 safari/537.36"
	    var vMatch = navigator.userAgent.match(/(?:Chrome\/.*)(\d+)/);
	    vMatch = vMatch + "";
	    vMatch = vMatch.split(" ");
	    return vMatch ? vMatch[0] : undefined;
	},
	/**
	 * browser  타입 체크 
	 * @returns
	 */
	browserType : function(){
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
	}, 
	
	
	/**
	 * <FORM> 객체에 타입이 TEXT, FILE 인 INPUT 객체를 추가한다.
	 * @param formId : 폼객체 아이디
	 * @param type : 객체 유형(file, text)
	 * @param inputId : 객체 아이디
	 * @param display : 화면 표시여부
	 */
	addInputForm : function(formId, type, inputId, value, display , divStyle ){
		if(value==undefined) value = "";
		if(display==undefined) display = "";
		
		//폼객채를 얻는다.
        var $form = $('#'+formId);
        

//    	$('<input></input>').attr({type:type, name:inputId, id:inputId, value:value }).appendTo($form);
        
        var d;
        if(divStyle==undefined){
        	d = $('<div></div>').appendTo($form);
        }else{
        	d = $('<div></div>').attr({id:'fDiv_' + inputId, style:divStyle }).appendTo($form);
        }
    	$('<input></input>').attr({type:type, name:inputId, id:inputId, value:value }).appendTo(d);
    	if(display!="") $('#'+inputId).attr('style', 'display:'+display);
	},
	
	/**
	 * <FORM> 객체에 타입이 TEXT, FILE 인 INPUT 객체를 추가한다.
	 * @param formId : 폼객체 아이디
	 * @param type : 객체 유형(file, text)
	 * @param inputId : 객체 아이디
	 * @param display : 화면 표시여부
	 */
	addInput2Form : function(formId, type, inputId, value, display){
		if(value==undefined) value = "";
		if(display==undefined) display = "";
		
		//폼객채를 얻는다.
        var $form = $('#'+formId);
        

    	$('<input></input>').attr({type:type, name:inputId, id:inputId, value:value }).appendTo($form);
    	if(display!="") $('#'+inputId).attr('style', 'display:'+display);
	},
	
	//s에 n만큼 왼쪽에서부터 c를 채운다.
	LPAD : function(s, c, n) {

		if(!s || !c || s.length >= n) {
			return s;
		}
		
		var max = (n - s.length) / c.length;
		
		for(var i = 0; i < max; i++) {
			s = c + s;
		}
		
		return s;
	},
	
	//s에 n만큼 오른쪽에서부터 c를 채운다.
  RPAD : function(s, c, n) {
		
    if(!s || !c || s.length >= n) {
    	return s;
    }
    	
    var max = (n - s.length) / c.length;
    	
    for(var i = 0; i < max; i++) {
    	s += c;
    }
    	
    return s;
  },

	/**
	 * input에 달력 component 보이기 
	 * @param calendar id명 또는 name
	 * @param 버튼과 텍스트 필드의 표시 여부 default:both , button:버튼만, text:텍스트만 
	 * 예)com.datepicker("calendar1")[디폴트], com.datepicker("calendar1", "button") 또는 com.datepicker("calendar1", "text") 
	 */
  datepicker : function(vCalId, vOpt){
	  
	  var showOn = vOpt;
	  if(showOn==undefined || showOn=="undefined"){
		  showOn='both';		// 버튼 과 텍스트 둘다 보여주기
	  }
	  var dateOptions = { 
			    showOn: showOn,
			    buttonImage: '/images/oklms/adm/inc/calendar_btn_01.png', // 버튼 이미지
			    buttonImageOnly: true, 
			    changeMonth:  true,   
			    changeYear:true,  
			    showButtonPanel:true,
			    showOtherMonths: true,
			    showMonthAfterYear:true,
			    selectOtherMonths: true,
			    currentText: '오늘 날짜' ,
			    closeText: '닫기', 
			    showAnim: 'slideDown',
			    dateFormat: 'yy-mm-dd',
			    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'],
			    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
			    buttonText : "날짜 선택"
			}; 
	  
	  var $_cal = $("input[name='" + vCalId + "']");
	  if($_cal.attr("name")==undefined || $_cal.attr("name")=="undefined"){
		  $_cal = $("#"+vCalId);
	  }
	  // input mask 처리 
	  $_cal.mask('9999-99-99');
	  
	  $_cal.datepicker(dateOptions).datepicker();
	  
	// 오늘날짜 클릭시 발생되는 이벤트
	  $.datepicker._gotoToday = function(id){
		  var currentDate = new Date();
		  $(id).datepicker("setDate", currentDate);
//		  $(this)._hideDatepicker();
		  this._hideDatepicker();
	  }
  }, 
  
  /**
	 * input에 달력 component 날짜 형식[default:2016.11.11] 
	 * @param calendar id명 또는 name
	 * @param 버튼과 텍스트 필드의 표시 여부 default:both , button:버튼만, text:텍스트만 
	 * 예)com.datepicker("calendar1")[디폴트], com.datepicker("calendar1", "button") 또는 com.datepicker("calendar1", "text") 
	 */
  datepickerDateFormat : function(vCalId, vOpt){
	  
	  var showOn = vOpt;
	  if(showOn==undefined || showOn=="undefined"){
		  showOn='both';		// 버튼 과 텍스트 둘다 보여주기
	  }
	  
	  var dateOptions = { 
			    showOn: showOn,
			    buttonImage: '/images/oklms/adm/inc/calendar_btn_01.png', // 버튼 이미지
			    buttonImageOnly: true, 
			    changeMonth:  true,   
			    changeYear:true,  
			    showButtonPanel:true,
			    showOtherMonths: true,
			    showMonthAfterYear:true,
			    selectOtherMonths: true,
			    currentText: '오늘 날짜' ,
			    closeText: '닫기', 
			    showAnim: 'slideDown',
			    dateFormat: 'yy.mm.dd',
			    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'],
			    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
			    buttonText : "날짜 선택"
			}; 
	  
	  var $_cal = $("input[name='" + vCalId + "']");
	  if($_cal.attr("name")==undefined || $_cal.attr("name")=="undefined"){
		  $_cal = $("#"+vCalId);
	  }
	  // input mask 처리 
	  $_cal.mask('9999.99.99');
	  
	  $_cal.datepicker(dateOptions).datepicker();
	  
	// 오늘날짜 클릭시 발생되는 이벤트
	  $.datepicker._gotoToday = function(id){
		  var currentDate = new Date();
		  $(id).datepicker("setDate", currentDate);
//		  $(this)._hideDatepicker();
		  this._hideDatepicker();
	  }
}, 
/**
 * input에 달력 component 날짜 지정[default:오늘날짜]  날짜 형식[default:2016.11.11] 
 * @param vCalId : calendar id명 또는 name
 * @param vAddDays : 현재일로부터 +- 날짜(일수) 숫자인 경우에는 오늘 날짜에서 더한 일수, -숫자인 경우에는 오늘 날짜에서 -일 수만큼 전일
 * @param 버튼과 텍스트 필드의 표시 여부 default:both , button:버튼만, text:텍스트만 
 * 예)com.datepicker("calendar1")[디폴트], com.datepicker("calendar1", 1 ) 오늘 날짜에서 하루 더한 날 
 */
datepickerToDateFormat : function(vCalId, vAddDays, vOpt){
  
  var showOn = vOpt;
  if(showOn==undefined || showOn=="undefined"){
	  showOn='both';		// 버튼 과 텍스트 둘다 보여주기
  }
  
  var dateOptions = { 
		    showOn: showOn,
		    buttonImage: '/images/oklms/adm/inc/calendar_btn_01.png', // 버튼 이미지
		    buttonImageOnly: true, 
		    changeMonth:  true,   
		    changeYear:true,  
		    showButtonPanel:true,
		    showOtherMonths: true,
		    showMonthAfterYear:true,
		    selectOtherMonths: true,
		    currentText: '오늘 날짜' ,
		    closeText: '닫기', 
		    showAnim: 'slideDown',
		    dateFormat: 'yy.mm.dd',
		    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    buttonText : "날짜 선택"
		}; 
	
  var $_cal = $("input[name='" + vCalId + "']");
  if($_cal.attr("name")==undefined || $_cal.attr("name")=="undefined"){
	  $_cal = $("#"+vCalId);
  }
// input mask 처리
  $_cal.mask('9999.99.99');
  
  var today = new Date();
  if( vAddDays!=undefined ){
	  today.setDate( today.getDate() + vAddDays  );
  }
  
  $_cal.datepicker(dateOptions).datepicker("setDate", today);
  
  // 오늘날짜 클릭시 발생되는 이벤트
  $.datepicker._gotoToday = function(id){
	  var currentDate = new Date();
	  $(id).datepicker("setDate", currentDate);
//	  $(this)._hideDatepicker();
	  this._hideDatepicker();
  }
},
  /**
	 * input에 달력 component 날짜 지정[default:오늘날짜] 
	 * @param vCalId : calendar id명 또는 name
	 * @param vAddDays : 현재일로부터 +- 날짜(일수) 숫자인 경우에는 오늘 날짜에서 더한 일수, -숫자인 경우에는 오늘 날짜에서 -일 수만큼 전일
	 * @param 버튼과 텍스트 필드의 표시 여부 default:both , button:버튼만, text:텍스트만 
	 * 예)com.datepicker("calendar1")[디폴트], com.datepicker("calendar1", 1 ) 오늘 날짜에서 하루 더한 날 
	 */
  datepickerToDate : function(vCalId, vAddDays, vOpt){
	  
	  var showOn = vOpt;
	  if(showOn==undefined || showOn=="undefined"){
		  showOn='both';		// 버튼 과 텍스트 둘다 보여주기
	  }
	  
	  var dateOptions = { 
			    showOn: showOn,
			    buttonImage: '/images/oklms/adm/inc/calendar_btn_01.png', // 버튼 이미지
			    buttonImageOnly: true, 
			    changeMonth:  true,   
			    changeYear:true,  
			    showButtonPanel:true,
			    showOtherMonths: true,
			    showMonthAfterYear:true,
			    selectOtherMonths: true,
			    currentText: '오늘 날짜' ,
			    closeText: '닫기', 
			    showAnim: 'slideDown',
			    dateFormat: 'yy-mm-dd',
			    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'],
			    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			    buttonText : "날짜 선택"
			}; 
		
	  var $_cal = $("input[name='" + vCalId + "']");
	  if($_cal.attr("name")==undefined || $_cal.attr("name")=="undefined"){
		  $_cal = $("#"+vCalId);
	  }
	// input mask 처리
	  $_cal.mask('9999-99-99');
	  
	  var today = new Date();
	  if( vAddDays!=undefined ){
		  today.setDate( today.getDate() + vAddDays  );
	  }
	  
	  $_cal.datepicker(dateOptions).datepicker("setDate", today);
	  
	  // 오늘날짜 클릭시 발생되는 이벤트
	  $.datepicker._gotoToday = function(id){
		  var currentDate = new Date();
		  $(id).datepicker("setDate", currentDate);
//		  $(this)._hideDatepicker();
		  this._hideDatepicker();
	  }
  },
  
  /**
	 * input에 달력 component 날짜 지정[default:오늘날짜] 
	 * @param vCalId : calendar id명 또는 name
	 * @param vAddDays : 현재일로부터 +- 날짜(일수) 숫자인 경우에는 오늘 날짜에서 더한 일수, -숫자인 경우에는 오늘 날짜에서 -일 수만큼 전일
	 * @param 버튼과 텍스트 필드의 표시 여부 default:both , button:버튼만, text:텍스트만
	 * @param Event : Event 실행 처리 예)doAction('gridSearch')
	 * 예)com.datepicker("calendar1")[디폴트], com.datepicker("calendar1", 1 ) 오늘 날짜에서 하루 더한 날 
	 */
	datepickerToDateEvent : function(vCalId, vAddDays, vOpt, vEvent){
		  
		  var showOn = vOpt;
		  if(showOn==undefined || showOn=="undefined"){
			  showOn='both';		// 버튼 과 텍스트 둘다 보여주기
		  }
		  
		  var dateOptions = { 
				    showOn: showOn,
				    buttonImage: '/images/oklms/adm/inc/calendar_btn_01.png', // 버튼 이미지
				    buttonImageOnly: true, 
				    changeMonth:  true,   
				    changeYear:true,  
				    showButtonPanel:true,
				    showOtherMonths: true,
				    showMonthAfterYear:true,
				    selectOtherMonths: true,
				    currentText: '오늘 날짜' ,
				    closeText: '닫기', 
				    showAnim: 'slideDown',
				    dateFormat: 'yy-mm-dd',
				    dayNamesMin: ['일','월', '화', '수', '목', '금', '토'],
				    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				    buttonText : "날짜 선택"
				}; 
			
		  var $_cal = $("input[name='" + vCalId + "']");
		  if($_cal.attr("name")==undefined || $_cal.attr("name")=="undefined"){
			  $_cal = $("#"+vCalId);
		  }
		// input mask 처리
		  $_cal.mask('9999-99-99');
		  
		  var today = new Date();
		  if( vAddDays!=undefined ){
			  today.setDate( today.getDate() + vAddDays  );
		  }
		  
		  
		  
		  // Event 처리
		  var enterEvent = vEvent;
		  if(enterEvent!=undefined && enterEvent!="undefined"){
			// input Type이 Text인 경우 Enter Key 입력시 조회 
			  $("input[name='" + vCalId + "']").keydown(function (key){
					if(key.keyCode == "13") {
						doAction(eval(enterEvent));
					}
				});
		  }
		  
		  $_cal.datepicker(dateOptions).datepicker("setDate", today);
		  
		  // 오늘날짜 클릭시 발생되는 이벤트
		  $.datepicker._gotoToday = function(id){
			  var currentDate = new Date();
			  $(id).datepicker("setDate", currentDate);
//			  $(this)._hideDatepicker();
			  this._hideDatepicker();
		  }
	},
  
  /******************************************************************************
	 * Function Name : diffDate
	 * Description   : startDate 와 endDate의 차이 일수 계산
	 * Parameter     : startDate : 년월일
	 * Parameter     : endDate : 년월일
	 * Return        : - 성공 = 숫자 형태의 차이일수  ( 예 : 10 ) 
             				단, sEndDate < sStartDate이면 음수가 return된다. 
    					- 실패 = 음수값
	 * Example       : diffDate("20160331", "20160401") 1,  diffDate("2016-03-31", "201-04-01") 
	 *****************************************************************************/
  diffDate : function (startDate, endDate){
	  var vStartDate = startDate;
	  var vEndDate = endDate;
	  
	  vStartDate = vStartDate.split("-").join("");
	  vStartDate = vStartDate.split(".").join("");
	  
	  vEndDate = vEndDate.split("-").join("");
	  vEndDate = vEndDate.split(".").join("");
	  
	  var vFromDate = new Date(parseInt(vEndDate.substring(0,4),  10), parseInt(vEndDate.substring(4,6)-1,  10), parseInt(vEndDate.substring(6,8), 10));
	  var vToDate = new Date(parseInt(vStartDate.substring(0,4),  10), parseInt(vStartDate.substring(4,6)-1,  10), parseInt(vStartDate.substring(6,8), 10));
	  
	  return parseInt((vFromDate - vToDate)/(1000*60*60*24));
  },
     /******************************************************************************
	 * Function Name : isMinValue
	 * obj           : value값 
	 * minLength     : 최소 길이 
	 * Description   : 값, 최소길이를 받아서 최소길이를 넘어서는지 확인 
	 *****************************************************************************/
	isMinValue : function(obj, minLength){
		return ($.trim(obj).length < minLength || !obj);
	},
	
	
	 /******************************************************************************
	 * Function Name : replaceAll
	 * vStr          : 원본 문자 또는 문자열
	 * vSearchStr    : 찾을 문자
	 * vReplaceStr	 : 치환할 문자
	 * Description   : 문자열 모두 치환
	 *****************************************************************************/
	replaceAll : function( vStr, vSearchStr, vReplaceStr){
		var reg =  new RegExp( vSearchStr, "gi");
		return vStr.replace(reg, vReplaceStr );
	},
	
	/******************************************************************************
	 * Function Name : toDefaultString
	 * vStr          : 원본 문자 또는 문자열
	 * vDefStr    	 : 대체할 문자(디폴트 공백)
	 * Description   : 문자가 null 값 또는 undefined일 경우 대체문자로 교환하기(defalut : 공백("")
	 *****************************************************************************/
	toDefaultString : function( vStr, vDefStr ){
		var retStr = vDefStr;
		if( vDefStr==undefined || vDefStr=="undefined" ){
			retStr = "";
		}
		if( vStr==null || vStr==undefined || vStr=="undefined" ){
			return retStr;
		}
		return vStr;
	}
	,
	
	/******************************************************************************
	 * Function Name : checkAll
	 * idStr          : 전체 선택 첵크박스 id
	 * inputNameStr    	 : 첵크되어야할 첵크박스 name
	 * Description   : idStr 의 첵크 여부를 기준으로 inputNameStr 의 첵크박스 들을 전체 선택 or 전체 비선택 처리함. 
	 *****************************************************************************/
	checkAll : function (idStr , inputNameStr){

		var isChecked = false;
		if( $('input:checkbox[id="' + idStr + '"]').is(":checked") == true ){
			isChecked = true;
		}
		
		com.log("isChecked : " + isChecked + " , idStr=" + idStr + " , inputNameStr=" + inputNameStr );
		
		if( isChecked ){

			com.log("isChecked 1: " + isChecked);
			// 체크 박스 모두 체크
			$("input[name=" + inputNameStr + "]:checkbox").each(function() {
				com.log("isChecked 11: " + isChecked);
//				$(this).attr("checked", true);
				this.checked=true;
			});
		}else{

			com.log("isChecked 2: " + isChecked);
			// 체크 박스 모두 해제
			$("input[name=" + inputNameStr + "]:checkbox").each(function() {
				com.log("isChecked 22: " + isChecked);
//				$(this).attr("checked", false);
				this.checked=false;
			});
		}
	}
	, 
	
	/******************************************************************************
	 * Function Name : getCheckedVal
	 * idStr          : 전체 선택 첵크박스 id
	 * inputNameStr    	 : 첵크되어야할 첵크박스 name
	 * Description   : 첵크된 항목의 value 값을 ","  구분자로 붙여서 return 한다.
	 *****************************************************************************/
	getCheckedVal : function(idStr , inputNameStr) {
		
		var isChecked = false;
		if( $('input:checkbox[id="' + idStr + '"]').is(":checked") == true ){
			isChecked = true;
		}
		
		var checkedVals="";
		// 체크 되어 있는 값 추출
		$("input[name=" + inputNameStr + "]:checked").each(function() {
			var test = $(this).val();
			console.log(test);
			if( "" != checkedVals ){
				checkedVals = checkedVals + "," + test;
			}else{
				checkedVals = checkedVals + test;
			}
		});
		return checkedVals;
	},
	
	/******************************************************************************
	 * Function Name : checkRequiredField
	 * obj          : input field에 체크할 id명
	 * Description   : com.checkRequiredField( $("#className_new")
	 *****************************************************************************/
	  checkRequiredField : function(obj){  
		var isValid = false;

		if( $(obj).val() ){
			isValid = true;
		}else{
			alert( getMessage( "REQUIRED_FIELD" ) );
			$(obj).focus();
		}
		
		return isValid;
	  }
  
};

var frm = {
	$_loopCnt : 1,
	$_iframe : null,
	$_form : null,

	setLoopCnt : function(loopCnt){
		this.$_loopCnt = loopCnt;
	},
	getLoopCnt : function(){
		return this.$_loopCnt;
	},
	
	init : function(_url){
		//동적으로 iframe 생성
		this.$_iframe = $("#transIFrame");
		if(this.$_iframe.length < 1){
			this.$_iframe = $("<iframe id='transIFrame' name='transIFrame' frameBorder='0' scrolling='no' width='300' height='150'></iframe>");
			$(document.body).append(this.$_iframe);
		}
			
		//동적으로 다운로드용 form 생성
		this.$_form = $("#transForm");
		if(this.$_form.length < 1) {
			this.$_form = $("<form id='transForm', name='transForm' method='post', action='"+_url+"' target='transIFrame' enctype='multipart/form-data'></form>");
		  	$(document.body).append(this.$_form);
		}
		
		//이전에 처리한 다운로드파일정보 삭제
		this.$_form.empty();
	},
	
	clone : function(_type, _id){
		$('#'+_id).clone().appendTo(this.$_form);
	},
	
	append : function(_type, _name, _value){
		//다운로드파일정보 세팅
		$("<input></input>").attr({type:_type, name:_name, value:$.trim(_value)}).appendTo(this.$_form);
	},
	
	submit : function(){
		this.$_form.submit();
	},
	
	success : function(){
		try{
			this.$_loopCnt -= 1;
			eventTransSuccess(this.$_loopCnt);
		}catch(e){
			this.$_loopCnt = -1;
			alert("eventTransSuccess() 함수가 없습니다.");
		}
	},
	
	fail : function(){
		
	},
	
	setPopupWindowResize : function() {
		
		
		 var thisX = parseInt(document.body.scrollWidth);
		 var thisY = parseInt(document.body.scrollHeight);
		 //var thisX = parseInt(document.getElementById(id).scrollWidth);
		 //var thisY = parseInt(document.getElementById(id).scrollHeight);
		 
		 
		 var maxThisX = screen.width - 50;
		 var maxThisY = screen.height - 50;
		 var marginY = 0;
		// alert(thisX + "===" + thisY);
		 //alert!("임시 브라우저 확인 : " + navigator.userAgent);
		 // 브라우저별 높이 조절. (표준 창 하에서 조절해 주십시오.)
		 if (navigator.userAgent.indexOf("MSIE 6") > 0) marginY = 45;        // IE 6.x
		 else if(navigator.userAgent.indexOf("MSIE 7") > 0) marginY = 75;    // IE 7.x
		 else if(navigator.userAgent.indexOf("MSIE 9") > 0) marginY = 80;    // IE 9.x
		 else if(navigator.userAgent.indexOf("Firefox") > 0) marginY = 80;   // FF
		 else if(navigator.userAgent.indexOf("Opera") > 0) marginY = 30;     // Opera
		 else if(navigator.userAgent.indexOf("Chrome") > 0) marginY = 70;     // Chrome
		 else if(navigator.userAgent.indexOf("Netscape") > 0) marginY = -2;  // Netscape

		 if (thisX > maxThisX) {
		  window.document.body.scroll = "yes";
		  thisX = maxThisX;
		 }
		 if (thisY > maxThisY - marginY) {
		  window.document.body.scroll = "yes";
		  thisX += 19;
		  thisY = maxThisY - marginY;
		 }
		 window.resizeTo(thisX+10, thisY+marginY);

		 var windowX = (screen.width - (thisX+10))/2;
		 var windowY = (screen.height - (thisY+marginY))/2 - 20;
		 window.moveTo(windowX,windowY);
	} ,
	
	
	// 사이즈 관련해서 수정
	setPopupWindowResizeNew : function() {
		
		
		 var thisX = 0 ;
		 var thisY = 0 ;
		 // var thisY = parseInt(document.body.scrollHeight);
		 //var thisX = parseInt(document.getElementById(id).scrollWidth);
		 //var thisY = parseInt(document.getElementById(id).scrollHeight);
		 
		 thisX = parseInt(document.body.scrollWidth);
		 thisY = parseInt(document.body.scrollHeight);
		 
		 var maxThisX = screen.width - 50;
		 var maxThisY = screen.height - 50;
		 var marginY = 0;
		 
		 // alert("임시 브라우저 확인 : " + navigator.userAgent);
		 // alert("MSIE 8==" + navigator.userAgent.indexOf("MSIE 8"));
		 // 브라우저별 높이 조절. (표준 창 하에서 조절해 주십시오.)
		 if (navigator.userAgent.indexOf("MSIE 6") > 0) marginY = 45;        // IE 6.x
		 else if(navigator.userAgent.indexOf("MSIE 7") > 0) marginY = 75;    // IE 7.x
		 else if(navigator.userAgent.indexOf("MSIE 8") > 0) marginY = 93;    // IE 8.x
		 else if(navigator.userAgent.indexOf("MSIE 9") > 0) marginY = 93;    // IE 9.x
		 else if(navigator.userAgent.indexOf("MSIE 10") > 0) marginY = 93;    // IE 10.x
		 else if(navigator.userAgent.indexOf("MSIE 11") > 0) marginY = 93;    // IE 11 호환성 보기 
		 else if(navigator.userAgent.indexOf("Firefox") > 0) marginY = 70;   // FF
		 else if(navigator.userAgent.indexOf("Opera") > 0) marginY = 30;     // Opera
		 else if(navigator.userAgent.indexOf("Chrome") > 0) marginY = 70;     // Chrome
		 else if(navigator.appName=='Netscape' && navigator.userAgent.indexOf("Trident") > 0) marginY = 83;  //IE 11
		 else if(navigator.userAgent.indexOf("Netscape") > 0) marginY = -2;  // Netscape
		 

		 if (thisX > maxThisX) {
		  window.document.body.scroll = "yes";
		  thisX = maxThisX;
		 }
		 if (thisY > maxThisY - marginY) {
		  window.document.body.scroll = "yes";
		  thisX += 19;
		  thisY = maxThisY - marginY;
		 }
		 
		 window.resizeTo(thisX+10, thisY+marginY);
		 
		 var windowX = (screen.width - (thisX+10))/2;
		 var windowY = (screen.height - (thisY+marginY))/2 - 20;
		 window.moveTo(windowX,windowY);
	}
};

/**
 * 파일명에서 확장자명 추출
 * @param filename   파일명
 * @returns _fileExt 확장자명
 */
function getExtensionOfFilename(filename) {

	var _fileLen = filename.length;

	/** 
	 * lastIndexOf('.') 
	 * 뒤에서부터 '.'의 위치를 찾기위한 함수
	 * 검색 문자의 위치를 반환한다.
	 * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
	 */
	var _lastDot = filename.lastIndexOf('.');

	// 확장자 명만 추출한 후 소문자로 변경
	var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();

	return _fileExt;
}

/**
 * table row 병합
여러 colum 을 병합하려 할 경우. 뒤(우측)부터 순차적으로 해야 올바르게 작동 됨.
 * */
function mergeCommonRows(table, columnIndexToMerge) {
	previous = null;
	cellToExtend = null;
	col1 = null;
	table.find("td:nth-child(" + columnIndexToMerge + ")").each(function() {
		jthis = $(this);
		content1 = jthis.parent('tr').children().first().text();
		if (col1 !== content1) {
			previous = null;
			col1 = content1;
		}
		content = jthis.text()
		if (previous == content && content !== "") {
			jthis.remove();
			if (cellToExtend.attr("rowspan") == undefined) {
				cellToExtend.attr("rowspan", 2);
			} else {
				currentrowspan = parseInt(cellToExtend.attr("rowspan"));
				cellToExtend.attr("rowspan", currentrowspan + 1);
			}
		} else {
			previous = content;
			cellToExtend = jthis;
		}
	});
}