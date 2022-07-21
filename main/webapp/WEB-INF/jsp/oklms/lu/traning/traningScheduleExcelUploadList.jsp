<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<script type="text/javascript">

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

		//기업체 검색 팝업 조회 성공여부
		var returnMsg = "";
		var subjectName = "";
		var searchYyyyCodeId = "";
		var resultScheduleViewListCnt = "";

		returnMsg = '${returnMsg}';
		subjectName = '${currProcReadVO.subjectName}';
		searchYyyyCodeId = '${searchYyyyCodeId}';
		resultScheduleViewListCnt = '${resultScheduleViewListCnt}';

		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			fn_showCompanypop();
		}

		if(searchYyyyCodeId == ''){
			$("#displaly2").show();
			$("#displaly3").hide();
		} else {
			$("#displaly2").hide();
			$("#displaly3").show();
		}

		//alert("subjectName: "+subjectName);
		//alert("searchYyyyCodeId: "+searchYyyyCodeId);

		if(subjectName != ''){
			$('#file-input').on("change", previewImages);$('#file-input').on("change", previewImages);
			$("#displaly1").show();
		}

		if(resultScheduleViewListCnt != '0'){
			$("#displaly5").show();
			$("#displaly4").hide();
			$("#displaly6").hide();
			$("#displaly7").hide();
		} else {
			$("#displaly4").show();
			$("#displaly5").hide();
		}
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );
		//var reqUrl = CONTEXT_ROOT + "/lu/popup/goPopupSearch.do";

		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";
		//var reqUrl = CONTEXT_ROOT + "/lu/popup/goPopupSearch.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = "/lu/traning/listTraningSchedule.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//개설강좌 조회
	function fn_goSearch(){

		if($("#companyId").val() == ''){
			alert("기업체 정보가 없습니다. 기업체 검색 버튼을 클릭하여 기업체를 선택하여주십시오.");
			return false;
		}

		if($("#searchYyyy").val() == ''){
			alert("학년도를 선택하여주십시오.");
			$("#searchYyyy").focus();
			return false;
		}

		if($("#searchTerm").val() == ''){
			alert("학기를 선택하여주십시오.");
			$("#searchTerm").focus();
			return false;
		}

		if($("#searchSubClass").val() == ''){
			alert("분반을 선택하여주십시오.");
			$("#searchSubClass").focus();
			return false;
		}

		if($("#searchSubjectCode").val() == ''){
			alert("개설강좌명을 선택하여주십시오.");
			$("#searchSubjectCode").focus();
			return false;
		}

		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	function fn_showCompanypop( ){
		$("#student-popup").show();
		$(".company-area,.popup-bg").addClass("open");
		window.location.hash = "#open";
	}

	function fn_closeCompanypop( ){
		$("#student-popup").hide();
		$(".company-area,.popup-bg").removeClass("open");
	}

	function fn_hideCompanypop( ){
		var obj = "";
		obj = $("input:radio[name='tempCompanyId']:checked").val();
		if(undefined == obj){
			alert("훈련시간표등록을 처리할 하나의 기업체를 선택하여주십시오. ")
			return false;
		} else {
			$(".company-area,.popup-bg").removeClass("open");
			fn_setCompanypopInfo(obj);
		}
	}

	// 기업체 정보 셋팅
	function fn_setCompanypopInfo(obj){
		if( obj ){
			var arrInfo = obj.split("||");
			var companyId = arrInfo[0];
			/* var companyName = arrInfo[1];
			var address = arrInfo[2];
			var choiceDay = arrInfo[3];
			var employInsManageNo = arrInfo[4]; */
			var traningProcessId = arrInfo[5];
			//var hrdTraningName = arrInfo[6];

			$("#companyId").val(companyId);
			$("#traningProcessId").val(traningProcessId);
			/* $("#companyName").html(companyName);
			$("#address").html(address);
			$("#choiceDay").html(choiceDay);
			$("#employInsManageNo").html(employInsManageNo);
			$("#hrdTraningName").html(hrdTraningName); */

			/* $("#displaly2").hide();
			$("#displaly3").show(); */

			fn_goSearchList( companyId, traningProcessId );

		} //obj 훈련시간표등록 목록 if문 End
	}


	//기업체 검색후 기업체명 한건을 선택시
	function fn_goSearchList( param1, param2 ){
		$("#companyId").val(param1);
		$("#traningProcessId").val(param2);
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//첫화면으로 이동
	function fn_goList( ){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningSchedule.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}
	
	function fn_delete(flag){
		
		if( flag == "" ){
			alert("등록 된 훈련시간표가 없습니다.");
			return;
		} else {
			// flag : 1 승인대기 , 2 승인 3 반려
			if( flag == "2" || flag == "3" ){
				alert("반려나 승인중인 훈련시간표는 삭제가 불가능합니다.");
				return;
			} else {
				if(confirm("승인대기중인 시간표를 삭제하시겠습니까?")){
					var reqUrl = CONTEXT_ROOT + "/lu/traning/deleteTraningSchedule.do";

					$("#frmTraning").attr("action", reqUrl);
					$("#frmTraning").attr("target", "_self");
					$("#frmTraning").submit(); 
				}
			}
		}
		

		
	}
	
	

	function previewImages() {
		  var $preview = $('#preview').empty();
		  $("#fileName").val($('#file-input').val());
		  var filesize = 0;
		  if (this.files) {
			  $.each(this.files, readAndPreview);
		  }

		  function readAndPreview(i, file) {
			if (window.FileReader) { // FireFox, Chrome, Opera 확인.
				//if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
				//      return alert(file.name +" is not an image");
			    //} // else...
			    filesize = filesize + file.size;
				if(filesize > 10000000){ //Checking Sum 10M Size
					alert("전체 사이즈 10M이상 업로드 할수 없습니다.");

					$("#uploadExcelFile").val("");

					return false;
				}else{

					var filesizeNumber = "";
					if(filesize>1000000){
						filesizeNumber = ((filesize/1000000).toFixed(2))+" M";
					}else if(filesize>1000){
						filesizeNumber = ((filesize/1000).toFixed(2))+" KB";
					}else{
						filesizeNumber = filesize+" B";
					}

					$("#fileSizeName").html( filesizeNumber +" / 10M");
				}
			}else{ // safari is not supported FileReader
		        alert('not supported Webbrowser!!');
		    }
		  }
	}

	/* 훈련시간표 엑셀일괄등록 */
	function fn_excel_upload(){

		if($("input:file[name=uploadExcelFile]").val() == ""){
			alert("첨부할 파일이 존재하지 않습니다.");
			return;
		}

		if($("#companyId").val() == ''){
			alert("훈련시간표를 일괄등록할 기업체를 선택해주세요.!");
			return;
		}

		var src = $("#uploadExcelFile").val();

		 if(!src.match(/\.(xls)$/i)) {
		      alert("엑셀(xls) 파일만 업로드 가능합니다.");
		      return;
		}

		if(confirm("작성한 엑셀파일로 훈련시간표를 일괄로 임시등록 하시겠습니까?")){

			$("#tempTraningProcessId").val('noDivPopup');

		    var reqUrl = CONTEXT_ROOT + "/lu/traning/insertTraningScheduleExcel.do";

		    $("#frmTraning").attr("method", "post" );
			$("#frmTraning").attr("enctype", "multipart/form-data" );
			$("#frmTraning").attr("action", reqUrl);
			$("#frmTraning").attr("target","_self");
			$("#frmTraning").submit();
		}
	}

	/* 훈련시간표 엑셀일괄등록 양식 다운로드 */
	function fn_excel_file_down(){
		var uploadFilePath = "/downloadfiles/";
	    $("#filename").val("TrainingScheduleSaveForm.xls");
	    $("#uploadFilePath").val(uploadFilePath);

	    var reqUrl = CONTEXT_ROOT + "/simpleDown.sv";
	    $("#frmDownLoad").attr("action",reqUrl);
	    $("#frmDownLoad").attr("target","_self");
	    $("#frmDownLoad").submit();
	}

	/* 훈련시간표보기 목록 조회 */
/* 	function fn_scheduleList(){
		var reqUrl = CONTEXT_ROOT + "/lu/traning/listTraningScheduleView.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	} */

	/* 훈련시간표보기 목록 조회 */
	function fn_scheduleList(){
		$("#scheduleViewYn").val('Y');

		var reqUrl = CONTEXT_ROOT + "/lu/traning/getTraningSchedule.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

</script>

<!-- 훈련시간표등록 템플릿다운로드 항목 -->
<form id="frmDownLoad" >
	<input type="hidden"  name="filename" id="filename"  />
	<input type="hidden"  name="uploadFilePath" id="uploadFilePath" />
</form>

<!-- 훈련시간표등록 관련 항목 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="companyId" name="companyId" value="${companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningProcessId}" />
<input type="hidden" id="yyyy" name="yyyy" value="${currProcReadVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${currProcReadVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${currProcReadVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${currProcReadVO.subClass}" />
<input type="hidden" id="scheduleViewYn" name="scheduleViewYn" />
<input type="hidden" id="uiGubun" name="uiGubun" value="traningProcessCompanyPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="/lu/traning/traningScheduleExcelUploadList" />
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>

<ul id="student-popup" style="display:none;">
	<li class="company-area" style="margin-left:-350px; margin-top:-139px;">
		<h1>기업체 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="기업명을 입력" value="${searchKeyword }" />
			<a href="#!" onclick="javascript:fn_search(1); return false" onkeypress="this.onclick();">조회</a>
			<a href="#!" onclick="javascript:fn_searchKeywordNo(1); return false" onkeypress="this.onclick();">전체조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="7%" />
					<col width="*" />
					<%-- <col width="*" /> --%>
					<col width="*" />
					<col width="*" />
					<col width="*" />
					<col width="*" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>기업명</th>
						<!-- <th>소재지</th> -->
						<th>선정일</th>
						<th>기업고용관리번호</th>
						<th>훈련과정명</th>
						<th>훈련과정번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultSearchList}" varStatus="status">
					<tr>
						<td><input type="radio" name="tempCompanyId" id="tempCompanyId" value="${result.companyId}||${result.companyName}||${result.address}||${result.choiceDay}||${result.employInsManageNo}||${result.traningProcessId}||${result.hrdTraningName}"></td>
						<td>${result.companyName}</td>
						<%-- <td class="left">${result.address}</td> --%>
						<td>${result.choiceDay}</td>
						<td>${result.employInsManageNo}</td>
						<td>${result.hrdTraningName}</td>
						<td>${result.hrdTraningNo}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultSearchList) == 0}">
					<tr>
						<td colspan="7"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 기업체검색 -->

			<c:if test="${fn:length(resultSearchList) != 0}">
			<div class="page-num mt-015">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_searchPaging" />
			</div>
			</c:if>

		<div class="btn-area align-center mt-010">
			<a href="#fn_closeCompanypop" class="yellow close" onclick="javascript:fn_closeCompanypop(); return false" onkeypress="this.onclick();">닫기</a>
			<a href="#fn_hideCompanypop" class="orange close" onclick="javascript:fn_hideCompanypop(); return false" onkeypress="this.onclick();">확인</a>
		</div><!-- E : btn-area -->
	</li>

	<li class="popup-bg"></li>
