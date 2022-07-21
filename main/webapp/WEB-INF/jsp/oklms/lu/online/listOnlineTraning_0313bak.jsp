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
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	var pageSize = '${pageSize}'; //페이지당 그리드에 조회 할 Row 갯수;
	var totalCount = '${totalCount}'; //전체 데이터 갯수
	var pageIndex = '${pageIndex}'; //현재 페이지 정보
	

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
		
		$(document).on("keyup", "input:text[name='weekMins']", function() {
			$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
		});
		
		$(document).on("keyup", "input:text[name='weekSchMins']", function() {
			changeElementAll();
		});
		
		
		$(document).on("change", "#selectMin", function() {
			var selectedVal = $(this).val();
			if(selectedVal != ""){
				$("input:text[name='weekSchMins']").each(function(index) { 
					$(this).val(selectedVal);
				});
				changeElementAll();
			} else {
				$("input:text[name='weekSchMins']").each(function(index) { 
					$(this).val("");
				});
				changeElementAll();
			}
		});
		
		$(document).on("click", "input:radio[name='weekStudyType']:checked", function() {
			var checkedVal = $(this).val();
			if(checkedVal != ""){
				if(checkedVal == "FREE"){
					$('#weekStudy').val(""); 
					$("#weekStudy").attr("disabled",true);
				} else {
					$("#weekStudy").attr("disabled",false);
				}
			}
		});
		
		
		
		$(document).on("click", "input:radio[name='attendReflectType']:checked", function() {
			var checkedVal = $(this).val();
			if(checkedVal != ""){
				if(checkedVal == "NOW"){
					$('#attendNextDay').val("");
					$("#attendNextDay").attr("disabled",true);
				} else {
					$("#attendNextDay").attr("disabled",false);
				}
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
		com.datepickerDateFormat('startDate', 'button');
		com.datepickerDateFormat('endDate', 'button');
		
		<c:forEach var="result" items="${resultList}" varStatus="status">
			com.datepickerDateFormat("weekStDate${result.weekCnt}" , 'button');
			com.datepickerDateFormat("weekEdDate${result.weekCnt}" , 'button');
		</c:forEach>
		
		<c:choose>
			<c:when test="${onlineTraningVO.weekStudyType eq 'FREE'}">
				$("#weekStudy").val("");
				$("#weekStudy").attr("disabled",true);
			</c:when>
			<c:otherwise>
				$("#weekStudy").attr("disabled",false);
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${onlineTraningVO.attendReflectType eq 'NOW'}">
				$("#attendNextDay").val("");
				$("#attendNextDay").attr("disabled",true);
			</c:when>
			<c:otherwise>
				$("#attendNextDay").attr("disabled",false);
			</c:otherwise>
		</c:choose>
		
		
		
		$("#startDate").val("${subjectVO.traningStDate}");
		$("#pageSize").val(pageSize); //페이지당 그리드에 조회 할 Row 갯수;
		$("#pageIndex").val(pageIndex); //현재 페이지 정보
		$("#totalRow").text(totalCount);
		
	}

	/*====================================================================
		사용자 정의 함수 
	====================================================================*/
	
	function addCalender(){
		var len = $('input[name="weekSchStDates"]').length;
		var startDate = $('#startDate').val(); 
		var weekDay = 0;
		 if($('input:radio[name="weekStudyType"]:checked').val()  == "WEEK"){
			if($('#weekStudy').val() != ""){
				weekDay = parseInt($('#weekStudy').val() * 7);
			}
		} 
		
		$('input[name="weekSchStDates"]').each(function(index){
			$(this).attr("id", "week_sch_st_date_"+(index+1));
			com.datepickerDateFormat($(this).attr("id"), 'button');
		});
		
		$('input[name="weekSchEdDates"]').each(function(index){
			$(this).attr("id", "week_sch_ed_date_"+(index+1));
			com.datepickerDateFormat($(this).attr("id"), 'button');
		});
		
		
	}
	
	function moveUp(el){
		
		var addLen = $('tr[name="add_sch"]').length;
		
		if(addLen > 0){
			com.datepickerDateFormat("weekStDate${result.weekCnt}" , 'button');
			com.datepickerDateFormat("weekEdDate${result.weekCnt}" , 'button');
		} 
		
		if(addLen > 0){
			alert("차시 매핑 후 주차 이동은 불가능합니다.");
			return;
		} 
		
		var $tr = $(el).parent().parent(); // 클릭한 버튼이 속한 상위 div 요소
		moveRowUp($tr);
	}

	function moveDown(el){
		var addLen = $('tr[name="add_sch"]').length;
		
		if(addLen > 0){
			alert("차시 매핑 후 주차 이동은 불가능합니다.");
			return;
		} 
		
		var $tr = $(el).parent().parent(); // 클릭한 버튼이 속한 div 요소
		moveRowDown($tr);
	} 
	var moveRowUp = function(element) {
		if( element.prev().html() != null  && element.prev().attr("id") != "week_first" ){
			element.insertBefore(element.prev());
			changeElementAll();
		} else {
			alert("최상단입니다.");
		}
	};

	var moveRowDown = function(element) {
		if( element.prev().html() != null  ){
			element.insertAfter(element.next());
			changeElementAll();
		}  else {
			alert("최하단입니다.");
		}  
	};
	
	
	
	function removeRow(element){
		var tr = $(element).parent().parent();
	    //라인 삭제
	    tr.remove();
		changeElementAll();
	}
	
	var changNum = function() {
		var num = 0;
		$('td[name=weekText]').each(function(){
			num++;
			$(this).text(num+"주차");
		});
	};
	
	
	function changeElementAll(){
		var len = $('td[name=weekText]').length;
		var minNum = 0;
		var weekSchLen = $("input:text[name='weekSchMins']").length;
		for(var i = 0; i < len; i++){
			$('td[name=weekText]').eq(i).text((i+1)+"주차");
			var unitLen = $(".add_sch_"+(i+1)).length;
			for(var y=0; y < unitLen; y++){
				// 차시명 변경
				$(".add_sch_"+(i+1)+" > td[name='week_sch_text']").eq(y).text((y+1)+"차시");
				
				// 주차별 합산시간을 합침
				minNum += Number($(".add_sch_"+(i+1)).find("input:text[name='weekSchMins']").eq(y).val());
				//console.log(minNum);
			} 
			if(minNum == 0){
				$('input:text[name=weekMins]').eq(i).val("");
			} else {
				$('input:text[name=weekMins]').eq(i).val(minNum);
			}
				
			minNum = 0;
		}
		
		$("tr[name='week']").each(function(index) { 
			$(this).removeClass();
			$(this).addClass("week_"+(index+1));
		});
	}
	
	
	function moveSchUp(el){
		var $tr = $(el).parent().parent(); // 클릭한 버튼이 속한 tr 요소
		moveSchRowUp($tr);
	}

	function moveSchDown(el){
		var $tr = $(el).parent().parent(); // 클릭한 버튼이 속한 tr 요소
		moveSchRowDown($tr);
	} 
	
	var moveSchRowUp = function(element) {
		if( element.prev().html() != null  && element.prev().attr("class") != "week_1" ){
			changeElement(element , 'prev')
			element.insertBefore(element.prev());
			changeElementAll();
		} else {
			alert("최상단입니다.");
		}
	};

	var moveSchRowDown = function(element) {
		
		 if( element.next().html() != null ){
			changeElement(element , 'next')
			element.insertAfter(element.next());
			changeElementAll();
		}  else {
			alert("최하단입니다.");
		} 
	};
	
	
	function changeElement(element, type) {
		var prev = element.prev();
		var next = element.next();
		var nowClass = element.attr("class");
		//alert(type);
		if(type == "next"){
			//alert(next.attr("class").indexOf("week_"));
			if(next.attr("class").indexOf("week_") == 0 ){
				//alert("next.attr(class) : "+next.attr("class"));
				var num = next.attr("class").split("_")[1];
				//alert("next num : "+num);
				element.removeClass();
				//alert("add_sch_"+num);
				element.addClass("add_sch_"+num);
			}
		}
		
		if(type == "prev"){
			//alert(next.attr("class").indexOf("week_"));
			if(prev.attr("class").indexOf("week_") == 0 ){
				//alert("prev.attr(class) : "+prev.attr("class"));
				var num = prev.attr("class").split("_")[1];
				//alert("prev num : "+num);
				element.removeClass();
				//alert(element.attr("class"));
				//alert("add_sch_"+(num-1));
				element.addClass("add_sch_"+(num-1));
			}
		}
	}
	
	function press(event) {
		if (event.keyCode==13) {
			fn_search('1');
		}
	}

	/* 리스트 조회 */
	function fn_search( param1 ){
		
		$("#pageIndex").val( param1 );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "listTrain.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 상세조회 페이지로 이동 */
	function fn_read( param1 ){
		
		//컨테츠 미리보기 테스트때문에 주석처리함.
		$("#id").val( param1 );
		//$("#id").val( '2' );
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "getTrain.do";
		$("#frmTrain").attr("action", reqUrl);
		$("#frmTrain").submit();
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_showLearningpop(){
		$(".learning-area,.popup-bg").addClass("open"); 
		window.location.hash = "#open";
	}
	
	/* 컨텐츠 미리보기 레이어팝업 open */
	function fn_hideLearningpop(){
		$(".learning-area,.popup-bg").removeClass("open");
	}
	
	function fn_opener_add_schedule( data, weekCnt, courseContentId, divisionNum, contentName){
		var jsonData = JSON.parse(data);
		var rowLen = $(".add_sch_"+weekCnt).length;
		var schStr = "";
		var weekNum = 1;
		var schNum = 0;
		var weekLen = $('tr[name="week"]').length;
		if(divisionNum > 0){
			$('tr[name="add_sch"]').each(function(){
				$(this).remove();
			});
			for (var i = 0; i < jsonData.body.list.length; i++) {		
			    var list = jsonData.body.list[i];
			    var registered_from_device_type_code = list.registered_from_device_type_code;
			    var chkVal = "CMS|"+list.title+"|"+courseContentId+"|"+list.lesson_id+"|"+list.id+"|"+registered_from_device_type_code;
			    
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
				
				schNum++;
				
			    schStr += "<tr name='add_sch' class='add_sch_"+weekNum+"' id='row_"+(i+1)+"'>";
				schStr += "<td></td>";
				schStr += "<td>ㄴ</td>";
				schStr += "<td name='week_sch_text'>"+schNum+"차시</td>";
				schStr += "<td class='left'>"+list.title;
				
				
				schStr+="	<input type='hidden' name='subjTitles' value='"+list.title+"' />";
				schStr+="	<input type='hidden' name='linkContentTypes' value='CMS' />";
				schStr+="	<input type='hidden' name='cmsCourseContentIds' value='"+courseContentId+"' />";
				schStr+="	<input type='hidden' name='cmsLessonIds' value='"+list.lesson_id+"' />";
				schStr+="	<input type='hidden' name='cmsIds' value='"+list.id+"' />";
				schStr+="	<input type='hidden' name='contentTypes'  />";
				schStr+="	<input type='hidden' name='contentsDirs'  />";
				schStr+="	<input type='hidden' name='contentsIdxFiles'  />";
				schStr+="	<input type='hidden' name='contentsRealFiles' />";
				schStr+="	<input type='hidden' name='urls'  />";
				schStr+="	<input type='hidden' name='deviceTypeCodes' value='"+registered_from_device_type_code+"' />";
				
				schStr += "</td>";
				
				
				schStr += "<td class='left'>";
				schStr += "<input type='text' name='weekSchStDates' id='week_sch_st_date_"+(i+1)+"'  readonly style='width:65px' />~<br />";
				schStr += "<input type='text' name='weekSchEdDates' id='week_sch_ed_date_"+(i+1)+"'  readonly style='width:65px' />";
				schStr += "</td>";
				schStr += "<td><input type='text' name='weekSchMins'  id='week_sch_min_"+(rowLen + i)+"'  class='week_min_"+weekNum+"'  maxlength='3' style='width:25px; text-align:center;' /></td>";
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
				
				if( (i+1) % divisionNum == 0){
					$(".week_"+weekNum).eq(-1).after(schStr);
					$("input:text[name='contentNames']").eq((weekNum-1)).val(""+weekNum+" 주차 "+contentName);
					schStr = "";
					weekNum++;
					schNum=0;
				}
			} 
			
		} else {
			for (var i = 0; i < jsonData.body.list.length; i++) {		
			    var list = jsonData.body.list[i];
			    
			    var registered_from_device_type_code = list.registered_from_device_type_code;
			    var chkVal = "CMS|"+list.title+"|"+courseContentId+"|"+list.lesson_id+"|"+list.id+"|"+registered_from_device_type_code;
			    
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
				schStr += "<td name='week_sch_text'>"+(rowLen + (i+1))+"차시</td>";
				
				schStr += "<td class='left'>"+list.title;
				
				
				schStr+="	<input type='hidden' name='subjTitles' value='"+list.title+"' />";
				schStr+="	<input type='hidden' name='linkContentTypes' value='CMS' />";
				schStr+="	<input type='hidden' name='cmsCourseContentIds' value='"+courseContentId+"' />";
				schStr+="	<input type='hidden' name='cmsLessonIds' value='"+list.lesson_id+"' />";
				schStr+="	<input type='hidden' name='cmsIds' value='"+list.id+"' />";
				schStr+="	<input type='hidden' name='contentTypes'  />";
				schStr+="	<input type='hidden' name='contentsDirs'  />";
				schStr+="	<input type='hidden' name='contentsIdxFiles'  />";
				schStr+="	<input type='hidden' name='contentsRealFiles' />";
				schStr+="	<input type='hidden' name='urls'  />";
				schStr+="	<input type='hidden' name='deviceTypeCodes' value='"+registered_from_device_type_code+"' />";
				
				schStr += "</td>";
				
				
				schStr += "<td class='left'>";
				schStr += "<input type='text' name='weekSchStDates' id='week_sch_st_date_"+(rowLen + (i+1))+"'  readonly style='width:65px' />~<br />";
				schStr += "<input type='text' name='weekSchEdDates' id='week_sch_ed_date_"+(rowLen + (i+1))+"'  readonly style='width:65px' />";
				schStr += "</td>";
				schStr += "<td><input type='text' name='weekSchMins'  id='week_sch_min_"+(rowLen + (i+1))+"'  class='week_min_"+weekCnt+"'  maxlength='3' value='' style='width:25px; text-align:center;' /></td>";
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
			} 
			
			if(rowLen > 0){
				$(".add_sch_"+weekCnt).eq(-1).after(schStr);
			} else {
				$(".week_"+weekCnt).eq(-1).after(schStr);
			}
		}
		
		addCalender();
	}
	
	function fn_opener_add_row(data, weekCnt){
		var rowLen = $(".add_sch_"+weekCnt).length;
		
		if(rowLen > 0){
			$(".add_sch_"+weekCnt).eq(-1).after(data);
		} else {
			$(".week_"+weekCnt).eq(-1).after(data);
		}
		
		addCalender();
	}
	
	function fn_opener_add_movie_row(data, weekCnt){
		var rowLen = $(".add_sch_"+weekCnt).length;
		if(rowLen > 0){
			$(".add_sch_"+weekCnt).eq(-1).after(data);
		} else {
			$(".week_"+weekCnt).eq(-1).after(data);
		}
		
		addCalender();
	}
	
	function fn_content_list_pop(weekCnt) { 
		popOpenWindow("", "popSearch", 1000, 750);
		var reqUrl = "/lu/online/popupOnlineTraning.do";
		$("#weekCnt").val(weekCnt);
		$("#frmPop").attr("action", reqUrl);
		$("#frmPop").attr("target", "popSearch");
		$("#frmPop").submit();
	} 
	
	
	function fn_saveOnStand() { 
		
		var startDate 			= $("#startDate").val().split(".").join("");
		var endDate 			= $("#endDate").val().split(".").join("");
		var attendProgress = $("#attendProgress").val();
		var cutProgress 		= $("#cutProgress").val();
		
		if(startDate == ""){
			alert("[시작일]은 반드시 선택해야 합니다.");	
			$("#startDate").focus();
			return;
		} 
		
		if(endDate == ""){
			alert("[종료일]은 반드시 선택해야 합니다.");
			$("#endDate").focus();
			return;
		}	
		
		if(Number(startDate) > Number(endDate)){
			alert("[시작일]이 [종료일]보다 클 수 없습니다.");
			$("#endDate").focus();
			return;
		}
		
		if(!$('input:radio[name=weekStudyType]').is(':checked')){
			alert("[주차 학습기간]은 반드시 선택해야 합니다.");
			$("#weekStudyType1").focus();
			return;
		} else {
			if($('input:radio[name="weekStudyType"]:checked').val()  == "WEEK"){
				if($('#weekStudy').val() == ""){
					alert("[주차]는 반드시 선택해야 합니다.");
					$("#weekStudy").focus();
					return;
				}
			}
		}
		
		if(attendProgress == ""){
			alert("[출석 진도율]은 반드시 선택해야 합니다.");
			$("#attendProgress").focus();
			return;
		}
		
		if(cutProgress == ""){
			alert("[결석 진도율]은 반드시 선택해야 합니다.");
			$("#cutProgress").focus();
			return;
		}
		
		if(Number(attendProgress) <= Number(cutProgress)){
			alert("[결석 진도율]이 [출석 진도율]보다 크거나 같을 수 없습니다.");
			$("#cutProgress").focus();
			return;
		}
		
		
		if(!$('input:radio[name=attendReflectType]').is(':checked')){
			alert("[출석반영일]은 반드시 선택해야 합니다.");
			$("#attendReflectType1").focus();
			return;
		} else {
			if($('input:radio[name="attendReflectType"]:checked').val()  == "NEXT"){
				if($('#attendNextDay').val() == ""){
					alert("[반영요일]은 반드시 선택해야 합니다.");
					$("#attendNextDay").focus();
					return;
				}
			}
		}
		
		if(!$('input:radio[name=progressAttendTypeCode]').is(':checked')){
			alert("[진도방식]은 반드시 선택해야 합니다.");
			$("#progressAttendTypeCode1").focus();
			return;
		}
		
		if(confirm("온라인훈련 기준정보를 저장 하시겠습니까?")){
			var reqUrl = CONTEXT_ROOT + "/lu/online/saveOnlineTraningStand.json";
		 	var param = $("#frmStand").serializeArray();
			com.ajax(reqUrl, param, function(obj, data, textStatus, jqXHR){	
				if (200 == jqXHR.status ) {
					var retData 	= jqXHR.responseJSON.retData;
					var retCd 	= jqXHR.responseJSON.retCd;
					if(retCd == 10000 && retData != "" ){
						//alert("저장 되었습니다.")
						$("#subjOnStandSeq").val(retData);
					} else {
						alert("등록에 실패했습니다.")
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
	
	
	function fn_saveOnSchedule(){
		
		if($('#subjOnStandSeq').val() == ""){
			alert("온라인훈련 기준정보를 먼저 등록해야 합니다.")
			return;
		}
		var weekLen = $('input:hidden[name=weekIds]').length;
		$('#frmWeekSch_weekStudyType').val($('input:radio[name="weekStudyType"]:checked').val());
		var weekStudyType = $('#frmWeekSch_weekStudyType').val();
		var weekSchDelim = "";
		if(weekLen > 0){
			for( var i=0; i < weekLen; i++){
				
				if($('input:text[name=contentNames]').eq(i).val() == ""){
					alert("[콘텐츠명]을 입력해야 합니다.");
					$('input:text[name=contentNames]').eq(i).focus();
					return;
				}
				if(weekStudyType != "FREE") {
					if($('input:text[name=weekStDates]').eq(i).val() == ""){
						alert("[주차시작일]을 선택해야 합니다.");
						$('input:text[name=weekStDates]').eq(i).focus();
						return;
					}
					
					if($('input:text[name=weekEdDates]').eq(i).val() == ""){
						alert("[주차종료일]을 선택해야 합니다.");
						$('input:text[name=weekEdDates]').eq(i).focus();
						return;
					}
				}
				if($('input:text[name=weekMins]').eq(i).val() == ""){
					alert("[주차시간(분)]을 입력해야 합니다.");
					$('input:text[name=weekMins]').eq(i).focus();
					return;
				}
				var unitLen = $(".add_sch_"+(i+1)).length;
				for(var y=0; y < unitLen; y++){
					// 학습기간 자유가 아닐경우만 체크
					if(weekStudyType != "FREE") {
						if($(".add_sch_"+(i+1)).find("input:text[name='weekStDates']").eq(y).val() == ""){
							alert("["+(i+1)+"주차 "+(y+1)+"차시] 시작일을 선택해야 합니다.");
							$(".add_sch_"+(i+1)).find("input:text[name='weekStDates']").eq(y).focus();
							return;
						}
						if($(".add_sch_"+(i+1)).find("input:text[name='weekEdDates']").eq(y).val() == ""){
							alert("["+(i+1)+"주차 "+(y+1)+"차시] 종료일을 선택해야 합니다.");
							$(".add_sch_"+(i+1)).find("input:text[name='weekStDates']").eq(y).focus();
							return;
						}
						if($(".add_sch_"+(i+1)).find("input:text[name='weekSchMins']").eq(y).val() == ""){
							alert("["+(i+1)+"주차 "+(y+1)+"차시] 시간을 입력해야 합니다.");
							$(".add_sch_"+(i+1)).find("input:text[name='weekSchMins']").eq(y).focus();
							return;
						}
					}
				} 
				weekSchDelim += $(".add_sch_"+(i+1)).find("input:hidden[name='subjTitles']").length+"|";
			}
		}
		
		weekSchDelim = weekSchDelim.substring(0,(weekSchDelim.length-1));
		console.log("---------------- weekSchDelim : "+weekSchDelim);
		$("#weekSchDelim").val(weekSchDelim);
		
		if(confirm("온라인 훈련 계획서를 등록 하시겠습니까?\n학습 근로자 학습 시작 후에는 기본정보만 수정이 가능합니다.")){
			var reqUrl = CONTEXT_ROOT + targetUrl + "insertOnlineWeekSchedule.do";
			$("#frmWeekSch").attr("method", "post" );
			$("#frmWeekSch").attr("action", reqUrl);
			$("#frmWeekSch").submit();
		}
	}
	
</script>

<!-- 회원정보 팝업용 시작 -->
<form id="frmPop" name="frmPop" method="post">
	<input type="hidden" name="weekCnt" id="weekCnt" value=""/>
	<input type="hidden" name="subjPoint" id="subjPoint" value="${subjectVO.point}"/>
</form>
<!-- 회원정보 팝업용 끝 -->
	<div id="content-area">
	<h2>온라인훈련 관리</h2>
	<h4 class="mb-010"><span>${subjectVO.subjectName}</span> (${subjectVO.subjectClass}반) ㅣ ${subjectVO.yyyy}학년도 ${subjectVO.term}학기</h4>


	<div class="group-area">
		<table class="type-1 mb-005">
			<colgroup>
				<col style="width:110px" />
				<col style="width:120px" />
				<col style="width:110px" />
				<col style="width:*" />
				<col style="width:110px" />
				<col style="width:110px" />
			</colgroup>
			<tbody>
				<tr>
					<th>교과형태</th>
					<td>${subjectVO.subjectTraningTypeName}</td>
					<th>과정구분</th>
					<td>${subjectVO.subjectTypeName}</td>
					<th>훈련시간</th>
					<td>${subjectVO.traningHour}시간</td>
				</tr>
				<tr>
					<th>담당교수</th>
					<td>${subjectVO.insName}</td>
					<th>온라인 교육형태</th>
					<td>${subjectVO.onlineTypeName} (성적비율 ${subjectVO.gradeRatio}%)</td>
					<th>선수여부</th>
					<td>${subjectVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
				</tr>
			<tbody>
		</table>
		<form id="frmStand" name="frmStand" method="post">
			<input type="hidden" name="subjOnStandSeq" id="subjOnStandSeq" value="${onlineTraningVO.subjOnStandSeq}"/>
			<table class="type-1" style="border-top:1px solid #DDD;">
				<colgroup>
					<col style="width:110px" />
					<col style="width:*" />
				</colgroup>
				<tbody>
					<tr>
						<th>학습기간</th>
						<td>
							- 전체 학습기간 : 
							<input type="text" name="startDate" id="startDate" value="${onlineTraningVO.startDate}" readonly="readonly" style="width:65px;" /> ~<input type="text"name="endDate" id="endDate" value="${onlineTraningVO.endDate}" readonly="readonly" style="width:65px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							- 주차 학습기간 : 
							<input type="radio" name="weekStudyType"  id="weekStudyType1" ${onlineTraningVO.weekStudyType eq 'WEEK' ? 'checked' : ''}  class="choice" value="WEEK"/> 
							<select name="weekStudy" id="weekStudy">
								<option value="">선택</option>
								<option value="1" ${onlineTraningVO.weekStudy eq '1' ? 'selected' : ''}>1 주</option>
								<option value="2" ${onlineTraningVO.weekStudy eq '2' ? 'selected' : ''}>2 주</option>
							</select>&nbsp;&nbsp;&nbsp;
							<input type="radio" name="weekStudyType"  id="weekStudyType2" ${onlineTraningVO.weekStudyType eq 'FREE' ? 'checked' : ''} class="choice" value="FREE"/> FREE
						</td>
					</tr>
					<tr>
						<th>출석기준</th>
						<td>
							- 출석 : 진도율&nbsp;&nbsp;
							<select name="attendProgress" id="attendProgress">
								<option value="">선택</option>
								<option value="100" ${onlineTraningVO.attendProgress eq '100' ? 'selected' : ''}>100</option>
								<option value="95" ${onlineTraningVO.attendProgress eq '95' ? 'selected' : ''}>95</option>
								<option value="90" ${onlineTraningVO.attendProgress eq '90' ? 'selected' : ''}>90</option>
								<option value="85" ${onlineTraningVO.attendProgress eq '85' ? 'selected' : ''}>85</option>
								<option value="80" ${onlineTraningVO.attendProgress eq '80' ? 'selected' : ''}>80</option>
								<option value="75" ${onlineTraningVO.attendProgress eq '75' ? 'selected' : ''}>75</option>
								<option value="70" ${onlineTraningVO.attendProgress eq '70' ? 'selected' : ''}>70</option>
								<option value="65" ${onlineTraningVO.attendProgress eq '65' ? 'selected' : ''}>65</option>
								<option value="60" ${onlineTraningVO.attendProgress eq '60' ? 'selected' : ''}>60</option>
								<option value="55" ${onlineTraningVO.attendProgress eq '55' ? 'selected' : ''}>55</option>
								<option value="50" ${onlineTraningVO.attendProgress eq '50' ? 'selected' : ''}>50</option>
								<option value="45" ${onlineTraningVO.attendProgress eq '45' ? 'selected' : ''}>45</option>
								<option value="40" ${onlineTraningVO.attendProgress eq '40' ? 'selected' : ''}>40</option>
								<option value="35" ${onlineTraningVO.attendProgress eq '35' ? 'selected' : ''}>35</option>
								<option value="30" ${onlineTraningVO.attendProgress eq '30' ? 'selected' : ''}>30</option>
								<option value="25" ${onlineTraningVO.attendProgress eq '25' ? 'selected' : ''}>25</option>
								<option value="20" ${onlineTraningVO.attendProgress eq '20' ? 'selected' : ''}>20</option>
								<option value="15" ${onlineTraningVO.attendProgress eq '15' ? 'selected' : ''}>15</option>
								<option value="10" ${onlineTraningVO.attendProgress eq '10' ? 'selected' : ''}>10</option>
							</select> 이상&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							- 결석 : 진도율&nbsp;&nbsp;
							<select name="cutProgress" id="cutProgress">
								<option value="">선택</option>
								<option value="95" ${onlineTraningVO.cutProgress eq '95' ? 'selected' : ''}>95</option>
								<option value="90" ${onlineTraningVO.cutProgress eq '90' ? 'selected' : ''}>90</option>
								<option value="85" ${onlineTraningVO.cutProgress eq '85' ? 'selected' : ''}>85</option>
								<option value="80" ${onlineTraningVO.cutProgress eq '80' ? 'selected' : ''}>80</option>
								<option value="75" ${onlineTraningVO.cutProgress eq '75' ? 'selected' : ''}>75</option>
								<option value="70" ${onlineTraningVO.cutProgress eq '70' ? 'selected' : ''}>70</option>
								<option value="65" ${onlineTraningVO.cutProgress eq '65' ? 'selected' : ''}>65</option>
								<option value="60" ${onlineTraningVO.cutProgress eq '60' ? 'selected' : ''}>60</option>
								<option value="55" ${onlineTraningVO.cutProgress eq '55' ? 'selected' : ''}>55</option>
								<option value="50" ${onlineTraningVO.cutProgress eq '50' ? 'selected' : ''}>50</option>
								<option value="45" ${onlineTraningVO.cutProgress eq '45' ? 'selected' : ''}>45</option>
								<option value="40" ${onlineTraningVO.cutProgress eq '40' ? 'selected' : ''}>40</option>
								<option value="35" ${onlineTraningVO.cutProgress eq '35' ? 'selected' : ''}>35</option>
								<option value="30" ${onlineTraningVO.cutProgress eq '30' ? 'selected' : ''}>30</option>
								<option value="25" ${onlineTraningVO.cutProgress eq '25' ? 'selected' : ''}>25</option>
								<option value="20" ${onlineTraningVO.cutProgress eq '20' ? 'selected' : ''}>20</option>
								<option value="15" ${onlineTraningVO.cutProgress eq '15' ? 'selected' : ''}>15</option>
								<option value="10" ${onlineTraningVO.cutProgress eq '10' ? 'selected' : ''}>10</option>
							</select> 미만&nbsp;&nbsp;&nbsp;
							(※사이 구간은 지각 처리)
						</td>
					</tr>
					<tr>
						<th>출석반영일</th>
						<td>
							- 학습종료 : 
							<input type="radio" name="attendReflectType" id="attendReflectType1" value="NOW" ${onlineTraningVO.attendReflectType eq 'NOW' ? 'checked' : ''} class="choice" /> 해당&nbsp;&nbsp;&nbsp;
							<input type="radio" name="attendReflectType" id="attendReflectType2" value="NEXT" ${onlineTraningVO.attendReflectType eq 'NEXT' ? 'checked' : ''} class="choice" /> 다음주&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<select name="attendNextDay" id="attendNextDay">
								<option value="">선택</option>
								<option value="2" ${onlineTraningVO.attendNextDay eq '1' ? 'selected' : ''}>월요일</option>
								<option value="3" ${onlineTraningVO.attendNextDay eq '2' ? 'selected' : ''}>화요일</option>
								<option value="4" ${onlineTraningVO.attendNextDay eq '3' ? 'selected' : ''}>수요일</option>
								<option value="5" ${onlineTraningVO.attendNextDay eq '4' ? 'selected' : ''}>목요일</option>
								<option value="6" ${onlineTraningVO.attendNextDay eq '5' ? 'selected' : ''}>금요일</option>
								<option value="7" ${onlineTraningVO.attendNextDay eq '6' ? 'selected' : ''}>토요일</option>
								<option value="1" ${onlineTraningVO.attendNextDay eq '7' ? 'selected' : ''}>일요일</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>차시기준</th>
						<td>
							<select name="scheduleTime" id="scheduleTime">
								<option value="">선택</option>
								<option value="25" ${onlineTraningVO.scheduleTime eq '25' ? 'selected' : ''}>25</option>
								<option value="30" ${onlineTraningVO.scheduleTime eq '30' ? 'selected' : ''}>30</option>
								<option value="35" ${onlineTraningVO.scheduleTime eq '35' ? 'selected' : ''}>35</option>
								<option value="40" ${onlineTraningVO.scheduleTime eq '40' ? 'selected' : ''}>40</option>
								<option value="45" ${onlineTraningVO.scheduleTime eq '45' ? 'selected' : ''}>45</option>
								<option value="50" ${onlineTraningVO.scheduleTime eq '50' ? 'selected' : ''}>50</option>
								<option value="55" ${onlineTraningVO.scheduleTime eq '55' ? 'selected' : ''}>55</option>
								<option value="60" ${onlineTraningVO.scheduleTime eq '60' ? 'selected' : ''}>60</option>
							</select> 분 이상
							&nbsp;&nbsp;
							<select name="selectMin" id="selectMin">
								<option value="">선택</option>
								<option value="30">30분</option>
								<option value="25">25분</option>
								<option value="20">20분</option>
							</select>&nbsp;(※각 차시별 일괄 선택)
						</td>
					</tr>
					<tr>
						<th>진도율 계산방식</th>
						<td>
							<input type="radio" name="progressAttendTypeCode" id="progressAttendTypeCode1" value="H" ${onlineTraningVO.progressAttendTypeCode eq 'H' ? 'checked' : ''}  class="choice" /> 시간+페이지&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="progressAttendTypeCode" id="progressAttendTypeCode2" value="T" ${onlineTraningVO.progressAttendTypeCode eq 'T' ? 'checked' : ''}  class="choice" /> 시간&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="progressAttendTypeCode" id="progressAttendTypeCode3" value="P" ${onlineTraningVO.progressAttendTypeCode eq 'P' ? 'checked' : ''}  class="choice" /> 페이지
						</td>
					</tr>
				<tbody>
			</table>
		</form>
		<div class="btn-area align-right mt-010"><a href="#none" onclick="fn_saveOnStand();" class="orange">설정 저장</a></div>
	</div>


	<form id="frmWeekSch" name="frmWeekSch" method="post">
		<input type="hidden" name="weekSchDelim" id="weekSchDelim" value=""/>
		<input type="hidden" name="weekStudyType" id="frmWeekSch_weekStudyType" value=""/>
		<table class="type-2 mt-020" id="weekTable">
			<colgroup>
				<col style="width:35px" />
				<col style="width:50px" />
				<col style="width:50px" />
				<col style="width:*" />
				<col style="width:140px" />
				<col style="width:60px" />
				<col style="width:60px" />
				<col style="width:60px" />
				<col style="width:60px" />
				<col style="width:60px" />
				<col style="width:60px" />
				<col style="width:40px" />
			</colgroup>
			<tr id="week_first">
				<th><input type="checkbox" name="" value="" class="choice" /></th>
				<th>주차</th>
				<th>차시</th>
				<th>콘텐츠명</th>
				<th>학습기간</th>
				<th>학습시간</th>
				<th>미리보기</th>
				<th>삭제</th>
				<th>콘텐츠</th>
				<th>보조자료</th>
				<th>지원기기</th>
				<th>이동</th>
			</tr>
		
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr name="week" class="week_${result.weekCnt}">
					<td><input type="checkbox" name="" value="" class="choice" /></td>
					<td name="weekText">${result.weekCnt}주차</td>
					<td></td>
					<td class="left">
					<input type="text" name="contentNames" id="contentName${result.weekCnt}" value="${result.contentName}" style="width:92%;" />
					<input type="hidden" name="weekCnts" id="weekCnt${result.weekCnt}" value="${result.weekCnt}"  />
					<input type="hidden" name="weekIds" id="weekId${result.weekId}" value="${result.weekId}" />
					</td>
					
					<td class="left">
						<input type="text" name="weekStDates" id="weekStDate${result.weekCnt}" value="${result.weekStDate}"  style="width:65px" readonly />~<br />
						<input type="text" name="weekEdDates" id="weekEdDate${result.weekCnt}" value="${result.weekEdDate}" style="width:65px" readonly />
					</td>
					<td><input type="text" name='weekMins'  id="week_min_${result.weekCnt}" value="${result.weekMin}" maxlength="3" style="width:25px; text-align:center;" /></td>
					<td><!-- <a href="11_학습자료실_view.html" class="btn-search-gray"></a> --></td>
					<td></td>
					<td><a href="#!" onclick="fn_content_list_pop('${result.weekCnt}');" class="btn-line-blue">등록</a></td>
					<td></td>
					<td></td>
					<td><a href="#none" onclick="moveUp(this);" class="text">▲</a><br /><a href="#none" onclick="moveDown(this);" class="text">▼</a></td>
				</tr>
				
				<c:forEach var="sch" items="${scheduleList}" varStatus="sch_status">
					<c:if test="${result.weekId eq sch.weekId}">
						<tr class="add_sch_${status.count}" id="row_${sch_status.count}" name="add_sch">
						<td></td>
						<td>ㄴ</td>
						<td name="week_sch_text">${sch.subjStep}차시</td>
						<td class="left">${sch.subjTitle}	
						<input name="subjTitles" type="hidden" value="${sch.subjTitle}">	
						<input name="linkContentTypes" type="hidden" value="${sch.linkContentYn eq 'Y' ? 'CMS' : 'LMS'}">	
						<input name="cmsCourseContentIds" type="hidden" value="${sch.cmsCourseContentId}">	
						<input name="cmsLessonIds" type="hidden" value="${sch.cmsLessonId}">	
						<input name="cmsIds" type="hidden" value="${sch.cmsId}">	
						<input name="contentTypes" type="hidden" value="${sch.contentType}">	
						<input name="contentsDirs" type="hidden" value="${sch.contentsDir}">	
						<input name="contentsIdxFiles" type="hidden" value="${sch.contentsIdxFile}">	
						<input name="contentsRealFiles" type="hidden" value="${sch.contentsRealFile}">	
						<input name="urls" type="hidden" value="${sch.url}">	
						<input name="deviceTypeCodes" type="hidden" value="${sch.deviceTypeCode}">
						</td>
						<td class="left">
						<input name="weekSchStDates"  id="week_sch_st_date_${sch_status.count}" value="${sch.startDate}"  style="width: 65px;" type="text" readonly>~<br>
						<input name="weekSchEdDates" id="week_sch_ed_date_${sch_status.count}" value="${sch.endDate}"  style="width: 65px;" type="text" readonly>
						</td>
						<td><input name="weekSchMins" class="week_min_1" id="week_sch_min_${sch_status.count}" value="${sch.studyTime}" style="width: 25px; text-align: center;" type="text" maxlength="3"></td>
						<td><a class="btn-search-gray " href="#none"></a></td>
						<td><a class="btn-line-gray" onclick="removeRow(this);" href="#none">삭제</a></td>
						<td><a class="btn-line-yellow" href="#!">편집</a></td>
						<td><a class="btn-line-blue" onclick="#" href="#!">등록</a></td>
						<td>PC + 모바일 (앱 설치 필요)</td><td><a onclick="moveSchUp(this);" href="#none"><img src="/images/oklms/std/inc/arrow_top.png"></a><br><a onclick="moveSchDown(this);" href="#none"><img src="/images/oklms/std/inc/arrow_down.png"></a></td>
						</tr>
					</c:if>
				</c:forEach>
			</c:forEach>
		</table>
	</form>
	<div class="btn-area align-right mt-010">
		<a href="#!" class="gray-1 float-left">교과목 정보</a>
		<a href="#!" class="yellow">콘텐츠 미리보기</a>
		<!-- <a href="#none" onclick="" class="orange">콘텐츠 등록</a> -->
		<a href="#" onclick="fn_saveOnSchedule();" class="orange">변경내용 저장</a>
	</div><!-- E : btn-area -->
</div><!-- E : content-area -->
	
<script type="text/javascript">
	addCalender();
</script>
	
	
					

	