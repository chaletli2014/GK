<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addservicedetail_form input").val("");
}
function submitForm(){
	$("#addservicedetail_form").submit();
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
			<form role="form" class="form-horizontal" action="saveorupdateservicedetail" method="post" id="addservicedetail_form">
			<input type="hidden" name="serviceDetailId" id="serviceDetailId" value="${serviceDetail.id}"/>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="categoryName">服务分类</label>
						<div class="col-sm-4">
							<input type="hidden" id="productCategory" name="productCategory"/>
							<div class="input-group">
								<input type="text" class="form-control" id="categoryName" name="categoryName" value="${serviceDetail.categoryName}" readonly="readonly"/>
								<span class="input-group-addon" onclick="showProductCategorys('<%=basePath%>')" style="cursor: pointer;"><i class="fa-keyboard-o"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="serviceCode">服务范围</label>
						<div class="col-sm-4">
							<c:if test="${fn:length(serviceCodes) > 0}">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#serviceCode").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="serviceCode" name="serviceCode">
									<option value="">---请选择---</option>
									<c:forEach items="${serviceCodes}" var="serviceCode">
										<option value="${serviceCode.dicCode}">${serviceCode.dicName}</option>
									</c:forEach>
								</select>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="price">服务价格</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="price" name="price" value="${serviceDetail.price}">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="serviceContent">服务内容</label>
						<div class="col-sm-6">
							<textarea class="form-control autogrow" cols="5" id="serviceContent" id="serviceContent" name="serviceContent" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 150px;">
								${serviceDetail.serviceContent}
							</textarea>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div style="text-align: center;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>serviceDetails">
							<button class="btn btn-info btn-icon">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
				</div>
			</div>
			</form>
			<%@include file="../common/footer.jsp" %>
		</div>
    </div>
    <%@include file="../common/categoryDiv.jsp" %>
    <jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>