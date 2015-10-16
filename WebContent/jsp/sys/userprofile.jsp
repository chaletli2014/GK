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
						<div class="form-group">
							<label class="col-sm-2 control-label" for="hasHouse">是否拥有不动产</label>
							<div class="col-sm-4">
								<input type="checkbox" class="iswitch iswitch-secondary" name="hasHouse" <c:if test="${userprofile.hasHouse=='on'}">checked</c:if> >
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="hasService">是否提供服务</label>
							<div class="col-sm-4">
								<input type="checkbox" class="iswitch iswitch-secondary" name="hasService" <c:if test="${userprofile.hasService=='on'}">checked</c:if>>
							</div>
						</div>
						<%--
						<div class="form-group">
							<label class="col-sm-2 control-label" for="field-1">用户类型</label>
							<div class="col-sm-10">
								<script type="text/javascript">
									jQuery(document).ready(function($){
										$("#level").selectBoxIt().on('open', function(){
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="level" name="level">
									<option value="1">产品库使用者</option>
									<option value="2">物品库使用者</option>
									<option value="3">流通库使用者</option>
									<option value="4">服务库使用者</option>
								</select>
							</div>
						</div>
						 --%>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>userlist">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
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