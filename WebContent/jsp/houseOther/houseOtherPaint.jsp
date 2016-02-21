<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade" id="housePaintDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">材料&装饰编辑</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#tab_basic" data-toggle="tab">基本信息</a>
					</li>
					<li>
						<a href="#tab_main" data-toggle="tab">主要参数</a>
					</li>
					<li>
						<a href="#tab_source" data-toggle="tab">资料库</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<form role="form" class="form-horizontal" id="modifyPaint_form">
				<input type="hidden" id="paintId" name="paintId">
				<input type="hidden" id="subjectId" name="subjectId">
				<input type="hidden" id="moduleId" name="moduleId">
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="tab_basic">
						<div class="modal-body">
							<div class="content">
								<div class="panel-body">
								<div class="form-group">
										<label class="col-sm-2 control-label" for="field-1">使用位置</label>
										<div class="col-sm-6">
											<div class="input-group">
												<span class="input-group-addon">主体</span>
												<input type="text" class="form-control" id="subjectName" name="subjectName" disabled="disabled">
												<span class="input-group-addon">构件</span>
												<input type="text" class="form-control" id="moduleName" name="moduleName" disabled="disabled">
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="paintType">材料类别</label>
										<div class="col-sm-4">
											<script type="text/javascript">
												jQuery(document).ready(function($)
												{
													$("#type1codeSelection").selectBoxIt().on('open', function()
													{
														$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
													});
												});
											</script>
											<select class="form-control" id="type1codeSelection" name="type1codeSelection">
												<option value="">请选择</option>
												<option value="jjp">建筑涂料</option>
											</select>
										</div>
										&nbsp;-&nbsp;
										<div class="col-sm-4">
											<script type="text/javascript">
												jQuery(document).ready(function($)
												{
													$("#type2codeSelection").selectBoxIt().on('open', function()
													{
														$(this).data('selectBoxSelectBoxIt').list.perfectScrollbar();
													});
												});
											</script>
											<select class="form-control" id="type2codeSelection" name="type2codeSelection">
												<option value="">请选择</option>
												<option value="wpp">防水涂料</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="paintName">材料名称</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="paintName" name="paintName" 
											data-validate="required" data-message-required="名称不能为空">
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-2 control-label" for="paintDesc">材料描述</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" id="paintDesc" name="paintDesc">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab_main">
						<jsp:include page="housePaintMain.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					</form>
					<div class="tab-pane" id="tab_source">
					<%--
						<%@include file="ordinaryhouse_param_source.jsp" %>
					 --%>
					</div>
					<!-- Tabs Pager -->
					<div style="float: right;margin-top:20px;">
						<a id="savePaintBtn">
							<button class="btn btn-success btn-icon btn-icon-standalone">
								<i class="fa-check-square-o"></i>
								<span>保存</span>
							</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
