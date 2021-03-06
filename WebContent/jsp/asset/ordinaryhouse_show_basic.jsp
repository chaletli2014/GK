<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-default">
	<div class="panel-body">
		<table class="show_table">
			<tr>
				<td class="show_tdTitle">开发公司</td>
				<td id="show_company">&nbsp;</td>
			</tr>
			<tr>
				<td class="show_tdTitle">项目方位</td>
				<td id="show_buildingName">
					<div class="input-group">
						<span class="input-group-addon">东至</span>
						<div id="show_projectPositionE"></div>
						<span class="input-group-addon">西至</span>
						<div id="show_projectPositionW"></div>
					</div>
					<div class="input-group">
						<span class="input-group-addon">南至</span>
						<div id="show_projectPositionS"></div>
						<span class="input-group-addon">北至</span>
						<div id="show_projectPositionN"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">竣工时间</td>
				<td>
					<div class="input-group">
						<div id="show_finishYear" style="display: inline;"></div>
						<div id="show_finishMonth" style="display: inline;"></div>
						<div id="show_finishDate" style="display: inline;"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">开盘时间</td>
				<td>
					<div class="input-group">
						<div id="show_startYear" style="display: inline;"></div>
						<div id="show_startMonth" style="display: inline;"></div>
						<div id="show_startDate" style="display: inline;"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">入住时间</td>
				<td id="show_location">
					<div class="input-group">
						<div id="show_checkinYear" style="display: inline;"></div>
						<div id="show_checkinMonth" style="display: inline;"></div>
						<div id="show_checkinDate" style="display: inline;"></div>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">占地面积</td>
				<td>
					<div class="input-group col-sm-2">
						<div id="show_floorSpace" style="display: inline;"></div>
						<span class="input-group-addon">平方米</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">主要联系人</td>
				<td id="show_contacterName">&nbsp;</td>
			</tr>
			<tr>
				<td class="show_tdTitle">职务</td>
				<td id="show_contacterPosition">&nbsp;</td>
			</tr>
			<tr>
				<td class="show_tdTitle">联系电话</td>
				<td id="show_contacterTelephone">&nbsp;</td>
			</tr>
			<tr>
				<td class="show_tdTitle">物业名称</td>
				<td id="show_propertyName">&nbsp;</td>
			</tr>
			<tr>
				<td class="show_tdTitle">建筑总栋数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_buildingNumber">&nbsp;</div>
						<span class="input-group-addon">栋</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">有电梯栋数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_hasLiftNumber">&nbsp;</div>
						<span class="input-group-addon">栋</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">无电梯栋数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_nonLiftNumber">&nbsp;</div>
						<span class="input-group-addon">栋</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">大堂数量</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_lobbyNumber">&nbsp;</div>
						<span class="input-group-addon">个</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">电梯大堂</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_liftLobbyNumber">&nbsp;</div>
						<span class="input-group-addon">个</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">无电梯栋数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_nonLiftLobbyNumber">&nbsp;</div>
						<span class="input-group-addon">个</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">业主总户数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_ownerHouseholds">&nbsp;</div>
						<span class="input-group-addon">户</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">租户总户数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_tenantHouseholds">&nbsp;</div>
						<span class="input-group-addon">户</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">已经交房户数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_deliveryHouseholds">&nbsp;</div>
						<span class="input-group-addon">户</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">未交房户数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_nonDeliveryHouseholds">&nbsp;</div>
						<span class="input-group-addon">户</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">总建筑面积</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_coveredArea">&nbsp;</div>
						<span class="input-group-addon">平方米</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">开发期数</td>
				<td>
					<div class="input-group col-sm-3">
						<div id="show_period">&nbsp;</div>
						<span class="input-group-addon">期</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">周界长度</td>
				<td id="show_buildingName">
					<div class="input-group">
						<span class="input-group-addon">东西长约</span>
						<div id="show_westEastLength">&nbsp;</div>
						<span class="input-group-addon">米</span>
						<span class="input-group-addon">南北长约</span>
						<div id="show_southNorthLength">&nbsp;</div>
						<span class="input-group-addon">米</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">规划出入口数量</td>
				<td id="show_buildingName">
					<div class="input-group">
						<span class="input-group-addon">人行</span>
						<div id="show_planSidewayNum">&nbsp;</div>
						<span class="input-group-addon">个</span>
						<span class="input-group-addon">车行</span>
						<div id="show_planCarwayNum">&nbsp;</div>
						<span class="input-group-addon">个</span>
					</div>
				</td>
			</tr>
			<tr>
				<td class="show_tdTitle">实际使用出入口数量</td>
				<td id="show_buildingName">
					<div class="input-group">
						<span class="input-group-addon">人行</span>
						<div id="show_actualSidewayNum">&nbsp;</div>
						<span class="input-group-addon">个</span>
						<span class="input-group-addon">车行</span>
						<div id="show_actualCarwayNum">&nbsp;</div>
						<span class="input-group-addon">个</span>
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>