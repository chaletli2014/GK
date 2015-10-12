<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script>
function disableUser(userId){
	if(confirm('是否确定注销该用户，注销后该用户不会继续显示在列表中，也不允许登录')){
		window.location.href="<%=basePath%>disableUser?userId="+userId;
	}
}
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
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">用户列表</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						<li>
							<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="#" onclick="javascript:void(0)">系统管理</a>
						</li>
						<li class="active">
							<strong>用户管理</strong>
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
								null
							],
						});
					});
					</script>
					<div>
						<a href="<%=basePath%>addprofile">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增用户</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>登录名</th>
								<th>用户名</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach items="${userlist}" var="user">
								<tr>
									<td>${user.loginName}</td>
									<td>${user.name}</td>
									<td>
										<a href="<%=basePath%>editprofile?userId=${user.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
											编辑
										</a>
										<%--
										<a href="<%=basePath%>userrole?userId=${user.id}" class="btn btn-orange btn-sm btn-icon icon-left">
											用户角色
										</a>
										 --%>
										<a class="btn btn-danger btn-sm btn-icon icon-left" onclick="disableUser('${user.id}')">
											注销
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