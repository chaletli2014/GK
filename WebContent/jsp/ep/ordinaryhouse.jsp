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
					<h1 class="title">不动产列表</h1>
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
						<strong>不动产列表</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-2").dataTable({
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
					<div>
						<a href="<%=basePath%>addordinaryhouse">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增不动产</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
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
									<td >${orHouse.buildingName}</td>
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
													设备列表
												</a>
											</li>
											<li>
												<a href="<%=basePath%>houseSP?orHouseId=${orHouse.id}" class="btn btn-info btn-sm btn-icon icon-left">
													服务商维护
												</a>
											</li>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
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