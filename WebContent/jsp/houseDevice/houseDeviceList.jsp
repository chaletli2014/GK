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
			<div class="page-title">
				<div>
					<button id="newDevice" class="btn btn-info">
						<li class="fa-plus-square">添加设施设备</li>
					</button>
					<button id="saveDevice" class="btn btn-info">
						<span>保存</span>
					</button>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<table class="table table-hover" id="deviceTable">
						<thead>
							<tr>
								<th width="20%">设备名称</th>
								<th width="30%">设备描述</th>
								<th>设施设备位置</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach items="${houseDevices}" var="houseDevice">
								<tr>
									<td class="dataEditable" title="${houseDevice.name}">${houseDevice.name}</td>
									<td class="dataEditable" title="${houseDevice.eqDesc}">${houseDevice.eqDesc}</td>
									<td>${houseDevice.subjectName}-${houseDevice.moduleName}</td>
									<td>
										<a id="${houseDevice.id}" class="btn btn-secondary btn-sm btn-icon icon-left modifyDevice">
											编辑
										</a>
										<a id="${houseDevice.id}" class="btn btn-danger btn-sm btn-icon icon-left removeDevice">
											删除
										</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="houseDevice.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>