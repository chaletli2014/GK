<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="houseDeviceDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">设施设备编辑</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#device_tab_basic" data-toggle="tab">基本信息</a>
					</li>
					<li>
						<a href="#device_tab_main" data-toggle="tab">主要参数</a>
					</li>
					<li>
						<a href="#device_tab_source" data-toggle="tab">资料库</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<form role="form" class="form-horizontal" id="modifyDevice_form">
				<input type="hidden" id="deviceId" name="deviceId">
				<input type="hidden" id="subjectId" name="subjectId">
				<input type="hidden" id="moduleId" name="moduleId">
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="device_tab_basic">
						<div class="modal-body">
							<div class="content">
							<%--
								<div class="label label-warning">注意：以下内容为必填项，若为空，无法提交</div>
							 --%>
								<div class="panel-body">
								<div class="form-group">
										<label class="col-sm-2 control-label" for="field-1">设备位置</label>
										<div class="col-sm-6">
											<div class="input-group">
												<span class="input-group-addon">主体</span>
												<input type="text" class="form-control" id="subjectName_device" name="subjectName" disabled="disabled">
												<span class="input-group-addon">构件</span>
												<input type="text" class="form-control" id="moduleName_device" name="moduleName" disabled="disabled">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deviceType">设备类别</label>
										<div class="col-sm-6">
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
												<option value="">请选择</option>
												<option value="yy">曳引式客梯</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deviceCode">编码</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="deviceCode" name="deviceCode" 
											data-validate="required" data-message-required="编码不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deviceName">设备名称</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="deviceName" name="deviceName" 
											data-validate="required" data-message-required="名称不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deviceDesc">设备描述</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="deviceDesc" name="deviceDesc">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deliveryDate">交付日期</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="deliveryDate" name="deliveryDate">
										</div>
										<label class="col-sm-2 control-label" for="purchasePrice">采购价格</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="purchasePrice" name="purchasePrice">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="deviceUser">使用人</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="deviceUser" name="deviceUser">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="device_tab_main">
						<jsp:include page="houseDeviceMain.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					</form>
					<div class="tab-pane" id="device_tab_source">
					<%--
						<%@include file="ordinaryhouse_param_source.jsp" %>
					 --%>
					</div>
					<!-- Tabs Pager -->
					<div style="float: right;margin-top:20px;">
						<a id="saveLiftBtn">
							<button class="btn btn-success btn-icon btn-icon-standalone">
								<i class="fa-check-square-o"></i>
								<span>保存</span>
							</button>
						</a>
					</div>
					<%--
					<ul class="pager wizard">
						<li class="previous">
							<a href="#"><i class="entypo-left-open"></i>上一步</a>
						</li>
						<li class="next">
							<a href="#" id="newProductNext">下一步<i class="entypo-right-open"></i></a>
						</li>
					</ul>
					 --%>
				</div>
			</div>
		</div>
	</div>
</div>
