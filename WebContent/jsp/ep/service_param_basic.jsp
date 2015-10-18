<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="col-md-12">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="serviceContent">服务内容</label>
					<div class="col-sm-6">
						<textarea class="form-control autogrow" cols="5" id="serviceContent" id="serviceContent" name="serviceContent" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 150px;">
							${serviceDetail.serviceContent}
						</textarea>
					</div>
				</div>
				<div>
				<a id="servicefinishWizard">
					<button class="btn btn-success btn-icon btn-icon-standalone">
						<i class="fa-check-square-o"></i>
						<span>完成</span>
					</button>
				</a>
			</div>
			</div>
		</div>
	</div>
</div>