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
				<div class="panel-body" style="padding-top:0px; text-align:center;">
					访问地址不存在...
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