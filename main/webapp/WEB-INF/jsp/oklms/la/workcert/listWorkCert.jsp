<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ page import="kr.co.sitglobal.oklms.comm.util.Config" %>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<c:set var="targetUrl" value="/lu/workcert/"/>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	
	$(document).on('click', '#openFileView1', function(){	
		$("#file1").click();
	});
	
	function changeValue1(obj){
        alert(obj.value);
        $("#fileAdd1").html(obj.value);
    }
	
	$(document).on('click', '#openFileView2', function(){	
		$("#file2").click();
	});
	
	function changeValue2(obj){
        alert(obj.value);
        $("#fileAdd2").html(obj.value);
    }
	
	$(document).on('click', '#openFileView3', function(){	
		$("#file3").click();
	});
	
	function changeValue3(obj){
        alert(obj.value);
        $("#fileAdd3").html(obj.value);
    }
	
	//tbody에 입력폼 생성
	function fn_work_add(){
		var htmlStr = "";
		var listCnt = "${resultCnt}";
		
		if($("#htmlAdd tr").length == 1){
			alert("제출 후 추가해 주세요.");
			return false;
			//alert("제출 건이 존재합니다. 제출 후 추가해 주세요.");
		}else {
			var d = new Date();
			var yyyy = d.getFullYear();
			var yyyyNext = "";
			
			if(listCnt == '0'){
				$("#disHide").html("");
			}
			yyyy = Number(yyyy);
			yyyyNext = yyyy+1;
			
			var n = $("#addcnt").val();
			add(n);
			var adcnt = n;
			
			htmlStr += "<tr id='"+adcnt+"'>";
			htmlStr += "<td>";
			htmlStr += "<select id='yyyy' name='yyyy'>";
			htmlStr += "<option value='"+yyyy+"'>"+yyyy+"학년도</option>";
			htmlStr += "<option value='"+yyyyNext+"'>"+yyyyNext+"학년도</option>";
			htmlStr += "</select>";
			htmlStr += "</td>";
			htmlStr += "<td>";
			htmlStr += "<select id='term' name='term' >";
			htmlStr += "<option value='1'>1학기</option>";
			htmlStr += "<option value='2'>2학기</option>";
			htmlStr += "</select>";
			htmlStr += "</td>";
			htmlStr += "<td>";
			htmlStr += "<img src='/images/oklms/std/icon_attach.png'><a href='#' id='fileAdd1' name='fileAdd1'></a>";
			htmlStr += "<input type='button' id='openFileView1' name='openFileView1' class='table_open' value='파일등록' title='jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.' />";
			htmlStr += "<input type='file' id='file1' name='file1' onchange='changeValue1(this);' style='display:none;'/>";
			htmlStr += "</td>";
			htmlStr += "<td>";
			htmlStr += "<img src='/images/oklms/std/icon_attach.png'><a href='#' id='fileAdd2' name='fileAdd2'></a>";
			htmlStr += "<input type='button' id='openFileView2' name='openFileView2' class='table_open' value='파일등록' title='jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.' />";
			htmlStr += "<input type='file' id='file2' name='file2' onchange='changeValue2(this);' style='display:none;'/>";
			htmlStr += "</td>";
			htmlStr += "<td>";
			htmlStr += "<img src='/images/oklms/std/icon_attach.png'><a href='#' id='fileAdd3' name='fileAdd3'></a>";
			htmlStr += "<input type='button' id='openFileView3' name='openFileView3' class='table_open' value='파일등록' title='jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.' />";
			htmlStr += "<input type='file' id='file3' name='file3' onchange='changeValue3(this);' style='display:none;'/>";
			htmlStr += "</td>";
			//htmlStr += "<td><input class='table_reg' name='file2' id='file2' type='file' title='파일등록'/></td>";
			//htmlStr += "<td><input class='table_reg' name='file3' id='file3' type='file' title='파일등록'/></td>";
			//htmlStr += "<td><input type='button' class='table_reg' value='파일등록' /></td>";
			htmlStr += "<td><input type='button' class='table_btn_orenge' onClick='javascript:fn_work_regist();return false;' value='제출' /></td>";
			htmlStr += "</tr>";
		
			$("#htmlAdd").append(htmlStr);
		}
		$("#openFileView1").poshytip();
		$("#openFileView2").poshytip();
		$("#openFileView3").poshytip();
	}

	function add( num ) {
		var cnt = num;
		cnt++;
		$("#addcnt").val( cnt );
		return cnt;
	}
	
	//제출 클릭시 insert 이벤트.
	function fn_work_regist(){
		
		var listCnt = "${resultCnt}";
		var yyyy = $("#yyyy option:selected").val();
		var term = $("#term option:selected").val();
		
		for(var i=1; i <= listCnt; i++){
			if(yyyy == $("tr").eq(i).find("td").eq(0).html() && term == $("tr").eq(i).find("td").eq(1).html()){
				alert("이미 등록된 학년도 입니다.");
				return false;
			}
			/* if(term == $("tr").eq(i).find("td").eq(1).html()){
				alert("이미 등록된 학기 입니다.");
				return false;
			} */
		}
		
		if($("#file1").val() == ""){
			alert("원천징수영수증 파일을 등록해주세요.");
			return false;
		}
		
		if($("#file2").val() == ""){
			alert("4대보험확인서 파일을 등록해주세요.");
			return false;
		}
		
		if($("#file3").val() == ""){
			alert("재직증명서 파일을 등록해주세요.");
			return false;
		}
		
		if( $("#file1").val() != "" ){
			var ext = $('#file1').val().split('.').pop().toLowerCase();
		    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			alert('jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.');
			return;
		    }
		}
		
		if( $("#file2").val() != "" ){
			var ext = $('#file2').val().split('.').pop().toLowerCase();
		    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			alert('jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.');
			return;
		    }
		}
		
		if( $("#file3").val() != "" ){
			var ext = $('#file3').val().split('.').pop().toLowerCase();
		    if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
			alert('jpg,jpeg,gif,png 파일만 업로드 할수 있습니다.');
			return;
		    }
		}
		
		var fileSize =  document.frmWork.file1.files[0].size;
		var maxSize  = 10485760    //10MB
		
	    	if(fileSize > maxSize)
	        {
	            alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.    ");
	            return;
	        }
	        
		var reqUrl = CONTEXT_ROOT + targetUrl + "insertWorkCertArticle.do";

		$("#frmWork").attr("method", "post" );
		$("#frmWork").attr("action", reqUrl);
		$("#frmWork").submit();
		
	}
	
