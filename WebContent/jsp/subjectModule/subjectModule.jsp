<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="subjectModuleDiv">
	<div class="modal-dialog" style="width:85%;">
		<div class="modal-content" style="min-height: 600px;padding:0px;background:#f1f1f1;">
			<div style="float:left;width: 70%;background:#fff;min-height:600px;">
				<div style="width: 120px;  background: lightskyblue;  color: #fff;margin-top:10px;padding:8px">
					<span style="margin-left:20px;">构件列表</span>
				</div>
				<div style="margin-left:10px;overflow-y:auto;max-height:520px;" id="equInfo">
					<script type="text/javascript">
					/**
					jQuery(document).ready(function($)
					{
						$("#subjectModuleTable").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							"bLengthChange": true,
							"iDisplayLength": 5
						});
					});
					*/
					</script>
					<table class="table table-hover" id="subjectModuleTable">
						<thead>
							<tr>
								<th width="10%">&nbsp;</th>
								<th width="15%">分类</th>
								<th width="25%">构件名称</th>
								<th width="30%">构件描述</th>
								<th width="20%">隶属于</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						</tbody>
					</table>
				</div>
			</div>
			<div style="float:left;width:30%;min-height:600px;" id="newSubjectModuleDiv">
				<div style="text-align: right;">
					<button id="subjectModule_close" class="close btn btn-icon btn-info" data-dismiss="modal" aria-hidden="true" style="padding:6px 12px;background:#cc0000;opacity:1;color:white">
						<li class="fa-close"></li>
					</button>
				</div>
				<input type="hidden" id="moduleId_h" name="moduleId"/>
				<div style="text-align: center;margin:4px;font-size: 20px;margin-top:40px;">
					<span style="">构件编辑</span>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">构件分类</label>
					<div class="col-sm-10">
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
							<option value="">请选择构件分类</option>
							<c:forEach items="${moduleTypes}" var="moduleType">
								<option value="${moduleType.dicCode}" title="${moduleType.dicName}">${moduleType.dicName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="moduleName">构件名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="moduleName" name="moduleName" data-validate="required" data-message-required="名称不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="moduleDesc">构件描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="moduleDesc" name="moduleDesc">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="subjectName">隶属主体</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="subjectName" name="subjectName" disabled="disabled">
						<input type="hidden" id="subjectId"/>
					</div>
				</div>
				<div class="modal-footer" style="text-align:left;margin-left:10px;">
					<button id="sm_submitBtn" class="btn btn-success">提交</button>
					<button id="sm_clearBtn" class="btn btn-warning">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>