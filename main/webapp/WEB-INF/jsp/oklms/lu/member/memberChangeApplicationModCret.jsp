<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<c:set var="targetUrl" value="/lu/member/"/>

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

		$(".requare").css("color", "red"); //필수값 * 빨간색 표시 css
		//$("#memId").focus();
		$("#display5").show();
		com.datepickerDateFormat('participateStartDate');
 	    com.datepickerDateFormat('participateEndDate');
 	   	$('#sameSiteWorkYear').attr("disabled",true);

 	   //기업체에 메핑된 훈련과정 검색
 	   	var returnMsg = "";
 	  	var cnt = "";
		returnMsg = '${returnMsg}';
		cnt = '${cnt}';
		//var cnt = '${cnt}';

		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			fn_showCompanypop();
		}

		if(cnt == ''){
			$("#displaly2").show();
			$("#displaly3").hide();
		} else {
			$("#displaly2").hide();
			$("#displaly3").show();
		}

		/* if(cnt == '0'){
			$(".list-show").show();
			$(".list-hide").hide();
		} else {
			$(".list-hide").show();
			$(".list-show").hide();
		} */

		$("#email1").val('${fn:substringBefore(MemberStdVO.email, "@")}');
		$("#email2").val('${fn:substringAfter(MemberStdVO.email, "@")}');
		$("#email2").attr("disabled", "");
		$("#email2").attr("disabled",false);
		$("#email3").change(function () {
			$("email3 option:selected").each(fn_emailChange());
		});
	}

	/*====================================================================
	사용자 정의 함수
	====================================================================*/
	function press(event) {
		if (event.keyCode==13) {
			fn_search();
		}
	}

	/* 기업체 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "_self");
		$("#frmMember").submit();
	}

	//초기화 기업체 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "_self");
		$("#frmMember").submit();
	}

	//기업체검색 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "_self");
		$("#frmMember").submit();
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
			alert("기업체를 선택하여주십시오. ")
			return false;
		} else {
			$(".company-area,.popup-bg").removeClass("open");
			fn_setCompanypopInfo(obj);
		}
	}

	// 기업체 정보 셋팅
	//${result.companyId}||${result.companyName}||${result.address}||${result.traningProcessId}||${result.hrdTraningName}||${result.hrdTraningNo}
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

		var reqUrl = CONTEXT_ROOT + targetUrl + "goInsertMemberChangeApplication.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "_self");
		$("#frmMember").submit();
	}

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmMember").find("input,select").val("");
	}

	/* .저장 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "updateMemberChangeApplicationNew.do";

			$("#frmMember").attr("method", "post" );
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").attr("target", "_self");
			$("#frmMember").submit();
		}
	}

	//저장버튼 클릭시 입력form Validate 체크
	function fn_formValidate(){
		var email = "";
		var memRegiNo = "";
		var memBirth = "";
		var startDate = "";
		var endDate = "";

		var tmpWorkingPlace = $("input:radio[name='tmpWorkingPlace']:checked").val();
		$("#workingPlace").val(tmpWorkingPlace);

		if($("#email1").val() != "" && $("#email2").val()  != ""){
			email = $("#email1").val() + "@" + $("#email2").val();
			$("#email").val(email);
		}

		if($("#memRegiNo1").val() != "" && $("#memRegiNo2").val()  != ""){
			memRegiNo = $("#memRegiNo1").val() + "-" + $("#memRegiNo2").val();
			$("#memRegiNo").val(memRegiNo);
		}

		if($("#year").val() != '' && $("#month").val() != '' && $("#day").val() != ''){
			memBirth = $("#year").val() + $("#month").val() + $("#day").val();
			$("#memBirth").val(memBirth);
		}

		if($("#companyId").val() == ""){
			alert("기업체검색 버튼을 클릭하여 훈련정보를 셋팅해주세요.");
			return false;
		}

		if($("#memId").val() == ""){
			alert("회원ID를 검색해서 셋팅 해주세요.");
			$("#memId").focus();
			return false;
		}

		if($("#memName").val() == ""){
			alert("성명 정보가 없습니다. 회원ID를 검색해서 셋팅 주세요.");
			$("#memName").focus();
			return false;
		}

		if($("#year").val() == "99"){
			alert("생년월일 년도를 선택해 주세요.");
			$("#year").focus();
			return false;
		}

		if($("#month").val() == "99"){
			alert("생년월일 월를 선택해 주세요.");
			$("#month").focus();
			return false;
		}

		if($("#day").val() == "99"){
			alert("생년월일 일를 선택해 주세요.");
			$("#day").focus();
			return false;
		}

		if($("#memRegiNo1").val() == ""){
			alert("주민번호 앞자리 6자리를 입력 해주세요.");
			$("#memRegiNo1").focus();
			return false;
		}

		if($("#memRegiNo2").val() == ""){
			alert("주민번호 뒷자리 7자리를 입력 해주세요.");
			$("#memRegiNo2").focus();
			return false;
		}

		if($("#hpNo1").val() == "99"){
			alert("핸드폰번호 첫번째자리를 선택 해주세요.");
			$("#hpNo1").focus();
			return false;
		}

		if($("#hpNo2").val() == ""){
			alert("핸드폰번호 중간자리를 입력 해주세요.");
			$("#hpNo2").focus();
			return false;
		}

		if($("#hpNo3").val() == ""){
			alert("핸드폰번호 뒷자리를 입력 해주세요.");
			$("#hpNo3").focus();
			return false;
		}

		if($("#email1").val() == ""){
			alert("이메일 ID를 입력 해주세요.");
			$("#email1").focus();
			return false;
		}

		if($("#email2").val() == ""){
			alert("이메일 주소를 직접입력 혹은 선택해주세요.");
			$("#email2").focus();
			return false;
		}

		if($("#title").val() == "99"){
			alert("직위를 선택 해주세요.");
			$("#title").focus();
			return false;
		}

		if($("#scholarship").val() == "99"){
			alert("학력을 선택 해주세요.");
			$("#scholarship").focus();
			return false;
		}

		//동일분야 현장경력이 5년이상일떼 년도를 입력했는지 여부
		var ameSiteWorkCdChecked = $("input:radio[name='sameSiteWorkCd']:checked").val();
		if("03" == ameSiteWorkCdChecked){
			if($("#sameSiteWorkYear").val() == ""){
				alert("동일분야 현장경력에 년도의 숫자를 입력해주세요.");
				$("#sameSiteWorkYear").focus();
				return false;
			}
		}

		var memType = $("#memType").val();
		if(memType == "99"){
			alert("인력구분을 선택 주세요.");
			$("#memType").focus();
			return false;
		}

		if(memType == "COT"){
			if($("#yyyy").val() == ""){
				alert("개설강좌명을 검색하여 메핑해주세요");
				$("#subjectName").focus();
				return false;
			}
		}

		if($("#participateStartDate").val() == ""){
			alert("참여 시작일을 선택 주세요.");
			$("#participateStartDate").focus();
			return false;
		}

		if($("#participateEndDate").val() == ""){
			alert("참여 종료일을 선택 주세요.");
			$("#participateEndDate").focus();
			return false;
		}

		startDate = $("#participateStartDate").val();
		endDate = $("#participateEndDate").val();

		startDate = startDate.replace(".","");
		startDate = startDate.replace(".","");
		endDate = endDate.replace(".","");
		endDate = endDate.replace(".","");

		if(Number(startDate) > Number(endDate)){
			alert("참여 종료일이 시작일 보다 전날짜입니다.");
			$("#participateEndDate").focus();
			return false;
		}

		/* if($("#status").val() != "true"){
			alert("회원ID에 중복확인 버튼을 클릭하여 중복여부를 체크하세요.");
			$("#memId").focus();
			return false;
		} */

		return true;
	}

	/* 목록 페이지로 이동 */
	function fn_list(){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = CONTEXT_ROOT + targetUrl + "listMemberChangeApplication.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 동일분야 현장경력 Change 이벤트 */
	function fn_change_event1(param1){

		if("03" == param1){
			$("#sameSiteWorkYear").val('');
			$('#sameSiteWorkYear').attr("disabled",false);
		}else{
			$('#sameSiteWorkYear').attr("disabled",true);
		}
	}

	/* 인력구분 Change 이벤트 */
	function fn_change_event2( param1 ){
		var memType = $("#memType").val();
		var authGroupId = "";

		if('99' != memType){
			if("COT" == memType){
				$("#subjectName").show();
				$("#display4").show();
				$("#display5").hide();
				authGroupId = "<%=Config.DEFAULT_AUTH_SUPERVISOR_TEACHAR%>";
			}else{
				$("#subjectName").hide();
				$("#display4").hide();
				$("#display5").show();

				$("#subjectName").val(null);
				$("#yyyy").val(null);
				$("#term").val(null);
				$("#subjectCode").val(null);
				$("#subClass").val(null);
				authGroupId = "<%=Config.DEFAULT_AUTH_CRI_COMPANY%>";
			}
		}

		if('99' == memType){
			$("#subjectName").hide();
			$("#display4").hide();
			$("#display5").show();

			$("#subjectName").val(null);
			$("#yyyy").val(null);
			$("#term").val(null);
			$("#subjectCode").val(null);
			$("#subClass").val(null);
			authGroupId = null;
		}

		$("#authGroupId").val(authGroupId);
	}

	//e-mail 선택
	function fn_emailChange(){
		//입력값 체크로직
		var optionVal = $("#email3").val();
		if(optionVal == "99"){
			$("#email2").attr("disabled",false);
			$("#email2").focus();
			$("#email2").val("");
		}else{
			$("#email2").val(optionVal.substring(1, optionVal.length));
			$("#email2").attr("disabled",true);
		}
	}

	//회원유형이 기업현장교사 일떼 개설교과 메핑 검색 팝업
	function fn_subjectSearch(){

		if($("#companyId").val() == ""){
			alert("기업체검색 버튼을 클릭하여 훈련정보를 셋팅해주세요.");
			return false;
		}

		popOpenWindow("", "popSch", 850, 560);

		var reqUrl = CONTEXT_ROOT + "/lu/popup/goPopupSearchCompanySubjectName.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "popSch");
		$("#frmMember").submit();
	}

	function fn_setSubjectNmInfo(obj){
		if( obj ){
			var arrInfo = obj.split("||");
			var yyyy = arrInfo[0];
			var term = arrInfo[1];
			var subjectCode = arrInfo[2];
			var subClass = arrInfo[3];
			var subjectName = arrInfo[4];

			$("#yyyy").val(yyyy);
			$("#term").val(term);
			$("#subjectCode").val(subjectCode);
			$("#subClass").val(subClass);
			$("#subjectName").val(subjectName);
		}
	}

	function fn_memberSearch(){

		if($("#companyId").val() == ""){
			alert("기업체검색 버튼을 클릭하여 훈련정보를 셋팅해주세요.");
			return false;
		}

		if('99' == $("#memType").val()){
			alert("인력구분을 선택해주세요.");
			$("#memType").focus();
			return false;
		}

		popOpenWindow("", "popSch", 850, 560);

		var reqUrl = CONTEXT_ROOT + "/lu/popup/goPopupSearchCompanyMappingMemberInfo.do";

		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").attr("target", "popSch");
		$("#frmMember").submit();
	}

	function fn_setMemberInfo(obj){
		if( obj ){
			var arrInfo = obj.split("||");
			var memSeq = arrInfo[0];
			var memId = arrInfo[1];
			var memName = arrInfo[2];

			$("#memSeq").val(memSeq);
			$("#memId").val(memId);
			$("#memName").val(memName);
		}
	}



</script>

<%-- 팝업폼 --%>
<form id="frmPop" method="post" name="frmPop">
  <input type="hidden" name="memIdPop" id="memIdPop"/>
</form>

<form id="frmMember" name="frmMember" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="parmMemType" name="parmMemType" value="${MemberStdVO.memType}" />
<input type="hidden" id="cretGubun" name="cretGubun" value="${MemberStdVO.cretGubun}" />
<input type="hidden" id="companyId" name="companyId" value="${MemberStdVO.companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${MemberStdVO.traningProcessId}" />
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>
<input type="hidden" id="status" name="status"/>
<input type="hidden" id="workingPlace" name="workingPlace" />
<input type="hidden" id="email" name="email" />
<input type="hidden" id="updtApplicationStatus" name="updtApplicationStatus" value="1" />
<input type="hidden" id="memRegiNo" name="memRegiNo"/>
<input type="hidden" id="memBirth" name="memBirth"/>
<input type="hidden" name="authGroupId" id="authGroupId"/>
<input type="hidden" id="uiGubun" name="uiGubun" value="memberNewPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="/lu/member/memberChangeApplicationNewCret" />

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

			<div class="page-num mt-015">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_searchPaging" />
			</div>

		<div class="btn-area align-center mt-010">
			<a href="#fn_closeCompanypop" class="yellow close" onclick="javascript:fn_closeCompanypop(); return false" onkeypress="this.onclick();">닫기</a>
			<a href="#fn_hideCompanypop" class="orange close" onclick="javascript:fn_hideCompanypop(); return false" onkeypress="this.onclick();">확인</a>
		</div><!-- E : btn-area -->
	</li>

	<li class="popup-bg"></li>
</ul><!-- E : student-popup -->

<div id="">
	<h2>담당자 신규등록</h2>

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
		<c:forEach var="result" items="${resultCompanyTraningProcessList}" varStatus="status">
		<tr>
			<td>${result.companyName}</td>
			<td>${result.address}</td>
			<td>${result.choiceDay}</td>
			<td>${result.employInsManageNo}</td>
			<td>${result.hrdTraningName}</td>
			<td>${result.hrdTraningNo}</td>
		</tr>
		</c:forEach>
	</table>
	</div>

	<div class="btn-area align-right mt-010">
		<a href="#fn_showCompanypop" class="yellow" onclick="javascript:fn_showCompanypop(); return false" onkeypress="this.onclick();">기업체 검색</a>
	</div><!-- E : btn-area -->

	<table class="type-write mt-030">
		<colgroup>
			<col style="width:100px" />
			<col style="width:45%" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th><span class="requare">*</span>회원ID</th>
				<td>
					<input type="text" id="memId" name="memId" maxlength="" value="" placeholder="ex)변경할 담당자 검색" style="width:30%;" />
					<input type="hidden" id="memSeq" name="memSeq" />
					<a href="#fn_memberSearch" onclick="javascript:fn_memberSearch( ); return false" class="btn-full-gray">검색</a>
				</td>
				<th><span class="requare">*</span>성명</th>
				<td>
					<input type="text" id="memName" name="memName" maxlength="" value="" placeholder="ex)성명 입력" style="width:30%;" />
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>성별</th>
				<td>
					<input type="radio" id="sex" name="sex" value="F" class="choice" checked/>
      				여자 &nbsp;
      				<input type="radio" id="sex" name="sex" value="M" class="choice"/>
      				남자
				</td>
				<th><span class="requare">*</span>생년월일</th>
				<td>
					<select name="year" id="year" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="yearCd" items="${yearCode}" varStatus="status">
							<option value="${yearCd.codeId}" ${yearCd.codeId == MemberStdVO.year ? 'selected="selected"' : '' }>${yearCd.codeName}</option>
						</c:forEach>
					</select>년
	      			&nbsp;
	      			<select name="month" id="month" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="monthCd" items="${monthCode}" varStatus="status">
							<option value="${monthCd.codeId}" ${monthCd.codeId == MemberStdVO.month ? 'selected="selected"' : '' }>${monthCd.codeName}</option>
						</c:forEach>
					</select>월
					&nbsp;
	      			<select name="day" id="day" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="dayCd" items="${dayCode}" varStatus="status">
							<option value="${dayCd.codeId}" ${dayCd.codeId == MemberStdVO.day ? 'selected="selected"' : '' }>${dayCd.codeName}</option>
						</c:forEach>
					</select>일
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>주민번호</th>
				<td colspan="3">
					<input type="text" id="memRegiNo1" name="memRegiNo1" maxlength="6" value="" placeholder="ex)앞자리 6자리 입력" style="width:10%;" onkeyup="javascript:com.checkNumber(document.getElementById('memRegiNo1'));" />
					-
					<input type="password" id="memRegiNo2" name="memRegiNo2" maxlength="7" value="" placeholder="ex)뒷자리 7자리 입력" style="width:10%;" onkeyup="javascript:com.checkNumber(document.getElementById('memRegiNo2'));" />
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>핸드폰번호</th>
				<td>
					<select name="hpNo1" id="hpNo1" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="mobileTelNoCd" items="${mobileTelNoCode}" varStatus="status">
							<option value="${mobileTelNoCd.codeId}" ${mobileTelNoCd.codeId == MemberStdVO.hpNo1 ? 'selected="selected"' : '' }>${mobileTelNoCd.codeName}</option>
						</c:forEach>
					</select>
					-
					<input type="text" id="hpNo2" name="hpNo2"  maxlength="4" value="" placeholder="ex)4자리 입력 (1111)" style="width:25%" onkeyup="javascript:com.checkNumber(document.getElementById('hpNo2'));" />
					-
					<input type="text" id="hpNo3" name="hpNo3"  maxlength="4" value="" placeholder="ex)4자리 입력 (2222)" style="width:25%" onkeyup="javascript:com.checkNumber(document.getElementById('hpNo3'));" />
				</td>
				<th><span class="requare">*</span>이메일</th>
				<td>
					<input type="text" id="email1" name="email1"  value=""  placeholder="ex)이메일ID 입력 (koreatech)" maxlength="" style="width:30%" />
					@&nbsp;<input type="text" id="email2" name="email2" value=""  placeholder="ex)이메일주소 입력 (koreatech.ac.kr)" maxlength="" style="width:40%" />
					<select name="email3" id="email3" style="width:130px;">
						<option selected value='99'>::직접입력::</option>
						<c:forEach var="emailDomainCd" items="${emailDomainCode}" varStatus="status">
							<option value="${emailDomainCd.codeName}" ${emailDomainCd.codeName == MemberStdVO.email3 ? 'selected="selected"' : '' }>${emailDomainCd.codeName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>직위</th>
				<td>
					<select id="title" name="title" onchange="" >
						<option selected value='99'>::선택::</option>
						<c:forEach var="jobPositionCd" items="${jobPositionCode}" varStatus="status">
							<option value="${jobPositionCd.codeId}" ${jobPositionCd.codeId == MemberStdVO.title ? 'selected="selected"' : '' }>${jobPositionCd.codeName}</option>
						</c:forEach>
					</select>
				</td>
				<th><span class="requare">*</span>학력</th>
				<td>
					<select id="scholarship" name="scholarship" onchange="" >
						<option selected value='99'>::선택::</option>
						<c:forEach var="qfeAbilityCd" items="${qfeAbilityCode}" varStatus="status">
							<option value="${qfeAbilityCd.codeId}" ${qfeAbilityCd.codeId == MemberStdVO.scholarship ? 'selected="selected"' : '' }>${qfeAbilityCd.codeName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>관련연구경력</th>
				<td>기관명&nbsp;&nbsp;&nbsp;<input type="text" id="relatedResearchOrganizationName" name="relatedResearchOrganizationName"  placeholder="ex)기관명 입력" maxlength="" value="" style="width:80%;" /></td>
				<th>기간</th>
				<td><input type="number" id="relatedResearchYear" name="relatedResearchYear" min="0" max="100" style="width:30px;" />&nbsp;&nbsp;년</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>동일분야<br />현장경력</th>
				<td>
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="01" class="choice" onchange="javascript:fn_change_event1('01');" checked /> 3년 미만&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="02" class="choice" onchange="javascript:fn_change_event1('02');" /> 3년 이상&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="03" class="choice" onchange="javascript:fn_change_event1('03');" /> 5년 이상 (
					<input type="number" id="sameSiteWorkYear" name="sameSiteWorkYear" min="0" max="100" value="" style="width:30px;" /> 년)
				</td>
				<th><span class="requare">*</span>근무형태</th>
				<td>
					<input type="radio" id="tmpWorkingPlace" name="tmpWorkingPlace" value="01" class="choice" checked /> 정규직&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="tmpWorkingPlace" name="tmpWorkingPlace" value="02" class="choice" /> 비정규직
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>개설교과</th>
				<td>
					<input type="text" id="subjectName" name="subjectName" placeholder="ex)개설교과명 검색후 정보셋팅" style="width:120px; display:none;" readonly="readonly">
					<input type="hidden" id="yyyy" name="yyyy" />
					<input type="hidden" id="term" name="term" />
					<input type="hidden" id="subjectCode" name="subjectCode" />
					<input type="hidden" id="subClass" name="subClass" />
					<a href="#fn_subjectSearch" onclick="javascript:fn_subjectSearch(); return false" class="btn-full-gray" id="display4" style="display:none;">검색</a>
					<span id="display5" style="display:none;">-</span>
				</td>
				<th><span class="requare">*</span>인력구분</th>
				<td>
					<select id="memType" name="memType" onchange="javascript:fn_change_event2();" >
						<option selected value='99'>::선택::</option>
						<option value="COT" ${'COT' == MemberStdVO.memType ? 'selected="selected"' : '' }>기업현장교사</option>
						<option value="CCM" ${'CCM' == MemberStdVO.memType ? 'selected="selected"' : '' }>HRD전담자</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>

	<table class="type-write mt-005" style="border-top:1px solid #CCC;">
		<colgroup>
			<col style="width:100px" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tr>
			<th rowspan="2">전담인력<br />이력관리</th>
			<th><span class="requare">*</span>구분</th>
			<td>
				<input type="radio" id="updtApplicationStatus1" name="updtApplicationStatus1" value="2" class="choice" disabled="disabled" /> 기존정보변경&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="updtApplicationStatus2" name="updtApplicationStatus2" value="1" class="choice" disabled="disabled" checked /> 신규등록&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="updtApplicationStatus3" name="updtApplicationStatus3" value="3" class="choice" disabled="disabled" /> 삭제
			</td>
		</tr>
		<tr>
			<th class="border-left"><span class="requare">*</span>참여일자 입력</th>
			<td>
				<input type="text" id="participateStartDate" name="participateStartDate" placeholder="ex)2017.03.01" style="width:65px" readonly="readonly" /> ~
				<input type="text" id="participateEndDate" name="participateEndDate"  placeholder="ex)2017.03.31" style="width:65px" readonly="readonly" />
			</td>
		</tr>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">목록</a>
		<a href="#fn_formSave" onclick="javascript:fn_formSave(); return false" class="orange">저장</a>
	</div>

</div><!-- E : content-area -->

<script type="text/javascript">
 $( function() {
	 $(".company-area").draggable();
  });

</script>

</form>




