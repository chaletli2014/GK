<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<%@include file="common/header.jsp"%>
<body style="background: #000;">
	<div>
		<img src="<%=basePath%>images/logo-blue.png" width="180" height="60" alt="" style="cursor: pointer;">
	</div>
	<table id="__01" border="0" cellpadding="0" cellspacing="0" style="margin:auto auto;">
	<tr>
		<td class="login_top_left"></td>
		<td colspan="4"  class="login_top_center"></td>
		<td  class="login_top_right"></td>
	</tr>
	<tr>
		<td rowspan="3" class="login_center_left"></td>
		<td colspan="4" class="login_center_center">
		<form action="dologin" name="loginForm" id="loginForm" method="post">
			<div style="margin-top:97px;margin-left:160px">
				<input type="text" name="web_username" style="width:194px;height:20px;"/>
			</div>
			<div style="margin-top:24px;margin-left:160px">
				<input type="password" name="web_password" style="width:194px;height:20px;"/>
			</div>
		</form>
		</td>
		<td rowspan="3" class="login_center_right"></td>
	</tr>
	<tr>
		<td rowspan="2" class="login_center_btn_left"></td>
		<td>
			<img src="<%=basePath%>images/black_bg_login_loginbtn.gif" width="85" height="40" alt="" style="cursor: pointer;" onclick="submitLogin()"></td>
		<td>
			<img src="<%=basePath%>images/black_bg_login_register.jpg" width="92" height="40" alt="" style="cursor: pointer;" onclick="registerUser()"></td>
		<td rowspan="2" class="login_center_btn_right"></td>
	</tr>
	<tr>
		<td colspan="2" class="login_center_btn_bottom">
		<c:if test="${web_login_message != ''}">
			<span style="margin-top:40px;color:red;" id="web_login_message">${web_login_message}</span>
	    </c:if>
		</td>
	</tr>
	<tr>
		<td class="login_bottom_left" ></td>
		<td colspan="4" class="login_bottom_center"></td>
		<td class="login_bottom_right"></td>
	</tr>
</table>
</body>
</HTML>
