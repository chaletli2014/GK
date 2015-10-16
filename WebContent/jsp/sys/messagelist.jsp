<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script>
</script>
<body class="page-body" onload="checkMessage('${errorMessage}')">
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
					<h1 class="title">消息列表</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						<li>
							<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="#" onclick="javascript:void(0)">我的控制台</a>
						</li>
						<li class="active">
							<strong>我的消息</strong>
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
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>申请者名称</th>
								<th>申请角色</th>
								<th>申请关联的对象</th>
								<th>申请日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach items="${messagelist}" var="message">
								<tr>
									<td>${message.spName}</td>
									<td>${message.serviceTypeName}</td>
									<td>${message.orHouseName}</td>
									<td>${message.createDate}</td>
									<td>
										<c:if test="${message.status=='1'}">
											<a href="<%=basePath%>handleMessage?messageId=${message.id}&actionType=2" class="btn btn-secondary btn-sm btn-icon icon-left">
												同意关联
											</a>
											<a href="<%=basePath%>handleMessage?messageId=${message.id}&actionType=3" class="btn btn-orange btn-sm btn-icon icon-left">
												拒绝关联
											</a>
										</c:if>
										<c:if test="${message.status=='2'}">已同意</c:if>
										<c:if test="${message.status=='3'}">已拒绝</c:if>
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