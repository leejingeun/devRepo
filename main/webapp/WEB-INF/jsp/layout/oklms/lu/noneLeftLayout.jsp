<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
	<head>
		<title><tiles:insertAttribute name="title" /></title>
		<link rel="stylesheet" type="text/css" href="/css/oklms/font.css" />
		<script type="text/javascript" src="/js/oklms/webfont.js"></script>
		<script type="text/javascript">
			WebFont.load({
				custom: {
					families: ['nsM' , 'play' , 'nbgM',]
				}
			});
		</script>

		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi" /> -->
		<meta content="IE=edge" http-equiv="X-UA-Compatible">
		
		<script type="text/javascript">
			var CONTEXT_ROOT = "<%= request.getContextPath() %>";
		</script>
		
		<%@include file="/includeCss.jsp"%>
		<link href= "<%= request.getContextPath() %>/css/oklms/usr_default.css" rel="stylesheet" type="text/css" />
		
		<%@include file="/includeStdJs.jsp"%>
		<%-- <script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/bootstrap.min.js"></script> --%>
		<script type="text/javascript" src="<%= request.getContextPath() %>/js/oklms/jquery-common.js"></script>
		
		<script type="text/javascript">
		
			//Controller에서  전달된 메시지를 출력한다.
			$(document).ready(function(){
		
				if(""!="${retMsgEncode}"){
					alert(decodeURI('${retMsgEncode}') );
				}else if ("" != "${retMsg}") {
					alert("${retMsg}");
				}else if ("" != "${returnResultMap.retMsg}") {
					alert("${returnResultMap.retMsg}");
				}
			});
		</script>
	</head>
<body>
</script>
	</head>
<body>
<!-- ############### LU nonLeftLayout ############### -->
<!-- 		<ul id="skip"> -->
<!-- 			<li><a href="#container">메인메뉴 건너뛰고 본문으로 이동</a></li> -->
<!-- 			<li><a href="#footer">메인메뉴, 본문 건너뛰고 푸터메뉴로 이동</a></li> -->
<!-- 		</ul>E : skip -->
		<div id="wrapper" class="sub-bg">
			<!-- S : top-area --><tiles:insertAttribute name="header" /><!-- E : top-area -->
			<ul id="container">
			<!-- S : top-area --><tiles:insertAttribute name="searchAndLogin" /><!-- E : searchAndLogin -->
				<li id="body-wrap">
<%-- 					<!-- S : menu-area --><tiles:insertAttribute name="left" /><!-- E : menu-area --> --%>
					<div id="content-wrap">
<%-- 						<!-- S : body location --><tiles:insertAttribute name="bodyLocation" /><!-- E : body location --> --%>
						<div id="content-area">
							<!-- S : body --><tiles:insertAttribute name="body" /><!-- E : body -->
