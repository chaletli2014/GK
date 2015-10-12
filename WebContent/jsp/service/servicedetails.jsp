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
        	<jsp:param name="actived" value="${actived}"/>
        	<jsp:param name="opened" value="${opened}"/>
        </jsp:include>
		<div class="main-content">
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">我的服务商列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">服务库管理</a>
					</li>
					<li class="active">
						<strong>我的服务库</strong>
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
								null
							],
						});
					});
					</script>
					<div>
						<a href="<%=basePath%>addservicedetail">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增</span>
							</button>
						</a>
						<a href="<%=basePath%>serviceprovider">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>服务分类</th>
								<th>服务范围</th>
								<th>服务价格</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						<c:forEach items="${serviceDetails}" var="serviceDetail">
							<tr>
								<td title="${serviceDetail.categoryName}">${serviceDetail.categoryName}</td>
								<td title="${serviceDetail.serviceName}">${serviceDetail.serviceName}</td>
								<td>${serviceDetail.price}</td>
								<td>
									<a href="<%=basePath%>addservicedetail?serviceDetailId=${serviceDetail.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
										编辑
									</a>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										删除
									</a>
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