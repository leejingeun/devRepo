<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 김덕진    2016. 11. 30 오후 1:20         First Draft.
  ~  * 이진근    2016. 12. 01 오후 1:20         Modify Draft.
  ~  *
  ~  *******************************************************************************
 --%>

<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/jquery.jqgrid.src.js"></script>
<script type="text/javascript" src="${contextRoot }/js/free-jqgrid/4_13_0/grid.locale-kr.js"></script>
<script type="text/javascript" src="${contextRoot }/js/oklms/common_jqGrid.js"></script>

<script type="text/javascript">
	var lastSel = 0;

// 	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
// 	var totalCount = '${totalCount}'; //전체 데이터 갯수
// 	var pageIndex = '${pageIndex}'; //현재 페이지 정보

	$(document).ready(function() {

// 		if ('' == pageSize) {
// 			pageSize = 10;
// 		}
// 		if ('' == totalCount) {
// 			totalCount = 0;
// 		}
// 		if ('' == pageIndex) {
// 			pageIndex = 1;
// 		}

		loadPage();
	});

	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
		initSheet();
		initEvent();
		initHtml();
	}

	function initSheet(){

		var COL_MODEL = [
					{ label : '메뉴코드',  		name : 'menuId',   			width : 110,  align : 'center' , hidden:true },
					{ label : '해당 권한 그룹이 사용하는 메뉴명',    		name : 'menuTitle',    		width : 338,    align : 'left'   },
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
					
					{ label : '권한 그룹 아이디',      	name : 'authGroupId',        		width : 90,   align : 'center' , hidden:true  },
					
					{ label : '생성 권한 여부',      	name : 'createAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '상세 조회 권한 여부',    name : 'readAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '수정 권한 여부',      	name : 'updateAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '삭제 권한 여부',      	name : 'deleteAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '출력 권한 여부',      	name : 'printAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '다운로드 권한 여부',     name : 'downloadAuthYn',        	width : 90,   align : 'center' , hidden:true  },				 
					{ label : '기타 권한 여부',      	name : 'otherAuthYn',        		width : 90,   align : 'center' , hidden:true  },					 
					{ label : '목록 조회 권한 여부',    name : 'listAuthYn',        		width : 90,   align : 'center' , hidden:true  },	
					
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
							com.log( colNm + " = " + rowData[colNm] );
							if ( colNm == "deleteYn" ){				//radio
								$("#frm_deleteYn"+rowData[colNm]).prop("checked", true);

							}else if ( colNm == "createAuthYn" ){				//radio
								$("#frm_createAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "readAuthYn" ){				//radio
								$("#frm_readAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "updateAuthYn" ){				//radio
								$("#frm_updateAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "deleteAuthYn" ){				//radio
								$("#frm_deleteAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "printAuthYn" ){				//radio
								$("#frm_printAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "downloadAuthYn" ){				//radio
								$("#frm_downloadAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "otherAuthYn" ){				//radio
								$("#frm_otherAuthYn"+rowData[colNm]).prop("checked", true);
							}else if ( colNm == "listAuthYn" ){				//radio
								$("#frm_listAuthYn"+rowData[colNm]).prop("checked", true);							
								
							}else{
								$("#frm_" + colNm).val( rowData[colNm] );
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
// 		$("#searchMenuArea").change(function() {
// 			doAction("gridSearch");
// 		});
// 		$("#searchMenuType").change(function() {
// 			doAction("gridSearch");
// 		});
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {

//         com.pageNavi( "pageNavi", totalCount , pageSize , pageIndex );

// 		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
// 		$("#pageIndex").val(pageIndex); //현재 페이지 정보
// 		$("#totalRow").text(totalCount);
		
// 		$("#frm_authGroupId").val('${searchAuthGroupId}');
// 		$("#frm_authGroupName").val($("#searchAuthGroupId option:selected").text());
		
// 	    com.datepicker('searchStartDate');
// 	    com.datepicker('searchEndDate');
	}

	
	
	/*====================================================================
		조회버튼이나 페이지 클릭시 실행되는 함수는 꼭 doAction(sAction, pageNum)으로 만들어 사용해 주시기 바랍니다.
	====================================================================*/
	function doAction(sAction) {
		switch (sAction) {
	
		/* Grid 검색 */
		case "gridSearch":

	 		$("#frm_authGroupId").val($("#searchAuthGroupId option:selected").val());
	 		$("#frm_authGroupName").val($("#searchAuthGroupId option:selected").text());
	 		
	 		if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000001"){//관리자
	 			$("#searchMenuType").val("ADM");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000002"){//학습근로자
	 			$("#searchMenuType").val("STD");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000003"){//담당교수
	 			$("#searchMenuType").val("PRT");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000004"){//기업전담자
	 			$("#searchMenuType").val("CCM");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000005"){//센터전담자
	 			$("#searchMenuType").val("CCN");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000006"){//학과전담자
	 			$("#searchMenuType").val("CDP");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000007"){//지도교수
	 			$("#searchMenuType").val("ATT");
	 		}else if($("#searchAuthGroupId option:selected").val() == "2016AUTH0000008"){//기업현장교사
	 			$("#searchMenuType").val("COT");
	 		}
	 		
			grid01_doAction("search");
			break;
			
		/* 상위메뉴 선택 팝업 */
		case "seachUperMenuPopup":
			var reqUrl = "/la/menu/uperMenuList.do";
			var params = "";
			var searchMenuAreaVal = $("#searchMenuArea").val();
			var searchMenuTypeVal = $("#searchMenuType").val();
			var searchAuthGroupIdVal = $("#searchAuthGroupId").val();
			if( com.isBlank( searchMenuTypeVal ) && com.isBlank( searchMenuAreaVal) && !com.isBlank( searchAuthGroupIdVal ) ){
// 				2016AUTH0000001	슈퍼운영자
// 				2016AUTH0000002	컨텐츠권한
// 				2016AUTH0000003	학습자
// 				2016AUTH0000004	게스트
// 				2016AUTH0000005	튜터
				if( "2016AUTH0000001" == searchAuthGroupIdVal ){
					searchMenuAreaVal = "LMS";
					searchMenuTypeVal = "ADM";
				}else if( "2016AUTH0000002" == searchAuthGroupIdVal ){
					searchMenuAreaVal = "LCMS";
					searchMenuTypeVal = "ADM";
				}else if( "2016AUTH0000003" == searchAuthGroupIdVal ){
					searchMenuAreaVal = "LMS";
					searchMenuTypeVal = "STD";
				}
			}
			
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
		
				var reqUrl = "/la/authority/authMap/listAuthMapTree.json";
				var param = $("#frmAuthMap").serializeArray();
				
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
		    		timeout : 30000			// 기본 30초
		    	});
				
				doAction('formReset');
		
				break;
				
			default:
				break;
		}
	}
	
	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_egov_select_adbkInfs('1');
		}
	}
	
	
// 	function fn_getAuthMap(targetUrl , param1){
// 		var reqUrl = CONTEXT_ROOT + targetUrl;
// 		$("#menuIdAndAuthGroupId").val( param1 );
// 		$("#frmAuthMap").attr("method", "post" );
// 		$("#frmAuthMap").attr("action", reqUrl);
// 		$("#frmAuthMap").submit();
// 	}




	/* 첵크된 Row 처리 */
	function fn_checkRowVals( thisObj ){

		var checkedCnt = $("#myGridTable input[name='" + thisObj.name + "']:checked").length;
// 		if( 1 < checkedCnt ){
// 			com.alert("한건만 선택해주세요.");
// 		}else{

			var valuesStr = "";
			$.each($("#myGridTable input[name='" + thisObj.name + "']:checked").parents("td").siblings(), function() {
				
				var inputObj = $(this).find("input");
				var inputName = inputObj.attr("name");
				var inputVal = inputObj.val();
				
				if( undefined != inputName ){
					inputName = inputName.replace("result_", "frm_");
					if ( "frm_createAuthYn" == inputName ){				//radio
						$("#frm_createAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_readAuthYn" == inputName ){				//radio
						$("#frm_readAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_updateAuthYn" == inputName ){				//radio
						$("#frm_updateAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_deleteAuthYn" == inputName ){				//radio
						$("#frm_deleteAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_printAuthYn" == inputName ){				//radio
						$("#frm_printAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_downloadAuthYn" == inputName ){				//radio
						$("#frm_downloadAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_otherAuthYn" == inputName ){				//radio
						$("#frm_otherAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_listAuthYn" == inputName ){				//radio
						$("#frm_listAuthYn" + inputVal).prop("checked", true);
					}else if ( "frm_deleteYn" == inputName ){				//radio
						$("#frm_deleteYn" + inputVal).prop("checked", true);
					}else{
						$("#" + inputName ).val(inputVal);
					}
				}
			});
			
// 			$('#myGridTable th').each(function() {
// 				var thObj = this;
// 				com.log( thObj.cellIndex + " , " + thObj.textContent);
// 			});
// 		}
	}
	
	
	
	/* 리스트 조회 */
	function fn_search( pageNo ){
		$("#pageIndex").val( pageNo );
			
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/listAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
	
	/* 추가 */
	function fn_cret(){
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goInsertAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/* 상세조회 */
	function fn_read( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/getAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/* 수정 */
	function fn_updt( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goUpdateAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
			
	/* save(추가/수정) */
	function fn_save( param1 ){
		
		$("#menuIdAndAuthGroupId").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/goSaveAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}
			
	/* 삭제 */
	function fn_delt(){
		
		var checkedVals = com.getCheckedVal('check0','check1');
		com.log(checkedVals);

		$("#delCodeId").val( checkedVals );
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/deleteAuthMap.do";
		$("#frmAuthMap").attr("action", reqUrl);
		$("#frmAuthMap").submit();
	}

	/*
	* jsp 엑셀
	*/
	function fn_toExcel2(){
		var excelDownUrl = "/la/authority/authMap/protoBoardExcelDownload2.do";
		
		//동적으로 iframe 생성
		var $iframe = $("#downIFrame");
		if($iframe.length < 1){
			var $iframe = $("<iframe id='downIFrame' name='downIFrame' frameBorder='0' scrolling='no' width='0' height='0'></iframe>");
			$(document.body).append($iframe);
		}
		
		//동적으로 다운로드용 form 생성
		var $form = $("#downForm");
		if($form.length < 1) {
			$form = $("<form id='downForm', method='post', action='"+excelDownUrl+"' target='downIFrame'></form>");
		  	$(document.body).append($form);
		}
		//이전에 처리한 다운로드파일정보 삭제
		$form.empty();

		//다운로드파일정보 세팅
//			$("<input></input>").attr({type:"hidden", name:"arg0", value:$.trim(arg0)}).appendTo($form);
//			$("<input></input>").attr({type:"hidden", name:"arg1", value:$.trim(arg1)}).appendTo($form);

		$form.submit();
		
	}
	
	function fn_view(){

// 		$("#frmAuthMapAJAX").find("input:radio").prop("checked", true).end().buttonset("refresh");

		$("#frm_createAuthYnN").prop("checked", true);
		$("#frm_readAuthYnY").prop("checked", true);
		$("#frm_updateAuthYnN").prop("checked", true);
		$("#frm_deleteAuthYnN").prop("checked", true);
		$("#frm_printAuthYnN").prop("checked", true);
		$("#frm_downloadAuthYnN").prop("checked", true);
		$("#frm_otherAuthYnN").prop("checked", true);
		$("#frm_listAuthYnY").prop("checked", true);
		$("#frm_deleteYnN").prop("checked", true);
	}

	function fn_default(){

// 		$("#frmAuthMapAJAX").find("input:radio").prop("checked", true).end().buttonset("refresh");

		$("#frm_createAuthYnY").prop("checked", true);
		$("#frm_readAuthYnY").prop("checked", true);
		$("#frm_updateAuthYnY").prop("checked", true);
		$("#frm_deleteAuthYnY").prop("checked", true);
		$("#frm_printAuthYnY").prop("checked", true);
		$("#frm_downloadAuthYnY").prop("checked", true);
		$("#frm_otherAuthYnY").prop("checked", true);
		$("#frm_listAuthYnY").prop("checked", true);
		$("#frm_deleteYnN").prop("checked", true);
	}
	
	function fn_reset(){

		$("#frmAuthMapAJAX").find("input:text,input:hidden,select,textarea").val("");
		$("#frmAuthMapAJAX").find("input:radio").prop("checked", false).end().buttonset("refresh");
	}
	
	
	
	/* AJAX 로 form 저장 */
	function fn_ajaxSave(){
		
		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/saveAuthMap.json";
// 			var param 	= $("#frmAuthMap").serialize();
		var param = $("#frmAuthMapAJAX").serializeArray();
		
		com.ajax4confirm( "저장 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					//fn_search( 1 );
					doAction('gridSearch');
				}
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 30000			// 기본 30초
    	});
	}


	function fn_ajaxDelt(){
		

		var reqUrl = CONTEXT_ROOT + "/la/authority/authMap/deleteAuthMap.json";
// 			var param 	= $("#frmAuthMap").serialize();
		var param = $("#frmAuthMapAJAX").serializeArray();
		
		com.ajax4confirm( "삭제 하시겠습니까?" , reqUrl, param, function(obj, data, textStatus, jqXHR){						
			if (200 == jqXHR.status ) {
				
				com.alert( jqXHR.responseJSON.retMsg );
				
				if( "SUCCESS" == jqXHR.responseJSON.retCd ){
					doAction('gridSearch');
				}
				fn_reset();
				
			}
		}, {
    		async : true,
    		type : "POST",
    		spinner : true,
			errorCallback : null,
    		timeout : 30000			// 기본 30초
    	});
	}
	
	function fnSelectMenuPopupAfter(map){
		var menuId = map['menuId'];
		var menuTitle = map['menuTitle'];
		var menuArea = map['searchMenuAreaVal'];
		var menuType = map['searchMenuTypeVal'];
		var menuObj = map['menuObj'];

// 		$("#menuId").val(menuId);
// 		$("#menuTitle").val(menuTitle);

		$("#frm_menuId").val(menuId);
		$("#frm_menuTitle").val(menuTitle);
    }
</script>


<img id="displayBox" src="${contextRoot}js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display: none">



<form id="frmAuthMap" name="frmAuthMap" action="<c:url value='/la/authority/authMap/listAuthMap.do'/>" method="post">
<%-- 	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" /> --%>
<%-- 	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />  --%>
	<input type="hidden" id="menuIdAndAuthGroupId" name="menuIdAndAuthGroupId" /> 
	<input type="hidden" id="delCodeId" name="delCodeId" />
	<input type="hidden" id="searchMenuType" name="searchMenuType" />
<table border="0" cellpadding="0" cellspacing="0" class="view-1">
	<tbody>
		<tr>
			<td>
				<ul class="search-list-1">
					<li>
						<span>권한 그룹</span>
						<select id="searchAuthGroupId" name="searchAuthGroupId" style="width:280px; margin-right:10px;">
												<option value="">선택하세요</option>
											<c:forEach items="${distAuthGroupList}" var="item" varStatus="status" >
												<option value="${item.authGroupId}" <c:if test="${ item.authGroupId eq searchAuthGroupId or item.authGroupId eq returnResultMap.searchAuthGroupId}" > selected="selected"</c:if> >${item.authGroupName}</option>
											</c:forEach></select>
					</li>
					<li>
						<span>메뉴 영역</span>
						<select id="searchMenuArea" name="searchMenuArea">
							<option value="PC" selected="selected">PC</option>
							<option value="MOBILE">MOBILE</option>
						</select>
					</li>
					<li>
						<span>메뉴명</span>
						<input type="text" id="searchMenuTitle" name="searchMenuTitle" style="width:415px;" value="${searchMenuTitle }"/>
					</li>
				</ul><!-- E : search-list-1 -->
<!-- 										<div class="search-btn-area"><a href="#" onclick="fn_search(1);">조회하기</a></div> -->
				<div class="search-btn-area"><a href="#" onclick="javascript:doAction('gridSearch');">조회</a></div>
					
			</td>
		</tr>
	</tbody>
</table><!-- E : view-1 -->

</form>


<div class="title-name-1" style="margin-top:40px;">권한 설정</div>



<ul class="code-box">
	<li class="code-area" style="width: 40%;">
		<form:form commandName="frmAuthMapAJAX">
			<table id="myInputTable" border="0" cellpadding="0" cellspacing="0" class="view-1">
				<tr><th>권한그룹아이디</th><td><input type="text" id="frm_authGroupId"       name="authGroupId"		value="${infoMap.authGroupId}" /></td></tr>
				<tr><th>권한그룹명</th><td><input type="text" id="frm_authGroupName"       name="authGroupName"		value="${infoMap.authGroupName}" /></td></tr> 					
				<tr><th>메뉴ID</th><td><input type="text" id="frm_menuId"       name="menuId"		value="${infoMap.menuId}" /></td></tr> 					
				<tr><th>메뉴명</th><td><input type="text" id="frm_menuTitle"       name="menuTitle"		value="${infoMap.menuTitle}" /><a class="btn" href="javascript://" onclick="doAction('seachUperMenuPopup');" title="상위메뉴 선택 팝업 열기">찾기</a></td></tr> 					
				<tr>	<th>목록 조회 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_listAuthYnY" name="listAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.listAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_listAuthYnN" name="listAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.listAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="listAuthYn" />		</td>	</tr>
				<tr>	<th>생성 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_createAuthYnY" name="createAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.createAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_createAuthYnN" name="createAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.createAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="createAuthYn" />		</td>	</tr>
				<tr>	<th>상세 조회 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_readAuthYnY" name="readAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.readAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_readAuthYnN" name="readAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.readAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="readAuthYn" />	</td>	</tr>
				<tr>	<th>수정 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_updateAuthYnY" name="updateAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.updateAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_updateAuthYnN" name="updateAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.updateAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="updateAuthYn" />		</td>	</tr>
				<tr>	<th>삭제 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_deleteAuthYnY" name="deleteAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.deleteAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_deleteAuthYnN" name="deleteAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.deleteAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="deleteAuthYn" />		</td>	</tr>
				<tr>	<th>출력 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_printAuthYnY" name="printAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.printAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_printAuthYnN" name="printAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.printAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="printAuthYn" />		</td>	</tr>
				<tr>	<th>다운로드 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_downloadAuthYnY" name="downloadAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.downloadAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_downloadAuthYnN" name="downloadAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.downloadAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="downloadAuthYn" />		</td>	</tr>
				<tr>	<th>기타 권한 여부</th>		<td colspan="3"><input type="radio" id="frm_otherAuthYnY" name="otherAuthYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.otherAuthYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_otherAuthYnN" name="otherAuthYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.otherAuthYn }"> checked="checked"</c:if>/><label for="temp-2">N</label>	<form:errors path="otherAuthYn" />		</td>	</tr>
				<tr>	<th>삭제여부</th>		<td colspan="3"><input type="radio" id="frm_deleteYnY" name="deleteYn" class="choice" value="Y" <c:if test="${ 'Y' eq infoMap.deleteYn }"> checked="checked"</c:if> /><label for="temp-1">Y</label> <input type="radio" id="frm_deleteYnN" name="deleteYn" class="choice" value="N"  <c:if test="${ 'N' eq infoMap.deleteYn }"> checked="checked"</c:if>/><label for="temp-2">N</label> 	<form:errors path="deleteYn" />	</td>	</tr>									
			</table>
		</form:form><!-- E : view-1 -->


		<div class="page-btn">
			<a href="javascript://" onclick="javascript:fn_reset();">초기화</a>
			<a href="javascript://" onclick="javascript:fn_view();">조회권한</a>
			<a href="javascript://" onclick="javascript:fn_default();">모든권한</a>
			<a href="javascript://" onclick="javascript:fn_ajaxSave();">저장</a>
			<a href="javascript://" onclick="javascript:fn_ajaxDelt();">삭제</a>
		</div><!-- E : page-btn -->
						</li>

<div style="float:left; height: auto; padding: 10px;">
	<table id="myGrid01"></table>
</div>
					
<!-- E : list -->

<%-- 
		<div class="page-num">
								<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" /> 
		</div><!-- E : page-num -->
--%>		
	</li>
</ul>
