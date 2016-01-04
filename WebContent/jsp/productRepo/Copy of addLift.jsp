<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="newLiftDiv">
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
							<label class="col-sm-4 control-label form_input_required" for="liftBrand">电梯品牌</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liftBrand" name="liftBrand" value="${lift.liftBrand}" data-validate="required" data-message-required="品牌不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="liftPurpose">用途</label>
							<div class="col-sm-6">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#liftPurpose").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="liftPurpose" name="liftPurpose">
									<option value="">---请选择电梯用途---</option>
									<c:forEach items="${liftPurpose}" var="liftPurposeDic">
										<option value="${liftPurposeDic.dicCode}">${liftPurposeDic.dicName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="liftStyle">款型</label>
							<div class="col-sm-6">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#liftStyle").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="liftStyle" name="liftStyle">
									<option value="">---请选择电梯款型---</option>
									<c:forEach items="${liftStyle}" var="liftStyleDic">
										<option value="${liftStyleDic.dicCode}">${liftStyleDic.dicName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="liftCT">载重量</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liftCT" name="liftCT" value="${lift.liftCT}" data-validate="required" data-message-required="载重量不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="liftNS">额定速度</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liftNS" name="liftNS" value="${lift.liftNS}" data-validate="required" data-message-required="额定速度不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="liftQA">质保</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="liftQA" name="liftQA" value="${lift.liftQA}" data-validate="required" data-message-required="质保不能为空">
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
