<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.goodsquick.model.WebUserInfo"%>
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
	String repoPreName = "资品";
	if( null != currentRepository && "3".equalsIgnoreCase(currentRepository.getRepositoryType())){
		repoPreName = "需品";
	}else if( null != currentRepository && "2".equalsIgnoreCase(currentRepository.getRepositoryType())){
		repoPreName = "产品";
	}
	
	String sideBar = "../common/sidebar.jsp";
	WebUserInfo webUser = (WebUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if( null != webUser && "group1".equalsIgnoreCase(webUser.getUserType()) ){
		sideBar = "../common/sidebar_community.jsp";
	}
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
    <script type="text/javascript" src="<%=basePath%>/js/goodsquick/goodsquick.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/highcharts.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/report/report.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/report/drilldown.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/goodsquick/message.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/xenon-core.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/xenon-forms.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/xenon-components.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/xenon-skins.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/custom.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/user_Login.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/zTreeStyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/select2/select2.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/select2/select2-bootstrap.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/jquery.alerts.css">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/global.css">
	
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