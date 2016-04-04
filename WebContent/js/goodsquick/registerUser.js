function submitRegister(){
	$("#register_btn").attr('src', basePath+"images/register_btn_2.png");
	if($("#companyName").val() == ''){
		loginAlert("企业名称不能为空","提醒");
		return false;
	}
	if($("#loginName").val() == ''){
		loginAlert("登录名不能为空","提醒");
		return false;
	}
	if($("#companyName").val() == ''){
		loginAlert("企业名称不能为空","提醒");
		return false;
	}
	if($("#password").val() == ''){
		loginAlert("密码不能为空","提醒");
		return false;
	}
	if($("#password").val() != $("#password2").val() ){
		loginAlert("两次密码不一致","提醒");
		return false;
	}
	if($("#realName").val() == ''){
		loginAlert("姓名不能为空","提醒");
		return false;
	}
	
	$("#register_form").submit();
}
function loginAlert(message,title){
	jAlert(message,title,function(){
		$("#register_btn").attr('src', basePath+"images/register_btn.png");
	});
}