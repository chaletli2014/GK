<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
function submitForm(){
	var brandName = $("#brandName").val();
	var designName = $("#designName").val();
	var certificationName = $("#certificationName").val();
	var channelName = $("#channelName").val();
	var logisticsName = $("#logisticsName").val();
	var ownerName = $("#ownerName").val();
	var trusteeshipName = $("#trusteeshipName").val();
	var supervisionName = $("#supervisionName").val();
	var recyclingName = $("#recyclingName").val();
	var orHouseId = $("#orHouseId").val();
	var relationshipPropertyId = $("#relationshipPropertyId").val();
	jQuery.ajax({
		url: "<%=basePath%>savehouseSP",
		data:{
			orHouseId : orHouseId,
			relationshipPropertyId : relationshipPropertyId,
			brandName : brandName,
			designName : designName,
			certificationName : certificationName,
			channelName : channelName,
			logisticsName : logisticsName,
			ownerName : ownerName,
			trusteeshipName : trusteeshipName,
			supervisionName : supervisionName,
			recyclingName : recyclingName
		}
		,success: function(response){
			var result = response.result;
			if( result != 'Y' ){
				jAlert("保存服务商信息失败:"+response.message,"提醒");
			}else{
				jAlert("保存服务商信息成功","提醒");
			}
		}
	});
}
</script>
<body class="page-body">
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
					<h1 class="title">${orHouse.buildingName} 服务商维护</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">商品信息管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">不动产列表</a>
					</li>
					<li class="active">
						<strong>服务商维护</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
					<div class="panel-body">
						<input type="hidden" name="orHouseId" id="orHouseId" value="${orHouse.id}"/>
						<input type="hidden" name="relationshipPropertyId" id="relationshipPropertyId" value="${relationshipProperty.id}"/>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="brandName">品牌商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="brandName" name="brandName" value="${relationshipProperty.brandName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="designName">设计商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="designName" name="designName" value="${relationshipProperty.designName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="certificationName">检测认证商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="certificationName" name="certificationName" value="${relationshipProperty.certificationName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="channelName">渠道商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="channelName" name="channelName" value="${relationshipProperty.channelName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="logisticsName">物流商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="logisticsName" name="logisticsName" value="${relationshipProperty.logisticsName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="ownerName">商品所有人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="ownerName" name="ownerName" value="${relationshipProperty.ownerName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="trusteeshipName">商品托管商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="trusteeshipName" name="trusteeshipName" value="${relationshipProperty.trusteeshipName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="supervisionName">市场监管人</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="supervisionName" name="supervisionName" value="${relationshipProperty.supervisionName}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="recyclingName">回收处理商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="recyclingName" name="recyclingName" value="${relationshipProperty.recyclingName}">
							</div>
						</div>
					</div>
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