<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_installation">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">公共配套设施</h4>
			</div>
			<div>
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
						"iDisplayLength": 10,
					});
				});
				</script>
				<div style="margin-top:10px;margin-bottom:10px;width:300px;">
					<script type="text/javascript">
						jQuery(document).ready(function($)
						{
							$("#insModuleType").selectBoxIt().on('open', function()
							{
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<select class="form-control" id="insModuleType" name="insModuleType">
						<option value="">请选择设施组件类别</option>
						<option value="dl" title="">道路</option>
						<option value="ld" title="">绿地</option>
						<option value="rzjg" title="">人造景观</option>
						<option value="wq" title="">围墙</option>
						<option value="dm" title="">大门（含道闸）</option>
					</select>
				</div>
				<table class="table table-bordered table-striped" id="moduleInsDetailTable">
					<thead>
						<tr>
							<th>设施名称</th>
							<th>设施描述</th>
							<th>设施位置</th>
						</tr>
					</thead>
					<tbody class="middle-align">
						<tr>
							<td >1号道路</td>
							<td >入门左手第一条道路</td>
							<td >公共</td>
						</tr>
						<tr>
							<td >2号道路</td>
							<td >入门右手第一条道路</td>
							<td >公共</td>
						</tr>
						<tr>
							<td >3号道路</td>
							<td >1号主体前</td>
							<td >1号主体</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
