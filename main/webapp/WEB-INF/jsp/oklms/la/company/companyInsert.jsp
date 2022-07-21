<%@page import="kr.co.sitglobal.oklms.comm.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<%
String retMsg = StringUtil.trimString((String)request.getAttribute("retMsg"),"");

%>
<c:set var="targetUrl" value="/la/company/"/>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">
	var targetUrl = "${targetUrl}";
	$(document).ready(function() {
		loadPage();	
		$('#companyNo').keypress(function (event) {
		    if (event.which && (event.which <= 47 || event.which >= 58) && event.which != 8) {
		      event.preventDefault();
		    }
		  });
	});
	
	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {

	}

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/*====================================================================
	사용자 정의 함수 
	====================================================================*/

	/* 입력 필드 초기화 */
	function fn_formReset( param1 ){
		$("#frmCompany").find("input,select").val("");
	}
	
	/* 수정 */
	function fn_formSave(){
		
		if($("#companyName").val() == ""){
			alert("기업명을 넣어 주세요.");
			return false;
		}
		
		if($("#companyNo").val() == ""){
			alert("사업자번호을 넣어 주세요.");
			return false;
		}
		
		if($("#businessType").val() == ""){
			alert("업명을 넣어 주세요.");
			return false;
		}
		
		if($("#businessCondition").val() == ""){
			alert("업태을 넣어 주세요.");
			return false;
		}
		
		if($("#ceo").val() == ""){
			alert("업체대표을 넣어 주세요.");
			return false;
		}

		if($("#zoneCode").val() == ""){
			alert("지역을 넣어 주세요.");
			return false;
		}
		
		if($("#zipCode").val() == ""){
			alert("우편번호를 넣어 주세요.");
			return false;
		}

		if($("#address").val() == ""){
			alert("주소를 넣어 주세요.");
			return false;
		}
		
		if($("#addressDtl").val() == ""){
			alert("주소상세를 넣어 주세요.");
			return false;
		}
		
		if($("#telNo1").val() == ""){
			alert("전화번호1를 넣어 주세요.");
			return false;
		}
		
		if($("#telNo2").val() == ""){
			alert("전화번호2를 넣어 주세요.");
			return false;
		}
		
		if($("#telNo3").val() == ""){
			alert("전화번호3를 넣어 주세요.");
			return false;
		}
				
		if($("#status").val() != "true"){
			alert("중복체크를 하지 않았습니다.");
			return false;
		}
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "insertCompany.do";

		$("#frmCompany").attr("method", "post" );
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();
	}
	
	/* 목록 페이지로 이동 */
	function fn_list(){
		var reqUrl = CONTEXT_ROOT + targetUrl + "listCompany.do";

		$("#frmCompany").attr("method", "post" );
		$("#frmCompany").attr("action", reqUrl);
		$("#frmCompany").submit();	
	}
	
	/* 다음라이브러리를 통한 도로명 검색 팝업 호출 */
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
	 
	//사업자번호 중복체크
	function fn_CompanyIdDuckCheck(){
		if($("#companyNo").val()==''){
			alert("사업자등록번호를 등록해 주세요.");
			return false;
		}
		if(checkBizID($("#companyNo").val())){
			$("#companyNoPop").val($("#companyNo").val());
			
			popOpenWindow("", "popSchMemId", 650, 560);
			
			var reqUrl = CONTEXT_ROOT + "/la/popup/popup/goPopupCompanyInfoIdCheck.do";
			
			$("#frmPop").attr("action", reqUrl);
			$("#frmPop").attr("target", "popSchMemId");
			$("#frmPop").submit();
		}else{
			alert("사업자등록번호가 틀립니다.");
			$("#companyNo").val("");
		}
	}
	
	function checkBizID(bizID) { 
	    // bizID는 숫자만 10자리로 해서 문자열로 넘긴다. 
	    var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
	    var tmpBizID, i, chkSum=0, c2, remander; 
	    var result;
	    bizID = bizID.replace(/-/gi,''); 
	    for (i=0; i<=7; i++) {
	        chkSum += checkID[i] * bizID.charAt(i);
	    } 
	    c2 = "0" + (checkID[8] * bizID.charAt(8)); 
	    c2 = c2.substring(c2.length - 2, c2.length); 
	    chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
	    remander = (10 - (chkSum % 10)) % 10 ; 
	    if (Math.floor(bizID.charAt(9)) == remander) {
	        result = true ; // OK!
	    } else {
	        result = false;
	    }
	    return result; 
	} 

	function checkNumber(check_form){
	     var numPattern = /([^0-9])/;
	     var numPattern = check_form.value.match(numPattern);
	     if(numPattern != null){
	         alert("숫자만 입력해 주세요!");
	         check_form.value = "";
	         check_form.focus();
	         return false;
	     }
	 }
	

