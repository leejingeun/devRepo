<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 
						<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
								<colgroup>
									<col style="width:10%" />
									<col style="width:17%" />
									<col style="width:10%" />
									<col style="width:17%" />
									<col style="width:10%" />
									<col style="width:17%" />
								</colgroup>
								<tbody>
									<tr>
										<th>학년도</th>
										<td style="text-align: center">${param.yyyy } ${empty param.yyyy ? '' : '학년도' }</td>
										<th>학기</th>
										<td style="text-align: center">${param.termNm }</td>
										<th>학과명</th>
										<td style="text-align: center">${param.deptNm }</td>
									</tr>
								</tbody>
							</table>
							<br/>
							

							<table  width="100%" border="1" cellspacing="1" cellpadding="1" class="tableBorder">
								<colgroup>
									<col style="width:5%" />
									<col style="width:5%" />
									<col style="width:6%" />
									<col style="width:12%" />
									<col style="width:12%" />
									<col style="width:12%" />
									<col style="width:12%" />
									<col style="width:12%" />
									<col style="width:4%" />
									<col style="width:4%" />
									<col style="width:4%" />
									<col style="width:6%" />
									<col style="width:6%" />
								</colgroup>
								<thead>
									<tr>
										<th>학년</th>
										<th>학번</th>
										<th>성명</th>
										<th>과목명 평점</th>
										<th>과목명 평점</th>
										<th>과목명 평점</th>
										<th>과목명 평점</th>
										<th>과목명 평점</th>
										<th>학</th>
										<th>강</th>
										<th>실</th>
										<th>취/신</th>
										<th>평점평균</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="result" items="${gradeList}" varStatus="status">
									<tr>
										<td style="text-align: center">${result.subjectGrade }학년</td>
										<td style="text-align: center">${result.memId}</td>
										<td style="text-align: center">${result.memName }</td>
										<td style="text-align: center">${fn:replace(result.subject1,'<BR>','')}</td>
										<td style="text-align: center">${fn:replace(result.subject2,'<BR>','')}</td>
										<td style="text-align: center">${fn:replace(result.subject3,'<BR>','')}</td>
										<td style="text-align: center">${fn:replace(result.subject4,'<BR>','')}</td>
										<td style="text-align: center">${fn:replace(result.subject5,'<BR>','')}</td>
										<td style="text-align: center">${result.totPnt }</td>
										<td style="text-align: center">${result.totLctrePnt }</td>
										<td style="text-align: center">${result.totReqstPnt }</td>
										<td style="text-align: center">${result.totGetPnt}/${result.totPnt}</td>
										<td style="text-align: center">${result.totMarkAvrg }</td>
									</tr>
								</c:forEach> 
								<c:if test="${empty gradeList}" >
									<tr>
										<td colspan="13" style="text-align: center"><spring:message code="common.nodata.msg" /></td>
									</tr>
								</c:if>
								</tbody>
							</table>