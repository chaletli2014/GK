<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/productlist.js"></script>
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
				<%@include file="../common/nav-title.jsp"%>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物库管理</a>
					</li>
					<li class="active">
						<strong>产品信息</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div>
						<div class="panel-heading">
							<h3 class="panel-title" style="line-height: 28px; margin-right:10px;">产品信息</h3>
							<a id="newProductLink">
								<button class="btn btn-icon btn-info">
									<li class="fa-plus-square"></li>
								</button>
							</a>
						</div>
						<div class="panel panel-color panel-success">
						<a href="#" data-toggle="panel">
						<div class="panel-heading" style="padding:10px 20px;margin-top:0px;">
							<h3 class="panel-title">服务类产品</h3>
							<div class="panel-options">
								<span class="collapse-icon">&ndash;</span>
								<span class="expand-icon">+</span>
							</div>
						</div>
						</a>
						<div class="panel-body">
						<table class="table table-bordered table-striped" id="productTable">
							<thead>
								<tr>
									<th>产品类目</th>
									<th>产品名称</th>
									<th>产品价格</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${products}" var="product">
									<tr>
										<td class="ptype" title="${product.productTypeDesc}">${product.productTypeDesc}</td>
										<td class="pname" title="${product.productNameDesc}">${product.productNameDesc}</td>
										<td class="pprice" title="${product.productPrice}">${product.productPrice}</td>
										<td class="remark" title="${product.remark}">${product.remark}</td>
										<td>
											<a id="${product.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyProduct">
												编辑
											</a>
											<a id="${product.id}" class="btn btn-danger btn-sm btn-icon icon-left removeProduct">
												删除
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						</div>
						<div class="panel panel-color panel-success">
						<a href="#" data-toggle="panel">
						<div class="panel-heading" style="padding:10px 20px;">
							<h3 class="panel-title">实物类产品-电梯</h3>
							<div class="panel-options">
								<span class="collapse-icon">&ndash;</span>
								<span class="expand-icon">+</span>
							</div>
						</div>
						</a>
						<div class="panel-body">
						<table class="table table-bordered table-striped" id="liftTable">
							<thead>
								<tr>
									<th>电梯品牌</th>
									<th>电梯用途</th>
									<th>电梯款型</th>
									<th>产品价格</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${lifts}" var="lift">
									<tr>
										<td class="liftBrand" title="${lift.liftBrand}">${lift.liftBrand}</td>
										<td class="liftPurpose" title="${lift.liftPurposeDesc}">${lift.liftPurposeDesc}</td>
										<td class="liftStyle" title="${lift.liftStyleDesc}">${lift.liftStyleDesc}</td>
										<td class="liftPrice" title="${lift.price}">${lift.price}</td>
										<td>
											<a id="${lift.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyProduct">
												编辑
											</a>
											<a id="${lift.id}" class="btn btn-danger btn-sm btn-icon icon-left removeProduct">
												删除
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="addProduct.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>