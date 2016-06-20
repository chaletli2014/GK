<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="modal fade showDetailDiv" id="showHouseDiv">
	<div class="modal-dialog" style="width:70%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">资品信息</h4>
			</div>
			<div id="rootwizard" class="form-wizard">
				<ul class="tabs">
					<li class="active">
						<a href="#tab1" data-toggle="tab">基本信息</a>
					</li>
					<li>
						<a href="#tab2" data-toggle="tab">主要参数</a>
					</li>
					<li>
						<a href="#tab5" data-toggle="tab">资料库</a>
					</li>
				</ul>
				<div class="progress-indicator">
					<span></span>
				</div>
				<div class="tab-content">
					<!-- Tabs Content -->
					<div class="tab-pane active" id="tab1">
						<div class="modal-body">
							<div class="content">
								<div class="panel-body">
									<table class="show_table">
										<tr>
											<td class="show_tdTitle">物业类型</td>
											<td id="show_propertyType">&nbsp;</td>
										</tr>
										<tr>
											<td class="show_tdTitle">资品名称</td>
											<td id="show_buildingName">&nbsp;</td>
										</tr>
										<tr>
											<td class="show_tdTitle">所在地域</td>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td class="show_tdTitle">坐落位置</td>
											<td id="show_location">&nbsp;</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="tab2">
						<jsp:include page="ordinaryhouse_show_basic.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
					<div class="tab-pane" id="tab5">
						<jsp:include page="ordinaryhouse_show_source.jsp" flush="true">
				        	<jsp:param name="basePath" value="<%=basePath%>"/>
				        </jsp:include>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
