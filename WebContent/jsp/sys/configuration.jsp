<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function showEditDiv(){
	jQuery('#editDiv').modal('show', {backdrop: 'static'});
}
function checkValues(){
	var configCode = $("#configCode").val();
	
	jQuery.ajax({
		url: "<%=basePath%>checkIfConfigExists",
		data:{
			configCode : configCode
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
					<h1 class="title">配置项</h1>
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
							<strong>配置项管理</strong>
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
								null
							],
						});
					});
					</script>
					<div>
						<a href="#" onclick="showEditDiv()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增配置项</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>配置项编码</th>
								<th>配置项内容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:if test="${fn:length(allConfigs) > 0}">
								<c:forEach items="${allConfigs}" var="config">
									<tr>
										<td>${config.configCode}</td>
										<td>${config.configValue}</td>
										<td>
											<a href="#" class="btn btn-secondary btn-sm btn-icon icon-left">
												编辑
											</a>
											<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
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
					<h4 class="modal-title">配置项编辑</h4>
				</div>
				<form action="addconfig" method="post" class="validate" role="form" onsubmit="return checkMessage()">
				<input type="hidden" id="configId" name="configId"/>
				<div class="modal-body">
					<div class="content">
						<div class="panel-body">
							<div class="form-group">
								<span id="returnMessage" style="color:red;"></span>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="configCode">配置项编码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="configCode" name="configCode" data-validate="required" data-message-required="编码不能为空" onblur="checkValues()">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="configValue">配置项内容</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="configValue" name="configValue">
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