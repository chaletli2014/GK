<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_body" style="width:100%;overflow: hidden;">
	<div class="modal-dialog" style="width:90%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">公共主体建筑</h4>
			</div>
			<div style="min-height: 500px;">
				<div id="leftBody" style="width:45%;float:left;">
					<a href="javascript:void(0)">新增</a>
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#moduleBodyTable").dataTable({
							dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
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
					<table class="table table-bordered table-striped" id="moduleBodyTable">
						<thead>
							<tr>
								<th>主体名称</th>
								<th>主体描述</th>
								<th>查看</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<tr>
								<td >1号楼</td>
								<td >1号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										明细
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div id="rightBody" style="width:50%;float:right;display: none;">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#moduleBodyDetailTable").dataTable({
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
					<div style="margin-top:10px;margin-bottom:10px;width:300px;">
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
							<option value="">请选择主体组件类别</option>
							<option value="jc" title="">基础</option>
							<option value="cz" title="">承重墙体、柱、梁、楼板</option>
							<option value="wd" title="">屋顶</option>
							<option value="wq" title="">外墙</option>
							<option value="mt" title="">门厅</option>
						</select>
					</div>
					<table class="table table-bordered table-striped" id="moduleBodyDetailTable">
						<thead>
							<tr>
								<th>主体组件名称</th>
								<th>主体组件描述</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<tr>
								<td >1号基础</td>
								<td >第1号基础</td>
							</tr>
							<tr>
								<td >2号基础</td>
								<td >第2号基础</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
