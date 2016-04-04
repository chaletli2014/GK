<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/header.jsp"%>
<script type="text/javascript" src="<%=basePath%>/js/goodsquick/registerUser.js"></script>
<body>
	<div class="banner_wrap" style="overflow-y:scroll;">
		<div class="login_logo">
			<img src="<%=basePath%>images/login_banner.png"  alt="" style="cursor: pointer;">
		</div>
		<div class="login_form" style="top:35%;">
			<div class="login_form_title">
				<h2>注册企业账号</h2>
				<a href="<%=basePath%>login">登录</a>
			</div>
			<div class="login_main register_main">
				<form role="form" class="form-horizontal" action="doRegisterUser" method="post" id="register_form">
					<p>
						<select style="height:24px;" name="industry" id="industry">
							<option>请选择行业分类</option>
							<option>物业</option>
							<option>互联网</option>
							<option>其他</option>
						</select>
					</p>
					<p>
						<select style="height:24px;" name="companyProvince">
							<option>请选择省份</option>
							<option>上海</option>
						</select>
						<select style="height:24px;margin-left:6px;" name="companyCity">
							<option>请选择城市</option>
							<option>上海</option>
						</select>
					</p>
					<p>
						<input type="text" name="companyName" id="companyName" placeholder="企业名称 *"/>
					</p>
					<p>
						<input type="text" name="loginName" id="loginName" placeholder="登录名  *"/>
					</p>
					<p>
						<input type="password" name="password" id="password" placeholder="密码  *"/>
					</p>
					<p>
						<input type="password" name="password2" id="password2" placeholder="确认密码  *"/>
					</p>
					<p>
						<input type="text" name="realName" id="realName" placeholder="姓名  *"/>
					</p>
					<p>
						<input type="text" name="companyEmail" id="companyEmail" placeholder="企业邮箱"/>
					</p>
					<p>
						<input type="text" name="telephone" id="telephone" placeholder="联系电话"/>
					</p>
					<p style="text-align: center;border-radius: 5px;">
						<img id="register_btn" src="<%=basePath%>images/register_btn.png" onclick="return submitRegister()" style="cursor: pointer;width: 196px;">
					</p>
				</form>
			</div>
		</div>
	</div>
	<%@include file="common/login_footer.jsp" %>
</body>
</html>