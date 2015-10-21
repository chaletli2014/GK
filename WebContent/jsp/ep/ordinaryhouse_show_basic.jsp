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
		</table>
	</div>
</div>