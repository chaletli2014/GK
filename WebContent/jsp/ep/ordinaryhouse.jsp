<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/ordinaryhouse.js"></script>
<script type="text/javascript">
jQuery(document).ready(function($){
	if( $("#houseSize").val() == 0 ){
		jConfirm("您还没有添加任何产品，是否现在添加？","信息",function(r) {
	    	if(r){
	    		window.location.href="<%=basePath%>newProductPre";
	    	}
		});
	}
});
</script>
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
					<h1 class="title">产品列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物库管理</a>
					</li>
					<li class="active">
						<strong>产品列表</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#houseTable").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							aoColumns: [
								null,
								null,
								null,
								null,
								null
							],
						});
						$("#serviceDetailTable").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							aoColumns: [
								null,
								null,
								null,
								null,
								null
							],
						});
					});
					</script>
					<input type="hidden" id="houseSize" value="${fn:length(orHouses)}"/>
					<c:if test="${fn:length(orHouses) == 0 }">
						<div>
							<span>您还没有添加任何产品,赶紧添加吧！</span>
						</div>
					</c:if>
					<div>
						<a href="<%=basePath%>newProductPre">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增产品</span>
							</button>
						</a>
					</div>
					<c:if test="${fn:length(orHouses) > 0 }">
						<table class="table table-bordered table-striped" id="houseTable">
							<thead>
								<tr>
									<th>项目名称</th>
									<th>开发公司</th>
									<th>物业名称</th>
									<th>坐落位置</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${orHouses}" var="orHouse">
									<tr>
										<td ><a class="houseNameLink" tabindex="${orHouse.id}" href="javascript:void(0)">${orHouse.buildingName}</a></td>
										<td >${orHouse.company}</td>
										<td >${orHouse.propertyName}</td>
										<td >${orHouse.location}</td>
										<td>
											<ul class="table_action_list">
												<li>
													<a href="<%=basePath%>addordinaryhouse?orHouseId=${orHouse.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
														编辑
													</a>
												</li>
												<li>
													<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
														删除
													</a>
												</li>
												<li>
													<a href="<%=basePath%>ordinaryHousedevice?orHouseId=${orHouse.id}" class="btn btn-info btn-sm btn-icon icon-left">
														设施/设备列表
													</a>
												</li>
												<%--
												<li>
													<a href="<%=basePath%>houseSP?orHouseId=${orHouse.id}" class="btn btn-info btn-sm btn-icon icon-left">
														服务商维护
													</a>
												</li>
												 --%>
											</ul>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${fn:length(serviceDetails) > 0 }">
						<table class="table table-bordered table-striped" id="serviceDetailTable">
							<thead>
								<tr>
									<th>服务名称</th>
									<th>服务范围</th>
									<th>服务价格</th>
									<th>服务简介</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${serviceDetails}" var="serviceDetail">
									<tr>
										<td >${serviceDetail.serviceName}</td>
										<td >${serviceDetail.serviceRangeName}</td>
										<td >${serviceDetail.price}</td>
										<td >${serviceDetail.serviceContent}</td>
										<td>
											<ul class="table_action_list">
												<li>
													<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
														删除
													</a>
												</li>
											</ul>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="ordinaryhouse_popshow.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>