<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addprofile_form input").val("");
}
function submitForm(){
	window.location.href = '<%=basePath%>myestate';
}
</script>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
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
					<h1 class="title">新增我的不动产</h1>
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
				<div class="panel-body">
					<form role="form" class="form-horizontal" action="saveprofile" method="post" id="addprofile_form">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-1">不动产名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="login_name" placeholder="" name="login_name" value="${userprofile.loginName}">
							</div>
						</div>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-link"></i>
							<span>新增</span>
						</button>
						<button class="btn btn-blue btn-icon" onclick="submitForm()">
							<i class="fa-share"></i>
							<span>返回</span>
						</button>
					</div>
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