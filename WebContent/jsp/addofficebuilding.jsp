<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript">
function clearform(){
	$("#addofficebuilding_form input").val("");
}
function submitForm(){
	window.location.href = '<%=basePath%>officebuilding';
}
</script>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
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
					<h1 class="title">新增写字楼</h1>
				</div>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">不动产产品库管理</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">商业物业</a>
					</li>
					<li class="active">
						<strong>商务办公楼（写字楼）</strong>
					</li>
					</ol>
				</div>
			</div>
			<form role="form" class="form-horizontal" action="saveprofile" method="post" id="addofficebuilding_form">
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
					
					<div class="tab-content">
							<div class="tab-pane active" id="home-3">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">名称</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="building_name" placeholder="写字楼名称" name="building_name" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">开发商</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="login_name" placeholder="开发商名称" name="login_name" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">物业公司</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="name" placeholder="物业公司名称" name="name" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">竣工年月</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="password" placeholder="竣工年月" name="password" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">楼宇总建筑面积</label>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="password" placeholder="楼宇总建筑面积" name="password" value="">
											<span class="input-group-addon">m²</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">标准层面积</label>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="password" placeholder="标准层面积" name="password" value="">
											<span class="input-group-addon">m²</span>
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">总楼层数</label>
									<div class="col-sm-3">
										<input type="text" class="form-control" id="password" placeholder="总楼层数" name="password" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label" for="field-1">得房率</label>
									<div class="col-sm-3">
										<div class="input-group">
											<input type="text" class="form-control" id="password" placeholder="得房率" name="password" value="">
											<span class="input-group-addon">%</span>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="profile-3">
								<div class="panel-heading">
									楼层信息：
								</div>
								<div class="form-group">
									<label class="col-sm-1 control-label" for="field-1">面积</label>
									<div class="col-sm-2">
										<div class="input-group">
										<input type="text" class="form-control" id="login_name" placeholder="面积" name="login_name" value="">
										<span class="input-group-addon">m²</span>
										</div>
									</div>
									<label class="col-sm-1 control-label" for="field-1">层高</label>
									<div class="col-sm-2">
										<div class="input-group">
										<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
										<span class="input-group-addon">m</span>
										</div>
									</div>
									<label class="col-sm-1 control-label" for="field-1">净层高</label>
									<div class="col-sm-2">
										<div class="input-group">
										<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
										<span class="input-group-addon">m</span>
										</div>
									</div>
									<label class="col-sm-1 control-label" for="field-1">得房率</label>
									<div class="col-sm-2">
										<div class="input-group">
										<input type="text" class="form-control" id="name" placeholder="" name="name" value="">
										<span class="input-group-addon">%</span>
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
						<button class="btn btn-red btn-icon btn-icon-standalone" onclick="clearform()">
							<i class="fa-magic"></i>
							<span>清空</span>
						</button>
					</div>
				</div>
			</div>
			<!-- Main Footer -->
			<!-- Choose between footer styles: "footer-type-1" or "footer-type-2" -->
			<!-- Add class "sticky" to  always stick the footer to the end of page (if page contents is small) -->
			<!-- Or class "fixed" to  always fix the footer to the end of page -->
			<%@include file="common/footer.jsp" %>
		</div>
    </div>
    <jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>