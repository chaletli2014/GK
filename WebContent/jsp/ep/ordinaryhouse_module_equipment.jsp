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
							$("#eqType").selectBoxIt().on('open', function()
							{
								$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
							});
						});
					</script>
					<select class="form-control" id="eqType" name="eqType">
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
							<th>隶属主体</th>
							<th>隶属构件</th>
						</tr>
					</thead>
					<tbody class="middle-align">
						<tr>
							<td >1-1号电梯</td>
							<td >1号楼电梯1</td>
							<td >1号主体</td>
							<td >1号主体</td>
						</tr>
						<c:forEach items="${devices}" var="device">
							<tr>
								<td >${device.name}</td>
								<td >${device.desc}</td>
								<td >${device.subjectName}</td>
								<td >${device.moduleName}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div style="float:right;width:48%;padding-top:18px;margin-left:4px;display: none;" id="newEquDiv">
				<div class="form-group">
					<label class="col-sm-4 control-label" for="field-1">设施设备分类</label>
					<div class="col-sm-6">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#eqType_new").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="eqType_new" name="eqType">
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
					<label class="col-sm-4 control-label" for="eqName">设施设备名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="eqName" name="eqName" data-validate="required" data-message-required="名称不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="eqBrand">设施设备品牌</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="eqBrand" name="eqBrand" data-validate="required" data-message-required="品牌不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="eqStyle">设施设备款型</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="eqStyle" name="eqStyle" data-validate="required" data-message-required="款型不能为空">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="eqDesc">设施设备描述</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="eqDesc" name="eqDesc">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="eqSubject">隶属主体</label>
					<div class="col-sm-6">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#eqSubject").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="eqSubject" name="eqSubject">
							<option value="">--请选择--</option>
							<c:forEach items="${houseSubjects}" var="subject">
								<option value="${subject.id}" title="${subject.name}">${subject.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="eqModule">隶属构件</label>
					<div class="col-sm-6">
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#eqModule").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="eqModule" name="eqModule">
							<option value="">--请选择--</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button id="eq_submitBtn" class="btn btn-success">提交</button>
					<button id="equ_cancelBtn" class="btn btn-white">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
