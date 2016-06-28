<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/normalize.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/vicons-font.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/buttons.css" />
<script type="text/javascript">
jQuery(document).ready(function($){
	$(document).on('click', '.assetlist_btn', function(){
		var repoCode = $(this).attr("bCode");
		window.location.href = "<%=basePath%>mainIndex?repository_code="+repoCode;
	});
});

</script>
<body class="page-body">
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="../common/sidebar_community.jsp" flush="true">
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
								<div class="modal-header">
									<h4 class="modal-title">不动产列表</h4>
								</div>
								<div class="product_level">
									<c:forEach items="${communityRepoList}" var="repos">
										<div class="product_pre_model" style="width:170px;">
											<section class="content">
												<div class="box bg-1">
													<button bCode="${repos.repositoryCode}" class="assetlist_btn button button--moema button--text-thick button--text-upper button--size-s">
														${repos.repositoryName}
													</button>
												</div>
											</section>
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