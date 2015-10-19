<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#userprofile_form input").val("");
}
function submitForm(){
	var loginName = $("#login_name").val();
	var password = $("#password").val();
	if( null == password || '' == password ){
		jAlert("登录密码不能为空","提醒");
		return false;
	}
	if( null == loginName || '' == loginName ){
		jAlert("登录名不能为空","提醒");
		return false;
	}
	
	jQuery.ajax({
		url: "<%=basePath%>checkAccount",
		data:{
			login_name : loginName,
			password : password
		}
		,success: function(response){
			var result = response.result;
			if( result != 'Y' && '' != $("#userId").val() ){
				jAlert("登录密码错误，无法修改信息","提醒");
				return false;
			}else{
				$("#userprofile_form").submit();
			}
		}
	});
}
$(document).ready(function(){
	var userId = $("#userId").val();
	if( userId == '' ){
		$("#realName").val('');
	}
});
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
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">用户编辑</h1>
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
					<form role="form" class="form-horizontal" action="saveOrUpdateprofile" method="post" id="userprofile_form">
						<input type="hidden" id="userId" name="userId" value="${userprofile.id}"/>
						<div class="row">
							<div class="col-md-12">
								<ul class="nav nav-tabs nav-tabs-justified">
									<li class="active">
										<a href="#userinfo" data-toggle="tab">
											<span class="visible-xs"><i class="fa-home"></i></span>
											<span class="hidden-xs">用户信息</span>
										</a>
									</li>
									<li>
										<a href="#companyinfo" data-toggle="tab">
											<span class="visible-xs"><i class="fa-user"></i></span>
											<span class="hidden-xs">公司信息</span>
										</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane active" id="userinfo">
										<div class="form-group">
											<label class="col-sm-2 control-label" for="login_name">登录名</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="login_name" placeholder="请输入登录名" name="login_name" value="${userprofile.loginName}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="password">登录密码</label>
											<div class="col-sm-10">
												<input type="password" class="form-control" id="password" placeholder="请输入登录密码" name="password">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="newPassword">新密码</label>
											<div class="col-sm-10">
												<input type="password" class="form-control" id="newPassword" placeholder="新密码为空代表不修改原密码" name="newPassword">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="realName">真实姓名</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="realName" placeholder="请输入真实姓名" name="realName" value="${userprofile.name}" autocomplete="off">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="telephone">手机号码</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="telephone" placeholder="请输入手机号码" name="telephone" value="${userprofile.telephone}" autocomplete="off">
											</div>
										</div>
									</div>
									<div class="tab-pane" id="companyinfo">
										<div class="form-group">
											<label class="col-sm-2 control-label" for="companyName">公司名称</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="companyName" placeholder="请输入公司名称" name="companyName">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="companyTel">联系电话</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="companyTel" placeholder="请输入联系电话" name="companyTel">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label" for="newPassword">公司简介</label>
											<div class="col-sm-10">
												<input type="password" class="form-control" id="companyInfo" placeholder="" name="companyInfo">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>index">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>返回</span>
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