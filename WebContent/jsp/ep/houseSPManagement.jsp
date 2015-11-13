<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function($){
	$("#addNewSP").click(function(){
		jQuery('#new_module_sp_div').modal('show', {backdrop: 'static'});
	});
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
						<strong>${spTypeName}管理</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="panel-body">
					<input type="hidden" name="orHouseId" id="orHouseId" value="${orHouse.id}"/>
					<script type="text/javascript">
					jQuery(document).ready(function($)
					{
						$("#moduleSPTable").dataTable({
							dom: "t" + "<'row'<'col-xs-3'i><'col-xs-9'p>>",
							aoColumns: [
								null,
								null
							],
						});
					});
					</script>
					<div>
						<div>
							<a href="javascript:void(0)" id="addNewSP">
								<button class="btn btn-purple btn-icon btn-icon-standalone">
									<i class="fa-cog"></i>
									<span>新增${spTypeName}</span>
								</button>
							</a>
						</div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<th>${spTypeName}名称</th>
									<th>查看</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${houseSPList}" var="houseSP">
									<tr>
										<td >${houseSP.moduleSPValue}</td>
										<td>
											<a href="#" class="btn btn-danger btn-sm btn-icon icon-left module_body_detail">
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
	<%@include file="addHouseModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>