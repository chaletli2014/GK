<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default" style="padding-bottom:10px;">
	<div class="panel-body">
		<table class="table table-bordered table-striped deviceTable">
			<thead>
				<tr>
					<th>设备分类 </th>
					<th>设备名称</th>
					<th>制造厂家</th>
					<th>品牌</th>
					<th>规格型号</th>
					<th>数量</th>
				</tr>
			</thead>
			<tbody class="middle-align">
				<c:forEach items="${deviceList}" var="device">
					<tr>
						<td title="${device.deviceType}">${device.deviceType}</td>
						<td title="${device.deviceName}">${device.deviceName}</td>
						<td title="${device.manufacturerName}">${device.manufacturerName}</td>
						<td title="${device.brandName}">${device.brandName}</td>
						<td title="${device.model}">${device.model}</td>
						<td title="${device.deviceNum}">${device.deviceNum}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>