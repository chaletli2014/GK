<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="asset_div" id="asset_overview">
	<div class="panel-body" style="padding-top:0px;padding-left:0px;">
		<input type="hidden" id="orHouse" value="${orHouse}"/>
		<c:if test="${null==orHouse}">
			<div>
				<span>您还没有添加任何资品,赶紧添加吧！</span>
				<div class="add_item">
					<a href="<%=basePath%>newProductPre">添加资品库</a>
				</div>
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
	<c:if test="${orHouse!=null}">
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
	</c:if>
</div>