<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/communityIndex.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BVcqwEMHD74riuQUuyi6rvtdl3bu7aaM"></script>
<script type="text/javascript" src="<%=basePath%>/js/devexpress-web-14.1/js/globalize.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/devexpress-web-14.1/js/dx.chartjs.js"></script>
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
#overviewMap{
	width:100%;
	height:400px;
}
</style>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
	<jsp:include page="common/sidebar_community.jsp" flush="true">
    	<jsp:param name="basePath" value="<%=basePath%>"/>
    	<jsp:param name="opened" value="${opened}"/>
    	<jsp:param name="actived" value="${actived}"/>
    </jsp:include>
		<div class="main-content">
	        <jsp:include page="common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="row draggable-portlets">
				<div class="col-md-12">
					<div>
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">管理台</div>
							</div>
							<!-- panel body -->
							<div class="panel-body" style="padding-top:4px;">
								<ul class="index_device">
									<li>
										<span class="info_title">我的消息</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_green">0</a></span>
									</li>
									<li>
										<span class="info_title">不动产数量</span>
										<span class="info_detail"><a href="javascript:void(0)">${fn:length(communityRepoList)}</a></span>
									</li>
									<li>
										<span class="info_title">电梯数量</span>
										<span class="info_detail"><a href="javascript:void(0)" class="color_red">0</a></span>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="panel panel-color">
						<div class="panel-heading panel-heading-thin">
							位置分布图
						</div>
						<div class="panel-body" style="padding-top:4px;">
							<div id="overviewMap"></div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="panel panel-color">
						<div class="panel-heading panel-heading-thin">
							房龄分布图
						</div>
						<div class="panel-body" style="padding-top:4px;">
							<div id="assetYear" style="height: 440px; width: 100%;"></div>
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