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
					<h1 class="title">乘客电梯列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">动产产品库管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">机器电子电气设备</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">特种设备</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">电梯</a>
					</li>
					<li class="active">
						<strong>乘客电梯</strong>
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
						<a href="<%=basePath%>addpassengerlift" class="btn btn-info btn-sm btn-icon icon-left">新增</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>设备名称</th>
								<th>规格型号</th>
								<th>制造厂家</th>
								<th>制造日期</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						<c:forEach items="${lifts}" var="lift">
							<tr>
								<td title="${lift.name}">${lift.name}</td>
								<td title="${lift.model}">${lift.model}</td>
								<td>${lift.manufacturer}</td>
								<td>${lift.madeDate}</td>
								<td>
									<a href="<%=basePath%>addpassengerlift?liftId=${lift.id}" class="btn btn-secondary btn-sm btn-icon icon-left">
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
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>