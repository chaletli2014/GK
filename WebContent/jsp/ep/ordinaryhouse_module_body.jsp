<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="houseModuleDiv_body" style="width:100%;overflow: hidden;">
	<div class="modal-dialog" style="width:90%">
		<div class="modal-content" style="min-height: 600px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">主体构件</h4>
			</div>
				<div id="leftBody" style="width:45%;float:left;">
					<div style="float:right;margin:4px 0px;">
						<a id="newBodyLink">
							新增主体
							<button class="btn btn-icon btn-info" style="margin-bottom:0px;">
								<li class="fa-plus-square"></li>
							</button>
						</a>
					</div>
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
								<td >主楼</td>
								<td >主楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >1号楼</td>
								<td >1号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >2号楼</td>
								<td >2号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >3号楼</td>
								<td >3号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >4号楼</td>
								<td >4号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >5号楼</td>
								<td >5号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >6号楼</td>
								<td >6号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >7号楼</td>
								<td >7号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >8号楼</td>
								<td >8号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >9号楼</td>
								<td >9号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
							<tr>
								<td >10号楼</td>
								<td >10号楼</td>
								<td>
									<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
										构件明细
									</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div style="float:right;width:48%;padding-top:18px;margin-left:4px;display: none;" id="newBodyDiv">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="field-1">主体名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="bodyName" name="bodyName" data-validate="required" data-message-required="名称不能为空">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="field-1">主体描述</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="bodyDesc" name="bodyDesc">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-success">提交</button>
						<button id="body_cancelBtn" class="btn btn-white">取消</button>
					</div>
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
							<option value="jc" title="">基础</option>
							<option value="cz" title="">承重墙体、柱、梁、楼板</option>
							<option value="wd" title="">屋顶</option>
							<option value="wq" title="">外墙</option>
							<option value="mt" title="">门厅</option>
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
					<div style="display: none;margin-top:70px;border:1px solid #aaa;padding:10px;" id="newBodyModuleDiv">
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
									<option value="jc" title="">基础</option>
									<option value="cz" title="">承重墙体、柱、梁、楼板</option>
									<option value="wd" title="">屋顶</option>
									<option value="wq" title="">外墙</option>
									<option value="mt" title="">门厅</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="field-1">构件名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="bodyName" name="bodyName" data-validate="required" data-message-required="名称不能为空">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="field-1">构件描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="bodyDesc" name="bodyDesc">
							</div>
						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success">提交</button>
							<button id="bodyModule_cancelBtn" class="btn btn-white">取消</button>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
