<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


<script language="javascript">
function fncGoAfterErrorPage(){
    history.back(-2);
}
</script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" valign="top"><br /> <br /> <br />
				<table width="600" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center"><table width="100%" border="0"
								cellspacing="9" cellpadding="0">
								<tr>
									<td bgcolor="#FFFFFF"><table width="100%" border="0"
											cellspacing="0" cellpadding="0">
											<tr>
												<td align="left">
													접근 권한이 없거나 세션이 만료되었습니다.[AccessDenied]<br/>
													<c:choose>
														<c:when test="${null != exception.message }">
															${exception.message}
														</c:when>
														<c:otherwise>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
											<tr>
												<td><br /> <br /></td>
											</tr>
											<tr>
												<td><br /> <br /></td>
											</tr>
											<tr>
												<td align="center"><a href="#LINK"
													onClick="fncGoAfterErrorPage();">바로가기</a></td>
											</tr>
										</table> <br /></td>
								</tr>
							</table></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>