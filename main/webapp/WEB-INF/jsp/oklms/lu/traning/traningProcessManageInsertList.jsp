<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">

	var count = 2;

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
		//훈련과정 검색 팝업 조회 성공여부
		var returnMsg = "";
		returnMsg = '${returnMsg}';
		if('POPUP_LIST_FAIL' != returnMsg && returnMsg != ''){
			$("#searchKeyword").val('${searchKeyword}');
			fn_showTrainpop();
		}
	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

	/* 훈련과정정보 리스트 조회 */
	function fn_search( param1 ){
		$("#pageIndex").val( param1 );

		//var reqUrl = "/lu/popup/goPopupSearch.do";
		var reqUrl = "/lu/traning/goInsertTraningProcessManageList.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//초기화 훈련과정정보 리스트 조회
	function fn_searchKeywordNo( param1 ){
		$("#searchKeyword").val('');
		$("#searchKeywordNull").val('allSearch');
		$("#pageIndex").val( param1 );

		//var reqUrl = "/lu/popup/goPopupSearch.do";
		var reqUrl = "/lu/traning/goInsertTraningProcessManageList.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	//훈련과정정보 목록에서 페이징 번호 클릭시 리스트 조회
	function fn_searchPaging( param1 ){
		$("#pageIndex").val( param1 );

		var reqUrl = "/lu/traning/goInsertTraningProcessManageList.do";
		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").submit();
	}

	function fn_showTrainpop( ){
		$("#student-popup").show();
		$(".training-area,.popup-bg").addClass("open");
		window.location.hash = "#open";
	}

	function fn_closeTrainpop( ){
		$("#student-popup").hide();
		$(".training-area,.popup-bg").removeClass("open");
	}

	function fn_hideTrainpop( ){
		var obj = "";
		obj = $("input:radio[name='tempTraningProcessId']:checked").val();
		if(undefined == obj){
			alert("훈련과정관리를 처리할 하나의 훈련과정명을 선택하여주십시오. ")
			return false;
		} else {
			fn_setTrainpopInfo(obj);
			$(".training-area,.popup-bg").removeClass("open");
		}
	}

	// 훈련과정관리 정보 셋팅
	function fn_setTrainpopInfo(obj){
		if( obj ){
			var arrInfo = obj.split("||");
			var traningProcessId = arrInfo[0];
			var traningProcessName = arrInfo[1];
			var traningProcessNo = arrInfo[2];

			$("#traningProcessId").val(traningProcessId);
			$("#traningProcessName").val(traningProcessName);
			$("#traningProcessNo").val(traningProcessNo);

		}
	}

	//OJT 행추가
	function fn_addTR(subjType, subjInfo){
		//행추가할때마다 행개수 +1
        ++count;
        var htmlStr = "";
        if( subjInfo )
        {
			var arrInfo = subjInfo.split("||");
			var yyyy = arrInfo[0];
			var term = arrInfo[1];
			var subjectCode = arrInfo[2];
			var subClass = arrInfo[3];
			var subjectName = arrInfo[4];
			var parmCount = arrInfo[5];
			
			
	        if(subjType == "OFF")
	       	{
	        	if($("#offSubjectNameTd").html() == "")
	       		{
		        	//첫줄인경우
	        		$("#offSubjectNameTd").text(subjectName);
	        		$("#offSubjectClassTd").text(subClass);
	
	        		htmlStr = $("#offSubjTr").html();
	        		htmlStr += "<input type='hidden' id='yyyy' name='yyyy' value='"+yyyy+"' />";
	                htmlStr += "<input type='hidden' id='term' name='term' value='"+term+"' />";
	                htmlStr += "<input type='hidden' id='subjectCode' name='subjectCode' value='"+subjectCode+"' />";
	                htmlStr += "<input type='hidden' id='subClass' name='subClass' value='"+subClass+"'/>";
	                htmlStr += "<input type='hidden' id='subjectName' name='subjectName' value='"+subjectName+"' />";
	                htmlStr += "<input type='hidden' id='subjType' name='subjType' value='"+subjType+"' />";
	                htmlStr += "<input type='hidden' id='subjInfo' name='subjInfo' value='"+subjInfo+"' />";
	                
	                $("#offSubjTr").html(htmlStr);
	       		}
	        	else
	       		{
		        	//두번째줄 이상인경우
	                htmlStr += "<tr id='"+subjType+"tr-"+count+"'>";
	                //htmlStr += "<td>"+count+"</td>";
	                htmlStr += "<td>Off-JT</td>";
	                htmlStr += "<td class='left'>"+subjectName+"</td>";
	                htmlStr += "<td>"+subClass+"</td>";
	                htmlStr += "<td> - </td>";
	                htmlStr += "<td><a href='#fn_delTR' onclick='javascript:fn_delTR(\""+subjType+"tr-"+count+"\");' class='btn-minus'>삭제</a>";
	
	                htmlStr += "<input type='hidden' id='yyyy' name='yyyy' value='"+yyyy+"' />";
	                htmlStr += "<input type='hidden' id='term' name='term' value='"+term+"' />";
	                htmlStr += "<input type='hidden' id='subjectCode' name='subjectCode' value='"+subjectCode+"' />";
	                htmlStr += "<input type='hidden' id='subClass' name='subClass' value='"+subClass+"'/>";
	                htmlStr += "<input type='hidden' id='subjectName' name='subjectName' value='"+subjectName+"' />";
	                htmlStr += "<input type='hidden' id='subjType' name='subjType' value='"+subjType+"' />";
	                htmlStr += "<input type='hidden' id='subjInfo' name='subjInfo' value='"+subjInfo+"' />";
	                htmlStr += "</td>";
	
	                htmlStr += "</tr>";
	
	                if($("[id^='OFFtr-']").length == 0)
                	{
	                	 $("#offSubjTr").after(htmlStr);
                	}
	                else
                	{
	                	$("[id^='OFFtr-']").last().after(htmlStr);
                	}
	       		}
	       	}
	        else
	       	{
	        	if($("#ojtSubjectNameTd").html() == "")
	       		{
	        		//첫줄인경우
	        		$("#ojtSubjectNameTd").html(subjectName);
	        		$("#ojtSubjectClassTd").html(subClass);
	
	        		htmlStr = $("#ojtSubjTr").html();
	        		htmlStr += "<input type='hidden' id='yyyy' name='yyyy' value='"+yyyy+"' />";
	                htmlStr += "<input type='hidden' id='term' name='term' value='"+term+"' />";
	                htmlStr += "<input type='hidden' id='subjectCode' name='subjectCode' value='"+subjectCode+"' />";
	                htmlStr += "<input type='hidden' id='subClass' name='subClass' value='"+subClass+"'/>";
	                htmlStr += "<input type='hidden' id='subjectName' name='subjectName' value='"+subjectName+"' />";
	                htmlStr += "<input type='hidden' id='subjType' name='subjType' value='"+subjType+"' />";
	                htmlStr += "<input type='hidden' id='subjInfo' name='subjInfo' value='"+subjInfo+"' />";
	                
	                $("#ojtSubjTr").html(htmlStr);
	       		}
	        	else
	       		{
	        		//두번째줄 이상인경우
	                htmlStr += "<tr id='"+subjType+"tr-"+count+"'>";
	                //htmlStr += "<td>"+count+"</td>";
	                htmlStr += "<td>"+subjType+"</td>";
	                htmlStr += "<td class='left'>"+subjectName+"</td>";
	                htmlStr += "<td>"+subClass+"</td>";
	                htmlStr += "<td> - </td>";
	                htmlStr += "<td><a href='#fn_delTR' onclick='javascript:fn_delTR(\""+subjType+"tr-"+count+"\");' class='btn-minus'>삭제</a>";
	
	                htmlStr += "<input type='hidden' id='yyyy' name='yyyy' value='"+yyyy+"' />";
	                htmlStr += "<input type='hidden' id='term' name='term' value='"+term+"' />";
	                htmlStr += "<input type='hidden' id='subjectCode' name='subjectCode' value='"+subjectCode+"' />";
	                htmlStr += "<input type='hidden' id='subClass' name='subClass' value='"+subClass+"'/>";
	                htmlStr += "<input type='hidden' id='subjectName' name='subjectName' value='"+subjectName+"' />";
	                htmlStr += "<input type='hidden' id='subjType' name='subjType' value='"+subjType+"' />";
	                htmlStr += "<input type='hidden' id='subjInfo' name='subjInfo' value='"+subjInfo+"' />";
	                htmlStr += "</td>";
	
	                htmlStr += "</tr>";
	
	                $("#traningLecTable").append(htmlStr);
	       		}
	       	}
        }
	}
	
	//OJT 행삭제
	function fn_delTR(param1){

		if(param1 == "OFFALL")
		{
			$("[id^='OFFtr-']").each(function(index)
			{
				$(this).remove();
			});
			
			$("#offSubjTr").find(":hidden").each(function(index)
			{
				$(this).remove();
			});
			
			$("#offSubjectNameTd").html("");
			$("#offSubjectClassTd").html("");
		}
		else if(param1 == "OJTALL")
		{
			$("[id^='OJTtr-']").each(function(index)
			{
				$(this).remove();
			});
			
			$("#ojtSubjTr").find(":hidden").each(function(index)
			{
				$(this).remove();
			});
			
			$("#ojtSubjectNameTd").html("");
			$("#ojtSubjectClassTd").html("");
		}
		else
		{
			$('#'+param1).remove();
		}
	}

	//Off-JT 교과목 검색 메핑
	function fn_lectureSearchPopup(gubun){
		//$("#uiGubun").val( 'tableTraningProcessPop' );
		//$("#returnUrl").val( '/lu/popup/searchSubjectNameListPopup' );
		$("#count").val( '1' );
		$("#subjectTraningType").val( gubun );

		popOpenWindow("", "popSearch", 850, 560);

		var reqUrl = CONTEXT_ROOT + "/lu/popup/goPopupSearchSubjectName.do";

		$("#frmPopupSearch").attr("target", "popSearch");
		$("#frmPopupSearch").attr("action", reqUrl);
		$("#frmPopupSearch").submit();
	}

	function fn_formSave(){
		if($("#traningProcessName").val() == ""){
			alert("훈련과정명을 선택 해주세요.");
			return false;
		}

		if($("#traningProcessNo").val() == ""){
			alert("훈련과정번호를 선택 해주세요.");
			return false;
		}

		//개셜강좌 메핑 행추가 없이 저장시
		if($("#offSubjTr").find(":hidden").length == 0)
		{
			alert("off-JT 과정이 선택되지 않았습니다.\n과정을 추가해 주세요.");
			return false;
		}
		
		if($("#ojtSubjTr").find(":hidden").length == 0)
		{
			alert("OJT 과정이 선택되지 않았습니다.\n과정을 추가해 주세요.");
			return false;
		}
		
		var count = 0;
		var insForm = "";
		$("#frmTraning").find("[id^='yyyy']").each(function(index)
		{
			insForm = $("<input type='hidden' name='yyyy"+(index+1)+"' value='"+$(this).val() +"' />");
			$("#frmTraning").append(insForm);
			count++;
		});
		
		$("#frmTraning").find("[id^='term']").each(function(index)
		{
			insForm = $("<input type='hidden' name='term"+(index+1)+"' value='"+$(this).val()+"' />");
			$("#frmTraning").append(insForm);
		});
		
		$("#frmTraning").find("[id^='subjectCode']").each(function(index)
		{
			insForm = $("<input type='hidden' name='subjectCode"+(index+1)+"' value='"+$(this).val()+"' />");
			$("#frmTraning").append(insForm);
		});
		
		$("#frmTraning").find("[id^='subClass']").each(function(index)
		{
			insForm = $("<input type='hidden' name='subClass"+(index+1)+"' value='"+$(this).val()+"' />");
			$("#frmTraning").append(insForm);
		});
		
		$("#frmTraning").find("[id^='subjectName']").each(function(index)
		{
			insForm = $("<input type='hidden' name='subjectName"+(index+1)+"' value='"+$(this).val()+"' />");
			$("#frmTraning").append(insForm);
		});
		
		$("#count").val( count );
		
		var reqUrl = "/lu/traning/insertTraningProcessManageList.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

	//훈련과정 첫화면으로 이동
	function fn_goList( ){
		$("#companyId").val('');
		$("#traningProcessId").val('');
		$("#tempTraningProcessId").val('noDivPopup');

		var reqUrl = "/lu/traning/listTraningProcessManage.do";

		$("#frmTraning").attr("action", reqUrl);
		$("#frmTraning").attr("target", "_self");
		$("#frmTraning").submit();
	}

</script>

<form id="frmPopupSearch" name="frmTraning" method="post">
<input type="hidden" id="pageSize" name="pageSize" value="20" />
<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
<input type="hidden" id="subjectTraningType" name="subjectTraningType" />
</form>
<!-- 회원정보 팝업용 끝 -->
<form id="frmTraning" name="frmTraning" method="post">
<input type="hidden" name="pageSize" value="${pageSize }" />
<input type="hidden" name="pageIndex" value="${pageIndex }" />
<input type="hidden" id="traningProcessId" name="traningProcessId" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="companyId" name="companyId" value="${companyId}" />
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<input type="hidden" id="searchKeywordNull" name="searchKeywordNull"/>
<!-- <input type="hidden" id="uiGubun" name="uiGubun" value="traningProcessPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="/lu/traning/traningProcessManageInsertList" /> -->

<ul id="student-popup" style="display:none;">
	<li class="training-area" style="margin-left:-350px; margin-top:-119px;">
		<h1>훈련과정 검색</h1>
		<div class="search-box-1 mb-020">
			<input type="text" id="searchKeyword" name="searchKeyword" style="width:200px" placeholder="훈련과정명, 훈련과정번호을 입력" value="${searchKeyword }" />
			<a href="#!" onclick="javascript:fn_search(1); return false">조회</a>
			<a href="#!" onclick="javascript:fn_searchKeywordNo(1); return false">전체조회</a>
		</div><!-- E : search-box-1 -->

		<table class="type-2">
				<colgroup>
					<col width="40px" />
					<col width="*" />
					<col width="180px" />
				</colgroup>
				<thead>
					<tr>
						<th>선택</th>
						<th>훈련과정명</th>
						<th>훈련과정번호</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${resultTraningProcessList}" varStatus="status">
					<tr>
						<td><input type="radio" name="tempTraningProcessId" id="tempTraningProcessId" value="${result.traningProcessId}||${result.traningProcessName}||${result.traningProcessNo}"></td>
						<td>${result.traningProcessName}</td>
						<td>${result.traningProcessNo}</td>
					</tr>
					</c:forEach>
					<c:if test="${fn:length(resultTraningProcessList) == 0}">
					<tr>
						<td colspan="3"><spring:message code="common.nodata.msg" /></td>
					</tr>
					</c:if>
				</tbody>
			</table><!-- E : 훈련과정검색 -->

			<c:if test="${fn:length(resultTraningProcessList) != 0}">
			<div class="page-num mt-015">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_searchPaging" />
			</div>
			</c:if>

		<div class="btn-area align-center mt-010">
			<a href="#fn_closeTrainpop" class="yellow close" onclick="javascript:fn_closeTrainpop(); return false" onkeypress="this.onclick();">닫기</a>
			<a href="#fn_hideTrainpop" class="orange close" onclick="javascript:fn_hideTrainpop(); return false" onkeypress="this.onclick();">확인</a>
		</div><!-- E : btn-area -->
	</li>

	<li class="popup-bg"></li>
</ul><!-- E : student-popup -->

		<div id="">
			<h2>훈련과정 관리</h2>

			<div class="group-area">
				<table class="type-2">
					<colgroup>
						<col style="width:*" />
						<col style="width:*" />
						<col style="width:*" />
						<col style="width:*" />
					</colgroup>
					<thead>
						<tr>
							<th>기업명</th>
							<th>소재지</th>
							<th>선정일</th>
							<th>기업고용보험관리번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${resultCompanyList}" varStatus="status">
						<tr>
							<td>${result.companyName}</td>
							<td>${result.address}</td>
							<td>${result.choiceDay}</td>
							<td>${result.employInsManageNo}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

				<div class="btn-area align-right mt-010">
					<a href="#fn_showTrainpop" class="yellow float-left" onclick="javascript:fn_showTrainpop(); return false" onkeypress="this.onclick();">훈련과정 검색</a>
				</div>

				<table class="type-write mt-020">
					<colgroup>
						<col style="width:194px" />
						<col style="width:37%" />
						<col style="width:194px" />
						<col style="width:*" />
					</colgroup>
					<tbody>
						<tr>
							<th>훈련과정명</th>
							<td><input type="text" id="traningProcessName" name="traningProcessName" style="width:99%;" readonly="readonly" /></td>
							<th>훈련과정번호</th>
							<td><input type="text" id="traningProcessNo" name="traningProcessNo" style="width:99%;" readonly="readonly" /></td>
						</tr>
					</tbody>
				</table>

				<table class="type-2 mt-020" id="traningLecTable">
					<colgroup>
						<%-- <col style="width:40px" /> --%>
						<col style="width:80px" />
						<col style="width:*" />
						<col style="width:60px" />
						<col style="width:90px" />
						<col style="width:80px" />
					</colgroup>
					<thead>
						<tr>
							<!-- <th>번호</th> -->
							<th>구분</th>
							<th>개설강좌명</th>
							<th>분반</th>
							<th>교과목추가</th>
							<th>삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr id="offSubjTr">
							<!-- <td>1</td> -->
							<td>Off-JT</td>
							<td class="left" id="offSubjectNameTd"></td>
							<td id="offSubjectClassTd"></td>
							<td><a href="#fn_offJtLectureSearchPopup" onclick="javascript:fn_lectureSearchPopup('OFF'); return false" class="btn-line-blue">검색</a></td>
							<td>
								<a href="#fn_delTR" onclick="javascript:fn_delTR('OFFALL'); return false">전체삭제</a>
							</td>
						</tr>
						<tr id="ojtSubjTr">
							<td>OJT</td>
							<td class="left" id="ojtSubjectNameTd"></td>
							<td id="ojtSubjectClassTd"></td>
							<td><a href="#fn_ojtLectureSearchPopup" onclick="javascript:fn_lectureSearchPopup('OJT'); return false" class="btn-line-blue">검색</a></td>
							<td>
								<a href="#fn_delTR" onclick="javascript:fn_delTR('OJTALL'); return false">전체삭제</a>
							</td>
						</tr>
					</tbody>
				</table>

				<div class="btn-area align-right mt-010">
					<a href="#fn_goList" class="gray-1 float-left" onclick="javascript:fn_goList();">목록</a>
					<a href="#fn_formSave" class="orange" onclick="javascript:fn_formSave(); return false" onkeypress="this.onclick();">저장</a>
				</div><!-- E : btn-area -->
			</div>
	</div><!-- E : container -->
	
	<script type="text/javascript">
	 $( function() {
		 $(".training-area").draggable();
	  });
	 
	</script>

</form>

