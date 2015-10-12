<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addpassengerlift_form input").val("");
}
function submitForm(){
	$("#addpassengerlift_form").submit();
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
					<h1 class="title">新增乘客电梯</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">动产产品库管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">机器电子电气设备</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">特种设备</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">电梯</a>
					</li>
					<li class="active">
						<strong>乘客电梯</strong>
					</li>
					</ol>
				</div>
			</div>
			<form role="form" class="form-horizontal" action="saveorupdatepl" method="post" id="addpassengerlift_form">
			<input type="hidden" name="liftId" id="liftId" value="${lift.id}"/>
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-tabs nav-tabs-justified">
						<li class="active">
							<a href="#home-1" data-toggle="tab">
								<span class="visible-xs"><i class="fa-home"></i></span>
								<span class="hidden-xs">基本参数</span>
							</a>
						</li>
						<li>
							<a href="#profile-1" data-toggle="tab">
								<span class="visible-xs"><i class="fa-user"></i></span>
								<span class="hidden-xs">详细参数</span>
							</a>
						</li>
					</ul>
					
					<div class="tab-content">
							<div class="tab-pane active" id="home-1">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="liftName">设备名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="liftName" name="liftName" value="${lift.name}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="companyCode">单位编号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="companyCode" name="companyCode" value="${lift.companyCode}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="model">规格型号</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="model" name="model" value="${lift.model}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="manufacturer">制造厂家</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="manufacturer" name="manufacturer" value="${lift.manufacturer}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="madeDate">制造日期</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="madeDate" name="madeDate" value="${lift.madeDate}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="mainArguments">主要参数</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="mainArguments" name="mainArguments" value="${lift.mainArguments}">
									</div>
								</div>
							</div>
							<div class="tab-pane" id="profile-1">
								
							</div>
					</div>
				</div>
				</div>
				</form>
			<div class="panel panel-default">
				<div class="panel-body">
					<div style="text-align: center;">
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
			<%@include file="../common/footer.jsp" %>
		</div>
    </div>
    <jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>