<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>js/goodsquick/index.js"></script>
<body class="page-body">
	<jsp:include page="common/settings_pane.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
	<div class="page-container">
		<div class="main-content">
			<jsp:include page="common/index_main_nav.jsp" flush="true">
	        	<jsp:param name="basePath" value="<%=basePath%>"/>
	        </jsp:include>
			<div class="page-title">
				<span class="index_title">欢迎你，</span><span>${currentUser.loginName}</span>
			</div>
			<div class="panel panel-default">
				<div class="panel-body" style="padding-top:0px;">
					<ul class="index_ul">
						<li>
							<div class="index_li_title">资品库</div>
							<div class="index_repository">
								<div class="index_li_list">
									<div>
										<a href="<%=basePath%>mainIndex?repository_code=${currentUser.loginName}_0">
										初始资品库
										</a>
									</div>
									<div>
										<i class="fa-envelope-o"></i>消息
										<span class="label label-default pull-right">0</span>
									</div>
									<div>
										<i class="fa-chain"></i>关联申请
										<span class="label label-secondary pull-right">1</span>
									</div>
								</div>
							<c:forEach items="${repositoryList1}" var="repos">
								<div class="index_li_list">
									<div>
										<a href="<%=basePath%>mainIndex?repository_code=${repos.repositoryCode}">
										${repos.repositoryName}
										</a>
									</div>
									<div>
										<i class="fa-envelope-o"></i>消息
										<span class="label label-default pull-right">0</span>
									</div>
									<div>
										<i class="fa-chain"></i>关联申请
										<span class="label label-default pull-right">0</span>
									</div>
								</div>
							</c:forEach>
							</div>
						</li>
						<li>
							<div class="index_li_title">产品库</div>
							<div class="index_repository">
								<c:forEach items="${repositoryList2}" var="repos">
								<div class="index_li_list">
									<div>
										<a href="<%=basePath%>mainIndex?repository_code=${repos.repositoryCode}">
										${repos.repositoryName}
										</a>
									</div>
									<div>
										<i class="fa-envelope-o"></i>消息
										<span class="label label-default pull-right">0</span>
									</div>
									<div>
										<i class="fa-chain"></i>关联申请
										<span class="label label-default pull-right">0</span>
									</div>
								</div>
							</c:forEach>
							</div>
						</li>
						<li>
							<div class="index_li_title">需品库</div>
							<div class="index_repository">
								<c:forEach items="${repositoryList3}" var="repos">
								<div class="index_li_list">
									<div>
										<a href="<%=basePath%>mainIndex?repository_code=${repos.repositoryCode}">
										${repos.repositoryName}
										</a>
									</div>
									<div>
										<i class="fa-envelope-o"></i>消息
										<span class="label label-default pull-right">0</span>
									</div>
									<div>
										<i class="fa-chain"></i>关联申请
										<span class="label label-default pull-right">0</span>
									</div>
								</div>
							</c:forEach>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<%@include file="common/login_footer.jsp" %>
	<jsp:include page="common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>