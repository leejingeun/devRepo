<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/jquery.jqgrid.src.js"></script>
<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/grid.locale-kr.js"></script>
<script type="text/javascript" src="${contextRoot }/js/oklms/common_jqGrid.js"></script>

<link href="<c:url value='/js/free-jqgrid/4_13_0/ui.jqgridTree.min.css' />" rel="stylesheet" type="text/css" />


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
<script type="text/javascript">


	var vCheckUnload = false;			//beforeunload 이벤트에 사용
	
	$(document).ready(function() {
		loadPage();	
		
//         $("#myGrid01").trigger('reloadGrid');				//collapse 기능과 expand 기능 불가
	});
	
	/*====================================================================
		jqGrid 및 화면 초기화 
	====================================================================*/
	function loadPage(){

		initSheet();
		initEvent();
		initHtml();
	}

	function initSheet(){
		var COL_MODEL = [
							{ label : '메뉴코드',  		name : 'menuId',   			width : 110,  align : 'center' , hidden:true },
							{ label : '메뉴명',    		name : 'menuTitle',    		width : 338,    align : 'left'   },
							{ label : '상위메뉴',    	name : 'upMenuId',   		width : 310,    align : 'left' , hidden:true    },
							{ label : '상위메뉴명',    	name : 'upMenuTitle',   	width : 310,    align : 'left' , hidden:true    },
							{ label : '메뉴순서',      	name : 'menuOrder',      	width : 90,  align : 'center' , hidden:true  },
							{ label : '메뉴유형',      	name : 'menuViewTypeCode',  width : 90,  align : 'center' , hidden:true  },
							{ label : '삭제여부',      	name : 'deleteYn',        		width : 90,   align : 'center' , hidden:true  },
							{ label : '보기여부',      	name : 'showYn',        		width : 90,   align : 'center' , hidden:true  },
							{ label : '메뉴깊이',      	name : 'menuDepth',        	width : 90,    align : 'center' , hidden:true  },
							{ label : 'expanded',      	name : 'expanded',     	width : 150,  align : 'center' , hidden:true    },
							{ label : 'isLeaf',      		name : 'isLeaf',     			width : 150,  align : 'center' , hidden:true    },
							{ label : 'loaded',      	name : 'loaded',     			width : 150,  align : 'center' , hidden:true    }
							];

		var myGrid01 = $("#myGrid01");
	
		myGrid01.jqGrid({
		    mtype : 'post',
		    colModel: COL_MODEL,
		    gridview: true,
		    sortname: 'scrnId',
		    treeGrid: true,
		    loadonce: true,
		    treeGridModel: 'adjacency',
		    treedatatype: 'post',
		    ExpandColumn: 'menuTitle',
		    height: '400px',
		    width: '355px',
			shrinkToFit : false,
		    treeReader:{
		    	level_field: "menuDepth"
		    	, parent_id_field: "upMenuId"
		    },
		    localReader:{
		    	id: "menuId"
		    },
			ondblClickRow : function(rowId, iRow, iCol, e) {
				fnGridSelect(rowId, iRow, iCol, e);
			}
		});
	
		doAction("gridSearch", 1);			//그리드 그린 후 자동조회

        /* 웹접근성을 위한 TABLE CAPTION 추가 */
		com_jqGrid.caption( "myGrid01", "메뉴관리 상위메뉴 목록" );
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

// 		doAction("gridSearch", pageIndex);
	}
	
	/*====================================================================
		조회버튼이나 페이지 클릭시 실행되는 함수는 꼭 doAction(sAction, pageNum)으로 만들어 사용해 주시기 바랍니다.
	====================================================================*/
	function doAction(sAction, pageNum) {
		switch (sAction) {
	
		/* Grid 검색 */
		case "gridSearch":
			grid01_doAction("search", pageNum);
			break;
	
		default:
			break;
		}
	}
	

	/*====================================================================
		jqGrid 이벤트 처리 전용 함수
	====================================================================*/
	function grid01_doAction(sAction, pageNum) {
	
		if (pageNum == undefined)
			pageNum = 1;
	
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
	                    
	                    $("#myGrid01").trigger('reloadGrid'); // refresh the grid
	                    
	                    if(itemList.length==0){
							var msg = '<spring:message code="common.nodata.msg" />';
							alert( msg );
							return ;
						}
					}
				}, {
		    		async : true,
		    		type  : "POST",
		    		timeout : 30000			// 기본 30초
		    	});
				
				doAction('formReset', 1);		//상세 포맷 리셋
		
				break;
				
			default:
				break;
		}
	}
	/**
	* 내부적으로 메뉴가 선택되고 나면 window.opener.fnSelectMenuPopupAfter(map); 를 수행하게됨.
	* 가져올 수 있는 값은 
	* var menuId = map['menuId'];
	* var menuTitle = map['menuTitle'];
	* var menuArea = map['searchMenuAreaVal'];
	* var menuType = map['searchMenuTypeVal'];
	* var menuObj = map['menuObj'];
	* //alert( "menuObj.menuTitle : " + menuObj.menuTitle );
	*/
	function fnGridSelect(rowId, iRow, iCol, e){
		
        var menuId = $("#myGrid01").getRowData(rowId).menuId;
        var upMenuId = $("#myGrid01").getRowData(rowId).upMenuId;
        var menuOrder = $("#myGrid01").getRowData(rowId).menuOrder;
        var menuTitle = $("#myGrid01").getRowData(rowId).menuTitle;

		var searchMenuAreaVal = $("#searchMenuArea").val();
		var searchMenuTypeVal = $("#searchMenuType").val();
		
        var map= {
        	    'menuId': menuId,
        	    'upMenuId': upMenuId,
        	    'menuTitle': menuTitle,
        	    'menuOrder': menuOrder,
        	    'menuArea': searchMenuAreaVal,
        	    'menuType': searchMenuTypeVal,
        	    'menuObj': $("#myGrid01").getRowData(rowId)
        	    }
		
		window.opener.fnSelectMenuPopupAfter(map);
		
		window.close();
		
	}
	
