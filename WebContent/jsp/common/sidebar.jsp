<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodsquick.model.WebUserInfo,org.springframework.security.core.context.SecurityContextHolder" %>
<%
	String opened = request.getParameter("opened");
	String actived = request.getParameter("actived");
	
	if( null == opened || "".equals(opened) ){
		opened = "*";
	}
	if( null == actived || "".equals(actived) ){
		actived = "*";
	}
	WebUserInfo webUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
%>

<div class="sidebar-menu toggle-others">
	<div class="sidebar-menu-inner">
		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="<%=request.getParameter("basePath") %>index" class="logo-expanded">
					<img src="<%=request.getParameter("basePath")%>images/logo-white.png" alt="" width="80px" height="30px"/>
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
			<div class="settings-icon" title="编辑用户">
				<a href="#" data-toggle="settings-pane" data-animate="true">
					<i class="linecons-cog"></i>
				</a>
			</div>
		</header>
		<ul id="main-menu" class="main-menu">
			<li>
				<a href="<%=request.getParameter("basePath") %>index">
					<i class="linecons-desktop"></i>
					<span>首页</span>
				</a>
			</li>
			<%if( "admin".equalsIgnoreCase(webUser.getLoginName()) ) {%>
			<li <% if( opened.indexOf("system")>-1 ){ %>class="active opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-cog"></i>
					<span class="title">我的控制台</span>
					<%if(0 != webUser.getMessageNum()){%>
					<span class="badge badge-secondary pull-right"><%=webUser.getMessageNum()%></span>
					<%}%>
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
					<li <% if( actived.indexOf(",category,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>category">
							<span class="title">分类管理</span>
						</a>
					</li>
					 --%>
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
					<li>
						<a href="<%=request.getParameter("basePath")%>configure">
							<span class="title">配置项管理</span>
						</a>
					</li>
				</ul>
			</li>
			<%} %>
			<li <% if( opened.indexOf(",estate,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=request.getParameter("basePath")%>ordinaryhouse">
					<i class="linecons-database"></i>
					<span class="title">物库管理</span>
				</a>
			</li>
			<li <% if( opened.indexOf(",,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=request.getParameter("basePath")%>myHouseSP">
					<i class="linecons-globe"></i>
					<span class="title">物链管理</span>
				</a>
			</li>
			<li <% if( actived.indexOf(",myMessage,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=request.getParameter("basePath")%>myMessage">
					<i class="linecons-mail"></i>
					<span class="title">消息管理</span>
					<%if(0 != webUser.getMessageNum()){%>
					<span class="badge badge-secondary pull-right"><%=webUser.getMessageNum()%></span>
					<%}%>
				</a>
			</li>
			<li <% if( actived.indexOf(",myReport,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=request.getParameter("basePath")%>myReport">
					<i class="linecons-mail"></i>
					<span class="title">数据服务</span>
				</a>
			</li>
			<%--
			<li <% if( opened.indexOf(",service,")>-1 ){ %>class="opened"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-note"></i>
					<span class="title">服务信息管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",serviceprovider,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>serviceprovider" onclick="javascript:void(0)">
							<span class="title">服务商列表</span>
						</a>
					</li>
				</ul>
			</li>
			 --%>
		</ul>
	</div>
</div>