<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="lms" uri="/WEB-INF/tlds/lms.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now}" pattern="yyyy" var="nowYear"/>
<c:set var="bigoDetail" value="" />
	<h2>활동내역서 작성</h2>
	
<script type="text/javascript">
<!--

$(document).ready(function() {
	com.datepickerDateFormat('activityDate');

});
function fn_search(){
	$("#mm").val("");
	var reqUrl = "/lu/activitydesc/listActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
}
function fn_detail(){ 
	var reqUrl = "/lu/activitydesc/goInsertActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").attr("target","_self");
	$("#frmActivity").submit();	 
}

function fn_selectTraining(){
	popOpenWindow("", "popSearch", 850, 560);
	//var reqUrl = "/lu/traning/popupTraningNote.do";
	var reqUrl = "/lu/activitydesc/popupActivityDesc.do";
	$("#frmActivity").attr("target", "popSearch");
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}

function fn_setWeekTraningCompanyInfo(obj){

	var objArr = obj.split("/");

	$("#subjectCode").val(objArr[0]);
	$("#companyName").val(objArr[1]);
	$("#address").val(objArr[2]);
	$("#traningStDate").val(objArr[3])
	$("#hrdTraningName").val(objArr[4]);

	$("#subjectCodeText").text(objArr[0]);
	$("#companyNameText").text(objArr[1]);
	$("#addressText").text(objArr[2]);
	$("#traningStDateText").text(objArr[3])
	$("#hrdTraningNameText").text(objArr[4]);


}
var cont = 0;
//로우 추가
function fn_addRow(){

	 
	cont+=1; 
	var html ='<tr id=tr'+cont +'>';
	html+='<td class="center-nopadding"><input type="text" name="activityDateArr" id="activityDate'+cont+'" style="width:70%;" /><input type="hidden" name="activityIdArr"   /><input type="hidden" name="activityDetailIdArr"  /></td>';
	html+='<td class="center-nopadding"><input type="text" name="memNameArr" id="memName'+cont+'" style="width:70%;" /></td>';
	html+='<td class="center-nopadding"><input type="text" name="activityContentArr" id="activityContent'+cont+'" style="width:88%;" /></td>';
	html+='<td class="center-nopadding"><input type="text" name="leadTimeArr" id="leadTime'+cont+'" style="width:60%;" /></td>';
	html+='<td class="center"><a href="javascript:fn_addRow();" class="btn-full-blue">추가</a> <a href="javascript:fn_deleteRow(\''+cont+'\');" class="btn-full-gray">삭제</a></td>';
	html+='</tr>'; 

	$("#tableTbody").prepend(html);
	com.datepickerDateFormat('activityDate'+cont);
}
//로우 삭제
function fn_deleteRow(index){
		
	$.ajax({
		  type: "POST",
		  url: "/lu/activitydesc/deleteActivityDesc.do",
		  data: { "activityDetailId": activityDetailId },
		  success:function( html ) {
				 		
		  }
	});
			  
	$("#tr"+index).empty();
}
//로우 삭제
function fn_delete(activityDetailId){
		
	$.ajax({
		  type: "POST",
		  url: "/lu/activitydesc/deleteActivityDesc.do",
		  data: { "activityDetailId": activityDetailId },
		  success:function( html ) {
				 		
		  }
	});
	$("#"+activityDetailId).empty();
}
//등록
function fn_insert(){
	var check = cont+1;
	//alert($("#memName0").val());
	
	if($("#bigo").val()==null || $("#bigo").val()== ""){
		alert("비고를 입력하세요.");
		
		return;
	}
	
	for(var i=0; i < check; i++){


		if($("#activityDate"+i).val() == ""){
			$("#activityDate"+i).focus();
			alert("일자를 입력하세요. ");
			return;
		}
		if($("#memName"+i).val() == ""){
			$("#memName"+i).focus();
			alert("이름을 입력하세요. ");
			return;
		}
		if($("#activityContent"+i).val() == ""){
			$("#activityContent"+i).focus();
			alert("임무수행 내용 입력하세요. ");
			return;
		}
		if($("#leadTime"+i).val() == ""){
			$("#leadTime"+i).focus();
			alert("소요시간을 입력하세요. ");
			return;
		}
	}
	var reqUrl = "/lu/activitydesc/insertActivityDesc.do";
	$("#frmActivity").attr("action", reqUrl);
	$("#frmActivity").submit();
}

//-->
</script>

 
 
	<table class="type-1-1">
		<colgroup>
			<col style="width:250px" />
			<col style="width:*" />
			<col style="width:*" />
			<col style="width:*" />
		</colgroup>
		<tr>
			
			<th>기업명</th>
			<th>소재지</th>
			<th>선정일</th>
			<th>훈련과정명</th>
		</tr>
		<c:if test="${!empty result}">
		<tr>
			<td>${result.companyName }</td>
			<td>${result.address }</td>
			<td>${result.insertDate }</td>
			<td>${result.hrdTraningName }</td>
		</tr>
		</c:if>
		<c:if test="${empty result}">
		<tr>
			<td colspan="4">기업체를 선택하세요</td>
		</tr>								
		</c:if>
	</table>


