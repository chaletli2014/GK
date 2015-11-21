<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="new_module_sp_div">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">${spTypeName}编辑</h4>
			</div>
			<form role="form" class="form-horizontal" action="saveOrUpdateHouseSP" method="post" id="addordinaryhouse_form">
			<input type="hidden" name="spTypeCode" value="${spTypeCode}">
			<input type="hidden" name="houseSPId" value="${houseSP.id}">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="spName">名称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="spName" name="spName" value="${houseSP.moduleSPValue}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="remark">备注</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="remark" name="remark" value="${houseSP.remark}">
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
