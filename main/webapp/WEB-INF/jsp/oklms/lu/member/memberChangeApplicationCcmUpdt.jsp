<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<link href="/js/jquery/jquery-ui-1.11.4/jquery-ui.min.css" rel="stylesheet" type="text/css" />

<c:set var="targetUrl" value="/lu/member/"/>

<script type="text/javascript">
	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
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
		$(".requare").css("color", "red"); //필수값 * 빨간색 표시 css
		//$("#memId").focus();
		com.datepickerDateFormat('participateStartDate');
 	    com.datepickerDateFormat('participateEndDate');

 	  	$("input:radio[name='updtApplicationStatus']:radio[value='2']").prop("checked", true);
 	  	$("#display2").show();

 	   	//주민번호
		$("#memRegiNo1").val('${fn:substringBefore(MemberReadVO.memRegiNo, "-")}');
		$("#memRegiNo2").val('${fn:substringAfter(MemberReadVO.memRegiNo, "-")}');

		//이메일
		$("#email1").val('${fn:substringBefore(MemberReadVO.email, "@")}');
		$("#email2").val('${fn:substringAfter(MemberReadVO.email, "@")}');
		$("#email2").attr("disabled", "");
		$("#email2").attr("disabled",false);
		$("#email3").change(function () {
			$("email3 option:selected").each(fn_emailChange());
		});
	}

	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmMember").find("input,select").val("");
	}

	/* 승인 */
	function fn_approvalUpdt(){
		if (confirm("담당자 변경신청건에 대해 승인 하시겠습니까?")) {

			$("#status").val('2');

			var reqUrl = CONTEXT_ROOT + targetUrl + "updateMemberChangeApplicationApproval.do";

			$("#frmMember").attr("method", "post" );
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").attr("target", "_self");
			$("#frmMember").submit();
		}
	}

	/* 반려 */
	function fn_returnUpdt(){
		if (confirm("담당자 변경신청건에 대해 반려 하시겠습니까?")) {

			if($("#retunReason").val() == ""){
				alert("반려시 사유를 입력 주세요.");
				$("#retunReason").focus();
				return false;
			}

			$("#status").val('3');

			var reqUrl = CONTEXT_ROOT + targetUrl + "updateMemberChangeApplicationReturn.do";

			$("#frmMember").attr("method", "post" );
			$("#frmMember").attr("action", reqUrl);
			$("#frmMember").attr("target", "_self");
			$("#frmMember").submit();
		}
	}

	/* 저장 */
	function fn_formSave(){
		if (fn_formValidate() && confirm("저장 하시겠습니까?")) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "updateMemberChangeApplication.do";

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

		/* if($("#memId").val() == ""){
			alert("회원ID를 입력해 주세요.");
			$("#memId").focus();
			return false;
		}

		if($("#memName").val() == ""){
			alert("성명을 입력해 주세요.");
			$("#memName").focus();
			return false;
		} */

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

		var reqUrl = CONTEXT_ROOT + targetUrl + "listMemberChangeApplication.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

	/* 동일분야 현장경력 Change 이벤트 */
	function fn_change_event1(param1){

		$("#sameSiteWorkYear").val(null);

		if("03" == param1){
			$('#sameSiteWorkYear').attr("disabled",false);
		}else{
			$('#sameSiteWorkYear').attr("disabled",true);
		}
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

	function fn_change_event(  ){
		var value = "";
		value = $("input:radio[name='updtApplicationStatus']:checked").val();

		if('3' == value){
			$("#display1").show();
			$("#display2").hide();
		} else {
			$("#display1").hide();
			$("#display2").show();
		}
	}

	function fn_delt(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "deleteMemberChangeApplicationCcm.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}

</script>

<%-- 팝업폼 --%>
<form id="frmPop" method="post" name="frmPop">
  <input type="hidden" name="memIdPop" id="memIdPop"/>
</form>

<form id="frmMember" name="frmMember" method="post" >
<input type="hidden" id="email" name="email" />
<input type="hidden" id="memRegiNo" name="memRegiNo"/>
<input type="hidden" id="memBirth" name="memBirth"/>
<input type="hidden" id="status" name="status" />
<input type="hidden" id="workingPlace" name="workingPlace" />
<input type="hidden" id="memType" name="memType" value="CCM" />
<input type="hidden" id="updtApplicationStatus" name="updtApplicationStatus" value="2" />

<div id="">
	<h2>담당자 변경신청 승인</h2>

	<table class="type-2">
		<colgroup>
			<col style="width:100px" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:170px" />
		</colgroup>
		<thead>
			<tr>
				<th class="bg-highlight">상태</th>
				<th>기업명</th>
				<th>소재지</th>
				<th>훈련과정명</th>
				<th>훈련과정번호</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${MemberReadVO.topStatusName}</td>
				<td>${MemberReadVO.topCompanyName}</td>
				<td>${MemberReadVO.topAddress}</td>
				<td>${MemberReadVO.topHrdTraningName}</td>
				<td>
					${MemberReadVO.topHrdTraningNo}
					<input type="hidden" id="companyId" name="companyId" value="${MemberReadVO.companyId}" />
					<input type="hidden" id="traningProcessId" name="traningProcessId" value="${MemberReadVO.traningProcessId}" />
				</td>
			</tr>
		</tbody>
	</table>

	<table class="type-write mt-030">
		<colgroup>
			<col style="width:100px" />
			<col style="width:45%" />
			<col style="width:100px" />
			<col style="width:*" />
		</colgroup>
		<tbody>
			<tr>
				<th>회원ID</th>
				<td>
					<input type="text" id="memId" name="memId" value="${MemberReadVO.memId}" style="width:30%;" disabled="disabled" />
					<input type="hidden" id="memSeq" name="memSeq" value="${MemberReadVO.memSeq}" />
				</td>
				<th>성명</th>
				<td>
					<input type="text" id="memName" name="memName" value="${MemberReadVO.memName}" style="width:30%;" disabled="disabled"  />
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>성별</th>
				<td>
					<input type="radio" id="sex" name="sex" value="F" class="choice" <c:if test="${ 'F' eq MemberReadVO.sex }"> checked="checked"</c:if> />
      				여자 &nbsp;
      				<input type="radio" id="sex" name="sex" value="M" class="choice" <c:if test="${ 'M' eq MemberReadVO.sex }"> checked="checked"</c:if>/>
      				남자
				</td>
				<th><span class="requare">*</span>생년월일</th>
				<td>
					<select name="year" id="year" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="yearCd" items="${yearCode}" varStatus="status">
							<option value="${yearCd.codeId}" ${yearCd.codeId == MemberReadVO.year ? 'selected="selected"' : '' }>${yearCd.codeName}</option>
						</c:forEach>
					</select>년
	      			&nbsp;
	      			<select name="month" id="month" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="monthCd" items="${monthCode}" varStatus="status">
							<option value="${monthCd.codeId}" ${monthCd.codeId == MemberReadVO.month ? 'selected="selected"' : '' }>${monthCd.codeName}</option>
						</c:forEach>
					</select>월
					&nbsp;
	      			<select name="day" id="day" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="dayCd" items="${dayCode}" varStatus="status">
							<option value="${dayCd.codeId}" ${dayCd.codeId == MemberReadVO.day ? 'selected="selected"' : '' }>${dayCd.codeName}</option>
						</c:forEach>
					</select>일
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>주민번호</th>
				<td colspan="3">
					<input type="text" id="memRegiNo1" name="memRegiNo1" maxlength="6" value="" placeholder="ex)앞자리 6자리 입력" style="width:10%;" onchange="javascript:com.checkNumber(document.getElementById('memRegiNo1'));" />
					-
					<input type="password" id="memRegiNo2" name="memRegiNo2" maxlength="7" value="" placeholder="ex)뒷자리 7자리 입력" style="width:10%;" onchange="javascript:com.checkNumber(document.getElementById('memRegiNo2'));" />
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>핸드폰번호</th>
				<td>
					<select name="hpNo1" id="hpNo1" style="width:77px;">
						<option selected value='99'>::선택::</option>
						<c:forEach var="mobileTelNoCd" items="${mobileTelNoCode}" varStatus="status">
							<option value="${mobileTelNoCd.codeId}" ${mobileTelNoCd.codeId == MemberReadVO.hpNo1 ? 'selected="selected"' : '' }>${mobileTelNoCd.codeName}</option>
						</c:forEach>
					</select>
					-
					<input type="text" id="hpNo2" name="hpNo2"  maxlength="4" value="${MemberReadVO.hpNo2}" style="width:25%" onchange="javascript:com.checkNumber(document.getElementById('hpNo2'));" />
					-
					<input type="text" id="hpNo3" name="hpNo3"  maxlength="4" value="${MemberReadVO.hpNo3}" style="width:25%" onchange="javascript:com.checkNumber(document.getElementById('hpNo3'));" />
				</td>
				<th><span class="requare">*</span>이메일</th>
				<td>
					<input type="text" id="email1" name="email1"  value=""  maxlength="" style="width:40%" />
					@&nbsp;<input type="text" id="email2" name="email2" value=""  maxlength="" style="width:25%" />
					<select name="email3" id="email3" style="width:130px;">
						<option selected value='99'>::직접입력::</option>
						<c:forEach var="emailDomainCd" items="${emailDomainCode}" varStatus="status">
							<option value="${emailDomainCd.codeName}" ${emailDomainCd.codeName == MemberReadVO.email3 ? 'selected="selected"' : '' }>${emailDomainCd.codeName}</option>
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
							<option value="${jobPositionCd.codeId}" ${jobPositionCd.codeId == MemberReadVO.title ? 'selected="selected"' : '' }>${jobPositionCd.codeName}</option>
						</c:forEach>
					</select>
				</td>
				<th><span class="requare">*</span>학력</th>
				<td>
					<select id="scholarship" name="scholarship" onchange="" >
						<option selected value='99'>::선택::</option>
						<c:forEach var="qfeAbilityCd" items="${qfeAbilityCode}" varStatus="status">
							<option value="${qfeAbilityCd.codeId}" ${qfeAbilityCd.codeId == MemberReadVO.scholarship ? 'selected="selected"' : '' }>${qfeAbilityCd.codeName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>관련연구경력</th>
				<td>기관명&nbsp;&nbsp;&nbsp;<input type="text" id="relatedResearchOrganizationName" name="relatedResearchOrganizationName"  maxlength="" value="${MemberReadVO.relatedResearchOrganizationName}" style="width:80%;" /></td>
				<th>기간</th>
				<td><input type="number" id="relatedResearchYear" name="relatedResearchYear" value="${MemberReadVO.relatedResearchYear}" min="0" max="100" style="width:30px;" />&nbsp;&nbsp;년</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>동일분야<br />현장경력</th>
				<td>
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="01" class="choice" onchange="javascript:fn_change_event1('01');" <c:if test="${ '01' eq MemberReadVO.sameSiteWorkCd }"> checked="checked"</c:if> /> 3년 미만&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="02" class="choice" onchange="javascript:fn_change_event1('02');" <c:if test="${ '02' eq MemberReadVO.sameSiteWorkCd }"> checked="checked"</c:if> /> 3년 이상&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="sameSiteWorkCd" name="sameSiteWorkCd" value="03" class="choice" onchange="javascript:fn_change_event1('03');" <c:if test="${ '03' eq MemberReadVO.sameSiteWorkCd }"> checked="checked"</c:if> /> 5년 이상 (
					<input type="number" id="sameSiteWorkYear" name="sameSiteWorkYear" min="0" max="100" value="${MemberReadVO.sameSiteWorkYear}" style="width:30px;" disabled="disabled" /> 년)
				</td>
				<th><span class="requare">*</span>근무형태</th>
				<td>
					<input type="radio" id="tmpWorkingPlace" name="tmpWorkingPlace" value="01" class="choice" <c:if test="${ '01' eq MemberReadVO.workingPlace }"> checked="checked"</c:if> /> 정규직&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="tmpWorkingPlace" name="tmpWorkingPlace" value="02" class="choice" <c:if test="${ '02' eq MemberReadVO.workingPlace }"> checked="checked"</c:if> /> 비정규직
				</td>
			</tr>
			<tr>
				<th><span class="requare">*</span>인력구분</th>
				<td colspan="3">
					<select id="memType1" name="memType1" disabled="disabled" >
						<option value="CCM" selected="selected">HRD전담자</option>
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
				<input type="radio" id="updtApplicationStatus" name="updtApplicationStatus" value="2" onchange="javascript:fn_change_event();" /> 기존정보변경&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="updtApplicationStatus" name="updtApplicationStatus" value="1" disabled="disabled" /> 신규등록&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" id="updtApplicationStatus" name="updtApplicationStatus" value="3" onchange="javascript:fn_change_event();" /> 삭제
			</td>
		</tr>
		<tr>
			<th class="border-left"><span class="requare">*</span>참여일자 입력</th>
			<td>
				<input type="text" id="participateStartDate" name="participateStartDate" value="${MemberReadVO.participateStartDate}" placeholder="ex)2017.03.01" style="width:65px" readonly="readonly" /> ~
				<input type="text" id="participateEndDate" name="participateEndDate"  value="${MemberReadVO.participateEndDate}" placeholder="ex)2017.03.31" style="width:65px" readonly="readonly" />
			</td>
		</tr>
	</table>

	<div class="btn-area align-right mt-010">
		<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">목록</a>

		<c:if test="${ 'CCN' eq MemberStdVO.sessionMemType}">
		<input type="text" id="retunReason" name="retunReason" placeholder="ex)반려시 사유 필수 입력" />
		<a href="#fn_returnUpdt" onclick="javascript:fn_returnUpdt(); return false" class="yellow">반려</a>
		<a href="#fn_approvalUpdt" onclick="javascript:fn_approvalUpdt(); return false" class="orange">승인</a>
		</c:if>

		<a href="#fn_delt" onclick="javascript:fn_delt(); return false" class="gray-1" style="display:none;" id="display1">삭제</a>
		<a href="#fn_formSave" onclick="javascript:fn_formSave(); return false" class="orange" style="display:none;" id="display2">수정</a>
	</div>

</div><!-- E : content-area -->

</form>




