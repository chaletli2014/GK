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
								null
							],
						});
					});
					</script>
					<div>
						<a href="javascript:void(0);" onclick="showSearch()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>搜索设备</span>
							</button>
						</a>
						<a href="javascript:void(0);" onclick="showAdd()">
							<button class="btn btn-purple btn-icon btn-icon-standalone">
								<i class="fa-cog"></i>
								<span>手动新增设备</span>
							</button>
						</a>
						<a href="<%=basePath%>ordinaryhouse">
							<button class="btn btn-info btn-icon btn-icon-standalone">
								<i class="fa-retweet"></i>
								<span>返回</span>
							</button>
						</a>
					</div>
					<c:forEach items="${goodsDics}" var="goodsDic" varStatus="status">
						<div class='panel panel-color panel-success <c:if test="${status.first!=true}">collapsed</c:if>'>
							<div class="panel-heading" style="padding:10px 20px;">
								<h3 class="panel-title">${goodsDic.dicName}</h3>
								<div class="panel-options">
									<a href="#" data-toggle="panel">
										<span class="collapse-icon">&ndash;</span>
										<span class="expand-icon">+</span>
									</a>
								</div>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped deviceTable">
									<thead>
										<tr>
											<th>设备名称</th>
											<th>品牌</th>
											<th>规格型号</th>
											<th>制造厂家</th>
											<th>安装地点</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody class="middle-align">
										<tr>
											<td title="乘客电梯1">乘客电梯1</td>
											<td title="A品牌">A品牌</td>
											<td title="pl001">pl001</td>
											<td>电梯厂1</td>
											<td>1栋2号</td>
											<td>
												<a href="<%=basePath%>officebuildingdevice" class="btn btn-info btn-sm btn-icon icon-left">
													设备维护
												</a>
												<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
													删除
												</a>
											</td>
										</tr>
										<tr>
											<td title="乘客电梯2">乘客电梯2</td>
											<td title="A品牌">A品牌</td>
											<td title="pl001">pl002</td>
											<td>电梯厂1</td>
											<td>1栋2号</td>
											<td>
												<a href="<%=basePath%>officebuildingdevice" class="btn btn-info btn-sm btn-icon icon-left">
													设备维护
												</a>
												<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
													删除
												</a>
											</td>
										</tr>
										<tr>
											<td title="乘客电梯3">乘客电梯3</td>
											<td title="A品牌">A品牌</td>
											<td title="pl003">pl003</td>
											<td>电梯厂1</td>
											<td>1栋3号</td>
											<td>
												<a href="<%=basePath%>officebuildingdevice" class="btn btn-info btn-sm btn-icon icon-left">
													设备维护
												</a>
												<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
													删除
												</a>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<div class="modal fade" id="deviceSearch_div">
		<div class="modal-dialog">
			<div class="modal-content" style="min-height: 400px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">搜索设备</h4>
				</div>
				<div class="modal-body">
					<div class="row" style="margin-bottom: 20px;">
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
					<div class="row">
						<div class="input-group input-group-minimal">
							<input type="text" class="form-control" placeholder="输入想要搜索的产品编号&hellip;" id="devicesearch_input">
							<span class="input-group-addon">
								<i class="linecons-search"></i>
							</span>
						</div>
					</div>
					<script type="text/javascript">
					jQuery(document).ready(function($){
						$("#searchDevice_table").dataTable({
							dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
							aoColumns: [
								{bSortable: false},
								{bSortable: false},
								{bSortable: false},
								{bSortable: false},
								{bSortable: false},
								{bSortable: false}
							]
						});
						
						// Replace checkboxes when they appear
						var $state = $("#searchDevice_table thead input[type='checkbox']");
						
						$("#searchDevice_table").on('draw.dt', function(){
							cbr_replace();
							$state.trigger('change');
						});
						
						// Script to select all checkboxes
						$state.on('change', function(ev){
							var $chcks = $("#searchDevice_table tbody input[type='checkbox']");
							if($state.is(':checked')){
								$chcks.prop('checked', true).trigger('change');
							}else{
								$chcks.prop('checked', false).trigger('change');
							}
						});
					});
					</script>
					<div class="row" id="searchDevice_div" style="display:none;">
						<table class="table table-bordered table-striped" id="searchDevice_table">
						<thead>
							<tr>
								<th class="no-sorting">
									<input type="checkbox" class="cbr">
								</th>
								<th>设备名称</th>
								<th>设备分类</th>
								<th>生产厂家</th>
								<th>规格型号</th>
								<th>是否占用</th>
							</tr>
						</thead>
						
						<tbody class="middle-align">
							<tr>
								<td>
									<input type="checkbox" class="cbr" value="pla00101">
								</td>
								<td title="乘客电梯a1">乘客电梯a1</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr" value="pla00201">
								</td>
								<td title="乘客电梯a2">乘客电梯a2</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00201</td>
								<td>未占用</td>
							</tr>
							
							<tr>
								<td>
									<input type="checkbox" class="cbr" value="gla00101">
								</td>
								<td title="货用电梯a1">货用电梯a1</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂1</td>
								<td>gla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="货用电梯a2">货用电梯a2</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂2</td>
								<td>gla00201</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调制冷a1">中央空调制冷a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acco00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调交换a1">中央空调交换a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acc00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="乘客电梯a1">乘客电梯a1</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="乘客电梯a2">乘客电梯a2</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00201</td>
								<td>未占用</td>
							</tr>
							
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="货用电梯a1">货用电梯a1</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂1</td>
								<td>gla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="货用电梯a2">货用电梯a2</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂2</td>
								<td>gla00201</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调制冷a1">中央空调制冷a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acco00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调交换a1">中央空调交换a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acc00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="乘客电梯a1">乘客电梯a1</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="乘客电梯a2">乘客电梯a2</td>
								<td title="乘客电梯">乘客电梯</td>
								<td>电梯厂1</td>
								<td>pla00201</td>
								<td>未占用</td>
							</tr>
							
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="货用电梯a1">货用电梯a1</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂1</td>
								<td>gla00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="货用电梯a2">货用电梯a2</td>
								<td title="货用电梯">货用电梯</td>
								<td>电梯厂2</td>
								<td>gla00201</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调制冷a1">中央空调制冷a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acco00101</td>
								<td>未占用</td>
							</tr>
							<tr>
								<td>
									<input type="checkbox" class="cbr">
								</td>
								<td title="中央空调交换a1">中央空调交换a1</td>
								<td>空调</td>
								<td>格力空调</td>
								<td>acc00101</td>
								<td>未占用</td>
							</tr>
						</tbody>
					</table>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-info" onclick="adddevices()">保存</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deviceAdd_div">
		<div class="modal-dialog">
			<div class="modal-content" style="min-height: 400px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">手动新增设备</h4>
				</div>
				<form action="addDevice2OrdinaryHouse" method="post" class="validate" role="form" onsubmit="return checkMessage()">
				<div class="modal-body">
						<div class="row" style="margin-bottom: 10px;">
							<c:if test="${fn:length(goodsDics) > 0}">
								<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#deviceType2").selectBoxIt().on('open', function()
										{
											$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
										});
									});
								</script>
								<select class="form-control" id="deviceType2" name="deviceType2">
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