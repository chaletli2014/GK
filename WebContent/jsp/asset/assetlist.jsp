<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/assetlist.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/subjectList.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/ordinaryhouse.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/houseDevice.js"></script>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/houseOther.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css" />
<style>
.user-info-navbar{margin-bottom:20px;}
.two_nav{width:100%;height:60px; margin-bottom:20px; background:#fff;}
.two_nav h3{width:95%; height:50px; margin:0 auto; border-bottom:1px #ccc solid; 
	line-height:50px; font-size:16px; font-weight:800;}
.two_nav ul{width:95%; height:40px; line-height:40px; margin:0 auto; margin-top:10px;}
.two_nav li{float:left; padding:0 15px;cursor: pointer;}
.two_nav li a{color:inherit;}
.two_nav li:hover{border-bottom:2px #00a4ef solid; color:#00a4ef;}
.two_nav li.active{ border-bottom:2px #00a4ef solid; color:#00a4ef;}
.asset_div{
	background: #fff;
	padding:30px;
}
.add_item{
	width:200px;
	margin:10px 0; 
	text-align:center; 
	background:url(../GoodsQuick/images/icon/add.png) no-repeat 35px center;
	display: inline-block;
}
.add_item a{display:inline-block; width:80%; text-indent:14px; padding:8px 0; border:1px #999 dashed; color:#999;}
.add_item a:hover{color:#000; border-color:#000;}
</style>
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
			<div class="two_nav">
				<ul>
					<li class="dropdown hover-line active">
						<a id="asset_a_overview" href="#" data-toggle="dropdown">
							<span class="">概览</span>
						</a>
					</li>
					<li class="dropdown">
						<a id="asset_a_subject" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">主体构件</span>
						</a>
						<!--主体构件下拉 -->
					</li>
					<li class="dropdown">
						<a id="asset_a_device" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">设施设备</span>
						</a>
					</li>
					<li class="dropdown">
						<a id="asset_a_other" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">材料&装饰</span>
						</a>
					</li>
				</ul>
			</div>
			<%@include file="asset_overview.jsp" %>
			<%@include file="asset_subject.jsp" %>
			<%@include file="asset_device.jsp" %>
			<%@include file="asset_other.jsp" %>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="ordinaryhouse_popadd.jsp" %>
	<%@include file="ordinaryhouse_popshow.jsp" %>
	<%@include file="houseDeviceLift.jsp" %>
	<%@include file="houseOtherPaint.jsp" %>
	<%@include file="subjectModule.jsp" %>
	<%@include file="subjectModify.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>