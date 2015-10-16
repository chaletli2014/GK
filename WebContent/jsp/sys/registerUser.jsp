<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#userprofile_form input").val("");
}
function submitForm(){
	$("#userprofile_form").submit();
}
$(document).ready(function(){
	var userId = $("#userId").val();
	if( userId == '' ){
		$("#name").val('');
	}
});
</script>
<body class="page-body">
	<div class="page-container">
		<div class="main-content">
			<div class="page-title" style="background: #2c2e2f;color:white;font-weight: bold;">
				<div class="title-env">
					<img src="<%=basePath%>images/logo-blue.png" width="100" height="40" alt="" style="cursor: pointer;">
				</div>
				<div class="title-env">
					<h1 class="title">用户注册</h1>
				</div>
			</div>
			<div class="panel panel-default" style="width:70%;margin:auto auto;">
				<div class="panel-body">
					<form role="form" class="form-horizontal" action="doRegisterUser" method="post" id="userprofile_form">
						<div class="form-group">
							<label class="col-sm-2 control-label required" for="login_name">登录名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="login_name" placeholder="请输入登录名" name="login_name" onblur="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="password">登录密码</label>
							<div class="col-sm-4">
								<input type="password" class="form-control" id="password" placeholder="请输入登录密码" name="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="name">真实姓名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="name" placeholder="请输入真实姓名" name="name" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="telephone">手机号码</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="telephone" placeholder="请输入手机号码" name="telephone" autocomplete="off">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="hasHouse">是否拥有不动产</label>
							<div class="col-sm-4">
								<input type="checkbox" class="iswitch iswitch-secondary" name="hasHouse">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="hasService">是否提供服务</label>
							<div class="col-sm-4">
								<input type="checkbox" class="iswitch iswitch-secondary" name="hasService">
							</div>
						</div>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>回到登录</span>
							</button>
						</a>
					</div>
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