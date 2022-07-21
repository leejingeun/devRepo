<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<c:set var="targetUrl" value="/la/company/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	
	$(document).ready(function() {
		
	});
	
	/*====================================================================
		화면 초기화 
	====================================================================*/
	function loadPage() {
	
	}

	/* 각종 버튼에 대한 액션 초기화 */
	function initEvent() {
	}

	/* 화면이 로드된후 처리 초기화 */
	function initHtml() {
	}

	/*====================================================================
	사용자 정의 함수 
	====================================================================*/

	/* 수정 페이지로 이동 */
	function fn_updt(){
		
		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateCompany.do";

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
	
	/* 회원삭제 */
	function fn_del(){
		if( confirm("회원을 삭제하겠습니까?") ) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteCompany.do";

			$("#frmCompany").attr("method", "post" );
			$("#frmCompany").attr("action", reqUrl);
			$("#frmCompany").submit();	
		}
	}
	
</script>

<form:form commandName="frmCompany">
<input type="hidden" name="companyId" id="companyId" value="${companyVO.companyId}"/>
<!-- 검색조건유지 필드 시작 -->
<input type="hidden" name="searchName" id="searchName" value="${companyVO.searchName}"/>
<input type="hidden" name="searchWord" id="searchWord" value="${companyVO.searchWord}"/>
</form:form>
<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
	<tbody>
		<tr>
			<th width="132px">기업명</th>
			<td>
				${companyVO.companyName}	
			</td>      
			<th width="132px">사업자번호</th>
	  		<td>
				${companyVO.companyNo}	
	  		</td>  
		</tr>
		<tr>
			<th>업종</th>
			<td>
				${companyVO.businessType}
			</td>      
			<th>업태</th>
			<td>
				${companyVO.businessCondition}
			</td>    
		</tr>
		<tr>
			<th>홈페이지</th>
			<td>
				${companyVO.homePage}
			</td>      
			<th>업체대표</th>
	  		<td>
				${companyVO.ceo}
	  		</td>  
		</tr>
		<tr>
			<th>주소</th>
			<td colspan="3">
				${companyVO.zipCode}&nbsp;&nbsp;${companyVO.address}
				</br>${companyVO.addressDtl}
			</td>      
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				${companyVO.telNo1}-${companyVO.telNo2}-${companyVO.telNo3}
			</td>   
			<th>팩스번호</th>
			<td>
				${companyVO.faxNo1}-${companyVO.faxNo2}-${companyVO.faxNo3}
			</td>   
		</tr>
	</tbody>
</table><!-- E : view-1 -->
<div class="page-btn">
	<a href="#fn_updt" onclick="javascript:fn_updt();" onkeypress="this.onclick;">수정</a>
	<a href="#fn_list" onclick="javascript:fn_list();" onkeypress="this.onclick;">목록</a>
</div><!-- E : page-btn -->	
		
