<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="common/login_header.jsp"%>
<body>
	<div class="g-banner-wrap">
        <div class="g-banner">
            <div class="m-nav">
                <a class="logo" href="#"><img src="images/logo.png"></a>
                <a href="./index.html"><em></em><b>首页</b></a>
                <a href="javascript:void(0);" class="z-crt zi4">
                <em></em><b>登录/注册</b></a>
            </div>
            <div class="m-banner">
                <div class="g-login" id="gLogin">
                    <div class="m-login m-login-1" id="mLogin">
                        <div class="header"><h2>登录物库系统</h2><%--<a id="aToReg">注册&gt;</a>--%></div>
                        <div class="main">
                        	<form action="dologin" name="loginForm" id="loginForm" method="post">
	                            <p class="email"><input name="web_username" placeholder="账号 /电子邮箱" type="text" data-old=""></p>
	                            <p class="pass"><input placeholder="密码" type="password" name="web_password"></p>
	                            <%--
	                            <p class="ckcode f-dn"><input placeholder="验证码" type="text"><a><img id="imgReGetCode" src="" alt=""></a></p>
	                             --%>
	                             <p>
									<span style="margin-top:40px;color:red;" id="web_login_message">&nbsp;<c:if test="${web_login_message != ''}">${web_login_message}</c:if></span>
	                             </p>
	                            <p><button type="button" id="btnLogin">登录</button></p>
                        	</form>
                            <p class="link">
                            	<label><input type="checkbox" checked="checked" id="rembMyPass">记住我</label>
                            	<a class="lgpass" href="">忘记密码？</a>
                            </p>
                        </div>
                    </div>
                    <div class="m-login m-reg" id="divReg">
                        <div class="m-banner-txt">
                          <img src="images/step-1.png" alt="">
                        </div>
                        <div class="header"><h2>注册大物库账号</h2><a id="aToLogin">登录&gt;</a></div>
                        <div class="main" id="divReg">
                            <p class="mobile"><input id="mobileInput" placeholder="手机号码" type="text" maxlength="11"><span>手机号码</span></p>
                            <p class="mscode2">
                                <span><input id="codeInput" placeholder="短信验证码" type="text" maxlength="6"></span>
                                <button type="button" id="btnGetMsg">发送</button>
                            </p>
                            <p class="mscode1" style="display:none;">
                                <a><img id="imgCode" src=""></a>
                                <span><input id="inpImgCode" placeholder="请输入防刷校证码" type="text" maxlength="4"></span>
                                <button type="button" id="btnCheck">验证</button>
                                <img class="checkok" src="" alt="">
                            </p>
                            <p class="ename"><input placeholder="企业名称" type="text" maxlength="50"></p>
                            <p class="adminame"><input placeholder="管理员姓名" type="text" maxlength="16"></p>
                            <p class="email"><input placeholder="邮箱地址" type="text" maxlength="32"></p>
                            <p class="pass"><input id="inpAdminPass" placeholder="管理员密" type="password"></p>
                            <p class="err" id="pERR">账号或密码错误</p>
                            <p><button type="button" id="btnReg">提交</button></p>
                            <p class="link"><label>点击提交，视为同意遵守<a class="u-protocol" href="#" target="_blank">大物库平台注册协议</a></label></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<%@include file="common/login_footer.jsp" %>
</body>
</HTML>
