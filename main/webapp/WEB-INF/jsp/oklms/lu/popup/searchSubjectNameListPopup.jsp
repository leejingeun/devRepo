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
		
		$("#pageIndex").val( param1 );
		//$("#uiGubun").val( 'tableTraningProcessPop' );
		//$("#returnUrl").val( '/lu/popup/searchSubjectNameListPopup' ); 
		//var reqUrl = "/lu/popup/goPopupSearch.do"; 
		
		var reqUrl = "/lu/popup/goPopupSearchSubjectName.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}
	
	//선택 버튼을 클릭시 부모창에 필요항목 셋팅
	function fn_selectInfo(){
		if( opener ){
			
			var successCount = 0;
			var failCount = 0;
			var check = true;
			$("input:checkbox[name='tempSubjectCode']:checked").each(function() { 
				var subjInfo = $(this).val();
				check = true; 
				
				$(opener.document).find("[id^='subjInfo']").each(function(index, value)
				{
					var prevSubjInfo = this.value;
					
					if(subjInfo == prevSubjInfo)
					{
						check = false;
						return false;
					}
				});
				
				if(check)
				{
			        opener.fn_addTR($("#subjectTraningType").val(), subjInfo);
			        successCount++;
				}
				else
				{
					failCount++;
				}

		   });

			if(successCount == 0 && failCount > 0){
				alert("이미 추가된 과정입니다.");
				return false;
			}
			else if(successCount > 0 && failCount > 0)
			{
				alert("이미 추가된 "+failCount+"개 과정을 제외한 "+successCount+"개 과정이 추가되었습니다.");
			}
			else if(successCount == 0)
			{
				alert("추가할 과정을 선택해주세요.");
				return false;
			}
			else
			{
				alert("선택된 "+successCount+"개 과정이 추가되었습니다.");
				return false;
			}
			
			//window.close();
		}
	}
	
	function fn_closeWin(){
		window.close();
	}
	
</script>

<!-- 회원정보 팝업용 끝 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="${pageSize }" />
<input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="subjectTraningType" name="subjectTraningType" value="${currProcVO.subjectTraningType }" /> 
<input type="hidden" id="count" name="count" value="${currProcVO.count }" />
<!-- <input type="hidden" id="uiGubun" name="uiGubun" />
<input type="hidden" id="returnUrl" name="returnUrl" /> -->
	
