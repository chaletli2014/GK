<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function deleteCustomer(spCustomerId){
	jConfirm("确定要删除关联客户吗？一旦删除，客户将消失","提醒",function(r){
		if( r ){
			window.location.href = "<%=basePath%>deleteSPCustomer?spCustomerId="+spCustomerId;
		}
	});
}
function relateCustomer(spCustomerId){
	jConfirm("确定要发申请给该客户进行关联吗？","提醒",function(r){
		if( r ){
			jQuery.ajax({
				url: "<%=basePath%>sendRequesttoCustomer",
				data:{
					spCustomerId : spCustomerId
				}
				,success: function(response){
					var result = response.result;
					var message = response.message;
					if( result != 'Y' ){
						if( '' != message ){
							jAlert(message,"提醒");
						}else{
							jAlert("请求发送失败!","提醒");
						}
					}else{
						jAlert("请求发送成功","信息");
						location.reload();
					}
				}
			});
		}
	});
}
</script>
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
					<h1 class="title">${serviceProvider.name} 客户列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">服务信息管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">服务商列表</a>
					</li>
					<li class="active">
						<strong>客户列表</strong>
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
						<a href="<%=basePath%>addserviceCustomer">
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
								<th>客户名称</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						<c:forEach items="${serviceCustomers}" var="serviceCustomer">
							<tr>
								<td title="${serviceCustomer.categoryName}">${serviceCustomer.categoryName}</td>
								<td title="${serviceCustomer.serviceTypeName}">${serviceCustomer.serviceTypeName}</td>
								<td title="${serviceCustomer.customerName}">${serviceCustomer.customerName}</td>
								<td>${serviceCustomer.status}</td>
								<td>
									<c:if test="${serviceCustomer.status=='已关联'}">
										<a href="javascript:void(0)" onclick="deleteCustomer(${serviceCustomer.id})" class="btn btn-danger btn-sm btn-icon icon-left">
											取消关联
										</a>
									</c:if>
									<c:if test="${serviceCustomer.status=='新建' || serviceCustomer.status=='已断开'}">
										<a href="javascript:void(0)" onclick="relateCustomer(${serviceCustomer.id})" class="btn btn-danger btn-sm btn-icon icon-left">
											申请关联
										</a>
									</c:if>
									<c:if test="${serviceCustomer.status=='审核中'}">
										<a href="javascript:void(0)" onclick="recallRequest(${serviceCustomer.id})" class="btn btn-danger btn-sm btn-icon icon-left">
											取消申请
										</a>
									</c:if>
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