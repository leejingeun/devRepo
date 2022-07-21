<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- ############### LA Left ############### -->
			<li id="menu-area">
				<h1>LMS관리자</h1>
						<c:set var="upperMenuNo" value="TOP"/>
						<c:set var="menuLevel" value="1"/>
						<c:set var="menuTypeNum" value="1"/>
						<c:set var="key1" value="${ upperMenuNo}_${menuLevel}"/>
						<input type="hidden" name="keyParam" value="${ upperMenuNo}_${menuLevel}"/>
						<c:if test="${not empty menuList[key1]}">
							<c:forEach var="menu1" items="${menuList[key1]}" varStatus="status">
							
<c:if test="${ menu1.menuType eq 'ADM'}" >
								<!-- S : depth-1 -->
								<dl class="menu-${menuTypeNum }">
								<c:set var="menuTypeNum" value="${menuTypeNum+1}"/>
								
									<dt id="dt_${menu1.menuId }"><c:choose>
											<c:when test="${0 eq menu1.isLeafMenu}"><a id="a_${menu1.menuId }" href="#" >${menu1.menuTitle }</a></c:when>
											<c:otherwise><a id="a_${menu1.menuId }" href="#" onclick="javascript:com.subPageMove('${menu1.menuArea }', '${menu1.rootMenuId }', '${menu1.menuId }' , false);">${menu1.menuTitle }</a></c:otherwise>
										</c:choose></dt>
										<c:set var="key2" value="${ menu1.menuId}_${menu1.menuLevel+1}"/>
										<c:choose>
											<c:when test="${not empty menuList[key2]}">
											<dd id="dd_${menu1.menuId }">
												<c:forEach var="menu2" items="${menuList[key2]}" varStatus="status2">
													<!-- S : depth-2 -->
													<dl class="depth-${menu2.menuDepth }">
														<dt id="dt_${menu2.menuId }"><c:choose>
																<c:when test="${0 eq menu2.isLeafMenu}"><a id="a_${menu2.menuId }" href="#" >${menu2.menuTitle }</a></c:when>
																<c:otherwise><a id="a_${menu2.menuId }" href="#" onclick="javascript:com.subPageMove('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuTitle }</a></c:otherwise>
															</c:choose></dt>
															<c:set var="key3" value="${ menu2.menuId}_${menu2.menuLevel+1}"/>
															<c:choose>
																<c:when test="${not empty menuList[key3]}">
																<dd id="dd_${menu2.menuId }">
																	<c:forEach var="menu3" items="${menuList[key3]}" varStatus="status3">
																		<!-- S : depth-3 -->
																		<dl class="depth-${menu3.menuDepth }">
																			<dt id="dt_${menu3.menuId }"><c:choose>
																					<c:when test="${0 eq menu3.isLeafMenu}"><a id="a_${menu3.menuId }" href="#" >${menu3.menuTitle }</a></c:when>
																					<c:otherwise><a id="a_${menu3.menuId }" href="#" onclick="javascript:com.subPageMove('${menu3.menuArea }', '${menu3.rootMenuId }', '${menu3.menuId }' , false);">${menu3.menuTitle }</a></c:otherwise>
																				</c:choose></dt>
																				<c:set var="key4" value="${ menu3.menuId}_${menu3.menuLevel+1}"/>
																				<c:choose>
																					<c:when test="${not empty menuList[key4]}">
																						<dd id="dd_${menu3.menuId }">
																							<c:forEach var="menu4" items="${menuList[key4]}" varStatus="status3">
																								<dl class="depth-${menu4.menuDepth }">
																									<dt id="dt_${menu4.menuId }"><a id="a_${menu4.menuId }" href="#" onclick="javascript:com.subPageMove('${menu4.menuArea }', '${menu4.rootMenuId }', '${menu4.menuId }' , false);">${menu4.menuTitle }</a></dt>
																									<dd id="dd_${menu4.menuId }"></dd>
																								</dl>
																							</c:forEach>
																						</dd>
																					</c:when>
																					<c:otherwise><dd class="hidden"></dd></c:otherwise>
																				</c:choose>
																		</dl><!-- E : depth-3 -->
																	</c:forEach>
																</dd>
																</c:when>
																<c:otherwise><dd class="hidden"></dd></c:otherwise>
															</c:choose>
													</dl><!-- E : depth-2 -->
												</c:forEach>
											</dd>
											</c:when>
											<c:otherwise><dd class="hidden"></dd></c:otherwise>
										</c:choose>
								</dl><!-- E : depth-1 -->
</c:if>								
								
							</c:forEach>
						</c:if>
					</li>
								
												
												
							<%--
							
												
													<a id="a_${menu2.menuId }" href="#" onclick="javascript:com.subPageMove('${menu2.menuArea }', '${menu2.rootMenuId }', '${menu2.menuId }' , false);">${menu2.menuTitle }
														<c:set var="key3" value="${ menu2.menuId}_${menu2.menuLevel+1}"/>
														<c:if test="${not empty menuList[key3]}">
															<ul class="depth3">
																<c:forEach var="menu3" items="${menuList[key3]}" varStatus="status3">
																	<li><a id="a_${menu3.menuId }" href="#" onclick="javascript:com.subPageMove('${menu3.menuArea }', '${menu3.rootMenuId }', '${menu3.menuId }' , false);">${menu3.menuTitle }</a>
																		<c:set var="key4" value="${ menu3.menuId}_${menu3.menuLevel+1}"/>
																		<c:if test="${not empty menuList[key4]}">
																			<ul class="depth4">
																				<c:forEach var="menu4" items="${menuList[key4]}" varStatus="status3">
																					<li><a id="a_${menu4.menuId }" href="#" onclick="javascript:com.subPageMove('${menu4.menuArea }', '${menu4.rootMenuId }', '${menu4.menuId }' , false);">${menu4.menuTitle }</a></li>
																				</c:forEach>														
																			</ul>				
																		</c:if>
																	</li>
																</c:forEach>
															</ul>
														</c:if>
													</a>
												</c:forEach>
											</dd>
									</c:if>
									</dl>
							</c:forEach>
					</c:if>
			</li>
							 --%>					
			
			
			
			
			

<script type="text/javascript">
// $("#a_${menuId }").css('background', '#1b77d3 url("/images/lnb_bg.png") no-repeat right top');
// $("#a_${menuId }").css('color', '#FFF');

// $("#a_${menuId }").css('color', '#1b77d3');
// $("#a_${menuId }").css('background', 'white');

// $("#a_${menuId }").parent().addClass("on");


			<c:set var="leftMenuIdPathList" value="${fn:split(menuIdPathLeafNode, '@')}" />

			<c:forEach var="menuPath" items="${leftMenuIdPathList }" varStatus="status">
			$("#dt_${leftMenuIdPathList[status.index]}").addClass('on');
			$("#dd_${leftMenuIdPathList[status.index]}").addClass('on');
// 			$("#a_dt_${leftMenuIdPathList[status.index]}").addClass('on');
			</c:forEach>


// $("#dt_2009MENU0000002").addClass('on');
// $("#dd_2009MENU0000002").addClass('on');
// $("#a_2009MENU0000002").addClass('on');

// $("#dt_2009MENU0000004").addClass('on');
// $("#dd_2009MENU0000004").addClass('on');
// $("#a_2009MENU0000004").addClass('on');
</script>
		
<!-- ############### // LA Left ############### -->