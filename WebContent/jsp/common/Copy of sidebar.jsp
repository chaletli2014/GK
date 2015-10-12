<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String opened = request.getParameter("opened");
	String actived = request.getParameter("actived");
	
	if( null == opened || "".equals(opened) ){
		opened = "*";
	}
	if( null == actived || "".equals(actived) ){
		actived = "*";
	}
%>

<div class="sidebar-menu toggle-others">
	<div class="sidebar-menu-inner">
		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="<%=request.getParameter("basePath") %>index" class="logo-expanded">
					<img src="<%=request.getParameter("basePath")%>images/logo@2x.png" width="80" alt="" />
				</a>
			</div>
			<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
			<div class="mobile-menu-toggle visible-xs">
				<a href="#" data-toggle="user-info-menu">
					<i class="fa-bell-o"></i>
					<span class="badge badge-success">7</span>
				</a>
				<a href="#" data-toggle="mobile-menu">
					<i class="fa-bars"></i>
				</a>
			</div>
			<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
			<div class="settings-icon">
				<a href="#" data-toggle="settings-pane" data-animate="true">
					<i class="linecons-cog"></i>
				</a>
			</div>
		</header>
		<ul id="main-menu" class="main-menu">
			<li <% if( opened.indexOf("system")>-1 ){ %>class="active opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-cog"></i>
					<span class="title">系统管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",userlist,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>userlist">
							<span class="title">用户管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",rolelist,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>rolelist">
							<span class="title">角色管理</span>
						</a>
					</li>
					<%--
					<li <% if( actived.indexOf(",privilegelist,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>privilegelist">
							<span class="title">权限管理</span>
						</a>
					</li>
					 --%>
					<li <% if( actived.indexOf(",category,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>category">
							<span class="title">分类管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",dictionarytype,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>dictionarytype">
							<span class="title">数据字典分类管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",dictionary,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>dictionary">
							<span class="title">数据字典管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",uploadSource,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>uploadSource">
							<span class="title">资料上传</span>
						</a>
					</li>
					<!-- 
					<li>
						<a href="<%=request.getParameter("basePath")%>configure">
							<span class="title">配置项管理</span>
						</a>
					</li>
					 -->
				</ul>
			</li>
			<li <% if( opened.indexOf(",estate,")>-1 ){ %>class="opened"<%} %>>
				<a href="layout-variants.html">
					<i class="linecons-desktop"></i>
					<span class="title">不动产产品库管理</span>
				</a>
				<ul>
					<li <% if( opened.indexOf(",resident,")>-1 ){ %>class="opened"<%} %>>
						<a href="layout-variants.html">
							<span class="title">居住物业</span>
						</a>
						<ul>
							<li <% if( actived.indexOf(",ordinaryhouse,")>-1 ){ %>class="active"<%} %>>
								<a href="<%=request.getParameter("basePath")%>ordinaryhouse">
									<i class="entypo-flow-parallel"></i>
									<span class="title">普通住宅</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">高级公寓</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">别墅</span>
								</a>
							</li>
						</ul>
					</li>
					<%--
					<li <% if( opened.indexOf(",business,")>-1 ){ %>class="opened"<%} %>>
						<a href="layout-collapsed-sidebar.html">
							<span class="title">商业物业</span>
						</a>
						<ul>
							<li <% if( actived.indexOf(",officebuilding,")>-1 ){ %>class="active"<%} %>>
								<a href="<%=request.getParameter("basePath")%>officebuilding">
									<i class="entypo-flow-parallel"></i>
									<span class="title">商务办公楼（写字楼）</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">旅馆</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">商店</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">餐馆</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">影剧院</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-static-sidebar.html">
							<span class="title">旅游物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">公园</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">风景名胜</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">历史古迹</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">沙滩</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-horizontal-menu.html">
							<span class="title">工业物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">厂房</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">仓库</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-horizontal-plus-sidebar.html">
							<span class="title">农业物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">农场</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">林场</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">牧场</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">果园</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-horizontal-menu-click-to-open-subs.html">
							<span class="title">交通物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">车站</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">地铁站</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">飞机场</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">公路</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">铁路</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-horizontal-menu-min.html">
							<span class="title">特殊物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">政府机关办公楼</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">学校</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">教堂</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">寺庙</span>
								</a>
							</li>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">墓地</span>
								</a>
							</li>
						</ul>
					</li>
					 --%>
				</ul>
			</li>
			<%-- 
			<li <% if( opened.indexOf(",estategoods,")>-1 ){ %>class="opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-desktop"></i>
					<span class="title">不动产物品库管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",myestate,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>myestate" onclick="javascript:void(0)">
							<span class="title">我的所有不动产</span>
						</a>
					</li>
					<li>
						<a href="layout-collapsed-sidebar.html">
							<span class="title">商业物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">商务办公楼（写字楼）</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="layout-collapsed-sidebar.html">
							<span class="title">居住物业</span>
						</a>
						<ul>
							<li>
								<a href="#">
									<i class="entypo-flow-parallel"></i>
									<span class="title">普通住宅</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li <% if( opened.indexOf(",product,")>-1 ){ %>class="opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-note"></i>
					<span class="title">动产产品库管理</span>
				</a>
				<ul>
					<li <% if( opened.indexOf(",machine,")>-1 ){ %>class="opened"<%} %>>
						<a href="#" onclick="javascript:void(0)">
							<span class="title">机器电子电气设备</span>
						</a>
						<ul>
							<li <% if( opened.indexOf(",specialmachine,")>-1 ){ %>class="opened"<%} %>>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">特种设备</span>
								</a>
								<ul>
									<li <% if( opened.indexOf(",lift,")>-1 ){ %>class="opened"<%} %>>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">电梯</span>
										</a>
										<ul>
											<li <% if( actived.indexOf(",passengerlift,")>-1 ){ %>class="active"<%} %>>
												<a href="<%=request.getParameter("basePath")%>passengerlift" onclick="javascript:void(0)">
													<span class="title">乘客电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">载货电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">医用电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">杂物电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">观光电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">车辆电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">船舶电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">建筑施工电梯</span>
												</a>
											</li>
											<li>
												<a href="#" onclick="javascript:void(0)">
													<span class="title">其它电梯</span>
												</a>
											</li>
										</ul>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">起重机械</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">客运索道</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">大型游乐设施</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="javascript:void(0)">
											<span class="title">场（厂）内专用机动车辆</span>
										</a>
									</li>
								</ul>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">电机电气设备</span>
								</a>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">电视音像设备</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">
							<span class="title">塑料橡胶制品</span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">塑料制品</span>
								</a>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">橡胶制品</span>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">
							<span class="title">皮制品</span>
						</a>
						<ul>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">生皮制品</span>
								</a>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">皮革制品</span>
								</a>
							</li>
							<li>
								<a href="#" onclick="javascript:void(0)">
									<span class="title">毛皮制品</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-note"></i>
					<span class="title">动产物品库管理</span>
				</a>
				<ul>
					<li>
						<a href="#" onclick="javascript:void(0)">
							<span class="title">我的物品库</span>
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-note"></i>
					<span class="title">流通库管理</span>
				</a>
				<ul>
					<li>
						<a href="#" onclick="javascript:void(0)">
							<span class="title">我的流通库</span>
						</a>
					</li>
				</ul>
			</li>
			 --%>
			<li <% if( opened.indexOf(",service,")>-1 ){ %>class="opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-note"></i>
					<span class="title">服务库管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",serviceprovider,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>serviceprovider" onclick="javascript:void(0)">
							<span class="title">我的服务库</span>
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>