</ul><!-- E : student-popup -->

	<div id="">
	<h2>훈련시간표 등록</h2>

	<div id="displaly2">
	<table class="type-1-1">
		<colgroup>
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th>기업명</th>
			<th>소재지</th>
			<th>선정일</th>
			<th>기업고용보험관리번호</th>
			<th>훈련과정명</th>
			<th>훈련과정번호</th>
		</tr>
		<tr>
			<td colspan="6">자료가 없습니다. 기업체 검색버튼을 클릭하여 하나의 기업체를 선택해주세요</td>
		</tr>
	</table>
	</div>

	<div id="displaly3">
	<table class="type-1-1">
		<colgroup>
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th>기업명</th>
			<th>소재지</th>
			<th>선정일</th>
			<th>기업고용보험관리번호</th>
			<th>훈련과정명</th>
			<th>훈련과정번호</th>
		</tr>
		<c:forEach var="result" items="${resultList}" varStatus="status">
		<tr>
			<td>${result.companyName}</td>
			<td>${result.address}</td>
			<td>${result.choiceDay}</td>
			<td>${result.employInsManageNo}</td>
			<td>${result.hrdTraningName}</td>
			<td>${result.hrdTraningNo}</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(resultList) == 0}">
		</c:if>
	</table>
	</div>

	<div class="btn-area align-right mt-010">
		<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">기업체 검색</a>
	</div><!-- E : btn-area -->

	<div class="search-box-1 mt-040 ">
		학년도:
		<select id="searchYyyy" name="searchYyyy" onchange="">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="yearSubjCd" items="${yearSubjCode}" varStatus="status">
				<option value="${yearSubjCd.codeId}" ${yearSubjCd.codeId == traningSchVO.searchYyyy ? 'selected="selected"' : '' }>${yearSubjCd.codeName} 학년도</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		학기:
		<select id="searchTerm" name="searchTerm" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="termSubjCd" items="${termSubjCode}" varStatus="status">
				<option value="${termSubjCd.codeId}" ${termSubjCd.codeId == traningSchVO.searchTerm ? 'selected="selected"' : '' }>${termSubjCd.codeName} 학기</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		개설교과명:
		<select id="searchSubjectCode" name="searchSubjectCode">
			<option value="" selected="selected">::선택::</option>
			<c:forEach var="subjNameSubjCd" items="${subjNameSubjCode}" varStatus="status">
				<option value="${subjNameSubjCd.codeId}" ${subjNameSubjCd.codeId == traningSchVO.searchSubjectCode ? 'selected="selected"' : '' }>${subjNameSubjCd.codeName}</option>
			</c:forEach>
		</select>
		분반:
		<select id="searchSubClass" name="searchSubClass" onchange="">
		<option value="" selected="selected">::선택::</option>
			<c:forEach var="classSubjCd" items="${classSubjCode}" varStatus="status">
				<option value="${classSubjCd.codeId}" ${classSubjCd.codeId == traningSchVO.searchSubClass ? 'selected="selected"' : '' }>${classSubjCd.codeName} 분반</option>
			</c:forEach>
		</select>&nbsp;&nbsp;
		
		<a href="#fn_goSearch" onclick="javascript:fn_goSearch(); return false" onkeypress="this.onclick();">조회</a>
	</div><!-- E : search-box-1 -->

	<div id="displaly1" style="display:none;">

	<h4 class="mt-020"><span>${currProcReadVO.subjectName}</span> (${currProcReadVO.subClass}반) ㅣ ${currProcReadVO.yyyy}학년도 ${currProcReadVO.term}학기</h4>

	<span class="mt-020 float-left"><font color="blue">※ "템플릿 다운로드후 엑셀파일 각항목 제목에 노란색"</font>으로 표시된 부분이 필수사항이므로 입력후 엑셀업로드 버튼클릭해주세요.<br><font color="blue">※"최대 15주차까지"</font> 개설강좌만 엑셀 일괄업로드 가능합니다.</span>


	<div class="group-area mt-010">
		<div id="displaly4" style="display:none;">
		<table class="type-write">
			<colgroup>
				<col style="width:100px" />
				<col style="width:150px" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th rowspan="3">훈련<br />시간표 등록</th>
				<th>주요교과목 및 내용</th>
				<td><input type="text" id="subjctTitle" name="subjctTitle" maxlength="298" style="width:50%;" ></td>
			</tr>
			<!-- <tr>
				<th rowspan="2" class="border-left">등록할 시간표</th>
				<td><a href="#!" class="text-file">일학습병행제-1(20170303).XLS</a> <a href="#!" class="btn-del">첨부파일 삭제</a></td>
			</tr> -->
			<tr>
				<th class="border-left">등록할 시간표</th>
				<td class="border-left">
					<input type="file" name="uploadExcelFile" id="uploadExcelFile" style="width:50%;" />
					<!-- <input type="text" id="uploadExcelFile" name="uploadExcelFile" style="width:50%;" readonly>
					<span>
						<a href="#@" class="checkfile">파일첨부</a>
						<input type="file" class="file_input_hidden" name="file-input" id="file-input"  onchange="javascript:document.getElementById('uploadExcelFile').value = this.value" />
					</span> -->
					<span id="fileSizeName">0KB / 10M</span>
				</td>
			</tr>
		</table>
		</div>

		<div id="displaly5" style="display:none;">
			<table class="type-write">
			<colgroup>
				<col style="width:100px" />
				<col style="width:150px" />
				<col style="width:*" />
			</colgroup>
			<tr>
				<th rowspan="3">훈련<br />시간표 등록</th>
				<th>주요교과목 및 내용</th>
				<td>${currProcReadVO.subjectName}</td>
			</tr>
			<tr>
				<th class="border-left">등록할 시간표</th>
				<td class="border-left">
					<a href="javascript:com.downFile('${resultFile.atchFileId}','${resultFile.fileSn}');" class="text-file">${resultFile.orgFileName}</a>
				</td>
			</tr>
		</table>
		</div>

		<div class="btn-area align-right mt-010">
			<a href="#fn_excel_file_down" onclick="javascript:fn_excel_file_down();" class="yellow float-left" id="displaly6">템플릿 다운로드</a>
			<a href="#fn_goList" onclick="javascript:fn_delete('${status}');" class="gray-1" >삭제</a>
			<a href="#fn_goList" onclick="javascript:fn_goList();" class="orange" >목록</a>
			<a href="#fn_excel_upload" onclick="javascript:fn_excel_upload();" class="yellow" id="displaly7" >엑셀업로드</a>
			<a href="#fn_scheduleList" onclick="javascript:fn_scheduleList();" class="orange" >시간표표기</a>
		</div>
	</div>

	</div>

</div><!-- E : content-area -->

<script type="text/javascript">
 $( function() {
	 $(".company-area").draggable();
  });

</script>

</form>