</script>
    
		<div class="popup_title">
			<h3>메뉴관리 메뉴검색</h3>
			<a class="btn_popclose" href="javascript://" onclick="window.close();" title="팝업닫기">닫기</a>
		</div>
		<div>
			<form id="searchFrm" name="searchFrm" method="post" action="">
				searchMenuArea : <select id="searchMenuArea" name="searchMenuArea">
					<option value="PC" selected="selected">PC</option>
					<option value="MOBILE">MOBILE</option>
				</select>
				<!-- <input type="hidden" name="searchMenuArea" id="searchMenuArea" value="LMS"/> -->
				searchMenuType : <select id="searchMenuType" name="searchMenuType">
					<option value="ADM" <c:if test="${ 'ADM' eq searchMenuType }"> selected="selected"</c:if> >관리자</option>
					<option value="STD" <c:if test="${ 'STD' eq searchMenuType }"> selected="selected"</c:if> >학습근로자</option>
					<option value="PRT" <c:if test="${ 'PRT' eq searchMenuType }"> selected="selected"</c:if> >담당교수</option>
					<option value="COT" <c:if test="${ 'COT' eq searchMenuType }"> selected="selected"</c:if> >기업현장교사</option>
					<option value="CCM" <c:if test="${ 'CCM' eq searchMenuType }"> selected="selected"</c:if> >HRD전담자</option>
					<option value="CCN" <c:if test="${ 'CCN' eq searchMenuType }"> selected="selected"</c:if> >센터전담자</option>
					<option value="CDP" <c:if test="${ 'CDP' eq searchMenuType }"> selected="selected"</c:if> >학과전담자</option>
					<%-- <option value="ATT" <c:if test="${ 'ATT' eq searchMenuType }"> selected="selected"</c:if> >지도교수</option> --%>
				</select>
			</form>
		</div>
		<div class="popup_content">
			
			<div class="treeMenu">
	    		<table id="myGrid01"></table>
	   		</div>
   		
			<div class="btnArea" style="margin-top:30px;">
				<a class="btn_w" href="javascript://" onclick="window.close();" title="팝업닫기">닫기</a>
			</div>
		</div>
