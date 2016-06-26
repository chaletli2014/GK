<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/houseDevice.js"></script>
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
	        <div class="two_nav" style="overflow-x: scroll;">
				<ul class="nav_ul">
					<li class="dropdown hover-line active">
						<a aid="all" class="asset_a_device" href="javascript:void(0)" data-toggle="dropdown">
							<span class="">全部</span>
						</a>
					</li>
					<c:forEach items="${assetDeviceTypes}" var="assetDevice">
						<li class="dropdown">
							<a aid="${assetDevice.dicCode}" class="asset_a_device" href="javascript:void(0)" data-toggle="dropdown">
								<span class="">${assetDevice.dicName}</span>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="panel panel-default" id="deviceDiv">
				<div>
					<button id="newDevice" class="btn btn-info">
						<li class="fa-plus-square">添加设施设备</li>
					</button>
					<button id="saveDevice" class="btn btn-info">
						<span>保存</span>
					</button>
				</div>
				<div class="panel-body" style="padding-top:0px;">
					<input id="eqTypeCode_h" type="hidden"/>
					<input id="assetDeviceTypeArray" type="hidden" value='${assetDeviceTypeArray}'/>
					<table class="table table-hover" id="deviceTable">
						<thead>
							<tr>
								<th width="7%">分类</th>
								<th width="15%">设备名称</th>
								<th width="30%">设备描述</th>
								<th>设施设备位置</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
						</tbody>
					</table>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="houseDeviceLift.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>