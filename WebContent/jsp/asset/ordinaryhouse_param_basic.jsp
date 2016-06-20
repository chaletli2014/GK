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
				<label class="col-sm-2 control-label" for="startDate">竣工时间</label>
				<div class="col-sm-8">
					<div class="input-group">
						<input type="text" class="form-control" id="finishYear" name="finishYear" value="${orHouse.finishYear}">
						<span class="input-group-addon">年</span>
						<input type="text" class="form-control" id="finishMonth" name="finishMonth" value="${orHouse.finishMonth}">
						<span class="input-group-addon">月</span>
						<input type="text" class="form-control" id="finishDate" name="finishDate" value="${orHouse.finishDate}">
						<span class="input-group-addon">日</span>
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
			<div class="form-group">
					<label class="col-sm-2 control-label" for="contacterName">主要联系人</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="contacterName" name="contacterName" value="${orHouse.contacterName}">
					</div>
					<label class="col-sm-1 control-label" for="contacterPosition">职务</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="contacterPosition" name="contacterPosition" value="${orHouse.contacterPosition}">
					</div>
					<label class="col-sm-1 control-label" for="contacterTelephone">联系电话</label>
					<div class="col-sm-2">
						<input type="text" class="form-control" id="contacterTelephone" name="contacterTelephone" value="${orHouse.contacterTelephone}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="propertyName">物业名称</label>
					<div class="col-sm-8">
						<input type="text" class="form-control" id="propertyName" name="propertyName" value="${orHouse.propertyName}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="buildingNumber">建筑总栋数</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="buildingNumber" name="buildingNumber" value="${orHouse.buildingNumber}">
							<span class="input-group-addon">栋</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="buildingNumber">有电梯</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="hasLiftNumber" name="hasLiftNumber" value="${orHouse.hasLiftNumber}">
							<span class="input-group-addon">栋</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="buildingNumber">无电梯</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="nonLiftNumber" name="nonLiftNumber" value="${orHouse.nonLiftNumber}">
							<span class="input-group-addon">栋</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="lobbyNumber">大堂数量</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="lobbyNumber" name="lobbyNumber" value="${orHouse.lobbyNumber}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="liftLobbyNumber">电梯大堂</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="liftLobbyNumber" name="liftLobbyNumber" value="${orHouse.liftLobbyNumber}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="nonLiftLobbyNumber">非电梯大堂</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="nonLiftLobbyNumber" name="nonLiftLobbyNumber" value="${orHouse.nonLiftLobbyNumber}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="ownerHouseholds">业主总户数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="ownerHouseholds" name="ownerHouseholds" value="${orHouse.ownerHouseholds}">
							<span class="input-group-addon">户</span>
						</div>
					</div>
					<label class="col-sm-2 control-label" for="tenantHouseholds">租户总户数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="tenantHouseholds" name="tenantHouseholds" value="${orHouse.tenantHouseholds}">
							<span class="input-group-addon">户</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="deliveryHouseholds">已经交房户数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="deliveryHouseholds" name="deliveryHouseholds" value="${orHouse.deliveryHouseholds}">
							<span class="input-group-addon">户</span>
						</div>
					</div>
					<label class="col-sm-2 control-label" for="nonDeliveryHouseholds">未交房户数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="nonDeliveryHouseholds" name="nonDeliveryHouseholds" value="${orHouse.nonDeliveryHouseholds}">
							<span class="input-group-addon">户</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="coveredArea">总建筑面积</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="coveredArea" name="coveredArea" value="${orHouse.coveredArea}">
							<span class="input-group-addon">平方米</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="period">开发期数</label>
					<div class="col-sm-3">
						<div class="input-group">
							<input type="text" class="form-control" id="period" name="period" value="${orHouse.period}">
							<span class="input-group-addon">期</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="field-1">周界长度</label>
					<label class="col-sm-1 control-label" for="westEastLength">东西长约</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="westEastLength" name="westEastLength" value="${orHouse.westEastLength}">
							<span class="input-group-addon">米</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="southNorthLength">南北长约</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="southNorthLength" name="southNorthLength" value="${orHouse.southNorthLength}">
							<span class="input-group-addon">米</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="field-1">规划出入口数量</label>
					<label class="col-sm-1 control-label" for="planSidewayNum">人行</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="planSidewayNum" name="planSidewayNum" value="${orHouse.planSidewayNum}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="planCarwayNum">车行</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="planCarwayNum" name="planCarwayNum" value="${orHouse.planCarwayNum}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="actual">实际使用出入口数量</label>
					<label class="col-sm-1 control-label" for="actualSidewayNum">人行</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="actualSidewayNum" name="actualSidewayNum" value="${orHouse.actualSidewayNum}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
					<label class="col-sm-1 control-label" for="southNorthLength">车行</label>
					<div class="col-sm-2">
						<div class="input-group">
							<input type="text" class="form-control" id="actualCarwayNum" name="actualCarwayNum" value="${orHouse.actualCarwayNum}">
							<span class="input-group-addon">个</span>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>