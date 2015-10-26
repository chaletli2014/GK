jQuery(document).ready(function($){
	$("#addNewServiceCustomer").click(function(){
		jQuery.ajax({
			url: basePath+"checkCompanyInfo",
			success: function(response){
				var result = response.result;
				if( result != 'Y' ){
					jConfirm("您当前还没有录入公司信息，无法添加客户.是否现在录入？","提醒",function(r){
						if( r ){
							window.location.href = basePath+"editprofile";
						}
					});
				}else{
					window.location.href=basePath+"addserviceCustomer";
				}
			}
		});
	});
});

function deleteCustomer(spCustomerId){
	jConfirm("确定要删除关联客户吗？一旦删除，客户将消失","提醒",function(r){
		if( r ){
			window.location.href = basePath+"deleteSPCustomer?spCustomerId="+spCustomerId;
		}
	});
}
function relateCustomer(spCustomerId){
	jConfirm("确定要发申请给该客户进行关联吗？","提醒",function(r){
		if( r ){
			jQuery.ajax({
				url: basePath+"sendRequesttoCustomer",
				data:{
					spCustomerId : spCustomerId
				}
				,success: function(response){
					var result = response.result;
					var message = response.message;
					if( result != 'Y' ){
						if( '' != message ){
							jAlert(message,"提醒");
						}else{
							jAlert("请求发送失败!","提醒");
						}
					}else{
						jAlert("请求发送成功","信息");
						location.reload();
					}
				}
			});
		}
	});
}