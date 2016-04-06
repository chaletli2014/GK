<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="new_module_sp_div">
	<div class="modal-dialog" style="width:60%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">编辑</h4>
			</div>
			<form role="form" class="form-horizontal" action="saveOrUpdateModuleSP" method="post">
			<input type="hidden" name="moduleSPId" id="moduleSPId">
			<input type="hidden" name="spTypeCode_h" id="spTypeCode_h" >
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="brandCode">产品与服务</label>
							<div class="col-sm-6">
								<script type="text/javascript">
									jQuery(document).ready(function($){
										$("#modifyModuleType1").selectBoxIt().on('open', function(){
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
										$("#modifyModuleType2").selectBoxIt().on('open', function(){
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" style="margin-bottom:20px;" id="modifyModuleType1" name="modifyModuleType1" onchange="populateModuleType2ByType1('modifyModuleType2',this.value)">
									<option value="">请选择</option>
									<c:forEach items="${moduleType1}" var="moduleType">
										<option value="${moduleType.dicCode}">${moduleType.dicName}</option>
									</c:forEach>
								</select>
								</br>
								<select class="form-control" style="width:200px;" id="modifyModuleType2" name="modifyModuleType2">
									<option value="">--请选择--</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label form_input_required" for="spName">供应商名称</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="spName" name="spName" data-validate="required" data-message-required="名称不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="spTel">联系电话</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="spTel" name="spTel">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="spPhone">手机号码</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="spPhone" name="spPhone">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<div style="text-align: center;">
						<button class="btn btn-success btn-icon" onclick="return submitModuleSP()">
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
