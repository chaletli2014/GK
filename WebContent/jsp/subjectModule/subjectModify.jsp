<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade subjectModifyDiv" id="subjectModifyDiv">
	<div class="modal-dialog">
		<div class="modal-content" style="min-height: 300px;padding:30px;">
			<div class="modal-header">
				<div style="position:absolute;top:0px;left:10px;width: 120px; background: #00a4ef;  color: #fff;margin-left:10px;padding:8px;">
					<span style="margin-left:20px;">主体编辑</span>
				</div>
				<div style="position:absolute;top:0px;right:10px;">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style="padding:6px 12px;background:#cc0000;opacity:1;color:white">
						<li class="fa-close"></li>
					</button>
				
				</div>
			</div>
			<input type="hidden" id="subjectIdModify" name="subjectId"/>
			<div class="modal-body">
				<div class="content">
					<div class="panel-body">
						<div class="form-group">
							<span id="returnMessage" style="color:red;"></span>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="subjectName">主体名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subjectNameModify" name="subjectName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="subjectDesc">主体描述</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="subjectDescModify" name="subjectDesc">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="parentName">隶属于</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="parentNameModify" name="parentName" disabled="disabled">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-success" id="modifySubjectBtn">提交</button>
			</div>
		</div>
	</div>
</div>