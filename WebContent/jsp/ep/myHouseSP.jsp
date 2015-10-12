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
							<table class="table table-bordered table-striped" id="">
								<thead>
									<tr>
										<th>品牌商</th>
										<th>设计商</th>
										<th>制造商</th>
										<th>检测认证商</th>
										<th>渠道商</th>
										<th>物流商</th>
										<th>所有人</th>
										<th>托管人</th>
										<th>市场监管人</th>
										<th>回收处理商</th>
									</tr>
								</thead>
								<tbody class="middle-align">
									<tr>
										<td title="${house.brandName}">&nbsp;${house.brandName}</td>
										<td title="${house.designName}">&nbsp;${house.designName}</td>
										<td title="${house.manufacturerName}">&nbsp;${house.manufacturerName}</td>
										<td title="${house.certificationName}">&nbsp;${house.certificationName}</td>
										<td title="${house.channelName}">&nbsp;${house.channelName}</td>
										<td title="${house.logisticsName}">&nbsp;${house.logisticsName}</td>
										<td title="${house.ownerName}">&nbsp;${house.ownerName}</td>
										<td title="${house.trusteeshipName}">&nbsp;${house.trusteeshipName}</td>
										<td title="${house.supervisionName}">&nbsp;${house.supervisionName}</td>
										<td title="${house.recyclingName}">&nbsp;${house.recyclingName}</td>
									</tr>
								</tbody>
							</table>
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