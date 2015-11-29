<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/moduleSPManagement.js"></script>
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
				<div class="breadcrumb-env">
					<ol class="breadcrumb bc-1">
					<li>
						<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
					</li>
					<li>
						<a href="#" onclick="javascript:void(0)">物链管理</a>
					</li>
					<li class="active">
						<strong>组件商管理</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<input type="hidden" name="orHouseId" id="orHouseId" value="${orHouse.id}"/>
					<div>
						<div class="panel-heading" style="margin-top:20px;">
							<h3 class="panel-title" style="line-height: 28px; margin-right:10px;">组件商信息</h3>
							<%--
							<a id="newModuleSPLink">
								<button class="btn btn-icon btn-info">
									<li class="fa-plus-square"></li>
								</button>
							</a>
							 --%>
						</div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<th>组件名称</th>
									<th>组件商名称</th>
									<th>备注</th>
									<th>查看</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${houseModuleSPList}" var="moduleSP">
									<tr>
										<td >${moduleSP.moduleType}</td>
										<td >${moduleSP.moduleSPName}</td>
										<td >${moduleSP.remark}</td>
										<td>
											<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
												删除
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="addModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>