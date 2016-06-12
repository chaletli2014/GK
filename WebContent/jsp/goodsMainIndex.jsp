<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/goodsServiceIndex.js"></script>
<style>
.index_device li{
  float: left;
  list-style: none;
  margin: 10px;
}
.info_title{
	display: inline-block;
	padding: 10px;
  	background: #2c2e2f;
  	color: #fff;
  	font-weight: bold;
}
.info_detail{
	display: inline-block;
  	padding: 10px;
}
.color_red{
	color:red;
}
.color_green{
	color:green;
}
</style>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="common/new_sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        </jsp:include>
		<div class="main-content">
	        <jsp:include page="common/main-nav-new.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="row draggable-portlets">
				<div class="col-md-12">
					<div>
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">物讯面板</div>
							</div>
							<!-- panel body -->
							<div class="panel-body" style="padding-top:4px;">
								<ul class="index_device">
									<li>
										<span class="info_title">客户数量</span>
										<span class="info_detail"><a href="javascript:void(0)">20</a></span>
									</li>
									<li>
										<span class="info_title">客户报障</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_red">0</a></span>
									</li>
									<li>
										<span class="info_title">我的消息</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_green">0</a></span>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<div class="panel panel-color panel-success">
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">资品看板</div>
								<div style="float:right;">
									<a href="<%=basePath%>newAssetPre">
										<button class="btn btn-primary">+ 新增资品</button>
									</a>
								</div>
							</div>
							<div class="panel-body" style="padding-top:4px;">
								<div class="label label-secondary">不动产</div>
								<span>${orHouse.buildingName}</span>
							</div>
							<c:forEach items="${lifts}" var="lift">
							<div class="panel-body" style="padding-top:4px;">
								<div class="label label-secondary">动产</div>
								${lift.liftBrand}
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div>
						<div class="panel panel-color panel-success">
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">物链看板</div>
							</div>
							<div class="panel-body" style="padding-top:4px;">
								<div class="panel panel-color panel-info" style="border:1px solid #40bbea;"><!-- Add class "collapsed" to minimize the panel -->
									<div class="panel-heading index_panel_heading" style="padding:4px;">
										<h3 class="panel-title">物链雷达</h3>
										<a href="<%=basePath%>houseRadar?spTypeCode=house_radar_supplier">更多</a>
									</div>
									<div class="panel-body index_panel_lift">
										<c:forEach items="${radarCompany}" var="company">
											<p>${company.companyName}</p>
										</c:forEach>
									</div>
								</div>
								<div class="panel panel-color panel-info" style="border:1px solid #40bbea;"><!-- Add class "collapsed" to minimize the panel -->
									<div class="panel-heading index_panel_heading" style="padding:4px;">
										<h3 class="panel-title">关联申请</h3>
										<a>更多</a>
									</div>
									<div class="panel-body index_panel_lift">
										<p></p>
									</div>
								</div>
								<div class="panel panel-color panel-info" style="border:1px solid #40bbea;"><!-- Add class "collapsed" to minimize the panel -->
									<div class="panel-heading index_panel_heading" style="padding:4px;">
										<h3 class="panel-title">推荐供应商</h3>
									</div>
									<div class="panel-body index_panel_lift">
										<p></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>