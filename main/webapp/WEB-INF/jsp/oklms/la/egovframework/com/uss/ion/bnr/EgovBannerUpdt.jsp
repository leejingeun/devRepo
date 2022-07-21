<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/fms/EgovMultiFile.js'/>"></script>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="banner" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
$(document).ready(function() {
	$('#file_1').on("change", previewImages);
});

function previewImages() {
  var $preview = $('#preview').empty();
  $("#bannerImage").val($('#file_1').val());
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
function fncSelectBannerList() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/la/banner/listBanner.do'/>";
    varFrom.submit();       
}

function fncBannerUpdate() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/la/banner/updateBanner.do'/>";

    if(varFrom.bannerType.value == '') {
		alert("배너종류를 선택해주세요.");
        return;
	}
    
    if(confirm("저장 하시겠습니까?")){
        if(!validateBanner(varFrom)){           
            return;
        }else{
            varFrom.submit();
        } 
    }
}

function fncBannerDelete() {
    var varFrom = document.getElementById("banner");
    varFrom.action = "<c:url value='/la/banner/deleteBanner.do'/>";
    if(confirm("삭제 하시겠습니까?")){
        varFrom.submit();
    }
}

</script>
</head>
<noscript class="noScriptTitle">자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<!--  상단타이틀 -->
<img id="displayBox" src="/js/jquery/plugins/blockUI/busy.gif" width="190" height="60" style="display:none">
<form:form commandName="banner" method="post" action="${pageContext.request.contextPath}/la/banner/updateBanner.do' />" enctype="multipart/form-data"> 
<input type="hidden" name="posblAtchFileNumber" value="1"  >
<table width="100%" cellpadding="8" class="view-1" summary="배너정보를 수정하는 항목">
  <caption>배너 수정</caption>
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="bannerId">배너ID</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap><input name="bannerId" id="bannerId" title="배너ID" type="text" value="<c:out value='${banner.bannerId}'/>" size="30" class="readOnlyClass" readonly></td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="bannerNm">배너명</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap>
        <input name="bannerNm" id="bannerNm" title="배너명" type="text" value="<c:out value='${banner.bannerNm}'/>" maxLength="30" size="30" >&nbsp;<form:errors path="bannerNm" /></td>
  </tr>
   <tr>
    <th class="required_text" width="20%" scope="row"><label for="bannerType">배너 종류</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap>
    	<select name="bannerType" class="selectpicker btn-xs reset" data-width="130px">
				<option value="">-선택-</option>
				<c:forEach items="${ccBannerTypeList}" var="ccBannerType" varStatus="status">
					<option value="${ccBannerType.codeCode}" ${banner.bannerType == ccBannerType.codeCode ? 'selected="selected"' : ''}>${ccBannerType.codeName}</option>
				</c:forEach>
		</select>
    </td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="linkUrl">링크URL</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap><input name="linkUrl" id="linkUrl" title="링크URL" type="text" value="<c:out value='${banner.linkUrl}'/>" maxLength="255" size="50" >&nbsp;<form:errors path="linkUrl" />
    <span color="red">※ [ex:http://www.naver.com]</span>
    </td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="egovComFileUploader">배너이미지</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap>
    
    		<input type="text" id="bannerImage" name="bannerImage" class="file_input_textbox" readonly="readonly"/>
			<div class="file_input_div">
				<input type="button" value="Search files" class="file_input_button"/>
				<input type="file" class="file_input_hidden" id="file_1" name="file_1" />
			</div>
			<div id="preview">
			<img alt="배너 이미지" src='<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${banner.bannerImageFile}"/>'  style="height:100px">
			</div>
			※최대 파일크기는 10.00MB / 이미지 파일(JPG. GIF. PNG.) 형식 지원
    </td>
  </tr>
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="bannerDc">배너설명</label><img src="<c:url value='/images/egovframework/com/cmm/icon/no_required.gif' />" width="15" height="15" alt="선택입력표시"></th>
    <td class="lt_text" nowrap><input name="bannerDc" id="bannerDc" title="배너설명" type="text" value="<c:out value='${banner.bannerDc}'/>" maxLength="100" size="80" ></td>
  </tr> 
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="sortOrdr">정렬순서</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap><input name="sortOrdr" id="sortOrdr" title="정렬순서" type="text" value="<c:out value='${banner.sortOrdr}'/>" maxLength="5" size="10" >&nbsp;<form:errors path="sortOrdr" /></td>
  </tr>    
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="reflctAt">반영여부</label><img src="<c:url value='/images/egovframework/com/cmm/icon/required.gif' />" width="15" height="15" alt="필수입력표시"></th>
    <td class="lt_text" nowrap>
      <select name="reflctAt" id="reflctAt" title="반영여부">
          <option value="Y" <c:if test="${banner.reflctAt == 'Y'}">selected</c:if> >Y</option>
          <option value="N" <c:if test="${banner.reflctAt == 'N'}">selected</c:if> >N</option>
      </select>
   </td> 
  </tr>  
  <tr>
    <th class="required_text" width="20%" scope="row"><label for="regDate">등록일시</label><img src="<c:url value='/images/egovframework/com/cmm/icon/no_required.gif' />" width="15" height="15" alt="선택입력표시"></th>
    <td class="lt_text" nowrap><input name="regDate" id="regDate" title="등록일시" type="text" value="<c:out value="${banner.regDate}"/>" maxLength="50" size="20" class="readOnlyClass" readonly></td>
  </tr>
</table>
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${bannerVO.searchCondition}'/>" >
<input type="hidden" name="searchKeyword" value="<c:out value='${bannerVO.searchKeyword}'/>" >
<input type="hidden" name="pageIndex" value="<c:out value='${bannerVO.pageIndex}'/>" >
</form:form>
<div class="page-btn">
	<a role="button" href="#fn_formSave" onclick="javascript:fncBannerUpdate();return false;" onkeypress="this.onclick;">수정</a>
	<a role="button" href="#fn_formSave" onclick="javascript:fncBannerDelete();return false;" onkeypress="this.onclick;">삭제</a>
	<a href="/la/banner/listBanner.do?pageIndex=<c:out value='${bannerVO.pageIndex}'/>&amp;searchKeyword=<c:out value="${bannerVO.searchKeyword}"/>&amp;searchCondition=1" onclick="fncSelectBannerList(); return false;">목록</a>
</div>	


