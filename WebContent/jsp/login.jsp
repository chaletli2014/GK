<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<%@include file="common/header.jsp"%>
<body style="background: #F4F4F4;">
	<div class="login_logo">
		<img src="<%=basePath%>images/login_logo2.png"  alt="" style="cursor: pointer;">
	</div>
	<div class="login_logo_center">
		<img src="<%=basePath%>images/login_logo1.png"  alt="">
	</div>
	<div class="login_messageDiv">
		<c:if test="${web_login_message != ''}">
			<span style="margin-top:40px;color:red;" id="web_login_message">${web_login_message}</span>
	    </c:if>
	</div>
	<div class="login_form">
		<div class="login_form_title">物库管理台</div>
		<form action="dologin" name="loginForm" id="loginForm" method="post">
			<div style="padding:10px 30px;">
				<input type="text" name="web_username" style="width:194px;height:20px;padding:12px;color:#A7C0BF;background: #DDE3EC;" placeholder="账号 /电子邮箱"/>
			</div>
			<div style="padding:10px 30px;">
				<input type="password" name="web_password" style="width:194px;height:20px;padding:12px;background: #DDE3EC;" placeholder="密码"/>
			</div>
			<div style="padding:10px 30px;">
				<input type="checkbox" name="rememberMe"><span style="color:#A7C0BF">记住登录状态</span>
				<a class="login_register_link" onclick="registerUser()">注册账号</a>
			</div>
			<div style="text-align: center;">
				<img id="login_btn" src="<%=basePath%>images/login_button.png"  alt="" onclick="submitLogin()" style="cursor: pointer;">
			</div>
		</form>
	</div>
	<div class="login_footer1">
		<div class="login_footer_wechat">关注我们</div>
		<div class="login_footer_weibo">&nbsp;</div>
		<div class="login_footer_info">
			<ul>
				<li>上海物库网络科技有限公司</li>
				<li>地址：上海市普陀区西康路1255号普陀科技大厦101室(200040)</li>
				<li>电话：+86 21 6209 5281</li>
				<li>邮箱：contact@goodsquick.com</li>
			</ul>
		</div>
	</div>
	<div class="login_footer2">
		<div>2015 © GoodsQuick Technologies. All Rights Reserved.</div>
		<div>沪ICP备14035318号-2</div>
	</div>
</body>
</HTML>
