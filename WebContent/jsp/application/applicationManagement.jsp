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
	$(document).on('click', '.applist_btn', function(){
		jAlert("功能建设中...","提示");
	});
});

</script>
<body class="page-body">
	<input type="hidden" id="basePath" value="<%=basePath%>">
	<jsp:include page="../common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<jsp:include page="<%=sideBar%>" flush="true">
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
					<li class="active">
						<a>应用管理</a>
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
									<h4 class="modal-title">应用列表</h4>
								</div>
								<div class="product_level">
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="预警管理">
													预警管理
												</button>
											</div>
										</section>
									</div>
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="计划管理">
													计划管理
												</button>
											</div>
										</section>
									</div>
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="车位管理">
													车位管理
												</button>
											</div>
										</section>
									</div>
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="巡检管理">
													巡检管理
												</button>
											</div>
										</section>
									</div>
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="账目管理">
													账目管理
												</button>
											</div>
										</section>
									</div>
									<div class="product_pre_model" style="width:170px;">
										<section class="content">
											<div class="box bg-4">
												<button class="button button--tamaya button--border-thick" data-text="例会管理">
													例会管理
												</button>
											</div>
										</section>
									</div>
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