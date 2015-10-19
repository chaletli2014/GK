<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default" style="padding-bottom:10px;">
	<div class="panel-body">
		<div class="col-md-12">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="floorSpace">占地面积</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" class="form-control" id="floorSpace" name="floorSpace" value="${orHouse.floorSpace}">
						<span class="input-group-addon">平方米</span>
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
	</div>
</div>