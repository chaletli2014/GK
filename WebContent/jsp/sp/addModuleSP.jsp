<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="new_module_sp_div">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">组件商编辑</h4>
			</div>
			<form role="form" class="form-horizontal" action="saveOrUpdateModuleSP" method="post">
			<input type="hidden" name="moduleSPId" value="${moduleSPId}">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="moduleType">组件名称</label>
							<div class="col-sm-4">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#moduleType").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="moduleType" name="moduleType">
									<option value="">---请选择组件---</option>
									<option value="dt" title="">电梯</option>
									<option value="sb" title="">水泵</option>
									<option value="sx" title="">水箱</option>
									<option value="bl" title="">避雷设备</option>
									<option value="xf" title="">消防设备</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="spName">组件商名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="spName" name="spName" value="${moduleSP.moduleSPName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="remark">备注</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="remark" name="remark" value="${moduleSP.remark}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div style="text-align: center;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
