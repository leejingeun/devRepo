<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<c:set var="ImgUrl" value="${pageContext.request.contextPath}/images/egovframework/com/cmm/" />
<c:set var="CssUrl" value="${pageContext.request.contextPath}/css/egovframework/com/" />
<script type="text/javascript" src="${contextRootJS }/common/smartEditor/js/HuskyEZCreator.js"></script>
<script type="text/javaScript" language="javascript">
/*********************************************************
 * 초기화
 **********************************************************/
var oEditors = [];
$(document).ready(function() {
	com.datepicker('startDate', 'button');
	com.datepicker('finishDate', 'button');
	
	$("table").find("select[name=startDateTime]").val("${result.startDateTime}").attr("selected","selected");
	$("table").find("select[name=finishDateTime]").val("${result.finishDateTime}").attr("selected","selected");

	if ("${result.contentType}" == "I") {
		$("table").find("tr.trImageUpload").show();
		$("table").find("tr.trContent").hide(500);
		$("table").find("input[name=popupWidth]").removeAttr("validateType");
		$("table").find("input[name=popupHeight]").removeAttr("validateType");
	} else {
		$("table").find("tr.trImageUpload").hide();
		$("table").find("tr.trContent").show();
		$("table").find("input[name=popupWidth]").attr("validateType", "numberminmaxsize");
		$("table").find("input[name=popupHeight]").attr("validateType", "numberminmaxsize");
	}
	
	//컨텐츠 종류 이벤트
	$("table").find("input[name=contentType]").bind("click", function() {
		var contentType = $(this).val();
		if (contentType == "I") {
			$("table").find("tr.trImageUpload").show();
			$("table").find("tr.trContent").hide(500);
			$("table").find("input[name=popupWidth]").removeAttr("validateType");
			$("table").find("input[name=popupHeight]").removeAttr("validateType");
		} else {
			$("table").find("tr.trImageUpload").hide();
			$("table").find("tr.trContent").show();
			$("table").find("input[name=popupWidth]").attr("validateType", "numberminmaxsize");
			$("table").find("input[name=popupHeight]").attr("validateType", "numberminmaxsize");
			//스마트에디트 띄우기 
			$("table").find("tr.trContent").css("height","300px");
			if($("table").find("iframe").css("height") == 0){
				$("table").find("iframe").css("height","300px");	
			}
		}
	});
	$('#file-input').on("change", previewImages);
});

function previewImages() {
  var $preview = $('#preview').empty();
  $("#fileName").val($('#file-input').val());
  var filesize = 0;
  if (this.files) {
	  $.each(this.files, readAndPreview);
  }
  
  function readAndPreview(i, file) {
	if (window.FileReader) { // FireFox, Chrome, Opera 확인.
		if (!/\.(jpe?g|png|gif)$/i.test(file.name)){
		      return alert(file.name +" is not an image");
	    } // else...
	    filesize = filesize + file.size;
		if(filesize > 10000000){ //Checking Sum 10M Size 
			alert("전체 사이즈 10M이상 업로드 할수 없습니다.");
			return false;
		}else{
		    var reader = new FileReader();
		    $(reader).on("load", function() {
		      $preview.append($("<img/>", {src:this.result, height:100}));
		    });
		    reader.readAsDataURL(file);
		}
	}else{ // safari is not supported FileReader
        alert('not supported Webbrowser!!');
    }
  }
}

function fn_formUpdate(){
	if (fn_formValidate() && confirm("수정 하시겠습니까?")) {
		var reqUrl = "/la/popmng/updatePopup.do";
		$("#frmPopup").attr("action", reqUrl);
		$("#frmPopup").submit();
	}
}

