<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/echarts/echarts.js"></script>
<body class="page-body">
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
			<div class="row draggable-portlets">
				<div id="liftYearBar" style="height:500px;border:1px solid #ccc;padding:10px;"></div>  
			      <script type="text/javascript">
				</script>
			</div>
			<%@include file="../common/footer.jsp" %>
		</div>
	</div>
	<jsp:include page="../common/bottomScript.jsp" flush="true">
       	<jsp:param name="basePath" value="<%=basePath%>"/>
    </jsp:include>
    <script type="text/javascript" src="<%=basePath%>/js/goodsquick/daChart.js"></script>
</body>
</html>