<form method="post" name="frmActivity" id="frmActivity">
<input type="hidden" name="companyId" id="companyId" value="${result.companyId }"  />
<input type="hidden" name="traningProcessId" id="traningProcessId"  value="${result.traningProcessId }" />
<input type="hidden" id="activityId" name="activityId" value="${activityVO.activityId}" />

		<div class="search-box-1 mt-020  float-left">  
				
			<select id="yyyy" name="yyyy"  onchange="javascript:fn_detail();" > 
					<c:forEach var="i" begin="0" end="5" step="1">
				      <option value="${nowYear-i}" <c:if test="${activityVO.yyyy eq nowYear-i }" > selected="selected"  </c:if>>${nowYear-i}학년도</option>
				    </c:forEach>								
			</select>
			<select name="mm" id="mm" onchange="javascript:fn_detail();">
					<option value="">전체</option>
					<c:forEach var="i" begin="1" end="12" step="1">
				      <option value="${i}" <c:if test="${activityVO.mm eq i }" > selected="selected"  </c:if>  >${i}월</option>
				    </c:forEach>
			</select>
			<select name="activityType" id="activityType" onchange="javascript:fn_detail();">
					<option value="COT"  <c:if test="${activityVO.activityType eq 'COT'}" > selected="selected"  </c:if>>기업현장교사</option>
					<option value="HRD"  <c:if test="${activityVO.activityType eq 'HRD'}" > selected="selected"  </c:if>>HRD담당자</option>
			</select>
				
		</div>
		<table class="type-write mt-020">
			<colgroup>
				<col style="width:150px" />
				<col style="width:110px" />
				<col style="width:*" />
				<col style="width:80px" />
				<col style="width:120px" />
			</colgroup>
			<thead>
				<tr>
					<th>일자</th>
					<th>성명</th>
					<th>업무수행 내용</th>
					<th>소요시간</th>
					<th>추가 / 삭제</th>
				</tr>
			</thead>
			<tbody id="tableTbody">

			 	<c:forEach var="result" items="${resultList}" varStatus="status" >
			 	<c:if test="${result.activityType eq 'HRD' }" >
			 	<c:set var="bigoDetail" value="${result.bigoHrd }" />
			 	</c:if>
			 	<c:if test="${result.activityType eq 'COT' }" >
			 	<c:set var="bigoDetail" value="${result.bigoCot }" />
			 	</c:if>
				<tr id="${result.activityDetailId}" >
					<td class="center-nopadding">
						<input type="text" name="activityDateArr"   style="width:70%;" value="${result.activityDate }" />
						<input type="hidden" name="activityIdArr"   value="${result.activityId }" />
						<input type="hidden" name="activityDetailIdArr"   value="${result.activityDetailId}" />
					</td>
					<td class="center-nopadding"><input type="text" name="memNameArr"   style="width:70%;"  value="${result.memName }" /></td>
					<td class="center-nopadding"><input type="text" name="activityContentArr"   style="width:88%;"  value="${result.activityContent }" /></td>
					<td class="center-nopadding"><input type="text" name="leadTimeArr"  style="width:60%;"  value="${result.leadTime }" /></td>
					<td class="center">
							<a href="javascript:fn_addRow();" class="btn-full-blue">추가</a> 
							<a href="javascript:fn_delete('${result.activityDetailId}');" class="btn-full-gray">삭제</a>
					</td>
				</tr>			 	
			 	</c:forEach>
			 	<c:if test="${empty resultList}">
				<tr>
					<td class="center-nopadding"><input type="text" name="activityDateArr" id="activityDate" style="width:70%;" />
						<input type="hidden" name="activityIdArr"   /><input type="hidden" name="activityDetailIdArr"  />
					</td>
					<td class="center-nopadding"><input type="text" name="memNameArr" id="memName0" style="width:70%;" /></td>
					<td class="center-nopadding"><input type="text" name="activityContentArr" id="activityContent0" style="width:88%;" /></td>
					<td class="center-nopadding"><input type="text" name="leadTimeArr" id="leadTime0" style="width:60%;" /></td>
					<td class="center">
							<a href="javascript:fn_addRow();" class="btn-full-blue">추가</a> 
							<a href="javascript:fn_deleteRow();" class="btn-full-gray">삭제</a>
					</td>
				</tr>
				</c:if>
				<tr id="trRemove">
					<th  colspan="2">비고</th>
					<td><textarea  rows="5" name="bigo" id="bigo">${bigoDetail}</textarea></td>
					<td  colspan="2"></td>
				</tr>
			</tbody>
		</table>
</form>
		<div class="btn-area align-right mt-010">
			<a href="#!" onclick="javascript:fn_search();" class="gray-1  float-left">이전</a>
			<a href="javascript:fn_insert();" class="gray-2 ">저장</a>
		</div>
 
 