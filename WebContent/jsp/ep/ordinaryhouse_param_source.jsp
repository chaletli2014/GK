<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="<%=basePath%>js/dropzone/dropzone.min.js"></script>

<div class="row" style="margin-bottom: 20px;">
	<div class="panel-body">
		 <script type="text/javascript">
			jQuery(document).ready(function($){
					var i = 1,
					$example_dropzone_filetable = $("#uploadFileForm"),
					example_dropzone = $("#advancedDropzone").dropzone({
					url: '<%=basePath%>uploadHouseSourceFile',
					// Events
					addedfile: function(file){
						if(i == 1){
							$example_dropzone_filetable.find('tbody').html('');
						}
						var size = parseInt(file.size/1024, 10);
						size = size < 1024 ? (size + " KB") : (parseInt(size/1024, 10) + " MB");
						var	$el = $('<tr><td class="text-center">'+(i++)+'</td><td>'+file.name+'</td>'
									+'<td><div class="progress progress-striped"><div class="progress-bar progress-bar-warning"></div></div></td>'
									+'<td>'+size+'</td>'
									+'<td>文件上传中...</td></tr>');
						$example_dropzone_filetable.find('tbody').append($el);
						file.fileEntryTd = $el;
						file.progressBar = $el.find('.progress-bar');
					},
					uploadprogress: function(file, progress, bytesSent){
						file.progressBar.width(progress + '%');
					},
					success: function(file,response){
						var resultMsg = response.resultMsg;
						if( null != resultMsg ){
							file.fileEntryTd.find('td:last').html('<span class="text-danger">上传失败：'+resultMsg+'</span>');
							file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
						}else{
							file.fileEntryTd.find('td:last').html('<span class="text-success">上传成功</span>');
							file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-success');
							
							var existsFiles = response.existsFiles;
							$("#existsFileTable tbody").empty();
							var count = 1;
							$.each(existsFiles,function(n,fileObj){
								$("#existsFileTable tbody").append("<tr><td>"+count+"</td><td>"+fileObj.fileType+"</td><td><a href='"+fileObj.filePath+"'>"+fileObj.fileName+"</a></td><td>"+fileObj.isMain+"</td><td></td></tr>");
								count++;
							});
						}
					},
					error: function(file){
						file.fileEntryTd.find('td:last').html('<span class="text-danger">上传失败</span>');
						file.progressBar.removeClass('progress-bar-warning').addClass('progress-bar-red');
					}
				});
				
				$("#advancedDropzone").css({
					minHeight: 200
				});
			});
		</script>
		<br />
		<div class="row">
			<div class="col-sm-3 text-center">
				<div id="advancedDropzone" class="droppable-area">
					点击上传文件
				</div>
			</div>
			<div class="col-sm-9">
				<table class="table table-bordered table-striped" id="uploadFileForm">
					<thead>
						<tr>
							<th width="8%" class="text-center">编号</th>
							<th width="50%">文件名称</th>
							<th width="12%">上传进度</th>
							<th width="15%">文件大小</th>
							<th width="15%">上传状态</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">本次上传的文件显示在这里</td>
						</tr>
					</tbody>
				</table>
				
			</div>
		</div>
		<div class="row">
			<div class="col-sm-9">
				<strong>已上传文件列表</strong>
				<table class="table table-model-2 table-hover" id="existsFileTable">
					<thead>
						<tr>
							<th>#</th>
							<th>文件类型</th>
							<th>文件名</th>
							<th>是否主图</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${houseFiles}" var="houseFile" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>${houseFile.fileType}</td>
								<td><a href="${houseFile.filePath}" target="_blank">${houseFile.fileName}</a></td>
								<td>${houseFile.isMain}</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>