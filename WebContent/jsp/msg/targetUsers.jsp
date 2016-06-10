<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="targetUsersDiv">
	<div class="modal-dialog" style="width:40%;">
		<div class="modal-content" style="min-height: 400px;padding:0px;padding-top:30px;">
			<div class="modal-header">
				<div style="position:absolute;top:0px;left:10px;width: 120px; background: #00a4ef;  color: #fff;margin-left:10px;padding:8px;">
					<span style="margin-left:20px;">联系人列表</span>
				</div>
				<div style="position:absolute;top:0px;right:10px;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding:6px 12px;background:#cc0000;opacity:1;color:white">
						<li class="fa-close"></li>
					</button>
				</div>
			</div>
			<div class="modal-body">
				<div class="content">
					<div class="panel-body">
						<div>
							<div style="margin-left:10px;overflow-y:auto;max-height:520px;" id="targetUserInfo">
								<table class="table table-hover" id="targetUserTable">
									<thead>
										<tr>
											<th width="10%"></th>
											<th width="15%">联系人名称</th>
										</tr>
									</thead>
									<tbody class="middle-align">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: left;padding-left:20px;">
				<button class="btn btn-success" id="chooseTarget">选中</button>
			</div>
		</div>
	</div>
</div>