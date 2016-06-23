<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/ordinaryhouse.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/demo.css" />
<script type="text/javascript">
jQuery(document).ready(function($){
	if( $("#orHouse").val() == null || $("#orHouse").val() == '' ){
		jConfirm("您还没有添加任何资品，是否现在添加？","信息",function(r) {
	    	if(r){
	    		window.location.href="<%=basePath%>newAssetPre";
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
			<div>
				<div class="panel-body" style="padding-top:0px;padding-left:0px;">
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
						<div style="width:560px;float:left;">
							<blockquote class="blockquote blockquote-info" style="height:160px;font-size:14px;">
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
									资品类型：<span>${orHouse.propertyTypeDesc}</span>
								</p>
								<p>
									坐落位置：<span>${orHouse.location}</span>
								</p>
							</blockquote>
						</div>
						<div style="width:35%;float:left;">
							<img width="300px" height="160px" src="${orHouse.mainPic}" />
						</div>
					</c:if>
				</div>
			</div>
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<div class="treeNodeTitle">主体</div>
					<ul id="subjectTree" class="ztree"></ul>
				</div>
				<div class="zTreeDemoBackground left">
					<div class="treeNodeTitle">设施设备</div>
					<ul id="deviceTree" class="ztree"></ul>
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