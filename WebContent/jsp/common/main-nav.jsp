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
<nav class="navbar user-info-navbar" role="navigation" style="height:46px;">
	<!-- Left links for user info navbar -->
	<ul class="user-info-menu left-links list-inline list-unstyled">
		<li class="hidden-sm hidden-xs">
			<a href="#" data-toggle="sidebar">
				<i class="fa-arrows-alt"></i>
			</a>
		</li>
		<li class="dropdown hover-line">
			<a href="#" data-toggle="dropdown" class="repositoryNav">
				<span class="">物库管理</span>
			</a>
			<ul class="dropdown-menu notifications">
				<li>
					<div id="navigationBar" class="qc-navigation">
						<div class="navigation-inner ">
							<div class="categories" id="nav-con">
								<ul class="menu">
									<li id="h_n_cooperate_t" class="presentation">
										<div class="nav-down-style nav-down-style-2" id="h_n_cooperate_c">
											<div class="nav-down-list">
												<ul class="nav-down-menu goods_menu">
													<li class="menu-title">
														<span>产品库</span>
														<span class="nav_newrepository">＋新建</span>
													</li>
												</ul>
												<ul class="nav-down-menu own_menu">
													<li class="menu-title">
														<span>资品库</span>
														<span class="nav_newrepository">＋新建</span>
													</li>
													<li class="menu-list-own">
														<div>
															<a href="<%=request.getParameter("basePath")%>index?repository_code=<%=userInfo.getLoginName()%>_0">初始资品库</a>
														</div>
													</li>
												</ul>
												<ul class="nav-down-menu req_menu">
													<li class="menu-title">
														<span>需品库</span>
														<span class="nav_newrepository">＋新建</span>
													</li>
												</ul>
											</div>
										</div>
									</li>					
								</ul>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</li>
		<li class="dropdown hover-line">
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
				<%--
				<li>
					<a href="<%=request.getParameter("basePath")%>index?<%=GoodsQuickAttributes.WEB_SESSION_REPOSITORY_CODE%>=<%=userInfo.getLoginName()+"_0"%>">
						<i class="fa-folder-o"></i>
						初始资品库
					</a>
				</li>
				<%
					if( !CollectionUtils.isEmpty(repositoryList) ){
						for( GoodsRepository repository : repositoryList ){
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
						新增物库
					</a>
				</li>
				<li>
					<a href="<%=request.getParameter("basePath")%>repositorylist">
						<span class="title">物库管理</span>
					</a>
				</li>
				 --%>
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