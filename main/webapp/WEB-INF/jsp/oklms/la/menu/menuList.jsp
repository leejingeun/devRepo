<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 02 오전 10:00         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/jquery.jqgrid.src.js"></script>
<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/grid.locale-kr.js"></script>
<script type="text/javascript" src="${contextRoot }/js/oklms/common_jqGrid.js"></script>

<style>
<!--
.treeMenu {
	border:solid 1px #dddddd;
	padding:20px 20px;
	width:355px;
	display:inline-block;
	min-height:100px;
}
.tree_rightcon {
	width:400px;
	display:inline-block;
	float:right;
}
.tree_leftcon {
	width:400px;
	display:inline-block;
	float:left;
	margin-right:10px;
}
-->
</style>

<script type="text/javascript" src="${contextRoot}/js/jquery/jquery.validate.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		loadValidator();
		loadPage();
	});

	function loadValidator(){
		$.validator.setDefaults({
			onkeyup: false
			, onclick: false
			, onfocusout: false
			, showErrors: function(errorMap, errorList) {
				if(errorList.length < 1)
					return;
						
				alert(errorList[0].message);
				errorList[0].element.focus();
			}
		});

		$("#frmHtml").validate({
			messages: {
    			menuTitle: {
    				required: '<spring:message code="errors.required" arguments="메뉴명" />'
				},
				menuOrder: {
    				required: '<spring:message code="errors.required" arguments="메뉴순서" />',
       				digits: '<spring:message code="errors.number" arguments="메뉴순서" />'
    			},
    			menuDepth :{
    				required: '<spring:message code="errors.required" arguments="menuDepth" />',
    			},
    			menuStatus :{
    				required: '<spring:message code="errors.required" arguments="메뉴상태" />',
    			},
    			menuOrder :{
    				required: '<spring:message code="errors.required" arguments="메뉴순사" />',
    			},
    			menuViewTypeCode :{
    				required: '<spring:message code="errors.required" arguments="메뉴유형" />',
    			},
     			menuArea :{
    				required: '<spring:message code="errors.required" arguments="menuArea" />',
    			}, 
    			menuType :{
    				required: '<spring:message code="errors.required" arguments="menuType" />',
    			}
			}
		});
	}
	
	/*====================================================================
		jqGrid 및 화면 초기화 
	====================================================================*/
	function loadPage() {
		initSheet();
		initEvent();
		initHtml();
	}

	function initSheet(){

		var COL_MODEL = [
					{ label : '메뉴코드',  		name : 'menuId',   			width : 110,  align : 'center' , hidden:true },
					{ label : '메뉴명',    		name : 'menuTitle',    		width : 338,    align : 'left'   },
					{ label : '메뉴URL',    	name : 'menuUrl',   		width : 310,    align : 'left' , hidden:true    },
					{ label : '상위메뉴',    	name : 'upMenuId',   		width : 310,    align : 'left' , hidden:true    },
					{ label : '상위메뉴명',    	name : 'upMenuTitle',   	width : 310,    align : 'left' , hidden:true    },
					{ label : '메뉴깊이',    	name : 'menuDepth',   	width : 310,    align : 'left' , hidden:true    },
					{ label : '메뉴상태',    	name : 'menuStatus',   	width : 310,    align : 'left' , hidden:true    },
					{ label : '메뉴순서',      	name : 'menuOrder',      	width : 90,  align : 'center' , hidden:true  },
					{ label : '메뉴유형',      	name : 'menuViewTypeCode',  width : 90,  align : 'center' , hidden:true  },
					{ label : 'menuArea',      	name : 'menuArea',  width : 90,  align : 'center' , hidden:true  },
					{ label : 'menuType',      	name : 'menuType',  width : 90,  align : 'center' , hidden:true  },
					{ label : '삭제여부',      	name : 'deleteYn',        		width : 90,   align : 'center' , hidden:true  },
					{ label : '보기여부',      	name : 'showYn',        		width : 90,   align : 'center' , hidden:true  },
					{ label : '생성 권한 URL 패턴',      	name : 'createAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '상세 조회 권한 URL 패턴',    name : 'readAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '수정 권한 URL 패턴',      	name : 'updateAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '삭제 권한 URL 패턴',      	name : 'deleteAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '출력 권한 URL 패턴',      	name : 'printAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '다운로드 권한 URL 패턴',     name : 'downloadAuthUrlPattern',        	width : 90,   align : 'center' , hidden:true  },				 
					{ label : '기타 권한 URL 패턴',      	name : 'otherAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '목록 조회 권한 URL 패턴',    name : 'listAuthUrlPattern',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : 'expanded',      	name : 'expanded',     	width : 150,  align : 'center' , hidden:true    },
					{ label : 'isLeaf',      		name : 'isLeaf',     			width : 150,  align : 'center' , hidden:true    },
					{ label : 'loaded',      	name : 'loaded',     			width : 150,  align : 'center' , hidden:true    }
					];

		var myGrid01 = $("#myGrid01");
	
		myGrid01.jqGrid({
		    mtype : 'post',
		    colModel: COL_MODEL,
		    gridview: true,
		    sortname: 'menuUrl',
		    treeGrid: true,
		    loadonce: true,
		    treeGridModel: 'adjacency',
		    treedatatype: 'post',
		    ExpandColumn: 'menuTitle',
		    height: '500px',
		    width: '355px',
			shrinkToFit : false,
		    treeReader:{
		    	level_field: "menuDepth"
		    	, parent_id_field: "upMenuId"
		    },
		    localReader:{
		    	id: "menuId"
		    },
		    onSelectRow: function(rowId, status, e) {
				lastSel=rowId; // jqg1 , jqg2 , jqg3
			    if (rowId != null) {
					var cm = $(this).jqGrid('getGridParam', 'colModel');
			    	var rowData = $("#myGrid01").jqGrid('getRowData' ,rowId);
			    	if( !com.isBlank( rowData['menuId'] ) ){
						for( var iCol=0; iCol < cm.length; iCol++ ){
							var colNm = cm[iCol].name;
							if ( colNm == "deleteYn" ){				//radio
								$("#deleteYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "showYn" ){				//radio
								$("#showYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "menuArea" ){				//select
								$("#menuArea").val(rowData[colNm]).prop("selected", true);
							}else if ( colNm == "menuType" ){				//select
								$("#menuType").val(rowData[colNm]).prop("selected", true);
							}else{
								$("#" + colNm).val( rowData[colNm] );
							}
						}
			    	}
			    }
				
			}
		});
		
		doAction("gridSearch");				//그리드 그린 후 자동조회

        /* 웹접근성을 위한 TABLE CAPTION 추가 */
		com_jqGrid.caption( "myGrid01", "메뉴관리 목록" );
	}

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
		$("#searchMenuArea").change(function() {
			doAction("gridSearch");
		});
		$("#searchMenuType").change(function() {
			doAction("gridSearch");
		});
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
 		//doAction("gridSearch", pageIndex);
		doAction("gridSearch");
	}
	
	/*====================================================================
		조회버튼이나 페이지 클릭시 실행되는 함수는 꼭 doAction(sAction, pageNum)으로 만들어 사용해 주시기 바랍니다.
	====================================================================*/
	function doAction(sAction) {
		switch (sAction) {
	
		/* Grid 검색 */
		case "gridSearch":
			grid01_doAction("search");
			
			doAction('formReset');
			break;
			
		case "setDefaultURLFix":
			var menuUrl = $("#menuUrl").val();
			if( com.isBlank( menuUrl ) ){
				com.alert( "메뉴 URL 값을 입력하세요.");
			}else{				
				$("#listAuthUrlPattern").val(menuUrl);
				$("#createAuthUrlPattern").val(menuUrl);
				$("#readAuthUrlPattern").val(menuUrl);
				$("#updateAuthUrlPattern").val(menuUrl);
				$("#deleteAuthUrlPattern").val(menuUrl);
				$("#printAuthUrlPattern").val(menuUrl);				
				$("#downloadAuthUrlPattern").val(menuUrl); 					
				$("#otherAuthUrlPattern").val(menuUrl);
			}
			
			break;
			
		case "setDefaultURLPattern":
			if( com.isBlank( $("#menuUrlPattern").val() ) ) {
				com.alert("메뉴 URL 패턴 입력하세요.(대소문자 구분하여 입력하세요)\n 예) 메뉴 링크된 URL \n /la/example/listMenuInfo.do 와 같은경우 \n /la/example#Menu \n\n /la/example/selectBoardInfo.do?boardId=000000 \n 또는 \n /la/example/000000/selectBoardInfo.do 와 같은경우 \n .do제거 \n /la/example#Board?boardId=000000");
			}else{
				var menuUrlPattern = $("#menuUrlPattern").val();
				var patternSplit = menuUrlPattern.split( '#' );
				if( 2 == patternSplit.length ){

// 					var prefix = "^";
					var prefix = "";
					var path01 = patternSplit[0];
					var path02 = patternSplit[1];
					var path03 = "";
// 					var suffix = ".*";
					var suffix = ".*[.]((do)|(json))";

					var listAuthUrlPattern = "";
					
					var createAuthUrlPattern = "";
					var readAuthUrlPattern = "";
					var updateAuthUrlPattern = "";
					var deleteAuthUrlPattern = "";
					var printAuthUrlPattern = "";
					var downloadAuthUrlPattern = "";
					
					var otherAuthUrlPattern = "";
					
					if(patternSplit[1].toLowerCase().indexOf("?") >= 0){
						// '/la/example#board?boardId=A00000000000003'
						var patternSplit2 = patternSplit[1].split("?");	
						
						path02 = patternSplit2[0];
						path03 = patternSplit2[1];
						
						var firstParam;
						if( path03.indexOf("&") > 0 ){
							var firstParamObj = path03.split("&");
							firstParam = firstParamObj[0];
						}else{
							firstParam = path03;
						}
						var firstPath03 = firstParam.split("=");
						var firstPath03Key = "";
						var firstPath03Value = "";
						if( 1 < firstPath03.length){
							firstPath03Key = firstPath03[0];
							firstPath03Value = firstPath03[1];
						}
						
						if( !com.isBlank(firstPath03Value) ){
// 아래와 같은 형태의 URL을 첵크하는 정규식 : ((/lu/cop/bbs(()|(/anonymous))/BBSMSTR_000000000004/((list)|(select))Board.*[.]((do)|(json)))|(/lu/cop/bbs(()|(/anonymous))/((get)|(select))Board.*[.]((do)|(json))[?]bbsId=BBSMSTR_000000000004.*))
// 		/lu/cop/bbs/BBSMSTR_000000000004/selectBoardList.do
// 		/lu/cop/bbs/BBSMSTR_000000000004/selectBoardList.do?bbsId=BBSMSTR_000000000004
// 		/lu/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_000000000004
// 	(
// 		(/lu/cop/bbs(()|(/anonymous))/BBSMSTR_000000000004/((list)|(select))Board.*[.]((do)|(json))[?]bbsId=BBSMSTR_000000000004.*)
// 		|
// 		(/lu/cop/bbs(()|(/anonymous))/((get)|(select))Board.*[.]((do)|(json))[?]bbsId=BBSMSTR_000000000004.*)
// 		|
// 		(/lu/cop/bbs(()|(/anonymous))/BBSMSTR_000000000004/((list)|(select))Board.*[.]((do)|(json)))
// 	)

							// 목록조회만 키값을 경로에 포함시킨것과 GET방식 둘다 첵크함.
							var pattern01 = "(" + path01 + "/" + firstPath03Value + "/((list)|(select))" + path02 + suffix + "[?]" + path03 + ".*)";
							var pattern02 = "(" + path01 + "/((list)|(select))" + path02 + suffix + "[?]" + path03 + ".*)";
							var pattern03 = "(" + path01 + "/" + firstPath03Value + "/((list)|(select))" + path02 + suffix + ")";
							listAuthUrlPattern = prefix + "(" + pattern01 + "|" + pattern02 + "|" + pattern03 + ")";

		 					createAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/((addReply)|(reply)|(goInsert)|(insert)|(add)|(goSave)|(save))" + path02 + suffix;
							readAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/get" + path02 + suffix;
							updateAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/((update)|(goUpdate)|(forUpdate)|(goSave)|(save))" + path02 + suffix;
							deleteAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/delete" + path02 + suffix;
							printAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/print" + path02 + suffix;
							downloadAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/download" + path02 + suffix;
							
		 					otherAuthUrlPattern = prefix + path01 + "/" + firstPath03Value + "/((popup)|(excel))" + path02 + suffix;
		 					
						}else{
							
		 					listAuthUrlPattern = prefix + path01 + "/((list)|(select))" + path02 + suffix + "[?]" + path03; // 여러건 찾게되는 문제가 발생할 수 있는 패턴이므로 주의할것!!

		 					createAuthUrlPattern = prefix + path01 + "[/]((addReply)|(reply)|(goInsert)|(insert)|(add)|(goSave)|(save))" + path02 + suffix + "[?]" + path03;
							readAuthUrlPattern = prefix + path01 + "/get" + path02 + suffix + "[?]" + path03;
							updateAuthUrlPattern = prefix + path01 + "[/]((update)|(goUpdate)|(forUpdate)|(goSave)|(save))" + path02 + suffix + "[?]" + path03;
							deleteAuthUrlPattern = prefix + path01 + "/delete" + path02 + suffix + "[?]" + path03;
							printAuthUrlPattern = prefix + path01 + "/print" + path02 + suffix + "[?]" + path03;
							downloadAuthUrlPattern = prefix + path01 + "/download" + path02 + suffix + "[?]" + path03;
							
		 					otherAuthUrlPattern = prefix + path01 + "[/]((popup)|(excel))" + path02 + suffix + "[?]" + path03;
						}
						
						
					}else{
						listAuthUrlPattern = prefix + path01 + "/((list)|(select))" + path02 + suffix + ".*";
						
						createAuthUrlPattern = prefix + path01 + "[/]((goInsert)|(insert)|(add)|(goSave)|(save))" + path02 + suffix + ".*";
						readAuthUrlPattern = prefix + path01 + "/get" + path02 + suffix + ".*";
						updateAuthUrlPattern = prefix + path01 + "[/]((update)|(goUpdate)|(forUpdate)|(goSave)|(save))" + path02 + suffix + ".*";
						deleteAuthUrlPattern = prefix + path01 + "/delete" + path02 + suffix + ".*";
						printAuthUrlPattern = prefix + path01 + "/print" + path02 + suffix + ".*";
						downloadAuthUrlPattern = prefix + path01 + "/download" + path02 + suffix + ".*";
						
						otherAuthUrlPattern = prefix + path01 + "[/]((popup)|(excel))" + path02 + suffix + ".*";
					}
					
					$("#createAuthUrlPattern").val(createAuthUrlPattern);
					$("#readAuthUrlPattern").val(readAuthUrlPattern);
					$("#updateAuthUrlPattern").val(updateAuthUrlPattern);
					$("#deleteAuthUrlPattern").val(deleteAuthUrlPattern);
					$("#printAuthUrlPattern").val(printAuthUrlPattern);				
					$("#downloadAuthUrlPattern").val(downloadAuthUrlPattern); 					
					$("#otherAuthUrlPattern").val(otherAuthUrlPattern);
					$("#listAuthUrlPattern").val(listAuthUrlPattern);
				}
			}
			break;
			
			
		/* 상위메뉴 선택 팝업 */
		case "seachUperMenuPopup":
			var reqUrl = "/la/menu/uperMenuList.do";
			var params = "";
			var searchMenuAreaVal = $("#searchMenuArea").val();
			var searchMenuTypeVal = $("#searchMenuType").val();
			$("#menuArea").val( searchMenuAreaVal );
			$("#menuType").val( searchMenuTypeVal );
			if( !com.isBlank( searchMenuAreaVal ) ){
				params = "?searchMenuArea=" + searchMenuAreaVal;
			}
			if( !com.isBlank( searchMenuTypeVal ) ){
				if( com.isBlank( searchMenuAreaVal ) ){
					params = params + "?";
				}else{
					params = params + "&";
				}
				params = params + "searchMenuType=" + searchMenuTypeVal;
			}
			reqUrl = reqUrl + params;
			
			com.openPopup("searchMenuList", reqUrl, 425, 620);
			break;
			
		/* 최상위 메뉴로 셋팅 */
		case "setRoot":

			var searchMenuAreaVal = $("#searchMenuArea").val();
			var searchMenuTypeVal = $("#searchMenuType").val();
			
			$("#upMenuId").val("TOP");
			$("#upMenuTitle").val("최상위");
			$("#menuDepth").val("1");
			$("#menuArea").val( searchMenuAreaVal );
			$("#menuType").val( searchMenuTypeVal );
			break;
		/* 입력 필드 초기화 */
		case "formReset":
			$("#frmHtml").find("input:text,input:hidden,select,textarea").val("");
			$("#useYsnoY").prop("checked", true);					//사용여부
			break;
			
		/* 입력 권한패턴 필드 초기화 */
		case "formAuthReset":
			$("#menuUrlPattern").val(""); 		
			$("#listAuthUrlPattern").val("");    
			$("#createAuthUrlPattern").val("");  
			$("#readAuthUrlPattern").val("");    
			$("#updateAuthUrlPattern").val("");  
			$("#deleteAuthUrlPattern").val("");  
			$("#printAuthUrlPattern").val("");   
			$("#downloadAuthUrlPattern").val("");
			$("#otherAuthUrlPattern").val("");   
			break;			
			
		/* HTML Form 신규, 수정, 삭제 대상 레코드 적용 */
		case "AJAX_formSave":
			grid01_doAction("save");
			break;
			
		default:
			break;
		}
	}
	

	/*====================================================================
		jqGrid 이벤트 처리 전용 함수
	====================================================================*/
	function grid01_doAction(sAction) {
	
		switch (sAction) {
		
		/* 조회 */
		case "search":
	
			var reqUrl = "/la/menu/listMenuTree.json";
			var param = $("#searchFrm").serializeArray();
			
			com.ajax( reqUrl, param , function(obj, resultData, textStatus, jqXHR){	
				if (200 == jqXHR.status ) {
					var gridArrayData = [];
					var itemList = resultData.rows;

                    $("#myGrid01")[0].addJSONData({ rows: {} });
                    $("#myGrid01")[0].addJSONData({ rows: itemList });
                    
				}
			}, {
	    		async : true,
		    		type  : "POST",
	    			timeout : 60000			// 기본 60초
	    	});
	
			break;
			
			case "save":

				if ( !$("#frmHtml").valid() )		return;			//validate
				
				var reqUrl = "/la/menu/saveMenu.json";
				var param = $("#frmHtml").serializeArray();
		
				com.ajax4confirm( "<spring:message code="common.save.msg" />" , reqUrl, param, function(obj, resultData, textStatus, jqXHR){						
					if (200 == jqXHR.status ) {
												
						com.alert( resultData.retMsg );
						
						if( "SUCCESS" == resultData.retCd ){
							doAction("gridSearch");
							return;
						} else if( "FAILE" == resultData.retCd ){
							return;
						}
					} else {
					}
		
				}, {
		    		async : true,
		    		type : "POST",
		    		spinner : true,
					errorCallback : null,
		    		timeout : 30000			// 기본 30초
		    	});
		
				break;
				
			default:
				break;
		}
	}
	
	function fnSelectMenuPopupAfter(map){
		var menuId = map['menuId'];
		var upMenuId = map['upMenuId'];
		var menuOrder = map['menuOrder'];
		var menuTitle = map['menuTitle'];
		var menuArea = map['menuArea'];
		var menuType = map['menuType'];
		var menuObj = map['menuObj'];

		//alert( "menuObj.menuTitle : " + menuObj.menuTitle );
		
/* 		alert( "menuId : " + menuId );
		alert( "menuTitle : " + menuTitle );
		alert( "menuArea : " + menuArea );
		alert( "menuType : " + menuType );
		alert( "menuObj : " + menuObj ); 
		
		alert( "menuId : " + menuId );
		alert( "upMenuId : " + upMenuId );
		alert( "menuTitle : " + menuTitle );
		alert( "menuOrder : " + menuOrder );
		*/
		
		$("#menuId").val(menuId);
		$("#menuIdTemp").val(menuId);
		
		$("#upMenuId").val(upMenuId);
		$("#menuOrder").val(menuOrder);
		$("#upMenuTitle").val(menuTitle);

		$("#searchMenuArea").val(menuArea);
		$("#searchMenuType").val(menuType);
		
		grid01_doAction("search");
    }
</script>
<div class="title-name-1" style="margin-top:40px;">메뉴 수정</div>

<ul class="code-box">						

	<li class="list-area" style="width:420px;">
		<div>
			<form id="searchFrm" name="searchFrm" method="post" action="">
				searchMenuArea : <select id="searchMenuArea" name="searchMenuArea">
					<option value="PC" selected="selected">PC</option>
					<option value="MOBILE">MOBILE</option>
				</select>
				<!-- <input type="hidden" name="searchMenuArea" id="searchMenuArea" value="LMS"/> -->
				searchMenuType : <select id="searchMenuType" name="searchMenuType">
					<option value="ADM" selected="selected">관리자</option>
					<option value="STD">학습근로자</option>
					<option value="PRT">담당교수</option>
					<option value="COT">기업현장교사</option>
					<option value="CCM">HRD전담자</option>
					<option value="CCN">센터전담자</option>
					<option value="CDP">학과전담자</option>
				</select>
			</form>
		</div>
		<div class="treeMenu">
			<table id="myGrid01"></table>
		</div>
	</li>
	
	<li class="code-area"  style="float:left;">
		<form id="frmHtml" name="frmHtml" method="post" action="">
			<table border="0" cellpadding="0" cellspacing="0" class="view-1">
				<tbody>
					<tr>
						<th>메뉴코드</th>
						<td><input type="text" id="menuId" name="menuId" style="width:160px;" readonly title="메뉴코드 입력"></td>
					</tr>
					<tr>
						<th>메뉴명<span class="pre"></span></th>
						<td><input type="text" id="menuTitle" name="menuTitle" style="width:160px;" maxLength="33" title="메뉴명 입력"></td>
					</tr>
					<tr>
						<th>상위메뉴</th>
						<td>
							<input type="hidden" id="upMenuId" name="upMenuId">
							<input type="hidden" id="menuIdTemp" name="menuIdTemp">
							<input type="text" id="upMenuTitle" name="upMenuTitle" style="width:160px;" readonly title="상위메뉴 입력">
							<a class="btn" href="javascript://" onclick="doAction('seachUperMenuPopup');" title="상위메뉴 선택 팝업 열기">추가하위대상메뉴 찾기</a>
							<a class="btn" href="javascript://" onclick="doAction('setRoot');" title="최상위 메뉴">최상위 메뉴</a>
						</td>
					</tr>
					<tr>
						<th>메뉴깊이<span class="pre"></span></th>
						<td><input type="text" id="menuDepth" name="menuDepth" dir="rtl" maxLength="3" title="메뉴깊이"></td>
					</tr>
				<!-- <tr>
						<th>메뉴상태<span class="pre"></span></th>
						<td><input type="hidden" id="menuStatus" name="menuStatus" dir="rtl" maxLength="3" title="메뉴상태"></td>
					</tr> -->
					<tr>
						<th>메뉴순서<span class="pre"></span></th>
						<td>
							<input type="text" id="menuOrder" name="menuOrder" dir="rtl" maxLength="3" title="순서 입력">
							<input type="hidden" id="menuStatus" name="menuStatus" value="A">
						</td>
					</tr>
					<tr>
						<th>menuArea<span class="pre"></span></th>
						<td><select id="menuArea" name="menuArea" data-rule-required="true">
								<option value="" selected="selected">선택</option>
								<option value="PC">PC</option>
								<option value="MOBILE">MOBILE</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>menuType<span class="pre"></span></th>
						<td><select id="menuType" name="menuType" data-rule-required="true">
								<option value="" selected="selected">선택</option>
								<option value="ADM">관리자</option>
								<option value="STD">학습근로자</option>
								<option value="PRT">담당교수</option>
								<option value="COT">기업현장교사</option>
								<option value="CCM">HRD전담자</option>
								<option value="CCN">센터전담자</option>
								<option value="CDP">학과전담자</option>
							</select>
							<!-- <input type="hidden" name="menuArea" id="menuArea" value="LMS"/> -->
						</td>
					</tr>
					<tr>
						<th>메뉴유형<span class="pre"></span></th>
						<td>
							<select id="menuViewTypeCode" name="menuViewTypeCode" title="메뉴유형 선택" data-rule-required="true">
								<option value="" selected="selected">선택</option>
								<option value="2016CCOD0000011">메뉴 노출</option>
								<option value="2016CCOD0000012">메뉴 비노출(페이지:메인,사용자관련등등...)</option>
								<option value="2016CCOD0000013">메뉴 비노출(팝업)</option>
								<option value="2016CCOD0000014">메뉴 비노출(import)</option>
								<%--
							<c:forEach items="${comCodeList}" var="item" varStatus="status" >
								<option value="${item.comCodeUntId}" >${item.codeName}</option>
							</c:forEach>
								 --%>
							</select>
						</td>
					</tr>
					<tr>
						<th>메뉴 URL</th>
						<td>
							<input type="text" id="menuUrl" name="menuUrl" style="width: 100%;">
						</td>
					</tr>
					<tr><th>메뉴 URL 패턴 간편 처리</th><td><input type="text" id="menuUrlPattern" style="width: 100%;"><a class="btn" href="javascript://" onclick="doAction('setDefaultURLPattern');" title="패턴 기본값">패턴 기본값</a>&nbsp;<a class="btn" href="javascript://" onclick="doAction('setDefaultURLFix');" title="고정 URL값">고정 URL값</a> (대소문자 구분함)</td></tr>											 					
					<tr><th>목록 조회 권한 URL 패턴</th><td><input type="text" id="listAuthUrlPattern"       name="listAuthUrlPattern"	 style="width: 100%;"/></td></tr>
					<tr><th>생성 권한 URL 패턴</th><td><input type="text" id="createAuthUrlPattern"       name="createAuthUrlPattern"	 style="width: 100%;"/></td></tr> 					
					<tr><th>상세 조회 권한 URL 패턴</th><td><input type="text" id="readAuthUrlPattern"       name="readAuthUrlPattern"	 style="width: 100%;"/></td></tr> 					
					<tr><th>수정 권한 URL 패턴</th><td><input type="text" id="updateAuthUrlPattern"       name="updateAuthUrlPattern"	 style="width: 100%;"/></td></tr> 					
					<tr><th>삭제 권한 URL 패턴</th><td><input type="text" id="deleteAuthUrlPattern"       name="deleteAuthUrlPattern"	 style="width: 100%;"/></td></tr> 					
					<tr><th>출력 권한 URL 패턴</th><td><input type="text" id="printAuthUrlPattern"       name="printAuthUrlPattern"		 style="width: 100%;"/></td></tr> 					
					<tr><th>다운로드 권한 URL 패턴</th><td><input type="text" id="downloadAuthUrlPattern"       name="downloadAuthUrlPattern"		 style="width: 100%;"/></td></tr> 					
					<tr><th>기타 권한 URL 패턴</th><td><input type="text" id="otherAuthUrlPattern"       name="otherAuthUrlPattern"		 style="width: 100%;"/></td></tr> 					
					<tr>
						<th>삭제여부<span class="pre"></span></th>
						<td>
							<input type="radio" id="deleteYnY" name="deleteYn" value="Y"><label for="deleteYnY">Yes</label>
							<input type="radio" id="deleteYnN" name="deleteYn" value="N" checked><label for="deleteYnN">No</label>
						</td>
					</tr>
					<tr>
						<th>보기여부<span class="pre"></span></th>
						<td>
							<input type="radio" id="showYnY" name="showYn" value="Y" checked><label for="showYnY">Yes</label>
							<input type="radio" id="showYnN" name="showYn" value="N"><label for="showYnN">No</label>
						</td>
					</tr>
				</tbody>
			</table>
		</form><!-- E : view-1 -->

		<div class="page-btn-2">
			<a href="javascript://" onclick="doAction('formAuthReset');">권한 초기화</a>
			<a href="javascript://" onclick="doAction('formReset');">초기화</a>
			<a href="javascript://" onclick="doAction('AJAX_formSave');">저장</a>
		</div><!-- E : page-btn -->
	</li>							
</ul><!-- E : code-box -->



