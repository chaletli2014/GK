<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addgoodsservice_form input").val("");
}
function submitForm(){
	$("#addgoodsservice_form").submit();
}
</script>
<body class="page-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="../common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="sideBarTopParent" value="${sideBarTopParent}"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">新增服务商</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">服务库管理</a>
					</li>
					<li class="active">
						<strong>我的服务库</strong>
					</li>
					</ol>
				</div>
			</div>
			<form role="form" class="form-horizontal" action="saveorupdateservice" method="post" id="addgoodsservice_form">
			<input type="hidden" name="serviceId" id="serviceId" value="${goodsService.id}"/>
			<div class="row">
				<div class="col-md-12">
					<div class="tab-content">
						<div class="tab-pane active" id="home-1">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="serviceName">服务商名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="serviceName" name="serviceName" value="${serviceProvider.name}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="address">服务商地址</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="address" name="address" value="${serviceProvider.address}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="telephone">手机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="telephone" name="telephone" value="${serviceProvider.telephone}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="contractNumber">联系电话</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="contractNumber" name="contractNumber" value="${serviceProvider.contractNumber}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="serviceContent">服务内容</label>
								<div class="col-sm-3">
									<textarea rows="6" cols="100" id="serviceContent" name="serviceContent">
										${serviceProvider.serviceContent}
									</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</form>
			<div class="panel-body">
				<div style="text-align: center;">
					<button class="btn btn-success btn-icon" onclick="submitForm()">
						<i class="fa-check"></i>
						<span>提交</span>
					</button>
					<a href="<%=basePath%>serviceprovider">
						<button class="btn btn-info btn-icon">
							<i class="fa-retweet"></i>
							<span>返回</span>
						</button>
					</a>
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