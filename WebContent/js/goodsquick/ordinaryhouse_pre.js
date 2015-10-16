jQuery(document).ready(function($){
	$(".product_link").click(function(){
		var curPath=window.document.location.href;
		var pathName=window.document.location.pathname;
		var pos=curPath.indexOf(pathName);  
	    var localhostPath=curPath.substring(0,pos);
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

	    var fullPath = localhostPath + projectName;
	    
	    window.location.href=fullPath+"/newProductPreOne?type="+this.title;
	});
	
	$("#finishWizard").click(function(){
		if(!$("#buildingName") || $("#buildingName").val() == ''){
			jAlert("产品名称必填，否则无法完成注册","注意");
			return false;
		}if(!$("#province") || $("#province").val() == '' || !$("#city") || $("#city").val() == ''){
			jAlert("产品所在地域必填，否则无法完成注册","注意");
			return false;
		}if(!$("#location") || $("#location").val() == ''){
			jAlert("坐落位置必填，否则无法完成注册","注意");
			return false;
		}else{
			$("#addordinaryhouse_form").submit();
		}
	});
});