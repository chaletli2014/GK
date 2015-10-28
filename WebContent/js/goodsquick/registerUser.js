function submitRegister(){
	$("#register_btn").attr('src', basePath+"images/register_btn_2.png");
	var regis = true;
	if($("#companyName").val() == ''){
		jAlert("企业名称不能为空","提醒");
		regis = false;
	}
	if($("#loginName").val() == ''){
		jAlert("登录名不能为空","提醒");
		regis = false;
	}
	if($("#companyName").val() == ''){
		jAlert("企业名称不能为空","提醒");
		regis = false;
	}
	if($("#password").val() == ''){
		jAlert("密码不能为空","提醒");
		regis = false;
	}
	if($("#password").val() != $("#password2").val() ){
		jAlert("两次密码不一致","提醒");
		regis = false;
	}
	
	$("#register_btn").attr('src', basePath+"images/register_btn.png");
	if(regis){
		$("#register_form").submit();
	}
}