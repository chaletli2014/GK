<%@page import="com.goodsquick.model.GoodsRepository"%>
<%@page import="com.goodsquick.utils.GoodsQuickAttributes"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="gq" uri="/gq" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
    GoodsRepository currentRepository = (GoodsRepository)request.getSession().getAttribute(GoodsQuickAttributes.WEB_SESSION_REPOSITORY_OBJ);
%>
<head>
	<title>GoodsQuick</title>
    <meta charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="goodsquick" />
	<meta http-equiv="expires" content="0">
	<meta name="author" content="" />
	
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.alerts.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/goodsquick/login.js"></script>
	<link href="<%=basePath%>css/login.css" rel="stylesheet">
	<link href="<%=basePath%>css/reset.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.alerts.css">
	
	<script type="text/javascript">
	var basePath = "<%=basePath%>";
	function submitLogin(){
		$("#login_btn").attr('src', basePath+"images/login_button_2.png");
		$("#loginForm").submit();
	}
	function registerUser(){
		window.location.href="<%=basePath%>registerUser";
	}
	</script>
</head>