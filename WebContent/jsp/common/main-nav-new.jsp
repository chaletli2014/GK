<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.cxf.common.util.CollectionUtils"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.goodsquick.model.WebUserInfo"%>
<%@page import="java.util.List"%>
<%
	WebUserInfo userInfo = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	
	List<GoodsRepository> repositoryList = new ArrayList<GoodsRepository>();
	GoodsRepository currentRepository = new GoodsRepository();
	String repositoryGoodsName = "资品";
	try{
		repositoryList = (List<GoodsRepository>)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_LIST);
		Object repository = request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ);
		if( null != repository ){
			currentRepository = (GoodsRepository)repository;
			if( "2".equalsIgnoreCase(currentRepository.getRepositoryType()) ){
				repositoryGoodsName = "商品";
			}else if( "3".equalsIgnoreCase(currentRepository.getRepositoryType()) ){
				repositoryGoodsName = "需品";
			}
		}
	}catch(Exception e){}
%>
<style>
.info-navbar-new{height:57px;}
.info-navbar-new .user-info-menu > li > a{height:57px; line-height:57px; padding:0 20px;color: inherit;}
.info-navbar-new .user-info-menu > li > a:hover{color:#00a4ef;}
.info-navbar-new .user-info-menu > li.active{color:#00a4ef;}
</style> 
<nav class="navbar user-info-navbar info-navbar-new" role="navigation">
	<!-- Left links for user info navbar -->
	<ul class="user-info-menu left-links list-inline list-unstyled" style="width:86%;">
		<li class="hidden-sm hidden-xs">
				<a href="#" data-toggle="sidebar">
					<i class="fa-arrows-alt"></i>
				</a>
		</li>
		<li class="dropdown active">
			<a href="#" data-toggle="dropdown" class="repositoryNav">
				<span>资品库管理</span>
			</a>
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown" class="repositoryNav">
				<span>产品库管理</span>
			</a>
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown" class="repositoryNav">
				<span class="">需品库管理</span>
			</a>
		</li>
		<%--
		<li class="dropdown" style="">
			<a href="#" data-toggle="dropdown">
				<i class="fa-bell-o" title="消息管理"></i>
				<span class="badge badge-purple" id="messageTips"></span>
			</a>
			<ul class="dropdown-menu notifications">
				<li class="top">
					<p class="small" id="messageCount"></p>
				</li>
				<li>
					<ul class="dropdown-menu-list list-unstyled ps-scrollbar" id="newMessageList">
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
					<a href="<%=request.getParameter("basePath")%>myMessage">
						<span>查看所有消息</span>
						<i class="fa-link-ext"></i>
					</a>
				</li>
			</ul>
		</li>
		 --%>
	</ul>
	<!-- Right links for user info navbar -->
	<ul class="user-info-menu right-links list-inline list-unstyled">
		<li class="dropdown user-profile">
			<a href="#" data-toggle="dropdown">
				<span><%=userInfo.getName() %>&nbsp;&nbsp;的账户
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

<!-- 二级导航 -->
<style>
.user-info-navbar{margin-bottom:20px;}
.two_nav{width:100%;height:111px; margin-bottom:20px; background:#fff;}
.two_nav h3{width:95%; height:50px; margin:0 auto; border-bottom:1px #ccc solid; 
	line-height:50px; font-size:16px; font-weight:800;}
.two_nav ul{width:95%; height:50px; line-height:50px; margin:0 auto; margin-top:10px;}
.two_nav li{float:left; padding:0 15px;}
.two_nav li a{color:inherit;}
.two_nav li:hover{border-bottom:2px #00a4ef solid; color:#00a4ef;}
.two_nav li.active{ border-bottom:2px #00a4ef solid; color:#00a4ef;}
</style>
<div class="two_nav">
	<!-- 当前资品名称 -->
	<h3>测试资品标题</h3>
	<!-- 二级导航内容 -->
	<ul>
		<li class="dropdown hover-line active">
			<a href="#" data-toggle="dropdown">
				<span class="">概览</span>
			</a>
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown">
				<span class="">基本信息</span>
			</a>
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown">
				<span class="">主体构件</span>
			</a>
			<!--主体构件下拉 -->
			
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown">
				<span class="">设施设备</span>
			</a>
		</li>
		<li class="dropdown">
			<a href="#" data-toggle="dropdown">
				<span class="">材料&装饰</span>
			</a>
		</li>
	</ul>
</div>