<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_equipment">
	<div class="modal-dialog" style="width:70%;">
		<div class="modal-content" style="min-height: 600px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">设施设备</h4>
			</div>
			<div style="width: 80%;float:left;" id="equInfo">
				<script type="text/javascript">
				jQuery(document).ready(function($)
				{
					$("#moduleEquDetailTable").dataTable({
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
							$("#equModuleType").selectBoxIt().on('open', function()
							{
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<select class="form-control" id="equModuleType" name="equModuleType">
						<option value="">请选择设施设备类别</option>
						<option value="dt" title="">电梯</option>
						<option value="sb" title="">水泵</option>
						<option value="sx" title="">水箱</option>
						<option value="bl" title="">避雷设备</option>
						<option value="xf" title="">消防设备</option>
					</select>
				</div>
				<div style="float:right;margin-top:18px;">
					<a id="newEquLink">
						<button class="btn btn-icon btn-info">
							<li class="fa-plus-square"></li>
						</button>
					</a>
				</div>
				<table class="table table-bordered table-striped" id="moduleEquDetailTable">
					<thead>
						<tr>
							<th>设施设备名称</th>
							<th>设施设备描述</th>
							<th>设施设备位置</th>
						</tr>
					</thead>
					<tbody class="middle-align">
						<tr>
							<td >1-1号电梯</td>
							<td >1号楼电梯1</td>
							<td >1号主体</td>
						</tr>
						<tr>
							<td >1-2号电梯</td>
							<td >1号楼电梯2</td>
							<td >1号主体</td>
						</tr>
						<tr>
							<td >2-1号电梯</td>
							<td >2号楼电梯1</td>
							<td >2号主体</td>
						</tr>
						<tr>
							<td >2-1号电梯</td>
							<td >2号楼电梯1</td>
							<td >2号主体</td>
						</tr>
						<tr>
							<td >2-1号电梯</td>
							<td >2号楼电梯1</td>
							<td >2号主体</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="float:right;width:48%;padding-top:18px;margin-left:4px;display: none;" id="newEquDiv">
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">设施设备分类</label>
					<div class="col-sm-10">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#equModuleType_new").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="equModuleType_new" name="equModuleType_new">
							<option value="">请选择设施设备类别</option>
							<option value="dt" title="">电梯</option>
							<option value="sb" title="">水泵</option>
							<option value="sx" title="">水箱</option>
							<option value="bl" title="">避雷设备</option>
							<option value="xf" title="">消防设备</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">设施设备名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="dicName" name="dicName" data-validate="required" data-message-required="名称不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">设施设备描述</label>
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
					<button id="equ_cancelBtn" class="btn btn-white">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
