<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_body" style="width:100%;overflow: hidden;">
	<div class="modal-dialog" style="width:90%">
		<div class="modal-content" style="min-height: 600px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">主体构件</h4>
			</div>
			<div id="leftBody" style="width:35%;height:400px;float:left;">
				<h5 class="modal-title" style="margin:6px 0px;">主体分类</h5>
				<iframe id="houseSubjectFrame" width="100%" height="100%"></iframe>
			</div>
			<div id="rightBody" style="width:50%;float:right;display: none;">
				<script type="text/javascript">
				jQuery(document).ready(function($)
				{
					$("#bodyModuleDetailTable").dataTable({
						dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
						aoColumns: [
							null,
							null
						],
						"bLengthChange": true,
						"iDisplayLength": 5,
					});
				});
				</script>
				<div style="margin-top:10px;margin-bottom:10px;width:300px;float:left;">
					<script type="text/javascript">
						jQuery(document).ready(function($)
						{
							$("#bodyModuleType").selectBoxIt().on('open', function()
							{
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<select class="form-control" id="bodyModuleType" name="bodyModuleType">
						<option value="">请选择主体构件类别</option>
						<c:forEach items="${moduleTypes}" var="moduleType">
							<option value="${moduleType.dicCode}" title="${moduleType.dicName}">${moduleType.dicName}</option>
						</c:forEach>
					</select>
				</div>
				<div style="float:left;margin:10px 10px;">
					<a id="newBodyModuleLink">
						新增构件
						<button class="btn btn-icon btn-info" style="margin-bottom:0px;">
							<li class="fa-plus-square"></li>
						</button>
					</a>
				</div>
				<div id="bodyModuleDetailDiv">
					<table class="table table-bordered table-striped" id="bodyModuleDetailTable">
						<thead>
							<tr>
								<th>主体构件名称</th>
								<th>主体构件描述</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						</tbody>
					</table>
				</div>
				<div style="display: none;margin-top:70px;border:1px solid #aaa;padding:10px;" id="newBodyModuleDiv">
					<input type="hidden" id="houseSubjectId" name="houseSubjectId"/>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="field-1">构件分类</label>
						<div class="col-sm-10">
							<script type="text/javascript">
								jQuery(document).ready(function($)
								{
									$("#newBodyModuleType").selectBoxIt().on('open', function()
									{
										$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
									});
								});
							</script>
							<select class="form-control" id="newBodyModuleType" name="newBodyModuleType">
								<option value="">请选择主体构件类别</option>
								<c:forEach items="${moduleTypes}" var="moduleType">
									<option value="${moduleType.dicCode}" title="${moduleType.dicName}">${moduleType.dicName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="field-1">构件名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="moduleName" name="moduleName" data-validate="required" data-message-required="名称不能为空">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="field-1">构件描述</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="moduleDesc" name="moduleDesc">
						</div>
					</div>
					<div class="modal-footer">
						<button id="bodyModule_submitBtn" class="btn btn-success">提交</button>
						<button id="bodyModule_cancelBtn" class="btn btn-white">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
