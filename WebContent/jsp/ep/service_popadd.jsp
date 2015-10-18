<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="newServiceDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">新增服务类产品</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#tab1" data-toggle="tab">定义产品名称</a>
					</li>
					<li>
						<a href="#tab2" data-toggle="tab">填写基本参数</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<form role="form" class="form-horizontal" action="saveorupdateservice" method="post" id="addservice_form">
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
											<input type="text" class="form-control" id="serviceName" name="serviceName" data-validate="required" data-message-required="名称不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="serviceRangeCode">服务范围</label>
										<div class="col-sm-4">
											<c:if test="${fn:length(serviceCodes) > 0}">
												<script type="text/javascript">
													jQuery(document).ready(function($)
													{
														$("#serviceCode").selectBoxIt().on('open', function()
														{
															$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
														});
													});
												</script>
												<select class="form-control" id="serviceRangeCode" name="serviceRangeCode">
													<option value="">---请选择---</option>
													<c:forEach items="${serviceCodes}" var="serviceCode">
														<option value="${serviceCode.dicCode}">${serviceCode.dicName}</option>
													</c:forEach>
												</select>
											</c:if>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="price">服务价格</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" id="price" name="price" value="${serviceDetail.price}">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
					<%@include file="service_param_basic.jsp" %>
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
