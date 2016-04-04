<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/houseRadar.js"></script>
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
			<div class="page-title" style="background: #dddddd;">
				<span style="font-size:16px;font-weight: bold;background: #f8f8f8;padding:18px;padding-left:30px;">物链雷达</span>
				<span style="padding-left:30px;">系统通过后台数据库快速搜索到所有相关的供应商 </span>
				<span style="float:right;margin-right:40px">
					<input name="openRadar" type="checkbox" class="iswitch iswitch-secondary openRadarCheckbox">
					开启自动搜索
				</span>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<ul class="sp_type_ul" id="sp_type_ul">
					<c:forEach items="${spTypes}" var="spType">
						<li lid="${spType.dicCode}" class="<c:if test="${spType.dicCode == spTypeCode}">active</c:if>" >${spType.dicName}</li>
					</c:forEach>
					</ul>
					<div style="margin-top:20px;">
						<a style="float:right;margin-right:16px;" id="newEquSpLink" class="btn btn-blue">
							<i class="fa fa-plus"></i>
							一键关联
						</a>
					</div>
					<div>
						<table class="table table-bordered table-striped" id="radarResultTable">
							<thead>
								<tr>
									<th>供应商名称</th>
									<th>产品与服务</th>
									<th>操作 </th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<tr>
									<td >上海微机机械制作有限公司</td>
									<td >拽拉机</td>
									<td>
										<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left radarResultEditLink">
											查看
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left radarResultRelateLink">
											关联
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-danger btn-sm btn-icon icon-left radarResultDeleteLink">
											忽略
										</a>
									</td>
								</tr>
								<tr>
									<td >上海光明灯饰</td>
									<td >顶灯</td>
									<td>
										<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left radarResultEditLink">
											查看
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left radarResultRelateLink">
											关联
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-danger btn-sm btn-icon icon-left radarResultDeleteLink">
											忽略
										</a>
									</td>
								</tr>
								<tr>
									<td >上海微机机械制作有限公司</td>
									<td >牵引泵</td>
									<td>
										<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left radarResultEditLink">
											查看
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left radarResultRelateLink">
											关联
										</a>
										<a aid="${moduleSP.id}" href="#" class="btn btn-danger btn-sm btn-icon icon-left radarResultDeleteLink">
											忽略
										</a>
									</td>
								</tr>
								<c:forEach items="${radarResultList}" var="result">
									<tr>
										<td >${moduleSP.spName}</td>
										<td >${moduleSP.spTel}</td>
										<td>
											<a aid="${moduleSP.id}" href="#" class="btn btn-orange btn-sm btn-icon icon-left moduleEditLink">
												编辑
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-danger btn-sm btn-icon icon-left moduleDeleteLink">
												删除
											</a>
											<a aid="${moduleSP.id}" href="#" class="btn btn-blue btn-sm btn-icon icon-left moduleRelateLink">
												关联
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
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>