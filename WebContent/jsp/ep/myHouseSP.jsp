<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<body class="page-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="../common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">不动产服务商列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">商品信息管理</a>
					</li>
					<li class="active">
						<strong>我的服务商</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<c:if test="${fn:length(houses) == 0}">
						<span>您还没有注册过任何不动产</span>
					</c:if>
					<c:forEach items="${houses}" var="house" varStatus="status">
						<div class="panel panel-color panel-success <c:if test="${status.first!=true}">collapsed</c:if>"><!-- Add class "collapsed" to minimize the panel -->
						<a href="#" data-toggle="panel">
						<div class="panel-heading" style="padding:10px 20px;">
							<h3 class="panel-title">${house.houseName} 服务商列表</h3>
							<div class="panel-options">
								<span class="collapse-icon">&ndash;</span>
								<span class="expand-icon">+</span>
							</div>
						</div>
						</a>
						<div class="panel-body">
							<ul class="sp_list">
								<li class="sp_list_item">
									<div class="sp_title">品牌商</div>
									<div class="sp_detail">&nbsp;${house.brandName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">设计商</div>
									<div class="sp_detail">&nbsp;${house.designName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">制造商</div>
									<div class="sp_detail">&nbsp;${house.manufacturerName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">检测认证商</div>
									<div class="sp_detail">&nbsp;${house.certificationName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">渠道商</div>
									<div class="sp_detail">&nbsp;${house.channelName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">物流商</div>
									<div class="sp_detail">&nbsp;${house.logisticsName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">所有人</div>
									<div class="sp_detail">&nbsp;${house.ownerName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">托管人</div>
									<div class="sp_detail">&nbsp;${house.trusteeshipName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">市场监管人</div>
									<div class="sp_detail">&nbsp;${house.supervisionName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
								<li class="sp_list_item">
									<div class="sp_title">回收处理商</div>
									<div class="sp_detail">&nbsp;${house.recyclingName}</div>
									<div class="sp_actions">
										<a><button class="btn btn-red">删除</button></a>
										<a><button class="btn btn-orange">更换</button></a>
									</div>
								</li>
							</ul>
						</div>
					</div>
					</c:forEach>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>