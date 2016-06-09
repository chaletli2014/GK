<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="targetUsersDiv">
	<div class="modal-dialog" style="width:40%;">
		<div class="modal-content" style="min-height: 600px;padding:0px;">
			<div style="background:#fff;min-height:600px;">
				<div style="width: 120px;  background: lightskyblue;  color: #fff;margin-top:10px;padding:8px">
					<span style="margin-left:20px;">用户列表</span>
				</div>
				<div style="margin-left:10px;overflow-y:auto;max-height:520px;" id="equInfo">
					<table class="table table-hover" id="targetUserTable">
						<thead>
							<tr>
								<th width="10%"><input type="checkbox" class="cbr"></th>
								<th width="15%">用户名称</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						</tbody>
					</table>
				</div>
				<div class="modal-footer" style="text-align:left;margin-left:10px;">
					<button id="sm_submitBtn" class="btn btn-success">选中</button>
					<button id="sm_clearBtn" class="btn btn-warning">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>