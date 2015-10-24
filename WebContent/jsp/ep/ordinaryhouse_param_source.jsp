<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row" style="margin-bottom: 20px;">
	<div class="panel-body">
		<div class="row">
			<div class="col-sm-3 text-center">
				<div id="advancedDropzone" class="droppable-area">将上传文件拖放到此</div>
			</div>
			<div class="col-sm-9">
				<table class="table table-bordered table-striped" id="example-dropzone-filetable">
					<thead>
						<tr>
							<th width="1%" class="text-center">#</th>
							<th width="40%">文件名称</th>
							<th width="20%">上传进度</th>
							<th width="20%">上传文件大小</th>
							<th width="20%">上传状态</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">上传文件列表将显示在此处</td>
						</tr>
					</tbody>
				</table>
				
			</div>
		</div>
	</div>
	<div>
		<a id="finishWizard">
			<button class="btn btn-success btn-icon btn-icon-standalone">
				<i class="fa-check-square-o"></i>
				<span>完成</span>
			</button>
		</a>
	</div>
</div>