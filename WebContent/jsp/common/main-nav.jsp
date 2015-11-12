<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.cxf.common.util.CollectionUtils"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.goodsquick.model.WebUserInfo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	WebUserInfo userInfo = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	List<GoodsRepository> reposigoryList = new ArrayList<GoodsRepository>();
	try{
		reposigoryList = (List<GoodsRepository>)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_LIST);
	}catch(Exception e){}
%>
<nav class="navbar user-info-navbar" role="navigation">
	<!-- Left links for user info navbar -->
	<ul class="user-info-menu left-links list-inline list-unstyled">
		<li class="hidden-sm hidden-xs">
			<a href="#" data-toggle="sidebar">
				<i class="fa-bars"></i>
			</a>
		</li>
		<li class="dropdown hover-line">
			<a href="#" data-toggle="dropdown">
				<i class="fa-bell-o"></i>
				<span class="badge badge-purple">1</span>
			</a>
			<ul class="dropdown-menu notifications">
				<li class="top">
					<p class="small">
						<a href="#" class="pull-right">设置全部已读</a>
						您当前有<strong>1</strong>条新消息.
					</p>
				</li>
				<li>
					<ul class="dropdown-menu-list list-unstyled ps-scrollbar">
						<li class="active notification-success">
							<a href="#">
								<i class="fa-user"></i>
								<span class="line">
									<strong>测试用户1 申请成为您的 品牌商</strong>
								</span>
								<span class="line small time">30秒前</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="external">
					<a href="#">
						<span>查看所有消息</span>
						<i class="fa-link-ext"></i>
					</a>
				</li>
			</ul>
		</li>
	</ul>
	<!-- Right links for user info navbar -->
	<ul class="user-info-menu right-links list-inline list-unstyled">
		<li class="dropdown user-profile">
			<a href="#" data-toggle="dropdown">
				<span><%=userInfo.getName() %>
					<i class="fa-angle-down"></i>
				</span>
			</a>
			<ul class="dropdown-menu user-profile-menu list-unstyled">
				<li>
					<a href="<%=request.getParameter("basePath")%>editprofile?userId=<%=userInfo.getId()%>">
						<i class="fa-user"></i>
						用户资料
					</a>
				</li>
				<li>
					<a href="<%=request.getParameter("basePath")%>index?<%=GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE%>=<%=userInfo.getLoginName()+"_0"%>">
						<i class="fa-folder-o"></i>
						我的资品库
					</a>
				</li>
				<%
					if( !CollectionUtils.isEmpty(reposigoryList) ){
						for( GoodsRepository repository : reposigoryList ){
				%>
					<li>
						<a href="<%=request.getParameter("basePath")%>index?<%=GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE%>=<%=repository.getRepositoryCode()%>">
							<i class="fa-folder-o"></i>
							<%=repository.getRepositoryName() %>
						</a>
					</li>
				<%}} %>
				<li>
					<a href="javascript:void(0)" id="nav_newrepository">
						<i class="fa-plus"></i>
						新增资品库
					</a>
				</li>
				<li class="last">
					<a href="<%=request.getParameter("basePath")%>logout">
						<i class="fa-lock"></i>
						注销
					</a>
				</li>
			</ul>
		</li>
	</ul>
</nav>