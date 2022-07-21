<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript">

	var count = '${ojtCnt}';
	++count;

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

	}

	/*====================================================================
		사용자 정의 함수
	====================================================================*/

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

	// 교과목 검색 메핑
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
			alert("훈련과정명을 넣어 주세요.");
			return false;
		}

		if($("#traningProcessNo").val() == ""){
			alert("훈련과정번호를 넣어 주세요.");
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
		
		$("#frmTraning").find("[id^='subjectName']").each(function(index)
		{
			insForm = $("<input type='hidden' name='subjectName"+(index+1)+"' value='"+$(this).val()+"' />");
			$("#frmTraning").append(insForm);
		});
		
		$("#count").val( count );

		var reqUrl = "/lu/traning/updateTraningProcessManageList.do";

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
<input type="hidden" id="companyId" name="companyId" value="${companyId}" />
<input type="hidden" id="traningProcessId" name="traningProcessId" value="${traningProcessId}" />
<input type="hidden" id="count" name="count" />
<input type="hidden" id="subjectTraningType" name="subjectTraningType" />
<input type="hidden" id="tempTraningProcessId" name="tempTraningProcessId" />
<!-- <input type="hidden" id="uiGubun" name="uiGubun" value="traningProcessPop" />
<input type="hidden" id="returnUrl" name="returnUrl" value="/lu/traning/traningProcessManageUpdateList" /> -->

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
						<c:forEach var="result" items="${resultCompanyOneList}" varStatus="status">
						<tr>
							<td>${result.companyName}</td>
							<td>${result.address}</td>
							<td>${result.choiceDay}</td>
							<td>${result.employInsManageNo}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

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
							<td><input type="text" id="traningProcessName" name="traningProcessName" value="${offJtTraingOneVO.hrdTraningName}" style="width:99%;" readonly="readonly" /></td>
							<th>훈련과정번호</th>
							<td><input type="text" id="traningProcessNo" name="traningProcessNo" value="${offJtTraingOneVO.hrdTraningNo}" style="width:99%;" readonly="readonly" /></td>
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
							<th>구분</th>
							<th>개설강좌명</th>
							<th>분반</th>
							<th>교과목검색</th>
							<th>추가/삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="resultOffJt" items="${resultOffJtSubjectInfoList}" varStatus="status">
							<c:choose>
								<c:when test="${status.count == 1}">
								<tr id="offSubjTr">
									<td>Off-JT</td>
									<td class="left" id="offSubjectNameTd">${resultOffJt.subjectName}</td>
									<td id="offSubjectClassTd">${resultOffJt.subClass}</td>
									<td><a href="#fn_offJtLectureSearchPopup" onclick="javascript:fn_lectureSearchPopup('OFF'); return false" class="btn-line-blue">검색</a></td>
									<td>
										<a href="#fn_delTR" onclick="javascript:fn_delTR('OFFALL'); return false">전체삭제</a>
										<input type="hidden" id="yyyy" name="yyyy" value="${resultOffJt.yyyy}" />
										<input type="hidden" id="term" name="term" value="${resultOffJt.term}" />
										<input type="hidden" id="subjectCode" name="subjectCode" value="${resultOffJt.subjectCode}" />
										<input type="hidden" id="subClass" name="subClass" value="${resultOffJt.subClass}" />
										<input type="hidden" id="subjectName" name="subjectName" value="${resultOffJt.subjectName}" />
										<input type="hidden" id="subjType" name="subjType" value="OFF" />
										<input type="hidden" id="subjInfo" name="subjectName" value="${resultOffJt.yyyy}||${resultOffJt.term}||${resultOffJt.subjectCode}||${resultOffJt.subClass}||${resultOffJt.subjectName}||" />
									</td>
								</tr>
								</c:when>
								<c:otherwise>
								<tr id="OFFtr-${status.index}">
									<td>Off-JT</td>
									<td class="left" id="offSubjectNameTd">${resultOffJt.subjectName}</td>
									<td id="offSubjectClassTd">${resultOffJt.subClass}</td>
									<td> - </td>
									<td>
										<a href="#fn_delTR" onclick="javascript:fn_delTR('OFFtr-${status.index}'); return false" class='btn-minus'>삭제</a>
										<input type="hidden" id="yyyy" name="yyyy" value="${resultOffJt.yyyy}" />
										<input type="hidden" id="term" name="term" value="${resultOffJt.term}" />
										<input type="hidden" id="subjectCode" name="subjectCode" value="${resultOffJt.subjectCode}" />
										<input type="hidden" id="subClass" name="subClass" value="${resultOffJt.subClass}" />
										<input type="hidden" id="subjectName" name="subjectName" value="${resultOffJt.subjectName}" />
										<input type="hidden" id="subjType" name="subjType" value="OFF" />
										<input type="hidden" id="subjInfo" name="subjectName" value="${resultOffJt.yyyy}||${resultOffJt.term}||${resultOffJt.subjectCode}||${resultOffJt.subClass}||${resultOffJt.subjectName}||" />
									</td>
								</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:forEach var="resultOjt" items="${resultOjtSubjectInfoList}" varStatus="status">
							<c:choose>
							<c:when test="${status.count == 1}">
							<tr id="ojtSubjTr">
								<td>OJT</td>
								<td class="left" id="ojtSubjectNameTd">${resultOjt.subjectName}</td>
								<td id="ojtSubjectClassTd">${resultOjt.subClass}</td>
								<td><a href="#fn_addOjtLectureSearchPopup" onclick="javascript:fn_lectureSearchPopup('OJT'); return false" class="btn-line-blue">검색</a></td>
								<td>
									<a href="#fn_delTR" onclick="javascript:fn_delTR('OJTALL'); return false">전체삭제</a>
								<input type="hidden" id="yyyy" name="yyyy" value="${resultOjt.yyyy}" />
								<input type="hidden" id="term" name="term" value="${resultOjt.term}" />
								<input type="hidden" id="subjectCode" name="subjectCode" value="${resultOjt.subjectCode}" />
								<input type="hidden" id="subClass" name="subClass" value="${resultOjt.subClass}" />
								<input type="hidden" id="subjectName" name="subjectName" value="${resultOjt.subjectName}" />
								<input type="hidden" id="subjType" name="subjType" value="OJT" />
								<input type="hidden" id="subjInfo" name="subjectName" value="${resultOjt.yyyy}||${resultOjt.term}||${resultOjt.subjectCode}||${resultOjt.subClass}||${resultOjt.subjectName}||" />
								</td>
							</tr>
							</c:when>
							<c:otherwise>
							<tr id="OJTtr-${status.index}">
								<td>OJT</td>
								<td class="left">${resultOjt.subjectName}</td>
								<td>${resultOjt.subClass}</td>
								<td> - </td>
								<td>
									<a href="#fn_delTR" onclick="javascript:fn_delTR('OJTtr-${status.index}'); return false" class='btn-minus'>삭제</a>
								<input type="hidden" id="yyyy" name="yyyy" value="${resultOjt.yyyy}" />
								<input type="hidden" id="term" name="term" value="${resultOjt.term}" />
								<input type="hidden" id="subjectCode" name="subjectCode" value="${resultOjt.subjectCode}" />
								<input type="hidden" id="subClass" name="subClass" value="${resultOjt.subClass}" />
								<input type="hidden" id="subjectName" name="subjectName" value="${resultOjt.subjectName}" />
								<input type="hidden" id="subjType" name="subjType" value="OJT" />
								<input type="hidden" id="subjInfo" name="subjectName" value="${resultOjt.yyyy}||${resultOjt.term}||${resultOjt.subjectCode}||${resultOjt.subClass}||${resultOjt.subjectName}||" />
								</td>
							</tr>
							</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>

				<div class="btn-area align-right mt-010">
					<a href="#fn_goList" class="gray-1 float-left" onclick="javascript:fn_goList();">목록</a>
					<a href="#fn_formSave" class="orange" onclick="javascript:fn_formSave(); return false" onkeypress="this.onclick();">저장</a>
				</div><!-- E : btn-area -->
			</div>
	</div><!-- E : container -->
</form>

