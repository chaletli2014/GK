<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/registerUser.js"></script>
<body style="background: #F4F4F4;">
	<div class="login_logo">
		<img src="<%=basePath%>images/login_logo2.png"  alt="" style="cursor: pointer;">
		<div style="float:right;">
			<a href="<%=basePath%>login"><img src="<%=basePath%>images/login_link.png"></a>
		</div>
	</div>
	<div class="register_form">
		<div class="register_left"></div>
		<div class="register_right">
			<form role="form" class="form-horizontal" action="doRegisterUser" method="post" id="register_form">
				<div class="register_form_title">企业用户注册</div>
				<div>
					<select style="height:24px;" name="industry" id="industry">
						<option>请选择行业分类</option>
						<option>物业</option>
						<option>互联网</option>
						<option>其他</option>
					</select>
				</div>
				<div>
					<input type="text" name="companyName" id="companyName" placeholder="企业名称 *"/>
				</div>
				<div>
					<select style="height:24px;" name="companyProvince">
						<option>请选择省份</option>
						<option>上海</option>
					</select>
					<select style="height:24px;margin-left:6px;" name="companyCity">
						<option>请选择城市</option>
						<option>上海</option>
					</select>
				</div>
				<div>
					<input type="text" name="loginName" id="loginName" placeholder="登录名  *"/>
				</div>
				<div>
					<input type="password" name="password" id="password" placeholder="密码  *"/>
				</div>
				<div>
					<input type="password" name="password2" id="password2" placeholder="确认密码  *"/>
				</div>
				<div>
					<input type="text" name="companyEmail" id="companyEmail" placeholder="企业邮箱"/>
				</div>
				<div>
					<input type="text" name="realName" id="realName" placeholder="姓名"/>
				</div>
				<div>
					<input type="text" name="telephone" id="telephone" placeholder="联系电话"/>
				</div>
				<div>
					<img id="register_btn" src="<%=basePath%>images/register_btn.png" onclick="return submitRegister()" style="cursor: pointer;width: 196px;">
				</div>
			</form>
		</div>
	</div>
	<%@include file="common/login_footer.jsp" %>
</body>
</html>