<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addcategory_form input").val("");
}
function submitForm(){
	window.location.href = '<%=basePath%>userlist';
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
					<h1 class="title">用户新增</h1>
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
					<form role="form" class="form-horizontal" action="saveprofile" method="post" id="addcategory_form">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-1">分类名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="login_name" placeholder="分类名称" name="login_name" value="${userprofile.loginName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-1">分类编码</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name" placeholder="分类编码" name="name" value="${userprofile.name}">
							</div>
						</div>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<button class="btn btn-red btn-icon btn-icon-standalone" onclick="clearform()">
							<i class="fa-magic"></i>
							<span>清空</span>
						</button>
					</div>
				</div>
			</div>
			<!-- Main Footer -->
			<%@include file="../common/footer.jsp" %>
		</div>
    </div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>