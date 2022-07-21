<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- ############### Left ############### -->
		<c:set var="upperMenuNo" value="ROOT"/>
		<c:set var="menuLevel" value="1"/>
		<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>
		<c:if test="${not empty menuList[key1]}">
				<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
						<h2>${menu1.menuName }</h2>
						<c:set var="key2" value="${ menu1.menuId}_${menu1.menuLevel+1}"/>
						<c:if test="${not empty menuList[key2]}">
							<ul class="depth2">
								<c:forEach var="menu2" items="${menuList[key2]}" varStatus="status2">
									<li><a id="a_${menu2.menuId }" href="#" onclick="javascript:com.subPageMove('${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuName }</a>
										<c:set var="key3" value="${ menu2.menuId}_${menu2.menuLevel+1}"/>
										<c:if test="${not empty menuList[key3]}">
											<ul class="depth3">
												<c:forEach var="menu3" items="${menuList[key3]}" varStatus="status3">
													<li><a id="a_${menu3.menuId }" href="#" onclick="javascript:com.subPageMove('${menu3.rootMenuId }', '${menu3.menuId }' , false);">${menu3.menuName }</a>
														<c:set var="key4" value="${ menu3.menuId}_${menu3.menuLevel+1}"/>
														<c:if test="${not empty menuList[key4]}">
															<ul class="depth4">
																<c:forEach var="menu4" items="${menuList[key4]}" varStatus="status3">
																	<li><a id="a_${menu4.menuId }" href="#" onclick="javascript:com.subPageMove('${menu4.rootMenuId }', '${menu4.menuId }' , false);">${menu4.menuName }</a></li>
																</c:forEach>														
															</ul>				
														</c:if>
													</li>
												</c:forEach>
											</ul>
										</c:if>
									</li>
								</c:forEach>
							</ul>
						</c:if>
				</c:forEach>
		</c:if>

<script type="text/javascript">
// $("#a_${menuId }").css('background', '#1b77d3 url("/images/lnb_bg.png") no-repeat right top');
// $("#a_${menuId }").css('color', '#FFF');

// $("#a_${menuId }").css('color', '#1b77d3');
// $("#a_${menuId }").css('background', 'white');

$("#a_${menuId }").parent().addClass("on");

</script>
		
<!-- ############### // Left ############### -->