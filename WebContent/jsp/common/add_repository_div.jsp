<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="addrepositorydiv">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">资品库编辑</h4>
			</div>
			<input type="hidden" id="repositoryId" name="repositoryId"/>
			<input type="hidden" id="repo_from_source" name="${fromSource}"/>
			<div class="modal-body">
				<div class="content">
					<div class="panel-body">
						<div class="form-group">
							<span id="returnMessage" style="color:red;"></span>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="field-1">资品库名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="repositoryName" name="repositoryName" data-validate="required" data-message-required="名称不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="field-1">资品库描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="repositoryCode" name="repositoryCode">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="field-1">资品库类型</label>
							<div class="col-sm-10">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#repositoryType").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="repositoryType" name="repositoryType">
									<option value="1">物品库</option>
									<option value="2">物资库</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-success" id="addNewRepositoryBtn">提交</button>
				<button type="reset" class="btn btn-white">清空</button>
			</div>
		</div>
	</div>
</div>