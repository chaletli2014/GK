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
						<strong><%=repoPreName%>信息</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div>
						<div class="panel-heading">
							<h3 class="panel-title" style="line-height: 28px; margin-right:10px;"><%=repoPreName%>信息</h3>
						</div>
						<div class="panel panel-color panel-success">
						<a href="#" data-toggle="panel">
						<div class="panel-heading" style="padding:10px 20px;">
							<h3 class="panel-title">实物类<%=repoPreName%></h3>
							<div class="panel-options">
								<span class="collapse-icon">&ndash;</span>
								<span class="expand-icon">+</span>
							</div>
						</div>
						</a>
						<div class="panel-body">
						<table class="table table-bordered table-striped" id="productObjTable">
							<thead>
								<tr>
									<th width="20%">产品类型</th>
									<th>名称</th>
									<th>品牌</th>
									<th>型号</th>
									<th>编码</th>
									<th>生产日期</th>
									<th>质保</th>
									<th>价格</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${productObjs}" var="obj">
									<tr>
										<td class="productType" title="${obj.productTypeDesc}">${obj.productTypeDesc}</td>
										<td class="productName" title="${obj.productName}">${obj.productName}</td>
										<td class="productBrand" title="${obj.productBrand}">${obj.productBrand}</td>
										<td class="productModel" title="${obj.productModel}">${obj.productModel}</td>
										<td class="itemCode" title="${obj.itemCode}">${obj.itemCode}</td>
										<td class="productDom" title="${obj.productDom}">${obj.productDom}</td>
										<td class="productQA" title="${obj.productQA}">${obj.productQA}</td>
										<td class="productPrice" title="${obj.productPrice}">${obj.productPrice}</td>
										<td>
											<a id="${obj.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyProductObj">
												编辑
											</a></br></br>
											<a id="${obj.id}" class="btn btn-danger btn-sm btn-icon icon-left removeProductObj">
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
						<div class="panel-heading" style="padding:10px 20px;margin-top:0px;">
							<h3 class="panel-title">服务类<%=repoPreName%></h3>
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
									<th><%=repoPreName%>类目</th>
									<th><%=repoPreName%>名称</th>
									<th><%=repoPreName%>价格</th>
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
											<a id="${product.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyServiceP">
												编辑
											</a>
											<a id="${product.id}" class="btn btn-danger btn-sm btn-icon icon-left removeServiceP">
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
	<%@include file="addProductObj.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>