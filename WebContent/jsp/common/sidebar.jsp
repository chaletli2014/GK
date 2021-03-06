<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.goodsquick.model.WebUserInfo,org.springframework.security.core.context.SecurityContextHolder" %>
<%
	GoodsRepository currentRepository = (GoodsRepository)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ);
	String currentRepositoryName = "";	

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
	String manageLink2 = basePath+"assetlist";
	String repoPreName = "资品";
	String newLink = basePath+"newAssetPre";
	if( null != currentRepository ){
		currentRepositoryName = currentRepository.getRepositoryName();
		
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
	}
%>
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
			<a href="<%=request.getParameter("basePath")%>mainIndex">
				<span style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;margin-left:0px;width:200px;" title="<%=currentRepositoryName%>">
					<%=currentRepositoryName%>
				</span>
			</a>
		</div>
		<ul id="main-menu" class="main-menu">
			<%--
			<li <% if( actived.indexOf(",index,")>-1 ){ %>class="active firstLI"<%}else{%>class="firstLI"<%} %>>
				<a href="<%=request.getParameter("basePath") %>index">
					<i class="linecons-desktop"></i>
					<span>视窗看板</span>
				</a>
			</li>
			 --%>
			<%if( "admin".equalsIgnoreCase(webUser.getLoginName()) ) {%>
			<li <% if( opened.indexOf("system")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
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
					<li>
						<a href="<%=request.getParameter("basePath")%>configure">
							<span class="title">配置项管理</span>
						</a>
					</li>
				</ul>
			</li>
			<%} %>
			<li <% if( opened.indexOf(",productManagement,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-database"></i>
					<span class="title"><%=manageName %></span>
				</a>
				<ul>
					<%
						if(!"2".equalsIgnoreCase(currentRepository.getRepositoryType())){
					%>
					<li <% if( actived.indexOf(",ordinaryhouse,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=manageLink%>">
							<i class="entypo-flow-parallel"></i>
							<span class="title">基本信息</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",subjectModule,")>-1 
							|| actived.indexOf(",subject1,")>-1
							|| actived.indexOf(",subject2,")>-1
							|| actived.indexOf(",subject3,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>subjectList?level=1">
							<i class="entypo-flow-parallel"></i>
							<span class="title">主体构件</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",houseDevice,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>houseDeviceList">
							<i class="entypo-flow-parallel"></i>
							<span class="title">设施设备</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",houseOther,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>houseOtherList">
							<i class="entypo-flow-parallel"></i>
							<span class="title">材料&nbsp;&&nbsp;装饰</span>
						</a>
					</li>
				<%}else{ %>
				<li class="add_item">
					<a href="<%=request.getParameter("basePath")%>newGoodsProductPre">添加产品</a>
				</li>
				<li <% if( actived.indexOf(",productlist,")>-1 ){%>class="active"<%} %>>
					<a href="<%=request.getParameter("basePath")%>productlist">
						<i class="entypo-flow-parallel"></i>
						<span class="title">产品列表</span>
					</a>
				</li>
				<%} %>
				</ul>
			</li>
			<% if( "common".equalsIgnoreCase(webUser.getUserType()) ) {%>
			<li <% if( opened.indexOf(",serviceCustomer,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a href="#" onclick="javascript:void(0)">
					<i class="linecons-globe"></i>
					<span class="title">物链管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",moduleSPList,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>moduleSPManagement?spTypeCode=sp_type_brand">
							<i class="entypo-flow-parallel"></i>
							<span class="title">物链列表</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",houseRadar,")>-1){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>houseRadar?spTypeCode=house_radar_supplier">
							<i class="entypo-flow-parallel"></i>
							<span class="title">物链雷达</span>
						</a>
					</li>
					<li>
						<a href="javascript:void(0)" onclick="jAlert('模块功能开发中','提示');">
							<i class="entypo-flow-parallel"></i>
							<span class="title">关联申请</span>
						</a>
					</li>
				</ul>
			</li>
			<li <% if( opened.indexOf(",msgManagement,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a>
					<i class="linecons-sound"></i>
					<span class="title">物讯管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",createNewMsg,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>createNewMsg">
							<i class="entypo-flow-parallel"></i>
							<span class="title">发讯息</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",inBox,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>getInBox">
							<i class="entypo-flow-parallel"></i>
							<span class="title">收件箱</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",outBox,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>getOutBox">
							<i class="entypo-flow-parallel"></i>
							<span class="title">发件箱</span>
						</a>
					</li>
				</ul>
			</li>
			<li <% if( opened.indexOf(",userManagement,")>-1 ){ %>class="active opened firstLI"<%}else{%>class="firstLI"<%} %>>
				<a>
					<i class="linecons-user"></i>
					<span class="title">用户管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",repositoryUser,")>-1 ){ %>class="active"<%} %>>
						<a href="<%=request.getParameter("basePath")%>repositoryUser">
							<i class="entypo-flow-parallel"></i>
							<span class="title">库用户管理</span>
						</a>
					</li>
				</ul>
			</li>
			<li <% if( actived.indexOf(",applicationManagement,")>-1 ){ %>class="active"<%} %>>
				<a href="<%=request.getParameter("basePath")%>applicationManagement">
					<i class="fa-windows"></i>
					<span class="title">应用管理</span>
				</a>
			</li>
			<%} %>
			<%--
			<li <% if( actived.indexOf(",wb,")>-1 ){ %>class="opened active firstLI"<%}else{%>class="firstLI"<%} %>>
				<a>
					<i class="linecons-sound"></i>
					<span class="title">维保管理</span>
				</a>
				<ul>
					<li <% if( actived.indexOf(",jh,")>-1 ){ %>class="active"<%} %>>
						<a href="javascript:void(0)" onclick="jAlert('模块功能开发中','提示');">
							<i class="entypo-flow-parallel"></i>
							<span class="title">计划管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",xj,")>-1 ){ %>class="active"<%} %>>
						<a href="javascript:void(0)" onclick="jAlert('模块功能开发中','提示');">
							<i class="entypo-flow-parallel"></i>
							<span class="title">巡检管理</span>
						</a>
					</li>
					<li <% if( actived.indexOf(",bx,")>-1 ){ %>class="active"<%} %>>
						<a href="javascript:void(0)">
							<i class="entypo-flow-parallel"></i>
							<span class="title">报修管理</span>
						</a>
						<ul>
							<li <% if( actived.indexOf(",gd,")>-1 ){ %>class="active"<%} %>>
								<a href="javascript:void(0)" onclick="jAlert('模块功能开发中','提示');">
									<i class="entypo-flow-parallel"></i>
									<span class="title">工单管理</span>
								</a>
							</li>
							<li <% if( actived.indexOf(",wb,")>-1 ){ %>class="active"<%} %>>
								<a href="javascript:void(0)" onclick="jAlert('模块功能开发中','提示');">
									<i class="entypo-flow-parallel"></i>
									<span class="title">维保查询</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</li>
			<li <% if( actived.indexOf(",ys,")>-1 ){ %>class="active firstLI"<%} %>>
				<a href="<%=request.getParameter("basePath") %>index">
					<i class="linecons-desktop"></i>
					<span>议事管理</span>
				</a>
			</li>
			--%>
		</ul>
	</div>
</div>