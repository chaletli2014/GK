<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addordinaryhouse_form input").val("");
}
function submitForm(){
	if( null == $("#buildingName").val() || '' == $("#buildingName").val() ){
		jAlert("项目名称不能为空","提醒");
		return false;
	}
	if( null == $("#location").val() || '' == $("#location").val() ){
		jAlert("坐落位置不能为空","提醒");
		return false;
	}
	$("#addordinaryhouse_form").submit();
}
</script>
<body class="page-body" onload="checkMessage(${errorMessage})">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="../common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">不动产信息维护</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">商品信息管理</a>
					</li>
					<li class="active">
						<strong>不动产列表</strong>
					</li>
					</ol>
				</div>
			</div>
			<form role="form" class="form-horizontal" action="saveordinaryhouse" method="post" id="addordinaryhouse_form">
			<input type="hidden" name="orHouseId" id="orHouseId" value="${orHouse.id}"/>
			<input type="hidden" name="relationshipPropertyId" id="relationshipPropertyId" value="${relationshipProperty.id}"/>
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="field-1">项目名称</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="buildingName" name="buildingName" value="${orHouse.buildingName}">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label class="col-sm-3 control-label" for="field-1">目前状况</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="buildingStatus" name="buildingStatus" value="${orHouse.buildingStatus}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-tabs nav-tabs-justified">
						<li class="active">
							<a href="#basicinfo" data-toggle="tab">
								<span class="visible-xs"><i class="fa-home"></i></span>
								<span class="hidden-xs">基本信息</span>
							</a>
						</li>
						<li>
							<a href="#relationinfo" data-toggle="tab">
								<span class="visible-xs"><i class="fa-user"></i></span>
								<span class="hidden-xs">物联参数</span>
							</a>
						</li>
						<li>
							<a href="#extrainfo" data-toggle="tab">
								<span class="visible-xs"><i class="fa-user"></i></span>
								<span class="hidden-xs">扩展参数</span>
							</a>
						</li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="basicinfo">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="company">开发公司</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="company" name="company" value="${orHouse.company}">
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
								<label class="col-sm-2 control-label" for="location">坐落位置</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="location" name="location" value="${orHouse.location}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="projectPosition">项目方位</label>
								<div class="col-sm-6">
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
								<label class="col-sm-2 control-label" for="field-1">物业类型</label>
								<div class="col-sm-9">
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="zz" checked>住宅										
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="xzl">写字楼
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="sc">商场
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="tcc">停车场
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="jd">酒店
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="djc">度假村
									</label>
									<label class="cbr-inline">
										<input type="radio" name="propertyType" class="cbr" value="other">其他
									</label>
									<label class="cbr-inline">
										<input type="text" class="form-control" id="propertytypeO" name="propertytypeO" value="">
									</label>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="startDate">开盘时间</label>
								<div class="col-sm-6">
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
								<div class="col-sm-6">
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
								<div class="col-sm-3">
									<div class="input-group">
										<input type="text" class="form-control" id="floorSpace" name="floorSpace" value="${orHouse.floorSpace}">
										<span class="input-group-addon">平方米</span>
									</div>
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
						<div class="tab-pane" id="relationinfo">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="ownerName">不动产所有者</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="ownerName" name="ownerName" value="${relationshipProperty.ownerName}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="designName">建筑设计单位</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="designName" name="designName" value="${relationshipProperty.designName}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="manufacturerName">建筑施工单位</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="manufacturerName" name="manufacturerName" value="${relationshipProperty.manufacturerName}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label" for="certificationName">检测认证单位</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="certificationName" name="certificationName" value="${relationshipProperty.certificationName}">
								</div>
							</div>
						</div>
						<div class="tab-pane" id="extrainfo">
							
						</div>
					</div>
				</div>
				</div>
				</form>
			<div class="panel panel-default">
				<div class="panel-body">
					<div style="text-align: center;">
						<button class="btn btn-success btn-icon" onclick="submitForm()">
							<i class="fa-check"></i>
							<span>提交</span>
						</button>
						<a href="<%=basePath%>ordinaryhouse">
							<button class="btn btn-info btn-icon">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
    </div>
    <jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>