<!-- 팝업 사이즈 : 가로 최소 650px 이상 설정 -->
		<div id="pop-wrapper" class="min-w650">

			<h1>교과목검색</h1>

			<div class="search-box-1 mb-010">
				<input type="text" id="searchDepartmentName" name="searchDepartmentName" value="${currProcVO.searchDepartmentName }" placeholder="학과명" style="width:150px;" />
				<select id="searchYyyyCd" name="searchYyyyCd" onchange="">
					<option value="" selected>::선택::</option>
					<option value="2016" ${'2016' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2016학년도</option>
					<option value="2017" ${'2017' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2017학년도</option>
					<option value="2018" ${'2018' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2018학년도</option>
					<option value="2019" ${'2019' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2019학년도</option>
					<option value="2020" ${'2020' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2020학년도</option>
					<option value="2021" ${'2021' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2021학년도</option>
					<option value="2022" ${'2022' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2022학년도</option>
					<option value="2023" ${'2023' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2023학년도</option>
					<option value="2024" ${'2024' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2024학년도</option>
					<option value="2025" ${'2025' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2025학년도</option>
					<option value="2026" ${'2026' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2026학년도</option>
					<option value="2027" ${'2027' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2027학년도</option>
					<option value="2028" ${'2028' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2028학년도</option>
					<option value="2029" ${'2029' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2029학년도</option>
					<option value="2030" ${'2030' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2030학년도</option>
					<option value="2031" ${'2031' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2031학년도</option>
					<option value="2032" ${'2032' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2032학년도</option>
					<option value="2033" ${'2033' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2033학년도</option>
					<option value="2034" ${'2034' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2034학년도</option>
					<option value="2035" ${'2035' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2035학년도</option>
					<option value="2036" ${'2036' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2036학년도</option>
					<option value="2037" ${'2037' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2037학년도</option>
					<option value="2038" ${'2038' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2038학년도</option>
					<option value="2039" ${'2039' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2039학년도</option>
					<option value="2040" ${'2040' == currProcVO.searchYyyyCd ? 'selected="selected"' : '' }>2040학년도</option>
				</select>
				<select id="searchTermCd" name="searchTermCd" onchange="">
					<option value="" selected>::선택::</option>
					<option value="1" ${'1' == currProcVO.searchTermCd ? 'selected="selected"' : '' }>1학기</option>
					<option value="2" ${'2' == currProcVO.searchTermCd ? 'selected="selected"' : '' }>2학기</option>
				</select>
				<input type="text" id="searchSubjectName" name="searchSubjectName" value="${currProcVO.searchSubjectName }" placeholder="개설강좌명 입력" style="width:150px;" />
				<select id="searchSubClassCd" name="searchSubClassCd" onchange="">
					<option value="" selected>::선택::</option>
					<option value="01" ${'01' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>01분반</option>
					<option value="02" ${'02' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>02분반</option>
					<option value="03" ${'03' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>03분반</option>
					<option value="04" ${'04' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>04분반</option>
					<option value="05" ${'05' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>05분반</option>
					<option value="06" ${'06' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>06분반</option>
					<option value="07" ${'07' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>07분반</option>
					<option value="08" ${'08' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>08분반</option>
					<option value="09" ${'09' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>09분반</option>
					<option value="10" ${'10' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>10분반</option>
					<option value="11" ${'11' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>11분반</option>
					<option value="12" ${'12' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>12분반</option>
					<option value="13" ${'13' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>13분반</option>
					<option value="14" ${'14' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>14분반</option>
					<option value="15" ${'15' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>15분반</option>
					<option value="16" ${'16' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>16분반</option>
					<option value="17" ${'17' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>17분반</option>
					<option value="18" ${'18' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>18분반</option>
					<option value="19" ${'19' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>19분반</option>
					<option value="20" ${'20' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>20분반</option>
					<option value="21" ${'21' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>21분반</option>
					<option value="22" ${'22' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>22분반</option>
					<option value="23" ${'23' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>23분반</option>
					<option value="24" ${'24' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>24분반</option>
					<option value="25" ${'25' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>25분반</option>
					<option value="26" ${'26' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>26분반</option>
					<option value="27" ${'27' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>27분반</option>
					<option value="28" ${'28' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>28분반</option>
					<option value="29" ${'29' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>29분반</option>
					<option value="30" ${'30' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>30분반</option>
					<option value="31" ${'31' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>31분반</option>
					<option value="32" ${'32' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>32분반</option>
					<option value="33" ${'33' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>33분반</option>
					<option value="34" ${'34' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>34분반</option>
					<option value="35" ${'35' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>35분반</option>
					<option value="36" ${'36' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>36분반</option>
					<option value="37" ${'37' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>37분반</option>
					<option value="38" ${'38' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>38분반</option>
					<option value="39" ${'39' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>39분반</option>
					<option value="40" ${'40' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>40분반</option>
					<option value="41" ${'41' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>41분반</option>
					<option value="42" ${'42' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>42분반</option>
					<option value="43" ${'43' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>43분반</option>
					<option value="44" ${'44' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>44분반</option>
					<option value="45" ${'45' == currProcVO.searchSubClassCd ? 'selected="selected"' : '' }>45분반</option>
				</select>
				<a href="#fn_search" onclick="javascript:fn_search('1'); return false" onkeypress="this.onclick();">조회</a>
			</div><!-- E : search-box-1 -->

			<table class="type-2">
				<colgroup>
					<col width="40px" />
					<col width="140px" />
					<col width="70px" />
					<col width="50px" />
					<col width="*" />
					<col width="60px" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>학과명</th>
						<th>학년도</th>
						<th>학기</th>
						<th>개설강좌명</th>
						<th>분반</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultSubjectSeachList}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="tempSubjectCode" id="tempSubjectCode${status.index}" value="${result.yyyy}||${result.term}||${result.subjectCode}||${result.subClass}||${result.subjectName}||${parmCount}"></td>
						<td>${result.departmentName}</td>
						<td>${result.yyyy}</td>
						<td>${result.term}</td>
						<td class="left"><label for="tempSubjectCode${status.index}">${result.subjectName}</label></td>
						<td>${result.subClass}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultSubjectSeachList) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 기업체검색 -->
			
			<ul class="page-num-btn mt-015">
				<li class="page-num-area">
					<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_search" />
				</li>
				<li class="page-btn-area">
					<a href="#fn_closeWin" class="yellow close" onclick="javascript:fn_closeWin(); return false" onkeypress="this.onclick();">닫기</a>
					<a href="#fn_selectInfo" class="orange close" onclick="javascript:fn_selectInfo(); return false" onkeypress="this.onclick();">추가</a>
				</li>
			</ul><!-- E : page-num-btn -->

		</div><!-- E : wrapper -->
</form>
					

	