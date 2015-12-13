<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_installation">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content" style="min-height: 600px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">其它</h4>
			</div>
			<div style="width: 80%;float:left;" id="otherInfo">
				<script type="text/javascript">
				jQuery(document).ready(function($)
				{
					$("#moduleInsDetailTable").dataTable({
						dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
						aoColumns: [
							null,
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
							$("#otherModuleType").selectBoxIt().on('open', function()
							{
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<select class="form-control" id="otherModuleType" name="otherModuleType">
						<option value="">请选择类别</option>
						<option value="xg" title="">悬挂物</option>
						<option value="lt" title="">立体装饰</option>
					</select>
				</div>
				<div style="float:right;margin-top:18px;">
					<a id="newOtherLink">
						<button class="btn btn-icon btn-info">
							<li class="fa-plus-square"></li>
						</button>
					</a>
				</div>
				<table class="table table-bordered table-striped" id="moduleInsDetailTable">
					<thead>
						<tr>
							<th>名称</th>
							<th>描述</th>
							<th>位置</th>
						</tr>
					</thead>
					<tbody class="middle-align">
						<tr>
							<td >1号挂像</td>
							<td >1号挂像</td>
							<td >1号主体</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="float:right;width:48%;padding-top:18px;margin-left:4px;display: none;" id="newOtherDiv">
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">分类</label>
					<div class="col-sm-10">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#otherModuleType_new").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="otherModuleType_new" name="otherModuleType_new">
							<option value="">请选择类别</option>
							<option value="xg" title="">悬挂物</option>
							<option value="lt" title="">立体装饰</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dicName" name="dicName" data-validate="required" data-message-required="名称不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">描述</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dicCode" name="dicCode">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">所在位置</label>
					<div class="col-sm-10">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#equBodyModule").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="equBodyModule" name="equBodyModule">
							<option value="">公共</option>
							<option value="1" title="">1号主体</option>
							<option value="2" title="">2号主体</option>
							<option value="3" title="">3号主体</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="submit" class="btn btn-success">提交</button>
					<button id="other_cancelBtn" class="btn btn-white">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