<%--
							<ul class="search1">
								<li class="sbox1">
									<label for="bselect-1">- 구분 : </label>
									<select name="select4" id="bselect-1" style="width:60px;">
										<option selected="selected">전체</option>
										<option>On-Line</option>
										<option>Off-Line</option>
										<option>Blended</option>
									</select>

									<label for="bselect-2">- 상태 : </label>
									<select name="select4" id="bselect-2" style="width:60px;">
										<option selected="selected">전체</option>
										<option>제목</option>
										<option>내용</option>
									</select>

									<label for="bselect-3"></label>
									<select name="select4" id="bselect-3" style="width:70px;">
										<option selected="selected">과정명</option>
									</select>

									<label for="bsearch"></label>
									<input type="text" name="textfield2" id="bsearch" style="width:120px;" title="검색어를 입력해 주세요." />
									<a href="#!">검색</a>
								</li>
							</ul>
							<!--  E : search1 -->

							<table class="list course-1">
								<caption>과정목록</caption>
								<thead>
									<tr>
										<th class="col-1">분류</th>
										<th class="col-2">과정명</th>
										<th class="col-3">기간</th>
										<th class="col-4">상태</th>
										<th class="col-5">미리보기</th>
										<th class="col-6">신청</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="col-1">한국어교육지도사</td>
										<td class="subject"><a href="course_view.html">미디어 이러닝 시스템 구축 사업 기출문제해설</a></td>
										<td class="col-3">2014.05.29~2014.06.12</td>
										<td class="col-4">진행</td>
										<td class="col-5"><a href="#"><img src="../..//images/oklms/std/btn/course_btn_03.png" alt="새창 - 과정 미리보기." /></a></td>
										<td class="col-6"><a href="#" class="btn-1">신청</a></td>
									</tr>
									<tr>
										<td class="col-1">가족생활지도사</td>
										<td class="subject"><a href="#">교사를 위한 저작권 실무사례</a></td>
										<td class="col-3">2014.05.29~2014.06.12</td>
										<td class="col-4">진행</td>
										<td class="col-5"><a href="#"><img src="../..//images/oklms/std/btn/course_btn_03.png" alt="새창 - 과정 미리보기." /></a></td>
										<td class="col-6"><a href="#" class="btn-1">신청</a></td>
										</tr>
									<tr>
										<td class="col-1">언어발달지도사</td>
										<td class="subject"><a href="#">미디어 이러닝 시스템 구축 사업</a></td>
										<td class="col-3">2014.05.29~2014.06.12</td>
										<td class="col-4">진행</td>
										<td class="col-5"><a href="#"><img src="../..//images/oklms/std/btn/course_btn_03.png" alt="새창 - 과정 미리보기." /></a></td>
										<td class="col-6"><a href="#" class="btn-1">신청</a></td>
									</tr>
									<tr>
										<td class="col-1">통번역전담인력</td>
										<td class="subject"><a href="#">이러닝 요소기술 특허등록</a></td>
										<td class="col-3">2014.05.29~2014.06.12</td>
										<td class="col-4">진행</td>
										<td class="col-5"><a href="#"><img src="../..//images/oklms/std/btn/course_btn_03.png" alt="새창 - 과정 미리보기." /></a></td>
										<td class="col-6"><a href="#" class="btn-1">신청</a></td>
										</tr>
									<tr>
										<td class="col-1">한국어교육지도사</td>
										<td class="subject"><a href="#">교사를 위한 저작권 실무사례</a></td>
										<td class="col-3">2014.05.29~2014.06.12</td>
										<td class="col-4">진행</td>
										<td class="col-5"><a href="#"><img src="../..//images/oklms/std/btn/course_btn_03.png" alt="새창 - 과정 미리보기." /></a></td>
										<td class="col-6"><a href="#" class="btn-1">신청</a></td>
									</tr>
								</tbody>
							</table>
							<!--  E : list -->

							<div class="page-num">
								<ul>
									<li class="page">.
										<a href="#!" class="page-btn1">처음 페이지</a>
										<a href="#!" class="page-btn2">이전 페이지</a>
										<a href="#!" class="num">1</a>
										<a href="#!" class="num ing">2</a>
										<a href="#!" class="num">3</a>
										<a href="#!" class="num">4</a>
										<a href="#!" class="num">5</a>
										<a href="#!" class="page-btn3">다음 페이지</a>
										<a href="#!" class="page-btn4">마지막 페이지</a>
									</li>
								</ul>
							</div><!-- E : page-num -->
 --%>							
						</div><!-- E : content-area -->
						<dl id="quick-wrap">
							<dt>바로가기</dt>
							<dd class="btn-type-1"><a href="">수강신청</a></dd>
							<dd class="btn-type-2"><a href="">나의 강의실</a></dd>
							<dd class="btn-type-3"><a href="">수료증 출력</a></dd>
							<dd class="btn-type-4"><a href="">공지사항</a></dd>
						</dl><!-- E : quick-wrap -->
					</div><!-- E : content-wrap -->

				</li><!-- E : body-wrap -->
			</ul><!-- E : container -->
			<hr />
			
					
			<!-- S : footer --><tiles:insertAttribute name="footer" /><!-- E : footer -->
		</div><!-- wrapper -->
<!-- ############### // LU nonLeftLayout ############### -->
 </body>
</html>