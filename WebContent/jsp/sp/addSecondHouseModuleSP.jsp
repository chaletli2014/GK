<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="new_second_module_sp_div">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">二级${spTypeName}编辑</h4>
			</div>
			<form role="form" class="form-horizontal" action="saveOrUpdateSecondHouseSP" method="post">
			<input type="hidden" name="spTypeCode" value="${spTypeCode}">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="moduleType">托管组件</label>
							<div class="col-sm-4">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#secondModuleType").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="secondModuleType" name="secondModuleType">
									<option value="">请选择设备类别</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="spName">名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="spName" name="spName" value="${houseSP2nd.moduleSPValue}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="remark">备注</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="remark" name="remark" value="${houseSP2nd.remark}">
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
