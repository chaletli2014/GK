<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".deleteRole").click(function(e){
		if(confirm("是否删除该角色?")){
			window.location.href = "<%=basePath%>deleteRole?roleId="+this.id;
		}
	});
	$(".rolePriv").click(function(e){
		window.location.href = "<%=basePath%>rolePrivs?roleCode="+this.id;
	});
	
	$(".modifyRole").click(function(e){
		jQuery.ajax({
			url: "<%=basePath%>getRoleById",
			data:{
				roleId : this.id
			}
			,success: function(response){
				if( response.status == 'failure' ){
					alert("获取角色信息失败");
				}else{
					$("#roleName").val(response.obj.roleName);
					$("#roleName").attr("disabled", "disabled");
					$("#roleCode").val(response.obj.roleCode);
					$("#roleCode").attr("disabled", "disabled");
					
					$("#roleDesc").val(response.obj.roleDesc);
					$("#roleId").val(response.obj.id);
					jQuery('#editDiv').modal('show', {backdrop: 'static'});
				}
			}
		});
	});
});

function addNewRole(){
	$("#roleName").val("");
	$("#roleCode").val("");
	$("#roleDesc").val("");
	$("#roleId").val("");
	
	$("#roleName").removeAttr("disabled");
	$("#roleCode").removeAttr("disabled");
	
	jQuery('#editDiv').modal('show', {backdrop: 'static'});
}
function checkValues(){
	var roleName = $("#roleName").val();
	
	jQuery.ajax({
		url: "<%=basePath%>checkIfRoleNameExists",
		data:{
			roleName : roleName
		}
		,success: function(response){
			if( response.status == 'failure' ){
				$("#returnMessage").html(response.info_message);
			}else{
				$("#returnMessage").html('');
			}
		}
	});
}
function checkRoleCode(){
	var roleCode = $("#roleCode").val();
	
	jQuery.ajax({
		url: "<%=basePath%>checkIfRoleCodeExists",
		data:{
			roleCode : roleCode
		}
		,success: function(response){
			if( response.status == 'failure' ){
				$("#returnMessage").html(response.info_message);
			}else{
				$("#returnMessage").html('');
			}
		}
	});
}
function checkMessage(){
	if( $("#returnMessage").html() != ''){
		return false;
	}
}
</script>
<style>
#editDiv .modal-body .content div.panel-body div{
	margin:10px 0px;
}
</style>
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
					<h1 class="title">角色管理</h1>
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
							<strong>角色管理</strong>
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
								null
							],
						});
					});
					</script>
					<div>
						<a href="#" onclick="addNewRole()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增角色</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>名称</th>
								<th>编码</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:if test="${fn:length(allRoles) > 0}">
								<c:forEach items="${allRoles}" var="role">
									<tr>
										<td>${role.roleName}</td>
										<td>${role.roleCode}</td>
										<td>${role.roleDesc}</td>
										<td>
											<a id="${role.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyRole">
												编辑
											</a>
											<%--
											<a id="${role.roleCode}" class="btn btn-secondary btn-sm btn-icon icon-left rolePriv">
												权限列表
											</a>
											 --%>
											<a id="${role.id}" class="btn btn-danger btn-sm btn-icon icon-left deleteRole">
												删除
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<!-- Main Footer -->
			<%@include file="../common/footer.jsp" %>
		</div>
    </div>
    <div class="modal fade" id="editDiv">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">角色编辑</h4>
				</div>
				<form action="addOrUpdateRole" method="post" class="validate" role="form" onsubmit="return checkMessage()">
				<input type="hidden" id="roleId" name="roleId"/>
				<div class="modal-body">
					<div class="content">
						<div class="panel-body">
							<div class="form-group">
								<span id="returnMessage" style="color:red;"></span>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">角色名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="roleName" name="roleName" data-validate="required" data-message-required="名称不能为空">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">角色编码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="roleCode" name="roleCode" data-validate="required" data-message-required="编码不能为空">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">角色描述</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="roleDesc" name="roleDesc">
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">提交</button>
					<button type="reset" class="btn btn-white">清空</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>