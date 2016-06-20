<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="asset_div" id="asset_other" style="display:none;">
	<div class="page-title">
		<div>
			<button id="newOther" class="btn btn-info">
				<li class="fa-plus-square">添加材料&装饰</li>
			</button>
			<button id="saveOther" class="btn btn-info">
				<span>保存</span>
			</button>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-body" style="padding-top:0px;">
			<table class="table table-hover" id="otherTable">
				<thead>
					<tr>
						<th width="7%">分类</th>
						<th width="15%">设备名称</th>
						<th width="30%">设备描述</th>
						<th width="30%">设施设备位置</th>
						<th width="5%">操作</th>
					</tr>
				</thead>
				<tbody class="middle-align">
					<c:forEach items="${houseOthers}" var="houseOther">
						<tr>
							<td class="dataEditable" title="${houseOther.typeName}">${houseOther.typeName}</td>
							<td class="dataEditable" title="${houseOther.name}">${houseOther.name}</td>
							<td class="dataEditable" title="${houseOther.desc}">${houseOther.desc}</td>
							<td>${houseOther.subjectName}-${houseOther.moduleName}</td>
							<td>
								<a id="${houseOther.id}" dtype="${houseOther.typeCode}" class="btn btn-secondary btn-sm btn-icon icon-left modifyOther">
									编辑
								</a>
								<a id="${houseOther.id}" dtype="${houseOther.typeCode}" class="btn btn-danger btn-sm btn-icon icon-left removeOther">
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