function fn_formValidate() {
	if($("#pageType").val() == ""){
		alert("노출위치를 선택해 주세요.");
		return false;
	}
	
	if($("input[id=title]").val() == ""){
		alert("제목을 넣어 주세요.");
		return false;
	}
	
	if($("#startDate").val() == ""){
		alert("게시일 시작일을 넣어 주세요.");
		return false;
	}
	
	if($("#finishDate").val() == ""){
		alert("게시일 종료일을 넣어 주세요.");
		return false;
	}
	
	if(com.diffDate($("#startDate").val(),$("#finishDate").val()) < 0){
		alert("게시일 종료일자가 시작일자보다 이전입니다.\n확인 해주세요.");
		return false;
	}
	
	if(parseInt($("#popupWidth").val(),10) <= 0){
		alert("팝업창 Width 크기 0이상이여야 합니다.");
		return false;
	}
	
	if(parseInt($("#popupHeight").val(),10) <= 0){
		alert("팝업창 Height 크기 0이상이여야 합니다.");
		return false;
	}
	
	var contentType = $("table").find("input[name=contentType]:checked").val();
	if(contentType=="H"){//HTML
		
		var data =oEditors.getById["textAreaContent"].getIR();
		var text = data.replace(/[<][^>]*[>]/gi, "");
		if(text=="" && data.indexOf("img") <= 0){
			alert("내용을 작성해 주세요.");
			return false;
		}
		
		$("#content").val(data);
	}else{//image
		if($("#pageUrl").val()=="http://" || $("#pageUrl").val()==""){
			alert("링크URL 을 넣어 주세요.");
			return false;
		}
	
// 		if($("#file-input").val()=="" ){
// 			alert("이미지 파일을 선택해 주세요.");
// 			return false;
// 		}
	}
	return true;
}
</script>
<!--  상단타이틀 -->
<img id="displayBox" src="/js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">
<form:form commandName="frmPopup" method="post" enctype="multipart/form-data">
				<input type="hidden" id="content" name="content" value=""/>
				<input type="hidden" id="popupId"   name="popupId"  value="${result.popupId}" />
				<input type="hidden" id="imageFileId"   name="imageFileId"  value="${result.imageFileId}" />
				
				<table border="0" cellpadding="0" cellspacing="0" class="view-1" style="margin:0;">
					<tbody>
						<tr><th>노출위치<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">	
							<select name="pageType" id="pageType" class="selectpicker btn-xs reset" data-width="130px">
								<option value="">-선택-</option>
								<c:forEach items="${ccFocusList}" var="ccInfo" varStatus="status">
									<option value="${ccInfo.codeCode}" ${result.pageType == ccInfo.codeCode ? 'selected="selected"' : ''}>${ccInfo.codeName}</option>
								</c:forEach>
							</select>
						</td>	</tr>
						<tr>	<th>제목<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3"><input type="text" id="title"  name="title"	 style="width:100%;" value="${result.title}" /></td>	</tr>
						<tr>	<th>게시일<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
						<input type="text" id="startDate" name="startDate" value="${result.startDate}" style="width: 70px;" readonly="readonly" />
						<select name="startDateTime" class="selectpicker btn-xs" data-width="80px">
			                	<option value="000000">00:00</option>
			                	<option value="003000">00:30</option>
			                	<option value="010000">01:00</option>
			                	<option value="013000">01:30</option>
			                	<option value="020000">02:00</option>
			                	<option value="023000">02:30</option>
			                	<option value="030000">03:00</option>
			                	<option value="033000">03:30</option>
			                	<option value="040000">04:00</option>
			                	<option value="043000">04:30</option>
			                	<option value="050000">05:00</option>
			                	<option value="053000">05:30</option>
			                	<option value="060000">06:00</option>
			                	<option value="063000">06:30</option>
			                	<option value="070000">07:00</option>
			                	<option value="073000">07:30</option>
			                	<option value="080000">08:00</option>
			                	<option value="083000">08:30</option>
			                	<option value="090000">09:00</option>
			                	<option value="093000">09:30</option>
			                	<option value="100000">10:00</option>
			                	<option value="103000">10:30</option>
			                	<option value="110000">11:00</option>
			                	<option value="113000">11:30</option>
			                	<option value="120000">12:00</option>
			                	<option value="123000">12:30</option>
			                	<option value="130000">13:00</option>
			                	<option value="133000">13:30</option>
			                	<option value="140000">14:00</option>
			                	<option value="143000">14:30</option>
			                	<option value="150000">15:00</option>
			                	<option value="153000">15:30</option>
			                	<option value="160000">16:00</option>
			                	<option value="163000">16:30</option>
			                	<option value="170000">17:00</option>
			                	<option value="173000">17:30</option>
			                	<option value="180000">18:00</option>
			                	<option value="183000">18:30</option>
			                	<option value="190000">19:00</option>
			                	<option value="193000">19:30</option>
			                	<option value="200000">20:00</option>
			                	<option value="203000">20:30</option>
			                	<option value="210000">21:00</option>
			                	<option value="213000">21:30</option>
			                	<option value="220000">22:00</option>
			                	<option value="223000">22:30</option>
			                	<option value="230000">23:00</option>
			                	<option value="233000">23:30</option>
			                	</select> ~ 
						<input type="text"  id="finishDate" name="finishDate" value="${result.finishDate}" style="width: 70px;" readonly="readonly" />
						 <select name="finishDateTime" class="selectpicker btn-xs" data-width="80px">
			                	<option value="000000">00:00</option>
			                	<option value="003000">00:30</option>
			                	<option value="010000">01:00</option>
			                	<option value="013000">01:30</option>
			                	<option value="020000">02:00</option>
			                	<option value="023000">02:30</option>
			                	<option value="030000">03:00</option>
			                	<option value="033000">03:30</option>
			                	<option value="040000">04:00</option>
			                	<option value="043000">04:30</option>
			                	<option value="050000">05:00</option>
			                	<option value="053000">05:30</option>
			                	<option value="060000">06:00</option>
			                	<option value="063000">06:30</option>
			                	<option value="070000">07:00</option>
			                	<option value="073000">07:30</option>
			                	<option value="080000">08:00</option>
			                	<option value="083000">08:30</option>
			                	<option value="090000">09:00</option>
			                	<option value="093000">09:30</option>
			                	<option value="100000">10:00</option>
			                	<option value="103000">10:30</option>
			                	<option value="110000">11:00</option>
			                	<option value="113000">11:30</option>
			                	<option value="120000">12:00</option>
			                	<option value="123000">12:30</option>
			                	<option value="130000">13:00</option>
			                	<option value="133000">13:30</option>
			                	<option value="140000">14:00</option>
			                	<option value="143000">14:30</option>
			                	<option value="150000">15:00</option>
			                	<option value="153000">15:30</option>
			                	<option value="160000">16:00</option>
			                	<option value="163000">16:30</option>
			                	<option value="170000">17:00</option>
			                	<option value="173000">17:30</option>
			                	<option value="180000">18:00</option>
			                	<option value="183000">18:30</option>
			                	<option value="190000">19:00</option>
			                	<option value="193000">19:30</option>
			                	<option value="200000">20:00</option>
			                	<option value="203000">20:30</option>
			                	<option value="210000">21:00</option>
			                	<option value="213000">21:30</option>
			                	<option value="220000">22:00</option>
			                	<option value="223000">22:30</option>
			                	<option value="230000">23:00</option>
			                	<option value="233000">23:30</option>
			                	<option value="235900">23:59</option>
			                </select>		
						</td>	</tr>
						<tr>	<th>팝업형식<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
						<input type="radio" id="popupType1" name="popupType" value="L" <c:if test="${result.popupType == 'L'}">checked</c:if> /><label for="popupType1">layer</label>
						<input type="radio" id="popupType2" name="popupType" value="W" <c:if test="${result.popupType == 'W'}">checked</c:if> /><label for="popupType2">windows</label>
						</td>	</tr>
						<tr>	<th>그만보기설정<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
