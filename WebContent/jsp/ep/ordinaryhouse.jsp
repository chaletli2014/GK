<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/ordinaryhouse.js"></script>
<script type="text/javascript">
jQuery(document).ready(function($){
	if( $("#orHouse").val() == null || $("#orHouse").val() == '' ){
		jConfirm("您还没有添加任何资品，是否现在添加？","信息",function(r) {
	    	if(r){
	    		window.location.href="<%=basePath%>newProductPre";
	    	}
		});
	}
});
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
				<%@include file="../common/nav-title.jsp"%>
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物库管理</a>
					</li>
					<li class="active">
						<strong>资品信息</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<%--
				<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#serviceDetailTable").dataTable({
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
				 --%>
				<div class="panel-body">
					<input type="hidden" id="orHouse" value="${orHouse}"/>
					<c:if test="${null==orHouse}">
						<div>
							<span>您还没有添加任何资品,赶紧添加吧！</span>
						</div>
						<div>
							<a href="<%=basePath%>newProductPre">
								<button class="btn btn-purple btn-icon btn-icon-standalone">
									<i class="fa-cog"></i>
									<span>新增资品</span>
								</button>
							</a>
						</div>
					</c:if>
					<c:if test="${orHouse!=null}">
						<div style="width:50%;float:left;">
							<blockquote class="blockquote blockquote-info" style="height:318px;font-size:14px;">
								<p>
									<strong>资品信息</strong>&nbsp;&nbsp;
									<a href="javascript:void(0)" id="editHouseLink">
										<i class="fa-edit"></i>
									</a>
								</p>
								<p>
									资品名称：<a class="houseNameLink" tabindex="${orHouse.id}" href="javascript:void(0)" title="点击查看明细">${orHouse.buildingName}</a>
								</p>
								<p>
									资品类型：<span>${orHouse.propertyType}</span>
								</p>
								<p>
									坐落位置：<span>${orHouse.location}</span>
								</p>
								<p>
									所属地域：<span>${orHouse.province} - ${orHouse.city}</span>
								</p>
								<p style="display: inline-flex;">
									竣工时间：
									<span class="input-group col-sm-8" style="text-align: center;">
										<span>${orHouse.finishYear}</span><span class="input-group-addon">年</span>
										<span>${orHouse.finishMonth}</span><span class="input-group-addon">月</span>
										<span>${orHouse.finishDate}</span><span class="input-group-addon">日</span>
									</span>
								</p>
								<p>
									开发公司：<span>${orHouse.company}</span>
								</p>
								<p>
									物业公司：<span>${orHouse.propertyName}</span>
								</p>
							</blockquote>
						</div>
						<div style="width:40%;float:right;">
							<img alt="" src=""/>
							<%--
							<ul class="sp_list">
								<li class="sp_list_item">
									<a href="javascript:void(0)" id="publicBody">
										<div class="sp_title">主体构件</div>
										<div class="sp_detail"></div>
									</a>
								</li>
								<li class="sp_list_item">
									<a href="javascript:void(0)" id="publicEquipment">
										<div class="sp_title">设施设备</div>
										<div class="sp_detail"></div>
									</a>
								</li>
								<li class="sp_list_item">
									<a href="javascript:void(0)" id="publicInstallation">
										<div class="sp_title">其它</div>
										<div class="sp_detail"></div>
									</a>
								</li>
							</ul>
							 --%>
						</div>
					</c:if>
					<%--
					<c:if test="${fn:length(serviceDetails) > 0 }">
						<div class="panel-heading" style="clear: both;">
							<h3 class="panel-title">组件列表</h3>
						</div>
						<table class="table table-bordered table-striped" id="serviceDetailTable">
							<thead>
								<tr>
									<th>服务名称</th>
									<th>服务范围</th>
									<th>服务价格</th>
									<th>服务简介</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${serviceDetails}" var="serviceDetail">
									<tr>
										<td >${serviceDetail.serviceName}</td>
										<td >${serviceDetail.serviceRangeName}</td>
										<td >${serviceDetail.price}</td>
										<td >${serviceDetail.serviceContent}</td>
										<td>
											<ul class="table_action_list">
												<li>
													<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
														删除
													</a>
												</li>
											</ul>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					 --%>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="ordinaryhouse_popshow.jsp" %>
	<%@include file="ordinaryhouse_popadd.jsp" %>
	<%@include file="ordinaryhouse_module_body.jsp" %>
	<%@include file="ordinaryhouse_module_installation.jsp" %>
	<%@include file="ordinaryhouse_module_equipment.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>