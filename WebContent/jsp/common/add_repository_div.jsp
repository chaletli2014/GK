<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="addrepositorydiv">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">物库编辑</h4>
			</div>
			<input type="hidden" id="repositoryId" name="repositoryId"/>
			<input type="hidden" id="repo_from_source" name="${fromSource}"/>
			<input type="hidden" id="repositoryType" name="repositoryType"/>
			<div class="modal-body">
				<div class="content">
					<div class="panel-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<span id="returnMessage" style="color:red;"></span>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="repositoryName">物库名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="repositoryName" name="repositoryName" data-validate="required" data-message-required="名称不能为空">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="repositoryDesc">物库描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="repositoryDesc" name="repositoryDesc">
							</div>
						</div>
						<div class="form-group-separator"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="reposCategory">物库分类</label>
							<div class="col-sm-10">
								<label class="radio-inline">
									<input type="radio" name="reposCategory" value="chattel" checked>动产
								</label>
								<label class="radio-inline">
									<input type="radio" name="reposCategory" value="estate">不动产
								</label>
							</div>
						</div>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-success" id="addNewRepositoryBtn">提交</button>
				<button type="reset" class="btn btn-white">清空</button>
			</div>
		</div>
	</div>
</div>