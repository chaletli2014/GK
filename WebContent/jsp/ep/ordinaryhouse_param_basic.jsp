<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="col-md-12">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="field-1">所在地域</label>
				<div class="col-sm-8">
					<div class="input-group">
						<span class="input-group-addon">省份</span>
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#province").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="province" name="province">
							<option key="上海">上海</option>
							<option key="北京">北京</option>
							<option key="深圳">深圳</option>
						</select>
						<span class="input-group-addon">城市</span>
						<script type="text/javascript">
							jQuery(document).ready(function($)
							{
								$("#city").selectBoxIt().on('open', function()
								{
									$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
								});
							});
						</script>
						<select class="form-control" id="city" name="city">
							<option key="上海">上海</option>
							<option key="北京">北京</option>
							<option key="深圳">深圳</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="company">开发公司</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="company" name="company" value="${orHouse.company}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="location">坐落位置</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="location" name="location" value="${orHouse.location}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="projectPosition">项目方位</label>
				<div class="col-sm-8">
					<div class="input-group">
						<span class="input-group-addon">东至</span>
						<input type="text" class="form-control" id="projectPositionE" name="projectPositionE" value="${orHouse.projectPositionE}">
						<span class="input-group-addon">西至</span>
						<input type="text" class="form-control" id="projectPositionW" name="projectPositionW" value="${orHouse.projectPositionW}">
					</div>
					<div class="input-group">
						<span class="input-group-addon">南至</span>
						<input type="text" class="form-control" id="projectPositionS" name="projectPositionS" value="${orHouse.projectPositionS}">
						<span class="input-group-addon">北至</span>
						<input type="text" class="form-control" id="projectPositionN" name="projectPositionN" value="${orHouse.projectPositionN}">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="startDate">开盘时间</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" class="form-control" id="startYear" name="startYear" value="${orHouse.startYear}">
						<span class="input-group-addon">年</span>
						<input type="text" class="form-control" id="startMonth" name="startMonth" value="${orHouse.startMonth}">
						<span class="input-group-addon">月</span>
						<input type="text" class="form-control" id="startDate" name="startDate" value="${orHouse.startDate}">
						<span class="input-group-addon">日</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="checkinDate">入住时间</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" class="form-control" id="checkinYear" name="checkinYear" value="${orHouse.checkinYear}">
						<span class="input-group-addon">年</span>
						<input type="text" class="form-control" id="checkinMonth" name="checkinMonth" value="${orHouse.checkinMonth}">
						<span class="input-group-addon">月</span>
						<input type="text" class="form-control" id="checkinDate" name="checkinDate" value="${orHouse.checkinDate}">
						<span class="input-group-addon">日</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="floorSpace">占地面积</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" class="form-control" id="floorSpace" name="floorSpace" value="${orHouse.floorSpace}">
						<span class="input-group-addon">平方米</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>