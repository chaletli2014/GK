<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#devicesearch_input').keydown(function(e){
		if(e.keyCode==13){
		   $("#searchDevice_div").css("display","block");
		}
	});
});

function adddevices(){
	var $inputs = jQuery('#searchDevice_table input[type="checkbox"].cbr');
	var newDevice = "新增设备 ";
	
	$inputs.each(function(i, el){
		var $el = jQuery(el);
		if( $el.prop('checked') ){
			newDevice = newDevice + $el.val() + ",";
		}
	});
	alert(newDevice.substring(0, newDevice.length-1));
	location.reload();
}

function checkMessage(){
	if( $("#deviceName").val() == ''){
		alert("设备名称不能为空");
		return false;
	}
	return true;
}

function showSearch(){
	jQuery('#deviceSearch_div').modal('show', {backdrop: 'fade'});
	$("#searchDevice_div").css("display","none");
}

function showAdd(){
	jQuery('#deviceAdd_div').modal('show', {backdrop: 'fade'});
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
					<h1 class="title">${orHouse.buildingName} 设备列表</h1>
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
			<div class="panel panel-default">
				<div class="panel-body">
					<script type="text/javascript">
					jQuery(document).ready(function($){
						$(".deviceTable").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							aoColumns: [
							    null,
								null,
								null,
								null,
								null,
								null,
								null
							],
						});
					});
					</script>
					<div>
						<a href="javascript:void(0);" onclick="showAdd()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>新增设备</span>
							</button>
						</a>
						<a href="<%=basePath%>ordinaryhouse">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
					<div class="panel panel-color panel-success">
						<div class="panel-body">
							<table class="table table-bordered table-striped deviceTable">
								<thead>
									<tr>
										<th>设备分类 </th>
										<th>设备名称</th>
										<th>制造厂家</th>
										<th>品牌</th>
										<th>规格型号</th>
										<th>数量</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="middle-align">
									<c:forEach items="${deviceList}" var="device">
										<tr>
											<td title="${device.deviceType}">${device.deviceType}</td>
											<td title="${device.deviceName}">${device.deviceName}</td>
											<td title="${device.manufacturerName}">${device.manufacturerName}</td>
											<td title="${device.brandName}">${device.brandName}</td>
											<td title="${device.model}">${device.model}</td>
											<td title="${device.deviceNum}">${device.deviceNum}</td>
											<td>
												<%--
												<a href="<%=basePath%>officebuildingdevice" class="btn btn-info btn-sm btn-icon icon-left">
												 --%>
												<a href="javascript:void(0)" class="btn btn-info btn-sm btn-icon icon-left">
													设备维护
												</a>
												<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
													删除
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<div class="modal fade" id="deviceAdd_div">
		<div class="modal-dialog">
			<div class="modal-content" style="min-height: 400px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">手动新增设备</h4>
				</div>
				<form action="addDeviceToOrdinaryHouse" method="post" class="validate" role="form" onsubmit="return checkMessage()">
				<input type="hidden" name="orHouseId" value="${orHouse.id}"/>
				<div class="modal-body">
					<div class="row" style="margin-bottom: 10px;">
						<c:if test="${fn:length(goodsDics) > 0}">
							<script type="text/javascript">
								jQuery(document).ready(function($)
								{
									$("#deviceType").selectBoxIt().on('open', function()
									{
										$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
									});
								});
							</script>
							<select class="form-control" id="deviceType" name="deviceType">
								<c:forEach items="${goodsDics}" var="dic">
									<option value="${dic.dicCode}">${dic.dicName}</option>
								</c:forEach>
							</select>
						</c:if>
						</div>
					<div class="row" style="margin-bottom: 20px;">
						<div class="form-group">
							<label class="col-sm-4 control-label" for="deviceName">设备名称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="deviceName" name="deviceName" data-validate="required" data-message-required="名称不能为空" onblur="checkValues()">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="manufacturerName">制造商</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="manufacturerName" name="manufacturerName" onblur="checkValues()">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="brandName">品牌</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="brandName" name="brandName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="model">规格型号</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="model" name="model">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label" for="deviceNum">数量</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="deviceNum" name="deviceNum">
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-info">保存</button>
				</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>