<%-- 						<input type="text" id="frm_codeNameEng"	name="codeNameEng"		value="${comcodeVO.codeNameEng}"  style="width:100%;" /> --%>
						<input type="radio" id="isCloseViewSettings1" name="isCloseViewSettings" value="D" <c:if test="${result.isCloseViewSettings == 'D'}">checked</c:if> /><label for="isCloseViewSettings1">하루동안 그만보기</label>
		        		<input type="radio" id="isCloseViewSettings2" name="isCloseViewSettings" value="W" <c:if test="${result.isCloseViewSettings == 'W'}">checked</c:if> /><label for="isCloseViewSettings2">일주일간 그만보기</label>		
						</td>	</tr>
						<tr>	<th>컨텐츠종류<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
						<input type="radio" id="contentType1" name="contentType" value="H" <c:if test="${result.contentType == 'H'}">checked</c:if> /><label for="contentType1">HTML</label>
						<input type="radio" id="contentType2" name="contentType" value="I" <c:if test="${result.contentType == 'I'}">checked</c:if> /><label for="contentType2">이미지 업로드</label>
					    </td>	</tr>
						<tr class="trPopupSize">	<th>팝업창 크기<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>	<td colspan="3">
						가로&nbsp;<input name="popupWidth" id="popupWidth" class="form-control inline wd60 numric" min="350" max="1000" validateMsg="팝업 창 크기는(은) {1} ~ {2} 사이로 입력해 주십시오." maxlength="4" value="${result.popupWidth}" />&nbsp;px
						&nbsp;&nbsp;&nbsp;&nbsp;
						세로&nbsp;<input name="popupHeight" id="popupHeight" class="form-control inline wd60 numric" min="100" max="1000" validateMsg="팝업 창 크기는(은) {1} ~ {2} 사이로 입력해 주십시오." maxlength="4" value="${result.popupHeight}" />&nbsp;px
						</br>※팝업창의 크기를 설정하여 주십시오.
						</td>	</tr>
						<tr>	<th>팝업위치</th>		<td colspan="3">
						Top&nbsp;<input name="positionTop" class="form-control inline wd60 numric" validateType="required" validateMsg="tit.popupPositionType 항목은 필수 입력 항목입니다." maxlength="4" value="${result.positionTop}" />&nbsp;px
						&nbsp;&nbsp;&nbsp;&nbsp;
						Left&nbsp;<input name="positionLeft" class="form-control inline wd60 numric" validateType="required" validateMsg="tit.popupPositionType 항목은 필수 입력 항목입니다." maxlength="4" value="${result.positionLeft}"/>&nbsp;px
						</br>※팝업 창 위치를 설정하여 주십시오. 미 입력 시 사용자 화면의 중앙에 노출됩니다.
						</td>	</tr>
						<tr class="trImageUpload">	<th>링크 URL<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
							<input id="pageUrl" name="pageUrl" class="form-control inline wd400" maxlength="255" value="${result.pageUrl}"/>
							</br>※이미지 클릭 시 이동할 URL을 입력하여 주십시오.
						</td>	</tr>
						<tr class="trImageUpload">	<th>이미지 업로드<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
								<input type="text" id="fileName" class="file_input_textbox" readonly="readonly"/>
								<div class="file_input_div">
									<input type="button" value="Search files" class="file_input_button"/>
									<input type="file" class="file_input_hidden" id="file-input" name="file-input" />
