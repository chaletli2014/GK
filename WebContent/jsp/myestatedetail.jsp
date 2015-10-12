<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
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
	
	var newDevice = "新增设备";
	$inputs.each(function(i, el){
		var $el = jQuery(el);
		if( $el.prop('checked') ){
			newDevice = newDevice + $el.val() + ",";
		}
	});
	alert(newDevice.substring(0, newDevice.length-1));
	location.reload();
}
</script>
<style>
.tab-content{
	line-height: 25px;
}
.tab-content .form-group{
	margin-bottom:0px;
}
</style>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container"><!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
			
		<!-- Add "fixed" class to make the sidebar fixed always to the browser viewport. -->
		<!-- Adding class "toggle-others" will keep only one menu item open at a time. -->
		<!-- Adding class "collapsed" collapse sidebar root elements and show only icons. -->
		<jsp:include page="common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        	<jsp:param name="opened" value="${opened}"/>
        	<jsp:param name="actived" value="${actived}"/>
        </jsp:include>
		<div class="main-content">
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">上海万达广场（南汇）明细</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">不动产物品库管理</a>
					</li>
					<li class="active">
						<strong>我的所有不动产</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div>
					<a href="<%=basePath%>myestate">
						<button class="btn btn-info btn-icon btn-icon-standalone">
							<i class="fa-retweet"></i>
							<span>返回</span>
						</button>
					</a>
				</div>
				<div class="panel-body">
					<div class="col-md-12">
						<div class="tabs-vertical-env tabs-vertical-bordered"><!-- add class "right-aligned" for the right aligned tabs -->
							<ul class="nav tabs-vertical">
								<li class="active"><a href="#v4-home" data-toggle="tab">基本信息</a></li>
								<li><a href="#v4-profile" data-toggle="tab">设备列表</a></li>
								<li><a href="#v4-messages" data-toggle="tab">其他</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="v4-home">
									<div class="row">
										<div class="col-md-12">
											<ul class="nav nav-tabs nav-tabs-justified">
												<li class="active">
													<a href="#home-3" data-toggle="tab">
														<span class="visible-xs"><i class="fa-home"></i></span>
														<span class="hidden-xs">基本参数</span>
													</a>
												</li>
												<li>
													<a href="#profile-3" data-toggle="tab">
														<span class="visible-xs"><i class="fa-user"></i></span>
														<span class="hidden-xs">硬件参数</span>
													</a>
												</li>
												<li>
													<a href="#messages-3" data-toggle="tab">
														<span class="visible-xs"><i class="fa-envelope-o"></i></span>
														<span class="hidden-xs">轨道公交</span>
													</a>
												</li>
												<li>
													<a href="#settings-3" data-toggle="tab">
														<span class="visible-xs"><i class="fa-cog"></i></span>
														<span class="hidden-xs">周边配套</span>
													</a>
												</li>
											</ul>
											<form role="form" class="form-horizontal">
											<div class="tab-content col-md-12">
												<div class="tab-pane active" id="home-3">
													<div class="form-group">
														<label class="col-sm-3 control-label" for="field-1">名称</label>
														<div class="col-sm-9">上海万达广场（南汇）</div>
													</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
														<label class="col-sm-3 control-label" for="field-1">开发商</label>
														<div class="col-sm-9">万达集团</div>
													</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
														<label class="col-sm-3 control-label" for="field-1">物业公司</label>
														<div class="col-sm-9">万达物业</div>
													</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
														<label class="col-sm-3 control-label" for="field-1">竣工年月</label>
														<div class="col-sm-9">2014年9月20号</div>
													</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
															<label class="col-sm-3 control-label" for="field-1">楼宇总建筑面积</label>
															<div class="col-sm-2">
																<div class="input-group">
																	1000<span class="input-group-addon">m²</span>
																</div>
															</div>
														</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
															<label class="col-sm-3 control-label" for="field-1">标准层面积</label>
															<div class="col-sm-2">
																<div class="input-group">
																	900<span class="input-group-addon">m²</span>
																</div>
															</div>
														</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
															<label class="col-sm-3 control-label" for="field-1">总楼层数</label>
															<div class="col-sm-2">8</div>
														</div>
													<div class="form-group-separator"></div>
														
													<div class="form-group">
															<label class="col-sm-3 control-label" for="field-1">得房率</label>
															<div class="col-sm-2">
																<div class="input-group">
																	72<span class="input-group-addon">%</span>
																</div>
															</div>
														</div>
													<div class="form-group-separator"></div>
												</div>
												<div class="tab-pane" id="profile-3">
														<div class="panel-heading">
															楼层信息：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">面积</label>
															<div class="col-sm-2">
																<div class="input-group">
																500<span class="input-group-addon">m²</span>
																</div>
															</div>
															<label class="col-sm-1 control-label" for="field-1">层高</label>
															<div class="col-sm-2">
																<div class="input-group">
																4.3<span class="input-group-addon">m</span>
																</div>
															</div>
															<label class="col-sm-2 control-label" for="field-1">净层高</label>
															<div class="col-sm-2">
																<div class="input-group">
																4<span class="input-group-addon">m</span>
																</div>
															</div>
															<label class="col-sm-2 control-label" for="field-1">得房率</label>
															<div class="col-sm-2">
																<div class="input-group">
																71<span class="input-group-addon">%</span>
																</div>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															大堂：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">层高</label>
															<div class="col-sm-2">
																<div class="input-group">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
																<span class="input-group-addon">m</span>
																</div>
															</div>
															<label class="col-sm-1 control-label" for="field-1">地面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">墙面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">天花</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															公共走廊：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">宽度</label>
															<div class="col-sm-2">
																<div class="input-group">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
																<span class="input-group-addon">m</span>
																</div>
															</div>
															<label class="col-sm-1 control-label" for="field-1">地面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">墙面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">天花</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															交屋标准：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">地面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">墙面</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">天花</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															电梯配置：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">品牌</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">客梯</label>
															<div class="col-sm-2">
																<div class="input-group">
																	<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
																	<span class="input-group-addon">部</span>
																</div>
															</div>
															<label class="col-sm-1 control-label" for="field-1">货梯</label>
															<div class="col-sm-2">
																<div class="input-group">
																	<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
																	<span class="input-group-addon">部</span>
																</div>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															车位配置：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1">地下</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
															<label class="col-sm-1 control-label" for="field-1">地上</label>
															<div class="col-sm-2">
																<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															卫生间：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label"></label>
															<div class="col-sm-10">
																<label class="cbr-inline">
																	<input type="radio" name="radio-1" class="cbr" checked>
																	冷水
																</label>
																<label class="cbr-inline">
																	<input type="radio" name="radio-1" class="cbr">
																	冷热水
																</label>
																<label class="cbr-inline">
																	<input type="radio" name="radio-1" class="cbr">
																	恒温水
																</label>
															</div>
														</div>
														
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															空调配置：
														</div>
														<div class="form-group">
															<div class="col-sm-10">
																<label class="col-sm-2 control-label" for="field-1">品牌：</label>
																<div class="col-sm-2">
																	<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
																</div>
																<label class="cbr-inline">
																	<input type="radio" name="radio-2" class="cbr" checked>
																	分体空调
																</label>
																<label class="cbr-inline">
																	<input type="radio" name="radio-2" class="cbr">
																	新风系统
																</label>
																<label class="cbr-inline">
																	<input type="radio" name="radio-2" class="cbr">
																	集中式中央空调
																</label>
																<label class="cbr-inline">
																	<input type="radio" name="radio-2" class="cbr">
																	半集中式中央空调
																</label>
															</div>
														</div>
														
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															安防系统：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	IC一卡通控制系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	闭路电视监视系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	门传感器监视系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	24小时巡逻系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	停车控制及车牌识别系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	应急电源,照明和扩音系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	智能自动火警检测系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-1">
																	自动喷水灭火系统
																</label>
															</div>
														</div>
														
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															通讯系统：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-2">
																	光纤
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-2">
																	无线网络
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-2">
																	卫星系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-2">
																	微波系统
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-2">
																	ADSL
																</label>
															</div>
														</div>
														
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															楼内配套：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	银行
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	ATM
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	餐饮
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	便利店
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	食堂
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	干洗店
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	商务中心
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	会议室
																</label>
																<label class="cbr-inline">
																	<input type="checkbox" name="checkbox-3">
																	会展中心
																</label>
															</div>
														</div>
													</div>
													
													<div class="tab-pane" id="messages-3">
														<div class="panel-heading">
															公交线路：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															地铁线路：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															快速干道：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
													</div>
													
													<div class="tab-pane" id="settings-3">
														<div class="panel-heading">
															临近商街：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															商场：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															酒店：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															银行：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
														<div class="form-group-separator"></div>
														<div class="panel-heading">
															餐饮：
														</div>
														<div class="form-group">
															<label class="col-sm-1 control-label" for="field-1"></label>
															<div class="col-sm-10">
																<textarea class="form-control" cols="5" id="field-5"></textarea>
															</div>
														</div>
													</div>
											</div>
												</form>
										</div>
									</div>
								</div>
								<div class="tab-pane" id="v4-profile">
									<script type="text/javascript">
									jQuery(document).ready(function($)
									{
										$("#example-2").dataTable({
											dom: "t" + "<'row'<'col-xs-6'i><'col-xs-6'p>>",
											aoColumns: [
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
										<a href="javascript:;" onclick="jQuery('#modal-6').modal('show', {backdrop: 'fade'});">
											<button class="btn btn-purple btn-icon btn-icon-standalone">
												<i class="fa-cog"></i>
												<span>新增设备</span>
											</button>
										</a>
									</div>
									<div class="panel panel-color panel-success">
										<div class="panel-heading" style="padding:10px 20px;">
											<h3 class="panel-title">电梯列表</h3>
											<div class="panel-options">
												<a href="#" data-toggle="panel">
													<span class="collapse-icon">&ndash;</span>
													<span class="expand-icon">+</span>
												</a>
											</div>
										</div>
										<div class="panel-body">
											<table class="table table-bordered table-striped" id="example-2">
												<thead>
													<tr>
														<th>设备名称</th>
														<th>规格型号</th>
														<th>制造厂家</th>
														<th>安装地点</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody class="middle-align">
													<tr>
														<td title="乘客电梯1">乘客电梯1</td>
														<td title="pl001">pl001</td>
														<td>电梯厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯2">乘客电梯2</td>
														<td title="pl001">pl002</td>
														<td>电梯厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯3">乘客电梯3</td>
														<td title="pl003">pl003</td>
														<td>电梯厂1</td>
														<td>1栋3号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
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
									<div class="panel panel-color panel-success collapsed">
										<div class="panel-heading" style="padding:10px 20px;">
											<h3 class="panel-title">给排水列表</h3>
											<div class="panel-options">
												<a href="#" data-toggle="panel">
													<span class="collapse-icon">&ndash;</span>
													<span class="expand-icon">+</span>
												</a>
											</div>
										</div>
										<div class="panel-body">
											<table class="table table-bordered table-striped" id="gpwater">
												<thead>
													<tr>
														<th>设备名称</th>
														<th>总功率</th>
														<th>制造厂家</th>
														<th>安装地点</th>
														<th>操作</th>
													</tr>
												</thead>
												<tbody class="middle-align">
													<tr>
														<td title="生活水泵1">生活水泵1</td>
														<td title="1000KW">1000KW</td>
														<td>水泵厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯1">补水稳压泵1</td>
														<td title="pl001">1000KW</td>
														<td>水泵厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯1">排污泵1</td>
														<td title="pl001">1000KW</td>
														<td>水泵厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯1">消防水泵1</td>
														<td title="pl001">1000KW</td>
														<td>水泵厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
																设备维护
															</a>
															<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
																删除
															</a>
														</td>
													</tr>
													<tr>
														<td title="乘客电梯1">喷淋水泵1</td>
														<td title="pl001">1000KW</td>
														<td>水泵厂1</td>
														<td>1栋2号</td>
														<td>
															<a href="#" class="btn btn-info btn-sm btn-icon icon-left">
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
								</div>
								<div class="tab-pane" id="v4-messages">
									建设中...
								</div>
							</div>
						</div>	
					</div>
				</div>
			</div>
			<!-- Main Footer -->
			<%@include file="common/footer.jsp" %>
		</div>
	</div>
    <div class="modal fade" id="modal-6">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">搜索设备</h4>
				</div>
				<div class="modal-body">
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
						
						$("#searchDevice_table").on('draw.dt', function()
						{
							cbr_replace();
							$state.trigger('change');
						});
						
						// Script to select all checkboxes
						$state.on('change', function(ev)
						{
							var $chcks = $("#searchDevice_table tbody input[type='checkbox']");
							if($state.is(':checked'))
							{
								$chcks.prop('checked', true).trigger('change');
							}
							else
							{
								$chcks.prop('checked', false).trigger('change');
							}
						});
					});
					</script>
					<div class="row" style="display:none;" id="searchDevice_div">
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
	<jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>