<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="new_product_div">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">产品编辑</h4>
			</div>
			<input type="hidden" name="productId" id="productId">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="productType">产品类目</label>
							<div class="col-sm-6">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#productType").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="productType" name="productType">
									<option value="">---请选择产品类目---</option>
									<c:forEach items="${productTypes}" var="productTypeDic">
										<option value="${productTypeDic.dicCode}">${productTypeDic.dicName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="productName">产品名称</label>
							<div class="col-sm-6">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#productName").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="productName" name="productName">
									<option value="">---请选择产品名称---</option>
									<c:forEach items="${productNameDics}" var="productNameDic">
										<option value="${productNameDic.dicCode}">${productNameDic.dicName}</option>
									</c:forEach>
									<option value="other">其它</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="productPrice">产品价格</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="productPrice" name="productPrice" value="${product.productPrice}" data-validate="required" data-message-required="价格不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="remark">备注</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="remark" name="remark" value="${product.remark}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div style="text-align: center;">
						<button class="btn btn-success" id="addNewProductBtn">提交</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
