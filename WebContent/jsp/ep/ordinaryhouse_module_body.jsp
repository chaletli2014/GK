<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_body">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">公共主体建筑</h4>
			</div>
			<div style="min-height: 500px;">
				<div id="leftBody" style="width:35%;float:left;">
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
				<div id="rightBody" style="width:50%;float:right;">
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#moduleBodyDetailTable").dataTable({
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
					<table class="table table-bordered table-striped" id="moduleBodyDetailTable">
						<thead>
							<tr>
								<th>公共主体建筑名称</th>
								<th>公共主体建筑描述</th>
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
			</div>
		</div>
	</div>
</div>
