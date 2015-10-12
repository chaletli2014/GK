<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<jsp:include page="common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">写字楼列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">不动产产品库管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">商业物业</a>
					</li>
					<li class="active">
						<strong>商务办公楼（写字楼）</strong>
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
						<a href="<%=basePath%>addofficebuilding">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增写字楼</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>写字楼名称</th>
								<th>开发商</th>
								<th>竣工年月</th>
								<th>总楼层数</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<tr>
								<td title="明凯大厦">明凯大厦</td>
								<td title="中铁置业和市北高新联合投资开发">中铁置业和市北高新联...</td>
								<td>2011年1月</td>
								<td>1栋 16层</td>
								<td>
									<a href="<%=basePath%>addofficebuilding" class="btn btn-secondary btn-sm btn-icon icon-left">
										编辑
									</a>
									<a href="<%=basePath%>officebuildingdevice" class="btn btn-info btn-sm btn-icon icon-left">
										设备库维护
									</a>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										删除
									</a>
								</td>
							</tr>
							<tr>
								<td title="汇阳广场">汇阳广场</td>
								<td title="上海华友房地产开发有限公司">上海华友房地产开发有...</td>
								<td>2004年1月</td>
								<td>2栋 28层</td>
								<td>
									<a href="#" class="btn btn-secondary btn-sm btn-icon icon-left">
										编辑
									</a>
									<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
										设备库维护
									</a>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										删除
									</a>
								</td>
							</tr>
							<tr>
								<td>测试</td>
								<td>---</td>
								<td>2012年1月</td>
								<td>1栋 21层</td>
								<td>
									<a href="#" class="btn btn-secondary btn-sm btn-icon icon-left">
										编辑
									</a>
									<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
										设备库维护
									</a>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										删除
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<%@include file="common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>