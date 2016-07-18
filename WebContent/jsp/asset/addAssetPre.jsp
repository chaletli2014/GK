<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/addAssetPre.js"></script>
<body class="page-body">
	<input type="hidden" id="basePath" value="<%=basePath%>">
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
						<a href="#" onclick="javascript:void(0)">物库管理</a>
					</li>
					<li class="active">
						<strong>添加资品</strong>
					</li>
					</ol>
				</div>
			</div>
			<div class="panel panel-default" style="padding-top:0px;">
				<div class="panel-body" style="padding-top:0px;">
					<div id="product_pre_div">
						<div class="modal-dialog" style="width:100%">
							<div class="modal-content product_block">
							<c:choose>
								<c:when test="${null != currentRepos && currentRepos.reposCategory == 'estate'}">
									<div class="modal-header">
										<h4 class="modal-title">不动产模板</h4>
									</div>
								</c:when>
								<c:when test="${null != currentRepos && currentRepos.reposCategory == 'chattel'}">
									<div class="modal-header">
										<h4 class="modal-title">动产模板</h4>
									</div>
								</c:when>
								<c:otherwise>
									<div class="modal-header">
										<h4 class="modal-title">当前库没有匹配的物库模板</h4>
									</div>
								</c:otherwise>
							</c:choose>
								<div class="product_level">
								<c:forEach items="${productTypes}" var="templateType">
									<div class="product_pre_model">
										<a class="product_link ${templateType.dicCode}" title="${templateType.dicCode}">${templateType.dicName}</a>
									</div>
								</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<%@include file="../ep/ordinaryhouse_popadd.jsp" %>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>