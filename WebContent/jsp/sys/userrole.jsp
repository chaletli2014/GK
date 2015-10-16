<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script>
function submitForm(){
	var $roles = jQuery('#role_div input[type="checkbox"].cbr');
	
	var checkedRoles = '';
	
	$roles.each(function(i, el){
		var $el = jQuery(el);
		if( $el.prop('checked') ){
			checkedRoles = checkedRoles + $el.val() + ",";
		}
	});
	
	$("#checkedRoles").val(checkedRoles);
	$("#userrole_form").submit();
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
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">用户角色列表</h1>
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
					<form class="form-horizontal" action="saveUserRole" method="post" id="userrole_form">
						<div class="form-group">
							<div class="panel-heading">
								<h3 class="panel-title">用户角色列表</h3>
							</div>
							<div class="form-block" id="role_div">
								<input type="hidden" id="checkedRoles" name="checkedRoles"/>
								<input type="hidden" id="userId" name="userId" value="${userId}"/>
								<c:forEach items="${allRoles}" var="role">
									<label>
										<input type="checkbox" class="cbr" name="userrole" id="userrole" value="${role.roleCode}" 
											<c:if test="${role.isOwn=='Y'}">checked</c:if> />
										${role.roleName}
									</label>
									<br />
								</c:forEach>
							</div>
						</div>
					</form>
					<div style="text-align: center;margin-top:20px;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>userlist">
							<button class="btn btn-info btn-icon">
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