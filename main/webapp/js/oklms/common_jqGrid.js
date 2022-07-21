/**
 * Class Name : common_jqGrid.js 
 * Description : jqGrid를 이용하는 사이트에서 사용되는 공통기능 제공
 * Modification Information
 *
 *            수정일                     수정자                   수정내용
 *   ----------------------------------------------- *   
 *   author   : 공통 이진근
 *   since    : 2016.11.21 
 */
//var vConsole = window.cosole || { log:function(){}};
//var vConsole;
//if (typeof console == "undefined") {
//	vConsole = window.console || { log:function(){}};
//}else{
//	vConsole = window.console; 
//}
var com_jqGrid = {
	
	/**
	 * 웹접근성을 위한 jqgrid table에 caption 추가
	 * jqgrid를 사용하는 table name 또는 id 
	 */
	caption : function( vGridId , vCaption ){
		
		var $_grid = $("table[name='" + vGridId + "']");
		if($_grid.attr("name")==undefined || $_grid.attr("name")=="undefined"){
			$_grid = $("#"+vGridId);
		}
		$_grid.parent().parent().parent().find("table.ui-jqgrid-htable").each(function(){
			$(this).prepend($("<caption></caption>").text(vCaption));
		});
	}	
};
