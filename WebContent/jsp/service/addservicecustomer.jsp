<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function submitForm(){
	$("#addcustomer_form").submit();
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
					<h1 class="title">新增客户</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物链管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">客户管理</a>
					</li>
					<li class="active">
						<strong>新增客户</strong>
					</li>
					</ol>
				</div>
			</div>
			<form role="form" class="form-horizontal" action="saveSPCustomer" method="post" id="addcustomer_form">
			<input type="hidden" name="spCode" id="spCode" value="${serviceProvider.code}"/>
			<input type="hidden" name="spCustomerId" id="spCustomerId" value="${spCustomer.id}"/>
			<div class="row">
				<div class="col-md-12">
					<div class="tab-content">
						<div class="tab-pane active" id="home-1">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="serviceCode">服务范围</label>
								<div class="col-sm-4">
									<c:if test="${fn:length(serviceTypes) > 0}">
										<script type="text/javascript">
											jQuery(document).ready(function($)
											{
												$("#serviceTypeCode").selectBoxIt().on('open', function()
												{
													$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
												});
											});
										</script>
										<select class="form-control" id="serviceTypeCode" name="serviceTypeCode">
											<option value="">---请选择---</option>
											<c:forEach items="${serviceTypes}" var="serviceType">
												<option value="${serviceType.dicCode}">${serviceType.dicName}</option>
											</c:forEach>
										</select>
									</c:if>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="customerName">客户名称</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="customerName" name="customerName" value="${spCustomer.customerName}">
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
					<a href="<%=basePath%>serviceCustomer">
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
    <%@include file="../common/categoryDiv.jsp" %>
    <jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>