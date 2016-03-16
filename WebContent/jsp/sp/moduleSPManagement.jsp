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
				<%@include file="../common/nav-title.jsp"%>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<input type="hidden" name="partCode" id="partCode" value="${partCode}"/>
					<input type="hidden" name="spTypeCode" id="spTypeCode" value="${spTypeCode}"/>
					<ul class="sp_type_ul" id="sp_type_ul">
					<c:forEach items="${spTypes}" var="spType">
						<li lid="${spType.dicCode}" class="<c:if test="${spType.dicCode == spTypeCode}">active</c:if>" >${spType.dicName}</li>
					</c:forEach>
					</ul>
					<div>
						<table class="table table-bordered table-striped" id="moduleSPTable">
							<thead>
								<tr>
									<th>${spTypeName}名称</th>
									<th>电话号码</th>
									<th>操作<a style="float:right;margin-right:16px;" id="newSpLink">新增</a></th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${houseModuleSPList}" var="moduleSP">
									<tr>
										<td >${moduleSP.spName}</td>
										<td >${moduleSP.spTel}</td>
										<td>
											<a href="#" class="btn btn-orange btn-sm btn-icon icon-left">
												编辑
											</a>
											<a href="#" class="btn btn-danger btn-sm btn-icon icon-left">
												删除
											</a>
											<a href="#" class="btn btn-blue btn-sm btn-icon icon-left">
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
	<%@include file="addModuleSP.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>