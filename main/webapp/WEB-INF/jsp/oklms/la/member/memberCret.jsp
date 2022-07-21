<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2016. 12. 01 오후 1:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>


<c:set var="targetUrl" value="/la/member/member/"/>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
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
		$("#email1").val('${fn:substringBefore(member.email, "@")}');
		$("#email2").val('${fn:substringAfter(member.email, "@")}');
		$("#email2").attr("disabled", "");   
		$("#memType").val("STD");
		$(".requare").css("color", "red");
		$("#email2").attr("disabled",false);
		$("#email3").change(function () {
			$("email3 option:selected").each(fn_emailChange());
		});
		$("#authGroupId").change(function () {
			chkVal();
		});
		chkVal();
	}

	/*====================================================================
	사용자 정의 함수 
	====================================================================*/

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmMember").find("input,select").val("");
	}
	
	/* 수정 */
	function fn_formSave(){
		var email = "";
		var birth = "";
		
		if($("#email1").val() != "" && $("#email2").val()  != ""){
			email = $("#email1").val() + "@" + $("#email2").val();
			$("#email").val(email);
		}
		if($("#year").val() != '' && $("#month").val() != '' && $("#day").val() != ''){
			birth = $("#year").val() + $("#month").val() + $("#day").val();
			$("#memBirth").val(birth);
		}

		if($("#status").val() != "true"){
			alert("중복체크를 하지 않았습니다.");
			$("#memId").focus();
			return false;
		}
		
		$("#compNm").val($("#companyId").val());
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "insertMember.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();
	}
	
	/* 목록 페이지로 이동 */
	function fn_list(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listMember.do";

		$("#frmMember").attr("method", "post" );
		$("#frmMember").attr("action", reqUrl);
		$("#frmMember").submit();	
	}
	
	/* 다음라이브러리를 통한 도로명 검색 
	팝업 호출 */
	function fn_goSearchDoroCodePop(){
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var fullAddr = ''; // 최종 주소 변수
	            var extraAddr = ''; // 조합형 주소 변수

	            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                fullAddr = data.roadAddress;

	            } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                fullAddr = data.jibunAddress;
	            }

	            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
	            if(data.userSelectedType === 'R'){
	                //법정동명이 있을 경우 추가한다.
	                if(data.bname !== ''){
	                    extraAddr += data.bname;
	                }
	                // 건물명이 있을 경우 추가한다.
	                if(data.buildingName !== ''){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
	                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	            }

	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	           // document.getElementById("zipCode").value = data.postcode; //6자리 우편번호 사용
	            //document.getElementById("zipCode").value = data.zonecode; //5자리 기초구역번호 사용
	            //document.getElementById("address").value = fullAddr;
	            
	            $("#zipCode").val(data.zonecode);
	            $("#address").val(fullAddr);

	            // 커서를 상세주소 필드로 이동한다.
	            //document.getElementById("addressDtl").focus();
	            $("#addressDtl").focus();
	        }
	    }).open();
	}
	
	//셀렉트박스 값에 따라서 해당 예문필드를 구성 // INS 등록자 설정 하기 .
	function chkVal(){
		/*
	    -- 회원유형 코드 --
	    관리자       : ADM, 
		학습근로자   : STD,
		담당교수     : PRT,
		기업전담자   : CCM,
		센터전담자   : CCN,
		학과전담자   : CDP,
		지도교수     : ATT,
		기업현장교사 : COT
		*/
		var authGroupId = $("#authGroupId").val(); 
		if( authGroupId == "<%=Config.DEFAULT_AUTH_ADM%>" ) {
			$("#memType").val("<%=Config.ADM%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_STD%>" ) {
			$("#memType").val("<%=Config.STD%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_PRO_TUTOR%>" ) {
			$("#memType").val("<%=Config.PRT%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_CRI_COMPANY%>" ) {
			$("#memType").val("<%=Config.CCM%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_CRI_CENTER%>" ) {
			$("#memType").val("<%=Config.CCN%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_CRI_DEPARTMENT%>" ) {
			$("#memType").val("<%=Config.CDP%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_ADV_TUTOR%>" ) {
			$("#memType").val("<%=Config.ATT%>");
		}
		if( authGroupId == "<%=Config.DEFAULT_AUTH_SUPERVISOR_TEACHAR%>" ) {
			$("#memType").val("<%=Config.COT%>");
		}
	}
	 
	//email 수신거부
	function fn_receiveNoEmail(){
		alert("이메일 수신거부시\n\n학습관리통합시스템 교육사이트에서 보내는 각종정보를 받아볼수없습니다..");
	}
	//SMS 수신거부
	function fn_receiveNoSMS(){
		alert("SMS 수신거부시\n\n학습관리통합시스템 교육사이트에서 보내는 각종정보를 받아볼수없습니다..");
	}
	 
	//닉네임 입력시 특수문자 사용불가
	function fn_codeCheck(e,param1){
		if(/\W/.test(String.fromCharCode(e.keyCode)) && e.keyCode != 46 ){
			alert('특수기호 및 공백은 사용할 수 없습니다.');
			if(param1 == '01'){
				$("#memPassword").val('');
			}
			else if(param1 == '02'){
				$("#memPassword1").val('');
			}
			e.returnValue=false;
			return false;
		}
	}
	
	//e-mail 선택
	function fn_emailChange(param1){
	//입력값 체크로직
		var optionVal = $("#email3").val();
		if(optionVal == ""){
			$("#email2").attr("disabled",false);
			$("#email2").focus();
			$("#email2").val("");
		}else{
			$("#email2").val(optionVal.substring(1, optionVal.length));
			$("#email2").attr("disabled","disabled");
		}
	}
	 
	//아이디 중복체크
	function fn_memberIdDuckCheck(){
		if("" == $("#memId").val()){
			alert("아이디를 입력 하세요.");
			$("#memId").focus();
			return;
		}
		if($("#memId").val().length <= 3){
			alert("4자 이상 입력 하세요.");
			$("#memId").focus();
			return;
		}
		
		$("#memIdPop").val($("#memId").val());
		
		popOpenWindow("", "popSchMemId", 650, 560);
		
		var reqUrl = CONTEXT_ROOT + "/la/popup/popup/goPopupMemberInfoIdCheck.do";
		
		$("#frmPop").attr("action", reqUrl);
		$("#frmPop").attr("target", "popSchMemId");
		$("#frmPop").submit();
	}
	
	/* 기업정보 팝업창열기 */
	function fn_search_companyPop( ){
		popOpenWindow("", "popSearch", 650, 560);
		
		var reqUrl = "/la/popup/popup/goPopupCompanyList.do";
		
		$("#frmPop").attr("action", reqUrl);
		$("#frmPop").attr("target", "popSearch");
		$("#frmPop").submit();
	}

	/* 기업정보 팝업창에서 선택버튼을 클릭시 전달정보 셋팅 */
	function setSelecItem1(objArr){
		if( objArr ){
			var objArr = objArr.split("||");
			var compNm = objArr[0];
			var companyId = objArr[1];
		
			$("#compNm").val(compNm);
			$("#companyId").val(companyId);
		}
	}

	/* 기업정보 팝업창에서 제목을 클릭시 전달정보 셋팅 */
	function setSelecItem2(param1, param2){
	 	var compNm = param1;
	 	var companyId = param2;

		$("#compNm").val(compNm);
		$("#companyId").val(companyId);
	}
	
</script>

<p style="display:block;text-align:left;"><font color="red">*</font> 는 필수입력사항입니다.</p>
<%-- 팝업폼 --%>
<form id="frmPop" method="post" name="frmPop">
  <input type="hidden" name="memIdPop" id="memIdPop"/>
</form>
<form:form commandName="frmMember">
<input type="hidden" name="email" id="email"/>
<input type="hidden" name="status" id="status"/>
<input type="hidden" name="memType" id="memType" />
<input type="hidden" name="memBirth" id="memBirth" />

<!-- 디폴트 셋팅항목 필드 시작 -->
<inputtype="hidden" name="namecheckYn"id="namecheckYn" value="Y"/>
<!-- 디폴트 셋팅항목 필드 끝 -->

<!-- 검색조건유지 필드 시작 -->
<input type="hidden" name="searchAuthGroupId" id="searchAuthGroupId" value="${memberVO.searchAuthGroupId}"/>
<input type="hidden" name="searchName" id="searchName" value="${memberVO.searchName}"/>
<input type="hidden" name="searchScsnYn" id="searchScsnYn" value="${memberVO.searchScsnYn}"/>
<input type="hidden" name="searchWord" id="searchWord" value="${memberVO.searchWord}"/>
<!-- 검색조건유지 필드 끝 -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th width="132px"><span class="requare">*</span>성명</th>
			<td>
				<input type="text" id="memName" name="memName"  value="${memberVO.memName}" style="width:99%" />	
				<form:errors path="memName" style="width:99%" />		
			</td>      
			<th width="132px"><span class="requare">*</span>성별</th>
	  		<td>
	  			<input type="radio" name="sex" value="F" class="choice" checked/>
      			여자 &nbsp;
      			<input type="radio" name="sex" value="M" class="choice"/>
      			남자 
	  		</td>  
		</tr>
		<tr>
			<th><span class="requare">*</span>아이디</th>
			<td>
				<input type="text" id="memId" name="memId"  value="${memberVO.memId}" style="width:60%" />
				<a href="#fn_memberIdDuckCheck" class="btn" onclick="javascript:fn_memberIdDuckCheck( ); return false" onkeypress="this.onclick;">중복확인</a>
				<form:errors path="memId" style="width:99%" />				
			</td>      
			<th><span class="requare">*</span>생년월일</th>
	  		<td>
	  			<select name="year" id="year" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="yearCd" items="${yearCode}" varStatus="status">
						<option value="${yearCd.codeId}" ${yearCd.codeId == memberVO.year ? 'selected="selected"' : '' }>${yearCd.codeName}</option>
					</c:forEach>
				</select>년
				<form:errors path="year" style="width:99%" />
      			&nbsp;
      			<select name="month" id="month" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="monthCd" items="${monthCode}" varStatus="status">
						<option value="${monthCd.codeId}" ${monthCd.codeId == memberVO.month ? 'selected="selected"' : '' }>${monthCd.codeName}</option>
					</c:forEach>
				</select>월
				<form:errors path="month" style="width:99%" />
				&nbsp;
      			<select name="day" id="day" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="dayCd" items="${dayCode}" varStatus="status">
						<option value="${dayCd.codeId}" ${dayCd.codeId == memberVO.day ? 'selected="selected"' : '' }>${dayCd.codeName}</option>
					</c:forEach>
				</select>일
				<form:errors path="day" style="width:99%" />
	  		</td>  
		</tr>
		<tr>
			<th><span class="requare">*</span>비밀번호</th>
			<td>
				<!-- <input type="password" id="memPassword" name="memPassword"  style="width:99%" onchange="javascript:fn_codeCheck(event,'01')" /> -->
				<input type="password" id="memPasswordEncript" name="memPasswordEncript"  value="${memberVO.memPasswordEncript}"  style="width:99%"  />	
				<form:errors path="memPasswordEncript" style="width:99%" />			
			</td>      
			<th><span class="requare">*</span>비밀번호확인</th>
	  		<td>
	  			<!-- <input type="password" id="memPassword1" name="memPassword1"  style="width:96.6%" onchange="javascript:fn_codeCheck(event,'02')" /> -->
	  			<input type="password" id="memPassword" name="memPassword"  value="${memberVO.memPassword}"  style="width:99%"  />
	  			<form:errors path="memPassword" style="width:99%" />			 
	  		</td>  
		</tr>
		<tr>
			<th><span class="requare">*</span>주소</th>
			<td colspan="3">
				<input type="text" id="zipCode" name="zipCode"  value="${memberVO.zipCode}"  style="width:6%" readonly="readonly" />
				<form:errors path="zipCode" style="width:99%" />
				<a href="#fn_goSearchDoroCodePop" class="btn" onclick="javascript:fn_goSearchDoroCodePop( ); return false" onkeypress="this.onclick;">도로명검색</a>
				&nbsp;<input type="text" id="address" name="address"  value="${memberVO.address}"  style="width:44%" readonly="readonly" />
				<form:errors path="address" style="width:99%" />
				&nbsp;<input type="text" id="addressDtl" name="addressDtl"  value="${memberVO.addressDtl}"  style="width:99%"/>
				<form:errors path="addressDtl" style="width:99%" />				
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>이메일</th>
			<td colspan="3">
				<input type="text" id="email1" name="email1"  value="${memberVO.email1}"  style="width:45%" />
				<form:errors path="email1" style="width:99%" />
				@&nbsp;<input type="text" id="email2" name="email2"  value="${memberVO.email2}"  style="width:25%" />
				<form:errors path="email2" style="width:99%" />
				<select name="email3" id="email3" style="width:130px;">
					<option selected value=''>--직접입력--</option>
					<c:forEach var="emailDomainCd" items="${emailDomainCode}" varStatus="status">
						<option value="${emailDomainCd.codeName}" ${emailDomainCd.codeName == memberVO.email3 ? 'selected="selected"' : '' }>${emailDomainCd.codeName}</option>
					</c:forEach>
				</select>
			</td>      
		</tr>
		<tr>
			<th>이메일 수신여부</th>
			<td>
				<input type="radio" name="receiveMailYn" id="receiveMailYn" value="Y" class="choice" checked/>
      			예 &nbsp;
      			<input type="radio" name="receiveMailYn" id="receiveMailYn" value="N" class="choice" onclick="javascript:fn_receiveNoEmail();" />
      			아니오			
			</td>      
			<th>SMS 수신여부</th>
	  		<td>
	  			<input type="radio" name="receiveSmsYn" id="receiveSmsYn" value="Y" class="choice" checked/>
      			예 &nbsp;
      			<input type="radio" name="receiveSmsYn" id="receiveSmsYn" value="N" class="choice" onclick="javascript:fn_receiveNoSMS();" />
      			아니오	
	  		</td>  
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<select name="telNo1" id="telNo1" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="localTelNoCd" items="${localTelNoCode}" varStatus="status">
						<option value="${localTelNoCd.codeId}" ${localTelNoCd.codeId == memberVO.telNo1 ? 'selected="selected"' : '' }>${localTelNoCd.codeId}</option>
					</c:forEach>
				</select>
				-
				<input type="text" id="telNo2" name="telNo2"  value="${memberVO.telNo2}"  style="width:25%" />
				-
				<input type="text" id="telNo3" name="telNo3"  value="${memberVO.telNo3}"  style="width:25%" />
			</td>   
			<th><span class="requare">*</span>핸드폰번호</th>
			<td>
				<select name="hpNo1" id="hpNo1" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="mobileTelNoCd" items="${mobileTelNoCode}" varStatus="status">
						<option value="${mobileTelNoCd.codeId}" ${mobileTelNoCd.codeId == memberVO.hpNo1 ? 'selected="selected"' : '' }>${mobileTelNoCd.codeName}</option>
					</c:forEach>
				</select>
				<form:errors path="hpNo1" style="width:99%" />
				-
				<input type="text" id="hpNo2" name="hpNo2"  value="${memberVO.hpNo2}" style="width:25%" />
				<form:errors path="hpNo2" style="width:99%" />
				-
				<input type="text" id="hpNo3" name="hpNo3"  value="${memberVO.hpNo3}" style="width:25%" />
				<form:errors path="hpNo3" style="width:99%" />
			</td>      
		</tr>
		<tr>
			<th><span class="requare">*</span>권한그룹</th>
			<td>
				<select name="authGroupId" id="authGroupId" style="width:110px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="authGroupCd" items="${authGroupCode}" varStatus="status">
						<option value="${authGroupCd.codeId}" ${authGroupCd.codeId == memberVO.authGroupId ? 'selected="selected"' : '' }>${authGroupCd.codeName}</option>
					</c:forEach>
				</select>
				<form:errors path="authGroupId" style="width:99%" />
			</td>      
			<th><span class="requare">*</span>소속</th>
			<td>
				<input type="text" id="compNm" name="compNm" style="width:80%" readonly />
				<input type="hidden" id="companyId" name="companyId" readonly/>
				<a href="#LINK" class="btn" onclick="javascript:fn_search_companyPop( ); return false" onkeypress="this.onclick;">검색</a>
				<form:errors path="compNm" style="width:99%" />
			</td>	
		</tr>
	</tbody>
</table><!-- E : view-1 -->
</form:form>

<div class="page-btn">
	<a href="#fn_formSave" onclick="javascript:fn_formSave();" onkeypress="this.onclick;">저장</a>
	<a href="#fn_formReset" onclick="javascript:fn_formReset();" onkeypress="this.onclick;">취소</a>
	<a href="#fn_list" onclick="javascript:fn_list();" onkeypress="this.onclick;">목록</a>
</div><!-- E : page-btn -->
		
