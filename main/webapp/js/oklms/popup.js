/******************************************************************************
 *   Common JavaScript
 *	 팝업 정의
 *
 *   @version 1.0  
 *
 *   @author  COPYRIGHT (C)2016 SMART INFORMATION TECHNOLOGY. ALL RIGHT RESERVED.
 *****************************************************************************/

/**
 * open popup window
 *
 * @param		sURL	url
 * @param		sWidth	window width(optional)
 * @param		sHeight	window height(optional)
 * @return		window	object
 *
 * @example		openWindow('blank.html');
 *              openWindow('blank.html', 200, 100);
 *              var oWin = openWindow('blank.html'); alert(oWin.name);
 */
function openWindow(sURL) {
	var sWidth, sHeight;
	var sFeatures;
	var oWindow;

	sWidth	= 400;
	sHeight	= 300;

	if (arguments[1] != null && arguments[1] != "") sWidth = arguments[1] ;
	if (arguments[2] != null && arguments[2] != "") sHeight = arguments[2] ;

	sFeatures =  "width=" + sWidth + ",height=" + sHeight ;
	sFeatures += ",left=3000,top=3000" ;
	sFeatures += ",directories=no,location=no,menubar=no,resizable=yes,scrollbars=no,status=no,titlebar=no,toolbar=no";

	oWindow = window.open(sURL, "PopupWindow", sFeatures);
	oWindow.focus();

	// move to screen center
	oWindow.moveTo( (window.screen.availWidth - sWidth) / 2, (window.screen.availHeight - sHeight) / 2);

	return oWindow;
}

/**
 * KVOC open popup window
 */
function popOpenWindow(url,wndName,widWidth,winHeight,top,left, features){
	var wndHelp;
	var wnd;
	if (top==null){
		top = "22";
	}
	if (left==null)	{
		left = "3";
	}
	if(features == null) {
		features = "";
	}
	if(wndHelp!=null){
		wndHelp.close();
	}

	wndHelp=window.open(url,wndName,"width=" + widWidth + ", height=" + winHeight + ", top=" + top + ", left=" + left + ",resizable=yes,scrollbars=yes," + features);
	wndHelp.focus();

	return wndHelp;
}


/**
 * open modal window
 *
 * @param		sURL		url
 * @param		sWidth		window width(optional)
 * @param		sHeight		window height(optional)
 * @param		vArguments	passed Arguments(optional)
 * @return	window object
 *
 * ex) openModalWindow('popup_test1.htm')
 *     openModalWindow('popup_test1.htm','600','400','aaa')
 *     openModalWindow('popup_test1.htm','600','400','aaa','bbb','ccc')
 */
function openModalWindow (sURL)
{
	var sWidth, sHeight;
	var sFeatures;
	var oWindow;
	var vArguments = new Array();
	var POPUP_HEIGHT = 100;
	var POPUP_WIDTH = 200;
	sHeight	= POPUP_HEIGHT + "px";
	sWidth	= POPUP_WIDTH + "px";

	if (arguments[1] != null && arguments[1] != "") sWidth = arguments[1] + "px" ;
	if (arguments[2] != null && arguments[2] != "") sHeight = arguments[2] + "px" ;

	//vArguments[0] = window.dialogArguments[0];

	for (var i=3; i<arguments.length; i++) {
		vArguments[i-3] = arguments[i] ;
	}

	sFeatures =  "dialogWidth:" + sWidth + "; dialogHeight:" + sHeight ;
	sFeatures += ";center:yes;resizable:yes;scroll:auto;status:no";
	sFeatures += arguments[3];

	oWindow = window.showModalDialog(sURL, vArguments, sFeatures);

	return oWindow;
}

/**
 * OpenNewWin
 *
 * @param		servlet_name	url
 */
function OpenNewWin(servlet_name, opener_window_name, window_name, size_width, size_height,yn)
{
	window.name = opener_window_name;
	var paraMeter = "scrollbars="+yn+", resizable=yes, width=" + size_width + ", height=" + size_height+ ", top=" +((screen.height/2)-( size_height/2 ))+ ", left=" +((screen.width/2)-( size_width/2 ));
	var newWin = window.open(servlet_name, window_name, paraMeter);

	return newWin;
}
function OpenNewWinForMail(servlet_name, opener_window_name, window_name, size_width, size_height,yn)
{
	window.name = opener_window_name;
	var paraMeter = "scrollbars="+yn+", resizable=yes, width=" + size_width + ", height=" + size_height+ ", top=" +((screen.height/3)-( size_height/2 ))+ ", left=" +((screen.width/3)-( size_width/2 ));
	var newWin = window.open(servlet_name, window_name, paraMeter);

	return newWin;
}


/**
 * open calendar window
 *
 * @param		formName	target form name
 * @param		targetName	target object name, ???? INPUTBOX
 *
 * @example		openCalendar('searchForm', 'txtDate');
 */
//function openCalendar(formName, targetName)
function openCalendar(targetname)
{
	var objTarget = eval("document.all."+targetname);

	var targetvalue = objTarget.value;

	var calendarHeight 	= 235;
	var calendarWidth	= 215;
	var _top_offset		= 16;
	var _left_offset	= 0;
	var _top	= 0;
	var _left	= 0;
	var _x 		= _getPosX(objTarget)+window.screenLeft ;
	var _y 		= _getPosY(objTarget)+window.screenTop ;

	_x-=window.document.body.scrollLeft;
	_y-=window.document.body.scrollTop;

	if ( _y + calendarHeight + _top_offset> window.screen.height)
		_top = window.screen.height - calendarHeight;
	else
		_top = _y + _top_offset ;

	if (_x + calendarWidth + _left_offset> window.screen.width)
		_left = window.screen.width - calendarWidth;
	else
		_left = _x + _left_offset;

	var url ="/common/backup/calendar.jsp?init=Y"

	url += "&year="+targetvalue.substring(0,4);
	url += "&month="+targetvalue.substring(5,7);
	url += "&day="+targetvalue.substring(8,10);
	url += "&target_obj_nm="+targetname;

	curWinCtl = window.open( url, "calendar", "width="+calendarWidth+",height="+calendarHeight+",status=no,resizable=no, top="+_top+",left="+_left );

}

function _getPosX(obj)
{
	return( obj.offsetParent==null ? obj.offsetLeft : obj.offsetLeft+_getPosX(obj.offsetParent) );
}

function _getPosY(obj)
{
	return( obj.offsetParent==null ? obj.offsetTop : obj.offsetTop+_getPosY(obj.offsetParent) );
}

// 프로그래스바 팝업
function openProgressPop( contextName ){
	// type별로 수정할것 lms or lcms
	openWindow( contextName + "/default.do?method=goCustomView&viewUrl=lms/common/uploadProgress", 330, 150 );
}
//LCMS 용
function openProgressPopLc( contextName ){
	openWindow( contextName + "/default.do?method=goCustomView&viewUrl=lcms/common/uploadProgress", 330, 130 );
}
//영문싸이트용
function openProgressPopEng( contextName ){
	openWindow( contextName + "/default.do?method=goCustomView&viewUrl=lmseng/common/uploadProgress", 330, 150 );
}