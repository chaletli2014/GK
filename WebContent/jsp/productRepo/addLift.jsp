<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="newLiftDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">产品编辑</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#lift_tab_basic" data-toggle="tab">基本信息</a>
					</li>
					<li>
						<a href="#lift_tab_main" data-toggle="tab">主要参数</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<form role="form" class="form-horizontal" action="saveLift" method="post" id="addlift_form">
				<input type="hidden" id="liftId" name="liftId" value="">
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="lift_tab_basic">
						<div class="modal-body">
							<div class="content">
								<div class="label label-warning">注意：以下内容为必填项，若为空，无法提交</div>
								<div class="panel-body">
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftBrand">电梯品牌</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="liftBrand" name="liftBrand" value="${lift.liftBrand}" data-validate="required" data-message-required="品牌不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftPurpose">用途</label>
										<div class="col-sm-6">
											<script type="text/javascript">
												jQuery(document).ready(function($)
												{
													$("#liftPurpose").selectBoxIt().on('open', function()
													{
														$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
													});
												});
											</script>
											<select class="form-control" id="liftPurpose" name="liftPurpose">
												<option value="">---请选择电梯用途---</option>
												<c:forEach items="${liftPurpose}" var="liftPurposeDic">
													<option value="${liftPurposeDic.dicCode}">${liftPurposeDic.dicName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftStyle">款型</label>
										<div class="col-sm-6">
											<script type="text/javascript">
												jQuery(document).ready(function($)
												{
													$("#liftStyle").selectBoxIt().on('open', function()
													{
														$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
													});
												});
											</script>
											<select class="form-control" id="liftStyle" name="liftStyle">
												<option value="">---请选择电梯款型---</option>
												<c:forEach items="${liftStyle}" var="liftStyleDic">
													<option value="${liftStyleDic.dicCode}">${liftStyleDic.dicName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftCT">载重量</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="liftCT" name="liftCT" value="${lift.liftCT}" data-validate="required" data-message-required="载重量不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftNS">额定速度</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="liftNS" name="liftNS" value="${lift.liftNS}" data-validate="required" data-message-required="额定速度不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftQA">质保</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="liftQA" name="liftQA" value="${lift.liftQA}" data-validate="required" data-message-required="质保不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-4 control-label form_input_required" for="liftPrice">产品价格</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="liftPrice" name="liftPrice" value="${lift.liftPrice}" data-validate="required" data-message-required="价格不能为空">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="lift_tab_main">
						<jsp:include page="../productRepo/lift_main.jsp" flush="true">
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
