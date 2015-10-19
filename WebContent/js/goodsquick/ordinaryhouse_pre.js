jQuery(document).ready(function($){
	var basePath = $("#basePath").val();
	
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
			jQuery.ajax({
				url: basePath+"checkBuildingName",
				data:{
					houseName : $("#buildingName").val()
				}
				,success: function(response){
					var result = response.result;
					var obj = response.obj;
					
					if( result == 'own' ){
						jAlert("您已经拥有了该产品，无法继续添加","提醒");
						return false;
					}else if( result == 'sent' ){
						jAlert("您已经申请过该产品","提醒");
						return false;
					}else if( result == 'Y' ){
						jConfirm("该产品已经存在无法继续添加，是否直接申请权限？","信息",function(r) {
					    	if(r){
					    		jQuery.ajax({
					    			url: basePath+"sendRequestToBuildingName",
					    			data:{
					    				houseName : $("#buildingName").val(),
					    				houseOwner: obj.createUser
					    			}
					    			,success: function(response){
					    				var result = response.result;
					    				if( result == 'Y' ){
					    					jAlert("申请发送成功，等待管理员审批","信息",function(){
					    						$("#buildingName").val('');
						    					jQuery('.close').click();
					    					});
					    					return false;
					    				}
					    			}
					    		});
					    	}
						});
					}else if( result == 'N' ){
						$("#addordinaryhouse_form").submit();
					}else if( result == 'E' ){
						jAlert(response.message,"提醒");
						return false;
					}
				}
			});
			return false;
		}
	});
	
	$("#servicefinishWizard").click(function(){
		if(!$("#serviceName") || $("#serviceName").val() == ''){
			jAlert("产品名称必填，否则无法完成注册","注意");
			return false;
		}if(!$("#serviceRangeCode") || $("#serviceRangeCode").val() == ''){
			jAlert("服务范围必填，否则无法完成注册","注意");
			return false;
		}if(!$("#price") || $("#price").val() == ''){
			jAlert("服务价格必填，否则无法完成注册","注意");
			return false;
		}else{
			$("#addservice_form").submit();
		}
	});
	
	$("#buildingName").blur(function(){
		var houseNameParam = this.value;
		jQuery.ajax({
			url: basePath+"checkBuildingName",
			data:{
				houseName : houseNameParam
			}
			,success: function(response){
				var result = response.result;
				var obj = response.obj;
				
				if( result == 'own' ){
					jAlert("您已经拥有了该产品，无法继续添加","提醒");
				}else if( result == 'sent' ){ 
					jAlert("您已经申请过该产品，无法继续发送申请","提醒");
				}else if( result == 'E' ){
					jAlert(response.message,"提醒");
				}else if( result == 'Y' ){
					jConfirm("该产品已经存在无法继续添加，是否直接申请权限？","信息",function(r) {
				    	if(r){
				    		jQuery.ajax({
				    			url: basePath+"sendRequestToBuildingName",
				    			data:{
				    				houseName : houseNameParam,
				    				houseOwner: obj.createUser
				    			}
				    			,success: function(response){
				    				var result = response.result;
				    				if( result == 'Y' ){
				    					jAlert("申请发送成功，等待管理员审批","信息",function(){
				    						$("#buildingName").val('');
					    					jQuery('.close').click();
				    					});
				    				}
				    			}
				    		});
				    	}
					});
				}
			}
		});
	});
});