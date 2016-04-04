<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<%@include file="common/header.jsp"%>
<body>
	<div class="banner_wrap">
		<div class="login_logo">
			<img src="<%=basePath%>images/login_banner.png"  alt="" style="cursor: pointer;">
		</div>
		<div class="login_form">
			<div class="login_form_title">
				<h2>物库管理后台</h2>
				<a onclick="registerUser()">企业注册</a>
			</div>
			<div class="login_main">
				<form action="dologin" name="loginForm" id="loginForm" method="post">
					<p class="login_name">
						<input type="text" name="web_username"placeholder="账号 /电子邮箱"/>
					</p>
					<p>
						<input type="password" name="web_password" placeholder="密码"/>
					</p>
					<p class="login_messageDiv">
						<c:if test="${web_login_message != ''}">
							<span style="margin-top:40px;color:red;" id="web_login_message">${web_login_message}</span>
					    </c:if>
					</p>
					<p style="text-align: center;">
						<img id="login_btn" src="<%=basePath%>images/login_button.png"  alt="" onclick="submitLogin()" style="cursor: pointer;">
					</p>
					<p class="login_link">
						<label>
						<input type="checkbox" name="rememberMe">记住登录状态
						</label>
					</p>
				</form>
			</div>
		</div>
	</div>
	<%@include file="common/login_footer.jsp" %>
</body>
</HTML>
