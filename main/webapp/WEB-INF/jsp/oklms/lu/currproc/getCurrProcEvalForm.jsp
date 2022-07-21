<%@page import="kr.co.sitglobal.oklms.comm.util.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>

<%--
  ~ *******************************************************************************
  ~  * COPYRIGHT(C) 2016 SMART INFORMATION TECHNOLOGY. ALL RIGHTS RESERVED.
  ~  * This software is the proprietary information of SMART INFORMATION TECHNOLOGY..
  ~  *
  ~  * Revision History
  ~  * Author   Date            Description
  ~  * ------   ----------      ------------------------------------
  ~  * 이진근    2017. 01. 09 오전 11:20         First Draft.
  ~  *
  ~  *******************************************************************************
 --%>
 
  <%
 
String estblYy = (String) request.getParameter("estblYy");
String estblSemstrCd = (String) request.getParameter("estblSemstrCd");
String courseNo = (String) request.getParameter("courseNo");
String partitnClasSeCd = (String) request.getParameter("partitnClasSeCd");
String studId = (String) request.getParameter("partitnClasSeCd");
 
String ls_estbl_yy = CommonUtil.AESenc(estblYy);
String ls_estbl_semstr_cd =  CommonUtil.AESenc(estblSemstrCd);
String ls_course_no =  CommonUtil.AESenc(courseNo);
String ls_partitn_clas_se_cd =  CommonUtil.AESenc(partitnClasSeCd);

%>
 
<c:set var="targetUrl" value="/lu/currproc/"/>

<script type="text/javascript">

	var targetUrl = "${targetUrl}";
	
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
		var ls_estbl_yy              = encodeURIComponent("<%=ls_estbl_yy %>");
		var ls_estbl_semstr_cd       = encodeURIComponent("<%=ls_estbl_semstr_cd %>");
		var ls_course_no             = encodeURIComponent("<%=ls_course_no %>");
		var ls_partitn_clas_se_cd    = encodeURIComponent("<%=ls_partitn_clas_se_cd %>");
		var ls_url = "http://kut80.kut.ac.kr/ozrpt/NCS/NC0206R.jsp?c=한글&I_ESTBL_YY="+ls_estbl_yy+"&I_ESTBL_SEMSTR_CD="+ls_estbl_semstr_cd+"&I_COURSE_NO="+ls_course_no+"&I_PARTITN_CLAS_SE_CD="+ls_partitn_clas_se_cd+"&I_NTH=1";
		$("#myframe").attr("src",ls_url);
	}
	
	
	//== 교과목명세서
	//http://kut80.kut.ac.kr/ozrpt/GYOG/HAK_GYOG_5010_M00R.jsp?I_COURSE_NO=000000

	//== 강의 계획서
	//http://kut80.kut.ac.kr/ozrpt/SUUP/HAK_SUUP_2020_M01R.jsp?I_ESTBL_YY=2016&I_ESTBL_SEMSTR_CD=102&I_COURSE_NO=SME460&I_PARTITN_CLAS_SE_CD=01
	
	//== 진단평가 결과서
	//http://kut80.kut.ac.kr/ozrpt/NCS/NC0206R.jsp?I_ESTBL_YY=2016&I_ESTBL_SEMSTR_CD=102&I_COURSE_NO=SME480&I_PARTITN_CLAS_SE_CD=01&I_NTH=1

	
	//== 직무수행능력평가결과서(수강자별)
	//http://kut80.kut.ac.kr/ozrpt/NCS/NC0213R.jsp?I_ESTBL_YY=2016&I_ESTBL_SEMSTR_CD=102&I_COURSE_NO=SME480&I_PARTITN_CLAS_SE_CD=01&I_STUD_ID=2011180015

	//== 직무수행능력평가결과서(교과목별)
	http://kut80.kut.ac.kr/ozrpt/NCS/NC0215R.jsp?I_ESTBL_YY=2016&I_ESTBL_SEMSTR_CD=102&I_COURSE_NO=SME480&I_PARTITN_CLAS_SE_CD=01&I_STUD_ID=2011180015

	
	/*====================================================================
		사용자 정의 함수 
	====================================================================*/

	
	
</script>
<br>
<br>
<br>
<br>
<br>
<h2>체크리스트</h2>
<div class="group-area align-center mb-040" style="height:100%;">
	<%-- <iframe src="http://61.250.228.44/oz60/ozrpt/NC/NC0206R.jsp?I_ESTBL_YY=${param.estblYy}&I_ESTBL_SEMSTR_CD=${param.estblSemstrCd}&I_COURSE_NO=${param.courseNo}&I_PARTITN_CLAS_SE_CD=${param.partitnClasSeCd}&I_NTH=1" width="100%" height="100%"></iframe> --%>
	<iframe id="myframe" name="myframe"  width="100%" height="100%"></iframe>
</div>	


<div class="btn-area align-right mt-010">
	<a href="#fn_cret" onclick="javascript:history.back();" class="orange">목록</a>
</div><!-- E : btn-area -->	
	