</script>

<form name="frmWork" id="frmWork" method="post" enctype="multipart/form-data" >
<input type="hidden" name="memId" id="memId" value="${memId}"/>
<input type="hidden" name="memName" id="memName" value="${memName}"/>

<div class="content">
	<h1>재직증빙서류 제출</h1>
		<table class="table" id="rptbl">
			<colgroup>
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
				<col style="width:*" />
			</colgroup>
	
			<tr>
				<th>학년도</th>
				<th>학기</th>
				<th>원천징수영수증</th>
				<th>4대보험확인서</th>
				<th>재직증명서</th>
				<th class="none">확인</th>
			</tr>
			
			<c:forEach var="result" items="${resultList}" varStatus="status">
				<tr>
					<td><c:out value="${result.yyyy}" /></td>
					<td><c:out value="${result.term}" /></td>
					<td>제출</td>
					<td>제출</td>
					<td>제출</td>
					<td>제출완료</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(resultList) == 0}">
				<tr id="disHide">
					<td class="none" colspan="6">등록된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		
			<tbody id="htmlAdd"></tbody>
		</table>
		
		<div class="btn_right"><input type="hidden" id="addcnt" name="addcnt" value="1"/><input type="button" class="black"  onClick="javascript:fn_work_add();" value="추가" /></div>
</div>
</form>
