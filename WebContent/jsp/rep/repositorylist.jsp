<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/repository.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
});

function addRepository(){
	$("#privName").val("");
	$("#privCode").val("");
	$("#privDesc").val("");
	$("#privId").val("");
	
	$("#privName").removeAttr("disabled");
	$("#privCode").removeAttr("disabled");
	
	jQuery('#addrepositorydiv').modal('show', {backdrop: 'static'});
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
					<h1 class="title">物库管理</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
						<li>
							<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
						</li>
						<li class="active">
							<strong>物库管理</strong>
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
						<a href="#" onclick="addRepository()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增物库</span>
							</button>
						</a>
					</div>
					<table class="table table-bordered table-striped" id="example-2">
						<thead>
							<tr>
								<th>物库名称</th>
								<th>物库类型</th>
								<th>描述</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:if test="${fn:length(repositoryList) > 0}">
								<c:forEach items="${repositoryList}" var="repo">
									<tr>
										<td title="name">${repo.repositoryName}</td>
										<td title="type">${repo.repositoryType}</td>
										<td title="desc">${repo.repositoryDesc}</td>
										<td>
											<a id="${repo.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyRepository">
												编辑
											</a>
											<a id="${repo.id}" class="btn btn-primary btn-sm btn-icon icon-left">
												用户列表
											</a>
											<a id="${repo.id}" class="btn btn-danger btn-sm btn-icon icon-left removeRepository">
												注销
											</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		</div>
    </div>
    <%@include file="../common/footer.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>