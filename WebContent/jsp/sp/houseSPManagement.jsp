<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/myHouseSP.js"></script>
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
								null,
								null,
								null
							],
						});
					});
					</script>
					<div>
						<c:choose>
							<c:when test="${null == houseSP}">
								<div>
									<a href="javascript:void(0)" id="addNewSP">
										<button class="btn btn-purple btn-icon btn-icon-standalone">
											<i class="fa-cog"></i>
											<span>新增${spTypeName}</span>
										</button>
									</a>
								</div>
							</c:when>
							<c:otherwise>
								<div style="width:400px;">
									<blockquote class="blockquote blockquote-info" style="height:218px;">
										<p>
											<strong>${spTypeName}信息</strong>&nbsp;&nbsp;
											<a href="javascript:void(0)" id="editSpLink">
												<i class="fa-edit"></i>
											</a>
										</p>
										<p>
											名称：${houseSP.moduleSPValue}
										</p>
										<p>
											备注：<span>${houseSP.remark}</span>
										</p>
									</blockquote>
								</div>
							</c:otherwise>
						</c:choose>
						<div class="panel-heading" style="margin-top:20px;">
							<h3 class="panel-title" style="line-height: 28px; margin-right:10px;">二级${spTypeName}</h3>
							<a id="newSecondSP">
								<button class="btn btn-icon btn-info">
									<li class="fa-plus-square"></li>
								</button>
							</a>
						</div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<th>托管组件</th>
									<th>名称</th>
									<th>备注</th>
									<th>查看</th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${sp2ndList}" var="sp2nd">
									<tr>
										<td >${sp2nd.moduleType}</td>
										<td >${sp2nd.spName}</td>
										<td >${sp2nd.remark}</td>
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
	<%@include file="../ep/addHouseModuleSP.jsp" %>
	<%@include file="addSecondHouseModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>