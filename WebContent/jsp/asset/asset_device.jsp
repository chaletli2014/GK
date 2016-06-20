<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="asset_div" id="asset_device" style="display:none;">
	<div class="page-title">
		<div>
			<button id="newDevice" class="btn btn-info">
				<li class="fa-plus-square">添加设施设备</li>
			</button>
			<button id="saveDevice" class="btn btn-info">
				<span>保存</span>
			</button>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body" style="padding-top:0px;">
			<table class="table table-hover" id="assetDeviceTable">
				<thead>
					<tr>
						<th width="10%">分类</th>
						<th width="15%">设备名称</th>
						<th width="30%">设备描述</th>
						<th width="25%">设施设备位置</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
				<tbody class="middle-align">
					<c:forEach items="${houseDevices}" var="houseDevice">
						<tr>
							<td class="dataEditable" title="${houseDevice.eqTypeName}">${houseDevice.eqTypeName}</td>
							<td class="dataEditable" title="${houseDevice.name}">${houseDevice.name}</td>
							<td class="dataEditable" title="${houseDevice.eqDesc}">${houseDevice.eqDesc}</td>
							<td>${houseDevice.subjectName}-${houseDevice.moduleName}</td>
							<td>
								<a id="${houseDevice.id}" dtype="${houseDevice.eqTypeCode}" class="btn btn-secondary btn-sm btn-icon icon-left modifyDevice">
									编辑
								</a>
								<a id="${houseDevice.id}" dtype="${houseDevice.eqTypeCode}" class="btn btn-danger btn-sm btn-icon icon-left removeDevice">
									删除
								</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>