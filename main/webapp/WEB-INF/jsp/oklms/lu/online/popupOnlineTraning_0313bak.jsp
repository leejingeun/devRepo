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
  ~  * 이진근    2017. 01. 09 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>
<c:set var="targetUrl" value="/lu/online/"/>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" media="all" />
<script type="text/javascript" src="/js/oklms/popup.js"></script>
<script type="text/javascript"  src="/js/jquery/jquery.ui.widget.js"></script>
<script type="text/javascript"  src="/js/jquery/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.fileupload.js"></script> 
<script type="text/javascript" src='/js/oklms/ajaxBase.js'></script>

<script type="text/javascript">

	
	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보
	var subjPoint = '${param.subjPoint}'; //교과 학점정보
	
	$(document).ready(function() {
		
		if ('' == pageSize) {
			pageSize = 10;
		}
		if ('' == totalCount) {
			totalCount = 0;
		}
		if ('' == pageIndex) {
			pageIndex = 1;
		}

		
		
		$('#movie').fileupload({
	        url : "/json/contentUpload.jsp?rootDir="+$("#rootDir").val()+"&currentFileView="+$("#currentFileView").val(),
	        dataType: 'json',
	        add: function(e, data){
	        	var uploadFile = data.files[0];
	            var isValid = true;
	            if ( !(/mp4/i).test(uploadFile.name.toLowerCase() ) ) {
	                alert('업로드는 mp4 파일만 가능합니다.');
	                isValid = false;
	            }
	            if ( uploadFile.size > 200000000 ) { // 2mb
	                alert('파일 용량은 200MB 이상을 초과할 수 없습니다.');
	                isValid = false;
	            }
	            
	            if (isValid) {
	            	$("#loadingImg").attr("style", "display:block;");
	                data.submit();              
	            }
	        }, progressall: function(e,data) {
	        	 $("#popup-wrapper").css('display','block');
	 	    	 $("#progress-box").css('display','block');
	 	    	
	 	        var progress = parseInt(data.loaded / data.total * 100, 10);
	 	       
	 			$("#bar").css(
	 	           'width',
	 	            progress + '%'
	 	        );
	 	        
	 	        if(progress == 100) {
	 	        	$("#popup-wrapper").css('display','none');
	 	        	$("#progress-box").css('display','none');
	 	        }
	        }, done: function (e, data) {
	        	//alert("done");
	           // var code = data.result.code;
	            $("#submit_chk").val("");
	            var msg = data.result.msg;
	            
	            if( msg == "0" ){
	            	 $("#loadingImg").attr("style", "display:none;");
	            	 alert("업로드가 완료 되었습니다.");
	            	 var uploadFile 			= data.files[0];
	            	 var contentsDir 			=  data.result.contentsDir;
	 	             var contentsIdxFile 	=  data.result.contentsIdxFile;
	 	             var contentsRealFile 	=  data.result.contentsRealFile;
	 	             var retSize = byteCalculation(uploadFile.size);
	 	             
	 	            $("#contentsDir").val(contentsDir);
	 	            $("#contentsIdxFile").val(contentsIdxFile);
	 	            $("#contentsRealFile").val(contentsRealFile);
	 	             
	 	        	$("#movieName").text(contentsRealFile+" ("+retSize+"/ 200M)");
	            } else if ( msg == "1" ) {
	            	 $("#loadingImg").attr("style", "display:none;");
	            	 alert("동일한 파일이 존재합니다. \n파일명 변경 후 업로드 하세요.");
	            }
	            
	            
	        }, fail: function(e, data){
	        	$("#popup-wrapper").css('display','none');
	        	$("#progress-box").css('display','none');
	            alert('서버와 통신 중 문제가 발생했습니다');
	        }
	    });  
		
		
		$(document).on("change","#movieType",function(){  
			if($(this).val() == "MOVIE"){
				$("#url1").css("display", "none");
				$("#url2").css("display", "none");
				$("#url3").css("display", "none");
				$("#movie1").css("display", "");
				$("#movie2").css("display", "");
				$("#movie3").css("display", "");
				
			} else {
				$("#movie1").css("display", "none");
				$("#movie2").css("display", "none");
				$("#movie3").css("display", "none");
				$("#url1").css("display", "");
				$("#url2").css("display", "");
				$("#url3").css("display", "");
			} 
				
		});
		
		loadPage();
	});

	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
		initEvent();
		initHtml();
	}


	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
		
		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
		
		com.datepickerDateFormat('searchStDate', 'button');
		com.datepickerDateFormat('searchEdDate', 'button');

		/* var pageBlock				= 10;
		var pagingHTML 			= "";
		var navigatorNum    	= 10;
		var firstPageNum			= 1;
		var lastPageNum			= Math.floor((totalCount-1)/pageBlock) + 1;
		var previewPageNum  = pageIndex == 1 ? 1 : pageIndex-1;
		var nextPageNum		= pageIndex == lastPageNum ? lastPageNum : pageIndex+1;
		var indexNum				= pageIndex <= navigatorNum  ? 0 : parseInt((pageIndex-1)/navigatorNum) * navigatorNum;

		if (totalCount > 1) {
			if (pageIndex > 1) {
				pagingHTML += "<a href='#' onclick='fn_search("+firstPageNum+");return false;' class='page-btn1'>처음 페이지</a>";
				pagingHTML += "<a href='#' onclick='fn_search("+previewPageNum+");return false;' class='page-btn2'>이전 페이지</a>";
			}
			for (var i=1; i<=navigatorNum; i++) {
				var pageNum = i + indexNum;
				if (pageNum == pageIndex) 
					pagingHTML += "<a href='#' class='num ing'>"+pageNum+"</a> ";
				else 
					pagingHTML += "<a href='#' onclick='fn_search("+pageNum+");return false;' class='num'>"+pageNum+"</a>";
				if (pageNum==lastPageNum)
					break;
			}
			if (pageIndex < lastPageNum) {
				pagingHTML += "<a href='#' onclick='fn_search("+nextPageNum+");return false;' class='page-btn3'>다음 페이지</a>";
				pagingHTML += "<a href='#' onclick='fn_search("+lastPageNum+");return false;' class='page-btn4'>마지막 페이지</a>";
			}
		}
		$("#page-num-area").html(pagingHTML); */
		
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		//$("#page").val( param1 );
		
		$("#pageIndex").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "popupOnlineTraning.do";
		$("#frmPop").attr("action", reqUrl);
		$("#frmPop").submit();
	}
	
	
	function fn_add_schedule(type){
		var schStr = "";
		var weekCnt = $("#weekCnt").val();
		if(type == "CMS"){
			var contentCnt = $('input:radio[name="contentId"]:checked').length;
			var checkedVal = $('input:radio[name="contentId"]:checked').val();
			var schCnt = $('input:checkbox[name="sch"]:checked').length;
			if( contentCnt == 0 && schCnt == 0  ){
			    alert("콘텐츠를 선택하세요.");
			    return;
			} else {
				if(contentCnt > 0){
					
					var message = "[ "+ subjPoint+"학점 ] 교과입니다.\n\n각 주차별 "+subjPoint+"차시 단위로 분배 하시겠습니까?\n이 경우 각 주차별 매핑 된 차시는 초기화 됩니다.\n\n취소 할 경우 해당 주차에 매핑이 됩니다.";
					var divisionNum = 0;
					if(confirm(message)){
						divisionNum = subjPoint;
					} 
					var courseContentId = checkedVal.split("|")[0];
					$("#courseContentId").val( courseContentId );
					var reqUrl = CONTEXT_ROOT + "/lu/online/getOnlineTraning.json";
				 	var param = $("#frmPop").serializeArray();
					com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){	
						if (200 == jqXHR.status ) {
							var retData 	= jqXHR.responseJSON.retData;
							var retCd 	= jqXHR.responseJSON.retCd;
							if(retCd == 10000){
								// 콘텐츠명 등록
								var weeLen = $(opener.document).find('tr[name="week"]').length;
								
								if(divisionNum > 0){
									
								} else {
									// 주차에 콘텐츠 명이 없을 경우에만 등록
									if($(opener.document).find("#contentName"+weekCnt).val() == ""){
										$(opener.document).find("#contentName"+weekCnt).val(checkedVal.split("|")[1]);
									}	
								}
								
								
								window.opener.fn_opener_add_schedule(retData,weekCnt, courseContentId, divisionNum, checkedVal.split("|")[1]);
							} else {
								alert("회차정보를 읽어오는대 실패했습니다.")
							}
						} else {
							alert("처리에 실패했습니다.")
						}
					}, {
						async : true,
						type : "POST",
						errorCallback : null
					});
				} else if( schCnt > 0){
					var rowLen = $(opener.document).find(".add_sch_"+weekCnt).length;
					var schArray = new Array();
					$('input:checkbox[name="sch"]:checked').each(function (index) {  
						var chkVal = $(this).val();
						//var chkVal = rowId+"|"+list.lesson_id+"|"+list.id+"|"+registered_from_device_type_code+"|"+list.title;
						var registered_from_device_type_code = chkVal.split('|')[3];
						var registered_from_device_type_code_name = "";
						if(registered_from_device_type_code == "1"){
					    	registered_from_device_type_code_name = "PC + 모바일";
					    } else if(registered_from_device_type_code == "2"){
					    	registered_from_device_type_code_name = "PC + 모바일 (앱 설치 필요)";
					    } else if(registered_from_device_type_code == "3"){
					    	registered_from_device_type_code_name = "PC + 모바일 (앱 설치 권장)";
					    } else if(registered_from_device_type_code == "4"){
					    	registered_from_device_type_code_name = "PC";
					    } else if(registered_from_device_type_code == "5"){
					    	registered_from_device_type_code_name = "모바일";
					    } else {
					    	registered_from_device_type_code_name = "기타";
					    }
						
						schStr += "<tr name='add_sch' class='add_sch_"+weekCnt+"' id='row_"+rowLen+"'>";
						schStr += "<td></td>";
						schStr += "<td>ㄴ</td>";
						schStr += "<td name='week_sch_text'>"+(rowLen + (index+1))+"차시</td>";
						schStr += "<td class='left'>";
						
						schStr+="	<input type='text' name='subjTitles' value='"+chkVal.split('|')[1]+"' />";
						schStr+="	<input type='hidden' name='linkContentTypes' value='CMS' />";
						schStr+="	<input type='hidden' name='cmsCourseContentIds' value='"+chkVal.split('|')[2]+"' />";
						schStr+="	<input type='hidden' name='cmsLessonIds' value='"+chkVal.split('|')[3]+"' />";
						schStr+="	<input type='hidden' name='cmsIds' value='"+chkVal.split('|')[4]+"' />";
						schStr+="	<input type='hidden' name='contentTypes'  />";
						schStr+="	<input type='hidden' name='contentsDirs'  />";
						schStr+="	<input type='hidden' name='contentsIdxFiles'  />";
						schStr+="	<input type='hidden' name='contentsRealFiles' />";
						schStr+="	<input type='hidden' name='urls'  />";
						schStr+="	<input type='hidden' name='deviceTypeCodes' value='"+chkVal.split('|')[5]+"' />";
						
						schStr += "</td>";
						
						schStr += "<td class='left'>";
						schStr += "<input type='text' name='weekSchStDates' id='week_sch_st_date_"+(rowLen + (index+1))+"'  readonly style='width:65px' />~<br />";
						schStr += "<input type='text' name='weekSchEdDates' id='week_sch_ed_date_"+(rowLen + (index+1))+"'  readonly style='width:65px' />";
						schStr += "</td>";
						schStr += "<td><input type='text' name='weekSchMins'  id='week_sch_min_"+(rowLen + (index+1))+"' class='week_min_"+weekCnt+"'  maxlength='3' value='' style='width:25px; text-align:center;' /></td>";
						schStr += "<td><a href='alert(1)'  class='btn-search-gray '></a></td>";
						schStr += "<td><a href='#none' onclick='removeRow(this);' class='btn-line-gray'>삭제</a></td>";
						schStr += "<td><a href='#!' class='btn-line-yellow'>편집</a></td>";
						schStr += "<td><a href='#!' onclick='#' class='btn-line-blue'>등록</a></td>";
						schStr += "<td>"+registered_from_device_type_code_name+"</td>";
						schStr += "<td>";
						schStr += "<a href='#none' onclick='moveSchUp(this);'><img src='/images/oklms/std/inc/arrow_top.png'></a><br />";
						schStr += "<a href='#none' onclick='moveSchDown(this);'><img src='/images/oklms/std/inc/arrow_down.png'></a>";
						schStr += "</td>"; 
						schStr += "</tr>";
					    
					}); 
					window.opener.fn_opener_add_row(schStr,weekCnt);
				}
			}
			
			
		} else if (type == "MOVIE"){
			
			var rowLen = $(opener.document).find(".add_sch_"+weekCnt).length;
			
			if($("#movieType").val() ==  "MOVIE"){
				if($("#movieSchTitle").val() == ""){
					alert("차시명 입력은 필수사항입니다.");
					$("#movieSchTitle").focus();
					return;
				}
				if($("#contentsDir").val() == "" | $("#contentsIdxFile").val() == "" | $("#contentsRealFile").val() == "" ){
					alert("동영상 업로드는 필수사항입니다.");
					return;
				}
				var chkVal = "LMS|MP4|"+$("#movieSchTitle").val()+"|"+$("#contentsDir").val()+"|"+$("#contentsIdxFile").val()+"|"+$("#contentsRealFile").val()+"|2";
				
				schStr += "<tr name='add_sch' class='add_sch_"+weekCnt+"' id='row_"+rowLen+"'>";
				schStr += "<td></td>";
				schStr += "<td>ㄴ</td>";
				schStr += "<td name='week_sch_text'>"+(rowLen +1)+"차시</td>";
				schStr += "<td class='left'>";
				
				schStr +="	<input type='text' name='subjTitles' value='"+$("#movieSchTitle").val()+"' />";
				schStr +="	<input type='hidden' name='linkContentTypes' value='LMS' />";
				schStr +="	<input type='hidden' name='cmsCourseContentIds'  />";
				schStr +="	<input type='hidden' name='cmsLessonIds'  />";
				schStr +="	<input type='hidden' name='cmsIds'  />";
				schStr +="	<input type='hidden' name='contentTypes' value='MP4'  />";
				schStr +="	<input type='hidden' name='contentsDirs' value='"+$("#contentsDir").val()+"'  />";
				schStr +="	<input type='hidden' name='contentsIdxFiles' value='"+$("#contentsIdxFile").val()+"' />";
				schStr +="	<input type='hidden' name='contentsRealFiles' value='"+$("#contentsRealFile").val()+"'/>";
				schStr +="	<input type='hidden' name='urls'  />";
				schStr +="	<input type='hidden' name='deviceTypeCodes' value='2' />";
				
				schStr +="</td>";
				
				schStr += "<td class='left'>";
				schStr += "<input type='text' name='weekSchStDates' id='week_sch_st_date_"+(rowLen +1)+"'  readonly style='width:65px' />~<br />";
				schStr += "<input type='text' name='weekSchEdDates' id='week_sch_ed_date_"+(rowLen +1)+"'  readonly style='width:65px' />";
				schStr += "</td>";
				schStr += "<td><input type='text' name='weekSchMins'  id='week_sch_min_"+(rowLen +1)+"' class='week_min_"+weekCnt+"' maxlength='3' value='' style='width:25px; text-align:center;' /></td>";
				schStr += "<td><a href='alert(1)'  class='btn-search-gray '></a></td>";
				schStr += "<td><a href='#none' onclick='removeRow(this);' class='btn-line-gray'>삭제</a></td>";
				schStr += "<td><a href='#!' class='btn-line-yellow'>편집</a></td>";
				schStr += "<td><a href='#!' onclick='#' class='btn-line-blue'>등록</a></td>";
				schStr += "<td></td>";
				schStr += "<td>";
				schStr += "<a href='#none' onclick='moveSchUp(this);'><img src='/images/oklms/std/inc/arrow_top.png'></a><br />";
				schStr += "<a href='#none' onclick='moveSchDown(this);'><img src='/images/oklms/std/inc/arrow_down.png'></a>";
				schStr += "</td>"; 
				schStr += "</tr>";
				
				window.opener.fn_opener_add_movie_row(schStr,weekCnt);
				
			} else {
				var urlVal = $("#url").val().toLowerCase();
				if($("#urlSchTitle").val() == ""){
					alert("차시명 입력은 필수사항입니다.");
					$("#urlSchTitle").focus();
					return;
				}
				if( urlVal == "" ){
					alert("URL을 입력하세요.");
					$("#url").focus();
					return;
				}
				if( !checkDetailUrl(urlVal)){
					alert("잘못 된 URL 입니다.");
					$("#url").focus();
					return;
				} 
				
				var chkVal = "LMS|URL|"+$("#urlSchTitle").val()+"|"+$("#url").val()+"|3";
				
				schStr += "<tr name='add_sch' class='add_sch_"+weekCnt+"' id='row_"+rowLen+"'>";
				schStr += "<td></td>";
				schStr += "<td>ㄴ</td>";
				schStr += "<td name='week_sch_text'>"+(rowLen +1)+"차시</td>";
				
				
				schStr += "<td class='left'>";
				
				schStr +="	<input type='text' name='subjTitles' value='"+$("#urlSchTitle").val()+"' />";
				schStr +="	<input type='hidden' name='linkContentTypes' value='LMS' />";
				schStr +="	<input type='hidden' name='cmsCourseContentIds'  />";
				schStr +="	<input type='hidden' name='cmsLessonIds'  />";
				schStr +="	<input type='hidden' name='cmsIds'  />";
				schStr +="	<input type='hidden' name='contentTypes' value='URL'  />";
				schStr +="	<input type='hidden' name='contentsDirs'   />";
				schStr +="	<input type='hidden' name='contentsIdxFiles'  />";
				schStr +="	<input type='hidden' name='contentsRealFiles' />";
				schStr +="	<input type='hidden' name='urls' value='"+$("#url").val()+"'  />";
				schStr +="	<input type='hidden' name='deviceTypeCodes' value='3' />";
				
				schStr +="</td>";
				
				
				schStr += "<td class='left'>";
				schStr += "<input type='text' name='weekSchStDates' id='week_sch_st_date_"+(rowLen +1)+"'  readonly style='width:65px' />~<br />";
				schStr += "<input type='text' name='weekSchEdDates' id='week_sch_ed_date_"+(rowLen +1)+"'  readonly style='width:65px' />";
				schStr += "</td>";
				schStr += "<td><input type='text' name='weekSchMins'  id='week_sch_min_"+(rowLen +1)+"' class='week_min_"+weekCnt+"'  maxlength='3' value='' style='width:25px; text-align:center;' /></td>";
				schStr += "<td><a href='alert(1)'  class='btn-search-gray '></a></td>";
				schStr += "<td><a href='#none' onclick='removeRow(this);' class='btn-line-gray'>삭제</a></td>";
				schStr += "<td><a href='#!' class='btn-line-yellow'>편집</a></td>";
				schStr += "<td><a href='#!' onclick='#' class='btn-line-blue'>등록</a></td>";
				schStr += "<td></td>";
				schStr += "<td>";
				schStr += "<a href='#none' onclick='moveSchUp(this);'><img src='/images/oklms/std/inc/arrow_top.png'></a><br />";
				schStr += "<a href='#none' onclick='moveSchDown(this);'><img src='/images/oklms/std/inc/arrow_down.png'></a>";
				schStr += "</td>"; 
				schStr += "</tr>";
				
				window.opener.fn_opener_add_movie_row(schStr,weekCnt);
			}
		}
	}
	



	function checkDetailUrl(strUrl) {
	    var expUrl = /^(http(s?)\:\/\/)?((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;
	    return expUrl.test(strUrl);
	}

	function fn_hide_course_content(courseContentId){
		$(".course_remove_tree_"+courseContentId).hide();
		$("#course_td_"+courseContentId+" > .text").text($("#course_td_"+courseContentId).text().replace('-', '+'));
		$("#course_href_"+courseContentId).prop("href", "javascript:fn_get_course_content("+courseContentId+")");
		$("#course_tree_"+courseContentId).removeClass("highlight");
	}
	
	
	
	function fn_get_course_content(courseContentId){
		
		// 이미 생성 된 회차정보가 있는지 체크
		var removeLen = $(".course_remove_tree_"+courseContentId).length;
		
		// 회차정보가 있다면 hide > show 로 변경
		if( removeLen > 0 ){
			$("#course_td_"+courseContentId+" > .text").text($("#course_td_"+courseContentId).text().replace('+', '-'));
			$("#course_href_"+courseContentId).prop("href", "javascript:fn_hide_course_content("+courseContentId+")");
			$("#course_tree_"+courseContentId).addClass("highlight");
			$(".course_remove_tree_"+courseContentId).show();
		} else { // 그 외는 회차정보를 다시 불러온다
			$("#courseContentId").val( courseContentId );
			var reqUrl = CONTEXT_ROOT + "/lu/online/getOnlineTraning.json";
		 	var param = $("#frmPop").serializeArray();
			com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){	
				if (200 == jqXHR.status ) {
					var retData 	= jqXHR.responseJSON.retData;
					var retCd 	= jqXHR.responseJSON.retCd;
					if(retCd == 10000){
						addTree( retData , courseContentId );
						$("#course_td_"+courseContentId+" > .text").text($("#course_td_"+courseContentId).text().replace('+', '-'));
						$("#course_href_"+courseContentId).prop("href", "javascript:fn_hide_course_content("+courseContentId+")");
						$("#course_tree_"+courseContentId).addClass("highlight");
					} else {
						alert("회차정보를 읽어오는대 실패했습니다.")
					}
				} else {
					alert("처리에 실패했습니다.")
				}
			}, {
				async : true,
				type : "POST",
				errorCallback : null
			});
		}
	}
	
	
	function addTree(data, rowId){
		var jsonData = JSON.parse(data);
		var treeStr = "";
		for (var i = 0; i < jsonData.body.list.length; i++) {
		    var list = jsonData.body.list[i];
		    var registered_date = list.registered_date;
		    var registered_from_device_type_code = list.registered_from_device_type_code;
		    var registered_from_device_type_code_name = "";
		    //var chkVal = rowId+"|"+list.lesson_id+"|"+list.id+"|"+registered_from_device_type_code+"|"+list.title;
		    var chkVal = "CMS|"+list.title+"|"+rowId+"|"+list.lesson_id+"|"+list.id+"|"+registered_from_device_type_code;
		    if(registered_from_device_type_code == "1"){
		    	registered_from_device_type_code_name = "PC + 모바일";
		    } else if(registered_from_device_type_code == "2"){
		    	registered_from_device_type_code_name = "PC + 모바일 (앱 설치 필요)";
		    } else if(registered_from_device_type_code == "3"){
		    	registered_from_device_type_code_name = "PC + 모바일 (앱 설치 권장)";
		    } else if(registered_from_device_type_code == "4"){
		    	registered_from_device_type_code_name = "PC";
		    } else if(registered_from_device_type_code == "5"){
		    	registered_from_device_type_code_name = "모바일";
		    } else {
		    	registered_from_device_type_code_name = "기타";
		    }
		    
		    console.log("lesson_item_id : "+list.lesson_item_id);
		    console.log("lesson_sub_item_id : "+list.lesson_sub_item_id);
		    
		    treeStr+="<tr class='course_remove_tree_"+rowId+"'>";
		    treeStr+="	<td></td>";
		    treeStr+="	<td></td>";
		   	treeStr+="	<td></td>";
		    treeStr+="	<td></td>";
		    treeStr+="	<td>";
		    
		    treeStr+="	<input type='checkbox' name='sch' class='choice' onclick='fn_clear_radio();'  value='"+chkVal+"' />";
		    
		    treeStr+="	<input type='hidden' name='subjTitles' value='"+list.title+"' />";
		    treeStr+="	<input type='hidden' name='linkContentTypes' value='CMS' />";
		    treeStr+="	<input type='hidden' name='cmsCourseContentIds' value='"+rowId+"' />";
		    treeStr+="	<input type='hidden' name='cmsLessonIds' value='"+list.lesson_id+"' />";
		    treeStr+="	<input type='hidden' name='cmsIds' value='"+list.id+"' />";
		    treeStr+="	<input type='hidden' name='contentTypes'  />";
		    treeStr+="	<input type='hidden' name='contentsDirs'  />";
		    treeStr+="	<input type='hidden' name='contentsIdxFiles'  />";
		    treeStr+="	<input type='hidden' name='contentsRealFiles' />";
		    treeStr+="	<input type='hidden' name='urls'  />";
		    treeStr+="	<input type='hidden' name='deviceTypeCodes' value='"+registered_from_device_type_code+"' />";
		    
		    treeStr+="	</td>";
		    treeStr+="	<td class='left'>  ┖  "+list.title+"</td>";
		    treeStr+="	<td>"+registered_from_device_type_code_name+"</td>";
		    treeStr+="	<td>"+registered_date.substring(0,10)+"</td>";
		    treeStr+="	<td><a href='javascript:fn_showPreviewPop("+rowId+",\""+list.lesson_id+"\");' class='btn-search-gray'></a></td>";
		    treeStr+="</tr>";
		}
		$("#course_tree_"+rowId).eq(-1).after(treeStr);
	}
	
	function fn_clear_radio(){
		var cnt = $("input[name=sch]:checkbox:checked").length;
		if( cnt > 0 ) $("input:radio[name='contentId']").attr('checked', false) ;
	}
	
	function fn_clear_checkbox(){
		var cnt = $("input[name='contentId']:radio:checked").length;
		if( cnt > 0 ) $("input:checkbox:checked[name='sch']").attr('checked', false) ;
	}
	
	
	function scrollLink(obj){
		var position = $("#"+obj).offset();
		$('html, body').animate({scrollTop : position.top}, 2000);
	}
	
	function byteCalculation(bytes) {
	        var bytes = parseInt(bytes);
	        var s = ['bytes', 'KB', 'MB', 'GB', 'TB', 'PB'];
	        var e = Math.floor(Math.log(bytes)/Math.log(1024));
	       
	        if(e == "-Infinity") return "0 "+s[0]; 
	        else 
	        return (bytes/Math.pow(1024, Math.floor(e))).toFixed(2)+" "+s[e];
	}
	

	
