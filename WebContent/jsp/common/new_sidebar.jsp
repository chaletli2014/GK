<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodsquick.model.WebUserInfo,org.springframework.security.core.context.SecurityContextHolder" %>
<%
	GoodsRepository currentRepository = (GoodsRepository)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ);
	
	String opened = request.getParameter("opened");
	String actived = request.getParameter("actived");
	
	if( null == opened || "".equals(opened) ){
		opened = "*";
	}
	if( null == actived || "".equals(actived) ){
		actived = "*";
	}
	WebUserInfo webUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	String basePath = request.getParameter("basePath");
	
	String manageName = "资品管理";
	String newRepoName = "添加资品";
	String manageLink = basePath+"ordinaryhouse";
	String repoPreName = "资品";
	String newLink = basePath+"newAssetPre";
	
	if("3".equalsIgnoreCase(currentRepository.getRepositoryType())){
		repoPreName = "需品";
		manageName = "需品管理";
		newRepoName = "添加需品";
		manageLink = basePath+"productlist";
		newLink = basePath+"newProductPre";
	}else if("2".equalsIgnoreCase(currentRepository.getRepositoryType())){
		repoPreName = "产品";
		manageName = "产品管理";
		newRepoName = "添加产品";
		manageLink = basePath+"productlist";
		newLink = basePath+"newGoodsProductPre";
	}
%>

<style type="text/css">
	.sidebar-menu .logo-env{padding:15px 0;}
	.sidebar-menu .logo-env .logo{margin-left:15px;}
	.sidebar-menu .main-menu{margin-top:10px;}
	.sidebar-menu{width:170px; background:#202020;}
	.silde_title{color:#999; padding:10px 0; text-indent:15px;}
	.add_item{margin:10px 0; text-align:center; background:url(../GoodsQuick/images/icon/add.png) no-repeat 35px center;}
	.add_item a{display:inline-block; width:80%; text-indent:14px; padding:8px 0; border:1px #999 dashed; color:#999;}
	.add_item a:hover{color:#fff; border-color:#fff;}
	.main-menu li{color:#999; text-indent:40px; background:url(../GoodsQuick/images/icon/nav.png) no-repeat 15px center;}
	.sidebar-menu .main-menu a{border:none;}
	.main-menu li:hover{background:#2d2d2d url(../GoodsQuick/images/icon/nav_hover.png) no-repeat 15px center;}
	.main-menu li.active{color:#fff; background:#2d2d2d url(../GoodsQuick/images/icon/nav_hover.png) no-repeat 15px center;}
</style>

<div class="sidebar-menu toggle-others">
	<div class="sidebar-menu-inner">
		<header class="logo-env">
			<!-- logo -->
			<div class="logo">
				<a href="<%=request.getParameter("basePath") %>index" class="logo-expanded">
					<img src="<%=request.getParameter("basePath")%>images/logo_new.png" alt="" width="80px" height="26px"/>
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
		<div class="sidebar_title">
			概览
		</div>
		<h3 class="silde_title">资品库列表</h3>
		
		<ul id="main-menu" class="main-menu">
			<li class="active"><a href="#">测试资品库01</a></li>
			<li><a href="#">测试资品库02</a></li>
			<li><a href="#">测试资品库03</a></li>
		</ul>
		<div class="add_item">
			<a href="javascript:;">添加资品库</a>
		</div>
	</div>
</div>