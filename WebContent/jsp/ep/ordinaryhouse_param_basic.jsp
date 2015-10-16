<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default">
	<div class="panel-body">
		<div class="col-md-12">
			<div class="form-group">
				<label class="col-sm-2 control-label" for="company">开发公司</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" id="company" name="company" value="${orHouse.company}">
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