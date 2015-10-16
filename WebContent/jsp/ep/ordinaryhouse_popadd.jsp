<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="editDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增产品</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#tab1" data-toggle="tab">定义产品名称</a>
					</li>
					<li>
						<a href="#tab2" data-toggle="tab">填写基本参数</a>
					</li>
					<li>
						<a href="#tab3" data-toggle="tab">填写详细参数</a>
					</li>
					<li>
						<a href="#tab4" data-toggle="tab">关联设备</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<form role="form" class="form-horizontal" action="saveordinaryhouse" method="post" id="addordinaryhouse_form">
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="tab1">
						<div class="modal-body">
							<div class="content">
								<div class="label label-warning">注意：以下内容为必填项，若为空，无法完成注册</div>
								<div class="panel-body">
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-1">产品名称</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="buildingName" name="buildingName" data-validate="required" data-message-required="名称不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="field-1">所在地域</label>
										<div class="col-sm-8">
											<div class="input-group">
												<span class="input-group-addon">省份</span>
												<script type="text/javascript">
													jQuery(document).ready(function($)
													{
														$("#province").selectBoxIt().on('open', function()
														{
															$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
														});
													});
												</script>
												<select class="form-control" id="province" name="province">
													<option value="">请选择</option>
													<option value="上海">上海</option>
													<option value="北京">北京</option>
													<option value="深圳">深圳</option>
												</select>
												<span class="input-group-addon">城市</span>
												<script type="text/javascript">
													jQuery(document).ready(function($)
													{
														$("#city").selectBoxIt().on('open', function()
														{
															$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
														});
													});
												</script>
												<select class="form-control" id="city" name="city">
													<option value="">请选择</option>
													<option value="上海">上海</option>
													<option value="北京">北京</option>
													<option value="深圳">深圳</option>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="location">坐落位置</label>
										<div class="col-sm-8">
											<input type="text" class="form-control" id="location" name="location" value="${orHouse.location}">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
						<jsp:include page="ordinaryhouse_param_basic.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					<div class="tab-pane" id="tab3">
						<jsp:include page="ordinaryhouse_param_detail.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					<div class="tab-pane" id="tab4">
						<jsp:include page="ordinaryhouse_param_device.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					<!-- Tabs Pager -->
					<ul class="pager wizard">
						<li class="previous">
							<a href="#"><i class="entypo-left-open"></i>上一步</a>
						</li>
						<li class="next">
							<a href="#" id="newProductNext">下一步<i class="entypo-right-open"></i></a>
						</li>
					</ul>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