<!-- 									multiple -->
								</div>
								<div id="preview">
									<c:if test="${!empty result.imageFileId}">
									<img src="/commbiz/atchfle/atchFileDown.do?importAtchFileId=${result.imageFileId}&importFleSn=1" style="height:100px"/>
									</c:if>
								</div>
								※최대 파일크기는 10.00MB / 이미지 파일(JPG. GIF. PNG.) 형식 지원
						</td>	</tr>
						<tr class="trContent">	<th>내용<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
							<textarea name="textAreaContent" id="textAreaContent" style="width: 100%;height: 500px;" rows="20" cols="80">${result.content}</textarea>
						</td>	</tr>
						<tr>	<th>사용여부<img src="${ImgUrl}icon/required.gif" alt="필수입력" title="필수입력" width="15" height="15"></th>		<td colspan="3">
						<input type="radio" id="isUse1" name="isUse" value="Y" <c:if test="${result.isUse == 'Y'}">checked</c:if> /><label for="isUse1">예</label>
		        		<input type="radio" id="isUse2" name="isUse" value="N" <c:if test="${result.isUse == 'N'}">checked</c:if> /><label for="isUse2">아니오</label>	
						</td>	</tr>
					</tbody>
				</table><!-- E : view-1 -->
</form:form>
<div class="page-btn">
	<a role="button" href="#fn_formSave" onclick="javascript:fn_formUpdate();return false;" onkeypress="this.onclick;">수정</a><a href="/la/popmng/listPopup.do">목록</a>
</div>	
<script type="text/javaScript" language="javascript">
<!-- Smart Editor -->
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "textAreaContent",
	sSkinURI: "/common/smartEditor/SmartEditor2Skin.html",	
	htParams : {bUseToolbar : true,
		fOnBeforeUnload : function(){
			//alert("아싸!");	
		}
	}, //boolean
	fOnAppLoad : function(){
		//예제 코드
		//oEditors.getById["textAreaContent"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
	},
	fCreator: "createSEditor2"
});
</script>