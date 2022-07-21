<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<c:set var="targetUrl" value="/lu/discuss/"/>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<script type="text/javascript">

	var targetUrl = "${targetUrl}";

	$(document).ready(function() {
		$('#content').change(function() {
			var content = $(this).val();
			if(Check_nonTag(content) == false){
				  alert("HTML 태그는 입력하실 수 없습니다! 예)< > 기호...");
				  $("#content").val('');
				  $("#content").focus();
				  return false;
			}
		});
	});


	/*====================================================================
	사용자 정의 함수
	====================================================================*/

	/* HTML 태그 검사 */
	function Check_nonTag(text){
	 var opentag = '><';
	 var i ;

	 for ( i=0; i < text.length; i++ ){
	  if( opentag.indexOf(text.substring(i,i+1)) > 0){
	   break ;
	  }
	 }

	 if ( i != text.length ){
	  return false;
	 }else{
	  return true ;
	 }

	 return false;
	}

	/* 토론 목록 페이지로 이동 */
	function fn_list(){
		/* var reqUrl = CONTEXT_ROOT + targetUrl + "listDiscuss.do";

		$("#discussId").val('');
		$("#discussOpinionId").val('');

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();	 */

		history.back(-2);
	}

	/* 토론의견 수정화면 이동 */
	function fn_updt(){

		var reqUrl = CONTEXT_ROOT + targetUrl + "goUpdateDiscussOpinion.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론의견 삭제 */
	function fn_delt(){
		if( confirm("생성된 토론의견을 삭제하겠습니까?") ) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteDiscussOpinion.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견을 추천 업데이트 */
	function fn_goodUpdt(){

		if( confirm("생성된 토론의견을 추천하겠습니까?") ) {
			var reqUrl = CONTEXT_ROOT + targetUrl + "updateDiscussGoodHist.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견 댓글상세 */
	function fn_comment_read( param1 ){

		$("#discussCommentId").val(param1);

		var reqUrl = CONTEXT_ROOT + targetUrl + "getDiscussOpinion.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}

	/* 토론의견 댓글신규 저장 */
	function fn_comment_save( ){
		if( confirm("토론의견에 입력한 댓글을 저장하겠습니까?") ) {

			if($("#content").val() == ""){
				alert("댓글에 내용을 입력 주세요.");
				$("#content").focus();
				return false;
			}

			var reqUrl = CONTEXT_ROOT + targetUrl + "insertDiscussOpinionComment.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견 댓글수정 */
	function fn_comment_updt( ){
		if( confirm("생성된 토론의견에 댓글을 수정하겠습니까?") ) {

			var discussCommentId = $("#discussCommentIdArr").val();
			$("#discussCommentId").val(discussCommentId);

			if($("#content").val() == ""){
				alert("댓글에 내용을 입력 주세요.");
				$("#content").focus();
				return false;
			}

			var reqUrl = CONTEXT_ROOT + targetUrl + "updateDiscussOpinionComment.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견 댓글삭제 */
	function fn_comment_delt( param1 ){
		if( confirm("생성된 토론의견에 댓글을 삭제하겠습니까?") ) {

			$("#discussCommentId").val(param1);

			var reqUrl = CONTEXT_ROOT + targetUrl + "deleteDiscussOpinionComment.do";

			$("#frmDiscuss").attr("method", "post" );
			$("#frmDiscuss").attr("action", reqUrl);
			$("#frmDiscuss").submit();
		}
	}

	/* 토론의견 댓글 초기화 */
	function fn_comment_reset( ){
		$("#memSeq").val('');

		var reqUrl = CONTEXT_ROOT + targetUrl + "getDiscussOpinion.do";

		$("#frmDiscuss").attr("method", "post" );
		$("#frmDiscuss").attr("action", reqUrl);
		$("#frmDiscuss").submit();
	}



</script>

<form id="frmDiscuss" name="frmDiscuss" method="post">
<input type="hidden" id="yyyy" name="yyyy" value="${discussVO.yyyy}" />
<input type="hidden" id="term" name="term" value="${discussVO.term}" />
<input type="hidden" id="subjectCode" name="subjectCode" value="${discussVO.subjectCode}" />
<input type="hidden" id="subClass" name="subClass" value="${discussVO.subClass}" />
<input type="hidden" id="discussId" name="discussId" value="${discussVO.discussId}" />
<input type="hidden" id="discussOpinionId" name="discussOpinionId" value="${discussVO.discussOpinionId}" />
<input type="hidden" id="memSeq" name="memSeq" />
<input type="hidden" id="discussCommentId" name="discussCommentId" />

<div id="">
<h2>토론</h2>
<h4 class="mb-010"><span><c:if test="${currProcReadVO.onlineTypeName eq '온라인'}">[ONLINE]</c:if>${currProcReadVO.subjectName}</span> ㅣ ${currProcReadVO.yyyy}학년도 / ${currProcReadVO.term}학기</h4>

<table class="type-1 mb-030">
	<colgroup>
		<col style="width:15%" />
		<col style="width:*px" />
		<col style="width:15%" />
		<col style="width:*px" />
		<col style="width:15%" />
		<col style="width:*px" />
	</colgroup>
	<tbody>
		<tr>
			<th>교과형태</th>
		<td>${currProcReadVO.subjectTraningTypeName}</td>
			<th>과정구분</th>
		<td>${currProcReadVO.subjectTypeName}</td>
			<th>학점</th>
			<td>${currProcReadVO.point }학점</td>
		</tr>
		<tr>
			<th>교수</th>
			<td>${currProcReadVO.insNames}</td>
			<th>온라인 교육형태</th>
			<td>${currProcReadVO.onlineTypeName} (성적비율 ${currProcReadVO.gradeRatio}%)</td>
			<th>선수여부</th>
			<td>${currProcReadVO.firstStudyYn eq 'Y' ? '필수' : '필수X'}</td>
		</tr>
	</tbody>
</table>

<h2>토론 조회</h2>
<table class="type-write mb-010">
	<colgroup>
		<col style="width:110px" />
		<col style="width:*" />
	</colgroup>
	<tbody>
		<tr>
			<th>제목</th>
			<td>${discussReadVO.title}</td>
		</tr>
		<tr>
			<th>공개일</th>
			<td>${discussReadVO.startDate} ${discussReadVO.startHour}:${discussReadVO.startMin}</td>
		</tr>
		<tr>
			<th>마김일</th>
			<td>${discussReadVO.endDate} ${discussReadVO.endHour}:${discussReadVO.endMin}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${discussReadVO.content}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td class="left">
				<c:if test="${!empty resultFile1.atchFileId }">
					<a href="javascript:com.downFile('${resultFile1.atchFileId}','${resultFile1.fileSn}');" class="text-file">${resultFile1.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</td>
		</tr>
	</tbody>
</table>

<h2>토론 의견조회</h2>
<table class="type-2 mb-040">
	<colgroup>
		<col style="width:50px" />
		<col style="width:*" />
		<col style="width:140px" />
		<col style="width:140px" />
		<col style="width:200px" />
		<%-- <col style="width:50px" />
		<col style="width:50px" /> --%>
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>첨부파일</th>
			<!-- <th>추천</th>
			<th>조회수</th> -->
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan="2">1</td>
			<td class="left">
				${discussOpinionReadVO.title} <c:if test="${discussOpinionReadVO.commentCnt > 0}"> [${discussOpinionReadVO.commentCnt}] </c:if>
			</td>
			<td>${discussOpinionReadVO.memName}</td>
			<td>${discussOpinionReadVO.insertDate}</td>
			<td class="left">
				<c:if test="${!empty resultFile2.atchFileId }">
					<a href="javascript:com.downFile('${resultFile2.atchFileId}','${resultFile2.fileSn}');" class="text-file">${resultFile2.orgFileName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
				</c:if>
			</td>
			<%-- <td>${discussOpinionReadVO.goodCnt}</td>
			<td>${discussOpinionReadVO.hitCnt}</td> --%>
		</tr>
		<tr>
			<td colspan="5" class="border-left left">${discussOpinionReadVO.content}</td>
		</tr>
	</tbody>
</table><!-- E : 의견 -->

<div class="btn-area align-right">
	<a href="#fn_list" onclick="javascript:fn_list(); return false" class="gray-1 float-left">취소</a>
	<c:if test="${discussVO.sessionMemType eq 'STD'}">
		<c:if test="${discussVO.sessionMemSeq eq discussOpinionReadVO.memSeq}">
		<a href="#fn_delt" onclick="javascript:fn_delt(); return false" class="gray-1">삭제</a>
		<a href="#fn_updt" onclick="javascript:fn_updt(); return false" class="orange">수정</a>
		</c:if>
		<a href="#fn_goodUpdt" onclick="javascript:fn_goodUpdt(); return false" class="yellow">추천</a>
	</c:if>
</div><!-- E : btn-area -->

<!-- 목록 버튼 및 덧글 제목 사이에 공간을 주기위해 table 태그 추가함. -->
<table border="0">
<tr>
	<td>&nbsp;</td>
</tr>
</table>

<dl class="comment-area">
	<dt>덧글 <span>( ${commentCnt} )</span></dt>
	<dd class="text-area mb-010 align-right">
		<textarea id="content" name="content" rows="4" placeholder="작성된 내용이 없습니다."><c:if test="${commentContent != null}">${commentContent}</c:if></textarea>
		<a href="#fn_comment_reset" onclick="javascript:fn_comment_reset(); return false" class="gray-1">초기화</a>
		<c:if test="${commentContent == null}"><a href="#fn_comment_save" onclick="javascript:fn_comment_save(); return false" class="">저장</a></c:if>
		<c:if test="${commentContent != null}"><a href="#fn_comment_updt" onclick="javascript:fn_comment_updt(); return false" class="">수정</a></c:if>
	</dd>

	<c:forEach var="result" items="${resultDiscussOpinionCommentList}" varStatus="status">
	<dd>
		<b>${result.memName}</b> [ ${result.insertDate} ]
		<c:if test="${result.memSeq eq discussVO.sessionMemSeq}">
		<span>
			<a href="#fn_comment_read" onclick="javascript:fn_comment_read('${result.discussCommentId}'); return false" class="btn-view">상세보기</a>
			<a href="#fn_comment_delt" onclick="javascript:fn_comment_delt('${result.discussCommentId}'); return false" class="btn-del">삭제</a>
			<input type="hidden" id="discussCommentIdArr" name="discussCommentIdArr" value="${result.discussCommentId}" />
		</span>
		</c:if>
		<p>${result.content}</p>
	</dd>
	</c:forEach>

</dl><!-- E : 댓글 -->

</div><!-- E : content-area -->


</form>

