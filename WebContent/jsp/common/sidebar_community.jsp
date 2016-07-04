<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodsquick.model.WebUserInfo,org.springframework.security.core.context.SecurityContextHolder" %>
<%
	String opened = request.getParameter("opened");
	String actived = request.getParameter("actived");
	String basePath = request.getParameter("basePath");
	
	if( null == opened || "".equals(opened) ){
		opened = "*";
	}
	if( null == actived || "".equals(actived) ){
		actived = "*";
	}
	WebUserInfo webUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	String manageLink = basePath+"assetlist";
	String newLink = basePath+"newAssetPre";
%>
<div class="sidebar-menu toggle-others">
	<div class="sidebar-menu-inner">
		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a class="logo-expanded">
					<img src="<%=basePath%>images/logo_new.png" alt="" width="80px" height="26px"/>
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
		</header>
		<a href="<%=basePath %>communityIndex">
		<div class="sidebar_title">
			<span>
				<%=webUser.getName() %> 物库
			</span>
		</div>
		</a>
		<ul id="main-menu" class="main-menu">
			<li <% if( actived.indexOf(",assetGroupOverview,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=basePath%>assetGroupOverview">
					<i class="linecons-database"></i>
					<span class="title">物业管理</span>
				</a>
			</li>
			<li <% if( opened.indexOf(",msgManagement,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a>
					<i class="linecons-sound"></i>
					<span class="title">物讯管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",createNewMsg,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>createNewMsg">
							<i class="entypo-flow-parallel"></i>
							<span class="title">发讯息</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",inBox,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>getInBox">
							<i class="entypo-flow-parallel"></i>
							<span class="title">收件箱</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",outBox,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>getOutBox">
							<i class="entypo-flow-parallel"></i>
							<span class="title">发件箱</span>
						</a>
					</li>
				</ul>
			</li>
			<li <% if( opened.indexOf(",dataManagement,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-database"></i>
					<span class="title">数据管理</span>
				</a>
				<ul id="main-menu" class="main-menu">
					<li <% if( actived.indexOf(",locationDis,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>locationDis">位置分布图</a>
					</li>
					<li <% if( actived.indexOf(",assetYearDis,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>assetYearDis">房龄分布图</a>
					</li>
					<li <% if( actived.indexOf(",liftYearDis,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>liftYearDis">梯龄分布图</a>
					</li>
					<li <% if( actived.indexOf(",densityDis,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=basePath%>densityDis">密度分布图</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>