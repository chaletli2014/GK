<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="addRepositoryUsersDiv">
	<div class="modal-dialog" style="width:40%;">
		<div class="modal-content" style="min-height: 400px;padding:0px;padding-top:30px;">
			<div class="modal-header">
				<div style="position:absolute;top:0px;left:10px;width: 120px; background: #00a4ef;  color: #fff;margin-left:10px;padding:8px;">
					<span style="margin-left:20px;">新增库用户</span>
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
						<div class="input-group">
							<input type="text" name="userSearch" id="userSearch" class="form-control search-field" placeholder="输入用户名搜索物库用户" />
							<span class="input-group-addon" style="cursor: pointer;" id="searchUserSpan">
								<i class="linecons-search"></i>
							</span>
						</div>
						<div style="margin-left:10px;overflow-y:auto;max-height:520px;display: none;" id="searchUserInfo">
							<table class="table table-hover" id="searchUserTable">
								<thead>
									<tr>
										<th width="10%"></th>
										<th width="15%">用户名称</th>
										<th width="15%">手机号码</th>
									</tr>
								</thead>
								<tbody class="middle-align">
								</tbody>
							</table>
							<div class="modal-footer" style="text-align: left;padding-left:20px;">
								<button class="btn btn-success" id="chooseTarget">关联用户</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>