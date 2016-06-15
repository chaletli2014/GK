<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/repositoryUser.js"></script>
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
				<span style="margin-left:28px;font-size:16px;font-weight: bold;">库用户管理</span>
				<span style="float:right;margin-top:2px;">
					<a style="margin-right:30px;" id="newRepoUserLink" class="btn btn-orange">
						<i class="fa fa-plus"></i>
						新增库用户
					</a>
				</span>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<div>
						<table class="table table-bordered table-striped" id="repositoryUserTable">
							<thead>
								<tr>
									<th width="20%">用户名</th>
									<th width="15%">联系电话</th>
									<th width="30%">操作 </th>
								</tr>
							</thead>
							<tbody class="middle-align">
								<c:forEach items="${repositoryUserList}" var="repoUser">
									<tr>
										<td >${repoUser.userName}</td>
										<td >${repoUser.telephone}</td>
										<td>
											<a aid="${repoUser.id}" href="#" class="btn btn-secondary btn-sm btn-icon icon-left repoUserEditLink">
												编辑
											</a>
											<a aid="${repoUser.id}" href="#" class="btn btn-black btn-sm btn-icon icon-left repoUserDeleteLink">
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
	<%@include file="addRepositoryUser.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>