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
		<jsp:include page="common/sidebar.jsp" flush="true">
        	<jsp:param name="basePath" value="<%=basePath%>"/>
        </jsp:include>
		<div class="main-content">
			<jsp:include page="common/main-nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<h3 style="margin-top:0px;margin-bottom:2px;">
				<span>
					<%=currentRepository.getId()==0?"初始资品库":currentRepository.getRepositoryName()%>
				</span>
				看板
			</h3>
			<div class="row draggable-portlets">
				<div class="col-md-12">
					<div class="sorted">
						<div class="panel panel-color panel-light-blue">
							<!-- panel head -->
							<div class="panel-heading panel-heading-thin">
								<div class="panel-title">我的面板</div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<ul class="index_device">
									<li>
										<span class="info_title">客户数量</span>
										<span class="info_detail"><a href="javascript:void(0)">30</a></span>
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
					<div class="sorted">
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading  panel-heading-thin" style="background:#4C4F53">
								<div class="panel-title"><b>设备监控</b></div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<div class="info-top" id="alertreport" style="width:100%;heigth:200px;"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="sorted">
						<div class="panel panel-color panel-success">
							<!-- panel head -->
							<div class="panel-heading  panel-heading-thin" style="background:#4C4F53">
								<div class="panel-title"><b>设备分布</b></div>
							</div>
							<!-- panel body -->
							<div class="panel-body">
								<div class="info-top" id="deviceArea" style="width:100%;heigth:200px;"></div>
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