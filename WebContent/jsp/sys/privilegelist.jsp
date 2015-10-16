<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$(".deletePrivilege").click(function(e){
		if(confirm("是否删除该权限?")){
			window.location.href = "<%=basePath%>deletePriv?privId="+this.id;
		}
	});
	$(".modifyPrivilege").click(function(e){
		jQuery.ajax({
			url: "<%=basePath%>getPrivilegeById",
			data:{
				privId : this.id
			}
			,success: function(response){
				if( response.status == 'failure' ){
					alert("获取权限信息失败");
				}else{
					$("#privName").val(response.obj.privilegeName);
					$("#privName").attr("disabled", "disabled");
					$("#privCode").val(response.obj.privilegeCode);
					$("#privCode").attr("disabled", "disabled");
					
					$("#privDesc").val(response.obj.privilegeDesc);
					$("#privId").val(response.obj.id);
					jQuery('#editDiv').modal('show', {backdrop: 'static'});
				}
			}
		});
	});
});

function addNewPrivilege(){
	$("#privName").val("");
	$("#privCode").val("");
	$("#privDesc").val("");
	$("#privId").val("");
	
	$("#privName").removeAttr("disabled");
	$("#privCode").removeAttr("disabled");
	
	jQuery('#editDiv').modal('show', {backdrop: 'static'});
}
function checkValues(){
	var privName = $("#privName").val();
	
	jQuery.ajax({
		url: "<%=basePath%>checkIfPrivilegeNameExists",
		data:{
			privName : privName
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
function checkPrivilegeCode(){
	var privCode = $("#privCode").val();
	
	jQuery.ajax({
		url: "<%=basePath%>checkIfPrivilegeCodeExists",
		data:{
			privCode : privCode
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
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">权限管理</h1>
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
							<strong>权限管理</strong>
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
						<a href="#" onclick="addNewPrivilege()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增权限</span>
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
							<c:if test="${fn:length(allPrivs) > 0}">
								<c:forEach items="${allPrivs}" var="priv">
									<tr>
										<td>${priv.privilegeName}</td>
										<td>${priv.privilegeCode}</td>
										<td>${priv.privilegeDesc}</td>
										<td>
											<a id="${priv.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyPrivilege">
												编辑
											</a>
											<a id="${priv.id}" class="btn btn-danger btn-sm btn-icon icon-left deletePrivilege">
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
					<h4 class="modal-title">权限编辑</h4>
				</div>
				<form action="addOrUpdatePrivilege" method="post" class="validate" role="form" onsubmit="return checkMessage()">
				<input type="hidden" id="privId" name="privId"/>
				<div class="modal-body">
					<div class="content">
						<div class="panel-body">
							<div class="form-group">
								<span id="returnMessage" style="color:red;"></span>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">权限名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="privName" name="privName" data-validate="required" data-message-required="名称不能为空">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">权限编码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="privCode" name="privCode" data-validate="required" data-message-required="编码不能为空">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="field-1">权限描述</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="privDesc" name="privDesc">
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