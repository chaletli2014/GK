<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="newProductObjDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">产品编辑</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#tab_basic" data-toggle="tab">基本信息</a>
					</li>
					<li>
						<a href="#tab_main" data-toggle="tab">主要参数</a>
					</li>
					<li>
						<a href="#tab_source" data-toggle="tab">资料库</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<input type="hidden" id="productType" name="productType" value="${productType}">
				<input type="hidden" id="productId" name="productId" value="">
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="tab_basic">
						<div class="modal-body">
							<div class="content">
								<div class="label label-warning">注意：以下内容为必填项，若为空，无法提交</div>
								<div class="panel-body">
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productName">产品名称</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" data-validate="required" data-message-required="名称不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productBrand">品牌</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productBrand" name="productBrand" value="${product.productBrand}" data-validate="required" data-message-required="品牌不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productModel">型号</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productModel" name="productModel" value="${product.productModel}" data-validate="required" data-message-required="型号不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="itemCode">产品编码</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="itemCode" name="itemCode" value="${product.itemCode}" data-validate="required" data-message-required="产品编码不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productDom">生产日期</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productDom" name="productDom" value="${product.productDom}" data-validate="required" data-message-required="生产日期不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productQA">质保</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productQA" name="productQA" value="${product.productQA}" data-validate="required" data-message-required="质保不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="productPrice">产品价格</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="productPrice" name="productPrice" value="${product.productPrice}" data-validate="required" data-message-required="价格不能为空">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab_main">
					<%--
						<jsp:include page="../productRepo/lift_main.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					 --%>
					</div>
					<div class="tab-pane" id="tab_source">
						<%@include file="addProductSource.jsp" %>
					</div>
					<!-- Tabs Pager -->
					<div>
						<a id="productObjBtn">
							<button class="btn btn-success btn-icon btn-icon-standalone">
								<i class="fa-check-square-o"></i>
								<span>完成</span>
							</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