</script>
<p style="display:block;text-align:left;"><font color="red">*</font> 는 필수입력사항입니다.</p>
<%-- 팝업폼 --%>
<form id="frmPop" method="post" name="frmPop">
  <input type="hidden" name="companyNoPop" id="companyNoPop"/>
</form>
<%-- 팝업폼 --%>
<form:form commandName="frmCompany">
<!-- 디폴트 셋팅항목 필드 시작 -->
<input type="hidden" name="status" id="status"/>
<input type="hidden" name="tempCompanyNo" id="tempCompanyNo"/>
<!-- 디폴트 셋팅항목 필드 끝 -->

<!-- 검색조건유지 필드 시작 -->
<input type="hidden" name="searchName" id="searchName" value="${CompanyVO.searchName}"/>
<input type="hidden" name="searchWord" id="searchWord" value="${CompanyVO.searchWord}"/>
<!-- 검색조건유지 필드 끝 -->
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th width="132px">기업명<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td>
				<input type="text" id="companyName" name="companyName"  value="${CompanyVO.companyName}" style="width:99%" />	
				<form:errors path="companyName" style="width:99%" />		
			</td>      
			<th width="132px">사업자번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	  		<td>
	  			<input type="text" id="companyNo" name="companyNo"  value="${CompanyVO.companyNo}" style="width:30%" maxlength="10"   />	
	  			<a href="#fn_CompanyIdDuckCheck" class="btn" onclick="javascript:fn_CompanyIdDuckCheck(); return false" onkeypress="this.onclick;">중복확인</a>
	  			※"-"빼고 넣어 주세요.
				<form:errors path="companyNo" style="width:99%" />		
	  		</td>  
		</tr>
		<tr>
			<th>업종<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td>
				<input type="text" id="businessType" name="businessType"  value="${CompanyVO.businessType}" style="width:60%" />
				<form:errors path="businessType" style="width:99%" />				
			</td>      
			<th>업태<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td>
				<input type="text" id="businessCondition" name="businessCondition"  value="${CompanyVO.businessCondition}" style="width:60%" />
				<form:errors path="businessCondition" style="width:99%" />				
			</td>    
		</tr>
		<tr>
			<th>홈페이지</th>
			<td>
				<input type="text" id="homePage" name="homePage"  value="${CompanyVO.homePage}" style="width:60%" />
				<form:errors path="homePage" style="width:99%" />				
			</td>      
			<th>업체대표<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
	  		<td>
	  			<input type="text" id="ceo" name="ceo"  value="${CompanyVO.ceo}"  style="width:99%"  />
	  			<form:errors path="ceo" style="width:99%" />			 
	  		</td>  
		</tr>
		<tr>
			<th>주소<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td colspan="3">
				<input type="text" id="zipCode" name="zipCode"  value="${CompanyVO.zipCode}"  style="width:6%" readonly="readonly" />
				<form:errors path="zipCode" style="width:99%" />
				<a href="#fn_goSearchDoroCodePop" class="btn" onclick="javascript:fn_goSearchDoroCodePop( ); return false" onkeypress="this.onclick;">도로명검색</a>
				&nbsp;<input type="text" id="address" name="address"  value="${CompanyVO.address}"  style="width:44%" readonly="readonly" />
				<form:errors path="address" style="width:99%" />
				&nbsp;<input type="text" id="addressDtl" name="addressDtl"  value="${CompanyVO.addressDtl}"  style="width:99%"/>
				<form:errors path="addressDtl" style="width:99%" />				
			</td>      
		</tr>
		<tr>
			<th>전화번호<img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
			<td>
				<select name="telNo1" id="telNo1" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="localTelNoCd" items="${localTelNoCode}" varStatus="status">
						<option value="${localTelNoCd.codeId}" ${localTelNoCd.codeId == CompanyVO.telNo1 ? 'selected="selected"' : '' }>${localTelNoCd.codeId}</option>
					</c:forEach>
				</select>
				-
				<input type="text" id="telNo2" name="telNo2"  value="${CompanyVO.telNo2}"  style="width:25%" maxlength="4"/>
				-
				<input type="text" id="telNo3" name="telNo3"  value="${CompanyVO.telNo3}"  style="width:25%" maxlength="4"/>
			</td>   
			<th>팩스번호</th>
			<td>
				<select name="faxNo1" id="faxNo1" style="width:77px;">
					<option selected value=''>--선택--</option>
					<c:forEach var="localTelNoCd" items="${localTelNoCode}" varStatus="status">
						<option value="${localTelNoCd.codeId}" ${localTelNoCd.codeId == CompanyVO.faxNo1 ? 'selected="selected"' : '' }>${localTelNoCd.codeId}</option>
					</c:forEach>
				</select>
				-
				<input type="text" id="faxNo2" name="faxNo2"  value="${CompanyVO.faxNo2}"  style="width:25%" maxlength="4"/>
				-
				<input type="text" id="faxNo3" name="faxNo3"  value="${CompanyVO.faxNo3}"  style="width:25%" maxlength="4"/>
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
		
