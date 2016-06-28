<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/daMap.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BVcqwEMHD74riuQUuyi6rvtdl3bu7aaM"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
<style>
#densityMap{
	width:100%;
	height:400px;
}
</style>
<body class="page-body">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
	<jsp:include page="../common/sidebar_community.jsp" flush="true">
    	<jsp:param name="basePath" value="<%=basePath%>"/>
    	<jsp:param name="opened" value="${opened}"/>
    	<jsp:param name="actived" value="${actived}"/>
    </jsp:include>
		<div class="main-content">
	        <jsp:include page="../common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
	        <div class="two_nav" style="height:30px; line-height:30px">
				<ul class="nav_ul" style="height:30px; line-height:30px">
					<li class="dropdown active">
						<a aid="lift_num" class="density_tab_a" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">电梯</span>
						</a>
					</li>
					<li class="dropdown">
						<a aid="parking_vg_num" class="density_tab_a" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">地上停车位</span>
						</a>
					</li>
					<li class="dropdown">
						<a aid="parking_ug_num" class="density_tab_a" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">地下停车位</span>
						</a>
					</li>
					<li class="dropdown">
						<a aid="fire_pump_num" class="density_tab_a" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">消防泵</span>
						</a>
					</li>
				</ul>
			</div>
			<div class="row draggable-portlets">
				<div class="col-md-12">
					<div class="panel panel-color">
						<div class="panel-heading panel-heading-thin">
							 密度分布图
						</div>
						<div class="panel-body" style="padding-top:4px;">
							<div id="densityMap"></div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>