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
						<a href="<%=basePath%>addserviceprovider">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>服务商名称</th>
								<th>服务商地址</th>
								<th>联系电话</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						<c:forEach items="${serviceproviders}" var="serviceprovider">
							<tr>
								<td title="${serviceprovider.name}">${serviceprovider.name}</td>
								<td title="${serviceprovider.address}">${serviceprovider.address}</td>
								<td>${serviceprovider.contractNumber}</td>
								<td>
									<a href="<%=basePath%>addserviceprovider?serviceId=${serviceprovider.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
										编辑
									</a>
									<a href="<%=basePath%>servicedetails?providerCode=${serviceprovider.code}" class="btn btn-orange btn-sm btn-icon icon-left">
										服务列表
									</a>
									<a href="<%=basePath%>serviceCustomer?providerCode=${serviceprovider.code}" class="btn btn-orange btn-sm btn-icon icon-left">
										客户列表
									</a>
									<%--
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										删除
									</a>
									 --%>
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