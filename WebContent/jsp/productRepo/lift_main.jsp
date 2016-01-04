<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row" style="margin-bottom: 20px;">
	<div class="panel-body">
		<div class="row">
			<div class="form-group">
				<label class="col-sm-4 control-label" for="holeSize">井道尺寸（宽*深）(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="holeSize" name="holeSize" value="${lift.holeSize}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="pitDepth">底坑深度(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="pitDepth" name="pitDepth" value="${lift.pitDepth}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="overheadHeight">顶层高度(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="overheadHeight" name="overheadHeight" value="${lift.overheadHeight}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="reservation">门洞预留（宽*高）(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="reservation" name="reservation" value="${lift.reservation}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="roomSize">机房尺寸（宽*深）(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="roomSize" name="roomSize" value="${lift.roomSize}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="roomHeight">机房净高(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="roomHeight" name="roomHeight" value="${lift.roomHeight}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="carSize">轿厢尺寸（宽*深）(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="carSize" name="carSize" value="${lift.carSize}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="carHeight">轿厢净高(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="carHeight" name="carHeight" value="${lift.carHeight}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="doorSize">开门尺寸（宽*高）(mm)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="doorSize" name="doorSize" value="${lift.doorSize}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label" for="mainPower">主机功率(kw)</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" id="mainPower" name="mainPower" value="${lift.mainPower}">
				</div>
			</div>
		</div>
	</div>
	<div>
		<a id="liftFinishWizard">
			<button class="btn btn-success btn-icon btn-icon-standalone">
				<i class="fa-check-square-o"></i>
				<span>完成</span>
			</button>
		</a>
	</div>
</div>