</script>

<form id="frmPop" name="frmPop" method="post">
	<%-- <input type="hidden" name="page" id="page" value="${pageIndex}"/> --%>
	<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
	<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
	<input type="hidden" id="id" name="id" value="" /> 
	<input type="hidden" id="courseContentId" name="courseContentId"/>
	<input type="hidden" id="lessonId" name="lessonId" />
	<input type="hidden" id="lessonItemId" name="lessonItemId" />
	<input type="hidden" id="lessonSubItemId" name="lessonSubItemId" />
	<input type="hidden" id="submit_chk" name="submit_chk" />
	<input type="hidden" id="contentsDir" name="contentsDir" />
	<input type="hidden" id="contentsIdxFile" name="contentsIdxFile" />
	<input type="hidden" id="contentsRealFile" name="contentsRealFile" />
	<input type="hidden" id="weekCnt" name="weekCnt" value="${param.weekCnt}" />
	
			
<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">
		
		<!-- <ul id="preview-popup">
			<li class="learning-area" id="learning-area">
				<div class="title"><span></span></div>
				<div class="movie-zone">
					<iframe id="contentFrame" src="https://www.youtube.com/embed/z1gn-CRc0qQ" frameborder="0" allowfullscreen></iframe>
					<iframe id="contentFrame" src="" frameborder="0" allowfullscreen></iframe>
					<a href="javascript:prevView();" class="pre">이전</a>
					<span id="contentPage"><b>01</b> / 24</span>
					<a href="javascript:nextView();" class="next">다음</a>
				</div>
				<div class="list-zone">
				</div>
				<div class="btn-zone"><a href="#!" class="movie">동영상문제해결</a><a href="#!" class="error">오류신고</a><a href="javascript:hidePreviewPop();" class="close">닫기</a></div>
			</li>
			<li class="popup-bg"></li>
		</ul>E : preview-popup -->
		
		
		<ul id="preview-popup">
			<li class="preview-area" id="preview-area">
				<div class="title">
					<span>미리보기</span>
					<a href="javascript:hidePreviewPop();">종료</a>
				</div>
				<div id="preview-container">
					<div id="preview-index">
						<p>학습목차</p>
						<div style="height:645px" id="">
						</div>
					</div>

					<div id="preview-movie-zone">
						<iframe id="contentFrame" src="" width="1000px" height="660px" frameborder="0" allowfullscreen></iframe>
						<!-- iframe 논이 값 height 값에 
						<p>학습목차</p>
						<div style="height:645px"> - 15px;
						-->
						<div class="movie-control">
							<a href="javascript:prevView();" class="pre">이전</a>
							<span id="contentPage"><b>01</b> / 24</span>
							<a href="javascript:nextView();" class="next">다음</a>
						</div>
					</div><!-- E : movie-zone -->
				</div>
			</li>
			<li class="popup-bg"></li>
		</ul><!-- E : preview-popup -->
		
		
		<ul id="popup-wrapper" style="display:none;"> 
		 	<li class="progress-box"  id="progress-box" style="margin-top: 200px; display:none;"> 
		<!-- <li class="progress-box"  id="progress-box" style="margin-top: 200px;"> -->
			<dl>
				<dt>현재 파일이 <span>업로드 중</span>입니다.</dt>
				<dd class="bar" id="bar" style="width:0%;"></dd>
				<dd class="bg"></dd>
			</dl>
		</li><!--  -->
		<li class="popup-bg"></li><!--  -->
	</ul><!-- E : popup layer -->

			<h1>콘텐츠 등록</h1>
			<dl id="tab-type">
				<dt class="tab-name-11 on"><a href="javascript:showTabbtn1();">기존 콘텐츠</a></dt>
				<dd id="tab-content-11" style="display:block;">
					<div class="search-box-1 mt-020 mb-010">
						<select id="searchInstitutionId" name="searchInstitutionId">
							<option value="">산하기관</option>
							<option value="A"  <c:if test="${cmsCourseBaseVO.searchInstitutionId eq 'A'}">selected</c:if> >평생능력</option>
							<option value="B"  <c:if test="${cmsCourseBaseVO.searchInstitutionId eq 'B'}">selected</c:if> >코리아텍</option>
						</select>
						<select id="searchYear" name="searchYear">
						    <option value="">개발년도</option>
							<option value="2017" ${cmsCourseBaseVO.searchYear eq '2017' ? 'selected' : ''}>2017</option>
							<option value="2016" ${cmsCourseBaseVO.searchYear eq '2016' ? 'selected' : ''}>2016</option>
							<option value="2015" ${cmsCourseBaseVO.searchYear eq '2015' ? 'selected' : ''}>2015</option>
							<option value="2014" ${cmsCourseBaseVO.searchYear eq '2014' ? 'selected' : ''}>2014</option>
							<option value="2013" ${cmsCourseBaseVO.searchYear eq '2013' ? 'selected' : ''}>2013</option>
							<option value="2012" ${cmsCourseBaseVO.searchYear eq '2012' ? 'selected' : ''}>2012</option>
							<option value="2011" ${cmsCourseBaseVO.searchYear eq '2011' ? 'selected' : ''}>2011</option>
							<option value="2010" ${cmsCourseBaseVO.searchYear eq '2010' ? 'selected' : ''}>2010</option>
							<option value="2009" ${cmsCourseBaseVO.searchYear eq '2009' ? 'selected' : ''}>2009</option>
							<option value="2008" ${cmsCourseBaseVO.searchYear eq '2008' ? 'selected' : ''}>2008</option>
							<option value="2007" ${cmsCourseBaseVO.searchYear eq '2007' ? 'selected' : ''}>2007</option>
							<option value="2006" ${cmsCourseBaseVO.searchYear eq '2006' ? 'selected' : ''}>2006</option>
							<option value="2005" ${cmsCourseBaseVO.searchYear eq '2005' ? 'selected' : ''}>2005</option>
							<option value="2004" ${cmsCourseBaseVO.searchYear eq '2004' ? 'selected' : ''}>2004</option>
							<option value="2003" ${cmsCourseBaseVO.searchYear eq '2003' ? 'selected' : ''}>2003</option>
						</select>
						<input type="text" name="searchStDate" id="searchStDate" style="width:75px" value="${cmsCourseBaseVO.searchStDate}" readonly="readonly" />  ~<input type="text" name="searchEdDate" id="searchEdDate" value="${cmsCourseBaseVO.searchEdDate}" style="width:75px" readonly="readonly"  /> 
						<input type="text" name="searchWrd" id="searchWrd" value="${cmsCourseBaseVO.searchWrd}" onkeypress="press(event);" style="width:150px"/>
						<a href="#" onclick="fn_search('1');">검색</a>
					</div><!-- E : search-box-1 -->


					<table class="type-2">
						<colgroup>
							<col style="width:35px" />
							<col style="width:40px" />
							<col style="width:90px" />
							<col style="width:60px" />
							<col style="width:100px" />
							<col style="width:*" />
							<col style="width:55px" />
							<col style="width:65px" />
							<col style="width:40px" />
							<col style="width:140px" />
						</colgroup>
						<thead>
							<tr>
								<th></th>
								<th>번호</th>
								<th>산하기관</th>
								<th>개별년도</th>
								<th>분야</th>
								<th>콘텐츠명</th>
								<th>지원<br />기기</th>
								<th>등록일</th>
								<th>미리<br />보기</th>
							</tr>
						</thead>
						<tbody>
						
						<c:forEach var="result" items="${resultList}" varStatus="status">
							<tr id="course_tree_${result.id}">
								<td><input type="radio" name="contentId" onclick="fn_clear_checkbox();" class="choice" value="${result.id}|${result.subtitle}" /></td>
								<td><c:out value="${totalCount-((pageIndex-1) * pageSize + status.count)+1}"/></td>
								<td>${result.institutionName}</td>
								<td>${result.year}</td>
								<td>${result.cateName}</td>
								<td class="left" id="course_td_${result.id}"><a href="javascript:fn_get_course_content(${result.id});" id="course_href_${result.id}" class="text">+ ${result.subtitle}</a></td>
								<td>${result.deviceName}</td>
								<td>${fn:substring(result.registeredDate,0,10)}${result.registered_date}</td>
								<td><a href="javascript:fn_showPreviewPop(${result.id},'');" class="btn-search-gray"></a></td> 
							</tr>
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0}">
							<tr>
								<td colspan="9"><spring:message code="common.nodata.msg" /></td>
							</tr>
						</c:if>
						</tbody>
					</table>
					
					
					<ul class="page-num-btn mt-015">
						<li class="page-num-area">
							<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
						</li>
						<li class="page-btn-area">
							<a href="#" onclick="fn_add_schedule('CMS');" class="orange">목록에 추가</a><a href="#" onclick="window.self.close();" class="gray-3">닫기</a>
						</li>
					</ul><!-- E : page-num-btn -->

		</form>




				<dt class="tab-name-12"><a href="javascript:showTabbtn2();">동영상</a></dt>
				<dd id="tab-content-12">

					<table class="type-write mt-020">
						<colgroup>
							<col style="width:15%" />
							<col style="width:12%" />
							<col style="width:*" />
						</colgroup>
						<tbody>
							<tr>
								<th>타입</th>
								<td colspan="2">
								<select id="movieType" name="movieType">
									<option value="MOVIE">동영상</option>
									<option value="URL">URL</option>
								</select>
								</td>
							</tr>
							
							<tr id="movie1">
								<th rowspan="3">동영상</th>
								<th>타입</th>
								<td>
									<select id="" onchange="">
										<option value="">동영상</option>
										<option value="">파일</option>
									</select>
								</td>
							</tr>
							<tr id="movie2">
								<th class="border-left">차시명</th>
								<td>
									<input type="text" name="movieSchTitle" id="movieSchTitle" style="width:50%;">
								</td>
							</tr>
							<tr id="movie3">
								<th class="border-left">첨부파일</th>
								<td>
									<input type="text" id="fileName-3" style="width:50%;" readonly="readonly">
									<span>
										<a href="#@" class="checkfile">파일선택</a>
										<input type="file" id="movie" class="file_input_hidden" onchange="javascript: document.getElementById('fileName-3').value = this.value" />
									</span> <span id="movieName" style="width: 10%">0KB / 200M</span>
								</td>
							</tr>
							
							<tr id="url1" style="display: none;">
								<th rowspan="3">URL</th>
								<th>타입</th>
								<td>
									<select id="" onchange="">
										<option value="">YouTube</option>
										<option value="">파일</option>
									</select>
								</td>
							</tr>
							<tr id="url2" style="display: none;">
								<th class="border-left">차시명</th>
								<td>
									<input type="text" name="urlSchTitle" id="urlSchTitle" style="width:50%;">
								</td>
							</tr>
							<tr id="url3" style="display: none;">
								<th class="border-left">주소</th>
								<td><input type="text" name="url" id="url" value="http://" style="width:50%;"/></td>
							</tr>
						</tbody>
					</table>

					<div class="btn-area align-right mt-010">
						<a href="#" onclick="fn_add_schedule('MOVIE');" class="orange">목록에 추가</a>
						<a href="#" onclick="window.self.close();" class="gray-3">닫기</a>
					</div><!-- E : btn-area -->
				</dd>





				<!-- <dt class="tab-name-13"><a href="javascript:showTabbtn3();">기존 과정</a></dt> -->
				<dt class="tab-name-13"><a href="javascript:alert('준비중입니다.');">기존 과정</a></dt>
				<dd id="tab-content-13">
					<div class="search-box-1 mt-020 mb-010">
						<select id="" onchange="">
							<option value="">2016학년도</option>
						</select>
						<select id="" onchange="">
							<option value="">2학기</option>
						</select>
						<select id="" onchange="">
							<option value="">학점</option>
						</select>
						<select id="" onchange="">
							<option value="">교과구분</option>
						</select>
						<input type="text" name="" value="교과명 검색" style="width:20%"/>
						<a href="#!">검색</a>
					</div><!-- E : search-box-1 -->



					<table class="type-2">
						<colgroup>
							<col style="width:40px" />
							<col style="width:50px" />
							<col style="width:40px" />
							<col style="width:40px" />
							<col style="width:70px" />
							<col style="width:*" />
							<col style="width:75px" />
							<col style="width:60px" />
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="checkbox" class="choice" /></th>
								<th>학년도</th>
								<th>학기</th>
								<th>학점</th>
								<th>과목구분</th>
								<th>개설교과명</th>
								<th>과정복사</th>
								<th>미리보기</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td>2016</td>
								<td>2</td>
								<td>3</td>
								<td>Off-JT</td>
								<td class="left"><a href="#!" class="text">+ 교과목_F</a></td>
								<td><a href="#!" class="btn-full-blue">과정복사</a></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td>2016</td>
								<td>2</td>
								<td>3</td>
								<td>Off-JT</td>
								<td class="left"><a href="#!" class="text">- 교과목_G</a></td>
								<td><a href="#!" class="btn-full-blue">과정복사</a></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td>2016</td>
								<td>2</td>
								<td>3</td>
								<td>Off-JT</td>
								<td class="left"><a href="#!" class="text">+ 교과목_F</a></td>
								<td><a href="#!" class="btn-full-blue">과정복사</a></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td>2016</td>
								<td>2</td>
								<td>3</td>
								<td>Off-JT</td>
								<td class="left"><a href="#!" class="text">- 교과목_G</a></td>
								<td><a href="#!" class="btn-full-blue">과정복사</a></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
							<tr>
								<td><input type="checkbox" name="checkbox" class="choice" /></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td class="left"> ┖ 1주차 /1차시 /제목_11</td>
								<td></td>
								<td><a href="javascript:showLearningpop();"  class="btn-search-gray"></a></td>
							</tr>
						</tbody>
					</table>
					<ul class="page-num-btn mt-015">
						<li class="page-num-area">
							<a href="#" class="page-btn1">처음 페이지</a>
							<a href="#" class="page-btn2">이전 페이지</a>
							<a href="#" class="num">1</a>
							<a href="#" class="num">2</a>
							<a href="#" class="num ing">3</a>
							<a href="#" class="num">4</a>
							<a href="#" class="num">5</a>
							<a href="#" class="num">6</a>
							<a href="#" class="num">7</a>
							<a href="#" class="num">8</a>
							<a href="#" class="num">9</a>
							<a href="#" class="num">10</a>
							<a href="#" class="page-btn3">다음 페이지</a>
							<a href="#" class="page-btn4">마지막 페이지</a>
						</li>
						<li class="page-btn-area">
							<a href="#!" class="orange">목록에 추가</a><a href="#" onclick="window.self.close();" class="gray-3">닫기</a>
						</li>
					</ul><!-- E : page-num-btn -->
				</dd>
			</dl>


		</div><!-- E : wrapper -->