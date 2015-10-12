<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<body class="page-body" onload="checkMessage('${errorMessage}')">
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
			<div class="page-title">
				<div class="title-env">
					<h1 class="title">我的报表</h1>
				</div>
					<div class="breadcrumb-env">
						<ol class="breadcrumb bc-1">
						<li>
							<a href="<%=basePath%>index"><i class="fa-home"></i>首页</a>
						</li>
						<li>
							<a href="#" onclick="javascript:void(0)">我的控制台</a>
						</li>
						<li class="active">
							<strong>我的报表</strong>
						</li>
						</ol>
				</div>
			</div>
			<div class="panel">
				<div>
				<span>选择报表内容</span>
					<select onchange="showChart('<%=basePath%>')" id="chartType">
						<option value="">---请选择---</option>
						<option value="chart1">建筑年代分布</option>
						<option value="chart2">电梯区域分布</option>
						<option value="chart3">市场销售趋势</option>
					</select>
				</div>
			</div>
			<div class="panel panel-default">
				<div class="info-top" id="container"></div>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
</body>
</html>