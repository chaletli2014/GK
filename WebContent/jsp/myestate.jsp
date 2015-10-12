<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript">
function searchestate(estateName){
	$("#hide_div").css("display","block");
	$.ajax({ 
        type: "get",
        url: "checkifestateexsits", 
        contentType:'application/json',
        dataType:'json',
        data:{
        	estateName:estateName
        },
        success: function (data) {
        	$("#hide_div").css("display","none");
        	$("#info_message").html(data.message);
        	$("#info_message").css("display","block");
        	if( data.isExists == 'Y' ){
	        	$("#info_message").removeClass("alert-danger");
	        	$("#info_message").addClass("alert-success");
        	}else{
	        	$("#info_message").removeClass("alert-success");
	        	$("#info_message").addClass("alert-danger");
        	}
        }, 
        error: function (XMLHttpRequest, textStatus, errorThrown) { 
        	$("#hide_div").css("display","none");
       	} 
	});
}
</script>
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
					<h1 class="title">不动产列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">不动产物品库管理</a>
					</li>
					<li class="active">
						<strong>我的所有不动产</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
			<div id="hide_div">
				<div id="loading_div">
					产品检索中。。。
				</div>
			</div>
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#example-2").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							aoColumns: [
								null,
								null,
								{bSortable: false}
							],
						});
					});
					</script>
					<div>
						<a href="<%=basePath%>addmyestate" class="btn btn-info btn-sm btn-icon icon-left">新增不动产</a>
						<span id="info_message" style="display:none;"></span>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>不动产名称</th>
								<th>不动产类别</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<tr>
								<td title="明凯大厦">明凯大厦</td>
								<td title="商务办公楼（写字楼）">商务办公楼（写字楼）</td>
								<td>
									<a onclick="searchestate('a')" class="btn btn-secondary btn-sm btn-icon icon-left">
										检索
									</a>
								</td>
							</tr>
							<tr>
								<td title="汇阳广场">汇阳广场</td>
								<td title="商务办公楼（写字楼）">商务办公楼（写字楼）</td>
								<td>
									<a onclick="searchestate('b')" class="btn btn-secondary btn-sm btn-icon icon-left">
										检索
									</a>
								</td>
							</tr>
							<tr>
								<td title="上海万达广场（南汇）">上海万达广场（南汇）</td>
								<td title="商务办公楼（写字楼）">商务办公楼（写字楼）</td>
								<td>
									<a href="<%=basePath%>myestatedetail" class="btn btn-success btn-sm btn-icon icon-left">
										查看
									</a>
									<a href="#" class="btn btn-warning btn-sm btn-icon icon-left">
										设备巡检
									</a>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
										断开连接
									</a>
								</td>
							</tr>
							<tr>
								<td title="由由小区">由由小区一村</td>
								<td title="普通住宅">普通住宅</td>
								<td>
									<a onclick="searchestate('b')" class="btn btn-secondary btn-sm btn-icon icon-left">
										检索
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