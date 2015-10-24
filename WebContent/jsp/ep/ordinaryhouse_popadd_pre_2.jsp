<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/ordinaryhouse_pre.js"></script>
<body class="page-body">
	<input type="hidden" id="basePath" value="<%=basePath%>">
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
					<h1 class="title">产品列表</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物库管理</a>
					</li>
					<li class="active">
						<strong>产品列表</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default" style="padding-top:0px;">
				<div class="panel-body">
					<div id="editProduct_pre">
						<div class="modal-dialog" style="width:70%">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title">选择产品类型</h4>
								</div>
								<div style="margin:10px;padding:20px;height:300px;">
									<div style="float:left;" class="product_select_pre">
										<a class="product_link" title="newProductCategory1">添加实物产品</a>
									</div>
									<div style="float:left;" class="product_select_pre">
										<div style="text-align: center;font-weight: bold;font-size: 14px;padding:4px;height:30%">
											<span>非实物产品注册</span>
										</div>
										<div style="margin:14px 0px;height:30%">
											<script type="text/javascript">
												jQuery(document).ready(function($)
												{
													$("#serviceCategory").selectBoxIt().on('open', function()
													{
														$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
													});
												});
											</script>
											<select class="form-control" id="serviceCategory" name="serviceCategory">
												<option value="">请选择非实物分类</option>
												<c:forEach items="${goodsCategory}" var="gc">
													<option value="${gc.dicCode}">${gc.dicName}</option>
												</c:forEach>
											</select>
										</div>
										<div style="height:30%;text-align: center;">
											<a href="javascript:void(0)" id="addNewServiceLink">
												<button class="btn btn-purple btn-icon btn-icon-standalone">
													<i class="fa-cog"></i>
													<span>新增产品</span>
												</button>
											</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="service_popadd.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>